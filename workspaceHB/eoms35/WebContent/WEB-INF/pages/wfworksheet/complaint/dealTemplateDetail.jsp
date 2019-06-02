<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.complaint.model.ComplaintLink  complaintLink = (com.boco.eoms.sheet.complaint.model.ComplaintLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(complaintLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(complaintLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/complaint/complaint.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/complaint/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/complaint.do" styleId="theform">       
	<br/>
        
        
               <input type="hidden" name="${sheetPageName}beanId" value="iComplaintMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.complaint.model.ComplaintMain"/>	<!--main表Model对象类名-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.complaint.model.ComplaintLink"/> <!--link表Model对象类名-->
               <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->
        
     <table class="listTable">
     	<tr>
           <td class="label" >
           		<bean:message bundle="sheet" key="input.templateName" />
           </td>
           <td colspan="3">
           		<input type="text" name="templateName" class="text" id="templateName" value="${sheetLink.templateName}"/>
           </td>         
       </tr>
     <%if(taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") || taskName.equals("ThirdExcuteHumTask") ) {%>
		

		<%if(operateType.equals("1")){ %>
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="1" />
		
			<%if(taskName.equals("FirstExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask" />
			<%} %>

			
		<tr>
		  <td class="label"><bean:message bundle="complaint" key="complaint.mainIfMutualCommunication"/></td>
		  <td>
		  	<eoms:comboBox name="${sheetPageName}linkIfMutualCommunication" id="${sheetPageName}linkIfMutualCommunication" initDicId="1011608" defaultValue="${sheetLink.linkIfMutualCommunication}"/>
		  </td>
		  <td class="label"><bean:message bundle="complaint" key="complaint.mainIfSafe"/></td>
		  <td>
		  	<eoms:comboBox name="${sheetPageName}linkIfSafe" id="${sheetPageName}linkIfSafe" initDicId="1011608" defaultValue="${sheetLink.linkIfSafe}"/>
		  </td>			  
		</tr>

		
		<tr>
		  <td class="label"><bean:message bundle="complaint" key="complaint.linkFaultFirstDealDesc"/></td>
		  <td colspan="3">
			 <textarea name="linkFaultFirstDealDesc" id="linkFaultFirstDealDesc" class="textarea max">${sheetLink.linkFaultFirstDealDesc}</textarea>	  
		  </td>
		</tr>		

		<tr>
		  <td class="label"><bean:message bundle="complaint" key="complaint.linkFaultDesc"/></td>
		  <td colspan="3">
		   	<textarea name="linkFaultDesc" id="linkFaultDesc" class="textarea max">${sheetLink.linkFaultDesc}</textarea>	  
		  </td>
		</tr>
				
		<%} %>


		<%if(operateType.equals("2")){ %>
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="2" />
			
			<%if(taskName.equals("SecondExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask" />
			<%} %>			
			
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkTransmitReason"/></td>
			  <td colspan="3">
		  		<textarea name="linkTransmitReason" id="linkTransmitReason" class="textarea max">${sheetLink.linkTransmitReason}</textarea>			   
			  </td>
			</tr>		
		
		<%} %>




		<%if(operateType.equals("46")){ %>
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="46" />
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />

			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDealResult"/></td>
			  <td colspan="3">
				<eoms:comboBox name="${sheetPageName}linkFaultDealResult" id="${sheetPageName}linkFaultDealResult" initDicId="1011612" defaultValue="${sheetLink.linkFaultDealResult}"/>		   
			  </td>
			</tr>

			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultReason"/></td>
			  <td colspan="3">
					<bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>
					<eoms:comboBox name="${sheetPageName}linkFaultReasonSort" id="${sheetPageName}linkFaultReasonSort" initDicId="1011609" defaultValue="${sheetLink.linkFaultReason}"/>
					<bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>	   
					<eoms:comboBox name="${sheetPageName}linkFaultReasonSubsection" id="${sheetPageName}linkFaultReasonSubsection" initDicId="1011610" defaultValue="${sheetLink.linkFaultReason}"/>
			  </td>
			</tr>

			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkDealStep"/></td>
			  <td colspan="3">
		  		<textarea name="linkDealStep" id="linkDealStep" class="textarea max">${sheetLink.linkDealStep}</textarea>			   
			  </td>
			</tr>

			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfExcuteNetChange"/></td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}linkIfExcuteNetChange" id="${sheetPageName}linkIfExcuteNetChange" initDicId="1011608" defaultValue="${sheetLink.linkIfExcuteNetChange}"/>	   
			  </td>
			</tr>
			
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfFinallySolveProject"/></td>
			  <td colspan="3">
			  	   <eoms:comboBox name="${sheetPageName}linkIfFinallySolveProject" id="${sheetPageName}linkIfFinallySolveProject" initDicId="1011608" defaultValue="${sheetLink.linkIfFinallySolveProject}"/>	   
			  </td>
			</tr>
			
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfAddCaseDataBase"/></td>
			  <td colspan="3">
			  	   <eoms:comboBox name="${sheetPageName}linkIfAddCaseDataBase" id="${sheetPageName}linkIfAddCaseDataBase" initDicId="1011608" defaultValue="${sheetLink.linkIfAddCaseDataBase}"/>	   
			  </td>
			</tr>
	
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/></td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}linkFaultAvoidTime" readonly="readonly" 
					id="${sheetPageName}linkFaultAvoidTime"  
					onclick="popUpCalendar(this, this)" value="${eoms:date2String(sheetLink.linkFaultAvoidTime)}"/> 	
			  </td>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/></td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}linkOperRenewTime" readonly="readonly" 
					id="${sheetPageName}linkOperRenewTime" 
					onclick="popUpCalendar(this, this)" value="${eoms:date2String(sheetLink.linkOperRenewTime)}"/> 
			  </td>			  
			</tr>

			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}linkAffectTimeLength" id="${sheetPageName}linkAffectTimeLength" value="${sheetLink.linkAffectTimeLength}" alt="allowBlank:true"/>
			  </td>
			  <td class="label"></td>
			  <td>
			  	
			  </td>			  
			</tr>
	
	

		<%} %>

		<%if(operateType.equals("5")){ %>		
		
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExamineTask" />
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="5" />
			
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkTransmitContent"/></td>
			  <td colspan="3">
		  		<textarea name="linkTransmitContent" id="linkTransmitContent" class="textarea max">${sheetLink.linkTransmitContent}</textarea>			   
			  </td>
			</tr>			
		
		<%}} %>
   


     <%if(taskName.equals("ExamineHumTask")) {%>

		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="6" />
			
		<tr>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfDeferResolve"/></td>
		  <td colspan="3">
	  		 <eoms:comboBox name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve" initDicId="1011608" defaultValue="${sheetLink.linkIfDeferResolve}"/>
		  </td>
		</tr>

		<tr>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkExamineContent"/></td>
		  <td colspan="3">
	  		<textarea name="linkExamineContent" id="linkExamineContent" class="textarea max">${sheetLink.linkExamineContent}</textarea>			   
		  </td>
		</tr>

		<tr>
		  <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
		  <td colspan="3">
		    <div class="x-form-item">
				<div class="x-form-element">
				     <eoms:attachment idList="" idField="${sheetPageName}nodeAccessories" appCode="commonfault" />
				</div>
			</div>		   
		  </td>
		</tr>
     <%} %>  

     <%if(taskName.equals("HoldHumTask")) {%>
     
     	<%if(operateType.equals("7")){ %>
     	
     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7" />
		<tr>
		  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkUntreadReason"/></td>
		  <td colspan="3">
	  		<textarea name="linkUntreadReason" id="linkUntreadReason" class="textarea max">${sheetLink.linkUntreadReason}</textarea>			   
		  </td>
		</tr>	
			     	
     	<%} %>


     	<%if(operateType.equals("18")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
	     	
			<tr>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultResponseLevel"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkFaultResponseLevel" id="${sheetPageName}linkFaultResponseLevel" initDicId="1011603" defaultValue="${sheetLink.linkFaultResponseLevel}" />
			  </td>
			  <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkIfGreatFault" id="${sheetPageName}linkIfGreatFault" initDicId="1011608" defaultValue="${sheetLink.linkIfGreatFault}"/>
			  </td>			  
			</tr>
			
			
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/></td>
		    <td colspan="3">
		      <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied" initDicId="10303" styleClass="select" />
		    </td>
		  </tr>
		  
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"  alt="allowBlank:false" class="textarea max"></textarea>
		    </td>
		  </tr>		
		     	
     	<%}} %>
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
       