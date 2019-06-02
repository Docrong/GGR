<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmTableColumns.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmTableColumn.list.heading" />
</content>

	<display:table name="kmTableColumnList" cellspacing="0" cellpadding="0"
		id="kmTableColumnList" pagesize="${pageSize}" class="table kmTableColumnList"
		export="false"
		requestURI="${app}/kmTableColumn/kmTableColumns.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="tableId" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.tableId" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="id" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.id" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createUser" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.createUser" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createDept" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.createDept" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.createTime" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="colName" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.colName" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="colChname" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.colChname" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="colType" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.colType" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="colDictType" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.colDictType" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="colDictId" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.colDictId" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="colDefault" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.colDefault" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="colSize" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.colSize" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isNullable" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.isNullable" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isOpen" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.isOpen" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isVisibl" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.isVisibl" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isUnique" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.isUnique" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isIndex" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.isIndex" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isDeleted" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.isDeleted" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="orderBy" sortable="true"
			headerClass="sortable" titleKey="kmTableColumn.orderBy" href="${app}/kmTableColumn/kmTableColumns.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmTableColumn" />
		<display:setProperty name="paging.banner.items_name" value="kmTableColumns" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>