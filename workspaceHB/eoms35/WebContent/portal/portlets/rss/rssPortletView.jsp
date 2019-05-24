<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body> 
<c:if test='${requestScope.rssLists != null}'>
<c:forEach var="item" items="${requestScope.rssLists}" >
<span class="portlet-rss" 
   onmouseover="javascript:showRssDesc(event,'<c:out value="${item.index}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideRssDesc();">
<a href='<c:out value="${item.link}"/>' target='blank' ><c:out value="${item.title}"/></a>
</span>
</c:forEach>
</c:if>
<c:if test='${requestScope.error != null}'>
<span class="portlet">
<c:out value="${requestScope.error}"/>
</span>
</c:if>
</body>
</html>