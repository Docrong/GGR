<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.businessoperation.model.BusinessOperationLink  businessOperationLink = (com.boco.eoms.sheet.businessoperation.model.BusinessOperationLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(businessOperationLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(businessOperationLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/BusinessOperation/businessoperation.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/businessoperation/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/businessoperation.do" styleId="theform">       
	<br/>
        
        
               <input type="hidden" name="${sheetPageName}beanId" value="iBusinessOperationMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessoperation.model.BusinessOperationMain"/>	<!--main表Model对象类名-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessoperation.model.BusinessOperationLink"/> <!--link表Model对象类名-->
               <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->
        
     <table class="formTable">
<%if(taskName.equals("operate")){ 
	             if(operateType.equals("91")){%>
	                 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="refine" />
	                  <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="92" />
			      <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkBusinessDesc"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkBusinessDesc" id="${sheetPageName}linkBusinessDesc" alt="width:500,allowBlank:false">${sheetLink.linkBusinessDesc}</textarea>
                    </td>
		          </tr>
	                <tr>
  						<td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkOperStartTime"/>*</td>
		   				 <td >
		   				 <input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}linkOperStartTime" readonly="readonly" alt="allowBlank:false" value="${eoms:date2String(sheetLink.linkOperStartTime)}" id="${sheetPageName}linkOperStartTime" />
		                </td>
  						<td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkOperEndTime"/>*</td>
		   				 <td>
		   				 <input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}linkOperEndTime" readonly="readonly"  alt="allowBlank:false" value="${eoms:date2String(sheetLink.linkOperEndTime)}" id="${sheetPageName}linkOperEndTime" />
		                </td>
		            </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkTestResult"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" 
            	      initDicId="1010507"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkTestResult}"/>
			        </td>	                
		                 <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkNetType"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType" 
            	      initDicId="1010508"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkNetType}"/>
			        </td>	                
		            </tr>
                       <tr>
  						<td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkOperateScheme"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" name="sheetLink" scope="request" property="linkOperateScheme" idField="${sheetPageName}linkOperateScheme" appCode="businessoperation" alt="allowBlank:false"/>
		                </td>
		            </tr>
	                <tr>
  						<td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkAlterationAcc"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" name="sheetLink" scope="request" property="linkAlterationAcc" idField="${sheetPageName}linkAlterationAcc" appCode="businessoperation" alt="allowBlank:false"/>
		                </td>
		            </tr>
		            <%}} %>
		            
		            
		     <%if(taskName.equals("refine")){ 
	          if(operateType.equals("92")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="prepare" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="93" />
	                <tr>
  						<td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkTGPolicyAcc"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" name="sheetLink" scope="request" property="linkTGPolicyAcc" idField="${sheetPageName}linkTGPolicyAcc" appCode="businessoperation" alt="allowBlank:false"/>
		                </td>
		            </tr>
		            <%}} %>
		            
		            
			 <%if(taskName.equals("prepare")){ 
	          if(operateType.equals("93")){%>
	          	     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="report" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="94" />	        
			      <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkDataFile"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkDataFile" id="${sheetPageName}linkDataFile" alt="width:500,allowBlank:true">${sheetLink.linkDataFile}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkVerify"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkVerify" id="${sheetPageName}linkVerify" alt="width:500,allowBlank:true">${sheetLink.linkVerify}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkPassMan"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkPassMan" id="${sheetPageName}linkPassMan" alt="width:500,allowBlank:true">${sheetLink.linkPassMan}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkReport"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkReport" id="${sheetPageName}linkReport" alt="width:500,allowBlank:true">${sheetLink.linkReport}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkWorkplan"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkWorkplan" id="${sheetPageName}linkWorkplan" alt="width:500,allowBlank:true">${sheetLink.linkWorkplan}</textarea>
                    </td>
		          </tr>
	                <tr>
  						<td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkMeetingAcc"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" name="sheetLink" scope="request" property="linkMeetingAcc" idField="${sheetPageName}linkMeetingAcc" appCode="businessoperation" alt="allowBlank:true"/>
		                </td>
		            </tr>
		            <%}} %>
		 <%if(taskName.equals("report")){ 
	          if(operateType.equals("94")){%>
	          	     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="hold" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="94" />	              
		         
	                <tr>
		                 <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.linkIsSuccess"/>*</td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIsSuccess" id="${sheetPageName}linkIsSuccess" 
            	      initDicId="1010513"  alt="allowBlank:false" onchange="ifSuccess(this.value);" defaultValue="${sheetLink.linkIsSuccess}" styleClass="select-class"/>
			        </td>	                
		            </tr>
	                <tr>
  						<td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkTGReportAcc"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" name="sheetLink" scope="request" property="linkTGReportAcc" idField="${sheetPageName}linkTGReportAcc" appCode="businessoperation" alt="allowBlank:true"/>
		                </td>
		            </tr>
		            <%}} %>
		            
		            
		            
		     <%if(operateType.equals("4")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
			
			<%if(taskName.equals("operate")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject" />
			  <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="90" />
			<%} %>
			<%if(taskName.equals("refine")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="operate" />
			<%} %>	
     		<%if(taskName.equals("prepare")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="refine" />
			<%} %>	
			<%if(taskName.equals("report")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="prepare" />
			<%} %>				
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>
		       
         <%if(taskName.equals("hold")) {%>  
     	  <%if(operateType.equals("7")){ %>
     	
     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7" />
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
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
		        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}" initDicId="10303" styleClass="select" alt="allowBlank:false"/>
		    </td>
		  </tr>		  
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" alt="allowBlank:false" id="${sheetPageName}endResult"  class="textarea max">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>					       			     	
     	    <%} }%>
     	
      <% if(taskName.equals("cc")){%>    
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
          <%} %> 
         <%if(taskName.equals("operate")||taskName.equals("refine")||taskName.equals("report")||taskName.equals("prepare")) {%>      
         
         <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} }%>
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
       