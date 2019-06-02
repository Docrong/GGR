<%@ include file="/common/taglibs.jsp"%>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink">  
       <%         
        jNum += 1;       
        String divName ="buzhou"+jNum;
       %>
     <div class="history-item"><!-- add space to hack ie-->&nbsp;
       <div class="history-item-title">
		 <%=jNum%>.
		 <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
  	     <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />	 
  	     <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemDeptDao" />	
  	      <bean:message bundle="sheet" key="task.operateName"/>:
  	     <eoms:dict key="dict-sheet-complaint" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="group" key="groupcomplaint.operateType"/>:
  	     <eoms:dict key="dict-sheet-complaint" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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
  				  <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />
  				  <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemDeptDao" />&nbsp;
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
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemDeptDao" />
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
  				  <td class="column-content" colspan=3>
  				 	 <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>
	  		  </tr>	 
  		      
                <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="group" key="groupcomplaint.operateType"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-complaint" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
		                  </td>
		  				  <td class="column-title">
		  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
  				   		   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />
  				   		   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemDeptDao" />
  				   		   &nbsp;&nbsp;
		                  </td>		                  
		  		</tr>
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




		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("FirstExcuteHumTask")){%> 
	
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 1){%> 
			 
               <tr>
  				  <td class="column-title">
  				     <bean:message bundle="group" key="groupcomplaint.linkTransmitReason"/>
  				  </td>
  				  <td class="column-content" colspan=3>
  				 	 <pre><bean:write name="baselink" property="linkTransmitReason" scope="page"/></pre>
                  </td>
	  		  </tr>				 			 
 
			  <%} %>
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 46 || baselink.getOperateType().intValue() == 11) ){%> 
	  				  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.ndeptContact"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<pre><bean:write name="baselink" property="ndeptContact" scope="page"/></pre>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.ndeptContactPhone"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="ndeptContactPhone" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.compProp"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<eoms:id2nameDB id="${baselink.compProp}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.isReplied"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.isReplied}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>			  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.isCorrect"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<eoms:id2nameDB id="${baselink.isCorrect}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.affectedAreas"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.affectedAreas}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>			  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.issueEliminatTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<bean:write name="baselink" property="issueEliminatTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.dealResult"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.dealResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>			  			  				  		
	  		
                <tr>                
	  				  <td class="column-title" >
	  				     <bean:message bundle="group" key="groupcomplaint.issueEliminatReason"/>
	  				  </td>
	  				  <td class="column-content"  colspan="3">
	  				   	<pre><bean:write name="baselink" property="issueEliminatReason" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	  		
  				  	 <tr>                
	  				  <td class="column-title" >
	  				     故障原因
	  				  </td>
	  				  <td class="column-content">
	  				   	  	<eoms:id2nameDB id="${baselink.faultReason}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	
	  				  <td class="column-title">
	  				     SLA等级
	  				  </td>
	  				  <td class="column-content">
	  				  
	  				   		<c:choose>
	  				   		<c:when test="${baselink.gradeSLA == '1' || baselink.gradeSLA == '2' || baselink.gradeSLA == '3'|| baselink.gradeSLA == '4'}">
	  				   			<eoms:id2nameDB id="${baselink.gradeSLA}" beanId="ItawSystemDictTypeDao"/>
	  				   		</c:when>
	  				   		<c:otherwise>
	  				   			${baselink.gradeSLA}
	  				   		</c:otherwise>
	  				   	</c:choose>
	            </td>	                  
		  		</tr>	  
		  		 <tr>
	  				  <td class="column-title">
	  				     电路代号
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="electricCode" scope="page"/></pre>
	                  </td>
		  		</tr>		
		  		
		  		<tr>
	  				  <td class="column-title">
	  				     地市
	  				  </td>
	  				  <td class="column-content" >
	  				  	<eoms:id2nameDB id="${baselink.linkCity}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     区县
	  				  </td>
	  				  <td class="column-content" >
	  				   	<eoms:id2nameDB id="${baselink.linkArea}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>			  	
	  					  		 			  					  		 
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.dealDesc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="dealDesc" scope="page"/></pre>
	                  </td>
		  		</tr>		  					  		 
			  		 
			 <%} %>				 	 
			 	 
		  		 
		 <%} %>	 
	 	
		 			 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("SecondExcuteHumTask") ){%> 
		 
			 
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 46 || baselink.getOperateType().intValue() == 11)  ){%> 

                 <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.ndeptContact"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<pre><bean:write name="baselink" property="ndeptContact" scope="page"/></pre>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.ndeptContactPhone"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="ndeptContactPhone" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.compProp"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<eoms:id2nameDB id="${baselink.compProp}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.isReplied"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.isReplied}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>			  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.isCorrect"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<eoms:id2nameDB id="${baselink.isCorrect}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.affectedAreas"/>1
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.affectedAreas}" beanId="ItawSystemDictTypeDao"/>${baselink.affectedAreas}
	                  </td>	                  
		  		</tr>			  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.issueEliminatTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<bean:write name="baselink" property="issueEliminatTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.dealResult"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.dealResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>			  			  				  		
	  		
                <tr>                
	  				  <td class="column-title" >
	  				     <bean:message bundle="group" key="groupcomplaint.issueEliminatReason"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="issueEliminatReason" scope="page"/></pre>
	                  </td>	
	  				  <td class="column-title">
	  				     故障分类
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.faultClassification}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>	  		
  				 <tr>                
	  				  <td class="column-title" >
	  				     故障原因
	  				  </td>
	  				  <td class="column-content">
	  				   	  	<eoms:id2nameDB id="${baselink.faultReason}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	
	  				  <td class="column-title">
	  				     SLA等级
	  				  </td>
	  				  <td class="column-content">
	  				  	<c:choose>
	  				   		<c:when test="${baselink.gradeSLA == '1' || baselink.gradeSLA == '2' || baselink.gradeSLA == '3'|| baselink.gradeSLA == '4'}">
	  				   			<eoms:id2nameDB id="${baselink.gradeSLA}" beanId="ItawSystemDictTypeDao"/>
	  				   		</c:when>
	  				   		<c:otherwise>
	  				   			${baselink.gradeSLA}
	  				   		</c:otherwise>
	  				   	</c:choose>
	            </td>	                  
		  		</tr>	  
		  		 <tr>
	  				  <td class="column-title">
	  				     电路代号
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="electricCode" scope="page"/></pre>
	                  </td>
		  		</tr>		
		  			<tr>
	  				  <td class="column-title">
	  				     地市
	  				  </td>
	  				  <td class="column-content" >
	  				  	<eoms:id2nameDB id="${baselink.linkCity}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     区县
	  				  </td>
	  				  <td class="column-content" >
	  				   	<eoms:id2nameDB id="${baselink.linkArea}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>			  	
	  					  		 			  					  		 
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.dealDesc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="dealDesc" scope="page"/></pre>
	                  </td>
		  		</tr>	
	
			  		 
			 <%} %>				 	 

 
		 
		 <%} %>			 

	
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("CheckingHumTask") ){%> 
		 	<%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 56 || baselink.getOperateType().intValue() == 54) ){%>
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.linkCheckResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkCheckResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		</tr>		 	
		 	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.linkCheckIdea"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="linkCheckIdea" scope="page"/></pre>
	                  </td>
		  		</tr>		 	
		 	<%} %>
		 <%} %>			 
 
		 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 5){%> 
		 
	             <tr>
					  <td class="column-title">
					     <bean:message bundle="group" key="groupcomplaint.linkTransmitContent"/>
					  </td>
					  <td class="column-content" colspan=3>
					 	 <pre><bean:write name="baselink" property="linkTransmitContent" scope="page"/></pre>
	                </td>
	 		  </tr>				 			 
	
		 <%} %>
 
 		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("DeferExamineHumTask") ){%> 
 		 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.linkExamineContent"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="linkExamineContent" scope="page"/></pre>
	                  </td>
		  		</tr> 		 
 		 
 		 <%} %>
 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("HoldHumTask") ){%> 
		 
		 	<%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 17){%> 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="group" key="groupcomplaint.linkUntreadReason"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="linkUntreadReason" scope="page"/></pre>
	                  </td>
		  		</tr>		 
		  	<%} %>
		 
		 <%} %>			 
		 
		 <%if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getOperateType().intValue() == 9 ||baselink.getActiveTemplateId().equals("advice")||baselink.getOperateType().intValue() == 4)){%> 
		 
               <tr>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.remark"/>
  				  </td>
  				  <td class="column-content" colspan=3>
  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                  </td>
	  		</tr>	
		  		 
		 <%} %>	
		 
         <%if(baselink.getOperateType()!=null && ( baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7) ){%> 
			 
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.remark" />
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	  			      </td>
	  				                  
  				</tr>
			  		 
		 <%} %>			 
		 
	  	 <%if(baselink.getNodeAccessories()!=null&&!baselink.getNodeAccessories().equals("")){%>
	  		 	<tr>  				
	  				  <td>
	  				     <bean:message bundle="group" key="groupcomplaint.nodeAccessories"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="complaint" 
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
		url: "groupcomplaint.do?method=getJsonLink&id="+id+"&beanName=GroupComplaint",
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