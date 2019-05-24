<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>


<div id="history" class="panel">
   <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
   </div>
  
	<%int jNum=0;%>
	
<logic:present name="HISTORY" scope="request">  
	  <bean:define id="readOnly" value="true"/>
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.maintenanceservice.model.MaintenanceServiceLink" >     
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
	  	      <c:choose>
	  	      		<c:when test="${baselink.activeTemplateId == 'Approval'}">
	  	      		审批
	  	      		</c:when>
	  	      		<c:otherwise>
	  	      		<eoms:dict key="dict-sheet-maintenanceservice" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
	  	      		</c:otherwise>
	  	      </c:choose>
	  	     
	  	     <bean:message bundle="maintenanceservice" key="maintenanceservice.operateType"/>:
	  	     <eoms:dict key="dict-sheet-maintenanceservice" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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

	

	
		 
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("Confirmation")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 102 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 说明 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkExplain"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkExplain}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 描述 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkDescribe"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkDescribe}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 106 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 强制归档原因 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkFilingReason"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkFilingReason}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("Fill")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 103 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 种类 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkType"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkType}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 序号 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkSerialNumber"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkSerialNumber}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 设备名称 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkDeviceName"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkDeviceName}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 设备厂家 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkManufacturers"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkManufacturers}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 系统名称 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkSystemName"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkSystemName}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 设备型号 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkEquipmentType"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkEquipmentType}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 设备序列号 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkDeviceNumber"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkDeviceNumber}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 入网时间 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkAccessTime"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkAccessTime}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 出保日期 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkOutDate"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkOutDate}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 接口类型及数量 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkInterfaceType"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkInterfaceType}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 是否配置双机热备软件和其它随机软件 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkSoftware"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkSoftware}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 操作系统名称及版本 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkSystemName"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkSystemName}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 系统集成商名称 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkIntegratorName"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkIntegratorName}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 拟购买的服务级别 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkLevelService"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkLevelService}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 设备物理位置 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkPhysicalLocation"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkPhysicalLocation}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 备注 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkRemarks"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkRemarks}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 联系人 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkContacts"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkContacts}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 联系方式 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkContactInformation"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkContactInformation}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 单位 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkCompany"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkCompany}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 4 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 驳回原因 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkReasonRejection"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkReasonRejection}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("Examine")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 104 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 审批结果 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkResult"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkResult}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 审批意见 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkApprovalOpinion"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkApprovalOpinion}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 服务内容是否满意 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkServiceContent"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkServiceContent}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 评分 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkScore"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkScore}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 处理是否及时 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkTimelyProcessing"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkTimelyProcessing}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 4 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 驳回原因 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkReasonRejection"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkReasonRejection}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 106 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 强制归档原因 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkFilingReason"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkFilingReason}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("Approval")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 105 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 服务内容 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkServiceContent"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkServiceContent}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 维护开始时间 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkMaintenanceStartTime"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkMaintenanceStartTime}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 维护结束时间 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkMaintenanceEndTime"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkMaintenanceEndTime}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 处理是否及时 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkTimelyProcessing"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkTimelyProcessing}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 4 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 驳回原因 -->
								<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkReasonRejection"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkReasonRejection}</pre>
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
		url: "maintenanceservice.do?method=getJsonLink&id="+id+"&beanName=MaintenanceService",
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