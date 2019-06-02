//div tree js start created by jerry
NS4 = (document.layers) ? 1 : 0;
IE4 = (document.all) ? 1 : 0;
ver4 = (NS4 || IE4) ? 1 : 0;
var path;

/*
功能说明：
   树型结构中checkbox框的onClick事件；支持单选；

使用举例：
EOMS_J2EE/sysadmin/manager/TawRmUser/entry.jsp;

参数说明：
temp：checkbox的id值;
temp3：点击的checkbox的value值;
tempId：点击checkbox后，隐藏保存id值的textfield名称
tempName：点击checkbox后，显示所选节点值的textfield名称
*/
       function selOnly(temp,temp3,tempId,tempName){
	   var i;
	   var flag= -100;
           var id = tempId.value;

          if(temp.length == null){

             if(temp.value == id){
                temp.checked = false;
                tempId.value=0;
                tempName.value="";
             }
             else{
               tempId.value=temp.value;
               tempName.value=temp3;
             }
          }
          else{
          if (temp.length>0){//zhujunxian add "if (){}else{}",2004-07-16,解决checkbox只有一个option的情况

	   for(i=0;i<temp.length;i++){
              if(temp[i].checked == true){
                 if(temp[i].value == id){
                   temp[i].checked = false;
                }
                else{
                   flag = i;
                }
             }
	   }

         if(flag == -100){
           tempId.value=0;
           tempName.value="";
         }
         else{
           tempId.value=temp[flag].value;
           tempName.value=temp3;
         }
       }else{  //zhujunxian add ,2004-07-16

	 if (temp.checked==true){
         	tempId.value=temp.value;
           	tempName.value=temp3;
         }
	}
          }
    }

/*
功能说明：
   树型结构中checkbox框的onClick事件；支持多选；

使用举例：
EOMS_J2EE/sysadmin/manager/TawRmUser/entry.jsp;

参数说明：
temp：checkbox的id值;
temp3：点击的checkbox的value值;
tempId：点击checkbox后，隐藏保存id值的textfield名称
tempName：点击checkbox后，显示所选节点值的textfield名称
*/
             function selMore(temp,temp3,tempId,tempName){
	   var i;
	   var flag= -100;
           var id = tempId.value;
           var names = "";
           var ids = "";
           var len = temp.length;


	//zhujunxian modify this section from "if(temp.length>0)" 2004-07-16,解决checkbox中，option只剩一个的时候的问题。
        //if(temp.length != null){//zhujunxian delete this clause,2004-07-16
	if (temp.length>0){ //zhujunxian add,2004-07-16

	   for(i=0;i<len;i++){
              if(temp[i].checked == true){
                //alert("i:"+i);
                // alert("temp[i].value:"+temp[i].value);
                // alert("fun"+getName(temp[i].value,temp));

              	 names = names  + getName(temp[i].value,temp) + " ";
             }
	   }
	}else{  //zhujunxian add, 2004-07-16

	   if (temp.checked==true)
 		names = names  + getName(temp.value,temp) + " ";
	}
           tempName.value=names;


       }

function getName(value,temp){
  var flag = -1;
  var name = "";
  var j =0;

  while((j<nodes.length) && (flag == -1)){
     var nodev = nodes[j].split("|");
     if(nodev[0] == value){
       name = nodev[2];
       flag = 1;
     }
     j = j + 1;
  }

  return name;
}

/*
功能说明：
div为Tree的树型结构的隐藏和显示；

使用举例：
EOMS_J2EE/sysadmin/manager/TawApparatusroom/entry.jsp;

参数说明：
num为0：表示显示树型结构;
num为1：表示隐藏树型结构;
*/
function headerDisplay(num)
{
      var headerBox, visible, hidden;

        if (document.layers)
	    {
	      visible = 'show';
		  hidden = 'hide';
		  headerBox = document.layers['tree'];
	    }
	    else if (document.all)
	    {
          visible = 'visible';
		  hidden = 'hidden';
		  headerBox = document.all('tree').style;
	    }

		if(num==1)
		{
		  headerBox.visibility = visible;
		}
		else
		{
		  headerBox.visibility = hidden;
		}
	  return;
}

/*
功能说明：
div为Tree2的树型结构的隐藏和显示；

使用举例：
EOMS_J2EE/sysadmin/manager/TawApparatusroom/entry.jsp;

参数说明：
num为0：表示显示树型结构;
num为1：表示隐藏树型结构;
*/
function headerDisplayTree2(num)
{
      var headerBox, visible, hidden;

        if (document.layers)
	    {
	      visible = 'show';
		  hidden = 'hide';
		  headerBox = document.layers['tree2'];
	    }
	    else if (document.all)
	    {
          visible = 'visible';
		  hidden = 'hidden';
		  headerBox = document.all('tree2').style;
	    }

		if(num==1)
		{
		  headerBox.visibility = visible;
		}
		else
		{
		  headerBox.visibility = hidden;
		}
	  return;
}

/*
功能说明：
div为Tree3的树型结构的隐藏和显示；

使用举例：
EOMS_J2EE/sysadmin/manager/TawApparatusroom/entry.jsp;

参数说明：
num为0：表示显示树型结构;
num为1：表示隐藏树型结构;
*/
function headerDisplayTree3(num)
{
      var headerBox, visible, hidden;

        if (document.layers)
	    {
	      visible = 'show';
		  hidden = 'hide';
		  headerBox = document.layers['tree3'];
	    }
	    else if (document.all)
	    {
          visible = 'visible';
		  hidden = 'hidden';
		  headerBox = document.all('tree3').style;
	    }

		if(num==1)
		{
		  headerBox.visibility = visible;
		}
		else
		{
		  headerBox.visibility = hidden;
		}
	  return;
}

function add_dept(dept_id,dept_name)
{
	var flag=document.formtree[dept_id].status;
	var sel_sprlen=document.form1.sel_receidept.options.length-1;
	var exist_flag=1;
      	var j=0;
      	if (flag)
      	{
      		for(j=0;j<=sel_sprlen;j++)
      		{
        		if(document.form1.sel_receidept.options[j].value==dept_id)
        		{
          			exist_flag=0;
          			break;
        		}
      		}

      		if(exist_flag)
      		{
        		var test1=new Option(dept_name);
        		test1.value=dept_id;
        		document.form1.sel_receidept.options[++sel_sprlen]=test1;
        	}
        }
        else
        {
      		for(j=0;j<=sel_sprlen;j++)
      		{
        		if(document.form1.sel_receidept.options[j].value==dept_id)
        		{
          			exist_flag=0;
          			break;
        		}
      		}

      		if(exist_flag==0)
      		{
			if(j!=-1)
    				document.form1.sel_receidept.options[j]=null;
        	}
        }
}


function getIndex(el) {
    ind = null;
    for (i=0; i<document.layers.length; i++) {
        whichEl = document.layers[i];
        if (whichEl.id == el) {
            ind = i;
            break;
        }
    }
    return ind;
}

function arrange() {
    nextY = document.layers[firstInd].pageY +document.layers[firstInd].document.height;
    for (i=firstInd+1; i<document.layers.length; i++) {
        whichEl = document.layers[i];
        if (whichEl.visibility != "hide") {
            whichEl.pageY = nextY;
            nextY += whichEl.document.height;
        }
    }
}

function initIt(){
    if (!ver4) return;
    if (NS4) {
        for (i=0; i<document.layers.length; i++) {
            whichEl = document.layers[i];
            if (whichEl.id.indexOf("Child") != -1) whichEl.visibility = "hide";
       }
        arrange();
    }
    else {
        divColl = document.all.tags("DIV");
        for (i=0; i<divColl.length; i++) {
            whichEl = divColl(i);
            if (whichEl.className == "child") whichEl.style.display = "none";
        }
    }
}

function expandIt(el) {
    if (!ver4) return;
    if (IE4) {
        whichEl = eval(el + "Child");
        if (whichEl.style.display == "none") {
            whichEl.style.display = "block";
        }
        else {
            whichEl.style.display = "none";
        }
    }
    else {
        whichEl = eval("document." + el + "Child");
        if (whichEl.visibility == "hide") {
            whichEl.visibility = "show";
        }
        else {
            whichEl.visibility = "hide";
        }
        arrange();
    }
}
//div trss js end


// Arrays for nodes and icons
var nodes		= new Array();;
var openNodes	= new Array();
var icons		= new Array(6);
var checkedNodes = new Array();
var checkedNo = new Array();
var checkedDp = new Array();
var formName = new Array();
var checkName;
var rootPath;
var path;

//var name;
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
有checkBox框的树型显示，可以显示可选节点和已选节点，其余节点都是不可选的；
在该类型的树图中，checkbox框支持多选。

使用举例：
EOMS_J2EE/sysadmin/manager/TawRole/tree.jsp;

参数说明：
arrName：Tree集合值，包含树型结构的各个节点的集合；
startNode：从该节点开始显示树型结构；
openNode：从该节点开始，展开其所有的子节点。若设为一个小于0的值，则表示：树型结构全部展开；
pth：web应用的名称；
checkedid：已选的节点，以","分割的字符串；
checkedno：不可选的节点，以","分割的字符串；
name：树型结构中checkbox框的name值,比如：window.tawApparatusroomForm.cid;
*/
function createTree2(arrName, startNode, openNode,pth,checkedid,checkedno,name) {
//checkedNodes = checkedid.split(",")
        path = pth;
	nodes = arrName;
        checkedNodes = checkedid;
        checkedNo = checkedno;

        formName = name.split(".");
        checkName = formName[2];

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
            checkedNo = checkedno.split(",");
        }
	//alert(nodes);
	if (nodes.length > 0) {
		preloadIcons();

		if (startNode == null) startNode = 0;
		//if (openNode != 0 || openNode != null) setOpenNodes(openNode);
                if (openNode > 0 || openNode !=null) setOpenNodes(openNode);      //从（openNode）此节点展开
                if (openNode < 0) {   //展开所有节点
                  openAllNodes();
                  //alert(openNode);
                }
		if (startNode !=0) {
                        var nodeValues = nodes[getArrayId(startNode)].split("|");
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' checked disabled>" + nodeValues[2] + "<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' disabled>" + nodeValues[2] + "<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' checked>" + nodeValues[2] + "<br />");
                        } else {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick=''>" + nodeValues[2] + "<br />");
                        }
		} else {
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' checked disabled> Eoms<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' disabled> Eoms<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' checked> Eoms<br />");
                        } else {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick=''> Eoms<br />");
                        }
		}
		var recursedNodes = new Array();
		addNode2(startNode, recursedNodes);
	}
}

// Adds a new node in the tree
function addNode2(parentNode, recursedNodes) {  //parentNode:startNodes,开始节点； resursedNodes:递归节点
	for (var i = 0; i < nodes.length; i++) {

		var nodeValues = nodes[i].split("|");

		if (nodeValues[1] == parentNode) {

			var ls	= lastSibling(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

                        var iss = isShowSelect(nodeValues[0],checkedNodes);  // Checks if a node has selected
                        var isro = isShowReadOnly(nodeValues[0],checkedNo);  //checks if a node is readonly
                        var srs = showRoSed(nodeValues[0],checkedNo,checkedNodes);  //readonly and selected
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
					document.write("<a href=\"javascript: oc2(" + nodeValues[0] + ", 1);\"><img id=\"join2" + nodeValues[0] + "\" src=\""+path+"/images/img/");
					 	if (ino) document.write("minus");
						else document.write("plus");
					document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				} else {
					document.write("<a href=\"javascript: oc2(" + nodeValues[0] + ", 0);\"><img id=\"join2" + nodeValues[0] + "\" src=\""+path+"/images/img/");
						if (ino) document.write("minus");
						else document.write("plus");
					document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				}
			} else {
				if (ls) document.write("<img src=\""+path+"/images/img/join.gif\" align=\"absbottom\" alt=\"\" />");
				else document.write("<img src=\""+path+"/images/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// Start link
			//document.write("<a href=\"" + nodeValues[3] + "\" target = rightFrame onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");

			// Write out folder & page icons
			if (hcn) {
				document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/folder")
                                if (srs) {  // Checks if a node has selected
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked disabled onClick=''>");
                                } else if (isro) {  // disable
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' disabled onClick=''>");
                                } else if (iss) {
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked  onClick=''>");
                                } else {
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "'  onClick=''>");
                                }
			} else {
                                if (srs) {  // Checks if a node has selected
                                    document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked disabled onClick=''> ");
                                } else if (isro) {
                                    document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' disabled onClick=''> ");
                                } else if (iss) {
                                    document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked onClick=''> ");
                                } else {
                                    document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick=''> ");
                                }
			}
			// Write out node name
			document.write(nodeValues[2]);

			// End link
			//document.write("</a><br />");
			document.write("<br />");
			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div nowrap id=\"div2" + nodeValues[0] + "\"");
					if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");

				addNode2(nodeValues[0], recursedNodes);

				document.write("</div>");
			}

			// remove last line or empty icon
			recursedNodes.pop();
		}
	}
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
// Opens or closes a node 1
function oc1(node, bottom) {

	var theDiv = document.getElementById("div1" + node);
	var theJoin	= document.getElementById("join1" + node);
	var theIcon = document.getElementById("icon1" + node);

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
有checkBox框的树型显示，可以显示可选节点和已选节点，其余节点都是不可选的；
在该类型的树图中，checkbox框支持多选。

使用举例：
EOMS_J2EE/sysadmin/manager/TawRole/tree.jsp;

参数说明：
arrName：Tree集合值，包含树型结构的各个节点的集合；
startNode：从该节点开始显示树型结构；
openNode：从该节点开始，展开其所有的子节点。若设为一个小于0的值，则表示：树型结构全部展开；
pth：web应用的名称；
checkedable：可选的节点，以","分割的字符串；
checkedid：已选的节点，以","分割的字符串；
name：树型结构中checkbox框的name值,比如：window.tawApparatusroomForm.cid;
*/
function createTree4(arrName,startNode,openNode,pth,checkedable,checkedid,name) {
//checkedNodes = checkedid.split(",")
	nodes = arrName;
        checkedNodes = checkedid;
        //checkedDp = checkedable;
        path = pth;
        formName = name.split(".");
        checkName = formName[2];

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

		if (startNode == null) startNode = 0;
		//if (openNode != 0 || openNode != null) setOpenNodes(openNode);
                if (openNode > 0 || openNode !=null) setOpenNodes(openNode);      //从（openNode）此节点展开
                if (openNode < 0) {   //展开所有节点
                  openAllNodes();
                  //alert(openNode);
                }
		if (startNode !=0) {
                        var nodeValues = nodes[getArrayId(startNode)].split("|");
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' checked>" + nodeValues[2] + "<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
                            document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' checked>" + nodeValues[2] + "<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick=''>" + nodeValues[2] + "<br />");
                        } else {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' disabled>" + nodeValues[2] + "<br />");
                        }
		} else {
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' checked > Eoms<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' checked> Eoms<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' > Eoms<br />");
                        } else {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' disabled> Eoms<br />");
                        }
		}

		var recursedNodes = new Array();
		addNode4(startNode, recursedNodes);
	}
}
// Adds a new node in the tree
function addNode4(parentNode, recursedNodes) {  //parentNode:startNodes,开始节点； resursedNodes:递归节点
	for (var i = 0; i < nodes.length; i++) {

		var nodeValues = nodes[i].split("|");

		if (nodeValues[1] == parentNode) {

			var ls	= lastSibling(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

                        var iss = isShowSelect(nodeValues[0],checkedNodes);  // Checks if a node has selected
                        var isro = isShowReadOnly(nodeValues[0],checkedNo);  //checks if a node is checkedable
                        var srs = showRoSed(nodeValues[0],checkedNo,checkedNodes);  //checkedable and selected
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
					document.write("<a href=\"javascript: oc2(" + nodeValues[0] + ", 1);\"><img id=\"join2" + nodeValues[0] + "\" src=\""+path+"/images/img/");
					 	if (ino) document.write("minus");
						else document.write("plus");
					document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				} else {
					document.write("<a href=\"javascript: oc2(" + nodeValues[0] + ", 0);\"><img id=\"join2" + nodeValues[0] + "\" src=\""+path+"/images/img/");
						if (ino) document.write("minus");
						else document.write("plus");
					document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				}
			} else {
				if (ls) document.write("<img src=\""+path+"/images/img/join.gif\" align=\"absbottom\" alt=\"\" />");
				else document.write("<img src=\""+path+"/images/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// Start link
			//document.write("<a href=\"" + nodeValues[3] + "\" target = rightFrame onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");

			// Write out folder & page icons
			if (hcn) {
				document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/folder")
                               if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {  // Checks if a node has selected
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked onClick=''>");
                                } else if (iss) {
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked  onClick=''>");
                                } else if (isro) {  // disable
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "'  onClick=''>");
                                } else {
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "'  onClick='' disabled>");
                                }
			} else {
                                if (srs) {  // Checks if a node has selected
                                    document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked  onClick=''> ");
                                } else if (iss) {
                                    document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked onClick=''> ");
                                } else if (isro) {
                                    document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "'  onClick=''> ");
                                } else {
                                    document.write("<img id=\"icon2" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick='' disabled> ");
                                }
			}
			// Write out node name
			document.write(nodeValues[2]);

			// End link
			//document.write("</a><br />");
			document.write("<br />");
			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div nowrap id=\"div2" + nodeValues[0] + "\"");
					if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");

				addNode4(nodeValues[0], recursedNodes);

				document.write("</div>");
			}

			// remove last line or empty icon
			recursedNodes.pop();
		}
	}
}

/*
功能说明：
有checkBox框的树型显示，可以显示已被选中节点、不可选节点和可选节点值。
在该类型的树图中，点击checkbox，触发名称为jsName的function，该function需要在页面中实现。

使用举例：
EOMS_J2EE/sysadmin/manager/TawApparatusroom/entry.jsp;

参数说明：
arrName：Tree集合值，包含树型结构的各个节点的集合；
startNode：从该节点开始显示树型结构；
openNode：从该节点开始，展开其所有的子节点。若设为一个小于0的值，则表示：树型结构全部展开；
path：web应用的名称；
checkedid：已被选中的节点，以","分割的字符串；
checkedno：不可选的节点，以","分割的字符串；
name：  树型结构中checkbox框的name值,比如：window.tawApparatusroomForm.cid;
jsName：点击树型结构中checkbox后，触发的js名称;
*/

function createTree9(arrName, startNode, openNode,path,checkedid,checkedno,
                     name,jsName,tempId,tempName,divName) {


        //checkedNodes = checkedid.split(",")
	nodes = arrName;
        checkedNodes = checkedid;
        checkedNo = checkedno;
        formName = name.split(".");
        checkName = formName[2];


        if (path !== null) {
            rootPath = path;
        }
        if (checkedNodes == null){
            checkedNodes = 0;
        } else {
            //checkedNodes = checkedid;
            checkedNodes = checkedid.split(",");
        }
        //alert(checkedNodes);
        if (checkedNo == null){
            checkedNo = 0;
        } else {
            //checkedNo = checkedno;
            checkedNo = checkedno.split(",");
        }
	//alert(nodes);
	if (nodes.length > 0) {
		preloadIcons();

		if (startNode == null) startNode = 0;
		//if (openNode != 0 || openNode != null) setOpenNodes(openNode);
                if (openNode > 0 || openNode !=null) setOpenNodes(openNode);      //从（openNode）此节点展开
                if (openNode < 0) {   //展开所有节点
                  openAllNodes();
                  //alert(openNode);
                }
		if (startNode !=0) {
                        var nodeValues = nodes[getArrayId(startNode)].split("|");
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {

                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox'  name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+"  checked disabled>" + nodeValues[2] + "<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {

                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox'  name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+"  disabled>" + nodeValues[2] + "<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {

                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox'  name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" checked>" + nodeValues[2] + "<br />");
                        } else {

                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox'  name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+">" + nodeValues[2] + "<br />");
                        }
		} else {
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {

                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" checked disabled> Eoms<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox'  name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" disabled> Eoms<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox'  name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" checked> Eoms<br />");
                        } else {
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox'  name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+"> Eoms<br />");
                        }
		}

		var recursedNodes = new Array();

		var ocname;
                var iconname;
                var joinname;

                if(divName == "tree"){
                  ocname = "oc";
                  iconname = "icon";
                  joinname = "join";
                  divName = "div";
                }

                if(divName == "tree2"){
                  ocname = "oc2";
                  iconname = "icon2";
                  joinname = "join2";
                  divName = "div2";
                }

		addNode9(startNode, recursedNodes,name,jsName,
                         tempId,tempName,checkName,
                         ocname,iconname,joinname,divName);
	}
}
// Adds a new node in the tree
//parentNode:startNodes,开始节点； resursedNodes:递归节点
function addNode9(parentNode, recursedNodes,name,jsName,
                  tempId,tempName,checkName,
                  ocname,iconname,joinname,divName) {
	for (var i = 0; i < nodes.length; i++) {

		var nodeValues = nodes[i].split("|");

		if (nodeValues[1] == parentNode) {

			var ls	= lastSibling(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

                        var iss = isShowSelect(nodeValues[0],checkedNodes);  // Checks if a node has selected
                        var isro = isShowReadOnly(nodeValues[0],checkedNo);  //checks if a node is readonly
                        var srs = showRoSed(nodeValues[0],checkedNo,checkedNodes);  //readonly and selected
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
					document.write("<a href=\"javascript: "+ocname+"(" + nodeValues[0] + ", 1);\"><img id=\""+joinname+ nodeValues[0] + "\" src=\""+path+"/images/img/");
					 	if (ino) document.write("minus");
						else document.write("plus");
					document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				} else {
					document.write("<a href=\"javascript: "+ocname+"(" + nodeValues[0] + ", 0);\"><img id=\""+joinname+ nodeValues[0] + "\" src=\""+path+"/images/img/");
						if (ino) document.write("minus");
						else document.write("plus");
					document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				}
			} else {
				if (ls) document.write("<img src=\""+path+"/images/img/join.gif\" align=\"absbottom\" alt=\"\" />");
				else document.write("<img src=\""+path+"/images/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// Start link
			//document.write("<a href=\"" + nodeValues[3] + "\" target = rightFrame onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");

			// Write out folder & page icons
			if (hcn) {
				document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/folder")
                                if (srs) {  // Checks if a node has selected
                                        if (ino) document.write("open");
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"'  value='" + nodeValues[0] + "' checked disabled onClick="+tep2+">");
                                } else if (isro) {  // disable
                                        if (ino) document.write("open");
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"'  value='" + nodeValues[0] + "' disabled onClick="+tep2+">");
                                } else if (iss) {
                                        if (ino) document.write("open");
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"'  value='" + nodeValues[0] + "' checked  onClick="+tep2+">");
                                } else {
                                        if (ino) document.write("open");
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox'  name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+">");
                                }
			} else {
                                if (srs) {  // Checks if a node has selected
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                    document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"'  value='" + nodeValues[0] + "' checked disabled onClick="+tep2+">");
                                } else if (isro) {
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                    document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"'   value='" + nodeValues[0] + "' disabled onClick="+tep2+">");
                                } else if (iss) {
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                    document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"'  value='" + nodeValues[0] + "' checked onClick="+tep2+">");
                                } else {
                                        var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                    document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"'  value='" + nodeValues[0] + "' onClick="+tep2+">");
                                }
			}
			// Write out node name
			document.write(nodeValues[2]);

			// End link
			//document.write("</a><br />");
			document.write("<br />");
			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div nowrap id=\""+divName + nodeValues[0] + "\"");
					if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");

				addNode9(nodeValues[0], recursedNodes,name,
                                         jsName,tempId,tempName,checkName,
                                         ocname,iconname,joinname,divName);

				document.write("</div>");
			}

			// remove last line or empty icon
			recursedNodes.pop();
		}
	}
}


/*
功能说明：
有checkBox框的树型显示，可以显示已被选中节点、不可选节点和可选节点值。
在该类型的树图中，点击checkbox，触发名称为jsName的function，该function需要在页面中实现。

使用举例：
EOMS_J2EE/sysadmin/manager/TawApparatusroom/entry.jsp;

参数说明：
arrName：Tree集合值，包含树型结构的各个节点的集合；
startNode：从该节点开始显示树型结构；
openNode：从该节点开始，展开其所有的子节点。若设为一个小于0的值，则表示：树型结构全部展开；
path：web应用的名称；
checkedable：可选的的节点，以","分割的字符串；
checkedid：已选的节点，以","分割的字符串；
name：树型结构中checkbox框的name值,比如：window.tawApparatusroomForm.cid;
jsName：点击树型结构中checkbox后，触发的js名称;
*/
function createTree10(arrName, startNode, openNode,path,checkedable,checkedid,
                      name,jsName,tempId,tempName,divName) {
//checkedNodes = checkedid.split(",")
	nodes = arrName;
        checkedNodes = checkedid;
        //checkedDp = checkedable;

        formName = name.split(".");
        checkName = formName[2];

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

		if (startNode == null) startNode = 0;
		//if (openNode != 0 || openNode != null) setOpenNodes(openNode);
                if (openNode > 0 || openNode !=null) setOpenNodes(openNode);      //从（openNode）此节点展开
                if (openNode < 0) {   //展开所有节点
                  openAllNodes();
                  //alert(openNode);
                }
		if (startNode !=0) {
                        var nodeValues = nodes[getArrayId(startNode)].split("|");
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" checked>" + nodeValues[2] + "<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" checked>" + nodeValues[2] + "<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+">" + nodeValues[2] + "<br />");
                        } else {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" disabled>" + nodeValues[2] + "<br />");
                        }
		} else {
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" checked > Eoms<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" checked> Eoms<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" > Eoms<br />");
                        } else {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+"  disabled> Eoms<br />");
                        }
		}

		var recursedNodes = new Array();

		var ocname;
                var iconname;
                var joinname;

                if(divName == "tree"){
                  ocname = "oc";
                  iconname = "icon";
                  joinname = "join";
                  divName = "div";
                }

                if(divName == "tree2"){
                  ocname = "oc2";
                  iconname = "icon2";
                  joinname = "join2";
                  divName = "div2";
                }

		addNode10(startNode, recursedNodes,name,
                          tempId,tempName,checkName,jsName,
                          ocname,iconname,joinname,divName);
	}
}

// Adds a new node in the tree
//parentNode:startNodes,开始节点； resursedNodes:递归节点
function addNode10(parentNode, recursedNodes,name,
                   tempId,tempName,checkName,jsName,
                   ocname,iconname,joinname,divName) {

	for (var i = 0; i < nodes.length; i++) {

		var nodeValues = nodes[i].split("|");

		if (nodeValues[1] == parentNode) {

			var ls	= lastSibling(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

                        var iss = isShowSelect(nodeValues[0],checkedNodes);  // Checks if a node has selected
                        var isro = isShowReadOnly(nodeValues[0],checkedNo);  //checks if a node is checkedable
                        var srs = showRoSed(nodeValues[0],checkedNo,checkedNodes);  //checkedable and selected
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
					document.write("<a href=\"javascript: "+ocname+"(" + nodeValues[0] + ", 1);\"><img id=\""+joinname+ nodeValues[0] + "\" src=\""+path+"/images/img/");
					 	if (ino) document.write("minus");
						else document.write("plus");
					document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				} else {
					document.write("<a href=\"javascript: "+ocname+"(" + nodeValues[0] + ", 0);\"><img id=\""+joinname+ nodeValues[0] + "\" src=\""+path+"/images/img/");
						if (ino) document.write("minus");
						else document.write("plus");
					document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				}
			} else {
				if (ls) document.write("<img src=\""+path+"/images/img/join.gif\" align=\"absbottom\" alt=\"\" />");
				else document.write("<img src=\""+path+"/images/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// Start link
			//document.write("<a href=\"" + nodeValues[3] + "\" target = rightFrame onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");

			// Write out folder & page icons
			if (hcn) {
				document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/folder")
                               if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {  // Checks if a node has selected
                                        if (ino) document.write("open");
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked  onClick="+tep2+">");
                                } else if (iss) {
                                        if (ino) document.write("open");
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' checked  onClick="+tep2+">");
                                } else if (isro) {  // disable
                                        if (ino) document.write("open");
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] +"' onClick="+tep2+">");
                                } else {
                                        if (ino) document.write("open");
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" disabled>");
                                }
			} else {
                                if (srs) {  // Checks if a node has selected
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                    document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+"  onClick=''> ");
                                } else if (iss) {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                    document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+"  onClick=''> ");
                                } else if (isro) {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                    document.write("<img id=\""+iconname+ nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+"> ");
                                } else {
                            var tep2="javascript:"+jsName+"(" +  name + "," +"\""+nodeValues[2]+"\""+","+tempId+","+tempName+");";
                                    document.write("<img id=\""+iconname + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkName+"' value='" + nodeValues[0] + "' onClick="+tep2+" disabled> ");
                                }
			}
			// Write out node name
			document.write(nodeValues[2]);

			// End link
			//document.write("</a><br />");
			document.write("<br />");
			// If node has children write out divs and go deeper
			if (hcn) {

				document.write("<div nowrap id=\""+divName + nodeValues[0] + "\"");
					if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");

				addNode10(nodeValues[0], recursedNodes,name,
                                          tempId,tempName,checkName,jsName,
                                          ocname,iconname,joinname,divName);

				document.write("</div>");
			}

			// remove last line or empty icon
			recursedNodes.pop();
		}
	}
}
//cheng 新增，封闭时期增加，后来去掉，由于时间急迫，所以再次增加
/*
* Create the tree21
* arrName：节点信息树组；startNode：从此节点开始显示部门；openNode：> 0，从此节点展开；< 0全部打开；
* checkedid：已被选中的节点；checkedno：不可选的节点；path：路径
* name：checkbox的名字
* 功能：实现复选树图，并且可以在同一个页面有多个树图
*/

function createTree21(arrName, startNode, openNode,path,checkedid,checkedno,checkname) {   //arrName：节点信息树组；startNode：从此节点开始显示部门；openNode：> 0，从此节点展开；< 0全部打开
//checkedNodes = checkedid.split(",")
	nodes = arrName;
        checkedNodes = checkedid;
        checkedNo = checkedno;
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
            checkedNo = checkedno.split(",");
        }
	//alert(nodes);
	if (nodes.length > 0) {
		preloadIcons();

		if (startNode == null) startNode = 0;
		//if (openNode != 0 || openNode != null) setOpenNodes(openNode);
                if (openNode > 0 || openNode !=null) setOpenNodes(openNode);      //从（openNode）此节点展开
                if (openNode < 0) {   //展开所有节点
                  openAllNodes();
                  //alert(openNode);
                }
		if (startNode !=0) {
                        var nodeValues = nodes[getArrayId(startNode)].split("|");
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick='' checked disabled>" + nodeValues[2] + "<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick='' disabled>" + nodeValues[2] + "<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick='' checked>" + nodeValues[2] + "<br />");
                        } else {
			    document.write("<img src=\""+path+"/images/img/folderopen.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick=''>" + nodeValues[2] + "<br />");
                        }

		} else {
                        if (showRoSed(nodeValues[0],checkedNo,checkedNodes)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick='' checked disabled> Eoms<br />");
                        } else if (isShowReadOnly(nodeValues[0],checkedNo)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick='' disabled> Eoms<br />");
                        } else if (isShowSelect(nodeValues[0],checkedNodes)) {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick='' checked> Eoms<br />");
                        } else {
                            document.write("<img src=\""+path+"/images/img/base.gif\" align=\"absbottom\" alt=\"\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick=''> Eoms<br />");
                        }
		}
		var recursedNodes = new Array();
		addNode21(startNode, recursedNodes,checkname);
	}
}
// Adds a new node in the tree
function addNode21(parentNode, recursedNodes,checkname) {  //parentNode:startNodes,开始节点； resursedNodes:递归节点
	for (var i = 0; i < nodes.length; i++) {

		var nodeValues = nodes[i].split("|");

		if (nodeValues[1] == parentNode) {

			var ls	= lastSibling(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

                        var iss = isShowSelect(nodeValues[0],checkedNodes);  // Checks if a node has selected
                        var isro = isShowReadOnly(nodeValues[0],checkedNo);  //checks if a node is readonly
                        var srs = showRoSed(nodeValues[0],checkedNo,checkedNodes);  //readonly and selected
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
					document.write("<a href=\"javascript: oc1(" + nodeValues[0] + ", 1);\"><img id=\"join1" + nodeValues[0] + "\" src=\""+path+"/images/img/");
					 	if (ino) document.write("minus");
						else document.write("plus");
					document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				} else {
					document.write("<a href=\"javascript: oc1(" + nodeValues[0] + ", 0);\"><img id=\"join1" + nodeValues[0] + "\" src=\""+path+"/images/img/");
						if (ino) document.write("minus");
						else document.write("plus");
					document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				}
			} else {
				if (ls) document.write("<img src=\""+path+"/images/img/join.gif\" align=\"absbottom\" alt=\"\" />");
				else document.write("<img src=\""+path+"/images/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// Start link
			//document.write("<a href=\"" + nodeValues[3] + "\" target = rightFrame onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");

			// Write out folder & page icons
			if (hcn) {
				document.write("<img id=\"icon1" + nodeValues[0] + "\" src=\""+path+"/images/img/folder")
                                if (srs) {  // Checks if a node has selected
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' checked disabled onClick=''>");
                                } else if (isro) {  // disable
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' disabled onClick=''>");
                                } else if (iss) {
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' checked  onClick=''>");
                                } else {
                                        if (ino) document.write("open");
                                        document.write(".gif\" align=\"absbottom\" alt=\"Folder\" /><input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "'  onClick=''>");
                                }
			} else {
                                if (srs) {  // Checks if a node has selected
                                    document.write("<img id=\"icon1" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' checked disabled onClick=''> ");
                                } else if (isro) {
                                    document.write("<img id=\"icon1" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' disabled onClick=''> ");
                                } else if (iss) {
                                    document.write("<img id=\"icon1" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' checked onClick=''> ");
                                } else {
                                    document.write("<img id=\"icon1" + nodeValues[0] + "\" src=\""+path+"/images/img/page.gif\" align=\"absbottom\" alt=\"Page\" /> <input  type='checkbox' name='"+checkname+"' value='" + nodeValues[0] + "' onClick=''> ");
                                }
			}
			// Write out node name
			document.write(nodeValues[2]);

			// End link
			//document.write("</a><br />");
			document.write("<br />");
			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div nowrap id=\"div1" + nodeValues[0] + "\"");
					if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");
				addNode21(nodeValues[0], recursedNodes,checkname);
				document.write("</div>");
			}

			// remove last line or empty icon
			recursedNodes.pop();
		}
//var xx = eval("document.all." + checkname);
//alert(xx.name);
//alert(xx[].length);
//alert("aa");
	}
}


