<%@page import="com.boco.eoms.base.model.BaseObject" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<!-- base info -->
<input type="hidden" name="${sheetPageName}linkId" id="${sheetPageName}linkId" value="${sheetLink.id}"/>
<input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetLink.id}"/>
<input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
<input type="hidden" name="${sheetPageName}piid" value="${sheetMain.piid}"/>
<!-- <input type="hidden" name="${sheetPageName}aiid" value="${sheetLink.aiid}"/> -->
<input type="hidden" name="${sheetPageName}activeTemplateId" value="${taskName}"/>
<input type="hidden" name="${sheetPageName}mainId" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
<input type="hidden" name="${sheetPageName}TKID" value="${sheetLink.tkid}"/>
<input type="hidden" name="${sheetPageName}preLinkId" value="${preLink.id}"/>
<input type="hidden" name="${sheetPageName}taskCompleteTime" value="${eoms:date2String(preLink.nodeCompleteLimit)}"/>
<input type="hidden" name="${sheetPageName}taskName" value="${taskName}"/>
<input type="hidden" name="${sheetPageName}taskStatus" value="${taskStatus}"/>
<input type="hidden" name="sheetPageName" id="sheetPageName" value="${sheetPageName}"/>
<input type="hidden" name="methodBeanId" id="methodBeanId" value="${methodBeanId}"/>
<input type="hidden" name="${sheetPageName}aiid" value="${sheetLink.tkid}"/>

<table class="formTable">
    <caption><bean:message bundle="sheet" key="linkForm.header"/></caption>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operateUserName"/>*
            <input type="hidden" name="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
        </td>
        <td class="content">
            <eoms:id2nameDB id="${sheetLink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operateDeptName"/>*
            <input type="hidden" name="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
        </td>
        <td class="content">
            <eoms:id2nameDB id="${sheetLink.operateDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
        </td>
    </tr>
    <%
        String operateType1 = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));

        if (operateType1.equals("61") || operateType1.equals("-10") || operateType1.equals("-15") || operateType1.equals("4") || operateType1.equals("15") || operateType1.equals("nullity") || operateType1.equals("forceNullity") || operateType1.equals("forceHold")) {
    %>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operateRoleName"/>*
            <input type="hidden" name="${sheetPageName}operateRoleId" value="${sheetLink.operateRoleId}"/>
        </td>
        <td>
            <c:if test="${!empty sheetLink.operateRoleId}">
                <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
                <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
            </c:if>
            <c:if test='${empty sheetLink.operateRoleId}'>
                <input type="hidden" name="${sheetPageName}operateRoleId" value="${sheetLink.operateUserId}"/>
                <eoms:id2nameDB id="${sheetLink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
            </c:if>
        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operateTime"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}operateTime" readonly="readonly"
                   value="${eoms:date2String(sheetLink.operateTime)}" id="${sheetPageName}operateTime"
                   alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operaterContact"/>*
        </td>
        <td class="content" colspan="3">
            <input type="text" class="text" name="${sheetPageName}operaterContact"
                   id="${sheetPageName}operaterContact" value="${sheetLink.operaterContact}" alt="allowBlank:false"/>
        </td>

    </tr>

    <%
    }
    //if(!operateType1.equals("61") && !operateType1.equals("15") &&!operateType1.equals("nullity") &&!operateType1.equals("forceNullity") &&!operateType1.equals("forceHold")){
    else {
    %>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operateRoleName"/>*
            <input type="hidden" name="${sheetPageName}operateRoleId" value="${sheetLink.operateRoleId}"/>
        </td>
        <td colspan="3">
            <c:if test="${!empty sheetLink.operateRoleId}">
                <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
                <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
            </c:if>
            <c:if test='${empty sheetLink.operateRoleId}'>
                <input type="hidden" name="${sheetPageName}operateRoleId" value="${sheetLink.operateUserId}"/>
                <eoms:id2nameDB id="${sheetLink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operaterContact"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}operaterContact"
                   id="${sheetPageName}operaterContact" value="${sheetLink.operaterContact}" alt="allowBlank:false"/>
        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operateTime"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}operateTime" readonly="readonly"
                   value="${eoms:date2String(sheetLink.operateTime)}" id="${sheetPageName}operateTime"
                   alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <c:set var="vtext1" value="${eoms:a2u('受理时限不能晚于处理时限')}"/>
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${vtext1}',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <c:set var="vtext2" value="${eoms:a2u('完成时限不能早于受理时限')}"/>
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${vtext2}',allowBlank:false"/>
        </td>
    </tr>

    <%} %>

</table>
