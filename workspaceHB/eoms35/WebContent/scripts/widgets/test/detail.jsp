<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
CommonFaultTask task = new CommonFaultTask();
String operaterType = "";
if(request.getAttribute("task")!=null){
 task = (CommonFaultTask)request.getAttribute("task");
 taskStatus = task.getTaskStatus();
 operaterType = task.getOperateType();
}

request.setAttribute("operaterType",operaterType);
BaseMain basemain = (BaseMain)request.getAttribute("sheetMain");
String sendUserId = basemain.getSendUserId();
System.out.println("sendUserId>>>>>>"+sendUserId);

 request.setAttribute("taskStatus", taskStatus);
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
  String ifInvokeUrgentFault = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvokeUrgentFault"));
 System.out.println("taskName>>>>>>"+taskName);
 System.out.println("ifInvokeUrgentFault>>>>>>"+ifInvokeUrgentFault); 
%>


<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript">
Ext.onReady(function() {
	var tabConfig = {
		items : [{
			id : 'sheetinfo',
			text : '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.historyView"/>',
			url : 'commonfault.do?method=showSheetDealList&sheetKey=${sheetMain.id}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			//url : '/ProcessMonitor/runtime/html/index.jsp?appName=CommonFaultProcessApp&templateName=CommonFaultMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
			url : 'commonfault.do?method=showPic',
			isIframe : true
		},{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'commonfault.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
			isIframe : true
		}, {
			text : '<bean:message bundle="sheet" key="sheet.allSheetsView"/>',
	 		url  : 'commonfault.do?method=showInvokeRelationShipList&id=${sheetMain.id}',
			isIframe : true
		}]
	};
	var tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);
    <%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
    	<%if(taskName.equals("cc")){ %>
	var url2="commonfault.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="commonfault.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="commonfault.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>
 
	

});
function forceOperation(obj){

	if(obj == 1){
	     window.location.href='commonfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold';
	     
	}else if(obj == 2){
	     window.location.href='commonfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity';
	
	}else{
	     window.location.href='commonfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity';
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="commonfault.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
</script>

<h3 class="sheet-title">

</h3>

<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
  <!-- Sheet Detail Tab Start -->
  <div id="sheetinfo">
  	<logic:present name="sheetMain" scope="request">
	<%@ include file="/WEB-INF/pages/wfworksheet/commonfault/basedetailnew.jsp"%>
	<br/>
	

	<table class="listTable"> 
	  <caption></caption>	  
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmId"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainAlarmId" scope="request"/></td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmNum"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainAlarmNum" scope="request"/></td>
	  </tr>  	  
	  
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmSolveDate"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="mainAlarmSolveDate" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmSource"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="mainAlarmSource" scope="request"/>
		</td>
	  </tr>  

	<tr>
	  <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmLogicSort"/></td>
	  <td class="content">
	  	<bean:write name="sheetMain" property="mainAlarmLogicSort" scope="request"/>
	  </td>
	  <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmLogicSortSub"/></td>
	  <td class="content">
	  	<bean:write name="sheetMain" property="mainAlarmLogicSortSub" scope="request"/>
	  </td>
	</tr>
		  
	  <tr>
  		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmDesc"/></td>
		<td class="content" colspan='3'><pre><bean:write name="sheetMain" property="mainAlarmDesc" scope="request"/></pre></td>
	  </tr>
	   	
	</table>

	<br/>
	
	<table class="listTable"> 
	  <caption></caption>
	  
        	
	  <tr>
	    <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortOne"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortTwo"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>
      <tr>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortThree"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/></td>
		
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultResponseLevel"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultResponseLevel}" beanId="ItawSystemDictTypeDao"/></td>
		
	  </tr> 
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.acceptLimit"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.completeLimit"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr>
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainCompleteLimitT1"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="mainCompleteLimitT1" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainCompleteLimitT2"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="mainCompleteLimitT2" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr>
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainCompleteLimitT3"/></td>
		<td class="content" colspan="3">
			<bean:write name="sheetMain" property="mainCompleteLimitT3" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		
	  </tr>
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultDiscoverableMode"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultDiscoverableMode}" beanId="ItawSystemDictTypeDao"/>
		</td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainSendMode"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainSendMode}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>
	  
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainEquipmentType"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainEquipmentType}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainEquipmentFactory"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainEquipmentFactory}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>

      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetName"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainNetName" scope="request"/></td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainEquipmentModel"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainEquipmentModel" scope="request"/></td>
	  </tr>	  

      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultGenerantPriv"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainFaultGenerantPriv" scope="request"/></td>
	 
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultGenerantCity"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.toDeptId}" beanId="tawSystemAreaDao"/></td>
	  </tr>

      <tr>
      <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultGenerantTime"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainFaultGenerantTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/></td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfAffectOperation"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainIfAffectOperation}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	   
	  
<c:if test="${!empty sheetMain.mainApplySheetId}">	  
	  <tr>
  		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainApplySheetId"/></td>
		<td colspan='3' class="content">
			<bean:write name="sheetMain" property="mainApplySheetId" scope="request"/>
		</td>
	  </tr>	  
</c:if>	
	  
	 <!--   <tr>
  		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultDesc"/></td>
		<td colspan='3' class="content">
			<bean:write name="sheetMain" property="mainFaultDesc" scope="request"/>
		</td>
	  </tr>	 -->	  	  
	   	  
	  
		<tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='5'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="commonfault" 
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

<!-- Sheet Deal Content Start -->
<c:url var="urlShowTransmitDealPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="1"/>  
</c:url>

<c:url var="urlShowTransmit2DealPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="2"/>  
</c:url>

<c:url var="urlShowCompleteDealPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="46"/>
</c:url>

<c:url var="urlShowHoldDealPage" value="commonfault.do">
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

<c:url var="urlShowRejectDealPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>

<c:url var="urlShowExamineYesDealPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="66"/>
</c:url>

<c:url var="urlShowExamineNoDealPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="64"/>
</c:url>

<c:url var="urlShowInvokeUrgentFaultDealPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="19"/>
</c:url>

<c:url var="urlShowTransferkPage" value="commonfault.do">
  <c:param name="method" value="showTransferWorkItemPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>

<c:url var="urlShowExamineDealPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="5"/>
  <c:param name="taskStatus" value="${taskStatus}"/>  
</c:url>
<c:url var="urlShowPhaseReplyPage" value="commonfault.do">
  <c:param name="method" value="showPhaseBackToUpPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="reply"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operaterType" value="${operaterType}"/> 
</c:url>
<c:url var="urlShowInputSplit1" value="commonfault.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="firstSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<c:url var="urlShowInputSplit2" value="commonfault.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="secondSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<c:url var="urlShowInputSplit3" value="commonfault.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="thirdSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<c:url var="urlShowInputSplit4" value="commonfault.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="fourSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<div class="sheet-deal">
	<div class='sheet-deal-header'>
	<table>
	  <tr>
	    <td width="50%">
	    <%if(!taskName.equals("cc")) { 
	    	if (!taskName.equals("reply")) { 
	    		if (!taskName.equals("advice")) {%>
	 <bean:message bundle="sheet" key="sheet.deal"/>:<%} } }%>
	<%if(taskName.equals("FirstExcuteHumTask")){ %>
	  <%--if(taskStatus.equals("8") && (task!=null && task.getTaskOwner().equals(userId))){--%>
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		  <option value="${urlShowTransmitDealPage}"><bean:message bundle="commonfault" key="operateType.transmit1"/> </option>
		  <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
		  <option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option>
		  <c:if test="${sheetMain.mainFaultResponseLevel == '101160301'}">
		  	<%if(ifInvokeUrgentFault.equals("no")){ %>
		  	<option value="${urlShowInvokeUrgentFaultDealPage}"><bean:message bundle="commonfault" key="operateType.InvokeUrgentFault"/></option>
		  	<%} %>
		  </c:if>
		  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		</select>
	  <%--}--%>
	<%}else if(taskName.equals("SecondExcuteHumTask")){%>	
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		  <option value="${urlShowTransmit2DealPage}"><bean:message bundle="commonfault" key="operateType.transmit2"/></option>
		  <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
		  <option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option>
		  <c:if test="${sheetMain.mainFaultResponseLevel == '101160301'}">
		  	<%if(ifInvokeUrgentFault.equals("no")){ %>
		  	<option value="${urlShowInvokeUrgentFaultDealPage}"><bean:message bundle="commonfault" key="operateType.InvokeUrgentFault"/></option>
		  	<%} %>
		  </c:if>		  
		  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>	
		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
  		  <option value="${urlShowInputSplit1}"><bean:message bundle="sheet" key="common.split"/></option>
		</select>
	<%}else if(taskName.equals("ThirdExcuteHumTask")){%>	
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		  <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
		  <option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option>
		  <c:if test="${sheetMain.mainFaultResponseLevel == '101160301'}">
		  	<%if(ifInvokeUrgentFault.equals("no")){ %>
		  	<option value="${urlShowInvokeUrgentFaultDealPage}"><bean:message bundle="commonfault" key="operateType.InvokeUrgentFault"/></option>
		  	<%} %>
		  </c:if>		  
		  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		</select>
	<%}else if(taskName.equals("DraftHumTask")){%>	
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		  <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.draft"/></option>
		</select>
	<%}else if(taskName.equals("ExamineHumTask")){%>	
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		  <option value="${urlShowExamineYesDealPage}"><bean:message bundle="commonfault" key="operateType.Examine"/></option>
		  <option value="${urlShowExamineNoDealPage}"><bean:message bundle="commonfault" key="operateType.examineReject"/></option>
		</select>
	<%}else if(taskName.equals("HoldHumTask")){ %>
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		  <option value="${urlShowHoldDealPage}"><bean:message bundle="commonfault" key="operateType.hold"/></option>
		  <option value="${urlShowRejectDealPage}"><bean:message bundle="commonfault" key="operateType.reject"/></option>
		</select>	
	<%}else if(taskName.equals("firstSubTask")||taskName.equals("secondSubTask")||taskName.equals("thirdSubTask")||taskName.equals("fourSubTask")){ %>
		
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">

		  <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.subTaskReply"/></option>
<%--		  <%if(taskName.equals("firstSubTask")){%>
		  <option value="${urlShowInputSplit1}"><bean:message bundle="sheet" key="common.split"/></option>
		  <%}%>
		  <%if(taskName.equals("secondSubTask")){%>
		  <option value="${urlShowInputSplit2}"><bean:message bundle="sheet" key="common.split"/></option>
		  <%}%>
		  <%if(taskName.equals("thirdSubTask")){%>
		  <option value="${urlShowInputSplit3}"><bean:message bundle="sheet" key="common.split"/></option>
		  <%}%>
		  <%if(taskName.equals("fourSubTask")){%>
		  <option value="${urlShowInputSplit4}"><bean:message bundle="sheet" key="common.split"/></option>
		  <%}%> --%>
		</select>	
	<%} %>
	</td>
	</tr></table>
	</div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>

<script type="text/javascript">
	Ext.onReady(function(){
	var url = "";
	try{
		 url = $("dealSelector").value + "&taskStatus=${taskStatus}";
		}catch(e){}
		
		var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
   			url = "commonfault.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
        }
		eoms.util.appendPage("sheet-deal-content",url);		
	});
</script>
<!-- Sheet Deal Content End -->
<%}%>

<c:if test="${sheetMain.status==0}"> 
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
 <%if(isAdmin.equals("true")){%>
 	<div id="buttonDisplay">
    <input type="button" title="${eoms:a2u('执行该操作，工单将进入强制归档状态，其他人不能在处理工单')}" value="<bean:message bundle="sheet" key="button.forceHold"/>" onclick="forceOperation(1);">
    <input type="button" title="${eoms:a2u('执行该操作，工单将进入强制作废状态，其他人不能在处理工单')}" value="<bean:message bundle="sheet" key="button.forceNullity"/>" onclick="forceOperation(3);">
    <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
 <% }else if(taskStatus.equals("5")&& !taskName.equals("cc")&& !taskName.equals("reply")&& !taskName.equals("advice")){%>
     <div id="buttonDisplay">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
     </div>
 <% }else if((taskStatus == null || taskStatus.equals(""))&&(userId.equals(sendUserId))){%> 
 <div id="advice" style="display:block">
     <input type="button" title="${eoms:a2u('执行该操作，工单将进入作废状态，其他人不能在处理工单')}" value="<bean:message bundle="sheet" key="button.nullity"/>" onclick="forceOperation(2);">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('advice').style.display='none';eventOperation(1);">
  </div>
 <% }%> 
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>