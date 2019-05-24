Ext.onReady(function(){
	Ext.QuickTips.init();
	var layout = new Ext.BorderLayout(document.body, {
		center: {
			autoScroll: true,
			split:true
	    },
		south: {
			split:true,
            initialSize: 250,
            minSize: 100,
            maxSize: 400,
			autoScroll: true,
	        titlebar: true,
	        collapsible: true
		}
	});
	
	layout.beginUpdate();
	layout.add('center', new Ext.ContentPanel('portal-north',{fitToFrame:true}));
	layout.add('south', new Ext.ContentPanel('portal-south', {fitToFrame:true}));
	layout.endUpdate();
	
	var loading = Ext.get('loading');
	loading.fadeOut({duration:.2,remove:true});

});