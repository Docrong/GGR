/**
 * Cookie相关方法
 * @namespace
 * @example
 *  将jack保存到username中,30天后过期:
 *  var c = eoms.util.Cookie;
 *  c.set("username","jack",30);
 */
eoms.util.Cookie = {
	/**
	 * 取出cookie值
	 * @param {String} n cookie名称
	 * @return {String} cookie值
	 */
	get : function(n){
	    var dc = "; "+document.cookie+"; ";
	    var coo = dc.indexOf("; "+n+"=");
	    if (coo!=-1){
		    var s = dc.substring(coo+n.length+3,dc.length);
		    return unescape(s.substring(0, s.indexOf("; ")));
	    }else{
		    return null;
	    }
    },
    /**
     * 保存cookie值
     * @param {String} name cookie名称
     * @param {String} value cookie值
     * @param {Number} expires 过期天数
     */
    set : function(name,value,expires){
        var expDays = expires*24*60*60*1000;
        var expDate = new Date();
        expDate.setTime(expDate.getTime()+expDays);
        var expString = expires ? "; expires="+expDate.toGMTString() : "";
        var pathString = ";path=/";
        document.cookie = name + "=" + escape(value) + expString + pathString;
    },
    /**
     * 删除cookie值
     * @param {String} n 要删除的cookie名称
     */
    del : function(n){
	    var exp = new Date();
	    exp.setTime(exp.getTime() - 1);
	    var cval=this.get(n);
	    if(cval!=null) document.cookie= n + "="+cval+";expires="+exp.toGMTString();
    }
};

