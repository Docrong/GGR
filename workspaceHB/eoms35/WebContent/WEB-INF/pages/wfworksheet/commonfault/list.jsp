<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<% 
 TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
 String deptid = sessionform.getDeptid();
 String userId = sessionform.getUserid();
 String source = request.getParameter("6578706f7274");
 if (source == null) {
 %>
<jsp:include page="/WEB-INF/pages/wfworksheet/${module}/batchJs.jsp"/>
<c:if test="${findForward != null && findForward == 'list'}">
<jsp:include page="/WEB-INF/pages/wfworksheet/commonfault/listsendUndoJSQuery.jsp"/>
</c:if>
<%} %>


<bean:define id="url" value="commonfault.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="listTable taskList"
		export="true" requestURI="commonfault.do"
		sort="external" size="total" partialList="true" 
		decorator="com.boco.eoms.sheet.commonfault.webapp.action.ProcessListCommonFaultDisplaytagDecoratorHelper">
   	<%  if (source == null) { %>
   		<display:caption media="html"> 
   			<span class="map alert colorrow">将要超时的工单</span>
			<span class="map serious">已经超时的工单</span>
   		</display:caption>
		<display:column sortable="true" property="id" media="html"
			headerClass="sortable" title="" />
 		<display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
		<%} %>
		
			
		<display:column  sortable="true"   sortName="sheetId" headerClass="sortable" title="工单流水号" >
	       <a href="${app}/sheet/commonfault/commonfault.do?method=showDetailPage&sheetKey=${taskList.sheetKey}&taskId=${taskList.id}&taskName=${taskList.taskName}&operateRoleId=${taskList.operateRoleId}&TKID=${taskList.id}&piid=${taskListAgent.processId}&taskStatus=${taskList.taskStatus}&preLinkId=${taskList.preLinkId}&type=interface&interface=interface&userName=<%=userId%>">
	       ${taskList.sheetId }	
	       </a>
		</display:column>
			
		<display:column property="title" sortable="true" sortName="main.title"
			headerClass="sortable" title="工单主题" />
		<display:column property="mainNetName" sortable="true" sortName="main.mainNetName"
			headerClass="sortable" title="网元名称" />
		<display:column  sortable="true" sortName="main.mainNetSortOne"
			headerClass="sortable" title="网络一级分类">
			<eoms:id2nameDB id="${taskList.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
		<display:column  sortable="true" sortName="main.mainNetSortTwo"
			headerClass="sortable" title="网络二级分类">
			<eoms:id2nameDB id="${taskList.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
		<display:column  sortable="true" sortName="main.mainNetSortThree"
			headerClass="sortable" title="网络三级分类">
			<eoms:id2nameDB id="${taskList.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
		</display:column>	
		<display:column  sortable="true" sortName="main.mainFaultResponseLevel"
			headerClass="sortable" title="故障处理响应级别">
			<eoms:id2nameDB id="${taskList.mainFaultResponseLevel}" beanId="ItawSystemDictTypeDao"/>
		</display:column>	
							
		<display:column property="sendTime" sortable="true"  sortName="task.sendTime"
			headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />	
		<display:column property="completeTimeLimit" sortable="true"  sortName="task.completeTimeLimit"
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		
		<display:column  sortable="true" sortName="task.taskDisplayName" headerClass="sortable" title="处理环节">
			${taskList.taskDisplayName}
			<c:if test="${taskList.sendRejectFlag=='0'&&taskList.taskName=='FirstExcuteHumTask'}">-新建派发</c:if>
			<c:if test="${taskList.sendRejectFlag=='4'&&taskList.taskName=='FirstExcuteHumTask'}">-驳回</c:if>	
		</display:column>
		
		<display:column sortable="true"	headerClass="sortable" title="任务状态" sortName="task.taskStatus">
		
			<c:if test="${taskList.ifWaitForSubTask=='true'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
			</c:if>
			<c:if test="${empty taskList.ifWaitForSubTask||taskList.ifWaitForSubTask=='false'}">
				<eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskList.taskStatus}" beanId="id2descriptionXML"/>
			</c:if>
		</display:column>
        <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
			<eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemUserDao" />
		</display:column>
		<display:column  sortable="true" sortName="main.perAllocatedUser"
			headerClass="sortable" title="预分配人员">
			<eoms:id2nameDB id="${taskList.perAllocatedUser}" beanId="tawSystemUserDao"/>
		</display:column>
		<display:column property="mainAlarmSolveDate" sortable="true"  sortName="main.mainAlarmSolveDate"
			headerClass="sortable" title="告警清除时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />			
		<display:column sortable="true"	headerClass="sortable" title="告警核查状态" sortName="task.checkStatus">
			<eoms:dict key="dict-sheet-common" dictId="checkStatus" itemId="${taskList.mainCheckStatus}"  beanId="id2descriptionXML"/>
		</display:column>
				<display:column  sortable="true" headerClass="sortable" title="T1一键回复">
			<c:if test="${not empty taskList.mainAlarmSolveDate && ''!=taskList.mainAlarmSolveDate && 'FirstExcuteHumTask'==taskList.taskName}">
			<input type="button" class="submit" id="onekey" name="onekey" value="T1一键回复" onclick="onekeyreply(this,'${taskList.sheetKey}')"/>
			</c:if>
		</display:column>
			
	<%  if (source == null&&("12201".equals(deptid)||"134".equals(deptid))) { %>
		<logic:notEmpty name="batchTaskMapKey">
			<display:footer>
				<tr><td colspan="15">
				
				<input type="radio" name="bathTask" value="none"  onclick="checkAll('none')" checked="checked"> 全不选

				
				<logic:iterate id="batchTaskMapKey" name="batchTaskMapKey">
						<input type="radio" name="bathTask" value="${batchTaskMapKey}" onclick="checkAll('${batchTaskMapKey}')"> ${batchTaskMap[batchTaskMapKey]}
				</logic:iterate>

				<c:choose>
				<c:when test="${fn:length(batchTaskMap) == 1 && batchTaskMap['HoldHumTask'] != null}">
					<input type="button" id="holdId" value="批量归档" onclick="loadBatchPage(this)" class="btn" operateType="18" taskName="HoldHumTask">
				</c:when>
				<c:when test="${batchTaskMap['HoldHumTask'] == null}">
					<input type="button" value="批量回复" onclick="loadBatchPage(this)" class="btn" operateType="46">
				</c:when>
				<c:when test="${fn:length(batchTaskMap) > 1 && batchTaskMap['HoldHumTask'] != null}">
					<input type="button" id="holdId" value="批量归档" onclick="loadBatchPage(this)" class="btn" operateType="18" taskName="HoldHumTask">
					<input type="button" value="批量回复" onclick="loadBatchPage(this)" class="btn" operateType="46">
				</c:when>
				</c:choose>
				
				</td></tr>
			</display:footer>
		</logic:notEmpty>
		<%} %>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
	
<script type="text/javascript">
function onekeyreply(onekey,sheetKey) {
	Ext.Ajax.request({
		url : "${app}/sheet/commonfault/commonfault.do?method=getReply&sheetKey="+sheetKey,				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			var reply = data[0].reply;
			if ('false'==reply) {
				alert('工单不在T1环节');
			}
		}
	});
onekey.disabled = true;
}
</script>
<%@ include file="/common/footer_eoms.jsp"%>