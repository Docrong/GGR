<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmExamPublics.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-kmexampublic">

<content tag="heading">
	<fmt:message key="kmExamPublic.list.heading" />
</content>

	<display:table name="kmExamPublicList" cellspacing="0" cellpadding="0"
		id="kmExamPublicList" pagesize="${pageSize}" class="table kmExamPublicList"
		export="false"
		requestURI="${app}/kmExamPublic/kmExamPublics.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="testId" sortable="true"
			headerClass="sortable" titleKey="kmExamPublic.testId" href="${app}/kmExamPublic/kmExamPublics.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createUser" sortable="true"
			headerClass="sortable" titleKey="kmExamPublic.createUser" href="${app}/kmExamPublic/kmExamPublics.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createDept" sortable="true"
			headerClass="sortable" titleKey="kmExamPublic.createDept" href="${app}/kmExamPublic/kmExamPublics.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="kmExamPublic.createTime" href="${app}/kmExamPublic/kmExamPublics.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="description" sortable="true"
			headerClass="sortable" titleKey="kmExamPublic.description" href="${app}/kmExamPublic/kmExamPublics.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmExamPublic" />
		<display:setProperty name="paging.banner.items_name" value="kmExamPublics" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>