<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmTableDicts.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmTableDict.list.heading" />
</content>

	<display:table name="kmTableDictList" cellspacing="0" cellpadding="0"
		id="kmTableDictList" pagesize="${pageSize}" class="table kmTableDictList"
		export="false"
		requestURI="${app}/kmTableDict/kmTableDicts.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="id" sortable="true"
			headerClass="sortable" titleKey="kmTableDict.id" href="${app}/kmTableDict/kmTableDicts.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="dictName" sortable="true"
			headerClass="sortable" titleKey="kmTableDict.dictName" href="${app}/kmTableDict/kmTableDicts.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="parentId" sortable="true"
			headerClass="sortable" titleKey="kmTableDict.parentId" href="${app}/kmTableDict/kmTableDicts.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isDeleted" sortable="true"
			headerClass="sortable" titleKey="kmTableDict.isDeleted" href="${app}/kmTableDict/kmTableDicts.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmTableDict" />
		<display:setProperty name="paging.banner.items_name" value="kmTableDicts" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>