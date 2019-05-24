<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
	 <img src="${app}/images/icons/warning.gif" />受理时限超时！ | 
	 <img src="${app}/images/icons/nodetype/failure.gif" />处理时限超时！
  </div>
  
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.softchange.model.SoftChangeLink">  
       <%         
        jNum += 1;       
        String divName ="buzhou"+jNum;
       %>
     <div class="history-item"><!-- add space to hack ie-->&nbsp;
       <div class="history-item-title">
		<%if((baselink.getOperateType()!=null && (baselink.getOperateType().intValue() != 0) && (baselink.getOperateType().intValue() == 61) && (baselink.getOperateType().intValue() != 22))
					 ){%> 
						<c:if test="${baselink.acceptFlag == 2 }">
							<img src="${app}/images/icons/warning.gif" />
						</c:if>	    

		<%} %>	
		<%if((baselink.getOperateType()!=null && (baselink.getOperateType().intValue() != 0) && (baselink.getOperateType().intValue() != 61) && (baselink.getOperateType().intValue() != 22))
					 ){%> 
						<c:if test="${baselink.completeFlag == 2 }">
							<img src="${app}/images/icons/nodetype/failure.gif" />
						</c:if>	 
		<%} %>
		 <%=jNum%>.
		 <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
  	     <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />	 
  	     <bean:message bundle="sheet" key="task.operateName"/>:
  	     <eoms:dict key="dict-sheet-softchange" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="softchange" key="softchange.operateType"/>:
  	     <eoms:dict key="dict-sheet-softchange" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
  	     
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
  				<%if(baselink.getOperateRoleId()!=null && ! baselink.getOperateRoleId().equals("")) {%>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" />
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;  				   
  			      </td>
  			      <%} %>
  				 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operaterContact"/>
  				  </td> 
                   <td class="column-content">
  				     <bean:write name="baselink" property="operaterContact" scope="page"/>
                  </td>             
  				</tr>
  		      
                <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="softchange" key="softchange.operateType"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-softchange" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
		                  </td>
		                <%if(baselink.getToOrgRoleId()!=null && ! baselink.getToOrgRoleId().equals("")) {%>  
		  				  <td class="column-title">
		  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
  				           <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />&nbsp;&nbsp;
		  				<!--    <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" /> -->
		                  </td>	
		                  <%}else{%>
		                  <td class="column-title"></td>
		                  <td class="column-content"></td>	
		                  <%} %>                  
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
                <tr>
                 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content" colspan='3'>
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				</tr>



		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ProjectCreateTask")){%> 
	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 110||baselink.getOperateType().intValue() == 11)){%> 
			 
			
  			 <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCompleteLimitTime"/></td>
  				    <td class="column-content">
  				     <bean:write name="baselink" property="linkCompleteLimitTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                    </td>   
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkDesignKey"/></td>
		            <td class="column-content" >
	  				   <pre><bean:write name="baselink" property="linkDesignKey" scope="page"/></pre>
	                </td>
		      </tr>
			  <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkDesignComment"/></td>
		            <td class="column-content" colspan="3">
	  				   <pre><bean:write name="baselink" property="linkDesignComment" scope="page"/></pre>
	                </td>
		      </tr>
			  <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkInvolvedProvince"/></td>
		            <td class="column-content">
	  				   <pre><bean:write name="baselink" property="linkInvolvedProvince" scope="page"/></pre>
	                </td>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkInvolvedCity"/></td>
		            <td class="column-content">
	  				   <pre><bean:write name="baselink" property="linkInvolvedCity" scope="page"/></pre>
	                </td>
		       </tr>
			   <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkRiskEstimate"/></td>
		            <td class="column-content" colspan="3">
	  				   <pre><bean:write name="baselink" property="linkRiskEstimate" scope="page"/></pre>
	                </td>
		       </tr>
			   <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkEffectAnalyse"/></td>
		            <td class="column-content" colspan="3">
	  				   <pre><bean:write name="baselink" property="linkEffectAnalyse" scope="page"/></pre>
	                </td>
		       </tr>
		       	<tr>  				
	  				  <td class="label">
	  				     <bean:message bundle="softchange" key="softchange.linkProjectAccessories"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="softchange" 
				               viewFlag="Y"/>
	                  </td>                  
	  	     </tr>
		       
		       
			 <%} }%>		
						 	 		  		 
			 <%if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getActiveTemplateId().equals("reply")||baselink.getActiveTemplateId().equals("advice")||baselink.getOperateType().intValue() == 4)){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			  		 
			 <%} %>		 	
		 			 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AuditTask")){%> 
		 
	       <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 130 || baselink.getOperateType().intValue() == 111||baselink.getOperateType().intValue() == 55||baselink.getOperateType().intValue() == 123)){%> 
	            <tr>
		              <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsCheck"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkIsCheck}" beanId="ItawSystemDictTypeDao"/>
	                  </td>                
		        </tr>
			    <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCheckComment"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="linkCheckComment" scope="page"/></pre>
	                  </td>
		         </tr>
			 <%} }%>		
			 
			

		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PermitTask")){%> 
		 
			 
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 131 ||baselink.getOperateType().intValue() == 112||baselink.getOperateType().intValue() == 55 || baselink.getOperateType().intValue() == 121|| baselink.getOperateType().intValue() == 122)){%> 
            	 <tr>
		             <td  class="label"><bean:message bundle="softchange" key="softchange.linkPermitResult"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkPermitResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>                   
		       </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkPermitIdea"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="linkPermitIdea" scope="page"/></pre>
	                  </td>
		          </tr>
				  <tr>
             	<td  class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
             	<td colspan="3">
				             <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="netdata" 
				               viewFlag="Y"/>   
             	</td>
             </tr>

	<%}} %>
	 

		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PlanTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 113||baselink.getOperateType().intValue() == 11)){%> 
			 
                  <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkManager"/></td>
		              <td class="column-content">
	  				   	 <pre><bean:write name="baselink" property="linkManager" scope="page"/></pre>
	                  </td>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkContact"/></td>
		               <td class="column-content">
	  				   	 <pre><bean:write name="baselink" property="linkContact" scope="page"/></pre>
	                  </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkPlanStartTime"/></td>
		            <td class="column-content">
  				     <bean:write name="baselink" property="linkPlanStartTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                    </td> 
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkPlanEndTime"/></td>
		            <td class="column-content">
  				     <bean:write name="baselink" property="linkPlanEndTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                    </td> 
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCellInfo"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="linkCellInfo" scope="page"/></pre>
	                  </td>
		          </tr>
	                <tr>
		              <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsEffectBusiness"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkIsEffectBusiness}" beanId="ItawSystemDictTypeDao"/>
	                  </td>  	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkEffectCondition"/></td>
	  				  <td class="column-content" colspan=3>
	  				   <pre><bean:write name="baselink" property="linkEffectCondition" scope="page"/></pre>
	                  </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkNetManage"/></td>
	  				  <td class="column-content" colspan=3>
	  				  <pre><bean:write name="baselink" property="linkNetManage" scope="page"/></pre>
	                  </td>
		          </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkBusinessDept"/></td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkBusinessDept}" beanId="ItawSystemDictTypeDao"/>
	                  </td>  	                
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsSendToFront"/></td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkIsSendToFront}" beanId="ItawSystemDictTypeDao"/>
	                  </td>                  
		            </tr>
		   		 		<tr>  				
	  				  <td class="label">
	  				    附件
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="softchange" 
				               viewFlag="Y"/>
	                  </td>                  
	  	     </tr>
		            
			  		 
			 <%} }%> 
			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExecuteTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 114||baselink.getOperateType().intValue() == 11)){%> 

			   <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkCutResult"/></td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkCutResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>                   
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsPlan"/></td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkIsPlan}" beanId="ItawSystemDictTypeDao"/>
	                  </td>                 
		            </tr>
			    <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCutComment"/></td>
	  				  <td class="column-content" colspan="3">
	  				   	 <pre><bean:write name="baselink" property="linkCutComment" scope="page"/></pre>
	                  </td>
		       </tr>
			    <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkBusinessComment"/></td>
	  				  <td class="column-content" colspan="3">
	  				   	 <pre><bean:write name="baselink" property="linkBusinessComment" scope="page"/></pre>
	                  </td>
		          </tr>
	               
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkAlertRecord"/></td>
	  				  <td class="column-content" colspan="3">
	  				   	 <pre><bean:write name="baselink" property="linkAlertRecord" scope="page"/></pre>
	                  </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkUnnormalComment"/></td>
	  				  <td class="column-content" colspan="3">
	  				   	 <pre><bean:write name="baselink" property="linkUnnormalComment" scope="page"/></pre>
	                  </td>
		          </tr>	 
		         	<tr>  				
	  				  <td>
	  				     <bean:message bundle="softchange" key="softchange.linkTestReport"/>
	  				  </td>
	  				  <td class="column-content" colspan="3">
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="softchange" 
				               viewFlag="Y"/>
	                  </td>                  
	  	          </tr>  
		          
		          
			 <%} }%>	
			 
			 
			 
		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ValidateTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 115||baselink.getOperateType().intValue() == 11)){%> 
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCutAnalyse"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="linkCutAnalyse" scope="page"/></pre>
	                  </td>
		          </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsUpdateWork"/></td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkIsUpdateWork}" beanId="ItawSystemDictTypeDao"/>
	                  </td> 	                
		                <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsNeedProject"/></td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkIsNeedProject}" beanId="ItawSystemDictTypeDao"/>
	                  </td> 	                
		            </tr>
		            
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkWorkUpdateAdvice"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="linkWorkUpdateAdvice" scope="page"/></pre>
	                  </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkSoftVersionUpdate"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="linkSoftVersionUpdate" scope="page"/></pre>
	                  </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkNextPlan"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="linkNextPlan" scope="page"/></pre>
	                  </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkProjectComment"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="linkProjectComment" scope="page"/></pre>
	                  </td>
		          </tr>	 
			 <%} }%>	
			 
			 
			 
			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("HoldTask")){%> 
			  	<tr>
                   <td class="label">
                           <bean:message bundle="softchange" key="softchange.mainIfDemonstrateCase"/>
                   </td>
	               <td colspan='3'>
	                       <eoms:id2nameDB id="${sheetMain.mainIfDemonstrateCase}" beanId="ItawSystemDictTypeDao"/>
	               </td>
                </tr>    
                <tr>		
                   <td class="label">
                           <bean:message bundle="softchange" key="softchange.mainCaseKeywords"/>
                   </td>
		           <td colspan='3'>
                   <pre><bean:write name="sheetMain" property="mainCaseKeywords" scope="request"/></pre>
		           </td>
                </tr>   	 
			 <%} %>	
		  <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 6||baselink.getOperateType().intValue() == 7||baselink.getOperateType().intValue() == 10||baselink.getOperateType().intValue() == 30)){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			  		 <tr>  				
	  				  <td class="label">
	  				    附件
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="softchange" 
				               viewFlag="Y"/>
	                  </td>                  
	  	     </tr>
			 <%}%>	

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
		url: "softchange.do?method=getJsonLink&id="+id+"&beanName=SoftChange",
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