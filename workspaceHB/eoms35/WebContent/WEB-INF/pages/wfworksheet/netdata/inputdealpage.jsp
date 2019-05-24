<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.boco.eoms.sheet.netdata.model.NetDataMain"%>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 request.setAttribute("operateType",operateType);
 String adate=com.boco.eoms.sheet.base.DateUtil.getStrCurrentDateAddDay(2);
 NetDataMain main = new NetDataMain();
if(request.getAttribute("sheetMain")!=null){
   main = (NetDataMain)request.getAttribute("sheetMain");
   }
   long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %> 
<%@ include file="/WEB-INF/pages/wfworksheet/netdata/baseinputlinkhtmlnew.jsp"%> 
		<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="NetDataMainProcess" />
		<input type="hidden" name="${sheetPageName}beanId" value="iNetDataMainManager"/> 
		<input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate" />
	    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.netdata.model.NetDataMain"/>	
	    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.netdata.model.NetDataLink"/>
		<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
        <input type="hidden" id="${sheetPageName}mainNetTypeOne" name="${sheetPageName}mainNetTypeOne" value="${sheetMain.mainNetTypeOne}"/>
        <input type="hidden" id="${sheetPageName}mainNetTypeTwo" name="${sheetPageName}mainNetTypeTwo" value="${sheetMain.mainNetTypeTwo}"/>
        <input type="hidden" id="${sheetPageName}mainNetTypeThree" name="${sheetPageName}mainNetTypeThree" value="${sheetMain.mainNetTypeThree}"/>
        <input type="hidden" id="${sheetPageName}mainFactory" name="${sheetPageName}mainFactory" value="${sheetMain.mainFactory}"/>
        <input type="hidden" id="operateRoleIdTest" name="operateRoleIdTest" value="${sheetLink.operateRoleId}">
        <!--修改实施验证的派往对象-->
        <c:if test="${taskName != 'HoldTask'&&taskName != 'ValidateTask' }">
       		<input type="hidden" id="toOrgRoleId" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
        </c:if>
        <c:if test="${taskName=='ValidateTask' }">
        	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${sheetMain.sendRoleId}"/>
        </c:if>
	<br/>

	<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
		<%if(!operateType.equals("61")) {%>
		<caption><bean:message bundle="netdata" key="netdata.header"/></caption>
		<%} %>
	<!--流程中的字段域 -->
		<!--流程中的字段域 -->
		<!-- 方案制定  -->
    <%if(taskName.equals("ProjectCreateTask") && (operateType.equals("110") || operateType.equals("11"))) {%>
    				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
			      	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}" />	
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
			      	<!-- 完成时限 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkCompleteLimitTime"/>*</td>
		            <td> 
		   				<input type="text" class="text" name="${sheetPageName}linkCompleteLimitTime" readonly="readonly" 
						id="${sheetPageName}linkCompleteLimitTime" value="${eoms:date2String(sheetLink.linkCompleteLimitTime)}" 
						onclick="popUpCalendar(this, this)"  alt="allowBlank:false"/>            
		            </td>
		            <!-- 技术方案关键字 -->
		             <td  class="label"><bean:message bundle="netdata" key="netdata.linkDesignKey"/>*</td>
		             <td> <input type="text"  class="text" name="${sheetPageName}linkDesignKey" id="${sheetPageName}linkDesignKey" value="${sheetLink.linkDesignKey}"  alt="allowBlank:false,maxLength:32,vtext:'请填入备注信息，最多输入32字符'"/></td>
		          </tr>
		          <tr>
			      	<!-- 方案资源号 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.mainDesignId"/></td>
		             <td colspan="3"> 	
		   				<input type="text" class="text" name="${sheetPageName}mainDesignId" value="${sheetMain.mainDesignId}" alt="allowBlank:true,maxLength:255,vtext:'最多输入255字符'"/>            
		            </td>           
		          </tr>
			      <tr>
			      	<!-- 技术方案说明 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkDesignComment"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkDesignComment" id="${sheetPageName}linkDesignComment" alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkDesignComment}</textarea>
                    </td>
		          </tr>
			      <tr>
			      	<!-- 变更涉及省份 -->
		            <td  class="label">
		            <!--
		            <bean:message bundle="netdata" key="netdata.linkInvolvedProvince"/>
		            <bean:message bundle="netdata" key="netdata.linkInvolvedCity"/>
		            -->
		            涉及省份及地市*</td>
		              <td colspan="3">					  
    				  <input type="button" id="areabtn" value="选择涉及省份和地市" class="btn"/>
    				  
    			
    				  <br/><br/>	
    				  涉及省份:<textarea class="textarea max" readonly="true" name="linkInvolvedProvince" style="height:50px" id="${sheetPageName}linkInvolvedProvince"  alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkInvolvedProvince }</textarea><br/>
    				  涉及地市:<textarea class="textarea max" readonly="true" name="linkInvolvedCity" style="height:50px" id="${sheetPageName}linkInvolvedCity" alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkInvolvedCity}</textarea>
                    
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
			      	<!-- 风险评估 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkRiskEstimate"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkRiskEstimate" id="${sheetPageName}linkRiskEstimate" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkRiskEstimate}</textarea>
                    </td>
		          </tr>
			      <tr>
			      	<!-- 影响业务分析 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkEffectAnalyse"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkEffectAnalyse" id="${sheetPageName}linkEffectAnalyse" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkEffectAnalyse}</textarea>
                    </td>
		          </tr>
		          <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">	
						<div class="x-form-item">
							<div class="x-form-element">				
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>		
					            </div>
							</div>		
					    </td>
		  		</tr>		
		        <tr>
			      	<!-- 技术方案附件 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkDesignAttachment"/></td>
		              <td colspan="3"> 	
	    				  	<eoms:attachment name="sheetLink" property="nodeAccessories" 
			            	scope="request" idField="${sheetPageName}nodeAccessories" appCode="netdata" /> 	
                    </td>
		          </tr>
	<!-- 方案审核/分派回复 -->
	<%}else if(taskName.equals("AuditTask") && (operateType.equals("111") || operateType.equals("55"))){%>
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask" />
	                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}" />
 					<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="" />
					<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="" />
					<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="" />
	                <tr>
		                <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsCheck"/>*</td>
		                <td colspan="3">  
	            	      <eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult" 
		            	      			initDicId="1010907"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkPermitResult}" onchange="ifAuditPass(this.value,'AuditTask')"/>
			        	</td>	                
		            </tr>
			        <tr>
		            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkCheckComment"/>*</td>
		             	<td colspan="3"> 	
	   				  		<textarea class="textarea max" name="${sheetPageName}linkCheckComment" id="${sheetPageName}linkCheckComment" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkCheckComment}</textarea>      	
	                   	</td>
		          	</tr>
		          	<tr>
		  	<td class="label"><bean:message bundle="netdata" key="netdata.linkIfStartOperationTest"/>*</td>
		     <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIfStartOperationTest" id="${sheetPageName}linkIfStartOperationTest" 
            	      initDicId="10301"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkIfStartOperationTest}" onchange="ifStart(this.value);"/>
			        </td>
		  </tr>	
		  	  
		  <tr id="ifDStart">
		  	<td class="label"><bean:message bundle="netdata" key="netdata.linkTestPerson"/></td>
		    <td colspan="3">
		     <div id="subroleview" class="hide"></div>
			    <script type="text/javascript">	             
	            //viewer
				var subroleViewer = new Ext.JsonView("subroleview",
					'<div class="viewlistitem-{nodeType}">{name}</div>',
					{ 
						emptyText : '<div>没有选择项目</div>'
				  }
				);
				var data = "[{id:'${sheetLink.linkTestPerson}',name:'<eoms:id2nameDB id='${sheetLink.linkTestPerson}' beanId='tawSystemSubRoleDao'/>',nodeType:'subrole'}]";
				subroleViewer.jsonData = eoms.JSONDecode(data);
				subroleViewer.refresh();
				 
				//subrole tree
	            var	roleTreeAction='${app}/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&roleId=380&flowId=78';
	            deptetree = new xbox({

    	          btnId:'${sheetPageName}showSubRole',dlgId:'dlg3',

    	          treeDataUrl:roleTreeAction,treeRootId:'-1',treeRootText:'网络配置员(业务测试)',treeChkMode:'',treeChkType:'subrole',
    	          showChkFldId:'${sheetPageName}showSubRole',saveChkFldId:'${sheetPageName}linkTestPerson',viewer:subroleViewer
	            });
               </script>
               <textarea class="textarea max" readonly="true" name="${sheetPageName}showSubRole" style="height:50px" id="${sheetPageName}showSubRole"  alt="allowBlank:true,maxLength:500,vtext:'请填入测试方名称，最多输入500字符'">
                    <c:forTokens items="${sheetLink.linkTestPerson}" delims="," var="linkTestPerson" varStatus="status">
                      <eoms:id2nameDB id="${linkTestPerson}" beanId="tawSystemSubRoleDao"/>
                         <c:choose>
                            <c:when test="${status.last}">
                            </c:when>
                        <c:otherwise>,
                        </c:otherwise>
                       </c:choose>
                     </c:forTokens>
                 </textarea> 
               <input type="hidden" name="${sheetPageName}linkTestPerson" id="${sheetPageName}linkTestPerson" value="${sheetLink.linkTestPerson}"/>			  
               
		    
		    </td>
		  </tr>
<script type="text/javascript">


</script>
	<!-- 方案审批 -->
	<%}else if(taskName.equals("PermitTask") && (operateType.equals("112")|| operateType.equals("55"))){%>
	              <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PlanTask" />
	              <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}" />	
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
		                 <td  class="label"><bean:message bundle="netdata" key="netdata.linkPermitResult"/>*</td>
		                 <td colspan="3">  
	            	      	<eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult" 
		            	      			initDicId="1010908"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkPermitResult}" onchange="ifAuditPass(this.value,'PermitTask')"/>
			        	</td>	                
		          </tr>
			      <tr>
			            <td  class="label"><bean:message bundle="netdata" key="netdata.linkPermitIdea"/>*</td>
			             <td colspan="3"> 	
		   				  	<textarea class="textarea max" name="${sheetPageName}linkPermitIdea" id="${sheetPageName}linkPermitIdea" alt="allowBlank:false,maxLength:4000,vtext:'请填入审批意见，最多输入4000字符'">${sheetLink.linkPermitIdea}</textarea>
	                   </td>
		          </tr>
	<!-- 排程 -->
	<%}else if(taskName.equals("PlanTask") && (operateType.equals("113") || operateType.equals("11"))){%>
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask" />
			    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}" />	
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
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkManager"/>*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}linkManager" id="${sheetPageName}linkManager" value="${sheetLink.linkManager}" alt="allowBlank:false,maxLength:32,vtext:'请填入备注信息，最多输入32字符'"/></td>
		          	<td  class="label"><bean:message bundle="netdata" key="netdata.linkContact"/>*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}linkContact" id="${sheetPageName}linkContact" value="${sheetLink.linkContact }"  alt="allowBlank:false,maxLength:32,vtext:'请填入备注信息，最多输入32字符'"/></td>
		          </tr>
	              <tr>
			     		<!-- 排程看板 -->
			     		<td class="label"><bean:message bundle="netdata" key="netdata.planSheet"/></td>
			     		<td colspan="3">
			     			<a href="#" onclick="popModifyTime()"><bean:message bundle="netdata" key="netdata.lookPlanSheet"/></a>
						 </td>
		        </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkPlanStartTime"/>*</td>
		            <td> 
		            	<input type="text" class="text" name="${sheetPageName}linkPlanStartTime" readonly="readonly" 
						id="${sheetPageName}linkPlanStartTime" value="${eoms:date2String(sheetLink.linkPlanStartTime)}" 
						onclick="popUpCalendar(this, this)"  alt="vtype:'lessThen',link:'${sheetPageName}linkPlanEndTime',vtext:'计划开始时间不能晚于计划结束时间',allowBlank:false"/>            
		            </td>
		          	<td  class="label"><bean:message bundle="netdata" key="netdata.linkPlanEndTime"/>*</td>
		            <td>     
		            	<input type="text" class="text" name="${sheetPageName}linkPlanEndTime" readonly="readonly" 
						id="${sheetPageName}linkPlanEndTime" value="${eoms:date2String(sheetLink.linkPlanEndTime)}" 
						onclick="popUpCalendar(this, this)"  alt="vtype:'moreThen',link:'${sheetPageName}linkPlanStartTime',vtext:'计划结束时间不能早于计划开始时间',allowBlank:false"/>            
		            </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkCellInfo"/>*</td>
		            <td  colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkCellInfo" id="${sheetPageName}linkCellInfo" alt="allowBlank:false,maxLength:500,vtext:'请填入备注信息，最多输入500字符'">${sheetLink.linkCellInfo }</textarea>
                    </td>
		          </tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsEffectBusiness"/></td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIsEffectBusiness" id="${sheetPageName}linkIsEffectBusiness" 
            	      	initDicId="10301"  alt="allowBlank:true" styleClass="select-class" defaultValue="${sheetLink.linkIsEffectBusiness}" onchange="ifAffect(this.value)"/>
			        </td>	                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkEffectCondition"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkEffectCondition" id="${sheetPageName}linkEffectCondition" alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkEffectCondition}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkNetManage"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkNetManage" id="${sheetPageName}linkNetManage" alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkNetManage}</textarea>
                    </td>
		          </tr>
                 <tr>
	                <td  class="label"><bean:message bundle="netdata" key="netdata.linkBusinessDept"/></td>
	                <td> 
				        <eoms:comboBox name="${sheetPageName}linkBusinessDept" id="${sheetPageName}linkBusinessDept" 
            	      	initDicId="1010902"  alt="allowBlank:true" styleClass="select-class" defaultValue="${sheetLink.linkBusinessDept}"/>

		        	</td>
		        	<td  class="label"><bean:message bundle="netdata" key="netdata.linkIsSendToFront"/></td>
	                <td>  
			        	<eoms:comboBox name="${sheetPageName}linkIsSendToFront" id="${sheetPageName}linkIsSendToFront" 
           	      		initDicId="10301"  alt="allowBlank:true" styleClass="select-class" defaultValue="${sheetLink.linkIsSendToFront}"/>
		        	</td>	 	                
	             </tr>
	              <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">	
						<div class="x-form-item">
							<div class="x-form-element">				
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>		
					            </div>
							</div>		
					    </td>
		  		</tr>	
	             <tr>
	             	<td  class="label"><bean:message bundle="netdata" key="netdata.executeAttachment"/></td>
	             	<td colspan="3">
	             		 <eoms:attachment name="sheetLink" property="nodeAccessories" 
			            	scope="request" idField="${sheetPageName}nodeAccessories" appCode="netdata" />
			            	 
	             	</td>
	             </tr>
	            	
	             
	<!-- 数据制作 -->
	<%}else if(taskName.equals("ExecuteTask") && (operateType.equals("114")|| operateType.equals("11"))){%>
                    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}" />	
                    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CheckDataTask" />
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
	                <td  class="label"><bean:message bundle="netdata" key="netdata.linkCutResult"/>*</td>
	                <td>  
				        <eoms:comboBox name="${sheetPageName}linkCutResult" id="${sheetPageName}linkCutResult" 
            	      	initDicId="1010903"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkCutResult }" onchange="executeResult(this.value);"/>
		        	</td>	
            	      <!-- 失败原因 -->
					  <td class="label"><bean:message bundle="netdata" key="netdata.linkFailedReason"/></td>
					  <td>
					  	<eoms:comboBox name="${sheetPageName}linkFailedReason" id="${sheetPageName}linkFailedReason" initDicId="1010910" defaultValue="${sheetLink.linkFailedReason}" alt="allowBlank:true"/>
					  </td>	                
	              </tr>
	              
	              <tr>
	              <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsPlan"/>*</td>
	                 <td>  
				        <eoms:comboBox name="${sheetPageName}linkIsPlan" id="${sheetPageName}linkIsPlan" 
            	      initDicId="10301"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkIsPlan }"/>
            	     </td>	   
	                 <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestResult"/>*</td>
	                 <td>  
				        <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" 
            	      	initDicId="1010904"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkTestResult }"/>
		        	 </td>
		         </tr>
		        <tr>
		        	 <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestReport"/>*</td>
		              <td colspan="3"> 	
	   				  <textarea class="textarea max" name="${sheetPageName}linkTestReport" id="${sheetPageName}linkTestReport" alt="allowBlank:false,maxLength:255,vtext:'请填入备注信息，最多输入255字符'">${sheetLink.linkTestReport }</textarea>
	                   </td> 
		        </tr>             
	              
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkCutComment"/>*</td>
		              <td colspan="3"> 	
	   				  <textarea class="textarea max" name="${sheetPageName}linkCutComment" id="${sheetPageName}linkCutComment" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkCutComment }</textarea>
	                   </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkBusinessComment"/>*</td>
		              <td colspan="3"> 	
	   				  <textarea class="textarea max" name="${sheetPageName}linkBusinessComment" id="${sheetPageName}linkBusinessComment" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkBusinessComment }</textarea>
	                   </td>
		          </tr>
	               
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkAlertRecord"/>*</td>
		              <td colspan="3"> 	
	   				  <textarea class="textarea max" name="${sheetPageName}linkAlertRecord" id="${sheetPageName}linkAlertRecord" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkAlertRecord}</textarea>
	                   </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkUnnormalComment"/>*</td>
		              <td colspan="3"> 	
	   				  <textarea class="textarea max" name="${sheetPageName}linkUnnormalComment" id="${sheetPageName}linkUnnormalComment" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkUnnormalComment}</textarea>
	                   </td>
		          </tr>
				  <tr>
		  <td class="label">附件</td>
		  <td colspan="3">
		
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netdata" />   
		  </td>
		</tr>

		          
		          <!-- 数据核查 -->
	<%}else if(taskName.equals("CheckDataTask") && (operateType.equals("117")|| operateType.equals("11"))){%>
		
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="117" />
         
<!--         		<input type="hidden" name="dealPerformer" id="dealPerformer" value="8a9982f224543898012467061aeb6a9c" />-->
<!--				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="huangfen" />-->
        <!-- dealPerformerType是角色类型 -->
<!--        <input type="hidden" name="dealPerformerType" id="dealPerformerType" value="subrole" />-->

     				   
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
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkDataCheck"/>*</td>
		              <td colspan="3"> 	
		              <!-- 原为 ${sheetPageName}linkCheckResult 修改为：${sheetPageName}linkDataCheck-->
	   				  <textarea class="textarea max" name="${sheetPageName}linkDataCheck" id="${sheetPageName}linkDataCheck" alt="allowBlank:false,maxLength:255,vtext:'请填入核查结果，最多输入255字符'">${sheetLink.linkDataCheck}</textarea>
	                   </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkCheckExplain"/></td>
		              <td colspan="3"> 	
	   				  <textarea class="textarea max" name="${sheetPageName}linkCheckExplain" id="${sheetPageName}linkCheckExplain" alt="allowBlank:true,maxLength:255,vtext:'请填入核查情况说明，最多输入255字符'">${sheetLink.linkCheckExplain}</textarea>
	                   </td>
		          </tr>
		          <tr>
		  <td class="label">附件</td>
		  <td colspan="3">
		
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netdata" />   
		  </td>
		</tr>
		          <!-- 业务测试 -->
	<%}else if(taskName.equals("TestOperationTask") && operateType.equals("119")){%>
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}" />
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask" />
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
				             <td class="content" > 
				              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
				                 name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
				                    value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
				       </td>
	      		  </tr>
		          <tr>
		          <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestResult"/>*</td>
	                 <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" 
            	      	initDicId="1010904"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkTestResult }"/>
		        	 </td>
		         </tr>
		        <tr>
		        	 <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestReport"/>*</td>
		              <td colspan="3"> 	
	   				  <textarea class="textarea max" name="${sheetPageName}linkTestReport" id="${sheetPageName}linkTestReport" alt="allowBlank:false,maxLength:255,vtext:'请填入报告信息，最多输入255字符'">${sheetLink.linkTestReport }</textarea>
	                   </td> 
		        </tr>     
				<tr>
		  <td class="label">附件</td>
		  <td colspan="3">
		
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netdata" />   
		  </td>
		</tr>
		   <!-- 业务测试分派回复 -->
	<%}else if(taskName.equals("TestOperationTask") &&  operateType.equals("11")){%>
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}" />
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask" />
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
				             <td class="content" > 
				              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
				                 name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
				                    value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
				       </td>
	      		  </tr>
		          <tr>
		          <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestResult"/>*</td>
	                 <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" 
            	      	initDicId="1010904"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkTestResult }"/>
		        	 </td>
		         </tr>
		        <tr>
		        	 <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestReport"/>*</td>
		              <td colspan="3"> 	
	   				  <textarea class="textarea max" name="${sheetPageName}linkTestReport" id="${sheetPageName}linkTestReport" alt="allowBlank:false,maxLength:255,vtext:'请填入报告信息，最多输入255字符'">${sheetLink.linkTestReport }</textarea>
	                   </td> 
		        </tr>     
		  <tr>        
	       <td class="label">
	        集中数据制作核查结果*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}transferReason" class="textarea max" id="${sheetPageName}transferReason" 
		        alt="allowBlank:false,maxLength:4000,vtext:'请填写意见，最多输入4000字符'">${sheetLink.transferReason}</textarea>
		  </td>
		 </tr> 
		 <tr>
		  <td class="label">附件</td>
		  <td colspan="3">
		
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="netdata" />   
		  </td>
		</tr>
   <!-- 实施结果验证 -->
	<%}else if(taskName.equals("ValidateTask") && (operateType.equals("115") || operateType.equals("11"))){%>
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
			    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}" />	
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
		                <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsUpdateWork"/>*</td>
		                <td>  
					        <eoms:comboBox name="${sheetPageName}linkIsUpdateWork" id="${sheetPageName}linkIsUpdateWork" 
	            	      		initDicId="10301"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkIsUpdateWork}"/>
			        	</td>
			        	<td  class="label"><bean:message bundle="netdata" key="netdata.linkCutResult"/>*</td>
		                <td>  
					        <eoms:comboBox name="${sheetPageName}linkCutResult" id="${sheetPageName}linkCutResult" 
	            	      	initDicId="1010903"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkCutResult }"/>
			        	</td>	                
		          </tr>
		          <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsNeedProject"/>*</td>
		              <td> 	
    				  	  <eoms:comboBox name="${sheetPageName}linkIsNeedProject" id="${sheetPageName}linkIsNeedProject" 
	            	      		initDicId="10301"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkIsNeedProject}"/>
                    </td>
           			 <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsPlan"/>*</td>
		              <td> 	
    				  	<eoms:comboBox name="${sheetPageName}linkIsPlan" id="${sheetPageName}linkIsPlan" 
	            	      		initDicId="10301"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkIsPlan}"/>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkCutAnalyse"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkCutAnalyse" id="${sheetPageName}linkCutAnalyse" alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkCutAnalyse}</textarea>
                    </td>
		          </tr>	          
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkWorkUpdateAdvice"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkWorkUpdateAdvice" id="${sheetPageName}linkWorkUpdateAdvice" alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkWorkUpdateAdvice}</textarea>
                    </td>
		          </tr> 
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkSoftVersionUpdate"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkSoftVersionUpdate" id="${sheetPageName}linkSoftVersionUpdate" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkSoftVersionUpdate}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkNextPlan"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkNextPlan" id="${sheetPageName}linkNextPlan"  alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkNextPlan}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkProjectComment"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkProjectComment" id="${sheetPageName}linkProjectComment" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetLink.linkProjectComment}</textarea>
                    </td>
		          </tr>
	<!-- 归档 -->
	<%}else if(taskName.equals("HoldTask") && (operateType.equals("18")|| operateType.equals("11"))){%>
				 <input type="hidden" name="${sheetPageName}phaseId" value="Over" />
				 <input type="hidden" name="${sheetPageName}status" value="1" />
				 <input type="hidden" name="${sheetPageName}operateType" value="${operateType}" />
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
				      <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied" initDicId="10303" alt="width:'500px',allowBlank:false" styleClass="select" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"/>
				    </td>
				 </tr>
				  
				 <tr>
				  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
				    <td colspan="3">
				      <textarea class="textarea max" name="${sheetPageName}endResult" id="${sheetPageName}endResult"  alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetMain.endResult}</textarea>
				    </td>
				 </tr>
				 <tr>
		  	<td class="label"><bean:message bundle="netdata" key="netdata.mainIfDemonstrateCase"/>*</td>
		     <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}mainIfDemonstrateCase" id="${sheetPageName}mainIfDemonstrateCase" 
            	      initDicId="10301"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainIfDemonstrateCase}" onchange="ifCase(this.value);"/>
			        </td>
		  </tr>	
		  <tr>	  
		  <tr id="ifDcase">
		  	<td class="label"><bean:message bundle="netdata" key="netdata.mainCaseKeywords"/></td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}mainCaseKeywords" alt="allowBlank:true,maxLength:100,vtext:'请填入案例关键字，最多输入100字符'" id="${sheetPageName}mainCaseKeywords"  class="textarea max">${sheetMain.mainCaseKeywords}</textarea>
		    </td>
		  </tr>	
<script type="text/javascript">
      var v1 = eoms.form;	
	  function ifCase(CaseKeywords){
		if(CaseKeywords=='1030101'){
		    v1.enableArea('ifDcase');
		}else{
			v1.disableArea('ifDcase',true);
		}		
	}
	ifCase($('${sheetPageName}mainIfDemonstrateCase').value);

</script>		  
		          
	<%} %>
	
	
	<!--流程中的字段域 结束-->
	
	
	<!-- 公共功能，抄送和确认受理 -->
	<!-- 抄送 -->
	<%if(taskName.equals("cc")){%>
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
   <%} %>
   
   <!-- 确认受理 -->
   <%if(operateType.equals("61")){%>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>
		-->  		
  <%} %>
  
  <!-- 驳回到上一级 -->
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
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
	<%} %>	
  </table>
 <!-- 公共功能，抄送和确认受理 结束 -->
 
 
  <!-- 各个环节中的选择角色 -->
  <!-- 方案制定  -->
<%if(taskName.equals("ProjectCreateTask") && operateType.equals("110")) {%>  		
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName"><bean:message bundle="netdata" key="role.ProjectCreate"/></span>
			
	 </legend>
		 <div ID="permit1">  
			<eoms:chooser id="netSendRole1" type="role" roleId="245" flowId="78" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
				category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发人',limit:1} ,{id:'${sheetPageName}copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
				data="${sendUserAndRoles}"/>
		</div>
	</fieldset>	
	<fieldset id="link5">
	 <legend>
			排程角色:
			<span id="roleName"><bean:message bundle="netdata" key="role.PlanTask"/></span>
			
	 </legend>
		 <div ID="permit2">  
			<eoms:chooser id="netSendRole2" type="role" roleId="247" flowId="78" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
				category="[{id:'${sheetPageName}extendPerformer',childType:'user,subrole',limit:1,text:'排程',allowBlank:false,vtext:'请选择排程人'}]"
				data="${sendUserAndRoles}"/>
		</div>
	</fieldset>
	<%} %>
	
 <!-- 方案审核 -->
	<%if(taskName.equals("AuditTask") && operateType.equals("111")) {%>
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"><bean:message bundle="netdata" key="role.PermitTask"/></span>
		 </legend>		
		<div ID="permit2">  
			<eoms:chooser id="netSendRole" type="role" roleId="246" flowId="78" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
				category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人',limit:1},{id:'${sheetPageName}copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
				data="${sendUserAndRoles}"/>
		</div>
		</fieldset>	
	 <%} %>
	 
	 <!-- 方案审批 -->
	<%if(taskName.equals("PermitTask") && operateType.equals("112")) {%>
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"><bean:message bundle="netdata" key="role.PlanTask"/></span>
		 </legend>		
		<div ID="permit3">  
	<% 
             Calendar limitDay = Calendar.getInstance();
		     limitDay.set(2009,7,11);
             Calendar sendTime = Calendar.getInstance();
			 sendTime.set(main.getSendYear(),main.getSendMonth(),main.getSendDay());
			 if(sendTime.compareTo(limitDay) <0 ){
           %>
            
		   <eoms:chooser id="netSendRole2" type="role" roleId="247" flowId="78" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',limit:1,text:'派发',vtext:'请选择派发对象'}]"/>	
		
		  
		   <%} %>
		</div>
		</fieldset>
	 <%} %>
  
 <!-- 排程 --> 
  	<%if(taskName.equals("PlanTask") && operateType.equals("113")) {%>  	
	<fieldset id="link4">
        <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"><bean:message bundle="netdata" key="role.PlanTask"/></span>
		 </legend>
		<eoms:chooser id="netSendRole"
	 		category="[{id:'${sheetPageName}dealPerformer',text:'派发',childType:'user,subrole',allowBlank:false,limit:'none',vtext:'请选择派发人'},{id:'${sheetPageName}copyPerformer',text:'抄送',allowBlank:true,childType:'user,subrole,dept',limit:'none'}]"
	 		panels="[
	  		{text:'可选子角色',dataUrl:'/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&roleId=378&flowId=78'},
	  		{text:'数据核查',dataUrl:'/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&roleId=379&flowId=78'},
	  		{text:'业务测试',dataUrl:'/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&roleId=380&flowId=78'},
	  		{text:'部门和人员',dataUrl:'/xtree.do?method=userFromDept'}
	 		]"
		/>
	</fieldset>

	<%} %>
	
<!-- 数据制作 -->
   <%if(taskName.equals("ExecuteTask") && operateType.equals("114")) {%> 
   <fieldset id="link4"> 

	
        <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"><bean:message bundle="netdata" key="role.PlanTask"/></span>
		 </legend>
		 
			<eoms:chooser id="netSendRole" type="role" roleId="379" flowId="78" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
				category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发人',limit:1},{id:'${sheetPageName}copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
				data="${sendUserAndRoles}"/>
	
	
	</fieldset>
<% }%>
<!-- 数据核查 -->
   <%if(taskName.equals("CheckDataTask") && operateType.equals("117")) {
   		String netTypeOne = main.getMainNetTypeOne();
   		if("101010403".equals(netTypeOne)){
   			%>
   			<!-- 当网络一级等于101010403(数据网)时 -->
   			<fieldset id="link4">
		        <legend>
						<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
						<span id="roleName"><bean:message bundle="netdata" key="role.PlanTask"/></span>
				 </legend>
				 <eoms:chooser id="netSendRole" type="role" roleId="380" flowId="78" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
						category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发人',limit:1},{id:'${sheetPageName}copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
						 data="[{id:'8a9982f22709095e01273b609e542394',nodeType:'subrole',categoryId:'dealPerformer',leaderId:'8a9982f22709095e01273b609e542394',leaderName:''}]"/>
			</fieldset>	
   		<% 
   		}else{
   		%>
   			<fieldset id="link4">
		        <legend>
						<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
						<span id="roleName"><bean:message bundle="netdata" key="role.PlanTask"/></span>
				 </legend>
				 <eoms:chooser id="netSendRole" type="role" roleId="380" flowId="78" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
						category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发人',limit:1},{id:'${sheetPageName}copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
						 data="[{id:'8a9982f224543898012467061aeb6a9c',nodeType:'subrole',categoryId:'dealPerformer',leaderId:'8a9982f224543898012467061aeb6a9c',leaderName:''}]"/>
			</fieldset>	
   		<%}} %>

<!-- 业务测试 -->
   <%if(taskName.equals("TestOperationTask") && operateType.equals("119")) {%>  	
   <fieldset id="link4">
        <legend>
		<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:<eoms:id2nameDB id="${ProjectCreatePerformer}" beanId="tawSystemSubRoleDao" />
	 </legend>	
		<eoms:chooser id="netSendRole" type="role" roleId="245" flowId="78" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
				category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发人',limit:1},{id:'${sheetPageName}copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
				data="${sendUserAndRoles}"/>
	</fieldset>				
	<%} %>
<!-- 实施结果验证 -->
   <%if(taskName.equals("ValidateTask") && operateType.equals("115")) {%>  	

	<%} %>
<!--抄送  -->
	<%if(taskName.equals("cc")) {%>	
	<fieldset id="link4">
	<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
		</fieldset>					
	 <%} %> 
<br/>

<div ID="idSpecia2"></div>

<script type="text/javascript">
 var v1 = eoms.form;
     function popModifyTime(){
		var height = window.screen.height/6;
   	 	var width = window.screen.width/4;
    	features = "dialogWidth:"+1024+"px;dialogHeight:"+768+"px; scroll:2; help:0; status:No; fullscreen;";
    	features += "dialogLeft:" + width + ";dialogTop:" + height;
		window.open('${app}/sheet/modifyTime/modifyTime.do?method=xquery','PlanSheet', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	}
 var operateType = "${operateType}";
  function ifAuditPass(input, task){
   if (operateType != "55" && operateType != "11") {
		if(input=="101090701" && task == "AuditTask"){	
			chooser_netSendRole.enable();
			//审核通过到审批
			$('${sheetPageName}phaseId').value='PermitTask';
			$('roleName').innerHTML="变更管理负责人";
			$('${sheetPageName}operateType').value='111';
			$('${sheetPageName}dealPerformer').disabled=true;
			$('${sheetPageName}dealPerformerLeader').disabled=true;
			$('${sheetPageName}dealPerformerType').disabled=true;
		} else if(input=="101090702" && task == "AuditTask"){
			//审核不通过到被驳回状态  
			chooser_netSendRole.disable();
			$('${sheetPageName}phaseId').value='ProjectCreateTask';
			$('roleName').innerHTML="变更管理员与技术组";
			$('${sheetPageName}operateType').value='123';
			$('${sheetPageName}dealPerformer').disabled=false;
			$('${sheetPageName}dealPerformerLeader').disabled=false;
			$('${sheetPageName}dealPerformerType').disabled=false;
			$('${sheetPageName}dealPerformer').value='${fOperateroleid}';
			$('${sheetPageName}dealPerformerLeader').value='${ftaskOwner}';
			$('${sheetPageName}dealPerformerType').value='${fOperateroleidType}';
		} else if(input=="101090801" && task == "PermitTask"){
			//审批通过到排程  
			//chooser_netSendRole.disable();
			$('${sheetPageName}phaseId').value='PlanTask';
			$('roleName').innerHTML="网络配置组";
			$('${sheetPageName}operateType').value='112';
			$('${sheetPageName}dealPerformer').value='';
			$('${sheetPageName}dealPerformerLeader').value='';
			$('${sheetPageName}dealPerformerType').value='';
			selectLimit('PlanTask');
		} else if(input=="101090802" && task == "PermitTask"){
			//审批不通过到方案制定   
			//chooser_netSendRole.disable();
			$('${sheetPageName}phaseId').value='ProjectCreateTask';
			$('roleName').innerHTML="变更管理员与技术组";
			$('${sheetPageName}operateType').value='121';
			$('${sheetPageName}dealPerformer').value='${ProjectCreateTaskOperateroleid}';
			$('${sheetPageName}dealPerformerLeader').value='${ProjectCreateTaskTaskOwner}';
			$('${sheetPageName}dealPerformerType').value='${ProjectCreateTaskOperateroleidType}';
			selectLimit('ProjectCreateTask');
		} else if(input=="101090803" && task == "PermitTask"){
			//直接驳回到被驳回状态  
			//chooser_netSendRole.disable();
			$('${sheetPageName}phaseId').value='RejectTask';
			$('${sheetPageName}operateType').value='122';
			$('roleName').innerHTML="建单人";
			$('${sheetPageName}dealPerformer').value='${Operateroleid}';
			$('${sheetPageName}dealPerformerLeader').value='${TaskOwner}';
			$('${sheetPageName}dealPerformerType').value='${OperateroleidType}';
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

ifAuditPass('${sheetLink.linkPermitResult}','AuditTask');
ifAuditPass('${sheetLink.linkPermitResult}','PermitTask');
		//处理时限不能超过工单完成时限
		//var dtnow = new Date();
		//var str = '${sheetMain.sheetCompleteLimit}';
		//str = str.replace(/-/g,"/");
		//str = str.substring(0,str.length-2);
		//var dt2 = new Date(str);
		//if(dt2>dtnow){
		//	document.getElementById("tmpCompleteLimit").value='${sheetMain.sheetCompleteLimit}';
		//}else{
		//	document.getElementById("tmpCompleteLimit").value='';
		//}
<%if(taskName.equals("AuditTask") && (operateType.equals("111") || operateType.equals("55"))){%>
	  function ifStart(IfStartTest){
		if(IfStartTest=='1030101'){
		    v1.enableArea('ifDStart');
		}else{
			v1.disableArea('ifDStart',true);
		}
	}
	ifStart($('${sheetPageName}linkIfStartOperationTest').value);
	<%}%>
	
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

 
