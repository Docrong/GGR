<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script language="javascript">
function newAction(){
	document.forms[0].action="<%=request.getContextPath()%>/workplan/tawwpreport/newnetreportquerylist.do";
	document.forms[0].submit();
}
</script>
<form name="tawwpreportnetform" method="post" action="<%=request.getContextPath()%>/workplan/tawwpreport/netreportquerylist.do">

<table class="formTable middle">
<caption>网元上报</caption> 
  <tr>
    <td class="label">
      网元变更
    </td>
    <td>
    <input  type="radio" name="netreport" value="1" checked="checked" />全年上报
    <input  type="radio" name="netreport" value="2" />新增上报
    <input  type="radio" name="netreport" value="3" />删除上报
    <input  type="radio" name="netreport" value="4" />补报请求
    </td>
  </tr>
  
  <tr>
    <td colSpan="2" >
      <input type="submit" value="查询" name="B1"  Class="button">
      <input type="button" value="新网元查询" name="B1"  Class="button"  onclick="newAction()">
      
    </td>
  </tr>
</table>
</form>




