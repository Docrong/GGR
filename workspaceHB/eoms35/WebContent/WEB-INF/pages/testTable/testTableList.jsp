<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="testTableList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTestTable.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="testTableList" cellspacing="0" cellpadding="0"
    id="testTableList" pagesize="25" class="table testTableList"
    export="true" requestURI="" sort="list">

    <display:column property="remark" sortable="true" headerClass="sortable"
         titleKey="testTableForm.remark"/>

    <display:column property="datestr" sortable="true" headerClass="sortable"
         titleKey="testTableForm.datestr"/>

    <display:setProperty name="paging.banner.item_name" value="testTable"/>
    <display:setProperty name="paging.banner.items_name" value="testTables"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("testTableList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>