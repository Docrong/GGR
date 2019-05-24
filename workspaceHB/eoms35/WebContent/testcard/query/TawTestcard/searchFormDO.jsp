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
    <td width="100%" colspan="14" height="25" class="label" align="center">
      <bean:write name="pagerHeader" scope="request" filter="false"/>
    </td>
</tr>
<tr bgcolor="#FFFFFF">
		<td nowrap>主题</td>
		<td nowrap>测试卡类型</td>
		<td nowrap>测试卡套餐</td>
		<td nowrap>所属分公司</td>
		<td nowrap>申请人</td>
		<td nowrap>申请时间</td>
        <td nowrap>状态</td>
        <%if(status.equals(StaticValue.STATUS_DRAFT)){%>
		<td nowrap colspan="2">操作</td>
        <%}%>
       <%if(status.equals(StaticValue.STATUS_REJECT)){ %>
		<td nowrap colspan="3">操作</td>
        <%}%>
        <%if(status.equals(StaticValue.STATUS_WAIT)){%>
		<td nowrap>操作</td>
        <%}%>
</tr>
<logic:iterate id="applyForm" name="applyForm" type="com.boco.eoms.testcard.model.TawTestcardApply">
<tr bgcolor="#FFFFFF">
	<td nowrap><a href="view.do?id=<%=applyForm.getId()%>"><bean:write name="applyForm" property="formName" scope="page"/></td>
	<td nowrap><bean:write name="applyForm" property="cardtypename" scope="page"/></td>
	<td nowrap><bean:write name="applyForm" property="cardpackagename" scope="page"/></td>
	<td nowrap><bean:write name="applyForm" property="leaveidname" scope="page"/></td>
	<td nowrap><bean:write name="applyForm" property="username" scope="page"/></td>
	<td nowrap><bean:write name="applyForm" property="insertTime" scope="page"/></td>
   	<td nowrap><bean:write name="applyForm" property="statusname" scope="page"/></td>
         <%if(status.equals(StaticValue.STATUS_DRAFT)){%>
        <td nowrap><a href="edit.do?id=<%=applyForm.getId()%>">修改</td>
        <td nowrap><a href="del.do?id=<%=applyForm.getId()%>">删除</td>
        <%}%>
        <%if(status.equals(StaticValue.STATUS_REJECT)){ %>
        <td nowrap><a href="edit.do?id=<%=applyForm.getId()%>">修改</td>
        <td nowrap><a href="del.do?id=<%=applyForm.getId()%>">删除</td>
        <td nowrap><a href="listauditinfo.do?id=<%=applyForm.getId()%>">查看审批意见</td>
        <%}%>
        <%if(status.equals(StaticValue.STATUS_WAIT)){%>
        <td nowrap><a href="auditpage.do?id=<%=applyForm.getId()%>&taskId=<%=applyForm.getTaskId()%>">审批</td>
        <%}%>
</tr>
    </logic:iterate>
</table>
</form>
</body>
</div>
</html>
