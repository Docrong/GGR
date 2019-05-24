<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmExamAnswerss.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-kmexamanswers">

<content tag="heading">
	<fmt:message key="kmExamAnswers.list.heading" />
</content>

	<display:table name="kmExamAnswersList" cellspacing="0" cellpadding="0"
		id="kmExamAnswersList" pagesize="${pageSize}" class="table kmExamAnswersList"
		export="false"
		requestURI="${app}/kmExamAnswers/kmExamAnswerss.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="questionId" sortable="true"
			headerClass="sortable" titleKey="kmExamAnswers.questionId" href="${app}/kmExamAnswers/kmExamAnswerss.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="answer" sortable="true"
			headerClass="sortable" titleKey="kmExamAnswers.answer" href="${app}/kmExamAnswers/kmExamAnswerss.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isRight" sortable="true"
			headerClass="sortable" titleKey="kmExamAnswers.isRight" href="${app}/kmExamAnswers/kmExamAnswerss.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="kmExamAnswers.score" href="${app}/kmExamAnswers/kmExamAnswerss.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="attendId" sortable="true"
			headerClass="sortable" titleKey="kmExamAnswers.attendId" href="${app}/kmExamAnswers/kmExamAnswerss.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmExamAnswers" />
		<display:setProperty name="paging.banner.items_name" value="kmExamAnswerss" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>