<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.*" %>

<div id="history" class="panel">
   <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
   </div>
  
	<%int jNum=0;%>
	
<logic:present name="HISTORY" scope="request">  
	  <bean:define id="readOnly" value="true"/>
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.equipmentinstallation.model.EquipmentInstallationLink" >     
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
	  	     <eoms:dict key="dict-sheet-equipmentinstallation" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
	  	     <bean:message bundle="equipmentinstallation" key="equipmentinstallation.operateType"/>:
	  	     <eoms:dict key="dict-sheet-equipmentinstallation" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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

	

	
		 
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("Audit")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 101 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 备注 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkDescr"/>
							</td>
							<td colspan="3">
		 					
							 	<pre>${baselink.linkDescr}</pre>
	 						</tr>
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("Install")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 102 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 安装设备类型 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkEquipmentType"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkEquipmentType}</pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 设备编号 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkEquipmentNum"/>
							</td>
							<td class="content">
		 					
								<pre>${baselink.linkEquipmentNum}</pre>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 安装时间 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkEquipmentTime"/>
							</td>
							<td class="content">
		 					
								<pre><bean:write name="baselink" property="linkEquipmentTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/></pre>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- MINI设备安装合作协议 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkEquipmentAgree"/>
							</td>
							<td class="content">
								<%  Map mapacc = (Map) request.getAttribute("mapacc"); 
									String equipmentAgree = StaticMethod.nullObject2String(mapacc.get(baselink.getLinkEquipmentAgree()));
									System.out.println("equipmentAgree==="+equipmentAgree);
									if(!"".equals(equipmentAgree)){
									String temStr[] = equipmentAgree.split("&");
									System.out.println("temStr==="+temStr[0]);
									for(int i=0;i<temStr.length;i++){
										String cnAndId = temStr[i];
										System.out.println("cnAndId==="+cnAndId);
										String tem[] = cnAndId.split("#");
										String cn = tem[0];
										String id = tem[1];
								%>
								<a href="${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=id %>"><%=cn %></a><br/>
								<%
									}}
								%>
								
								
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 用户回执单 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkUserReceipt"/>
							</td>
							<td class="content">
								<%  
									String userReceipt = StaticMethod.nullObject2String(mapacc.get(baselink.getLinkUserReceipt()));
									System.out.println("userReceipt==="+userReceipt);
									if(!"".equals(userReceipt)){
									String [] temStr1= userReceipt.split("&");
									for(int i=0;i<temStr1.length;i++){
										String cnAndId = temStr1[i];
										System.out.println("cnAndId==="+cnAndId);
										String tem[] = cnAndId.split("#");
										String cn = tem[0];
										String id = tem[1];
								%>
								<a href="${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=id %>"><%=cn %></a><br/>
								<%
									}}
								%>
								 
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- MINI设备运行信息表 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkInformation"/>
							</td>
							<td class="content">
								<%  
									String information = StaticMethod.nullObject2String(mapacc.get(baselink.getLinkInformation()));
									System.out.println("information==="+information);
									if(!"".equals(information)){
									String [] temStr2 = information.split("&");
									for(int i=0;i<temStr2.length;i++){
										String cnAndId = temStr2[i];
										System.out.println("cnAndId==="+cnAndId);
										String tem[] = cnAndId.split("#");
										String cn = tem[0];
										String id = tem[1];
								%>
								<a href="${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=id %>"><%=cn %></a><br/>
								<%
									}}
								%>
	 						</td>
		 					
		 					
	 						</tr>
	 						
		 					
	 						
			 				<tr>
			 				
		 					<td class="label">
								<!-- 现场施工照片 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkScenePhoto"/>
							</td>
							<td class="content">
		 						<% 
									String scenePhoto = StaticMethod.nullObject2String(mapacc.get(baselink.getLinkScenePhoto()));
									System.out.println("scenePhoto==="+scenePhoto);
									if(!"".equals(scenePhoto)){
									String [] temStr3 = scenePhoto.split("&");
									for(int i=0;i<temStr3.length;i++){
										String cnAndId = temStr3[i];
										System.out.println("cnAndId==="+cnAndId);
										String tem[] = cnAndId.split("#");
										String cn = tem[0];
										String id = tem[1];
								%>
								<a href="${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=id %>"><%=cn %></a><br/>
								<%
									}}
								%>
	 						</td>
		 					
		 					
		 					
	 						
		 					<td class="label">
								<!-- 收费照片 -->
								<bean:message bundle="equipmentinstallation" key="equipmentInstallationLink.linkChargePhoto"/>
							</td>
							<td class="content">
		 						<%  
									String chargePhoto = StaticMethod.nullObject2String(mapacc.get(baselink.getLinkChargePhoto()));
									System.out.println("chargePhoto==="+chargePhoto);
									if(!"".equals(chargePhoto)){
									String [] temStr4 = chargePhoto.split("&");
									for(int i=0;i<temStr4.length;i++){
										String cnAndId = temStr4[i];
										System.out.println("cnAndId==="+cnAndId);
										String tem[] = cnAndId.split("#");
										String cn = tem[0];
										String id = tem[1];
								%>
								<a href="${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=id %>"><%=cn %></a><br/>
								<%
									}}
								%>
								
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
		url: "equipmentinstallation.do?method=getJsonLink&id="+id+"&beanName=EquipmentInstallation",
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