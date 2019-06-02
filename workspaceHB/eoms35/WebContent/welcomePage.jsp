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
request.setAttribute("total",new Integer(2));
%>

<fmt:bundle basename="config/ApplicationResources-sheet">	
<logic:notEmpty name="SeResourceAssessList">
	<display:table name="SeResourceAssessList" cellspacing="0" cellpadding="0"
		id="process" pagesize="5" class="table resourceAssessTable"
		export="true" requestURI="ResourceAssessAction.do"
		sort="list" size="SeResourceAssessTotal" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		<display:caption><font size=2 color=blue>${eoms:a2u('安全审计工单')}</font></display:caption>
		
		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" paramName="sss"/>

		<display:column property="acceptTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

		<display:column property="completeTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" titleKey="task.stepName" />

	</display:table>
</logic:notEmpty>
	<p>
<logic:notEmpty name="SeSpecificationList">
	<display:table name="SeSpecificationList" cellspacing="0" cellpadding="0"
		id="process" pagesize="5" class="table SeSpecification"
		export="false" requestURI="SeSpecification.do"
		sort="list" size="SeSpecificationTotal" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		<display:caption><font size=2 color=blue>${eoms:a2u('安全评估工单')}</font></display:caption>
		
		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" />

		<display:column property="acceptTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

		<display:column property="completeTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" titleKey="task.stepName" />

	</display:table>
</logic:notEmpty>
	<p>
<logic:notEmpty name="NopList">
	<display:table name="NopList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="Nop.do"
		sort="list" size="NopTotal" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">
    
    	<display:caption><font size=2 color=blue>${eoms:a2u('网优工单')}</font></display:caption>
    	
       	<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" />
		

		<display:column property="acceptTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" titleKey="task.stepName" />

	</display:table>
</logic:notEmpty>
	<p>
<logic:notEmpty name="performanceList">
	<display:table name="performanceList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="performance.do?method=showListUndo"
		sort="list" size="performanceTotal" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		<display:caption><font size=2 color=blue>${eoms:a2u('性能管理工单')}</font></display:caption>
		
		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" />

		<display:column property="acceptTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" titleKey="task.stepName" />

	</display:table>
</logic:notEmpty>
	<p>
<logic:notEmpty name="AbrEventList">
	<display:table name="AbrEventList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="AbrupEvent.do?method=showListUndo"
		sort="list" size="AbrEventTotal" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		<display:caption><font size=2 color=blue>${eoms:a2u('突发事件应急通信保障工单')}</font></display:caption>
		
		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" />

		<display:column property="acceptTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" titleKey="task.stepName" />

	</display:table>
</logic:notEmpty>
	<p>
<logic:notEmpty name="BigActList">
	<display:table name="BigActList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="BigActivity.do?method=showListUndo"
		sort="list" size="BigActTotal" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		<display:caption><font size=2 color=blue>${eoms:a2u('重大活动应急通信保障工单')}</font></display:caption>
		
		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" />

		<display:column property="acceptTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" titleKey="task.stepName" />

	</display:table>
</logic:notEmpty>
	<p>
<logic:notEmpty name="DesignList">
	<display:table name="DesignList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="Design.do?method=showListUndo"
		sort="list" size="DesignTotal" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		<display:caption><font size=2 color=blue>${eoms:a2u('应急预案制定工单')}</font></display:caption>
		
		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" />

		<display:column property="acceptTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" titleKey="task.stepName" />

	</display:table>
</logic:notEmpty>
	<p>
<logic:notEmpty name="DrilList">
	<display:table name="DrilList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table resourceAssessTable"
		export="true" requestURI="Drilling.do?method=showListUndo"
		sort="list" size="DrilTotal" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">

		<display:caption><font size=2 color=blue>${eoms:a2u('应急预案演练工单')}</font></display:caption>
		
		<display:column property="title" sortable="true"
			headerClass="sortable" titleKey="task.title" />

		<display:column property="acceptTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.acceptTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true"
			headerClass="sortable" titleKey="task.completeTimeLimit"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"
			headerClass="sortable" titleKey="task.stepName" />

	</display:table>
</logic:notEmpty>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
