<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.itrequirement.model.ITRequirementTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
ITRequirementTask task = new ITRequirementTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
 task = (ITRequirementTask)request.getAttribute("task");
 taskStatus = task.getTaskStatus();
 operaterType = task.getOperateType();
 ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
 ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
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
			url : 'itrequirement.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			//url : '/ProcessMonitor/runtime/html/index.jsp?appName=itrequirementProcessApp&templateName=itrequirementMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
			//url : 'itrequirement.do?method=showWorkFlow&sheetKey=${sheetMain.id}',
			  url : 'itrequirement.do?method=showWorkFlow&linkServiceName=iITRequirementLinkManager&dictSheetName=dict-sheet-itrequirement&description=mainOperateType&sheetKey=${sheetMain.id}',
			isIframe : true
		},{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'itrequirement.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="itrequirement.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="itrequirement.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="itrequirement.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>
 
	

});
function forceOperation(obj){

	if(obj == 1){
	    // window.location.href='itrequirement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold';
	   var url2="itrequirement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
	   eoms.util.appendPage("sheet-deal-content",url2);	  
	}else if(obj == 2){
	    // window.location.href='itrequirement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity';
	   var url2="itrequirement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
	   eoms.util.appendPage("sheet-deal-content",url2);	 
	}else{
	   //  window.location.href='itrequirement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity';
        var url2="itrequirement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
	    eoms.util.appendPage("sheet-deal-content",url2);
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="itrequirement.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
	<%@ include file="/WEB-INF/pages/wfworksheet/itrequirement/basedetailnew.jsp"%>
	<br/>
	

	<table class="formTable">
	  <caption></caption>	  
		  <tr>
		     <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/></td>
			  <td class="content">
			    <bean:write  name="sheetMain"  property="sheetAcceptLimit"  formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/> 
		  			  
			  </td>				
			  <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/></td>
			  <td class="content">
			    <bean:write  name="sheetMain"  property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>  
			  </td>		  
		  </tr>					
				
				
				
				
				
				
	                <tr>
		                 <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainNetSystem"/></td>
		                <td class="content"> 
		                 <eoms:id2nameDB id="${sheetMain.mainNetSystem}" beanId="ItawSystemDictTypeDao"/> 
			        </td>	                

		              <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainUrgentDegree"/></td>
		                <td class="content">  
				       <eoms:id2nameDB id="${sheetMain.mainUrgentDegree}" beanId="ItawSystemDictTypeDao"/> 
			        </td>	                
		            </tr>


			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainSheetID"/></td>
		            <td  class="content"> <bean:write  name="sheetMain"  property="mainSheetID"  scope="request"/></td>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainBusinessTarget"/></td>
		            <td  class="content"> <bean:write  name="sheetMain"  property="mainBusinessTarget"  scope="request"/></td>
		          </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainUser"/></td>
		             <td  class="content">
		             <eoms:id2nameDB id="${sheetMain.mainUser}" beanId="tawSystemUserDao"/>
		            </td>

 					<td class="label"><bean:message bundle="itrequirement" key="itrequirement.mainCompleteTime"/></td>
	   				<td  class="content">
	   				 <bean:write  name="sheetMain"  property="mainCompleteTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/> 
	                </td>
		            </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainRequirementDesc"/></td>
		              <td class="content" colspan="3"> 	
    				  <pre><bean:write  name="sheetMain"  property="mainRequirementDesc"  scope="request"/></pre>
                    </td>
		          </tr>
	              <tr>
  						<td class="label"><bean:message bundle="itrequirement" key="itrequirement.mainRequirementDetail"/></td>
		   				 <td  class="content" colspan='3'>
		   				 <eoms:attachment name="sheetMain" property="mainRequirementDetail" idList=""
            			  scope="request" idField="${sheetPageName}mainRequirementDetail" appCode="itrequirementsheet" viewFlag="Y"/> 
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
<c:url var="urlShowAuditTaskDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="91"/>  
</c:url>


<c:url var="urlShowAnalysisTaskDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="92"/>  
</c:url>


<c:url var="urlShowAddInfoTaskDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="93"/>
</c:url>


<c:url var="urlShowDevAuditTaskDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="94"/>
</c:url>

<c:url var="urlShowOperateTaskDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="95"/>
</c:url>

<c:url var="urlShowTempSaveTaskDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="96"/>
</c:url>

<c:url var="urlShowReplyTaskDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="97"/>
</c:url>

<!-- 特使归档 -->
<c:url var="urlShowConfirmHoldTaskDealPage" value="itrequirement.do">
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
<!-- 草稿派发 -->
<c:url var="urlShowDraftDeal" value="itrequirement.do">
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
<c:url var="urlShowBackDeal" value="itrequirement.do">
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
<c:url var="urlShowHoldDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="99"/>
</c:url>
<!-- 特殊退回 -->
<c:url var="urlShowRejectBackTDealPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="101"/> 
</c:url>
<!-- 退回 -->
<c:url var="urlShowRejectBackDealPage" value="itrequirement.do">
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
<c:url var="urlShowTransferkPage" value="itrequirement.do">
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
<c:url var="urlShowTransferAuditPage" value="itrequirement.do">
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
<c:url var="urlShowPhaseReplyPage" value="itrequirement.do">
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

<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="itrequirement.do">
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
<c:url var="urlShowAcceptDealPage" value="itrequirement.do">
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
<c:url var="urlShowInputSplit" value="itrequirement.do">
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

<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="itrequirement.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="subTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="30"/>  
</c:url>

<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="subTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="11"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="itrequirement.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="subTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="55"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>
<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="itrequirement.do">
  <c:param name="method" value="showDealReplyAcceptPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="subTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="6"/>
</c:url>
<!-- 处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="itrequirement.do">
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





<div class="sheet-deal">
	<div class='sheet-deal-header'>
	<table>
	  <tr>
	    <td width="50%">
	    <%if(!taskName.equals("cc")) { 
	    	if (!taskName.equals("reply")) { 
	    		if (!taskName.equals("advice")) {%>
	 <bean:message bundle="sheet" key="sheet.deal"/>:<%} } }%>
	
	<%if(taskName.equals("AuditTask")){ %>
		<select id="dealSelector">
		 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		  <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option> -->
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>	    
		  <option value="${urlShowAuditTaskDealPage}"><bean:message bundle="itrequirement" key="operateType.audit"/> </option>
		  <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet" key="common.transferAudit"/></option>
		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		  <%}%>
		  <%}else{%> 
		  <% if (ifwaitfor.equals("false")) {%> 
		  <option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet" key="common.splitReplyAudit"/></option>
		  <%}%>
		  <%}%>
		  <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>
		  <c:if test="${needDealReply == 'true'}">
	      <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
		  <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
		  </c:if>
		  <%}%>
		</select>
	<%}else if(taskName.equals("AnalysisTask")){%>	
		<select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		   <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>	
		  <option value="${urlShowAnalysisTaskDealPage}"><bean:message bundle="itrequirement" key="operateType.analysis"/></option>	  
		  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>	
		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		  <%}%>
		  <%}else{%> 
		  <% if (ifwaitfor.equals("false")) {%> 
		  <option value="${urlShowDispatchPage}"><bean:message bundle="sheet" key="common.splitReply"/></option>
		  <%}%>
		  <%}%>
		  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
		  <c:if test="${needDealReply == 'true'}">
	      <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
		  <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
		  </c:if>
  		  <%} %>
		</select>

	<%}else if(taskName.equals("DevAuditTask")){%>	
		<select id="dealSelector">
		 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		  <!--<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>  -->
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>	    
		  <option value="${urlShowDevAuditTaskDealPage}"><bean:message bundle="itrequirement" key="operateType.devAudit"/> </option>
		  <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet" key="common.transferAudit"/></option>
		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		  <%}%>
		  <%}else{%> 
		  <% if (ifwaitfor.equals("false")) {%> 
		  <option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet" key="common.splitReplyAudit"/></option>
		  <%}%>
		  <%}%>
		  <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>
		  <c:if test="${needDealReply == 'true'}">
	      <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
		  <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
		  </c:if>
		  <%}%>
		</select>
		<%}else if(taskName.equals("OperateTask")){%>	
		<select id="dealSelector">
		<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		   <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		  <option value="${urlShowOperateTaskDealPage}"><bean:message bundle="itrequirement" key="operateType.operate"/></option>		  
		  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		  <%}%>
		  <%}else{%> 
		  <% if (ifwaitfor.equals("false")) {%> 
		  <option value="${urlShowDispatchPage}"><bean:message bundle="sheet" key="common.splitReply"/></option>
		  <%}%>
		  <%}%>
		  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
		  <c:if test="${needDealReply == 'true'}">
	      <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
		  <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
		  </c:if>
		 <%} %>
		</select>	
		<%}else if(taskName.equals("ReplyTask")){%>	
		<select id="dealSelector">
		<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		   <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		  <option value="${urlShowReplyTaskDealPage}"><bean:message bundle="itrequirement" key="operateType.reply"/></option>		  
		  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		  <%}%>
		  <%}else{%> 
		  <% if (ifwaitfor.equals("false")) {%> 
		  <option value="${urlShowDispatchPage}"><bean:message bundle="sheet" key="common.splitReply"/></option>
		  <%}%>
		  <%}%>
		  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
		  <c:if test="${needDealReply == 'true'}">
	      <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
		  <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
		  </c:if>
		 <%} %>
		</select>		
	<%}else if(taskName.equals("AddInfoTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowAddInfoTaskDealPage}"><bean:message bundle="itrequirement" key="operateType.addInfo"/></option>
		</select>	
	<%}else if(taskName.equals("DraftTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowDraftDeal}"><bean:message bundle="itrequirement" key="operateType.draft"/></option>
		</select>
		
	<%}else if(taskName.equals("RejectTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowBackDeal}"><bean:message bundle="itrequirement" key="operateType.reApplication"/></option>
		</select>
	<%}else if(taskName.equals("TempSaveTask")){ %>
		<select id="dealSelector">
		  <option value="${urlShowTempSaveTaskDealPage}"><bean:message bundle="itrequirement" key="operateType.tempSave"/></option>
		</select>
   <%}else if(taskName.equals("HoldTask")){ %>
		<select id="dealSelector">
		  <option value="${urlShowHoldDealPage}"><bean:message bundle="itrequirement" key="operateType.hold"/></option>
		  <option value="${urlShowRejectBackDealPage}"><bean:message bundle="itrequirement" key="operateType.back"/></option>
		</select>
   <%}else if(taskName.equals("ConfirmHoldTask")){ %>
		<select id="dealSelector">
		  <option value="${urlShowConfirmHoldTaskDealPage}"><bean:message bundle="itrequirement" key="operateType.hold1"/></option>
		 <option value="${urlShowRejectBackTDealPage}"><bean:message bundle="itrequirement" key="operateType.backoftest"/></option>
		</select>						
	<%}%>			
		

	</td>
	</tr></table>
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
   			url = "itrequirement.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
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
    <input type="button" title="执行该操作，工单将进入强制归档状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceHold"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
    <input type="button" title="执行该操作，工单将进入强制作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceNullity"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
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