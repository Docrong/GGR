<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>


<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.maintenanceservice.model.MaintenanceServiceTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>


<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
MaintenanceServiceTask task = new MaintenanceServiceTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
	 task = (MaintenanceServiceTask)request.getAttribute("task");
	 taskStatus = task.getTaskStatus();
	 operaterType = task.getOperateType();
	 ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
	 ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
}

request.setAttribute("operaterType",operaterType);
BaseMain basemain = (BaseMain)request.getAttribute("sheetMain");
String sendUserId = basemain.getSendUserId();

request.setAttribute("taskStatus", taskStatus);
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 %>

<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>
<script type="text/javascript">
Ext.onReady(function() {
	var tabConfig = {
		items : [{
			id : 'sheetinfo',
			text : '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.historyView"/>',
			url : 'maintenanceservice.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			url : 'maintenanceservice.do?method=showWorkFlow&linkServiceName=iMaintenanceServiceLinkManager&dictSheetName=dict-sheet-maintenanceservice&description=mainOperateType&sheetKey=${sheetMain.id}',
			isIframe : true
		  }
		,{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'maintenanceservice.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
			var url2="maintenanceservice.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
			eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
		var url2="maintenanceservice.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
		eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
		var url2="maintenanceservice.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
		eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>

});
function forceOperation(obj){

	if(obj == 1){
	     
		var url2="maintenanceservice.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
		eoms.util.appendPage("sheet-deal-content",url2);	
	}else if(obj == 2){
	    
		var url2="maintenanceservice.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
		eoms.util.appendPage("sheet-deal-content",url2);	
	}else{
	   
	    var url2="maintenanceservice.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
		eoms.util.appendPage("sheet-deal-content",url2);	
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="maintenanceservice.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
	<%@ include file="/WEB-INF/pages/wfworksheet/maintenanceservice/basedetailnew.jsp"%>
	<br/>
<table id="sheet" class="formTable" > 
 
      <tr>
        <td class="label">受理时限</td>
		<td class="content">
			<bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label">处理时限</td>
		<td class="content">
			<bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr>
	  
		<tr><td class="label">
				<!-- 种类 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainType"/>
			</td>
			<td> 
				${sheetMain.mainType}
			</td><td class="label">
				<!-- 序号 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainSerialNumber"/>
			</td>
			<td> 
				${sheetMain.mainSerialNumber}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 设备名称 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainDeviceName"/>
			</td>
			<td> 
				${sheetMain.mainDeviceName}
			</td><td class="label">
				<!-- 设备厂家 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainManufacturers"/>
			</td>
			<td> 
				${sheetMain.mainManufacturers}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 系统名称 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainSystemName"/>
			</td>
			<td> 
				${sheetMain.mainSystemName}
			</td><td class="label">
				<!-- 设备型号 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainEquipmentType"/>
			</td>
			<td> 
				${sheetMain.mainEquipmentType}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 设备序列号 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainDeviceNumber"/>
			</td>
			<td> 
				${sheetMain.mainDeviceNumber}
			</td><td class="label">
				<!-- 入网时间 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainAccessTime"/>
			</td>
			<td> 
				${sheetMain.mainAccessTime}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 出保日期 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainOutDate"/>
			</td>
			<td> 
				${sheetMain.mainOutDate}
			</td><td class="label">
				<!-- 接口类型及数量 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainInterfaceType"/>
			</td>
			<td> 
				${sheetMain.mainInterfaceType}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 是否配置双机热备软件和其它随机软件 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainSoftware"/>
			</td>
			<td> 
				${sheetMain.mainSoftware}
			</td><td class="label">
				<!-- 操作系统名称及版本 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainSystemName"/>
			</td>
			<td> 
				${sheetMain.mainSystemName}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 系统集成商名称 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainIntegratorName"/>
			</td>
			<td> 
				${sheetMain.mainIntegratorName}
			</td><td class="label">
				<!-- 拟购买的服务级别 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainLevelService"/>
			</td>
			<td> 
				${sheetMain.mainLevelService}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 设备物理位置 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainPhysicalLocation"/>
			</td>
			<td> 
				${sheetMain.mainPhysicalLocation}
			</td><td class="label">
				<!-- 备注 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainRemarks"/>
			</td>
			<td> 
				${sheetMain.mainRemarks}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 联系人 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainContacts"/>
			</td>
			<td> 
				${sheetMain.mainContacts}
			</td><td class="label">
				<!-- 联系方式 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainContactInformation"/>
			</td>
			<td> 
				${sheetMain.mainContactInformation}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 单位 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainCompany"/>
			</td>
			<td> 
				${sheetMain.mainCompany}
			</td>
		</tr>
	
	
	  <tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='3'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="maintenanceservice" 
		             viewFlag="Y"/> 
		    </td>
	  </tr>
</table>
    </logic:present>
  </div>
</div>
<!-- Sheet Tabs End -->

<!-- 工单处理环节开始 -->
<% if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>

<!-- Sheet Deal Content Start -->
<!-- 下面是流程中的步骤URL -->
<!-- 维保确认 -->
<c:url var="urlShowConfirmation102Page" value="maintenanceservice.do">
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
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 维保确认 -->
<c:url var="urlShowConfirmation106Page" value="maintenanceservice.do">
	<c:param name="method" value="showDealPage"/>
	<c:param name="sheetKey" value="${sheetMain.id}"/>
	<c:param name="piid" value="${sheetMain.piid}"/>
	<c:param name="taskId" value="${taskId}"/>
	<c:param name="taskName" value="${taskName}"/>
	<c:param name="operateRoleId" value="${operateRoleId}"/>
	<c:param name="TKID" value="${TKID}"/>
	<c:param name="taskStatus" value="${taskStatus}"/>
	<c:param name="preLinkId" value="${preLinkId}"/>
	<c:param name="operateType" value="106"/>
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 填报 -->
<c:url var="urlShowFill103Page" value="maintenanceservice.do">
	<c:param name="method" value="showDealPage"/>
	<c:param name="sheetKey" value="${sheetMain.id}"/>
	<c:param name="piid" value="${sheetMain.piid}"/>
	<c:param name="taskId" value="${taskId}"/>
	<c:param name="taskName" value="${taskName}"/>
	<c:param name="operateRoleId" value="${operateRoleId}"/>
	<c:param name="TKID" value="${TKID}"/>
	<c:param name="taskStatus" value="${taskStatus}"/>
	<c:param name="preLinkId" value="${preLinkId}"/>
	<c:param name="operateType" value="103"/>
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 填报 -->
<c:url var="urlShowFill4Page" value="maintenanceservice.do">
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
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 审核 -->
<c:url var="urlShowExamine104Page" value="maintenanceservice.do">
	<c:param name="method" value="showDealPage"/>
	<c:param name="sheetKey" value="${sheetMain.id}"/>
	<c:param name="piid" value="${sheetMain.piid}"/>
	<c:param name="taskId" value="${taskId}"/>
	<c:param name="taskName" value="${taskName}"/>
	<c:param name="operateRoleId" value="${operateRoleId}"/>
	<c:param name="TKID" value="${TKID}"/>
	<c:param name="taskStatus" value="${taskStatus}"/>
	<c:param name="preLinkId" value="${preLinkId}"/>
	<c:param name="operateType" value="104"/>
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 审核 -->
<c:url var="urlShowExamine4Page" value="maintenanceservice.do">
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
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 审核 -->
<c:url var="urlShowExamine106Page" value="maintenanceservice.do">
	<c:param name="method" value="showDealPage"/>
	<c:param name="sheetKey" value="${sheetMain.id}"/>
	<c:param name="piid" value="${sheetMain.piid}"/>
	<c:param name="taskId" value="${taskId}"/>
	<c:param name="taskName" value="${taskName}"/>
	<c:param name="operateRoleId" value="${operateRoleId}"/>
	<c:param name="TKID" value="${TKID}"/>
	<c:param name="taskStatus" value="${taskStatus}"/>
	<c:param name="preLinkId" value="${preLinkId}"/>
	<c:param name="operateType" value="106"/>
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 审批 -->
<c:url var="urlShowApproval105Page" value="maintenanceservice.do">
	<c:param name="method" value="showDealPage"/>
	<c:param name="sheetKey" value="${sheetMain.id}"/>
	<c:param name="piid" value="${sheetMain.piid}"/>
	<c:param name="taskId" value="${taskId}"/>
	<c:param name="taskName" value="${taskName}"/>
	<c:param name="operateRoleId" value="${operateRoleId}"/>
	<c:param name="TKID" value="${TKID}"/>
	<c:param name="taskStatus" value="${taskStatus}"/>
	<c:param name="preLinkId" value="${preLinkId}"/>
	<c:param name="operateType" value="105"/>
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 审批 -->
<c:url var="urlShowApproval4Page" value="maintenanceservice.do">
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
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 归档 -->
<c:url var="urlShowHoldTask18Page" value="maintenanceservice.do">
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
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	

<!-- 流程中的步骤已结束 -->

<!-- 下面是公共的URL -->
<!-- 退回 -->
<c:url var="urlShowRejectDealPage" value="maintenanceservice.do">
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


<!-- 移交，由于不走showDealPage方法，所以不需要设置operateType,也不需要保存模板 -->
<c:url var="urlShowTransferkPage" value="maintenanceservice.do">
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

<!-- 阶段回复 直接到另一个页面,不需要保存模板-->
<c:url var="urlShowPhaseReplyPage" value="maintenanceservice.do">
  <c:param name="method" value="showPhaseBackToUpPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="reply"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="${operaterType}"/> 
</c:url>

<!--任务分派-->
<c:url var="urlShowInputSplit" value="maintenanceservice.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="MaintenanceServiceSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="operateType" value="10"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>

<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="maintenanceservice.do">
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
<c:url var="urlShowDealReplyRejectPage" value="maintenanceservice.do">
  <c:param name="method" value="showDealReplyRejectPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="MaintenanceServiceSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>
<!-- 驳回上一级 -->
<c:url var="urlShowRejectBackPage" value="maintenanceservice.do">
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
<c:url var="urlShowAcceptDealPage" value="maintenanceservice.do">
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

<!-- 被驳回 -->
<c:url var="urlBackDealPage" value="maintenanceservice.do">
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
  <c:param name="backFlag" value="yes"/>
</c:url>

<!-- 草稿 -->
<c:url var="urlShowDraftPage" value="maintenanceservice.do">
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


<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="maintenanceservice.do">
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

<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="maintenanceservice.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="MaintenanceServiceSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="30"/>  
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="maintenanceservice.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="MaintenanceServiceSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="55"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="maintenanceservice.do">
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


<script type="text/javascript">
	Ext.onReady(function(){
		eoms.Sheet.setPageLoader("dealSelector","sheet-deal-content");
		
	var url = "";
	try{
		 url = $("dealSelector").value + "&taskStatus=${taskStatus}";
		}catch(e){}
		
		var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
   			url = "maintenanceservice.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
        }
		eoms.util.appendPage("sheet-deal-content",url);		
	});
</script>

<div class="sheet-deal">
	<div class='sheet-deal-header'>
	<!-- 下面是各个处理环节的下拉框功能 -->
		<table>
					<tr>
						 <td width="50%">  
						 	<%if(!taskName.equals("cc") && !taskName.equals("reply") && !taskName.equals("advice")) { %>
						 		<bean:message bundle="sheet" key="sheet.deal"/>:
						 	<%}%>
						 	
 							<!-- 维保确认 -->
								<%   if(taskName.equals("Confirmation")){ %>
									<select id="dealSelector">
										
										<% if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									 		<!-- 确认受理 -->
									  		<option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  		<!-- 驳回 -->
									  		<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <% } else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									   
									 	   <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false") || ifsub == null){%>
									 	  	 <!-- 不需要等待回复 -->
									 	  <% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowConfirmation102Page}"><bean:message bundle="maintenanceservice" key="operateType.Confirmation102"/> </option>
											  <option value="${urlShowConfirmation106Page}"><bean:message bundle="maintenanceservice" key="operateType.Confirmation106"/> </option>
											  
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
									 		<% }%>
									 <%} else { %>
									 		<!-- 是子任务 -->
									 		<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="maintenanceservice" key="operateType.subTaskReply"/></option>
									 		<% }%>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									  </c:if>
									
									<%}%>
									
							 	</select>
 							<!-- 填报 -->
								<% }  if(taskName.equals("Fill")){ %>
									<select id="dealSelector">
										
										<% if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									 		<!-- 确认受理 -->
									  		<option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  		<!-- 驳回 -->
									  		<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <% } else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									   
									 	   <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false") || ifsub == null){%>
									 	  	 <!-- 不需要等待回复 -->
									 	  <% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowFill103Page}"><bean:message bundle="maintenanceservice" key="operateType.Fill103"/> </option>
											  <option value="${urlShowFill4Page}"><bean:message bundle="maintenanceservice" key="operateType.Fill4"/> </option>
											  
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
									 		<% }%>
									 <%} else { %>
									 		<!-- 是子任务 -->
									 		<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="maintenanceservice" key="operateType.subTaskReply"/></option>
									 		<% }%>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									  </c:if>
									
									<%}%>
									
							 	</select>
 							<!-- 审核 -->
								<% }  if(taskName.equals("Examine")){ %>
									<select id="dealSelector">
										
										<% if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									 		<!-- 确认受理 -->
									  		<option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  		<!-- 驳回 -->
									  		<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <% } else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									   
									 	   <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false") || ifsub == null){%>
									 	  	 <!-- 不需要等待回复 -->
									 	  <% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowExamine104Page}"><bean:message bundle="maintenanceservice" key="operateType.Examine104"/> </option>
											  <option value="${urlShowExamine4Page}"><bean:message bundle="maintenanceservice" key="operateType.Examine4"/> </option>
											  <option value="${urlShowExamine106Page}"><bean:message bundle="maintenanceservice" key="operateType.Examine106"/> </option>
											  
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
									 		<% }%>
									 <%} else { %>
									 		<!-- 是子任务 -->
									 		<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="maintenanceservice" key="operateType.subTaskReply"/></option>
									 		<% }%>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									  </c:if>
									
									<%}%>
									
							 	</select>
 							<!-- 审批 -->
								<% }  if(taskName.equals("Approval")){ %>
									<select id="dealSelector">
										
										<% if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									 		<!-- 确认受理 -->
									  		<option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  		<!-- 驳回 -->
									  		<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <% } else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									   
									 	   <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false") || ifsub == null){%>
									 	  	 <!-- 不需要等待回复 -->
									 	  <% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowApproval105Page}"><bean:message bundle="maintenanceservice" key="operateType.Approval105"/> </option>
											  <option value="${urlShowApproval4Page}"><bean:message bundle="maintenanceservice" key="operateType.Approval4"/> </option>
											  
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
									 		<% }%>
									 <%} else { %>
									 		<!-- 是子任务 -->
									 		<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="maintenanceservice" key="operateType.subTaskReply"/></option>
									 		<% }%>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									  </c:if>
									
									<%}%>
									
							 	</select>
 							<!-- 归档 -->
								<% }  if(taskName.equals("HoldTask")){ %>
									<select id="dealSelector">
										
									 	   <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false") || ifsub == null){%>
									 	  	 <!-- 不需要等待回复 -->
									 	  <% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowHoldTask18Page}"><bean:message bundle="maintenanceservice" key="operateType.HoldTask18"/> </option>
											  
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
									 		<% }%>
									 <%} else { %>
									 		<!-- 是子任务 -->
									 		<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="maintenanceservice" key="operateType.subTaskReply"/></option>
									 		<% }%>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									  </c:if>
									
							 	</select>
							<% }else if(taskName.equals("RejectTask")){%>	
								<select id="dealSelector">
								  <option value="${urlBackDealPage}"><bean:message bundle="sheet" key="common.reSend"/></option>
								</select>
					  		<% }else if(taskName.equals("DraftTask")){%>	
								<select id="dealSelector">
								  <option value="${urlShowDraftPage}"><bean:message bundle="maintenanceservice" key="operateType.DraftTask"/></option>
								</select>
					   		<% } %>
					</td>
				</tr>
		</table>
		
	<!-- 各个处理环节的下拉框功能结束 -->
	</div>
	<div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>
<!-- 工单处理环节结束 -->



<script type="text/javascript">
	Ext.onReady(function(){
	var url = "";
	try{
		 url = $("dealSelector").value + "&taskStatus=${taskStatus}";
		}catch(e){}
		var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
   			url = "maintenanceservice.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
        }
		eoms.util.appendPage("sheet-deal-content",url);		
	});
</script>
<% }%>


<c:if test="${sheetMain.status==0}"> 
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
 <% if(isAdmin.equals("true")){%>
 	<div id="buttonDisplay" style="display:block">
   <input type="button" title="执行该操作，工单将进入强制归档状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceHold"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
   <input type="button" title="执行该操作，工单将进入强制作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceNullity"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
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
