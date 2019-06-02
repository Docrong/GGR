

var nodes=new Array();
var openNodes=new Array();
var icons=new Array(6);
var dept = null;
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
没有checkBox框的部门树型显示，每个节点，可以加载一个锚连接值,
后面并且可以连接两个参数：dept=所选的节点值，wsclass=工单类型。
并且，可以确定从哪个节点展开树，以及标亮显示选中的节点；
该function必须与com.boco.eoms.common.tree.WKTree.java类中的strWKTree()方法联合使用
例如：
WKTree wk_tree = new WKTree();
String strTree = wk_tree.strWKTree(1);
其中1：表示列出以该参数为父节点下面的所有部门

使用举例：
EOMS_J2EE/sysadmin/manager/TawDept/depttree.jsp;

参数说明：
arrName：Tree集合值，包含树型结构的各个节点的集合；
startNode：从该节点开始显示树型结构；
openNode：从该节点开始，展开其所有的子节点。若设为一个小于0的值，则表示：树型结构全部展开；
path：web应用的名称；
url：树型结构的各个节点的锚连接值。若为：""，则表示是本身的Action值；
deptid：树型结构中标亮显示的节点值；
wsclass：工单类型，用于工单模块。若不使用，可以设为：-1；
*/
function createTree3(arrName, startNode, openNode,path,url,deptid,wsclass)
{
    nodes = arrName;
    dept = deptid;
        if (path !== null) {
            rootPath = path;
        }
    if (nodes.length > 0) {
		preloadIcons();
		if (startNode == null) startNode = 0;

                if (openNode > 0 || openNode !=null) setOpenNodes(openNode);      //从（openNode）此节点展开

                if (openNode < 0) {   //展开所有节点
                  openAllNodes();
                  //alert(openNode);
                }
		if (startNode !=0) {
			var nodeValues = nodes[getArrayId(startNode)].split("|");
                        if (deptid == nodeValues[0]) {
                            document.write("<a href=\"" + url + "?dept="+nodeValues[0]+"&wsclass=" +wsclass+ "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><font color=\"#3300FF\">" + nodeValues[2] + "</font></a><br />");
                        } else {
                            document.write("<a href=\"" + url + "?dept="+nodeValues[0]+"&wsclass=" +wsclass+ "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" />" + nodeValues[2] + "</a><br />");
                        }
		} else {
                     document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" />Eoms<br />");
                }

		var recursedNodes = new Array();
		addNode3(startNode, recursedNodes);
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
// Adds a new node in the tree3
function addNode3(parentNode, recursedNodes) {
	for (var i = 0; i < nodes.length; i++) {

		var nodeValues = nodes[i].split("|");

		if (nodeValues[1] == parentNode) {

			var ls	= lastSibling(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

			// Write out line & empty icons
			for (g=0; g<recursedNodes.length; g++) {
				if (recursedNodes[g] == 1) document.write("<img src=\""+path+"/images/img/line.gif\" align=\"absbottom\" alt=\"\" />");
				else  document.write("<img src=\""+path+"/images/img/empty.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// put in array line & empty icons
			if (ls) recursedNodes.push(0);
			else recursedNodes.push(1);

			// Write out join icons
			if (hcn) {
				if (ls) {
					document.write("<a href=\"javascript: oc(" + nodeValues[0] + ", 1);\"><img id=\"join" + nodeValues[0] + "\" src=\""+path+"/images/img/");
					 	if (ino) document.write("minus");
						else document.write("plus");
					document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				} else {
					document.write("<a href=\"javascript: oc(" + nodeValues[0] + ", 0);\"><img id=\"join" + nodeValues[0] + "\" src=\""+path+"/images/img/");
						if (ino) document.write("minus");
						else document.write("plus");
					document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				}
			} else {
				if (ls) document.write("<img src=\""+path+"/images/img/join.gif\" align=\"absbottom\" alt=\"\" />");
				else document.write("<img src=\""+path+"/images/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// Start link
                            document.write("<a href=\"" + url + "?dept="+ nodeValues[0]+"&wsclass="+wsclass+ "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");
			// Write out folder & page icons
			if (hcn) {
				document.write("<img id=\"icon" + nodeValues[0] + "\" src=\""+path+"/images/img/folder")
					if (ino) document.write("open");
				document.write(".gif\" align=\"absbottom\" alt=\"Folder\" />");
			} else { document.write("<img id=\"icon" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" />");}

			// Write out node name
                        if (dept == nodeValues[0]) {
			    document.write("<font color=\"#3300FF\">"+nodeValues[2]+"</font>");
                        }
                        else {
			    document.write(nodeValues[2]);
                        }
			// End link
			document.write("</a><br />");

			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div nowrap id=\"div" + nodeValues[0] + "\"");
					if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");
				addNode3(nodeValues[0], recursedNodes);
				document.write("</div>");
			}

			// remove last line or empty icon
			recursedNodes.pop();
		}
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