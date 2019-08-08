// Arrays for nodes and icons
var nodes = new Array();
;
var openNodes = new Array();
var icons = new Array(6);
var checkedNodes = new Array();
var rootPath;

// Loads all icons that are used in the tree
function preloadIcons(path) {
    icons[0] = new Image();
    icons[0].src = "" + path + "/images/img/plus.gif";
    icons[1] = new Image();
    icons[1].src = "" + path + "/images/img/plusbottom.gif";
    icons[2] = new Image();
    icons[2].src = "" + path + "/images/img/minus.gif";
    icons[3] = new Image();
    icons[3].src = "" + path + "/images/img/minusbottom.gif";
    icons[4] = new Image();
    icons[4].src = "" + path + "/images/img/folder.gif";
    icons[5] = new Image();
    icons[5].src = "" + path + "/images/img/folderopen.gif";
}

/*
����˵����
��checkBox��Ĳ���������ʾ��ÿ���ڵ㣬���Ը��ݲ�������ʾ��ѡ��δѡ��Ľڵ㣻
�ڸ����͵���ͼ�У�checkbox��֧�ֶ�ѡ��
��function������com.boco.eoms.common.tree.WKTree.java���е�strWKTree()��������ʹ��
���磺
WKTree wk_tree = new WKTree();
String strTree = wk_tree.strWKTree(1);
����1����ʾ�г��Ըò���Ϊ���ڵ���������в���

ʹ�þ�����
EOMS_J2EE/worksheet/manager/Tawfaultsheet/transmit.jsp;

����˵����
arrName��Tree����ֵ���������ͽṹ�ĸ����ڵ�ļ��ϣ�
startNode���Ӹýڵ㿪ʼ��ʾ���ͽṹ��
openNode���Ӹýڵ㿪ʼ��չ�������е��ӽڵ㡣����Ϊһ��С��0��ֵ�����ʾ�����ͽṹȫ��չ����
checkedid����ѡ��Ľڵ�ֵ����","�ָ���ַ�����
path��webӦ�õ����ƣ�
*/
function createTree1(arrName, startNode, openNode, checkedid, path) {
    nodes = arrName;
    //alert(nodes);
    if (path !== null) {
        rootPath = path;
    }
    if (nodes.length > 0) {
        document.write("<script language='JavaScript'>\n");
        document.write("var bCheck = false;\n");
        document.write("function checkAll() {\n");
        document.write("bCheck = !bCheck;\n");
        document.write("var ChkEls = document.getElementsByName('cid');\n");
        document.write("for (var i=0;i<ChkEls.length;i++) {\n");
        document.write("ChkEls.item(i).checked = bCheck;\n");
        document.write("}\n");
        document.write("}\n");
        document.write("</script>\n");
        document.write("<input type='button' value='ȫѡ' name='checkall' onclick=checkAll();><br>");
        checkedNodes = checkedid.split(",")
        //checkedNodes = checkedid;
        if (checkedNodes.length == 0) //�ж���û���Ѿ�ѡ�еĽڵ�
        {
            preloadIcons(path);
            if (startNode == null) startNode = 0;
            if (openNode != 0 || openNode != null) setOpenNodes(openNode);   //չ����һ��(����)�ڵ�

            if (openNode < 0) {   //չ�����нڵ�
                openAllNodes();
                //alert(openNode);
            }

            if (startNode != 0) {    //չ��������˾�����
                var nodeValues = nodes[getArrayId(startNode)].split("|");

//			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] +"</a><br />");
                document.write("<img src=\"" + path + "/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] + "<br />");
            } else {
                document.write("<img src=\"" + path + "/images/img/base.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> Eoms<br />");  //�������
            }
            //var recursedNodes = new Array();
            //addNode(startNode, recursedNodes);
        } else {
            preloadIcons(path);
            if (startNode == null) startNode = 0;
            if (openNode != 0 || openNode != null) setOpenNodes(openNode);   //չ����һ��(����)�ڵ�

            if (openNode < 0) {   //չ�����нڵ�
                openAllNodes();
                //alert(openNode);
            }


            if (startNode != 0) {    //չ��������˾�����
                var nodeValues = nodes[getArrayId(startNode)].split("|");

                for (i = 0; i < checkedNodes.length; i++) { //�����Ѿ�ѡ�еĽڵ�Ͳ�����Ľڵ�Ƚ�
                    //                          boolean isfind = false;
                    if (checkedNodes[i] == nodeValues[0]) {  //���Ѿ�ѡ�еĽڵ���ʾΪ��ѡ��
                        document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\"" + path + "/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick='' > " + nodeValues[2] + "</a><br />");
//                                    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] +"<br />");
                        break;
                    } else {
                        if (i == (checkedNodes.length - 1))
//                                    document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' disabled value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] +"</a><br />");
                            document.write("<img src=\"" + path + "/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='sid' disabled value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] + "<br />");
                    }
                }
            } else {
                for (i = 0; i < checkedNodes.length; i++) { //�����Ѿ�ѡ�еĽڵ�Ͳ�����Ľڵ�Ƚ�
                    if (checkedNodes[i] == nodeValues[0]) { //���Ѿ�ѡ�еĽڵ���ʾΪ��ѡ��
                        document.write("<img src=\"" + path + "/images/img/base.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> Eoms<br />");  //�������
                        break;
                    } else {
                        document.write("<img src=\"" + path + "/images/img/base.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='sid' value='" + nodeValues[0] + "' onClick='' disabled> Eoms<br />");  //�������
                    }
                }
            }
        }
        var recursedNodes = new Array();
        addNode(startNode, recursedNodes);
    }

}


// Returns the position of a node in the array
function getArrayId(node) {
    for (i = 0; i < nodes.length; i++) {
        var nodeValues = nodes[i].split("|");
        if (nodeValues[0] == node) return i;
    }
}

// Puts in array nodes that will be open
function setOpenNodes(openNode) {
    for (i = 0; i < nodes.length; i++) {
        var nodeValues = nodes[i].split("|");
        if (nodeValues[0] == openNode) {
            openNodes.push(nodeValues[0]);
            setOpenNodes(nodeValues[1]);
        }
    }
}

// open all nodes
function openAllNodes() {
    for (i = 0; i < nodes.length; i++) {
        var nodeValues = nodes[i].split("|");
        //alert(nodeValues[0]);
        //document.write(nodeValues[0]);
        openNodes.push(nodeValues[0]);
        //openAllNodes();
    }
}

// Checks if a node is open
function isNodeOpen(node) {
    for (i = 0; i < openNodes.length; i++)
        if (openNodes[i] == node) return true;
    return false;
}

// Checks if a node has any children
function hasChildNode(parentNode) {
    for (i = 0; i < nodes.length; i++) {
        var nodeValues = nodes[i].split("|");
        if (nodeValues[1] == parentNode) return true;
    }
    return false;
}

// Checks if a node is the last sibling
function lastSibling(node, parentNode) {
    var lastChild = 0;
    for (i = 0; i < nodes.length; i++) {
        var nodeValues = nodes[i].split("|");
        if (nodeValues[1] == parentNode)
            lastChild = nodeValues[0];
    }
    if (lastChild == node) return true;
    return false;
}

// Adds a new node in the tree
function addNode(parentNode, recursedNodes) {
    for (var i = 0; i < nodes.length; i++) {
        var nodeValues = nodes[i].split("|");

        if (nodeValues[1] == parentNode) {


            var ls = lastSibling(nodeValues[0], nodeValues[1]);
            var hcn = hasChildNode(nodeValues[0]);
            var ino = isNodeOpen(nodeValues[0]);

            // Write out line & empty icons
            for (g = 0; g < recursedNodes.length; g++) {
                if (recursedNodes[g] == 1) document.write("<img src=\"" + path + "/images/img/line.gif\" align=\"absbottom\" alt=\"\" />");
                else document.write("<img src=\"" + path + "/images/img/empty.gif\" align=\"absbottom\" alt=\"\" />");
            }
            //var cid = nodeValues[0];  // nodeValue[0] ����id���Ѳ���id��ֵ��var cid
            // put in array line & empty icons
            if (ls) recursedNodes.push(0);
            else recursedNodes.push(1);

            // Write out join icons
            if (hcn) {
                if (ls) {
                    document.write("<a href=\"javascript: oc(" + nodeValues[0] + ", 1);\"><img id=\"join" + nodeValues[0] + "\" src=\"" + path + "/images/img/");
                    if (ino) document.write("minus");
                    else document.write("plus");
                    document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
                } else {
                    document.write("<a href=\"javascript: oc(" + nodeValues[0] + ", 0);\"><img id=\"join" + nodeValues[0] + "\" src=\"" + path + "/images/img/");
                    if (ino) document.write("minus");
                    else document.write("plus");
                    document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
                }
            } else {
                if (ls) document.write("<img src=\"" + path + "/images/img/join.gif\" align=\"absbottom\" alt=\"\" />");
                else document.write("<img src=\"" + path + "/images/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
            }

            // Start link
//			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");
            //document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");
//alert(checkedNodes.length);
            if (checkedNodes.length == 0) {   //�ж���û���Ѿ�ѡ�еĽڵ�
                // Write out folder & page icons
                if (hcn) {
                    document.write("<img id=\"icon" + nodeValues[0] + "\" src=\"" + path + "/images/img/folder")
                    if (ino) document.write("open");
                    document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "'  onClick=''> ");
                } else {
                    document.write("<img id=\"icon" + nodeValues[0] + "\" src=\"" + path + "/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> ");
                }
            } else {
                for (k = 0; k < checkedNodes.length; k++) {   //�����Ѿ�ѡ�еĽڵ�Ͳ�����Ľڵ�Ƚ�
//alert(checkedNodes.length);
                    // Write out folder & page icons
                    if (hcn) {
                        if (checkedNodes[k] == nodeValues[0]) {//���Ѿ�ѡ�еĽڵ���ʾΪ��ѡ��
                            document.write("<img id=\"icon" + nodeValues[0] + "\" src=\"" + path + "/images/img/folder")
                            if (ino) document.write("open");

                            document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "'  onClick='' > ");
//                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "'  onClick=''> ");
                            break;
                        } else {
                            if (k == (checkedNodes.length - 1)) {
                                document.write("<img id=\"icon" + nodeValues[0] + "\" src=\"" + path + "/images/img/folder")
                                if (ino) document.write("open");

                                document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /> <input type='checkbox' name='sid' value='" + nodeValues[0] + "'  onClick='' disabled> ");
                            }

                        }
                    } else {
                        if (checkedNodes[k] == nodeValues[0]) {//���Ѿ�ѡ�еĽڵ���ʾΪ��ѡ��
                            document.write("<img id=\"icon" + nodeValues[0] + "\" src=\"" + path + "/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick='' > ");
//                                        document.write("<img id=\"icon" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> ");
                            break;
                        } else {
                            if (k == (checkedNodes.length - 1)) {
                                document.write("<img id=\"icon" + nodeValues[0] + "\" src=\"" + path + "/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input type='checkbox' name='sid' value='" + nodeValues[0] + "' onClick='' disabled> ");
                            }
                        }
                    }

                }

            }


            // Write out node name
            document.write(nodeValues[2]);
            //document.write(cid);   //�������id���

            // End link
//			document.write("</a><br />");
            document.write("<br />");

            // If node has children write out divs and go deeper
            if (hcn) {
                document.write("<div nowrap id=\"div" + nodeValues[0] + "\"");
                if (!ino) document.write(" style=\"display: none;\"");
                document.write(">");
                addNode(nodeValues[0], recursedNodes);
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
    var theJoin = document.getElementById("join" + node);
    var theIcon = document.getElementById("icon" + node);

    if (theDiv.style.display == 'none') {
        if (bottom == 1) theJoin.src = icons[3].src;
        else theJoin.src = icons[2].src;
        theIcon.src = icons[5].src;
        theDiv.style.display = '';
    } else {
        if (bottom == 1) theJoin.src = icons[1].src;
        else theJoin.src = icons[0].src;
        theIcon.src = icons[4].src;
        theDiv.style.display = 'none';
    }
}

// Push and pop not implemented in IE(crap!    don\uFFFDt know about NS though)
if (!Array.prototype.push) {
    function array_push() {
        for (var i = 0; i < arguments.length; i++)
            this[this.length] = arguments[i];
        return this.length;
    }

    Array.prototype.push = array_push;
}
if (!Array.prototype.pop) {
    function array_pop() {
        lastElement = this[this.length - 1];
        this.length = Math.max(this.length - 1, 0);
        return lastElement;
    }

    Array.prototype.pop = array_pop;
}

/*
�����¼ӵ�function
//arrName���ڵ���Ϣ���飻startNode���Ӵ˽ڵ㿪ʼ��ʾ���ţ�openNode��> 0���Ӵ˽ڵ�չ����< 0ȫ���򿪣�
//checkedid���ѱ�ѡ�еĽڵ㣻path��·����chkbxname ��checkbox��name
*/
function createTree11(arrName, startNode, openNode, checkedid, path, chkbxname) {
    nodes = arrName;
    //alert(nodes);
    if (path !== null) {
        rootPath = path;
    }
    if (nodes.length > 0) {
        document.write("<script language='JavaScript'>\n");
        document.write("var bCheck11 = false;\n");
        document.write("function checkAll11() {\n");
        document.write("bCheck11 = !bCheck11;\n");
        document.write("var ChkEls11 = document.getElementsByName('" + chkbxname + "');\n");
        document.write("for (var i=0;i<ChkEls11.length;i++) {\n");
        document.write("ChkEls11.item(i).checked = bCheck11;\n");
        document.write("}\n");
        document.write("}\n");
        document.write("</script>\n");
        document.write("<input type='button' value='ȫѡ' name='checkall11' onclick=checkAll11();><br>");
        checkedNodes = checkedid.split(",")
        //checkedNodes = checkedid;
        if (checkedNodes.length == 0) //�ж���û���Ѿ�ѡ�еĽڵ�
        {
            preloadIcons(path);
            if (startNode == null) startNode = 0;
            if (openNode != 0 || openNode != null) setOpenNodes(openNode);   //չ����һ��(����)�ڵ�

            if (openNode < 0) {   //չ�����нڵ�
                openAllNodes();
                //alert(openNode);
            }

            if (startNode != 0) {    //չ��������˾�����
                var nodeValues = nodes[getArrayId(startNode)].split("|");

//			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] +"</a><br />");
                document.write("<img src=\"" + path + "/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='" + chkbxname + "' value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] + "<br />");
            } else {
                document.write("<img src=\"" + path + "/images/img/base.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='" + chkbxname + "' value='" + nodeValues[0] + "' onClick=''> Eoms<br />");  //�������
            }
            //var recursedNodes = new Array();
            //addNode(startNode, recursedNodes);
        } else {
            preloadIcons(path);
            if (startNode == null) startNode = 0;
            if (openNode != 0 || openNode != null) setOpenNodes(openNode);   //չ����һ��(����)�ڵ�

            if (openNode < 0) {   //չ�����нڵ�
                openAllNodes();
                //alert(openNode);
            }

            if (startNode != 0) {    //չ��������˾�����
                var nodeValues = nodes[getArrayId(startNode)].split("|");

                for (i = 0; i < checkedNodes.length; i++) { //�����Ѿ�ѡ�еĽڵ�Ͳ�����Ľڵ�Ƚ�
                    //                          boolean isfind = false;
                    if (checkedNodes[i] == nodeValues[0]) {  //���Ѿ�ѡ�еĽڵ���ʾΪ��ѡ��
                        document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\"" + path + "/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='" + chkbxname + "' value='" + nodeValues[0] + "' onClick='' > " + nodeValues[2] + "</a><br />");
//                                    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] +"<br />");
                        break;
                    } else {
                        if (i == (checkedNodes.length - 1))
//                                    document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='cid' disabled value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] +"</a><br />");
                            document.write("<img src=\"" + path + "/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='sid' disabled value='" + nodeValues[0] + "' onClick=''> " + nodeValues[2] + "<br />");
                    }
                }
            } else {
                for (i = 0; i < checkedNodes.length; i++) { //�����Ѿ�ѡ�еĽڵ�Ͳ�����Ľڵ�Ƚ�
                    if (checkedNodes[i] == nodeValues[0]) { //���Ѿ�ѡ�еĽڵ���ʾΪ��ѡ��
                        document.write("<img src=\"" + path + "/images/img/base.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='" + chkbxname + "' value='" + nodeValues[0] + "' onClick=''> Eoms<br />");  //�������
                        break;
                    } else {
                        document.write("<img src=\"" + path + "/images/img/base.gif\" align=\"absbottom\" alt=\"\" /> <input type='checkbox' name='sid' value='" + nodeValues[0] + "' onClick='' disabled> Eoms<br />");  //�������
                    }
                }
            }
        }
        var recursedNodes = new Array();
        addNode11(startNode, recursedNodes, chkbxname);
    }

}

// Adds a new node in the tree
function addNode11(parentNode, recursedNodes, chkbxname) {
    for (var i = 0; i < nodes.length; i++) {
        var nodeValues = nodes[i].split("|");

        if (nodeValues[1] == parentNode) {


            var ls = lastSibling(nodeValues[0], nodeValues[1]);
            var hcn = hasChildNode(nodeValues[0]);
            var ino = isNodeOpen(nodeValues[0]);

            // Write out line & empty icons
            for (g = 0; g < recursedNodes.length; g++) {
                if (recursedNodes[g] == 1) document.write("<img src=\"" + path + "/images/img/line.gif\" align=\"absbottom\" alt=\"\" />");
                else document.write("<img src=\"" + path + "/images/img/empty.gif\" align=\"absbottom\" alt=\"\" />");
            }
            //var cid = nodeValues[0];  // nodeValue[0] ����id���Ѳ���id��ֵ��var cid
            // put in array line & empty icons
            if (ls) recursedNodes.push(0);
            else recursedNodes.push(1);

            // Write out join icons
            if (hcn) {
                if (ls) {
                    document.write("<a href=\"javascript: oc11(" + nodeValues[0] + ", 1);\"><img id=\"join11" + nodeValues[0] + "\" src=\"" + path + "/images/img/");
                    if (ino) document.write("minus");
                    else document.write("plus");
                    document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
                } else {
                    document.write("<a href=\"javascript: oc11(" + nodeValues[0] + ", 0);\"><img id=\"join11" + nodeValues[0] + "\" src=\"" + path + "/images/img/");
                    if (ino) document.write("minus");
                    else document.write("plus");
                    document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
                }
            } else {
                if (ls) document.write("<img src=\"" + path + "/images/img/join.gif\" align=\"absbottom\" alt=\"\" />");
                else document.write("<img src=\"" + path + "/images/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
            }

            // Start link
//			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");
            if (checkedNodes.length == 0) {   //�ж���û���Ѿ�ѡ�еĽڵ�
                // Write out folder & page icons
                if (hcn) {
                    document.write("<img id=\"icon11" + nodeValues[0] + "\" src=\"" + path + "/images/img/folder")
                    if (ino) document.write("open");
                    document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /> <input type='checkbox' name='" + chkbxname + "' value='" + nodeValues[0] + "'  onClick=''> ");
                } else {
                    document.write("<img id=\"icon11" + nodeValues[0] + "\" src=\"" + path + "/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input type='checkbox' name='" + chkbxname + "' value='" + nodeValues[0] + "' onClick=''> ");
                }
            } else {
                for (k = 0; k < checkedNodes.length; k++) {   //�����Ѿ�ѡ�еĽڵ�Ͳ�����Ľڵ�Ƚ�
                    // Write out folder & page icons
                    if (hcn) {
                        if (checkedNodes[k] == nodeValues[0]) {//���Ѿ�ѡ�еĽڵ���ʾΪ��ѡ��
                            document.write("<img id=\"icon11" + nodeValues[0] + "\" src=\"" + path + "/images/img/folder")
                            if (ino) document.write("open");

                            document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /> <input type='checkbox' name='" + chkbxname + "' value='" + nodeValues[0] + "'  onClick='' > ");
                            break;
                        } else {
                            if (k == (checkedNodes.length - 1)) {
                                document.write("<img id=\"icon11" + nodeValues[0] + "\" src=\"" + path + "/images/img/folder")
                                if (ino) document.write("open");

                                document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /> <input type='checkbox' name='sid' value='" + nodeValues[0] + "'  onClick='' disabled> ");
                            }

                        }
                    } else {
                        if (checkedNodes[k] == nodeValues[0]) {//���Ѿ�ѡ�еĽڵ���ʾΪ��ѡ��
                            document.write("<img id=\"icon11" + nodeValues[0] + "\" src=\"" + path + "/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input type='checkbox' name='" + chkbxname + "' value='" + nodeValues[0] + "' onClick='' > ");
                            break;
                        } else {
                            if (k == (checkedNodes.length - 1)) {
                                document.write("<img id=\"icon11" + nodeValues[0] + "\" src=\"" + path + "/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input type='checkbox' name='sid' value='" + nodeValues[0] + "' onClick='' disabled> ");
                            }
                        }
                    }

                }

            }


            // Write out node name
            document.write(nodeValues[2]);
            //document.write(cid);   //�������id���

            // End link
            document.write("<br />");

            // If node has children write out divs and go deeper
            if (hcn) {
                document.write("<div nowrap id=\"div11" + nodeValues[0] + "\"");
                if (!ino) document.write(" style=\"display: none;\"");
                document.write(">");
                addNode11(nodeValues[0], recursedNodes, chkbxname);
                document.write("</div>");
            }

            // remove last line or empty icon
            recursedNodes.pop();
        }
    }
}

// Opens or closes a node
function oc11(node, bottom) {
    var theDiv = document.getElementById("div11" + node);
    var theJoin = document.getElementById("join11" + node);
    var theIcon = document.getElementById("icon11" + node);

    if (theDiv.style.display == 'none') {
        if (bottom == 1) theJoin.src = icons[3].src;
        else theJoin.src = icons[2].src;
        theIcon.src = icons[5].src;
        theDiv.style.display = '';
    } else {
        if (bottom == 1) theJoin.src = icons[1].src;
        else theJoin.src = icons[0].src;
        theIcon.src = icons[4].src;
        theDiv.style.display = 'none';
    }
}

