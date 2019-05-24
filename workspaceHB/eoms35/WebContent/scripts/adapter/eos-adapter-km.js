/**
 * eos需求下的特殊处理
 */ 
Chooser.prototype.update = function(){
	this.chosenView.jsonData = [];
	this.chosen.each(function(nodeInfo) {
		//如果未指定组长，则设定leaderId为选中对象的id
		if(!nodeInfo.leaderId || nodeInfo.leaderId==""){
			nodeInfo.leaderId = nodeInfo.id;
		}
		//如果有leaderName，则info为组长信息
		nodeInfo.info = nodeInfo.leaderName ? '(组长:'+nodeInfo.leaderName+')' : '';

		this.chosenView.jsonData.push(nodeInfo);
	},this);

	this.chosenView.refresh();
	
	function toStr(o,isShowLeader){
		var str = '{'
			+'id:\''+o.id+'\''
			+',nodeType:\''+o.nodeType+'\''
		if(o.nodeType=='subrole' && isShowLeader && o.leaderId){
			str += ',leaderId:\''+o.leaderId+'\'';
		}
		str += '}';
		return str;
	}
	
	Ext.each(this.category, function(c) {
		var arrId = [], arrName = [], arrNodeType = [], arrLeaderId = [], arrJSON = [],type;	
		var t = new Ext.MasterTemplate(
			'<list>',
				'<tpl name="participant">',
					'<Participant><id>{id}</id><name>{text}</name><type>{nodeType}</type></Participant>',
				'</tpl>',
			'</list>'
		);
		this.chosen.each(function(nodeInfo) {
			if (nodeInfo.categoryId == c.id) {
				arrId.push(nodeInfo.id);
				arrName.push(nodeInfo.text);
				t.add('participant',nodeInfo);
				arrNodeType.push(nodeInfo.nodeType);
				arrLeaderId.push(nodeInfo.leaderId);
				type = nodeInfo.categoryId;
				if(this.returnJSON){
					arrJSON.push(toStr(nodeInfo,this.showLeader));				
				}
			}
			//alert(nodeInfo.categoryId);
		}, this);

		//c['hiddenEl'].dom.value = this.chosen.length > 0 ? t.apply() : "";
		if(arrId.toString()!=''){
		
		document.getElementById(type+"Id").value = arrId.toString();
		document.getElementById(type+"Name").value = arrName.toString();
		document.getElementById(type+"Type").value = arrNodeType.toString();
//		window.document.all.toOrgId.value = arrId.toString();
//		window.document.all.toOrgName.value = arrName.toString();
//		window.document.all.toOrgType.value = arrNodeType.toString();
		}
		//c['hiddenEl_nodeType'].dom.value = arrNodeType.toString();
		//c['hiddenEl_leaderId'].dom.value = arrLeaderId.toString();
		//if(this.returnJSON){
		//	c['hiddenEl_JSON'].dom.value = "[" + arrJSON.toString() + "]";
		//}
				
	}, this);		
}

xbox.prototype.update = function(){
		var jsonData = this.gridData.getJSONData();
		var chosenTextList = [],chosenDataList = [], chosenType = [] ,leaf;
		Ext.each(jsonData,function(item){
			chosenTextList.push(item.text || item.name);
			chosenDataList.push(item.id);
			chosenType.push(item.nodeType);
		},this);

		if (this.viewer) {
			this.viewer.jsonData = jsonData;
			this.viewer.refresh();
		}
		if(this.returnJSON) chosenDataList = this.gridData.getJSON();
		this.onOutput(chosenTextList,chosenDataList,jsonData);
		Ext.getDom("toOrgType").value = chosenType.toString();
};