<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.businesschange.task.impl.BusinessChangeTaskImpl" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="com.boco.eoms.sheet.businesschange.model.BusinessChangeMain"%>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
BusinessChangeTaskImpl task = new BusinessChangeTaskImpl();
String operaterType = "";
if(request.getAttribute("task")!=null){
 task = (BusinessChangeTaskImpl)request.getAttribute("task");
 taskStatus = task.getTaskStatus();
 operaterType = task.getOperateType();
}

request.setAttribute("operaterType",operaterType);
BaseMain basemain = (BaseMain)request.getAttribute("sheetMain");
String sendUserId = basemain.getSendUserId();
BusinessChangeMain businesstype1=(BusinessChangeMain)request.getAttribute("sheetMain");
String busi=businesstype1.getBusinesstype1();
System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQ"+busi);
 request.setAttribute("taskStatus", taskStatus);
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
  String ifInvokeUrgentFault = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvokeUrgentFault"));
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
			url : 'businesschange.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, 
		//{
		//	text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			//url : '/ProcessMonitor/runtime/html/index.jsp?appName=businesschangeProcessApp&templateName=businesschangeMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
		//	url : 'businesschange.do?method=showPic',
		//	isIframe : true
		//},
		{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'businesschange.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="businesschange.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="businesschange.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="businesschange.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>

});
function forceOperation(obj){

	if(obj == 1){
	    var url2="businesschange.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
	    eoms.util.appendPage("sheet-deal-content",url2); 
	}else if(obj == 2){
	     var url2="businesschange.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
	    eoms.util.appendPage("sheet-deal-content",url2); 
	}else{
	     var url2="businesschange.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
	     eoms.util.appendPage("sheet-deal-content",url2);
	 }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="businesschange.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
	<%@ include file="/WEB-INF/pages/wfworksheet/businesschange/basedetailnew.jsp"%>
	<br/>

	<table class="formTable"> 
		  <tr>
		     <td class="label"><bean:message bundle="businesschange" key="businesschange.sheetAcceptLimit"/></td>
			  <td class="content">
			    <bean:write  name="sheetMain"  property="sheetAcceptLimit"  formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/> 
		  			  
			  </td>				
			  <td class="label"><bean:message bundle="businesschange" key="businesschange.sheetCompleteLimit"/></td>
			  <td class="content">
			    <bean:write  name="sheetMain"  property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>  
			  </td>		  
		  </tr>	
	      <tr>
	      	 <td  class="label"><bean:message bundle="businesschange" key="businesschange.businesstype1"/></td>
             <td class="content"><html:hidden name="sheetMain" property="businesstype1"  />  
                 <eoms:id2nameDB id="${sheetMain.businesstype1}" beanId="ItawSystemDictTypeDao"/> </td>
             <td  class="label"><bean:message bundle="businesschange" key="businesschange.urgentDegree"/></td>
             <td class="content"><html:hidden name="sheetMain" property="urgentDegree"  />  
                 <eoms:id2nameDB id="${sheetMain.urgentDegree}" beanId="ItawSystemDictTypeDao"/> </td>  
         </tr>
	     <tr>
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.bdeptContact"/></td>
			<td class="content"> <bean:write  name="sheetMain"  property="bdeptContact"  scope="request"/></td>
         
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.bdeptContactPhone"/></td>
            <td class="content"> <bean:write  name="sheetMain"  property="bdeptContactPhone"  scope="request"/></td>
        </tr>

        <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.customNo"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="customNo"  scope="request"/></td>
         
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.customName"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="customName"  scope="request"/></td>
        </tr>

         <tr>
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.customContact"/></td>
          <td class="content"> <bean:write  name="sheetMain"  property="customContact"  scope="request"/></td>
       
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.customContactPhone"/></td>
          <td class="content"> <bean:write  name="sheetMain"  property="customContactPhone"  scope="request"/></td> 
         </tr>

         
		<tr>
		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.cityName"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="cityName" scope="request"/></td>

		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.countyName"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="countyName" scope="request"/></td>
		</tr>
		<tr>
		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.cmanagerPhone"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="cmanagerPhone" scope="request"/></td>

		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.cmanagerContactPhone"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="cmanagerContactPhone" scope="request"/></td>
		</tr>
		<tr>
		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.customLevel"/></td>
		            <td class="content"><eoms:id2nameDB id="${sheetMain.customLevel}" beanId="ItawSystemDictTypeDao"/></td>	      

		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.productName"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="productName" scope="request"/></td>
		</tr>
		
		<tr>
           
        
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.provinceName"/></td>
		    <td  class="content" colspan='3'><bean:write name="sheetMain" property="provinceName" scope="request"/></td>
	  </tr>
		
<c:if test="${sheetMain.businesstype1==101100101}">

       <tr>
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.factureArea"/></td>
		  <td><eoms:id2nameDB id="${sheetMain.factureArea}" beanId="tawSystemAreaDao"/> </td>  
          <td class="label"><bean:message bundle="businesschange" key="businesschange.APNName"/></td>
		  <td class="content"> <bean:write  name="sheetMain"  property="apNName"  scope="request"/></td> 
        </tr> 
        
        <tr>   
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.ipAddressAssign"/></td>
          <td><eoms:id2nameDB id="${sheetMain.ipAddressAssign}" beanId="ItawSystemDictTypeDao"/> </td>
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.apnRouterMode"/></td>		            
          <td><eoms:id2nameDB id="${sheetMain.apnRouterMode}" beanId="ItawSystemDictTypeDao"/> </td>
        </tr> 
        
        <tr>
         <td  class="label"><bean:message bundle="businesschange" key="businesschange.apnIPPool"/></td>
         <td class="content"> <bean:write  name="sheetMain"  property="apnIPPool"  scope="request"/></td> 
         <td  class="label"><bean:message bundle="businesschange" key="businesschange.transferMode"/></td>		            
         <td><eoms:id2nameDB id="${sheetMain.transferMode}" beanId="ItawSystemDictTypeDao"/> </td>
        </tr>
        
        <tr>
         <td  class="label"><bean:message bundle="businesschange" key="businesschange.AppServerIPAdd"/></td>
         <td class="content"> <bean:write  name="sheetMain"  property="appServerIPAdd"  scope="request"/></td> 
     	 <td  class="label"><bean:message bundle="businesschange" key="businesschange.doubleGGSN"/></td>
         <td><eoms:id2nameDB id="${sheetMain.doubleGGSN}" beanId="ItawSystemDictTypeDao"/> </td>
        </tr>
        <tr> 
         <td  class="label"><bean:message bundle="businesschange" key="businesschange.secondIPPool"/></td>		            
		 <td class="content"> <bean:write  name="sheetMain"  property="secondIPPool"  scope="request"/></td> 
		 <td  class="label"><bean:message bundle="businesschange" key="businesschange.isRadiusValidate"/></td>
		 <td><eoms:id2nameDB id="${sheetMain.isRadiusValidate}" beanId="ItawSystemDictTypeDao"/> </td>
       
        </tr>
        <tr>
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.radiusValidateIPAdd"/></td>
		  <td class="content"> <bean:write  name="sheetMain"  property="radiusValidateIPAdd"  scope="request"/></td> 
		  <td  class="label"><bean:message bundle="businesschange" key="businesschange.simHLR"/></td>
		  <td class="content"> <bean:write  name="sheetMain"  property="simHLR"  scope="request"/></td> 
        </tr>
        <tr>
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.terminalNum"/></td>
		  <td class="content" colspan='3'> <bean:write  name="sheetMain"  property="terminalNum"  scope="request"/></td> 
		</tr>
		</tr>	
		   <tr>
		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.numDetail"/></td>
		            <td  class="content" colspan="3"><bean:write name="sheetMain" property="numDetail" scope="request"/></td>
		   </tr>
	
		 <tr>     
		  <td  class="label"><bean:message bundle="businesschange" key="businesschange.numDetailFile"/></td>
		  <td colspan="3" >
                   <eoms:attachment name="sheetMain" property="numDetailFile" 
        			  scope="request" idField="${sheetPageName}numDetailFile" appCode="businesschange" viewFlag="Y"/> 
          </td>
        </tr>
      	<tr>
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.volumeAssessment"/></td>
           <td class="content" colspan='3'> <bean:write  name="sheetMain"  property="volumeAssessment"  scope="request"/></td> 
	     </tr> 	
</c:if>        
<c:if test="${sheetMain.businesstype1==101100102}">
		 <tr>
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.bCode"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="bcode"  scope="request"/></td> 
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.siconnectMMSGatewayName"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="siconnectMMSGatewayName"  scope="request"/></td> 
		 </tr>
	      <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.SIEnterpriseCode"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="siEnterpriseCode"  scope="request"/></td> 
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.SIServerCode"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="siServerCode"  scope="request"/></td> 
         </tr>
         <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.SIConnectMMSGatewayID"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="siConnectMMSGatewayID"  scope="request"/></td> 
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.SIIPAdd"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="siIPAdd"  scope="request"/></td> 
         </tr>
	     <tr>
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.SIUplinkUrl"/></td>
            <td class="content"> <bean:write  name="sheetMain"  property="siUplinkUrl"  scope="request"/></td> 
          
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.isConnectMISC"/></td>
		   <td  class="content"><eoms:id2nameDB id="${sheetMain.isConnectMISC}" beanId="ItawSystemDictTypeDao"/></td> 
         </tr>
         <tr>
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.factureArea"/></td>
          <td> <eoms:id2nameDB id="${sheetMain.factureArea}" beanId="tawSystemAreaDao"/> </td>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.nameListSetType"/></td>
          <td> <eoms:id2nameDB id="${sheetMain.nameListSetType}" beanId="ItawSystemDictTypeDao"/> </td>
         </tr>
		 <tr>          
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.comProtocol"/></td>
		   <td><eoms:id2nameDB id="${sheetMain.comProtocol}" beanId="ItawSystemDictTypeDao"/> </td>
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.connectGatewayBandwidth"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="connectGatewayBandwidth"  scope="request"/></td> 
         </tr>
	     <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.maxConnections"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="maxConnections"  scope="request"/></td> 
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.maxUnderFlow"/></td>
          <td class="content"> <bean:write  name="sheetMain"  property="maxUnderFlow"  scope="request"/></td> 
         </tr>	
         <tr>
     	  <td  class="label"><bean:message bundle="businesschange" key="businesschange.maxUplinkFlow"/></td>	     
           <td class="content"> <bean:write  name="sheetMain"  property="maxUplinkFlow"  scope="request"/></td> 
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.portRateIsDown"/></td>
          <td><eoms:id2nameDB id="${sheetMain.portRateIsDown}" beanId="ItawSystemDictTypeDao"/> </td>
         </tr>
         <tr>
      	   <td  class="label"><bean:message bundle="businesschange" key="businesschange.flowControlPriority"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="flowControlPriority"  scope="request"/></td> 
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.enterpriseType"/></td>
		   <td ><eoms:id2nameDB id="${sheetMain.enterpriseType}" beanId="ItawSystemDictTypeDao"/></td>	                 
		 </tr>
		             
		 <tr>
		  <td class="label"><bean:message bundle="businesschange" key="businesschange.AppProgramme"/></td>
			 <td colspan="3" >
			 <eoms:attachment name="sheetMain" property="appProgramme" 
              scope="request" idField="${sheetPageName}appProgramme" appCode="businesschangesheet" viewFlag="Y"/> </td>
         </tr>
 </c:if>
<c:if test="${sheetMain.businesstype1==101100103}">
         <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.nameListSetType"/></td>
          <td> <eoms:id2nameDB id="${sheetMain.nameListSetType}" beanId="ItawSystemDictTypeDao"/> </td>
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.SMSSigned"/></td>
          <td class="content"> <bean:write  name="sheetMain"  property="smSSigned"  scope="request"/></td> 
         </tr>
      	 <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.enterpriseCode"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="enterpriseCode"  scope="request"/></td> 
 		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.bCode"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="bcode"  scope="request"/></td> 
         </tr>
         <tr>
     	  <td  class="label"><bean:message bundle="businesschange" key="businesschange.serverCode"/></td>
          <td class="content"> <bean:write  name="sheetMain"  property="serverCode"  scope="request"/></td> 
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.factureArea"/></td>
          <td><eoms:id2nameDB id="${sheetMain.factureArea}" beanId="tawSystemAreaDao"/> </td>
         </tr>
         <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.singleWordsBit"/></td>
		   <td  class="content"><bean:write name="sheetMain" property="singleWordsBit" scope="request"/></td>
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.HostIPAdd"/></td>
		   <td class="content"> <bean:write  name="sheetMain"  property="hostIPAdd"  scope="request"/></td> 
         </tr>
         
         <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.connectGatewayName"/></td>
		   <td  class="content"><bean:write name="sheetMain" property="connectGatewayName" scope="request"/></td>
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.connectGatewayID"/></td>
		   <td  class="content"><bean:write name="sheetMain" property="connectGatewayID" scope="request"/></td>
         </tr>
             
		 <tr>          
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.comProtocol"/></td>
		   <td><eoms:id2nameDB id="${sheetMain.comProtocol}" beanId="ItawSystemDictTypeDao"/> </td>
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.connectGatewayBandwidth"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="connectGatewayBandwidth"  scope="request"/></td> 
         </tr>
	     <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.maxConnections"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="maxConnections"  scope="request"/></td> 
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.maxUnderFlow"/></td>
          <td class="content"> <bean:write  name="sheetMain"  property="maxUnderFlow"  scope="request"/></td> 
         </tr>	
         <tr>
     	  <td  class="label"><bean:message bundle="businesschange" key="businesschange.maxUplinkFlow"/></td>	     
         <td class="content"> <bean:write  name="sheetMain"  property="maxUplinkFlow"  scope="request"/></td> 
          <td  class="label"><bean:message bundle="businesschange" key="businesschange.portRateIsDown"/></td>
          <td> <eoms:id2nameDB id="${sheetMain.portRateIsDown}" beanId="ItawSystemDictTypeDao"/> </td>
         </tr>
		                   
         <tr>
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.authenticationModel"/></td>
		   <td><eoms:id2nameDB id="${sheetMain.authenticationModel}" beanId="ItawSystemDictTypeDao"/></td>	         	
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.flowControlPriority"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="flowControlPriority"  scope="request"/></td> 
         </tr>	
         
         
         <tr>
		             <td  class="label"><bean:message bundle="businesschange" key="businesschange.enterpriseType"/></td>
		                 <td colspan='3'><eoms:id2nameDB id="${sheetMain.enterpriseType}" beanId="ItawSystemDictTypeDao"/></td>	  
		                 
		            </tr>
        
         <tr>
 		  <td class="label"><bean:message bundle="businesschange" key="businesschange.AppProgramme"/></td>
			 <td colspan="3" >
			 <eoms:attachment name="sheetMain" property="sheetAccessories" 
              scope="request" idField="${sheetPageName}sheetAccessories" appCode="businesschangesheet" viewFlag="Y"/> </td>
         </tr>
</c:if>        
<c:if test="${sheetMain.businesstype1==101100104}">
          <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.cityA"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="cityA"  scope="request"/></td> 
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.cityZ"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="cityZ"  scope="request"/></td> 
         </tr>	
         <tr>
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.bandwidth"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="bandwidth"  scope="request"/></td> 
        
           <td  class="label"><bean:message bundle="businesschange" key="businesschange.amount"/></td>
           <td class="content"> <bean:write  name="sheetMain"  property="amount"  scope="request"/></td> 
         </tr>  
	     <tr>
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.portA"/></td>
            <td class="content"> <bean:write  name="sheetMain"  property="portA"  scope="request"/></td> 
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.portAInterfaceType"/></td>
            <td><eoms:id2nameDB id="${sheetMain.portAInterfaceType}" beanId="ItawSystemDictTypeDao"/> </td>
          </tr>
	      <tr>
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.portADetailAdd"/></td>
            <td class="content"> <bean:write  name="sheetMain"  property="portADetailAdd"  scope="request"/></td> 
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.portABDeviceName"/></td>
            <td class="content"> <bean:write  name="sheetMain"  property="portABDeviceName"  scope="request"/></td> 
          </tr>  
	      <tr>
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.portABDevicePort"/></td>
            <td class="content"> <bean:write  name="sheetMain"  property="portABDevicePort"  scope="request"/></td> 
         
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.portAContactPhone"/></td>
            <td class="content"> <bean:write  name="sheetMain"  property="portAContactPhone"  scope="request"/></td> 
          </tr>  
                                          
         <tr>
         	<td  class="label"><bean:message bundle="businesschange" key="businesschange.portZ"/></td>
		    <td class="content"> <bean:write  name="sheetMain"  property="portZ"  scope="request"/></td> 
		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.portZInterfaceType"/></td>
            <td><eoms:id2nameDB id="${sheetMain.portZInterfaceType}" beanId="ItawSystemDictTypeDao"/> </td>
         </tr>	
         <tr>
            <td  class="label"><bean:message bundle="businesschange" key="businesschange.portZBDeviceName"/></td>
		    <td class="content"> <bean:write  name="sheetMain"  property="portZBDeviceName"  scope="request"/></td> 
		    <td  class="label"><bean:message bundle="businesschange" key="businesschange.portZBDevicePort"/></td>
		    <td class="content"> <bean:write  name="sheetMain"  property="portZBDevicePort"  scope="request"/></td> 
		 </tr>
		
		 
		  <tr >
		            <td  class="label"><bean:message bundle="businesschange" key="businesschange.confCRMTicketNo"/></td>
		            <td class="content"><bean:write name="sheetMain" property="confCRMTicketNo" scope="request"/></td>

		             <td  class="label"><bean:message bundle="businesschange" key="businesschange.transfBusiness"/></td>
		                 <td ><eoms:id2nameDB id="${sheetMain.transfBusiness}" beanId="ItawSystemDictTypeDao"/></td>	  
		          </tr>
		           <tr>
		   <td  class="label"><bean:message bundle="businesschange" key="businesschange.portZContactPhone"/></td>
		   <td class="content" colspan='3'> <bean:write  name="sheetMain"  property="portZContactPhone"  scope="request"/></td> 
		 </tr>
</c:if>
 		<tr>          
	         <td  class="label"><bean:message bundle="businesschange" key="businesschange.bRequirementDesc"/></td>
	        <td class="content" colspan='3'> <pre><bean:write  name="sheetMain"  property="brequirementDesc"  scope="request"/></pre></td> 
	    </tr>

</table>
    </logic:present>
  </div>
  <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->
<%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="businesschange.do">
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


<!-- 任务创建审批通过 -->
<c:url var="urlShowTransmitDealPage" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="201"/>  
</c:url>
<!-- 任务创建审批不通过 -->
<c:url var="urlShowTransmit2DealPage" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="202"/>  
</c:url>
<!-- 分派 -->
<c:url var="urlShowSplitDealPage" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="10"/>
</c:url>
<!-- 处理完成无需审批 -->
<c:url var="urlShowCompleteDealPage" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="205"/>
</c:url>
<!-- 执行完成送审 -->
<c:url var="urlShowPassDealPage" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="206"/>
</c:url>
<!-- 任务完成审批通过 -->
<c:url var="urlShowTaskCompleteAuditYes" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="208"/>
</c:url>
<!-- 任务完成审批不通过 -->
<c:url var="urlShowTaskCompleteAuditNo" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="209"/>
</c:url>
<!--处理回复通过 -->
<c:url var="urlShowDealReplyPassPage" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="211"/>
  <c:param name="taskStatus" value="${taskStatus}"/>  
</c:url>
<!--处理回复不通过 -->
<c:url var="urlShowDealReplyNoPassPage" value="businesschange.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="212"/>
  <c:param name="taskStatus" value="${taskStatus}"/>  
</c:url>
<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="businesschange.do">
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
<c:url var="urlShowCompleteDraftPage" value="businesschange.do">
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
<!-- 驳回 -->
<c:url var="urlShowBackDeal" value="businesschange.do">
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
<!-- 移交 -->
<c:url var="urlShowTransferPage" value="businesschange.do">
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

<!-- 移交 -->
<c:url var="urlShowRejectBackPage" value="businesschange.do">
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
<c:url var="urlShowPhaseReplyPage" value="businesschange.do">
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

<!-- 启动网络配置流程 -->
<c:url var="urlShowDispatchDealPage" value="businesschange.do">
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
<div class="sheet-deal">
	<div class='sheet-deal-header'>
	<table>
 <tr>
		    <td width="50%">
		    <%if(!taskName.equals("cc")&&!taskName.equals("reply")&&!taskName.equals("advice")) {%>
		 		<bean:message bundle="sheet" key="sheet.deal"/>:
		 	<%}%>
		 	
		<%if(taskName.equals("TaskCreateAuditHumTask")){ %>	
			<select id="dealSelector"> 
			 <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
			 <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
			 <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
			 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){%>
			 <option value="${urlShowTransmitDealPage}"><bean:message bundle="businesschange" key="businesschange.taskpass"/> </option>
			 <option value="${urlShowTransmit2DealPage}"><bean:message bundle="businesschange" key="businesschange.tasknopass"/></option>
			 <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
             <%}%>
           </select>					
		<%}else if(taskName.equals("ExcuteHumTask")){%>	
	  		  <select id="dealSelector">
			  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
			    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
			 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
			  <option value="${urlShowCompleteDealPage}"><bean:message bundle="businesschange" key="businesschange.dealwcnosp"/></option>
			  <option value="${urlShowPassDealPage}"><bean:message bundle="businesschange" key="businesschange.totaskcompsq"/></option>
			  <option value="${urlShowDispatchDealPage}"><bean:message bundle="sheet" key="common.dispatchType"/></option>
			  <option value="${urlShowSplitDealPage}"><bean:message bundle="businesschange" key="businesschange.fenpai"/></option> 		   
	  		  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
	  		     <%} %>
			</select>
		<%}else if(taskName.equals("TaskCompleteAuditHumTask")){%>	
			<select id="dealSelector">
			<%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
			    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
			 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
			  <option value="${urlShowTaskCompleteAuditYes}"><bean:message bundle="businesschange" key="businesschange.TaskCompleteAuditYes"/></option>
			  <option value="${urlShowTaskCompleteAuditNo}"><bean:message bundle="businesschange" key="businesschange.TaskCompleteAuditNo"/></option>
              <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>			
			<%} %>
			</select>
		<%}else if(taskName.equals("AffirmHumTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowDealReplyPassPage}"><bean:message bundle="businesschange" key="businesschange.dealrejectpass"/></option>
			  <option value="${urlShowDealReplyNoPassPage}"><bean:message bundle="businesschange" key="businesschange.nopassback"/></option>
			  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
			</select>
		<%}else if(taskName.equals("DraftHumTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowCompleteDraftPage}"><bean:message bundle="businesschange" key="businesschange.draft"/></option>
			</select>
		<%}else if(taskName.equals("ByRejectHumTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowBackDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
			</select>
		<%}else if(taskName.equals("HoldHumTask")){ %>
			<select id="dealSelector">
			  <option value="${urlShowHoldDealPage}"><bean:message bundle="businesschange" key="businesschange.hold"/></option>
			</select>	
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
   			url = "businesschange.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
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
 	<div id="buttonDisplay">
    <input type="button" title="${eoms:a2u('执行该操作，工单将进入强制归档状态，其他人不能在处理工单')}" value="<bean:message bundle="sheet" key="button.forceHold"/>" onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
    <input type="button" title="${eoms:a2u('执行该操作，工单将进入强制作废状态，其他人不能在处理工单')}" value="<bean:message bundle="sheet" key="button.forceNullity"/>" onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
    <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
 <% }else if((taskStatus == null || taskStatus.equals(""))&&(userId.equals(sendUserId))){%> 
 <div id="advice" style="display:block">
     <input type="button" title="${eoms:a2u('执行该操作，工单将进入作废状态，其他人不能在处理工单')}" value="<bean:message bundle="sheet" key="button.nullity"/>" onclick="$('advice').style.display='none';forceOperation(2);">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('advice').style.display='none';eventOperation(1);">
  </div>
 <% }%> 
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>
