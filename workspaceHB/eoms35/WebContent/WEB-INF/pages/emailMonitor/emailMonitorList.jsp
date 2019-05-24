
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="emailMonitorList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editEmailMonitor.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="emailMonitorList" cellspacing="0" cellpadding="0"
    id="emailMonitorList" pagesize="25" class="table emailMonitorList"
    export="true" requestURI="/emailMonitors.html" sort="external" partialList="true" size="resultSize">

    <display:column property="applyId" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.applyId"/>

    <display:column property="content" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.content"/>

    <display:column property="dispatchTime" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.dispatchTime"/>

    <display:column property="email" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.email"/>

    <display:column property="receiverId" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.receiverId"/>

    <display:column property="buizid" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.buizid"/>

    <display:column property="serviceId" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.serviceId"/>

    <display:column property="isSendImediat" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.isSendImediat"/>

    <display:column property="deleted" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.deleted"/>

    <display:column property="regetData" sortable="true" headerClass="sortable"
        url="/editEmailMonitor.html" paramId="id" paramProperty="id"
         titleKey="emailMonitorForm.regetData"/>

    <display:setProperty name="paging.banner.item_name" value="emailMonitor"/>
    <display:setProperty name="paging.banner.items_name" value="emailMonitors"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp"%>

