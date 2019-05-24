<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawPrefixTableList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawPrefixTable.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawPrefixTableList" cellspacing="0" cellpadding="0"
    id="tawPrefixTableList" pagesize="25" class="table tawPrefixTableList"
    export="true" requestURI="" sort="list">

    <display:column property="prefixName" sortable="true" headerClass="sortable"
         titleKey="tawPrefixTableForm.prefixName"/>

    <display:setProperty name="paging.banner.item_name" value="tawPrefixTable"/>
    <display:setProperty name="paging.banner.items_name" value="tawPrefixTables"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawPrefixTableList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>