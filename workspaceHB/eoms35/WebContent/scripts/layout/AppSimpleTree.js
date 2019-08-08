/**
 * 对树图数据进行简单增删改操作的模板
 * @version 0.3.3 080917
 * @author mios
 */
Ext.QuickTips.init();
var AppSimpleTree = function () {
    //private
    var layout, treeroot, treesm, treeCtxMenu, form, formData, sbmBtn;

    //public 
    return {
        reloadStatus: {
            deleted: true
        },
        // {Object}选中的数据
        selected: null,
        // {Ext TreeNode} 选中的树图节点
        selectedNode: null,
        // {Ext Menu}右键菜单
        treeCtxMenu: new Ext.menu.Menu(),
        init: function () {
            Ext.get(document.body).setStyle("background-image", "none");
            var xt = Ext.tree;

            /**************
             * 创建树图工具栏
             **************/
            var tb = new Ext.Toolbar('treePanel-tb');
            tb.add({
                id: 'expand', text: '展开树图', cls: 'x-btn-text-icon expand',
                handler: this.expandAll, tooltip: '展开树图'
            }, {
                id: 'collapse', text: '收起树图', cls: 'x-btn-text-icon collapse',
                handler: this.collapseAll, tooltip: '收起树图'
            }, {
                id: 'refresh', text: '刷新树图', cls: 'x-btn-text-icon refresh',
                handler: this.refreshTree, tooltip: '刷新树图', scope: this
            });
            var btns = tb.items.map;

            /**************
             * 初始化布局
             **************/
            layout = new Ext.BorderLayout(document.body, {
                north: {
                    initialSize: 40, titlebar: false,
                    margins: {left: 5, right: 5, bottom: -1, top: 5}
                },
                east: {
                    split: true, titlebar: false, tabPosition: 'top', alwaysShowTabs: true,
                    initialSize: 300, minSize: 200, maxSize: 400,
                    margins: {left: 0, right: 5, bottom: 5, top: 0}
                },
                center: {
                    titlebar: false, autoScroll: true, tabPosition: 'top', hideTabs: config.hideSearchPanel != false,
                    margins: {left: 5, right: 0, bottom: 5, top: 0}
                }
            });
            layout.beginUpdate();
            layout.add('north', new Ext.ContentPanel('headerPanel'));
            var formPanel = layout.add('east', new Ext.ContentPanel('formPanel', {
                id: 'formPanel', title: '信息', fitToFrame: true, autoScroll: true
            }));
            formPanel.on('activate', function () {
                form.reset();
            });

            layout.add('center', new Ext.ContentPanel('searchPanel', {
                id: 'searchPanel',
                autoCreate: true,
                title: '搜索结果',
                fitToFrame: true,
                background: true
            }));
            layout.add('center', new Ext.ContentPanel('treePanel', {
                toolbar: tb,
                title: '树图'
            }));
            layout.add('east', new Ext.ContentPanel('helpPanel', {
                title: '帮助', fitToFrame: true, autoScroll: true
            }));
            this.initRegion("east", "helpPanel");
            // restore any state information
            layout.restoreState();
            layout.endUpdate();
            layout.getRegion("center").hidePanel('searchPanel');
            this.layout = layout;

            /**************
             * 创建树图
             **************/
            var treeLoader = new xt.TreeLoader({
                dataUrl: config.treeGetNodeUrl
            });

            var tree = new xt.TreePanel('treePanel-body', {
                animate: true,
                enableDD: false,
                containerScroll: true,
                lines: true,
                rootVisible: true
            });

            treeroot = new xt.AsyncTreeNode({
                id: config.treeRootId,
                allowDrag: false, allowDrop: false,
                allowChild: true, allowEdit: false, allowDelete: false,
                text: config.treeRootText,
                loader: treeLoader
            });
            tree.setRootNode(treeroot);
            tree.render();
            treeroot.expand();

            treesm = tree.getSelectionModel();

            treeLoader.on('beforeload', function (treeLoader, node, callback) {
                treeLoader.baseParams['nodeType'] = (node.attributes.nodeType || '');
            });
            /**************
             * 树图右键菜单
             **************/
            var miNewNode = {
                id: 'newnode', text: '新建子节点', cls: 'new-mi', scope: this,
                handler: this.doNewNode
            }
            Ext.apply(miNewNode, config.ctxMenu.newNode);

            var miEdtNode = {
                id: 'edtnode', text: '修改', cls: 'edit-mi', scope: this,
                handler: function () {
                    this.doEdtNode(this.selected);
                }
            };
            Ext.apply(miEdtNode, config.ctxMenu.edtNode);

            var miDelNode = {
                id: 'delnode', text: '删除', cls: 'remove-mi', scope: this,
                handler: function () {
                    this.doDelNode(this.selected, this.selectedNode);
                }
            };
            Ext.apply(miDelNode, config.ctxMenu.delNode);

            this.treeCtxMenu.add(miNewNode, miEdtNode, miDelNode);

            function prepareCtx(node, e) {
                node.select();
                this.selected = node.attributes;
                this.selectedNode = node;
                var mis = this.treeCtxMenu.items;
                var mis_hidden = true;
                mis.get('newnode')[node.attributes.allowChild ? 'show' : 'hide']();
                mis.get('delnode')[node.attributes.allowDelete ? 'show' : 'hide']();
                mis.get('edtnode')[(node.isRoot || node.attributes.allowEdit === false) ? 'hide' : 'show']();
                mis.each(function (mi) {
                    if (mi.nodeTypeMapping) {
                        mi[mi.nodeTypeMapping == node.attributes.nodeType ? 'show' : 'hide']();
                    }
                    if (!mi.hidden)
                        mis_hidden = false;
                });
                if (!mis_hidden) {
                    this.treeCtxMenu.showAt(e.getXY());
                }
            }

            /**************
             * 树图事件处理
             **************/
            tree.on('contextmenu', prepareCtx, this);

            tree.el.swallowEvent('contextmenu', true);
            tree.el.addKeyListener(Ext.EventObject.DELETE, this.doDelNode);
            tree.el.on('keypress', function (e) {
                if (e.isNavKeyPress()) {
                    e.stopEvent();
                }
            });
            /**************
             * 表单
             **************/
            form = new Ext.form.Form({
                labelWidth: 50, labelAlign: 'top', buttonAlign: 'left'
            });

            Ext.each(config.fields, function (f) {
                form.add(f);
            });
            form.applyToFields(config.fieldOptions);

            sbmBtn = form.addButton('保存', function () {
                if (form.isValid()) {
                    form.submit({
                        failure: function (form, x) {
                            var response = eoms.JSONDecode(x.response.responseText);
                            Ext.MessageBox.alert('操作执行时出现问题!', response.message || "操作执行失败");
                        },
                        success: function () {
                            Ext.MessageBox.alert('提示', "操作执行成功");
                            this.initRegion("east", "helpPanel");
                            if (form.url == config.actions.edtNode.url) {
                                if (config.actions.edtNode.success) {
                                    config.actions.edtNode.success();
                                } else {
                                    this.reloadNode(this.selectedNode);
                                }
                            } else if (form.url == config.actions.newNode.url) {
                                if (config.actions.newNode.success) {
                                    config.actions.newNode.success();
                                } else {
                                    this.reloadNode(this.selectedNode);
                                }
                            }
                        },
                        scope: AppSimpleTree,
                        waitMsg: '保存中...'
                    });
                } else {
                    Ext.MessageBox.alert('提示', "表单数据填写不正确，请检查。");
                }
            });

            form.render('formPanel-body');

            formData = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url: config.actions.getNode.url}),
                reader: new Ext.data.JsonReader({}, [])
            });
            formData.on('load', function () {
                this.savedFormData = formData.reader.jsonData;
                form.setValues(formData.reader.jsonData);
                this.onFormLoad(formData.reader.jsonData);
            }, this);

            this.form = form;
            this.formData = formData;
            this.sbmBtn = sbmBtn;
            try {
                config.onLoadFunctions();
            } catch (e) {
            }
            ;

        }, // init 底部
        initRegion: function (r, defaultPanel) {
            var _region = layout.getRegion(r);
            var _panel = _region.getPanel(defaultPanel);
            _region.panels.each(function (p) {
                if (p != _panel) {
                    _region.hidePanel(p);
                }
            });
            _region.showPanel(_panel);
        },
        openPanel: function (p, title) {
            var _region = layout.getRegion('east');
            var _panel = _region.getPanel(p);
            this.initRegion('east', 'helpPanel');

            _region.showPanel(_panel);
            if (title != null) _panel.setTitle(title);
            _panel.fireEvent('activate');
        },
        openPanelOfRegion: function (region, panel, title) {
            var _region = layout.getRegion(region);
            var _panel = _region.getPanel(panel);
            _region.showPanel(_panel);
            if (title) _panel.setTitle(title);
            _panel.fireEvent('activate');
        },
        collapseAll: function () {
            setTimeout(function () {
                treeroot.eachChild(function (n) {
                    n.collapse(false, false);
                });
            }, 10);
        },

        expandAll: function () {
            setTimeout(function () {
                treeroot.eachChild(function (n) {
                    n.expand(false, false);
                });
            }, 10);
        },
        refreshTree: function () {
            treeroot.reload();
        },
        reloadNode: function (node) {
            if (node) {
                node = node.parentNode || node;
                treesm.select(node);
                node.reload();
            }
        },
        getSelNode: function (a) {
            if (a)
                return this.selected[a];
            else
                return this.selectedNode;
        },
        /**
         * 在数图右键菜单添加跟节点类型对应的菜单项
         * @param {String} nodeType 对应的节点类型
         * @param {Object} config 菜单项设置
         * @param (Number) index 添加到第几项
         */
        addmenu: function (nodeType, config, index) {
            var newmi = index >= 0
                ? this.treeCtxMenu.insert(index, new Ext.menu.Item(config))
                : this.treeCtxMenu.add(config);
            newmi.nodeTypeMapping = nodeType;
        },
        doNewNode: function () {
            this.openPanel('formPanel', '在' + this.selected.text + ' 下新建');
            form.url = config.actions.newNode.url;
            sbmBtn.setText(config.actions.newNode.btnText);
            try {
                config.actions.newNode.init();
            } catch (e) {
            }
            ;
            this.savedFormData = null;
        },
        doEdtNode: function (nodeData) {
            this.openPanel('formPanel', '修改' + nodeData.text + '的信息');
            form.url = config.actions.edtNode.url;
            sbmBtn.setText(config.actions.edtNode.btnText);
            try {
                config.actions.edtNode.init();
            } catch (e) {
            }
            ;
            formData.load({params: {id: nodeData.id}});
        },
        onFormLoad: function () {
        },
        doDelNode: function (nodeData, node) {
            var params = "id=" + nodeData.id;

            if (typeof config.actions.delNode.customData == "function") {
                params = config.actions.delNode.customData(nodeData);
            }

            //弹出一个确认框
            Ext.MessageBox.confirm('确认:', '您确定删除这个项目吗',
                function (btn) {
                    if (btn == "yes") {
                        Ext.Ajax.request({
                            method: 'post',
                            url: config.actions.delNode.url,
                            scope: AppSimpleTree,
                            success: function () {
                                if (node) {
                                    treesm.selectPrevious();
                                    node.parentNode.removeChild(node);
                                }
                                this.initRegion('east', 'helpPanel');
                                this.reloadStatus.deleted = true;
                                Ext.MessageBox.alert('提示', "您成功删除了一个节点。");
                            },
                            failure: function () {
                                Ext.MessageBox.alert('提示', "删除失败");
                            },
                            params: params
                        });
                    }
                }
            );
        },
        setField: function (fid, attr) {
            form.findField(fid).setValue(this.selected[attr]);
        }

    } //return 底部
}(); //AppSimpleTree 底部
