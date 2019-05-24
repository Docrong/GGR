/**
 * 权限分配模块布局
 * 
 * @version 0.1 080110
 * @author mios
 */
var Options = eoms.form.Options;
var PrivAssigner = function() {
	// private
	var layout, treeroot, treesm, treeCtxMenu, form, formData, sbmBtn, selNode, t, deptCtxMenu;

	// public
	return {
		init : function() {
			pageFrame = Ext.get(config.pageFrameId).dom;
			var xt = Ext.tree;
			t = new Ext.Template(
					'<div>',
					'<span class="textHeader">选中的项目：</span><br/><br/>{type} {name}<br/><br/>',
					'</div>');
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
				east : {
					split : true,
					titlebar : false,
					tabPosition : 'top',
					alwaysShowTabs : true,
					initialSize : 500,
					minSize : 450,
					maxSize : 550,
					margins : {
						left : 0,
						right : 5,
						bottom : 5,
						top : 0
					}
				},
				center : {
					titlebar : false,
					autoScroll : true,
					tabPosition : 'top',
					alwaysShowTabs : true,
					margins : {
						left : 5,
						right : 0,
						bottom : 5,
						top : 0
					}
				}
			});
			layout.beginUpdate();
			layout.add('north', new Ext.ContentPanel('headerPanel'));
			var formPanel = layout.add('east', new Ext.ContentPanel(
					'formPanel', {
						id : 'formPanel',
						title : '信息',
						fitToFrame : true,
						autoScroll : true
					}));
			formPanel.on('activate', function() {
					// form.reset();
				});

			var up = layout.add('center', new Ext.ContentPanel('userTreePanel', {
				id : 'up',
				title : '分配给部门/人员'
			}));
			layout.add('center', new Ext.ContentPanel('roleTreePanel', {
				id : 'rp',
				title : '分配给角色'
			}));
			layout.add('east', new Ext.ContentPanel('helpPanel', {
				title : '帮助',
				fitToFrame : true,
				autoScroll : true
			}));
			layout.add('east', new Ext.ContentPanel('regionPanel', {
				title : '分配机房域',
				id : 'regionPanel',
				fitToFrame : true,
				autoScroll : true
			}));
			this.initRegion("east", "helpPanel");
			
			// restore any state information
			layout.restoreState();

			layout.getRegion("center").setActivePanel(up);
			layout.endUpdate();
			/*******************************************************************
			 * 创建角色树图
			 ******************************************************************/
			var roleTree = new xt.TreePanel('roleTreePanel', {
				containerScroll : true,
				lines : true,
				rootVisible : true
			});

			roleTreeRoot = new xt.AsyncTreeNode({
				id : '1',
				allowEdit : false,
				text : "角色树",
				loader : new xt.TreeLoader({
					dataUrl : config.actions.roleTree.url
				})
			});
			roleTree.setRootNode(roleTreeRoot);
			roleTree.render();

			roleTree.on('contextmenu', prepareCtx);
			roleTree.el.swallowEvent('contextmenu', true);
			/*******************************************************************
			 * 创建用户树图
			 ******************************************************************/
			var userTree = new xt.TreePanel('userTreePanel', {
				containerScroll : true,
				lines : true,
				rootVisible : true
			});

			userTreeRoot = new xt.AsyncTreeNode({
				id : '-1',
				allowEdit : false,
				text : "部门/人员树",
				loader : new xt.TreeLoader({
					dataUrl : config.actions.userTree.url
				})
			});
			userTree.setRootNode(userTreeRoot);
			userTree.render();

			userTree.on('contextmenu', prepareCtx);
			userTree.el.swallowEvent('contextmenu', true);

			/*******************************************************************
			 * 初始化树图区域
			 ******************************************************************/
			var treeRegion = layout.getRegion('center');
			treeRegion.on('panelactivated', function(r, p) {
				switch (p.id) {
					case "rp" :
						this.curtTree = roleTree;
						break;
					case "up" :
						this.curtTree = userTree;
						break;
				}
			},this);
			this.curtTree = roleTree;
			
			/*******************************************************************
			 * 树图右键菜单
			 ******************************************************************/
			if(config.ctxMenu){			
		    	treeCtxMenu = new Ext.menu.Menu();
		 		Ext.each(config.ctxMenu,function(m){
			 		if(m.url){
			 			m.handler = function(){
			 				selNode = PrivAssigner.getSelNode();
		 					PrivAssigner.openPanel('regionPanel',m.text + ": " + selNode.text);
		 					pageFrame.src = m.url+"?objId="+selNode.attributes.id+"&nodeType="+selNode.attributes.nodeType;
			 			};
			 		}
		 			treeCtxMenu.add(m);
		 		},this);
		 	}
			if(config.deptCtxMenu){
				deptCtxMenu = new Ext.menu.Menu();
		 		Ext.each(config.deptCtxMenu,function(m){
		 			deptCtxMenu.add(m);
		 		},this);
			}
			function prepareCtx(node, e) {
				if (node.attributes.nodeType == 'user' || node.attributes.nodeType == 'subrole') {
					node.select();
					treeCtxMenu.showAt(e.getXY());
				}
				if(node.attributes.nodeType == 'dept'){
					node.select();
					deptCtxMenu.showAt(e.getXY());					
				}
			}
			try {
				config.onLoadFunctions();
			} catch (e) {
			};
		}, // init 底部
		getSelNode : function(){
			return this.curtTree.getSelectionModel().selNode;
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
			var _region = layout.getRegion('east');
			var _panel = _region.getPanel(p);
			this.initRegion('east', 'helpPanel');

			_region.showPanel(_panel);
			if (title != null)
				_panel.setTitle(title);
			_panel.fireEvent('activate');
		},
		collapseAll : function() {
			treeCtxMenu.hide();
			setTimeout(function() {
				treeroot.eachChild(function(n) {
					n.collapse(false, false);
				});
			}, 10);
		},

		expandAll : function() {
			treeCtxMenu.hide();
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
			if (node) {
				node = node.attributes.leaf != null ? node.parentNode : node;
				// node.select();
				node.reload();
			}
		},
		loadAsgd : function() {
			selNode = this.getSelNode();
			var url = config.actions.getPrivAsgd.url + "&type="
					+ selNode.attributes.nodeType + "&objectid="
					+ selNode.attributes.id;
			Ext.lib.Ajax.request("get", url, {
				scope : PrivAssigner,
				success : function(x) {
					var r = Ext.decode(x.responseText);
					//Options.loadJSON("extendAsg", r.extendAsg);
					Options.loadJSON("privAsgd", r.asg);
					Options.loadJSON("roleAsg", r.roleAsg);
					Options.loadJSON("deptAsg", r.deptAsg);
					Options.loadJSON("alldeptAsg", r.alldeptAsg);
					Options.loadJSON("allAsg", r.allAsg);
					this.openPanel('formPanel', '分配权限');
					// this.updateName();
				},
				failure : function() {
					Ext.MessageBox.alert('提示', "载入权限失败");
				}
			});
		},
		updateName : function() {
			var re = " (未分配权限)";
			if ($("privAsgd").length > 0) {
				selNode.setText(selNode.attributes.text.replace(re, ""));
			} else if (selNode.attributes.text.indexOf(re) == -1) {
				selNode.setText(selNode.attributes.text + re);
			}
		},
		doEdtNode : function(a, b, c) {
			selNode = this.getSelNode();
			var type;
			switch (selNode.attributes.nodeType) {
				case "subrole" :
					type = "子角色";
					break;
				case "user" :
					type = "人员";
					break;
				case "dept" :
					type = "部门";
					break;
			}
			var name = selNode.attributes.text;
			t.overwrite("curItemInfo", {
				type : type,
				name : name
			}, false);

			this.loadAsgd();
		},
		addPriv : function() {
			selNode = this.getSelNode();
			if ($V("privSource") == "") {
				alert("请选择您要分配的菜单方案");
				return;
			}
			var privid = $V("privSource");
			var privName = $("privSource").options[$("privSource").selectedIndex].text;

			if (Options.findText("privAsgd", privName)) {
				alert("该菜单方案已分配，请选择其他菜单方案");
				return;
			}
			eoms.log(selNode);
			var assigntype = 3;
			switch (selNode.attributes.nodeType) {
				case "subrole" :
					assigntype = 3;
					break;
				case "user" :
					assigntype = 2;
					break;
				case "dept" :
					assigntype = 1;
					break;
			}
			layout.getEl().mask("处理中,请稍候");
			Ext.lib.Ajax.request("post", config.actions.addPrivAsg.url, {
				scope : PrivAssigner,
				success : function(x) {
					layout.getEl().unmask();
					var r = Ext.decode(x.responseText);
					Ext.MessageBox.alert('提示', r.message);
					this.loadAsgd();
				},
				failure : function() {
					layout.getEl().unmask();
					Ext.MessageBox.alert('提示', "保存中出现错误！");
				}
			}, "assigntype=" + assigntype + "&objectid=" + selNode.id
					+ "&privid=" + privid);

		},
		delPriv : function() {
			selNode = this.getSelNode();
			var assigntype = 3;
			switch (selNode.attributes.nodeType) {
				case "subrole" :
					assigntype = 3;
					break;
				case "user" :
					assigntype = 2;
					break;
				case "dept" :
					assigntype = 1;
					break;
			}
			 
			Ext.lib.Ajax.request("post", config.actions.delPrivAsg.url, {
				scope : PrivAssigner,
				success : function(x) {
					layout.getEl().unmask();
					var r = Ext.decode(x.responseText);
					Ext.MessageBox.alert('提示', r.message);
					this.loadAsgd();
				},
				failure : function() {
					layout.getEl().unmask();
					Ext.MessageBox.alert('提示', "保存中出现错误！");
				}
			}, "assigntype=" + assigntype + "&id=" + $V("privAsgd"));
		},
		doDelNode : function() {
			// 弹出一个确认框
			Ext.MessageBox.confirm('确认:', '您确定删除这个项目吗', function(btn) {
				if (btn == "yes") {
					Ext.lib.Ajax.request("post", config.actions.delNode.url, {
						scope : PrivAssigner,
						success : function() {
							var n = treesm.getSelectedNode();
							if (n && n.attributes.allowDelete) {
								treesm.selectPrevious();
								n.parentNode.removeChild(n);
							}
							this.initRegion('east', 'helpPanel');
							Ext.MessageBox.alert('提示', "您成功删除了一个节点。");
						},
						failure : function() {
							Ext.MessageBox.alert('提示', "删除失败");
						}
					}, "id=" + selNode.id);
				}
			});
		},
		setField : function(fid, aid) {
			form.findField(fid).setValue(eval("(selNode.attributes." + aid
					+ ")"));
		}

	} // return 底部
}(); // PrivAssigner 底部

