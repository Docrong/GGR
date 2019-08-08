<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    request.setAttribute("operateType", operateType);
    String operateUserId = "";
    BaseLink bl = (BaseLink) request.getAttribute("preLink");
    IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("SoftwareApply");
    if (bl != null) {
        String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
        if (!prelinkid.equals("")) {
            BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
            operateUserId = base.getOperateUserId();
        }
    }
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userName = sessionform.getUserid();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/softwareApply/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" name="${sheetPageName}beanId" value="iSoftwareApplyMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.softwareApply.model.SoftwareApplyMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.softwareApply.model.SoftwareApplyLink"/>
    <c:if test="${taskName != 'HoldTask' }">
        <%if (operateType.equals("")) { %>
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${task.operateRoleId}"/>
        <%} else {%>
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
        <%} %>
    </c:if>

    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                   value="true"/>
        </c:when>
    </c:choose>


    <!-- 审核 -->
    <%if (taskName.equals("AuditingTask")) { %>
    <%if (operateType.equals("200") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入处理意见，最多输入125字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%
        }
    } else if (taskName.equals("ValidateTask")) {
    %>
    <%if (operateType.equals("201") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BatchTask"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入处理意见，最多输入125字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%
        }
    } else if (taskName.equals("BatchTask")) {
    %>
    <%if (operateType.equals("202") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入处理意见，最多输入125字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>

    <c:choose>
        <c:when test="${empty fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName == 'DraftTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${taskName == 'AuditingTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask"/>
        </c:when>
        <c:when test="${taskName == 'ValidateTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BatchTask"/>
        </c:when>
        <c:when test="${taskName == 'BatchTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
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
                          alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入处理意见，最多输入125字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>
    <!-- 待归档 -->
    <%if (taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>

    <%
        String parentProcessName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName"));
        if (parentProcessName.equals("iBusinessPilotMainManager")) {
    %>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="backToBaseStationOpenYN"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BaseStationOpenYN"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId"
           value="CityEleCallTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} %>


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
    <%}%>
    <%}%>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> -->

    <%}%>


    <% if (taskName.equals("cc")) {%>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                      alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>

</table>
<%if (taskName.equals("AuditingTask") && operateType.equals("200")) { %>

<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:测试验证
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="dept" roleId="917" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<%} %>
<%if (taskName.equals("ValidateTask") && operateType.equals("201")) { %>

<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:结果批复
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="dept" roleId="917" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<%} %>
<%if (taskName.equals("BatchTask") && operateType.equals("202")) { %>

<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:派单人
			 </span>
    </legend>
    <div class="x-form-item">
    </div>
</fieldset>
<%} %>

<%if (taskName.equals("HoldTask")) { %>
<%} %>

