<%@ include file="/common/taglibs.jsp"%>

<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink">  
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
  	     <eoms:dict key="dict-sheet-netOptimize" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="netOptimize" key="netOptimize.operateType"/>:
  	     <eoms:dict key="dict-sheet-netOptimize" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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
  				 <%//if(baselink.getOperateType()!= null && baselink.getOperateType().intValue() != 61){%>
  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				    <c:if test="${!empty baselink.operateRoleId}">
  				        <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" />
  				        <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />&nbsp; 
  				    </c:if> 
  				    <c:if test="${empty baselink.operateRoleId}">
  				        <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />&nbsp;
  				    </c:if>				   
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operaterContact"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="operaterContact" scope="page"/>
                  </td>  
               </tr>
  		   
                 <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.operateType"/>
	  				  </td>
	  				  <td class="column-content">
	  				   <eoms:dict key="dict-sheet-netOptimize" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
	                  </td>
	                  <td class="column-title">
	 				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
	 				  </td>
	 				  <td class="column-content">
	 				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                 </td>
	  			</tr>
	  		 <% if(baselink.getOperateType()!= null && baselink.getOperateType().intValue() != 61 && baselink.getOperateType().intValue() != -15 && baselink.getOperateType().intValue() != -12 && baselink.getOperateType().intValue() != -13 && baselink.getOperateType().intValue() != -14&& baselink.getOperateType().intValue() != 111){%>	
	  			<tr>
	  			  <td class="column-title">
		  		   <bean:message bundle="sheet" key="linkForm.toOrgName"/>
		  			</td>
		  			 <td colspan="3" class="column-content">
		  			 <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
		  			 <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />&nbsp;
		           </td>	
		  		</tr>
		  <%} %>
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
		  
		    
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AcceptTask")){
		   
		 %> 
	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 80|| baselink.getOperateType().intValue() == 11)){%> 
			  
  				 <tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="netOptimize" key="netOptimize.linkIsAccept"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				       <eoms:id2nameDB id="${baselink.linkIsAccept}" beanId="ItawSystemDictTypeDao"/>
	  			      </td>
	  			 </tr>
	  			 
	  			 
	  			 <tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="netOptimize" key="netOptimize.linkTestResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				       <pre><bean:write name="baselink" property="linkTestResult" scope="page"/></pre>
	  			      </td>
	  			 </tr>
	  		
			 <%} }%>		
			 
			<%if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getActiveTemplateId().equals("reply")||baselink.getActiveTemplateId().equals("advice"))){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			  		 
			 <%} %>		 	
		 <%if (baselink.getOperateType()!= null && baselink.getOperateType().intValue() != 61 && baselink.getOperateType().intValue() != 10 && baselink.getOperateType().intValue() != 11 && baselink.getOperateType().intValue() != 8 && baselink.getOperateType().intValue() != 4){ %>			 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ProjectCreateTask")){%> 
		 
			
		          <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="netOptimize" key="netOptimize.linkProjectExplain"/>
		  				  </td>
		  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkProjectExplain" scope="page"/></pre>
		                  </td>
		  		 </tr>	
		  		  <tr>  				
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.linkProjectAcc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="linkProjectAcc" 
				              scope="page" idField="linkProjectAcc" appCode="netOptimize" 
				               viewFlag="Y"/>
	                  </td>                  
	  				</tr>	
		
		 <%}if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AuditTask")){%> 

		  <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 5 ||baselink.getOperateType().intValue() == 66 ||baselink.getOperateType().intValue() == 55)){%> 

                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.linkAuditResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkAuditResult" scope="page"/></pre>
		              </td>
		  		</tr>	
		  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.linkAuditOpinion"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkAuditOpinion" scope="page"/></pre>
		              </td>
		  		</tr>					  		 
	
               
			 <%}} %>				 	 

			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExcuteTask")){%> 
		 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.linkIsSuccess"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkIsSuccess}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
	                  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.linkIsStartSheet"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkIsStartSheet}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		</tr>	
		  		<tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.linkSummarize"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkSummarize" scope="page"/></pre>
		              </td>
		  		</tr>	
		  		
		  			
		  		  <tr>  				
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.linkOperationAcc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="linkOperationAcc" 
				              scope="page" idField="linkOperationAcc" appCode="netOptimize" 
				               viewFlag="Y"/>
	                  </td>                  
	  				</tr>	
			 
		 <%} %>	

		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ResultEvaluateTask")){%> 
		 
				 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.linkAssessResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkAssessResult" scope="page"/></pre>
		              </td>
		  		</tr>	
		 <%}} %>	
		
       
         <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 8 || baselink.getOperateType().intValue() == 88)){%> 
			 
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.transmitReason"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
	  			      </td>
	  				                  
  				</tr>
			  		 
		 <%} %>		
		    <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 10 || baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7|| baselink.getOperateType().intValue() == 11 || baselink.getOperateType().intValue() == 30)){
		    
		    %> 
			 
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	  			      </td>
	  				                  
  				</tr>
			  		 
		 <%} %>	
		 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 4 ){%> 
			 
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	  			      </td>
	  				                  
  				</tr>
			  		 
		 <%} %>	
	  	 <%if(baselink.getNodeAccessories()!=null&&!baselink.getNodeAccessories().equals("")){%>
	  		 	<tr>  				
	  				  <td class="column-title">
	  				     <bean:message bundle="netOptimize" key="netOptimize.nodeAccessories"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="netOptimize" 
				               viewFlag="Y"/>
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
		url: "netOptimize.do?method=getJsonLink&id="+id+"&beanName=NetOptimize",
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
