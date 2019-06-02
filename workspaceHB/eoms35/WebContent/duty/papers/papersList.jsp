<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/papers.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/ApplicationResources-duty">

<content tag="heading">
	<fmt:message key="papers.list.heading" />
</content>

	<display:table name="papersList" cellspacing="0" cellpadding="0"
		id="papersList" pagesize="${pageSize}" class="table papersList"
		export="false"
		requestURI="${app}/duty/papers/papers.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="papers.title" href="${app}/duty/papers/papers.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="insertTime" sortable="true"
			headerClass="sortable" titleKey="papers.insertTime" href="${app}/duty/papers/papers.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="insertUserId" sortable="true"
			headerClass="sortable" titleKey="papers.insertUserId" href="${app}/duty/papers/papers.do?method=edit" paramId="id" paramProperty="id"/>

	<%--<display:column property="deptId" sortable="true"
			headerClass="sortable" titleKey="papers.deptId" href="${app}/duty/papers/papers.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="deleted" sortable="true"
			headerClass="sortable" titleKey="papers.deleted" href="${app}/duty/papers/papers.do?method=edit" paramId="id" paramProperty="id"/>


		--%><display:column sortable="true"
			headerClass="sortable" titleKey="papers.accessories" >
			<eoms:download ids="${ papersList.accessoriesId }"></eoms:download>
		</display:column>

		<display:setProperty name="paging.banner.item_name" value="papers" />
		<display:setProperty name="paging.banner.items_name" value="papers" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>