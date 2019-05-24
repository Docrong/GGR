<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonMessageMonitorList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/message/editTawCommonMessageMonitor.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonMessageMonitorList" cellspacing="0" cellpadding="0"
    id="tawCommonMessageMonitorList" pagesize="25" class="table tawCommonMessageMonitorList"
    export="true" requestURI="" sort="list">

    <display:column property="urgent" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.urgent"/>

    <display:column property="cycle" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.cycle"/>

    <display:column property="dispatcher" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.dispatcher"/>

    <display:column property="dispatchTime" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.dispatchTime"/>

    <display:column property="endTime" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.endTime"/>

    <display:column property="hieAmount" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieAmount"/>

    <display:column property="hieArrive" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieArrive"/>

    <display:column property="hieClose" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieClose"/>

    <display:column property="hieContent" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieContent"/>

    <display:column property="hieCount" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieCount"/>

    <display:column property="hieId" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieId"/>

    <display:column property="hieInterval" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieInterval"/>

    <display:column property="hieTimeLimit" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieTimeLimit"/>

    <display:column property="hieWay" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.hieWay"/>

    <display:column property="nightAllow" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.nightAllow"/>

    <display:column property="receiver" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.receiver"/>

    <display:column property="receiverType" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.receiverType"/>

    <display:column property="regionId" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.regionId"/>

    <display:column property="startTime" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.startTime"/>

    <display:column property="taskId" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageMonitor.do" paramId="monitorId" paramProperty="monitorId"
         titleKey="tawCommonMessageMonitorForm.taskId"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonMessageMonitor"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonMessageMonitors"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonMessageMonitorList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>