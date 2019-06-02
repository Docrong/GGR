<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>


<div id="history" class="panel">
   <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
   </div>
  
	<%int jNum=0;%>
	
<logic:present name="HISTORY" scope="request">  
	  <bean:define id="readOnly" value="true"/>
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.widencomplaint.model.WidenComplaintLink" >     
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
	  	     <eoms:dict key="dict-sheet-widencomplaint" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
	  	     <bean:message bundle="widencomplaint" key="widencomplaint.operateType"/>:
	  	     <eoms:dict key="dict-sheet-widencomplaint" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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

	

	
		 
		  	
		  		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExcuteHumTask")) {%>
		  			
		  				<%if(baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 102 || baselink.getOperateType().intValue() == 11)){ %>
		  					
	 						
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.ndeptContact"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<pre><bean:write name="baselink" property="ndeptContact" scope="page"/></pre>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.ndeptContactPhone"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="ndeptContactPhone" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	
	  				  	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.linkIfInvokeChange"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<eoms:id2nameDB id="${baselink.linkIfInvokeChange}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.linkChangeSheetId"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="linkChangeSheetId" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	
	  				
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.linkFaultStartTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<bean:write name="baselink" property="linkFaultStartTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.linkFaultEndTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<bean:write name="baselink" property="linkFaultEndTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>	                  
		  		</tr>		  				  		
	  				  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.linkFaultGenerantPlace"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<pre><bean:write name="baselink" property="linkFaultGenerantPlace" scope="page"/></pre>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.linkPlaceDesc"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="linkPlaceDesc" scope="page"/></pre>
	                  </td>	                  
		  		</tr>		  				  		
	  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.issueEliminatTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				 	<bean:write name="baselink" property="issueEliminatTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>	  				  	
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.issueEliminatReason"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="issueEliminatReason" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	  		
	  				  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.dealResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				    	<pre><bean:write name="baselink" property="dealResult" scope="page"/></pre>
	                  </td>
		  		</tr>			  					  		 
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.dealDesc"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="dealDesc" scope="page"/></pre>
	                  </td>
	                  
	                  <td class="column-title">
	  				     是否修完质量测试记录
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre>是,达标</pre>
	                  </td>
		  		
		  		
		  		</tr>	
		  	<tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.compProp"/>
	  				  </td>
	  				  <td class="column-content">
						<eoms:id2nameDB id="${baselink.compProp}" beanId="ItawSystemDictTypeDao"/>
	                 		 </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.isReplied"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.isReplied}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>
		  		 <tr>
	  				  <td class="column-title">
	  				     首次联系用户时间
	  				  </td>
 
	  				  <td class="column-content">
	  				   	<bean:write name="baselink" property="linkFirstContactUesrTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>
	                  
	                  <td class="column-title">
	  				     预约上门时间
	  				  </td>
 
	  				  <td class="column-content">
	  				   	<bean:write name="baselink" property="linkReservationTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>
		  		
		  		
		  		</tr>	
	 			
	 			
	 			<tr>
	  				  <td class="column-title">
	  				     修障材料:网线
	  				  </td>
	  				  <td class="column-content">
						${baselink.linkNetline}
	                  </td>	                 
	  				  <td class="column-title">
	  				     修障材料:光缆
	  				  </td>
	  				  <td class="column-content">
	  				   	${baselink.linkOptical}
	                  </td>	                  
		  		</tr>
		  		
		  		<tr>
	  				  <td class="column-title">
	  				     现场测速照片
	  				  </td>
	  				  <td colspan="3">
						<eoms:attachment name="baselink" property="linkSpeed" 
		            	scope="page" idField="linkSpeed" appCode="widencomplaint" 
		             	viewFlag="Y"/>
	                  </td>	
		  		</tr>
		  		<tr>
	  				  <td class="column-title">
	  				     现场服务回执单
	  				  </td>
	  				  <td colspan="3">
						<eoms:attachment name="baselink" property="linkReceipt" 
		            	scope="page" idField="linkReceipt" appCode="widencomplaint" 
		             	viewFlag="Y"/> 
	                  </td>	                 
	  				                   
		  		</tr>
		  		
		  		<tr>
	  				  <td class="column-title">
	  				     联系客户录音
	  				  </td>
	  				  <td colspan="3">
						<eoms:attachment name="baselink" property="linkTeleRecord" 
		            	scope="page" idField="linkTeleRecord" appCode="widencomplaint" 
		             	viewFlag="Y"/> 
	                  </td>	                 
	  				                   
		  		</tr>
		  		
		  					
		 					
		  				<%}%>
		  			
		  		 <%}%>
		  	
		  
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("CheckingHumTask") ){%> 
		 	<%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 103 || baselink.getOperateType().intValue() == 104) ){%>
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.linkCheckResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkCheckResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		</tr>		 	
		 	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="widencomplaint" key="widenComplaintLink.linkCheckIdea"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="linkCheckIdea" scope="page"/></pre>
	                  </td>
		  		</tr>		 	
		 	<%} %>
		 <%} %>			 		  	
		  
	

	

	 	
			    
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
	  <%if((baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getActiveTemplateId().equals("reply")||baselink.getActiveTemplateId().equals("advice")||baselink.getOperateType().intValue() == 4))||baselink.getOperateType().intValue() == -11){%> 
	 
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
		url: "widencomplaint.do?method=getJsonLink&id="+id+"&beanName=WidenComplaint",
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