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
 String roleName="";
 String ifInvoke=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
 System.out.println("=====ifInvoke======"+ifInvoke);
 %> 

<%@ include file="/WEB-INF/pages/wfworksheet/emergencydrill/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
     <input type="hidden" name="${sheetPageName}beanId" value="iEmergencyDrillMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillLink"/>
        <c:if test="${taskName != 'HoldTask'||taskName != 'ConfirmHoldTask'||taskName != 'TempSaveTask'}">
       <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
        </c:if>

			  <%if(taskName.equals("MakeProgramTask")){ 
	             if(operateType.equals("91")|| operateType.equals("11")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditProgramTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	            
			      <tr>
		             <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkEmergencyDrillNote"/>*</td>
		              <td colspan="3"> 	
    				 <textarea class="textarea max" name="${sheetPageName}linkEmergencyDrillNote" id="${sheetPageName}linkEmergencyDrillNote" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入应急演练方案说明，最多输入125字'">${sheetLink.linkEmergencyDrillNote}</textarea>
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
  						<td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkEmergencyDrill"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment name="sheetLink" property="linkEmergencyDrill" idList=""
            			  scope="request" idField="${sheetPageName}linkEmergencyDrill" appCode="emergencydrill" 
            			  alt="allowBlank:false"/> 
		                </td>
		            </tr>
		          
		          <%}} %>
		          
		          
		            
		     <%if(taskName.equals("AuditProgramTask")){ 
	          if(operateType.equals("92")|| operateType.equals("55")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ImplementProgramTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	               	 
	                <tr>
		                 <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkAuditOpinion"/>*</td>
		                <td  colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkAuditOpinion" id="${sheetPageName}linkAuditOpinion" 
            	      initDicId="1011601"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass(this.value);" defaultValue="${sheetLink.linkAuditOpinion}"/>
			        </td>	                
		                
		            </tr>
		            
				            
			      <tr>
		            <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkAuditResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
                    </td>
		          </tr>
		     	
		            <%}}%>
		            
		   <%if(taskName.equals("ImplementProgramTask")){ 
	          if(operateType.equals("93")|| operateType.equals("11")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SubmitSummaryTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	               	 
	                
		            
				            
			      <tr>
		            <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkImplementResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkImplementResult" id="${sheetPageName}linkImplementResult" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入实施演练结果，最多输入125字'">${sheetLink.linkImplementResult}</textarea>
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
  						<td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkDrillReport"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment name="sheetLink" property="linkDrillReport" idList=""
            			  scope="request" idField="${sheetPageName}linkDrillReport" appCode="emergencydrill" 
            			  alt="allowBlank:false"/> 
		                </td>
		            </tr>
		     	
		            <%}}%>
      			  <%if(taskName.equals("SubmitSummaryTask")){ 
	         		 if(operateType.equals("94")|| operateType.equals("11")){%>
	           		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditSummaryTask" />
	           		 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	               	 
	                
		            
				            
				      <tr>
			            <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkImplementSummary"/>*</td>
			              <td colspan="3"> 	
	    				  <textarea class="textarea max" name="${sheetPageName}linkImplementSummary" id="${sheetPageName}linkImplementSummary" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入实施演练结果，最多输入125字'">${sheetLink.linkImplementSummary}</textarea>
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
	  						<td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkDrillSummaryReport"/>*</td>
			   				 <td   colspan='3'>
			   				 <eoms:attachment name="sheetLink" property="linkDrillSummaryReport" idList=""
	            			  scope="request" idField="${sheetPageName}linkDrillSummaryReport" appCode="emergencydrill" 
	            			  alt="allowBlank:false"/> 
			                </td>
			            </tr>
		     	
		            <%}}%>
		              <%if(taskName.equals("AuditSummaryTask")){ 
	         		 if(operateType.equals("95")|| operateType.equals("55")){%>
	           		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AmendmentProgramTask" />
	           		 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	               	 
	                  <tr>
		                 <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkAuditOpinion"/>*</td>
		                <td  colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkAuditOpinion" id="${sheetPageName}linkAuditOpinion" 
            	      initDicId="1011601"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass3(this.value);" defaultValue="${sheetLink.linkAuditOpinion}"/>
			        </td>
			        </tr>
			        
				  <tr>
		            <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkAuditResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
                    </td>
		          </tr>
		          
		          <tbody id="ifchangeplan">
		          <tr>
		     	<td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkIsAmendmentDrill"/>*</td>
		                <td  colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIsAmendmentDrill" id="${sheetPageName}linkIsAmendmentDrill" 
            	      initDicId="1011605"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass4(this.value);" defaultValue="${sheetLink.linkIsAmendmentDrill}"/>
			        </td>	                	                
		                
		            </tr>
		           </tbody> 
		            <%}}%>
		            
		               <%if(taskName.equals("AmendmentProgramTask")){ 
	         		 if(operateType.equals("96")|| operateType.equals("11")){%>
	           		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
	           		 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	               	 
	               <tr>
		            <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkAmendmentDrillNote"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAmendmentDrillNote" id="${sheetPageName}linkAmendmentDrillNote" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入修订预案说明，最多输入125字'">${sheetLink.linkAmendmentDrillNote}</textarea>
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
	  						<td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.linkEmergencyPreparedness"/>*</td>
			   				 <td  colspan='3'>
			   				 <eoms:attachment name="sheetLink" property="linkEmergencyPreparedness" idList=""
	            			  scope="request" idField="${sheetPageName}linkEmergencyPreparedness" appCode="emergencydrill" 
	            			  alt="allowBlank:false"/> 
			                </td>
			            </tr>
		     	
		            <%}}%>
           
           
           
			
		            
			
			
         
		     <%if(operateType.equals("4")){ %>
				<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
				<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
				<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
			
			<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${fPreTaskName == 'DraftTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>				
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>
		       
         <%if(taskName.equals("HoldTask")) {%>  	
			<%if(operateType.equals("18")){ %>
     	    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${sheetMain.sendRoleId}" />
	     	<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${sheetMain.sendRoleId}" />
	     	<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="subrole" />
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
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
		      <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:255,vtext:'请最多输入125字'" id="${sheetPageName}endResult"  class="textarea max">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>	
		  <%}else if(operateType.equals("102")){%>
     	    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
	        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />	
	    	<tr>
		       <td class="label">
		        <bean:message bundle="sheet" key="linkForm.remark" />*
			    </td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
			        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" alt="width:'500px'">${sheetLink.remark}</textarea>
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
		        alt="allowBlank:false,maxLength:2000,vtext:'请填入信息，最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
          <%} %>     
         
         <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />	
							
    	<%-- <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px,vtext:'请最多输入1000字''">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	--%>
		
		<%}%>
  </table>
  
  
   	 <%if(taskName.equals("cc")) {%>	
		<fieldset id="link4">
	<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
		</fieldset>					
	 <%} %>   
	  <%if(taskName.equals("HoldTask")) {
	  	   if(operateType.equals("102")){ %>	
		<fieldset id="link4">
		 <legend>
				 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				 <c:if test="${fPreTaskName == 'AuditSummaryTask'}">
				 <bean:message bundle="emergencydrill" key="role.admin"/>
				 </c:if>
				 <c:if test="${fPreTaskName == 'AmendmentProgramTask'}">
				 <bean:message bundle="emergencydrill" key="role.organizer"/>	
				 </c:if>
				<span id="roleName"></span>		 
		 </legend>		
       </fieldset>		
	 <%}} %>	 
	 	

	 
	 <%if(taskName.equals("MakeProgramTask")) {
	  	   if(operateType.equals("91")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="emergencydrill" key="emergencydrill.linkAuditObject"/>:
				 <bean:message bundle="emergencydrill" key="role.admin"/>		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="365" flowId="061" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%}} %>
	 
	  <%if(taskName.equals("AuditProgramTask")) {
	  	   if(operateType.equals("92")){ %>	
		<fieldset id="link4">
		 <legend>
				 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"></span>		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="367" flowId="061" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%}} %>
	 
	  <%if(taskName.equals("ImplementProgramTask")) {
	  	   if(operateType.equals("93")){ %>	
		<fieldset id="link4">
		 <legend>
				 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				 <bean:message bundle="emergencydrill" key="role.organizer"/>		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="366" flowId="061" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%}} %>
	 
	 <%if(taskName.equals("SubmitSummaryTask")) {
	  	   if(operateType.equals("94")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="emergencydrill" key="emergencydrill.linkAuditObject"/>:
				 <bean:message bundle="emergencydrill" key="role.admin"/>		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="365" flowId="061" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%}} %>
	 
	  <%if(taskName.equals("AuditSummaryTask")) {
	  	   if(operateType.equals("95")){ %>	
		<fieldset id="link4">
		 <legend>
				 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				 <span id="roleName"></span>		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="365" flowId="061" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		    <eoms:chooser id="sendRole1" type="role" roleId="366" flowId="061" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%}} %>
	 
	 <%if(taskName.equals("AmendmentProgramTask")) {
	  	   if(operateType.equals("96")){ %>	
		<fieldset id="link4">
		 <legend>
				 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				 <bean:message bundle="emergencydrill" key="role.admin"/>
				 
					 
		 </legend>		
</fieldset>		
	 <%}} %>
	 
	
	 	
<script type="text/javascript">
  function ifAuditPass(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160101"){
		//审核通过
		chooser_sendRole.enable();
		$('${sheetPageName}phaseId').value='ImplementProgramTask';
		$('${sheetPageName}operateType').value='921';
		$('roleName').innerHTML="应急演练工作组";
	} else if(input=="101160102"){
		//审核不通过  
		chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='MakeProgramTask';
		$('${sheetPageName}operateType').value='922';
		$('roleName').innerHTML="应急演练组织人";
	} else{
	   chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	}
	}
	function ifAuditPass3(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160101"){
		//审核通过
		 chooser_sendRole1.disable();
	     chooser_sendRole.disable();
	     eoms.form.enableArea('ifchangeplan');		     
	} else if(input=="101160102"){
	
		//审核不通过
		 chooser_sendRole1.disable();
	     chooser_sendRole.disable();
	     document.all.${sheetPageName}linkIsAmendmentDrill.value='';
		 eoms.form.disableArea('ifchangeplan',true);
		$('${sheetPageName}phaseId').value='SubmitSummaryTask';
		$('${sheetPageName}operateType').value='952';
		$('roleName').innerHTML="应急演练组织人";
	}else{

	     chooser_sendRole.disable();
	     chooser_sendRole1.disable();
		 document.all.${sheetPageName}linkIsAmendmentDrill.value='';	     
		 eoms.form.disableArea('ifchangeplan',true);
		    
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	}
	}
	function ifAuditPass4(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160501"){	
	     chooser_sendRole1.enable();
	     chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='AmendmentProgramTask';
		$('${sheetPageName}operateType').value='953';
		$('roleName').innerHTML="应急演练组织人";	     
	     	     
	} else if(input=="101160502"){
		  	
	     chooser_sendRole.enable();
	     chooser_sendRole1.disable();
		$('${sheetPageName}phaseId').value='HoldTask';
		$('${sheetPageName}operateType').value='951';
		$('roleName').innerHTML="应急预案管理员";
	}else{
	     chooser_sendRole1.disable();
	     chooser_sendRole.disable();	    
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	}
	}	
    var frm = document.forms[0];
    var temp1 = frm.linkAuditOpinion ? frm.linkAuditOpinion.value : '';
    var temp3 = frm.linkIsAmendmentDrill ? frm.linkIsAmendmentDrill.value : '';
	if("${taskName}"=="AuditProgramTask"&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4'&&temp1 != ''){
	ifAuditPass(temp1);
	}
	if("${taskName}"=="AuditSummaryTask"&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4'){
	ifAuditPass3(temp1);
	}
	if("${taskName}"=="AuditSummaryTask"&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4'&&temp3 != ''){
	ifAuditPass4(temp3);
	}

	function openwin() {
	
	var url="${app}/sheet/securitydeal/securitydeal.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iSecurityEvaluateMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism";
	window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
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
</script>	 
