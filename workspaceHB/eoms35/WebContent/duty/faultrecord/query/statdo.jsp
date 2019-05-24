<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<style type="text/css">
  	body{background-image:none;}
</style>
<content tag="heading">${eoms:a2u('故障记录')}&nbsp;${eoms:a2u('统计结果')}</content>
<fmt:bundle basename="config/ApplicationResources-duty">
<display:table name="faultrecordlist" cellspacing="0" cellpadding="0"
    id="faultrecordlist" pagesize="${pageSize}" class="table faultrecordlist"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/Faultrecord/statdo.do" 
    decorator="com.boco.eoms.duty.displaytag.support.FaultRecordDisplaytagDecorator" >


	<display:column property="userId" sortable="true" headerClass="sortable"
         titleKey="Faultrecord.createUser"/>
         
    <display:column property="deptId" sortable="true" headerClass="sortable"
         titleKey="Faultrecord.createdept"/>

    <display:column property="startTime" sortable="true" headerClass="sortable"
         titleKey="Faultrecord.startTime"/>

    <display:column property="networkName" sortable="true" headerClass="sortable"
         titleKey="Faultrecord.networkName"/>
         
    <display:column property="devicetype" sortable="true" headerClass="sortable"
         titleKey="Faultrecord.devicetype"/>

    <display:column property="declareUser" sortable="true" headerClass="sortable"
         titleKey="Faultrecord.declareUser"/>

    <display:column property="dealUser" sortable="true" headerClass="sortable"
         titleKey="Faultrecord.dealUser"/>

    <display:column property="totalTime" sortable="true" headerClass="sortable"
         titleKey="Faultrecord.totleCount"/>
         
</display:table>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>

