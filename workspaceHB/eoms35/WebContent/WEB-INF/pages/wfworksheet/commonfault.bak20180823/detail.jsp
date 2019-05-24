<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask" %>
<%@page import="com.boco.eoms.sheet.commonfault.model.CommonFaultMain" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="com.boco.eoms.commons.util.xml.XmlManage" %>
<%@page import="com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless" %>
<%@page import="com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@page import="java.util.List;" %>
<%
String currentDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
String roleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
String subDeptId =sessionform.getDeptid().substring(0,3);
System.out.println("--------------------ph-------------deptId===>" + subDeptId);
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
CommonFaultMain basemain = (CommonFaultMain)request.getAttribute("sheetMain");
String sendUserId = basemain.getSendUserId();
System.out.println("sendUserId>>>>>>"+sendUserId);
System.out.println("11111111111111111");
String mainNetName = basemain.getMainNetName();
System.out.println("2222222222222");
String tttype ="";
if(!"".equals(mainNetName)&&mainNetName !=null){
INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager)ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
System.out.println("333333333333");
NetOwnershipwireless netownershipwireless = netOwnershipwirelessManager.getNetOwnershipByNetName(mainNetName);
System.out.println("44444444444444");
if( netownershipwireless !=null){
 tttype = 	netownershipwireless.getTttype();
 }
}
System.out.println("5555555555");
//______________151022lipan
String[] mainT2Applicants=null;
String[] mainT2ApplyCheckTimes=null;
String[] mainCheckDealers=null;
String[] mainT1ReplyTimes=null;
if(basemain.getMainCheckStatus()!=null&&basemain.getMainT2Applicant()!=null){
	if(basemain.getMainCheckStatus().equals("2") || basemain.getMainCheckStatus().equals("3")){
		mainT2Applicants = basemain.getMainT2Applicant().split(";");
		mainT2ApplyCheckTimes = basemain.getMainT2ApplyCheckTime().split(";");
		mainCheckDealers = basemain.getMainCheckDealer().split(";");
		mainT1ReplyTimes = basemain.getMainT1ReplyTime().split(";");
		if(mainT2Applicants.length!=0){
			request.setAttribute("mainT2Applicants_0",mainT2Applicants[0]);
			request.setAttribute("mainT2ApplyCheckTimes_0",mainT2ApplyCheckTimes[0]);
			request.setAttribute("mainCheckDealers_0",mainCheckDealers[0]);
			request.setAttribute("mainT1ReplyTimes_0",mainT1ReplyTimes[0]);
		}
		if(mainT2Applicants.length==2||mainT2Applicants.length==3){
			request.setAttribute("mainT2Applicants_1",mainT2Applicants[1]);
			request.setAttribute("mainT2ApplyCheckTimes_1",mainT2ApplyCheckTimes[1]);
			request.setAttribute("mainCheckDealers_1",mainCheckDealers[1]);
			request.setAttribute("mainT1ReplyTimes_1",mainT1ReplyTimes[1]);
		}
		if(mainT2Applicants.length==3){
			request.setAttribute("mainT2Applicants_2",mainT2Applicants[2]);
			request.setAttribute("mainT2ApplyCheckTimes_2",mainT2ApplyCheckTimes[2]);
			request.setAttribute("mainCheckDealers_2",mainCheckDealers[2]);
			request.setAttribute("mainT1ReplyTimes_2",mainT1ReplyTimes[2]);
		}
	}
}
String urlTitle = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.urlTitle");//从配置文件中读取url地址
String stringType = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.stringType");//从配置文件中读取url地址
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
<script type="text/javascript" src="${app}/scripts/soprule.js" charset="gb2312"></script>
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
		}, {
			text : '追单列表',
	 		url  : '../commonfault/commonfaultpack.do?method=showOwnStarterList&mainId=${sheetMain.id}&alarmMethod=<%=alarmMethod%>&taskName=<%=taskName%>',
			isIframe : true
		}, {
			text : '副单列表',
	 		url  : 'commonfault.do?method=showVisList&id=${sheetMain.id}',
			isIframe : true
		}]
	};
	var tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);
    <%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
    	<%if(taskName.equals("cc")){ %>
	var url2="commonfault.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-15&taskStatus=${taskStatus}";
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
   
 function getalarmpreresult(mainAlarmId) {
	Ext.Ajax.request({
		url : "${app}/sheet/commonfault/commonfault.do?method=getAlarmPreResult&mainAlarmId="+mainAlarmId,				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			var flag = data[0].flag;	
			if(flag==""||flag ==null){
				alert("无数据!");
			}else{
				document.getElementById("selvalue").value = flag;
				alert("查询成功!");
			}
			
		}
	});
}

  function GetAlarmPreOperation(sheetId){
    var url ="http://10.25.116.75:8080/inas-web/netAct/manuPreDeal.action?order_id="+sheetId+"&operateUserId=<%=userId%>";
  window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
  }
  function GetAlarmPreSearch(sheetId){
    var url ="http://10.25.116.75:8080/inas-web/netAct/orderPredealZHCX.action?order_id="+sheetId+"&operateUserId=<%=userId%>";
   window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
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
    if(alarmSolveTime==""||alarmSolveTime==null){
      alert("请输入核实时间");}
    else{
	var url = "${app}/sheet/commonfault/commonfault.do?method=showSuccess&sheetKey=${sheetMain.id}&alarmSolveTime="+alarmSolveTime+"&type=interface&interface=interface&userName=<%=userId%>";
	location.href = url;
	}
}
  function rejectedStatus(){
   var suggestions= document.getElementById("suggesions").value;
 var accessories= document.getElementById("rejectAccessories").value;
     if(suggestions ==""||suggestions==null){
     alert("请输入驳回意见");}
   else{
     var url ="${app}/sheet/commonfault/commonfault.do?method=showReject&sheetKey=${sheetMain.id}&suggestions="+suggestions+"&accessories="+accessories+"&type=interface&interface=interface&userName=<%=userId%>";
  location.href =url;
   }
  }

</script>
<html>
<body onload="win_load('<bean:write name='sheetMain' property='mainAlarmId' scope='request' />')">
<h3 class="sheet-title">

</h3>
<!-- ###################################告警百科####################################################### -->	
<!-- form表单中action的属性修改成告警百科对应的url -->
<%if("http".equals(stringType)){ %>
	<form action="<%=urlTitle %>" method="post" target="_blank">
		<input type="hidden" name="token" value="<%=request.getAttribute("token")%>" />
		<input style="margin-left: 91.7%" type="submit" value="进入告警百科" />
	</form>
<%}else{ %>
	<form action="/sheet/commonfault/centralcommonfault.do?method=interfaceForBaiKe" method="post" target="_blank">
		<input type="hidden" name="token" value="<%=request.getAttribute("token")%>" />
		<input style="margin-left: 91.7%" type="submit" value="进入告警百科" />
	</form>
<%} %>
<!-- ###################################告警百科######################################################## -->


<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
  <!-- Sheet Detail Tab Start -->
  <div id="sheetinfo">
  	<logic:present name="sheetMain" scope="request">
	<%@ include file="/WEB-INF/pages/wfworksheet/commonfault/basedetailnew.jsp"%>
	<br/>
	
	<table class="formTable"> 
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmId"/></td>
		<td class="content"><bean:write name="sheetMain" property="mainAlarmId" scope="request"/>
			<INPUT id="btn"  type="button" value="sop" onclick="go('<bean:write name='sheetMain' property='mainAlarmId' scope='request' />')">
			  	<br/>
			     	<input type="button" class="submit" value="自动预处理结果" onclick="getalarmpreresult('${sheetMain.sheetId}')"/>
    <c:if test="${!empty sheetMain.mainPretResult}">
			     	<input type="button" class="submit" value="指令操作"onclick="GetAlarmPreOperation('${sheetMain.sheetId}')"/>
            <input type="button" class="submit" value="综合查询"onclick="GetAlarmPreSearch('${sheetMain.sheetId}')"/>
    </c:if>
			</td>
		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmNum"/></td>
		<td class="content" ><bean:write name="sheetMain" property="mainAlarmNum" scope="request"/></td>
	  </tr>  
	  	  
      <tr>
        <td class="label">查询结果</td>
		<td class="content" colspan='3'>
			
			 <textarea name="selvalue" id="selvalue" readonly="readonly" value="" cols="100" rows="6"></textarea>
			
		</td>
		
	  </tr>
	</table>
	  	

	<table class="formTable"> 
	  <caption></caption>	  
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmSolveDate"/></td>
		<td class="content" colspan='3'>
			<bean:write name="sheetMain" property="mainAlarmSolveDate" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		
	  </tr>  
      <tr>
      <td class="label">网管告警级别</td>
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
	<td class="content" ><eoms:id2nameDB id="${sheetMain.mainSendMode}" beanId="ItawSystemDictTypeDao"/></td>
		<%if(!"".equals(tttype)&&tttype !=null){%>
		<td class="label">网元铁塔属性</td>
		<td class="content"><eoms:id2nameDB id="<%=tttype%>" beanId="ItawSystemDictTypeDao"/></td>
		<%}%>
		</tr>
		<tr>
		<td class="label">网元ID</td>
		<td class="content"><bean:write name="sheetMain" property="mainNeId" scope="request"/></td>
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
	      <td class="label">特殊工单说明</td>
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
	
		<c:if test="${!empty sheetMain.equipmentName}">	  
	  <tr>
  		<td class="label">设备名称</td>
		<td colspan='3' class="content">
			<bean:write name="sheetMain" property="equipmentName" scope="request"/>
		</td>
	  </tr>	  
    </c:if>	
    	<tr>
  		<td class="label">铁塔编号</td>
		<td class="content" colspan='3'>${sheetMain.mainTowerId}</td>
	 </tr>
	<tr>
  		<td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmDesc"/></td>
		<td class="content" colspan='3'><pre><bean:write name="sheetMain" property="mainAlarmDesc" scope="request"/></pre></td>
	 </tr>
	<tr>
  		<td class="label">故障发现时间</td>
		<td class="content"><bean:write name="sheetMain" property="mainDetectFaultTime" scope="request"/></td>
  		<td class="label">告警标题</td>
		<td class="content"><bean:write name="sheetMain" property="mainSendSystem" scope="request"/></td>
	 </tr>
      <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmSolveDate"/></td>
		<td>
			<div id="selValueAlarmDisplay1" style="display: block;">
			<bean:write name="sheetMain" property="mainAlarmSolveDate" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
			</div>
			<div id="selValueAlarmDisplay2" style="display: none;">
			<input type="text" class="text" name="selValueAlarm" id="selValueAlarm" readonly="readonly" value=""/>
			</div>
		</td>
		<td class="content" colspan='2'>
			<input type="button" class="submit" style=" color:red"  value="获取告警清除时间" onclick="onFaultAvoidDetail();"/>
		</td>
	  </tr>
	<tr>
  		<td class="label">是否系统智能预处理</td>
	 <c:choose>
	 <c:when test="${sheetMain.mainNetSortOne=='101010401'}">
	      <c:choose>
	       <c:when test="${!empty sheetMain.mainPretResult||!empty sheetMain.mainFaultFirstDealDesc}">
	         <td class="content">是</td>  
	      </c:when>
	      <c:otherwise>
	        <td class="content">否</td>
	      </c:otherwise>
   	   </c:choose>	   
	 </c:when>
	 <c:otherwise>
	     <c:choose>
	       <c:when test="${!empty sheetMain.mainPretResult}">
	         <td class="content">是</td>  
	      </c:when>
	      <c:otherwise>
	        <td class="content">否</td>
	      </c:otherwise>
   	   </c:choose>	   
	</c:otherwise>
	</c:choose>
  		<td class="label">是否人工预处理</td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.mainManualPreSolve}" beanId="ItawSystemDictTypeDao"/></td>
	 </tr>
	<tr>
  		<td class="label">自动预处理结果</td>
		<td class="content">
					<pre><bean:write name="sheetMain" property="mainPretResult" scope="request"/></pre>
		</td>
  		<td class="label">预处理重现结果第一次</td>
		<td class="content"><bean:write name="sheetMain" property="mainFaultFirstDealDesc" scope="request"/></td>
	 </tr>
	<tr>
  		<td class="label">预处理重现结果第二次</td>
		<td class="content"><bean:write name="sheetMain" property="mainPretResultTwo" scope="request"/></td>
  		<td class="label">预处理重现结果第三次</td>
		<td class="content"><bean:write name="sheetMain" property="mainPretResultThree" scope="request"/></td>
	 </tr>
	<tr>
  		<td class="label">预处理重现结果第四次</td>
		<td class="content" colspan='3'><bean:write name="sheetMain" property="mainPretResultFour" scope="request"/></td>
	 </tr>
	 <tr>
  		<td class="label">驳回意见</td>
		<td class="content" colspan='3'><pre>${linkUntreadReason}</pre></td>
	 </tr>
<c:if test="${sheetMain.qcMark==1}">
	<tr>
  		<td class="label">质检评语</td>
		<td class="content" colspan='3'><pre><bean:write name="sheetMain" property="qcRemark" scope="request"/></pre></td>
	 </tr>
</c:if>
<!--<c:if test="${sheetMain.mainIsPack == '1030101'}">
	<tr>
		  <td class="label" colspan="4" width="120"> 
		   <iframe height="400"
	       width="100%" src="../commonfault/commonfaultpack.do?method=showOwnStarterList&mainId=${sheetMain.id}&alarmMethod=<%=alarmMethod%>&taskName=<%=taskName%>"></iframe> 
		   </td>
		</tr>	  
	</c:if> -->
	  
		<tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='5'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="commonfault" 
		             viewFlag="Y"/> 
		    </td>
		</tr>
					<c:if test="${sheetMain.mainCheckStatus== '1'&& isAdmin == 'true'}">
	<tr>
			<td class="label" nowrap>申请附件</td>
		     <td  >
		    <eoms:attachment name="sheetMain" property="applyAccessories" 
		            scope="request" idField="applyAccessories" appCode="commonfault" 
		             viewFlag="Y"/> 
		    </td>	  
	
		<td class="label" nowrap>驳回附件</td>
		     <td >
		    <eoms:attachment name="sheetMain" property="rejectAccessories" 
		            scope="request" idField="rejectAccessories" appCode="commonfault" 
		             viewFlag="Y"/> 
		    </td>	  
	</tr>	
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
		<td class="content" colspan='3'>				<input type="text" name="alarmSolveTime" id="alarmSolveTime"

				onclick="popUpCalendar(this, this,'yyyy-mm-dd hh:ii:ss');"
					styleClass="text medium" alt="allowBlank:true" style="width: 150"
					value="<%=currentDate%>" readonly="true"/>
		</td>
		<td>
		<input type="button"
					value="确认"
					onClick="javascript:checkStatus();" class="button">
		</td>	
	</tr>
	
	
	
	<tr id='rejected'>
			<td class="label" nowrap>驳回意见</td>
		<td width=content colspan="1">
				<textarea name="suggesions" id="suggesions" class="textarea max"
					  		alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'" >${suggesions}</textarea>		
				</td>
  <td class="label">附件</td>
			  <td colspan="1">
			  		 
    		<eoms:attachment name="sheetMain" property="rejectAccessories" 
              scope="request" idField="${sheetPageName}rejectAccessories" appCode="commonfault" />	
   
			  </td>
			<td>
		        <input type="button"
					value="确认"
					onClick="javascript:rejectedStatus();" class="button">
		</td>
		</tr> 	  
	</c:if> 	
		
	<c:if test="${sheetMain.mainCheckStatus== '2'}">
	<tr >
	     <td class="label" nowrap>申请核查次数：</td>
		<td class="content"  ><bean:write name="sheetMain" property="checkCount" scope="request"/></td>

       <td class="label" nowrap>核查告警清除时间：</td>
		<td class="content"  colspan='3'><bean:write name="sheetMain" property="mainCheckTime" format="yyyy-MM-dd HH:mm:ss" scope="request"/></td>
	</tr>  		  
		<tr>
			<td class="label" nowrap>申请附件</td>
		     <td  >
		    <eoms:attachment name="sheetMain" property="applyAccessories" 
		            scope="request" idField="applyAccessories" appCode="commonfault" 
		             viewFlag="Y"/> 
		    </td>	  
	
		<td class="label" nowrap>驳回附件</td>
		     <td >
		    <eoms:attachment name="sheetMain" property="rejectAccessories" 
		            scope="request" idField="rejectAccessories" appCode="commonfault" 
		             viewFlag="Y"/> 
		    </td>	  
	</tr>	
	<!-- __________________151022新添加lipan ______________-->	
	<%if(mainT2Applicants!=null){ %>
	<tr colspan='5'>
		<table class="formTable">
			<%if(mainT2Applicants.length!=0){ %>
			<tr>
				<td class="label" nowrap>第1次申请核实:</td>
				<td class="label" nowrap>T2申请人:</td>
				<td width="150px" nowrap><bean:write name="mainT2Applicants_0" scope="request"/></td>
				<td class="label" nowrap>申请时间:</td>
				<td nowrap><bean:write name="mainT2ApplyCheckTimes_0" scope="request"/></td>
				<td class="label" nowrap>T1审核人:</td>
				<td nowrap><bean:write name="mainCheckDealers_0" scope="request"/></td>
				<td class="label" nowrap>处理时间:</td>
				<td nowrap><bean:write name="mainT1ReplyTimes_0" scope="request"/></td>
			</tr>  
			<%} if(mainT2Applicants.length==2 || mainT2Applicants.length==3){%>
			<tr>
				<td class="label" nowrap>第2次申请核实:</td>
				<td class="label" nowrap>T2申请人:</td>
				<td width="150px" nowrap><bean:write name="mainT2Applicants_1" scope="request"/></td>
				<td class="label" nowrap>申请时间:</td>
				<td nowrap><bean:write name="mainT2ApplyCheckTimes_1" scope="request"/></td>
				<td class="label" nowrap>T1审核人:</td>
				<td nowrap><bean:write name="mainCheckDealers_1" scope="request"/></td>
				<td class="label" nowrap>处理时间:</td>
				<td nowrap><bean:write name="mainT1ReplyTimes_1" scope="request"/></td>
			</tr>  
			<%} if(mainT2Applicants.length==3){ %>
			<tr>
				<td class="label" nowrap>第3次申请核实:</td>
				<td class="label" nowrap>T2申请人:</td>
				<td width="150px" nowrap><bean:write name="mainT2Applicants_2" scope="request"/></td>
				<td class="label" nowrap>申请时间:</td>
				<td nowrap><bean:write name="mainT2ApplyCheckTimes_2" scope="request"/></td>
				<td class="label" nowrap>T1审核人:</td>
				<td nowrap><bean:write name="mainCheckDealers_2" scope="request"/></td>
				<td class="label" nowrap>处理时间:</td>
				<td nowrap><bean:write name="mainT1ReplyTimes_2" scope="request"/></td>
			</tr>  
			<%} %>
		</table>
	</tr>
	<%} %>
	<!-- _________________________________________-->
	</c:if> 	
		<c:if test="${sheetMain.mainCheckStatus== '3'}">
	<tr >
	     <td class="label" nowrap>申请核查次数：</td>
		<td class="content"  ><bean:write name="sheetMain" property="checkCount" scope="request"/></td>
	
       <td class="label" nowrap>核查告警驳回意见：</td>
		<td class="content"  colspan='3'><bean:write name="sheetMain" property="mainCheckRejected" scope="request"/></td>
	</tr>  		  
		<tr>
			<td class="label" nowrap>申请附件</td>
		     <td  >
		    <eoms:attachment name="sheetMain" property="applyAccessories" 
		            scope="request" idField="applyAccessories" appCode="commonfault" 
		             viewFlag="Y"/> 
		    </td>	  
	
		<td class="label" nowrap>驳回附件</td>
		     <td >
		    <eoms:attachment name="sheetMain" property="rejectAccessories" 
		            scope="request" idField="rejectAccessories" appCode="commonfault" 
		             viewFlag="Y"/> 
		    </td>	  
	</tr>
	<!-- __________________151022新添加lipan ______________-->	
	<%if(mainT2Applicants!=null){ %>
	<tr colspan='5'>
		<table class="formTable">
			<%if(mainT2Applicants.length!=0){ %>
			<tr>
				<td class="label" width="" nowrap>第1次申请核实:</td>
				<td class="label" nowrap>T2申请人:</td>
				<td nowrap><bean:write name="mainT2Applicants_0" scope="request"/></td>
				<td class="label" nowrap>申请时间:</td>
				<td nowrap><bean:write name="mainT2ApplyCheckTimes_0" scope="request"/></td>
				<td class="label" nowrap>T1审核人:</td>
				<td nowrap><bean:write name="mainCheckDealers_0" scope="request"/></td>
				<td class="label" nowrap>处理时间:</td>
				<td nowrap><bean:write name="mainT1ReplyTimes_0" scope="request"/></td>
			</tr>  
			<%} if(mainT2Applicants.length==2 || mainT2Applicants.length==3){%>
			<tr>
				<td class="label" nowrap>第2次申请核实:</td>
				<td class="label" nowrap>T2申请人:</td>
				<td nowrap><bean:write name="mainT2Applicants_1" scope="request"/></td>
				<td class="label" nowrap>申请时间:</td>
				<td nowrap><bean:write name="mainT2ApplyCheckTimes_1" scope="request"/></td>
				<td class="label" nowrap>T1审核人:</td>
				<td nowrap><bean:write name="mainCheckDealers_1" scope="request"/></td>
				<td class="label" nowrap>处理时间:</td>
				<td nowrap><bean:write name="mainT1ReplyTimes_1" scope="request"/></td>
			</tr>  
			<%} if(mainT2Applicants.length==3){ %>
			<tr>
				<td class="label" nowrap>第3次申请核实:</td>
				<td class="label" nowrap>T2申请人:</td>
				<td nowrap><bean:write name="mainT2Applicants_2" scope="request"/></td>
				<td class="label" nowrap>申请时间:</td>
				<td nowrap><bean:write name="mainT2ApplyCheckTimes_2" scope="request"/></td>
				<td class="label" nowrap>T1审核人:</td>
				<td nowrap><bean:write name="mainCheckDealers_2" scope="request"/></td>
				<td class="label" nowrap>处理时间:</td>
				<td nowrap><bean:write name="mainT1ReplyTimes_2" scope="request"/></td>
			</tr>  
			<%} %>
		</table>
	</tr>
	<%} %>
	<!-- _________________________________________--> 		  
</c:if> 	


<tr>
		<td class="label">T2驳回T1次数</td>
		<td colspan="3">${sheetMain.mainT2RejectionNum }</td>
	</tr>
</table>
		
		
	</table>
		
	
    </logic:present>
  </div>
  <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->
</body>
<html>
	
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
  <c:param name="centerMonitor" value="${centerMonitor}"/> 
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="centerMonitor" value="${centerMonitor}"/> 
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
	<c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="centerMonitor" value="${centerMonitor}"/>
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="roleLeader" value="${roleLeader}"/>
  <c:param name="centerMonitor" value="${centerMonitor}"/>
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
  
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
  <c:param name="centerMonitor" value="${centerMonitor}"/>
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
  <c:param name="undoFlag" value="${undoFlag}"/> 
</c:url>

<c:url var="urlShowCCDealPage" value="commonfault.do">
  <c:param name="method" value="newShowCCPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="teamFlag" value="${teamFlag}"/>
  <c:param name="correlationKey" value="${sheetMain.correlationKey}"/> 
  <c:param name="operateType" value="-10"/> 
  <c:param name="centerMonitor" value="${centerMonitor}"/> 
  <c:param name="undoFlag" value="${undoFlag}"/> 
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
                       <% if("combine".equals(basemain.getMainIfCombine())){ %>
		     <option value="${urlShowCompleteDealPage}" selected="selected"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
<%  }else{ %>
			 		  <!-- 是父任务 -->
			 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
			 	  	<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
					  <!-- 流程步骤，移交，阶段回复，分派 -->
					  	<c:if test="${operateType=='1'||operateType==null||operateType==''}">
					  	 <option value="${urlShowTransmitDealPage}" selected="selected"><bean:message bundle="commonfault" key="operateType.transmit1"/> </option>
					     <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
					  	</c:if>
					  	<c:if test="${operateType=='2'}">
					  	 <option value="${urlShowTransmitDealPage}" ><bean:message bundle="commonfault" key="operateType.transmit1"/> </option>
					     <option value="${urlShowCompleteDealPage}" selected="selected"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
					  	</c:if>
					     <option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option> <!--寤舵湡鐢宠-->
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
                               <%} %>
			<% } %>
	 	</select>
          
	<%}else if(taskName.equals("SecondExcuteHumTask")){ %>	
		<select id="dealSelector" onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
		 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
		      <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
		      	<%if(ifsub.equals("") || ifsub.equals("false")){%>
		      	<c:if test="${roleLeader == 'true'}">
		      		<option value="${urlShowTransferPage}">班组长指定</option>
		      	</c:if>
		      		  <% if (!userId.equals("chenjing13")&&!userId.equals("chenlong1")&&!userId.equals("sunmeng")&&!userId.equals("xuyi3")) { %>
		      <c:if test="${centerMonitor == 'true' && sheetMain.mainT2RejectionNum != '6'}">
		      		<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
		       </c:if>
		            <%}%>
			 <%}} else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
                                   <% if("combine".equals(basemain.getMainIfCombine())){ %>
			 	   <option value="${urlShowCompleteDealPage}" selected="selected"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
			 	<%  }else{ %>
			 	   <!-- 是父任务 -->
			 	  <%if(ifsub.equals("") || ifsub.equals("false")){%>
			 	  	<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
					  <!-- 流程步骤，移交，阶段回复，分派 -->
					    <option value="${urlShowCompleteDealPage}"><bean:message bundle="commonfault" key="operateType.dealComplete"/></option>
					    <c:if test="${centerMonitor != 'true'}">
					    <option value="${urlShowTransmit2DealPage}"><bean:message bundle="commonfault" key="operateType.transmit2"/></option>
						  </c:if>
						  <option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option><!--延期申请-->
						   <c:if test="${sheetMain.mainT2RejectionNum != '6'}">
						  <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
						  </c:if>
						  	<c:choose>
						  		<c:when test="${centerMonitor == 'true'}">
						  		<option value="${urlShowCCDealPage}">抄送</option>
						  		<% if ("admin".equals(userId)|| "itjk".equals(userId) ) {%>
						  			<option value="${urlShowTransferPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
						  		<%}%>
						  	</c:when>
						  	<c:otherwise>
						  		<option value="${urlShowTransferPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
						  		<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>	    
						  		</c:otherwise>
						  	</c:choose>
		                  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>    	                  
			 		<%}else{%>	
			 		     	<c:if test="${needDealReply == 'true'}"> 
			                <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			                <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			             </c:if>
			             <c:if test="${ empty needDealReply && centerMonitor != 'true' }">
			                <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
			             </c:if> 
			         <%} %>
			     <%} else { %>
			 		<!-- 是子任务 -->
			 		<!-- 不需要等待回复 -->
			 	  	<% if (ifwaitfor.equals("false")) {%>
			 			<option value="${urlShowDispatchPage}"><bean:message bundle="commonfault" key="operateType.subTaskReply"/></option>			 		    
			       <c:if test="${centerMonitor != 'true'}">
			          <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>		  
			            </c:if>     
			 		<%}else {%>
		  		<c:if test="${needDealReply == 'true'}">  
			              <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
			              <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
			           </c:if>
			            <c:if test="${empty needDealReply && centerMonitor != 'true'}">
			 			 <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
			 		   </c:if> 
			 		<%} %>
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
						  <option value="${urlShowExamineDealPage}"><bean:message bundle="commonfault" key="operateType.sendToExamine"/></option><!--延期申请-->
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
<c:if test="${qctype=='on'}">
    &nbsp;&nbsp;<input type="button" value="工单质检" onclick="openQcPage()">
</c:if>
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
   function openQcPage(){
      window.location.href="${app}/sheet/commonfault/commonfault.do?method=showQcPage&sheetKey=${sheetMain.id}";
   }
</script>