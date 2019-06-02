<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>


<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false">
	    <bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>
<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.greatevent.model.GreatEventLink">  
       <%         
        jNum += 1;       
        String divName ="buzhou"+jNum;
       %>
      <div class="history-item"><!-- add space to hack ie-->&nbsp;
        <div class="history-item-title">
         <%=jNum%>.
         <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
  	     <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />
  	      <bean:message bundle="sheet" key="task.operateName"/>:	 
  	      <eoms:dict key="dict-sheet-greatevent" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	      <bean:message bundle="greatevent" key="greatevent.operateType"/>:
  	      <eoms:dict key="dict-sheet-greatevent" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
		 <img class="switchIcon" src="${app}/images/icons/closed.gif" align="absmiddle"/>
	   </div>
       <div class="history-item-content hide">
         <c:if test="${taskName==baselink.activeTemplateId && ifWaitForSubTask=='false'&&(baselink.operateType=='11'||baselink.operateType=='55')}">
         <input type="checkbox" name="${sheetPageName}checkuse" id="${baselink.id}" value="${baselink.id}" onclick="copy(this.value);" />
         <bean:message bundle="sheet" key="common.copy"/>	     
  	     </c:if>        
  		<table class="history-item-table" width="100%" cellpadding="0" cellspacing="0">
  				<tr>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateUserName"/>
  				  </td>
  				  <td class="column-content">
  				     <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />&nbsp;
                  </td>
                  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateDeptName"/>
  				  </td>
  				  <td class="column-content">
  				     <eoms:id2nameDB id="${baselink.operateDeptId}" beanId="tawSystemDeptDao" />&nbsp;
				  </td>
  				</tr>
  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" />
  				      <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				</tr>
  				 <tr>                
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operaterContact"/> 
  				  </td>
  				  <%if(baselink.getOperateType().intValue()==22||baselink.getOperateType().intValue()==-10||baselink.getOperateType().intValue()==0 || baselink.getOperateType().intValue() == 3 || baselink.getOperateType().intValue() == -12){%> 	
	  				  <td class="column-content">
	  				  		${sheetMain.sendContact}
	  			      </td>
  			      <%} else {%>
  			      		<td class="column-content">
	  				  		${baselink.operaterContact}
	  			      </td>
  			      <% }%>
  			     
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
	  				  </td>
	  				  <td class="column-content">
		  				    <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
  				   			<eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />&nbsp;&nbsp;
	                  </td>
	             </tr>

               <%if(baselink.getNodeAcceptLimit()!= null && ! baselink.getNodeAcceptLimit().equals("")
		  		   &&baselink.getNodeCompleteLimit()!=null && ! baselink.getNodeCompleteLimit().equals("")) {%>
				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
  				  </td>
  				  <td class="column-content">
  				       <bean:write name="baselink" property="nodeAcceptLimit" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.completeLimit"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="nodeCompleteLimit" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				</tr>
                <%} %>
               
                
		  	  <!-- 方案制定 -->
		  	 <%if(baselink.getActiveTemplateId()!=null&& baselink.getActiveTemplateId().equals("MakeTask")&&(baselink.getOperateType().intValue()==90|| baselink.getOperateType().intValue()==11)){%> 	
		  		 	
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkResReadyResult"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <bean:write name="baselink" property="linkResReadyResult" scope="page"/>
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkGreatSecurityProgram"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                                <eoms:attachment name="baselink" property="linkGreatSecurityProgram" 
		                     scope="page" idField="linkGreatSecurityProgram" appCode="greatevent" 
		                     viewFlag="Y"/> 
		                  </td>                  
		  			</tr>		  		 
		  	 <%}%>
		  	 <!-- 方案审核 -->
		  	 <%if(baselink.getActiveTemplateId()!=null &&baselink.getActiveTemplateId().equals("AuditTask")&& (baselink.getOperateType().intValue()==911 || baselink.getOperateType().intValue()==55 || baselink.getOperateType().intValue()==912)){%> 
		  		 	<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <eoms:id2nameDB id="${baselink.linkAuditAdvice}" beanId="ItawSystemDictTypeDao"/>
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <bean:write name="baselink" property="linkAuditResult" scope="page"/>
		                  </td>                  
		  			</tr>		  		 
		  	 <%}%>
		  	 <!-- 方案审批 -->
			<%if(baselink.getActiveTemplateId()!=null &&baselink.getActiveTemplateId().equals("ApprovalTask")&& (baselink.getOperateType().intValue()==921 || baselink.getOperateType().intValue()==55 || baselink.getOperateType().intValue()==922)){%>
		  		 	<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkPermisAdvice"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <eoms:id2nameDB id="${baselink.linkPermisAdvice}" beanId="ItawSystemDictTypeDao"/>
		                  </td>
		                                   
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkPermisResult"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <bean:write name="baselink" property="linkPermisResult" scope="page"/>
		                  </td>                  
		  			</tr>		  		 
		  	 <%}%>
			 <!-- 方案实施,提交结束申请 -->
			<%if(baselink.getActiveTemplateId()!=null &&baselink.getActiveTemplateId().equals("PerformTask")&& (baselink.getOperateType().intValue()==93|| baselink.getOperateType().intValue()==11|| baselink.getOperateType().intValue()==111)){%>
		  		 	
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkSencePerformSummary"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                              <bean:write name="baselink" property="linkSencePerformSummary" scope="page"/>
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkSencePerformReport"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                             <eoms:attachment name="baselink" property="linkSencePerformReport" 
		            scope="page" idField="linkSencePerformReport" appCode="greatevent" 
		             viewFlag="Y"/> 
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkIfStartChangeProcess"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                             <eoms:id2nameDB id="${baselink.linkIfStartChangeProcess}" beanId="ItawSystemDictTypeDao"/> 
		                  </td>                  
		  			</tr>		  		 
		  	 <%}%>
		  	 
		  	 <!-- 审核结束申请 -->
			<%if(baselink.getActiveTemplateId()!=null &&baselink.getActiveTemplateId().equals("AuditEndTask")&& (baselink.getOperateType().intValue()==941 || baselink.getOperateType().intValue()==55 || baselink.getOperateType().intValue()==942)){%>
		  		 	<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <eoms:id2nameDB id="${baselink.linkAuditAdvice}" beanId="ItawSystemDictTypeDao"/>
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <bean:write name="baselink" property="linkAuditResult" scope="page"/>
		                  </td>                  
		  			</tr>		  		 
		  	 <%}%>
		  	 
			<!-- 完成保障报告，提交审核-->
			<%if(baselink.getActiveTemplateId()!=null &&baselink.getActiveTemplateId().equals("AssessmentTask")&& (baselink.getOperateType().intValue()==95|| baselink.getOperateType().intValue()==11)){%>
		  		 	
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkSenceSecuritySummary"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                             <eoms:attachment name="baselink" property="linkSenceSecuritySummary" 
		                     scope="page" idField="linkSenceSecuritySummary" appCode="greatevent" 
		                     viewFlag="Y"/> 
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkSenceSecurityReport"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                             <eoms:attachment name="baselink" property="linkSenceSecurityReport" 
		                     scope="page" idField="linkSenceSecurityReport" appCode="greatevent" 
		                     viewFlag="Y"/> 
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkAssessReport"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                             <eoms:attachment name="baselink" property="linkAssessReport" 
		                     scope="page" idField="linkAssessReport" appCode="greatevent" 
		                     viewFlag="Y"/> 
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkIfStartChangeProcess"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                             <eoms:id2nameDB id="${baselink.linkIfStartChangeProcess}" beanId="ItawSystemDictTypeDao"/> 
		                  </td>                  
		  			</tr>
		  	 <%}%>
		  	 
		  	<!-- 审核保障报告-->
			<%if(baselink.getActiveTemplateId()!=null &&baselink.getActiveTemplateId().equals("AuditReportTask")&& (baselink.getOperateType().intValue()==961 ||baselink.getOperateType().intValue()==962 ||baselink.getOperateType().intValue()==963 || baselink.getOperateType().intValue()==55)){%>
		  		 	<tr>  				
		  				 <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <eoms:id2nameDB id="${baselink.linkAuditAdvice}" beanId="ItawSystemDictTypeDao"/>
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <bean:write name="baselink" property="linkAuditResult" scope="page"/>
		                  </td>                  
		  			</tr>
		  			 <%if(baselink.getLinkAuditAdvice()!=null && !baselink.getLinkAuditAdvice().equals("")){ %>
		  			<tr>  				
		  				 <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkIfModifyPlans"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                               <eoms:id2nameDB id="${baselink.linkIfModifyPlans}" beanId="ItawSystemDictTypeDao"/>
		                  </td>                  
		  			</tr>
		  			<%} %>		  		 
		  	 <%}%>
		  	<!-- 完成预案修订，提交预案-->
			<%if(baselink.getActiveTemplateId()!=null &&baselink.getActiveTemplateId().equals("ModifyTask")&& (baselink.getOperateType().intValue()==97|| baselink.getOperateType().intValue()==11)){%>
		  		 	<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkAmendPlanHelp"/>
		  				  </td>
		  				  <td class="column-content" colspan=3>
                               <bean:write name="baselink" property="linkAmendPlanHelp" scope="page"/>
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="greatevent" key="greatevent.linkEmergencyPlan"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
                             <eoms:attachment name="baselink" property="linkEmergencyPlan" 
		                     scope="page" idField="linkEmergencyPlan" appCode="greatevent" 
		                     viewFlag="Y"/> 
		                  </td>   
		                                
		  			</tr>		  		 
		  	 <%}%>
			 <!-- 归档 -->
		  	 <%if(baselink.getActiveTemplateId()!=null&& baselink.getActiveTemplateId().equals("HoldTask")&&(baselink.getOperateType().intValue()==18)){%> 	 
          
		  	 <tr>
		            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/></td>
		            <td colspan="3"> 	
    				 <eoms:id2nameDB id="${sheetMain.holdStatisfied}" beanId="ItawSystemDictTypeDao"/>
                    </td>
             </tr>
             <tr>		
                    <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
		            <td colspan='3'>
                      <pre><bean:write name="sheetMain" property="endResult" scope="request"/></pre>
		            </td>
             </tr>  
		  	 <%}%>
			 <!-- 移交、转审 -->
		   <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 8||baselink.getOperateType().intValue() == 88)){%> 
			 
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.transmitReason"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
	  			      </td>	  				                  
  				</tr>
			  		 
		 <%} %>	
		   <!-- 分派、回复通过、回复不通过 -->		 
		   <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() ==-15 || baselink.getOperateType().intValue() == 6||baselink.getOperateType().intValue() == 7||baselink.getOperateType().intValue() == 10||baselink.getOperateType().intValue() == 30)){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			  		 
			 <%}%>		
			
		   <!-- 抄送、阶段回复、阶段通知 --> 	 
           <%if(baselink.getActiveTemplateId()!=null && (baselink.getOperateType().intValue() == -11 || baselink.getOperateType().intValue() == 9 || baselink.getOperateType().intValue() == -10)){%> 
			 <%if(baselink.getRemark()!=null&&!baselink.getRemark().equals("")){ %>
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			 <%}} %>	
			 <!-- 驳回 -->
			 <%if(baselink.getActiveTemplateId()!=null && baselink.getOperateType().intValue() == 4){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				    <bean:message bundle="sheet" key="link.linkRejectCause" />
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			 <%} %>	
             <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 102){%>
		                  <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>
		  
			  		 
			 <%}%>	
  			</table>
  		  </div>
	    </div>
      </logic:iterate>
</logic:present>
</div>
<script type="text/javascript">
 switcher = new detailSwitcher();
  switcher.init({
	container:'history',
  	handleEl:'div.history-item-title'
  });
  
 function copy(id){
  	var ifCheck = document.getElementById(id);
  	if(ifCheck.checked == true){
    Ext.Ajax.request({
		method:"get",
		url: "greatevent.do?method=getJsonLink&id="+id+"&beanName=GreatEvent",
		success: function(x){
				    var o = eoms.JSONDecode(x.responseText);
					for(p in o){
		            var a = eoms.$(p);
				    if(a && a.tagName == "TEXTAREA" ){
				        if(a.value == ""){
				        a.value = o[p];
				        }
				        else{
				        a.value +="   "+o[p];
				        }				        
				     }	     
                  }		     
        	   }
       });
     }
   }
</script>