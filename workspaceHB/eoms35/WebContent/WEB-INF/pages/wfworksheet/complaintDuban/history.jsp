<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanLink">  
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
  	     <eoms:dict key="dict-sheet-complaintDuban" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="complaintDuban" key="complaintDuban.operateType"/>:
  	     <eoms:dict key="dict-sheet-complaintDuban" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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
		  				     <bean:message bundle="complaintDuban" key="complaintDuban.operateType"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-complaintDuban" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
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
			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("Assessor")){%> 	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 200||baselink.getOperateType().intValue() == 11)){%> 	
				<tr>
			       <td class="column-title">
			       	 延期原因
			        
				    </td>
					<td colspan="column-content">			
				        <pre><bean:write name="baselink" property="delayCase" scope="page"/></pre>
				  </td>
			       <td class="column-title">
			        延期解决时间
			        
				    </td>
					<td class="column-content">			
				        <pre><bean:write name="baselink" property="delayTime" scope="page"/></pre>
				  </td>
				</tr>
				<tr>
			       <td class="column-title">
			       	 申请报结时间
			        
				    </td>
					<td colspan="column-content">			
				        <pre><bean:write name="baselink" property="applyTime" scope="page"/></pre>
				  </td>
			       <td class="column-title">
			        审核通过时间
			        
				    </td>
					<td class="column-content">			
				        <pre><bean:write name="baselink" property="approveTime" scope="page"/></pre>
				  </td>
				</tr>
				<tr>
			       <td class="column-title">
			       	 解决情况
			        
				    </td>
					<td colspan="column-content">			
				        <pre><bean:write name="baselink" property="solveCond" scope="page"/></pre>
				  </td>
			       <td class="column-title">
			       	 解决手段
			        
				    </td>
					<td class="column-content">			
				        <pre><bean:write name="baselink" property="solveTool" scope="page"/></pre>
				  </td>
				</tr>
				<tr>
			       <td class="column-title">
			       	 项目名称
			        
				    </td>
					<td colspan="column-content">			
				        <pre><bean:write name="baselink" property="projectName" scope="page"/></pre>
				  </td>
			       <td class="column-title">
			       	审核结果
			        
				    </td>
					<td class="column-content">			
				        <pre><eoms:id2nameDB id="${baselink.approveResult}" beanId="ItawSystemDictTypeDao"/></pre>
				  </td>
				</tr>
				<tr>
			       <td class="label">
			        审核意见
			        
				    </td>
					<td colspan="3">			
				        <pre><bean:write name="baselink" property="approveOpinion" scope="page"/></pre>
				  </td>
				</tr>
				<tr>
			       <td class="label">
			        <bean:message bundle="sheet" key="linkForm.remark"/>
			        
				    </td>
					<td colspan="3">			
				        <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
				  </td>
				</tr>

			 <%} }%>
			 
		
 		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("HoldTask")){%>
		  <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 18){%>
	                		  		 
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
		  <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 4)){%> 
			 
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
		url: "complaintDuban.do?method=getJsonLink&id="+id+"&beanName=ComplaintDuban",
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