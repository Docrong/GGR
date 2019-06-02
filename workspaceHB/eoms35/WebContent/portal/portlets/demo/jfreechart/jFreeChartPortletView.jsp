<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body>

<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<img src='<c:out value="${requestScope.path}"/>'/>
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
