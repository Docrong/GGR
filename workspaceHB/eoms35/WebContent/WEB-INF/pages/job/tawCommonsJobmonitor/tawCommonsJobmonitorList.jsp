<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('tawCommonsJobmonitorList');
})
</script>
<div class="list-title"><bean:message key="tawCommonsJobmonitorList.heading"/></div>
<fmt:bundle basename="config/ApplicationResources-job">
<display:table name="tawCommonsJobMonitorList" cellspacing="0" cellpadding="0"
    id="tawCommonsJobmonitorList" pagesize="25" class="table tawCommonsJobmonitorList"
    export="false" requestURI="" sort="list">

    <display:column property="jobSortName" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobmonitorForm.jobSortName"/>

    <display:column property="jobSubId" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobmonitorForm.jobSubId"/>

    <display:column property="maxExecuteTime" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobmonitorForm.maxExecuteTime"/>

    <display:column property="executeEndTime" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobmonitorForm.executeEndTime"/>

    <display:column property="executeStartTime" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobmonitorForm.executeStartTime"/>

    <display:column property="statusName" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobmonitorForm.status"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonsJobmonitor"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonsJobmonitors"/>
</display:table>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>