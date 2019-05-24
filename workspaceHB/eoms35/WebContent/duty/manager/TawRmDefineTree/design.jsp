<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<script src="../../js/scripts.js"></script>
<script  src="../../js/navigate.js"></script>
<script src="../../js/foldtree.js"></script>
<link rel='StyleSheet' href='../../css/foldtree.css' type='text/css'>
<%
String   nodeId=(String)request.getAttribute("nodeId");
String   nodeName=(String)request.getAttribute("Name");
String   roomId=(String)request.getAttribute("roomId");
String   specility=(String)request.getAttribute("specility");
String   cycles=(String)request.getAttribute("cycle");
%>
<%!
public String getCycleName(String cycle)
{
   String[] cycles={"workserial","day","week","month","quarter","halfyear","year"};
   String[] cyclesNames={"每班","每天","每周","每月","每季度","每半年","每年"};
   int i=0;
   for(i=0;i<cycles.length;i++)
   {
   if(cycle.equals(cycles[i]))
     break;
   }
   return cyclesNames[i];
}
%>
<style>
<!--
.skin0{ position:absolute; text-align:left; width:200px; border:2px solid black; background-color:menu; font-family:宋体; line-height:20px; cursor:default; visibility:hidden; }  .skin1 { cursor:default; font:menutext; position:absolute; text-align:left; font-family: 宋体, Helvetica, sans-serif; font-size: 9pt; width:120px; background-color:menu; border:1 solid buttonface; visibility:hidden; border:2 outset buttonhighlight; }  .menuitems { padding-left:15px; padding-right:10px; }
-->
</style>
<SCRIPT LANGUAGE="JavaScript">
<!--
function ModifyTask () {
  /*
  var CurrentData = new Array();
  CurrentData["TaskName"] = TaskName
  CurrentData["Specialty"] = Specialty
  CurrentData["TaskType"] = TaskType

  var ItemData = showModalDialog("/" + CurDB + "/EditDeptDutyTask?OpenForm", CurrentData, "status:no;resizable:no;help:no;dialogWidth:350px;dialogHeight:165px");

  if (ItemData) {
    document.forms[0].QueryString.value = QueryStr + "&TaskName=" + ItemData["TaskName"] + "&Specialty=" + ItemData["Specialty"] + "&TaskType=" + ItemData["TaskType"] + "&OldName=" + TaskName;
    document.forms[0].Modify.click();
   }
   */
 var CurrentData = new Array();
 var ParentName = "<%=nodeName%>";
 var ParentNode ="<%=nodeId%>";
 //var ItemEntity=getNodeEntity(ParentNode);

  CurrentData["PlanName"] = ParentName;
  CurrentData["cycle"]="<%=cycles%>";
  CurrentData["specility"]="<%=specility%>";

 var ItemData = showModalDialog("../manager/TawRmDefineTree/Folder.jsp", CurrentData, "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px")

  if (ItemData) {
      document.forms[0].QueryString.value ="Type=Folder&Action=MODI&Parent=" +ParentNode + "&Name=" + ItemData["PlanName"] + "&cycle=" + ItemData["cycle"]+"&specility="+ItemData["specility"];
      document.form1.action="save.do"
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
  form.action+="?"+document.forms[0].QueryString.value+"&nodeId=<%=nodeId%>&roomId=<%=roomId%>";
  form.submit();
  return false;
}
// -->
</SCRIPT>
</HEAD>
<BODY TEXT="000000" BGCOLOR="FFFFFF" topmargin=0 leftmargin=0 bgColor=#ffffff>

<FORM METHOD=post ACTION="" NAME="form1">
<INPUT NAME="QueryString" VALUE="" STYLE="display:none">
<table width="100%" border="0" cellspacing="0" cellpadding="0" background="../../images/main-top_02.gif" style=font-size:9pt class="p9">
     <tr>
      <td width="11"><img src="../../images/main-top_01.gif" width="14" height="33"></td>
      <td valign="middle" nowrap><img width="200" height="0"><br>&nbsp
         <a href=javascript:self.location="listview.do?roomId=<%=request.getParameter("roomId")%>">
         <img src="../../images/gd.gif" align="absmiddle" border="0">值班作业列表</a>
         <a href=javascript:self.location="preview.do?roomId=<%=roomId%>&parent_id=<%=nodeId%>&Action=View&Name=<%=nodeName%>&specility=<%=specility%>&cycle=<%=cycles%>">
         <img src="../../images/previewon.gif" align="absmiddle" border="0">预览</a>
         <a href=javascript:ModifyTask()>
         <img src="../../images/write.gif" align="absmiddle" border="0">修改值班作业</a>
　　   </td>
      <td align="right" width="158"><img src="../../images/main-top_03.gif" width="158" height="33"></td>
     </tr>
    </table>
<table width=99% border="1" cellspacing="1" cellpadding="1" align="center" style=font-size:9pt bordercolor="#3535FF">
<tr bgcolor="#FFFFFF">
     <td width="10%" class="text-a" align="left">作业名称</td>
     <td colspan="3" align="left"><%=nodeName%></td>
</tr>
<tr bgcolor="#FFFFFF">
     <td width="10%" class="text-a" align="left">专业</td>
     <td width="40%" align="left"><%=specility%></td>
     <td width="10%" class="text-a" align="left">作业类型</td>
     <td width="40%" align="left"><%=getCycleName(cycles)%></td>
</tr>
<tr>
  <td colspan="4" height="400">
    <IFRAME frameBorder=0 height="100%" name=pcarttop scrolling=yes
    src='DesignTree.do?nodeId=<%=nodeId%>&roomId=<%=roomId%>' width="100%">
    </IFRAME>
  </td>
</tr>
</table>
</FORM>
</BODY>
</HTML>
