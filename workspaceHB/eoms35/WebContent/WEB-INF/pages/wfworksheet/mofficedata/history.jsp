﻿<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.sheet.mofficedata.model.MofficeDataSubLink"%>
<%@page import="java.util.List"%>
<%@page import="com.boco.eoms.base.util.StaticMethod"%>
<div id="history" class="panel">
   <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
   </div>
  
	<%int jNum=0;%>
	
<logic:present name="HISTORY" scope="request">  
	  <bean:define id="readOnly" value="true"/>
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.mofficedata.model.MofficeDataLink" >     
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
	  	     <eoms:dict key="dict-sheet-mofficedata" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
	  	     <bean:message bundle="mofficedata" key="mofficedata.operateType"/>:
	  	     <eoms:dict key="dict-sheet-mofficedata" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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
					  				       <bean:write name="baselink" property="nodeAcceptLimit" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
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

	

	
		 
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AuditDataTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 101 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr class="history-item-title">
			 				
		 					<td class="label">
								<!-- 审批意见 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkAuditSu"/>
							</td>
							<td colspan="3">
		 					
							 	<pre>${baselink.linkAuditSu}</pre>
							
		 					
	 						</tr>
	 						<tr class="history-item-title">
			 				
		 					<td class="label">是否调用</td>
							<td colspan="3">
		 					
							 	<pre><eoms:id2nameDB id="${baselink.linkIsNeedTest}" beanId="ItawSystemDictTypeDao"/></pre>
							
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 102 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 审批意见 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkAuditSu"/>
							</td>
							<td class="content">
		 					
							 	<pre>${baselink.linkAuditSu}</pre>
							
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("OfficeMadeTask")) {%>		  			
	  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 103 || baselink.getOperateType().intValue() == 11)){ %>
	  					<tr>
		  					<td class="label">设备厂家</td>
							<td class="content">	 					
							 	<pre>${baselink.linkStyle1}</pre>							 					
	 						</td>
	 						<td class="label">业务类型</td>
							<td class="content">	 					
							 	<pre><eoms:id2nameDB id="${baselink.linkStyle2}" beanId="IMofficeDataBuisTypeDAO"/></pre>							 					
	 						</td>	
 						</tr>
	  					<%java.util.List sublinks = (List)request.getAttribute(baselink.getId());
	  					 if(null!=sublinks){
	  					 	for(int i=0;i<sublinks.size();i++){
	  					 		MofficeDataSubLink sublink = (MofficeDataSubLink)sublinks.get(i);%>
	  					 		<tr>
				 					<td class="label">局数据操作人</td>
					 				  <td class="content">	
					 				    <eoms:id2nameDB id="<%=sublink.getOperateUserName()%>" beanId="tawSystemUserDao" />			   
				 			      </td>
				 					<td class="label">局数据操作时间</td>
									<td class="content">
										<%=StaticMethod.date2String(sublink.getOperateTime()) %>
									</td>
		 						</tr>
		 						<tr>
				 					<td class="label">局数据工单状态</td>
									<td class="content" colspan="3">
										<eoms:id2nameDB id="<%=sublink.getSheetStatus() %>" beanId="ItawSystemDictTypeDao" />
									</td>
		 						</tr>
	  					 		
							<%}%>
	 					<%}%>
	 					
	  				<%}%>	  	
	  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 115 )){ %>		
			 			<tr>
							<td class="label">制作方案</td>
							<td>
								<pre>${baselink.remark}</pre>
							</td>
						</tr>			
		  			<%}%>
		  					
		  		<%}%>		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("DataCheckTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 104 )){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 是否需要地市拨测 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkIsNeedTest"/>
							</td>
							<td class="content">
		 					
						    	<pre><eoms:id2nameDB id="${baselink.linkIsNeedTest}" beanId="ItawSystemDictTypeDao"/></pre>
							
		 					
		 					
	 						
		 					<td class="label">
								<!-- 审批意见 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkAuditRe"/>
							</td>
							<td class="content">
		 					
							 	<pre>${baselink.linkAuditRe}</pre>
							
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 内容描述 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkRemarkDe"/>
							</td>
							<td class="content">
		 					
							 	<pre>${baselink.linkRemarkDe}</pre>
							
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 105 )){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 驳回原因 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkRejectRe"/>
							</td>
							<td class="content">
		 					
							 	<pre>${baselink.linkRejectRe}</pre>
							
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  				<%if(baselink.getOperateType() != null && ( baselink.getOperateType().intValue() == 11)){ %>
			 				<tr>
			 				
		 					<td class="label">
								<!-- 处理结果描述 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkDealDesc"/>
							</td>
							<td class="content">
		 					
							 	<pre>${baselink.linkDealDesc}</pre>
							
		 					
	 						</tr>	 						
		 					
		  				<%}%>
		  				<%if(baselink.getOperateType() != null && ( baselink.getOperateType().intValue() == 117)){ %>
			 				<tr>
								<td class="label">说明</td>
								<td colspan="3"><pre>${baselink.remark}</pre>
								</td>
							</tr>
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  	 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("DataInsTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 116 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 			<tr>
							<td class="label">检查结果*</td>
							<td colspan="3">
								<pre>${baselink.linkDealDesc}</pre>
							</td>
						</tr>
						<tr>
							<td class="label">说明</td>
							<td colspan="3">
								<pre>${baselink.remark}</pre>
							</td>
						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 118 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
								<td class="label">检查结果*</td>
								<td colspan="3">
									<pre>${baselink.linkDealDesc}</pre>
								</td>
							</tr>
							<tr>
								<td class="label">说明</td>
								<td colspan="3">
									<pre>${baselink.remark}</pre>
								</td>
							</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ConfirmDataTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 106 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 处理结果描述 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkDealDesc"/>
							</td>
							<td class="content">
		 					
							 	<pre>${baselink.linkDealDesc}</pre>
							
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 107 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 驳回原因 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkRejectWh"/>
							</td>
							<td class="content">
		 					
							 	<pre>${baselink.linkRejectWh}</pre>
							
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AutoCheckTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 110 || baselink.getOperateType().intValue() == 114)){ %>
		  					
		  					<tr>
			 				
		 					<td class="label">是否新建成功</td>
							<td colspan="3">
		 					
							 	<pre><eoms:id2nameDB id="${baselink.linkAuditRe}" beanId="ItawSystemDictTypeDao"/></pre>
							
		 					
	 						</tr>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">退回原因</td>
							<td colspan="3">
		 					
							 	<pre>${baselink.linkAuditSu}</pre>
							
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  			
		  		 <%}%>
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AutoACheckTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 112 || baselink.getOperateType().intValue() == 113)){ %>
		  					
		  					<tr>
			 				
		 					<td class="label">是否新建成功</td>
							<td colspan="3">
		 					
							 	<pre><eoms:id2nameDB id="${baselink.linkAuditRe}" beanId="ItawSystemDictTypeDao"/></pre>
							
		 					
	 						</tr>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">退回原因</td>
							<td colspan="3">
		 					
							 	<pre>${baselink.linkAuditSu}</pre>
							
		 					
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
	  
	  	 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 10)){%> 

       			 				<tr>
			 				
		 					<td class="label">
								<!-- 是否需要地市拨测 -->
								<bean:message bundle="mofficedata" key="mofficeDataLink.linkIsNeedTest"/>
							</td>
							<td class="content">
		 					
						    	<pre><eoms:id2nameDB id="${baselink.linkIsNeedTest}" beanId="ItawSystemDictTypeDao"/></pre>
							
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
		url: "mofficedata.do?method=getJsonLink&id="+id+"&beanName=MofficeData",
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