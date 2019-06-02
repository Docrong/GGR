Ext.form.Form.prototype.addHidden = function(name, value){
     var f = new Ext.form.Field({
          autoCreate : {tag:'input', type: 'hidden', name:name, value:value},
          initEvents : Ext.emptyFn,
          setSize : Ext.emptyFn
     });
     f.render(this.el);
     this.items.add(f);
};

var Editor = {
    init : function(){
    	//初始化信息提示
    	Ext.QuickTips.init();
		
		//设置表单验证信息显示位置
    	Ext.form.Field.prototype.msgTarget = 'side'; 
    	
    	//定义一个表单
    	var simple = new Ext.form.Form({name:'simpleform',	labelAlign: 'left'});
    	
    	simple.add(
    		new Ext.form.TextField({fieldLabel: 'USER ID',id:'userid',name: 'userid',value:'admin22', width:175,allowBlank:false
	     	}),
	
	        new Ext.form.TextField({fieldLabel: 'MODEL ID', id:'modelid', name: 'modelid',value:'11',width:175,allowBlank:false
	        })
	    );

	    simple.addButton('search', function(){
	        loadGrid();
	    });
	
	    simple.render('search-form');
	    
	    var loadGrid = function(){
	    	myds.load({params:{userid:simple.getValues().userid, modelid:simple.getValues().modelid}});
	    }
	    
	    var editer = new Ext.form.Form({name:'editerform',labelAlign: 'top'});
    	
    	editer.add(
	        new Ext.form.TextField({ fieldLabel: 'USER ID',id:'user',name: 'userid',width:175,allowBlank:false
	        }),
	
	        new Ext.form.TextField({ fieldLabel: 'MODEL ID',id:'model',name: 'modelid',width:175,allowBlank:false
	        }),
	
	        new Ext.form.TextField({ fieldLabel: 'MODEL NAME',id:'modelname',name: 'modelname',width:175,allowBlank:false
	        }),
	        new Ext.form.TextField({ fieldLabel: 'VIEW MODEL',id:'viewmodel',name: 'viewmodel',value:'ajax',width:175,allowBlank:false
	        })
	    );
		
	    editer.addButton('edit', function(){
	        editer.save();
	    });
	    editer.addButton('new');
	
	    editer.render('detail');
	    editer.addHidden('id', '');
	    
	    editer.save = function(){
	    	var data = Ext.lib.Ajax.serializeForm(editer.el.dom);
	    	Ext.lib.Ajax.request(
	    	"post",
	    	JSWorkSpace.appPath+"/log/saveTawCommonLogDeploy.html?method=save",
	    	{success: loadGrid},
	    	data
	    	);
	    }
        
        var myds = new Ext.data.Store({
        	
        	proxy: new Ext.data.HttpProxy({
            	url: JSWorkSpace.appPath+'/log/tawCommonLogDeploys.html?method=xsearch'
        	}),
        	
        	reader: new Ext.data.JsonReader({
            		root: 'rows',
            		totalProperty: 'results'
        		}, [
            		{name: 'userid', mapping: 'userid'},
            		{name: 'modelid', mapping: 'modelid'},
            		{name: 'modelname', mapping: 'modelname'}
        	])
        });
        loadGrid();

		var myColModel = new Ext.grid.ColumnModel([
			{header: "用户ID", width: 75, sortable: true, locked:false, dataIndex: 'userid'},
			{header: "模块ID", width: 75, sortable: true, locked:false,dataIndex: 'modelid'},
			{header: "模块名称", width: 200, sortable: true, dataIndex: 'modelname'}
		]);

        // create the Grid
        var grid = new Ext.grid.Grid('grid', {
            ds: myds,
            cm: myColModel,
            selModel: new Ext.grid.RowSelectionModel({singleSelect:true})
        });
        
		grid.dodbclick = function(){
			detailEl.hide();
			detailEl.slideIn('l', {stopFx:true,duration:.2});
			var record = grid.getSelectionModel().getSelected().json;
			editer.setValues(record);
		}


    	var layout = new Ext.BorderLayout(document.body, {
				north:{
					split:false,
                    titlebar: true,
                    autoScroll:false
					
				},
                center: {
	                titlebar: false,
	                autoScroll:true
                },
                east: {
                    split:true,
                    initialSize: 300,
                    minSize: 300,
                    maxSize: 400,
                    titlebar: true
                }
        });
        
		layout.beginUpdate();
		
		layout.add('north', new Ext.ContentPanel('search', {title: '查询', fitToFrame:true}));
		layout.add('center', new Ext.ContentPanel('grid-panel',{fitToFrame:true}));
		var dp = layout.add('east', new Ext.ContentPanel('detail', {title: 'Detail', fitToFrame:true}));
		var detailEl = dp.getEl();
		
		// restore any state information
        layout.endUpdate();
		
		grid.addListener('rowdblclick', grid.dodbclick);
        grid.render();

    }
};
Ext.onReady(Editor.init, Editor, true);