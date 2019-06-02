<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
ComplaintDubanTask task = new ComplaintDubanTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
 task = (ComplaintDubanTask)request.getAttribute("task");
 taskStatus = task.getTaskStatus();
 operaterType = task.getOperateType();
	 ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
	 ifwaitfor = StaticMethod.nullObject2String(task.getReplaceWaitForSubTask()); 
}

request.setAttribute("operaterType",operaterType);
BaseMain basemain = (BaseMain)request.getAttribute("sheetMain");
String sendUserId = basemain.getSendUserId();

 request.setAttribute("taskStatus", taskStatus);
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
%>
<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript">
Ext.onReady(function() {
	var tabConfig = {
		items : [{
			id : 'sheetinfo',
			text : '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.historyView"/>',
			url : 'complaintDuban.do?method=showSheetDealList&orderByDetp=true&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		},{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'complaintDuban.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
			isIframe : true
		}, {
			text : '<bean:message bundle="sheet" key="sheet.allSheetsView"/>',
	 		url  : '../sheetRelation/sheetRelation.do?method=showInvokeRelationShipList&sheetKey=${sheetMain.id}',
			isIframe : true
		}]
	};
	var tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);
    <%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
    	<%if(taskName.equals("cc")){ %>
	var url2="complaintDuban.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="complaintDuban.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="complaintDuban.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>
});
function forceOperation(obj){

	if(obj == 1){
	    
		var url2="complaintDuban.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
		eoms.util.appendPage("sheet-deal-content",url2);
		
	}else if(obj == 2){
	     
		var url2="complaintDuban.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
		eoms.util.appendPage("sheet-deal-content",url2);
	}else{
	    
	    var url2="complaintDuban.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
		eoms.util.appendPage("sheet-deal-content",url2);
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="complaintDuban.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
             eoms.util.appendPage("sheet-deal-content",url2,true);
	}
   }
   function operateType(url){
		var R = GetQueryString("operateType",url);
		var operateTypeObj = document.getElementById("operateType");
		operateTypeObj.value = opertateTypeValue;
		var divObj = document.getElementById("templateButtonDiv");
		if (opertateTypeValue == "") {
			divObj.style.display = "none";
		} else {
			divObj.style.display = "";
		}
   }
  function GetQueryString(name,url) {   
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");   
      var r = url.substr(1).match(reg);   
      if (r != null) return unescape(r[2]); return "";   
  }  
   
	function handleCallBackInit(originalRequest)
	{
		var urls= originalRequest.responseText;

	  obj = document.all["tabiframe"];
	  obj.src = urls; 
		
	}
</script>

<h3 class="sheet-title">

</h3>

<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
  <!-- Sheet Detail Tab Start -->
  <div id="sheetinfo">
  	<logic:present name="sheetMain" scope="request">
	<%@ include file="/WEB-INF/pages/wfworksheet/complaintDuban/basedetailnew.jsp"%>
	<br/>
	<table class="formTable"> 
	  <caption></caption>
	  <tr>
		<td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/></td>
		<td class="content">
			${eoms:date2String(sheetMain.sheetAcceptLimit)}
		</td>
		<td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/></td>
		<td class="content">
			${eoms:date2String(sheetMain.sheetCompleteLimit)}
		</td>
	  </tr>	
	  <tr>
		  <td class="label">
		  	 投诉跟踪工单流水号
		  </td>
		  <td class="content">
		  	${sheetMain.trailId}
		  </td>	
		  <td class="label">
		  	 EOMS投诉工单流水号
		  </td>
		  <td class="content">
		  	${sheetMain.eomsId}
		  </td>		  
		</tr> 
	  <tr>
		  <td class="label">
		  	 投诉类别
		  </td>
		  <td class="content">
		  	${sheetMain.complaintSort}
		  </td>	
		  <td class="label">
		  	 投诉现象
		  </td>
		  <td class="content">
		  	${sheetMain.appearance}
		  </td>		  
		</tr> 
	  <tr>
		  <td class="label">
		  	 受理号码
		  </td>
		  <td colspan="3">
		  	${sheetMain.acceptNum}
		  </td>		  
		</tr> 
	  <tr>
		  <td class="label">
		  	 报结意见
		  </td>
		  <td colspan="3">
		  	${sheetMain.opinion}
		  </td>		  
		</tr> 
	  <tr>
		  <td class="label">
		  	 <bean:message bundle="complaintDuban" key="complaintDuban.complaintInfo" />*
		  </td>
		  <td colspan="3">
		  	${sheetMain.complaintInfo}
		  </td>		  
		</tr> 
		<tr>
		  <td class="label">
		  	 <bean:message bundle="complaintDuban" key="complaintDuban.complaintText" />*
		  </td>
		  <td colspan="3">
		  	${sheetMain.complaintText}
		  </td>		  
		</tr>
		<tr>
		    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
				    <td colspan='3'>
				    <eoms:attachment name="sheetMain" property="sheetAccessories" 
				            scope="request" idField="sheetAccessories" appCode="commontask" 
				             viewFlag="Y"/> 
				    </td>
				</tr> 	  
	</table>
    </logic:present>
  </div>
  <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->

<%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>

<c:url var="urlShowAssessorDealPage" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="200"/>
</c:url>


<!-- 草稿派发 -->
<c:url var="urlShowDraftDeal" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="3"/>
</c:url> 

<!-- 重新派发 -->
<c:url var="urlShowBackDeal" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="54"/>
  <c:param name="backFlag" value="yes"/> 
</c:url>

<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="18"/>
</c:url>

<!-- 退回 -->
<c:url var="urlShowRejectBackDealPage" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="102"/> 
</c:url>

<!-- 转派 -->
<c:url var="urlShowTransferkPage" value="complaintDuban.do">
  <c:param name="method" value="showTransferWorkItemPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="8"/>
</c:url>
<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="complaintDuban.do">
  <c:param name="method" value="showTransferWorkItemPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="88"/>
</c:url>

<!-- 阶段回复 -->
<c:url var="urlShowPhaseReplyPage" value="complaintDuban.do">
  <c:param name="method" value="showPhaseBackToUpPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="reply"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operaterType" value="${operaterType}"/>
  <c:param name="displayTaskName" value="${taskName}"/>  
</c:url>

<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="4"/>
</c:url>

<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="61"/>  
</c:url>

<!-- 分派 -->
<c:url var="urlShowInputSplit" value="complaintDuban.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ComplaintDubanSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="10"/>  
</c:url>

<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="complaintDuban.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ComplaintDubanSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="30"/>  
</c:url>

<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ComplaintDubanSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="11"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="complaintDuban.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ComplaintDubanSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="55"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>
<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="complaintDuban.do">
  <c:param name="method" value="showDealReplyAcceptPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ComplaintDubanSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="6"/>
</c:url>
<!-- 处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="complaintDuban.do">
  <c:param name="method" value="showDealReplyRejectPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ComplaintDubanSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>


<div class="sheet-deal">
	<div class='sheet-deal-header'>
		<table>
		  <tr>
		    <td width="50%">
		    <%if(!taskName.equals("cc")) { 
		    	if (!taskName.equals("reply")) { 
		    		if (!taskName.equals("advice")) {%>
		 		<bean:message bundle="sheet" key="sheet.deal"/>:
		 	<%  	} 
		 		} 
		 	 }
		 	 
		 	%>
		<% if(taskName.equals("Assessor")){%>	
	  		  <select id="dealSelector">
			  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
			    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		            <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
			 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>  
				<!-- 是父任务 -->
				<%if(ifsub.equals("") || ifsub.equals("false")){ %>
					<% if (ifwaitfor.equals("false")||ifwaitfor.equals("")) {%>
			    <option value="${urlShowAssessorDealPage}">审核通过</option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>	   
<!--	  		    <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>-->
<!--			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>-->
			     
					<%} %>
				<%} else { %>
					<!-- 有子任务 -->
					<% if (ifwaitfor.equals("false")||ifwaitfor.equals("")) {%>
					<option value="${urlShowDispatchPage}">分派回复</option>
					<%} %>
				<% } %>
			    <c:if test="${needDealReply == 'true'}">
			       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			    </c:if>					
				<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>		  
	  		  
	  		  <%} %>
			</select>
		<%}else if(taskName.equals("DraftTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowDraftDeal}"><bean:message bundle="complaintDuban" key="operateType.draft"/></option>
			</select>
		<%}else if(taskName.equals("RejectTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowBackDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
			</select>
		<%}else if(taskName.equals("HoldTask")){ %>
			<select id="dealSelector">
			  <option value="${urlShowHoldDealPage}"><bean:message bundle="complaintDuban" key="operateType.hold"/></option>
			</select>		
		<%} %>
		</td>
		</tr>
		</table>
	</div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>


<script type="text/javascript">
	Ext.onReady(function(){	
	eoms.Sheet.setPageLoader("dealSelector","sheet-deal-content");
	var url = "";
	try{
		 url = $("dealSelector").value + "&taskStatus=${taskStatus}";
		}catch(e){}
		var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
   			url = "complaintDuban.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
        }
		eoms.util.appendPage("sheet-deal-content",url);		
	});
	var selector = document.getElementById("dealSelector");
	var operateType = "${operateType}";
	if (operateType != "") {
		for (var i = 0; i < selector.length; i++){
				var selectorValue = selector[i].value;
				var splitArray = selectorValue.split("&operateType=");
				if (splitArray.length == 2 ){
					if (splitArray[1] == operateType) {
						selector.selectedIndex = i;
						break;
					}
				}
		}
	}
</script>
<!-- Sheet Deal Content End -->
<%}%>

<c:if test="${sheetMain.status==0}"> 
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
 <%if(isAdmin.equals("true")){%>
 	<div id="buttonDisplay" style="display:block">
    <input type="button" title="执行该操作，工单将进入强制归档状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceHold"/>" onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
    <input type="button" title="执行该操作，工单将进入强制作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceNullity"/>" onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
    <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
 <% }else if(taskStatus.equals("5")&& !taskName.equals("cc")&& !taskName.equals("reply")&& !taskName.equals("advice")){%>
     <div id="buttonDisplay">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
     </div>
 <% }else if((taskStatus == null || taskStatus.equals(""))&&(userId.equals(sendUserId))){%> 
 <div id="advice" style="display:block">
     <input type="button" title="执行该操作，工单将进入作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.nullity"/>" onclick="$('advice').style.display='none';forceOperation(2);">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('advice').style.display='none';eventOperation(1);">
  </div>
 <% }%> 
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>
