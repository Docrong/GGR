<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.greatevent.model.GreatEventLink greateventlink = (com.boco.eoms.sheet.greatevent.model.GreatEventLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(greateventlink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(greateventlink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/greatevent/greatevent.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/greatevent/baseinputlinkhtmlnew.jsp" %>
<html:form action="/greatevent.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="iGreatEventMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.greatevent.model.GreatEventMain"/> <!--main\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.greatevent.model.GreatEventLink"/> <!--link\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <table class="formTable">
        <caption><bean:message bundle="greatevent" key="greatevent.header"/></caption>
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
            if (taskName.equals("MakeTask")) {
                if (operateType.equals("90") || operateType.equals("11")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <!-- 接单时限 -->
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <!-- 完成时限 -->
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
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkResReadyResult"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkResReadyResult"
                          id="${sheetPageName}linkResReadyResult"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入资源准备结果，最多输入125字'">${sheetLink.linkResReadyResult}</textarea
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkGreatSecurityProgram"/>*</td>
            <td colspan='3'>
                <eoms:attachment name="sheetLink" property="linkGreatSecurityProgram"
                                 scope="request" idField="${sheetPageName}linkGreatSecurityProgram" appCode="greatevent"
                                 alt="allowBlank:false"/>
            </td>
        </tr>
        <%
                }
            }
        %>
        <%
            if (taskName.equals("AuditTask")) {
                if (operateType.equals("91") || operateType.equals("55")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ApprovalTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <!-- 接单时限 -->
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <!-- 完成时限 -->
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
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkAuditAdvice" id="${sheetPageName}linkAuditAdvice"
                               initDicId="1011601" alt="allowBlank:false" styleClass="select-class"
                               onchange="ifAuditPass(this.value)" defaultValue="${sheetLink.linkAuditAdvice}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkAuditResult"
                          id="${sheetPageName}linkAuditResult" defaultValue="${sheetLink.linkAuditResult}"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>
        <%
            if (taskName.equals("ApprovalTask")) {
                if (operateType.equals("92") || operateType.equals("55")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PerformTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <!-- 接单时限 -->
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <!-- 完成时限 -->
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
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkPermisAdvice"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkPermisAdvice" id="${sheetPageName}linkPermisAdvice"
                               initDicId="1011604" alt="allowBlank:false" styleClass="select-class"
                               onchange="ifAuditPass1(this.value)" defaultValue="${sheetLink.linkPermisAdvice}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkPermisResult"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkPermisResult"
                          id="${sheetPageName}linkPermisResult" defaultValue="${sheetLink.linkPermisResult}"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审批结果，最多输入125字'">${sheetLink.linkPermisResult}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>
        <%
            if (taskName.equals("PerformTask")) {
                if (operateType.equals("93") || operateType.equals("11")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditEndTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <!-- 接单时限 -->
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <!-- 完成时限 -->
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
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkSencePerformSummary"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkSencePerformSummary"
                          id="${sheetPageName}linkSencePerformSummary"
                          defaultValue="${sheetLink.linkSencePerformSummary}"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入现场实施总结，最多输入125字'">${sheetLink.linkSencePerformSummary}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkSencePerformReport"/>*</td>
            <td colspan='3'>
                <eoms:attachment name="sheetLink" property="linkSencePerformReport"
                                 scope="request" idField="${sheetPageName}linkSencePerformReport" appCode="greatevent"
                                 alt="allowBlank:false"/>

            </td>
        </tr>
        <%if (operateType.equals("93")) { %>
        <tr>
            <!-- 是否启动网络变更配置工单 -->
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkIfStartChangeProcess"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIfStartChangeProcess"
                               id="${sheetPageName}linkIfStartChangeProcess"
                               initDicId="1011602" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkIfStartChangeProcess}"
                               onchange="popOtherFlow(this.value);"/>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startCircuit"
                             onclick="openwin('101160301')">电路调度工单</html:button>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startSoftChange"
                             onclick="openwin('101160302')">软件变更工单</html:button>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetChange"
                             onclick="openwin('101160304')">网络综合调整工单</html:button>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetData"
                             onclick="openwin('101160303')">网络数据管理工单</html:button>
            </td>
        </tr>
        <%
                    }
                }
            }
        %>
        <%
            if (taskName.equals("AuditEndTask")) {
                if (operateType.equals("94") || operateType.equals("55")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AssessmentTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <!-- 接单时限 -->
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <!-- 完成时限 -->
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
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkAuditAdvice" id="${sheetPageName}linkAuditAdvice"
                               initDicId="1011601" alt="allowBlank:false" styleClass="select-class"
                               onchange="ifAuditPass2(this.value);" defaultValue="${sheetLink.linkAuditAdvice}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkAuditResult"
                          id="${sheetPageName}linkAuditResult"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>
        <%
            if (taskName.equals("AssessmentTask")) {
                if (operateType.equals("95") || operateType.equals("11")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditReportTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <!-- 接单时限 -->
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <!-- 完成时限 -->
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
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkSenceSecuritySummary"/>*</td>
            <td colspan='3'>
                <eoms:attachment name="sheetLink" property="linkSenceSecuritySummary"
                                 scope="request" idField="${sheetPageName}linkSenceSecuritySummary" appCode="greatevent"
                                 alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkSenceSecurityReport"/>*</td>
            <td colspan='3'>
                <eoms:attachment name="sheetLink" property="linkSenceSecurityReport"
                                 scope="request" idField="${sheetPageName}linkSenceSecurityReport" appCode="greatevent"
                                 alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkAssessReport"/>*</td>
            <td colspan='3'>
                <eoms:attachment name="sheetLink" property="linkAssessReport"
                                 scope="request" idField="${sheetPageName}linkAssessReport" appCode="greatevent"
                                 alt="allowBlank:false"/>
            </td>
        </tr>
        <%if (operateType.equals("95")) { %>
        <tr>
            <!-- 是否启动网络变更配置工单 -->
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkIfStartChangeProcess"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIfStartChangeProcess"
                               id="${sheetPageName}linkIfStartChangeProcess"
                               initDicId="1011602" alt="allowBlank:true" styleClass="select-class"
                               defaultValue="${sheetLink.linkIfStartChangeProcess}"
                               onchange="popOtherFlow1(this.value);"/>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startCircuit"
                             onclick="openwin1('101160301')">电路调度工单</html:button>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startSoftChange"
                             onclick="openwin1('101160302')">软件变更工单</html:button>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetChange"
                             onclick="openwin1('101160304')">网络综合调整工单</html:button>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetData"
                             onclick="openwin1('101160303')">网络数据管理工单</html:button>
            </td>
        </tr>
        <%
                    }
                }
            }
        %>
        <%
            if (taskName.equals("AuditReportTask")) {
                if (operateType.equals("96") || operateType.equals("55")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ModifyTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <!-- 接单时限 -->
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <!-- 完成时限 -->
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
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkAuditAdvice" id="${sheetPageName}linkAuditAdvice"
                               initDicId="1011601" alt="allowBlank:false" styleClass="select-class"
                               onchange="ifAuditPass3(this.value);" defaultValue="${sheetLink.linkAuditAdvice}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkAuditResult"
                          id="${sheetPageName}linkAuditResult"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
            </td>
        </tr>
        <tbody id="ifchangeplan">
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkIfModifyPlans"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIfModifyPlans" id="${sheetPageName}linkIfModifyPlans"
                               initDicId="1011605" alt="allowBlank:false" styleClass="select-class"
                               onchange="ifAuditPass4(this.value);" defaultValue="${sheetLink.linkIfModifyPlans}"/>
            </td>
        </tr>
        </tbody>
        <%
                }
            }
        %>
        <%
            if (taskName.equals("ModifyTask")) {
                if (operateType.equals("97") || operateType.equals("11")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <!-- 接单时限 -->
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <!-- 完成时限 -->
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
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkAmendPlanHelp"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkAmendPlanHelp"
                          id="${sheetPageName}linkAmendPlanHelp"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入修订预案说明，最多输入125字'">${sheetLink.linkAmendPlanHelp}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkEmergencyPlan"/>*</td>
            <td colspan='3'>
                <eoms:attachment name="sheetLink" property="linkEmergencyPlan"
                                 scope="request" idField="${sheetPageName}linkEmergencyPlan" appCode="greatevent"
                                 alt="allowBlank:false"/>
            </td>
        </tr>

        <%
                }
            }
        %>
        <%if (taskName.equals("HoldTask")) {%>
        <%if (operateType.equals("18")) { %>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="over"/>
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
                <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:255,vtext:'请最多输入125字'"
                          id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
            </td>
        </tr>
        <%} else if (operateType.equals("102")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReplyTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'"
                              alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>
        <% if (taskName.equals("cc")) {%>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
                <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:2000,vtext:'请填入信息，最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%} %>
        <%if (operateType.equals("61")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
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
