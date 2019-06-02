<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmExamChoices.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-kmexamchoice">

<content tag="heading">
	<fmt:message key="kmExamChoice.list.heading" />
</content>

	<display:table name="kmExamChoiceList" cellspacing="0" cellpadding="0"
		id="kmExamChoiceList" pagesize="${pageSize}" class="table kmExamChoiceList"
		export="false"
		requestURI="${app}/kmExamChoice/kmExamChoices.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="choiceID" sortable="true"
			headerClass="sortable" titleKey="kmExamChoice.choiceID" href="${app}/kmExamChoice/kmExamChoices.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="questionsID" sortable="true"
			headerClass="sortable" titleKey="kmExamChoice.questionsID" href="${app}/kmExamChoice/kmExamChoices.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="content" sortable="true"
			headerClass="sortable" titleKey="kmExamChoice.content" href="${app}/kmExamChoice/kmExamChoices.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="orderBy" sortable="true"
			headerClass="sortable" titleKey="kmExamChoice.orderBy" href="${app}/kmExamChoice/kmExamChoices.do?method=edit" paramId="id" paramProperty="id"/>


		<display:setProperty name="paging.banner.item_name" value="kmExamChoice" />
		<display:setProperty name="paging.banner.items_name" value="kmExamChoices" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>