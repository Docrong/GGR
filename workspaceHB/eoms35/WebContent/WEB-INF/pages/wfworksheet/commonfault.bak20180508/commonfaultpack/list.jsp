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

<bean:define id="url" value="../commonfault/commonfaultpack.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="../commonfault/commonfaultpack.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.commonfaultpack.webapp.action.faultpackListDisplaytagDecoratorHelper">

		 <display:column property="mainAlarmId" sortable="true"
			headerClass="sortable" title="${eoms:a2u('')}" /> 
			
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>
