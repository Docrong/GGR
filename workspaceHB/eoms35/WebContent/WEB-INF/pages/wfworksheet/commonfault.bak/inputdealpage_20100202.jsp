<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
 <%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
 <%@page import="com.boco.eoms.base.util.StaticMethod" %>
 <%@page import="com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask" %>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 String ifsub = "";
 if(request.getAttribute("task")!=null){
	 CommonFaultTask task = (CommonFaultTask)request.getAttribute("task");
	 ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
}
 %> 

 <script type="text/javascript">
 	var frm = document.forms[0];
	var count=0;
	var fm = eoms.form;

 	Ext.onReady(function(){
 		//alert(<%=operateType%>);
 		if('<%=operateType%>' == '19'){
 			eoms.util.appendPage("idSpecia2","${app}/sheet/urgentfault/urgentfault.do?method=showNewInputSheetPage&sheetPageName=urgent&parentCorrelation=${sheetMain.correlationKey}&invokerObject=CommonFault&invokerId=${sheetMain.id}");
 			fm.enableArea('idSpecia2');
 		}else{
 			fm.disableArea('idSpecia2',true);
 		}
 	});
 </script>
  <script type="text/javascript">
function popupKnowledge()
{

	Ajax.Request(
	  "${app}/sheet/commonfault/commonfault.do?method=createKnowledge",
	  {
  		method:"GET",
  		parameters:"&sheetKey=${sheetMain.id}",
  		onComplete: handleCallBack
	  }
  	);
	var height = window.screen.height/6;
    var width = window.screen.width/4;

    features = "dialogWidth:"+1024+"px;dialogHeight:"+768+"px; scroll:2; help:0; status:No; fullscreen;";
    features += "dialogLeft:" + width + ";dialogTop:" + height;
}

function popupLeaveKnowledge()
{

	Ajax.Request(
	  "${app}/sheet/commonfault/commonfault.do?method=createLeaveKnowledge",
	  {
  		method:"GET",
  		parameters:"&sheetKey=${sheetMain.id}",
  		onComplete: handleCallBack
	  }
  	);
	var height = window.screen.height/6;
    var width = window.screen.width/4;

    features = "dialogWidth:"+1024+"px;dialogHeight:"+768+"px; scroll:2; help:0; status:No; fullscreen;";
    features += "dialogLeft:" + width + ";dialogTop:" + height;
}
function handleCallBack(originalRequest) //回调函数，对服务端的响应处理，监视response状态

{
	var url=originalRequest.responseText;
	window.open(url);
}

function changRequired(selectedValue) {
		var obj1 = document.getElementById("norequiredtd");
		var obj3 = document.getElementById("${sheetPageName}linkOperRenewTime");
		var obj2 = document.getElementById("requiredtd");
	if (selectedValue == "101030603") {
		obj1.style.display = "inline";
		obj2.style.display = "none";
		obj3.setAttribute("alt","allowBlank:true"); 
	} else {
		obj2.style.display = "inline";
		obj1.style.display = "none";
		obj3.setAttribute("alt","allowBlank:false");
	}
}

</script>
<%if( operateType.equals("19")){%>

   <%@include file="/WEB-INF/pages/wfworksheet/commonfault/hiddendealtitle.jsp"%>

 <%}else{%> 
 
    <%@ include file="/WEB-INF/pages/wfworksheet/commonfault/baseinputlinkhtmlnew.jsp" %>

<%}%>
	<br/>      <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="CommonFaultMainFlowProcess" />
               <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate" />
               <input type="hidden" name="${sheetPageName}beanId" value="iCommonFaultMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.commonfault.model.CommonFaultMain"/>	<!--main表Model对象类名-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.commonfault.model.CommonFaultLink"/> <!--link表Model对象类名-->
               <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->
        <input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
        <input type="hidden" name="${sheetPageName}mainNetSortOne" value="${sheetMain.mainNetSortOne}"/>
        <input type="hidden" name="${sheetPageName}mainNetSortTwo" value="${sheetMain.mainNetSortTwo}"/>
        <input type="hidden" name="${sheetPageName}mainNetSortThree" value="${sheetMain.mainNetSortThree}"/>
        <input type="hidden" name="${sheetPageName}mainEquipmentFactory" value="${sheetMain.mainEquipmentFactory}"/>
	<c:if test="${taskName != 'HoldHumTask' }">
	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
	</c:if>        
     <table class="formTable">
     
     <%if(taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
            taskName.equals("ThirdExcuteHumTask") || taskName.equals("firstSubTask") || 
            taskName.equals("secondSubTask") || taskName.equals("thirdSubTask") ) {%>
		

		<%if(operateType.equals("1")){ %>
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="1" />
		
			<%if(taskName.equals("FirstExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask" />
			<%} %>

			
		<tr>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfMutualCommunication"/></td>
		  <td class="content">
		  	<eoms:comboBox name="${sheetPageName}linkIfMutualCommunication" id="${sheetPageName}linkIfMutualCommunication" initDicId="10301" defaultValue="${sheetLink.linkIfMutualCommunication}"/>
		  </td>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfSafe"/></td>
		  <td class="content">
		  	<eoms:comboBox name="${sheetPageName}linkIfSafe" id="${sheetPageName}linkIfSafe" initDicId="10301" defaultValue="${sheetLink.linkIfSafe}"/>
		  </td>			  
		</tr>

		
		<tr>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultFirstDealDesc"/>*</td>
		  <td colspan="3">
			 <textarea name="${sheetPageName}linkFaultFirstDealDesc" id="${sheetPageName}linkFaultFirstDealDesc" 
			    class="textarea max" alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" >${sheetLink.linkFaultFirstDealDesc}</textarea>	  
		  </td>
		</tr>		

		<tr>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDesc"/></td>
		  <td colspan="3">
		   	<textarea name="linkFaultDesc" id="linkFaultDesc" class="textarea max" 
		   	alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入1000汉字'" >${sheetLink.linkFaultDesc}</textarea>	  
		  </td>
		</tr>
				
		<%} %>


		<%if(operateType.equals("2")){ %>
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="2" />
			
			<%if(taskName.equals("SecondExcuteHumTask")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask" />
			<%} %>			
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDealInfo"/>*</td>
			  <td colspan="3">
		  		<textarea name="${sheetPageName}linkFaultDealInfo" id="${sheetPageName}linkFaultDealInfo" 
		  		  class="textarea max" alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入100汉字'"  >${sheetLink.linkFaultDealInfo}</textarea>			   
			  </td>
			</tr>
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkTransmitReason"/>*</td>
			  <td colspan="3">
		  		<textarea name="${sheetPageName}linkTransmitReason" id="${sheetPageName}linkTransmitReason" 
		  		  class="textarea max"  alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'" >${sheetLink.linkTransmitReason}</textarea>			   
			  </td>
			</tr>		
		
		<%} %>
		 
		<%if(operateType.equals("4")){ %>
		
			<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
			<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
			<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		    <%if(ifsub.equals("true")) {%>
               <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="invokeProcess" /> 
            <% } %>			
		  	<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BackExcuteTask" />
				</c:when>
			  <c:when test="${'DraftHumTask' == fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BackExcuteTask" />
				</c:when>				
			  <c:when test="${'BackHumTask' == fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BackExcuteTask" />
				</c:when>				
			  	<c:when test="${'FirstExcuteHumTask' == fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask" />
				</c:when>
			  	<c:when test="${'SecondExcuteHumTask' == fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask" />
				</c:when>
				<c:when test="${'HoldHumTask' == fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
				</c:when>
			</c:choose>							
	    	<tr>
		       <td class="label">
		        <bean:message bundle="sheet" key="linkForm.remark" />*
			    </td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
			        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.remark}</textarea>
			  </td>
			</tr>  	
		
		<%} %>
		
       <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />	
		<!-- 						
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		 -->
		<%} %>
        <%if(operateType.equals("46") || operateType.equals("11") ){ %>
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
	             <%if(taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
	                  taskName.equals("ThirdExcuteHumTask")){ %>
	                <%if (operateType.equals("46")){ %>
	                  <tr>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDealResult"/>*</td>
				  <td class="content" >
				   <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="46" />
					<eoms:comboBox name="${sheetPageName}linkFaultDealResult" 
					   id="${sheetPageName}linkFaultDealResult" initDicId="1010306" 
					   defaultValue="${sheetLink.linkFaultDealResult}" alt="allowBlank:false" onchange="changRequired(this.value)" />		   
				  </td>	
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/></td>
			      <td class="content" >
			  	    <eoms:comboBox name="${sheetPageName}linkIfGreatFault" 
			  	      id="${sheetPageName}linkIfGreatFault" initDicId="10301" defaultValue="${sheetLink.linkIfGreatFault}"/>
			  </td>			  
				</tr>
	                <%} else if (operateType.equals("11")){ %>
	                  <tr>
	              <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/></td>
			      <td class="content" colspan='3'>
			        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="11" />
			  	    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="splitReply" />			  	    
			  	    <eoms:comboBox name="${sheetPageName}linkIfGreatFault" 
			  	      id="${sheetPageName}linkIfGreatFault" initDicId="10301" defaultValue="${sheetLink.linkIfGreatFault}"/>
			  </td>	
	            </tr>
	                <%} %>                 
				
	          <%} %>
	           
				<tr>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>*</td>
				  <td class="content">
				   
				      <eoms:comboBox name="${sheetPageName}linkFaultReasonSort" 
				         id="${sheetPageName}linkFaultReasonSort" initDicId="1010303"  sub="${sheetPageName}linkFaultReasonSubsection"
				         defaultValue="${sheetLink.linkFaultReasonSort}"  alt="allowBlank:false"/>
				  </td>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>*</td>
				  <td class="content">
				      <eoms:comboBox name="${sheetPageName}linkFaultReasonSubsection" 
				         id="${sheetPageName}linkFaultReasonSubsection"   initDicId="${sheetLink.linkFaultReasonSort}"
				         defaultValue="${sheetLink.linkFaultReasonSubsection}"  alt="allowBlank:false"/>
				  </td>
				</tr>
	
				<tr>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkDealStep"/>*</td>
				  <td colspan="3">
			  		<textarea name="linkDealStep" id="linkDealStep" class="textarea max" 
					alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'">${sheetLink.linkDealStep}</textarea>			   
				  </td>
				</tr>
	         <%if((taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
	                taskName.equals("ThirdExcuteHumTask"))&& operateType.equals("46")){ %>
	                
				<tr>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfExcuteNetChange"/>*</td>
				  <td >
				  	<eoms:comboBox name="${sheetPageName}linkIfExcuteNetChange" 
				  	  id="${sheetPageName}linkIfExcuteNetChange" initDicId="10301"
				  	   defaultValue="${sheetLink.linkIfExcuteNetChange}" alt="allowBlank:false"/>	   
				  </td>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfFinallySolveProject"/>*</td>
				  <td >
				  	   <eoms:comboBox name="${sheetPageName}linkIfFinallySolveProject" 
				  	     id="${sheetPageName}linkIfFinallySolveProject" initDicId="10301"
				  	       defaultValue="${sheetLink.linkIfFinallySolveProject}" alt="allowBlank:false"/>	   
				  </td>
				</tr>
				
				<tr>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfAddCaseDataBase"/>*</td>
				  <td colspan="3">
				  	   <eoms:comboBox name="${sheetPageName}linkIfAddCaseDataBase"
				  	      id="${sheetPageName}linkIfAddCaseDataBase" initDicId="10301" 
				  	      defaultValue="${sheetLink.linkIfAddCaseDataBase}" alt="allowBlank:false"/>	   
				  </td>
				</tr>
		     <%} %>
				<tr>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>*</td>
				  <td>
				    <input type="text" class="text" name="${sheetPageName}linkFaultAvoidTime" readonly="readonly" 
						id="${sheetPageName}linkFaultAvoidTime"  
						onclick="popUpCalendar(this, this, null, null, null, true, -1)"  value="${eoms:date2String(sheetLink.linkFaultAvoidTime)}" alt="vtype:'moreThen',link:'${sheetPageName}mainFaultGenerantTime',vtext:'故障消除时间晚于故障发生时间',allowBlank:false"/> 
						<input type="hidden" name="${sheetPageName}mainFaultGenerantTime" id="${sheetPageName}mainFaultGenerantTime" value="${sheetMain.mainFaultGenerantTime}" />
				  </td>
				  <td id="norequiredtd" class="label"><bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/></td>
				  <td id="requiredtd" class="label" style="display:none"><bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/>*</td>
				  <td id="ifrequired">
				    <input type="text" class="text" name="${sheetPageName}linkOperRenewTime" readonly="readonly" 
						id="${sheetPageName}linkOperRenewTime" 
						onclick="popUpCalendar(this, this,null,null,null,true,-1)" value="${eoms:date2String(sheetLink.linkOperRenewTime)}" alt="allowBlank:true"/> 
				  </td>	
				</tr>
	
				<tr>
				  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/></td>
				  <td colspan="3">
				  	<input type="text" class="text" name="${sheetPageName}linkAffectTimeLength" id="${sheetPageName}linkAffectTimeLength" value="${sheetLink.linkAffectTimeLength}" alt="allowBlank:true"/>
				  </td>
			  
				</tr>
	

		<%} %>

		


		<%if(operateType.equals("5")){ %>		
		
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExamineTask" />
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="5" />
			
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkTransmitContent"/></td>
			  <td colspan="3">
		  		<textarea name="linkTransmitContent" id="linkTransmitContent" class="textarea max"
		  		alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'" >${sheetLink.linkTransmitContent}</textarea>			   
			  </td>
			</tr>			
		
		<%} %>


		<%if(operateType.equals("19")){ %>		
			
			<%if(taskName.equals("FirstExcuteHumTask")){ %>
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask" />
			<%}%>			
            <%if(taskName.equals("SecondExcuteHumTask")){ %>
            	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask" />
			<%}%> 
            <%if(taskName.equals("ThirdExcuteHumTask")){ %> 
            	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask" />
			<%}%>            
			
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="19" />
			<input type="hidden" name="${sheetPageName}linkIfUrgentFault" id="${sheetPageName}linkIfUrgentFault" value="1030101" />
					
		<%} %>  

		<%if(operateType.equals("8")){ %>	
		<!-- 环节内容（组内） 移交处理 -->	
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8" />		
			<%if(taskName.equals("FirstExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask" />
			<%} %>
			<%if(taskName.equals("SecondExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask" />
			<%} %>			
			<%if(taskName.equals("ThirdExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask" />
			<%} %>			
			
	        <tr>
	           <td class="label">
	           			 <bean:message bundle="sheet" key="linkForm.transmitReason"/>
				 </td>
	             <td colspan="3">
	    				<textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason" 
	    				alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.transferReason}</textarea>
				</td>		
		   </tr>							
		<%} %>


		<%if(!operateType.equals("19")&&!operateType.equals("4")&&!operateType.equals("61") && !operateType.equals("8")){%>	
		
		    <tr>
		      <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			  </td>	
			<td colspan="3">			
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
		    </td>
		  </tr>		
			<tr>
			  <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
			  <td colspan="3">
					     <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="commonfault" />	   
			  </td>
			</tr>
		<%}%>  
		
		
		
		
     <%}%>
   


     <%if(taskName.equals("ExamineHumTask")) {%>
     

		<%if(operateType.equals("66")){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="66" />
			<input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve" value="1030101" />		
			<input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value="1" />		
		<%} %>		
		<%if(operateType.equals("64")){ %>			
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="64" />
			<input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve" value="1030102" />	
			<input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value="" />				
		<%} %>
		<%if(operateType.equals("55")){ %>					
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="55" />	
		<%} %>			
		<%if(operateType.equals("88")){ %>	
		<!-- 环节内（组内） 转审处理 -->	
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExamineTask" />
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="88" />				
			
	        <tr>
	           <td class="label">
	           			 <bean:message bundle="sheet" key="linkForm.transmitReason"/>*
				 </td>
	             <td colspan="3">
	    				<textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason" 
	    				alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'" alt="width:'500px'">${sheetLink.transferReason}</textarea>
				</td>		
		   </tr>							
		<%} %>		
			
       <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
		-->	
		<%} %>
		
       <%if(operateType.equals("66")||operateType.equals("64") || operateType.equals("55")){ %>
		<tr>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkExamineContent"/></td>
		  <td colspan="3">
	  		<textarea name="linkExamineContent" id="linkExamineContent" class="textarea max">${sheetLink.linkExamineContent}</textarea>			   
		  </td>
		</tr>
         <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>		
		    </td>
		  </tr>		
		<tr>
		  <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
		  <td colspan="3">
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="commonfault" />		   
		  </td>
		</tr>
		<%} %>
		
		
		
     <%}%>  

     <%if(taskName.equals("HoldHumTask")) {%>
     
     	<%if(operateType.equals("17")){%>
     	<input type="hidden" name="${sheetPageName}mainRejectCount" id="${sheetPageName}mainRejectCount" value="${sheetMain.mainRejectCount}"/>
     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17" />
		<tr>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkUntreadReason"/>*</td>
		  <td colspan="3">
	  		<textarea name="linkUntreadReason" id="linkUntreadReason" class="textarea max" alt="allowBlank:false">${sheetLink.linkUntreadReason}</textarea>			   
		  </td>
		</tr>	
			     	
     	<%} %>


     	<%if(operateType.equals("18")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
	     	
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultResponseLevel"/></td>
			  <td class="content">
			  	<eoms:comboBox name="${sheetPageName}linkFaultResponseLevel" id="${sheetPageName}linkFaultResponseLevel" initDicId="1010304" defaultValue="101030402" />
			  </td>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/></td>
			  <td class="content">
			  	<eoms:comboBox name="${sheetPageName}linkIfGreatFault" 
			  	   id="${sheetPageName}linkIfGreatFault" initDicId="10301" defaultValue="1030102"/>
			  </td>			  
			</tr>
		 <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
		    <td class="content">
		      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
		        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}" initDicId="10303" defaultValue="1030301" styleClass="select" alt="allowBlank:false"/>
		    </td>
		     <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfAffectOperation"/>*</td>
			  <td class="content">
			  	<eoms:comboBox name="${sheetPageName}mainIfAffectOperation" id="${sheetPageName}mainIfAffectOperation" initDicId="10301" defaultValue="1030102" alt="allowBlank:false"/>
			  </td>
		  </tr>
		  
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"  class="textarea max" alt="allowBlank:false">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>			
		   <tr>
		    	<td colspan="4">
		    		<input type="button" title="knowledge" value="新增知识" onclick="popupKnowledge();">
						<input type="button" title="knowledge" value="入遗留问题库" onclick="popupLeaveKnowledge();">
		    	</td>
		   </tr>
		     	
     	<%} %>
     	 <%if(operateType.equals("61")){ %>
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		-->
		<%} %>

     <%} %> 
    
    
  <%  if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'" >${sheetLink.remark}</textarea>
		  </td>
		</tr>  
  <%} %> 
    
	 </table>
	 
  <%if(operateType.equals("1")){ %>	 
  	<%if(taskName.equals("FirstExcuteHumTask")) {%>  	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="commonfault" key="role.SecondExcute"/>			 
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="192" flowId="51" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
	</fieldset>		
	<%} %>
  <%}%>
 
  <%if(operateType.equals("2")){ %>	 
	 <%if(taskName.equals("SecondExcuteHumTask")) {%>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<bean:message bundle="commonfault" key="role.ThirdExcute"/>			 
		 </legend>		

			<eoms:chooser id="commonfaultSendRole" type="role" roleId="193" flowId="51" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%} %>
  <%}%>
  
  <%if(operateType.equals("8")){ %>	 
	<%if(taskName.equals("FirstExcuteHumTask")) {%>
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:				
				<bean:message bundle="commonfault" key="role.FirstExcute"/>
		 </legend>					   
           <eoms:chooser id="test" type="role" roleId="191" flowId="51" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]" />			   			   
		</fieldset>	
	<%} %> 
	<%if(taskName.equals("SecondExcuteHumTask")) {%>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<bean:message bundle="commonfault" key="role.SecondExcute"/>
		 </legend>					   
           <eoms:chooser id="test" type="role" roleId="192" flowId="51" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]" />			   			   
		</fieldset>	
	<%} %>	
	<%if(taskName.equals("ThirdExcuteHumTask")) {%>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<bean:message bundle="commonfault" key="role.ThirdExcute"/>
		 </legend>					   
           <eoms:chooser id="test" type="role" roleId="193" flowId="51" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]" />			   			   
		</fieldset>	
	<%} %>
	
  <%}%>  
  
  <%if(taskName.equals("cc")) {%>	
		<fieldset id="link4">
			<eoms:chooser id="commonfaultSendRole" category="[{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" type="role"  roleId="193" flowId="51" config="{returnJSON:true,showLeader:true}"/>
		</fieldset>		
  <%} %>   
	 
  <%if(operateType.equals("5")){ %>	 
  	<%if(taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") || taskName.equals("ThirdExcuteHumTask")) {%>  	
	<fieldset id="link4">
        <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<bean:message bundle="commonfault" key="role.Examine"/>			 
		 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="194" flowId="51" config="{returnJSON:true,showLeader:true}"
		  category="[{id:'${sheetPageName}dealPerformer' ,childType:'subrole',text:'派发',allowBlank:true,vtext:'请选择派发对象'} ]"/>	
	</fieldset>		
	<%} %>
  <%}%>

  <%if(operateType.equals("88")){ %>	 
 	
	<fieldset id="link4">
        <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<bean:message bundle="commonfault" key="role.Examine"/>			 
		 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="194" flowId="51" config="{returnJSON:true,showLeader:true}"
		  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'} ]"/>	
	</fieldset>		

  <%}%>  
  
  <%if(operateType.equals("46")){ %>	 
 	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="commonfault" key="role.faultSheetCreater"/>			 
	 </legend>
	</fieldset>		

  <%}%> 

<br/>

<div ID="idSpecia2"></div>    