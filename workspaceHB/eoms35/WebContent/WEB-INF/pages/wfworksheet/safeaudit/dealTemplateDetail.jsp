<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    com.boco.eoms.sheet.safeaudit.model.SafeAuditLink safeauditLink = (com.boco.eoms.sheet.safeaudit.model.SafeAuditLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(safeauditLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(safeauditLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/safeaudit/safeaudit.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/safeaudit/baseinputlinkhtmlnew.jsp" %>
<html:form action="/safeaudit.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="iSafeAuditMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.safeaudit.model.SafeAuditMain"/> <!--main\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.safeaudit.model.SafeAuditLink"/> <!--link\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <table class="formTable">
        <%if (!operateType.equals("61")) {%>
        <caption><bean:message bundle="safeaudit" key="safeaudit.header"/></caption>
        <%} %>
        <input type="hidden" name="${sheetPageName}beanId" value="iSafeAuditMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.safeaudit.model.SafeAuditMain"/>
        <input type="hidden" name="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.safeaudit.model.SafeAuditLink"/>
        <c:if test="${taskName != 'HoldTask' }">
            <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
        </c:if>
        <%
            if (taskName.equals("EvaluateMaterialHumTask")) {
                if (operateType.equals("90") || operateType.equals("11")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="EditReportHumTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

            </td>
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
            <td class="label"><bean:message bundle="safeaudit" key="safeaudit.linkPresentDescribe"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkPresentDescribe"
                          value="${sheetLink.linkPresentDescribe}" id="${sheetPageName}linkPresentDescribe"
                          alt="width:500,allowBlank:false,maxLength:500,vtext:'请填入安全现状描述，最多输入250字'">${sheetLink.linkPresentDescribe}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="safeaudit" key="safeaudit.linkPresentReport"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkPresentReport"
                          value="${sheetLink.linkPresentReport}" id="${sheetPageName}linkPresentReport"
                          alt="width:500,allowBlank:false,maxLength:500,vtext:'请填入安全现状报告，最多输入250字'">${sheetLink.linkPresentReport}</textarea>
            </td>
        </tr>

        <%
                }
            }
        %>
        <%
            if (taskName.equals("EditReportHumTask")) {
                if (operateType.equals("91") || operateType.equals("11")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ApprovingHumTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

            </td>
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
            <td class="label"><bean:message bundle="safeaudit" key="safeaudit.linkAuditReport"/>*</td>
            <td colspan='3'>
                <eoms:attachment name="sheetLink" property="linkAuditReport"
                                 scope="request" idField="${sheetPageName}linkAuditReport" appCode="safeaudit"
                                 alt="allowBlank:false"/>
            </td>
        </tr>


        <%
                }
            }
        %>
        <%
            if (taskName.equals("ApprovingHumTask")) {
                if (operateType.equals("93") || operateType.equals("55")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

            </td>
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
            <td class="label"><bean:message bundle="safeaudit" key="safeaudit.linkCheckIdeas"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkCheckIdeas" id="${sheetPageName}linkCheckIdeas"
                               initDicId="1011301" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkCheckIdeas}"
                               onchange="ifAuditPass(this.value,'AuditTask')"/>
            </td>

        </tr>
        <tr>
            <td class="label"><bean:message bundle="safeaudit" key="safeaudit.linkCheckResult"/>*</td>
            <td colspan='3'>
                <textarea class="textarea max" name="${sheetPageName}linkCheckResult"
                          id="${sheetPageName}linkCheckResult"
                          alt="width:500,allowBlank:false">${sheetLink.linkCheckResult}</textarea>
            </td>
        </tr>

        <%
                }
            }
        %>
        <%if (taskName.equals("HoldHumTask")) {%>
        <%if (operateType.equals("18") || operateType.equals("11")) { %>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied"
                               defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                               initDicId="10303" styleClass="select"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="safeaudit" key="safeaudit.LinkIfStartSecurityDeal"/></td>
            <td colspan="3">
                    <eoms:comboBox name="${sheetPageName}LinkIfStartSecurityDeal"
                                   id="${sheetPageName}LinkIfStartSecurityDeal"
                                   initDicId="1011303" alt="allowBlank:true" styleClass="select-class"
                                   defaultValue="${baseLink.LinkIfStartSecurityDeal}"
                                   onchange="popOtherFlow1(this.value);"/>
                <html:button styleClass="btn" style="display:none" property="method.save" styleId="startSecurityDeal"
                             onclick="openwin1('101160301')">安全问题处理工单</html:button>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
            <td colspan="3">
                <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:1000,vtext:'请填入信息，最多输入1000字'"
                          id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
            </td>
        </tr>

        <%
                }
            }
        %>
        <!--流程中的字段域 结束-->
        <!-- 公共功能，抄送和确认受理 -->
        <!-- 驳回到上一级 -->
        <%if (operateType.equals("4")) { %>
        <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
               value="${fOperateroleid}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
               value="${ftaskOwner}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
               value="${fOperateroleidType}"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
        <c:choose>
            <c:when test="${empty fPreTaskName}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="ByRejectHumTask"/>
            </c:when>
            <c:when test="${fPreTaskName == 'DraftTask'}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="ByRejectHumTask"/>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="${fPreTaskName}"/>
            </c:otherwise>
        </c:choose>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="link.linkRejectCause"/>*
            </td>
            <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>

        <%} %>
        <!-- 抄送-->
        <% if (taskName.equals("cc")) {%>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
                <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
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
