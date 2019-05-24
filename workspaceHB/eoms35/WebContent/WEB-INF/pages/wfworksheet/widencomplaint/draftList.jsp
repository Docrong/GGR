<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

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

<bean:define id="url" value="widencomplaint.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="widencomplaint.do"
		sort="external" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		 <display:column property="sheetId" sortable="true" sortName="task.sheetId"
			headerClass="sortable" title="工单流水号"/>
			
		<display:column property="title" sortable="true"  sortName="main.title"
			headerClass="sortable" title="工单主题"/>

		<display:column property="createTime" sortable="true"  sortName="task.createTime"
			headerClass="sortable" 
			format="{0,date,yyyy-MM-dd HH:mm:ss}" title="创建时间" />

		<display:column property="taskDisplayName" sortable="true" sortName="task.taskDisplayName"
			headerClass="sortable" title="处理环节"/>

		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>