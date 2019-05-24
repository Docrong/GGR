/**************************************************************************
	Copyright (c) 2001 Geir Landr\uFFFD (drop@destroydrop.com)
	JavaScript Tree - www.destroydrop.com/hugi/javascript/tree/
	Version 0.96

	This script can be used freely as long as all copyright messages are
	intact.
**************************************************************************/

// Arrays for nodes and icons
var nodes		= new Array();;
var openNodes	= new Array();
var icons		= new Array(6);
var checkedNodes = new Array();
var checkedNo = new Array();
var checkedDp = new Array();
var rootPath ;
// Loads all icons that are used in the tree
function preloadIcons() {
	icons[0] = new Image();
	icons[0].src = ""+path+"/images/img/plus.gif";
	icons[1] = new Image();
	icons[1].src = ""+path+"/images/img/plusbottom.gif";
	icons[2] = new Image();
	icons[2].src = ""+path+"/images/img/minus.gif";
	icons[3] = new Image();
	icons[3].src = ""+path+"/images/img/minusbottom.gif";
	icons[4] = new Image();
	icons[4].src = ""+path+"/images/img/folder.gif";
	icons[5] = new Image();
	icons[5].src = ""+path+"/images/img/folderopen.gif";
}


/*
功能说明：
带checkbox框的机房，或者第三方域的显示列表。可以显示已被选和不可选的节点；
该function必须与com.boco.eoms.common.tree.RoomTree.java类中的方法联合使用
例如：
1、机房列表：
       RoomTree wk_tree1 = new RoomTree();
       strTree = wk_tree1.getinfo();
2、第三方域列表：
       RoomTree wk_tree5 = new RoomTree();
       strTree = wk_tree5.strWKTree(domType-7,domType);
其中，domType：第三方域的域类型；
     domType-7：为信息发布树型显示预留的参数；

参数说明：
arrName：节点信息树组，包含树型结构的各个节点的集合；
path：web应用名称；
checkedid：已被选中的节点；
checkedno：不可选的节点；
*/
function createTree2(arrName,path,checkedid,checkedno) {
	nodes = arrName;
        checkedNodes = checkedid;
        checkedNo = checkedno;
        if (path !== null) {
            rootPath = path;
        }
        if (checkedNodes == null){
            checkedNodes = 0;
        }
        else {
            //checkedNodes = checkedid;
            checkedNodes = checkedid.split(",");
        }

        if (checkedNo == null){
            checkedNo = 0;
        }

        else {
            //checkedNo = checkedno;
            checkedNo = checkedno.split(",");
        }

	//alert(nodes);
	if (nodes.length > 0) {
		preloadIcons();
        	for (g=0; g<nodes.length; g++) {

                     var nodeValues = nodes[g].split("|");

                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick='' checked disabled>" + nodeValues[1] + "<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick='' disabled>" + nodeValues[1] + "<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick='' checked>" + nodeValues[1] + "<br />");
                        } else {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''>" + nodeValues[1] + "<br />");
                        }
        	}

	}
}
// Returns the position of a node in the array
function getArrayId(node) {
	for (i=0; i<nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[0]==node) return i;
	}
}
// Puts in array nodes that will be open
function setOpenNodes(openNode) {
	for (i=0; i<nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[0]==openNode) {
			openNodes.push(nodeValues[0]);
			setOpenNodes(nodeValues[1]);
		}
	}
}

// open all nodes
function openAllNodes()
{
  for (i=0;i<nodes.length;i++) {
    var nodeValues = nodes[i].split("|");
      //alert(nodeValues[0]);
      //document.write(nodeValues[0]);
    openNodes.push(nodeValues[0]);
      //openAllNodes();
  }
}

// Checks if a node is open
function isNodeOpen(node) {
	for (i=0; i<openNodes.length; i++)
		if (openNodes[i]==node) return true;
	return false;
}
// Checks if a node has any children
function hasChildNode(parentNode) {
	for (i=0; i< nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode) return true;
	}
	return false;
}
// Checks if a node is the last sibling
function lastSibling (node, parentNode) {
	var lastChild = 0;
	for (i=0; i< nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode)
			lastChild = nodeValues[0];
	}
	if (lastChild==node) return true;
	return false;
}
// Checks if a node has selected
function isShowSelect (node,cNodes) {
    for (j=0;j< cNodes.length;j++)
    {
        if (cNodes[j] == node)
            return true;
    }
            return false;
}
// Checks if a node is readonly
function isShowReadOnly (node,cNo) {
    for (j=0;j<cNo.length;j++)
    {
        if (cNo[j] == node)
            return true;
    }
            return false;
}
// readonly and selected
function showRoSed(node,cNo,cNodes)
{
    for (j=0;j<cNo.length;j++) {
        for (k=0;k<cNodes.length;k++)
        {
            if ((cNo[j] == cNodes[k]) && (cNodes[j] == node))
                return true;
        }
    }
    return false;
}

// Opens or closes a node 2
function oc2(node, bottom) {
	var theDiv = document.getElementById("div2" + node);
	var theJoin	= document.getElementById("join2" + node);
	var theIcon = document.getElementById("icon2" + node);

	if (theDiv.style.display == 'none') {
		if (bottom==1) theJoin.src = icons[3].src;
		else theJoin.src = icons[2].src;
		theIcon.src = icons[5].src;
		theDiv.style.display = '';
	} else {
		if (bottom==1) theJoin.src = icons[1].src;
		else theJoin.src = icons[0].src;
		theIcon.src = icons[4].src;
		theDiv.style.display = 'none';
	}
}
// Opens or closes a node
function oc(node, bottom) {
	var theDiv = document.getElementById("div" + node);
	var theJoin	= document.getElementById("join" + node);
	var theIcon = document.getElementById("icon" + node);

	if (theDiv.style.display == 'none') {
		if (bottom==1) theJoin.src = icons[3].src;
		else theJoin.src = icons[2].src;
		theIcon.src = icons[5].src;
		theDiv.style.display = '';
	} else {
		if (bottom==1) theJoin.src = icons[1].src;
		else theJoin.src = icons[0].src;
		theIcon.src = icons[4].src;
		theDiv.style.display = 'none';
	}
}

// Push and pop not implemented in IE(crap!    don\uFFFDt know about NS though)
if(!Array.prototype.push) {
	function array_push() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
	Array.prototype.push = array_push;
}
if(!Array.prototype.pop) {
	function array_pop(){
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
	Array.prototype.pop = array_pop;
}


/*
功能说明：
带checkbox框的机房，或者第三方域的显示列表。可以显示已选、可选但未选择的节点；
该function必须与com.boco.eoms.common.tree.RoomTree.java类中的方法联合使用
例如：
1、机房列表：
       RoomTree wk_tree1 = new RoomTree();
       strTree = wk_tree1.getinfo();
2、第三方域列表：
       RoomTree wk_tree5 = new RoomTree();
       strTree = wk_tree5.strWKTree(domType-7,domType);
其中，domType：第三方域的域类型；
     domType-7：为信息发布树型显示预留的参数；

参数说明：
arrName：节点信息树组，包含树型结构的各个节点的集合；
path：web应用名称；
checkedable：可选的的节点；
checkedid：已选的的节点；
*/
function createTree4(arrName,path,checkedable,checkedid) {
//checkedNodes = checkedid.split(",")
	nodes = arrName;
        checkedNodes = checkedid;
        //checkedDp = checkedable;
        if (path !== null) {
            rootPath = path;
        }
        if (checkedNodes == null){
            checkedNodes = 0;
        } else {
            //checkedNodes = checkedid;
            checkedNodes = checkedid.split(",");
        }
        if (checkedNo == null){
            checkedNo = 0;
        } else {
            //checkedNo = checkedno;
            checkedNo = checkedable.split(",");
        }
	//alert(nodes);
	if (nodes.length > 0) {
		preloadIcons();
        	for (g=0; g<nodes.length; g++) {

                     var nodeValues = nodes[g].split("|");

                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick='' checked>" + nodeValues[1] + "<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
                            document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick='' checked>" + nodeValues[1] + "<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''>" + nodeValues[1] + "<br />");
                        } else {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick='' disabled>" + nodeValues[1] + "<br />");
                        }
        	}
	}
}

