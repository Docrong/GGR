<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpModelGroupVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpModelGroupManageVO"%>

<link rel="stylesheet" href="../css/table_style.css" type="text/css">

<%
String modelPlanId = request.getParameter("modelplanid");
String modelGroupId = (String)request.getAttribute("modelgroupid");
%>

<script language="JavaScript">
<!--
function onModelGroupAdd()
{
<%
if(modelGroupId == null){
%>
  location.href="groupadd.do?modelplanid=<%=request.getParameter("modelplanid")%>";
<%
}
else{
%>
  location.href="groupadd.do?modelplanid=<%=request.getParameter("modelplanid")%>&modelgroupid=<%=modelGroupId%>";
<%
}
%>
}

function onModelGroupEdit()
{

  location.href="groupedit.do?modelplanid=<%=request.getParameter("modelplanid")%>&modelgroupid=<%=modelGroupId%>";
}

function onModelGroupRemove()
{
  location.href="groupremove.do?modelplanid=<%=request.getParameter("modelplanid")%>&modelgroupid=<%=modelGroupId%>";
}
--->
</script>

<%
TawwpModelGroupManageVO tawwpModelGroupManageVO = null;
tawwpModelGroupManageVO = (TawwpModelGroupManageVO)request.getAttribute("modelgroup");
Hashtable hashtabel = tawwpModelGroupManageVO.getAllHashtable();
TawwpModelGroupVO tawwpModelGroupVO = null;
%>


<table border="0" width="100%" >
  <tr>
    <td width="100%">
<%
if(tawwpModelGroupManageVO != null && hashtabel.size() >0){
%>

<script language='javascript'>
<!--
function expandChildren( sId ){
  var objSpan  = document.getElementById('id_item_'+sId);
  var objImg1  = document.getElementById('id_img1_'+sId);
  if( objSpan==null ) return;
  if( objSpan.style.display=='none' ){
    objSpan.style.display = 'inline';
    objImg1.src = '../images/tree--.gif';
  }
  else{
    objSpan.style.display = 'none';
    objImg1.src = '../images/tree2.gif';
  }
}
-->
</script>


<br>
  <a href="grouplist.do?modelplanid=<%=request.getParameter("modelplanid")%>">
  <img src='../images/icon-tree.gif' align=TEXTTOP>
  </a>
<br>
  <img src='../images/tree-v.gif' align=TEXTTOP>
<br>

<%
Enumeration enumeration = hashtabel.keys();
while(enumeration.hasMoreElements()){
  tawwpModelGroupVO = (TawwpModelGroupVO)enumeration.nextElement();
%>
<a class='blue' href="javascript:expandChildren('<%=tawwpModelGroupVO.getId()%>');">
  <img Id='id_img1_<%=tawwpModelGroupVO.getId()%>' src='../images/tree2.gif' align=TEXTTOP border='0'>
</a>
<a class='blue' href="javascript:expandChildren('<%=tawwpModelGroupVO.getId()%>');">
  <img Id='id_img2_<%=tawwpModelGroupVO.getId()%>' src='../images/icon-group2.gif' align=TEXTTOP border='0'>
</a>
<a id='id_groupHref_<%=tawwpModelGroupVO.getId()%>'class='blue' href="grouplist.do?modelplanid=<%=request.getParameter("modelplanid")%>&modelgroupid=<%=tawwpModelGroupVO.getId()%>">
<%=tawwpModelGroupVO.getName()%>
</a>

<%=tawwpModelGroupManageVO.printGroupTree(tawwpModelGroupVO,modelPlanId,0)%>
<br>
<%
}
%>

<script language='javascript'>
<!----
var objHref = document.getElementById('id_groupHref_<%=modelGroupId%>');
if( objHref!=null ) {
  objHref.style.color = '#ff0000';
  objHref.style.fontWeight = 'bolder';
}
--->
</script>
<%
}
else{
%>
目前没有组织结构信息
<%
}
%>
    </td>
  </tr>
</table>
<br>
<br>
<br>
<br>
<table >
  <tr>
    <td width="100%">
      <input type="button" value="增加" onclick="javascript:onModelGroupAdd();" class="clsbtn2">
      <%if(modelGroupId != null){%>
      <input type="button" value="删除" onclick="javascript:onModelGroupRemove();" class="clsbtn2">
      <input type="button" value="修改" onclick="javascript:onModelGroupEdit();" class="clsbtn2">
      <%}%>
      <input type="button" value="返回" onclick="javascript:window.history.back();" class="clsbtn2">
    </td>
  </tr>
</table>


