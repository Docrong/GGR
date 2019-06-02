function removeLoading() {
    var loading = Ext.get('loading');
    var mask = Ext.get('loading-mask');
    mask.setOpacity(.7);
    mask.shift({
        xy : loading.getXY(),
        width : loading.getWidth(),
        height : loading.getHeight(),
        remove : true,
        duration : 1,
        opacity : .3,
        easing : 'bounceOut',
        callback : function() {
            loading.fadeOut({
                duration : .2,
                remove : true
            });
        }
    });
}

var indexLayout = function() {
    var layout = new Ext.BorderLayout(document.body, {
        north : {
            split : false,
            initialSize : 97,
            minSize : 97
        },
        center : {
            autoScroll : false
        }
    });

    layout.beginUpdate();
    layout.add('north', new Ext.ContentPanel('header', {fitToFrame : true}));
    layout.add('center', new Ext.ContentPanel('mainFrame', {fitToFrame : true}));
    layout.endUpdate();
    
    var north = layout.getRegion("north");
        north.el.setStyle({'border-color':'#f0f0f0'});
    var center = layout.getRegion("center");
        center.el.setStyle({'border-color':'#f0f0f0'});
    
    var page = window.location.href.split('#')[1];
    if (page) {
        window.frames['mainFrame'].location.href = page;
    }

    if (Ext.isSafari || Ext.isOpera) {
        layout.layout();
    }

    removeLoading();
    
    var menu = Ext.select("ul#menu li a");
    
    menu.on("click", function(e,item) {
        if(item.tagName=="SPAN"){
            item = item.parentNode;
        }
        if(item.tagName=="A"){
            Ext.select("ul#menu li a.cur").removeClass("cur");
            Ext.get(item).addClass("cur").blur();
        }
    });
    
    var tid = 0, expmenu = new Ext.menu.Menu(), doc = Ext.get(document);
    var exptab = Ext.DomHelper.append('menubar',{cls:'expmenu',html:'更多<span>&raquo;</span>'},true);
    expmenu.exptab = exptab;
    
    var handleOver = function(e, t){
        if(t != exptab.dom && t != expmenu.el.dom && !e.within(exptab) && !e.within(expmenu.el)){
            hideMenu();
        }   
    };
    
    var handleDown = function(e){
        if(!e.within(expmenu.el)){
            hideMenu();
        }
    }
    
    var showMenu = function(){
        if (!expmenu.isVisible()) {
            expmenu.show(exptab, 'tr-br?');
            doc.on('mouseover', handleOver, null, {buffer:150});
            doc.on('mousedown', handleDown);
        }
    };
    
    var hideMenu = function(){
        expmenu.hide();
        doc.un('mouseover', handleOver);
        doc.un('mousedown', handleDown);
    }
    
    exptab.on('mouseover',function(e){
        if(!tid){
            tid = showMenu.defer(150);
        }       
    });
    exptab.on('mouseout',function(e){
        if(tid && !e.within(exptab, true)){
            clearTimeout(tid);
            tid = 0;                
        }
    });
    
    var initMenu = function(){
        hideMenu();      
        expmenu.removeAll();
        var w = document.body.offsetWidth;
        menu.each(function(item){
            var tab = Ext.get(item.dom.parentNode);
            tab.setDisplayed(true);
            w -= tab.getWidth();
            if(w-55 < 0){
                expmenu.add({text:item.down('span').dom.innerHTML,href:item.dom.href,hrefTarget:'mainFrame'});
            }
            tab.setDisplayed(w-55 > 0);
        });
        exptab.setDisplayed(w < 0);
    }
    
    initMenu();
    window.onresize = initMenu;
    
    return {
        layout : layout,
        north : north,
        center : center,
        menu : menu,
        expmenu : expmenu
	};
}();
