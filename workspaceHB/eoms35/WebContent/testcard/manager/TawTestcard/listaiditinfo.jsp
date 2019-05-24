<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.testcard.util.StaticValue" %>
<html>
  <head>
    <title> 测试卡申请单</title>
     
  </head>
<%
String status = StaticMethod.nullObject2String(request.getAttribute("status"));
%>

<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
  <table width="95%" cellspacing="1" class="formTable">
<tr>
<td width="100%" colspan="14" class="label" align="center" height="25"><b>
申请单 列表
 </b> </td>
</tr>
<tr bgcolor="#FFFFFF">
		<td nowrap>审批人</td>
		<td nowrap>审批意见</td>
		<td nowrap>审批时间</td>
		<td nowrap>状态</td>
</tr>
<logic:iterate id="auditInfo" name="auditInfo" type="com.boco.eoms.testcard.model.TawTestcardAuditInfo">
<tr bgcolor="#FFFFFF">
	<td nowrap><bean:write name="auditInfo" property="auditUserName" scope="page"/></td>
	<td nowrap><bean:write name="auditInfo" property="auditInfo" scope="page"/></td>
	<td nowrap><bean:write name="auditInfo" property="auditTime" scope="page"/></td>
	<td nowrap><bean:write name="auditInfo" property="statusname" scope="page"/></td>
</tr>
    </logic:iterate>
</table>
</body>
</html>
