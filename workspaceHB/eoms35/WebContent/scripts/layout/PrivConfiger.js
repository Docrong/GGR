var PrivConfiger = function () {
    //private
    var layout, grid, gridds, gridEl, treeroot, treesm, treeCtxMenu, form, formData, sbmBtn, selRowRecord;
    var proot, selNode;
    //public 
    return {
        init: function () {
            var xt = Ext.tree;

            /**************
             * 创建工具栏
             **************/
            var tb = new Ext.Toolbar('gridPanel-tb');
            tb.add({
                id: 'tb-newMenu', text: '新增菜单方案', cls: 'x-btn-text-icon add',
                handler: this.doNewMenu, tooltip: '新增菜单方案', scope: this
            });

            /**************
             * 初始化布局
             **************/
            layout = new Ext.BorderLayout(document.body, {
                north: {
                    initialSize: 40, titlebar: false,
                    margins: {left: 5, right: 5, bottom: -1, top: 5}
                },
                west: {
                    titlebar: false, autoScroll: true,
                    initialSize: 250,
                    margins: {left: 5, right: 0, bottom: 5, top: 0}
                },
                center: {
                    titlebar: false, tabPosition: 'top', alwaysShowTabs: true,
                    margins: {left: 5, right: 5, bottom: 5, top: 0}
                }
            });
            layout.beginUpdate();
            layout.add('north', new Ext.ContentPanel('headerPanel'));
            layout.add('west', new Ext.ContentPanel('gridPanel', {
                id: 'gridPanel', title: '信息', fitToFrame: true, autoScroll: true,
                toolbar: tb
            }));

            layout.add('center', new Ext.ContentPanel('helpPanel', {
                title: '帮助', fitToFrame: true, autoScroll: true
            }));
            var formPanel = layout.add('center', new Ext.ContentPanel('formPanel', {
                id: 'formPanel', title: '信息', fitToFrame: true, autoScroll: true
            }));
            var treePanel = layout.add('center', new Ext.ContentPanel('treePanel', {
                id: 'treePanel', title: '修改菜单树配置', fitToFrame: true, autoScroll: true
            }));
            this.initRegion("center", "helpPanel");
            // restore any state information
            layout.restoreState();
            layout.endUpdate();

            /**************
             * 创建表格
             **************/
            var gridColumns = ['id', 'name', 'ownerId', 'remark'];
            var columnModel = new Ext.grid.ColumnModel([
                {id: 'name', header: "菜单方案名称(双击查看)", dataIndex: 'name', width: '140'},
//				{header: "创建者ID",dataIndex: 'ownerId'},
                {id: 'remark', header: "备注", dataIndex: 'remark', css: 'white-space:normal;', width: '100'}
            ]);
            var pageSize = 30;
            var pageIndexTo = 0;

            // create the Data Store
            gridds = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({
                    url: gridAction
                }),

                reader: new Ext.data.JsonReader({
                    root: 'rows',
                    totalProperty: 'total',
                    id: 'id'
                }, gridColumns),

                remoteSort: true
            });
            gridds.on('beforeload', function () {
                //console.log(pageIndexTo);
                gridds.baseParams = {
                    'pageIndex': pageIndexTo,
                    'pageSize': pageSize
                };
            });

            grid = new Ext.grid.Grid('gridPanel-body', {
                ds: gridds,
                cm: columnModel,
                //autoExpandColumn:'remark',
                //autoSizeColumns: true,
                trackMouseOver: true,
                loadMask: true
            });

            grid.on('rowdblclick', this.doTreeCfg, this);

            grid.render();

            gridEl = grid.getGridEl();
            var gridFoot = grid.getView().getFooterPanel(true);

            // add a paging toolbar to the grid's footer
            var paging = new Ext.PagingToolbar(gridFoot, gridds, {
                pageSize: pageSize,
                displayInfo: true,
                //displayMsg: '显示 {0} - {1}项 共 {2}项',
                displayMsg: '',
                emptyMsg: '没有找到记录'
            });

            paging.next.on('click', function () {
                pageIndexTo = paging.getPageData().activePage + 1;
            });
            paging.prev.on('click', function () {
                pageIndexTo = paging.getPageData().activePage - 1;
            });
            paging.first.on('click', function () {
                pageIndexTo = 1;
            });
            paging.last.on('click', function () {
                pageIndexTo = paging.getPageData().pages;
            });
            paging.field.on('keyup', function () {
                //console.log('onsubmit:'+this.dom.value);
                pageIndexTo = this.dom.value;
            });

            pageIndexTo = 1;
            gridds.load({params: {start: 0, limit: pageSize}});

            gridSM = grid.getSelectionModel();
            gridSM.on('selectionchange', function () {
                selRowRecord = this.getSelected();
            });
            /**************
             * 创建树图
             **************/
                //菜单资源树
            var stree = new xt.TreePanel('treePanel-sourceTree', {
                    animate: true,
                    enableDrag: true,
                    containerScroll: true,
                    lines: true,
                    rootVisible: true,
                    loader: new xt.TreeLoader({dataUrl: config.treeGetNodeUrl})
                });

            streeroot = new xt.AsyncTreeNode({
                id: config.treeRootId,
                draggable: false,
                text: config.treeRootText
            });
            stree.setRootNode(streeroot);
            stree.render();
            streeroot.expand(false, false);


            //当前菜单方案树
            var ptree = new xt.TreePanel('treePanel-privTree', {
                animate: true,
                enableDD: true,
                containerScroll: true,
                loader: new Ext.tree.TreeLoader()
            });

            //ptree.el.addKeyListener(Ext.EventObject.DELETE, removeNode);
            var pTreeloader = new Ext.tree.TreeLoader({
                dataUrl: config.actions.getMenuNodes.url
            });
            pTreeloader.on("beforeload", function (treeLoader, node) {
                this.baseParams.privid = proot.privid;
                this.baseParams.parentcode = node.attributes.code;
            });
            proot = new xt.AsyncTreeNode({
                draggable: false,
                allowDelete: false,
                id: '-1',
                code: '-1',
                text: '当前菜单树',
                cls: 'croot',
                type: 'priv',
                loader: pTreeloader
            });

            //右键菜单-删除
            var ptreeMenu = new Ext.menu.Menu({
                items: [{text: '删除', handler: this.doDeleteTreeItem}],
                scope: this
            });

            function prepareTreeCtx(node, e) {
                node.select();
                if (node.attributes.allowDelete) {
                    ptreeMenu.showAt(e.getXY());
                }
            }

            ptree.on('contextmenu', prepareTreeCtx);
            //阻止默认的右键动作
            ptree.el.swallowEvent('contextmenu', true);

            ptree.setRootNode(proot);
            ptree.render();

            ptreesm = ptree.getSelectionModel();
            ptreesm.on('selectionchange', function () {
                selNode = this.selNode;
            });

            //拖放
            function hasNode(t, n) {
                return (t.findChild('code', n.attributes.code)) ||
                    (t.leaf === true && t.parentNode.findChild('code', n.attributes.code));
            };

            function isSourceCopy(e, n) {
                var a = e.target.attributes;
                return n.getOwnerTree() == stree && !hasNode(e.target, n) && a.code == n.attributes.parentcode &&
                    ((e.point == 'append') || a.leaf === true);
            };

            function isReorder(e, n) {
                return n.parentNode == e.target.parentNode && e.point != 'append';
            };

            ptree.on('nodedragover', function (e) {
                var n = e.dropNode;
                return isSourceCopy(e, n);
//		        return isSourceCopy(e, n) || isReorder(e, n);
            });

            ptree.on('beforenodedrop', function (e) {
                var n = e.dropNode;

                // copy node from source tree
                if (isSourceCopy(e, n)) {
                    var copy = new xt.TreeNode(
                        Ext.apply({allowDelete: true, expanded: true, type: 'priv'}, n.attributes)
                    );
                    copy.loader = undefined;
                    e.dropNode = copy;
                    return true;
                }

                return isReorder(e, n);
            });

            //拖放保存菜单项
            ptree.on('nodedrop', function (e) {
                var a = e.dropNode.attributes;
                layout.getEl().mask("处理中");
                Ext.lib.Ajax.request(
                    'POST',
                    config.actions.saveMenuItem.url,
                    {
                        success: function (x) {
                            layout.getEl().unmask();
                            var r = eval("(" + x.responseText + ")");
                            Ext.MessageBox.alert('提示', r.message);
                            e.target.reload();
                        },
                        failure: function () {
                            layout.getEl().unmask();
                            Ext.MessageBox.alert('提示', "保存中出现错误！");
                        }
                    },
                    //{success:hide,failure:hide},
                    'menuid=' + proot.privid + '&code=' + a.code + '&parentcode=' + a.parentcode
                    + '&isApp=' + a.isApp
                );
            });
            /**************
             * 表格右键菜单
             **************/
            var miViewNode = {
                id: 'newnode', text: '编辑菜单项', cls: 'new-mi', scope: this,
                handler: this.doTreeCfg
            }

            var miEdtNode = {
                id: 'edtnode', text: '修改菜单方案名称和备注', cls: 'edit-mi', scope: this,
                handler: this.doEdit
            }

            var miDelNode = {
                id: 'delnode', text: '删除', cls: 'remove-mi', scope: this,
                handler: this.doDelete
            }

            gridCtxMenu = new Ext.menu.Menu();
            gridCtxMenu.add(miViewNode, miEdtNode, miDelNode);

            function prepareCtx(g, i, e) {
                gridCtxMenu.showAt(e.getXY());
            }

            /**************
             * 表格事件处理
             **************/
            grid.on('rowcontextmenu', prepareCtx);
            //阻止默认的右键动作
            gridEl.swallowEvent('contextmenu', true);
            gridEl.addKeyListener(Ext.EventObject.DELETE, this.doDelNode);
            gridEl.on('keypress', function (e) {
                if (e.isNavKeyPress()) {
                    e.stopEvent();
                }
            });
            /**************
             * 表单
             **************/
            form = new Ext.form.Form({
                labelWidth: 50, labelAlign: 'top', buttonAlign: 'left',
                url: config.actions.saveNewMenu.url
            });

            Ext.each(config.fields, function (f) {
                form.add(f);
            });
            form.applyToFields(config.fieldOptions);

            sbmBtn = form.addButton('保存', function () {
                if (form.isValid()) {
                    form.submit({
                        failure: function () {
                            Ext.MessageBox.alert('提示', "操作执行失败");
                        },
                        success: function () {
                            Ext.MessageBox.alert('提示', "操作执行成功");
                            this.initRegion("center", "helpPanel");
                            gridds.reload();
                        },
                        scope: PrivConfiger,
                        waitMsg: '保存中...'
                    });
                } else {
                    Ext.MessageBox.alert('提示', "表单数据填写不正确，请检查。");
                }
            });

            form.render('formPanel-body');

            formData = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url: config.actions.getMenu.url}),
                reader: new Ext.data.JsonReader({}, [])
            });
            formData.on('load', function () {
                form.setValues(formData.reader.jsonData);
            });

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
            var _region = layout.getRegion('center');
            var _panel = _region.getPanel(p);
            this.initRegion('center', 'helpPanel');

            _region.showPanel(_panel);
            if (title != null) _panel.setTitle(title);
            _panel.fireEvent('activate');
        },
        collapseAll: function () {
            treeCtxMenu.hide();
            setTimeout(function () {
                treeroot.eachChild(function (n) {
                    n.collapse(false, false);
                });
            }, 10);
        },

        expandAll: function () {
            treeCtxMenu.hide();
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
                node = node.attributes.leaf != null ? node.parentNode : node;
                ptreesm.select(node);
                node.reload();
            }
        },
        getSelNode: function (a) {
            if (a)
                return eval("(treesm.selNode.attributes." + a + ")");
            else
                return selNode;
        },
        doAction: function (a) {
            this.openPanel('formPanel', a.title);
            form.url = a.url;
            sbmBtn.setText(a.btnText);
            try {
                a.init();
            } catch (e) {
            }
            ;
        },
        doNewMenu: function () {
            form.reset();
            this.doAction(config.actions.saveNewMenu);
        },
        doTreeCfg: function () {
            this.openPanel('treePanel');
            proot.privid = selRowRecord.id;
            //eoms.log(selRowRecord);
            proot.setText(selRowRecord.data.name);
            proot.reload();

        },
        doEdit: function () {
            this.openPanel('formPanel', '修改菜单方案');
            form.url = config.actions.editMenu.url;
            sbmBtn.setText(config.actions.editMenu.btnText);
            try {
                config.actions.editMenu.init();
            } catch (e) {
            }
            ;
            formData.load({params: {id: selRowRecord.id}});
        },
        doDelete: function () {
            //弹出一个确认框
            Ext.MessageBox.confirm('确认:', '您确定删除这个项目吗',
                function (btn) {
                    if (btn == "yes") {
                        Ext.lib.Ajax.request(
                            "post", config.actions.deleteMenu.url,
                            {
                                scope: PrivConfiger,
                                success: function () {
                                    gridds.reload();
                                    this.initRegion('center', 'helpPanel');
                                    Ext.MessageBox.alert('提示', "您成功删除了一个菜单。");
                                },
                                failure: function () {
                                    Ext.MessageBox.alert('提示', "删除失败");
                                }
                            },
                            "id=" + selRowRecord.id);
                    }
                }
            );
        },
        doDeleteTreeItem: function () {
            //弹出一个确认框
            Ext.MessageBox.confirm('确认:', '您确定删除此菜单项及其所有子菜单项吗',
                function (btn) {
                    if (btn == "yes") {
                        Ext.lib.Ajax.request(
                            "post", config.actions.deleteMenuItem.url,
                            {
                                scope: PrivConfiger,
                                success: function () {
                                    this.reloadNode(selNode.parentNode);
                                    Ext.MessageBox.alert('提示', "您成功删除了一个菜单项。");
                                },
                                failure: function () {
                                    Ext.MessageBox.alert('提示', "删除失败");
                                }
                            },
                            "id=" + selNode.id + "&privid=" + selNode.attributes.privid + "&code=" + selNode.attributes.code);
                    }
                }
            );
        },
        setField: function (fid, aid) {
            form.findField(fid).setValue(eval("(selNode.attributes." + aid + ")"));
        }

    } //return 底部
}(); //PrivConfiger 底部
Ext.onReady(PrivConfiger.init, PrivConfiger);