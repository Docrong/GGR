<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="smsLogList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editSmsLog.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="smsLogList" cellspacing="0" cellpadding="0"
    id="smsLogList" pagesize="25" class="table smsLogList"
    export="true" requestURI="/smsLogs.html" sort="external" partialList="true" size="resultSize">

    <display:column property="content" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.content"/>

    <display:column property="dispatchTime" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.dispatchTime"/>

    <display:column property="insertTime" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.insertTime"/>

    <display:column property="mobile" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.mobile"/>

    <display:column property="moduleId" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.moduleId"/>

    <display:column property="moduleName" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.moduleName"/>

    <display:column property="monitorId" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.monitorId"/>

    <display:column property="reason" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.reason"/>

    <display:column property="receiverId" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.receiverId"/>

    <display:column property="serviceId" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.serviceId"/>

    <display:column property="serviceName" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.serviceName"/>

    <display:column property="success" sortable="true" headerClass="sortable"
        url="/editSmsLog.html" paramId="id" paramProperty="id"
         titleKey="smsLogForm.success"/>

    <display:setProperty name="paging.banner.item_name" value="smsLog"/>
    <display:setProperty name="paging.banner.items_name" value="smsLogs"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("smsLogList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>