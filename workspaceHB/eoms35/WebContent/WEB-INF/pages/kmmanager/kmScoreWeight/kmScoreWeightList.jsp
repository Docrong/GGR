<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmScoreWeights.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<display:table name="kmScoreWeightList" cellspacing="0" cellpadding="0"
		id="kmScoreWeightList" pagesize="${pageSize}" class="table kmScoreWeightList"
		export="false"
		requestURI="${app}/kmScoreWeight/kmScoreWeights.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="powerName" sortable="true"
			headerClass="sortable" titleKey="kmScoreWeight.powerName" href="${app}/kmScoreWeight/kmScoreWeights.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="actionName" sortable="true"
			headerClass="sortable" titleKey="kmScoreWeight.actionName" href="${app}/kmScoreWeight/kmScoreWeights.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="actionWeight" sortable="true"
			headerClass="sortable" titleKey="kmScoreWeight.actionWeight" href="${app}/kmScoreWeight/kmScoreWeights.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="powerWeight" sortable="true"
			headerClass="sortable" titleKey="kmScoreWeight.powerWeight" href="${app}/kmScoreWeight/kmScoreWeights.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isDeleted" sortable="true"
			headerClass="sortable" titleKey="kmScoreWeight.isDeleted" href="${app}/kmScoreWeight/kmScoreWeights.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="area" sortable="true"
			headerClass="sortable" titleKey="kmScoreWeight.area" href="${app}/kmScoreWeight/kmScoreWeights.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="remark" sortable="true"
			headerClass="sortable" titleKey="kmScoreWeight.remark" href="${app}/kmScoreWeight/kmScoreWeights.do?method=edit" paramId="id" paramProperty="id"/>


		<display:setProperty name="paging.banner.item_name" value="kmScoreWeight" />
		<display:setProperty name="paging.banner.items_name" value="kmScoreWeights" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>