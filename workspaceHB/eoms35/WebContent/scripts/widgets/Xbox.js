eoms.xbox = function(cfg){
    Ext.apply(this, cfg);
    
    this.addEvents({
        'inlayout':true
    });
    
    var el = Ext.DomHelper.append(document.body,{cls:'x-layout-inactive-content'},true);
    
    this.handler = Ext.get(cfg.handler);
    if(this.handler)
        this.handler.on('click',this.onclick,this);
    
    this.update();
    
    eoms.xbox.superclass.constructor.call(this,el,{
        title : cfg.dlgTitle || '选择',
        width : 450,
        height : 400,
        minWidth : 300,
        minHeight : 300,
        modal : true,
        autoTabs : true,
        shadow : false,
        proxyDrag : false,
        west : {
            split : true,
            initialSize : 250,
            autoScroll : true,
            titlebar : false,
            tabPosition : 'top',
            alwaysShowTabs : true
        },
        center : {
            titlebar : true,
            autoScroll : true,
            tabPosition : 'top',
            alwaysShowTabs : false
        }
    });
    
    this.addKeyListener(27, this.hide, this);
    this.addButton('确定', this.output, this);
    this.addButton('关闭', this.hide, this);
    
    var layout = this.layout;
    layout.beginUpdate();
    layout.add('west', this.creatTree(cfg),this);
    layout.add('center', this.creatList(el),this);
    layout.endUpdate();
    
    this.el = el;
}

Ext.extend(eoms.xbox, Ext.LayoutDialog,{
    creatTree : function(cfg){
        var checktype = cfg.checktype;
        var treeEl = this.el.createChild({cls:'x-layout-inactive-content'});
        var treeLoader = new Ext.tree.TreeLoader({
            dataUrl : cfg.dataUrl,
            baseParams : {
                checktype : checktype || ''
            }
        });
        
        treeLoader.on('beforeload',function(treeLoader,node,callback){
            treeLoader.baseParams['nodeType'] = node.attributes.nodeType || '';
            if(node.parentNode){
                treeLoader.baseParams['parentNode'] = node.parentNode.id;
                treeLoader.baseParams['parentNodeType'] = node.parentNode.attributes.nodeType || '';
            }
            treeLoader.baseAttrs = this.baseAttrs;
        },this);
        
        var tree = new Ext.tree.TreePanel(treeEl, {
            animate : true,
            enableDD : false,
            containerScroll : true,
            loader : treeLoader
        });
    
        var root = new Ext.tree.AsyncTreeNode({
            id : cfg.treeRootId || -1,
            text : cfg.treeRootText || '树图',
            nodeType : 'root'
        });
        tree.setRootNode(root); 
        tree.render();
        tree.on('checkchange', this.onCheck, this);
        //tree.on('expand', this.onTreeExpand, this);
        tree.checktype = checktype;
        
        //是否显示checkbox,以nodeType判断，多个nodeType用,隔开
        tree.on('beforeappend',function(tree,parentNode,node){
            var _checktype = tree.checktype;
            if(_checktype == null){
                node.attributes.checked = false;
            }
            else if(node.attributes.nodeType
                && _checktype.hasSubString(node.attributes.nodeType,","))
            {
                node.attributes.checked = false;
            }
        },this);
        
        if(cfg.defaultTree){
            this.defaultTree = tree;
        }
        var panel = new Ext.ContentPanel(treeEl, {
            title : cfg.treeRootText
        });
        return panel;   
    },
    creatList : function(el){
        var listEl = el.createChild({cls:'x-layout-inactive-content viewer-list'});
        this.store = new Ext.data.JsonStore({
            id : 'id',
            proxy : new Ext.data.MemoryProxy(),
            fields : this.fields || ['id','text','name','nodeType']
        });
        var lister = new Ext.View(listEl,
            '<div class="viewlistitem-{nodeType}">{text}; </div>',
            {
                emptyText : '<div>没有选择项目</div>',
                store : this.store
            }
        );
        lister.refresh();
        this.lister = lister;
        return new Ext.ContentPanel(listEl, {
            title : '选择结果(双击名称删除)'
        });
    },
    onclick : function(){
        this.show();
    },
    addData : function(node,isAppend){
        this.store.loadData([node.attributes],isAppend);
        this.lister.refresh();
    },
    removeData : function(node){       
        var record = this.store.getById(node.id);
        if (typeof record == "object")
            this.store.remove(record);
    },
    onBeforeCheck : function(node, checked){return true},
    onCheck : function(node, checked){
        if(!this.onBeforeCheck(node, checked)){
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
                    this.store.removeAll();
                    this.checkedNode = null;
                } else {
                    this.checkedNode = node;
                    this.addData(node,false);
                }
                return;
            }
            this.checkedNode = node;
            this.addData(node,false);
            return;
        }
        // 多选
        if (checked) {
            if (this.store.indexOfId(node.id) == -1) {           
                this.addData(node,true);
            }
        } else {
            if (this.store.indexOfId(node.id) >= 0) {
                this.removeData(node);
            }
        }
        //this.onAfterCheck(node, checked);
    },
    output : Ext.emptyFn,
    recover : Ext.emptyFn,
    update : Ext.emptyFn
});