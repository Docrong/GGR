<%@ include file="/common/taglibs.jsp"%>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink">  
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
  	     <eoms:dict key="dict-sheet-businesspilot" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="businesspilot" key="businesspilot.operateType"/>:
  	     <eoms:dict key="dict-sheet-businesspilot" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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
  		      <%//}else{ %>
  		      
  		      <!--  <tr>
	  			  <td class="column-title">
		  		   <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
		  			</td>
		  			 <td colspan="3" class="column-content">
		  			 <c:if test="${!empty baselink.operateRoleId}">
  				        <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" />
  				        <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />&nbsp; 
  				    </c:if> 
  				    <c:if test="${empty baselink.operateRoleId}">
  				        <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />&nbsp;
  				    </c:if>	
		           </td>	
		  		</tr>
  		       -->
  		      <%//} %>
                 <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.operateType"/>
	  				  </td>
	  				  <td class="column-content">
	  				   <eoms:dict key="dict-sheet-businesspilot" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
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
		  
		    
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("pilot")){
		   
		 %> 
	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 1 || baselink.getOperateType().intValue() == 111||baselink.getOperateType().intValue() == 2 || baselink.getOperateType().intValue() == 11)){%> 
			  <c:if test="${empty baselink.linkTestResult}">
			  <c:if test="${baselink.operateType!=11 }">
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="businesspilot" key="businesspilot.linkOperStartTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				       <bean:write name="baselink" property="linkOperStartTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	  			      </td>
	  				  <td class="column-title">
	  				     <pre><bean:message bundle="businesspilot" key="businesspilot.linkOperEndTime"/></pre>
	  				  </td>
	  				  <td class="column-content">
	  				     <bean:write name="baselink" property="linkOperEndTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>                  
  				   </tr>
  				<tr>   
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkNetType"/>
	  				  </td>
	  				  <td class="column-content" colspan="3">
			             <%
			             String[] arrNetType = baselink.getLinkNetType().split(",");
			             for(int i=0;i<arrNetType.length;i++){
			              %>
	  				   	<eoms:id2nameDB id="<%=arrNetType[i] %>" beanId="ItawSystemDictTypeDao"/>
	  				   	<%} %>
	                  </td>	                  
		  		  </tr>
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkBusDesc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="linkBusDesc" scope="page"/></pre>
	                  </td>
		  		</tr>
                  <tr>  				
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkPilotScheme"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="linkPilotScheme" 
				              scope="page" idField="linkPilotScheme" appCode="businesspilot" 
				               viewFlag="Y"/>
	                  </td>                  
	  				</tr>	
			  </c:if>
			  </c:if>
			  <c:if test="${!empty baselink.linkTestResult}">
  				    <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkTestResult"/>
	  				  </td>
	  				  <td class="column-content"  colspan="3">
	  				  	<eoms:id2nameDB id="${baselink.linkTestResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	                  </tr>
			  		 <tr>  				
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkOperateDesc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="linkOperateDesc" 
				              scope="page" idField="linkOperateDesc" appCode="businesspilot" 
				               viewFlag="Y"/>
	                  </td>                  
	  				</tr>	
			  </c:if>
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
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("strategy")){%> 
		 
			
		          <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="businesspilot" key="businesspilot.linkIlluminate"/>
		  				  </td>
		  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkIlluminate" scope="page"/></pre>
		                  </td>
		  		 </tr>	
		  		  <tr>  				
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkTGPolicyAcc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="linkTGPolicyAcc" 
				              scope="page" idField="linkTGPolicyAcc" appCode="businesspilot" 
				               viewFlag="Y"/>
	                  </td>                  
	  				</tr>	
		
		 <%}if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("prepare")){%> 
		 
			     <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkDeviceVerify"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkDeviceVerify}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		</tr>	
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkDataFile"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkDataFile" scope="page"/></pre>
		              </td>
		  		</tr>	
		  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkPassMan"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkPassMan" scope="page"/></pre>
		              </td>
		  		</tr>					  		 
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkReport"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkReport" scope="page"/></pre>
		              </td>
		  		</tr>
		  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkWorkplan"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkWorkplan" scope="page"/></pre>
		              </td>
		  		</tr>
                <tr>  				
  				  <td>
  				     <bean:message bundle="businesspilot" key="businesspilot.linkMeetingAcc"/>
  				  </td>
  				  <td class="column-content" colspan=3>
                        <eoms:attachment name="baselink" property="linkMeetingAcc" 
			              scope="page" idField="linkMeetingAcc" appCode="businesspilot" 
			               viewFlag="Y"/>
                  </td>                  
  				</tr>	
	
			  		 
			 <%} %>				 	 

			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("run")){%> 
		 

		            <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="businesspilot" key="businesspilot.linkRunFeedBack"/>
		  				  </td>
		  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkRunFeedBack" scope="page"/></pre>
		                  </td>
		  		 </tr>	
		  		  <tr>  				
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkRunReport"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="linkRunReport" 
				              scope="page" idField="linkRunReport" appCode="businesspilot" 
				               viewFlag="Y"/>
	                  </td>                  
	  				</tr>	
			 
		 <%} %>	

		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("check")){%> 
		 
			  <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkIsUpdate"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkIsUpdate}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		</tr>	
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkUpdateAdvice"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
		  				   <pre><bean:write name="baselink" property="linkUpdateAdvice" scope="page"/></pre>
		              </td>
		  		</tr>	
		 <%} %>	
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("report")){%> 
		 
		    	 <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkIsSuccess"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkIsSuccess}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		 </tr>	
	
		     </tr>	
		     
		    
              <tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="businesspilot" key="businesspilot.linkConsult"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="linkConsult" scope="page"/></pre>
	  			      </td>
	  				                  
  				</tr>
  				    
		     	
		  		 <tr>  				
  				                   
  				  <td class="label">
  				     <bean:message bundle="businesspilot" key="businesspilot.linkSummarizeAcc"/>*
  				  </td>
  				  <td class="content" colspan=3>
  				     <eoms:attachment name="baselink" property="linkSummarizeAcc" 
				              scope="page" idField="linkSummarizeAcc" appCode="businesspilot" viewFlag="Y"/>
			      </td>                  
  				</tr>	
		 
		 
		 <%} %>	
        <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("note")){
            if(baselink.getOperateType()!=null && ( baselink.getOperateType().intValue() == 80 || baselink.getOperateType().intValue() == 11)){
        %> 
		 
			  <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkSDEndTime"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	   <bean:write name="baselink" property="linkSDEndTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>
		  		</tr>	
			
 					
		 <%}else if( baselink.getOperateType().intValue() == 56 || baselink.getOperateType().intValue() == 11){%>
		      <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkIsFinish"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	  	<eoms:id2nameDB id="${baselink.linkIsFinish}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		</tr>	
		      <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="businesspilot" key="businesspilot.linkIsChange"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	  	<eoms:id2nameDB id="${baselink.linkIsChange}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		</tr>
		 
		 <% }}}%>	
         <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 8){%> 
			 
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.transmitReason"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
	  			      </td>
	  				                  
  				</tr>
			  		 
		 <%} %>		
		    <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 10 || baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7|| baselink.getOperateType().intValue() == 11)){
		    
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
	  				     <bean:message bundle="businesspilot" key="businesspilot.nodeAccessories"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="businesspilot" 
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
		url: "businesspilot.do?method=getJsonLink&id="+id+"&beanName=BusinessPilot",
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