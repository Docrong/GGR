<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 %>
<!-- 不是从历史页面进来 -->
<c:if test="${readOnly != 'true'}">

<table class="formTable">
<!--流程中的字段域 -->

	

<c:if test="${operateType == '911'}">	
<input type="hidden" name="phaseId" id="phaseId" value="${toPhaseIdMap[operateType]}" />
		<tr><td class="label">
				<!-- 审批结果通过 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsCheck"/>*
			</td colspan='3'>
			<td>
		
			    <input type="hidden" name="${sheetPageName}linkIsCheck" value="101040102"/>
			    <eoms:id2nameDB id="101040102" beanId="ItawSystemDictTypeDao"/>
			    

			</td>
			</tr>	
			<tr>
			<td class="label">
				<!-- 审批意见 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCheckComment"/>*
			</td>
			<td colspan="3">
				<textarea class="textarea max" name="linkCheckComment" 
								id="linkCheckComment" alt="allowBlank:false,maxLength:255,vtext:'请填入 审批意见 信息，最多输入 255 字符'">${sheetLink.linkCheckComment}</textarea>
				
			</td>
		</tr> 				
</c:if>

<c:if test="${operateType == '55'}">	
<input type="hidden" name="phaseId" id="phaseId" value="${toPhaseIdMap[operateType]}" />
		<tr><td class="label">
				<!-- 审批结果通过 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsCheck"/>*
			</td colspan='3'>
			<td>
			    <eoms:comboBox name="linkIsCheck" id="linkIsCheck" defaultValue="101040101" alt="allowBlank:false" initDicId="1010401" />			   
			</td>
			</tr>	
			<tr>
			<td class="label">
				<!-- 审批意见 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCheckComment"/>*
			</td>
			<td colspan="3">
				<textarea class="textarea max" name="linkCheckComment" 
								id="linkCheckComment" alt="allowBlank:false,maxLength:255,vtext:'请填入 审批意见 信息，最多输入 255 字符'">${sheetLink.linkCheckComment}</textarea>
				
			</td>
		</tr> 				
</c:if>


		
		

	
<c:if test="${ operateType == '912'}">	
<input type="hidden" name="phaseId" id="phaseId" value="${toPhaseIdMap[operateType]}" />
		<tr><td class="label">
				<!-- 审批结果不通过  -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsCheck"/>*
			</td>
			<td>
			    <input type="hidden" name="${sheetPageName}linkIsCheck" value="101040101"/>
			    <eoms:id2nameDB id="101040101" beanId="ItawSystemDictTypeDao"/>
			</td>
		</tr>	
		<tr>
			<td class="label">
				<!-- 审批意见 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCheckComment"/>*
			</td>
			<td colspan="3">
				<textarea class="textarea max" name="linkCheckComment" 
								id="linkCheckComment" alt="allowBlank:false,maxLength:255,vtext:'请填入 审批意见 信息，最多输入 255 字符'">${sheetLink.linkCheckComment}</textarea>
				
			</td>
		</tr> 				
</c:if>

<!--流程中的字段域 结束-->
</table>
	


	 				
<!-- 审批通过-->

<c:if test="${operateType == '912'}">
	<!-- 派单树 -->	
	<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName">
				<bean:message bundle="commonchangedeploy" key="role.changeadmin"/>
                </span>
		 </legend>		
		<div ID="permit3">  
			<eoms:chooser id="sendObject" type="role" roleId="407" flowId="${flowId}" config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
				category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人',limit:'none'},{id:'copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
				 data="${sheetLink.sendObject}"/>
		</div>
	</fieldset>	 				
</c:if>




<!-- 审批不通过-->
	
<c:if test="${operateType == '911'}">
	<!-- 派单树 -->	
	<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName">
				<bean:message bundle="commonchangedeploy" key="role.changeproposer"/>
                </span>
		 </legend>		
	</fieldset>	 				
</c:if>
</c:if>	  

<!-- 历史页面进入 -->

<c:if test="${readOnly == 'true'}">

<c:if test="${operateType == '911' || operateType == '912' || operateType == '55'}">				
		<tr><td class="label">
				<!-- 审批结果 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsCheck"/>
			</td>
			<td class="content">
				<eoms:id2nameDB id="${sheetLink.linkIsCheck}" beanId="ItawSystemDictTypeDao"/>
			</td><td class="label">
				<!-- 审批意见 -->
				<bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCheckComment"/>
			</td>
			<td class="content">
				<pre>${sheetLink.linkCheckComment}</pre>
				
			</td>
		</tr>
</c:if>
 
</c:if>
