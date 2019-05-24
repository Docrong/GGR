<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<style type="text/css">
  	body{background-image:none;}
</style>
<content tag="heading">${eoms:a2u('人员上报设置')}&nbsp;${eoms:a2u('列表')}</content>
<display:table name="tawSystemReportedList" cellspacing="0" cellpadding="0"
    id="tawSystemReportedList" pagesize="${pageSize}" class="table tawSystemReportedList"
    export="false"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/reported/tawSystemReported.do?method=list" 
    decorator="com.boco.eoms.commons.system.reported.displaytag.support.TawSystemReportedListDisplayTagDecorator" >


	<display:column property="modelId" sortable="true" headerClass="sortable"
         titleKey="tawSystemReported.modelName"/>
         
    <display:column property="functionId" sortable="true" headerClass="sortable"
         titleKey="tawSystemReported.functionName"/>

    <display:column property="reportedOrgName" sortable="true" headerClass="sortable"
         titleKey="tawSystemReported.reportedUser"/>
    
    <display:column sortable="true" headerClass="sortable" titleKey="tawSystemReported.edit" href="${app}/reported/tawSystemReported.do?method=edit" paramId="id" paramProperty="id" >${eoms:a2u('编辑')}</display:column>

    <display:column sortable="true" headerClass="sortable" titleKey="tawSystemReported.delete" href="${app}/reported/tawSystemReported.do?method=delete" paramId="id" paramProperty="id" >${eoms:a2u('删除')}</display:column>
         
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>

