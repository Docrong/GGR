<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmContentsDicts.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<fmt:message key="kmContentsDict.list.heading" />
</content>

	<display:table name="kmContentsDictList" cellspacing="0" cellpadding="0"
		id="kmContentsDictList" pagesize="${pageSize}" class="table kmContentsDictList"
		export="false"
		requestURI="${app}/kmContentsDict/kmContentsDicts.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="contentId" sortable="true"
			headerClass="sortable" titleKey="kmContentsDict.contentId" href="${app}/kmContentsDict/kmContentsDicts.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="dictId" sortable="true"
			headerClass="sortable" titleKey="kmContentsDict.dictId" href="${app}/kmContentsDict/kmContentsDicts.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="dictType" sortable="true"
			headerClass="sortable" titleKey="kmContentsDict.dictType" href="${app}/kmContentsDict/kmContentsDicts.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmContentsDict" />
		<display:setProperty name="paging.banner.items_name" value="kmContentsDicts" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>