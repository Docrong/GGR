/** 对树图数据进行增删改操作的模板
 * @version 0.2
 * @author mios
 */

var selectNode;

Ext.onReady(function(){
    var xt = Ext.tree;	
    Ext.QuickTips.init();

    var cview = Ext.DomHelper.append('main-ct',
        {cn:[{id:'main-tb',cls:'tb'},{id:'cbody'}]}
    );
    
    // create the primary toolbar
    var tb = new Ext.Toolbar('main-tb');
    tb.add(
    {
        id:'expand',
        text:'展开树图',
        handler:expandAll,
        cls:'x-btn-text-icon expand',
        tooltip:'展开所有树图'
    },{
        id:'collapse',
        text:'关闭树图',
        handler:collapseAll,
        cls:'x-btn-text-icon collapse',
        tooltip:'关闭所有打开的树'
    },{
        id:'refresh',
        text:'刷新树图',
        handler:refreshTree,
        cls:'x-btn-text-icon refresh',
        tooltip:'刷新整个树图'
    });
    // for enabling and disabling
    var btns = tb.items.map;
    
    // create our layout
    var layout = new Ext.BorderLayout(document.body, {        
    	north: {
            initialSize: 40,
            titlebar: false,      
            margins:{left:5,right:5,bottom:-1,top:5}
        },
        east: {
            split:true,
            initialSize: 250,
            minSize: 180,
            maxSize: 400,
            titlebar: true,
            margins:{left:0,right:5,bottom:5,top:0}
        },
//        south: {
//            initialSize: 100,
//            titlebar: false,
//            showPin:true,
//            margins:{left:5,right:5,bottom:5,top:5}
//        },
        center: {
            titlebar: false,
            margins:{left:5,right:0,bottom:5,top:0}
        }
    }, 'main-ct');

    layout.batchAdd({
       north: {
           el: 'headerPanel',
           fitToFrame:true         
       },
       east: {
           id: 'detail-ct',
           autoCreate:true,
           title:'执行操作',
           autoScroll:true,
           fitToFrame:true,
           margins:{left:15}
           
       },
       center : {
           el: cview,
           autoScroll:true,
           fitToFrame:true,
           toolbar: tb,
           resizeEl:'cbody'
       }
    });
    Ext.DomHelper.append('detail-ct',{id:'msg',cls:'msgbox'});
    Ext.DomHelper.append('detail-ct',{id:'form-ct'});
       
    var detailEl = Ext.get('detail-ct');
    var formEl = Ext.get('form-ct');
    var msgEl = Ext.get('msg');
    msgEl.hide();
	
	/* 定义表单按钮和隐藏域
	 * 
	 */
    var newbtn = form.addButton('保存',newRecord);
    var newsubbtn = form.addButton({text:'保存子节点',hidden:true}, function(){
        newSubNode();
    });
    var modifybtn = form.addButton({text:'保存修改',hidden:true}, function(){
        modifyNode();
    });
    //form.addButton('清空',function(){form.reset()});
    form.render('form-ct');
        
	/* 树图
	 * 
	 */
    var ctree = new xt.TreePanel('cbody', {
        animate:true,
        enableDD:true,
        containerScroll: true,
        lines:true,
        rootVisible:true,
        loader: new Ext.tree.TreeLoader()
    });

	
    var croot = new xt.AsyncTreeNode({
        allowDrag:false,
        allowDrop:true,
        allowDelete:false,
        allowChild:true,
        allowEdit:false,
        id:treeRootId,
        text:config.treeRootText,
        cls:'croot',
        loader:new Ext.tree.TreeLoader({
            dataUrl:treeAction
        })
    });
    ctree.setRootNode(croot);
    ctree.render();
    croot.expand();  
    
   
 	/* 选择节点时的处理
 	 * 
 	 */
    var sm = ctree.getSelectionModel();
    sm.on('selectionchange', function(){
        //var n = sm.getSelectedNode();
    });    
    
 	/* 右键菜单
 	 * 
 	 */
    var ctxMenu = new Ext.menu.Menu({
        id:'copyCtx',
        items: [{
                id:'newnode',
                handler:doNewSub,
                cls:'new-mi',
                text:'新建子节点'
            },{
                id:'modify',
                handler:modify,
                cls:'edit-mi',
                text:'修改'
            },'-',{
                id:'remove',
                handler:dodel,
                cls:'remove-mi',
                text: '删除'
        }]
    });
    
	/* 树图事件处理
	 * 
	 */	 
    ctree.on('nodedragover', function(e){
        var n = e.dropNode;
        return isSourceCopy(e, n) || isReorder(e, n);
    });

    ctree.on('beforenodedrop', function(e){
        var n = e.dropNode;

        // copy node from source tree
        if(isSourceCopy(e, n)){
            var copy = new xt.TreeNode(
                Ext.apply({allowDelete:true,expanded:true}, n.attributes)
            );
            copy.loader = undefined;
            if(e.target.attributes.options){
                e.target = createOption(e.target, copy.text);
                //return false;
            }
            e.dropNode = copy;
            return true;
        }

        return isReorder(e, n);
    });
    ctree.on('contextmenu', prepareCtx);

    ctree.el.swallowEvent('contextmenu', true);
    ctree.el.addKeyListener(Ext.EventObject.DELETE, dodel);
    ctree.el.on('keypress', function(e){
        if(e.isNavKeyPress()){
            e.stopEvent();
        }
    });
    
    /* 修改节点信息
     * 
     */
    function modify(){
    	selectNode =  sm.getSelectedNode();
		var nodeData = new Ext.data.Store({
        	proxy: new Ext.data.HttpProxy({url:detailAction+selectNode.id}),
        	reader: new Ext.data.JsonReader({}, [])
		});
    	nodeData.on('load', function() {
		    form.setValues(nodeData.reader.jsonData);	    
    		msgEl.hide();
    		newbtn.hide();newsubbtn.hide();modifybtn.show();
    		
    		var temp = fs.getEl().dom;
    		var le = temp.firstChild;
    		le.innerHTML = '修改 '+selectNode.text+' 的信息';
    	
	    	if(Ext.isIE)
	    		formEl.fadeIn({duration:.4});
	    	else
	    		formEl.slideIn('r', {duration:.1});
		 }); 
		
		nodeData.load();
    }
    function modifyNode(){
    	if(!form.isValid())	{
    		return;
    	}
    	var data = Ext.lib.Ajax.serializeForm(form.el.dom);
    	form.reset();
    	
    	layout.el.mask('保存数据中，请稍候。', 'x-mask-loading');
        var hide = layout.el.unmask.createDelegate(layout.el);
    	Ext.lib.Ajax.request(
    		"post",
    		modifyAction+selectNode.id,
    		{success: function(){reloadTree(selectNode.parentNode);hide();setMsg("修改成功")},
    		 failure: function(){hide();setMsg("修改失败")}
    		},
    		data
    	);   	
    }
    
    /* 新建子节点
     *
     */
    function doNewSub(){
    	selectNode =  sm.getSelectedNode();
    	form.reset();
    	
    	msgEl.hide();
    	newbtn.hide();newsubbtn.show();modifybtn.hide();
    	
    	var temp = fs.getEl().dom;
    	var le = temp.firstChild;
    	le.innerHTML = '在 '+selectNode.text+' 下新建';
    	
    	if(Ext.isIE)
    		formEl.fadeIn({duration:.4});
    	else
    		formEl.slideIn('r', {duration:.1});
    	
    }
    function newSubNode(){
    	if(!form.isValid())	{
    		return;
    	}
		
    	initHiddens('newSubNode');
    	var data = Ext.lib.Ajax.serializeForm(form.el.dom);
    	layout.el.mask('保存数据中，请稍候。', 'x-mask-loading');
        var hide = layout.el.unmask.createDelegate(layout.el);
    	Ext.lib.Ajax.request(
    		"post",
    		newAction,
    		{success: function(){reloadTree(selectNode);hide();setMsg("创建成功")},
    		 failure: function(){hide();setMsg("创建失败")}
    		},
    		data
    	);
    	
    }
        
    /* 新建一级模块
     * 
     */
    function newPriv(){	
    	form.reset();
    	initHiddens('newPrivNode');
    	
    	msgEl.hide();
    	newbtn.show();newsubbtn.hide();modifybtn.hide();
    	
    	var temp = fs.getEl().dom;
    	var le = temp.firstChild;
    	le.innerHTML = '新建一级模块';
    	
    	if(Ext.isIE)
    		formEl.fadeIn({duration:.4});
    	else
    		formEl.slideIn('r', {duration:.1});
    	
    }  
    function newRecord(params){
    	if(!form.isValid())	{
    		return;
    	}
    	var data = Ext.lib.Ajax.serializeForm(form.el.dom);
    	layout.el.mask('保存数据中，请稍候。', 'x-mask-loading');
        var hide = layout.el.unmask.createDelegate(layout.el);
    	Ext.lib.Ajax.request(
    		"post",
    		newAction,
    		{success: function(){reloadTree(croot);hide();setMsg("创建成功")},
    		 failure: function(){hide();setMsg("创建失败")}
    		},
    		data
    	);
    };
    
    /* 弹出右键菜单前的处理
     * 
     */
    function prepareCtx(node, e){
        node.select();
        ctxMenu.items.get('newnode')[node.attributes.allowChild ? 'enable' : 'disable']();
        ctxMenu.items.get('remove')[node.attributes.allowDelete ? 'enable' : 'disable']();

        ctxMenu.items.get('modify')[!node.isRoot ? 'enable' : 'disable']();
        ctxMenu.showAt(e.getXY());
    }

    function collapseAll(){
        ctxMenu.hide();
        setTimeout(function(){
            croot.eachChild(function(n){
               n.collapse(false, false);
            });
        }, 10);
    }
    
    function expandAll(){
        ctxMenu.hide();
        setTimeout(function(){
            croot.eachChild(function(n){
               n.expand(false, false);
            });
        }, 10);
    }

    
	/* 删除节点
	 * 
	 */
	function dodel(){	    	
	    //弹出一个确认框
    	Ext.MessageBox.confirm(
    		'Confirm', 
    		'您确定删除这个项目吗？', 
    		function(btn){
    			if(btn=="yes"){
					var n = sm.getSelectedNode();
					var data = "id="+n.id;
			    	Ext.lib.Ajax.request(
			    		"post",
			    		deleteAction,
			    		{success: removeNode},
			    		data
			    	);
    				
    			}
    		}
    	);
	}
    function removeNode(){
        var n = sm.getSelectedNode();
        if(n && n.attributes.allowDelete){
            ctree.getSelectionModel().selectPrevious();
            n.parentNode.removeChild(n);
        }
        setMsg("您成功删除了一个节点。");
    }
    
    /* 操作结果通知
     * 
     */
     function setMsg(msg){
     	formEl.hide();
     	msgEl.dom.innerHTML = msg;
     	msgEl.slideIn('r', {stopFx:true,duration:.1});
     }
     
    /*
     * 刷新指定的节点
     */
    function reloadTree(node){
    	if(node) {
			node = node.attributes.leaf!=null ? node.parentNode : node;
			sm.select(node);
			node.reload();
		}
    }
    function refreshTree(){
    	reloadTree(croot);
    	
    } 

	/* 拖拽节点时的处理
	 * 
	 */
    function hasNode(t, n){
        return (t.attributes.type == 'fileCt' && t.findChild('id', n.id)) ||
            (t.leaf === true && t.parentNode.findChild('id', n.id));
    };

    function isSourceCopy(e, n){
    	return false;
//        var a = e.target.attributes;
//        return n.getOwnerTree() == stree && !hasNode(e.target, n) &&
//           ((e.point == 'append' && a.type == 'fileCt') || a.leaf === true);
    };

    function isReorder(e, n){
        return n.parentNode == e.target.parentNode && e.point != 'append';
    };


});