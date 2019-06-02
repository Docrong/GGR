<%@ include file="/common/taglibs.jsp"%>

<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>
  
<%int jNum=0;%>
<logic:present name="deptIdtable">
<logic:iterate id="deptId" name="deptIdtable">
<%         
 jNum += 1;       
 String divName ="buzhou"+jNum;
%>


<div class="history-item">&nbsp;
  
    <div class="history-item-title" style="background: url('../../images/history_title.gif');">
		 <%=jNum%>. <eoms:id2nameDB id="${deptId}" beanId="tawSystemDeptDao" />
		 <img class="switchIcon" src="${app}/images/icons/closed.gif" align="absmiddle"/>
	</div>
	<div class="history-item hide">
		<c:forEach items="${deptIdMap[deptId]}" var="baselink">
		 <br>
		 <div class="history-item-title">
			 ${numberMap[baselink.id]}. <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
	  	     <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />	 
	  	     <bean:message bundle="sheet" key="task.operateName"/>:
	  	     <eoms:dict key="dict-sheet-localCommonTask" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
	         <bean:message bundle="localCommonTask" key="localCommonTask.operateType"/>: 
	  	     <eoms:dict key="dict-sheet-localCommonTask" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
			 <img class="switchIcon" src="${app}/images/icons/closed.gif" align="absmiddle"/>
		 </div>
		 	<div>
<c:if test="${taskName==baselink.activeTemplateId && ifWaitForSubTask=='false' && (baselink.operateType=='11' || baselink.operateType=='55')}">
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
				  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
				  				   </td>
				  				   <td class="column-content">
				  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
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
		                           <bean:message bundle="localCommonTask" key="localCommonTask.operateType"/>  
				  				  </td>
				  				  <td class="column-content">
				  				   <eoms:dict key="dict-sheet-localCommonTask" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
				                  </td>
				  				  <td class="column-title">
				  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
				  				  </td>
				  				  <td class="column-content">
				  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
				  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />
		  				           <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemDeptDao" />&nbsp;&nbsp;
				                  </td>		                  
		  					</tr>
<c:if test="${!empty baselink.nodeAcceptLimit && !empty baselink.nodeCompleteLimit}">

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

</c:if>

<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'TaskCreateAuditHumTask' || baselink.activeTemplateId == 'TaskCompleteAuditHumTask') }">
  <c:if test="${!empty baselink.operateType && (baselink.operateType == '201' || baselink.operateType == '202' || baselink.operateType == '208' || baselink.operateType == '209')}">
 
				  		 	<tr>  				
				  				  <td class="column-title">
				  				     <bean:message bundle="localCommonTask" key="localCommonTask.linkAuditResult"/>
				  				  </td>
				  				  <td class="column-content" colspan="3">
		                               <eoms:id2nameDB id="${baselink.linkAuditResult}" beanId="ItawSystemDictTypeDao"/>
				                  </td>                  
				  			</tr>
				  		 	<tr>  				
				  				  <td class="column-title">
				  				     <bean:message bundle="localCommonTask" key="localCommonTask.linkAuditIdea"/>
				  				  </td>
				  				  <td class="column-content" colspan="3">
		                              <pre> <bean:write name="baselink" property="linkAuditIdea" scope="page"/></pre>
				                  </td>                  
				  			</tr>
  </c:if>			  					  			
</c:if>

<c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'ExcuteHumTask'}">
	<c:if test="${!empty baselink.operateType && (baselink.operateType == '205' || baselink.operateType == '206')}">
            		      <tr>
		            			  <td  class="label"><bean:message bundle="localCommonTask" key="localCommonTask.linkTaskComplete"/></td>
				  				  <td class="column-content" colspan="3">
		                              <pre> <bean:write name="baselink" property="linkTaskComplete" scope="page"/></pre>
				                  </td>  
				          </tr>			  			   			
 
    </c:if>
    <c:if test="${!empty baselink.operateType && baselink.operateType == '10'}">
						  <tr>  				
				  		    	<td class="column-title">
				  				 	<bean:message bundle="localCommonTask" key="localCommonTask.fenpairesion"/>
				  		    	</td>
				  		   	 	<td class="column-content" colspan="3">
		                          	<pre><bean:write name="baselink" property="linkTaskComplete" scope="page"/></pre>
				            	</td>                  
				  		 </tr>       		  		 
	</c:if>
	<c:if test="${!empty baselink.operateType && baselink.operateType == '8'}">
						  <tr>  				
				  		    	<td class="column-title">
				  				 	<bean:message bundle="localCommonTask" key="localCommonTask.yijiaoresion"/>
				  		    	</td>
				  		    	<td class="column-content" colspan="3">
		                          	<pre><bean:write name="baselink" property="linkTaskComplete" scope="page"/></pre>
				            	</td>                  
				  		 </tr>       		  		 
	</c:if>
</c:if>


<c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'AffirmHumTask'}">
        <c:if test="${!empty baselink.operateType && (baselink.operateType == '211' || baselink.operateType == '212'|| baselink.operateType == '213')}">
           
		  				 <tr>
			  				  	<td class="column-title">
			  				     	<bean:message bundle="localCommonTask" key="localCommonTask.advice"/>
			  				  	</td>
			  				  	<td class="column-content" colspan=3>
			  				   	 	<pre><bean:write name="baselink" property="linkTaskComplete" scope="page"/></pre>
			                  	</td>
				  		</tr>	
		</c:if>
</c:if>


<c:if test="${!empty baselink.activeTemplateId && (baselink.operateType == '4' || baselink.operateType == '-10' || baselink.operateType == '-11' || baselink.operateType == '-15' || baselink.operateType == '9')}">  			
		
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>			  		 
</c:if>


<c:if test="${!empty baselink.activeTemplateId && (baselink.operateType == '-12' || baselink.operateType == '-14')}">  			
		 
			 
                <tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="mainForm.cancelReason"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>			  		 
</c:if>

          </table>	     
	 
		</div>
		 	
	</c:forEach>
		   
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
		url: "localCommonTask.do?method=getJsonLink&id="+id+"&beanName=LocalCommonTask",
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
