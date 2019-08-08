<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table class="formTable">
    <!-- 工单流水号 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.sheetId"/>
        </td>
        <td width="100%">
            <input type="hidden" name="sheetIdStringExpression" value="like"/>
            <input type="text" name="main.sheetId" id="sheetId" size="30" class="text"/>
        </td>
    </tr>
    <!-- 工单标题 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.title"/>
        </td>
        <td>
            <input type="hidden" name="titleStringExpression" value="like"/>
            <input type="text" name="main.title" id="title" size="30" class="text"/>
        </td>
    </tr>
    <!-- 工单状态 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.status"/>
        </td>
        <td>
            <input type="hidden" name="main.status"/>
            <select name="statusChoiceExpression" onchange="selectStep(this)">
                <option value=""></option>
                <option value="0"><bean:message bundle="sheet" key="query.status.running"/></option>
                <option value="1"><bean:message bundle="sheet" key="query.status.hold"/></option>
                <option value="-1"><bean:message bundle="sheet" key="query.status.cancel"/></option>
                <option value="-14"><bean:message bundle="sheet" key="query.status.forceHold"/></option>
                <option value="-13"><bean:message bundle="sheet" key="query.status.forceCancel"/></option>
                <option value="-12"><bean:message bundle="sheet" key="query.status.blankOut"/></option>
                <option value="5">锁单</option>
            </select>
        </td>
    </tr>
    <!-- 工单处理环节 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.stepId"/>
        </td>
        <td>
            <input type="hidden" name="task.taskName"/>
            <select name="taskNameChoiceExpression" disabled="disabled" id="stepId">
                <option value=""></option>
                <logic:iterate name="stepIdList" id="stepId">
                    <option value="${stepId}"> ${phaseIdMap[stepId]}</option>
                </logic:iterate>
            </select>

        </td>
    </tr>
    <!-- 工单派单对象 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.sendObject"/>
        </td>
        <td class="content">
            <select id="sendsel">
                <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
                <option value="role"><bean:message bundle="sheet" key="query.status.role"/></option>
                <option value="dept"><bean:message bundle="sheet" key="query.status.dept"/></option>
                <option value="user"><bean:message bundle="sheet" key="query.status.user"/></option>
            </select>
            <div id="viewer" class="viewer-list"></div>
            <input type="button" name="showSendRole"
                   value="<bean:message bundle="sheet" key="query.status.selectRole"/>" class="btn" id="showSendRole">
            <input type="hidden" name="sendRoleIdStringExpression" value="in"/>
            <input type="hidden" name="main.sendRoleId" id="toSendRoleId"/>
            <input type="button" name="showSendDpt" value="<bean:message bundle="sheet" key="query.status.selectDept"/>"
                   class="btn" id="showSendDpt">
            <input type="hidden" name="sendDeptIdStringExpression" value="in"/>
            <input type="hidden" name="main.sendDeptId" id="toSendDptId"/>
            <input type="button" name="showSendUser"
                   value="<bean:message bundle="sheet" key="query.status.selectUser"/>" class="btn" id="showSendUser">
            <input type="hidden" name="sendUserIdStringExpression" value="in"/>
            <input type="hidden" name="main.sendUserId" id="toSendUserId"/>
        </td>
    </tr>
    <!-- 工单处理对象 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.dealObject"/>
        </td>
        <td class="content">
            <select id="dealsel">
                <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
                <option value="role"><bean:message bundle="sheet" key="query.status.role"/></option>
                <option value="dept"><bean:message bundle="sheet" key="query.status.dept"/></option>
                <option value="user"><bean:message bundle="sheet" key="query.status.user"/></option>
            </select>
            <div id="viewer-deal" class="viewer-list"></div>
            <input type="hidden" name="operateRoleIdStringExpression" value="in"/>
            <input type="hidden" name="link.operateRoleId" id="toDealRoleId"/>
            <input type="hidden" name="operateDeptIdStringExpression" value="in"/>
            <input type="hidden" name="link.operateDeptId" id="toDealDptId"/>
            <input type="hidden" name="operateUserIdStringExpression" value="in"/>
            <input type="hidden" name="link.operateUserId" id="toDealUserId"/>
            <input type="button" name="showDealRole"
                   value="<bean:message bundle="sheet" key="query.status.selectRole"/>" class="btn" id="showDealRole">
            <input type="button" name="showDealDpt" value="<bean:message bundle="sheet" key="query.status.selectDept"/>"
                   class="btn" id="showDealDpt">
            <input type="button" name="showDealUser"
                   value="<bean:message bundle="sheet" key="query.status.selectUser"/>" class="btn" id="showDealUser">

        </td>
    </tr>
</table>