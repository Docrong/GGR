<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmExamAttends.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-kmexamattend">

<content tag="heading">
	<fmt:message key="kmExamAttend.list.heading" />
</content>

	<display:table name="kmExamAttendList" cellspacing="0" cellpadding="0"
		id="kmExamAttendList" pagesize="${pageSize}" class="table kmExamAttendList"
		export="false"
		requestURI="${app}/kmmanager/kmExamAttends.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="testId" sortable="true"
			headerClass="sortable" titleKey="kmExamAttend.testId" href="${app}/kmExamAttend/kmExamAttends.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="attendUser" sortable="true"
			headerClass="sortable" titleKey="kmExamAttend.attendUser" href="${app}/kmExamAttend/kmExamAttends.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="attendDept" sortable="true"
			headerClass="sortable" titleKey="kmExamAttend.attendDept" href="${app}/kmExamAttend/kmExamAttends.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="attendTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmExamAttend.attendTime" href="${app}/kmExamAttend/kmExamAttends.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="attendOverTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmExamAttend.attendOverTime" href="${app}/kmExamAttend/kmExamAttends.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="kmExamAttend.score" href="${app}/kmExamAttend/kmExamAttends.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="isRead" sortable="true"
			headerClass="sortable" titleKey="kmExamAttend.isRead" href="${app}/kmExamAttend/kmExamAttends.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmExamAttend" />
		<display:setProperty name="paging.banner.items_name" value="kmExamAttends" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>