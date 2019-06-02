<%@ include file="/portal/common/taglibs-portal.jsp"%>
<%@ include file="/portal/common/header_portal.jsp"%>

<content tag="heading"><bean:message  key="portalSysManageList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/portal/editPortalSysManage.html"/>'"
        value="<bean:message  key="button.add"/>"/>
        
    <input type="button" onclick="location.href='<%= request.getContextPath() %>/portal/portal.jsp'"
        value="<bean:message  key="button.cancel"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="portalSysManageList" cellspacing="0" cellpadding="0"
    id="portalSysManageList" pagesize="25" class="table portalSysManageList"
    export="true" requestURI="" sort="list">

    <display:column property="sysid" sortable="true" headerClass="sortable"
        url="/portal/editPortalSysManage.html" paramId="id" paramProperty="id"
         titleKey="portalSysManageForm.sysid"/>

    <display:column property="sysname" sortable="true" headerClass="sortable"
        url="/portal/editPortalSysManage.html" paramId="id" paramProperty="id"
         titleKey="portalSysManageForm.sysname"/>

    <display:column property="sysurl" sortable="true" headerClass="sortable"
        url="/portal/editPortalSysManage.html" paramId="id" paramProperty="id"
         titleKey="portalSysManageForm.sysurl"/>

    <display:setProperty name="paging.banner.item_name" value="portalSysManage"/>
    <display:setProperty name="paging.banner.items_name" value="portalSysManages"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("portalSysManageList");
</script>

<%@ include file="/portal/common/footer_portal.jsp"%>