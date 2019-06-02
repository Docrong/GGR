<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="java.util.*" %>
<%
 TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
 String deptid = sessionform.getDeptid();
 System.out.println("lizhi:deptid=" + deptid);
 deptid = "," + deptid + ",";
 String source = request.getParameter("6578706f7274");
 if (source == null) {
 %>
<jsp:include page="/WEB-INF/pages/wfworksheet/${module}/batchJs.jsp"/>
<c:if test="${findForward != null && findForward == 'listall'}">
	<jsp:include page="/WEB-INF/pages/wfworksheet/common/listsendUndoJS.jsp"/>
</c:if>
<%} %>
       

<bean:define id="url" value="commontask.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="listTable taskList"
		export="true" requestURI="commontask.do"
		sort="external" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.commontask.webapp.action.ProcessListCommonDisplaytagDecoratorHelper">
   	<!--display:column sortable="true" property="id" media="html" headerClass="sortable" title="" /-->
   		<display:caption media="html"> 
   			<span class="map alert">将要超时的工单</span>
			<span class="map serious">已经超时的工单</span>
			未调用代维流程
   		</display:caption>
		<display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
		 <display:column property="sheetId" sortable="true" sortName="task.sheetId"
			headerClass="sortable" title="工单流水号" />
			
		<display:column property="title" sortable="true"   sortName="main.title"
			headerClass="sortable" title="工单主题" />
 
		<display:column property="sendTime" sortable="true"  sortName="task.sendTime"
			headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true" sortName="task.completeTimeLimit"
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"  sortName="task.taskDisplayName"
			headerClass="sortable" title="处理环节" />
		<display:column sortable="true"	headerClass="sortable" title="任务状态"  sortName="task.taskStatus">
			<c:if test="${taskList.ifWaitForSubTask=='true'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
			</c:if>
			<c:if test="${empty taskList.ifWaitForSubTask||taskList.ifWaitForSubTask=='false'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskList.taskStatus}" beanId="id2descriptionXML"/>
			</c:if>
		</display:column>
        <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
			<eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemUserDao" />
			<eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemDeptDao" />
		</display:column>
		
		<display:column headerClass="sortable" sortable="true" title="上一级处理部门" sortName="task.preDealDept">
			<eoms:id2nameDB id="${taskList.preDealDept}" beanId="tawSystemDeptDao"/>
		</display:column>
			
		<display:column headerClass="sortable" sortable="true" title="上一级处理人员" sortName="task.preDealUserId">
			<eoms:id2nameDB id="${taskList.preDealUserId}" beanId="tawSystemUserDao"/>
		</display:column>
		
		<display:footer>
				<tr><td colspan="11">
				
				<input type="radio" name="bathTask" value="none"  onclick="checkAll('none')">全不选 
				<input type="button" id="operateId" value="批量处理回复" onclick="loadBatchPage(this)" class="btn">
				</td></tr>
			</display:footer>
		
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
	
	<display:table name="taskListAgent" cellspacing="0" cellpadding="0"
		id="taskListAgent" pagesize="${pageSize}" class="listTable taskListAgent"
		export="true" requestURI="commontask.do"
		sort="external" size="totalAgent" partialList="true"
		decorator="com.boco.eoms.sheet.commontask.webapp.action.ProcessListCommonDisplaytagDecoratorHelper">
   	<!--display:column sortable="true" property="id" media="html" headerClass="sortable" title="" /-->
   		<display:caption media="html"> 
   			<span class="map alert">将要超时的工单</span>
			<span class="map serious">已经超时的工单</span>
			已调用代维流程
   		</display:caption>
		<display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
		 <display:column property="sheetId" sortable="true" sortName="task.sheetId"
			headerClass="sortable" title="工单流水号" />
			
		<display:column property="title" sortable="true"   sortName="main.title"
			headerClass="sortable" title="工单主题" />
 
		<display:column property="sendTime" sortable="true"  sortName="task.sendTime"
			headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="completeTimeLimit" sortable="true" sortName="task.completeTimeLimit"
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="taskDisplayName" sortable="true"  sortName="task.taskDisplayName"
			headerClass="sortable" title="处理环节" />
		<display:column sortable="true"	headerClass="sortable" title="任务状态"  sortName="task.taskStatus">
			<c:if test="${taskListAgent.ifWaitForSubTask=='true'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
			</c:if>
			<c:if test="${empty taskListAgent.ifWaitForSubTask||taskListAgent.ifWaitForSubTask=='false'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskListAgent.taskStatus}" beanId="id2descriptionXML"/>
			</c:if>
		</display:column>
		<display:column sortable="true" headerClass="sortable" title="代维流程是否回复">


			</display:column>
        <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
			<eoms:id2nameDB id="${taskListAgent.taskOwner}" beanId="tawSystemUserDao" />
			<eoms:id2nameDB id="${taskListAgent.taskOwner}" beanId="tawSystemDeptDao" />
		</display:column>
		
		<display:column headerClass="sortable" sortable="true" title="上一级处理部门" sortName="task.preDealDept">
			<eoms:id2nameDB id="${taskListAgent.preDealDept}" beanId="tawSystemDeptDao"/>
		</display:column>
			
		<display:column headerClass="sortable" sortable="true" title="上一级处理人员" sortName="task.preDealUserId">
			<eoms:id2nameDB id="${taskListAgent.preDealUserId}" beanId="tawSystemUserDao"/>
		</display:column>
		
		<display:footer>
				<tr><td colspan="11">
				
				<input type="radio" name="bathTask" value="none"  onclick="checkAll('none')">全不选 
				<input type="button" id="operateId" value="批量处理回复" onclick="loadBatchPage(this)" class="btn">
				</td></tr>
			</display:footer>
		
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>
