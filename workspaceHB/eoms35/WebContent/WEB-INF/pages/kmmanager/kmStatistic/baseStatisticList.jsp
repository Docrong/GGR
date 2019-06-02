<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="baseStatistic.list.heading" />
</content>

	<display:table name="baseStatisticList" cellspacing="0" cellpadding="0"
		id="baseStatisticList" pagesize="${pageSize}" class="table baseStatisticList"
		export="false"
		requestURI="${app}/kmmanager/baseStatistics.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="baseName" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.baseName" paramId="id" paramProperty="id"/>

	<display:column property="userName" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.userName" paramId="id" paramProperty="id"/>

	<display:column property="availableCount" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.availableCount" paramId="id" paramProperty="id"/>

	<display:column property="invalidCount" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.invalidCount" paramId="id" paramProperty="id"/>

	<display:column property="deleteCount" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.deleteCount" paramId="id" paramProperty="id"/>

	<display:column property="utilizationCount" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.utilizationCount" paramId="id" paramProperty="id"/>

	<display:column property="utilizationRate" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.utilizationRate" paramId="id" paramProperty="id"/>

	<display:column property="readCount" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.readCount" paramId="id" paramProperty="id"/>

	<display:column property="usedCount" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.usedCount" paramId="id" paramProperty="id"/>

	<display:column property="usedRate" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.usedRate" paramId="id" paramProperty="id"/>

	<display:column property="recommendCount" sortable="true"
			headerClass="sortable" titleKey="baseStatistic.recommendCount" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="baseStatistic" />
		<display:setProperty name="paging.banner.items_name" value="baseStatistics" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>