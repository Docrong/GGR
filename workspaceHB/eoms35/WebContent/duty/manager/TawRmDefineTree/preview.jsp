<%@ page contentType="text/html; charset=gb2312"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<link href="../css/tab.css" rel="stylesheet" type="text/css">
<script src="../js/scripts.js"></script>
<script  src="../js/navigate.js"></script>
<script src="../js/foldtree.js"></script>
<link rel='StyleSheet' href='../css/foldtree.css' type='text/css'>
<style>
<!--
.skin0 { position:absolute; text-align:left; width:200px; border:2px solid black; background-color:menu; font-family:宋体; line-height:20px; cursor:default; visibility:hidden; }  .skin1 { cursor:default; font:menutext; position:absolute; text-align:left; font-family: 宋体, Helvetica, sans-serif; font-size: 9pt; width:120px; background-color:menu; border:1 solid buttonface; visibility:hidden; border:2 outset buttonhighlight; }  .menuitems { padding-left:15px; padding-right:10px; }
.p9 {  font-size: 9pt}
.mainsheet {position:absolute; left:4px; top:30px; width:expression(document.body.clientWidth-8); height:99%; z-index:1; overflow: auto;BACKGROUND-COLOR:#ff0000;;background:url("");background-attachment: fixed;background-repeat: no-repeat;background-position: right bottom;}
-->
</style>
<SCRIPT LANGUAGE="JavaScript">
<!--
if(window.operner)
   window.opener.close();
// -->
</SCRIPT>
</HEAD>
<%
String nodeId=(String)request.getAttribute("nodeId");
String nodeName=(String)request.getAttribute("Name");
String roomId=(String)request.getAttribute("roomId");
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
<BODY TEXT="000000" BGCOLOR="FFFFFF">
<FORM>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" height="100%">
  <tr>
   <td  colspan="3" height="28">
   <table width="100%" border="0" cellspacing="0" cellpadding="0" background="../images/shitu/main-top_02.gif" class="p9">
     <tr>
      <td width="11"><img src="../images/main-top_01.gif" width="14" height="33"></td>
      <td valign="middle" nowrap><img width="200" height="0"><br>&nbsp
         <a href=javascript:self.location="design.do?roomId=<%=roomId%>&nodeId=<%=nodeId%>&Name=<%=nodeName%>&cycle=<%=cycles%>&specility=<%=specility%>"><img src="../images/reply.gif" align="absmiddle" border="0">返回</a>
　　   </td>
      <td align="right" width="158"><img src="../images/main-top_03.gif" width="158" height="33"></td>
      </tr>
    </table>
   </td>
  </tr>
  <tr>
    <td bgcolor="#EFEFEF" width="1"><img width="1"></td>
    <td valign="top" width="2000">
    <div id="Layer1" class=mainsheet align=center style="position:relative; left:2px; top:0px; width:expression(document.body.clientWidth-2); height:100%; z-index:1; overflow: auto">
    <table width=99% border="1" cellspacing="1" cellpadding="1" align="center" style=font-size:9pt bordercolor="#3535FF">
    <tr bgcolor="#FFFFFF">
     <td width="10%" class="text-a" align="left">作业名称</td>
     <td colspan=3 align="left"><%=nodeName%></td>
    </tr>
    <tr bgcolor="#FFFFFF">
     <td width="10%" class="text-a" align="left">专业</td>
     <td width="40%" align="left"><%=specility%></td>
     <td width="10%" class="text-a" align="left">作业类型</td>
     <td width="40%" align="left"><%=getCycleName(cycles)%></td>
     </tr>
</table>
<br>
<table width=99% border="1" cellspacing="1" cellpadding="1" align="center" style=font-size:9pt bordercolor="#3535FF">
<%=request.getAttribute("strTable")%>
</table>
</div>
   </td>
   <td bgcolor="#EFEFEF" width="1"><img width="1"></td>
 </table></FORM>
</BODY>
</HTML>
