<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmOperateDateLogs.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmOperateDateLog.list.heading" />
</content>

	<display:table name="kmOperateDateLogList" cellspacing="0" cellpadding="0"
		id="kmOperateDateLogList" pagesize="${pageSize}" class="table kmOperateDateLogList"
		export="false"
		requestURI="${app}/kmmanager/kmOperateDateLogs.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="operateDate" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.operateDate" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateUser" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.operateUser" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateDept" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.operateDept" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="addCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.addCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="addScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.addScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="modifyCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.modifyCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="modifyScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.modifyScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="overCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.overCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="overScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.overScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="deleteCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.deleteCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="deleteScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.deleteScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="upCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.upCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="upScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.upScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="downCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.downCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="downScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.downScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="useCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.useCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="useScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.useScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="opinionCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.opinionCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="opinionScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.opinionScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="adviceCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.adviceCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="adviceScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.adviceScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="auditOkCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.auditOkCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="auditOkScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.auditOkScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="auditBackCount" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.auditBackCount" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="auditBackScore" sortable="true"
			headerClass="sortable" titleKey="kmOperateDateLog.auditBackScore" href="${app}/kmmanager/kmOperateDateLogs.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmOperateDateLog" />
		<display:setProperty name="paging.banner.items_name" value="kmOperateDateLogs" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>