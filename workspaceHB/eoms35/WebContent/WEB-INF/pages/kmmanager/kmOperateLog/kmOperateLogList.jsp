<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmOperateLogs.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmOperateLog.list.heading" />
</content>

	<display:table name="kmOperateLogList" cellspacing="0" cellpadding="0"
		id="kmOperateLogList" pagesize="${pageSize}" class="table kmOperateLogList"
		export="false"
		requestURI="${app}/kmmanager/kmOperateLogs.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="themeId" sortable="true"
			headerClass="sortable" titleKey="kmOperateLog.themeId" href="${app}/kmmanager/kmOperateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="tableId" sortable="true"
			headerClass="sortable" titleKey="kmOperateLog.tableId" href="${app}/kmmanager/kmOperateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="contentId" sortable="true"
			headerClass="sortable" titleKey="kmOperateLog.contentId" href="${app}/kmmanager/kmOperateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateUser" sortable="true"
			headerClass="sortable" titleKey="kmOperateLog.operateUser" href="${app}/kmmanager/kmOperateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateDept" sortable="true"
			headerClass="sortable" titleKey="kmOperateLog.operateDept" href="${app}/kmmanager/kmOperateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateTime" sortable="true"
			headerClass="sortable" titleKey="kmOperateLog.operateTime" href="${app}/kmmanager/kmOperateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="kmOperateLog.score" href="${app}/kmmanager/kmOperateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="toUser" sortable="true"
			headerClass="sortable" titleKey="kmOperateLog.toUser" href="${app}/kmmanager/kmOperateLogs.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmOperateLog" />
		<display:setProperty name="paging.banner.items_name" value="kmOperateLogs" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>