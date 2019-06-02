<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemOperationList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawSystemOperation.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemOperationList" cellspacing="0" cellpadding="0"
    id="tawSystemOperationList" pagesize="25" class="table tawSystemOperationList"
    export="true" requestURI="/tawSystemOperations.do" sort="external" partialList="true" size="resultSize">

    <display:column property="code" sortable="true" headerClass="sortable"
         titleKey="tawSystemOperationForm.code"/>

    <display:column property="isApp" sortable="true" headerClass="sortable"
         titleKey="tawSystemOperationForm.isApp"/>

    <display:column property="name" sortable="true" headerClass="sortable"
         titleKey="tawSystemOperationForm.name"/>

    <display:column property="parentId" sortable="true" headerClass="sortable"
         titleKey="tawSystemOperationForm.parentId"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
         titleKey="tawSystemOperationForm.remark"/>

    <display:column property="url" sortable="true" headerClass="sortable"
         titleKey="tawSystemOperationForm.url"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemOperation"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemOperations"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemOperationList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>