/**
 * 建立FlowView工作空间，装载建立工作空间所需要的资源。
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)</p>
 * @author mios
 */

/**
 * FlowView的工作空间
 */
function FlowViewWorkSpace() {
}

FlowViewWorkSpace.BASE_PATH = "/scripts";
FlowViewWorkSpace.FLOW_PATH = FlowViewWorkSpace.BASE_PATH + "/flowview";


/**
 * 建立工作空间
 */
FlowViewWorkSpace.build = function (webAppPath) {
 	//引入所需要的资源，资源加载顺序不能更改
	FlowViewWorkSpace.BASE_PATH = webAppPath + FlowViewWorkSpace.BASE_PATH;
    FlowViewWorkSpace.FLOW_PATH = webAppPath + FlowViewWorkSpace.FLOW_PATH;
	//
	BuildLibrary.loadCSS(FlowViewWorkSpace.FLOW_PATH + "/css/flowview.css", "GB2312");

	//
    BuildLibrary.loadJS(FlowViewWorkSpace.BASE_PATH + "/util/Toolkit.js", "GB2312");
    BuildLibrary.loadJS(FlowViewWorkSpace.BASE_PATH + "/base/eoms.js", "UTF-8");
    BuildLibrary.loadJS(FlowViewWorkSpace.BASE_PATH + "/util/util.js");
    BuildLibrary.loadJS(FlowViewWorkSpace.BASE_PATH + "/util/geom/Point.js", "UTF-8");

    BuildLibrary.loadJS(FlowViewWorkSpace.FLOW_PATH + "/flowview.js", "utf-8");

};

/**
 * 资源加载
 */
function BuildLibrary() {
}
BuildLibrary.loadJS = function (url, charset) {
    if (!charset) {
        charset = "UTF-8";
    }
    var charsetProperty = " charset=\"" + charset + "\" ";
    document.write("<script type=\"text/javascript\" src=\"" + url + "\" onerror=\"alert('Error loading ' + this.src);\"></script>");
};
BuildLibrary.loadCSS = function (url, charset) {
    if (!charset) {
        charset = "UTF-8";
    }
    var charsetProperty = " charset=\"" + charset + "\" ";
    document.write("<link href=\"" + url + "\" type=\"text/css\" rel=\"stylesheet\" onerror=\"alert('Error loading ' + this.src);\"" + charsetProperty + "/>");
};

