<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmAuditers.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<caption>
		<div class="header center">审核人配置列表</div>
	</caption>
	<display:table name="kmAuditerList" cellspacing="0" cellpadding="0"
		id="kmAuditerList" pagesize="${pageSize}" class="table kmAuditerList"
		export="false"
		requestURI="${app}/kmAuditer/kmAuditers.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="kmAuditer.createTime" href="${app}/kmAuditer/kmAuditers.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="auditType" sortable="true"
			headerClass="sortable" titleKey="审核类型" paramId="id" paramProperty="id"/>

	<display:column sortable="true" headerClass="sortable" titleKey="文件分类">
			<eoms:id2nameDB id="${kmAuditerList.nodeId}" beanId="kmFileTreeDao" />
	</display:column>

	<display:column sortable="true" headerClass="sortable" titleKey="知识库分类">
			<eoms:id2nameDB id="${kmAuditerList.tableId}" beanId="kmTableGeneralDao" />
	</display:column>

	<display:column sortable="true" headerClass="sortable" titleKey="知识分类">
			<eoms:id2nameDB id="${kmAuditerList.themeId}" beanId="kmTableThemeDao" />
	</display:column>

	<display:column sortable="true" headerClass="sortable" titleKey="kmAuditer.roleId">
			<eoms:id2nameDB id="${kmAuditerList.roleId}" beanId="tawSystemSubRoleDao" />
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmAuditer.master">
			<eoms:id2nameDB id="${kmAuditerList.masterId}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column sortable="true" headerClass="sortable" titleKey="kmAuditer.masterAudit">
			<eoms:id2nameDB id="${kmAuditerList.masterAudit}" beanId="ItawSystemDictTypeDao" />
	</display:column>

	<display:column sortable="true" headerClass="sortable" titleKey="kmAuditer.isSign">
			<eoms:id2nameDB id="${kmAuditerList.isSign}" beanId="ItawSystemDictTypeDao" />
	</display:column>


		<display:setProperty name="paging.banner.item_name" value="kmAuditer" />
		<display:setProperty name="paging.banner.items_name" value="kmAuditers" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>