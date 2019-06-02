/**
 * @class eoms.sheet  工单相关函数
 * @version 0.1
 */
eoms.Sheet = function(){
	return {
		/**
		 * 设置一个下拉框为ajax页面载入器
		 * @param {String} selectId 下拉框id或下拉框元素
		 * @param {String} divId 载入页面的容器id
		 * @param {Function} fn 载入后执行的函数
		 */
		setPageLoader : function(select,divId,fn){
			var sel = Ext.get(select);
			if(!sel) return;
			sel.on("change",function(e){
				sel.dom.disabled = true;
				sel.blur();
				document.body.focus(); //for IE6
				
				function onUpdate(){
					window.scrollTo(sel.getX(),sel.getY()-30);
					sel.dom.disabled = false;
					if(typeof fn == "function")fn();
				}
			
				eoms.util.appendPage(divId,sel.dom.value,true,onUpdate);		
			});
			
		}
	}
}();