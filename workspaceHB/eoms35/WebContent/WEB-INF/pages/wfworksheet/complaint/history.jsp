<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.complaint.model.ComplaintLink">  
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
  	     <eoms:dict key="dict-sheet-complaint" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="complaint" key="complaint.operateType"/>:
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
  				  <td class="column-content" colspan=3>
  				 	 <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>
	  		  </tr>	 
  		      
                <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="complaint" key="complaint.operateType"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-complaint" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
		                  </td>
		  				  <td class="column-title">
		  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
  				   		   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />&nbsp;&nbsp;
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
		
		<%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 53 || baselink.getOperateType().intValue() == 0)){%> 
			<tr>
  				  <td class="column-title">
  				     预处理情况
  				  </td>
  				  <td class="column-content" colspan=3>
  				 	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                  </td>
	  		  </tr>
		<%} %>



		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("FirstExcuteHumTask")){ %> 
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 8){%> 
			 
           <tr>
  				  <td class="column-title">
  				     <bean:message bundle="complaint" key="complaint.linkTransmitReason"/>
  				  </td>
  				  <td class="column-content" colspan=3>
  				 	 <pre><bean:write name="baselink" property="linkTransmitReason" scope="page"/></pre>
                  </td>
	  		  </tr>				 			 
			  <%} %>
			 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 1){%> 
			 
           <tr>
  				  <td class="column-title">
  				     <bean:message bundle="complaint" key="complaint.linkTransmitReason"/>
  				  </td>
  				  <td class="column-content" colspan=3>
  				 	 <pre><bean:write name="baselink" property="linkTransmitReason" scope="page"/></pre>
                  </td>
	  		  </tr>				 			 
			  <%} %>
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 46 || baselink.getOperateType().intValue() == 11) ){%> 
	  				  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.ndeptContact"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<pre><bean:write name="baselink" property="ndeptContact" scope="page"/></pre>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.ndeptContactPhone"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="ndeptContactPhone" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	
	  				  	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkIfInvokeChange"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<eoms:id2nameDB id="${baselink.linkIfInvokeChange}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkChangeSheetId"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="linkChangeSheetId" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	
	  				
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkFaultStartTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<bean:write name="baselink" property="linkFaultStartTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkFaultEndTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<bean:write name="baselink" property="linkFaultEndTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>	                  
		  		</tr>		  				  		
	  				  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkFaultGenerantPlace"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<pre><bean:write name="baselink" property="linkFaultGenerantPlace" scope="page"/></pre>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkPlaceDesc"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="linkPlaceDesc" scope="page"/></pre>
	                  </td>	                  
		  		</tr>		  				  		
	  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.issueEliminatTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				 	<bean:write name="baselink" property="issueEliminatTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>	  				  	
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.issueEliminatReason"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="issueEliminatReason" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	  		
	  				  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.dealResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				    	<pre><bean:write name="baselink" property="dealResult" scope="page"/></pre>
	                  </td>
		  		</tr>			  					  		 
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.dealDesc"/>
	  				  </td>
	  				  <td class="column-content" colspan="3">
	  				   	<pre><bean:write name="baselink" property="dealDesc" scope="page"/></pre>
	                  </td>
		  		
		  		
		  		</tr>	
		  	<tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.compProp"/>
	  				  </td>
	  				  <td class="column-content">
						<eoms:id2nameDB id="${baselink.compProp}" beanId="ItawSystemDictTypeDao"/>
	                 		 </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.isReplied"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.isReplied}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>	
		  		</tr>			  					  		 
	
                <tr>
	  				  <td class="column-title">
	  				     爱网络测试结果
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="aiNetResult" scope="page"/></pre>
	                  </td>
		  		</tr>
  		 
			  		 
			 <%} %>				 	 
			 	 
		  		 
		 <%} %>	 
	 	
		 			 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("SecondExcuteHumTask") ){ %> 
				<%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 8){%> 
			 
           <tr>
  				  <td class="column-title">
  				     <bean:message bundle="complaint" key="complaint.linkTransmitReason"/>
  				  </td>
  				  <td class="column-content" colspan=3>
  				 	 <pre><bean:write name="baselink" property="linkTransmitReason" scope="page"/></pre>
                  </td>
	  		  </tr>				 			 
			  <%} %>
		 <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 10){%> 
			 
           <tr>
  				  <td class="column-title">
  				     <bean:message bundle="complaint" key="complaint.linkTransmitReason1"/>
  				  </td>
  				  <td class="column-content" colspan=3>
  				 	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                  </td>
	  		  </tr>				 			 
			  <%} %>
			 
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 46 || baselink.getOperateType().intValue() == 11)  ){%> 

                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.ndeptContact"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<pre><bean:write name="baselink" property="ndeptContact" scope="page"/></pre>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.ndeptContactPhone"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="ndeptContactPhone" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	
	  				  	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkIfInvokeChange"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<eoms:id2nameDB id="${baselink.linkIfInvokeChange}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkChangeSheetId"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	 <pre><bean:write name="baselink" property="linkChangeSheetId" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	
	  				
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkFaultStartTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<bean:write name="baselink" property="linkFaultStartTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkFaultEndTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<bean:write name="baselink" property="linkFaultEndTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	                  </td>	                  
		  		</tr>		  				  		
	  				  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkFaultGenerantPlace"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	<pre><bean:write name="baselink" property="linkFaultGenerantPlace" scope="page"/></pre>
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkPlaceDesc"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="linkPlaceDesc" scope="page"/></pre>
	                  </td>	                  
		  		</tr>		  				  		
	  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.issueEliminatTime"/>
	  				  </td>
	  				  <td class="column-content">
	  				 	<bean:write name="baselink" property="issueEliminatTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>	  				  	
	                  </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.issueEliminatReason"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<pre><bean:write name="baselink" property="issueEliminatReason" scope="page"/></pre>
	                  </td>	                  
		  		</tr>	  		
	  				  		
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.dealResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
  				    	<pre><bean:write name="baselink" property="dealResult" scope="page"/></pre>
	                  </td>
		  		</tr>			  					  		 
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.dealDesc"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="dealDesc" scope="page"/></pre>
	                  </td>
		  		</tr>
	      <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.compProp"/>
	  				  </td>
	  				  <td class="column-content">
						<eoms:id2nameDB id="${baselink.compProp}" beanId="ItawSystemDictTypeDao"/>
	                 		 </td>	                 
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.isReplied"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.isReplied}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>	
		  		</tr>			  					  		 
	
                <tr>
	  				  <td class="column-title">
	  				     爱网络测试结果
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<pre><bean:write name="baselink" property="aiNetResult" scope="page"/></pre>
	                  </td>
		  		</tr>
		  		
		  		
		  		  				<!-- 湖北本地化字段 --> 
  				<tr>
  					 <td class="column-title">
	  				     回复人
	  				  </td>
					  <td class="column-content">

					  		<bean:write name="baselink" property="linkReplyPerson" />
					  </td>	
					  
					  <td class="column-title">
	  				     回复人联系电话
	  				  </td>
					  <td class="column-content">
					  		<pre><bean:write name="baselink" property="linkReplayPhone" scope="page"/></pre>
					  		
					  </td>					  
				</tr>
				<tr>
	  				  <td class="column-title">
	  				     处理经过（解释口径）
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				  	<pre><bean:write name="baselink" property="linkDealDesc" scope="page"/></pre>
	                  </td>
		  		</tr>			  					  		 
		  		
		  		<tr>
	  				  <td class="column-title">
	  				    	投诉是否解决
	  				  </td>
	  				  <td class="column-content" colspan="3">
							<eoms:id2nameDB id="${baselink.linkIsComplaintSolve}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	
	           </tr>
				<c:if test="${baselink.linkIsComplaintSolve == '1030101'}"> 
		  		<tr>
	  				  <td class="column-title">
	  				    	解决时间
	  				  </td>
	  				  <td class="column-content" colspan="3">
								<bean:write name="baselink" property="linkComplaintSolveDate" scope="page" formatKey="date.formatDateTimeAll"/>
	                  </td>	
	           </tr>
				</c:if>	
				<c:if test="${baselink.linkIsComplaintSolve == '1030102'}">
		  		<tr>
	  				  <td class="column-title">
	  				    	是否计划解决
	  				  </td>
	  				  <td class="column-content">
								<eoms:id2nameDB id="${baselink.linkPlanSolveTypeparent}" beanId="ItawSystemDictTypeDao"/>-<eoms:id2nameDB id="${baselink.linkPlanSolveType}" beanId="ItawSystemDictTypeDao"/>
	            </td>
	            <c:if test="${baselink.linkPlanSolveTypeparent == '101061601'}">
		  				  <td class="column-title">
		  				    	计划解决时间
		  				  </td>
		  				  <td class="column-content">
									<bean:write name="baselink" property="linkPlanSolveDate" scope="page" formatKey="date.formatDateTimeAll"/>
		            </td>	            	
	            </c:if>	
	           </tr>					
				</c:if>           	           
	           <tr>                 
	  				  <td class="column-title">
	  				    	用户是否确认或签署回执单
	  				  </td>
	  				  <td class="column-content" colspan="3">
	  				   	<eoms:id2nameDB id="${baselink.linkIsUserConfirm}" beanId="ItawSystemDictTypeDao"/>
	  				   	<c:if test="${baselink.linkIsUserConfirm == '1030102'}">
	  				   		<br>原因 : <pre><bean:write name="baselink" property="linkNoConfirmReason" scope="page"/></pre>
	  				   	</c:if>
	                  </td>	                  
		  		</tr>
		  		
		  		<tr>
	  				  <td class="column-title">
	  				     投诉是否重复投诉
	  				  </td>
	  				  <td class="column-content">
						<eoms:id2nameDB id="${baselink.linkIsRepeatComplaint}" beanId="ItawSystemDictTypeDao"/>
							<c:if test="${baselink.linkIsRepeatComplaint == '1030101'}">
	  				   			<br>重复投诉原因 : <eoms:id2nameDB id="${baselink.linkRepeatComplaintType}" beanId="ItawSystemDictTypeDao"/>
	  				   		</c:if>
	                  </td>	                 
	  				  <td class="column-title">
	  				    用户满意情况
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkIsUserStatisfied}" beanId="ItawSystemDictTypeDao"/>
		  				   	<c:if test="${baselink.linkIsReciveFaultId == '101061503'}">
		  				   		<br>不满意原因 : <pre><bean:write name="baselink" property="linkUserNoStatisfied" scope="page"/></pre>
		  				   	</c:if>
	                  </td>	                  
		  		</tr>

				<tr>
	  				  <td class="column-title">
	  				     是否联系用户
	  				  </td>
	  				  <td class="column-content" colspan="3">
						<eoms:id2nameDB id="${baselink.linkIsContactUser}" beanId="ItawSystemDictTypeDao"/>
							<c:if test="${baselink.linkIsContactUser == '1030101'}">
	  				   			<br>联系时间 : <bean:write name="baselink" property="linkContactDate" scope="page" formatKey="date.formatDateTimeAll"/>
	  				   			<br>联系方式 : <eoms:id2nameDB id="${baselink.linkContactship}" beanId="ItawSystemDictTypeDao"/>
	  				   			<br>联 系 人 : <bean:write name="baselink" property="linkContactUser" scope="page" />
	  				   			<br>联系电话 : <bean:write name="baselink" property="linkContactPhone" scope="page" />
	  				   		</c:if>
	  				   		<c:if test="${baselink.linkIsContactUser == '1030102'}">
	  				   			<br>未与用户联系原因 : <eoms:id2nameDB id="${baselink.linkNoContactReason}" beanId="ItawSystemDictTypeDao"/>
	  				   		</c:if>
	                  </td>	                       
		  		</tr>
				<tr>
	  				  <td class="column-title">
	  				     投诉涉及专业
	  				  </td>
	  				  <td class="column-content">
						<eoms:id2nameDB id="${baselink.linkSpecialty}" beanId="ItawSystemDictTypeDao"/>
	                 		 </td>	                 
	  				  <td class="column-title">
	  				     故障类别
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkQuality}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                  
		  		</tr>
				<tr>
	  				  <td class="column-title">
	  				     主覆盖小区CI
	  				  </td>
	  				  <td class="column-content">
						<bean:write name="baselink" property="linkAddressCI" />
	                 		 </td>	                 
	  				  <td class="column-title">
	  				     主覆盖小区名称
	  				  </td>
	  				  <td class="column-content">
	  				   	<bean:write name="baselink" property="linkAddressName" />
	                  </td>	                  
		  		</tr>
				<tr>
	  				  <td class="column-title">
	  				     设备类型
	  				  </td>
	  				  <td class="column-content">
						<bean:write name="baselink" property="linkEquipmentType" />
	                 		 </td>	                 
	  				  <td class="column-title">
	  				     设备类型厂家
	  				  </td>
	  				  <td class="column-content">
	  				   	<bean:write name="baselink" property="linkEquipmentFactory" />
	                  </td>	                  
		  		</tr>
				<tr>
	  				  <td class="column-title">
	  				     是否有告警
	  				  </td>
	  				  <td class="column-content" colspan="3">
						<eoms:id2nameDB id="${baselink.linkIsAlarm}" beanId="ItawSystemDictTypeDao"/>
							<c:if test="${baselink.linkIsAlarm == '1030101'}">
	  				   			<br>告警详情 : <bean:write name="baselink" property="linkAlarmDetail" scope="page" />
	  				   			<br>故障工单号 : <bean:write name="baselink" property="linkCommonfaultNumber" scope="page" />
	  				   		</c:if>
	                  </td>	                       
		  		</tr>
				<tr>
	  				  <td class="column-title">
	  				     弱覆盖三网测试情况
	  				  </td>
	  				  <td class="column-content" colspan="3">
	  				   		<br><eoms:id2nameDB id="${baselink.linkCoverDian}" beanId="ItawSystemDictTypeDao"/>
	  				   		<br><eoms:id2nameDB id="${baselink.linkCoverLian}" beanId="ItawSystemDictTypeDao"/>
	                  </td>	                       
		  		</tr>
			<!--湖北本地化需求增加字段  结束  -->	  					
		  		
			  		 
			 <%} %>				 	 

 
		 
		 <%} %>			 

	
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("CheckingHumTask") ){ %> 
		 	<%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 56 || baselink.getOperateType().intValue() == 54) ){%>
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkCheckResult"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	<eoms:id2nameDB id="${baselink.linkCheckResult}" beanId="ItawSystemDictTypeDao"/>
	                  </td>
		  		</tr>		 	
		 	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkCheckIdea"/>
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
					     <bean:message bundle="complaint" key="complaint.linkTransmitContent"/>
					  </td>
					  <td class="column-content" colspan=3>
					 	 <pre><bean:write name="baselink" property="linkTransmitContent" scope="page"/></pre>
	                </td>
	 		  </tr>				 			 
	
		 <%} %>
		 
 		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("DeferExamineHumTask") ){%> 
 		 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="complaint" key="complaint.linkExamineContent"/>
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
	  				     <bean:message bundle="complaint" key="complaint.linkUntreadReason"/>
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
		 
         <%if(baselink.getOperateType()!=null && ( baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7 || baselink.getOperateType().intValue() == -11) ){%> 
			 
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
	  				     <bean:message bundle="complaint" key="complaint.nodeAccessories"/>
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
		url: "complaint.do?method=getJsonLink&id="+id+"&beanName=Complaint",
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