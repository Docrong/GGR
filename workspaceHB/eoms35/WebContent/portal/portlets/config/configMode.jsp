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
<table border='0' cellpadding='0' cellspacing='0' width="95%" >
<tr>
<td class='portlet-table-td-left' width="50%"><fmt:message key="portlet.label.title"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='pcTitle' value='<c:out value="${requestScope.portlet.title}"/>' class='portlet-form-input-field' size='16' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.titleBgColor"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='pcBarBgColor' value='<c:out value="${requestScope.portlet.barBgColor}"/>' class='portlet-form-input-field' size='10'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.titleColor"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='pcBarFontColor' value='<c:out value="${requestScope.portlet.barFontColor}"/>' class='portlet-form-input-field' size='10'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.contentBgColor"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='pcContentBgColor' value='<c:out value="${requestScope.portlet.contentBgColor}"/>' class='portlet-form-input-field' size='10'/>
</td>
</tr>
<tr>
<td class='cright' colspan='2'>
<input name='Submit' type='button' value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button'
 onclick="javascript:configPortlet('<%= responseId %>');" />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>