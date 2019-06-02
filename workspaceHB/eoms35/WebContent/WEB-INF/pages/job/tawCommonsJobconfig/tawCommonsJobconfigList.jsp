<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonsJobconfigList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawCommonsJobconfig.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonsJobConfigList" cellspacing="0" cellpadding="0"
    id="tawCommonsJobconfigList" pagesize="25" class="table tawCommonsJobconfigList"
    export="true" requestURI="" sort="list">

    <display:column property="ifSerialFlag" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobconfigForm.ifSerialFlag"/>

    <display:column property="busyEndTime" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobconfigForm.busyEndTime"/>

    <display:column property="busyStartTime" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobconfigForm.busyStartTime"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonsJobconfig"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonsJobconfigs"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonsJobconfigList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>