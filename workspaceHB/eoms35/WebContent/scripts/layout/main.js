var mainLayout = function() {
    var nt = Ext.tree;
    Ext.QuickTips.init();
    
    var layout = new Ext.BorderLayout(document.body, {
        west : {
            split : true,
            initialSize : 200,
            minSize : 150,
            maxSize : 300,
            titlebar : true,
            collapsible : true,
            animate : false,
            autoScroll : true
        },
        center : {
            autoScroll : true
        }
    });
    
    layout.beginUpdate();
    layout.add('west', new Ext.ContentPanel('classes', {
        title : '模块浏览器',
        fitToFrame : true
    }));
    // 会增加一个iframe请求,不明原因
    layout.add('center', new Ext.ContentPanel('pages', {
        fitToFrame : true
    }));
    layout.endUpdate();
    

    // 创建导航树
    var treeLoader = new nt.TreeLoader({
        dataUrl : config.treeGetNodeUrl,
        baseAttrs : {
            hrefTarget : 'pages'
        }
    });

    var tree = new nt.TreePanel('classes-body', {
        enableDD : false,
        containerScroll : true,
        lines : true,
        rootVisible : true
    });

    tree.on('beforeappend', function(t, parent, node) {
        var href = node.attributes.href;
        node.attributes._href = href;
        if (href) {
            // 以:开头,则说明要指定端口，需要为其加上scheme和serverName
            if (/^:/.test(href)) {
                node.attributes.href = config.scheme + "://" + config.serverName + href;
            }

            // 以/开头,则加上eoms.appPath
            if (/^\//.test(href)) {
                node.attributes.href = eoms.appPath + href;
            }
        }
    });
    
    var treeLog = function(node, e) {
        if(node.attributes.href && node.attributes.href != "" && e.getTarget().tagName == "SPAN"){
            Ext.Ajax.request({
                url:eoms.appPath+'/log/tawCommonLogOperators.do?method=saveLog',
                params : {
                    id : node.attributes.id,
                    href : node.attributes._href,
                    text : node.attributes.text
                }
            });
        }
    };
    if(config.log) tree.on('click', treeLog);
    
    // TODO 导航键在IE下不起作用
    tree.el.on('keypress', function(e) {
        if (e.isNavKeyPress()) {
            e.stopEvent();
        }
    });

    treeroot = new nt.AsyncTreeNode({
        id : config.treeRootId,
        allowDrag : false,
        allowDrop : false,
        text : config.treeRootText,
        loader : treeLoader
    });
    tree.setRootNode(treeroot);
    tree.render();
    treeroot.expand();
    
    var west = layout.regions.west;
    west.createToolButton({cls:'refresh',fn:function(){treeroot.reload();},tips:'刷新树图'});
    west.createToolButton({cls:'collapse',fn:function() {
        setTimeout(function() {
            treeroot.eachChild(function(n) {
                n.collapse(false, false);
            });
        }, 10);
    },tips:'收起树图'});
    
    west.createToolButton({cls:'expand',fn:function() {
        setTimeout(function() {
            treeroot.eachChild(function(n) {
                n.expand(false, false);
            });
        }, 10);
    },tips:'展开树图'});
    
    document.body.style.backgroundImage = 'none';
    
    return {
        layout : layout,
        tree : tree,
        loadDoc : function(url) {
            Ext.get('main').dom.src = url;
        }
    };
};
