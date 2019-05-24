<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmTableThemes.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmTableTheme.list.heading" />
</content>

	<display:table name="kmTableThemeList" cellspacing="0" cellpadding="0"
		id="kmTableThemeList" pagesize="${pageSize}" class="table kmTableThemeList"
		export="false"
		requestURI="${app}/kmTableTheme/kmTableThemes.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="id" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.id" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createUser" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.createUser" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createDept" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.createDept" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.createTime" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="themeName" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.themeName" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="parentId" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.parentId" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isLeaf" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.isLeaf" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isOpen" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.isOpen" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isDeleted" sortable="true"
			headerClass="sortable" titleKey="kmTableTheme.isDeleted" href="${app}/kmTableTheme/kmTableThemes.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmTableTheme" />
		<display:setProperty name="paging.banner.items_name" value="kmTableThemes" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>