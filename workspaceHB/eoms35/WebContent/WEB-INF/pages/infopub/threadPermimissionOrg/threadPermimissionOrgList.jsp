<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<content tag="heading"><fmt:message key="threadPermimissionOrgList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editThreadPermimissionOrg.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="threadPermimissionOrgList" cellspacing="0" cellpadding="0"
    id="threadPermimissionOrgList" pagesize="25" class="table threadPermimissionOrgList"
    export="true" requestURI="/threadPermimissionOrgs.html" sort="external" partialList="true" size="resultSize">

<display:setProperty name="export.rtf" value="false"></display:setProperty>

    <display:column property="orgId" sortable="true" headerClass="sortable"
         titleKey="threadPermimissionOrgForm.orgId"/>

    <display:column property="ortType" sortable="true" headerClass="sortable"
         titleKey="threadPermimissionOrgForm.ortType"/>

    <display:setProperty name="paging.banner.item_name" value="threadPermimissionOrg"/>
    <display:setProperty name="paging.banner.items_name" value="threadPermimissionOrgs"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("threadPermimissionOrgList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>