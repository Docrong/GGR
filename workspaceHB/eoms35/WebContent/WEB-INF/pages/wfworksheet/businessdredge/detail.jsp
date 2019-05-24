<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.businessdredge.model.BusinessDredgeMain" %>
<%@page import="com.boco.eoms.sheet.businessdredge.task.impl.BusinessDredgeTaskImpl" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
BusinessDredgeTaskImpl task = new BusinessDredgeTaskImpl();
String operaterType = "";
if(request.getAttribute("task")!=null){
 task = (BusinessDredgeTaskImpl)request.getAttribute("task");
 taskStatus = task.getTaskStatus();
 operaterType = task.getOperateType();
}

request.setAttribute("operaterType",operaterType);
BusinessDredgeMain basemain = (BusinessDredgeMain)request.getAttribute("sheetMain");
String businesstype1=basemain.getBusinesstype1();
String sendUserId = basemain.getSendUserId();
request.setAttribute("taskStatus", taskStatus);
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
String ifInvokeUrgentFault = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvokeUrgentFault"));
System.out.println("taskname is ================"+taskName+"======operaterType====="+operaterType);
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
			url : 'businessdredge.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, 
		//{
		//	text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			//url : '/ProcessMonitor/runtime/html/index.jsp?appName=resourceaffirmProcessApp&templateName=resourceaffirmMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
		//	url : 'businessdredge.do?method=showPic',
		//	isIframe : true
		//},
		{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'businessdredge.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
	var url2="businessdredge.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
	var url2="businessdredge.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
	var url2="businessdredge.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>

});
function forceOperation(obj){

	if(obj == 1){
	    var url2="businessdredge.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
	    eoms.util.appendPage("sheet-deal-content",url2); 
	}else if(obj == 2){
	     var url2="businessdredge.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
	    eoms.util.appendPage("sheet-deal-content",url2); 
	}else{
	     var url2="businessdredge.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
	     eoms.util.appendPage("sheet-deal-content",url2);
	 }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="businessdredge.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
	<%@ include file="/WEB-INF/pages/wfworksheet/businessdredge/basedetailnew.jsp"%>
	<br/>
<table class="formTable">
     <tr>
	     <td class="label"><bean:message bundle="businessdredge" key="businessdredge.sheetacceptlimit"/></td>
	     <td class="content"><bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/></td>
         <td class="label"><bean:message bundle="businessdredge" key="businessdredge.sheetcompletelimit"/></td>
         <td class="content"><bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/></td>
      </tr>
	  <tr>
	      <td class="label"><bean:message bundle="businessdredge" key="businessdredge.businesstype1"/> </td>
           <input type="hidden" id="businesstype1" value="<%=businesstype1%>">
          <td class="content"><eoms:id2nameDB id="${sheetMain.businesstype1}" beanId="ItawSystemDictTypeDao"/></td>
          <td class="label"><bean:message bundle="businessdredge" key="businessdredge.mainRemark"/></td>
	      <td class="content"><eoms:id2nameDB id="${sheetMain.urgentDegree}" beanId="ItawSystemDictTypeDao"/></td>		                
      </tr>

	  <tr>
		  <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.bdeptContact"/></td>
		  <td  class="content"><bean:write name="sheetMain" property="bdeptContact" scope="request"/></td>	                
		  <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.bdeptContactPhone"/></td>
		  <td  class="content"><bean:write name="sheetMain" property="bdeptContactPhone" scope="request"/></td>
		  
	  </tr>

	  <tr>
		   <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.customNo"/></td>
		   <td  class="content"><bean:write name="sheetMain" property="customNo" scope="request"/></td>
	       <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.customName"/></td>
		   <td  class="content"><bean:write name="sheetMain" property="customName" scope="request"/></td>
	  </tr>
	  <tr>
		   <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.customContact"/></td>
		   <td  class="content"><bean:write name="sheetMain" property="customContact" scope="request"/></td>
           <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.customContactPhone"/></td>
		   <td  class="content"><bean:write name="sheetMain" property="customContactPhone" scope="request"/></td>
	  </tr>

	  

		<tr>
		    <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.cityName"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="cityName" scope="request"/></td>

		    <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.countyName"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="countyName" scope="request"/></td>
		</tr>
		<tr>
		    <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.cmanagerPhone"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="cmanagerPhone" scope="request"/></td>

		    <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.cmanagerContactPhone"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="cmanagerContactPhone" scope="request"/></td>
		</tr>
		<tr>
		    <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.customLevel"/></td>
		            <td class="content"><eoms:id2nameDB id="${sheetMain.customLevel}" beanId="ItawSystemDictTypeDao"/></td>	      

		    <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.productName"/></td>
		    <td  class="content"><bean:write name="sheetMain" property="productName" scope="request"/></td>
		</tr>
		
		 <tr>
		   

		    <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.provinceName"/></td>
		    <td  class="content" colspan='3'><bean:write name="sheetMain" property="provinceName" scope="request"/></td>
	  </tr>

	   

		 
		 <!-- GPRS -->
		 <% if("101100101".equals(businesstype1)){ %>
         		     
		<tr>
            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.factureArea"/></td>
		    <td  class="content"><eoms:id2nameDB id="${sheetMain.factureArea}" beanId="ItawSystemDictTypeDao"/></td>	               
            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.AppServerIPAdd"/></td>
	        <td ><bean:write name="sheetMain" property="appServerIPAdd" scope="request"/></td>
		 </tr> 		     
	       <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.isRadiusValidate"/></td>
		            <td class="content"><eoms:id2nameDB id="${sheetMain.isRadiusValidate}" beanId="ItawSystemDictTypeDao"/></td>	                
		            
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.radiusValidateIPAdd"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="radiusValidateIPAdd" scope="request"/></td>
		   </tr>	
		   <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.ipAddressAssign"/></td>
		            <td class="content"><eoms:id2nameDB id="${sheetMain.ipAddressAssign}" beanId="ItawSystemDictTypeDao"/></td>	                
		            
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.apnRouterMode"/></td>
		            <td  class="content"><eoms:id2nameDB id="${sheetMain.apnRouterMode}" beanId="ItawSystemDictTypeDao"/></td>
		   </tr>	
		   <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.apnIPPool"/></td>
		            <td class="content"><bean:write name="sheetMain" property="apnIPPool" scope="request"/></td>	                
		            
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.transferMode"/></td>
		            <td  class="content"><eoms:id2nameDB id="${sheetMain.transferMode}" beanId="ItawSystemDictTypeDao"/></td>
		   </tr>	
		   <tr>
		           <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.doubleGGSN"/></td>
		            <td  class="content"><eoms:id2nameDB id="${sheetMain.doubleGGSN}" beanId="ItawSystemDictTypeDao"/></td>	                
		            
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.secondIPPool"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="secondIPPool" scope="request"/></td>
		   </tr>
		    <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.simHLR"/></td>
		            <td class="content"><bean:write name="sheetMain" property="simHLR" scope="request"/></td>	                
		            
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.APNName"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="apnName" scope="request"/></td>
		   </tr>	
		   <tr>
		    <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.terminalNum"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="terminalNum" scope="request"/></td>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.numDetail"/></td>
		            <td  class="content" ><bean:write name="sheetMain" property="numDetail" scope="request"/></td>
		   </tr>
	
		 <tr>     
		  <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.numDetailFile"/></td>
		  <td colspan="3" >
                   <eoms:attachment name="sheetMain" property="numDetailFile" 
        			  scope="request" idField="${sheetPageName}numDetailFile" appCode="businessdredge" viewFlag="Y"/> 
          </td>
        </tr>			     
			      <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.volumeAssessment"/></td>
		            <td  class="content" colspan="3"><pre><bean:write name="sheetMain" property="volumeAssessment" scope="request"/></pre></td>
		          </tr>
		        		<%} %>
       <!-- MMS -->	 <% if("101100102".equals(businesstype1)){ %>
					<tr>
			            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.factureArea"/></td>
					    <td  class="content"><eoms:id2nameDB id="${sheetMain.factureArea}" beanId="ItawSystemDictTypeDao"/></td>	  
					     <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.nameListSetType"/></td>
		                <td class="content"><eoms:id2nameDB id="${sheetMain.nameListSetType}" beanId="ItawSystemDictTypeDao"/></td>	              
					 </tr>	
			      <tr>
			        <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.bCode"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="bcode" scope="request"/></td>

		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.isConnectMISC"/></td>
		            <td  class="content"><eoms:id2nameDB id="${sheetMain.isConnectMISC}" beanId="ItawSystemDictTypeDao"/></td>	
		           </tr> 
		           
		           
		           <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.siconnectMMSGatewayName"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="siconnectMMSGatewayName" scope="request"/></td>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.SIEnterpriseCode"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="siEnterpriseCode" scope="request"/></td>
		          
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.SIServerCode"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="siServerCode" scope="request"/></td>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.SIConnectMMSGatewayID"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="siConnectMMSGatewayID" scope="request"/></td>
		          
		          </tr>
		          
		          <tr>
		             <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.SIIPAdd"/></td>
		             <td  class="content"><bean:write name="sheetMain" property="siIPAdd" scope="request"/></td>
		             <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.SIUplinkUrl"/></td>
		             <td  class="content"><bean:write name="sheetMain" property="siUplinkUrl" scope="request"/></td>
		          
		          </tr>
		          
		          <tr>
		             <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.comProtocol"/></td>
		             <td class="content"><eoms:id2nameDB id="${sheetMain.comProtocol}" beanId="ItawSystemDictTypeDao"/></td>
			         <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.connectGatewayBandwidth"/></td>
		             <td class="content"><bean:write name="sheetMain" property="connectGatewayBandwidth" scope="request"/></td>   
		          </tr>
		          
		          <tr>
		               <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.maxConnections"/></td>
		                <td class="content"><bean:write name="sheetMain" property="maxConnections" scope="request"/></td> 
			            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.maxUnderFlow"/></td>
		                <td class="content"><bean:write name="sheetMain" property="maxUnderFlow" scope="request"/></td>	
		            </tr>
		         
		         
	                <tr>
		                <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.maxUplinkFlow"/></td>
		                <td class="content"><bean:write name="sheetMain" property="maxUplinkFlow" scope="request"/></td>
			            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.flowControlPriority"/></td>
		               <td  class="content"><bean:write name="sheetMain" property="flowControlPriority" scope="request"/></td>
		          	                
		            </tr>
		            
		           	 <tr>
		                 <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portRateIsDown"/></td>
		                 <td ><eoms:id2nameDB id="${sheetMain.portRateIsDown}" beanId="ItawSystemDictTypeDao"/></td>	
		                 <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.enterpriseType"/></td>
		                 <td ><eoms:id2nameDB id="${sheetMain.enterpriseType}" beanId="ItawSystemDictTypeDao"/></td>	                 
		            </tr>
		             
		    		 <tr>
					  <td class="label"><bean:message bundle="businessdredge" key="businessdredge.AppProgramme"/></td>
						 <td colspan="3" >
						 <eoms:attachment name="sheetMain" property="appProgramme" 
			              scope="request" idField="${sheetPageName}appProgramme" appCode="businessdredge" viewFlag="Y"/> </td>
			         </tr>        
		            
		         <!--SMS  -->   		<%} %>
		         
		         	 <% if("101100103".equals(businesstype1)){ %>

	                        <tr>
		                <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.nameListSetType"/></td>
		                <td class="content"><eoms:id2nameDB id="${sheetMain.nameListSetType}" beanId="ItawSystemDictTypeDao"/></td>	
			            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.SMSSigned"/></td>
		                <td class="content"><bean:write name="sheetMain" property="smsSigned" scope="request"/></td>                
		            </tr>
		            
		            <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.enterpriseType"/></td>
		                 <td ><eoms:id2nameDB id="${sheetMain.enterpriseType}" beanId="ItawSystemDictTypeDao"/></td>	  
		                   <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.enterpriseCode"/></td>
		               <td class="content"><bean:write name="sheetMain" property="enterpriseCode" scope="request"/></td>
		            </tr>
		            
		            <tr>
		             <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.serverCode"/></td>
		                <td  class="content"><bean:write name="sheetMain" property="serverCode" scope="request"/></td>
		                <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.bCode"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="bcode" scope="request"/></td>    
		            </tr>
		            
		            <tr>
		             <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.HostIPAdd"/></td>
		               <td  class="content"><bean:write name="sheetMain" property="hostIPAdd" scope="request"/></td>
		               <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.connectGatewayName"/></td>
		                <td class="content"><bean:write name="sheetMain" property="connectGatewayName" scope="request"/></td>
		            </tr>
		            
		            <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.connectGatewayID"/></td>
		                <td class="content"><bean:write name="sheetMain" property="connectGatewayID" scope="request"/></td>
		                 <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.factureArea"/></td>
				    <td  class="content"><eoms:id2nameDB id="${sheetMain.factureArea}" beanId="ItawSystemDictTypeDao"/></td>
		            </tr>
		            
		            
		            
		            <tr>
		             <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.comProtocol"/></td>
		             <td class="content"><eoms:id2nameDB id="${sheetMain.comProtocol}" beanId="ItawSystemDictTypeDao"/></td>
			         <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.connectGatewayBandwidth"/></td>
		                <td class="content"><bean:write name="sheetMain" property="connectGatewayBandwidth" scope="request"/></td>	   
		          </tr>
		         
		         <tr>
		               <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.maxConnections"/></td>
		                <td class="content"><bean:write name="sheetMain" property="maxConnections" scope="request"/></td> 
			            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.maxUnderFlow"/></td>
		                <td class="content"><bean:write name="sheetMain" property="maxUnderFlow" scope="request"/></td>	
		            </tr>
		            
		            
		            <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.maxUplinkFlow"/></td>
		                <td class="content"><bean:write name="sheetMain" property="maxUplinkFlow" scope="request"/></td>
		                <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portRateIsDown"/></td>
		                 <td ><eoms:id2nameDB id="${sheetMain.portRateIsDown}" beanId="ItawSystemDictTypeDao"/></td>	       
		            </tr>
		            
		            
		            <tr>
		                   <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.authenticationModel"/></td>
		                 <td><eoms:id2nameDB id="${sheetMain.authenticationModel}" beanId="ItawSystemDictTypeDao"/></td>	
		                 <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.singleWordsBit"/></td>
		               <td  class="content"><bean:write name="sheetMain" property="singleWordsBit" scope="request"/></td>		           	 	
		                          
		            </tr>
		            
		            <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.flowControlPriority"/></td>
		               <td  class="content"><bean:write name="sheetMain" property="flowControlPriority" scope="request"/></td>
		               <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.betweenNetwork"/></td>
		              <td  class="content"><eoms:id2nameDB id="${sheetMain.betweenNetwork}" beanId="ItawSystemDictTypeDao"/></td> 
		            </tr>
		            
		            
		            <tr>
					  <td class="label"><bean:message bundle="businessdredge" key="businessdredge.AppProgramme"/></td>
						 <td colspan="3" >
						 <eoms:attachment name="sheetMain" property="appProgramme" 
			              scope="request" idField="${sheetPageName}appProgramme" appCode="businessdredge" viewFlag="Y"/> </td>
			         </tr> 	            
		           		<%} %>
		            
		            <!-- chuanshu -->
		            	 <% if("101100104".equals(businesstype1)){ %>
		            
		           <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.bandwidth"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="bandwidth" scope="request"/></td>
		          
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.amount"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="amount" scope="request"/></td>
		          </tr>
		            
	
			      <tr >
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.cityA"/></td>
		            <td class="content"><bean:write name="sheetMain" property="cityA" scope="request"/></td>

		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.cityZ"/></td>
		            <td class="content"><bean:write name="sheetMain" property="cityZ" scope="request"/></td>
		          </tr>


			       <tr >
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portA"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="portA" scope="request"/></td>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portZ"/></td>
		            <td  class="content"><bean:write name="sheetMain" property="portZ" scope="request"/></td>
		          </tr>

			      <tr >
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portAInterfaceType"/></td>
		            <td class="content"><eoms:id2nameDB id="${sheetMain.portAInterfaceType}" beanId="ItawSystemDictTypeDao"/></td>

		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portADetailAdd"/></td>
		            <td class="content"><bean:write name="sheetMain" property="portADetailAdd" scope="request"/></td>
		          </tr>

			      <tr >
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portABDeviceName"/></td>
		            <td class="content"><bean:write name="sheetMain" property="portABDeviceName" scope="request"/></td>

		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portABDevicePort"/></td>
		            <td class="content"><bean:write name="sheetMain" property="portABDevicePort" scope="request"/></td>
		          </tr>

			      

			      <tr>
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portZInterfaceType"/></td>
		            <td class="content"><eoms:id2nameDB id="${sheetMain.portZInterfaceType}" beanId="ItawSystemDictTypeDao"/></td>

		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portZBDeviceName"/></td>
		            <td class="content"><bean:write name="sheetMain" property="portZBDeviceName" scope="request"/></td>
		          </tr>

			      <tr >
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portZBDevicePort"/></td>
		            <td class="content"><bean:write name="sheetMain" property="portZBDevicePort" scope="request"/></td>

		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portZContactPhone"/></td>
		            <td class="content"><bean:write name="sheetMain" property="portZContactPhone" scope="request"/></td>
		          </tr>
		           <tr >
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.confCRMTicketNo"/></td>
		            <td class="content"><bean:write name="sheetMain" property="confCRMTicketNo" scope="request"/></td>

		             <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.transfBusiness"/></td>
		                 <td ><eoms:id2nameDB id="${sheetMain.transfBusiness}" beanId="ItawSystemDictTypeDao"/></td>	  
		          </tr>
		          <tr >
		            <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.portAContactPhone"/></td>
		            <td class="content" colspan='3' ><bean:write name="sheetMain" property="portAContactPhone" scope="request"/></td>
		          </tr>
 		      		<%} %>
		                      
	        <tr>
	               
		           <td  class="label"><bean:message bundle="businessdredge" key="businessdredge.bRequirementDesc"/></td>
		           <td  class="content" colspan="3"><pre><bean:write name="sheetMain" property="brequirementDesc" scope="request"/></pre></td>
		     </tr> 
		       
		    
   </table>
	
    </logic:present>
  </div>
  <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->

<%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>

<!-- ???? -->
<c:url var="urlShowNewAuditPassPage" value="businessdredge.do">
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
<!-- ????? -->
<c:url var="urlShowNewAuditUnpassPage" value="businessdredge.do">
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
<!-- ???????? -->
<c:url var="urlShowCompleteDealPage" value="businessdredge.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- ?? -->
<c:url var="urlShowCompleteDraftPage" value="businessdredge.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="22"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- ?????? -->
<c:url var="urlShowPassDealPage" value="businessdredge.do">
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
<!-- ?? -->
<c:url var="urlShowHoldDealPage" value="businessdredge.do">
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
<!-- ?? -->
<c:url var="urlShowTransmitDealPage" value="businessdredge.do">
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
<!-- ?? -->
<c:url var="urlShowExamineYesDealPage" value="businessdredge.do">
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
<!-- ???????? -->
<c:url var="urlShowTaskCompleteAuditYes" value="businessdredge.do">
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
<!-- ????????? -->
<c:url var="urlShowTaskCompleteAuditNo" value="businessdredge.do">
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

<c:url var="urlShowTransferkPage" value="businessdredge.do">
  <c:param name="method" value="showTransferWorkItemPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<!--????????? -->
<c:url var="urlShowExamineDealPage" value="businessdredge.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
  <c:param name="taskStatus" value="${taskStatus}"/>  
</c:url>
<!--?????? -->
<c:url var="urlShowExamineNODealPage" value="businessdredge.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="6"/>
  <c:param name="taskStatus" value="${taskStatus}"/>  
</c:url>
<!--???? -->
<c:url var="urlShowPhaseReplyPage" value="businessdredge.do">
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
<c:url var="urlShowInputSplit1" value="businessdredge.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="firstSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<c:url var="urlShowInputSplit2" value="businessdredge.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="secondSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<c:url var="urlShowInputSplit3" value="businessdredge.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="thirdSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<c:url var="urlShowInputSplit4" value="businessdredge.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="fourSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>
<!-- ?? -->
<c:url var="urlShowRejectBackPage" value="businessdredge.do">
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
<!-- ???? -->
<c:url var="urlShowAcceptDealPage" value="businessdredge.do">
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
<!-- ???????????? -->
<c:url var="urlShowDispatchDealPage" value="businessdredge.do">
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
  <!-- ??? -->
<c:url var="urlShowRejectPage" value="businessdredge.do">
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
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>
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
			 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
			 <option value="${urlShowNewAuditPassPage}"><bean:message bundle="businessdredge" key="businessdredge.taskpass"/> </option>
			 <option value="${urlShowNewAuditUnpassPage}"><bean:message bundle="businessdredge" key="businessdredge.tasknopass"/></option>
             <%} %>
           </select>
		<%}else if(taskName.equals("TaskCompleteAuditHumTask")){%>	
			<select id="dealSelector">
			  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
			    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
			 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
			  <option value="${urlShowTaskCompleteAuditYes}"><bean:message bundle="businessdredge" key="businessdredge.TaskCompleteAuditYes"/></option>
			  <option value="${urlShowTaskCompleteAuditNo}"><bean:message bundle="businessdredge" key="businessdredge.TaskCompleteAuditNo"/></option>
			 <%} %>
			</select>			
		<%}else if(taskName.equals("ExcuteHumTask")){%>	
			<select id="dealSelector">
		    <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
			    <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
			    <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
			 <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
			  <option value="${urlShowCompleteDealPage}"><bean:message bundle="businessdredge" key="businessdredge.dealwcnosp"/></option>
			  <option value="${urlShowTransmitDealPage}"><bean:message bundle="sheet" key="common.split"/></option>
			  <option value="${urlShowDispatchDealPage}"><bean:message bundle="sheet" key="common.dispatchType"/></option>
			  <option value="${urlShowPassDealPage}"><bean:message bundle="businessdredge" key="businessdredge.totaskcompsq"/></option>
			  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>	   
			 <%} %>
			</select>
		<%}else if(taskName.equals("AffirmHumTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowExamineNODealPage}"><bean:message bundle="businessdredge" key="businessdredge.dealrejectpass"/></option>
			  <option value="${urlShowExamineDealPage}"><bean:message bundle="businessdredge" key="businessdredge.nopassback"/></option>
			</select>
		<%}else if(taskName.equals("DraftHumTask")){%>	
			<select id="dealSelector">
			  <option value="${urlShowCompleteDraftPage}"><bean:message bundle="businessdredge" key="businessdredge.draft"/></option>
			</select>
		<%}else if(taskName.equals("HoldHumTask")){ %>
			<select id="dealSelector">
			  <option value="${urlShowHoldDealPage}"><bean:message bundle="businessdredge" key="businessdredge.Hold"/></option>
			</select>	
		<%}else if(taskName.equals("ByRejectHumTask")){ %>
		   <select id="dealSelector">
			  <option value="${urlShowRejectPage}"><bean:message bundle="sheet" key="common.reSend"/></option>
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
   			url = "businessdredge.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
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
    <input type="button" title="${eoms:a2u('??????????????????????????????????????????')}" value="<bean:message bundle="sheet" key="button.forceHold"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
    <input type="button" title="${eoms:a2u('??????????????????????????????????????????')}" value="<bean:message bundle="sheet" key="button.forceNullity"/>" onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
    <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
 <% }else if((taskStatus == null || taskStatus.equals(""))&&(userId.equals(sendUserId))){%> 
 <div id="advice" style="display:block">
     <input type="button" title="${eoms:a2u('???????????????????????????????????????')}" value="<bean:message bundle="sheet" key="button.nullity"/>" onclick="$('advice').style.display='none';forceOperation(2);">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('advice').style.display='none';eventOperation(1);">
  </div>
 <% }%> 
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>
