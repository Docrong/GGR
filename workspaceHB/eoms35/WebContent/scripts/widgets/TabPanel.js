/**
 * TabPanel控件
 *@version 1.0
 */

/**
 * @param el tabPanel容器ID
 * @param config 设置
 * @cfg items.id 元素ID
 * @cfg items.text tab文本
 * @cfg items.url tab载入的页面URL
 * @cfg items.params tab载入的页面URL的参数
 * @cfg items.loadOnce 是否载入一次
 * @cfg items.isIframe 是否是iframe
 * @cfg items.hidden 是否隐藏
 */
eoms.TabPanel = function(el,config){
	this.el = Ext.get(el);
	this.tabPanel = new Ext.TabPanel(this.el);
	if(config.items){
		Ext.each(config.items,this.addTab,this);
	}
	this.tabPanel.activate(0);
}

eoms.TabPanel.prototype.addTab = function(item){
	var id = item.id || Ext.id();
	var tabItemEl = Ext.get(id);
	if(item.isIframe){
		if(tabItemEl){
			tabItemEl.set({
				width:'100%',
				height:500,
				frameborder:0
			},false);		
		}
		else{
			this.el.createChild({
				tag:"iframe",
				id:id,
				name:id,
				width:"100%",
				height:500,
				frameborder:0,
				src:"about:blank"
			});
		}
	}
	var tab = this.tabPanel.addTab(id,item.text);
	if(item.isIframe){
		tab.on("activate",function(){
			if(Ext.getDom(id).src=="about:blank"){
				Ext.getDom(id).src = item.url;
			}    		
      	});
	}
	else if(item.url){
		tab.setUrl(item.url, item.params, item.loadOnce || true).loadScripts = true;
	}
	tab.setHidden(item.hidden || false);
}