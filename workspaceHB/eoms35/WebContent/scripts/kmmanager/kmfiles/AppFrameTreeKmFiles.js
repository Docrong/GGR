/**
 * 树图数据界面嵌入普通页面的模板
 * 
 * @version 0.2 080603
 * @author mios
 */
Ext.QuickTips.init();
var AppFrameTree = function() {
	// private
	var layout, treeroot, treesm, treeCtxMenu, form, formData, sbmBtn, pageFrame;
	// public
	return {
		selNode : null,
		init : function() {
			pageFrame = Ext.getDom(config.pageFrameId);
			if (pageFrame.name == "" || pageFrame.name == null){
				pageFrame.name = config.pageFrameId;
			}
				
			form = Ext.DomHelper.append(document.body, {
				tag : 'form',
				method : 'post',
				style : 'display:none',
				target : pageFrame.name,
				cn : [{
					tag : 'input',
					type : 'hidden',
					name : 'node'
				}, {
					tag : 'input',
					type : 'hidden',
					name : 'nodeType'
				}]
			});

			var xt = Ext.tree;

			/*******************************************************************
			 * 创建树图工具栏
			 ******************************************************************/
			var tb = new Ext.Toolbar('treePanel-tb');
			tb.add({
				id : 'expand',
				text : '展开树图',
				cls : 'x-btn-text-icon expand',
				handler : this.expandAll,
				tooltip : '展开树图'
			}, {
				id : 'collapse',
				text : '收起树图',
				cls : 'x-btn-text-icon collapse',
				handler : this.collapseAll,
				tooltip : '收起树图'
			}, {
				id : 'refresh',
				text : '刷新树图',
				cls : 'x-btn-text-icon refresh',
				handler : this.refreshTree,
				tooltip : '刷新树图'
			});
			var btns = tb.items.map;

			/*******************************************************************
			 * 初始化布局
			 ******************************************************************/
			layout = new Ext.BorderLayout(document.body, {
				north : {
					initialSize : 40,
					titlebar : false,
					margins : {
						left : 5,
						right : 5,
						bottom : -1,
						top : 5
					}
				},
				west : {
					split : true,
					titlebar : true,
					collapsible: true,
					autoScroll : true,
					initialSize : 300,
					minSize : 200,
					maxSize : 400,
					margins : {
						left : 5,
						right : 0,
						bottom : 5,
						top : 0
					}
				},
				center : {
					titlebar : false,
					tabPosition : 'top',
					alwaysShowTabs : true,
					margins : {
						left : 0,
						right : 5,
						bottom : 5,
						top : 0
					}
				}
			});
			layout.beginUpdate();
			layout.add('north', new Ext.ContentPanel('headerPanel'));
			var formPanel = layout.add('center', new Ext.ContentPanel(
					'formPanel', {
						id : 'formPanel',
						title : '信息',
						fitToFrame : true,
						autoScroll : true
					}));

			layout.add('west', new Ext.ContentPanel('treePanel', {
				toolbar : tb
			}));
			layout.add('center', new Ext.ContentPanel('helpPanel', {
				title : '帮助',
				fitToFrame : true,
				autoScroll : true
			}));
			this.initRegion("center", "helpPanel");
			// restore any state information
			layout.restoreState();
			layout.endUpdate();

			/*******************************************************************
			 * 创建树图
			 ******************************************************************/
			var treeLoader = new xt.TreeLoader({
				dataUrl : config.treeGetNodeUrl
			});
			var tree = new xt.TreePanel('treePanel-body', {
				animate : true,
				enableDD : false,
				containerScroll : true,
				lines : true,
				rootVisible : true
			});

			treeroot = new xt.AsyncTreeNode({
				id : config.treeRootId,
				allowDrag : false,
				allowDrop : false,
				allowChild : true,
				allowEdit : false,
				allowDelete : false,
				allowClick: false,
				text : config.treeRootText,
				cls : 'croot',
				loader : treeLoader
			});
			tree.setRootNode(treeroot);
			tree.render();
			treeroot.expand();

			treesm = tree.getSelectionModel();
			treesm.on('selectionchange', function() {
				this.selNode = treesm.selNode;
			}, this);

			treeLoader.on('beforeload', function(treeLoader, node, callback) {
				treeLoader.baseParams['nodeType'] = (node.attributes.nodeType || '');
			});

			/*******************************************************************
			 * 树图右键菜单
			 ******************************************************************/
			if (config.ctxMenu) {
				treeCtxMenu = new Ext.menu.Menu();
				Ext.each(config.ctxMenu, function(m) {
					treeCtxMenu.add(m);
				}, this);
				treeCtxMenu.on('click', this.submit, this);
				tree.on('contextmenu', this.prepareCtx, this);
				tree.el.swallowEvent('contextmenu', true);
			}

			/*******************************************************************
			 * 树图事件处理
			 ******************************************************************/
			if (config.onClick) {
				tree.on('click', this.onClick, this);
			}
			tree.el.on('keypress', function(e) {
				if (e.isNavKeyPress()) {
					e.stopEvent();
				}
			});

			try {
				config.onLoadFunctions();
			} catch (e) {
			};
			window.onerror = function() {
				Ext.Msg.alert('提示', '抱歉，操作执行中发生了错误，请重新载入页面。');
			}
		}, // init 底部
		prepareCtx : function(node,e){
			node.select();
			isMenuShow = false;
			treeCtxMenu.items.each(function(item) {
				var allow;
				if (node.isRoot) {
					allow = item.id == "newnode"
							? true
							: item['rootCanShow'];
				} else {
					var idfix = (item.id == "newnode"
							? "Child"
							: (item.id == "edtnode"
									? "Edit"
									: (item.id == "delnode"
											? "Delete"
											: item.id)));
					allow = node.attributes['allow' + idfix];
				}
				item[allow ? 'show' : 'hide']();
				if (!item.hidden)
					isMenuShow = true;
			});
			if (isMenuShow)
				treeCtxMenu.showAt(e.getXY());	
		},
		/**
		 * 树图点击时处理
		 */
		onClick : function(node, e) {
			//if (node.attributes.leaf || node.attributes.allowClick) {
			if (node.attributes.allowClick) {
				this.submit(null,config.onClick,e);
			}
		},
		/**
		 * 树图点击或右键菜单点击时，提交请求
		 * @param menu 右键菜单，节点点击时为null
		 * @param m 右键菜单项，节点点击时为config.onClick对象
		 * @param e 事件
		 */
		submit : function(menu, m, e) {
			if (m['isDelete'] == true && !confirm("您确定删除这个项目吗")) {
				return;
			}
			if (!this.selNode)
				return;

			this.openPanel('formPanel', this.selNode.text + ' ' + m.text);
			form['nodeType'].value = this.selNode.attributes.nodeType;
			form['node'].value = this.selNode.attributes.id;
			form.action = this.fixUrl(m.url + this.selNode.attributes.id);
			this.onBeforeSubmit(form,this.selNode);
			form.submit();
		},
		/**
		 * 提交前事件
		 * @param form 隐藏的表单
		 * @param node 当前选中的节点
		 */
		onBeforeSubmit : function(form,node){},
		/**
		 * 修正form提交的action地址，添加参数
		 * @param url 点击的链接地址或右键菜单的链接地址
		 */
		fixUrl : function(url) {
			if (url.substr(url.length - 1) == "=") {
				return url + this.selNode.id;
			} else if (url.indexOf('?') > 0) {
				return url + "&id=" + this.selNode.id;
			} else {
				return url + "?id=" + this.selNode.id;
			}
		},
		initRegion : function(r, defaultPanel) {
			var _region = layout.getRegion(r);
			var _panel = _region.getPanel(defaultPanel);
			_region.panels.each(function(p) {
				if (p != _panel) {
					_region.hidePanel(p);
				}
			});
			_region.showPanel(_panel);
		},
		openPanel : function(p, title) {
			var _region = layout.getRegion('center');
			var _panel = _region.getPanel(p);
			this.initRegion('center', 'helpPanel');

			_region.showPanel(_panel);
			if (title != null)
				_panel.setTitle(title);
			_panel.fireEvent('activate');
		},
		collapseAll : function() {
			setTimeout(function() {
				treeroot.eachChild(function(n) {
					n.collapse(false, false);
				});
			}, 10);
		},

		expandAll : function() {
			setTimeout(function() {
				treeroot.eachChild(function(n) {
					n.expand(false, false);
				});
			}, 10);
		},
		refreshTree : function() {
			treeroot.reload();
		},
		reloadNode : function(node) {
			if (arguments.length == 0)
				node = this.selNode;
			if (node) {
				node = node.attributes.leaf != null ? node.parentNode : node;
				treesm.select(node);
				node.reload();
			}
		}
	} // return 底部
}(); // AppSimpleTree 底部
