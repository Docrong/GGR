<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.Date" %>
<%@page import="com.boco.eoms.sheet.algorithm.model.AlgorithmMain" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.print("taskName+operateType" + taskName + operateType);
    request.setAttribute("operateType", operateType);
    String adate = com.boco.eoms.sheet.base.DateUtil.getStrCurrentDateAddDay(2);
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/algorithm/baseinputlinkhtmlnew.jsp" %>
<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
       value="AlgorithmMainProcess"/>
<input type="hidden" name="${sheetPageName}beanId" value="iAlgorithmMainManager"/>
<input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate"/>
<input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.algorithm.model.AlgorithmMain"/>
<input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.algorithm.model.AlgorithmLink"/>
<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
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
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <%if (!operateType.equals("61")) {%>
    <caption><bean:message bundle="algorithm" key="algorithm.header"/></caption>
    <%} %>
    <!--流程中的字段域 -->
    <!--流程中的字段域 -->
    <!-- 方案制定  -->
    <%if (taskName.equals("EvaluationTask") && (operateType.equals("201") || operateType.equals("11"))) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="EvaluationTestTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}nodeAcceptLimit" readonly="readonly"
                   id="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(task.acceptTimeLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>

        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   id="${sheetPageName}nodeCompleteLimit" value="${eoms:date2String(task.completeTimeLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">评估意见*</td>
        <td colspan="3">
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea"
                                id="${sheetPageName}linkAuditIdea"
                                alt="width:500,allowBlank:false,maxLength:2000,vtext:'请输入评估意见，最多输入1000汉字'">${sheetLink.linkAuditIdea}</textarea>
        </td>
    </tr>
    <%} else if (taskName.equals("EvaluationTestTask") && (operateType.equals("202") || operateType.equals("55"))) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidationTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}nodeAcceptLimit" readonly="readonly"
                   id="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(task.acceptTimeLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>

        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   id="${sheetPageName}nodeCompleteLimit" value="${eoms:date2String(task.completeTimeLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">操作描述*</td>
        <td colspan="3">
	    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea"
                                    id="${sheetPageName}linkAuditIdea"
                                    alt="width:500,allowBlank:false,maxLength:2000,vtext:'请输入评估意见，最多输入1000汉字'">${sheetLink.linkAuditIdea}</textarea>
        </td>
    </tr>
    <%} else if (taskName.equals("ValidationTask") && (operateType.equals("203") || operateType.equals("55"))) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ResultsTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}nodeAcceptLimit" readonly="readonly"
                   id="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(task.acceptTimeLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>

        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   id="${sheetPageName}nodeCompleteLimit" value="${eoms:date2String(task.completeTimeLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">操作描述*</td>
        <td colspan="3">
		  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea"
                    alt="width:500,allowBlank:false,maxLength:2000,vtext:'请输入评估意见，最多输入1000汉字'">${sheetLink.linkAuditIdea}</textarea>
        </td>
    </tr>
    <%} else if (taskName.equals("LayoutTask") && (operateType.equals("205") || operateType.equals("55"))) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}nodeAcceptLimit" readonly="readonly"
                   id="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(task.acceptTimeLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>

        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   id="${sheetPageName}nodeCompleteLimit" value="${eoms:date2String(task.completeTimeLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">操作描述*</td>
        <td colspan="3">
		  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea"
                    alt="width:500,allowBlank:false,maxLength:2000,vtext:'请输入评估意见，最多输入1000汉字'">${sheetLink.linkAuditIdea}</textarea>
        </td>
    </tr>
    <%} else if (taskName.equals("ResultsTask") && (operateType.equals("204") || operateType.equals("55"))) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="LayoutTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
    <tr>
        <td class="label">操作描述*</td>
        <td colspan="3">
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea"
                                id="${sheetPageName}linkAuditIdea"
                                alt="width:500,allowBlank:false,maxLength:2000,vtext:'请输入评估意见，最多输入1000汉字'">${sheetLink.linkAuditIdea}</textarea>
        </td>
    </tr>
    <%} else if (taskName.equals("HoldTask") && (operateType.equals("18") || operateType.equals("11"))) {%>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan='3'>
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied"
                           defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
			      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                            alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
        </td>
    </tr>

    <%} %>


    <!--流程中的字段域 结束-->


    <!-- 公共功能，抄送和确认受理 -->
    <!-- 抄送 -->
    <%if (taskName.equals("cc")) {%>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                      alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>

    <!-- 确认受理 -->
    <%if (operateType.equals("61")) {%>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
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
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <c:choose>
        <c:when test="${empty fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName == 'DraftTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
    </c:choose>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
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
<%if (taskName.equals("EvaluationTask") && operateType.equals("201")) {%>
<fieldset>
    <legend>
        <bean:message bundle="algorithm" key="role.toOrgName"/>
        <span id="roleName">:算法测试
			 </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'派发',vtext:'请选择派发对象'}]"/>
</fieldset>
<%} %>
<%if (taskName.equals("EvaluationTestTask") && operateType.equals("202")) {%>
<fieldset>
    <legend>
        <bean:message bundle="algorithm" key="role.toOrgName"/>
        <span id="roleName">:测试数据验证
			 </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'派发',vtext:'请选择派发对象'}]"/>
</fieldset>
<%} %>
<%if (taskName.equals("ValidationTask") && operateType.equals("203")) {%>
<fieldset>
    <legend>
        <bean:message bundle="algorithm" key="role.toOrgName"/>
        <span id="roleName">:算法确认
			 </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'派发',vtext:'请选择派发对象'}]"/>
</fieldset>
<%} %>
<%if (taskName.equals("LayoutTask") && operateType.equals("205")) {%>
<fieldset>
    <legend>
        <bean:message bundle="algorithm" key="role.toOrgName"/>
        <span id="roleName">:算法布置
			 </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'派发',vtext:'请选择派发对象'}]"/>
</fieldset>
<%} %>
<%if (taskName.equals("ResultsTask") && operateType.equals("204")) {%>
<fieldset>
    <legend>
        <bean:message bundle="algorithm" key="role.toOrgName"/>
        <span id="roleName">:发布实施、并归档
			 </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'派发',vtext:'请选择派发对象'}]"/>
</fieldset>
<%} %>
<!--抄送 -->
<%if (taskName.equals("cc")) {%>
<fieldset id="link4">
    <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>
<br/>

<div ID="idSpecia2"></div>



 
