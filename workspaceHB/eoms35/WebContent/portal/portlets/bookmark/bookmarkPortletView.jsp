<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body>
<form>
<table border='0' cellpadding='0' cellspacing='0'>
<c:forEach var="bookmark" items="${requestScope.bookmarks}" >
<tr>
<td class='portlet-table-td-left'>
<a href='<c:out value="${bookmark.value}"/>' target='_blank'><c:out value="${bookmark.name}"/></a>
</td>
</tr>
</c:forEach>
</table>
</form>
</body>
</html>