<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmContentsOpinions.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmContentsOpinion.list.heading" />
</content>

	<display:table name="kmContentsOpinionList" cellspacing="0" cellpadding="0"
		id="kmContentsOpinionList" pagesize="${pageSize}" class="table kmContentsOpinionList"
		export="false"
		requestURI="${app}/kmContentsOpinion/kmContentsOpinions.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="contentId" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.contentId" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createUser" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.createUser" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createDept" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.createDept" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.createTime" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="opinionContent" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.opinionContent" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="opinionGrade" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.opinionGrade" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isEdit" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.isEdit" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isRefedit" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.isRefedit" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isDeleted" sortable="true"
			headerClass="sortable" titleKey="kmContentsOpinion.isDeleted" href="${app}/kmContentsOpinion/kmContentsOpinions.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmContentsOpinion" />
		<display:setProperty name="paging.banner.items_name" value="kmContentsOpinions" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>