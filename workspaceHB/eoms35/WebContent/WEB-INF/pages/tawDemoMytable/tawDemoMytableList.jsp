<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawDemoMytableList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawDemoMytable.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawDemoMytableList" cellspacing="0" cellpadding="0"
    id="tawDemoMytableList" pagesize="25" class="table tawDemoMytableList"
    export="true" requestURI="" sort="list">

    <display:column property="remark" sortable="true" headerClass="sortable"
         titleKey="tawDemoMytableForm.remark"/>

    <display:column property="tableName" sortable="true" headerClass="sortable"
         titleKey="tawDemoMytableForm.tableName"/>

    <display:setProperty name="paging.banner.item_name" value="tawDemoMytable"/>
    <display:setProperty name="paging.banner.items_name" value="tawDemoMytables"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawDemoMytableList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>