<%@ page language="java" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="../css/table_style.css" type="text/css">
<script type="text/javascript" src="../css/functions.js"></script>

<head>
  <title>作业计划模板组织结构新增</title>
</head>

<script language="javascript">

function SubmitCheck(){

  frmReg = document.tawwpgroupadd;

  if(frmReg.name.value == ''){
    alert("请输入作业计划组织结构内容名称");
    return false;
  }

  return true;
}

</script>

<%
String modelPlanId = request.getParameter("modelplanid");
String modelGroupId = request.getParameter("modelgroupid");

if(modelPlanId == null){
%>
<form name="tawwpgroupadd" method="post" action="../tawwpmodel/groupsave.do?modelgroupid=<%=modelGroupId%>" onsubmit='return SubmitCheck()'>
<%
}
else if(modelGroupId == null){
%>
<form name="tawwpgroupadd" method="post" action="../tawwpmodel/groupsave.do?modelplanid=<%=modelPlanId%>" onsubmit='return SubmitCheck()'>
<%
}
else{
%>
<form name="tawwpgroupadd" method="post" action="../tawwpmodel/groupsave.do?fathergroupid=<%=modelGroupId%>&modelplanid=<%=modelPlanId%>" onsubmit='return SubmitCheck()'>
<%
}
%>
  <table cellSpacing="1" cellPadding="1" width="500" align="center">
    <tr align="center" width="100%">
      <td align="center" class="table_title" >作业计划模板组织结构新增</td>
    </tr>
  </table>
  <table width="500" border="0"  cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
      <td class="td_label">
        组织结构名称
      </td>
      <td width="400">
        <input type="text" name="name" size="40" styleClass="clstext">
      </td>
    </tr>
    <tr class="tr_show">
      <td align="middle"  width="500"  class="clsthd2" colspan='2'>
        <input type="submit" value="保存" name="B1"  Class="clsbtn2">
        <input type="button" value="返回" onclick="javascript:window.history.back();" class="clsbtn2">
      </td>
    </tr>
  </table>
</form>



