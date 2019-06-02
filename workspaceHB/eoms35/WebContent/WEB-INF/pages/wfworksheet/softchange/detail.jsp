<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.softchange.model.SoftChangeTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
SoftChangeTask task = new SoftChangeTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
 task = (SoftChangeTask)request.getAttribute("task");
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
			url : 'softchange.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			//url : '/ProcessMonitor/runtime/html/index.jsp?appName=softchangeProcessApp&templateName=SoftChangeMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
			//url : 'softchange.do?method=showWorkFlow&sheetKey=${sheetMain.id}',
			  url : 'softchange.do?method=showWorkFlow&linkServiceName=iSoftChangeLinkManager&dictSheetName=dict-sheet-softchange&description=mainOperateType&sheetKey=${sheetMain.id}',
			isIframe : true
		},{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'softchange.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="softchange.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="softchange.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="softchange.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>
 
	

});
function forceOperation(obj){

	if(obj == 1){
	    
	     var url2="softchange.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
	     eoms.util.appendPage("sheet-deal-content",url2);	
	}else if(obj == 2){
	    
	     var url2="softchange.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
	     eoms.util.appendPage("sheet-deal-content",url2);	
	}else{
	    
         var url2="softchange.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
     	eoms.util.appendPage("sheet-deal-content",url2);	
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="softchange.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
	<%@ include file="/WEB-INF/pages/wfworksheet/softchange/basedetailnew.jsp"%>
	
	<br/>
	 

	<table class="formTable"> 
	<tr>
	   <td class="label">工单最终完成时限</td>
	   <td colspan="3"><bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/></td>
	   </tr>
	
        <tr>
			  <td class="label"><bean:message bundle="softchange" key="softchange.mainNetTypeOne"/>*</td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainNetTypeOne}" beanId="ItawSystemDictTypeDao"/></td>				
			  <td class="label"><bean:message bundle="softchange" key="softchange.mainNetTypeTwo"/></td>
		      <td class="content"><eoms:id2nameDB id="${sheetMain.mainNetTypeTwo}" beanId="ItawSystemDictTypeDao"/></td>						  
	    </tr>
	    <tr>
		      <td class="label"><bean:message bundle="softchange" key="softchange.mainNetTypeThree"/></td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainNetTypeThree}" beanId="ItawSystemDictTypeDao"/></td>	           
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainIsSecurity"/></td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsSecurity}" beanId="ItawSystemDictTypeDao"/></td>	                
		</tr>
        <tr>		
			<td  class="label"><bean:message bundle="softchange" key="softchange.mainAssortSpeciality"/></td>
			<td colspan="3"> 
             	<eoms:invert appCode="softchange" sheetKey="${sheetMain.mainAssortSpeciality}" beanId="CacheBean" scope="page"/> 
             </td>
      </tr>
	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainIsConnect"/></td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsConnect}" beanId="ItawSystemDictTypeDao"/></td>	              
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainFactory"/></td>
              <td class="content">
                     <c:forTokens items="${sheetMain.mainFactory}" delims="," var="mainFactory" varStatus="status"><eoms:id2nameDB id="${mainFactory}" beanId="ItawSystemDictTypeDao"/><c:choose><c:when test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens>
              </td>	                              
		</tr>

	    <tr>
              <td  class="label"><bean:message bundle="softchange" key="softchange.mainApplyReason"/></td>
		      <td class="content" colspan="3"><pre><bean:write name="sheetMain" property="mainApplyReason" scope="request"/></pre></td>
		</tr>

	     <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainCellInfo"/></td>
		      <td class="content" colspan="3"><pre><bean:write name="sheetMain" property="mainCellInfo" scope="request"/></pre></td>
		</tr>

	     <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftCDKey"/></td>
		      <td class="content"><eoms:id2nameDB beanId="ItawSystemDictTypeDao" id="${sheetMain.mainSoftCDKey }"/></td>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftPatchKey"/></td>
		      <td class="content"><bean:write name="sheetMain" property="mainSoftPatchKey" scope="request"/></td>
		</tr>

	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftDetail"/></td>
		      <td class="content" colspan="3"><pre><bean:write name="sheetMain" property="mainSoftDetail" scope="request"/></pre></td>
	    </tr>
	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainIsAllow"/></td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsAllow}" beanId="ItawSystemDictTypeDao"/></td>		                
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainAllowKey"/></td>
		      <td class="content"><bean:write name="sheetMain" property="mainAllowKey" scope="request"/></td>
		</tr>
	     <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainCellInfo"/>1</td>
		      <td class="content" colspan="3"><pre><bean:write name="sheetMain" property="maincellinfo1" scope="request"/></pre></td>
		</tr>

	     <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftCDKey"/>1</td>
		      <td class="content"><eoms:id2nameDB beanId="ItawSystemDictTypeDao" id="${sheetMain.mainsoftcdkey1 }"/></td>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftPatchKey"/>1</td>
		      <td class="content"><bean:write name="sheetMain" property="mainsoftpatchkey1" scope="request"/></td>
		</tr>

	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftDetail"/>1</td>
		      <td class="content" colspan="3"><pre><bean:write name="sheetMain" property="mainsoftdetail1" scope="request"/></pre></td>
	    </tr>
	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainIsAllow"/>1</td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainisallow1}" beanId="ItawSystemDictTypeDao"/></td>		                
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainAllowKey"/>1</td>
		      <td class="content"><bean:write name="sheetMain" property="mainallowkey1" scope="request"/></td>
		</tr>
	     <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainCellInfo"/>2</td>
		      <td class="content" colspan="3"><pre><bean:write name="sheetMain" property="maincellinfo2" scope="request"/></pre></td>
		</tr>

	     <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftCDKey"/>2</td>
		      <td class="content"><eoms:id2nameDB beanId="ItawSystemDictTypeDao" id="${sheetMain.mainsoftcdkey2 }"/></td>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftPatchKey"/>2</td>
		      <td class="content"><bean:write name="sheetMain" property="mainsoftpatchkey2" scope="request"/></td>
		</tr>

	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainSoftDetail"/>2</td>
		      <td class="content" colspan="3"><pre><bean:write name="sheetMain" property="mainsoftdetail2" scope="request"/></pre></td>
	    </tr>

	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainIsAllow"/>2</td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsAllow}" beanId="ItawSystemDictTypeDao"/></td>		                
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainAllowKey"/>2</td>
		      <td class="content"><bean:write name="sheetMain" property="mainAllowKey" scope="request"/></td>
		</tr>

	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainIsBack"/></td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsBack}" beanId="ItawSystemDictTypeDao"/></td>		                
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainChangeSource"/></td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainChangeSource}" beanId="ItawSystemDictTypeDao"/></td>	                
		</tr>

	    <tr>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainParentProcessName"/></td>
		      <td class="content"><bean:write name="sheetMain" property="mainParentProcessName" scope="request"/></td>
		      <td  class="label"><bean:message bundle="softchange" key="softchange.mainIsNeedDesign"/></td>
			  <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsNeedDesign}" beanId="ItawSystemDictTypeDao"/></td>	                 
		</tr>
     <c:if test="${sheetMain.status==1}">
	   <tr>
                   <td class="label">
                           <bean:message bundle="softchange" key="softchange.mainIfDemonstrateCase"/>
                   </td>
	               <td colspan='3'>
	                       <eoms:id2nameDB id="${sheetMain.mainIfDemonstrateCase}" beanId="ItawSystemDictTypeDao"/>
	               </td>
                </tr>    
                <tr>		
                   <td class="label">
                           <bean:message bundle="softchange" key="softchange.mainCaseKeywords"/>
                   </td>
		           <td colspan='3'>
                   <pre><bean:write name="sheetMain" property="mainCaseKeywords" scope="request"/></pre>
		           </td>
                </tr>     
  </c:if>
	    <tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='3'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="softchange" 
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
<c:url var="urlShowProjectCreateTaskDealPage" value="softchange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="110"/>  
</c:url>

<c:url var="urlShowAuditTaskDealPage" value="softchange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="130"/>  
</c:url>

<c:url var="urlShowPermitTaskDealPage" value="softchange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="131"/>
</c:url>
<c:url var="urlShowPlanTaskDealPage" value="softchange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="113"/>
</c:url>
<c:url var="urlShowExecuteTaskDealPage" value="softchange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="114"/>
</c:url>
<c:url var="urlShowValidateTaskDealPage" value="softchange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="115"/>
</c:url>
<!-- 
<c:url var="urlShowDraftDeal" value="softchange.do">
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
</c:url>  -->

<c:url var="urlShowDraftDeal" value="softchange.do">
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
<c:url var="urlShowHoldDealPage" value="softchange.do">
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

<c:url var="urlShowRejectDealPage" value="softchange.do">
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


<c:url var="urlShowTransferkPage" value="softchange.do">
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
<c:url var="urlShowTransferAuditPage" value="softchange.do">
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

<c:url var="urlShowPhaseReplyPage" value="softchange.do">
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

<c:url var="urlShowRejectBackPage" value="softchange.do">
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
<c:url var="urlShowAcceptDealPage" value="softchange.do">
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
<c:url var="urlShowInputSplit" value="softchange.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="softSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="10"/>
</c:url>

<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="softchange.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="softSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="30"/>
</c:url>

<c:url var="urlShowDispatchPage" value="softchange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="softSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="11"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="softchange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="softSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="55"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<c:url var="urlShowDealReplyAcceptPage" value="softchange.do">
  <c:param name="method" value="showDealReplyAcceptPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="softSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="6"/>
</c:url>

<c:url var="urlShowDealReplyRejectPage" value="softchange.do">
  <c:param name="method" value="showDealReplyRejectPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="softSubTask1"/>
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
	<%if(taskName.equals("ProjectCreateTask")){ %>
		<select id="dealSelector">
		 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		  <option value="${urlShowProjectCreateTaskDealPage}"><bean:message bundle="softchange" key="operateType.makeScheme"/> </option>
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
	<%}else if(taskName.equals("AuditTask")){%>	
		<select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		<!--    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>-->
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		  <option value="${urlShowAuditTaskDealPage}"><bean:message bundle="softchange" key="operateType.audit"/></option>	  
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
  		  <%} %>
		</select>
	<%}else if(taskName.equals("PermitTask")){%>	
		<select id="dealSelector">
		<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		<!--    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>-->
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		  <option value="${urlShowPermitTaskDealPage}"><bean:message bundle="softchange" key="operateType.permit"/></option>		  
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
		 <%} %>
		</select>
		<%}else if(taskName.equals("PlanTask")){%>	
		<select id="dealSelector">
		<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		 <!--    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>-->
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		  <option value="${urlShowPlanTaskDealPage}"><bean:message bundle="softchange" key="operateType.make"/></option>		  
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
		<%}else if(taskName.equals("ExecuteTask")){%>	
		<select id="dealSelector">
		<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		   <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		  <option value="${urlShowExecuteTaskDealPage}"><bean:message bundle="softchange" key="operateType.operate"/></option>		  
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
		
	   <%}else if(taskName.equals("ValidateTask")){%>	
		<select id="dealSelector">
		<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		 <!--    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>-->
		 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		  <option value="${urlShowValidateTaskDealPage}"><bean:message bundle="softchange" key="operateType.checkResult"/></option>		  
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
		
	<%}else if(taskName.equals("DraftTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowDraftDeal}"><bean:message bundle="softchange" key="operateType.draft"/></option>
		</select>
		
	<%}else if(taskName.equals("RejectHumTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowDraftDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
		  <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		</select>
	<%}else if(taskName.equals("HoldTask")){ %>
		<select id="dealSelector">
		  <option value="${urlShowHoldDealPage}"><bean:message bundle="softchange" key="operateType.hold"/></option>
		<%--  <option value="${urlShowRejectDealPage}"><bean:message bundle="softchange" key="operateType.reject"/></option> --%>
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
   			url = "softchange.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
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
     <input type="button" title="执行该操作，工单将进入作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.nullity"/>"onclick="$('advice').style.display='none';forceOperation(2);">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('advice').style.display='none';eventOperation(1);">
  </div>
 <% }%> 
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>
