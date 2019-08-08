<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink urgentfaultLink = (com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(urgentfaultLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(urgentfaultLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save"/>');
            return false;
        }
        form.action = "${app}/sheet/urgentfault/urgentfault.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/commonfault/baseinputlinkhtmlnew.jsp" %>
<html:form action="/urgentfault.do" styleId="theform">
    <br/>

    <input type="hidden" name="${sheetPageName}beanId" value="iurgentfaultMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.urgentfault.model.urgentfaultMain"/> <!--main表Model对象类名-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.urgentfault.model.urgentfaultLink"/> <!--link表Model对象类名-->
    <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->

    <table class="listTable">
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="input.templateName"/>
            </td>
            <td colspan="3">
                <input type="text" name="templateName" class="text" id="templateName"
                       value="${sheetLink.templateName}"/>
            </td>
        </tr>
        <%
            if (taskName.equals("MiddleReportAffirmHumTask") ||
                    taskName.equals("BackReportAffirmHumTask") ||
                    taskName.equals("SolveFaultAffirmHumTask")) {
        %>

        <%if (operateType.equals("913") || operateType.equals("923") || operateType.equals("933")) { %>

        <%if (taskName.equals("MiddleReportAffirmHumTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="913"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SolveFaultHumTask"/>
        <%} %>

        <%if (taskName.equals("BackReportAffirmHumTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="923"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
               value="SolveFaultAffirmHumTask"/>
        <%} %>

        <%if (taskName.equals("SolveFaultAffirmHumTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="933"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReportHumTask"/>
        <%} %>


        <tr>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIfGreatFault" id="${sheetPageName}linkIfGreatFault"
                               initDicId="1011608" defaultValue="${sheetLink.linkIfGreatFault}"/>
            </td>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainGreatFaultId"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkGreatFaultId"
                       id="${sheetPageName}linkGreatFaultId" value="${sheetLink.linkGreatFaultId}"
                       alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkIfAffirm1"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIfAffirm" id="${sheetPageName}linkIfAffirm" initDicId="1011608"
                               defaultValue="${sheetLink.linkIfAffirm}"/>
            </td>
        </tr>


        <%if (taskName.equals("SolveFaultAffirmHumTask")) {%>
        <tr>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkFaultAvoidReport"/></td>
            <td colspan="3">
                <textarea name="linkFaultAvoidReport" id="linkFaultAvoidReport"
                          class="textarea max">${sheetLink.linkFaultAvoidReport}</textarea>
            </td>
        </tr>
        <%} else { %>
        <tr>
            <td class="label">
                <%if (taskName.equals("MiddleReportAffirmHumTask")) {%>
                <bean:message bundle="urgentfault" key="urgentfault.linkFaultReportDesc2"/>
                <%} %>
                <%if (taskName.equals("BackReportAffirmHumTask")) {%>
                <bean:message bundle="urgentfault" key="urgentfault.linkFaultReportDesc1"/>
                <%} %>
            </td>
            <td colspan="3">
                <textarea name="linkFaultReportDesc" id="linkFaultReportDesc"
                          class="textarea max">${sheetLink.linkFaultReportDesc}</textarea>
            </td>
        </tr>
        <%} %>


        <%} %>

        <%if (operateType.equals("4")) { %>

        <%if (taskName.equals("MiddleReportAffirmHumTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="914"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DraftHumTask"/>
        <%} %>

        <%if (taskName.equals("BackReportAffirmHumTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="924"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DraftHumTask"/>
        <%} %>

        <%if (taskName.equals("SolveFaultAffirmHumTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="934"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SolveFaultHumTask"/>
        <%} %>

        <tr>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkRejectReason"/></td>
            <td colspan="3">
                <textarea name="linkFaultReportDesc" id="linkFaultReportDesc"
                          class="textarea max">${sheetLink.linkFaultReportDesc}</textarea>
            </td>
        </tr>

        <%} %>


        <tr>
            <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
            <td colspan="3">
                <eoms:attachment idList="" idField="${sheetPageName}nodeAccessories" appCode="urgentfault"/>
            </td>
        </tr>

        <%}%>

        <!-- 待销障，销障上报 -->
        <%if (taskName.equals("SolveFaultHumTask")) {%>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="93"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
               value="SolveFaultAffirmHumTask"/>

        <tr>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIfGreatFault" id="${sheetPageName}linkIfGreatFault"
                               initDicId="1011608" defaultValue="${sheetLink.linkIfGreatFault}"/>
            </td>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultGenerantPlace"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkFaultGenerantPlace"
                       id="${sheetPageName}linkFaultGenerantPlace" value="${sheetLink.linkFaultGenerantPlace}"
                       alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultAvoidTime"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkFaultAvoidTime" readonly="readonly"
                       id="${sheetPageName}linkFaultAvoidTime"
                       onclick="popUpCalendar(this, this)" value="${eoms:date2String(sheetLink.linkFaultAvoidTime)}"/>
            </td>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectTimeLength"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkAffectTimeLength"
                       id="${sheetPageName}linkAffectTimeLength" value="${sheetLink.linkAffectTimeLength}"
                       alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectOperationDesc"/></td>
            <td colspan="3">
                <textarea name="linkAffectOperationDesc" id="linkAffectOperationDesc"
                          class="textarea max">${sheetLink.linkAffectOperationDesc}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkFaultAvoidReport"/></td>
            <td colspan="3">
                <textarea name="linkFaultAvoidReport" id="linkFaultAvoidReport"
                          class="textarea max">${sheetLink.linkFaultAvoidReport}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
            <td colspan="3">
                <eoms:attachment idList="" idField="${sheetPageName}nodeAccessories" appCode="urgentfault"/>
            </td>
        </tr>

        <%}%>


        <%if (taskName.equals("ReportHumTask")) {%>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="94"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SumUpHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
            <td colspan="3">
                <eoms:attachment idList="" idField="${sheetPageName}nodeAccessories" appCode="urgentfault"/>
            </td>
        </tr>

        <%}%>
        <% if (taskName.equals("cc")) {%>

        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>
            </td>
            <td colspan="3">
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                             alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
            </td>
        </tr>
        <%} %>


        <%if (taskName.equals("SumUpHumTask")) {%>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="95"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldHumTask"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
            <td colspan="3">
                <eoms:attachment idList="" idField="${sheetPageName}nodeAccessories" appCode="urgentfault"/>
            </td>
        </tr>

        <%}%>

    </table>
    <br>
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
       