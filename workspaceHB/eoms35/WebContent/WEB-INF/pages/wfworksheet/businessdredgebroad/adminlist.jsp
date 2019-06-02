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
<bean:define id="url" value="businessdredgebroad.do" />
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table taskList"
		export="true" requestURI="businessdredgebroad.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.AdminListDisplaytagDecoratorHelper">

         <display:column property="sheetId" sortable="true"
			headerClass="sortable" title="${eoms:a2u('工单流水号')}"/>
			
		<display:column property="title" sortable="true"
			headerClass="sortable" title="${eoms:a2u('工单主题')}"  />

		<display:column property="sheetAcceptLimit" sortable="true"
			headerClass="sortable" title="${eoms:a2u('受理时限')}"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="sheetCompleteLimit" sortable="true"
			headerClass="sortable" title="${eoms:a2u('处理时限')}"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
			
        <display:column sortable="true" headerClass="sortable" title="${eoms:a2u('工单状态')}">
           <eoms:dict key="dict-sheet-common" dictId="sheetStatus" itemId="${taskList.status}" beanId="id2descriptionXML" />  
		</display:column>
		
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>

	</display:table>
<!-- 

<%@ include file="/common/footer_eoms.jsp"%>
