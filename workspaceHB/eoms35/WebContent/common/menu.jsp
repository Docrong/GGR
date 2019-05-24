<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="welcomePage" value="/welcome.jsp" />
<c:forEach var='menu' items='${sessionScope.menu}'><c:if test="${fn:startsWith(menu.url, ':')}"><c:set target="${menu}" property="url" value="${scheme}://${serverName}${menu.url}" /></c:if></c:forEach>
<c:if test="${!empty sessionScope.menu[0]}"><c:set var="welcomePage" value="${sessionScope.menu[0].url}" /></c:if>
<ul id="menu">
  <c:forEach var='menu' items='${sessionScope.menu}' varStatus="status">
    <li><a id="mi-${menu.itemId}" href="<c:url value='${menu.url}' />" target="mainFrame" ${status.first?"class=cur":""}><span>${menu.text}</span></a></li>
  </c:forEach>
</ul>