<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmAskVotes.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-kmaskvote">

<content tag="heading">
	<fmt:message key="kmAskVote.list.heading" />
</content>

	<display:table name="kmAskVoteList" cellspacing="0" cellpadding="0"
		id="kmAskVoteList" pagesize="${pageSize}" class="table kmAskVoteList"
		export="false"
		requestURI="${app}/kmAskVote/kmAskVotes.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="voteUser" sortable="true"
			headerClass="sortable" titleKey="kmAskVote.voteUser" href="${app}/kmAskVote/kmAskVotes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="voteDate" sortable="true"
			headerClass="sortable" titleKey="kmAskVote.voteDate" href="${app}/kmAskVote/kmAskVotes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="voteDept" sortable="true"
			headerClass="sortable" titleKey="kmAskVote.voteDept" href="${app}/kmAskVote/kmAskVotes.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmAskVote" />
		<display:setProperty name="paging.banner.items_name" value="kmAskVotes" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>