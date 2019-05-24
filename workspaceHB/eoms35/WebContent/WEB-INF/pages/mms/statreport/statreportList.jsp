<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/statreports.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResources-mms">
<bean:define id="url" value="statreports.do" />
<content tag="heading">
	<fmt:message key="statreport.list.heading" />
</content>

	<display:table name="statreportList" cellspacing="0" cellpadding="0"
		id="statreportList" pagesize="${pageSize}" class="table statreportList"
		export="false"
		requestURI="statreports.do"
		sort="list" partialList="true" size="resultSize"
		decorator="com.boco.eoms.commons.mms.statreport.util.StatReportDisplaytagDecoratorHelper">

<% /* 
	<display:column property="id" sortable="true"
			headerClass="sortable" titleKey="statreport.id" />
	 */%>		

	<display:column property="reportName" sortable="true"
			headerClass="sortable" titleKey="statreport.reportName"/>
	
	<display:column property="userid" sortable="true"
			headerClass="sortable" titleKey="statreport.userid"/>
	
	<display:column property="reportType" sortable="true"
			headerClass="sortable" titleKey="statreport.reportType"/>
			
	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="statreport.createTime"/>
			
	<display:column property="showDetail" sortable="true"
		headerClass="sortable" titleKey="statreport.showDetail" />


		<display:setProperty name="paging.banner.item_name" value="statreport" />
		<display:setProperty name="paging.banner.items_name" value="statreports" />
	</display:table>

<!--  	<c:out value="${buttons}" escapeXml="false" /> -->

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>