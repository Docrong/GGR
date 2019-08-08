<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.businessbackout.model.BusinessBackoutTask businessbackoutLink = (com.boco.eoms.sheet.businessbackout.model.BusinessBackoutTask) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(businessbackoutLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(businessbackoutLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/businessbackout/businessbackout.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
        form.submit();
    }

    function removeTemplate() {
        if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
            var thisform = document.forms[0];
            thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
            thisform.submit();
        }
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/businessbackout/baseinputlinkhtmlnew.jsp" %>
<html:form action="/businessbackout.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="ibusinessbackoutMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businessbackout.model.businessbackoutMain"/> <!--main表Model对象类名-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businessbackout.model.businessbackoutLink"/> <!--link表Model对象类名-->
    <table class="formTable">
        <caption><bean:message bundle="businessbackout" key="businessbackout.header"/></caption>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="input.templateName"/>
            </td>
            <td colspan="3">
                <input type="text" name="templateName" class="text" id="templateName"
                       value="${sheetLink.templateName}"/>
            </td>
        </tr>

        <%if (taskName.equals("TaskCreateAuditHumTask")) { %>
        <%if (operateType.equals("201")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="201"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask"/>
        <%} else if (operateType.equals("202")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="202"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask"/>
        <%}%>
        <%if (!operateType.equals("61")) { %>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.auditResult"/></td>
            <td colspan="3"><textarea class="textarea max" name="${sheetPageName}auditResult"
                                      id="${sheetPageName}auditResult"
                                      alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea></td>
        </tr>
        <%
            }
        } else if (taskName.equals("TaskCompleteAuditHumTask")) {
        %>
        <%if (operateType.equals("203")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="203"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>
        <%} else if (operateType.equals("204")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="204"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        <%}%>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.auditResult"/></td>
            <td colspan="3"><textarea class="textarea max" name="${sheetPageName}auditResult"
                                      id="${sheetPageName}auditResult"
                                      alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea></td>
        </tr>
        <%} else if (taskName.equals("ExcuteHumTask")) {%>
        <%
            if (operateType.equals("61")) {
                //确认受理
        %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
            <%--  <tr>
              <td class="label"><bean:message bundle="businessbackout" key="businessbackout.remark" /></td>
              <td colspan="3"><textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" alt="width:'500px'">${sheetLink.remark}</textarea></td>
           </tr>
           --%>
        <%} else if (operateType.equals("4")) { //驳回%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
        <c:choose>
            <c:when test="${taskName=='TaskCreateAuditHumTask'}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="ByRejectHumTask"/>
            </c:when>
            <c:when test="${taskName=='ExcuteHumTask'}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>
            </c:when>
            <c:when test="${taskName=='TaskCompleteAuditHumTask'}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
            </c:when>
        </c:choose>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.rejectReason"/></td>
            <td colspan="3"><textarea name="${sheetPageName}rejectReason" class="textarea max"
                                      id="${sheetPageName}rejectReason"
                                      alt="width:'500px'">${sheetLink.rejectReason}</textarea></td>
        </tr>
        <%} else if (operateType.equals("205")) { //回复%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="205"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.ndeptContact"/>*</td>
            <td class="content"><input type="text" class="text" value="${sheetLink.ndeptContact}"
                                       name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                                       alt="allowBlank:false,vtext:'${eoms:a2u('请输入网络部门联系人电话！')}'"/></td>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.ndeptContactPhone"/>*</td>
            <td class="content"><input type="text" class="text" value="${sheetLink.ndeptContactPhone}"
                                       name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone"
                                       alt="allowBlank:false,vtext:'${eoms:a2u('请输入网络部门联系人电话！')}'"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealResult"/>*</td>
            <td class="content" colspan="3"><eoms:comboBox defaultValue="${sheetLink.dealResult}"
                                                           name="${sheetPageName}dealResult"
                                                           id="${sheetPageName}dealResult" initDicId="1011007"
                                                           alt="allowBlank:true" styleClass="select-class"
                                                           alt="allowBlank:false,vtext:'${eoms:a2u('请选择处理结果！')}'"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealDesc"/></td>
            <td colspan="3"><textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                                      alt="width:500,allowBlank:true">${sheetLink.dealDesc}</textarea></td>
        </tr>
        <%} else if (operateType.equals("206")) { //回复送审%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="206"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
               value="TaskCompleteAuditHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.ndeptContact"/>*</td>
            <td class="content"><input type="text" class="text" value="${sheetLink.ndeptContact}"
                                       name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                                       alt="allowBlank:false,vtext:'${eoms:a2u('请输入网络部门联系人电话！')}'"/></td>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.ndeptContactPhone"/>*</td>
            <td class="content"><input type="text" class="text" value="${sheetLink.ndeptContactPhone}"
                                       name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone"
                                       alt="allowBlank:false,vtext:'${eoms:a2u('请输入网络部门联系人电话！')}'"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealResult"/>*</td>
            <td class="content" colspan="3"><eoms:comboBox defaultValue="${sheetLink.dealResult}"
                                                           name="${sheetPageName}dealResult"
                                                           id="${sheetPageName}dealResult" initDicId="1011007"
                                                           alt="allowBlank:true" styleClass="select-class"
                                                           alt="allowBlank:false,vtext:'${eoms:a2u('请选择处理结果！')}'"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealDesc"/></td>
            <td colspan="3"><textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                                      alt="width:500,allowBlank:true">${sheetLink.dealDesc}</textarea></td>
        </tr>
        <%} else if (operateType.equals("10")) { //转派%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="10"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.transmitReason"/></td>
            <td colspan="3"><textarea class="textarea max" name="${sheetPageName}transferReason"
                                      id="${sheetPageName}transferReason"
                                      alt="width:500,allowBlank:true">${sheetLink.transferReason}</textarea></td>
        </tr>
        <% } else if (operateType.equals("8")) { //移交%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.yijiaoresion"/></td>
            <td colspan="3"><textarea class="textarea max" name="${sheetPageName}transferReason"
                                      id="${sheetPageName}transferReason"
                                      alt="width:500,allowBlank:true">${sheetLink.transferReason}</textarea></td>
        </tr>
        <%}%>
        <%} else if (taskName.equals("AffirmHumTask")) {%>
        <%if (operateType.equals("7")) { //处理回复不通过%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealDesc"/></td>
            <td colspan="3"><textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                                      alt="width:500,allowBlank:true">${sheetLink.dealDesc}</textarea></td>
        </tr>

        <%} else if (operateType.equals("6")) { //处理回复通过%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="6"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive"/>
        <tr>
            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealDesc"/></td>
            <td colspan="3"><textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                                      alt="width:500,allowBlank:true">${sheetLink.dealDesc}</textarea></td>
        </tr>

        <%}%>
        <%} else if (taskName.equals("DraftHumTask")) {%>

        <%if (operateType.equals("22")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="22"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
               value="TaskCreateAuditHumTask"/>
        <%}%>

        <%} else if (taskName.equals("HoldHumTask")) {%>
        <%if (operateType.equals("18")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10303" styleClass="select"
                               alt="allowBlank:false,vtext:'${eoms:a2u('请选择归档满意度！')}'"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                          class="textarea max">${sheetMain.endResult}</textarea>
            </td>
        </tr>
        <%}%>
        <%} else if (taskName.equals("cc")) {%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <%--
            <tr>
              <td class="label"><bean:message bundle="businessbackout" key="businessbackout.remark" /></td>
              <td colspan="3"><textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea></td>
            </tr>
            --%>
        <%} %>
    </table>
    </table>
</html:form>
<logic:present name="type">
    <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
        <html:button styleClass="btn" property="method.save" styleId="method.save"
                     onclick="saveDealTemplate('current')">
            <bean:message bundle="sheet" key="button.saveCurTemplate"/>
        </html:button>
    </c:if>
    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
        <bean:message bundle="sheet" key="button.delete"/>
    </html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
    <bean:message bundle="sheet" key="button.back"/>
</html:button>
