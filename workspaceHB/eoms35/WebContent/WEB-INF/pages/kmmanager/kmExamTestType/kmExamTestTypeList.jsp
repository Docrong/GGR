<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmExamTestTypes.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-kmexamtesttype">

<content tag="heading">
	<fmt:message key="kmExamTestType.list.heading" />
</content>

	<display:table name="kmExamTestTypeList" cellspacing="0" cellpadding="0"
		id="kmExamTestTypeList" pagesize="${pageSize}" class="table kmExamTestTypeList"
		export="false"
		requestURI="${app}/kmExamTestType/kmExamTestTypes.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="testTypeId" sortable="true"
			headerClass="sortable" titleKey="kmExamTestType.testTypeId" href="${app}/kmExamTestType/kmExamTestTypes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="testID" sortable="true"
			headerClass="sortable" titleKey="kmExamTestType.testID" href="${app}/kmExamTestType/kmExamTestTypes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="type" sortable="true"
			headerClass="sortable" titleKey="kmExamTestType.type" href="${app}/kmExamTestType/kmExamTestTypes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="description" sortable="true"
			headerClass="sortable" titleKey="kmExamTestType.description" href="${app}/kmExamTestType/kmExamTestTypes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="quantity" sortable="true"
			headerClass="sortable" titleKey="kmExamTestType.quantity" href="${app}/kmExamTestType/kmExamTestTypes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="kmExamTestType.score" href="${app}/kmExamTestType/kmExamTestTypes.do?method=edit" paramId="id" paramProperty="id"/>


		<display:setProperty name="paging.banner.item_name" value="kmExamTestType" />
		<display:setProperty name="paging.banner.items_name" value="kmExamTestTypes" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>