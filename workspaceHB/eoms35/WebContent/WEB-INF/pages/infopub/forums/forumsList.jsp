<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading">
<bean:message key="forumsList.heading" />
</content>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/forums.do?method=edit'/>'"
		value="<fmt:message key="button.add"/>" />

</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<fmt:bundle basename="config/applicationResource-workbench-infopub">
	<display:table name="forumsList" cellspacing="0" cellpadding="0"
		id="forumsList" pagesize="${pageSize}" class="table forumsList"
		export="true"
		requestURI="${app }/workbench/infopub/forums.do?method=search"
		sort="list" partialList="true" size="resultSize">

<display:setProperty name="export.rtf" value="false"></display:setProperty>

		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="forumsForm.title" href="${app }/workbench/infopub/thread.do?method=list4forumId" paramId="forumsId" paramProperty="id"/>
		
		<display:column property="description" sortable="true"
			headerClass="sortable" titleKey="forumsForm.description" />
			
			
		<display:column property="createrId" sortable="true"
			headerClass="sortable" titleKey="forumsForm.createrId" />

		<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="forumsForm.createTime" />


		<display:column property="parentId" sortable="true"
			headerClass="sortable" titleKey="forumsForm.parentId" />

		<display:setProperty name="paging.banner.item_name" value="forums" />
		<display:setProperty name="paging.banner.items_name" value="forumss" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>
<!-- 
<script type="text/javascript">
    highlightTableRows("forumsList");
</script>
 -->
<%@ include file="/common/footer_eoms.jsp"%>
