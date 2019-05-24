<%@ include file="/common/taglibs.jsp"%>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink">  
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
  	     <eoms:dict key="dict-sheet-circuitdispatch" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="circuitdispatch" key="circuitdispatch.operateType"/>:
  	     <eoms:dict key="dict-sheet-circuitdispatch" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
  	     
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
  				<%if(baselink.getOperateRoleId()!=null && ! baselink.getOperateRoleId().equals("")) {%>
  				
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
  				     <bean:write name="baselink" property="operaterContact" scope="page"/>
                  </td>  
  			      <%}else{ %>
  				 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operaterContact"/>
  				  </td> 
                   <td class="column-content" colspan="3">
  				     <bean:write name="baselink" property="operaterContact" scope="page"/>
                  </td>
                <%} %>
                <tr>
		                <%if(baselink.getToOrgRoleId()!=null && ! baselink.getToOrgRoleId().equals("")) {%>  
		  				  <td class="column-title">
		  				     <bean:message bundle="circuitdispatch" key="circuitdispatch.operateType"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-circuitdispatch" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
		                  </td>
		  				  <td class="column-title">
		  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
  				           <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />&nbsp;&nbsp;
		  				<!--    <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" /> -->
		                  </td>	
		                  <%}else{%>
		  				  <td class="column-title">
		  				     <bean:message bundle="circuitdispatch" key="circuitdispatch.operateType"/>
		  				  </td>
		  				  <td class="column-content" colspan="3">
		  				   <eoms:dict key="dict-sheet-circuitdispatch" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
		                  </td>
		                  <%} %>
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
  				<%if(baselink.getTransferReason()!=null&&!baselink.getTransferReason().equals("")){ %>
		  				<tr>
	  						<td class="column-title">
		  				      <bean:message bundle="sheet" key="linkForm.transmitReason"/>
	  				  		</td>
	  				  		<td class="column-content" colspan="3">
	  				     		<pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
	                  		</td>                  
	  					</tr>
	  				<%} %>
                <tr>
                 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content" colspan='3'>
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				</tr>
  				<!-- 方案制定 -->
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ProjectCreateTask")){%>
		 		<%if(baselink.getOperateType().intValue()==124){%>
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="circuitdispatch" key="circuitdispatch.linkRejectReason"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				      <eoms:id2nameDB id="${baselink.linkRejectReason}" beanId="ItawSystemDictTypeDao" />
	  			      </td>
  				</tr>
		 		<%}else if(baselink.getOperateType().intValue()==110||baselink.getOperateType().intValue()==11){ %>
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="circuitdispatch" key="circuitdispatch.linkExecuteEndDate"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				      <bean:write name="baselink" property="linkExecuteEndDate" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	  			      </td>
  				</tr>
	
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="circuitdispatch" key="circuitdispatch.linkInvolvedProvince"/>
	  				  </td>
	  				  <td class="column-content">
	  				  <bean:write name="baselink" property="linkInvolvedProvince" scope="page"/>
	                  </td>
	  				  <td class="column-title">
	  				     <bean:message bundle="circuitdispatch" key="circuitdispatch.linkInvolvedCity"/>
	  				  </td>
	  				  <td class="column-content">
	  				   	<eoms:id2nameDB id="${baselink.linkInvolvedCity}" beanId="tawSystemAreaDao" />
	                  </td>
		  		</tr>	
		     	<tr>
		     		<!-- 技术方案关键字 -->
		     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectKey"/>*</td>
		     		<td>
		    			<bean:write name="baselink" property="linkProgrammeKey" scope="page"/>
					  </td>
     		       <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.mainResProjectNo"/></td>
     		           <td>
     		           <bean:write name="sheetMain" property="mainResourceNo" scope="request"/>
			          </td>					  
		     	</tr>
		     	<tr>
		     		<!-- 风险评估 -->
		     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkRiskEvaluate"/>*</td>
		     		<td>
		     			<pre><bean:write name="baselink" property="linkRiskEvaluate" scope="page"/></pre>
					  </td>
		     		<!-- 影响业务分析 -->
		     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkOperationConstrue"/>*</td>
		     		<td>
		     			<pre><bean:write name="baselink" property="linkOperationConstrue" scope="page"/></pre>
					  </td>
		     	</tr>
		     	<tr>
		     		<!-- 技术方案说明 -->
		     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectExplain"/>*</td>
		     		<td colspan="3">
		     			<pre><bean:write name="baselink" property="linkProgrammeExplain" scope="page"/></pre>
					  </td>
				</tr>
				<%if(baselink.getNodeAccessories()!=null&&!baselink.getNodeAccessories().equals("")){%>
		     	<tr>
		     		<!-- 技术方案附件 -->
		     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectAcc"/></td>
		     		<td colspan="3">
							<eoms:attachment name="baselink" property="nodeAccessories" 
				            scope="page" idField="nodeAccessories" appCode="circuitdispatch" 
				             viewFlag="Y"/>
					  </td>
				</tr>
		 <%} } }%>	 
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PermitTask")&&(baselink.getOperateType().intValue()==111||baselink.getOperateType().intValue()==121||baselink.getOperateType().intValue()==122||baselink.getOperateType().intValue()==55)){%> 
        <tr>
     		<!-- 审批结果 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPermitResult"/>*</td>
     		<td colspan="3">
     			<eoms:id2nameDB id="${baselink.linkPermitResult}" beanId="ItawSystemDictTypeDao" />
			  </td>
		</tr>
        <tr>
     		<!-- 审批意见 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPermitIdea"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkPermitIdea" scope="page"/></pre>
			  </td>
		</tr>
		<%if(baselink.getNodeAccessories()!=null&&baselink.getNodeAccessories().equals("")==false){%>
     	<tr>
     		<!-- 附件 -->
     		<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="baselink" property="nodeAccessories" 
		            scope="page" idField="nodeAccessories" appCode="circuitdispatch" 
		             viewFlag="Y"/>
			  </td>
		</tr>
		 
		 <%} }%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PlanTask")&&(baselink.getOperateType().intValue()==112||baselink.getOperateType().intValue()==11)){%> 
     	<tr>
     		<!-- 实施负责人 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcutePrincipal"/>*</td>
     		<td>
    			<bean:write name="baselink" property="linkExcutePrincipal" scope="page"/>
			  </td>
     		<!-- 联系方式 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkContact"/>*</td>
     		<td>
    			<bean:write name="baselink" property="linkContact" scope="page"/>
			  </td>
     	</tr>
		   <tr>
		   <!-- 计划开始时间 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanStartDate"/>*</td>
			  <td>
			    <bean:write name="baselink" property="linkPlanStartDate" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
			  </td>
			  <!-- 计划结束时间 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanEndDate"/>*</td>
			  <td>
			    <bean:write name="baselink" property="linkPlanEndDate" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
			  </td>
			</tr>
            <!-- 是否影响业务 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfAffectOperation"/>*</td>
			  <td>
			  	 <eoms:id2nameDB id="${baselink.linkIfAffectOperation}" beanId="ItawSystemDictTypeDao" />
			  </td>
			  <!-- 是否通知客服 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfNotify"/>*</td>
			  <td>
			  	<eoms:id2nameDB id="${baselink.linkIfNotify}" beanId="ItawSystemDictTypeDao" />
			  </td>
			</tr>
			<tr>
			<!-- 涉及业务部门 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkReferOperateDept"/>*</td>
			  <td colspan="3">
			  	<eoms:id2nameDB id="${baselink.linkReferOperateDept}" beanId="ItawSystemDictTypeDao" />
			  </td>
			</tr>
        <tr>
     		<!-- 影响网元范围 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkNetAffectArea"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkNetAffectArea" scope="page"/></pre>
			  </td>
		</tr>
        <tr>
     		<!-- 影响业务情况 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectSituation"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkAffectSituation" scope="page"/></pre>
			  </td>
		</tr>
        <tr>
     		<!-- 影响网管情况 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectNetManage"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkAffectNetManage" scope="page"/></pre>
			  </td>
		</tr>
		<%if(baselink.getNodeAccessories()!=null&&baselink.getNodeAccessories().equals("")==false){%>
     	<tr>
     		<!-- 实施方案 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExecuteAcc"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="baselink" property="nodeAccessories" 
              scope="page" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" viewFlag="Y"/>
			  </td>
		</tr>
		 
		 <%} }%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExecuteTask")&&(baselink.getOperateType().intValue()==113||baselink.getOperateType().intValue()==11)){%> 
     		<tr>
            <!-- 实施结果 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteResult"/>*</td>
			  <td colspan="3">
			  	 <eoms:id2nameDB id="${baselink.linkExcuteResult}" beanId="ItawSystemDictTypeDao" />
			  </td>
			  <!-- 测试结果
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkTestResult"/>*</td>
			  <td>
			  	<eoms:id2nameDB id="${baselink.linkTestResult}" beanId="ItawSystemDictTypeDao" />
			  </td> -->
			</tr>
			<tr>
			<!-- 是否完全按照方案实施 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIsAccordanceProgramme"/>*</td>
			  <td>
			  	<eoms:id2nameDB id="${baselink.linkIsAccordanceProgramme}" beanId="ItawSystemDictTypeDao" />
			  </td>
			  <!-- 失败原因 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkFailedReason"/>*</td>
			  <td>
			  	<eoms:id2nameDB id="${baselink.linkFailedReason}" beanId="ItawSystemDictTypeDao" />
			  </td>
			</tr>
        <tr>
     		<!-- 实施情况说明 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteExplain"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkExcuteExplain" scope="page"/></pre>
			  </td>
		</tr>
		<tr>
     		<!-- 影响业务情况说明 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectOperationExplain"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkAffectOperationExplain" scope="page"/></pre>
			  </td>
		</tr>
		<tr>
     		<!-- 告警情况记录 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAlarmRecord"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkAlarmRecord" scope="page"/></pre>
			  </td>
		</tr>
			<!--<tr>
     	 统计报告异常说明 
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkReportAbnormityExplain"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkReportAbnormityExplain" scope="page"/></pre>
			  </td>
		</tr>-->
		<%if(baselink.getNodeAccessories()!=null&&!baselink.getNodeAccessories().equals("")){%>
     	<tr>
     		<!-- 测试报告 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkTestReport"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="baselink" property="nodeAccessories" 
              scope="page" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" viewFlag="Y"/>
			  </td>
		</tr>
		 
		 <%} }%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ValidateTask")&&(baselink.getOperateType().intValue()==114||baselink.getOperateType().intValue()==11)){%> 
		<tr>
            <!-- 是否需要更新作业计划 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfUpdatePlan"/>*</td>
			  <td>
			  	 <eoms:id2nameDB id="${baselink.linkIfUpdatePlan}" beanId="ItawSystemDictTypeDao" />
			  </td>
			  <!-- 资源库电路数据更新 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkCircuitUpdate"/>*</td>
			  <td>
			  	<eoms:id2nameDB id="${baselink.linkCircuitUpdate}" beanId="ItawSystemDictTypeDao" />
			  </td>
			</tr>
			<tr>
				<!-- 是否涉及工程交维 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIsToMaintenance"/>*</td>
			  <td colspan="3">
			  	<eoms:id2nameDB id="${baselink.linkIsToMaintenance}" beanId="ItawSystemDictTypeDao" />
			  </td>
			</tr>
		<tr>
     		<!-- 执行情况分析 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteConstrue"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkExcuteConstrue" scope="page"/></pre>
			  </td>
		</tr>
		<tr>
     		<!-- 作业计划更新建议 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanUpdateIdea"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkPlanUpdateIdea" scope="page"/></pre>
			  </td>
		</tr>
		<tr>
     		<!-- 后续工作安排 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkWorkPlan"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkWorkPlan" scope="page"/></pre>
			  </td>
		</tr>
		<tr>
     		<!-- 交维描述 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkMaintenanceDes"/>*</td>
     		<td colspan="3">
     			<pre><bean:write name="baselink" property="linkMaintenanceDes" scope="page"/></pre>
			  </td>
		</tr>
		<%if(baselink.getNodeAccessories()!=null&&!baselink.getNodeAccessories().equals("")){%>
     	<tr>
     		<!-- 业务侧资源数据反馈 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkServiceResData"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="baselink" property="nodeAccessories" 
              scope="page" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" viewFlag="Y"/>
			  </td>
		</tr>
		 
		 <%}} %>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("HoldTask")){%> 
		 
		 
		 <%} %>
			 <%if(baselink.getRemark()!=null&&!baselink.getRemark().equals("")){ %>
                <tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
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
		url: "circuitdispatch.do?method=getJsonLink&id="+id+"&beanName=CircuitDispatch",
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