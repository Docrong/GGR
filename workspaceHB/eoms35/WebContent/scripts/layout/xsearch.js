
//定义一个应用
var Editor = {
    init : function(){
    	//初始化信息提示
    	Ext.QuickTips.init();
		
		//设置表单验证信息显示位置
    	Ext.form.Field.prototype.msgTarget = 'side';
    	
/** 表单的设置 **/
    	     	 
	/** 定义一个表单searcher,用于输入搜索条件,设置文本显示位置为靠左 **/
    	var searcher = new Ext.form.Form({name:'simpleform',	labelAlign: 'left'});
    	
    	//增加两个文本框USER ID 和MODEL ID
    	searcher.add(
    		new Ext.form.TextField({fieldLabel: 'USER ID',id:'userid',name: 'userid',value:'', width:175
	     	}),
	
	        new Ext.form.TextField({fieldLabel: 'MODEL ID', id:'modelid', name: 'modelid',value:'',width:175
	        })
	    );
	    
		//增加查询按钮
	    searcher.addButton(Local.form.search, loadGrid);
		
		//将此form渲染到search-form元素中
	    searcher.render('search-form');
	        
	/** 定义修改时的表单editer,设置文本显示位置为靠顶部 **/
	
	    var editer = new Ext.form.Form({name:'editerform',labelAlign: 'top'});
    	
    	//为editer增加三个文本框
    	editer.add(
	        new Ext.form.TextField({ fieldLabel: 'USER ID',id:'user',name: 'userid',width:175,allowBlank:false
	        }),
	
	        new Ext.form.TextField({ fieldLabel: 'MODEL ID',id:'model',name: 'modelid',width:175,allowBlank:false
	        }),
	
	        new Ext.form.TextField({ fieldLabel: 'MODEL NAME',id:'modelname',name: 'modelname',width:175,allowBlank:false
	        })
	    );
		
		//增加保存修改的按钮
	    var savebtn = editer.addButton({text:Local.form.save,hidden:true}, function(){
	        editer.save("save");
	    });
	    
	    //增加删除按钮
	    var delbtn = editer.addButton({text:Local.form.del,hidden:true}, function(){
	    	//弹出一个确认框
	    	Ext.MessageBox.confirm(
	    		'Confirm', 
	    		Local.confirm.delRecord, 
	    		function(btn){if(btn=="yes"){editer.save("delete");newRecord();}}
	    	);
	    });
	    
	    //增加新建按钮,点击时执行clearEditer
	    var newbtn = editer.addButton({text:Local.form.add,hidden:false}, function(){
	        editer.save("save");
	    });
		
		//渲染editer到detail元素
	    editer.render('detail-form');
	    
	    //手动增加一个hidden域,用于区别修改还是新增,当值为空时新增
	    editer.addHidden('id', '');
	    
	    //@param m
	    //value: save 执行修改或新建,此处新建后也应实现选中刚才添加的行
	    //value: delete 执行删除
	    editer.save = function(m){	
	    	//console.log(editer.isValid());
	    	if(!editer.isValid())	{
	    		return;
	    	}
	    	var data = Ext.lib.Ajax.serializeForm(editer.el.dom); //将editer表单中的数据转化为参数字符串
	    	
	    	Ext.lib.Ajax.request(	//提交一个ajax请求,此处后台处理时需要改进为return null
	    		"post",
	    		JSWorkSpace.appPath+"/log/saveTawCommonLogDeploy.html?method="+m,
	    		{success: loadGrid},
	    		data
	    	);
	    };
	    

		//用searcher的数据重载表格
	    function loadGrid(){
	    	myds.load({
	    		params:{userid:searcher.getValues().userid, modelid:searcher.getValues().modelid},
	    		
	    		//载入成功后选中刚才编辑的行
	    		callback:function(){
	    			var recordId = editer.getValues().id;
	    			var recordIndex = myds.indexOfId(recordId);
	    			grider.getSelectionModel().selectRow(recordIndex);
	    		}
	    	});
	    	
	    }
	       
	    //清除editer表单
	    function newRecord(){	    	
			detailEl.hide();			//先隐藏detail元素			
			$("detail-header").innerHTML = Local.form.newRecord;
	    	editer.reset();
	    	//因为reset()有bug,hidden域的值会被设为undefined,所以需手动设为空
	    	editer.findField("id").setValue("");
			detailEl.slideIn('l', {stopFx:true,duration:.2});	//再动态显示detail元素
	    	newbtn.show();savebtn.hide();delbtn.hide();
	    }	
/** 表格的设置 **/	    
        
        //查询时取得data source
        var myds = new Ext.data.Store({
        	
        	//使用httpProxy,另外的方式还有MemoryProxy(从页面取)和ScriptTagProxy(跨主机取)两种方式
        	proxy: new Ext.data.HttpProxy({
            	url: JSWorkSpace.appPath+'/log/tawCommonLogDeploys.html?method=xsearch'
        	}),
        	
        	//数据读取器,根据数据格式的不同使用不同的读取器,此处使用json,另外还有ArrayReader和XmlReader等
        	reader: new Ext.data.JsonReader({
        			id: 'id',
            		root: 'rows',//对应返回的list对象
            		totalProperty: 'results'//对应返回的记录数
        		}, [
            		{name: 'userid', mapping: 'userid'},//字段映射
            		{name: 'modelid', mapping: 'modelid'},
            		{name: 'modelname', mapping: 'modelname'}
        	])
        });
        

		//列模型,sortable为可排序,dataIndex是字段名对应
		var myColModel = new Ext.grid.ColumnModel([
			{header: "用户ID", width: 75, sortable: true,dataIndex: 'userid'},
			{header: "模块ID", width: 75, sortable: true,dataIndex: 'modelid'},
			{header: "模块名称", width: 200, sortable: true,dataIndex: 'modelname'}
		]);

        //创建表格对象
        var grider = new Ext.grid.Grid('grid', {
        	ds: myds,		//数据源,使用myds
            cm: myColModel,	//列模型,使用myColModel
            selModel: new Ext.grid.RowSelectionModel({singleSelect:true}) //行选择模型,设置为只可单选
        });
        
        
        //取默认的数据
        loadGrid();
                
        //在表格行中双击的处理
		grider.dodbclick = function(){
			layout.showPanel("detail");	//显示detail的Panel,此处可删除
			detailEl.hide();			//先隐藏detail元素
			$("detail-header").innerHTML = Local.form.editRecord;
			newbtn.hide();savebtn.show();delbtn.show();
			var record = grider.getSelectionModel().getSelected().json; //获取表格中当前选择行的json对象
			editer.setValues(record);	//将json数据填充进editer表单
			//console.log(record.id);
			detailEl.slideIn('l', {stopFx:true,duration:.2});	//再动态显示detail元素
		};

		
		//为表格增加行双击事件,双击时执行grid.dodbclick
		grider.addListener('rowdblclick', grider.dodbclick);
		
		//渲染表格
        grider.render();
          	
/** 布局的设置 **/        
        
		//布局
    	var layout = new Ext.BorderLayout(document.body, {
                center: {
	                titlebar: false,	//是否显示标题行
	                autoScroll:false	//是否自动滚动
                },
                east: {
                    split:false,		//是否可伸缩
                    initialSize: 300,	//初始化宽度
                    titlebar: true		//显示标题栏
                }
        });
        
        var east = layout.getRegion('east');
		east.addButton = east.createToolButton('x-layout-tools-add', newRecord, window);
		
        //更新布局,用于在布局对象中插入真正的内容
		layout.beginUpdate();
		layout.add('center', new Ext.ContentPanel('grid-panel')); //插入表格部分,包括查询的表单和列表
		
		var dp = layout.add('east', new Ext.ContentPanel('detail', {title: Local.form.detail, fitToFrame:true})); //插入详细信息部分,包括修改/新建/删除
		var detailEl = dp.getEl();
		
        layout.endUpdate();
	    
	    $("detail-header").innerHTML = Local.form.newRecord;

    }
};

//当Ext载入成功时执行Editor.init, 其scope为Editor
Ext.onReady(Editor.init, Editor, true);

