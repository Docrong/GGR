<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%
String openNode=(String)request.getAttribute("parentID");
String nodeId=(String)request.getAttribute("nodeId");
%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<script src="<%=request.getContextPath()%>/css/js/scripts.js"></script>
<script src="<%=request.getContextPath()%>/css/js/navigate.js"></script>
<script src="<%=request.getContextPath()%>/css/js/foldtree.js"></script>
<link rel='StyleSheet' href='<%=request.getContextPath()%>/css/js/foldtree.css' type='text/css'>

<style>
<!--
.skin0 { position:absolute; text-align:left; width:200px; border:2px solid black; background-color:menu; font-family:宋体; line-height:20px; cursor:default; visibility:hidden; }
.skin1 { cursor:default; font:menutext; position:absolute; text-align:left; font-family: 宋体, Helvetica, sans-serif; font-size: 9pt; width:120px; background-color:menu; border:1 solid buttonface; visibility:hidden; border:2 outset buttonhighlight; }
.menuitems { padding-left:15px; padding-right:10px; }
-->
 </style>
<SCRIPT LANGUAGE="JavaScript">
<!--
var DispMenu;

var menuskin = "skin1"; // skin0, or skin1
var display_url = 1; // Show URLs in status bar

function showmenu() {

  hidemenu()

  document.forms[0].ParentName.value = document.forms[0].OverName.value;
  document.forms[0].ParentNode.value = document.forms[0].OverNode.value;


var rightedge = document.body.clientWidth-event.clientX;
var bottomedge = document.body.clientHeight-event.clientY;
if (rightedge < DispMenu.offsetWidth)
DispMenu.style.left = document.body.scrollLeft + event.clientX - DispMenu.offsetWidth;
else
DispMenu.style.left = document.body.scrollLeft + event.clientX;
if (bottomedge < DispMenu.offsetHeight)
DispMenu.style.top = document.body.scrollTop + event.clientY - DispMenu.offsetHeight;
else
DispMenu.style.top = document.body.scrollTop + event.clientY;
DispMenu.style.visibility = "visible";
return false;
}

function hidemenu() {
  popuped = false;
  FormMenu.style.visibility = "hidden";
  FoldMenu.style.visibility = "hidden";
  ItemMenu.style.visibility = "hidden";
}
//click一个目录或叶子时解发的事件，把paretnode,parentname赋予overName,overNode
function setmenu (nodetype, parentname, parentnode) {
  if (nodetype == "Folder")
    DispMenu = FoldMenu;
  else
    DispMenu = ItemMenu;

  if (document.forms[0].OverName) {
    document.forms[0].OverName.value = parentname;
    document.forms[0].OverNode.value = parentnode;
   }
}

//回到空白页面
function clearmenu () {
  DispMenu = FormMenu;

  if (document.forms[0].OverName) {
    document.forms[0].OverName.value = "";
    document.forms[0].OverNode.value = "";
   }
}

function highlightitem() {
if (event.srcElement.className == "menuitems") {
event.srcElement.style.backgroundColor = "highlight";
event.srcElement.style.color = "white";
if (display_url)
window.status = event.srcElement.url;
}
}
function lowlightitem() {
if (event.srcElement.className == "menuitems") {
event.srcElement.style.backgroundColor = "";
event.srcElement.style.color = "black";
window.status = "";
}
}
function execitem() {
if (event.srcElement.className == "menuitems") {
if (event.srcElement.getAttribute("target") != null)
window.open(event.srcElement.url, event.srcElement.getAttribute("target"));
else
window.location = event.srcElement.url;
}
}

function getparent (node)
{
 for (i = 0; i < Tree.length; i ++) {
    var NodeValue = Tree[i].split("|");
    if (node == NodeValue[5]) {   // Get Current Node Index
      if (NodeValue[1] == "0")   // No Parent
        return ""
      else {
        var ParentValue = Tree[eval(NodeValue[1]) - 1].split("|");
        return ParentValue[5];
       }
     }
  }
}

function getNodeEntity(node)
{
 for (i = 0; i < Tree.length; i ++) {
    var NodeValue = Tree[i].split("|");
    if (node == NodeValue[5]) {   // Get Current Node Index
        return NodeValue;
       }
  }
}

function getOpenNode (node)
{
 for (i = 0; i < Tree.length; i ++) {
    var NodeValue = Tree[i].split("|");
    if (node == NodeValue[5]) {   // Get Current Node Index
       return NodeValue[0];
     }
  }
}

function getRootNode ()
{
 var rootNodes=",";
 for (i = 0; i < Tree.length; i ++) {
    var NodeValue = Tree[i].split("|");
    if (0 == NodeValue[1]) {   // Get Current Node Index
        rootNodes+=NodeValue[5]+",";
     }
  }
  return rootNodes;
}


function checkidentity (parent, value) {
 if (parent == "") {   // Node without Parent
   for (i = 0; i < Tree.length; i ++) {
     var NodeValue = Tree[i].split("|");

     if (NodeValue[1] == "0") {
       var NodeName = NodeValue[2].split("(");
       if (NodeName[0] == value)
         return true;
      }
    }
  }
 else {
   var NodeNum = 0;
   for (i = 0; i < Tree.length; i ++) {
      var NodeValue = Tree[i].split("|");
      if (NodeNum > 0) {
        if (NodeNum == NodeValue[1]) {
          var ItemName = NodeValue[2].split("(");
          if (ItemName[0] == value)
            return true;
         }
        else {  // Nomore Child Node
          if (NodeValue[1] < NodeNum)
            break;
         }
       }
      else {
        if (parent == NodeValue[5])
          NodeNum = i + 1;
       }
     }
  }

 return false;
}

function addfolder () {
  if (Tree.length >= 200) {
    alert ("目前仅支持" + 200 +"个节点, 请联系软件开发商增加节点上限!");
    return;
   }

  if(document.forms[0].ParentNode.value=="")
  {
    document.forms[0].ParentNode.value="<%=nodeId%>";
  }
     var foldname = prompt ("请输入作业目录名称(只能包含文字、字母、数字)","")
    if (foldname) {
    if (foldname.indexOf("|") != -1 || foldname.indexOf("'") != -1 || foldname.indexOf(";") != -1 || foldname.indexOf('"') != -1 || foldname.indexOf("\\") != -1)
    {
      alert ("请勿输入 | ' \" ; \\ 等字符!");
      return;
     }
    if (checkidentity(document.forms[0].ParentNode.value, foldname))
      alert ("已有相同的作业目录/项目");
    else {
      document.forms[0].QueryString.value =  "Name=" + foldname + "&Action=ADD&Type=Folder&Parent=" + document.forms[0].ParentNode.value;
      _doClick();
     }
    }
}

function additem () {
  if (Tree.length >= 200) {
    alert ("目前仅支持" + 200 +"个节点, 请联系软件开发商增加节点上限!");
    return;
   }

  var ItemData = showModalDialog("../manager/TawRmDefineTree/Item.jsp", "", "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px")

  if (ItemData) {
    if (checkidentity(document.forms[0].ParentNode.value, ItemData["ItemName"]))
      alert ("已有相同的作业目录/项目");
    else {
      if(document.forms[0].ParentNode.value=="")
        document.forms[0].ParentNode.value="<%=nodeId%>";
      document.forms[0].QueryString.value ="Type=Item&Action=ADD&Parent=" + document.forms[0].ParentNode.value + "&Name=" + ItemData["ItemName"] + "&Default=" + ItemData["DefaultValue"] + "&LineNum=" + ItemData["LineNum"];
      _doClick();
     }
   }
}

function delfolder () {
  var result = confirm ("您确认删除" + document.forms[0].ParentName.value + "及其下属作业项目?");

  if (result == true) {
    document.forms[0].QueryString.value =  "Node=" + document.forms[0].ParentNode.value+"&Action=DEL";
    _doClick();
   }
}

function modifyfolder () {
  /*
  var rootNodes=getRootNode();
  if(rootNodes.indexOf(document.forms[0].ParentNode.value)>-1)
  {
 var CurrentData = new Array();
 var ParentName = document.forms[0].ParentName.value;
 var ParentNode =document.forms[0].ParentNode.value;
 var ItemEntity=getNodeEntity(ParentNode);

  CurrentData["PlanName"] = ParentName;
  CurrentData["cycle"]=ItemEntity[6];

 var ItemData = showModalDialog("../manager/TawRmDefineTree/Folder.jsp", CurrentData, "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px")

  if (ItemData) {
    if (CurrentData["PlanName"] != ItemData["PlanName"]) {
      if (checkidentity(getparent(document.forms[0].ParentNode.value), ItemData["PlanName"])) {
        alert ("已有相同的项目");
        return;
       }
     }

      document.forms[0].QueryString.value ="Type=Folder&Action=MODI&Parent=" + document.forms[0].ParentNode.value + "&Name=" + ItemData["PlanName"] + "&cycle=" + ItemData["cycle"];
      _doClick();
   }

  }else{
  */
  var oldname = document.forms[0].ParentName.value;
  var foldname = prompt ("请输入作业目录名称(只能包含文字、字母、数字)",oldname)

  if (foldname) {
    if (foldname == oldname)
      return;
    if (checkidentity(getparent(document.forms[0].ParentNode.value), foldname))
      alert ("已有相同的作业目录");
    else {
      if (foldname.indexOf("|") != -1 || foldname.indexOf("'") != -1 || foldname.indexOf(";") != -1 || foldname.indexOf('"') != -1 || foldname.indexOf("\\") != -1)
      {
        alert ("请勿输入 | '  ; \\ 等字符!");
        return;
       }
      document.forms[0].QueryString.value ="Type=Folder&Parent=" + document.forms[0].ParentNode.value+"&Action=MODI&Name="+foldname;
      _doClick();
     }
   }
  //}
}

function modifyitem () {
 var ParentName = document.forms[0].ParentName.value;
 var ParentNode =document.forms[0].ParentNode.value;
 var ItemEntity=getNodeEntity(ParentNode);
 var CurrentData = new Array();
 CurrentData["ItemName"] = ParentName;
 CurrentData["DefaultValue"] =ItemEntity[6];
 CurrentData["LineNum"] =ItemEntity[7];
// alert(ItemEntity[7]);

 var ItemData = showModalDialog("../manager/TawRmDefineTree/Item.jsp", CurrentData, "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px");

  if (ItemData) {
    if (CurrentData["ItemName"] != ItemData["ItemName"]) {
      if (checkidentity(getparent(document.forms[0].ParentNode.value), ItemData["ItemName"])) {
        alert ("已有相同的项目");
        return;
       }
     }

      document.forms[0].QueryString.value ="Type=Item&Action=MODI&Parent=" + document.forms[0].ParentNode.value + "&Name=" + ItemData["ItemName"] + "&Default=" + ItemData["DefaultValue"] + "&LineNum=" + ItemData["LineNum"];
      _doClick();
   }
}

function delitem () {
  var result = confirm ("您确认删除" + document.forms[0].ParentName.value + "?");

  if (result == true) {
    document.forms[0].QueryString.value ="Node=" + document.forms[0].ParentNode.value+"&Action=DEL";
    _doClick();
   }
}
// -->
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
<!--
function _doClick() {
  var form = document.form1;
  if (form.onsubmit) {
     var retVal = form.onsubmit();
     if (typeof retVal == "boolean" && retVal == false)
       return false;
  }
  form.action+="?"+document.forms[0].QueryString.value+"&nodeId=<%=nodeId%>";
  form.submit();
  return false;
}
// -->
</SCRIPT>
</HEAD>
<BODY TEXT="000000" BGCOLOR="FFFFFF">

<FORM METHOD="POST" ACTION="save.do" NAME="form1">

<div id="FormMenu" class="skin1" onMouseover="highlightitem()" onMouseout="lowlightitem()"
onClick="execitem();">
<div class="menuitems" url="javascript:addfolder();">增加作业目录</div>
<hr>
<div class="menuitems" url="javascript:additem();">增加作业项目</div>
</div>
</div>

<div id="FoldMenu" class="skin1" onMouseover="highlightitem()" onMouseout="lowlightitem()"
onClick="execitem();">
<div class="menuitems" url="javascript:addfolder();">增加作业目录</div>
<hr>
<div class="menuitems" url="javascript:modifyfolder();">修改作业目录</div>
<hr>
<div class="menuitems" url="javascript:delfolder();">删除作业目录</div>
<hr>
<div class="menuitems" url="javascript:additem();">增加作业项目</div>
</div>


<div id="ItemMenu" class="skin1" onMouseover="highlightitem()" onMouseout="lowlightitem()"
onClick="execitem();">
<div class="menuitems" url="javascript:modifyitem();">修改作业项目</div>
<hr>
<div class="menuitems" url="javascript:delitem();">删除作业项目</div>
</div>


<script language="JavaScript1.2">
document.oncontextmenu = showmenu;
document.body.onclick = hidemenu;
FormMenu.className = menuskin;
FoldMenu.className = menuskin;
ItemMenu.className = menuskin;
DispMenu = FormMenu
<%
out.println(request.getAttribute("treeStr"));
System.out.println(request.getAttribute("treeStr"));
%>
</script>
<div id="tree" style=font-size:9pt>
<script type="text/javascript">
createTree(Tree,null,getOpenNode('<%=openNode%>'));
</script>
</div><BR>

<INPUT NAME="roomId" VALUE="<%=request.getAttribute("roomId")%>" STYLE="display:none">
<INPUT NAME="QueryString" VALUE="" STYLE="display:none">
<INPUT NAME="ParentName" VALUE="" STYLE="display:none">
<INPUT NAME="ParentNode" VALUE="" STYLE="display:none">
<INPUT NAME="OverName" VALUE="" STYLE="display:none">
<INPUT NAME="OverNode" VALUE="" STYLE="display:none"><BR>
</form>
</BODY>
</HTML>
