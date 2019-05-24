var Page = function(){
    var layout;
       
    return {
        init : function(){
            
            // create the main layout
            layout = new Ext.BorderLayout(document.body, {
            	north:{
            		initialSize: 30
            	},
                west: {
                    size: 200
                },
                center: {
                }
            });
            // tell the layout not to perform layouts until we're done adding everything
            layout.beginUpdate();
            
            layout.add('north', new Ext.ContentPanel('north'));
            layout.add('west', new Ext.ContentPanel('west'));
            layout.add('center',new Ext.ContentPanel('center', {fitToFrame:true}));
            
            layout.restoreState();
            layout.endUpdate();
            
        }
    };
}();
Ext.onReady(Page.init, Page, true);
