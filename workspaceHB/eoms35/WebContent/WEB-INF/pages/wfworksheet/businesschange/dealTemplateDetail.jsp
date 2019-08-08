<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.businesschange.model.BusinessChangeLink businesschangeLink = (com.boco.eoms.sheet.businesschange.model.BusinessChangeLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(businesschangeLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(businesschangeLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/businesschange/businesschange.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/businesschange/baseinputlinkhtmlnew.jsp" %>
<html:form action="/businesschange.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="ibusinesschangeMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businesschange.model.businesschangeMain"/> <!--main\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businesschange.model.businesschangeLink"/> <!--link\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <table class="formTable">
        <caption><bean:message bundle="businesschange" key="businesschange.header"/></caption>
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <%if (operateType.equals("201")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask"/>
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.auditResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                          alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea>
            </td>
        </tr>
        <%} else if (operateType.equals("202")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask"/>

        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.auditResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                          alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea>
            </td>
        </tr>
        <%}%>
        <%} else if (taskName.equals("TaskCompleteAuditHumTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <%if (operateType.equals("208")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.auditResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                          alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea>
            </td>
        </tr>


        <%} else if (operateType.equals("209")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>

        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.auditResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                          alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea>
            </td>
        </tr>
        <%}%>
        <%} else if (taskName.equals("ExcuteHumTask")) {%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <%if (operateType.equals("205")) { //执行回复%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>

        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.ndeptContact"/>*</td>
            <td class="content">
                    <eoms:comboBox name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                                   initDicId="1010509" alt="allowBlank:false"
                                   defaultValue="${sheetPageName}ndeptContact" styleClass="select-class"/>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.ndeptContactPhone"/>*</td>
            <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                                       value="${sheetLink.ndeptContactPhone}" id="${sheetPageName}ndeptContactPhone"
                                       alt="allowBlank:false"/></td>
        </tr>
        <!-- GPRS -->
        <logic:equal value="101100101" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.apnID"/>*</td>
                <td class="content" colspan='3'><input type="text" class="text" name="${sheetPageName}apnID"
                                                       id="${sheetPageName}apnID" value="${sheetLink.apnID}"
                                                       alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>

        <!-- MMS -->
        <logic:equal value="101100102" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.loginUserName"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserNam"
                                           id="${sheetPageName}loginUserNam" value="${sheetLink.loginUserNam}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.loginUserPassWord"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserPas"
                                           id="${sheetPageName}loginUserPas" value="${sheetLink.loginUserPassWord}"
                                           alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>
        <!-- SMS -->
        <logic:equal value="101100103" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange."/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserName"
                                           id="${sheetPageName}loginUserName" value="${sheetLink.loginUserName}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.loginUserPassWord"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserPas"
                                           id="${sheetPageName}loginUserPas" value="${sheetLink.loginUserPas}"
                                           alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>
        <!-- chuanshu -->
        <logic:equal value="101100104" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.expectFinishdays"/></td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}expectFinish"
                                           id="${sheetPageName}expectFinish" value="${sheetLink.expectFinish}"
                                           alt="allowBlank:true"/></td>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.circuitCode"/></td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}circuitCode"
                                           id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"
                                           alt="allowBlank:true"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.netResCapacity"/></td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}netResCapaci"
                                           id="${sheetPageName}netResCapaci" value="${sheetLink.netResCapaci}"
                                           alt="allowBlank:true"/></td>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.clientPgmCapacity"/></td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}clientPgmCap"
                                           id="${sheetPageName}clientPgmCap" value="${sheetLink.clientPgmCap}"
                                           alt="allowBlank:true"/></td>

            </tr>
        </logic:equal>
        <tr>

            <td class="label"><bean:message bundle="businesschange" key="businesschange.dealResult"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                               initDicId="1010509" alt="allowBlank:false" defaultValue="${sheetLink.dealResult}"
                               styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.dealDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                          alt="width:500,allowBlank:false">${sheetLink.dealDesc }</textarea>
            </td>
        </tr>


        <%} else if (operateType.equals("206")) { //回复送审%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="206"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
               value="TaskCompleteAuditHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.ndeptContact"/>*</td>
            <td class="content">
                    <eoms:comboBox name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                                   initDicId="1010509" alt="allowBlank:false" defaultValue="${sheetLink.ndeptContact}"
                                   styleClass="select-class"/>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.ndeptContactPhone"/>*</td>
            <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                                       id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}"
                                       alt="allowBlank:false"/></td>
        </tr>
        <!-- MMS -->
        <logic:equal value="101010602" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.loginUserName"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserNam"
                                           id="${sheetPageName}loginUserNam" value="${sheetLink.loginUserNam}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.loginUserPassWord"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserPas"
                                           id="${sheetPageName}loginUserPas" value="${sheetLink.loginUserPas}"
                                           alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>
        <!-- SMS -->
        <logic:equal value="101010603" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange."/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserNam"
                                           id="${sheetPageName}loginUserNam" value="${sheetLink.loginUserNam}"
                                           alt="allowBlank:false"/></td>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.loginUserPassWord"/>*</td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}loginUserPas"
                                           id="${sheetPageName}loginUserPas" value="${sheetLink.loginUserPas}"
                                           alt="allowBlank:false"/></td>
            </tr>
        </logic:equal>
        <!-- chuanshu -->
        <logic:equal value="101010604" property="businesstype1" name="sheetMain">
            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.expectFinishdays"/></td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}expectFinish"
                                           id="${sheetPageName}expectFinish" value="${sheetLink.expectFinish}"
                                           alt="allowBlank:true"/></td>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.circuitCode"/></td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}circuitCode"
                                           id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"
                                           alt="allowBlank:true"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.netResCapacity"/></td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}netResCapaci"
                                           id="${sheetPageName}netResCapaci" value="${sheetLink.netResCapaci}"
                                           alt="allowBlank:true"/></td>
                <td class="label"><bean:message bundle="businesschange" key="businesschange.clientPgmCapacity"/></td>
                <td class="content"><input type="text" class="text" name="${sheetPageName}clientPgmCap"
                                           id="${sheetPageName}clientPgmCap" value="${sheetLink.clientPgmCap}"
                                           alt="allowBlank:true"/></td>

            </tr>
        </logic:equal>
        <tr>

            <td class="label"><bean:message bundle="businesschange" key="businesschange.dealResult"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                               initDicId="1010509" alt="allowBlank:false" defaultValue="${sheetLink.dealResult }"
                               styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.dealDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                          alt="width:500,allowBlank:false">${sheetLink.dealDesc }</textarea>
            </td>
        </tr>
        <%} else if (operateType.equals("10")) { //分派%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask"/>
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.transmitReason"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}transmitReason" id="${sheetPageName}transmitReason"
                          alt="width:500,allowBlank:true">${sheetLink.transmitReason }</textarea>
            </td>
        </tr>
        <% } else if (operateType.equals("8")) {//移交 %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask"/>
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.yijiaoReason"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}transmitReason" id="${sheetPageName}transmitReason"
                          alt="width:500,allowBlank:true">${sheetLink.transmitReason }</textarea>
            </td>
        </tr>
        <%}%>
        <%} else if (taskName.equals("AffirmHumTask")) {%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <%if (operateType.equals("212")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.dealResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                          alt="width:500,allowBlank:true">${sheetLink.dealResult }</textarea>
            </td>
        </tr>

        <%} else if (operateType.equals("211")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive"/>


        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.dealResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                          alt="width:500,allowBlank:true">${sheetLink.dealResult }</textarea>
            </td>
        </tr>
        <%}%>
        <%} else if (taskName.equals("HoldHumTask")) {%>
        <%if (operateType.equals("18")) { %>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10303" styleClass="select" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
            <td colspan="3">
                <textarea name="${sheetPageName}endResult"
                          alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请填入信息，最多输入1000字')}'"
                          id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
            </td>
        </tr>
        <%}%>
        <%
            }
            if (taskName.equals("cc")) {
        %>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
                <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:1000,width:500,vtext:'${eoms:a2u('请填入信息，最多输入1000字')}'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%} %>
        <%if (taskName.equals("TaskCreateAuditHumTask") || taskName.equals("ExcuteHumTask") || taskName.equals("TaskCompleteAuditHumTask") || taskName.equals("AffirmHumTask")) {%>

        <%if (operateType.equals("61")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
            <%-- <tr>
               <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark" />
                </td>
                <td colspan="3">
                    <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                    alt="width:'500px',maxLength:1000,vtext:'${eoms:a2u('请最多输入1000字')}'">${sheetLink.remark}</textarea>
              </td>
            </tr>  	--%>

        <%
                }
            }
        %>

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
