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

</table>       