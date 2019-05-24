<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
 <%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 %> 

 <script type="text/javascript">
 
	var frm = document.forms[0];
	var count=0;
	var fm = eoms.form;

	function popupKnowledge()
	{
	
		Ajax.Request(
		  "${app}/sheet/groupcomplaint/groupcomplaint.do?method=createKnowledge",
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
		//处理时限不能超过工单完成时限
		var dtnow = new Date();
		var str = '${sheetMain.sheetCompleteLimit}';
		str = str.replace(/-/g,"/");
		str = str.substring(0,str.length-2);
		var dt2 = new Date(str);
		if(dt2>dtnow){
			document.getElementById("tmpCompleteLimit").value='${sheetMain.sheetCompleteLimit}';
		}else{
			document.getElementById("tmpCompleteLimit").value='';
		}
		
	function ifElectricCode(value){
		if(value == '0'){//是，填写产品实例标识
			eoms.form.enableArea('changaaa');
			eoms.form.disableArea('changeee',true);
		}else if(value == '1'){
			eoms.form.enableArea('changeee');
			eoms.form.disableArea('changaaa',true);
		}else{
			eoms.form.disableArea('changaaa',true);
			eoms.form.disableArea('changeee',true);
		}
		
		
	}
		
 </script>
<%if( operateType.equals("19")){%>

   <%@include file="/WEB-INF/pages/wfworksheet/groupcomplaint/hiddendealtitle.jsp"%>

 <%}else{%>
    <%@ include file="/WEB-INF/pages/wfworksheet/groupcomplaint/baseinputlinkhtmlnew.jsp"%>

<%}%>
<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
	<br/>      <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="GroupComplaintProcess" />
               <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate" />
               <input type="hidden" name="${sheetPageName}beanId" value="iGroupComplaintMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain"/>	<!--main表Model对象类名-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink"/> <!--link表Model对象类名-->
	<c:if test="${taskName != 'HoldHumTask' }">
	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
	</c:if>

     <table class="formTable">
     
     
     <%if(taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ) {  %>
		

		<%if(operateType.equals("1")){ %>	
		<!-- 移交T2处理 -->	
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="1" />		
			<%if(taskName.equals("FirstExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteHumTask" />
			<%} %>

	    	<tr>
		       <td class="label"><bean:message bundle="group" key="groupcomplaint.linkTransmitReason" />*</td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}linkTransmitReason" class="textarea max" id="${sheetPageName}linkTransmitReason" 
			        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.linkTransmitReason}</textarea>
			  </td>
			</tr>				
		<%} %>
		
		<%if(operateType.equals("8")){ %>	
		<!-- 组内 移交处理 -->	
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8" />		
			<%if(taskName.equals("FirstExcuteHumTask")){ %>
			<!-- 不符合新版本的流程设计，隐藏 -->
			<!--  
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT1Task" />
			-->
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask" />
			<%} %>
			<%if(taskName.equals("SecondExcuteHumTask")){ %>
			<!-- 不符合新版本的流程设计，隐藏 -->
			<!--   
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT2Task" />
			-->
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteHumTask" />
			<%} %>			

	    	<tr>
		       <td class="label"><bean:message bundle="group" key="groupcomplaint.linkTransmitReason" />*</td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}transferReason" class="textarea max" id="${sheetPageName}transferReason" 
			        alt="allowBlank:false,width:500,maxLength:200,vtext:'最多输入100汉字'" alt="width:'500px'">${sheetLink.transferReason}</textarea>
			  </td>
			</tr>				
		<%} %>		



		<%if(operateType.equals("46") || operateType.equals("11") ){ %>
			<!-- 处理完成/分派回复 -->			
			
			<!--<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CheckingHumTask" />-->
			<%if(operateType.equals("46")){ %>
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value=46 />	
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeCheckRuleTaskNew" />
				<input type="hidden" name="${sheetPageName}mainCheckResult" id="${sheetPageName}mainCheckResult" value="1" />
			<%} %>
			<%if(operateType.equals("11")){ %>
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="11" />	
			<%} %>			
  			
  			
  		   <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.ndeptContact"/>*</td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact" 
			  	value="${sheetLink.ndeptContact}" alt="allowBlank:false,maxLength:40,vtext:'最多输入20汉字'"/>
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.ndeptContactPhone"/>*</td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone" 
			  	value="${sheetLink.ndeptContactPhone}" alt="allowBlank:false,maxLength:40,vtext:'最多输入20汉字'"/>
			  </td>		  
			</tr>


			<td class="label"><bean:message bundle="group" key="groupcomplaint.compProp"/>*</td>
			  <td>
		  	     <eoms:comboBox name="${sheetPageName}compProp" id="${sheetPageName}compProp" initDicId="1011006" defaultValue="${sheetLink.compProp}" alt="allowBlank:false"/>
			  </td>	
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.isReplied"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}isReplied" id="${sheetPageName}isReplied" initDicId="1011050" defaultValue="${sheetLink.isReplied}" alt="allowBlank:ture"/>
			  </td>			  	 					  
			</tr>
			<td class="label"><bean:message bundle="group" key="groupcomplaint.isCorrect"/>*</td>
			  <td>
		  	     <eoms:comboBox name="${sheetPageName}isCorrect" id="${sheetPageName}isCorrect" initDicId="1011050" defaultValue="${sheetLink.isCorrect}" alt="allowBlank:false"/>
			  </td>	
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.affectedAreas"/>*</td>
			  <td>
                 <eoms:comboBox name="${sheetPageName}affectedAreas" id="${sheetPageName}affectedAreas" initDicId="1011003" defaultValue="${sheetLink.affectedAreas}" alt="allowBlank:false"/>
			  </td>			  	 					  
			</tr>			
			

  		   <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.issueEliminatTime"/>*</td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}issueEliminatTime" readonly="readonly" 
					id="${sheetPageName}issueEliminatTime" value="${eoms:date2String(sheetLink.issueEliminatTime)}" 
					onclick="popUpCalendar(this, this,null,null,null,true,-1)"
					alt="allowBlank:false" />   			  	
			  </td>					  		
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.dealResult"/>*</td>
			  <td>
                 <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult" initDicId="1011007" defaultValue="${sheetLink.dealResult}" alt="allowBlank:false"/>
			  </td>	
			</tr>
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.issueEliminatReason"/>*</td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}issueEliminatReason" id="${sheetPageName}issueEliminatReason" 
			  	value="${sheetLink.issueEliminatReason}" alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'"/>
			  </td>	
				  <td class="label">故障分类*</td>
				  <td>
				  	   <eoms:comboBox name="${sheetPageName}faultClassification"
				  	      id="${sheetPageName}faultClassification" initDicId="1010310" 
				  	      defaultValue="${sheetLink.faultClassification}" alt="allowBlank:false"/>	
				  </td>  
			 </tr>			
			<tr>
			  <td class="label">故障原因*</td>
			  <td>
			   <eoms:comboBox name="${sheetPageName}faultReason"
				  	      id="${sheetPageName}faultReason" initDicId="1010318" 
				  	      defaultValue="${sheetLink.faultReason}" alt="allowBlank:false"/>	
			  </td>	
				  <td class="label">SLA保障等级*</td>
				  <td>
				  	  <eoms:comboBox name="${sheetPageName}gradeSLA"
				  	      id="${sheetPageName}gradeSLA" initDicId="1012801" 
				  	      defaultValue="${sheetLink.gradeSLA}" alt="allowBlank:false"/>	
				  </td>  
	 		 </tr> 
	 		 			<tr>
			  <td class="label">地市*</td>
			  <td>
			   <eoms:comboBox name="${sheetPageName}linkCity"
				  	      id="${sheetPageName}linkCity" initDicId="1012806"  sub ="${sheetPageName}linkArea"
				  	      defaultValue="" alt="allowBlank:false"/>	
			  </td>	
				  <td class="label">区县*</td>
				  <td>
				  	  <eoms:comboBox name="${sheetPageName}linkArea"
				  	      id="${sheetPageName}linkArea" initDicId="${sheetPageName}linkCity" 
				  	      defaultValue="" alt="allowBlank:false"/>	
				  </td>  
	 		 </tr>
	 		 
	 		 <tr>
				<td class="label">是否存在产品实例标识*</td>
				<td colspan="3">
					<select name="linkIfElectricCode" id="linkIfElectricCode" onchange="ifElectricCode(this.value);" alt="allowBlank:false,vtext:'请选择是否存在产品实例标识！'">
				  		<option value ="">请选择</option>
				  		<option value ="0">是</option>
				  		<option value ="1">否</option>
					</select>
	
				</td>
			</tr>
			
			<tr>
				<td class="label">是否转派业务支撑中心*</td>
				<td colspan="3">
					<select name="linkIsNeedBomcAssist" id="linkIsNeedBomcAssist"  alt="allowBlank:false,vtext:'请选择是否转派业务支撑中心！'">
				  		<option value ="0">不需要</option>
				  		<option value ="1">需要</option>
					</select>
	
				</td>
			</tr>
			
			
			<tbody id="changaaa" style="display:none">
			<tr>
			  <td class="label">产品实例标识*</td>
			  <td colspan="3">
		  		<textarea name="electricCode" id="electricCode" class="textarea max" alt="allowBlank:false,maxLength:1000,vtext:'最多输入50汉字'">${sheetLink.electricCode}</textarea>			   
			  </td>
			</tr>	
			</tbody>
			
			<tbody id="changeee" style="display:none">
			<tr>
			  <td class="label">不存在原因*</td>
			  <td colspan="3">
		  		<textarea name="linkNoReason" id="linkNoReason" class="textarea max" alt="allowBlank:false,maxLength:1000,vtext:'最多输入300汉字'">${sheetLink.electricCode}</textarea>			   
			  </td>
			</tr>
			</tbody>
			
			
					
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.dealDesc"/>*</td>
			  <td colspan="3">
		  		<textarea name="dealDesc" id="dealDesc" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'最多输入1000汉字'">${sheetLink.dealDesc}</textarea>			   
			  </td>
			</tr>


		<%} %>


		<%if(!operateType.equals("4")&&!operateType.equals("61")){%>	
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
              				scope="request" idField="${sheetPageName}nodeAccessories" appCode="groupcomplaint" />		   
			  </td>
			</tr>
		<%}%>  
		
     <%}%>
   
		<%if(operateType.equals("4")){ %>
			<%if(taskName.equals("SecondExcuteHumTask")){ %>	
				<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${fOperateroleid}" />
			<%} else { %>
				<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
			<%} %>
			<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
			<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
			
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
			
		  	<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>
			  	<c:when test="${fPreTaskName == 'DraftHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>				
			  	<c:when test="${fPreTaskName == 'FirstExcuteHumTask'}">
			  		<%if(taskName.equals("FirstExcuteHumTask")) {%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT1Task" />
					<%}else{ %>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask" />
					<%} %>
				</c:when>						
			  	<c:when test="${fPreTaskName == 'SecondExcuteHumTask'}">
			  		<%if(taskName.equals("SecondExcuteHumTask")) {%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT2Task" />
					<%}else{%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteHumTask" />
					<%}%>				
				</c:when>						
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>			
		
	    	<tr>
		        <td class="label"><bean:message bundle="sheet" key="linkForm.remark" />*</td>
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
	       	<td class="label"><bean:message bundle="sheet" key="linkForm.remark" /></td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.remark}</textarea>
		   	</td>
		</tr>  	
		 -->	
		<%} %>


     <%if(taskName.equals("CheckingHumTask")) {%>
 		<!-- 质检通过 -->
 		<%if(operateType.equals("56")){ %> 		
	      	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldHumTask" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="56" />
	     	<input type="hidden" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult" value="1030101" />
 	        <input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="1"/> 
 	         <input type="hidden" name="${sheetPageName}mainCheckResult" id="${sheetPageName}mainCheckResult" value="1030101" />
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.linkCheckResult"/></td>
			  <td colspan="3">
  				是
			  </td>
			</tr>		 		
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.linkCheckIdea"/>*</td>
			  <td colspan="3">
		  		<textarea name="linkCheckIdea" id="linkCheckIdea" class="textarea max" alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkCheckIdea}</textarea>			   
			  </td>
			</tr>		 		
 		<%} %>
		<!-- 质检不通过 -->
 		<%if(operateType.equals("54")){ %> 		
	      	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="54" />
 			<input type="hidden" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult" value="1030102" />
 			<input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="1"/> 
			<input type="hidden" name="${sheetPageName}mainQcRejectTimes" id="${sheetPageName}mainQcRejectTimes" value="${sheetMain.mainQcRejectTimes+1}"/> 			
 			<input type="hidden" name="${sheetPageName}mainCheckResult" id="${sheetPageName}mainCheckResult" value="1030102" />
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.linkCheckResult"/></td>
			  <td colspan="3">
 				否
			  </td>
			</tr>
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.linkCheckIdea"/>*</td>
			  <td colspan="3">
		  		<textarea name="linkCheckIdea" id="linkCheckIdea" class="textarea max" alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkCheckIdea}</textarea>			   
			  </td>
			</tr>			 		
 		<%} %>

     <%}%>  

      <%if(taskName.equals("DeferExamineHumTask")) {%>
     
     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />     
		<%if(operateType.equals("66")){ %>		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="66" />
			<input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve" value="1030101" />		
			<input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value="1" />		
		<%} %>		
		<%if(operateType.equals("64")){ %>				
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="64" />
			<input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve" value="1030102" />	
			<input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value="0" />				
		<%} %> 	
    	<tr>
	       <td class="label">
	        <bean:message bundle="group" key="groupcomplaint.linkExamineContent" />*
		    </td>
			<td colspan="3">			 
		           <textarea name="${sheetPageName}linkExamineContent" class="textarea max" id="${sheetPageName}linkExamineContent" 
		        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'" alt="width:'500px'">${sheetLink.linkExamineContent}</textarea>
		  </td>
		</tr>   		

     <%}%> 

     <%if(taskName.equals("HoldHumTask")) {%>
     
     	<%if(operateType.equals("17")){ %>
     	
     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17" />
		<tr>
		  <td class="label"><bean:message bundle="group" key="groupcomplaint.linkUntreadReason"/>*</td>
		  <td colspan="3">
	  		<textarea name="linkUntreadReason" id="linkUntreadReason" class="textarea max" 
	  		alt="allowBlank:false,maxLength:500,vtext:'最多输入250汉字'">${sheetLink.linkUntreadReason}</textarea>			   
		  </td>
		</tr>	
			     	
     	<%} %>


     	<%if(operateType.equals("18")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>			
			
		 <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
		    <td colspan="3">
		      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
		        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select" alt="allowBlank:false"/>
		    </td>
		  </tr>
		  
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
		      alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>			
		  <tr>
		    	<td colspan="4">
		    		<input type="button" title="knowledge" value="新增知识库" onclick="popupKnowledge();">
		    	</td>
		   </tr>
		     	
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
		        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
  <%} %> 

	 </table>
	 
  <%if(operateType.equals("1")){ %>	 
  	<%if(taskName.equals("FirstExcuteHumTask")) {%>  	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="group" key="role.SecondExcute"/>			 
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="307" flowId="057" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'dept,user',limit:'none',text:'抄送'}]"/>	
	</fieldset>		
	<%} %>
  <%}%>
 
  <%if(operateType.equals("8")){ %>	 
  	<%if(taskName.equals("FirstExcuteHumTask")) {%>  	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="group" key="role.FirstExcute"/>		 
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="306" flowId="057" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发对象'} ]"/>	
	</fieldset>		
	<%} %>
  	<%if(taskName.equals("SecondExcuteHumTask")) {%>  	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="group" key="role.SecondExcute"/>			 
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="307" flowId="057" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发对象'} ]"/>	
	</fieldset>		
	<%} %>	
  <%}%> 
  
 <%if(taskName.equals("cc")) {%>	
	<fieldset id="link4">
		<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
		   category="[{id:'copyPerformer',childType:'dept,user',limit:'none',text:'抄送'}]"/>
    </fieldset>		
 <%} %>   
	 
   
  <%if(operateType.equals("46")){ %>	 
 	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
	 		
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="309" flowId="057" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]"/>		 
	</fieldset>		

  <%}%> 



