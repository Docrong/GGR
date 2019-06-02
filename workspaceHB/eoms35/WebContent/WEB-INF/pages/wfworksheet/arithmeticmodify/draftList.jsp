<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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

<bean:define id="url" value="arithmeticmodify.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="arithmeticmodify.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		 <display:column property="sheetId" sortable="true"
			headerClass="sortable" title="工单流水号" />
			
		<display:column property="title" sortable="true"
			headerClass="sortable" title="主题" />

		<display:column property="createTime" sortable="true"
			headerClass="sortable" title="创建时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" title="工单环节" />

		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>