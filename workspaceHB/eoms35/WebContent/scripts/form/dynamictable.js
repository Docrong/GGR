/**
 * 动态表格 
 * 基于prototype.js 1.5.0
 * param tableId :		表格ID
 * param templateId : 	模板行ID
 * param offsetIndex:	行数修正值
 *@mios
 *@version 1.0
 */

//需要在插入前更新的字符的标志
var re = /-UID-/g;
var Dyna = {
	all:[],
	set:function(id,targetId, templateId, offsetIndex){
	  this.all[id] = new DynamicTable(targetId, templateId, offsetIndex);
	}
};
/**
 * param targetId :	对象id
 * param templateId :    包含要插入内容的模板id,
 * 只有template的内容(不包括template标签本身)才能被插入到目标元素中
 * 如果要插入行,则需创建一个隐藏的, 包含此行的table,并给tbody一个模板id
 * 如果要插入table,则创建一个隐藏的,包含此table的div,给其一个模板id
 * param offsetIndex:	行数修正值
*/
var DynamicTable = function(targetId, templateId, offsetIndex){
  this.targetId = targetId;
  this.templateId = templateId;
  this.uid = 0;
  this.offsetIndex = offsetIndex || 0;
};
DynamicTable.prototype.add = function(){
  var target = $(this.targetId);
  //var newRow = $(this.templateId).cloneNode(true);
  var newNode = $(this.templateId);
  eoms.log('newnode:'+newNode);
  newNode = newNode.innerHTML.toString();
  newNode = newNode.replace(re, this.uid);
  if (target.tagName == 'TABLE') target = target.tBodies[0];
  new Insertion.Bottom(target, newNode);
//  newNode = newNode.replace(new RegExp('(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)', 'img'), '');
//  eoms.log(target);
//  eoms.log(newNode);
//  target.insertAdjacentHTML('beforeEnd',newNode);
  this.uid = this.uid + 1;
};

//返回元素所在行是动态添加的第几行
function getDynaIndex(el){
  var elTable = getParentForTag(el,"TABLE");
  if(Dyna.all[elTable.id]){
    return getRowIndex(el) - Dyna.all[elTable.id].offsetIndex;
  }
  else{
    return getRowIndex(el);
  }
}
//返回元素所在行序数
function getRowIndex(el){
  return getParentForTag(el,"TR").rowIndex;
}
//删除元素所在行
function removeRow(el){
  var elTable = getParentForTag(el,"TABLE");
  elTable.deleteRow(getParentForTag(el,"TR").rowIndex);
}
//删除元素所在表格
function removeTable(el){
  var elTable = getParentForTag(el,"TABLE");
  elTable.parentNode.removeChild(elTable);
  if(Dyna.all[elTable.id]){
    Dyna.all[elTable.id]=null;
  }
}
//查找el节点的指定标签名的父节点
function getParentForTag(el,tag){
  if(el==null || el.tagName==tag){
    return el;
  }
  else{
    while(el.tagName!=tag && el.tagName!="BODY"){
	  el=el.parentNode;
	}
	return el;
  }
}