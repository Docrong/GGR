<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
function openSheet(url){
	if(parent.frames['north']){
		parent.frames['north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
</script>
<bean:define id="url" value="rescheck.do" />
<fmt:bundle basename="config/ApplicationResources-sheet">
	<display:table name="mainList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table seResourceAssessMain"
		export="true" requestURI="rescheck.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.commons.statistic.base.util.KpiMainListDisplaytagDecoratorHelper">

		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" />

		<display:column property="sheetAcceptLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="sheetCompleteLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
      
	</display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
