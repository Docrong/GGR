<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.safetyproblem.model.SafetyProblemLink  safetyproblemLink = (com.boco.eoms.sheet.safetyproblem.model.SafetyProblemLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(safetyproblemLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(safetyproblemLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		
   		return false;
   	}
   	form.action = "${app}/sheet/safetyproblem/safetyproblem.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/safetyproblem/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/safetyproblem.do" styleId="theform">       
	<br/>
               <input type="hidden" name="${sheetPageName}beanId" value="iSafetyProblemMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.safetyproblem.model.safetyproblemMain"/>	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.safetyproblem.model.safetyproblemLink"/> 
     <table class="formTable">
     		<caption><bean:message bundle="safetyproblem" key="safetyproblem.header"/></caption>
		<tr>
           <td class="label" >
           		<bean:message bundle="sheet" key="input.templateName" />
           </td>
           <td colspan="3">
           		<input type="text" name="templateName" class="text max" id="templateName" value="${sheetLink.templateName}"/>
           </td>         
       </tr>
     
        <%if(taskName.equals("TaskCreateAuditHumTask")){ %>
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />           
           <%if(operateType.equals("201")){ %>
         	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask" />
         	 <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040101" />
         	 <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkAuditResult"/></td>
		            <td colspan="3"> 	
			           ${eoms:a2u('通过')}   
                    </td>
		     </tr>
			 <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>
           <%}else if(operateType.equals("202")){ %>
         	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
         	<input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040102" />
         	 <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkAuditResult"/></td>
		            <td colspan="3"> 	
		              ${eoms:a2u('不通过')}
                    </td>
		     </tr>
			  <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>
         	 
           <%}%>
         
         <%}else if(taskName.equals("TaskCompleteAuditHumTask")){ %>
 		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />                   
            <%if(operateType.equals("208")){ %>
             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
             <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040101" />
         	 <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkAuditResult"/></td>
		            <td colspan="3"> 	
			            ${eoms:a2u('通过')}  
                    </td>
		     </tr>
			 <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>             
             
           <%}else if(operateType.equals("209")){ %>
             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
         	 <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040102" />
         	 <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkAuditResult"/></td>
		            <td colspan="3"> 	
		              ${eoms:a2u('不通过')}
                    </td>
		     </tr>
			  <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>             
             
           <%}%>
         
         <%}else if(taskName.equals("ExcuteHumTask")){%>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 		           
      	    <% if(operateType.equals("205")){ %>
        	  		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
        	  		 <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkTaskComplete"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入完成情况，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
        	    <%}else if(operateType.equals("206")){ %>
        	        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCompleteAuditHumTask" />
        	        <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.linkTaskComplete"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入完成情况，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
        	    <%}else if(operateType.equals("10")){ %>
        	    	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
        	       <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.fenpairesion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入分派理由，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
        	    <% }else if(operateType.equals("8")){ %>
         			 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask" />
         			  <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.yijiaoresion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入移交理由，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
                <%}%>       	        	
         <%}else if( taskName.equals("AffirmHumTask") ){%>
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />          
         	 <%if(operateType.equals("212")){ %>
         		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
         		  <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.advice"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入建议，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
         		 
            <%}else if(operateType.equals("211")){ %>
         		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Receive" />
         		  <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.advice"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入建议，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>         		 
            <%}%>
          <%}else if( taskName.equals("HoldHumTask")){%>
         	 <%if(operateType.equals("18")){ %>
         	 	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
      			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
      			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverHumTask" />
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
			      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
			      alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入归档内容，最多输入1000汉字')}'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>	        			 
         			 
         			 
              <%}%>
          <%}%>
        <% if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:true,width:500,maxLength:2000,vtext:'${eoms:a2u('请最多输入1000汉字')}'" alt="width:'500px'">${sheetlink.remark}</textarea>
		  </td>
		</tr>  
     <%} %> 
     <%if(taskName.equals("TaskCreateAuditHumTask")||taskName.equals("ExcuteHumTask")||taskName.equals("TaskCompleteAuditHumTask")||taskName.equals("AffirmHumTask")) {%> 
      <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'${eoms:a2u('最多输入100汉字')}'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 	
		
		<%} }%>
		
		<%if(operateType.equals("4")){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		  <c:choose> 
		  	    <c:when test="${taskName=='TaskCreateAuditHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>
				<c:when test="${taskName=='ExcuteHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
				</c:when>
				<c:when test="${taskName=='TaskCompleteAuditHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
				</c:when>				
			</c:choose>				
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,vtext:'${eoms:a2u('请最多输入1000字')}'" alt="width:'500px'">${sheetLink.remark}</textarea>
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
