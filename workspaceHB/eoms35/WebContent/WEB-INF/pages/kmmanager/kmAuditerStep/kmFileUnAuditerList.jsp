<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmAuditerSteps.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<!--
<content tag="heading">
	<fmt:message key="kmAuditerStep.list.heading" />
</content>

	<display:table name="kmAuditerStepList" cellspacing="0" cellpadding="0"
		id="kmAuditerStepList" pagesize="${pageSize}" class="table kmAuditerStepList"
		export="false"
		requestURI="${app}/kmAuditerStep/kmAuditerSteps.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="kmId" sortable="true"
			headerClass="sortable" titleKey="kmAuditerStep.kmId" href="${app}/kmAuditerStep/kmAuditerSteps.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="kmAuditerStep.createTime" href="${app}/kmAuditerStep/kmAuditerSteps.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateTime" sortable="true"
			headerClass="sortable" titleKey="kmAuditerStep.operateTime" href="${app}/kmAuditerStep/kmAuditerSteps.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateType" sortable="true"
			headerClass="sortable" titleKey="kmAuditerStep.operateType" href="${app}/kmAuditerStep/kmAuditerSteps.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="toOrgId" sortable="true"
			headerClass="sortable" titleKey="kmAuditerStep.toOrgId" href="${app}/kmAuditerStep/kmAuditerSteps.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="toOrgType" sortable="true"
			headerClass="sortable" titleKey="kmAuditerStep.toOrgType" href="${app}/kmAuditerStep/kmAuditerSteps.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateId" sortable="true"
			headerClass="sortable" titleKey="kmAuditerStep.operateId"/>


		<display:setProperty name="paging.banner.item_name" value="kmAuditerStep" />
		<display:setProperty name="paging.banner.items_name" value="kmAuditerSteps" />
	</display:table>
-->


	<display:table name="kmFileList" cellspacing="0" cellpadding="0"
		id="kmFileList" pagesize="${pageSize}" class="table kmFileList"
		export="false"
		requestURI="${app}/kmmanager/kmAuditerSteps.do?method=getFileUnAuditList"
		sort="list" partialList="true" size="resultSize" decorator="com.boco.eoms.km.knowledge.displaytag.support.KmContentsDisplaytabDecorator">


	<display:column sortable="true" property="fileName" titleKey="kmFile.fileName" 
		href="${app}/kmmanager/kmAuditerSteps.do?method=fileAudit"  paramId="id" paramProperty="id" headerClass="sortable" />
	
	
	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFileTree.nodeName" paramId="id" paramProperty="id">
		<eoms:id2nameDB beanId="kmFileTreeDao" id="${kmFileList.nodeId}"/>
	</display:column>

	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFile.userId" paramId="id" paramProperty="id">
		<eoms:id2nameDB beanId="tawSystemUserDao" id="${kmFileList.userId}"/>
	</display:column>

	<display:column property="uploadTime" sortable="true"
			headerClass="sortable" titleKey="kmFile.uploadTime" paramId="id" paramProperty="id"/>

	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFile.fileSize" paramId="id" paramProperty="id">
	${kmFileList.fileSize}&nbsp;KB
	</display:column>

	<display:column sortable="true"
			headerClass="sortable" titleKey="kmFile.fileGrade" paramId="id" paramProperty="id">
		<eoms:dict key="dict-kmmanager" dictId="fileGrade" itemId="${kmFileList.fileGrade}" beanId="id2nameXML" />
	</display:column>

	<display:column sortable="true" headerClass="state" title="状态">
		<eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${kmFileList.state}" beanId="id2nameXML" />
	</display:column>
		
		
		<display:setProperty name="paging.banner.item_name"  value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />
	</display:table>


</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>