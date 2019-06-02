<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/mmsreports.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="config/applicationResources-mms">
<bean:define id="url" value="mmsreports.do" />
<content tag="heading">
	<fmt:message key="mmsreport.list.heading" />
</content>

	<display:table name="mmsreportList" cellspacing="0" cellpadding="0"
		id="mmsreportList" pagesize="${pageSize}" class="table mmsreportList"
		export="false"
		requestURI="mmsreports.do"
		sort="list" partialList="true" size="resultSize"
		decorator="com.boco.eoms.commons.mms.mmsreport.util.MmsReportDisplaytagDecoratorHelper">
		
	<display:column property="mmsReportName" sortable="true"
			headerClass="sortable" titleKey="mmsreport.mmsReportName"/>
			
	<display:column property="userid" sortable="true"
			headerClass="sortable" titleKey="mmsreport.userid"/>
	
	<display:column property="mmsreportType" sortable="true"
			headerClass="sortable" titleKey="mmsreport.mmsreportType" />

	<display:column property="mmsReportCreateDate" sortable="true"
			headerClass="sortable" titleKey="mmsreport.mmsReportCreateDate" />
			
	<display:column property="sendStatus" sortable="true"
			headerClass="sortable" titleKey="mmsreport.sendStatus" />
			
	<display:column property="showDetail" sortable="true"
		headerClass="sortable" titleKey="mmsreport.showDetail" />
	</display:table>


</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>