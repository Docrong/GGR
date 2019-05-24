
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawWorkbenchContactGroupList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawWorkbenchContactGroup.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawWorkbenchContactGroupList" cellspacing="0" cellpadding="0"
    id="tawWorkbenchContactGroupList" pagesize="25" class="table tawWorkbenchContactGroupList"
    export="true" requestURI="/tawWorkbenchContactGroups.html" sort="external" partialList="true" size="resultSize">

    <display:column property="deleted" sortable="true" headerClass="sortable"
        url="/editTawWorkbenchContactGroup.html" paramId="id" paramProperty="id"
         titleKey="tawWorkbenchContactGroupForm.deleted"/>

    <display:column property="groupName" sortable="true" headerClass="sortable"
        url="/editTawWorkbenchContactGroup.html" paramId="id" paramProperty="id"
         titleKey="tawWorkbenchContactGroupForm.groupName"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/editTawWorkbenchContactGroup.html" paramId="id" paramProperty="id"
         titleKey="tawWorkbenchContactGroupForm.remark"/>

    <display:column property="userId" sortable="true" headerClass="sortable"
        url="/editTawWorkbenchContactGroup.html" paramId="id" paramProperty="id"
         titleKey="tawWorkbenchContactGroupForm.userId"/>

    <display:setProperty name="paging.banner.item_name" value="tawWorkbenchContactGroup"/>
    <display:setProperty name="paging.banner.items_name" value="tawWorkbenchContactGroups"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp"%>

