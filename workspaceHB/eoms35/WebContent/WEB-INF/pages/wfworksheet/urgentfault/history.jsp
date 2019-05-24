<%@ include file="/common/taglibs.jsp"%>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink">  
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
  	     <eoms:dict key="dict-sheet-urgentfault" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="urgentfault" key="urgentfault.operateType"/>:
  	     <eoms:dict key="dict-sheet-urgentfault" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
  	     
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
  				      <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operaterContact"/>
  				  </td>
  				  <td class="column-content">
  				     <pre><bean:write name="baselink" property="operaterContact" scope="page"/></pre>
                  </td>                  
  				</tr>

  				<tr>
  				  <td class="column-title">
  				    <bean:message bundle="sheet" key="linkForm.operateTime"/>   
  				  </td>
  				  <td class="column-content"  colspan=3>
  				    <pre><bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/></pre>				   
  			      </td>                  
  				</tr> 
   
                 <tr>
 				  <td class="column-title">
 				     <bean:message bundle="urgentfault" key="urgentfault.operateType"/>
 				  </td>
 				  <td class="column-content">
 				   <eoms:dict key="dict-sheet-urgentfault" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
                 </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.toOrgRoleId"/>
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

		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("MiddleReportAffirmHumTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 913){%> 

  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/>
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="urgentfault" key="urgentfault.mainGreatFaultId"/>
  				  </td>
  				  <td class="column-content">
  				     <pre><bean:write name="baselink" property="linkGreatFaultId" scope="page"/></pre>
                  </td>                  
  				</tr>			 

		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.linkIfAffirm1"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <eoms:id2nameDB id="${baselink.linkIfAffirm}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		 </tr>	
		  		 
		          <tr>
	  				  <td class="column-title">
	  				    <bean:message bundle="urgentfault" key="urgentfault.linkFaultReportDesc2"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <pre> <bean:write name="baselink" property="linkFaultReportDesc" scope="page"/></pre>
	                  </td>
		  		 </tr>			  		 
			  		 
			 <%} %>	
			 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 914){%> 

		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.linkRejectReason"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <pre><bean:write name="baselink" property="linkFaultReportDesc" scope="page"/></pre>
	                  </td>
		  		 </tr>			 

			 <%} %>				 		 

		 <%} %>	

		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("BackReportAffirmHumTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 923){%> 
			 
  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/>
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="urgentfault" key="urgentfault.mainGreatFaultId"/>
  				  </td>
  				  <td class="column-content">
  				     <pre><bean:write name="baselink" property="linkGreatFaultId" scope="page"/></pre>
                  </td>                  
  				</tr>			 

		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.linkIfAffirm1"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				  	 <eoms:id2nameDB id="${baselink.linkIfAffirm}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		 </tr>	
		  		 
		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.linkFaultReportDesc1"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <pre><bean:write name="baselink" property="linkFaultReportDesc" scope="page"/></pre>
	                  </td>
		  		 </tr>	
			  		 
			 <%} %>	
			 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 924){%> 

		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.linkRejectReason"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <pre><bean:write name="baselink" property="linkFaultReportDesc" scope="page"/></pre>
	                  </td>
		  		 </tr>			 

			 <%} %>				 		 

		 <%} %>	
 			 <%if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getOperateType().intValue() == 9 ||baselink.getActiveTemplateId().equals("advice"))){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			  		 
			 <%} %>
		 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("SolveFaultHumTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 93){%> 
			 
  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/>
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="urgentfault" key="urgentfault.mainFaultGenerantPlace"/>
  				  </td>
  				  <td class="column-content">
  				     <pre><bean:write name="baselink" property="linkFaultGenerantPlace" scope="page"/></pre>
                  </td>                  
  				</tr>
			  		 
  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="urgentfault" key="urgentfault.mainFaultAvoidTime"/>
  				  </td>
  				  <td class="column-content">
  				      <bean:write name="baselink" property="linkFaultAvoidTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="urgentfault" key="urgentfault.mainAffectTimeLength"/>
  				  </td>
  				  <td class="column-content">
  				     <pre><bean:write name="baselink" property="linkAffectTimeLength" scope="page"/></pre>
                  </td>                  
  				</tr>			  		 
			  		 
		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.mainAffectOperationDesc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <pre><bean:write name="baselink" property="linkAffectOperationDesc" scope="page"/></pre>
	                  </td>
		  		 </tr>
		  		 
		          <tr>
	  				  <td class="column-title">
	  				    <bean:message bundle="urgentfault" key="urgentfault.linkFaultAvoidReport"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="linkFaultAvoidReport" scope="page"/></pre>
	                  </td>
		  		 </tr>		  		 
		  		 				  		 
			 <%} %>				 		 

		 <%} %>	
		 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("SolveFaultAffirmHumTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 933){%> 

  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/>
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="urgentfault" key="urgentfault.mainGreatFaultId"/>
  				  </td>
  				  <td class="column-content">
  				     <pre><bean:write name="baselink" property="linkGreatFaultId" scope="page"/></pre>
                  </td>                  
  				</tr>			 

		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.linkIfAffirm1"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <eoms:id2nameDB id="${baselink.linkIfAffirm}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		 </tr>	
		  		 
		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.linkFaultAvoidReport"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <pre><bean:write name="baselink" property="linkFaultAvoidReport" scope="page"/></pre>
	                  </td>
		  		 </tr>				 

			  		 
			 <%} %>	
			 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 934){%> 
			 
		          <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="urgentfault" key="urgentfault.linkRejectReason"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				     <pre><bean:write name="baselink" property="linkFaultReportDesc" scope="page"/></pre>
	                  </td>
		  		 </tr>			 

			 <%} %>				 		 

		 <%} %>	
		 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ReportHumTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 94){%> 
			 

			  		 
			 <%} %>			 		 

		 <%} %>			 		 		 

		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("SumUpHumTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 95){%> 
			 

			  		 
			 <%} %>	
			 		 

		 <%} %>	

		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("HoldHumTask")){%> 
		 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 18){%> 
			 

			  		 
			 <%} %>		 		 

		 <%} %>	
		 

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
		 
         	 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 61){%> 
			 
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
	  				  <td>
	  				     <bean:message bundle="urgentfault" key="urgentfault.nodeAccessories"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="urgentfault" 
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
		url: "urgentfault.do?method=getJsonLink&id="+id+"&beanName=UrgentFault",
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