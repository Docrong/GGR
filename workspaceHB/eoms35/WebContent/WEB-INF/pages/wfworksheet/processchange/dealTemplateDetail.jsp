<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.processchange.model.ProcessChangeLink  processchangeLink = (com.boco.eoms.sheet.processchange.model.ProcessChangeLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(processchangeLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(processchangeLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/processchange/processchange.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/processchange/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/processchange.do" styleId="theform">       
	<br/>
        
        
               <input type="hidden" name="${sheetPageName}beanId" value="iProcessChangeMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.processchange.model.ProcessChangeMain"/>	<!--main表Model对象类名-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.processchange.model.ProcessChangeLink"/> <!--link表Model对象类名-->
               <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->
        
     <table class="formTable">
     		<caption><bean:message bundle="processchange" key="processchange.header"/></caption>
		<tr>
           <td class="label" >
           		<bean:message bundle="sheet" key="input.templateName" />
           </td>
           <td colspan="3">
           		<input type="text" name="templateName" class="text max" id="templateName" value="${sheetLink.templateName}"/>
           </td>         
       </tr>
			  <%if(taskName.equals("EvaluateTask")){ 
	             if(operateType.equals("91")|| operateType.equals("11")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
			      
	               	 <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="processchange" key="processchange.linkProcessType"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkProcessType" id="${sheetPageName}linkProcessType" 
            	      initDicId="1010701" sub="${sheetPageName}linkProcess" defaultValue="${sheetLink.linkProcessType}" alt="allowBlank:false" styleClass="select-class"/>
			        </td>	                
		                 <td  class="label"><bean:message bundle="processchange" key="processchange.linkProcess"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkProcess" id="${sheetPageName}linkProcess" 
            	      initDicId="${sheetLink.linkProcessType}"  defaultValue="${sheetLink.linkProcess}" alt="allowBlank:false" styleClass="select-class"/>
			        </td>	                
		            </tr>
		            <td  class="label"><bean:message bundle="processchange" key="processchange.linkFrameDesc"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkFrameDesc" id="${sheetPageName}linkFrameDesc" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入流程变更方案框架概述，最多输入125字'">${sheetLink.linkFrameDesc}</textarea>
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
  						<td class="label"><bean:message bundle="processchange" key="processchange.linkSchemeFrame"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkSchemeFrame" name="sheetLink" property="linkSchemeFrame" appCode="processchange" alt="allowBlank:false"/>
		                </td>
		            </tr>				  	            	             
		          <%}} %>		          		          
		            
		     <%if(taskName.equals("MakeTask")){ 
	          if(operateType.equals("92")|| operateType.equals("11")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		            </tr>
		            <td  class="label"><bean:message bundle="processchange" key="processchange.linkSchemeDesc"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkSchemeDesc" id="${sheetPageName}linkSchemeDesc" alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入流程变更方案概述，最多输入125字'">${sheetLink.linkSchemeDesc}</textarea>
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
  						<td class="label"><bean:message bundle="processchange" key="processchange.linkChangeScheme"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkChangeScheme" name="sheetLink" property="linkChangeScheme" appCode="processchange" alt="allowBlank:true"/>
		                </td>
		            </tr>
	                <tr>
  						<td class="label"><bean:message bundle="processchange" key="processchange.linkITChangeScheme"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkITChangeScheme" name="sheetLink" property="linkITChangeScheme" appCode="processchange" alt="allowBlank:true"/>
		                </td>
		            </tr>		            	            				  
		            <%}}%>

		     <%if(taskName.equals("AuditTask")){ 
	          if(operateType.equals("93")|| operateType.equals("55")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReleaseTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	               	 <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				  <tr>
		             <td  class="label"><bean:message bundle="processchange" key="processchange.linkAuditResult"/>*</td>
		             <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" 
            	      initDicId="1010702"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass(this.value);" defaultValue="${sheetLink.linkAuditResult}" />
			        </td>	                                                                          
		            </tr>
			      <tr>
		             <td  class="label"><bean:message bundle="processchange" key="processchange.linkAuditDesc"/></td>
		              <td colspan="3"> 	
    				 <textarea class="textarea max" name="${sheetPageName}linkAuditDesc" id="${sheetPageName}linkAuditDesc" alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入审批意见，最多输入125字'">${sheetLink.linkAuditDesc}</textarea>
                    </td>
		          </tr>				  	            				  
		            <%}}%>	
		            
		     <%if(taskName.equals("ReleaseTask")){ 
	          if(operateType.equals("94")|| operateType.equals("11")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OperateTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	               	 <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				 <%if(!operateType.equals("11")) {%>
				  <tr>
		             <td  class="label"><bean:message bundle="processchange" key="processchange.linkIfInvoke"/>*</td>
		             <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIfInvoke" id="${sheetPageName}linkIfInvoke" 
            	      initDicId="1010703"  alt="allowBlank:false" styleClass="select-class"  defaultValue="${sheetLink.linkIfInvoke}" />
			        </td>	                                                                          
		            </tr>
		            <%} %>
			      <tr>
		             <td  class="label"><bean:message bundle="processchange" key="processchange.linkReleaseDesc"/></td>
		              <td colspan="3"> 	
    				 <textarea class="textarea max" name="${sheetPageName}linkReleaseDesc" id="${sheetPageName}linkReleaseDesc" alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入发布流程变更概述，最多输入125字'">${sheetLink.linkReleaseDesc}</textarea>
                    </td>
		          </tr>		            	            				  
		            <%}}%>	
		            
		     <%if(taskName.equals("OperateTask")){ 
	          if(operateType.equals("95")|| operateType.equals("11")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	               	 <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				  <tr>
		             <td  class="label"><bean:message bundle="processchange" key="processchange.linkOptimizeImpact"/></td>
		             <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkOptimizeImpact" id="${sheetPageName}linkOptimizeImpact" 
            	      initDicId="1010704"  alt="allowBlank:true" styleClass="select-class"  defaultValue="${sheetLink.linkOptimizeImpact}" />
			        </td>	                                                                          
		            </tr>
			      <tr>
		             <td  class="label"><bean:message bundle="processchange" key="processchange.linkEvaluateDesc"/></td>
		              <td colspan="3"> 	
    				 <textarea class="textarea max" name="${sheetPageName}linkEvaluateDesc" id="${sheetPageName}linkEvaluateDesc" alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入实施后评估概述，最多输入125字'">${sheetLink.linkEvaluateDesc}</textarea>
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
  						<td class="label"><bean:message bundle="processchange" key="processchange.linkEvaluateAttach"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkEvaluateAttach" name="sheetLink" property="linkEvaluateAttach" appCode="processchange" alt="allowBlank:false"/>
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
     	    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OperateTask" />
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
       