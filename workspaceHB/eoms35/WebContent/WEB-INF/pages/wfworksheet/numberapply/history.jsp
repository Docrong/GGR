<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>


<div id="history" class="panel">
   <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
   </div>
  
	<%int jNum=0;%>
	
<logic:present name="HISTORY" scope="request">  
	  <bean:define id="readOnly" value="true"/>
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.numberapply.model.NumberApplyLink" >     
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
	  	     <eoms:dict key="dict-sheet-numberapply" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
	  	     <bean:message bundle="numberapply" key="numberapply.operateType"/>:
	  	     <eoms:dict key="dict-sheet-numberapply" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
	  	     <img class="switchIcon" src="${app}/images/icons/closed.gif" align="absmiddle"/>
	   	 </div>
		   <!-- 历史列表标题结束 -->
		   
		   <!-- 历史步骤的详细信息-->
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
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content" colspan="3">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				</tr>
  				 <tr>                
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operaterContact"/> 
  				  </td>
  				  <%if(baselink.getOperateType().intValue()==22||baselink.getOperateType().intValue()==-10||baselink.getOperateType().intValue()==0 || baselink.getOperateType().intValue() == 3 || baselink.getOperateType().intValue() == -12){%> 	
	  				  <td class="column-content">
	  				  		${sheetMain.sendContact}
	  			      </td>
  			      <%} else {%>
  			      		<td class="column-content">
	  				  		${baselink.operaterContact}
	  			      </td>
  			      <% }%>
  			     
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
	  				  </td>
	  				  <td class="column-content">
	  				        <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemDeptDao" />
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
	  		
	  		
	 <!-- 流程中的历史页面 -->
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PermitTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 81 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 是否通过 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkIfPass"/>
							</td>
							<td class="content">
		 					
						    	<pre><eoms:id2nameDB id="${baselink.linkIfPass}" beanId="ItawSystemDictTypeDao"/></pre>
							
		 					
		 					
	 						
		 					<td class="label">
								<!-- 审核意见 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkPermitsuggest"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkPermitsuggest}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
		  				<%}%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 81 || baselink.getOperateType().intValue() == 11)){ %>
		  					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AllotResourceTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 18 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 网元名称 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkNetName"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkNetName}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 网元代号 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkNetID"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkNetID}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 网元属性 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkNetProp"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkNetProp}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 建设地点 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkBuildAddress"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkBuildAddress}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 供应商 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkSupplier"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkSupplier}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 信令点编码(24位) -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkCommandCode24"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkCommandCode24}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 信令点编码(14位) -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkCommandCode14"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkCommandCode14}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- HLRID -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkHLRID"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkHLRID}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 硬件平台 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkHardWareFlatRoof"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkHardWareFlatRoof}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 软件版本 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkSoftwareVersion"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkSoftwareVersion}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 容量（万） -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkCapability"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkCapability}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 信令链路数（2MB） -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkCommondLink"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkCommondLink}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- E1端口总数（承载窄带) -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkPortCount"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkPortCount}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 覆盖地区 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkCoverArea"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkCoverArea}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 覆盖地区长途区号 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkAreaNumber"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkAreaNumber}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 城市 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkCity"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkCity}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 设备所在本地网名称 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkDeviceName"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkDeviceName}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 归属区域 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkAttachArea"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkAttachArea}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 批复文件号 -->
								<bean:message bundle="numberapply" key="numberApplyLink.linkFileNumber"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkFileNumber}</pre>
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
		url: "numberapply.do?method=getJsonLink&id="+id+"&beanName=NumberApply",
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