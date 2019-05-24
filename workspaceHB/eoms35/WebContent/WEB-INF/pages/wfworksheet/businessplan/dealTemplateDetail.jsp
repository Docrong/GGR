<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.businessplan.model.BusinessPlanLink  businessPlanLink = (com.boco.eoms.sheet.businessplan.model.BusinessPlanLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(businessPlanLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(businessPlanLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/businessplan/businessplan.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/businessplan/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/businessplan.do" styleId="theform">       
	<br/>
        
        
               <input type="hidden" name="${sheetPageName}beanId" value="iBusinessPlanMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessplan.model.BusinessPlanMain"/>	<!--main表Model对象类名-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessplan.model.BusinessPlanLink"/> <!--link表Model对象类名-->
               <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->
        
     <table class="listTable">
<%if(taskName.equals("analyse")){ 
	     if(operateType.equals("91")){%>
	     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="audit" />
	     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="91" />
			      <tr>
		            <td  class="label"><bean:message bundle="businessplan" key="businessplan.linkAnalyse"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAnalyse" id="${sheetPageName}linkAnalyse" alt="width:500,allowBlank:true">${sheetLink.linkAnalyse}</textarea>
                    </td>
		          </tr>
	                <tr>
  						<td class="label"><bean:message bundle="businessplan" key="businessplan.linkreport"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment name="sheetLink" idList=""  scope="request" property="linkreport" idField="${sheetPageName}linkreport" scope="request" appCode="businessplan" alt="allowBlank:false"/>
		                </td>
		            </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="businessplan" key="businessplan.linkIsKx"/></td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIsKx" id="${sheetPageName}linkIsKx" 
            	      initDicId="10306"  alt="allowBlank:true" styleClass="select-class" defaultValue="${sheetLink.linkIsKx}"/>
			        </td>	                
		            </tr>
		  <%}} %>
		  
		  <%if(taskName.equals("audit")){ 
	          if(operateType.equals("92")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="appraisal" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="92" />
	                <tr>
		                 <td  class="label"><bean:message bundle="businessplan" key="businessplan.linkAuditResult"/></td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" 
            	      initDicId="10301"  alt="allowBlank:true" styleClass="select-class" onchange="ifAuditPass(this.value);" defaultValue="${sheetLink.linkAuditResult}" />
			        </td>	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessplan" key="businessplan.linkAuditDesc"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditDesc" id="${sheetPageName}linkAuditDesc" alt="width:500,allowBlank:true">${sheetLink.linkAuditDesc}</textarea>
                    </td>
		          </tr>
		          <%}} %>
		      <%if(taskName.equals("standard")){ 
	          if(operateType.equals("94")){%>
	          	     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="hold" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="94" />
	                <tr>
		                 <td  class="label"><bean:message bundle="businessplan" key="businessplan.linkAuditResult"/></td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" 
            	      initDicId="10301"  alt="allowBlank:true" styleClass="select-class" onchange="ifAuditPass(this.value);" defaultValue="${sheetLink.linkAuditResult}" />
			        </td>	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessplan" key="businessplan.linkAuditDesc"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditDesc" id="${sheetPageName}linkAuditDesc" alt="width:500,allowBlank:true">${sheetLink.linkAuditDesc}</textarea>
                    </td>
		          </tr>
		          <%}} %>
		          
		          
	     <%if(taskName.equals("appraisal")){ 
	          if(operateType.equals("93")){%>	 
	          	  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="standard" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="93" />      
			      <tr>
		            <td  class="label"><bean:message bundle="businessplan" key="businessplan.linkOpion"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkOpion" id="${sheetPageName}linkOpion" alt="width:500,allowBlank:true">${sheetLink.linkOpion}</textarea>
                    </td>
		          </tr>
	                <tr>
  						<td class="label"><bean:message bundle="businessplan" key="businessplan.linkOpionReport"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment name="sheetLink"  property="linkOpionReport"  idList="" idField="${sheetPageName}linkOpionReport"  
                    property="linkOpionReport"  scope="request" appCode="businessplan" alt="allowBlank:false"/>
		                </td>
		            </tr>
		            
		       <%}} %>
		       
		<%if(operateType.equals("4")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
			
			<%if(taskName.equals("analyse")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject" />
			  <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="90" />
			<%} %>
			<%if(taskName.equals("audit")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="analyse" />
			<%} %>	
     		<%if(taskName.equals("appraisal")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="audit" />
			<%} %>	
			<%if(taskName.equals("standard")){ %>
			  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="appraisal" />
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
	        <bean:message bundle="sheet" key="linkForm.remark" />
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
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"  class="textarea max">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>					       			     	
     	    <%} }%>
     	
      <% if(taskName.equals("cc")){%>    
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
         <%if(taskName.equals("analyse")||taskName.equals("audit")||taskName.equals("standard")||taskName.equals("appraisal")) {%>      
         
         <%if(operateType.equals("61")){ %>
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
       