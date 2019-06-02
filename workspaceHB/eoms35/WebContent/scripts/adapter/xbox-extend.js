/*
 * 对Xbox的扩展，主要实现了父子节点不可同时check：父节点被选，子节点disabled
 * 若想选择子节点，则父节点不要check。
 * */
xbox.prototype.onAfterCheck = function(node, checked) {
	if (checked && node.childNodes) {
		Ext.each(node.childNodes, function(cnode) {
			cnode.getUI().checkbox.disabled = true;
			cnode.getUI().toggleCheck(false);
		}, this);
	}
	if ((!checked) && node.childNodes) {
		Ext.each(node.childNodes, function(cnode) {
			cnode.getUI().checkbox.disabled = false;
		}, this);
	}
};
/*
 * 展开时的扩展，当展开父节点时，默认父节点为未选择，主要是为了配合父子节点不可同时check的实现*/
xbox.prototype.onTreeExpand = function(node){	
	node.getUI().toggleCheck(false);
	Ext.each(this.gridData.getJSONData(), function(item) {
		var cknode = node.findChild('id', item.id);
		if (cknode){
			if(cknode.attributes.nodeType && cknode.attributes.nodeType == item.nodeType){
				cknode.getUI().toggleCheck(true);
			}			
		}				
	});
};
