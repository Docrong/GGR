
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawWorkdaySetList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawWorkdaySet.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawWorkdaySetList" cellspacing="0" cellpadding="0"
    id="tawWorkdaySetList" pagesize="25" class="table tawWorkdaySetList"
    export="true" requestURI="/tawWorkdaySets.html" sort="external" partialList="true" size="resultSize">

    <display:column property="areaId" sortable="true" headerClass="sortable"
         titleKey="tawWorkdaySetForm.areaId"/>

    <display:column property="createTime" sortable="true" headerClass="sortable"
         titleKey="tawWorkdaySetForm.createTime"/>

    <display:column property="deleted" sortable="true" headerClass="sortable"
         titleKey="tawWorkdaySetForm.deleted"/>

    <display:column property="endTime" sortable="true" headerClass="sortable"
         titleKey="tawWorkdaySetForm.endTime"/>

    <display:column property="startTime" sortable="true" headerClass="sortable"
         titleKey="tawWorkdaySetForm.startTime"/>

    <display:column property="status" sortable="true" headerClass="sortable"
         titleKey="tawWorkdaySetForm.status"/>

    <display:column property="userId" sortable="true" headerClass="sortable"
         titleKey="tawWorkdaySetForm.userId"/>

    <display:column property="workDate" sortable="true" headerClass="sortable"
         titleKey="tawWorkdaySetForm.workDate"/>

    <display:setProperty name="paging.banner.item_name" value="tawWorkdaySet"/>
    <display:setProperty name="paging.banner.items_name" value="tawWorkdaySets"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp"%>

