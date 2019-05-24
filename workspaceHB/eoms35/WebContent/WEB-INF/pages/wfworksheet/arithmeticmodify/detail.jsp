<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyTask" %>
<%@page import="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain" %>
<%@page import="com.boco.eoms.sheet.complaint.model.ComplaintMain" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
request.setAttribute("userId",userId);
ArithmeticModifyTask task = new ArithmeticModifyTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
 task = (ArithmeticModifyTask)request.getAttribute("task");
 taskStatus = task.getTaskStatus();
 operaterType = task.getOperateType();
	 ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
	 ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask()); 
}

request.setAttribute("operaterType",operaterType);
ArithmeticModifyMain basemain = (ArithmeticModifyMain)request.getAttribute("sheetMain");
String sendUserId = basemain.getSendUserId();
String mainYuLiuA = StaticMethod.nullObject2String(request.getAttribute("mainYuLiuA"));
 request.setAttribute("mainYuLiuA", mainYuLiuA);
if(!"".equals(mainYuLiuA)){
    ComplaintMain complaintmain = (ComplaintMain)request.getAttribute("complaintmain");
}
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
			url : 'arithmeticmodify.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			url : 'arithmeticmodify.do?method=showWorkFlow&linkServiceName=iArithmeticModifyLinkManager&dictSheetName=dict-sheet-arithmeticmodify&description=mainOperateType&sheetKey=${sheetMain.id}',		
			isIframe : true
		},{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'arithmeticmodify.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="arithmeticmodify.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="arithmeticmodify.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="arithmeticmodify.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>

});
function forceOperation(obj){

	if(obj == 1){
	    
		var url2="arithmeticmodify.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
		eoms.util.appendPage("sheet-deal-content",url2);
		
	}else if(obj == 2){
	     
		var url2="arithmeticmodify.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
		eoms.util.appendPage("sheet-deal-content",url2);
	}else{
	    
	    var url2="arithmeticmodify.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
		eoms.util.appendPage("sheet-deal-content",url2);
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="arithmeticmodify.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
	<%@ include file="/WEB-INF/pages/wfworksheet/arithmeticmodify/basedetailnew.jsp"%>
  	<br/>
  	<table id="sheet" class="formTable" > 
  		<tr>
	      <td class="label">指标级别</td>
			<td class="content">
				<eoms:id2nameDB id="${sheetMain.mainTargetLevel}" beanId="ItawSystemDictTypeDao"/>
			</td>
	      <td class="label">指标分类</td>
			<td class="content">
				<eoms:id2nameDB id="${sheetMain.mainTargetType}" beanId="ItawSystemDictTypeDao"/>
			</td>
      </tr>
  	</table>
    </logic:present>
  </div>
</div>
<%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="arithmeticmodify.do">
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

<!-- 审批申请 -->
<c:url var="urlShowPermitTaskDealPage" value="arithmeticmodify.do">
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
<!-- 需求确认 -->
<c:url var="urlShowRequireConfirmTaskDealPage" value="arithmeticmodify.do">
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
</c:url>
<!-- 测试部署实施-->
<c:url var="urlShowDeployImplementTaskDealPage" value="arithmeticmodify.do">
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
</c:url>
<!-- 部署结果确认 -->
<c:url var="urlShowResultConfirmTaskDealPage" value="arithmeticmodify.do">
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
</c:url>
<!-- 验证数据一致性 -->
<c:url var="urlShowCheckDataSameTaskDealPage" value="arithmeticmodify.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="107"/>  
</c:url>
<!-- 验证结果确认-->
<c:url var="urlShowResultCheckTaskDealPage" value="arithmeticmodify.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="108"/>  
</c:url>
<!-- 指标合理性检查 -->
<c:url var="urlShowTargetCheckTaskDealPage" value="arithmeticmodify.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="109"/>  
</c:url>
<!-- 指标确认部署-->
<c:url var="urlShowTargetConfirmTaskDealPage" value="arithmeticmodify.do">
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
<!-- 正式部署 -->
<c:url var="urlShowFormalDeployTaskDealPage" value="arithmeticmodify.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="111"/>  
</c:url>
<!-- 算法发布通知 -->
<c:url var="urlShowPublishNoticeTaskDealPage" value="arithmeticmodify.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="112"/>  
</c:url>
<!-- 处理回复 -->
<c:url var="urlShowDealBackTaskDealPage" value="arithmeticmodify.do">
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
<!--任务分派-->
<c:url var="urlShowInputSplit" value="arithmeticmodify.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ArithmeticModifySubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="operateType" value="10"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="arithmeticmodify.do">
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
<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="arithmeticmodify.do">
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
<c:url var="urlShowInputSplitAudit" value="arithmeticmodify.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ArithmeticModifySubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="30"/>  
</c:url>
<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="arithmeticmodify.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ArithmeticModifySubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="55"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>
<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="arithmeticmodify.do">
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
<c:url var="urlShowDealReplyRejectPage" value="arithmeticmodify.do">
  <c:param name="method" value="showDealReplyRejectPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="ArithmeticModifySubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>
<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="arithmeticmodify.do">
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
<c:url var="urlShowCompleteDraftPage" value="arithmeticmodify.do">
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
<c:url var="urlShowBackDeal" value="arithmeticmodify.do">
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
<c:url var="urlShowReSendDeal" value="arithmeticmodify.do">
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
<c:url var="urlShowRejectBackPage" value="arithmeticmodify.do">
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
<c:url var="urlShowPhaseReplyPage" value="arithmeticmodify.do">
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
<!-- 移交，由于不走showDealPage方法，所以不需要设置operateType,也不需要保存模板 -->
<c:url var="urlShowTransferkPage" value="arithmeticmodify.do">
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
<!-- 退回 -->
<c:url var="urlShowRejectBackDealPage" value="arithmeticmodify.do">
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
<div class="sheet-deal">
	<div class='sheet-deal-header'>
		<table>
		  <tr>
		    <td width="50%">
		    <%if(!taskName.equals("cc")) { 
		    	if (!taskName.equals("reply")) { 
		    		if (!taskName.equals("advice")) {%>
		 		<bean:message bundle="sheet" key="sheet.deal"/>:
		 	<%}}}%>
		<%if(taskName.equals("PermitTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	      <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		   <option value="${urlShowPermitTaskDealPage}">申请审批</option>
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
		<%}else if(taskName.equals("RequireConfirmTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowRequireConfirmTaskDealPage}">需求确认</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}else if(taskName.equals("DeployImplementTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowDeployImplementTaskDealPage}">提交测试报告</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}else if(taskName.equals("DraftTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowCompleteDraftPage}">草稿派发</option>
			</select>
		<%}else if(taskName.equals("RejectTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowReSendDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
			</select>
		<%}else if(taskName.equals("HoldTask")){ %>
			<select id="dealSelector">
			  <option value="${urlShowHoldDealPage}"><bean:message bundle="arithmeticmodify" key="operateType.hold"/></option>
			</select>		
		<%}else if(taskName.equals("ResultConfirmTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowResultConfirmTaskDealPage}">提交测试报告</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}else if(taskName.equals("CheckDataSameTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowCheckDataSameTaskDealPage}">验证结果反馈</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}else if(taskName.equals("ResultCheckTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowResultCheckTaskDealPage}">确认通过</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}else if(taskName.equals("TargetCheckTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowTargetCheckTaskDealPage}">核查确认结果</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}else if(taskName.equals("TargetConfirmTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowTargetConfirmTaskDealPage}">确认完毕/提交正式部署</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}else if(taskName.equals("FormalDeployTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowFormalDeployTaskDealPage}">部署完毕</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}else if(taskName.equals("PublishNoticeTask")){%>	
		 <select id="dealSelector">
		  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
	            <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/></option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
		 <%if(ifsub.equals("") || ifsub.equals("false")){%>
		 <%if(ifwaitfor.equals("false")) {%>
		         <option value="${urlShowPublishNoticeTaskDealPage}">算法发布通知并抄送</option>
		         <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			     <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>			     
					<%} %>
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
		<%}%>
		
		</td>
		</tr>
		</table>
		<br/>
	</div>
    <div class="sheet-deal-content"  id="sheet-deal-content"></div>
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
   			url = "arithmeticmodify.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
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
<c:if test="${sheetMain.status==5}">
<html:form action="/arithmeticmodify.do?method=makeSheetRemoveHang&sheetKey=${sheetMain.id}"  styleId="theform">
         <input type="hidden" name="sheetKey" id="sheetKey" value="${sheetMain.id}"/>
				<input type="submit" class="submit" value="解除挂起" />
</html:form>
</c:if> 
<%@ include file="/common/footer_eoms.jsp"%>
