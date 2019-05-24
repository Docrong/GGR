/**
 * @namespace eoms
 * @description EOMS javascript 常用类库
 * @author mios 2008.12.29
 * @version 0.6 文档更新
 */

eoms = {
	/**
	 * 系统的context路径,如"/eoms"
	 * @type String
	 */	
	appPath : "",
	/**
	 * 浏览器为IE则返回真
	 * @type Boolean
	 */
	isIE : (/*@cc_on!@*/false),
	/**
	 * 浏览器为Firefox则返回真
	 * @type Boolean
	 */
	isFF : navigator.userAgent.toLowerCase().indexOf("firefox") > -1,
	/**
	 * 浏览器为IE6则返回真
	 * @type Boolean
	 */
	isIE6 : navigator.userAgent.toLowerCase().indexOf("msie 6") > -1,
	/**
	 * 浏览器为IE7则返回真
	 * @type Boolean
	 */
	isIE7 : navigator.userAgent.toLowerCase().indexOf("msie 7") > -1,

	/**
 	 * 用于拷贝一个对象的属性到另一个对象
 	 * @param {Object} o 接受属性的对象
 	 * @param {Object} c 要拷贝其属性的config对象
 	 * @param {Object} [defaults] 缺省属性对象,如果有此参数，会先拷贝此参数的属性
 	 * @return {Object} 返回新对象
	 */
	apply : function(o, c, defaults){
	    if(defaults){
	        eoms.apply(o, defaults);
	    }
	    if(o && c && typeof c == 'object'){
	        for(var p in c){
	            o[p] = c[p];
	        }
	    }
	    return o;
	},
	
	/**
	 * 拷贝一个对象的属性到另一个属性，如果目标对象已有这个属性，则不拷贝
	 * @param {Object} o 接受属性的对象
	 * @param {Object} c 要拷贝其属性的config对象
	 * @return {Object} 返回新对象
	 */
	applyIf : function(o, c){
        if(o && c){
            for(var p in c){
                if(typeof o[p] == "undefined"){ o[p] = c[p]; }
            }
        }
        return o;
    },
    
	/**
	 * 在firefox的控制台打印调试信息
	 * @param {Object} o 要打印的对象
	 * @param {Object} [o, etc]
	 */	
	log:function(){
		if(typeof console != "undefined") {
			for (var i=0; i<arguments.length; i++){
				console.log(arguments[i]);
			}				
		}
	},
	
    /**
     * 把JSON字符串转换成对象
     * @param {String} json JSON格式字符串
     * @example
     *  var user = eoms.JSONDecode("{name:'jack'}");
     *  //user.name = "jack";
     */
    JSONDecode : function(json){
		return eval("(" + json + ')');
	},
	
	/**
	 * 停止事件
	 * @param {Event} e 事件对象
	 * @example
	 *  //停止theform表单的提交事件
	 *  eoms.$("theform").onsubmit=function(e){
	 *    var event = e || window.event;
	 *    eoms.stopEvent(event);
	 *  };
	 */
	stopEvent : function(e){
		if (e.preventDefault) {
	      e.preventDefault();
	      e.stopPropagation();
	    } else {
	      e.returnValue = false;
	      e.cancelBubble = true;
	    }
	},
	
    /**
     * 用于创建名字空间
     * @param {String} namespace
     * @param {String} [namespace, etc]
     * @example
     *  eoms.namespace("world.china","com.boco.eoms");
     */
	namespace : function(){
        var a=arguments, o=null, i, j, d, rt;
        for (i=0; i<a.length; ++i) {
            d=a[i].split(".");
            rt = d[0];
            eval('if (typeof ' + rt + ' == "undefined"){' + rt + ' = {};} o = ' + rt + ';');
            for (j=1; j<d.length; ++j) {
                o[d[j]]=o[d[j]] || {};
                o=o[d[j]];
            }
        }
    },
    /**
     * 载入CSS
     * @param {String} url css文件的url地址
     * @example
     *  eoms.loadCSS(eoms.appPath+"/scripts/form/popcalendar.css");
     */
    loadCSS : function(url){
		var head = document.getElementsByTagName("head")[0] || document.documentElement,
			link = document.createElement("link");
		link.type = "text/css";
		link.rel = "stylesheet";
		link.href = url;
		head.insertBefore(link, head.firstChild);
    },
    /**
     * 载入JS
     * @param {String} url js文件的url地址
     * @param {Function} [fn] 载入后执行的函数
     * @example
     *  eoms.loadJS(eoms.appPath+"/scripts/ExtTable.js",function(){
	 *    new eoms.ExtTable("list").init();
	 *  });
     */
    loadJS : function(url,fn,scope){
	    var head = document.getElementsByTagName('head')[0],
			js = document.createElement('script');
	    js.type = "text/javascript";
	    js.src = url;
	    head.insertBefore(js, head.firstChild);
		
	    if(typeof fn !="function") return false;
	    
	    if (!eoms.isIE) {
	        //Firefox2、Firefox3、Safari3.1+、Opera9.6+ support js.onload
	        js.onload = callback;
	    } else {
	        //IE6、IE7 support js.onreadystatechange
	        js.onreadystatechange = function () {
	            if (js.readyState == 'loaded' || js.readyState == 'complete') {
	            	callback();
	            }
	        }
	    }
		function callback(){
            fn.call(scope);
	    	head.removeChild(js);
		}
    	return false;
    },
	$A : function(list){
		var arr = [];
		for (var i=0,len=list.length; i<len; i++){
			arr[i] = list[i];
		}
		return arr;
	},
	/**
	 * 将字符串(链接地址)解码
	 * @param {String} str 字符串
	 * @return {String} 解码后的字符串
	 * @example
	 *  eoms.$D("eoms%3Fname%3D%E7%94%B5%E5%AD%90%E8%BF%90%E7%BB%B4");
	 *  //"eoms?name=电子运维"
	 */
    $D : function(str){return str.URLDecode();},
	/**
	 * 将字符串(链接地址)编码
	 * @param {String} str 字符串
	 * @return {String} 编码后的字符串
	 * @example
	 *  eoms.$E("eoms?name=电子运维");
	 *  //"eoms%3Fname%3D%E7%94%B5%E5%AD%90%E8%BF%90%E7%BB%B4"
	 */
    $E : function(str){return str.URLEncode();},
    $V : function(id){return $(id).value;}
};

eoms.namespace("eoms.util","eoms.form","eoms.layout");

/**
 * 查询一个对象在数组中的位置
 * @param {Object} o
 * @return {Number} 该对象在数组中的位置，没有找到则返回 -1
 */
Array.prototype.indexOf = function(o){
	for (var i=0, len=this.length; i<len; i++){
		if (this[i]==o)	return i;
    }
 	return -1;
};

/**
 * 为每个数组元素绑定一个函数fn并执行。
 * <br/>fn的第一个参数为数组元素，第2个参数为序数
 * @param {Function} fn
 * @return {Array}
 * @example
 *  ["zero","one","two"].each(function(item,i){alert(i+"="+item)});
 *  //"0=zero","1=one","2=two"
 */
Array.prototype.each = function(fn){
		for (var i=0,len=this.length; i<len; i++){
			fn(this[i],i);
		}
		return this;
};

/**
 * 查找并删除数组中的一个元素对象
 * @param {Object} o
 */
Array.prototype.remove = function (o) {
    	var index = this.indexOf(o);
   		if(index != -1){
			this.splice(index, 1);
		}
};

/**
 * 去掉首尾空格，如果有str参数，则去掉字符串开头和结尾的str字符
 * @param {String} [str] 去掉字符串首尾的指定参数字符，为Null则去掉字符串首尾空格
 * @return {String} 新字符串
 */
String.prototype.trim = function(str){
	var _re,str = str || " ";
	typeof(str)=="string" 
		?(str == " " ?_re = /(^(\s|　)*)|((\s|　)*$)/g : _re = new RegExp("(^"+str+"*)|("+str+"*$)","g")) 
		: _re = str;
	return this.replace(_re,"");
};

/**
 * 骆驼写法，即转换font-size为fontSize
 * @return {String} 新字符串
 */
String.prototype.camelize = function(){
	return this.replace(/(-\S)/g,function($1){return $1.toUpperCase().substring(1,2)});
};	

/**
 * 查找字符串中是否含有以f隔开的子字符串s,通常用于查找className
 * @param {String} s 要查找的子字符串
 * @param {Stinrg} [f] 用做分隔符的字符,缺省为""
 * @retrun {Boolean}
 * @example 
 *  "red green".hasSubString("red");
 *  //true
 */
String.prototype.hasSubString = function(s,f){
	if(!f) f="";
	return (f+this+f).indexOf(f+s+f)==-1?false:true;
};

/**
 * 查找字符串中,是否至少含有一个数组arr中的子字符串
 * @param {Array} arr 数组,元素为要查找的子字符串
 * @param {String} [f] 用做分隔符的字符,缺省为""
 * @retrun {Boolean}
 * @example 
 *  "red green".hasSubStrInArr(["red","yellow"]);
 *  //true
 */
String.prototype.hasSubStrInArr = function(arr,f){
	for(var i=0; i<arr.length; i++){
		if(this.hasSubString(arr[i],f)){return true;}
	}
	return false;
}	    

/**
 * 将字符串(链接地址)编码
 * @return {String} 新字符串
 * @example 
 *  "eoms?name=电子运维".URLEncode();
 *  //"eoms%3Fname%3D%E7%94%B5%E5%AD%90%E8%BF%90%E7%BB%B4"
 */
String.prototype.URLEncode = function(){ return encodeURIComponent(this);};

/**
 * 将字符串(链接地址)解码
 * @return {String} 新字符串
 * @example 
 *  "eoms%3Fname%3D%E7%94%B5%E5%AD%90%E8%BF%90%E7%BB%B4".URLDecode();
 *  //"eoms?name=电子运维"
 */
String.prototype.URLDecode = function(){ return decodeURIComponent(this);};

/**
 * @namespace eoms.Element
 * @description 
 *  DOM元素常用方法,要通过eoms.$("id")将方法绑定到元素id上使用。
 * @example
 *  隐藏元素：
 *  eoms.$("id").hide();
 * 
 *  显示元素并给元素添加一个className：
 *  eoms.$("id").show().addClass("red");
 */
eoms.Element = {
	isComposite : true,
	/**
 	 * 隐藏页面元素
 	 * @example
 	 *  eoms.$("btn").hide();
	 */
	hide : function(){this.style.display="none"; return this;},
	
	/**
	 * 显示页面元素
 	 * @example
 	 *  eoms.$("btn").show();
	 */
	show : function(){this.style.display=""; return this;},
	/**
	 * 切换页面元素的隐藏和显示
 	 * @example
 	 *  eoms.$("btn").toggle();
	 */
	toggle : function(){this.getStyle("display")=="none"?this.show():this.hide(); return this;},
	
	/**
	 * 取得元素的style属性值
	 * @param {String} s style属性名
	 * @return {String} 属性值
	 * @example
	 *  eoms.$("btn").getStyle("margin-left");
	 */
	getStyle : function(s){
		var value = this.style[s=="float"?(eoms.isIE?"styleFloat":"cssFloat"):s.camelize()];
		if (!value){
			if (this.currentStyle){
				value = this.currentStyle[s.camelize()];
			}else if (document.defaultView && document.defaultView.getComputedStyle){
				var css = document.defaultView.getComputedStyle(this, null);
				value = css ? css.getPropertyValue(s) : null;
			}
		}
		return value;
	},
	/**
	 * 设置元素的style属性
	 * @param {String} s style字符串
	 * @return {Element}
	 * @example
	 *  eoms.$("btn").setStyle("width:80px;font-size:16px");
	 */
	setStyle : function(s){
		var sList = s.split(";");
		for (var i=0,j; j=sList[i]; i++){
			var k = j.split(/:(?!\/\/)/g);
			var key = k[0].trim();
			key=key=="float"?(eoms.isIE()?"styleFloat":"cssFloat"):key.camelize();
			this.style[key] = k[1].trim();
		}
		return this;
	},
	/**
	 * 查找元素是否含有指定的className
	 * @param {String} c className
	 * @return {Boolean}
	 * @example
	 *  eoms.$("btn").hasClass("hide");
	 */
	hasClass : function(c){return this.className.hasSubString(c," ");},
	
	/**
	 * 如果元素没有指定的className,则给它添加这个className
	 * @param {String} c className
	 * @return {HTMLElement}
	 * @example
	 *  eoms.$("btn").addClass("hide");
	 */
	addClass : function(c){if(!this.hasClass(c)){this.className+=" "+c};return this;},

	/**
	 * 如果元素有指定的className,则删除这个className
	 * @param {String} c className
	 * @return {HTMLElement}
	 * @example
	 *  eoms.$("btns").removeClass("hide");
	 */			
	removeClass : function(c){if(this.hasClass(c)){this.className = (" "+this.className+" ").replace(" "+c+" "," ").trim();} return this;},

	/**
	 * 切换元素的一个指定的className
	 * @param {String} c className
	 * @return {HTMLElement}
	 * @example
	 *  eoms.$("btn").toggleClass("hide");
	 */			
	toggleClass : function(c){if(this.hasClass(c)){this.removeClass(c);}else{this.addClass(c);};return this;},
	
	/**
	 * 给元素增加一个事件监听
	 * @param {String} eventName 事件名,如 "click"
	 * @param {Function} fn 事件绑定的函数,第1个参数为该元素，第2个参数为event对象
	 * @param {Object} scope 该函数执行时的作用域
	 * @example
	 *  //当一个按钮被点击时,弹出该按钮的文字
	 *  eoms.$("btn").on("click",function(el,event){alert(el.value)});
	 */				
	on : function(eventName, fn, scope){
		var h = function(e){
			fn.call(scope, e.target || e.srcElement, e);
		};
		if (window.addEventListener) {
			this.addEventListener(eventName, h, (false));
		} else if (window.attachEvent) {
			this.attachEvent("on" + eventName, h);
		}
	},
	
	/**
	 * 以本元素为根执行一个css查询
	 * @param {String} selector 选择符路径或xpath，可用逗号隔开进行分组
	 * @return {Array} 查询结果
	 * @see eoms.select
	 */				
	select : function(selector){return eoms.DomQuery.select(selector,this);},
	
	/**
	 * 如果本元素是INPUT/TEXTAREA/SELECT/BUTTON,则设置本元素的value值，否则用innerHTML填充本元素
	 * @param {String} str value值或HTML代码
	 * @example
	 *  //修改按钮文字为审核
	 *  eoms.$("btn").update("审核");
	 */				
	update : function(str){
		if(this.tagName.hasSubStrInArr(["INPUT","TEXTAREA","SELECT","BUTTON"])){
			this.value = str || '';
		}
		else{
			this.innerHTML = str || '';
		}
		return this;
	}
};

/**
 * 获取页面中某个元素，并绑定一些常用方法
 * @param {String/HTMLElement} el 元素或元素id
 * @see eoms.element
 * @return {HTMLElement} 绑定方法后的元素
 */
eoms.$ = function(el){
	if(!el){ return null; }
	var elem = typeof el == "string" ? document.getElementById(el) : el;
	if (!elem){return null; }
	if (elem.tagName && !elem['isComposite']){
		eoms.apply(elem,eoms.Element);
	}
	return elem;
};

eoms.DomQuery=function(){var cache={},simpleCache={},valueCache={};var nonSpace=/\S/;var trimRe=/^\s+|\s+$/g;var tplRe=/\{(\d+)\}/g;var modeRe=/^(\s?[\/>+~]\s?|\s|$)/;var tagTokenRe=/^(#)?([\w-\*]+)/;var nthRe=/(\d*)n\+?(\d*)/,nthRe2=/\D/;function child(p,index){var i=0;var n=p.firstChild;while(n){if(n.nodeType==1){if(++i==index){return n}}n=n.nextSibling}return null}function next(n){while((n=n.nextSibling)&&n.nodeType!=1){}return n}function prev(n){while((n=n.previousSibling)&&n.nodeType!=1){}return n}function children(d){var n=d.firstChild,ni=-1;while(n){var nx=n.nextSibling;if(n.nodeType==3&&!nonSpace.test(n.nodeValue)){d.removeChild(n)}else{n.nodeIndex=++ni}n=nx}return this}function byClassName(c,a,v){if(!v){return c}var r=[],ri=-1,cn;for(var i=0,ci;ci=c[i];i++){if((" "+ci.className+" ").indexOf(v)!=-1){r[++ri]=ci}}return r}function attrValue(n,attr){if(!n.tagName&&typeof n.length!="undefined"){n=n[0]}if(!n){return null}if(attr=="for"){return n.htmlFor}if(attr=="class"||attr=="className"){return n.className}return n.getAttribute(attr)||n[attr]}function getNodes(ns,mode,tagName){var result=[],ri=-1,cs;if(!ns){return result}tagName=tagName||"*";if(typeof ns.getElementsByTagName!="undefined"){ns=[ns]}if(!mode){for(var i=0,ni;ni=ns[i];i++){cs=ni.getElementsByTagName(tagName);for(var j=0,ci;ci=cs[j];j++){result[++ri]=ci}}}else{if(mode=="/"||mode==">"){var utag=tagName.toUpperCase();for(var i=0,ni,cn;ni=ns[i];i++){cn=ni.children||ni.childNodes;for(var j=0,cj;cj=cn[j];j++){if(cj.nodeName==utag||cj.nodeName==tagName||tagName=="*"){result[++ri]=cj}}}}else{if(mode=="+"){var utag=tagName.toUpperCase();for(var i=0,n;n=ns[i];i++){while((n=n.nextSibling)&&n.nodeType!=1){}if(n&&(n.nodeName==utag||n.nodeName==tagName||tagName=="*")){result[++ri]=n}}}else{if(mode=="~"){for(var i=0,n;n=ns[i];i++){while((n=n.nextSibling)&&(n.nodeType!=1||(tagName=="*"||n.tagName.toLowerCase()!=tagName))){}if(n){result[++ri]=n}}}}}}return result}function concat(a,b){if(b.slice){return a.concat(b)}for(var i=0,l=b.length;i<l;i++){a[a.length]=b[i]}return a}function byTag(cs,tagName){if(cs.tagName||cs==document){cs=[cs]}if(!tagName){return cs}var r=[],ri=-1;tagName=tagName.toLowerCase();for(var i=0,ci;ci=cs[i];i++){if(ci.nodeType==1&&ci.tagName.toLowerCase()==tagName){r[++ri]=ci}}return r}function byId(cs,attr,id){if(cs.tagName||cs==document){cs=[cs]}if(!id){return cs}var r=[],ri=-1;for(var i=0,ci;ci=cs[i];i++){if(ci&&ci.id==id){r[++ri]=ci;return r}}return r}function byAttribute(cs,attr,value,op,custom){var r=[],ri=-1,st=custom=="{";var f=eoms.DomQuery.operators[op];for(var i=0,ci;ci=cs[i];i++){var a;if(st){a=eoms.DomQuery.getStyle(ci,attr)}else{if(attr=="class"||attr=="className"){a=ci.className}else{if(attr=="for"){a=ci.htmlFor}else{if(attr=="href"){a=ci.getAttribute("href",2)}else{a=ci.getAttribute(attr)}}}}if((f&&f(a,value))||(!f&&a)){r[++ri]=ci}}return r}function byPseudo(cs,name,value){return eoms.DomQuery.pseudos[name](cs,value)}var isIE=window.ActiveXObject?true:false;eval("var batch = 30803;");var key=30803;function nodupIEXml(cs){var d=++key;cs[0].setAttribute("_nodup",d);var r=[cs[0]];for(var i=1,len=cs.length;i<len;i++){var c=cs[i];if(!c.getAttribute("_nodup")!=d){c.setAttribute("_nodup",d);r[r.length]=c}}for(var i=0,len=cs.length;i<len;i++){cs[i].removeAttribute("_nodup")}return r}function nodup(cs){if(!cs){return[]}var len=cs.length,c,i,r=cs,cj,ri=-1;if(!len||typeof cs.nodeType!="undefined"||len==1){return cs}if(isIE&&typeof cs[0].selectSingleNode!="undefined"){return nodupIEXml(cs)}var d=++key;cs[0]._nodup=d;for(i=1;c=cs[i];i++){if(c._nodup!=d){c._nodup=d}else{r=[];for(var j=0;j<i;j++){r[++ri]=cs[j]}for(j=i+1;cj=cs[j];j++){if(cj._nodup!=d){cj._nodup=d;r[++ri]=cj}}return r}}return r}function quickDiffIEXml(c1,c2){var d=++key;for(var i=0,len=c1.length;i<len;i++){c1[i].setAttribute("_qdiff",d)}var r=[];for(var i=0,len=c2.length;i<len;i++){if(c2[i].getAttribute("_qdiff")!=d){r[r.length]=c2[i]}}for(var i=0,len=c1.length;i<len;i++){c1[i].removeAttribute("_qdiff")}return r}function quickDiff(c1,c2){var len1=c1.length;if(!len1){return c2}if(isIE&&c1[0].selectSingleNode){return quickDiffIEXml(c1,c2)}var d=++key;for(var i=0;i<len1;i++){c1[i]._qdiff=d}var r=[];for(var i=0,len=c2.length;i<len;i++){if(c2[i]._qdiff!=d){r[r.length]=c2[i]}}return r}function quickId(ns,mode,root,id){if(ns==root){var d=root.ownerDocument||root;return d.getElementById(id)}ns=getNodes(ns,mode,"*");return byId(ns,null,id)}return{getStyle:function(el,name){return eoms.$(el).getStyle(name)},compile:function(path,type){type=type||"select";var fn=["var f = function(root){\n var mode; ++batch; var n = root || document;\n"];var q=path,mode,lq;var tk=eoms.DomQuery.matchers;var tklen=tk.length;var mm;var lmode=q.match(modeRe);if(lmode&&lmode[1]){fn[fn.length]='mode="'+lmode[1].replace(trimRe,"")+'";';q=q.replace(lmode[1],"")}while(path.substr(0,1)=="/"){path=path.substr(1)}while(q&&lq!=q){lq=q;var tm=q.match(tagTokenRe);if(type=="select"){if(tm){if(tm[1]=="#"){fn[fn.length]='n = quickId(n, mode, root, "'+tm[2]+'");'}else{fn[fn.length]='n = getNodes(n, mode, "'+tm[2]+'");'}q=q.replace(tm[0],"")}else{if(q.substr(0,1)!="@"){fn[fn.length]='n = getNodes(n, mode, "*");'}}}else{if(tm){if(tm[1]=="#"){fn[fn.length]='n = byId(n, null, "'+tm[2]+'");'}else{fn[fn.length]='n = byTag(n, "'+tm[2]+'");'}q=q.replace(tm[0],"")}}while(!(mm=q.match(modeRe))){var matched=false;for(var j=0;j<tklen;j++){var t=tk[j];var m=q.match(t.re);if(m){fn[fn.length]=t.select.replace(tplRe,function(x,i){return m[i]});q=q.replace(m[0],"");matched=true;break}}if(!matched){throw'Error parsing selector, parsing failed at "'+q+'"'}}if(mm[1]){fn[fn.length]='mode="'+mm[1].replace(trimRe,"")+'";';q=q.replace(mm[1],"")}}fn[fn.length]="return nodup(n);\n}";eval(fn.join(""));return f},select:function(path,root,type){if(!root||root==document){root=document}if(typeof root=="string"){root=document.getElementById(root)}var paths=path.split(",");var results=[];for(var i=0,len=paths.length;i<len;i++){var p=paths[i].replace(trimRe,"");if(!cache[p]){cache[p]=eoms.DomQuery.compile(p);if(!cache[p]){throw p+" is not a valid selector"}}var result=cache[p](root);if(result&&result!=document){results=results.concat(result)}}if(paths.length>1){return nodup(results)}return results},selectNode:function(path,root){return eoms.DomQuery.select(path,root)[0]},selectValue:function(path,root,defaultValue){path=path.replace(trimRe,"");if(!valueCache[path]){valueCache[path]=eoms.DomQuery.compile(path,"select")}var n=valueCache[path](root);n=n[0]?n[0]:n;var v=(n&&n.firstChild?n.firstChild.nodeValue:null);return((v===null||v===undefined||v==="")?defaultValue:v)},selectNumber:function(path,root,defaultValue){var v=eoms.DomQuery.selectValue(path,root,defaultValue||0);return parseFloat(v)},is:function(el,ss){if(typeof el=="string"){el=document.getElementById(el)}var isArray=(el instanceof Array);var result=eoms.DomQuery.filter(isArray?el:[el],ss);return isArray?(result.length==el.length):(result.length>0)},filter:function(els,ss,nonMatches){ss=ss.replace(trimRe,"");if(!simpleCache[ss]){simpleCache[ss]=eoms.DomQuery.compile(ss,"simple")}var result=simpleCache[ss](els);return nonMatches?quickDiff(result,els):result},matchers:[{re:/^\.([\w-]+)/,select:'n = byClassName(n, null, " {1} ");'},{re:/^\:([\w-]+)(?:\(((?:[^\s>\/]*|.*?))\))?/,select:'n = byPseudo(n, "{1}", "{2}");'},{re:/^(?:([\[\{])(?:@)?([\w-]+)\s?(?:(=|.=)\s?['"]?(.*?)["']?)?[\]\}])/,select:'n = byAttribute(n, "{2}", "{4}", "{3}", "{1}");'},{re:/^#([\w-]+)/,select:'n = byId(n, null, "{1}");'},{re:/^@([\w-]+)/,select:'return {firstChild:{nodeValue:attrValue(n, "{1}")}};'}],operators:{"=":function(a,v){return a==v},"!=":function(a,v){return a!=v},"^=":function(a,v){return a&&a.substr(0,v.length)==v},"$=":function(a,v){return a&&a.substr(a.length-v.length)==v},"*=":function(a,v){return a&&a.indexOf(v)!==-1},"%=":function(a,v){return(a%v)==0},"|=":function(a,v){return a&&(a==v||a.substr(0,v.length+1)==v+"-")},"~=":function(a,v){return a&&(" "+a+" ").indexOf(" "+v+" ")!=-1}},pseudos:{"first-child":function(c){var r=[],ri=-1,n;for(var i=0,ci;ci=n=c[i];i++){while((n=n.previousSibling)&&n.nodeType!=1){}if(!n){r[++ri]=ci}}return r},"last-child":function(c){var r=[],ri=-1,n;for(var i=0,ci;ci=n=c[i];i++){while((n=n.nextSibling)&&n.nodeType!=1){}if(!n){r[++ri]=ci}}return r},"nth-child":function(c,a){var r=[],ri=-1;var m=nthRe.exec(a=="even"&&"2n"||a=="odd"&&"2n+1"||!nthRe2.test(a)&&"n+"+a||a);var f=(m[1]||1)-0,l=m[2]-0;for(var i=0,n;n=c[i];i++){var pn=n.parentNode;if(batch!=pn._batch){var j=0;for(var cn=pn.firstChild;cn;cn=cn.nextSibling){if(cn.nodeType==1){cn.nodeIndex=++j}}pn._batch=batch}if(f==1){if(l==0||n.nodeIndex==l){r[++ri]=n}}else{if((n.nodeIndex+l)%f==0){r[++ri]=n}}}return r},"only-child":function(c){var r=[],ri=-1;for(var i=0,ci;ci=c[i];i++){if(!prev(ci)&&!next(ci)){r[++ri]=ci}}return r},empty:function(c){var r=[],ri=-1;for(var i=0,ci;ci=c[i];i++){var cns=ci.childNodes,j=0,cn,empty=true;while(cn=cns[j]){++j;if(cn.nodeType==1||cn.nodeType==3){empty=false;break}}if(empty){r[++ri]=ci}}return r},contains:function(c,v){var r=[],ri=-1;for(var i=0,ci;ci=c[i];i++){if((ci.textContent||ci.innerText||"").indexOf(v)!=-1){r[++ri]=ci}}return r},nodeValue:function(c,v){var r=[],ri=-1;for(var i=0,ci;ci=c[i];i++){if(ci.firstChild&&ci.firstChild.nodeValue==v){r[++ri]=ci}}return r},checked:function(c){var r=[],ri=-1;for(var i=0,ci;ci=c[i];i++){if(ci.checked==true){r[++ri]=ci}}return r},not:function(c,ss){return eoms.DomQuery.filter(c,ss,true)},odd:function(c){return this["nth-child"](c,"odd")},even:function(c){return this["nth-child"](c,"even")},nth:function(c,a){return c[a-1]||[]},first:function(c){return c[0]||[]},last:function(c){return c[c.length-1]||[]},has:function(c,ss){var s=eoms.DomQuery.select;var r=[],ri=-1;for(var i=0,ci;ci=c[i];i++){if(s(ss,ci).length>0){r[++ri]=ci}}return r},next:function(c,ss){var is=eoms.DomQuery.is;var r=[],ri=-1;for(var i=0,ci;ci=c[i];i++){var n=next(ci);if(n&&is(n,ss)){r[++ri]=ci}}return r},prev:function(c,ss){var is=eoms.DomQuery.is;var r=[],ri=-1;for(var i=0,ci;ci=c[i];i++){var n=prev(ci);if(n&&is(n,ss)){r[++ri]=ci}}return r}}}}();

/**
 * CSS/XPATH选择器.
 * 以CSS或XPATH语法查找HTML元素，返回一个查找结果的数组，支持的选择符语法如下：
 * <h4>元素选择符:</h4>
 * <ul class="list">
 *     <li> <b>*</b> 所有元素</li>
 *     <li> <b>E</b> 所有E元素</li>
 *     <li> <b>E F</b> 被E元素包含的所有F元素</li>
 *     <li> <b>E > F</b> or <b>E/F</b> E元素下的所有一级F子元素</li>
 *     <li> <b>E + F</b> 紧接E元素后面出现的所有F兄弟元素</li>
 *     <li> <b>E ~ F</b> E元素后的所有F兄弟元素</li>
 * </ul>
 * <h4>属性选择符:</h4>
 * <ul class="list">
 *     <li> <b>E[foo]</b> 含有属性"foo"</li>
 *     <li> <b>E[foo=bar]</b> 属性"foo"的值等于"bar"</li>
 *     <li> <b>E[foo!=bar]</b> 属性"foo"的值不等于"bar"</li>
 *     <li> <b>E[foo^=bar]</b> 属性"foo"的值以"bar"开头</li>
 *     <li> <b>E[foo$=bar]</b> 属性"foo"的值以"bar"结尾</li>
 *     <li> <b>E[foo*=bar]</b> 属性"foo"的值中含有"bar"</li>
 *     <li> <b>E[foo%=2]</b> 属性"foo"的值可以被2整除</li>
 * </ul>
 * <h4>伪类选择符:</h4>
 * <ul class="list">
 *     <li> <b>E:first-child</b> E元素是兄弟元素中的第1个</li>
 *     <li> <b>E:last-child</b> E元素是兄弟元素中的最后一个</li>
 *     <li> <b>E:nth-child(<i>n</i>)</b> E元素是兄弟元素中的第n个(以1开始)</li>
 *     <li> <b>E:nth-child(odd)</b> E元素是兄弟元素中的第奇数个</li>
 *     <li> <b>E:nth-child(even)</b> E元素是兄弟元素中的第偶数个</li>
 *     <li> <b>E:only-child</b> E元素是父元素中的唯一一个子元素</li>
 *     <li> <b>E:checked</b> E元素的checked属性值为true (例如radio或checkbox) </li>
 *     <li> <b>E:first</b> 结果集中的第1个E元素</li>
 *     <li> <b>E:last</b> 结果集中的最后一个E元素</li>
 *     <li> <b>E:nth(<i>n</i>)</b> 结果集中的第n个E元素(以1开始)</li>
 *     <li> <b>E:odd</b> :nth-child(odd)的简写</li>
 *     <li> <b>E:even</b> :nth-child(even)的简写</li>
 *     <li> <b>E:contains(foo)</b> E元素的innerHTML中含有"foo"字符串</li>
 *     <li> <b>E:nodeValue(foo)</b> E元素的文本为"foo"</li>
 *     <li> <b>E:not(S)</b> 不符合S选择符的E元素</li>
 *     <li> <b>E:has(S)</b> 含有符合S选择符的子元素的E元素</li>
 *     <li> <b>E:next(S)</b> 下一个兄弟元素符合S选择符的E元素</li>
 *     <li> <b>E:prev(S)</b> 上一个兄弟元素符合S选择符的E元素</li>
 * </ul>
 * <h4>CSS值选择符:</h4>
 * <ul class="list">
 *     <li> <b>E{display=none}</b> css中"display"的值等于 "none"</li>
 *     <li> <b>E{display!=none}</b> css中"display"的值不等于"none"</li>
 *     <li> <b>E{display^=none}</b> css中"display"的值以"none"开头</li>
 *     <li> <b>E{display$=none}</b> css中"display"的值以"none"结尾</li>
 *     <li> <b>E{display*=none}</b> css中"display"的值含有"none"子字符串</li>
 *     <li> <b>E{display%=2}</b> css中"display"的值可以被2整除</li>
 * </ul>
 * @function
 * @memberOf eoms
 * @param {String} selector CSS选择符或xpath，可用逗号隔开进行分组
 * @param {HTMLElement} root (可选) 指定开始进行查询的节点(默认为document).
 * @return {Array}
 * @example
 *  在id为theform的表单中，选择所有提交按钮和下拉框：
 *  eoms.select("input[type=submit], select", document.getElementById("theform"));
 */
eoms.select = eoms.DomQuery.select;

/**
 * 常用工具函数
 * @namespace
 */
eoms.util = {
	/**
	 * checkbox的全选/取消全选
	 * @param {HTMLElement} checkbox 作为handler的checkbox元素
	 * @param {String} name 要切换选中状态的checkbox的name
	 * @example 
	 *  当第一个checkbox选中时,切换所有name="user"的checkbox的选中状态
	 *  &lt;input type="checkbox" onclick="javascript:eoms.util.checkAll(this,'user');"/&gt;
	 *  &lt;input type="checkbox" name="user"/&gt;
	 *  &lt;input type="checkbox" name="user"/&gt;
	 */
	checkAll : function(checkbox, name){
		$A(document.getElementsByName(name)).each(function(el){
			if(el.tagName=='INPUT' && el.type=='checkbox'){
				el.checked = checkbox.checked;
			}
		});
	},
	/**
	 * 将指定id的table元素隔行换色
	 * @param {String} tableid table元素id
	 * @param {Number} [startIndex] 从第几个tr元素开始应用隔行换色，默认为1
	 * @example
	 *  eoms.util.colorRows("list");
	 */
	colorRows : function(tableid, startIndex){
		if(startIndex==null) startIndex = 1;
		eoms.select('tr', tableid).each(function(tr, index) {
			if(index >= startIndex && index % 2 == 0)  eoms.$(tr).addClass("colorrow");
		});	
	},
	/**
	 * 在元素中异步载入一个url返回的html，并执行其中的JS.
	 * <br/>注意返回的内容必须是html片段，而不能是一个完整页面，否则会引起页面结构混乱。
	 * @requires Ext
	 * @param {String} id 元素id
	 * @param {String} url 页面url
	 * @param {Boolean} isUpdate 是否要重复载入
	 * @param {Function} [fn] 载入后执行的函数
	 * @param {Object} [scope] fn执行时的作用域
	 * @example
	 *  eoms.util.appendPage("sheet-deal-content", "netdata.do", true);
	 */
	appendPage : function(id, url, isUpdate, fn, scope) {
		try{
			if(Ext){
				var el = Ext.get(id);
				if (!isUpdate && el.dom.innerHTML != "") return;
				var mgr = el.getUpdateManager();
				mgr.loadScripts = true;
				mgr.update(url);
				if(typeof fn == "function"){			
					mgr.on('update',fn,scope);
				}
				mgr.on('failure',function(){
			      eoms.log("页面载入失败");
			      $(id).innerHTML = "";			
				});
			}
		}catch(e){}
	}
};

/**
 * 表单相关函数
 * @namespace
 */
eoms.form = function(){
	return {
		/**
		 * 屏蔽指定的一个或多个表单项
		 * @param {String} id 表单项id
		 * @param {String}[id, etc]
		 * @example
		 *  eoms.form.disable("btn","btn2");
		 */
		disable : function(){
			var a=arguments;
        	for (var i=0; i<a.length; ++i) {
                if(!eoms.$(a[i])) continue;
				eoms.$(a[i]).removeClass('invalid').addClass('disabled').disabled = "disabled";
        	}
		},
		/**
		 * 隐藏指定的一个或多个表单项(不屏蔽)
		 * @param {String} id 表单项id
		 * @param {String}[id, etc]
		 * @example
		 *  eoms.form.hide("btn","btn2");
		 */
		hide : function(){
			var a=arguments;
        	for (var i=0; i<a.length; ++i) {
				eoms.$(a[i]).hide();
        	}
		},
		/**
		 * 激活指定的一个或多个表单项，如果表单项被隐藏了，则显示它
		 * @param {String} id 表单项id
		 * @param {String}[id, etc]
		 * @example
		 *  eoms.form.enable("btn","btn2");
		 */
		enable : function(){
			var a=arguments;
        	for (var i=0; i<a.length; ++i) {
                if(!eoms.$(a[i])) continue;
				eoms.$(a[i]).show().removeClass('disabled').disabled = false;
        	}
		},
		/**
		 * 屏蔽指定id的一个元素下所有表单项
		 * @param {String} id 要隐藏的元素id
		 * @param {Boolean} [isHide] 是否要隐藏这个元素,默认为不隐藏
		 * @example
		 *  eoms.form.disableArea("theform");
		 */
		disableArea : function(id,isHide){
			var el = eoms.$(id);
			el.select('input,select,textarea').each(function(f) {
				eoms.form.disable(f);
			});
			if (isHide)	el.hide();
		},
		/**
		 * 激活指定id的一个元素下所有表单项，并显示这个元素
		 * @param {String} id 要显示的元素id
		 * @example
		 *  eoms.form.enableArea("theform");
		 */
		enableArea : function(id){
			eoms.$(id).show();
			eoms.$(id).select('input,select,textarea').each(function(f) {
				eoms.form.enable(f);
			});
		},
		readOnly : function(){
			var a=arguments;
        	for (var i=0; i<a.length; ++i) {
                if(!eoms.$(a[i])) 
                	continue;
                if(eoms.$(a[i]).type=="select-one"){
                	eoms.$(a[i]).onmouseover = function(){this.setCapture();};   
		            eoms.$(a[i]).onmouseout = function(){this.releaseCapture();};  
                }else{
                	eoms.$(a[i]).readOnly = "true";
                }
                eoms.$(a[i]).style.background="#cccccc"
				
        	}
		},
		readOnlyArea : function(id){
			eoms.$(id).select('input,select,textarea').each(function(f) {
				eoms.form.readOnly(f);
			});
		}
	}
}();


var $ = eoms.$;
var $A = eoms.$A;
var $D = eoms.$D;
var $E = eoms.$E;
var $V = eoms.$V;
var colorRows = eoms.util.colorRows;

var Ajax={
	xmlhttp:function (){
		try{
			return new ActiveXObject('Msxml2.XMLHTTP');
		}catch(e){
			try{
				return new ActiveXObject('Microsoft.XMLHTTP');
			}catch(e){
				return new XMLHttpRequest();
			}
		}
	}
};

Ajax.Request=function (){
	if(arguments.length<2)return ;
	var para = {asynchronous:true,method:"GET",parameters:""};
	for (var key in arguments[1]){
		para[key] = arguments[1][key];
	}
	var _x=Ajax.xmlhttp();
	var _url=arguments[0];
	if(para["parameters"].length>0) para["parameters"]+='&_=';
	if(para["method"].toUpperCase()=="GET") _url+=(_url.match(/\?/)?'&':'?')+para["parameters"];
	_x.open(para["method"].toUpperCase(),_url,para["asynchronous"]);
	_x.onreadystatechange=function (){
		if(_x.readyState==4){
			if(_x.status==200 || _x.status == 0)
				para["onComplete"]?para["onComplete"](_x):"";
			else{
				para["onError"]?para["onError"](_x):"";
			}
		}
	};
	if(para["method"].toUpperCase()=="POST")_x.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	for (var ReqHeader in para["setRequestHeader"]){
		_x.setRequestHeader(ReqHeader,para["setRequestHeader"][ReqHeader]);
	}
	_x.send(para["method"].toUpperCase()=="POST"?(para["postBody"]?para["postBody"]:para["parameters"]):null);

};



