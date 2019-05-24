<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="appfuseSampleList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editAppfuseSample.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="appfuseSampleList" cellspacing="0" cellpadding="0"
    id="appfuseSampleList" pagesize="25" class="table appfuseSampleList"
    export="true" requestURI="" sort="list">

    <display:column property="remark" sortable="true" headerClass="sortable"
         titleKey="appfuseSampleForm.remark"/>

    <display:setProperty name="paging.banner.item_name" value="appfuseSample"/>
    <display:setProperty name="paging.banner.items_name" value="appfuseSamples"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("appfuseSampleList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>