<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.circuitdispatch.model.NetChangeLink  netchangeLink = (com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink)request.getAttribute("sheetLink");
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
   	form.action = "${app}/sheet/netchange/netchange.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/netchange/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/netchange.do" styleId="theform">       
	<br/>
         <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="NetChangeMainProcess" />
          <input type="hidden" name="${sheetPageName}beanId" value="iNetChangeMainManager"/>
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.netchange.model.NetChangeMain"/>	<!--main?Model????-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.netchange.model.NetChangeLink"/> <!--link?Model????-->
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
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
     	<tr>
     		<!-- 技术方案关键字 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkProjectKey"/>*</td>
     		<td colspan="3">
    			<input type="text" class="text" name="${sheetPageName}linkProjectKey" id="${sheetPageName}linkProjectKey" value="${sheetLink.linkProjectKey}" alt="allowBlank:false"/>
			  </td>
     	</tr>
     	<tr>
     		<!-- 风险评估 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkRiskEvaluate"/>*</td>
     		<td colspan="3">
     			<textarea name="${sheetPageName}linkRiskEvaluate" id="${sheetPageName}linkRiskEvaluate" class="textarea max" alt="allowBlank:false">${sheetLink.linkRiskEvaluate}</textarea>
			  </td>
     	</tr>
     	<tr>
     		<!-- 影响业务分析 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkOperationConstrue"/>*</td>
     		<td colspan="3">
     			<textarea name="${sheetPageName}linkOperationConstrue" id="${sheetPageName}linkOperationConstrue" class="textarea max" alt="allowBlank:false">${sheetLink.linkOperationConstrue}</textarea>
			  </td>
     	</tr>
     	<tr>
     		<!-- 总技术方案说明 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkProjectExplain"/></td>
     		<td colspan="3">
     			<textarea name="${sheetPageName}linkProjectExplain" id="${sheetPageName}linkProjectExplain" class="textarea max" alt="allowBlank:true">${sheetLink.linkProjectExplain}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- 总技术方案附件 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkProjectAcc"/></td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange" />
			  </td>
		</tr>
     <%}else if(taskName.equals("AuditTask")){%>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask" />
        <tr>
     		<!-- 审核结果 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkAuditingResult"/>*</td>
     		<td colspan="3">
     			<eoms:comboBox name="${sheetPageName}linkAuditingResult" id="${sheetPageName}linkAuditingResult" initDicId="1011608" defaultValue="${sheetLink.linkAuditingResult}" alt="allowBlank:false"/>
			  </td>
		</tr>
        <tr>
     		<!-- 审核意见 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkAuditingIdea"/>*</td>
     		<td colspan="3">
     			<textarea name="linkAuditingIdea" id="linkAuditingIdea" class="textarea max" alt="allowBlank:false">${sheetLink.linkAuditingIdea}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- 附件 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkProjectAcc"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange" />
			  </td>
		</tr>
     <%}else if(taskName.equals("PermitTask")){%>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask" />
        <tr>
     		<!-- 审批结果 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkPermitResult"/>*</td>
     		<td colspan="3">
     			<eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult" initDicId="1011608" defaultValue="${sheetLink.linkPermitResult}" onchange="ifAuditPass(this.value);" alt="allowBlank:false"/>
			  </td>
		</tr>
        <tr>
     		<!-- 审批意见 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkPermitIdea"/>*</td>
     		<td colspan="3">
     			<textarea name="linkPermitIdea" id="linkPermitIdea" class="textarea max" alt="allowBlank:false">${sheetLink.linkPermitIdea}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- 附件 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.nodeAccessories"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange" />
			  </td>
		</tr>
     <%}else if(taskName.equals("ExecuteTask")){%>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TestTask" />
     	<tr>
            <!-- 是否启动电路调度工单 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartCircuit"/>*</td>
			  <td>
			  	<input type="hidden" name="${sheetPageName}linkIfStartCircuit" id="${sheetPageName}linkIfStartCircuit" value="" />
			  	<a href="/WEB-INF/pages/wfworksheet/circuitdispatch/inputmain.jsp"><bean:message bundle="netchange" key="netchange.linkIfStartCircuit"/></a>
			  </td>
			  <!-- 是否启动软件变更工单 -->
			  <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartData"/>*</td>
			  <td>
			  <input type="hidden" name="${sheetPageName}linkIfStartData" id="${sheetPageName}linkIfStartData" value="" />
			  	<eoms:comboBox name="${sheetPageName}linkIfNotify" id="${sheetPageName}linkIfNotify" initDicId="${sheetLink.linkIfNotify}" defaultValue="${sheetLink.linkIfNotify}" alt="allowBlank:false"/>
			  </td>
			</tr>
			<tr>
			<!-- 是否启动网络数据管理工单 -->
			  <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartSoft"/>*</td>
			  <td colspan="3">
			  <input type="hidden" name="${sheetPageName}linkIfStartCircuit" id="${sheetPageName}linkIfStartCircuit" value="" />
			  	<a href="/WEB-INF/pages/wfworksheet/circuitdispatch/inputmain.jsp"><bean:message bundle="netchange" key="netchange.linkIfStartCircuit"/></a>
			  </td>
			</tr>
        <tr>
     		<!-- 综合实施结果 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkColligateExcuteResult"/>*</td>
     		<td colspan="3">
     			<textarea name="linkColligateExcuteResult" id="linkColligateExcuteResult" class="textarea max" alt="allowBlank:false">${sheetLink.linkColligateExcuteResult}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- 综合实施报告 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkColligateExcuteReport"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange" />
			  </td>
		</tr>
     <%}else if(taskName.equals("TestTask")){%>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
    		<tr>
            <!-- 实施结果 -->
			  <td class="label"><bean:message bundle="netchange" key="netchange.linkExcuteResult"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}linkExcuteResult" id="${sheetPageName}linkExcuteResult" initDicId="1011608" defaultValue="${sheetLink.linkExcuteResult}" alt="allowBlank:false"/>
			  </td>
			  <!-- 是否完全按照方案实施 -->
			  <td class="label"><bean:message bundle="netchange" key="netchange.linkIfAccordingProject"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkIfAccordingProject" id="${sheetPageName}linkIfAccordingProject" initDicId="1011608" defaultValue="${sheetLink.linkIfAccordingProject}" alt="allowBlank:false"/>
			  </td>
			</tr>
			<tr>
				<!-- 执行是否成功 -->
			  <td class="label"><bean:message bundle="netchange" key="netchange.linkIfExcuteSuccess"/>*</td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}linkIfExcuteSuccess" id="${sheetPageName}linkIfExcuteSuccess" initDicId="1011608" defaultValue="${sheetLink.linkIfExcuteSuccess}" alt="allowBlank:false"/>
			  </td>
			</tr>
		<tr>
     		<!-- 综合测试情况概述 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkTestSummarize"/>*</td>
     		<td colspan="3">
     			<textarea name="${sheetPageName}linkTestSummarize" id="${sheetPageName}linkTestSummarize" class="textarea max" alt="allowBlank:false">${sheetLink.linkTestSummarize}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- 后续工作安排 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkWorkPlan"/>*</td>
     		<td colspan="3">
     			<textarea name="${sheetPageName}linkWorkPlan" id="${sheetPageName}linkWorkPlan" class="textarea max" alt="allowBlank:false">${sheetLink.linkWorkPlan}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- 是否涉及工程交维 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkIsToMaintenance"/>*</td>
     		<td colspan="3">
     		<eoms:comboBox name="${sheetPageName}linkIsToMaintenance" id="${sheetPageName}linkIsToMaintenance" initDicId="1011608" defaultValue="${sheetLink.linkIsToMaintenance}" alt="allowBlank:false"/>
			  </td>
		</tr>
		<tr>
     		<!-- 交维描述 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkMaintenanceDes"/>*</td>
     		<td colspan="3">
     			<textarea name="${sheetPageName}linkMaintenanceDes" id="${sheetPageName}linkMaintenanceDes" class="textarea max" alt="allowBlank:false">${sheetLink.linkMaintenanceDes}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- 综合测试报告 -->
     		<td class="label"><bean:message bundle="netchange" key="netchange.linkExcuteReport"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange" />
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
       