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

<%
	String reUrl = String.valueOf(request.getRequestURL()) + "?method=statistFileInfo"; 
	out.println(reUrl);
 %>
 
<!-- bean:define id="url" value="stat.do" / -->
<fmt:bundle basename="config/statistic/ApplicationResources-statistic">
	<display:table name="estList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table estList"
		export="false" requestURI="stat.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.commons.statistic.base.util.BaseStatListDisplaytagDecoratorHelper">

	<display:column headerClass="sortable" titleKey="list.no"
	 sortable="true"><%=pageContext.getAttribute("process_rowNum")%></display:column>

	<!-- display:column property="subscibeid" sortable="true"
		headerClass="sortable" titleKey="custom.subscibeid" /-->

	<display:column property="department" sortable="true"
		headerClass="sortable" titleKey="custom.department" />

	<display:column property="person" sortable="true"
		headerClass="sortable" titleKey="custom.person" />

	<display:column property="statName" sortable="true"
		headerClass="sortable" titleKey="custom.statName" paramId="subscibeid" paramProperty="id" href="<%=reUrl%>"/>

	<display:column property="reportType" sortable="true"
		headerClass="sortable" titleKey="custom.reportType" />
		
	<display:column property="customTime" sortable="true"
		headerClass="sortable" titleKey="custom.customTime" />
		
	<display:column property="customDescribe" sortable="true"
		headerClass="sortable" titleKey="custom.customDescribe" />

	<!--display:column property="sendtime" sortable="true"
		headerClass="sortable" titleKey="list.sendtime"
		format="{0,date,yyyy-MM-dd HH:mm:ss}" /-->

      
	</display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
