<%@ include file="/common/taglibs.jsp"%>


<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>
<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.resourceaffirm.model.ResourceAffirmLink">  
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
  	     <eoms:dict key="dict-sheet-resourceaffirm" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="resourceaffirm" key="resourceaffirm.operateType"/>:
  	     <eoms:dict key="dict-sheet-resourceaffirm" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
         
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
  				      <bean:message bundle="sheet" key="linkForm.toOrgName"/>
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
  				      <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />&nbsp;&nbsp;
		                  
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
                <tr>
             <%
             if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("TaskCreateAuditHumTask")
                                                        ||baselink.getActiveTemplateId().equals("TaskCompleteAuditHumTask"))){%> 
             <%if(baselink.getOperateType()!=null && ( baselink.getOperateType().intValue() == 201 || baselink.getOperateType().intValue() == 202
                                                        ||baselink.getOperateType().intValue() == 208 || baselink.getOperateType().intValue() == 209 )){%> 				
  				 <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.auditResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="auditResult" scope="page"/></pre>
	                  </td>
		  		</tr>	
  			 <%}}%>	
  			 

  			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExcuteHumTask")){%>  	
  			  	 <%if(baselink.getOperateType()!=null &&  baselink.getOperateType().intValue() == 111){%>
                
                      <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="common.dispatchType"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   <%
	  				   if(baselink.getLinkNetType() != null && !baselink.getLinkNetType().equals("")){
			             String[] arrNetType = baselink.getLinkNetType().split(",");
			             for(int i=0;i<arrNetType.length;i++){
			              %>
	  				   	<eoms:id2nameDB id="<%=arrNetType[i] %>" beanId="ItawSystemDictTypeDao"/>&nbsp;
	  				   	<%} }%>
	                  </td>
		  	     	</tr>	
					
				  <%} %>	
             <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 205||baselink.getOperateType().intValue() == 206 )){%> 			  			 
		  		 	<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.bdeptContact"/>
		  				  </td>
		  				  <td class="column-content">
		  				  <bean:write name="baselink" property="ndeptContact" scope="page"/>
		                  </td>                  
 				
		  				  <td class="column-title">
		  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.bdeptContactPhone"/>
		  				  </td>
		  				  <td class="column-content">
                               <bean:write name="baselink" property="ndeptContactPhone" scope="page"/>
		                  </td>                  
		  			</tr>
               
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.netResCapacity"/>
		  				  </td>
		  				  <td class="column-content">
		  				    <bean:write name="baselink" property="netResCapacity" scope="page"/>
		                  </td>                  
 				
		  				  <td class="column-title">
		  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.expectFinishdays"/>
		  				  </td>
		  				  <td class="column-content">
                               <bean:write name="baselink" property="expectFinishdays" scope="page"/>
		                  </td>                  
		  			</tr>
		  			<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.investEvaluate"/>
		  				  </td>
		  				  <td class="column-content">
		  				    <bean:write name="baselink" property="investEvaluate" scope="page"/>
		                  </td>                  
 				
		  				  <td class="column-title">
		  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.clientPgmCapacity"/>
		  				  </td>
		  				  <td class="column-content">
                               <bean:write name="baselink" property="clientPgmCapacity" scope="page"/>
		                  </td>                  
		  			</tr>
		  			
		  			<tr>
		  			<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.isOccupied"/></td>
		            <td colspan='3'> <eoms:id2nameDB id="${baselink.isOccupied}" beanId="ItawSystemDictTypeDao"/></td>
		  			</tr>
		  			
		  				
		  			<%if(baselink.getNodeAccessories()!=null&&!baselink.getNodeAccessories().equals("")){%>
					<tr>
					<td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
		     		<td colspan=3>
		    			<eoms:attachment name="baselink" property="nodeAccessories" 
				            scope="page" idField="nodeAccessories" appCode="resourceAffirm" 
				             viewFlag="Y"/>
					  </td>
				  </tr>		
	  	        <%}%>	  			
		  		<% } } %>	
  			<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExcuteHumTask")){%>  		
             <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 8)){%>	
		  		 	<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.yijiao"/>
		  				  </td>
		  				  <td class="column-content" colspan=3>
                               <bean:write name="baselink" property="transferReason" scope="page"/>
		                  </td>                  
		  			</tr>		  		 
		  	 <%}%>
		  	 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 10)){%>	
		  		 	<tr>  				
		  				  <td class="column-title">
		  				     <bean:message bundle="sheet" key="linkForm.transferReason"/>
		  				  </td>
		  				  <td class="column-content" colspan=3>
                               <bean:write name="baselink" property="transferReason" scope="page"/>
		                  </td>                  
		  			</tr>		  		 
		  	 <%}%>
		  	 <% }%>
		  	 

			 <%if(baselink.getActiveTemplateId()!=null && (baselink.getOperateType().intValue() == 4 ||baselink.getOperateType().intValue() == -10||baselink.getOperateType().intValue() == -11||baselink.getOperateType().intValue() == -15||baselink.getOperateType().intValue() == 9) ){%> 
	          <tr>  				
		  				  <td class="column-title">
	         <bean:message bundle="sheet" key="linkForm.remark"/>
		  				  </td>
		  				  <td class="column-content" colspan=3>
                               <bean:write name="baselink" property="remark" scope="page"/>
		                  </td>                  
		  			</tr>		 
  			 <% }%>
 		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AffirmHumTask")){%> 
             <%if(baselink.getOperateType()!=null && ( baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7)){%> 				
  				 <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="resourceaffirm" key="resourceaffirm.dealResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="dealResult" scope="page"/></pre>
	                  </td>
		  		</tr>	
  			<%}}%>	
		  	 
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
		url: "resourceaffirm.do?method=getJsonLink&id="+id+"&beanName=ResourceAffirm",
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