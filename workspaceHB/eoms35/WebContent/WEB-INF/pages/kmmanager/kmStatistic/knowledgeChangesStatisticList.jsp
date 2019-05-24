<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="knowledgeChangesStatistic.list.heading" />
</content>

	<display:table name="knowledgeChangesStatisticList" cellspacing="0" cellpadding="0"
		id="knowledgeChangesStatisticList" pagesize="${pageSize}" class="table knowledgeChangesStatisticList"
		export="false"
		requestURI="${app}/kmmanager/knowledgeChangesStatistics.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="baseName" sortable="true"
			headerClass="sortable" titleKey="knowledgeChangesStatistic.baseName" paramId="id" paramProperty="id"/>

	<display:column property="userName" sortable="true"
			headerClass="sortable" titleKey="knowledgeChangesStatistic.userName" paramId="id" paramProperty="id"/>

	<display:column property="addCount" sortable="true"
			headerClass="sortable" titleKey="knowledgeChangesStatistic.addCount" paramId="id" paramProperty="id"/>

	<display:column property="modifyCount" sortable="true"
			headerClass="sortable" titleKey="knowledgeChangesStatistic.modifyCount" paramId="id" paramProperty="id"/>

	<display:column property="deleteCount" sortable="true"
			headerClass="sortable" titleKey="knowledgeChangesStatistic.deleteCount" paramId="id" paramProperty="id"/>

	<display:column property="overCount" sortable="true"
			headerClass="sortable" titleKey="knowledgeChangesStatistic.overCount" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="knowledgeChangesStatistic" />
		<display:setProperty name="paging.banner.items_name" value="knowledgeChangesStatistics" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>