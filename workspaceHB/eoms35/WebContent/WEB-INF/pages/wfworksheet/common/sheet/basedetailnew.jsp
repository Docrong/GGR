<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<table class="formTable">
    <caption><bean:message bundle="sheet" key="mainForm.header"/></caption>
    <!-- 工单公共基本信息 -->
    <tr>
        <!-- 工单流水号 -->
        <td class="label"><bean:message bundle="sheet" key="mainForm.sheetId"/></td>
        <td class="content"><bean:write name="sheetMain" property="sheetId" scope="request"/></td>
        <!-- 工单状态 -->
        <td class="label"><bean:message bundle="sheet" key="mainForm.status"/></td>
        <td class="content"><eoms:dict key="dict-sheet-common" dictId="sheetStatus" itemId="${sheetMain.status}"
                                       beanId="id2nameXML"/></td>

    </tr>
    <tr>
        <!-- 工单状态 -->
        <td class="label"><bean:message bundle="sheet" key="mainForm.title"/></td>
        <td colspan='3'><bean:write name="sheetMain" property="title" scope="request"/></td>
    </tr>
    <tr>
        <!-- 工单操作人 -->
        <td class="label"><bean:message bundle="sheet" key="mainForm.sendUserName"/></td>
        <td class="content">
            <eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/>
        </td>
        <!-- 工单操作部门 -->
        <td class="label"><bean:message bundle="sheet" key="mainForm.sendDeptName"/></td>
        <td class="content"><eoms:id2nameDB id="${sheetMain.sendDeptId}" beanId="tawSystemDeptDao"/></td>
    </tr>
    <tr>
        <!-- 操作人角色 -->
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.sendRoleName"/>
        </td>
        <td colspan="3">
            <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
        </td>

    </tr>
    <tr>
        <!-- 操作人联系方式 -->
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.sendContact"/>
        </td>
        <td>
            <bean:write name="sheetMain" property="sendContact" scope="request"/>
        </td>
        <td class="label"><bean:message bundle="sheet" key="mainForm.sendTime"/></td>
        <td><bean:write name="sheetMain" property="sendTime" formatKey="date.formatDateTimeAll" bundle="sheet"
                        scope="request"/></td>
    </tr>
    <!-- 工单公共基本信息结束 -->

    <!-- 如果工单是强制作废，强制归档等，就要显示原因 -->
    <c:if test="${sheetMain.status==-13||sheetMain.status==-14||sheetMain.status==-12}">
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="mainForm.cancelReason"/>
            </td>
            <td colspan='3'>
                <bean:write name="sheetMain" property="cancelReason" scope="request"/>
            </td>
        </tr>
    </c:if>

    <!-- 如果工单是归档工单 -->
    <c:if test="${sheetMain.status==1}">

        <tr>
            <!-- 结束时间 -->
            <td class="label"><bean:message bundle="sheet" key="mainForm.endTime"/></td>
            <td colspan='3'>
                <bean:write name="sheetMain" property="endTime" formatKey="date.formatDateTimeAll" bundle="sheet"
                            scope="request"/>
            </td>
        </tr>
        <tr>
            <!-- 归档满意度 -->
            <td class="label">
                <bean:message bundle="sheet" key="mainForm.holdStatisfied"/>
            </td>
            <td colspan='3'>
                <eoms:id2nameDB id="${sheetMain.holdStatisfied}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <!-- 归档内容 -->
            <td class="label">
                <bean:message bundle="sheet" key="mainForm.endResult"/>
            </td>
            <td colspan='3'>
                <pre><bean:write name="sheetMain" property="endResult" scope="request"/></pre>
            </td>
        </tr>
    </c:if>

    <!-- 如果有调子流程 -->
    <c:if test="${!empty sheetMain.parentSheetId}">
        <tr>
            <!-- 父工单名 -->
            <td class="label">
                <bean:message bundle="sheet" key="mainForm.parentSheetName"/>
            </td>
            <td>
                    ${parentProcessCnName}
            </td>
            <!-- 父工单号 -->
            <td class="label">
                <bean:message bundle="sheet" key="mainForm.parentSheetId"/>
            </td>
            <td>
                    ${parentSheetId}
            </td>
        </tr>
    </c:if>
</table>