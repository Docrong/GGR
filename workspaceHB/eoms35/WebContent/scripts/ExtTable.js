/**
 * 表格扩展,目前有列隐藏功能,在初始化后,右键点击表头,可弹出菜单进行对列的隐藏和显示.
 * @constructor
 * @requires Ext
 * @param {String/HTMLElement} el 表格Id或表格元素
 * @param {Object} config 选项设置
 * @cfg {Boolean} [enableColHide=true] 是否激活列隐藏菜单 
 * @example
 *  new eoms.ExtTable("list").init();
 */
eoms.ExtTable = function(el,config) {
    var c = document.cookie + ";";
    var re = /\s?username=(.*?);/g;
    var username, matches;
    while((matches = re.exec(c)) != null){
        username = matches[1];
    }
	var el = Ext.get(el);
	if(!el) return;

	return {
		enableColHide : true,
		el : el,
		init : function(){
			Ext.apply(this,config);
			if(this.enableColHide) {
                eoms.loadJS(eoms.appPath+'/scripts/util/userData.js',this.colhide,this);               
            }
		},
		/**
		 * 列隐藏
		 * @method
		 */
		colhide : function() {
				var id = el.dom.id, key = username+"-colhide-"+id;
                var cp = new Ext.state.userData();
				var menu = new Ext.menu.Menu();
				var tHead = el.down('thead'), ths = tHead.select('th');
				var state = (cp.get(key)) ? (cp.get(key).split(',')) : [];
                var hidden = 0;
				var pb;
				//TODO tip
				if(pb=Ext.select("span.pagebanner")){
					pb.createChild({tag:'span', id:'colhide-tip',style:'color:#27779F;'});		
				}
				
				ths.each(function(item, list, index){
					if(typeof state[index] == 'undefined' || state[index]=='') state[index] = "1";
					var t = item.dom.innerText || item.dom.textContent;
					if(!t || t.trim()=="") return;
					menu.add(new Ext.menu.CheckItem({
						text: t,
						checked: state[index]=="1",
						hideOnClick:false,
						handler:onItemClick,
						checkHandler: function(item, checked){onItemCheck(item,checked,index)}
			    	}));
			    	if(state[index]=="0"){
			    		el.select('th:nth-child('+(index+1)+'),td:nth-child('+(index+1)+')').setDisplayed(false);
			    		hidden++;
			    	}
				});
				
				tHead.on("contextmenu",function(e){
					menu.showAt([tHead.getX(),tHead.getY()+tHead.getHeight()]);
				});
				tHead.swallowEvent('contextmenu', true);
				setCount();
				
				function onItemClick(item){
					if(item.checked && (menu.items.length-hidden <= 1)){
						return false;
					}					
				}
				function onItemCheck(item, checked, index){
			    	el.select('th:nth-child('+(index+1)+'),td:nth-child('+(index+1)+')').setDisplayed(checked);
					hidden += checked ? -1 : 1;
			    	state[index] = checked ? "1" : "0";
					cp.set(key,state.join(','));
					setCount();
				}
				function setCount(){
					var tipEl = Ext.get('colhide-tip');
					if(!tipEl)return;
					var text = "右键点击表头可编辑列";
					tipEl.update(hidden > 0 ? text+":"+hidden+"列数据已隐藏" : text);
				}
		
		}

	}
};


