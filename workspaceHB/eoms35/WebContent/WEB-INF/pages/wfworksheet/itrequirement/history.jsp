<%@ include file="/common/taglibs.jsp"%>

<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.itrequirement.model.ITRequirementLink">  
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
  	     <eoms:dict key="dict-sheet-itrequirement" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="itrequirement" key="itrequirement.operateType"/>:
  	     <eoms:dict key="dict-sheet-itrequirement" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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
  				<%if(baselink.getOperateRoleId()!=null && ! baselink.getOperateRoleId().equals("")){ %>
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
		  				     <bean:message bundle="itrequirement" key="itrequirement.operateType"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-itrequirement" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
		                  </td>
                 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
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
               <%if(baselink.getToOrgRoleId()!=null && ! baselink.getToOrgRoleId().equals("")) {%>
                 <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
		  				  </td>
		  				  <td class="column-content"  colspan="3">
		  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
  				           <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />
		  				   
		                  </td>	
  				</tr>
 				<%} %>


		 <%if((baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AuditTask"))||
		     (baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("DevAuditTask"))){%> 
		 
			 <%if((baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 911||baselink.getOperateType().intValue() == 912|| baselink.getOperateType().intValue() == 55))||
			      (baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 941||baselink.getOperateType().intValue() == 942|| baselink.getOperateType().intValue() == 55))){%> 
			 <tr>
			  <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAuditResult"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkAuditResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
			</tr>

		       <tr>
		  		 <td class="column-title">  				    
		  		  <bean:message bundle="itrequirement" key="itrequirement.linkAuditDesc"/>
		  	      </td>
		  		<td class="column-content" colspan=3>
		  	       <pre><bean:write name="baselink" property="linkAuditDesc" scope="page"/></pre>
		        </td>
		  	</tr>	  		 
			 <%}}%>	
			 
			 
			 
			 
			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AnalysisTask")){%> 
	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 921||baselink.getOperateType().intValue() == 922||baselink.getOperateType().intValue() == 923
			                                          ||baselink.getOperateType().intValue() == 924||baselink.getOperateType().intValue() == 925||baselink.getOperateType().intValue() == 11)){%> 
			 	   <tr>
		                 <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkChangeType"/></td>
		                <td class="column-content">  
		                <eoms:id2nameDB id="${baselink.linkChangeType}" beanId="ItawSystemDictTypeDao"/>

			        </td>	                
		                 <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAnalysisResult"/></td>
		                <td class="column-content">  
				         <eoms:id2nameDB id="${baselink.linkAnalysisResult}" beanId="ItawSystemDictTypeDao"/>
			        </td>	                
		            </tr>
<!--  			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkDevAmount"/></td>
		            <td  class="column-content"> <bean:write name="baselink" property="linkDevAmount" scope="page"/></td>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAuditPer"/></td>
		            <td  class="column-content"> <bean:write name="baselink" property="linkAuditPer" scope="page"/></td>
		          </tr>	-->		            
			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAnalysisDesc"/></td>
		              <td colspan="3"><pre><bean:write name="baselink" property="linkAnalysisDesc" scope="page"/></pre></td>
		          </tr>
		     	<tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkHardWareExp"/></td>
		              <td colspan="3"> 	
    				  <pre><bean:write name="baselink" property="linkHardWareExp" scope="page"/></pre>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkSystemImpact"/></td>
		              <td colspan="3"> 	
    				  <pre><bean:write name="baselink" property="linkSystemImpact" scope="page"/></pre>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkOtherImpact"/></td>
		              <td colspan="3"> 	
    				 <pre><bean:write name="baselink" property="linkOtherImpact" scope="page"/></pre>
                    </td>
		          </tr> 		          			          
	                <tr>
  						<td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkTechnicalprogram"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment  name="baselink" property="linkTechnicalprogram"  scope="page"
		   				 idField="${sheetPageName}linkTechnicalprogram" appCode="itrequirementsheet" 
		   				 viewFlag="Y"/>
		                </td>
		            </tr>
			 <%} }%>	
			 
			 
			 
			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AddInfoTask")){%> 
	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 93||baselink.getOperateType().intValue() == 11)){%> 		            
			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkRequirementDesc"/></td>
		              <td colspan="3"><pre><bean:write name="baselink" property="linkRequirementDesc" scope="page"/></pre></td>
		          </tr>			          
	                <tr>
  						<td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkRequirementDetail"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment  name="baselink" property="linkRequirementDetail" scope="page"
		   				 idField="${sheetPageName}linkRequirementDetail" appCode="itrequirementsheet" 
		   				 viewFlag="Y"/>
		                </td>
		            </tr>
			 <%} }%>
			 
	    <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("OperateTask")){%> 	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 95||baselink.getOperateType().intValue() == 11)){%> 		            	     
			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkCompleteDesc"/></td>
		              <td colspan="3"><pre><bean:write name="baselink" property="linkCompleteDesc" scope="page"/></pre></td>
		          </tr>			          

			 <%} }%>	
			 
	    <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("TempSaveTask")){%> 	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 96||baselink.getOperateType().intValue() == 11)){%> 		            	     
			   <!--    <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkTempSaveDesc"/></td>
		              <td colspan="3"><pre><bean:write name="baselink" property="linkTempSaveDesc" scope="page"/></pre></td>
		          </tr>	--> 		          

			 <%} }%>
	    <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ReplyTask")){%> 	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 97||baselink.getOperateType().intValue() == 11)){%> 		            	     
			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkReplyDesc"/></td>
		              <td colspan="3"><pre><bean:write name="baselink" property="linkReplyDesc" scope="page"/></pre></td>
		          </tr>			          

			 <%} }%>				 			 		 				 
			 
			 
			 
       <%if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getOperateType().intValue() == 9||baselink.getActiveTemplateId().equals("advice")||baselink.getOperateType().intValue() == 4)){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			  		 
			 <%} %>		 	
		 			 
		
 		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("HoldTask")){%>
		  <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 102){%>
		                  <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>
		  
			  		 
			 <%}}%>	
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ConfirmHoldTask")){%>
		  <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 101){%>
		                  <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="itrequirement" key="itrequirement.linkTestDesc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				  <pre><bean:write name="baselink" property="linkTestDesc" scope="page"/></pre>
	                  </td>
		  		</tr>
		  
			  		 
			 <%}}%>		 
				
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

           <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == -12||baselink.getOperateType().intValue() == -13||baselink.getOperateType().intValue() == -14)){%> 
			 
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.transmitReason"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
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
			  		 
			 <%}%>			
	  	 <%if(baselink.getNodeAccessories()!=null&&!baselink.getNodeAccessories().equals("")){%>
	  		 	<tr>  				
	  				  <td>
	  				     <bean:message bundle="sheet" key="linkForm.accessories"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="businessoperation" 
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
		url: "itrequirement.do?method=getJsonLink&id="+id+"&beanName=ITRrequirement",
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