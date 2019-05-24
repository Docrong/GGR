<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.groupcomplaint.task.impl.GroupComplaintTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
GroupComplaintTask task = new GroupComplaintTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
	 task = (GroupComplaintTask)request.getAttribute("task");
	 
	 taskStatus = task.getTaskStatus();
	 operaterType = task.getOperateType();
	 ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
	 ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
	 System.out.println("ifsub===>" + ifsub);
	  System.out.println("ifwaitfor===>" + ifwaitfor); 
}

request.setAttribute("operaterType",operaterType);
BaseMain basemain = (BaseMain)request.getAttribute("sheetMain");
String sendUserId = basemain.getSendUserId();
System.out.println("sendUserId>>>>>>"+sendUserId);

 request.setAttribute("taskStatus", taskStatus);
 System.out.println("taskStatus>>>>>>>>"+taskStatus);
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));

 System.out.println("taskName>>>>>>"+taskName);

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
			url : 'groupcomplaint.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			url :  'groupcomplaint.do?method=showWorkFlow&linkServiceName=iGroupComplaintLinkManager&dictSheetName=dict-sheet-groupcomplaint&description=mainOperateType&sheetKey=${sheetMain.id}',
			isIframe : true
		},{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'groupcomplaint.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="groupcomplaint.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="groupcomplaint.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="groupcomplaint.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>
 
	

});
function forceOperation(obj){

	if(obj == 1){
	    var url2="groupcomplaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
	    eoms.util.appendPage("sheet-deal-content",url2);	 
	}else if(obj == 2){
	    
	   var url2="groupcomplaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
	   eoms.util.appendPage("sheet-deal-content",url2);	
	}else{
	     
        var url2="groupcomplaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
	    eoms.util.appendPage("sheet-deal-content",url2);	
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="groupcomplaint.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
             eoms.util.appendPage("sheet-deal-content",url2,true);
	}
	if(obj == 2){
	     var url2="groupcomplaint.do?method=showBackCheckDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}";
         //alert(url2);
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
	<%@ include file="/WEB-INF/pages/wfworksheet/groupcomplaint/basedetailnew.jsp"%>
	<br/>
	

	<table class="formTable"> 
	 	  <caption></caption>	  

	  <tr>
	    <td class="label"><bean:message bundle="group" key="groupcomplaint.urgentDegree"/></td>
		<td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.urgentDegree}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	  
       <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.dealTime1"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="dealTime1" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.dealTime2"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="dealTime2" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr> 


      <tr>
      	  	<c:choose>
      	<c:when test="${sheetMain.cancelReason == '1'}">
      	    <td class="label">移交处理时限</td>
      	</c:when>	
      		<c:otherwise>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.mainCompleteLimitT1"/></td>
        	</c:otherwise>	  	
        	</c:choose>
		<td class="content">
			<bean:write name="sheetMain" property="mainCompleteLimitT1" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.mainCompleteLimitT2"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="mainCompleteLimitT2" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr> 

	</table>

	<br/>
	<table class="formTable"> 
	  <caption></caption>
	  <tr>
        <td class="label">是否关联上告警</td>
		<td class="content"><bean:write name="sheetMain" property="mainIfMatch" scope="request"/></td>
		<td class="label">关联故障工单号</td>
		<td class="content"><bean:write name="sheetMain" property="mainCommSheetId" scope="request"/></td>
	  </tr> 
      <tr>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.bservType"/></td>
		<td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.bservType}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr> 
	  <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.customNo"/></td>
		<td class="content"><bean:write name="sheetMain" property="customNo" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.customName"/></td>
		<td class="content"><bean:write name="sheetMain" property="customName" scope="request"/></td>
	  </tr> 
	  <tr>
        <td class="label">业务保障级别</td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.customLevel}" beanId="ItawSystemDictTypeDao"/></td>
		<!-- td class="content"><bean:write name="sheetMain" property="customLevel" scope="request"/></td -->
		<td class="label"><bean:message bundle="group" key="groupcomplaint.provinceName"/></td>
		<td class="content"><bean:write name="sheetMain" property="provinceName" scope="request"/></td>
	  </tr> 
	  <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.cityName"/></td>
		<td class="content"><bean:write name="sheetMain" property="cityName" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.countyName"/></td>
		<td class="content"><bean:write name="sheetMain" property="countyName" scope="request"/></td>
	  </tr> 	  	  	  
	  
	  
	  <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.bdeptContactPhone"/></td>
		<td class="content"><bean:write name="sheetMain" property="bdeptContactPhone" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.bdeptContact"/></td>
		<td class="content"><bean:write name="sheetMain" property="bdeptContact" scope="request"/></td>
	  </tr> 
	  <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.cManagerPhone"/></td>
		<td class="content"><bean:write name="sheetMain" property="cmanagerPhone" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.cManagerContactPhone"/></td>
		<td class="content"><bean:write name="sheetMain" property="cmanagerContactPhone" scope="request"/></td>
	  </tr> 
	  <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.customContact"/></td>
		<td class="content"><bean:write name="sheetMain" property="customContact" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.customContactPhone"/></td>
		<td class="content"><bean:write name="sheetMain" property="customContactPhone" scope="request"/></td>
	  </tr> 
	  <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.productName"/></td>
		<td class="content" colspan="3"><bean:write name="sheetMain" property="productName" scope="request"/></td>
	  </tr> 
	  <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.affectedAreas"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.affectedAreas}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.customAttribution"/></td>
		<td class="content"><bean:write name="sheetMain" property="customAttribution" scope="request"/></td>
	  </tr> 	  	  	  	  		   	  
      <tr>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.faultTime"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="faultTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintTime"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="complaintTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>		
	  </tr>  
	  <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintNum"/></td>
		<td class="content"><bean:write name="sheetMain" property="complaintNum" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.complaintLoca"/></td>
		<td class="content"><bean:write name="sheetMain" property="complaintLoca" scope="request"/></td>
	  </tr> 
	  <tr>
	    <td class="label"><bean:message bundle="group" key="groupcomplaint.termType"/></td>
		<td class="content" colspan="3"><bean:write name="sheetMain" property="termType" scope="request"/></td>
	  </tr>	
	  
		<tr>
		  <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintDesc"/></td>
		  <td class="content" colspan="3">
		  	<bean:write name="sheetMain" property="complaintDesc" scope="request"/>
		  </td>
		</tr>	
		<tr>
		  <td class="label"><bean:message bundle="group" key="groupcomplaint.preDealResult"/></td>
		  <td class="content" colspan="3">
		  	<bean:write name="sheetMain" property="preDealResult" scope="request"/>
		  </td>
		</tr>	

   <tr>
	    <td class="label"><bean:message bundle="group" key="groupcomplaint.enterpriseCode"/></td>
		<td class="content"><bean:write name="sheetMain" property="enterpriseCode" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.serverCode"/></td>
		<td class="content"><bean:write name="sheetMain" property="serverCode" scope="request"/></td>
	  </tr>		  
	    <tr>
	    <td class="label"><bean:message bundle="group" key="groupcomplaint.apnName"/></td>
		<td class="content"><bean:write name="sheetMain" property="apnName" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.circuitCode"/></td>
		<td class="content"><bean:write name="sheetMain" property="circuitCode" scope="request"/></td>
	  </tr>	
	  <tr>
	    <td class="label"><bean:message bundle="group" key="groupcomplaint.ecsiType"/></td>
		<td class="content"><bean:write name="sheetMain" property="ecsiType" scope="request"/></td>
		<td class="label"><bean:message bundle="group" key="groupcomplaint.connectType"/></td>
		<td class="content"><bean:write name="sheetMain" property="connectType" scope="request"/></td>
	  </tr>	
	   
		<tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='3'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="groupcomplaint" 
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

<!-- 移交T2 -->
<c:url var="urlShowTransmitDealPage" value="groupcomplaint.do">
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
<!-- 处理完成 -->
<c:url var="urlShowCompleteDealPage" value="groupcomplaint.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="groupcomplaint.do">
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
<!-- 待归档-退回 -->
<c:url var="urlShowRejectDealPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="17"/>
</c:url>
<!-- 质检合格 -->
<c:url var="urlShowCheckYesDealPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="56"/>
</c:url>

<!-- 质检不合格 -->
<c:url var="urlShowCheckNoDealPage" value="groupcomplaint.do">
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
</c:url>
<!-- 组内移交 -->
<c:url var="urlShowTransferPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealPage"/>
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


<c:url var="urlShowSendExaminePage" value="groupcomplaint.do">
  <c:param name="method" value="showPhaseBackToUpPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="SendDeferExamine"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operaterType" value="${operateType}"/> 
</c:url>

<!-- 送 延期申请 -->
<c:url var="urlShowExamineDealPage" value="groupcomplaint.do">
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

<c:url var="urlShowPhaseReplyPage" value="groupcomplaint.do">
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



<!-- 被驳回，显示草稿 -->
<c:url var="urlShowDraftDealPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="firstSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="53"/> 
  <c:param name="backFlag" value="yes"/>
</c:url>
<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="groupcomplaint.do">
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
<!-- 接单 -->
<c:url var="urlShowAcceptDealPage" value="groupcomplaint.do">
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

<!--任务分派-->
<c:url var="urlShowInputSplit" value="groupcomplaint.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="subTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="10"/>  
</c:url>
<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="11"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>
<!-- 分派，处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealReplyAcceptPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="6"/>
</c:url>

<!-- 分派，处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealReplyRejectPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="subTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>
<c:url var="urlShowExamineYesDealPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="66"/>
  <c:param name="taskStatus" value="${taskStatus}"/>  
</c:url>  
<c:url var="urlShowExamineNoDealPage" value="groupcomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="64"/>
  <c:param name="taskStatus" value="${taskStatus}"/>  
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
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		  <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 
			 	   <!-- 是父任务 -->
			 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
			 	  	<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
					  <!-- 流程步骤，移交，阶段回复，分派 -->
					  <option value="${urlShowTransmitDealPage}"><bean:message bundle="group" key="operateType.transmit1"/> </option>
					  <option value="${urlShowCompleteDealPage}"><bean:message bundle="group" key="operateType.dealComplete"/></option>
					  <option value="${urlShowTransferPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
					   <option value="${urlShowSendExaminePage}"><bean:message bundle="group" key="operateType.sendToExamine"/></option>					  	     
					  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>	
					  <!-- <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->				     
			 		<%}else{%>
			 			<!-- <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->	
			 		<%} %>
			 					 		
			 	 <%} else { %>
			 		<!-- 是子任务 -->
			 		<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
			 			<option value="${urlShowDispatchPage}"><bean:message bundle="group" key="operateType.subTaskReply"/></option>
			 		<%}%>
			     <% } %>
			  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>		  
			  <c:if test="${needDealReply == 'true'}">
			       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			  </c:if>
			  
		 <%} %>
		</select>
	<%}else if(taskName.equals("SecondExcuteHumTask")){%>	
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		   <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 
		 	   <!-- 是父任务 -->
		 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 	  	<!-- 不需要等待回复 -->
		 	  	<% if (ifwaitfor.equals("false")) {%>
				  <!-- 流程步骤，移交，阶段回复，分派 -->
				  <option value="${urlShowCompleteDealPage}"><bean:message bundle="group" key="operateType.dealComplete"/></option>
				  <option value="${urlShowTransferPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				   <option value="${urlShowSendExaminePage}"><bean:message bundle="group" key="operateType.sendToExamine"/></option>
	              <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>	      
	              <!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->            
		 		<%}else{%>
		 			<!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->
		 		<%} %>	 
		 				
		     <%} else { %>
		 		<!-- 是子任务 -->
		 		<!-- 不需要等待回复 -->
		 	  	<% if (ifwaitfor.equals("false")) {%>
		 			<option value="${urlShowDispatchPage}"><bean:message bundle="group" key="operateType.subTaskReply"/></option>
		 		<%}%>
		     <%} %>
			  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>			  
			  <c:if test="${needDealReply == 'true'}">
			       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			  </c:if>
			  
  		  <%} %>
		</select>
	<%}else if(taskName.equals("CheckingHumTask")){%>	
		<select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		   <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		   <option value="${urlShowCheckYesDealPage}"><bean:message bundle="group" key="operateType.checkYes"/> </option>
		   <option value="${urlShowCheckNoDealPage}"><bean:message bundle="group" key="operateType.checkNo"/></option>			  
  		  <%}%>
  		</select>
	<%}else if(taskName.equals("DraftHumTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowCompleteDealPage}"><bean:message bundle="group" key="operateType.draft"/></option>
		</select>
	<%}else if(taskName.equals("ByRejectHumTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowDraftDealPage}"><bean:message bundle="group" key="operateType.reSend"/></option>
		</select>		
	<%}else if(taskName.equals("ExamineHumTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowExamineYesDealPage}"><bean:message bundle="group" key="operateType.Examine"/></option>
		  <option value="${urlShowExamineNoDealPage}"><bean:message bundle="group" key="operateType.examineReject"/></option>
		</select>
	<%}else if(taskName.equals("DeferExamineHumTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowExamineYesDealPage}"><bean:message bundle="group" key="operateType.Examine"/></option>
		  <option value="${urlShowExamineNoDealPage}"><bean:message bundle="group" key="operateType.examineReject"/></option>
		</select>
	<%}else if(taskName.equals("HoldHumTask")){ %>
		<select id="dealSelector">
		  <option value="${urlShowHoldDealPage}"><bean:message bundle="group" key="operateType.hold"/></option>
		  <option value="${urlShowRejectDealPage}"><bean:message bundle="group" key="operateType.reject"/></option>
		</select>	
	<%}%>

	</td>
	</tr></table>
	</div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>

<script type="text/javascript">
	Ext.onReady(function(){
		//设置下拉框为ajax页面载入器
		eoms.Sheet.setPageLoader("dealSelector","sheet-deal-content");
		
	var url = "";
	try{
		 url = $("dealSelector").value + "&taskStatus=${taskStatus}";
		}catch(e){}
		
		var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
   			url = "groupcomplaint.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
        }
		eoms.util.appendPage("sheet-deal-content",url);		
	});
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
<c:if test="${sheetMain.status==1 && sheetMain.mainIfCheck =='0' }"> 
	<div class="sheet-deal-content" id="sheet-deal-content"></div>
	
	<div id="advice" style="display:block">
		<input type="button" value="质检" onclick="$('advice').style.display='none';eventOperation(2);">
	</div>
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>