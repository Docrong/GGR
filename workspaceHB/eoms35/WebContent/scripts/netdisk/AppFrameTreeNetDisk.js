/** 树图数据界面嵌入普通页面的模板
 * @version 0.1.1 071119 
 * @author mios
 */
Ext.QuickTips.init();
var AppFrameTree = function(){
	//private
	var layout,treeroot,treesm,treeCtxMenu,form,formData,sbmBtn,selNode,pageFrame;
	
	//public 
	return {
		init : function(){
			pageFrame = Ext.get(config.pageFrameId).dom;
			var xt = Ext.tree;	
            
            /**************
		 	* 创建树图工具栏
		 	**************/
		    var tb = new Ext.Toolbar('treePanel-tb');
		    tb.add({
		        id:'expand', text:'展开树图', cls:'x-btn-text-icon expand',
		        handler:this.expandAll, tooltip:'展开树图'
		    },{
		        id:'collapse', text:'收起树图', cls:'x-btn-text-icon collapse',
		        handler:this.collapseAll, tooltip:'收起树图'
		    },{
		        id:'refresh', text:'刷新树图', cls:'x-btn-text-icon refresh',
		        handler:this.refreshTree, tooltip:'刷新树图'
		    });
		    var btns = tb.items.map;
		       		
			/**************
			 * 初始化布局
			 **************/    		
			layout = new Ext.BorderLayout(document.body, {        
		    	north: {
		            initialSize: 40, titlebar: false,      
		            margins:{left:5,right:5,bottom:-1,top:5}
		        },
		        west: {
		            split:true,titlebar: false, autoScroll:true,
		            initialSize: 300, minSize: 200, maxSize: 400,
		            margins:{left:5,right:0,bottom:5,top:0}
		        },
		        center: {
		            titlebar: false,tabPosition:'top', alwaysShowTabs: true,
		            margins:{left:0,right:5,bottom:5,top:0}
		        }
			});
			layout.beginUpdate();
            layout.add('north', new Ext.ContentPanel('headerPanel'));
            var formPanel = layout.add('center', new Ext.ContentPanel('formPanel', {
            	id:'formPanel', title: '信息', fitToFrame:true, autoScroll:true
            }));
    		
            layout.add('west', new Ext.ContentPanel('treePanel', {
            	toolbar: tb
            }));
            layout.add('center', new Ext.ContentPanel('helpPanel', {
            	title:'帮助',fitToFrame:true, autoScroll:true
            }));
            this.initRegion("center","helpPanel");
            // restore any state information
            layout.restoreState();
            layout.endUpdate();
            
            /**************
		 	* 创建树图
		 	**************/
	 		var treeLoader = new xt.TreeLoader({
	            dataUrl:config.treeGetNodeUrl
	        });
		    var tree = new xt.TreePanel('treePanel-body', {
		        animate:true,
		        enableDD:false,
		        containerScroll: true,
		        lines:true,
		        rootVisible:true
		    });
				
		    treeroot = new xt.AsyncTreeNode({
		    	id:config.treeRootId,
		        allowDrag:false,allowDrop:true,
		        allowChild:false,allowEdit:false,allowDelete:false,
		        allowList:false, allowShare:false,
		        text:config.treeRootText,
		        cls:'croot',
		        loader:treeLoader
		    });
		    tree.setRootNode(treeroot);
		    tree.render();
		    treeroot.expand();
		    
		    treesm = tree.getSelectionModel();
    			treesm.on('selectionchange', function(){
        			selNode = this.selNode;
    		});
    		
    		treeLoader.on('beforeload',function(treeLoader,node,callback){
		    	treeLoader.baseParams['nodeType'] = (node.attributes.nodeType || '');
		    });
		        		
            /**************
		 	* 树图右键菜单
		 	**************/
		 	if(config.ctxMenu){
		    	treeCtxMenu = new Ext.menu.Menu();
		 		Ext.each(config.ctxMenu,function(m){
		 			Ext.apply(m,{
		 				handler: function(){
		 					if(m['isDelete']===true){
	    			              if(confirm("您确定删除这个项目吗")){
									AppFrameTree.openPanel('formPanel',selNode.text+' '+m.text);
		 					        pageFrame.src = m.url+selNode.id+'&nodeType='+selNode.attributes.nodeType;
	    			              }
		 					}
		 					else{
		 					  AppFrameTree.openPanel('formPanel',selNode.text+' '+m.text);
		 					  pageFrame.src = m.url+selNode.id+'&nodeType='+selNode.attributes.nodeType;
		 					}
		 				}
		 			});
		 			treeCtxMenu.add(m);
		 		},this);
		 		tree.on('contextmenu', prepareCtx);
		 	}
		    
		    function prepareCtx(node, e){
		        node.select();
		        var mitems = treeCtxMenu.items;
		        if(mitems.get('newnode'))
		          mitems.get('newnode')[node.attributes.allowChild ? 'enable' : 'disable']();	        
		        if(mitems.get('delnode'))
		          mitems.get('delnode')[node.attributes.allowDelete ? 'enable' : 'disable']();       
		        if(mitems.get('edtnode'))
		          mitems.get('edtnode')[(node.isRoot || node.attributes.allowEdit===false) ? 'disable' : 'enable']();
		        if(mitems.get('listnode'))
		          mitems.get('listnode')[(node.isRoot || node.attributes.allowList===false) ? 'disable' : 'enable']();
		        if(mitems.get('sharenode'))
		          mitems.get('sharenode')[(node.isRoot || node.attributes.allowShare===false) ? 'disable' : 'enable']();
		        treeCtxMenu.showAt(e.getXY());
    		}
    			    
		    /**************
		 	* 树图事件处理
		 	**************/
		 	if(config.onClick){
		      tree.on('click',function(node,e){
		      	if(node.attributes.leaf!=null && node.id=="search"){
		    	  this.openPanel('formPanel',selNode.text+' '+config.onClick.text);
		    	  pageFrame.src=config.onClick.url+node.id;
		      	}
		      },this);
		 	}
		    tree.el.on('keypress', function(e){
		        if(e.isNavKeyPress()){
		            e.stopEvent();
		        }
		    });
 		 	
		 	try{config.onLoadFunctions();}catch(e){};
		 	window.onerror = function(){
		 		Ext.Msg.alert('提示','抱歉，操作执行中发生了错误，请重新载入页面。');
		 	}
		}, // init 底部
		initRegion : function(r,defaultPanel){
			var _region = layout.getRegion(r);
			var _panel = _region.getPanel(defaultPanel);
			_region.panels.each(function(p){
				if(p!=_panel){
					_region.hidePanel(p);
				}
			});
			_region.showPanel(_panel);			
		},
		openPanel : function(p,title){
			var _region = layout.getRegion('center');
			var _panel = _region.getPanel(p);
			this.initRegion('center','helpPanel');
			
			_region.showPanel(_panel);
			if(title!=null) _panel.setTitle(title);
			_panel.fireEvent('activate');			
		},
		collapseAll : function(){
	        setTimeout(function(){
	            treeroot.eachChild(function(n){
	               n.collapse(false, false);
	            });
	        }, 10);
    	},
    
    	expandAll : function(){
	        setTimeout(function(){
	            treeroot.eachChild(function(n){
	               n.expand(false, false);
	            });
	        }, 10);
    	},
    	refreshTree : function(){
    		treeroot.reload();
    	},
    	reloadNode : function(node){
    		if(arguments.length==0)node = selNode;
			if(node) {
				node = node.attributes.leaf!=null ? node.parentNode : node;
				treesm.select(node);
				node.reload();
			}
    	},
    	getSelNode : function(a){
    		if(a)
    			return eval("(treesm.selNode.attributes."+a+")");
    		else
    			return selNode;
    	}
    	
	} //return 底部
}(); //AppSimpleTree 底部
