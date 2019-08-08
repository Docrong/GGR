/**
 * portal adapter for AJAX
 */

(function () {
    var n = "IV_JCT";
    var dc = "; " + document.cookie + "; ";
    var coo = dc.indexOf("; " + n + "=");
    if (coo != -1) {
        var s = dc.substring(coo + n.length + 3, dc.length);
        window.portalURIFix = unescape(s.substring(0, s.indexOf("; ")));
    } else {
        window.portalURIFix = null;
    }

    window.getPortalAdaptURI = function (uri) {
        if (portalURIFix) {
            var r = new RegExp(portalURIFix, "g");
            var s = uri.match(r);
            if (s && s.length < 2) uri = portalURIFix + uri;
        }
        return uri;
    };
    window.getPortalAdaptCtn = function (t) {
        var re = new RegExp("document.cookie = \"IV_JCT=%2Feoms35; path=/\";");
        return portalURIFix && re.test(t)
            ? t.substring(99).trim()
            : t;
    }
})();

Ext.lib.Ajax.request = function (method, uri, cb, data, options) {
    if (options) {
        var hs = options.headers;
        if (hs) {
            for (var h in hs) {
                if (hs.hasOwnProperty(h)) {
                    this.initHeader(h, hs[h], false);
                }
            }
        }
        if (options.xmlData) {
            this.initHeader('Content-Type', 'text/xml', false);
            method = 'POST';
            data = options.xmlData;
        }
    }

    //portal url fix
    uri = getPortalAdaptURI(uri);

    return this.asyncRequest(method, uri, cb, data);
};

Ext.lib.Ajax.createResponseObject = function (o, callbackArg) {
    var obj = {};
    var headerObj = {};

    try {
        var headerStr = o.conn.getAllResponseHeaders();
        var header = headerStr.split('\n');
        for (var i = 0; i < header.length; i++) {
            var delimitPos = header[i].indexOf(':');
            if (delimitPos != -1) {
                headerObj[header[i].substring(0, delimitPos)] = header[i].substring(delimitPos + 2);
            }
        }
    } catch (e) {
    }

    obj.tId = o.tId;
    obj.status = o.conn.status;
    obj.statusText = o.conn.statusText;
    obj.getResponseHeader = headerObj;
    obj.getAllResponseHeaders = headerStr;

    // portal response fix
    obj.responseText = getPortalAdaptCtn(o.conn.responseText);

    obj.responseXML = o.conn.responseXML;

    if (typeof callbackArg !== undefined) {
        obj.argument = callbackArg;
    }

    return obj;
};

Ajax.Request = function () {
    if (arguments.length < 2) return;
    var para = {asynchronous: true, method: "GET", parameters: ""};
    for (var key in arguments[1]) {
        para[key] = arguments[1][key];
    }
    var _x = Ajax.xmlhttp();
    var _url = arguments[0];
    if (para["parameters"].length > 0) para["parameters"] += '&_=';
    if (para["method"].toUpperCase() == "GET") _url += (_url.match(/\?/) ? '&' : '?') + para["parameters"];

    //portal uri fix
    _url = getPortalAdaptURI(_url);

    _x.open(para["method"].toUpperCase(), _url, para["asynchronous"]);
    _x.onreadystatechange = function () {
        if (_x.readyState == 4) {
            if (_x.status == 200 || _x.status == 0) {

                // portal response fix
                _x.responseText = getPortalAdaptCtn(_x.responseText);

                para["onComplete"] ? para["onComplete"](_x) : "";
            } else {
                para["onError"] ? para["onError"](_x) : "";
            }
        }
    };
    if (para["method"].toUpperCase() == "POST") _x.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    for (var ReqHeader in para["setRequestHeader"]) {
        _x.setRequestHeader(ReqHeader, para["setRequestHeader"][ReqHeader]);
    }
    _x.send(para["method"].toUpperCase() == "POST" ? (para["postBody"] ? para["postBody"] : para["parameters"]) : null);

};