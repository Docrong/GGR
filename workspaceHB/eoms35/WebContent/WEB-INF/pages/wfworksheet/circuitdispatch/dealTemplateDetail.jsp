<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink  circuitDispatchLink = (com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(circuitDispatchLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(circuitDispatchLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/circuitdispatch/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/circuitdispatch.do" styleId="theform">       
	<br/>
        
         <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="CircuitDispatchMainFlowProcess" />
          <input type="hidden" name="${sheetPageName}beanId" value="iCircuitDispatchMainManager"/>
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchMain"/>	<!--main?Model????-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink"/> <!--link?Model????-->
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
     <%if(taskName.equals("ProjectCreateTask")) {%>

     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask" />
		<tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExecuteEndDate"/>*</td>
     		<td colspan="3">
    			<input type="text" class="text" name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
					id="${sheetPageName}linkExecuteEndDate" value="${eoms:date2String(sheetLink.linkExecuteEndDate)}" 
					onclick="popUpCalendar(this, this)"/>
			  </td>
		</tr>
     	<tr>
     		<!-- ???? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.nodeAcceptLimit"/>*</td>
     		<td>
    			<input type="text" class="text" name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
					id="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" 
					onclick="popUpCalendar(this, this)"/>
			  </td>
			  <!-- ???? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.nodeCompleteLimit"/>*</td>
			  <td>
    			<input type="text" class="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
					id="${sheetPageName}nodeCompleteLimit" value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" 
					onclick="popUpCalendar(this, this)"/>
			  </td>
		</tr>
		<tr>
            <!-- ?????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkInvolvedProvince"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}linkInvolvedProvince" id="${sheetPageName}linkInvolvedProvince" initDicId="1011608" defaultValue="${sheetLink.linkInvolvedProvince}" alt="allowBlank:false"/>
			  </td>
			  <!-- ?????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkInvolvedCity"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkInvolvedCity" id="${sheetPageName}linkInvolvedCity" initDicId="1011608" defaultValue="${sheetLink.linkInvolvedCity}" alt="allowBlank:false"/>
			  </td>
			</tr>
		<tr>
			<!-- ?????? -->
			<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkServiceResData"/>*</td>
     		<td colspan="3">
    			<eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="circuitdispatch" 
		             viewFlag="Y"/>
		        <html:button styleClass="btn" property="putData" onclick="">
		        	<bean:message bundle='circuitdispatch' key='button.putData'/>
		        </html:button>
			  </td>
		</tr>
		<tr>
			  <!-- ???? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="button.projectCreate"/>*</td>
			  <td colspan="3">
    			<html:button styleClass="btn" property="projectCreate" onclick="">
		        	<bean:message bundle='circuitdispatch' key='button.projectCreate'/>
		        </html:button>
			  </td>
		</tr>
     	<tr>
     		<!-- ??????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectKey"/>*</td>
     		<td>
    			<input type="text" class="text" name="${sheetPageName}linkProgrammeKey" id="${sheetPageName}linkProgrammeKey" value="${sheetLink.linkProgrammeKey}" alt="allowBlank:false"/>
			  </td>
     	</tr>
     	<tr>
     		<!-- ???? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkRiskEvaluate"/>*</td>
     		<td colspan="3">
     			<textarea name="linkRiskEvaluate" id="linkRiskEvaluate" class="textarea max" alt="allowBlank:false">${sheetLink.linkRiskEvaluate}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectSituation"/>*</td>
     		<td colspan="3">
     			<textarea name="linkAffectSituation" id="linkAffectSituation" class="textarea max" alt="allowBlank:false">${sheetLink.linkAffectSituation}</textarea>
			  </td>
     	</tr>
     	<tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectExplain"/>*</td>
     		<td colspan="3">
     			<textarea name="linkProgrammeExplain" id="linkProgrammeExplain" class="textarea max" alt="allowBlank:false">${sheetLink.linkProgrammeExplain}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectAcc"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" />
			  </td>
		</tr>
     <%}else if(taskName.equals("PermitTask")){%>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PlanTask" />
        <tr>
     		<!-- ???? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPermitResult"/>*</td>
     		<td colspan="3">
     			<eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult" initDicId="10301" defaultValue="${sheetLink.linkPermitResult}" alt="allowBlank:false" onchange="ifAuditPass(this.value);"/>
			  </td>
		</tr>
        <tr>
     		<!-- ???? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPermitIdea"/>*</td>
     		<td colspan="3">
     			<textarea name="linkPermitIdea" id="linkPermitIdea" class="textarea max" alt="allowBlank:false">${sheetLink.linkPermitIdea}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- ?? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectAcc"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" />
			  </td>
		</tr>
     <%}else if(taskName.equals("PlanTask")){%>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask" />
     	<tr>
     		<!-- ????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcutePrincipal"/>*</td>
     		<td>
    			<input type="text" class="text" name="${sheetPageName}linkExcutePrincipal" id="${sheetPageName}linkExcutePrincipal" value="${sheetLink.linkExcutePrincipal}" alt="allowBlank:false"/>
			  </td>
     		<!-- ???? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkContact"/>*</td>
     		<td>
    			<input type="text" class="text" name="${sheetPageName}linkContact" id="${sheetPageName}linkContact" value="${sheetLink.linkContact}" alt="allowBlank:false"/>
			  </td>
     	</tr>
		   <tr>
		   <!-- ?????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanStartDate"/>*</td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}linkPlanStartDate" readonly="readonly" 
					id="${sheetPageName}linkPlanStartDate" onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
			  </td>
			  <!-- ?????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanEndDate"/>*</td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}linkPlanEndDate" readonly="readonly" 
					id="${sheetPageName}linkPlanEndDate" onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
			  </td>
			</tr>
            <!-- ?????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfAffectOperation"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}linkIfAffectOperation" id="${sheetPageName}linkIfAffectOperation" initDicId="10301" defaultValue="${sheetLink.linkIfAffectOperation}" alt="allowBlank:false"/>
			  </td>
			  <!-- ?????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfNotify"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkIfNotify" id="${sheetPageName}linkIfNotify" initDicId="10301" defaultValue="${sheetLink.linkIfNotify}" alt="allowBlank:false"/>
			  </td>
			</tr>
			<tr>
			<!-- ?????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkReferOperateDept"/>*</td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}linkReferOperateDept" id="${sheetPageName}linkReferOperateDept" initDicId="10301" defaultValue="${sheetLink.linkIfNotify}" alt="allowBlank:false"/>
			  </td>
			</tr>
        <tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkNetAffectArea"/>*</td>
     		<td colspan="3">
     			<textarea name="linkNetAffectArea" id="linkNetAffectArea" class="textarea max" alt="allowBlank:false">${sheetLink.linkNetAffectArea}</textarea>
			  </td>
		</tr>
        <tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectSituation"/>*</td>
     		<td colspan="3">
     			<textarea name="linkAffectSituation" id="linkAffectSituation" class="textarea max" alt="allowBlank:false">${sheetLink.linkAffectSituation}</textarea>
			  </td>
		</tr>
        <tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectNetManage"/>*</td>
     		<td colspan="3">
     			<textarea name="linkAffectNetManage" id="linkAffectNetManage" class="textarea max" alt="allowBlank:false">${sheetLink.linkAffectNetManage}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- ???? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExecuteAcc"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" />
			  </td>
		</tr>
     <%}else if(taskName.equals("ExecuteTask")){%>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask" />
     		<tr>
            <!-- ???? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteResult"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}linkExcuteResult" id="${sheetPageName}linkExcuteResult" initDicId="1011608" defaultValue="${sheetLink.linkExcuteResult}" alt="allowBlank:false"/>
			  </td>
			  <!-- ???? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkTestResult"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" initDicId="1011608" defaultValue="${sheetLink.linkTestResult}" alt="allowBlank:false"/>
			  </td>
			</tr>
			<tr>
			<!-- ?????????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIsAccordanceProgramme"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkIsAccordanceProgramme" id="${sheetPageName}linkIsAccordanceProgramme" initDicId="1011608" defaultValue="${sheetLink.linkIsAccordanceProgramme}" alt="allowBlank:false"/>
			  </td>
			  <!-- ???? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkFailedReason"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkFailedReason" id="${sheetPageName}linkFailedReason" initDicId="1011608" defaultValue="${sheetLink.linkFailedReason}" alt="allowBlank:false"/>
			  </td>
			</tr>
        <tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteExplain"/>*</td>
     		<td colspan="3">
     			<textarea name="linkExcuteExplain" id="linkExcuteExplain" class="textarea max" alt="allowBlank:false">${sheetLink.linkExcuteExplain}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- ???????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectOperationExplain"/>*</td>
     		<td colspan="3">
     			<textarea name="linkAffectOperationExplain" id="linkAffectOperationExplain" class="textarea max" alt="allowBlank:false">${sheetLink.linkAffectOperationExplain}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAlarmRecord"/>*</td>
     		<td colspan="3">
     			<textarea name="linkAlarmRecord" id="linkAlarmRecord" class="textarea max" alt="allowBlank:false">${sheetLink.linkAlarmRecord}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- ???????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkReportAbnormityExplain"/>*</td>
     		<td colspan="3">
     			<textarea name="linkReportAbnormityExplain" id="linkReportAbnormityExplain" class="textarea max" alt="allowBlank:false">${sheetLink.linkReportAbnormityExplain}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- ???? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkTestReport"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" />
			  </td>
		</tr>
     <%}else if(taskName.equals("ValidateTask")){%>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
     		<tr>
            <!-- ?????????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfUpdatePlan"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}linkIfUpdatePlan" id="${sheetPageName}linkIfUpdatePlant" initDicId="1011608" defaultValue="${sheetLink.linkIfUpdatePlan}" alt="allowBlank:false"/>
			  </td>
			  <!-- ????????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkCircuitUpdate"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkCircuitUpdate" id="${sheetPageName}linkCircuitUpdate" initDicId="1011608" defaultValue="${sheetLink.linkCircuitUpdate}" alt="allowBlank:false"/>
			  </td>
			</tr>
			<tr>
				<!-- ???????? -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIsToMaintenance"/>*</td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}linkIsToMaintenance" id="${sheetPageName}linkIsToMaintenance" initDicId="1011608" defaultValue="${sheetLink.linkIsToMaintenance}" alt="allowBlank:false"/>
			  </td>
			</tr>
		<tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteConstrue"/>*</td>
     		<td colspan="3">
     			<textarea name="linkExcuteConstrue" id="linkExcuteConstrue" class="textarea max" alt="allowBlank:false">${sheetLink.linkExcuteConstrue}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- ???????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanUpdateIdea"/>*</td>
     		<td colspan="3">
     			<textarea name="linkPlanUpdateIdea" id="linkPlanUpdateIdea" class="textarea max" alt="allowBlank:false">${sheetLink.linkPlanUpdateIdea}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- ?????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkWorkPlan"/>*</td>
     		<td colspan="3">
     			<textarea name="linkWorkPlan" id="linkWorkPlan" class="textarea max" alt="allowBlank:false">${sheetLink.linkWorkPlan}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- ???? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkMaintenanceDes"/>*</td>
     		<td colspan="3">
     			<textarea name="linkMaintenanceDes" id="linkMaintenanceDes" class="textarea max" alt="allowBlank:false">${sheetLink.linkMaintenanceDes}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- ????????? -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkServiceResData"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" />
			  </td>
		</tr>
     <%} %>
    <%  if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
  <%} %> 
  <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
 <%} %> 
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
       