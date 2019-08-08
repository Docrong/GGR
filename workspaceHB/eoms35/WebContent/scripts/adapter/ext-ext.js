Ext.BLANK_IMAGE_URL = eoms.appPath + '/images/s.gif';
Ext.lib.Ajax.defaultPostHeader += '; charset=utf-8';

/**
 * 修正表单项的actionEl,使隐藏时label也隐藏
 */
Ext.form.Field.prototype.getActionEl = function () {
    return this.el.findParent("div.x-form-item", 5, true);
}
Ext.form.TriggerField.prototype.onHide = function () {
    this.getActionEl().setDisplayed(false);
    this.el.dom.disabled = true;
    if (this.hiddenField) this.hiddenField.disabled = true;
}
Ext.form.TriggerField.prototype.onShow = function () {
    this.getActionEl().setDisplayed(true);
    this.el.dom.disabled = false;
    if (this.hiddenField) this.hiddenField.disabled = false;
}
Ext.form.Field.prototype.onHide = function () {
    this.getActionEl().setDisplayed(false);
    this.el.dom.disabled = true;
}
Ext.form.Field.prototype.onShow = function () {
    this.getActionEl().setDisplayed(true);
    this.el.dom.disabled = false;
}

/**
 * Ext.form.HiddenField
 */
Ext.form.HiddenField = function (config) {
    config.type = 'hidden';
    config.inputType = 'hidden';
    Ext.form.HiddenField.superclass.constructor.call(this, config);
};
Ext.extend(Ext.form.HiddenField, Ext.form.TextField, {
    // private
    onRender: function (ct, position) {
        if (this.el) {
            this.el = Ext.get(this.el);
            if (!this.target) {
                ct.dom.appendChild(this.el.dom);
            }
        } else {
            var cfg = {
                tag: "input",
                type: "hidden"
            };
            if (!cfg.name) {
                cfg.name = this.name || this.id;
            }
            cfg.id = this.id;
            this.el = ct.createChild(cfg, position);
        }
        this.el.dom.readOnly = true;
        this.el.dom.parentNode.parentNode.style.display = 'none';
        this.initValue();
    }
});

/**
 * Ext.form.SimpleSelect 用于平台模块下拉框的简易写法
 */
Ext.form.SimpleSelect = function (config) {
    config.store = new Ext.data.SimpleStore({
        fields: ['text', 'value'],
        data: config.values
    });
    config.displayField = 'text';
    config.valueField = 'value';
    config.editable = false;
    config.mode = 'local';
    config.triggerAction = 'all';
    config.emptyText = '请选择';
    config.selectOnFocus = true;
    Ext.form.SimpleSelect.superclass.constructor.call(this, config);
};
Ext.extend(Ext.form.SimpleSelect, Ext.form.ComboBox, {});

/**
 * Ext.LayoutRegion.createToolButton 在region上创建一个按钮，可显示图标和文字
 * @param {Object} cfg 设置选项
 * @cfg {String} cls className
 * @cfg {Function} fn 点击时执行的函数
 * @cfg {Obj} scope 函数作用域
 * @cfg {String} text 显示的文本
 * @cfg {String} tips 鼠标说明文字
 */
Ext.LayoutRegion.prototype.createToolButton = function (cfg) {
    var _btn = this.createTool(this.tools.dom, cfg.cls);
    var icon = _btn.down('div.x-layout-tools-button-inner');
    icon.addClass('x-layout-panel-hd-tb');
    icon.dom.innerHTML = cfg.text || '';
    if (cfg.tips) icon.dom['qtip'] = cfg.tips;
    _btn.enableDisplayMode();
    _btn.on('click', cfg.fn, cfg.scope, true);
    return _btn;
};
/**
 * Ext.LayoutRegion.addTreePanel 在region上增加一个TreePanel
 * @param {Object} cfg TreePanel config
 * @cfg id
 * @cfg text
 * @cfg dataUrl
 * @cfg rootId
 * @return {Ext.TreePanel}
 */
Ext.LayoutRegion.prototype.addTreePanel = function (cfg) {
    var id = cfg.id || Ext.id();
    var panel = this.add(new Ext.ContentPanel(id, {
        title: cfg.text,
        fitToFrame: true,
        autoScroll: true,
        autoCreate: true
    }));

    var treeLoader = new Ext.tree.TreeLoader({
        dataUrl: /^\//.test(cfg.dataUrl) ? eoms.appPath + cfg.dataUrl : cfg.dataUrl,
        baseParams: cfg.params || {},
        baseAttrs: cfg.attrs || {}
    });
    treeLoader.on('beforeload', function (treeLoader, node, callback) {
        this.baseParams['nodeType'] = node.attributes.nodeType || '';
        if (node.parentNode) {
            this.baseParams['parentNode'] = node.parentNode.id;
            this.baseParams['parentNodeType'] = node.parentNode.attributes.nodeType || '';
        }
        //工单派发树中: 地域-专业-子角色
        if (cfg.type == 'rolePanel' && node.attributes.nodeType == 'dict') this.baseParams['areaId'] = node.parentNode.id;
    });

    var tree = new Ext.tree.TreePanel(id, {
        animate: true,
        enableDD: false,
        containerScroll: true,
        rootVisible: true,
        loader: treeLoader
    });

    var root = new Ext.tree.AsyncTreeNode({
        id: cfg.rootId || '-1',
        text: cfg.text,
        nodeType: 'root',
        expanded: false
    });
    tree.setRootNode(root);
    tree.render();
    if (cfg.hidden) {
        this.hidePanel(panel);
    }
    panel.tree = tree;
    return panel;
};

/**
 * Ext.menu.BaseItem 扩展
 * 根据 typeMapping 和传入的type判断是否属于该type的菜单项
 * 用于菜单项的隐藏和显示
 * @param {String} type 菜单项类型
 */
Ext.menu.BaseItem.prototype.matchType = function (type) {
    if (!this.typeMapping) return true;
    if (type instanceof Array) {
        return this.typeMapping.hasSubStrInArr(type, ',');
    } else {
        return this.typeMapping.hasSubString(type, ',');
    }
}


/**
 * + Ext.tree.TreePanel.resetRoot
 */
Ext.tree.TreePanel.prototype.resetRoot = function (url) {
    if (url == null || url == "")
        return;
    this.loader.dataUrl = url;
    this.root.reload();
};

/**
 * + Ext.tree.TreePanel.setParam
 */
Ext.tree.TreePanel.prototype.setParam = function (pname, pvalue) {
    eval("this.loader.baseParams." + pname + " = pvalue");
    this.root.reload();
};
/**
 * 添加树图节点的getCustomPath方法
 */
Ext.data.Node.prototype.getCustomPath = function (attr) {
    attr = attr || "id";
    var p = this.parentNode;
    var b = [this.attributes[attr]];
    while (p && !p.isRoot) {
        b.unshift(p.attributes[attr]);
        p = p.parentNode;
    }
    return b.join('-');
}
Ext.tree.TreeNode.prototype.render = function (bulkRender) {
    this.ui.render(bulkRender);
    if (!this.isRoot) {
        this.ui.ecNode.id = 'ecNode-' + this.attributes.id;
        this.ui.textNode.id = 'textNode-' + this.attributes.id;
    }
    if (!this.rendered) {
        this.rendered = true;
        if (this.expanded) {
            this.expanded = false;
            this.expand(false, false);
        }
    }
};
Ext.tree.TreeNodeUI.prototype.toggleCheck = function (value) {
    var cb = this.checkbox;
    if (cb) {
        cb.checked = (value === undefined ? !cb.checked : value);
        this.node.attributes.checked = cb.checked;
        this.fireEvent('checkchange', this.node, cb.checked);
    }
};
/**
 * 去掉树节点双击时执行toggle
 */
Ext.tree.TreeNodeUI.prototype.onDblClick = function (e) {
    e.preventDefault();
    if (this.disabled) return;
    if (this.checkbox) this.toggleCheck();
    this.fireEvent("dblclick", this.node, e);
};

/**
 * xbox 弹出一个选择窗口
 *
 * @e.g 例子
 *
 * <pre>
 * Ext.onReady(function() {
 * 	tree = new xbox({
 * 		btnId : 'btnDlg2',
 * 		treeDataUrl : treeAction
 * 	});
 * 	box = new xbox({
 * 		btnId : 'btnDlg',
 * 		treeDataUrl : treeAction,
 * 		treeChkMode : '',
 * 		showChkFld : '',
 * 		saveChkFld : ''
 * 	});
 * });
 * </pre>
 *
 * @cfg btnId 按钮ID
 * @cfg treeDataUrl 树图action地址
 * @cfg treeRootId 树图根节点id
 * @cfg treeRootText 树图根节点显示文字
 * @cfg treeChkMode 单选('single')或者多选('')
 * @cfg treeChkType 可供选择的节点类型(user,dept,...)
 * @cfg showChkFldId 选择后显示域的ID
 * @cfg saveChkFldId 选择后保存ID的隐藏域ID
 * @cfg returnJSON 为true返回JSON格式数据，默认返回逗号隔开的数据
 * @cfg callback 选择后执行的函数
 * @cfg dlgTitle 窗口文字
 * @cfg mode =clearPath 根据父子节点清除同路径其他选中节点; =clearPathById 根据id清除同路径其他选中节点
 **/
function xbox(cfg) {
    Ext.apply(this, cfg);
    this.defaultConfig = cfg;

    this.body = Ext.DomHelper.append(document.body, {id: Ext.id(), cls: 'x-layout-inactive-content'}, true);
    this.listEl = this.body.createChild({id: Ext.id(), cls: 'x-layout-inactive-content'});

    if (this.viewer === true) {
        this.viewer = new Ext.JsonView("xbox_" + this.id + "_view",
            '<div class="viewlistitem-{nodeType}">{text}; </div>',
            {emptyText: '<div>没有选择项目</div>'}
        );
        this.viewer.refresh();
    }
    var listcm = new Ext.grid.ColumnModel([
        {
            header: "名称",
            dataIndex: 'name'
        }

    ]);

    var gridData = new Ext.data.JsonStore({
        id: 'id',
        proxy: new Ext.data.MemoryProxy(),
        reader: new Ext.data.JsonReader({}, [{name: 'name'}, {name: 'id'}]),
        fields: ['id', 'name', 'text', 'nodeType', 'mobile']
    });
    var grid = new Ext.grid.Grid(this.listEl, {
        ds: gridData,
        cm: listcm,
        enableColLock: true,
        autoWidth: true
    });

    // 增加双击名称列时执行删除的事件

    grid.on('celldblclick', function (grid, rowIndex, columnIndex, e) {
        if (grid.getColumnModel().getDataIndex(columnIndex) == 'name') {
            var record = gridData.getAt(rowIndex);
            var checkedNodes = this.getChecked();
            gridData.remove(gridData.getAt(rowIndex));
            for (var i = 0; i < checkedNodes.length; i++) {
                if (checkedNodes[i].id == record.get('id')) {
                    checkedNodes[i].getUI().toggleCheck(false);
                }
            }
        }
    }, this);

    this.grid = grid;
    this.gridData = gridData;

    if (this.viewer && this.viewer.jsonData) {
        this.data = this.viewer.jsonData;
    }

    if (this.data && this.data.length > 0) {
        gridData.loadData(this.data);
        this.update();
    }

    grid.render();

    this.showBtn = Ext.get(this.btnId);
    this.showBtn.on('click', this.onBtnClick, this);

    this.trees = [];
    this.defaultConfig.defaultTree = true;
    this.defaultPanel = this.newTreePanel(this.defaultConfig);
    if (this.returnJSON) {
        Ext.getDom(this.saveChkFldId).value = Ext.util.JSON.encode(this.viewer ? (this.viewer.jsonData ? this.viewer.jsonData : []) : []);
    }

    if (!this.dialog) {
        // root.expand(false, /*no anim*/ false);
        this.dialog = new Ext.LayoutDialog(this.body, {
            title: this.dlgTitle || '选择',
            modal: true,
            autoTabs: true,
            width: 450,
            height: 400,
            shadow: false,
            minWidth: 300,
            minHeight: 300,
            proxyDrag: false,
            west: {
                split: true,
                initialSize: 250,
                autoScroll: true,
                titlebar: false,
                tabPosition: 'top',
                alwaysShowTabs: true
            },
            center: {
                titlebar: true,
                autoScroll: true,
                tabPosition: 'top',
                alwaysShowTabs: false
            }
        }, this);
        this.dialog.addKeyListener(27, this.recover, this);
        this.dialog.addButton('确定', this.output, this);
        this.dialog.addButton('关闭', this.recover, this);

        var layout = this.dialog.getLayout();
        layout.beginUpdate();
        layout.add('west', this.defaultPanel, this);
        layout.add('center', new Ext.GridPanel(this.grid, {
            title: '选择结果(双击名称删除)'
        }), this);
        this.onLayout(this, layout);
        layout.getRegion('west').showPanel(this.defaultPanel);
        layout.endUpdate();
    }

    if (this.mode) eval('this.' + this.mode + '()');
};
xbox.prototype = {
    //点击按钮时执行事件
    onBtnClick: function () {
        this.show(this.showBtn.dom, this.defaultConfig.showChkFldId, this.defaultConfig.saveChkFldId);
    },
    onLayout: function () {
    },
    onBeforeCheck: function (node, checked) {
        return true
    },

    //点击树图checkbox时事件
    loadGridData: function (node, append) {
        this.gridData.loadData([{
            'id': node.id,
            'name': node.attributes.name || node.text,
            'text': (this.mode == 'clearPath' || this.mode == 'clearPathById') ? node.getCustomPath('text') : (node.text || node.attributes.name),
            'nodeType': node.attributes.nodeType,
            'mobile': node.attributes.mobile
        }], append);
    },
    onCheck: function (node, checked) {
        if (!this.onBeforeCheck(node, checked)) {
            node.getUI().toggleCheck(false);
            return;
        }
        // 单选
        if (this.treeChkMode == 'single' || this.single) { //old property
            if (this.checkedNode != null) {
                var c = this.checkedNode;
                c.attributes.checked = false;
                c.getUI().checkbox.checked = false;
                if (c.id == node.id) {// 删除
                    this.gridData.removeAll();
                    this.checkedNode = null;
                } else {
                    this.checkedNode = node;
                    this.loadGridData(node, false);
                }
                return;
            }
            this.checkedNode = node;
            this.loadGridData(node, false);
            return;
        }
        // 多选
        if (checked) {
            if (this.gridData.indexOfId(node.id) == -1) {
                this.loadGridData(node, true);
            }
        } else {
            if (this.gridData.indexOfId(node.id) >= 0) {
                var record = this.gridData.getById(node.id);
                if (typeof record == "object")
                    this.gridData.remove(record);
            }
        }
        this.onAfterCheck(node, checked);
    },
    onAfterCheck: function (node, checked) {
    },
    //树图展开时事件
    onTreeExpand: function (node) {
        Ext.each(this.gridData.getJSONData(), function (item) {
            var cknode = node.findChild('id', item.id);
            if (cknode) {
                if (cknode.attributes.nodeType && cknode.attributes.nodeType == item.nodeType) {
                    cknode.getUI().toggleCheck(true);
                }
            }

        });
    },
    //新建一个树图面板
    newTreePanel: function (cfg) {
        var checktype = cfg.checktype || cfg.treeChkType; //old property
        var treeEl = this.body.createChild({id: Ext.id(), cls: 'x-layout-inactive-content'});
        var treeLoader = new Ext.tree.TreeLoader({
            dataUrl: cfg.treeDataUrl,
            baseParams: {
                checktype: checktype || ''
            }
        });
        treeLoader.on('beforeload', function (treeLoader, node, callback) {
            treeLoader.baseParams['nodeType'] = node.attributes.nodeType || '';
            if (node.parentNode) {
                treeLoader.baseParams['parentNode'] = node.parentNode.id;
                treeLoader.baseParams['parentNodeType'] = node.parentNode.attributes.nodeType || '';
            }
            treeLoader.baseAttrs = this.baseAttrs;
        }, this);
        var tree = new Ext.tree.TreePanel(treeEl, {
            animate: true,
            enableDD: false,
            containerScroll: true,
            loader: treeLoader
        });

        var root = new Ext.tree.AsyncTreeNode({
            id: cfg.treeRootId,
            text: cfg.treeRootText,
            nodeType: 'root'
        });
        tree.setRootNode(root);
        tree.render();
        tree.on('checkchange', this.onCheck, this);
        tree.on('expand', this.onTreeExpand, this);
        tree.checktype = checktype;

        //是否显示checkbox,以nodeType判断，多个nodeType用,隔开
        tree.on('beforeappend', function (tree, parentNode, node) {
            var _checktype = tree.checktype;
            if (_checktype == null) {
                node.attributes.checked = false;
            } else if (node.attributes.nodeType
                && _checktype.hasSubString(node.attributes.nodeType, ",")) {
                node.attributes.checked = false;
            }
        }, this);

        if (cfg.defaultTree) {
            this.defaultTree = tree;
        }
        this.trees.push(tree);
        var panel = new Ext.ContentPanel(treeEl, {
            title: cfg.treeRootText
        });
        return panel;
    },
    getChecked: function () {
        var c = [];
        Ext.each(this.trees, function (t) {
            c = c.concat(t.getChecked());
        }, this);
        return c;
    },
    /**
     * 显示窗口，可在另外的按钮上绑定此事件，并设置显示和保存数据的容器id
     * 如：onclick="javascript:userTree.show(this,'show','save')"
     * @param dom btn 按钮节点
     * @param string showElId 显示选择数据的容器id
     * @param stinrg saveElId 保存选择数据的容器id
     */
    show: function (btn, showElId, saveElId) {
        if (showElId) this.showChkFldId = showElId;
        if (saveElId) this.saveChkFldId = saveElId;

        this.dialog.show();
    },
    //点击关闭时，数据恢复初始状态
    recover: function () {
        this.dialog.hide();
        if (this.data) {
            this.gridData.loadData(this.data);
        }
        Ext.each(this.trees, function (item) {
            item.root.reload();
        }, this);
    },
    //点击确定时将数据输出
    output: function () {
        this.update();
        this.dialog.hide();
        if (typeof this.callback == 'function') {
            var list = [], jsonData = this.gridData.getJSONData();
            Ext.each(jsonData, function (item) {
                list.push(item.id);
            }, this);
            this.callback(jsonData, list.toString());
        }
        this.data = this.gridData.getJSONData();
    },
    update: function () {
        var jsonData = this.gridData.getJSONData();
        var chosenTextList = [], chosenDataList = [];

        Ext.each(jsonData, function (item) {
            chosenTextList.push(item.text || item.name);
            chosenDataList.push(item.id);
        }, this);

        if (this.viewer) {
            this.viewer.jsonData = jsonData;
            this.viewer.refresh();
        }

        if (this.returnJSON) chosenDataList = this.gridData.getJSON();
        this.onOutput(chosenTextList, chosenDataList, jsonData);
    },
    //输出事件
    onOutput: function (textList, dataList, jsonData) {
        if (this.showChkFldId) {
            $(this.showChkFldId).update(textList.toString());
            //激活文本域的验证
            try {
                $(this.showChkFldId).focus();
                $(this.showChkFldId).blur();
            } catch (e) {
            }
        }
        if (this.saveChkFldId) {
            $(this.saveChkFldId).update(dataList.toString());
        }
    },
    //重定treeRootUrl
    resetRoot: function (url) {
        if (url == null || url == "") return;
        this.defaultTree.loader.dataUrl = url;
        this.defaultTree.root.reload();
    },
    reset: function () {
        this.gridData.removeAll();
        if (this.viewer) {
            this.viewer.jsonData = [];
            this.viewer.refresh();
        }
        this.onOutput("", "", null);
        Ext.each(this.trees, function (tree) {
            var r = tree.root;
            if (r.isExpanded()) {
                r.reload();
            }
        });
    },
    /**
     * clearPath模式，根据父子节点清除同路径上的其他节点。
     */
    clearPath: function () {
        this.onAfterCheck = function (node, checked) {
            if (checked === false) return;

            function clearcheck(n) {
                if (!n.isRoot && n.id != node.id && n.attributes.checked) {
                    n.attributes.checked = false;
                    n.getUI().checkbox.checked = false;
                    var record = this.gridData.getById(n.id);
                    if (typeof record == "object") this.gridData.remove(record);
                }
            }

            node.bubble(clearcheck, this);
            node.cascade(clearcheck, this);
        };
    },
    /**
     * clearPathById模式，根据节点Id清除同路径上的其他节点。
     */
    clearPathById: function () {
        this.onAfterCheck = function (node, checked) {
            if (checked === false) return;
            this.gridData.snapshot = this.gridData.data;
            this.gridData.filterBy(function (d) {
                if (d.id != node.id && (d.id.indexOf(node.id) == 0 || node.id.indexOf(d.id) == 0)) {
                    var n = this.defaultTree.getNodeById(d.id);
                    if (n) {
                        n.attributes.checked = false;
                        n.getUI().checkbox.checked = false;
                    }
                    return false;
                }
                return true;
            }, this);
        };
    }
};

/**
 * 用于选择派发对象的弹出窗口
 */
var Chooser = function (config) {
    var xt = Ext.tree;
    var CP = Ext.ContentPanel;
    this.showFilter = false; //是否显示条件过滤(主面板)
    this.returnJSON = false; //是否返回JSON数据格式
    this.showLeader = false; //是否设置组长
    this.chosen = new Ext.util.MixedCollection();	//已选择项,保存已选择节点的属性,即node.attributes
    this.defaultChildType = ''; //默认不限节点类型
    Ext.apply(this, config, config.config);

    this.panels = this.panels || [{text: '部门与人员', dataUrl: '/xtree.do?method=userFromDept'}];
    this.body = Ext.get(this.id);
    this.body.down("input[type='button']").on('click', this.show, this);
    this.totalJSON = this.body.createChild({tag: 'input', type: 'hidden', name: this.id + 'TotalJSON'});

    // 主选择框
    var dlg = new Ext.LayoutDialog(this.id + '-chooser', {
        title: this.title || '选择派发对象',
        width: 750,
        height: 450,
        modal: false,
        shadow: false,
        collapsible: false,
        proxyDrag: false,
        resizable: false
    });

    // 创建中间选择按钮容器
    var mainBtnEl = dlg.body.createChild({
        tag: 'table',
        style: 'width:100%;height:100%',
        cn: {
            tag: 'tr',
            cn: {
                tag: 'td',
                align: 'center',
                id: this.id + '-chooser-centercell'
            }
        }
    });

    // 设置布局	
    dlg.layout = Ext.BorderLayout.create({
        west: {
            initialSize: 340,
            autoScroll: true,
            titlebar: true
        },
        center: {
            autoScroll: false,
            titlebar: false,
            panels: [new CP(mainBtnEl)]
        },
        east: {
            autoScroll: true,
            titlebar: true,
            tabPosition: 'top',
            initialSize: 340,
            panels: [
                new CP(this.id + 'chsTree', {
                    autoCreate: true,
                    title: '已选择项目:'
                })
            ]
        }

    }, dlg.body.dom);

    dlg.addKeyListener(27, this.hide, this);
    dlg.addButton('确定', this.output, this);
    dlg.setDefaultButton(dlg.addButton('关闭', this.hide, this));
    this.dlg = dlg;
    this.layout = dlg.layout;
    this.west = this.layout.getRegion("west");
    this.center = this.layout.getRegion("center");
    this.east = this.layout.getRegion("east");

    // 修正选择按钮区域的样式
    this.center.el.addClass("chooser-bg");

    if (this.type == "role") {
        this.defaultChildType = 'subrole';
        this.panels.unshift({
            type: 'rolePanel',
            text: '可选子角色',
            dataUrl: '/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea',
            params: {roleId: this.roleId, flowId: this.flowId, ifSpecial: this.ifSpecial}
        });
        this.panels.push({
            type: 'search',
            text: '搜索子角色',
            dataUrl: '/role/tawSystemRoles.do?method=xSearchSubRoleNodes',
            params: {roleId: this.roleId},
            hidden: true
        });
    }
    // 通过panels创建备选面板
    Ext.each(this.panels, function (p) {
        var newp = this.west.addTreePanel(p);
        newp.tree.on('dblclick', this.doChoose, this);
        if (p.type == "search") {
            this.searchPanel = newp;
        }
        if (p.type == "rolePanel") {
            this.rolePanel = newp;
        }
    }, this);
    this.west.showPanel(0);

    // 创建右侧已选择树图
    this.category.tree = new xt.TreePanel(this.id + 'chsTree', {
        animate: true,
        enableDD: false,
        containerScroll: true,
        lines: false,
        rootVisible: false
    });
    this.category.root = new xt.TreeNode({nodeType: 'root', expanded: true});
    this.category.tree.setRootNode(this.category.root);
    this.category.tree.render();

    this.chosenCtxMenu = new Ext.menu.Menu();
    this.category.tree.on('contextmenu', this.onChosenCtx, this);
    this.category.tree.el.swallowEvent('contextmenu', true);
    this.category.tree.getSelectionModel().on('selectionchange', function (sm, node) {
        this.clearSelction('west');
        if (node) node.select();
    }, this);
    this.category.tree.on('dblclick', this.remove, this);

    this.categoryTreeSM = this.category.tree.getSelectionModel();

    // 根据category创建已选择项目分组的树图节点,创建选择按钮
    this.categoryNodes = {};
    var showCate = function (c) {
        if (c.hidden === false) return;
        c.btn.show();
        c.node.getUI().show();
        c.hidden = false;
        c['hiddenEl'].dom.disabled = false;
    };
    var hideCate = function (c) {
        if (c.hidden === true || this.category.root.childNodes.length <= 1) return;
        c.btn.hide();
        c.node.getUI().hide();
        c.hidden = true;
        c['hiddenEl'].dom.disabled = true;
    };
    var attrCate = function (c, attr, value) {
        if (arguments.length == 3) {
            c.node.attributes[attr] = value;
        } else {
            return c.node.attributes[attr];
        }
    };
    Ext.each(this.category, function (item, index) {
        var node = this.createCNode(item);

        var cell = Ext.get(this.id + '-chooser-centercell');
        var btn = cell.createChild({
            tag: 'input',
            type: 'button',
            value: item.text,
            style: 'margin:20 0;display:block',
            cls: 'button'
        });
        btn.on('click', function () {
            this.doChoose(item.id)
        }, this);

        //chooser所属的页面form
        this.pageForm = btn.dom.form;

        item.node = node;
        item.btn = btn;
        item.index = index;
        item.show = showCate.createDelegate(this, [item]);
        item.hide = hideCate.createDelegate(this, [item]);
        item.attr = attrCate.createDelegate(this, [item], 0);
        item.hidden = false;

        //根据category创建隐藏域
        var alt = item.allowBlank == false ? "allowBlank:" + item.allowBlank + ",vtext:'" + item.vtext + "'" : "";
        item['hiddenEl'] = this.body.createChild({tag: 'input', type: 'hidden', alt: alt, name: item.id});
        item['hiddenEl_nodeType'] = this.body.createChild({tag: 'input', type: 'hidden', name: item.id + 'Type'});
        item['hiddenEl_leaderId'] = this.body.createChild({tag: 'input', type: 'hidden', name: item.id + 'Leader'});
        item['hiddenEl_JSON'] = this.body.createChild({tag: 'input', type: 'hidden', name: item.id + 'JSON'});
    }, this);

    // 已选择项目确定后在父页面上的显示
    this.chosenView = new Ext.JsonView(
        this.id + '-chooser-show',
        '<div>{categoryName}:<div class="viewlistitem-{nodeType}" style="display:inline">{text} {info}</div></div>',
        {
            singleSelect: true,
            selectedClass: "",
            emptyText: '<div>未选择项目</div>',
            scope: this
        });
    this.chosenView.jsonData = [];
    this.chosenView.refresh();

    if (this.type == "role") {
        this.showFilter = true;
        if (this.showFilter) this.initFilterDlg(); //过滤条件窗口
        if (this.showLeader) this.initSetLeader(); //设置组长
    }

};
Chooser.prototype = {
    show: function () {
        this.dlg.show();
    },
    hide: function () {
        this.dlg.hide();
        if (this.showFilter) {
            this.filterDlg.hide();
        }
    },
    /**
     * 隐藏按钮
     */
    hideBtn: function () {
        this.body.down("input[type='button']").setDisplayed(false);
    },
    disable: function () {
        eoms.form.disableArea(this.id, true);
    },
    enable: function () {
        eoms.form.enableArea(this.id, true);
    },
    clearSelction: function (region) {
        var re = this.layout.getRegion(region)
        var ap = re.getActivePanel();
        ap.tree.getSelectionModel().clearSelections();
    },
    /**
     * 设置面板的树图url地址，并刷新
     * @param {String} url 树图action地址
     * @param {String} rootId 树图根节点id,默认为-1
     * @param {Number} index 面板序数，缺省为第一个面板
     */
    setDataUrl: function (url, rootId, index) {
        if (!index) index = 0;
        if (!rootId) rootId = -1;
        var tree = this.west.getPanel(index).tree;
        var treeLoader = tree.getLoader();
        var root = tree.getRootNode();
        if ((eoms.appPath + url) == treeLoader.dataUrl && root.id == rootId) {
            return;
        }
        treeLoader.dataUrl = eoms.appPath + url;
        this.reset();
        tree.getRootNode().id = rootId;
        tree.getRootNode().reload();
    },
    // 已选择项目的右键菜单
    onChosenCtx: function (node, e) {
        node.select();
        //设置组长
        if (this.showLeader && node.attributes.nodeType == 'user' && node.parentNode.attributes.nodeType == 'subrole') {
            this.chosenCtxMenu.showAt(e.getXY());
        }
    },
    getKey: function (o) {
        return o.nodeType + ':' + o.id;
    },
    createCNode: function (c) {
        var nodeText, childType = c.childType || this.defaultChildType, childTypeText = '';

        if (childType == '') { // 不限节点类型
            nodeText = c.text;
        } else {
            var childTypeArr = [];
            Ext.each(childType.split(","), function (item) {
                childTypeArr.push(Local.nodeType[item]);
            })
            childTypeText = childTypeArr.join("或");
            nodeText = c.text + ' (只能选择' + childTypeText + ')';
        }

        var node = new Ext.tree.TreeNode({
            categoryId: c.id,
            categoryName: c.text,
            text: nodeText,
            iconCls: 'folder',
            cls: 'cmp',
            expanded: true,
            nodeType: 'category',
            childType: childType,
            childTypeText: childTypeText,
            limit: c.limit || 1
        });

        this.category.root.appendChild(node);
        this.categoryNodes[c.id] = node;
        return node;
    },
    // 执行选择动作
    doChoose: function (categoryId) {
        var r = this.layout.getRegion("west").getActivePanel();
        var selectNode = r.tree.getSelectionModel().selNode;
        var node = selectNode || this.categoryTreeSM.selNode;
        if (node == null) return;
        //删除
        if (node.getOwnerTree() == this.category.tree) {
            this.remove(node);
            return;
        }
        //添加
        var nodeInfo = Ext.apply({}, node.attributes);
        if (nodeInfo.nodeType == 'root') {
            return;
        }
        if (nodeInfo.nodeType == 'subrole' && nodeInfo.user && nodeInfo.user.length == 0) {
            alert('该角色下没有人员，请选择其他角色。');
            return;
        }
        if (this.chosen.containsKey(this.getKey(nodeInfo))) {
            alert('该项目已被选择了。');
            return;
        }

        var c = this.categoryNodes[categoryId] || this.category.root.firstChild;
        var cAttr = c.attributes;

        if (cAttr.limit !== 'none' && cAttr.limit !== -1 && c.childNodes.length >= parseInt(cAttr.limit)) {
            alert(cAttr.categoryName + "中最多可以选择" + cAttr.limit + "个");
            return;
        }

        if (cAttr.childType != '' && !cAttr.childType.hasSubString(nodeInfo.nodeType, ",")) {
            alert(cAttr.categoryName + "中只能选择" + cAttr.childTypeText);
            return;
        }

        nodeInfo['categoryId'] = cAttr.categoryId;
        nodeInfo['categoryName'] = cAttr.categoryName;

        this.add(nodeInfo, this.category.root.firstChild, true);
        this.refresh();
    },
    /**
     * 添加已选节点数据
     * @param {Object/Array} nodeInfos 一个节点的信息或多个节点的信息数组
     * @param {Ext TreeNode} defaultCtgNode 缺省的categoryNode
     * @param {Boolean} isAsync 是否创建动态节点
     */
    add: function (nodeInfos, defaultCtgNode, isAsync) {
        Ext.each([].concat(nodeInfos), function (nodeInfo) {
            if (!this.categoryNodes[nodeInfo.categoryId] && !defaultCtgNode) {
                return;
            }
            var cnode = this.categoryNodes[nodeInfo.categoryId] || defaultCtgNode;
            nodeInfo.categoryName = cnode.attributes.categoryName;
            nodeInfo.iconCls = nodeInfo.iconCls || nodeInfo.nodeType;
            var newNode = (isAsync && !nodeInfo.leaf) ? new Ext.tree.AsyncTreeNode(nodeInfo) : new Ext.tree.TreeNode(nodeInfo);
            if (nodeInfo.user) {
                Ext.each(nodeInfo.user, function (child) {
                    newNode.appendChild(new Ext.tree.TreeNode(child));
                });
            }
            cnode.appendChild(newNode);
            cnode.expand();
            this.chosen.add(this.getKey(nodeInfo), nodeInfo);
        }, this);
    },
    remove: function (node) {
        if (node.parentNode.attributes.nodeType == 'category') {
            node.parentNode.removeChild(node);
            this.chosen.removeKey(this.getKey(node.attributes));
            this.refresh();
        }
    },
    refresh: function () {
        // this.chosenView.refresh();
        // this.chosenPanel.setTitle("已选择："+this.chosen.getCount()+"个角色
        // (双击删除,右键指定组长)");
    },
    /**
     * 将选中的结果更新到页面上，并把选中的值填入隐藏域
     */
    update: function () {
        this.chosenView.jsonData = [];
        this.chosen.each(function (nodeInfo) {
            //如果未指定组长，则设定leaderId为选中对象的id
            if (!nodeInfo.leaderId || nodeInfo.leaderId == "") {
                nodeInfo.leaderId = nodeInfo.id;
            }
            //如果有leaderName，则info为组长信息
            nodeInfo.info = nodeInfo.leaderName ? '(组长:' + nodeInfo.leaderName + ')' : '';

            this.chosenView.jsonData.push(nodeInfo);
        }, this);

        this.chosenView.refresh();

        function toStr(o, isShowLeader) {
            var str = '{'
                + 'id:\'' + o.id + '\''
                + ',nodeType:\'' + o.nodeType + '\''
                + ',categoryId:\'' + o.categoryId + '\''
            if (o.nodeType == 'subrole' && isShowLeader && o.leaderId) {
                str += ',leaderId:\'' + o.leaderId + '\'' + ',leaderName:\'' + o.leaderName + '\'';
            }
            str += '}';
            return str;
        }

        var totalJsonData = [];
        this.chosen.each(function (nodeInfo) {
            if (!nodeInfo.leaderId || nodeInfo.leaderId == "") {
                nodeInfo.leaderId = nodeInfo.id;
            }
            nodeInfo.leaderName = nodeInfo.leaderName ? '(组长:' + nodeInfo.leaderName + ')' : '';
            totalJsonData.push(toStr(nodeInfo, this.showLeader));
        }, this);

        Ext.each(this.category, function (c) {
            var arrId = [], arrNodeType = [], arrLeaderId = [], arrJSON = [];
            this.chosen.each(function (nodeInfo) {
                if (nodeInfo.categoryId == c.id) {
                    arrId.push(nodeInfo.id);
                    arrNodeType.push(nodeInfo.nodeType);
                    arrLeaderId.push(nodeInfo.leaderId);
                    if (this.returnJSON) {
                        arrJSON.push(toStr(nodeInfo, this.showLeader));
                    }
                }
            }, this);

            c['hiddenEl'].dom.value = arrId.toString();
            c['hiddenEl_nodeType'].dom.value = arrNodeType.toString();
            c['hiddenEl_leaderId'].dom.value = arrLeaderId.toString();
            if (this.returnJSON) {
                c['hiddenEl_JSON'].dom.value = "[" + arrJSON.toString() + "]";
            }
        }, this);
        this.totalJSON.dom.value = "[" + totalJsonData.toString() + "]";
    },
    /**
     * 向父页面输出选择结果
     * @private
     */
    output: function () {
        var valid = true;
        this.chosen.each(function (i) {
            //显示组长 且为虚拟组 则 必选一个组长
//			if(this.showLeader && this.isVirtual && i.nodeType=="subrole" && (!i.leaderId || i.leaderId=="")){
//				 alert("未指定组长！\n请为 "+i.name+" 指定一个组长：\n在该组的人员上单击右键，选择‘指定组长’");
//				 valid = false;
//				 return;
//			}
        }, this);

        if (!valid) return;

        this.update();
        this.hide();
    },
    //初始化设置组长
    initSetLeader: function () {
        this.chosenCtxMenu.add({
            id: 'newnode',
            text: '指定组长',
            cls: 'new-mi',
            scope: this,
            handler: this.setLeader
        });

        //this.chosenView.leaderStore 在view中保存已设置的组长信息：
        //{
        //   '子角色1id':{'id':'组长id','name':'组长name'},
        //   '子角色2id':{'id':'组长id','name':'组长name'},
        //   ...
        //}
        this.chosenView.leaderStore = {};
        this.chosenView.prepareData = function (data) {
            switch (data.nodeType) {
                case 'subrole':
                    Ext.applyIf(data, this.leaderStore[data.id]);
                    break;
                default:
                    break;
            }

            return data;
        };
    },
    // 设置组长
    setLeader: function () {
        var userNode = this.categoryTreeSM.selNode;
        var subroleNode = userNode.parentNode;
        var subroleNodeId = subroleNode.attributes.id;
        var key = this.getKey(subroleNode.attributes);

        if (userNode.attributes.nodeType != 'user' || subroleNode.attributes.nodeType != 'subrole') return;

        this.chosenView.leaderStore[subroleNodeId] = {
            leaderId: userNode.attributes.id,
            leaderName: userNode.attributes.name
        };

        Ext.apply(this.chosen.get(key), this.chosenView.leaderStore[subroleNodeId]);

        //刷新人员列表组长状态
        subroleNode.eachChild(function (node) {
            node.ui.iconNode.className = "x-tree-node-icon user";
        });
        userNode.ui.iconNode.className = "x-tree-node-icon leader";
    },
    /**
     * type=role 模式下重新设置大角色roleId,刷新可选子角色
     * @param {Number} roleId
     */
    setRoleId: function (roleId) {
        if (this.type != 'role' || this.roleId == roleId) return;
        this.roleId = roleId;
        if (this.rolePanel) {
            this.rolePanel.tree.getLoader().baseParams.roleId = roleId;
            this.rolePanel.tree.root.reload();
        }
        if (this.searchPanel) {
            this.searchPanel.tree.getLoader().baseParams.roleId = roleId;
        }
        Ext.Ajax.request({
            method: 'post',
            url: eoms.appPath + "/role/tawSystemRoles.do?method=resetChooserRoleId",
            success: function (x) {
                var obj = eoms.JSONDecode(x.responseText);
                Ext.getDom(this.id + "-chooser-deptList").innerHTML = obj.filterHTML;
            },
            scope: this,
            params: "chooserId=" + this.id + "&roleId=" + this.roleId
        });
        this.reset();
    },
    reset: function () {
        if (this.searchPanel) {
            this.west.hidePanel(this.searchPanel);
        }
        this.west.showPanel(0);
        Ext.each(this.category.root.childNodes, function (cnode) {
            while (cnode.firstChild) {
                cnode.removeChild(cnode.firstChild);
            }
        });
        this.chosen.clear();
        this.chosenView.jsonData = [];
        this.chosenView.refresh();
        Ext.select("input[type=hidden]", false, this.id).each(function (f) {
            f.dom.value = "";
        });
        this.refresh();
    },
    // 初始化过滤器
    initFilterDlg: function () {
        var filterBtn = this.west.createToolButton({
            cls: 'x-layout-search',
            fn: this.showFilterDlg,
            scope: this,
            text: '搜索'
        });

        if (!this.filterDlg) {
            this.filterDlg = new Ext.BasicDialog(this.id + "-chooser-filter-dlg", {
                shadow: false,
                modal: false,
                autoTabs: true,
                resizable: true,
                collapsible: false,
                width: 400,
                height: 450
            });
            this.filterDlg.addKeyListener(27, this.filterDlg.hide,
                this.filterDlg);
            this.filterDlg.addButton('查找', this.doFilter, this);
            this.filterDlg.addButton('关闭', this.filterDlg.hide, this.filterDlg);
        }
    },
    // 显示过滤器窗口
    showFilterDlg: function () {
        this.filterDlg.show();
        this.filterDlg.toFront();
    },
    // 执行查询
    doFilter: function () {
        var p = {};
        Ext.select("input,select", false, this.id + "-chooser-filter-dlg").each(function (f) {
            if (f.dom.type == "checkbox" && f.dom.checked) {
                p[f.dom.name] = true;
            } else if (f.dom.tagName == "SELECT") {
                p[f.dom.name] = f.dom.value;
            }
        });
        this.west.showPanel(this.searchPanel);
        var st = this.searchPanel.tree;
        Ext.apply(st.getLoader().baseParams, p);
        st.root.reload();
        this.filterDlg.hide();
    }

};

/**
 * Ext.form.VTypes
 */
Ext.form.VTypes['numberVal'] = /^[0-9]*$/;
Ext.form.VTypes['numberMask'] = /^[0-9]*$/;
Ext.form.VTypes['numberText'] = '请输入数字';
Ext.form.VTypes['number'] = function (v) {
    return Ext.form.VTypes['numberVal'].test(v);
}

Ext.form.VTypes['lessThenText'] = '必须小于';
Ext.form.VTypes['lessThen'] = function (v, field) {
    if (!Ext.getDom(field.link)) {
        return;
    }
    var lv = Ext.getDom(field.link).value;
    var linkField = field.valider.fields.get(field.link);

    if (linkField) {
        Ext.form.VTypes['lessThenText'] = field.vtext;
        v < lv ? linkField.clearInvalid() : linkField.markInvalid(linkField.vtext);
    }

    return v < lv;
};
Ext.form.VTypes['moreThenText'] = '必须大于';
Ext.form.VTypes['moreThen'] = function (v, field) {
    if (!Ext.getDom(field.link)) {
        return;
    }
    var lv = Ext.getDom(field.link).value;
    var linkField = field.valider.fields.get(field.link);

    if (linkField) {
        Ext.form.VTypes['moreThenText'] = field.vtext;
        v > lv ? linkField.clearInvalid() : linkField.markInvalid(linkField.vtext);
    }

    return v > lv;
};
/**
 * 唯一性验证
 */
Ext.form.VTypes['uniqueText'] = '该名称已存在，请换一个';
Ext.form.VTypes['delText'] = '该名称已被管理员删除，请联系管理员';
Ext.form.VTypes['unique'] = function (v, field) {
    try {
        if (v == AppSimpleTree.savedFormData[field.name]) {
            return true;
        }
    } catch (e) {
    }
    ;

    Ext.lib.Ajax.request("post", eoms.appPath
        + "/sysuser/tawSystemUsers.do?method=checkUnique", {
        success: function (x) {
            if (x.responseText == "true") {
                field.markInvalid(Ext.form.VTypes['uniqueText']);
            } else if (x.responseText == "falsedelete") {
                field.markInvalid(Ext.form.VTypes['delText']);
            } else {
                field.clearInvalid();
            }
        }
    }, "name=" + field.id + "&value=" + v);
    return true;
}

/**
 * Ext.form.TimeField
 */
Ext.form.TimeField = function (config) {
    Ext.form.TimeField.superclass.constructor.call(this, config);
};

Ext.extend(Ext.form.TimeField, Ext.form.TriggerField, {
    // triggerClass : 'x-form-browse-trigger',
    defaultAutoCreate: {
        tag: "input",
        readonly: "readonly",
        type: "text",
        autocomplete: "off"
    },

    // Trigger button clicked, create and show time picker dialog.
    onTriggerClick: function () {
        if (this.disabled) {
            return;
        }
        try {
            popUpCalendar(this.el.dom, this.el.dom, 'yyyy-mm-dd', -1, -1);
        } catch (e) {
            eoms.log(e);
        }

    }
});

/**
 * 处理详细信息的显示和隐藏
 * @cfg {String} container 容器ID
 * @cfg {String} handleEl 标题元素的css查询字符串
 * @cfg {Boolean} showFirst 是否要打开第一项,默认为true
 * @example 例子
 *        switcher = new detailSwitcher();
 *        switcher.init({ container:'history',handleEl:'div.history-item-title'});
 */
var detailSwitcher = function () {
    var closedIcon = eoms.appPath + '/images/icons/closed.gif';
    var openedIcon = eoms.appPath + '/images/icons/opened.gif';
    var container, handleEl, group;
    var showFirst, allHide = true;
    return {
        init: function (cfg) {
            container = cfg.container;
            handleEl = cfg.handleEl;
            showFirst = cfg.showFirst === false ? false : true;
            group = Ext.select(handleEl, false, container);
            group.on('click', function (e, item) {
                this.toggleItem(item);
            }, this);

            if (showFirst) {
                this.toggleIndex(0, true);
            }
        },
        toggle: function () {
            group.each(function (item) {
                this.toggleItem(item, allHide);
            }, this);
            allHide = !allHide;
        },
        toggleIndex: function (index, unhide) {
            var item = group.elements[index];
            if (!item) return;
            this.toggleItem(item, unhide);
        },
        toggleItem: function (item, unhide) {
            if (!item) return;
            if (item.tagName == "IMG") item = item.parentNode;
            item = Ext.get(item);
            var detail = Ext.get(item.getNextSibling());
            var img = item.down('img.switchIcon').dom;
            unhide == null ? detail.toggleClass('hide')
                : (unhide === true ? detail.removeClass('hide') : detail.addClass('hide'));
            img.src = detail.hasClass('hide') ? closedIcon : openedIcon;
        }
    };
}

/**
 * Ext.data.JsonStore.getJSON
 */
Ext.data.JsonStore.prototype.getJSON = function () {
    var _tempArr = [];
    if (this.data.items.length > 0) {
        this.each(function (r) {
            _tempArr.push(Ext.util.JSON.encode(r.data));
        });
        return "[" + _tempArr.toString() + "]";
    } else
        return "[]";
};

/**
 * Ext.data.JsonStore.getJSONData
 */
Ext.data.JsonStore.prototype.getJSONData = function () {
    var _tempArr = [];
    if (this.data.items.length > 0) {
        this.each(function (r) {
            _tempArr.push(r.data);
        });
    }
    return _tempArr;
};
