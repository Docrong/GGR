<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="java.util.List;" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
String roleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));

//System.out.println("=====roleId=====>"+roleId);
CommonFaultTask task = new CommonFaultTask();
String operaterType = "";

String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
	 task = (CommonFaultTask)request.getAttribute("task");
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
String ifInvokeUrgentFault = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvokeUrgentFault"));
 System.out.println("taskName>>>>>>"+taskName);
 System.out.println("ifInvokeUrgentFault>>>>>>"+ifInvokeUrgentFault); 
String teamFlag = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("teamFlag"));
 String alarmMethod="update";
 if(userId.equals(sendUserId))
    alarmMethod="";
 else{
 if("DraftHumTask".equals(taskName))
    alarmMethod="new";
 else if("".equals(taskName))
    alarmMethod="";
 }
%>


<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript">
window.onload=function(){
changeAuditBasis();
}
Ext.onReady(function() {
	var tabConfig = {
		items : [{
			id : 'sheetinfo',
			text : '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.historyView"/>',
			url : 'commonfault.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			url :  'commonfault.do?method=showWorkFlow&linkServiceName=iCommonFaultLinkManager&dictSheetName=dict-sheet-commonfault&description=mainOperateType&sheetKey=${sheetMain.id}',
			isIframe : true
		},{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'commonfault.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="commonfault.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
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
	    
	    var url2="commonfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
	    eoms.util.appendPage("sheet-deal-content",url2);  
	}else if(obj == 2){
	    
	     var url2="commonfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
      	 eoms.util.appendPage("sheet-deal-content",url2);
	}else{
	     
         var url2="commonfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
	     eoms.util.appendPage("sheet-deal-content",url2);
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="commonfault.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
             eoms.util.appendPage("sheet-deal-content",url2,true);
	}
	if(obj == 2){
	     var url2="commonfault.do?method=showBackCheckDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}";
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
  
  function changeAuditBasis()
        {
         var auditBasis = document.getElementById("check").value;
         if (auditBasis=='1')
         {
         $('checkSuccess').show();
         $('rejected').hide();
         }else if (auditBasis=='2'){
         $('checkSuccess').hide();
         $('rejected').show();
         }else{
         $('checkSuccess').hide();
         $('rejected').hide();
         }
        }
 
  function checkStatus(){
    var alarmSolveTime = document.getElementById("alarmSolveTime").value;
	var url = "${app}/sheet/commonfault/commonfault.do?method=showSuccess&sheetKey=${sheetMain.id}&alarmSolveTime="+alarmSolveTime;
	location.href = url;
}
  function rejectedStatus(){
   var suggestions= document.getElementById("suggesions").value;
   var url ="${app}/sheet/commonfault/commonfault.do?method=showReject&sheetKey=${sheetMain.id}&suggestions="+suggestions;
   location.href =url;
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
	
<c:if test="${sheetMain.mainIsPack == '1030102'||sheetMain.mainIsPack==null||empty sheetMain.mainIsPack}">
	<table class="formTable"> 
	  <caption></caption>	  
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmId"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainAlarmId" scope="request"/></td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmNum"/></td>
		<td class="content" ><bean:write name="sheetMain" property="mainAlarmNum" scope="request"/></td>
	  </tr>  	  
	  
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmSolveDate"/></td>
		<td class="content" colspan='3'>
			<bean:write name="sheetMain" property="mainAlarmSolveDate" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		
	  </tr>  
      <tr>
      <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmLevel"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="mainAlarmLevel" scope="request"/>
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
</c:if>	
	<table class="formTable"> 
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
		<td class="content" colspan='3'>
			<bean:write name="sheetMain" property="mainCompleteLimitT3" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		  </tr>
      <tr>     
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainSendMode"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainSendMode}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetName"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainNetName" scope="request"/></td>
	  </tr>
	  
     
      <tr>
  <td class="label"><bean:message bundle="commonfault" key="commonfault.mainEquipmentFactory"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainEquipmentFactory}" beanId="ItawSystemDictTypeDao"/></td>      
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
      <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultDiscoverableMode"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultDiscoverableMode}" beanId="ItawSystemDictTypeDao"/>
		</td> 
      </tr>	
	   <tr>
	     <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfAffectOperation"/></td>
		 <td class="content"><eoms:id2nameDB id="${sheetMain.mainIfAffectOperation}" beanId="ItawSystemDictTypeDao"/></td>
	      <td class="label"><bean:message bundle="commonfault" key="commonfault.mainPretreatment"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainPretreatment}" beanId="ItawSystemDictTypeDao"/></td>
	
	   </tr>
	   
	<c:if test="${!empty sheetMain.mainApplySheetId}">	  
	  <tr>
  		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainApplySheetId"/></td>
		<td colspan='3' class="content">
			<bean:write name="sheetMain" property="mainApplySheetId" scope="request"/>
		</td>
	  </tr>	  
    </c:if>	
	
		<c:if test="${sheetMain.mainIsPack == '1030101'}">
	<tr>
  		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmDesc"/></td>
		<td class="content" colspan='3'><pre><bean:write name="sheetMain" property="mainAlarmDesc" scope="request"/></pre></td>
	 </tr>
	<tr>
		  <td class="label" colspan="4" width="120"> 
		   <iframe height="400"
	       width="100%" src="../commonfault/commonfaultpack.do?method=showOwnStarterList&mainId=${sheetMain.id}&alarmMethod=<%=alarmMethod%>&taskName=<%=taskName%>"></iframe> 
		   </td>
		</tr>	  
	</c:if> 
	  
		<tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='5'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="commonfault" 
		             viewFlag="Y"/> 
		    </td>
		</tr>
		
	<c:if test="${sheetMain.mainCheckStatus== '1'}">
	<tr >
       <td class="label">告警核查操作：</td>
		<td class="content" colspan='3'>
		  <select name="check" id ="check" size="1" style="FONT-SIZE: 8pt"  onclick ="changeAuditBasis()">
		    <option value="0">请选择</option>
            <option value="1">通过</option>
            <option value="2">驳回</option>
          </select>
        </td>
	</tr>  
	
	<tr id='checkSuccess'>
	<td class="label" nowrap>核查告警清除时间</td>
		<td class="content" colspan='2'>
				<input type="text" name="alarmSolveTime" id="alarmSolveTime"
					onclick="popUpCalendar(this, this,'yyyy-mm-dd hh:ii:ss');"
					styleClass="text medium" alt="allowBlank:true" style="width: 150"
					value="${alarmSolveTime}" readonly="true"/>
		</td>
		<td>
		<input type="button"
					value="确认"
					onClick="javascript:checkStatus();" class="button">
		</td>	
	</tr>
	
	<tr id='rejected'>
			<td class="label" nowrap>驳回意见</td>
			<td width=content colspan="2">
				<input type="text" id="suggesions" name="suggestions"
					styleClass="text medium"  alt="allowBlank:true"
					style="width: 350" />
			</td>
			<td>
		        <input type="button"
					value="确认"
					onClick="javascript:rejectedStatus();" class="button">
		</td>
		</tr> 	  
	</c:if> 	
		
		
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
  <c:param name="teamFlag" value="${teamFlag}"/> 
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

<!-- 显示处理完成 -->
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>
  <c:param name="teamFlag" value="${teamFlag}"/> 
</c:url>

<!-- 草稿节点-显示草稿页面 -->
<c:url var="urlShowDraftPageDealPage" value="commonfault.do">
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
<!-- 被驳回，显示草稿修改页面 -->
<c:url var="urlBackDealPage" value="commonfault.do">
	<c:param name="method" value="showDealPage" />
	<c:param name="sheetKey" value="${sheetMain.id}" />
	<c:param name="piid" value="${sheetMain.piid}" />
	<c:param name="taskId" value="${taskId}" />
	<c:param name="taskName" value="${taskName}" />
	<c:param name="operateRoleId" value="${operateRoleId}" />
	<c:param name="TKID" value="${TKID}" />
	<c:param name="taskStatus" value="${taskStatus}" />
	<c:param name="preLinkId" value="${preLinkId}" />
	<c:param name="operateType" value="53" />
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>
	<c:param name="backFlag" value="yes"/>
</c:url>

<!-- 归档时，退回 -->
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
  <c:param name="operateType" value="17"/>
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

<!-- 环节内（组内）移交
<c:url var="urlShowTransferPage" value="commonfault.do">
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
</c:url> -->
<!-- 移交 -->
<c:url var="urlShowTransferPage" value="commonfault.do">
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
  <c:param name="teamFlag" value="${teamFlag}"/> 
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
  <c:param name="teamFlag" value="${teamFlag}"/>  
</c:url>

<!-- 阶段回复 -->
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

<!-- 驳回上一级 -->
<c:url var="urlShowRejectBackPage" value="commonfault.do">
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
<c:url var="urlShowAcceptDealPage" value="commonfault.do">
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
<c:url var="urlShowInputSplit" value="commonfault.do">
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
  <c:param name="teamFlag" value="${teamFlag}"/>
</c:url>
<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="commonfault.do">
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
<c:url var="urlShowDealReplyAcceptPage" value="commonfault.do">
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
<c:url var="urlShowDealReplyRejectPage" value="commonfault.do">
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

<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="commonfault.do">
  <c:param name="method" value="showDealPage"/>
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
<c:url var="urlShowInputSplitAudit" value="commonfault.do">
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

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="commonfault.do">
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
		      <!--option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option-->
			 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
			 	   <!-- 是父任务 -->
			 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
			 	  	<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
					  <!-- 流程步骤，移交，阶段回复，分派 -->
					     <option value="${urlShowTransmitDealPage}"><bean:message bundle="commonfault" key="operateType.transmit1"/> </option>
					     <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
					     <!--option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option-->
					     <option value="${urlShowTransferPage}"><bean:message bundle="sheet" key="common.transfer"/></option>		     
					     <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
				 		 <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>						 				     
			 		<% }else{%>
			 			<!-- 需要等待回复 -->
			 			 <c:if test="${needDealReply == 'true'}">
			                <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			                <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			             </c:if>
			             <c:if test="${ empty needDealReply }">
			                <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
			             </c:if>
			          <%} %>
			 	 <%} else { %>
			 		<!-- 是子任务 -->
			 		<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
			 			<option value="${urlShowDispatchPage}"><bean:message bundle="commonfault" key="operateType.subTaskReply"/></option>			 		    
			            <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>		            
			 		<%}else {%>
			 		    <!-- 需要等待回复 -->
			 		    <c:if test="${needDealReply == 'true'}">
			              <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			              <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			            </c:if>
			            <c:if test="${empty needDealReply }">
			 			 <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
			 		   </c:if>
			 		  <%} %>
			 		<%}%>
			<% } %>
	 	</select>
          
	<%}else if(taskName.equals("SecondExcuteHumTask")){%>	
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		      <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		      <!--
		      <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		       -->
			 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
			 	   <!-- 是父任务 -->
			 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
			 	  	<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
					  <!-- 流程步骤，移交，阶段回复，分派 -->
					    <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
					    <option value="${urlShowTransmit2DealPage}"><bean:message bundle="commonfault" key="operateType.transmit2"/></option>
						  
						  <!--option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option-->
						  <option value="${urlShowTransferPage}"><bean:message bundle="sheet" key="common.transfer"/></option>	
		                  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		                  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>	            	                  
			 		<%}else{%>	
			 			<c:if test="${needDealReply == 'true'}">
			                <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			                <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			             </c:if>
			             <c:if test="${ empty needDealReply }">
			                <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
			             </c:if>
			         <%} %>
			     <%} else { %>
			 		<!-- 是子任务 -->
			 		<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
			 			<option value="${urlShowDispatchPage}"><bean:message bundle="commonfault" key="operateType.subTaskReply"/></option>			 		    
			            <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>		            
			 		<%}else {%>
			 		<c:if test="${needDealReply == 'true'}">
			              <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			              <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			            </c:if>
			            <c:if test="${empty needDealReply }">
			 			 <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
			 		   </c:if>
			 		<%} %>
			     <%} %>
			 <%} %>
	 	</select>
		
	<%}else if(taskName.equals("ThirdExcuteHumTask")){%>	
	  	<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		      <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		      <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
			 <%} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
			 	   <!-- 是父任务 -->
			 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
			 	  	<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
					  <!-- 流程步骤，移交，阶段回复，分派 -->
					      <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
						  <!--option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option-->
						  <option value="${urlShowTransferPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
						  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
				 		  <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>			           						  
			 		<%}else{%>
					   <c:if test="${needDealReply == 'true'}">
			                <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			                <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			             </c:if>
			             <c:if test="${ empty needDealReply }">
			                <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
			             </c:if>
			      	<%} %>
			 		
			     <%} else { %>
			 		<!-- 是子任务 -->
			 		<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
			 			<option value="${urlShowDispatchPage}"><bean:message bundle="commonfault" key="operateType.subTaskReply"/></option>			 		    
			            <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>		            
			 		<%}else{%>
			 		<c:if test="${needDealReply == 'true'}">
			                <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			                <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			             </c:if>
			             <c:if test="${ empty needDealReply }">
			                <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
			             </c:if>
			 		<% } %>
			     <%} %>
				 
			 <%} %>
	 	</select>
	
	<%}else if(taskName.equals("DraftHumTask")){%>	
		<select id="dealSelector">
		  <option value="${urlShowDraftPageDealPage}"><bean:message bundle="commonfault" key="operateType.draft"/></option>
		</select>
	<%}else if(taskName.equals("BackHumTask")){%>	
		<select id="dealSelector">
		  <option value="${urlBackDealPage}"><bean:message bundle="commonfault" key="operateType.reSend"/></option>
		</select>		
	<%}else if(taskName.equals("ExamineHumTask")){%>	
		<select id="dealSelector">

		  
	 	   <!-- 是父任务 -->
	 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
	 	  	<!-- 不需要等待回复 -->
	 	  	<% if (ifwaitfor.equals("false")) {%>
			  <!-- 流程步骤 -->
			  <option value="${urlShowExamineYesDealPage}"><bean:message bundle="commonfault" key="operateType.Examine"/></option>
			  <option value="${urlShowExamineNoDealPage}"><bean:message bundle="commonfault" key="operateType.examineReject"/></option>
			  <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet" key="common.transferAudit"/></option>
			  <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>			           						  
	 		<%}else{%>
			 <!-- 需要等待回复 -->	 
			  <c:if test="${needDealReply == 'true'}">
		        <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
		        <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
		      </c:if>
	 		  <c:if test="${empty needDealReply }">
			    <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>			           						  
	 		 </c:if><%} %>
	 		
	     <%} else { %>
	 		<!-- 是子任务 -->
	 		<!-- 不需要等待回复 -->
	 	  	<% if (ifwaitfor.equals("false")) {%>
	 	  		<option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet" key="common.splitReplyAudit"/></option>	 		
			    <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>			           						  
	 		  <%} else {%>
	 	     <!-- 需要等待回复 -->
	 	      <c:if test="${needDealReply == 'true'}">
		        <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
		        <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
		      </c:if>
	 		  <c:if test="${empty needDealReply}">
	 		   <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>					 	  
	 		    </c:if>
			   <%} %>
	     <%} %>			  
				  
		  
		</select>
	<%}else if(taskName.equals("HoldHumTask")){ %>
		<select id="dealSelector">
		  <option value="${urlShowHoldDealPage}"><bean:message bundle="commonfault" key="operateType.hold"/></option>
		  <option value="${urlShowRejectDealPage}"><bean:message bundle="commonfault" key="operateType.reject"/></option>
		</select>	
	<%} %>
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
 	<div id="buttonDisplay" style="display:block">
    <input type="button" title="执行该操作，工单将进入强制归档状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceHold"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
    <input type="button" title="执行该操作，工单将进入强制作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceNullity"/>" onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
    <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
 <% }else if(taskStatus.equals("5")&& !taskName.equals("cc")&& !taskName.equals("reply")&& !taskName.equals("advice")){%>
     <div id="buttonDisplay">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
     </div>
 <% }else if((taskStatus == null || taskStatus.equals(""))&&(userId.equals(sendUserId))){%> 
 <div id="advice" style="display:block">
     <input type="button" title="执行该操作，工单将进入作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.nullity"/>"  onclick="$('advice').style.display='none';forceOperation(2);">
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
<%if(roleId.indexOf("190") > -1){%>
	<input type="button" title="newSheet" value="<bean:message bundle='sheet' key='button.creatnew'/>" onclick="createNewSheet();">
<%} %>
<%@ include file="/common/footer_eoms.jsp"%>
<script type="text/javascript">
	function createNewSheet()
	{
		//alert("555555555");
		//	 var undo=document.location.href;
	 	//	undo = undo.substring(0,undo.indexOf("?")+1)+"method=showNewSendPage&sheetKey=${sheetMain.id}";
	 	//	location.href = undo;
		//
		//newSheet();
		window.location.href= "${app}/sheet/commonfault/commonfault.do?method=showNewSendPage&sheetKey=${sheetMain.id}&processname=CommonFaultMainFlowProcess";
	}
   
</script>