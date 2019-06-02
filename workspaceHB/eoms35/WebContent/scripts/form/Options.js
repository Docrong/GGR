/**
 * @class eoms.form.Options
 * @description  HTML SELECT/OPTION 标签 相关方法
 */
eoms.form.Options = {
	/**
	 * 为指定的select元素添加一个option
	 * @param {String} select 对象select元素的id
	 * @param {String} text 要添加的option项的文本
	 * @param {String} [value] 要添加的option项的值,默认为""
	 * @example
 	 *  给id="sel"的下拉框添加一个选项,选项文字为"用户",值为"jack"
 	 *  var options = eoms.form.Options;
 	 *  options.add("sel","用户","jack");
	 */
	add : function(select,text,value){
		var o = eoms.$(select);
		if(!value)value="";
		o.options[o.length] = new Option(text, value, false, false);
	},
	/**
	 * 搜索指定的select元素中是否有指定value的option项
	 * @param {String} select 对象select元素的id
	 * @param {String} value 要搜索的option项的值
	 * @return {Boolean}
	 * @example
 	 *  eoms.form.Options.findValue("sel","jack");
	 */
	findValue : function(select,value){
		var s = eoms.$(select);
		if(!s) return false;
		for(var i=(s.options.length-1);i>=0;i--){
			if(s.options[i].value==value){
				return true;
			}
		}
		return false;
	},
	/**
	 * 搜索指定的select元素中是否有指定text的option项
	 * @param {String} select 对象select元素的id
	 * @param {String} text 要搜索的option项的文本
	 * @return {Boolean}
	 * @example
 	 *  eoms.form.Options.findText("sel","用户");
	 */
	findText : function(select,text){
		var s = eoms.$(select);
		if(!s)return false;
		for(var i=(s.options.length-1);i>=0;i--){
			if(s.options[i].text==text){
				return true;
			}
		}
		return false;
	},
	/**
	 * 删除指定value的select元素的option项.
	 * 如果没有value参数，效果为删除该select当前选择项，如果当前项的值为"",则不会删除
	 * @param {String} select 对象select元素的id
	 * @param {String} [value] 要删除的option项的值
	 * @example
 	 *  eoms.form.Options.del("sel","jack");
 	 *  eoms.form.Options.del("sel");
	 */
	del : function(select,value){
		var from = eoms.$(select);
		if(arguments[1]){
			for(var i=(from.options.length-1);i>=0;i--){
				var o=from.options[i];
				if(o.value==value){from.options[i] = null;}
			}
		}
		else{
			if(from.value=="")return;
			from.options[from.selectedIndex] = null;		
		}
		from.selectedIndex = 0;
	},
	/**
	 * 用JSON数据填充指定的select
	 * @param {String} select 指定的select元素id
	 * @param {Array} a 包含JSON数据的数组，形如[{text:"a",value:"1"},{text:"b",value:"2"}...]
	 * @param {Boolean} [isKeepFirst] 是否保留第一个选项, 默认为不保留
	 * @example
 	 *  eoms.form.Options.loadJSON("sel",[{text:"用户1",value:"jack"},{text:"用户2",value:"tom"}]);
	 */
	loadJSON : function(select,a,isKeepFirst){
		eoms.$(select).length = isKeepFirst ? 1 : 0;
		if(a && a.length){
		  a.each(function(o,i){
			eoms.form.Options.add(select,o.text,o.value || "");
		  });	
		}
	}
}
