<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSupplierkpiItemTypeList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawSupplierkpiItemType.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSupplierkpiItemTypeList" cellspacing="0" cellpadding="0"
    id="tawSupplierkpiItemTypeList" pagesize="25" class="table tawSupplierkpiItemTypeList"
    export="true" requestURI="/tawSupplierkpiItemTypes.html" sort="external" partialList="true" size="resultSize">

    <display:column property="dictName" sortable="true" headerClass="sortable"
         titleKey="tawSupplierkpiItemTypeForm.dictName"/>

    <display:column property="dictRemark" sortable="true" headerClass="sortable"
         titleKey="tawSupplierkpiItemTypeForm.dictRemark"/>

    <display:setProperty name="paging.banner.item_name" value="tawSupplierkpiItemType"/>
    <display:setProperty name="paging.banner.items_name" value="tawSupplierkpiItemTypes"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSupplierkpiItemTypeList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>