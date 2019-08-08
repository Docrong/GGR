<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String roleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
%>

<!-- base info -->
<input type="hidden" name="id" id="id" value="${sheetMain.id}"/>
<input type="hidden" name="mainId" id="mainId" value="${sheetMain.id}"/>
<input type="hidden" name="sheetKey" id="sheetKey" value="${sheetMain.id}"/>
<input type="hidden" name="sheetId" id="sheetId" value="${sheetMain.sheetId}"/>
<input type="hidden" name="sheetKey" id="sheetKey" value="${sheetMain.id}"/>
<input type="hidden" name="sendUserId" id="sendUserId" value="${sheetMain.sendUserId}"/>
<input type="hidden" name="sendOrgType" id="sendOrgType" value="${sheetMain.sendOrgType}"/>
<input type="hidden" name="status" id="status" value="${status}"/>
<input type="hidden" name="aiid" id="aiid" value="${taskId}"/>
<input type="hidden" name="piid" id="piid" value="${piid}"/>
<input type="hidden" name="activeTemplateId" id="activeTemplateId" value="${taskName}"/>
<input type="hidden" name="correlationKey" id="correlationKey" value="${sheetMain.correlationKey}"/>
<input type="hidden" name="parentCorrelation" id="parentCorrelation" value="${sheetMain.parentCorrelation}"/>
<input type="hidden" name="parentSheetName" id="parentSheetName" value="${sheetMain.parentSheetName}"/>
<input type="hidden" name="parentSheetId" id="parentSheetId" value="${sheetMain.parentSheetId}"/>
<input type="hidden" name="operateUserId" id="operateUserId" value="${sheetMain.sendUserId}"/>
<input type="hidden" name="operateDeptId" id="operateDeptId" value="${sheetMain.sendDeptId}"/>
<input type="hidden" name="tkid" id="tkid" value="${tkid}"/>
<input type="hidden" name="preLinkId" id="preLinkId" value="${preLink.id}"/>
<input type="hidden" name="sendDeptId" id="sendDeptId" value="${sheetMain.sendDeptId}"/>
<input type="hidden" name="operateType" id="operateType" value="${preLink.operateType}"/>
<input type="hidden" name="taskAcceptLimit" id="taskAcceptLimit"
       value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"/>
<input type="hidden" name="taskCompleteLimit" id="taskCompleteLimit"
       value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"/>
<input type="hidden" name="sheetPageName" id="sheetPageName" value=""/>
<input type="hidden" name="methodBeanId" id="methodBeanId" value="${methodBeanId}"/>
<input type="hidden" name="parentPhaseName" id="parentPhaseName" value="${parentPhaseName}"/>
<input type="hidden" name="linkBeanId" id="linkBeanId" value="iSecurityObjAuditLinkManager"/>


<table class="formTable">
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.title"/>*</td>
        <td colspan="3">
            <c:if test="${!empty taskName}">
                <c:if test="${empty sheetMain.title}">
                    <input type="text" name="title" class="text max" id="title"
                           value="${sheetMain.title}"
                           alt="allowBlank:false,maxLength:200,vtext:'请输入工单主题，最大长度为100个汉字！'"/>
                </c:if>
                <c:if test="${!empty sheetMain.title}">
                    <bean:write name="sheetMain" property="title" scope="request"/>
                </c:if>
            </c:if>
            <c:if test="${empty taskName}">
                <input type="text" name="title" class="text max" id="title"
                       value="${sheetMain.title}"
                       alt="allowBlank:false,vtext:'<bean:message bundle="sheet" key="mainForm.titleCheck"/>'"/>
            </c:if>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.sendUserName"/>*</td>
        <td class="content"><eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/></td>
        <td class="label"><bean:message bundle="sheet" key="mainForm.sendDeptName"/>*</td>
        <td class="content"><eoms:id2nameDB id="${sheetMain.sendDeptId}" beanId="tawSystemDeptDao"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.sendRoleName"/>*</td>
        <td colspan="3">

            <c:if test="${empty sheetMain.sendRoleId}">

                <eoms:roleComboBox name="sendRoleId" id="sendRoleId"
                                   userId="${sheetMain.sendUserId}" roleId="4301"
                                   defaultValue="${sheetMain.sendRoleId}"/>
            </c:if>
            <c:if test="${!empty sheetMain.sendRoleId}">

                <input type="hidden" name="sendRoleId" id="sendRoleId" value="${sheetMain.sendRoleId}"/>
                <c:if test="${empty endRoleId}">

                    <input type="hidden" name="operateRoleId" id="operateRoleId" value="${sheetMain.sendRoleId}"/>
                </c:if>
                <c:if test="${!empty endRoleId}">

                    <input type="hidden" name="operateRoleId" id="operateRoleId" value="${endRoleId}"/>
                </c:if>
                <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
            </c:if>
        </td>

    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.sendContact"/>*</td>
        <td>
            <input type="text" class="text" name="sendContact" id="sendContact" value="${sheetMain.sendContact}"
                   alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="sheet" key="mainForm.sendTime"/>*</td>
        <td>
            <input type="text" class="text" name="sendTime" readonly="readonly"
                   id="sendTime" value="${eoms:date2String(sheetMain.sendTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
        </td>
    </tr>


    <c:if test="${status==1}">
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/></td>
            <td colspan="3">
                <eoms:comboBox name="holdStatisfied" id="holdStatisfied" initDicId="10303" styleClass="select"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
            <td colspan="3">
                <textarea name="endResult" id="endResult" alt="width:'500px',allowBlank:false"></textarea>
            </td>
        </tr>

    </c:if>

    <c:if test="${status==-1}">
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endUserName"/></td>
            <td>
                <input type="hidden" name="endUserId" value="${endUserId}"/>
                <eoms:id2nameDB id="${sheetMain.endUserId}" beanId="tawSystemUserDao"/>&nbsp;
            </td>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endDeptName"/></td>
            <td>
                <input type="hidden" name="endDeptId" value="${endDeptId}"/>
                <eoms:id2nameDB id="${endDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endRoleName"/></td>
            <td colspan="3">
                <input type="hidden" name="endRoleId" value="${endRoleId}"/>
                <eoms:id2nameDB id="${endRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="mainForm.cancelReason"/>
            </td>
            <td colspan="3">
                <textarea class="textarea" name="endResult" id="endResult" alt="allowBlank:false"></textarea>
            </td>
        </tr>

    </c:if>
</table>