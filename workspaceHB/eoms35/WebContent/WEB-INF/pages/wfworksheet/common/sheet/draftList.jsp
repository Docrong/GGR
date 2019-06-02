<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
function openSheet(url){
	if(parent.frames['north']){
		parent.frames['north'].location.href = url;
	} else {
		location.href = url;
	}
}
</script>
<bean:define id="url" value="${module}.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
	id="process" pagesize="${pageSize}" class="table resourceAssessTable"
	export="true" requestURI="${module}.do"
	sort="list" size="total" partialList="true"
	decorator="com.boco.eoms.sheet.base.webapp.action.DraftListDisplaytagDecoratorHelper">

	 <display:column property="sheetId" sortable="true"
		headerClass="sortable" title="工单流水号" />
		
	<display:column property="title" sortable="true"
		headerClass="sortable" title="工单主题" />

	<display:column property="sendTime" sortable="true"
		headerClass="sortable" title="创建时间"
		format="{0,date,yyyy-MM-dd HH:mm:ss}" />

	<display:column property="taskDisplayName" sortable="true"
		headerClass="sortable" title="处理环节" />

	<display:setProperty name="export.pdf" value="false"/>
	<display:setProperty name="export.xml" value="false"/>
	<display:setProperty name="export.csv" value="false"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>