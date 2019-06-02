/********************************************************************************
 Copyright (c) 2002,亿阳信通网络事业部IP网管
 All rights reserved.
 Filename ：comm.js
 Abstract ：常用树型结构数据操作集
 Version　：2.0
 Author   ：Liu Guoyuan
 Finished Date ：2002-08-30
 Last Modified ：2003-01-16

********************************************************************************/

function hasSubNode(obj){
//判断树图菜单的选择项是否有子结点
  var nodeIndex = obj.selectedIndex; 
  var strNodeName = obj.options[nodeIndex].text; 
  var curNodeClass = parseInt(strNodeName.substring(0,1));//取当前结点级数
  if (nodeIndex == obj.length-1){ //最后一个节点时，不用判断
    return false;
  }else{
    var nextNodeClass = parseInt(obj.options[nodeIndex+1].text.substring(0,1));//取下一个结点级数
    if (nextNodeClass>curNodeClass){//有子结点
      return true;
    }
    else{
      return false;
    }
  }
}

function builderTree(sourceObject,targetObject)
{
//生成树型菜单
//数据格式：ID，CLASS，NAME;  对应ID值，级别，名称
//如 1,1,全国;2,2,北京;
	var numargs = arguments.length; //返回的参数数量
	if (numargs>0){
		var sObject=eval(sourceObject);
		var tObject=eval(targetObject);
	}
	else{
		var sObject=eval("document.form1.treeCode");       //源对象：源数据所在对象,源数据格式为 "id,class,name;"　如"1000,1,全国;1001,2,北京;"
		var tObject=eval("document.form1.selectTree");    //目标对象
	}
	var name,count;
	var treeCode=new Array();
	treeCode=sObject.value.split(";");
	count=treeCode.length-1;
	j=0;
	for (i=0;i<count;i++){
		var treeCodeItem=new Array(3);
		treeCodeItem=treeCode[i].split(",");
		nodeId=treeCodeItem[0];        //节点ID
		nodeClass=treeCodeItem[1];     //节点级数
		nodeName=treeCodeItem[2];      //节点名称
		name=nodeClass+"级";
		for (k=1;k<=nodeClass;k++){
			name=name+"...";
		}
		tObject.options.length=j+1;
		if (nodeClass==0){
			tObject.options[j].className="OptionRedColor";
			nodeName="···"+nodeName+"···";
		}
		else{
			nodeName=name+"|"+nodeName;
		}
		tObject.options[j].text=nodeName;
		tObject.options[j].value=nodeId;
		tObject.options[j].classvalue=nodeClass;
		j++;
	}
	tObject.options[0].selected=true;
}