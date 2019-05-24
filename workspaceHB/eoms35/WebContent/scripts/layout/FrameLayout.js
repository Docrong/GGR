/** 
 * 左边操作，右边iframe页面的布局模板
 * @version 0.1 080722
 */
 
eoms.layout.FrameLayout = function(cfg){
	Ext.apply(this, cfg);
	this.el = cfg.el || document.body;
	this.defaultCfg = {
		north:{
			initialSize : 40,
			titlebar : false,
			margins : {left : 5,right : 5,bottom : -1,top : 5}
		},
		west : {
			split : true,
			titlebar : true,
			collapsible: true,
			autoScroll : true,
			initialSize : 200,
			margins : {left : 5,right : 0,bottom : 5,top : 0}
		},
		center : {
			titlebar : true,
			autoScroll : true,
			margins : {left : 0,right : 5,bottom : 5,top : 0}
		}
	};
	Ext.get(document.body).setStyle("background-image","none");
			
	/**************
	 * 初始化布局
	 **************/
	layout = new Ext.BorderLayout(document.body, {        
    	north : Ext.apply(this.defaultCfg.north,this.north),
		west : Ext.apply(this.defaultCfg.west,this.west),
		center : Ext.apply(this.defaultCfg.center,this.center)
	});
	layout.beginUpdate();
    layout.add('north', new Ext.ContentPanel('headerPanel'));    		
    layout.add('west', new Ext.ContentPanel('westPanel'));
    layout.add('center', new Ext.ContentPanel('centerPanel', {
    	id:'layoutcenterPanel',
    	fitToFrame:true
    }));
    layout.endUpdate();
    this.layout = layout;
};
eoms.layout.FrameLayout.prototype = {
	getLayout : function(){
		return this.layout;
	}
};