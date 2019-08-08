<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="activeUsers.title"/></title>
    <content tag="heading"><fmt:message key="activeUsers.heading"/></content>
    <meta name="menu" content="AdminMenu"/>
</head>
<body id="activeUsers"/>

<p><fmt:message key="activeUsers.message"/></p>

<div class="separator"></div>

<input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
       value="<fmt:message key="button.done"/>"/>

<display:table name="applicationScope.userNames" id="user" cellspacing="0" cellpadding="0"
               defaultsort="1" class="table" pagesize="50" requestURI="">

    <display:column property="username" escapeXml="true" style="width: 30%" titleKey="userForm.username"
                    sortable="true"/>
    <display:column titleKey="activeUsers.fullName" sortable="true">
        <c:out value="${user.firstName} ${user.lastName}" escapeXml="true"/>
        <c:if test="${not empty user.email}">
            <a href="mailto:${user.email}">
                <html:img pageKey="icon.email.img"
                          altKey="icon.email" styleClass="icon"/></a>
        </c:if>
    </display:column>

    <display:setProperty name="paging.banner.item_name" value="user"/>
    <display:setProperty name="paging.banner.items_name" value="users"/>
</display:table>
