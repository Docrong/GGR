<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.netdata.model.NetDataTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
NetDataTask task = new NetDataTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
	 task = (NetDataTask)request.getAttribute("task");
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
			url : 'netdata.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			//url : '/ProcessMonitor/runtime/html/index.jsp?appName=netdataProcessApp&templateName=netdataMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
			url : 'netdata.do?method=showWorkFlow&linkServiceName=iNetDataLinkManager&dictSheetName=dict-sheet-netdata&description=mainOperateType&sheetKey=${sheetMain.id}',
			isIframe : true
		  }
		,{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'netdata.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="netdata.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="netdata.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="netdata.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>

});
function forceOperation(obj){

	if(obj == 1){
	     
		var url2="netdata.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
		eoms.util.appendPage("sheet-deal-content",url2);	
	}else if(obj == 2){
	    
		var url2="netdata.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
		eoms.util.appendPage("sheet-deal-content",url2);	
	}else{
	   
	    var url2="netdata.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
		eoms.util.appendPage("sheet-deal-content",url2);	
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="netdata.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
	<%@ include file="/WEB-INF/pages/wfworksheet/netdata/basedetailnew.jsp"%>
	<br/>
	<table class="formTable"> 
	  <caption></caption>
	   <tr>
		  	<!-- 工单接单时限 -->
			  <td class="label"><bean:message bundle="sheet" key="linkForm.acceptLimit"/></td>
			  <td>
			    ${eoms:date2String(sheetMain.sheetAcceptLimit)}
			  </td>
			  <!-- 设计完成时限 -->
			  <td  class="label"><bean:message bundle="netdata" key="netdata.linkCompleteLimitTime"/></td>
			  <td>
			    ${eoms:date2String(sheetMain.sheetCompleteLimit)}
			  </td>
	  </tr>
	  <!-- 下面是main表里的字段属性 -->
	  <tr>
		    <td class="label"><bean:message bundle="netdata" key="netdata.mainNetTypeOne"/></td>
			<td class="content"><eoms:id2nameDB id="${sheetMain.mainNetTypeOne}" beanId="ItawSystemDictTypeDao"/></td>
			<td class="label"><bean:message bundle="netdata" key="netdata.mainNetTypeTwo"/></td>
			<td class="content"><eoms:id2nameDB id="${sheetMain.mainNetTypeTwo}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>
      <tr>
			<td class="label"><bean:message bundle="netdata" key="netdata.mainNetTypeThree"/></td>
			<td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.mainNetTypeThree}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>
	  <tr>		
			<td  class="label"><bean:message bundle="netdata" key="netdata.mainAssortSpeciality"/></td>
			<td colspan="3"> 
                 <eoms:invert appCode="netdata" sheetKey="${sheetMain.mainAssortSpeciality}" beanId="CacheBean"/> 
             </td>
      </tr>  
             <tr>
			<td  class="label"><bean:message bundle="netdata" key="netdata.mainIsSecurity"/></td>
			<td> 
				<eoms:id2nameDB id="${sheetMain.mainIsSecurity}" beanId="ItawSystemDictTypeDao"/> 
			</td>	
	  	 	 <td  class="label"><bean:message bundle="netdata" key="netdata.mainIsConnect"/></td>
             <td>  
       	 		<eoms:id2nameDB id="${sheetMain.mainIsConnect}" beanId="ItawSystemDictTypeDao"/> 
       	 	 </td>
       	 	 </tr>
       	 	 <tr>
       	 	 <td  class="label"><bean:message bundle="netdata" key="netdata.mainFactory"/></td>
	     	 <td>   
       	 		<eoms:id2nameDB id="${sheetMain.mainFactory}" beanId="ItawSystemDictTypeDao"/> 
       	 	 </td>	      
 
       		 <td  class="label"><bean:message bundle="netdata" key="netdata.mainChangeSource"/></td>
             <td>  
        	 	<eoms:id2nameDB id="${sheetMain.mainChangeSource}" beanId="ItawSystemDictTypeDao"/> 
        	 </td>
        	 </tr>
        	 <tr>	                
           	 <td  class="label"><bean:message bundle="netdata" key="netdata.mainParentProcessName"/></td>
             <td> 
             	${sheetMain.mainParentSheetId}
             </td>

       		<td  class="label"><bean:message bundle="netdata" key="netdata.mainIsNeedDesign"/></td>
		    <td>  
				  <eoms:id2nameDB id="${sheetMain.mainIsNeedDesign}" beanId="ItawSystemDictTypeDao"/> 
			</td>
			     
          </tr>
          <tr>
        	 <td  class="label"><bean:message bundle="netdata" key="netdata.mainCellInfo"/>*</td>
			 <td colspan="3"> 	
	    		<pre>${sheetMain.mainCellInfo}</pre>
	         </td>
	  </tr>
	   <tr>
          	<!-- 网络数据变更需求说明 -->
            <td  class="label"><bean:message bundle="netdata" key="netdata.mainNetDataCommont"/>*</td>
            <td colspan="3"> 	
  				  <pre>${sheetMain.mainNetDataCommont}</pre>
             </td>
		</tr>
	  <tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='3'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="netdata" 
		             viewFlag="Y"/> 
		    </td>
	  </tr>
	</table>
    </logic:present>
  </div>
</div>
<!-- Sheet Tabs End -->

<!-- 工单处理环节开始 -->
<%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>

<!-- Sheet Deal Content Start -->
<!-- 下面是流程中的步骤URL -->
<!-- 方案制定  -->
<c:url var="urlShowProjectCreateTaskPage" value="netdata.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>
<!-- 方案审核 -->
<c:url var="urlShowAuditTaskPage" value="netdata.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/> 
</c:url>

<!-- 方案审批 -->
<c:url var="urlShowPermitTaskPage" value="netdata.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 排程 -->
<c:url var="urlShowPlanTaskPage" value="netdata.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 数据制作 -->
<c:url var="urlShowExecuteTaskPage" value="netdata.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>
<!-- 数据核查 -->
<c:url var="urlShowCheckTaskPage" value="netdata.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="117"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>
<!-- 业务测试 -->
<c:url var="urlShowTestTaskPage" value="netdata.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="119"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>
<!-- 实施结果验证 -->
<c:url var="urlShowValidateTaskPage" value="netdata.do">
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
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 待归档 -->
<c:url var="urlShowHoldTaskPage" value="netdata.do">
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
<!-- 流程中的步骤已结束 -->

<!-- 下面是公共的URL -->
<!-- 退回 -->
<c:url var="urlShowRejectDealPage" value="netdata.do">
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
<c:url var="urlShowTransferkPage" value="netdata.do">
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
<c:url var="urlShowPhaseReplyPage" value="netdata.do">
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
<c:url var="urlShowInputSplit" value="netdata.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="netDataSubTask1"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="operateType" value="10"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>

<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="netdata.do">
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
<c:url var="urlShowDealReplyRejectPage" value="netdata.do">
  <c:param name="method" value="showDealReplyRejectPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="netDataSubTask1"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>
<!-- 驳回上一级 -->
<c:url var="urlShowRejectBackPage" value="netdata.do">
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
<c:url var="urlShowAcceptDealPage" value="netdata.do">
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
<c:url var="urlBackDealPage" value="netdata.do">
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
<c:url var="urlShowDraftPage" value="netdata.do">
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


<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="netdata.do">
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
<c:url var="urlShowInputSplitAudit" value="netdata.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="netDataSubTask1"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="30"/>  
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="netdata.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="netDataSubTask1"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="55"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="netdata.do">
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
   			url = "netdata.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
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
						 	<%if(taskName.equals("ProjectCreateTask")){ %>
						 		<select id="dealSelector">
									 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									 <!-- 确认受理 -->
									  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  <!-- 驳回 -->
									  <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									 	   <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
									 	  	<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowProjectCreateTaskPage}"><bean:message bundle="netdata" key="operateType.projectCreateTask"/> </option>
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
									 		<% }%>
									 <%} else { %>
									 		<!-- 是子任务 -->
									 		<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="netdata" key="operateType.subTaskReply"/></option>
									 		<%}%>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									  </c:if>
							 	</select>
						    <%}}else if(taskName.equals("AuditTask")){%>	
								<select id="dealSelector">
								<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									 <!-- 确认受理 -->
									  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
								<%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									 	  <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false")){ %>
									 	  	 <!-- 不需要等待回复 -->
									 	  	 <% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowAuditTaskPage}"><bean:message bundle="netdata" key="operateType.auditTask"/> </option>
											  <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet" key="common.transferAudit"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
											  <%}%>
										 
									  <%} else { %>
									 		<!-- 有子任务 -->
									 		<% if (ifwaitfor.equals("false")) {%>
									 			 <option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet" key="common.splitReplyAudit"/></option>
									 		<% }%>
									 <% } %>
									  <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									   </c:if>
							 	</select>
						    <%}}else if(taskName.equals("PermitTask")){%>	
								<select id="dealSelector">
								<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									 <!-- 确认受理 -->
									  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
								<%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>		
									  	  <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false")){ %>
									 	  	 <!-- 不需要等待回复 -->
									 	  	 <% if (ifwaitfor.equals("false")) {%>
											  <option value="${urlShowPermitTaskPage}"><bean:message bundle="netdata" key="operateType.permitTask"/> </option>
											  <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet" key="common.transferAudit"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>	  
										 	<%} %>
									  <%} else { %>
									 		<!-- 有子任务 -->
									 		<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet" key="common.splitReplyAudit"/></option>
									 		<% }%>
									 <% } %>
									  <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									   </c:if>
							 	</select>
						     <%}} else if(taskName.equals("PlanTask")){%>	
								<select id="dealSelector">
									 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									 	  <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false")){ %>
									 	   	<!-- 不需要等待回复 -->
									 	  	 <% if (ifwaitfor.equals("false")) {%>
											  <option value="${urlShowPlanTaskPage}"><bean:message bundle="netdata" key="operateType.planTask"/> </option>
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
									  		<% }%>
									  <%} else { %>
									 		<!-- 有子任务 -->
									 		<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="netdata" key="operateType.subTaskReply"/></option>
									 		<%} %>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									   </c:if>
							 	</select>
						  	<%}}else if(taskName.equals("ExecuteTask")){%>	
								<select id="dealSelector">
									 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  <!-- 驳回 -->
									  <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									 	 <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false")){ %>
									 	  	<% if (ifwaitfor.equals("false")) {%>
											  <option value="${urlShowExecuteTaskPage}"><bean:message bundle="netdata" key="operateType.executeTask"/> </option>
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
											<%} %>
									  <%} else { %>
									 		<!-- 有子任务 -->
									 		<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="netdata" key="operateType.subTaskReply"/></option>
									 		<%} %>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									   </c:if>
							 	</select>
							<%}}else if(taskName.equals("CheckDataTask")){%>	
								<select id="dealSelector">
									 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  <!-- 驳回 -->
									  <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									 	 <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false")){ %>
									 	  	<% if (ifwaitfor.equals("false")) {%>
											  <option value="${urlShowCheckTaskPage}"><bean:message bundle="netdata" key="operateType.checkDataTask"/> </option>
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
											<%} %>
									  <%} else { %>
									 		<!-- 有子任务 -->
									 		<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="netdata" key="operateType.subTaskReply"/></option>
									 		<%} %>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									   </c:if>
							 	</select>
							<%}}else if(taskName.equals("TestOperationTask")){%>	
								<select id="dealSelector">
									 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  <!-- 驳回 -->
									  <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									 	 <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false")){ %>
									 	  	<% if (ifwaitfor.equals("false")) {%>
											  <option value="${urlShowTestTaskPage}"><bean:message bundle="netdata" key="operateType.testOperationTask"/> </option>
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
											<%} %>
									  <%} else { %>
									 		<!-- 有子任务 -->
									 		<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="netdata" key="operateType.subTaskReply"/></option>
									 		<%} %>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									   </c:if>
							 	</select>
					    	<%}}else if(taskName.equals("ValidateTask")){%>	
								<select id="dealSelector">
									 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									  <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									 	 <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false")){ %>
									 	  	<% if (ifwaitfor.equals("false")) {%>
											  <option value="${urlShowValidateTaskPage}"><bean:message bundle="netdata" key="operateType.validateTask"/> </option>
											  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
											<%} %>
									<%} else { %>
									 		<!-- 有子任务 -->
									 		<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="netdata" key="operateType.subTaskReply"/></option>
									 		<%} %>
									 <% } %>
									  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									   </c:if>
							 	</select>
					   		<%}}else if(taskName.equals("HoldTask")){%>	
								<select id="dealSelector">
									<option value="${urlShowHoldTaskPage}"><bean:message bundle="netdata" key="operateType.holdTask"/> </option>
							 	</select>
							<%}else if(taskName.equals("RejectTask")){%>	
								<select id="dealSelector">
								  <option value="${urlBackDealPage}"><bean:message bundle="sheet" key="common.reSend"/></option>
								</select>
					  		<%}else if(taskName.equals("DraftTask")){%>	
								<select id="dealSelector">
								  <option value="${urlShowDraftPage}"><bean:message bundle="netdata" key="operateType.DraftTask"/></option>
								</select>
					   		<%} %>
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
   			url = "netdata.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
        }
		eoms.util.appendPage("sheet-deal-content",url);		
	});
</script>
<%}%>


<c:if test="${sheetMain.status==0}"> 
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
 <%if(isAdmin.equals("true")){%>
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
