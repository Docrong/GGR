<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/trainInformations.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">


	<display:table name="trainInformationList" cellspacing="0" cellpadding="0"
		id="trainInformationList" pagesize="${pageSize}" class="table trainInformationList"
		export="false"
		requestURI="${app}/trainInformation/trainInformations.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="trainName" sortable="true"
			headerClass="sortable" titleKey="trainInformation.trainName" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainGrade" sortable="true"
			headerClass="sortable" titleKey="trainInformation.trainGrade" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainAddress" sortable="true"
			headerClass="sortable" titleKey="trainInformation.trainAddress" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainInformation.trainTime" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainBeginTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainInformation.trainBeginTime" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainEndTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainInformation.trainEndTime" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainDept" sortable="true"
			headerClass="sortable" titleKey="trainInformation.trainDept" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainDocument" sortable="true"
			headerClass="sortable" titleKey="trainInformation.trainDocument" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainVender" sortable="true"
			headerClass="sortable" titleKey="trainInformation.trainVender" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainSpeciality" sortable="true"
			headerClass="sortable" titleKey="trainInformation.trainSpeciality" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="trainType" sortable="true"
			headerClass="sortable" titleKey="trainInformation.trainType" href="${app}/trainInformation/trainInformations.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="trainInformation" />
		<display:setProperty name="paging.banner.items_name" value="trainInformations" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>