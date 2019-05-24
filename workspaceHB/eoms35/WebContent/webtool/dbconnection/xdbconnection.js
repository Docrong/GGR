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
		
		var loadGrid = function(){
	    	myds.load();
	    }
		
		//创建编辑页面属性
		var editer = new Ext.form.Form({labelWidth:'75',name:'editerform',labelAlign: 'top'});
    	
    	
    	editer.add(
	        new Ext.form.TextField({ fieldLabel: '数据源名称',id:'dbName',name: 'dbName',width:175,allowBlank:false
	        }),
	        new Ext.form.TextField({ fieldLabel: 'URL',id:'dbConnectionUrl',name: 'dbConnectionUrl',width:175,allowBlank:false
	        }),
	        new Ext.form.TextField({ fieldLabel: '用户名',id:'userName',name: 'userName',width:175,allowBlank:false
	        }),
	        new Ext.form.TextField({ fieldLabel: '口令',id:'password',name: 'password',width:175,allowBlank:false
	        }),
	       	new Ext.form.TextField({ fieldLabel: '字符集',id:'dbCharset',name: 'dbCharset',width:175,allowBlank:false
	        }),
	        new Ext.form.TextField({ fieldLabel: '描述',id:'description',name: 'description',width:175,allowBlank:false
	        }),
	        new Ext.form.TextField({ fieldLabel: '数据库类型',id:'dbType',name: 'dbType',width:175,allowBlank:false
	        })
	    );
		
		var createb = editer.addButton('新建');
	    createb.minWidth = 30;
	    createb.on('click', LayoutExample.showDialog,this);
		
	    var modifyb = editer.addButton('保存',function(){
	        editer.dbinfmothed("modify");
	        clearEditer();
	    });
	    modifyb.minWidth = 30;
	    
	    var removeb = editer.addButton('删除', function(){
	    	//弹出一个确认框
	    	Ext.MessageBox.confirm(
	    		'Confirm', 
	    		'您确认要删除本条记录吗?', 
	    		function(btn){if(btn=="yes"){editer.dbinfmothed("remove");clearEditer();}}
	    	);
	    });
	    
	    removeb.minWidth = 30;
	    
	    editer.render('detail');
	    editer.addHidden('dbId', '');
		
	    editer.dbinfmothed = function(mothed){
	    	var data = Ext.lib.Ajax.serializeForm(editer.el.dom);
	    	Ext.lib.Ajax.request(
	    	"post",
	    	JSWorkSpace.appPath+"/wap/wapdbconnection.html?method=" + mothed,
	    	{success: loadGrid},
	    	data
	    	);
	    }
		
	    function clearEditer(){
	    	editer.reset();
	    	//因为reset()有bug,hidden域的值会被设为undefined,所以需手动设为空
	    	editer.findField("dbId").setValue("");
	    }
	 
	    
		//数据访问资源信息
        var myds = new Ext.data.Store({
        	
        	proxy: new Ext.data.HttpProxy({
            	url: JSWorkSpace.appPath+'/wap/wapdbconnection.html?method=list'
        	}),
        	
        	reader: new Ext.data.JsonReader({
            		root: 'rows',
            		totalProperty: 'results'
        		}, [
            		{name: 'dbName', mapping: 'dbName'},
            		{name: 'dbType', mapping: 'dbType'},
            		{name: 'dbConnectionUrl', mapping: 'dbConnectionUrl'}
        	])
        });
		
		loadGrid();
		
		//列信息定义
		var myColModel = new Ext.grid.ColumnModel([
			{header: "数据源", width: 100, sortable: true, locked:false, dataIndex: 'dbName'},
			{header: "描述", width: 100, sortable: true, locked:false,dataIndex: 'dbType'},
			{header: "URL", width: 200, sortable: true, dataIndex: 'dbConnectionUrl'}
		]);

        //创建Grid
        var grid = new Ext.grid.Grid('search', {
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
		
		
		//页面布局
    	var layout = new Ext.BorderLayout(document.body, {
                center: {
	                titlebar: false,
	                autoScroll:true
                },
                east:{
                    split:true,
                    initialSize: 200,
                    minSize: 200,
                    maxSize: 200,
                    titlebar: true
                }
        });
        
		layout.beginUpdate();
		layout.add('center', new Ext.ContentPanel('search', {fitToFrame:true}));
		var dp = layout.add('east', new Ext.ContentPanel('detail', {title: '内容', fitToFrame:true}));
		var detailEl = dp.getEl();
        layout.endUpdate();

		grid.addListener('rowdblclick', grid.dodbclick);
        grid.render();
    }
};

var LayoutExample = function(){
    // everything in this space is private and only accessible in the HelloWorld block
    
    // define some private variables
    var dialog, showBtn;
    
    var toggleTheme = function(){
        Ext.get(document.body, true).toggleClass('ytheme-gray');
    };
    // return a public interface
    return {
        init : function(){
             showBtn = Ext.get('show-dialog-btn');
             // attach to click event
             showBtn.on('click', this.showDialog, this);
             
        },
        
        showDialog : function(){
            if(!dialog){ // lazy initialize the dialog and only create it once
                dialog = new Ext.LayoutDialog("hello-dlg", { 
                        modal:true,
                        width:600,
                        height:400,
                        shadow:true,
                        minWidth:300,
                        minHeight:300,
                        proxyDrag: true,
                        west: {
	                        split:true,
	                        initialSize: 150,
	                        minSize: 100,
	                        maxSize: 250,
	                        titlebar: true,
	                        collapsible: true,
	                        animate: true
	                    },
	                    center: {
	                        autoScroll:true,
	                        tabPosition: 'top',
	                        closeOnTab: true,
	                        alwaysShowTabs: true
	                    }
                });
                dialog.addKeyListener(27, dialog.hide, dialog);
                dialog.addButton('Submit', dialog.hide, dialog);
                dialog.addButton('Close', dialog.hide, dialog);
                
                var layout = dialog.getLayout();
                layout.beginUpdate();
                layout.add('west', new Ext.ContentPanel('west', {title: 'West'}));
	            layout.add('center', new Ext.ContentPanel('center', {title: 'The First Tab'}));
                // generate some other tabs
                layout.add('center', new Ext.ContentPanel(Ext.id(), {
                                        autoCreate:true, title: 'Another Tab', background:true}));
	            layout.add('center', new Ext.ContentPanel(Ext.id(), {
                                        autoCreate:true, title: 'Third Tab', closable:true, background:true}));
	            layout.endUpdate();
            }
            dialog.show(showBtn.dom);
        }
    };
}();

Ext.onReady(Editor.init, Editor, true);
