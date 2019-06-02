<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);

%>

<%
     String addonsid = request.getParameter("addonsid");
     String url = (String)request.getAttribute("url");
     String action = (String)request.getAttribute("action");
     String model = request.getParameter("model");
     String myid = request.getParameter("myid");
     String xmltype = (String)request.getAttribute("xmltype");
     String reaction = request.getParameter("reaction");
     String window = request.getParameter("window");
//显示XMLHtml
StringBuffer StrBuffer =(StringBuffer)request.getAttribute("strbuffer");
%>

<SCRIPT language=JavaScript src="../css/Validator.js">
</SCRIPT>

<style>
<!--配置右键style
.skin0 {
position:absolute;
text-align:left;
width:200px;
border:2px solid black;
background-color:menu;
font-family:Verdana;
line-height:20px;
cursor:default;
visibility:hidden;
}
.skin1 {
cursor:default;
font:menutext;
position:absolute;
text-align:left;
font-family: Arial, Helvetica, sans-serif;
font-size: 10pt;
width:120px;
background-color:menu;
border:1 solid buttonface;
visibility:hidden;
border:2 outset buttonhighlight;
}
.menuitems {
padding-left:15px;
padding-right:10px;
}
-->
</style>
<SCRIPT LANGUAGE="JavaScript1.2">
<!-- 右键删除记录
var myId=0;
function current(objDel)
{

        myId=objDel.id;
        myId++;

}

var menuskin = "skin1";
var display_url = 0;
function showmenuie5() {//显示菜单
if(myId!=null&&myId!="undefined"&&myId!=1&&myId!="1"&&myId!=""){
var rightedge = document.body.clientWidth-event.clientX;
var bottomedge = document.body.clientHeight-event.clientY;
if (rightedge < ie5menu.offsetWidth)
ie5menu.style.left = document.body.scrollLeft + event.clientX - ie5menu.offsetWidth;
else
ie5menu.style.left = document.body.scrollLeft + event.clientX;
if (bottomedge < ie5menu.offsetHeight)
ie5menu.style.top = document.body.scrollTop + event.clientY - ie5menu.offsetHeight;
else
ie5menu.style.top = document.body.scrollTop + event.clientY;

	ie5menu.style.visibility = "visible";
return false;
}
}
function hidemenuie5() {
ie5menu.style.visibility = "hidden";
}
function highlightie5() {
if (event.srcElement.className == "menuitems") {
event.srcElement.style.backgroundColor = "highlight";
event.srcElement.style.color = "white";
if (display_url)
window.status = event.srcElement.url;
   }
}
function lowlightie5() {
if (event.srcElement.className == "menuitems") {
event.srcElement.style.backgroundColor = "";
event.srcElement.style.color = "black";
window.status = "";
   }
}
function jumptoie5() {
   if(myId!=null&&myId!="undefined"&&myId!="1"&&myId!=""){
      if (confirm("<bean:message key="addonsread.title.warnDeleteElmt" />"+myId+"<bean:message key="addonsread.title.warnElmt" />")==true){
        //window.location = event.srcElement.url+"?emtermid="+myId+"&url=<%=url%>&action=<%=action%>&reaction=<%=reaction%>&model=<%=model%>&addonsid=<%=addonsid%>&myid=<%=myid%>&window=<%=window%>";
        window.location = "addonsdelem.do?emtermid="+myId+"&url=<%=url%>&action=<%=action%>&reaction=<%=reaction%>&model=<%=model%>&addonsid=<%=addonsid%>&myid=<%=myid%>&window=<%=window%>";
      }
   }else{
  	alert("<bean:message key="addonsread.title.warnCantDelete" />");
   }
}
//  End -->
</script>

<script language="JavaScript">
function onFile(obj,obj2)
{
	var objectName=obj.name;
	var objectValue=obj.value;
	var object2Name=obj2.name;
	var object2Value=obj2.value;
	var _sHeight = 200;
        var _sWidth = 420;
        var sTop=(window.screen.availHeight-_sHeight)/2;
        var sLeft=(window.screen.availWidth-_sWidth)/2;
        var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:Yes;help: No; resizable: No; status: No;";
        window.showModalDialog('../addons/fileupload.jsp?name='+ objectName+'&numname='+ object2Name+'&resulturl='+objectValue+'&action=<%=action%>',window,sFeatures);
}
function GoBack()
{
  window.history.back()
}
function Close()
{
  window.close()
}
</script>
</head>

<body>
  <base target="_self">
  <form name="addonssaveform" action="addonssave.do" method="post" onSubmit="return Validator.Validate(this,3)" >
<%=StrBuffer%>
<table class="table_show">

<input type="hidden" name="url" value="<%=url%>">
<input type="hidden" name="action" value="<%=action%>">
<input type="hidden" name="reaction" value="<%=reaction%>">
<input type="hidden" name="model" value="<%=model%>">
<input type="hidden" name="myid" value="<%=myid%>">
<input type="hidden" name="addonsid" value="<%=addonsid%>">
<input type="hidden" name="window" value="<%=window%>">
<tr>
  <td colspan="10">
    <% if(!action.equals("read")){%>
    <input type="submit" value="<bean:message key="addonsread.title.btnSubmit" />" name="B2" class="submit">
      <%}%>
      <% if(window.equals("new")){%>
    <input type="button" value="<bean:message key="addonsread.title.btnClose" />" name="B2" Onclick="Close();" class="button">
      <%}else{%>
    <input type="button" value="<bean:message key="addonsread.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">
      <%}%>
   </td>
</tr>

</table>
</form>
<%if(xmltype.equals("2") && !action.equals("read")){%>
<form name="addonsaddt2form" action="addonsaddt2.do" method="post">
<table class="table_show">
<input type="hidden" name="url" value="<%=url%>">
<input type="hidden" name="action" value="<%=action%>">
<input type="hidden" name="reaction" value="<%=reaction%>">
<input type="hidden" name="model" value="<%=model%>">
<input type="hidden" name="addonsid" value="<%=addonsid%>">
<input type="hidden" name="myid" value="<%=myid%>">
<input type="hidden" name="window" value="<%=window%>">
<tr>
  <td colspan="10">
    <input type="submit" value="<bean:message key="addonsread.title.btnAddRow" />" name="B2" class="submit">
   </td>
</tr>

</table>
</form>
<%}%>

<div id="ie5menu" class="skin0" onMouseover="highlightie5()" onMouseout="lowlightie5()" onClick="jumptoie5();">
<div class="menuitems" url="addonsdelem.do" height="30"><bean:message key="addonsread.title.labDeleteRow" /></div>
</div>

<script language="JavaScript1.2">

if (document.all && window.print) {
ie5menu.className = menuskin;
document.oncontextmenu = showmenuie5;
document.body.onclick = hidemenuie5;
}
</script>

<%@ include file="/common/footer_eoms.jsp"%>
