<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>


<div id="history" class="panel">
   <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
   </div>
  
	<%int jNum=0;%>
	
<logic:present name="HISTORY" scope="request">  
	  <bean:define id="readOnly" value="true"/>
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.citycustom.model.CityCustomLink" >     
       <%         
        jNum += 1;       
        String divName ="buzhou"+jNum;
       %>
      <div class="history-item">&nbsp;   
      	   <!-- 历史列表标题开始 -->
	      <div class="history-item-title">
			 <%=jNum%>.
			 <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
	  	     <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />	 
	  	      <bean:message bundle="sheet" key="task.operateName"/>:
	  	     <eoms:dict key="dict-sheet-citycustom" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
	  	     <bean:message bundle="citycustom" key="citycustom.operateType"/>:
	  	     <eoms:dict key="dict-sheet-citycustom" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
	  	     <img class="switchIcon" src="${app}/images/icons/closed.gif" align="absmiddle"/>
	   	 </div>
		   <!-- 历史列表标题结束 -->
		   
		   <!-- 历史步骤的详细信息-->
      	   <div class="history-item-content hide">
 		 			<table class="formTable" width="100%" cellpadding="0" cellspacing="0">
 		 				  <!-- 开头的公共部份 -->
			 			  <tr>
				 				  <td class="label">
			 				     		 <bean:message bundle="sheet" key="linkForm.operateUserName"/>
				 				  </td>
				 				  <td class="content">
			 				  			 <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />&nbsp;
				                  </td>
					              <td class="label">
				 				     	 <bean:message bundle="sheet" key="linkForm.operateDeptName"/>
					 		      </td>
					 			  <td class="content">
			 	  				    	 <eoms:id2nameDB id="${baselink.operateDeptId}" beanId="tawSystemDeptDao" />&nbsp;
					              </td>
			 				</tr>
			 				<tr>
								  <td class="label">
					  				 	<bean:message bundle="${module}" key="${module}.operateType"/>
				  				  </td>
					  			  <td class="content">
					            		<eoms:dict key="dict-sheet-${module}" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2nameXML" />  
					              </td>
				 				  <td class="label">
				 				     	<bean:message bundle="sheet" key="linkForm.operateRoleName"/>
				 				  </td>
				 				  <td class="content">
					 				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" />  	
					 				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />			   
				 			      </td>
			 		       </tr>
				 		   <tr>
				 				  <td class="label">
				 				      	<bean:message bundle="sheet" key="linkForm.operaterContact"/> 
				 				  </td>
				 				  <!-- 是新建，草稿派发，作废 -->
				 				  <%if(baselink.getOperateType().intValue()==0 || baselink.getOperateType().intValue() == 3 || baselink.getOperateType().intValue() == -12){%> 	
					  				  	<td class="content">
					  				  		${sheetMain.sendContact}
					  			      	</td>
				 			      <%} else {%>
				 			      		<td class="content">
				  				  			${baselink.operaterContact}
				  			      		</td>
				 			      <% }%>
				 			      <%if(baselink.getOperateType()!=null &&baselink.getOperateType().intValue() != 61){ %>
					  				  <td class="column-title">
					  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
					  				  </td>
					  				  <td class="column-content">
					  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
			  				   		   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />&nbsp;&nbsp;
					                  </td>	
			                  	<%} else {%>
					                  <td class="column-title"> </td>
					  				  <td class="column-content"></td>	
			                  	<% }%>	
			 			  </tr>
			              <tr> 
				               	  <td class="label">
				  				     	<bean:message bundle="sheet" key="linkForm.operateTime"/>
				  				  </td>
				  				  <td class="content">
				  				     	<bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
				                  </td> 
				                  <%if(baselink.getNodeCompleteLimit() != null) {%>    
					                  <td class="label">
									      	<bean:message bundle="sheet" key="linkForm.acceptLimit"/>
					  				  </td>
					  				  <td class="content">
					  				       <bean:write name="baselink" property="nodeCompleteLimit" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
					  			      </td>
				  			       <%} else {%>
				  			       		<td class="label">
				  				  		</td>
						  				<td class="content">
						                </td> 
				  			       <% }%>		 
			              </tr>
			            <!-- 开头的公共部份结束 -->
               
	  		
	  		
	 <!-- 流程中的历史页面 -->

	

	
		 
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("OrderExamine")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 102 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 审核情况 -->
								审核情况
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("NetOrderExamine")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 103 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 审核情况 -->
								审核情况
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("SchemeDesign")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 104 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 完成情况 -->
								<bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("SubmitExamine")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 105 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 审核情况 -->
								审核情况
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 115 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 完成情况 -->
								<bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExamineScheme")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 106 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 审核情况 -->
								审核情况
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 113 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 完成情况 -->
								<bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("Construction")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 107 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 完成情况 -->
								<bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("SubmitCheck")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 108 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 验收情况 -->
								验收情况
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("CheckExamine")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 109 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 审核情况 -->
								审核情况
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 114 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 完成情况 -->
								<bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("OpenComplete")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 110 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 完成情况 -->
								<bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AffirmHumTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 111 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 完成情况 -->
								<bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 112 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 完成情况 -->
								<bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkGroupComplete}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  
	

	

	 	
			    
	 <!-- 流程页面结束 -->
     
     <!-- 下面是公共的 -->
     <!-- 移交或转审 -->
     <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 8||baselink.getOperateType().intValue() == 88)){%> 

 				<tr>
  				  <td class="label">
  				      <bean:message bundle="sheet" key="linkForm.transmitReason"/>
  				  </td>
  				   <td class="content" colspan=3>
  				      <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
  			      </td>	  				                  
 				</tr>
 				
		  		 
	  <%} %>	
	  
	  <!-- 处理回复不通过 或 处理回复通过 -->
	 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7 || baselink.getOperateType().intValue() == 10||baselink.getOperateType().intValue() == 30)){%> 

               <tr>
  				  <td class="label">
  				     <bean:message bundle="sheet" key="linkForm.remark"/>
  				  </td>
  				  <td class="content" colspan=3>
  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                  </td>
	  		</tr>	

	  <%}%>	
	  
	  <!-- 抄送，阶段回复，阶段通知 -->
	  <%if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getActiveTemplateId().equals("reply")||baselink.getActiveTemplateId().equals("advice")||baselink.getOperateType().intValue() == 4)){%> 
	 
              <tr>
 				  <td class="label">
 				     <bean:message bundle="sheet" key="linkForm.remark"/>
 				  </td>
 				  <td class="content" colspan=3>
 				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
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
		url: "citycustom.do?method=getJsonLink&id="+id+"&beanName=CityCustom",
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