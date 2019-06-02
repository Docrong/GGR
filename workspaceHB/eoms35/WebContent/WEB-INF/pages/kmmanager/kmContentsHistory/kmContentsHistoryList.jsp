<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmContentsHistorys.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmContentsHistory.list.heading" />
</content>

	<display:table name="kmContentsHistoryList" cellspacing="0" cellpadding="0"
		id="kmContentsHistoryList" pagesize="${pageSize}" class="table kmContentsHistoryList"
		export="false"
		requestURI="${app}/kmContentsHistory/kmContentsHistorys.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="themeId" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.themeId" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="tableId" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.tableId" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createUser" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.createUser" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createDept" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.createDept" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.createTime" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="modifyUser" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.modifyUser" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="modifyDept" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.modifyDept" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="modifyTime" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.modifyTime" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="contentTitle" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.contentTitle" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="contentKeys" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.contentKeys" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="contentStatus" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.contentStatus" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="auditFlag" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.auditFlag" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="rolestrFlag" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.rolestrFlag" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="levelFlag" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.levelFlag" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isBest" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.isBest" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isPublic" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.isPublic" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="gradeOne" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.gradeOne" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="gradeTwo" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.gradeTwo" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="gradeThree" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.gradeThree" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="gradeFour" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.gradeFour" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="gradeFive" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.gradeFive" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="readCount" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.readCount" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="useCount" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.useCount" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="contentXml" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.contentXml" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="modifyCount" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.modifyCount" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="version" sortable="true"
			headerClass="sortable" titleKey="kmContentsHistory.version" href="${app}/kmContentsHistory/kmContentsHistorys.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmContentsHistory" />
		<display:setProperty name="paging.banner.items_name" value="kmContentsHistorys" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>