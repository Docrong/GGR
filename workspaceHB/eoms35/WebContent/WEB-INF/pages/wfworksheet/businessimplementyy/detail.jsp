<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYTask" %>
<%@page import="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
BusinessImplementYYTask task = new BusinessImplementYYTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
 task = (BusinessImplementYYTask)request.getAttribute("task");
 taskStatus = task.getTaskStatus();
 operaterType = task.getOperateType();
	 ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
	 ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask()); 
}

request.setAttribute("@@operaterTypeishere",operaterType);
BusinessImplementYYMain basemain = (BusinessImplementYYMain)request.getAttribute("sheetMain");

String sendUserId = basemain.getSendUserId();
String orderSheetId =basemain.getOrderSheetId();



 request.setAttribute("taskStatus", taskStatus);
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
	System.out.println("@@taskNameishere"+taskName);
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
			url : 'businessimplementyy.do?method=showSheetDealList&orderByDetp=true&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',	
			url : 'businessimplementyy.do?method=showWorkFlow&linkServiceName=iBusinessImplementYYLinkManager&dictSheetName=dict-sheet-businessimplementyy&description=mainOperateType&sheetKey=${sheetMain.id}',
			isIframe : true
		},{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'businessimplementyy.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="businessimplementyy.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="businessimplementyy.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="businessimplementyy.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>

});
function forceOperation(obj){

	if(obj == 1){
	    
		var url2="businessimplementyy.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
		eoms.util.appendPage("sheet-deal-content",url2);
		
	}else if(obj == 2){
	     
		var url2="businessimplementyy.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
		eoms.util.appendPage("sheet-deal-content",url2);
	}else{
	    
	    var url2="businessimplementyy.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
		eoms.util.appendPage("sheet-deal-content",url2);
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="businessimplementyy.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
	<%@ include file="/WEB-INF/pages/wfworksheet/businessimplementyy/basedetailnew.jsp"%>
	<br/>

		<!-- 
      <tr>
        <td class="label">工单受理时限</td>
		<td class="content">
			<bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label">工单处理时限</td>
		<td class="content">
			<bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr>
		     -->  
	<% if(orderSheetId != null && !"".equals(orderSheetId)){ %>	     

    <fieldset id="link10">
	 <legend>客户相关信息</legend> 
  		<table class="formTable">		     
		<tr>
		     <td class="label">客户名称*</td>
		     <td class="content"><bean:write name="orderSheet" property="customName"  scope="request"/></td>
		     </td>
			 <td class="label">客户地址*</td>
			 <td class="content"><bean:write name="orderSheet" property="customAdd"  scope="request"/></td>
		</tr>
		<tr>
		     <td class="label">客户联系人</td>
		     <td class="content"><bean:write name="orderSheet" property="customContact" scope="request"/></td>
		 
		     <td class="label">客户联系人电话</td>
		      <td class="content"><bean:write name="orderSheet" property="customContactPhone" bundle="businessimplementyy" scope="request"/></td>

		   </tr> 
		   <tr> 
			     <td class="label">集团客户联系人邮箱</td>
			     <td class="content"><bean:write name="orderSheet" property="customConnMain" bundle="businessimplementyy" scope="request"/></td>
			     <td class="label">集团客户编号*</td>
			     <td class="content"><bean:write name="orderSheet" property="groupCustomNo" bundle="businessimplementyy" scope="request"/></td>
		    </tr>     
		</table>
	</fieldset>
<fieldset id="link1" >
<legend>CMCC客户经理信息</legend>
	<table class="formTable">
		 <br>
	    <tr>
		     <td class="label">客户经理姓名*</td>
		     <td class="content"><bean:write name="orderSheet" property="cmanagerPhone" bundle="businessimplementyy" scope="request"/></td>
		     <td class="label">业务所属区域*</td>
		     <td class="content"><bean:write name="orderSheet" property="businesBelongCity" bundle="businessimplementyy" scope="request"/></td>
		   </tr>
		   <!-- old end-->
		   <tr>
		     <td class="label">客户经理联系邮箱*</td>
		     <td class="content"><bean:write name="orderSheet" property="customManagerMail" bundle="businessimplementyy" scope="request"/></td>
		     <td class="label">客户经理电话*</td>
		     <td class="content"><bean:write name="orderSheet" property="projectManagerPhone" bundle="businessimplementyy" scope="request"/></td>
		   </tr>   
		</table>
</fieldset>
<fieldset id="link2" >
<legend>合同号信息</legend>
	<table class="formTable">
		 <br>
		<tr>
		     <td class="label">合同号</td>
		     <td class="content"><bean:write name="orderSheet" property="productSN" bundle="businessimplementyy" scope="request"/></td>
		     <td class="label">签订日期</td>

		      <td class="content">
			        <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
					 id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
					 alt="allowBlank:true" /> 
		  			  
			    </td>	
		   </tr> 
		</table>
</fieldset>
<fieldset id="link3" >
<legend>技术方案信息</legend>
	<table class="formTable">
		 <br>
		<tr>
	      <td class="label">技术方案描述</td>
			 <td class="content"><bean:write name="orderSheet" property="needDesc" bundle="businessimplementyy" scope="request"/></td>
		</tr>
		<tr>
			<td colspan="4">
				<iframe id="frame" src="" width="100%" height="300px" style="display:none"></iframe>
			</td>
		</tr>	
		</table>
</fieldset>	        
<br>
<%} %>
<% if(orderSheetId != null && !"".equals(orderSheetId)){ %>
<iframe id="frame" src="${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=${orderSheetId}&taskStatus=${taskStatus}&specialtyType=yuyin&sheetType=businessImplementyy&taskName=${taskName}" width="100%" height="300px"></iframe>
<%}%>


    </logic:present>
  </div>
  <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->

<%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="businessimplementyy.do">
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
<c:url var="urlShowInputSplit" value="businessimplementyy.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="BusinessImplementYYSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="operateType" value="10"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>

<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="businessimplementyy.do">
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

<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="businessimplementyy.do">
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

<!-- 处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealReplyRejectPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="BusinessImplementYYSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>


<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="businessimplementyy.do">
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
<!-- 草稿 -->
<c:url var="urlShowCompleteDraftPage" value="businessimplementyy.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>

<!-- 待归档/退回 -->
<c:url var="urlShowBackDeal" value="businessimplementyy.do">
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
  <c:param name="backFlag" value="yes"/> 
</c:url> 

<!-- 重派 -->
<c:url var="urlShowReSendDeal" value="businessimplementyy.do">
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


<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="businessimplementyy.do">
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
<!-- 阶段回复 -->
<c:url var="urlShowPhaseReplyPage" value="businessimplementyy.do">
  <c:param name="method" value="showPhaseBackToUpPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="reply"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="${operateType}"/> 
</c:url>

<!-- 确认驳回urlShowAcceptRejectTaskDealPage -->

<c:url var="urlShowAcceptRejectTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="204"/>
</c:url>

<!-- 移交，由于不走showDealPage方法，所以不需要设置operateType,也不需要保存模板 -->
<c:url var="urlShowTransferkPage" value="businessimplementyy.do">
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
<c:url var="urlShowTransferAuditPage" value="businessimplementyy.do">
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

<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="businessimplementyy.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="BusinessImplementYYSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="30"/>  
</c:url>
<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="OperationBackOutSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="55"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- AuditTask -->
<c:url var="urlShowAuditTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="71"/>
</c:url>

<!-- CodeDispthTask -->
<c:url var="urlShowCodeDispthTaskDealPageAudit" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="72"/>
</c:url>
<!-- GetWayTask -->
<c:url var="urlShowGetWayTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="73"/>
</c:url>
<!-- OpenTask -->
<c:url var="urlShowOpenTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="74"/>
</c:url>
<!-- DataMakeTask -->
<c:url var="urlShowDataMakeTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="75"/>
</c:url>
<!-- BusiTestTask -->
<c:url var="urlShowBusiTestTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="76"/>
</c:url>
<!-- DataOkTask -->
<c:url var="urlShowDataOkTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="77"/>
</c:url>

<c:url var="urlShowNetDateMakeTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="931"/>
</c:url>

<c:url var="urlShowOptimalizeTestTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="932"/>
</c:url>


<c:url var="urlShowVilidateTaskDealPage" value="businessimplementyy.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="961"/>
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
		 	
		 	<!--工程施工，数据确认  -->

	<% if(taskName.equals("HoldTask")){ System.out.println("@@HoldTask"); %>
			<select id="dealSelector">
			  <option value="${urlShowHoldDealPage}"><bean:message bundle="businessimplementyy" key="operateType.hold"/></option>
			  <!-- <option value="${urlShowBackDeal}">退回验证</option> -->
			  
			</select>		
		<%} %>
				<%if(taskName.equals("RejectTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowReSendDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
			</select>
			<%} %>
			
			<% if(taskName.equals("DraftTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowCompleteDraftPage}"><bean:message bundle="businessimplementyy" key="operateType.draft"/></option>
			</select>
			<%} %>
		<%if(taskName.equals("AuditTask")){ %>	
					<select id="dealSelector"> 
					 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
							 <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
							 <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>				 
					 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){%>
						<!-- 是父任务 -->
						<%if(ifsub.equals("") || ifsub.equals("false")){ %>
							<% if (ifwaitfor.equals("false")) {%>
						 <option value="${urlShowAuditTaskDealPage}">送编续码</option>
					
						 <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
						 <!-- <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet" key="common.transferAudit"/></option>	  -->
					    
					     
							<%} %>
						<%} else { %>
							<!-- 有子任务 -->
							<% if (ifwaitfor.equals("false")) {%>
							<option value="${urlShowDispatchPage}">分派回复</option>
							<option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet" key="common.splitReplyAudit"/></option>
							<%} %>
						<% } %>
					    <c:if test="${needDealReply == 'true'}">
					       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
					       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
					    </c:if>		
					    <!--  -->
					    <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>			

		             <%}%>
		           </select>
		         <%} %>
		         <%if(taskName.equals("GetWayTask")){%>	
			  		  <select id="dealSelector">
					  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
					    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
					    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
					 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>  
						<!-- 是父任务 -->
						<%if(ifsub.equals("") || ifsub.equals("false")){ %>
							<% if (ifwaitfor.equals("false")) {%>
							<!-- 工程入网送，优化测试 -->
					     <option value="${urlShowGetWayTaskDealPage}">送专线开通</option>	
						<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
					     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
					     
						
						<%} else { %>
							<!-- 有子任务 -->
							<% if (ifwaitfor.equals("false")) {%>
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
					<%} %>
					<%} %>
				 <%if(taskName.equals("OpenTask")){%>	
			  		  <select id="dealSelector">
					  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
					    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
					    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
					 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>  
						<!-- 是父任务 -->
						<%if(ifsub.equals("") || ifsub.equals("false")){ %>
							<% if (ifwaitfor.equals("false")) {%>
							<!-- 工程入网送，优化测试 -->
					        <option value="${urlShowOpenTaskDealPage}">送数据制作</option>
						<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
					     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
					     
						
						<%} else { %>
							<!-- 有子任务 -->
							<% if (ifwaitfor.equals("false")) {%>
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
					<%} %>
					<%} %>
			<%if(taskName.equals("DataMakeTask")){%>	
			  		  <select id="dealSelector">
					  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
					    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
					    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
					 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>  
						<!-- 是父任务 -->
						<%if(ifsub.equals("") || ifsub.equals("false")){ %>
							<% if (ifwaitfor.equals("false")) {%>
							<!-- 工程入网送，优化测试 -->
					       <option value="${urlShowDataMakeTaskDealPage}">送业务测试</option>
						<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
					     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
					     
						
						<%} else { %>
							<!-- 有子任务 -->
							<% if (ifwaitfor.equals("false")) {%>
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
					<%} %>
					<%} %>
			<%if(taskName.equals("BusiTestTask")){%>	
			  		  <select id="dealSelector">
					  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
					    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
					    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
					 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>  
						<!-- 是父任务 -->
						<%if(ifsub.equals("") || ifsub.equals("false")){ %>
							<% if (ifwaitfor.equals("false")) {%>
							<!-- 工程入网送，优化测试 -->
					    <option value="${urlShowBusiTestTaskDealPage}">送数据确认</option>
						<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
					     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
					     
						
						<%} else { %>
							<!-- 有子任务 -->
							<% if (ifwaitfor.equals("false")) {%>
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
					<%} %>
					<%} %>
		<%if(taskName.equals("DataOkTask")){%>	
			  		  <select id="dealSelector">
					  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
					    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
					    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
					 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>  
						<!-- 是父任务 -->
						<%if(ifsub.equals("") || ifsub.equals("false")){ %>
							<% if (ifwaitfor.equals("false")) {%>
							<!-- 工程入网送，优化测试 -->
					    <option value="${urlShowDataOkTaskDealPage}">送归档</option>
						<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
					     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
					     
						
						<%} else { %>
							<!-- 有子任务 -->
							<% if (ifwaitfor.equals("false")) {%>
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
					<%} %>
					<%} %>
           <%if(taskName.equals("CodeDispthTask")){%>	
			  		  <select id="dealSelector">
					  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
					    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
					    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
					 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>  
						<!-- 是父任务 -->
						<%if(ifsub.equals("") || ifsub.equals("false")){ %>
							<% if (ifwaitfor.equals("false")) {%>
							<!-- 工程入网送，优化测试 -->
					    <option value="${urlShowCodeDispthTaskDealPageAudit}">送设备端口分配</option>
						<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
					     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
					     
						
						<%} else { %>
							<!-- 有子任务 -->
							<% if (ifwaitfor.equals("false")) {%>
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
					<%} %>
					
		
		
				
			<%if(taskName.equals("DataMakeTask")){ %>	
					<select id="dealSelector"> 
					 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
							 <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
							 <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>				 
					 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){%>
						<!-- 是父任务 -->
						<%if(ifsub.equals("") || ifsub.equals("false")){ %>
							<% if (ifwaitfor.equals("false")) {%>
						 <option value="${urlShowDataMakeTaskDealPage}">送业务测试</option>
						 <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
					      <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet" key="common.transferAudit"/></option>	
					     
							<%} %>
						<%} else { %>
							<!-- 有子任务 -->
							<% if (ifwaitfor.equals("false")) {%>
							<option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet" key="common.splitReplyAudit"/></option>
							<%} %>
						<% } %>
						
					    <c:if test="${needDealReply == 'true'}">
					       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
					       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
					    </c:if>	
					    
					    <%}%>
		           </select>	
	

       
				
		<%}else if(taskName.equals("DraftTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowCompleteDraftPage}"><bean:message bundle="businessimplementyy" key="operateType.draft"/></option>
			</select>

		<%}else if(taskName.equals("HoldTask")){ System.out.println("@@HoldTask"); %>
			<select id="dealSelector">
			  <option value="${urlShowHoldDealPage}"><bean:message bundle="businessimplementyy" key="operateType.hold"/></option>
			  <!-- <option value="${urlShowBackDeal}">退回验证</option> -->
			  
			</select>		
		<%} %>
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
   			url = "businessimplementyy.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
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

<%} %>

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
