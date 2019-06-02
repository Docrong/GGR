<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.boco.eoms.sheet.softchange.model.SoftChangeMain"%>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 String roleName="";
  SoftChangeMain main = new SoftChangeMain();
  if(request.getAttribute("sheetMain")!=null){
    main = (SoftChangeMain)request.getAttribute("sheetMain");
   }
   long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %> 
<script type="text/javascript" src="${app}/scripts/util/util.js"></script>
<%@ include file="/WEB-INF/pages/wfworksheet/softchange/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		<!--  <caption><bean:message bundle="softchange" key="softchange.header"/></caption>-->
     <input type="hidden" name="${sheetPageName}beanId" value="iSoftChangeMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.softchange.model.SoftChangeMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.softchange.model.SoftChangeLink"/>
   	<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
    <input type="hidden" id="${sheetPageName}mainNetTypeOne" name="${sheetPageName}mainNetTypeOne" value="${sheetMain.mainNetTypeOne}"/>
    <input type="hidden" id="${sheetPageName}mainNetTypeTwo" name="${sheetPageName}mainNetTypeTwo" value="${sheetMain.mainNetTypeTwo}"/>
    <input type="hidden" id="${sheetPageName}mainNetTypeThree" name="${sheetPageName}mainNetTypeThree" value="${sheetMain.mainNetTypeThree}"/>
    <input type="hidden" id="${sheetPageName}mainFactory" name="${sheetPageName}mainFactory" value="${sheetMain.mainFactory}"/>
    <c:if test="${taskName != 'HoldTask' }">
    <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    </c:if>
    <input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <%if(taskName.equals("ProjectCreateTask")) {
            	if(operateType.equals("110")|| operateType.equals("11")){%>
            	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	 
	               <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>       
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCompleteLimitTime"/>*</td>
		   			<td class="content"><input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}linkCompleteLimitTime" readonly="readonly" alt="allowBlank:false" 
		   			value="${eoms:date2String(sheetLink.linkCompleteLimitTime)}" id="${sheetPageName}linkCompleteLimitTime" />
                    </td>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkDesignKey"/>*</td>
		             <td class="content"> <input type="text"  class="text" name="${sheetPageName}linkDesignKey" id="${sheetPageName}linkDesignKey" value="${sheetLink.linkDesignKey}" alt="allowBlank:false,maxLength:25,vtext:'请填入技术方案关键字，最多输入25字'"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkDesignComment"/></td>
		              <td colspan="3" class="content"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkDesignComment" id="${sheetPageName}linkDesignComment" alt="width:500,allowBlank:true,maxLength:2000,vtext:'请填入技术方案说明，最多输入2000字'">${sheetLink.linkDesignComment}</textarea>
                    </td>
		          </tr>
						      <tr>
			      	<!-- 变更涉及省份 -->
		            <td  class="label">
		            涉及省份及地市*</td>
		              <td colspan="3">					  
    				  <input type="button" id="areabtn" value="选择涉及省份和地市" class="btn"/>
    				  
    			
    				  <br/><br/>	
    				  涉及省份:<textarea class="textarea max" readonly="true" name="linkInvolvedProvince" style="height:50px" id="${sheetPageName}linkInvolvedProvince"  alt="allowBlank:false,maxLength:2000,vtext:'请填入备注信息，最多输入2000字符'">${sheetLink.linkInvolvedProvince }</textarea><br/>
    				  涉及地市:<textarea class="textarea max" readonly="true" name="linkInvolvedCity" style="height:50px" id="${sheetPageName}linkInvolvedCity" alt="allowBlank:true,maxLength:2000,vtext:'请填入备注信息，最多输入2000字符'">${sheetLink.linkInvolvedCity}</textarea>
                    
<script type="text/javascript">
Ext.onReady(function(){
function callback(jsonData,str){
	var baseFlag = 2, shengNameArr=[], shengIdArr=[],shiNameArr=[], shiIdArr=[];
	eoms.log(jsonData);
	Ext.each(jsonData,function(data){
		switch(data.id.length){
			case 1 :
			  shengNameArr.push(data.name);
			  shengIdArr.push(data.id);
			  break;
			case baseFlag :
			  shengNameArr.push(data.name);
			  shengIdArr.push(data.id);
			  break;
			case baseFlag + 1 :
			  shiNameArr.push(data.name);
			  shiIdArr.push(data.id);
			  break;
			case baseFlag + 2 :
			  shiNameArr.push(data.name);
			  shiIdArr.push(data.id);
			  break;
		}
	});
	$('${sheetPageName}linkInvolvedProvince').value = shengNameArr.join(",");
	$('${sheetPageName}linkInvolvedCity').value = shiNameArr.join(",");
	//$('province-name').value = shengNameArr.join(",");
	//$('city-name').value = shiNameArr.join(",");
}
	  var treeAction='${app}/area/tawSystemAreas.do?method=getNodes';
	  var cfg = {
		btnId:'areabtn',
		baseAttrs:{checked:false},
		treeDataUrl:treeAction,
		treeRootId:'-1',
		treeRootText:'地域树图',
		treeChkMode:'',
		treeChkType:'area',
		callback:callback
	}
	var areaTree = new xbox(cfg);
	areaTree.onBeforeCheck = function(node,checked){
		if(checked && node.parentNode){
			node.parentNode.getUI().toggleCheck(true);
		}
		return true;
	}
});
</script>                     
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkRiskEstimate"/>*</td>
		              <td colspan="3" class="content"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkRiskEstimate" id="${sheetPageName}linkRiskEstimate" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入风险评估，最多输入2000字'">${sheetLink.linkRiskEstimate}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkEffectAnalyse"/>*</td>
		              <td colspan="3" class="content"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkEffectAnalyse" id="${sheetPageName}linkEffectAnalyse" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入影响业务分析，最多输入2000字'">${sheetLink.linkEffectAnalyse}</textarea>
                    </td>
		          </tr>

		             <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">					
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
					    </td>
		           </tr>
		          
	<tr>
		  <td class="label"><bean:message bundle="softchange" key="softchange.linkProjectAccessories" /></td>
		  <td colspan="3">
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="softchange" />
   
		  </td>
		</tr>
		          
	 <%}}%>
	 
	 
	 <% if(taskName.equals("AuditTask")){
	            if(operateType.equals("130")|| operateType.equals("55")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	        
 					<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="" />
					<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="" />
					<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="" />
	                <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsCheck"/>*</td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIsCheck" id="${sheetPageName}linkIsCheck" 
            	      initDicId="1010907"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkIsCheck}" onchange="ifAuditPass(this.value);" />
			        </td>	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCheckComment"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkCheckComment" id="${sheetPageName}linkCheckComment" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入审核意见，最多输入2000字'">${sheetLink.linkCheckComment}</textarea>
                    </td>
		          </tr>
	<%}}%>
	    <% if(taskName.equals("PermitTask")){
	              if(operateType.equals("131")|| operateType.equals("55")){%>
	              <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PlanTask" />
	              <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	        
 					<% 
                      Calendar limitDay = Calendar.getInstance();
		              limitDay.set(2009,7,11);
                      Calendar sendTime = Calendar.getInstance();
			          sendTime.set(main.getSendYear(),main.getSendMonth(),main.getSendDay());
			          if(sendTime.compareTo(limitDay) >=0 ){ 
                    %>
 					<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="" />
					<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="" />
					<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="" />
	               <%} %>
	               <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td> 
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr> 
	                <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkPermitResult"/>*</td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult" 
            	      initDicId="1010908"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkPermitResult}" onchange="ifAuditPass1(this.value);" />
			        </td>	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkPermitIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkPermitIdea" id="${sheetPageName}linkPermitIdea" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入审批意见，最多输入2000字'">${sheetLink.linkPermitIdea}</textarea>
                    </td>
		          </tr>
				  <tr>
		  <td class="label">附件</td>
		  <td colspan="3">
		
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="softchange" />   
		  </td>
		</tr>

	<%}}%>
	    <% if(taskName.equals("PlanTask")){
	                if(operateType.equals("113")|| operateType.equals("11")){%>
	               <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask" />
	              <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	        
	              <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr> 
	              <tr>
			     		<!-- 排程看板 -->
			     		<td class="label"><bean:message bundle="softchange" key="softchange.planSheet"/></td>
			     		<td colspan="3">
			     			<a href="#" onclick="popModifyTime()"><bean:message bundle="softchange" key="softchange.lookPlanSheet"/></a>
						 </td>
		        </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkManager"/>*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}linkManager" id="${sheetPageName}linkManager"  value="${sheetLink.linkManager}" alt="allowBlank:false,maxLength:25,vtext:'请填入实施负责人，最多输入25字'"/></td>		
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkContact"/>*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}linkContact" id="${sheetPageName}linkContact"  value="${sheetLink.linkContact}" alt="allowBlank:false,maxLength:25,vtext:'请填入联系方式，最多输入25字'"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkPlanStartTime"/>*</td>
		                 <td>
		   				 <input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}linkPlanStartTime"  readonly="readonly"   
		   				 alt="vtype:'lessThen',link:'${sheetPageName}linkPlanEndTime',vtext:'计划开始时间不能晚于计划结束时间',allowBlank:false" 
		   				 value="${eoms:date2String(sheetLink.linkPlanStartTime)}" id="${sheetPageName}linkPlanStartTime" />
		                </td>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkPlanEndTime"/></td>
		                <td>
		   				 <input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}linkPlanEndTime"  readonly="readonly" 
		   				 alt="vtype:'moreThen',link:'${sheetPageName}linkPlanStartTime',vtext:'计划结束时间不能早于计划开始时间',allowBlank:false" 
		   				 value="${eoms:date2String(sheetLink.linkPlanEndTime)}" id="${sheetPageName}linkPlanEndTime" />
		                </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCellInfo"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkCellInfo" id="${sheetPageName}linkCellInfo" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入影响网元范围，最多输入2000字'">${sheetLink.linkCellInfo}</textarea>
                    </td>
		          </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsEffectBusiness"/></td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIsEffectBusiness" id="${sheetPageName}linkIsEffectBusiness" 
            	      initDicId="10301"  alt="allowBlank:true" defaultValue="${sheetLink.linkIsEffectBusiness}" styleClass="select-class" onchange="ifAffect(this.value)"/>
			        </td>	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkEffectCondition"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkEffectCondition" id="${sheetPageName}linkEffectCondition" alt="width:500,allowBlank:true,maxLength:2000,vtext:'请填入影响业务范围及时长，最多输入2000字'">${sheetLink.linkEffectCondition}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkNetManage"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkNetManage" id="${sheetPageName}linkNetManage" alt="width:500,allowBlank:true,maxLength:2000 ,vtext:'请填入影响网管情况，最多输入2000字'">${sheetLink.linkNetManage}</textarea>
                    </td>
		          </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkBusinessDept"/></td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkBusinessDept" id="${sheetPageName}linkBusinessDept" 
            	      initDicId="1010902"  alt="allowBlank:true" defaultValue="${sheetLink.linkBusinessDept}" styleClass="select-class"/>
			        </td>	                
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsSendToFront"/></td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkIsSendToFront" id="${sheetPageName}linkIsSendToFront" 
            	      initDicId="10301"  alt="allowBlank:true" defaultValue="${sheetLink.linkIsSendToFront}" styleClass="select-class"/>
			        </td>	                
		            </tr>
		             <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">					
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
					    </td>
		           </tr>
	            
	<tr>
		  <td class="label"><bean:message bundle="softchange" key="softchange.linkExecuteAccessories" /></td>
		  <td colspan="3">
		
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="softchange" />   
		  </td>
		</tr>
	<%}}%>
	    
	    <% if(taskName.equals("ExecuteTask")){
	         if(operateType.equals("114")|| operateType.equals("11")){%>
	         	  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask" />
	              <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	        
	              <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="invokeProcess" />
	               <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr> 
	                <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkCutResult"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkCutResult" id="${sheetPageName}linkCutResult" 
            	      initDicId="1010903"  alt="allowBlank:false" defaultValue="${sheetLink.linkCutResult}" styleClass="select-class" onchange="executeResult(this.value);"/>
			            </td>
			            <td  class="label"><bean:message bundle="softchange" key="softchange.linkFailedReason"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkFailedReason" id="${sheetPageName}linkFailedReason" 
            	      initDicId="1010910"  alt="allowBlank:false" defaultValue="${sheetLink.linkFailedReason}" styleClass="select-class"/>
			            </td>
			         </tr>   
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCutComment"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkCutComment" id="${sheetPageName}linkCutComment" alt="width:500,allowBlank:false,maxLength:2000 ,vtext:'请填入实施情况说明，请最多输入2000字'">${sheetLink.linkCutComment}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkBusinessComment"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkBusinessComment" id="${sheetPageName}linkBusinessComment" alt="width:500,allowBlank:false,maxLength:2000 ,vtext:'请填入影响业务情况说明，请最多输入2000字'">${sheetLink.linkBusinessComment}</textarea>
                    </td>
		          </tr>
	                <tr>
	                <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsPlan"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkIsPlan" id="${sheetPageName}linkIsPlan" 
            	      initDicId="10301"  alt="allowBlank:false" defaultValue="${sheetLink.linkIsPlan}" styleClass="select-class"/>
			        </td>
	                
		             <td  class="label"><bean:message bundle="softchange" key="softchange.linkTestResult"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" 
            	      initDicId="1010904"  alt="allowBlank:false" defaultValue="${sheetLink.linkTestResult}" styleClass="select-class"/>
			        </td>	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkAlertRecord"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAlertRecord" id="${sheetPageName}linkAlertRecord" alt="width:500,allowBlank:false,maxLength:2000 ,vtext:'请填入告警情况记录，请最多输入2000字'">${sheetLink.linkAlertRecord}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkUnnormalComment"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkUnnormalComment" id="${sheetPageName}linkUnnormalComment" alt="width:500,allowBlank:false,maxLength:2000 ,vtext:'请填入统计报告异常说明，请最多输入2000字'">${sheetLink.linkUnnormalComment}</textarea>
                    </td>
		          </tr>
		             <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">					
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
					    </td>
		           </tr>

		          
  	<tr>
		  <td class="label"><bean:message bundle="softchange" key="softchange.linkTestReport" />*</td>
		  <td colspan="3">
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" alt="allowBlank:false" idField="${sheetPageName}nodeAccessories" appCode="softchange" /> 
		  </td>
		</tr>
	<%}}%>
	    <% if(taskName.equals("ValidateTask")){
	            if(operateType.equals("115")|| operateType.equals("11")){%>
	             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
	             <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	  
			      <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr> 
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkCutAnalyse"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkCutAnalyse" id="${sheetPageName}linkCutAnalyse" alt="width:500,allowBlank:true,maxLength:2000 ,vtext:'请填入执行情况分析，最多输入2000字'">${sheetLink.linkCutAnalyse}</textarea>
                    </td>
		          </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsUpdateWork"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkIsUpdateWork" id="${sheetPageName}linkIsUpdateWork" 
            	      initDicId="10301"  alt="allowBlank:false" defaultValue="${sheetLink.linkIsUpdateWork}" styleClass="select-class"/>
			        </td>	                
		                 <td  class="label"><bean:message bundle="softchange" key="softchange.linkIsNeedProject"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}linkIsNeedProject" id="${sheetPageName}linkIsNeedProject" 
            	      initDicId="10301"  alt="allowBlank:false" defaultValue="${sheetLink.linkIsNeedProject}" styleClass="select-class"/>
			        </td>	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkWorkUpdateAdvice"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkWorkUpdateAdvice" id="${sheetPageName}linkWorkUpdateAdvice" alt="width:500,allowBlank:true,maxLength:2000 ,vtext:'请填入作业计划更新建议，最多输入2000字'">${sheetLink.linkWorkUpdateAdvice}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkSoftVersionUpdate"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkSoftVersionUpdate" id="${sheetPageName}linkSoftVersionUpdate" alt="width:500,allowBlank:false,maxLength:2000 ,vtext:'请填入资源库软件版本数据更新，请最多输入2000字'">${sheetLink.linkSoftVersionUpdate}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkNextPlan"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkNextPlan" id="${sheetPageName}linkNextPlan" alt="width:500,allowBlank:true,maxLength:2000 ,vtext:'请填入后续工作安排，最多输入2000字'">${sheetLink.linkNextPlan}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.linkProjectComment"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkProjectComment" id="${sheetPageName}linkProjectComment" alt="width:500,allowBlank:false,maxLength:2000 ,vtext:'请填入交维描述，请最多输入2000字'">${sheetLink.linkProjectComment}</textarea>
                    </td>
		          </tr>
	<%}} %>
	
	
	   <%if(taskName.equals("HoldTask")) {%>  
 
			<%if(operateType.equals("18")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="115" />
     	<%String parentProcessName=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName")); 
     	  if(parentProcessName.equals("iNetChangeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToNetChange" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="NetChange" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExecuteTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessPilotMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="callProcess" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessPilot" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="pilot" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessOperationMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="callProcess" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessOperation" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="operate" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessBackoutMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessBackout" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessDredgeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessDredge" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessChangeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessChange" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iResourceAffirmMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="ResourceAffirm" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iSecurityDealMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToSecurityDeal" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="SecurityDeal" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="PerformTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" /> 
     	<%}else if(parentProcessName.equals("iGreatEventMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToGreatEvent" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="GreatEvent" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="PerformTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" /> 
     	<%}else{%>
     		<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
     	<%}%>	     	
			 <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
		    <td colspan="3">
		      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
		        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select" alt="allowBlank:false"/>
		    </td>
		  </tr>		  
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:2000,vtext:'请填入信息，最多输入2000字符'" id="${sheetPageName}endResult"  class="textarea max">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>	
		  <tr>
		  	<td class="label"><bean:message bundle="softchange" key="softchange.mainIfDemonstrateCase"/>*</td>
		     <td>  
				        <eoms:comboBox name="${sheetPageName}mainIfDemonstrateCase" id="${sheetPageName}mainIfDemonstrateCase" 
            	      initDicId="1010912"   alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainIfDemonstrateCase}" onchange="ifCase(this.value);"/>
			        </td>
			  <td colspan="2">  
			 	<input type="button" value="网元入库" onclick="addNet()" class="btn">
			 	<script type="text/javascript">
	              function addNet(){
	              	var url  = "${app}/repository/tawLocalRepositoryUps.do?method=update&sheetId=${sheetMain.sheetId}";
	              	Ext.Ajax.request({
	              		url:url,
	              		success:function(v,x){
	              			var json = eoms.JSONDecode(v.responseText);
	              			alert(json.message);
	              		}
	              	});
	              }
               </script>
			 </td>
		  </tr>		  
		  <tr id="ifDcase">
		  	<td class="label"><bean:message bundle="softchange" key="softchange.mainCaseKeywords"/>*</td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}mainCaseKeywords" alt="allowBlank:false,maxLength:100,vtext:'请填入案例关键字，最多输入100字符'" id="${sheetPageName}mainCaseKeywords"  class="textarea max">${sheetMain.mainCaseKeywords}</textarea>
		    </td>
		  </tr>	
<script type="text/javascript">
      var v1 = eoms.form;	
	  function ifCase(CaseKeywords){
		if(CaseKeywords=='101091201'){
		    v1.enableArea('ifDcase');
		}else{
			v1.disableArea('ifDcase',true);
		}		
	}
	ifCase($('${sheetPageName}mainIfDemonstrateCase').value);

</script>		       			     	
     	    <%} }%>
     	
      <% if(taskName.equals("cc")){%>    
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'请填入信息，最多输入2000字符'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
          <%} %> 
         <%if(taskName.equals("ProjectCreateTask")||taskName.equals("AuditTask")||taskName.equals("PermitTask")||taskName.equals("PlanTask")||taskName.equals("ExecuteTask")||taskName.equals("ValidateTask")) {%>      
         
         <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />
		<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>							
    <%-- 	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:2000,vtext:'请最多输入2000字符'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	--%>
		
		<%} }%>
		<%if(operateType.equals("4")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
			
 	        <c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${fPreTaskName == 'DraftTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${fPreTaskName == 'RejectHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose> 		
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'请填入信息，最多输入2000字符'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>
		
		
		
  </table>
    
   	 <%if(taskName.equals("cc")) {%>	
		<fieldset id="link4">
	<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
		</fieldset>					
	 <%} %>   
	 
	 
	 <%if(taskName.equals("ProjectCreateTask")) {
	  	   if(operateType.equals("110")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				 <bean:message bundle="softchange" key="role.changeAdmin"/>		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="255" flowId="43" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>	
	<fieldset id="link5">
	 <legend>
			排程角色:
			<span id="roleName"><bean:message bundle="softchange" key="role.netSchemer"/></span>
			
	 </legend>
		 <div ID="permit2">  
			<eoms:chooser id="sendRole2" type="role" roleId="256" flowId="43" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
				category="[{id:'${sheetPageName}extendPerformer',childType:'user,subrole',limit:'none',text:'排程',allowBlank:false,vtext:'请选择排程人'}]"
				data="${sendUserAndRoles}"/>
		</div>
	</fieldset>	
	 <%}} %>
	 
	 	 <%if(taskName.equals("AuditTask")) {
	  	   if(operateType.equals("130")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"></span>		 
		 </legend>		
        <div id="audit" display="none"> 
			<eoms:chooser id="sendRole" type="role" roleId="254" flowId="43" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		  </div>
		</fieldset>		
	 <%}} %>
	 
	 
	 	 <%if(taskName.equals("PermitTask")) {
	  	   if(operateType.equals("131")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"></span>	 
		 </legend>		
		<div id="permit" display="none"> 
		<% 
             Calendar limitDay = Calendar.getInstance();
		     limitDay.set(2009,7,11);
             Calendar sendTime = Calendar.getInstance();
			 sendTime.set(main.getSendYear(),main.getSendMonth(),main.getSendDay());
			 if(sendTime.compareTo(limitDay) <0 ){
           %>
            
		   <eoms:chooser id="sendRole2" type="role" roleId="247" flowId="78" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',limit:'none',text:'派发',vtext:'请选择派发对象'}]"/>	
		
		  
		   <%} %>
		</div>
		</fieldset>		
	 <%}} %>
	 
	 <%if(taskName.equals("PlanTask")) {
	  	   if(operateType.equals("113")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                 <bean:message bundle="softchange" key="role.netoperater"/>
		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="257" flowId="43" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%}} %>
	 
	 
	 	 <%if(taskName.equals("ExecuteTask")) {
	  	   if(operateType.equals("114")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                 <bean:message bundle="softchange" key="role.changeAdmin"/>
		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="255" flowId="43" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%}} %>
	 
	 	 
	 
	 	 <%if(taskName.equals("ValidateTask")) {
	  	   if(operateType.equals("115")){ %>	
             <input type="hidden" name="${sheetPageName}dealPerformer" value="${sheetMain.sendRoleId}"/> 
             <input type="hidden" name="${sheetPageName}dealPerformerType" value="subrole"/> 
             <input type="hidden" name="${sheetPageName}dealPerformerLeader" value="${sheetMain.sendUserId}"/> 
	 <%}} %>
	 
	
<script type="text/javascript">
 var v1 = eoms.form;
    function popModifyTime(){
		var height = window.screen.height/6;
   	 	var width = window.screen.width/4;
    	features = "dialogWidth:"+1024+"px;dialogHeight:"+768+"px; scroll:2; help:0; status:No; fullscreen;";
    	features += "dialogLeft:" + width + ";dialogTop:" + height;
		window.open('${app}/sheet/modifyTime/modifyTime.do?method=xquery','PlanSheet', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	}
  function ifAuditPass(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101090701"){
		chooser_sendRole.enable();
		//审核通过到审批
		$('${sheetPageName}phaseId').value='PermitTask';
		$('${sheetPageName}operateType').value='111';
		$('roleName').innerHTML="变更管理负责人";
			$('${sheetPageName}dealPerformer').disabled=true;
			$('${sheetPageName}dealPerformerLeader').disabled=true;
			$('${sheetPageName}dealPerformerType').disabled=true;
	} else if(input=="101090702"){
		//审核不通过到方案制定  
		chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='ProjectCreateTask';
		$('${sheetPageName}operateType').value='123';
			$('${sheetPageName}dealPerformer').disabled=false;
			$('${sheetPageName}dealPerformerLeader').disabled=false;
			$('${sheetPageName}dealPerformerType').disabled=false;
			$('${sheetPageName}dealPerformer').value='${fOperateroleid}';
			$('${sheetPageName}dealPerformerLeader').value='${ftaskOwner}';
			$('${sheetPageName}dealPerformerType').value='${fOperateroleidType}';
		$('roleName').innerHTML="变更管理员与技术组";
	} 
	}
	}
	function ifAuditPass1(input){
	if('<%=operateType%>'!='55'){
	if(input=="101090801"){
		//审批通过到排程  
		//chooser_sendRole.enable();
		$('${sheetPageName}phaseId').value='PlanTask';
		$('${sheetPageName}operateType').value='112';
		$('${sheetPageName}dealPerformer').value='';
		$('${sheetPageName}dealPerformerLeader').value='';
		$('${sheetPageName}dealPerformerType').value='';
		$('roleName').innerHTML="网络配置管理员";
		selectLimit('PlanTask');
	} else if(input=="101090802"){
		//审批不通过到方案制定   
		//v1.disableArea('permit',true);
		//chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='ProjectCreateTask';
		$('${sheetPageName}operateType').value='121';
			$('${sheetPageName}dealPerformer').value='${ProjectCreateTaskOperateroleid}';
			$('${sheetPageName}dealPerformerLeader').value='${ProjectCreateTaskTaskOwner}';
			$('${sheetPageName}dealPerformerType').value='${ProjectCreateTaskOperateroleidType}';
		$('roleName').innerHTML="变更管理员与技术组";
		selectLimit('ProjectCreateTask');
	} else if(input=="101090803"){
		//直接驳回到被驳回状态  
		//v1.disableArea('permit',true);
		//chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='RejectTask';
		$('${sheetPageName}operateType').value='122';
			$('${sheetPageName}dealPerformer').value='${Operateroleid}';
			$('${sheetPageName}dealPerformerLeader').value='${TaskOwner}';
			$('${sheetPageName}dealPerformerType').value='${OperateroleidType}';
		$('roleName').innerHTML="建单人";
		selectLimit('RejectTask');
	}
	}
	}
		function executeResult(input){
		if(input=="101090301"){
			document.all.${sheetPageName}linkFailedReason.disabled=true;
			document.all.${sheetPageName}linkFailedReason.value='';
		}else{
			document.all.${sheetPageName}linkFailedReason.disabled=false;
		}
	}
	var frm = document.forms[0];
    var temp1 = frm.linkIsCheck ? frm.linkIsCheck.value : '';
    var temp2 = frm.linkPermitResult ? frm.linkPermitResult.value : '';
	if(temp1 != ''){
		ifAuditPass(temp1);
	}
	if(temp2 != ''){
		ifAuditPass1(temp2);
	}
		//处理时限不能超过工单完成时限
		var dtnow = new Date();
		var str = '${sheetMain.sheetCompleteLimit}';
		str = str.replace(/-/g,"/");
		str = str.substring(0,str.length-2);
		var dt2 = new Date(str);
		if(dt2>dtnow){
			document.getElementById("tmpCompleteLimit").value='${sheetMain.sheetCompleteLimit}';
		}else{
			document.getElementById("tmpCompleteLimit").value='';
		}
		
	//当是否影响业务选择否时，影响业务情况和涉及业务部门不需要填写
	if('<%=operateType%>'=='113'||('${taskName}'=='PlanTask'&&'<%=operateType%>'=='11')){
		ifAffect(document.getElementById("linkIsEffectBusiness").value);
	}
	function ifAffect(affectValue){
		if(affectValue!=null&&affectValue=='1030102'){
			eoms.form.disable("linkBusinessDept");
			eoms.form.disable("linkEffectCondition");
		}else{
			eoms.form.enable("linkBusinessDept");
			eoms.form.enable("linkEffectCondition");
		}
	}
	
	function selectLimit(nextPhaseId){
    var temp1=$("${sheetPageName}mainNetTypeOne").value;
    var temp2=$("${sheetPageName}mainNetTypeTwo").value;
    var temp3=$("${sheetPageName}mainNetTypeThree").value;
    var temp4=$("${sheetPageName}mainFactory").value;
    Ext.Ajax.request({
		method:"get",
		url: "softchange.do?method=newShowLimit&specialty1="+temp1+"&specialty2="+temp2+"&specialty3="+temp3+"&specialty4="+temp4+"&flowName=SoftChangeMainProcess&taskName="+nextPhaseId,
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	    //$("${sheetPageName}sheetAcceptLimit").value = "";
        	    //$('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date(times).add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("${sheetPageName}nodeAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('${sheetPageName}nodeCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
 </script>
