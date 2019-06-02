String.prototype.hasSubString = function(s,f){
	if(!f) f="";
	return (f+this+f).indexOf(f+s+f)==-1?false:true;
};

winbox = function (){
	var chosen = new Ext.util.MixedCollection();
	return {
		init : function(cfg) {
			Ext.apply(this,cfg);
			this.body = Ext.get(window.dialogArguments);
			var datastr = this.body.down("div.data").dom.innerHTML;
			this.data = datastr=="" ? [] : Ext.util.JSON.decode(datastr);
			
			this.initSelected();
			this.initTree();
			this.initBtn();
			
		},
		initSelected : function(){
			if(this.data.length && this.data.length>0){
				Ext.each(this.data,function(d){
					chosen.add(d.nodeType+":"+d.id,d);
				},this);
			}
		},
		initBtn : function(){
			new Ext.Button("btn",{
				text:'确定',
				handler:this.output,
				scope:this
			})
		},
		output : function(){
			var json, jsonArray=[],deptList=[],userList=[];
			var viewer = this.body.down("div.viewer-box");
			viewer.update("");
			chosen.each(function(item){
				
				if("dept"==item.nodeType){
					deptList.push(item.id);
					jsonArray.push(item);
					viewer.dom.innerHTML += "<div class='viewlistitem-dept'>"+item.text+";</div>";
				}
				else if ("user"==item.nodeType){
					userList.push(item.id);
					jsonArray.push(item);
					viewer.dom.innerHTML += "<div class='viewlistitem-user'>"+item.text+";</div>";
				}
			});
			
			json = Ext.util.JSON.encode(jsonArray);
			this.body.down("input[name=userId]").dom.value = userList.toString();
			this.body.down("input[name=deptId]").dom.value = deptList.toString();
			this.body.down("div.data").update(json);
			window.close();
			
		},
		initTree : function() {

			var treeLoader = new Ext.tree.TreeLoader({
				dataUrl : this.url,
				baseParams : {
					checktype : this.checktype || ''
				}
			});
			
			treeLoader.on('beforeload', function(treeLoader, node, callback) {
				treeLoader.baseParams['nodeType'] = node.attributes.nodeType || '';
				treeLoader.baseAttrs = this.baseAttrs;
			}, this);
			
			var tree = new Ext.tree.TreePanel("tree", {
				animate : true,
				enableDD : false,
				containerScroll : true,
				loader : treeLoader
			});

			var root = new Ext.tree.AsyncTreeNode({
				id : this.rootId,
				text : this.rootText,
				nodeType : 'root'
			});
			
			tree.setRootNode(root);
			tree.render();
			tree.on('checkchange', this.onCheck, this);
			tree.on('expand', this.onTreeExpand, this);

			// 是否显示checkbox,以nodeType判断，多个nodeType用,隔开
			tree.on('beforeappend', function(tree, parentNode, node) {
				if (!this.checktype) {
					node.attributes.checked = chosen.containsKey(this.getKey(node)) ? true : false;
				} else if (node.attributes.nodeType && this.checktype.hasSubString(node.attributes.nodeType, ",")) {
					node.attributes.checked = chosen.containsKey(this.getKey(node)) ? true : false;
				}
			}, this);

		},
		getKey : function(o){
			return o.attributes.nodeType + ':' + o.id;
		},
		addRecord : function(node){
			var key = this.getKey(node);
			chosen.add(key,{
				'id' : node.id,
				'text' : node.text,
				'nodeType' : node.attributes.nodeType
			})
		},
		onBeforeCheck : function(node, checked){return true},
		onCheck : function(node, checked) {
			var key = this.getKey(node);
			if (!this.onBeforeCheck(node, checked)) {
				node.getUI().toggleCheck(false);
				return;
			}
			
			// 单选
			if (this.single) {
				if (this.checkedNode != null) {
					var c = this.checkedNode;
					c.attributes.checked = false;
					c.getUI().checkbox.checked = false;
					if (c.id == node.id) {// 删除
						chosen.clear();
						this.checkedNode = null;
					} else {
						this.checkedNode = node;
						chosen.clear();
						this.addRecord(node);
					}
					return;
				}
				
				this.checkedNode = node;
				this.addRecord(node);
				return;
			}
			
			// 多选
			if (checked) {
				if (!chosen.containsKey(key)) {
					this.addRecord(node);
				}
			} else {
				if (chosen.containsKey(key)) {
					chosen.removeKey(key);
				}
			}
		},
		onTreeExpand : function(node){	
		}
	}
}();
