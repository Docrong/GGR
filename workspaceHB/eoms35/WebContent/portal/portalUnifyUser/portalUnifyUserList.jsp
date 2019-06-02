<%@ include file="/portal/common/taglibs-portal.jsp"%>
<%@ include file="/portal/common/header_portal.jsp"%>

<content tag="heading"><bean:message key="portalUnifyUserList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/portal/editPortalUnifyUser.html"/>'"
        value="<bean:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<bean:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="portalUnifyUserList" cellspacing="0" cellpadding="0"
    id="portalUnifyUserList" pagesize="25" class="table portalUnifyUserList"
    export="true" requestURI="" sort="list">

    <display:column property="passwd" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.passwd"/>

    <display:column property="relateuserid" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.relateuserid"/>

    <display:column property="sysid" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.sysid"/>

    <display:column property="useremail" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.useremail"/>

    <display:column property="userfield" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.userfield"/>

    <display:column property="userid" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.userid"/>

    <display:column property="usermobile" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.usermobile"/>

    <display:column property="username" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.username"/>

    <display:column property="usertel" sortable="true" headerClass="sortable"
        url="/portal/editPortalUnifyUser.html" paramId="id" paramProperty="id"
         titleKey="portalUnifyUserForm.usertel"/>

    <display:setProperty name="paging.banner.item_name" value="portalUnifyUser"/>
    <display:setProperty name="paging.banner.items_name" value="portalUnifyUsers"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("portalUnifyUserList");
</script>

<%@ include file="/portal/common/footer_portal.jsp"%>