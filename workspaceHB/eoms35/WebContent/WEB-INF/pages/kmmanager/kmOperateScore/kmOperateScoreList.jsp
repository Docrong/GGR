<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmOperateScores.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmOperateScore.list.heading" />
</content>

	<display:table name="kmOperateScoreList" cellspacing="0" cellpadding="0"
		id="kmOperateScoreList" pagesize="${pageSize}" class="table kmOperateScoreList"
		export="false"
		requestURI="${app}/kmOperateScore/kmOperateScores.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="operateName" sortable="true"
			headerClass="sortable" titleKey="kmOperateScore.operateName" href="${app}/kmOperateScore/kmOperateScores.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="kmOperateScore.score" href="${app}/kmOperateScore/kmOperateScores.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="operateId" sortable="true"
			headerClass="sortable" titleKey="kmOperateScore.operateId" href="${app}/kmOperateScore/kmOperateScores.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmOperateScore" />
		<display:setProperty name="paging.banner.items_name" value="kmOperateScores" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>