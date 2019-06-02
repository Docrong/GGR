<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemOrganizationProxyList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawSystemOrganizationProxy.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemOrganizationProxyList" cellspacing="0" cellpadding="0"
    id="tawSystemOrganizationProxyList" pagesize="25" class="table tawSystemOrganizationProxyList"
    export="true" requestURI="/tawSystemOrganizationProxys.do" sort="external" partialList="true" size="resultSize">

    <display:column property="deleted" sortable="true" headerClass="sortable"
         titleKey="tawSystemOrganizationProxyForm.deleted"/>

    <display:column property="endTime" sortable="true" headerClass="sortable"
         titleKey="tawSystemOrganizationProxyForm.endTime"/>

    <display:column property="fromUserId" sortable="true" headerClass="sortable"
         titleKey="tawSystemOrganizationProxyForm.fromUserId"/>

    <display:column property="proxyPostId" sortable="true" headerClass="sortable"
         titleKey="tawSystemOrganizationProxyForm.proxyPostId"/>

    <display:column property="startTime" sortable="true" headerClass="sortable"
         titleKey="tawSystemOrganizationProxyForm.startTime"/>

    <display:column property="toUserId" sortable="true" headerClass="sortable"
         titleKey="tawSystemOrganizationProxyForm.toUserId"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemOrganizationProxy"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemOrganizationProxys"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemOrganizationProxyList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>