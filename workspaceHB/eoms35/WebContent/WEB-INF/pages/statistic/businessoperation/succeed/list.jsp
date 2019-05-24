<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="com.boco.eoms.commons.statistic.base.util.StatAttributeUrlLocator"%>
<%@ page import="java.util.*"%>

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
<fmt:bundle basename="config/statistic/businessoperation-config/ApplicationResources-statistic-businessoperation">
	<display:table name="estList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table estList"
		export="false" requestURI="stat.do"
		sort="list" size="total" partialList="true">

	<display:column headerClass="sortable" titleKey="list.no"
	 sortable="true"><%=pageContext.getAttribute("process_rowNum")%></display:column>

	<display:column property="sheetid" sortable="true"
		headerClass="sortable" titleKey="list.sheetid" href="${app}/sheet/businessoperation/businessoperation.do?method=showDetailPage" paramId="sheetKey" paramProperty="mainid"/>

	<display:column property="title" sortable="true"
		headerClass="sortable" titleKey="list.title" />
		
	<display:column property="mainproductcode" sortable="true"
		headerClass="sortable" titleKey="list.mainProductCode" />

	<display:column property="endtime" sortable="true"
		headerClass="sortable" titleKey="list.mainEndTime"
		format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		
	<%--<display:column property="overtimeDept" sortable="true"
		headerClass="sortable" titleKey="list.overtimeDept" />

      
	--%></display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
