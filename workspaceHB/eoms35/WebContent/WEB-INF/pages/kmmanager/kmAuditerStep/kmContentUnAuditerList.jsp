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

	<display:table name="kmContentsList" cellspacing="0" cellpadding="0"
		id="kmContentsList" pagesize="${pageSize}" class="table kmContentsList"
		export="false"
		requestURI="${app}/kmAuditerStep/kmAuditerSteps.do?method=search"
		sort="list" partialList="true" size="resultSize" decorator="com.boco.eoms.km.knowledge.displaytag.support.KmContentsDisplaytabDecorator">

		<display:column sortable="true" headerClass="sortable" title="知识分类">
			<eoms:id2nameDB id="${kmContentsList.themeId}" beanId="kmTableThemeDao" />
		</display:column>

		<display:column sortable="true" property="contentTitle" titleKey="kmContents.contentTitle" 
			 href="${app}/kmmanager/kmAuditerSteps.do?method=contentAudit"  paramId="id" paramProperty="id" headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.createUser">
			<eoms:id2nameDB id="${kmContentsList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column sortable="false" property="contentKeys" titleKey="kmContents.contentKeys" 
			paramId="id" paramProperty="id" headerClass="sortable" />
			
		<display:column sortable="true" headerClass="contentStatus" title="状态">
			<eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${kmContentsList.contentStatus}" beanId="id2nameXML" />
		</display:column>
		
		
		<display:setProperty name="paging.banner.item_name"  value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />
	</display:table>


</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>