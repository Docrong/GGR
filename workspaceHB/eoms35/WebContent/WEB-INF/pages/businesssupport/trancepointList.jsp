<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/businessupports.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResource-businessupport">

<content tag="heading">
	<fmt:message key="businessupport.list.heading" />
</content>

	<display:table name="businessupportList" cellspacing="0" cellpadding="0"
		id="businessupportList" pagesize="${pageSize}" class="table businessupportList"
		export="false"
		requestURI="${app}/businessupport/trancepoint/trancePointSheet.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="orderSheetId" sortable="true"
			headerClass="sortable" titleKey="businessupport.orderSheetId" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="portEngineRoomName" sortable="true"
			headerClass="sortable" titleKey="businessupport.portEngineRoomName" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="portInterfaceType" sortable="true"
			headerClass="sortable" titleKey="businessupport.portInterfaceType" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="portDeviceName" sortable="true"
			headerClass="sortable" titleKey="businessupport.portDeviceName" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="portCustAidPerson" sortable="true"
			headerClass="sortable" titleKey="businessupport.portCustAidPerson" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="portACustAidPhone" sortable="true"
			headerClass="sortable" titleKey="businessupport.portACustAidPhone" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="portDetailAdd" sortable="true"
			headerClass="sortable" titleKey="businessupport.portDetailAdd" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="sevPointContact" sortable="true"
			headerClass="sortable" titleKey="businessupport.sevPointContact" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="sevPointContactPhone" sortable="true"
			headerClass="sortable" titleKey="businessupport.sevPointContactPhone" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="sevPointContactEmail" sortable="true"
			headerClass="sortable" titleKey="businessupport.sevPointContactEmail" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="sevPointContactAdd" sortable="true"
			headerClass="sortable" titleKey="businessupport.sevPointContactAdd" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="fiberEquipName" sortable="true"
			headerClass="sortable" titleKey="businessupport.fiberEquipName" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="fiberEquipCode" sortable="true"
			headerClass="sortable" titleKey="businessupport.fiberEquipCode" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="siteEquipCode" sortable="true"
			headerClass="sortable" titleKey="businessupport.siteEquipCode" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="siteName" sortable="true"
			headerClass="sortable" titleKey="businessupport.siteName" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="accessSiteIden" sortable="true"
			headerClass="sortable" titleKey="businessupport.accessSiteIden" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="remark" sortable="true"
			headerClass="sortable" titleKey="businessupport.remark" href="${app}/businessupport/businessupports.do?method=edit" paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="businessupport" />
		<display:setProperty name="paging.banner.items_name" value="businessupports" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>