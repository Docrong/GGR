<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body>
<%
String responseId = (String)request.getAttribute("responseId");
%>
<fmt:bundle basename="resourceBundle">
<form name="form_<%= responseId %>">
<table border='0' cellpadding='0' cellspacing='0' width='95%'>
<tr>
<td class='portlet-table-td-left'>*<fmt:message key="portlet.label.userId"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='plUserId' value='<c:out value="${requestScope.user.userId}"/>' disabled="true" class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>*<fmt:message key="portlet.label.userPassword"/>: </td>
<td class='portlet-table-td-left'>
<input type='password' name='plPassword' value='<c:out value="${requestScope.user.password}"/>' class='portlet-form-input-field' size='18' />	
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>*<fmt:message key="portlet.label.confirmPassword"/>:</td>
<td class='portlet-table-td-left'>
<input type='password' name='plConfirmPassword' value='<c:out value="${requestScope.user.password}"/>' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>*<fmt:message key="portlet.label.firstName"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='plFirstName' value='<c:out value="${requestScope.user.firstName}"/>' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>*<fmt:message key="portlet.label.lastName"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='plLastName' value='<c:out value="${requestScope.user.lastName}"/>' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.email"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='plEmail' value='<c:out value="${requestScope.user.email}"/>' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'></td>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='plShowLocale'  value='1'>Show Change Locale Box</input>
</td>
</tr>
</table>
<!-- 20070329 zhoucz quchu channal -->
<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-right'>
<input name='signup' type='button' value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button'
 	onclick="javascript:saveProfile('<%= responseId %>');" />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>