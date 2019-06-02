<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
function openSheet(url){
	if(parent.frames['portal-north']){
		parent.frames['portal-north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
</script>
<logic:notPresent name="recordTotal">
<bean:define id="url" value="daiweiindexreduction.do" />
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
		export="true" requestURI="daiweiindexreduction.do"
		sort="external" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.QueryListDisplaytagDecoratorHelper">

		<display:column property="sheetId" sortable="true"
			headerClass="sortable" title="工单流水号" sortName="sheetId" />
			
		<display:column property="title" sortable="true"
			headerClass="sortable" title="工单主题"  sortName="title" />

		<display:column property="sendTime" sortable="true" sortName="sendTime" 
			headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="sheetAcceptLimit" sortable="true" sortName="sheetAcceptLimit" 
			headerClass="sortable" title="受理时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="sheetCompleteLimit" sortable="true" sortName="sheetCompleteLimit" 
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
			
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>

	</display:table>
</logic:notPresent>
<logic:present name="recordTotal">
	<center>
	<bean:message bundle="sheet" key="worksheet.query.total" />${recordTotal}<bean:message bundle="sheet" key="worksheet.query.totalNumber" />
	</center>
</logic:present>

<%@ include file="/common/footer_eoms.jsp"%>