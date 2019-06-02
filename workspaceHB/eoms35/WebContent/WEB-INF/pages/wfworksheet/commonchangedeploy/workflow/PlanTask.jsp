<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!-- 不是从历史页面进来 -->
<c:if test="${readOnly != 'true'}">
<table class="formTable">
<!--流程中的字段域 -->
<c:if test="${operateType == '1'}">	
<input type="hidden" name="phaseId" id="phaseId" value="ImplementTask" />
	<tr><td class="label">
				<!-- 实施负责人及联系方式 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkManagerContact"/>*
			</td>
			<td class="content">
				<input type="text"  class="text" name="linkManagerContact" id="linkManagerContact"  alt="allowBlank:false,maxLength:32,vtext:'请填入 实施负责人及联系方式 信息，最多输入 32 字符'" value="${sheetLink.linkManagerContact}"/>
			</td><td class="label">
				<!-- 抄送对象 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCopyObject"/>*
			</td>
			<td class="content">
				<input type="text"  class="text" name="linkCopyObject" id="linkCopyObject"  alt="allowBlank:false,maxLength:32,vtext:'请填入 抄送对象 信息，最多输入 32 字符'" value="${sheetLink.linkCopyObject}"/>
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 计划开始时间 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkPlanStartTime"/>*
			</td>
			<td>					
				<input type="text" class="text" name="linkPlanStartTime" readonly="readonly" 
						id="linkPlanStartTime" value="${eoms:date2String(sheetLink.linkPlanStartTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
			</td><td class="label">
				<!-- 计划结束时间 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkPlanEndTime"/>*
			</td>
			<td>					
				<input type="text" class="text" name="linkPlanEndTime" readonly="readonly" 
						id="linkPlanEndTime" value="${eoms:date2String(sheetLink.linkPlanEndTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
			</td>
		</tr>
		<tr><td class="label">
				<!-- 影响网元范围 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectCellScope"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkEffectCellScope" id="linkEffectCellScope"  alt="allowBlank:false,maxLength:32,vtext:'请填入 影响网元范围 信息，最多输入 32 字符'" value="${sheetLink.linkEffectCellScope}"/>
			</td><td class="label">
				<!-- 是否影响业务 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectBusiness"/>*
			</td>
			<td>
				<eoms:comboBox name="linkEffectBusiness" id="linkEffectBusiness" 
						  
						defaultValue="${sheetLink.linkEffectBusiness}" alt="allowBlank:false" initDicId="10301" onchange="executeEffectBusiness(this.value);"/>
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 影响业务范围及时长 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectCondition"/>*
			</td>
			<td colspan="3">
				<input type="text"  class="text" name="linkEffectCondition" id="linkEffectCondition"  alt="allowBlank:false,maxLength:32,vtext:'请填入 影响业务范围及时长 信息，最多输入 32 字符'" value="${sheetLink.linkEffectCondition}"/>
			</td>
		</tr>
		
		<tr><td class="label">
				<!-- 涉及业务部门 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkBusinessDept"/>*
			</td>
			<td>
				<eoms:comboBox name="linkBusinessDept" id="linkBusinessDept" 
						  
						defaultValue="${sheetLink.linkBusinessDept}" alt="allowBlank:false" initDicId="1010902"/>
			</td>
			<td class="label">
				<!-- 是否通知客服 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsSendToFront"/>*
			</td>
			<td>
				<eoms:comboBox name="linkIsSendToFront" id="linkIsSendToFront" 
						  
						defaultValue="${sheetLink.linkIsSendToFront}" alt="allowBlank:false" initDicId="10301"/>
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 实施方案 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkExecuteAccessories"/>
			</td>
			<td colspan="3">
				<eoms:attachment name="sheetLink" property="linkExecuteAccessories" scope="request" idField="linkExecuteAccessories" appCode="commonchangedeploy" alt="allowBlank:true"/> 
				
			</td>
		</tr>	
		<tr>
		<td class="label">
				<!-- 影响网管情况 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectNetInstance"/>*
			</td>
			<td colspan="3">
				<textarea class="textarea max" name="linkEffectNetInstance" 
								id="linkEffectNetInstance" alt="allowBlank:false,maxLength:255,vtext:'请填入 影响网管情况 信息，最多输入 255 字符'">${sheetLink.linkEffectNetInstance}</textarea>
				
			</td>
			</tr>
	</c:if>		

<c:if test="${operateType == '92' || operateType == '11'}">	
<input type="hidden" name="phaseId" id="phaseId" value="${toPhaseIdMap[operateType]}" />

		<tr><td class="label">
				<!-- 实施负责人及联系方式 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkManagerContact"/>*
			</td>
			<td class="content">
				<input type="text"  class="text" name="linkManagerContact" id="linkManagerContact"  alt="allowBlank:false,maxLength:32,vtext:'请填入 实施负责人及联系方式 信息，最多输入 32 字符'" value="${sheetLink.linkManagerContact}"/>
			</td><td class="label">
				<!-- 抄送对象 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCopyObject"/>*
			</td>
			<td class="content">
				<input type="text"  class="text" name="linkCopyObject" id="linkCopyObject"  alt="allowBlank:false,maxLength:32,vtext:'请填入 抄送对象 信息，最多输入 32 字符'" value="${sheetLink.linkCopyObject}"/>
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 计划开始时间 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkPlanStartTime"/>*
			</td>
			<td>					
				<input type="text" class="text" name="linkPlanStartTime" readonly="readonly" 
						id="linkPlanStartTime" value="${eoms:date2String(sheetLink.linkPlanStartTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
			</td><td class="label">
				<!-- 计划结束时间 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkPlanEndTime"/>*
			</td>
			<td>					
				<input type="text" class="text" name="linkPlanEndTime" readonly="readonly" 
						id="linkPlanEndTime" value="${eoms:date2String(sheetLink.linkPlanEndTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
			</td>
		</tr>
		<tr><td class="label">
				<!-- 影响网元范围 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectCellScope"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkEffectCellScope" id="linkEffectCellScope"  alt="allowBlank:false,maxLength:32,vtext:'请填入 影响网元范围 信息，最多输入 32 字符'" value="${sheetLink.linkEffectCellScope}"/>
			</td><td class="label">
				<!-- 是否影响业务 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectBusiness"/>*
			</td>
			<td>
				<eoms:comboBox name="linkEffectBusiness" id="linkEffectBusiness" 
						  
						defaultValue="${sheetLink.linkEffectBusiness}" alt="allowBlank:false" initDicId="10301" onchange="executeEffectBusiness(this.value);"/>
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 影响业务范围及时长 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectCondition"/>*
			</td>
			<td colspan="3">
				<input type="text"  class="text" name="linkEffectCondition" id="linkEffectCondition"  alt="allowBlank:false,maxLength:32,vtext:'请填入 影响业务范围及时长 信息，最多输入 32 字符'" value="${sheetLink.linkEffectCondition}"/>
			</td>
		</tr>
		
		<tr><td class="label">
				<!-- 涉及业务部门 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkBusinessDept"/>*
			</td>
			<td>
				<eoms:comboBox name="linkBusinessDept" id="linkBusinessDept" 
						  
						defaultValue="${sheetLink.linkBusinessDept}" alt="allowBlank:false" initDicId="1010902"/>
			</td>
			<td class="label">
				<!-- 是否通知客服 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsSendToFront"/>*
			</td>
			<td>
				<eoms:comboBox name="linkIsSendToFront" id="linkIsSendToFront" 
						  
						defaultValue="${sheetLink.linkIsSendToFront}" alt="allowBlank:false" initDicId="10301"/>
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 实施方案 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkExecuteAccessories"/>*
			</td>
			<td colspan="3">
				<eoms:attachment name="sheetLink" property="linkExecuteAccessories" scope="request" idField="linkExecuteAccessories" appCode="commonchangedeploy" /> 
				
			</td>
		</tr>	
		<tr>
		<td class="label">
				<!-- 影响网管情况 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectNetInstance"/>*
			</td>
			<td colspan="3">
				<textarea class="textarea max" name="linkEffectNetInstance" 
								id="linkEffectNetInstance" alt="allowBlank:false,maxLength:255,vtext:'请填入 影响网管情况 信息，最多输入 255 字符'">${sheetLink.linkEffectNetInstance}</textarea>
				
			</td>
			</tr> 				
</c:if>

<!--流程中的字段域 结束-->
</table>
	

	 				
	
<c:if test="${operateType == '92'||operateType == '1'}">
	<!-- 派单树 -->	
	<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"><bean:message bundle="commonchangedeploy" key="role.netdeployoperator"/></span>
		 </legend>		
		<div ID="permit3">  
			<eoms:chooser id="sendObject" type="role" roleId="408" flowId="${flowId}" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
				category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人',limit:'none'},{id:'copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
				 data="${sheetLink.sendObject}"/>
		</div>
	</fieldset>	 				
</c:if>
</c:if>
<!-- 历史页面进入 -->
<c:if test="${readOnly == 'true'}">


<c:if test="${operateType == '92' || operateType == '11'}">		
	 				
		<tr><td class="label">
				<!-- 实施负责人及联系方式 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkManagerContact"/>
			</td>
			<td class="content">
				${sheetLink.linkManagerContact}
				
			</td><td class="label">
				<!-- 抄送对象 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCopyObject"/>
			</td>
			<td class="content">
				${sheetLink.linkCopyObject}
				
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 计划开始时间 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkPlanStartTime"/>
			</td>
			<td class="content">
				${eoms:date2String(sheetLink.linkPlanStartTime)}
			</td><td class="label">
				<!-- 计划结束时间 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkPlanEndTime"/>
			</td>
			<td class="content">
				${eoms:date2String(sheetLink.linkPlanEndTime)}
			</td>
		</tr>
		<tr><td class="label">
				<!-- 影响网元范围 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectCellScope"/>
			</td>
			<td class="content">
				${sheetLink.linkEffectCellScope}
				
			</td><td class="label">
				<!-- 是否影响业务 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectBusiness"/>
			</td>
			
			<td class="content">
				<eoms:id2nameDB id="${sheetLink.linkEffectBusiness}" beanId="ItawSystemDictTypeDao"/>
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 影响业务范围及时长 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectCondition"/>
			</td>
			<td class="content">
				${sheetLink.linkEffectCondition}
				
			</td><td class="label">
				<!-- 影响网管情况 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectNetInstance"/>
			</td>
			<td class="content">
				<pre>${sheetLink.linkEffectNetInstance}</pre>
				
			</td>
		</tr>
		<tr><td class="label">
				<!-- 涉及业务部门 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkBusinessDept"/>
			</td>
			<td class="content">
				<eoms:id2nameDB id="${sheetLink.linkBusinessDept}" beanId="ItawSystemDictTypeDao"/>
				
			</td><td class="label">
				<!-- 是否通知客服 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsSendToFront"/>
			</td>
			<td class="content">
				<eoms:id2nameDB id="${sheetLink.linkIsSendToFront}" beanId="ItawSystemDictTypeDao"/>
			</td>
			</tr>
			<tr>
			<td class="label">
				<!-- 实施方案 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkExecuteAccessories"/>
			</td>
			<td class="content" colspan="3">
				<eoms:attachment name="sheetLink" property="linkExecuteAccessories"  scope="request" idField="linkExecuteAccessories" appCode="commonchangedeploy"  viewFlag="Y"/> 
				
			</td>
		</tr>
</c:if>
 
</c:if>