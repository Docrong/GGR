<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmAskReplys.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-kmaskreply">

<content tag="heading">
	<fmt:message key="kmAskReply.list.heading" />
</content>

	<display:table name="kmAskReplyList" cellspacing="0" cellpadding="0"
		id="kmAskReplyList" pagesize="${pageSize}" class="table kmAskReplyList"
		export="false"
		requestURI="${app}/kmAskReply/kmAskReplys.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="questionId" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.questionId" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="answer" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.answer" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="conment" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.conment" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isBest" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.isBest" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="answerUser" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.answerUser" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="answerDept" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.answerDept" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="answerTime" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.answerTime" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="fileName" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.fileName" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isAnonymity" sortable="true"
			headerClass="sortable" titleKey="kmAskReply.isAnonymity" href="${app}/kmAskReply/kmAskReplys.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmAskReply" />
		<display:setProperty name="paging.banner.items_name" value="kmAskReplys" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>