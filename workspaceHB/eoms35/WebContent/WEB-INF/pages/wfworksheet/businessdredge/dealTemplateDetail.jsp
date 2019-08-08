<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.businessdredge.model.BusinessDredgeLink businessdredgeLink = (com.boco.eoms.sheet.businessdredge.model.BusinessDredgeLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(businessdredgeLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(businessdredgeLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/businessdredge/businessdredge.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/businessdredge/baseinputlinkhtmlnew.jsp" %>
<html:form action="/businessdredge.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="ibusinessdredgeMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businessdredge.model.businessdredgeMain"/> <!--main\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businessdredge.model.businessdredgeLink"/> <!--link\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <table class="formTable">
        <caption><bean:message bundle="businessdredge" key="businessdredge.header"/></caption>
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
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.auditResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                          alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea>
            </td>
        </tr>
        <%} else if (operateType.equals("202")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="202"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.auditResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                          alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea>
            </td>
        </tr>
        <%}%>

        <%} else if (taskName.equals("TaskCompleteAuditHumTask")) { %>
        <%if (operateType.equals("203")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="203"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>
        <%} else if (operateType.equals("204")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="204"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        <%}%>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.auditResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                          alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea>
            </td>
        </tr>
        <%
        } else if (taskName.equals("TaskCreateAuditHumTask") || taskName.equals("ExcuteHumTask")
                || taskName.equals("TaskCompleteAuditHumTask") || taskName.equals("AffirmHumTask")) {
        %>
        <%
            if (operateType.equals("61")) {
                //????
        %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
            <%--  <tr>
              <td class="label">
              <bean:message bundle="sheet" key="linkForm.remark" />
              </td>
              <td colspan="3">
               <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                alt="width:'500px'">${sheetLink.remark}</textarea>
              </td>
           </tr>
           --%>
        <%} else if (operateType.equals("4")) { //??%>
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
            <td class="label">
                <bean:message bundle="businessdredge" key="businessdredge.rejectCause"/>
            </td>
            <td colspan="3">
		          <textarea name="${sheetPageName}rejectReason" class="textarea max" id="${sheetPageName}rejectReason"
                            alt="width:'500px'">${sheetLink.rejectReason}</textarea>
            </td>
        </tr>
        <%} else if (operateType.equals("205")) { //??%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="205"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>

        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.ndeptContact"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                       value="${sheetLink.ndeptContact}" alt="allowBlank:false"/></td>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.ndeptContactPhone"/>*</td>
            <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                                       id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}"
                                       alt="allowBlank:false"/></td>
        </tr>
        <!-- GPRS -->
        <logic:equal value="101100101" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.apnID"/>*</td>
                <td class="content" colspan='3'>
                    <input type="text" class="text" name="${sheetPageName}apnID" id="${sheetPageName}apnID"
                           value="${sheetLink.apnID}" alt="allowBlank:false"/>
                </td>
            </tr>
        </logic:equal>

        <!-- MMS -->
        <logic:equal value="101100102" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.loginUserName"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserName"
                                           id="${sheetPageName}loginUserName" value="${sheetLink.loginUserName}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.loginUserPassWord"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserPassWord"
                                           id="${sheetPageName}loginUserPassWord" value="${sheetLink.loginUserPassWord}"
                                           alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>
        <!-- SMS -->
        <logic:equal value="101100103" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.loginUserName"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserName"
                                           id="${sheetPageName}loginUserName" value="${sheetLink.loginUserName}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.loginUserPassWord"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserPassWord"
                                           id="${sheetPageName}loginUserPassWord" value="${sheetLink.loginUserPassWord}"
                                           alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>
        <!-- chuanshu -->
        <logic:equal value="101100104" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.expectFinishdays"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}expectFinishdays"
                                           id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.circuitCode"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}circuitCode"
                                           id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"
                                           alt="allowBlank:false"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.netResCapacity"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}netResCapacity"
                                           id="${sheetPageName}netResCapacity" value="${sheetLink.netResCapacity}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.clientPgmCapacity"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}clientPgmCapacity"
                                           id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}"
                                           alt="allowBlank:false"/></td>

            </tr>
        </logic:equal>
        <tr>

            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.dealResult"/>*</td>
            <td colspan='3'>
                <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                               defaultValue="${sheetLink.dealResult}"
                               initDicId="1010509" alt="allowBlank:false" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.dealDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                          alt="width:500,allowBlank:false">${sheetLink.dealDesc}</textarea>
            </td>
        </tr>


        <%} else if (operateType.equals("206")) { //????%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="206"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
               value="TaskCompleteAuditHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.ndeptContact"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                       value="${sheetLink.ndeptContact}" alt="allowBlank:false"/></td>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.ndeptContactPhone"/>*</td>
            <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                                       id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}"
                                       alt="allowBlank:false"/></td>
        </tr>
        <!-- GPRS -->
        <logic:equal value="101100101" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.apnID"/>*</td>
                <td class="content" colspan='3'>
                    <input type="text" class="text" name="${sheetPageName}apnID" id="${sheetPageName}apnID"
                           value="${sheetLink.apnID}" alt="allowBlank:false"/>
                </td>
            </tr>
        </logic:equal>

        <!-- MMS -->
        <logic:equal value="101100102" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.loginUserName"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserName"
                                           id="${sheetPageName}loginUserName" value="${sheetLink.loginUserName}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.loginUserPassWord"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserPassWord"
                                           id="${sheetPageName}loginUserPassWord" value="${sheetLink.loginUserPassWord}"
                                           alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>
        <!-- SMS -->
        <logic:equal value="101100103" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.loginUserName"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserName"
                                           id="${sheetPageName}loginUserName" value="${sheetLink.loginUserName}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.loginUserPassWord"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserPassWord"
                                           id="${sheetPageName}loginUserPassWord" value="${sheetLink.loginUserPassWord}"
                                           alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>
        <!-- chuanshu -->
        <logic:equal value="101100104" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.expectFinishdays"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}expectFinishdays"
                                           id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.circuitCode"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}circuitCode"
                                           id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"
                                           alt="allowBlank:false"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.netResCapacity"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}netResCapacity"
                                           id="${sheetPageName}netResCapacity" value="${sheetLink.netResCapacity}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businessdredge" key="businessdredge.clientPgmCapacity"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}clientPgmCapacity"
                                           id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}"
                                           alt="allowBlank:false"/></td>

            </tr>
        </logic:equal>
        <tr>

            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.dealResult"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                               defaultValue="${sheetLink.dealResult}"
                               initDicId="1010509" alt="allowBlank:false" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.dealDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                          alt="width:500,allowBlank:false">${sheetLink.dealDesc}</textarea>
            </td>
        </tr>
        <%} else if (operateType.equals("10")) { //??%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="10"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.transmitReason"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason"
                          alt="width:500,allowBlank:true">${sheetLink.transferReason}</textarea>
            </td>
        </tr>
        <% } else if (operateType.equals("8")) {//?? %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.yijiaoresion"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason"
                          alt="width:500,allowBlank:true">${sheetLink.transferReason}</textarea>
            </td>
        </tr>

        <%}%>
        <%} else if (taskName.equals("AffirmHumTask")) {%>
        <%if (operateType.equals("7")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.dealResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                          alt="width:500,allowBlank:true">${sheetLink.dealDesc}</textarea>
            </td>
        </tr>

        <%} else if (operateType.equals("6")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="6"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive"/>
        <tr>
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.dealResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                          alt="width:500,allowBlank:true">${sheetLink.dealDesc}</textarea>
            </td>
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
            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.holdStatisfied"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10303" defaultValue="${sheetMain.holdStatisfied}" styleClass="select"
                               alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>

            <td class="label"><bean:message bundle="businessdredge" key="businessdredge.endResult"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                          value="${sheetMain.endResult}"
                          alt="width:500,allowBlank:false">${sheetMain.endResult}</textarea>
            </td>
        </tr>

        <%}%>
        <%} else if (taskName.equals("cc")) {%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <%--
            <tr>
              <td class="label"><bean:message bundle="businessdredge" key="businessdredge.remark" /></td>
              <td colspan="3"><textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea></td>
            </tr>
             --%>
        <%}%>
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
