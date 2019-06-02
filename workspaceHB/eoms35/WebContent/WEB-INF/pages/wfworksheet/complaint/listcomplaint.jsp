<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<script type="text/javascript">
function openSheet(url){
	if(parent.frames['north']){
		parent.frames['north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
</script>
<% String source = request.getParameter("6578706f7274");
 if (source == null) {
 %>
<c:if test="${findForward != null && findForward == 'listall'}">
	<jsp:include page="/WEB-INF/pages/wfworksheet/common/listsendUndoJS.jsp"/>
</c:if>
<%} %>
<bean:define id="url" value="complaint.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="listTable taskList"
		export="true" requestURI="complaint.do"
		sort="external" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">
   		<display:caption media="html"> 
   			<span class="map alert">将要超时的工单</span>
			<span class="map serious">已经超时的工单</span>
			未调用代维流程
   		</display:caption>
   		
   		<%
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		String userIds = "huxing,pengguangjun,";
		
		if(userIds.indexOf(userId+",")!=-1){  
			System.out.println("包含"); 
		%>
   		
   		<display:caption media="html"> 
   			<span>
   				<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./complaint.do?method=getAccessories')">
	        		投诉百科
	    		</html:button>
   			</span>
   		</display:caption>
   		
   		<%} %>
   		
		<display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
		 <display:column property="sheetId" sortable="true"  sortName="task.sheetId"
			headerClass="sortable" title="工单流水号" />
			
		<display:column property="title" sortable="true"   sortName="main.title"
			headerClass="sortable" title="工单主题" />

		<display:column property="sendTime" sortable="true" sortName="task.sendTime"
			headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true" sortName="task.completeTimeLimit"
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true" sortName="task.taskDisplayName"
			headerClass="sortable" title="处理环节" />
		<display:column sortable="true"	headerClass="sortable" title="任务状态" sortName="task.taskStatus">
		  <c:if test="${taskList.ifRejectOrYijiao!='1'}"> 
			<c:if test="${taskList.ifWaitForSubTask=='true'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
			</c:if>
			<c:if test="${empty taskList.ifWaitForSubTask||taskList.ifWaitForSubTask=='false'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskList.taskStatus}" beanId="id2descriptionXML"/>
			</c:if>
		  </c:if>	
		  <c:if test="${taskList.ifRejectOrYijiao=='1'}">	
		         ${taskList.taskStatus1}  
		  </c:if>	
		</display:column>
        <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
			<eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemUserDao" />
		</display:column>
	<display:column  title="重复投诉情况" property="customPhoneRepeatCount" sortName="main.customPhoneRepeatCount">
			 
		</display:column>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
	
	<display:table name="taskListAgent" cellspacing="0" cellpadding="0"
		id="taskListAgent" pagesize="${pageSize}" class="listTable taskListAgent"
		export="true" requestURI="complaint.do"
		sort="external" size="totalAgent" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper">
   		<display:caption media="html"> 
   			<span class="map alert">将要超时的工单</span>
			<span class="map serious">已经超时的工单</span>
			已调用代维流程
   		</display:caption>
		<display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
		 <display:column property="sheetId" sortable="true"  sortName="task.sheetId"
			headerClass="sortable" title="工单流水号" />
			
		<display:column property="title" sortable="true"   sortName="main.title"
			headerClass="sortable" title="工单主题" />

		<display:column property="sendTime" sortable="true" sortName="task.sendTime"
			headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true" sortName="task.completeTimeLimit"
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true" sortName="task.taskDisplayName"
			headerClass="sortable" title="处理环节" />
		<display:column sortable="true"	headerClass="sortable" title="任务状态" sortName="task.taskStatus">
			<c:if test="${taskListAgent.ifWaitForSubTask=='true'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
			</c:if>
			<c:if test="${empty taskListAgent.ifWaitForSubTask||taskListAgent.ifWaitForSubTask=='false'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskListAgent.taskStatus}" beanId="id2descriptionXML"/>
			</c:if>
		</display:column>
		<display:column sortable="true" headerClass="sortable" title="代维流程是否回复">
			<c:if test="${taskListAgent.revert=='1'}">
				是
				</c:if>
			<c:if test="${taskListAgent.revert=='0'||empty taskListAgent.revert}">
				否
				</c:if>
			</display:column>
        <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
			<eoms:id2nameDB id="${taskListAgent.taskOwner}" beanId="tawSystemUserDao" />
		</display:column>
		
		<display:column  title="重复投诉情况" property="customPhoneRepeatCount" sortName="main.customPhoneRepeatCount">
			 
		</display:column>
					
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>