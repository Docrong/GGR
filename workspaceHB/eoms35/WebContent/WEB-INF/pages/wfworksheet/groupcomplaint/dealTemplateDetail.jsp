<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink  groupcomplaintLink = (com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(groupcomplaintLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(groupcomplaintLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/groupcomplaint/groupcomplaint.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
   	form.submit();
 }
 
function removeTemplate() {
	if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
		var thisform = document.forms[0];
		thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
		thisform.submit();
	}
}
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/groupcomplaint/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/groupcomplaint.do" styleId="theform">       
	<br/>
        
        
               <input type="hidden" name="${sheetPageName}beanId" value="iGroupComplaintMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain"/>	<!--main表Model对象类名-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink"/> <!--link表Model对象类名-->
               <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->
        
     <table class="formTable">
     	<tr>
           <td class="label" >
           		<bean:message bundle="sheet" key="input.templateName" />
           </td>
           <td colspan="3">
           		<input type="text" name="templateName" class="text" id="templateName" value="${sheetLink.templateName}"/>
           </td>         
       </tr>
      <%if(taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ) {%>
		
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
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT1Task" />
			<%} %>
			<%if(taskName.equals("SecondExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT2Task" />
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
			<%} %>
			<%if(operateType.equals("11")){ %>
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="11" />	
			<%} %>			
  			
  			
  		   <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.ndeptContact"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact" 
			  	value="${sheetLink.ndeptContact}" alt="allowBlank:true,maxLength:40,vtext:'最多输入20汉字'"/>
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.ndeptContactPhone"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone" 
			  	value="${sheetLink.ndeptContactPhone}" alt="allowBlank:true,maxLength:40,vtext:'最多输入20汉字'"/>
			  </td>		  
			</tr>


			<td class="label"><bean:message bundle="group" key="groupcomplaint.compProp"/></td>
			  <td>
		  	     <eoms:comboBox name="${sheetPageName}compProp" id="${sheetPageName}compProp" initDicId="1011006" defaultValue="${sheetLink.compProp}" alt="allowBlank:ture"/>
			  </td>	
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.isReplied"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}isReplied" id="${sheetPageName}isReplied" initDicId="1011050" defaultValue="${sheetLink.isReplied}" alt="allowBlank:ture"/>
			  </td>			  	 					  
			</tr>
			<td class="label"><bean:message bundle="group" key="groupcomplaint.isCorrect"/></td>
			  <td>
		  	     <eoms:comboBox name="${sheetPageName}isCorrect" id="${sheetPageName}isCorrect" initDicId="1011050" defaultValue="${sheetLink.isCorrect}" alt="allowBlank:ture"/>
			  </td>	
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.affectedAreas"/></td>
			  <td>
                 <eoms:comboBox name="${sheetPageName}affectedAreas" id="${sheetPageName}affectedAreas" initDicId="1011003" defaultValue="${sheetLink.affectedAreas}" alt="allowBlank:ture"/>
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
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.dealResult"/></td>
			  <td>
                 <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult" initDicId="1011007" defaultValue="${sheetLink.dealResult}" alt="allowBlank:ture"/>
			  </td>	
			</tr>
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.issueEliminatReason"/></td>
			  <td colspan="3">
			  	<input type="text" class="text" name="${sheetPageName}issueEliminatReason" id="${sheetPageName}issueEliminatReason" 
			  	value="${sheetLink.issueEliminatReason}" alt="allowBlank:true,maxLength:1000,vtext:'最多输入500汉字'"/>
			  </td>	
			 </tr>			

			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.dealDesc"/></td>
			  <td colspan="3">
		  		<textarea name="dealDesc" id="dealDesc" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'最多输入1000汉字'">${sheetLink.dealDesc}</textarea>			   
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
		
			<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
			<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
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
</table>
</html:form>
<logic:present name="type">
<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveDealTemplate('current')">
          	<bean:message bundle="sheet" key="button.saveCurTemplate" />
    	</html:button>
</c:if>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
         <bean:message bundle="sheet" key="button.delete" />
</html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
         <bean:message bundle="sheet" key="button.back" />
</html:button>
       