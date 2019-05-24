<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="com.boco.eoms.commons.statistic.base.util.StatAttributeUrlLocator"%>

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
<!-- bean:define id="url" value="stat.do" / -->
<fmt:bundle basename="config/statistic/task-config/ApplicationResources-statistic-task">
	<display:table name="estList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table estList"
		export="false" requestURI="stat.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.commons.statistic.commonstat.util.CommonStatListDisplayHelper">


	
	<display:column property="areaName" sortable="true"
		headerClass="sortable" titleKey="list.areaName" />

	<display:column property="deptName" sortable="true"
		headerClass="sortable" titleKey="list.deptName" />


	</display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
