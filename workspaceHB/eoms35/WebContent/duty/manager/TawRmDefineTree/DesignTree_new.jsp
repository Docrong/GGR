<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%String openNode=(String)request.getAttribute("parentID");%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<!--
<script src="../../js/scripts.js"></script>
<script  src="../../js/navigate.js"></script>
<script src="../../js/foldtree.js"></script>
<link rel='StyleSheet' href='/css/foldtree.css' type='text/css'>
-->
<style>   <!-- .skin0 { position:absolute; text-align:left; width:200px; border:2px solid black; background-color:menu; font-family:����; line-height:20px; cursor:default; visibility:hidden; }  .skin1 { cursor:default; font:menutext; position:absolute; text-align:left; font-family: ����, Helvetica, sans-serif; font-size: 9pt; width:120px; background-color:menu; border:1 solid buttonface; visibility:hidden; border:2 outset buttonhighlight; }  .menuitems { padding-left:15px; padding-right:10px; } -->  </style>

<script src="<%=request.getContextPath()%>/css/js/scripts.js"></script>
<script src="<%=request.getContextPath()%>/css/js/navigate.js"></script>
<script src="<%=request.getContextPath()%>/css/js/foldtree.js"></script>
<link rel='StyleSheet' href='<%=request.getContextPath()%>/css/js/foldtree.css' type='text/css'>

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
    alert ("Ŀǰ��֧��" + 200 +"���ڵ�, ����ϵ������������ӽڵ�����!");
    return;
   }

  var foldname = prompt ("��������ҵĿ¼����(ֻ�ܰ������֡���ĸ������)","")

  if (foldname) {
    if (foldname.indexOf("|") != -1 || foldname.indexOf("'") != -1 || foldname.indexOf(";") != -1 || foldname.indexOf('"') != -1 || foldname.indexOf("\\") != -1) {
      alert ("�������� | ' \" ; \\ ���ַ�!");
      return;
     }

    if (checkidentity(document.forms[0].ParentNode.value, foldname))
      alert ("������ͬ����ҵĿ¼/��Ŀ");
    else {
	  /*
      document.forms[0].QueryString.value = QueryStr + "&Name=" + foldname + "&Type=Folder&Parent=" + document.forms[0].ParentNode.value+"&ComName="+document.all.ComName.value;
      document.forms[0].Add.click();
	  */
      document.forms[0].QueryString.value =  "Name=" + foldname + "&Action=ADD&Type=Folder&Parent=" + document.forms[0].ParentNode.value;
      _doClick();
     }
   }
}

function additem () {
  if (Tree.length >= 200) {
    alert ("Ŀǰ��֧��" + 200 +"���ڵ�, ����ϵ������������ӽڵ�����!");
    return;
   }
  /*
  var ItemData = showModalDialog("/" + CurDB + "/ItemSetting?OpenForm", "", "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px")
  */
    var ItemData = showModalDialog("../manager/TawRmDefineTree/Item.jsp", "", "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px")


  if (ItemData) {
    if (checkidentity(document.forms[0].ParentNode.value, ItemData["ItemName"]))
      alert ("������ͬ����ҵĿ¼/��Ŀ");
    else {
	  /*
      document.forms[0].QueryString.value = QueryStr + "&Type=Item&Parent=" + document.forms[0].ParentNode.value + "&Name=" + ItemData["ItemName"] + "&Default=" + ItemData["DefaultValue"] + "&LineNum=" + ItemData["LineNum"]+"&ComName="+document.all.ComName.value;
      document.forms[0].Add.click();
	  */
      document.forms[0].QueryString.value ="Type=Item&Action=ADD&Parent=" + document.forms[0].ParentNode.value + "&Name=" + ItemData["ItemName"] + "&Default=" + ItemData["DefaultValue"] + "&LineNum=" + ItemData["LineNum"];
      _doClick();

     }
   }
}

function delfolder () {
  var result = confirm ("��ȷ��ɾ��" + document.forms[0].ParentName.value + "����������ҵ��Ŀ?");

  if (result == true) {
    document.forms[0].QueryString.value = QueryStr + "&Node=" + document.forms[0].ParentNode.value+"&ComName="+document.all.ComName.value;
    document.forms[0].Delete.click();
   }
}

function modifyfolder () {
  var oldname = document.forms[0].ParentName.value;
  var foldname = prompt ("��������ҵĿ¼����(ֻ�ܰ������֡���ĸ������)",oldname)

  if (foldname) {
    if (foldname == oldname)
      return;

    if (checkidentity(getparent(document.forms[0].ParentNode.value), foldname))
      alert ("������ͬ����ҵĿ¼");
    else {
      if (foldname.indexOf("|") != -1 || foldname.indexOf("'") != -1 || foldname.indexOf(";") != -1 || foldname.indexOf('"') != -1 || foldname.indexOf("\\") != -1) {
        alert ("�������� | ' \" ; \\ ���ַ�!");
        return;
       }
       /*
      document.forms[0].QueryString.value = QueryStr + "&Node=" + document.forms[0].ParentNode.value + "&ItemName=" + foldname+"&ComName="+document.all.ComName.value;
      document.forms[0].Modify.click();
	  */
      document.forms[0].QueryString.value =  "Node=" + document.forms[0].ParentNode.value+"&Action=MODI&Name="+foldname;
      _doClick();
     }
   }
}

function modifyitem () {
 /*
 var ParentName = document.forms[0].ParentName.value;

 var CurrentData = new Array();
 CurrentData["ItemName"] = ParentName.substring(0,ParentName.indexOf("("))
 CurrentData["DefaultValue"] = ParentName.substring(ParentName.indexOf("(") + 1, ParentName.indexOf(","));
 CurrentData["LineNum"] = ParentName.substring(ParentName.indexOf(",") + 1, ParentName.indexOf(")"));
 */

 var ParentName = document.forms[0].ParentName.value;
 var ParentNode =document.forms[0].ParentNode.value;
 var ItemEntity=getNodeEntity(ParentNode);
 var CurrentData = new Array();
 CurrentData["ItemName"] = ParentName;
 CurrentData["DefaultValue"] =ItemEntity[6];
 CurrentData["LineNum"] =ItemEntity[7];
 /*
 var ItemData = showModalDialog("/" + CurDB + "/ItemSetting?OpenForm", CurrentData, "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px")
 */
  var ItemData = showModalDialog("../manager/TawRmDefineTree/Item.jsp", CurrentData, "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px");

  if (ItemData) {
    if (CurrentData["ItemName"] != ItemData["ItemName"]) {
      if (checkidentity(getparent(document.forms[0].ParentNode.value), ItemData["ItemName"])) {
        alert ("������ͬ����Ŀ");
        return;
       }
     }
     /*
    document.forms[0].QueryString.value = QueryStr + "&Node=" + document.forms[0].ParentNode.value + "&ItemName=" + ItemData["ItemName"] + "&Default=" + ItemData["DefaultValue"] + "&LineNum=" + ItemData["LineNum"]+"&ComName="+document.all.ComName.value;
    document.forms[0].Modify.click();
	*/
      document.forms[0].QueryString.value ="Type=Item&Action=MODI&Parent=" + document.forms[0].ParentNode.value + "&Name=" + ItemData["ItemName"] + "&Default=" + ItemData["DefaultValue"] + "&LineNum=" + ItemData["LineNum"];
      _doClick();
   }
}

function delitem () {
  var result = confirm ("��ȷ��ɾ��" + document.forms[0].ParentName.value + "?");

  if (result == true) {
    /*
    document.forms[0].QueryString.value = QueryStr + "&Node=" + document.forms[0].ParentNode.value+"&ComName="+document.all.ComName.value;
    document.forms[0].Delete.click();
	*/
    document.forms[0].QueryString.value ="Node=" + document.forms[0].ParentNode.value+"&Action=DEL";
    _doClick();
   }
}
// -->
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
<!--
/*
document._domino_target = "_self";
function _doClick(v, o, t, h) {
  var form = document._EmbededTaskContent;
  if (form.onsubmit) {
     var retVal = form.onsubmit();
     if (typeof retVal == "boolean" && retVal == false)
       return false;
  }
  var target = document._domino_target;
  if (o.href != null) {
    if (o.target != null)
       target = o.target;
  } else {
    if (t != null)
      target = t;
  }
  form.target = target;
  form.__Click.value = v;
  if (h != null)
    form.action += h;
  form.submit();
  return false;
  */
document._domino_target = "_self";
function _doClick() {
  var form = document.form1;
  if (form.onsubmit) {
     var retVal = form.onsubmit();
     if (typeof retVal == "boolean" && retVal == false)
       return false;
  }
  form.action+="?"+document.forms[0].QueryString.value;
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
<div class="menuitems" url="javascript:addfolder();">������ҵĿ¼</div>
<hr>
<div class="menuitems" url="javascript:additem();">������ҵ��Ŀ</div>
</div>
<div id="FoldMenu" class="skin1" onMouseover="highlightitem()" onMouseout="lowlightitem()"
onClick="execitem();">
<div class="menuitems" url="javascript:addfolder();">������ҵĿ¼</div>
<hr>
<div class="menuitems" url="javascript:modifyfolder();">�޸���ҵĿ¼</div>
<hr>
<div class="menuitems" url="javascript:delfolder();">ɾ����ҵĿ¼</div>
<hr>
<div class="menuitems" url="javascript:additem();">������ҵ��Ŀ</div>
</div>
<div id="ItemMenu" class="skin1" onMouseover="highlightitem()" onMouseout="lowlightitem()"
onClick="execitem();">
<div class="menuitems" url="javascript:modifyitem();">�޸���ҵ��Ŀ</div>
<hr>
<div class="menuitems" url="javascript:delitem();">ɾ����ҵ��Ŀ</div>
</div>
<script language="JavaScript1.2">
//document.oncontextmenu = showmenu;
document.body.onclick = hidemenu;
FormMenu.className = menuskin;
FoldMenu.className = menuskin;
ItemMenu.className = menuskin;
DispMenu = FormMenu
<%
out.println(request.getAttribute("treeStr"));
System.out.println(request.getAttribute("treeStr"));
%>

/*
Tree = new Array;
Tree[0]='1|0|ֵ����Ϣ|JavaScript:oc(1, 0);|Folder|59B8C65EBC745DD648256DFD00287199';
Tree[1]='2|1|ֵ��ʱ��|#|Folder|443CED6D16E9418748256E600024AA40';
Tree[2]='3|1|ֵ����Ա|JavaScript:oc(3, 1);|Folder|5E18622593162FD548256E600024AFE2';
Tree[3]='4|3|�������|#|Folder|9DBDB887FA4C1A0F48256E600024B969';
Tree[4]='5|3|��ó·����|#|Folder|B5CF75FEF24E74BF48256E600024C7B3';
Tree[5]='6|3|��������|#|Folder|757280E4C97E5CBA48256E600024E092';
Tree[6]='7|3|��ԭ·����|#|Folder|391948B5A3B4897248256EAC000EBD9A';
Tree[7]='8|0|����Ѳ��|JavaScript:oc(8, 0);|Folder|C029FE38C4295A5148256DFD0028966F';
Tree[8]='9|8|��������(,1)|#|Item|3563706FE2B12A6A48256DFF001325E9';
Tree[9]='10|8|�յ�״��(,1)|#|Item|F80A2D24A55E312348256DFF00133548';
Tree[10]='11|8|ʩ��״��(,1)|#|Item|698F092F53C30BF448256DFF00134595';
Tree[11]='12|8|������ʪ��(,1)|#|Item|D050BF19FF39BE6348256DFF0016D066';
Tree[12]='13|8|��������(,1)|#|Item|7FD84BCA9476D6D348256DFF001C451B';
Tree[13]='14|0|����ά��|JavaScript:oc(14, 0);|Folder|A22BE12970AE1EAD48256DFF0012B751';
Tree[14]='15|14|�ɼ��豸|JavaScript:oc(15, 14);|Folder|E1CFC07EA1707EC848256E04003090E3';
Tree[15]='16|15|���IDA�Ĺ���״̬(����,1)|#|Item|841C57262BA1363348256E040030B17E';
Tree[16]='17|15|���OCE�Ĺ���״̬(����,1)|#|Item|3861B568CD5663A148256E040030B8CD';
Tree[17]='18|14|����վ|JavaScript:oc(18, 14);|Folder|AAFC6A073A7FFDDF48256E0400309492';
Tree[18]='19|18|���ǰ�û��Ĺ���״̬(����,1)|#|Item|06352DB69655495B48256E040030CA4E';
Tree[19]='20|18|���ҵ��̨�Ĺ���״̬(����,1)|#|Item|357ECD196ED2756748256E040030D166';
Tree[20]='21|14|ͼ��ϵͳ|JavaScript:oc(21, 14);|Folder|415C539CB6072CFD48256E040030992A';
Tree[21]='22|21|���DTM���״̬(����,1)|#|Item|54F810EA0BA0C8E748256E040030DBF7';
Tree[22]='23|14|������|JavaScript:oc(23, 14);|Folder|3D33C95E61733DBE48256E040030A389';
Tree[23]='24|23|������ݿ����ݶε�ʣ��ռ�(,1)|#|Item|C1BFAAFE1223854348256E040030ECAB';
Tree[24]='25|23|���CPU����(,1)|#|Item|8D8085078D41D27D48256E040030F35A';
Tree[25]='26|23|���ݿ������û���|#|Folder|8DE5A4148188ADDC48256E140016C904';
Tree[26]='27|14|��������|#|Folder|5A8F670372B4283048256E040031089F';
Tree[27]='28|0|���ϼ�¼|JavaScript:oc(28, 0);|Folder|9B80162A1E02E83448256DFF0012C706';
Tree[28]='29|28|�����ɷ����(,1)|#|Item|B1B19883CA3A266948256DFF001691F6';
Tree[29]='30|28|ͨѶ״̬(,1)|#|Item|A9DFCA86DA5F8D5548256E00002416E0';
Tree[30]='31|28|�澯���ִ������|#|Folder|ECF9523636E4F17948256EBA000E1A7B';
Tree[31]='32|28|��������(OK,1)|#|Item|37D7CDAA0B7F065F48256EBA000E45EC';
Tree[32]='33|0|��ע|#|Folder|2C9100EDA0044D9D48256DFF0016BE4B';
*/
</script>
<div id="tree" style=font-size:9pt>
<script type="text/javascript">
//createTree(Tree);
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
