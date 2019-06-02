<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawDemoTableList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawDemoTable.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawDemoTableList" cellspacing="0" cellpadding="0"
    id="tawDemoTableList" pagesize="25" class="table tawDemoTableList"
    export="true" requestURI="" sort="list">

    <display:column property="addr" sortable="true" headerClass="sortable"
         titleKey="tawDemoTableForm.addr"/>

    <display:column property="tel" sortable="true" headerClass="sortable"
         titleKey="tawDemoTableForm.tel"/>

    <display:setProperty name="paging.banner.item_name" value="tawDemoTable"/>
    <display:setProperty name="paging.banner.items_name" value="tawDemoTables"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawDemoTableList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>