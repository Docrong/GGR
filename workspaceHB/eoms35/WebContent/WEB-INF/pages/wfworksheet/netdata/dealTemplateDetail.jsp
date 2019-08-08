<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.netdata.model.NetDataLink netdataLink = (com.boco.eoms.sheet.netdata.model.NetDataLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(netdataLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(netdataLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/netdata/netdata.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<html:form action="/netdata.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="inetdataMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.netdata.model.netdataMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.netdata.model.netdataLink"/>
    <table class="formTable">
        <caption><bean:message bundle="netdata" key="netdata.header"/></caption>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="input.templateName"/>
            </td>
            <td colspan="3">
                <input type="text" name="templateName" class="text" id="templateName"
                       value="${sheetLink.templateName}"/>
            </td>
        </tr>
        <!--流程中的字段域 -->
        <!-- 方案制定  -->
        <%if (taskName.equals("ProjectCreateTask") && operateType.equals("110")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkCompleteLimitTime"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkCompleteLimitTime" readonly="readonly"
                       id="${sheetPageName}linkCompleteLimitTime"
                       value="${eoms:date2String(sheetLink.linkCompleteLimitTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkDesignKey"/></td>
            <td><input type="text" class="text" name="${sheetPageName}linkDesignKey" id="${sheetPageName}linkDesignKey"
                       value="${sheetLink.linkDesignKey}" alt="allowBlank:false"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkDesignComment"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkDesignComment"
                          id="${sheetPageName}linkDesignComment"
                          alt="width:500,allowBlank:true">${sheetLink.linkDesignComment}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkInvolvedProvince"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkInvolvedProvince"
                          id="${sheetPageName}linkInvolvedProvince"
                          alt="width:500,allowBlank:false">${sheetLink.linkInvolvedProvince}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkInvolvedCity"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkInvolvedCity"
                          id="${sheetPageName}linkInvolvedCity"
                          alt="width:500,allowBlank:false">${sheetLink.linkInvolvedCity}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkRiskEstimate"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkRiskEstimate"
                          id="${sheetPageName}linkRiskEstimate"
                          alt="width:500,allowBlank:false">${sheetLink.linkRiskEstimate}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkEffectAnalyse"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkEffectAnalyse"
                          id="${sheetPageName}linkEffectAnalyse"
                          alt="width:500,allowBlank:false">${sheetLink.linkEffectAnalyse}</textarea>
            </td>
        </tr>
        <!-- 方案审核 -->
        <%} else if (taskName.equals("AuditTask") && operateType.equals("111")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask"/>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkIsCheck"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult"
                               initDicId="1011608" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkPermitResult}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkCheckComment"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCheckComment"
                          id="${sheetPageName}linkCheckComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkCheckComment}</textarea>
            </td>
        </tr>
        <!-- 方案审批 -->
        <%} else if (taskName.equals("PermitTask") && operateType.equals("112")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PlanTask"/>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkPermitResult"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult"
                               initDicId="1011608" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkPermitResult}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkPermitIdea"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkPermitIdea" id="${sheetPageName}linkPermitIdea"
                          alt="width:500,allowBlank:false">${sheetLink.linkPermitIdea}</textarea>
            </td>
        </tr>
        <!-- 排程 -->
        <%} else if (taskName.equals("PlanTask") && operateType.equals("113")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask"/>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkManager"/></td>
            <td><input type="text" class="text" name="${sheetPageName}linkManager" id="${sheetPageName}linkManager"
                       value="${sheetLink.linkManager}" alt="allowBlank:false"/></td>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkContact"/></td>
            <td><input type="text" class="text" name="${sheetPageName}linkContact" id="${sheetPageName}linkContact"
                       value="${sheetLink.linkContact }" alt="allowBlank:false"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkPlanStartTime"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkPlanStartTime" readonly="readonly"
                       id="${sheetPageName}linkPlanStartTime" value="${eoms:date2String(sheetLink.linkPlanStartTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkPlanEndTime"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkPlanEndTime" readonly="readonly"
                       id="${sheetPageName}linkPlanEndTime" value="${eoms:date2String(sheetLink.linkPlanEndTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkCellInfo"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCellInfo" id="${sheetPageName}linkCellInfo"
                          alt="width:500,allowBlank:false">${sheetLink.linkCellInfo }</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkIsEffectBusiness"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIsEffectBusiness" id="${sheetPageName}linkIsEffectBusiness"
                               initDicId="1011608" alt="allowBlank:true" styleClass="select-class"
                               defaultValue="${sheetLink.linkIsEffectBusiness}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkEffectCondition"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkEffectCondition"
                          id="${sheetPageName}linkEffectCondition"
                          alt="width:500,allowBlank:true">${sheetLink.linkEffectCondition}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkNetManage"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkNetManage" id="${sheetPageName}linkNetManage"
                          alt="width:500,allowBlank:true">${sheetLink.linkNetManage}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkBusinessDept"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkBusinessDept" id="${sheetPageName}linkBusinessDept"
                               initDicId="1011608" alt="allowBlank:true" styleClass="select-class"
                               defaultValue="${sheetLink.linkBusinessDept}"/>
            </td>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkIsSendToFront"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIsSendToFront" id="${sheetPageName}linkIsSendToFront"
                               initDicId="1011608" alt="allowBlank:true" styleClass="select-class"
                               defaultValue="${sheetLink.linkIsSendToFront}"/>
            </td>
        </tr>
        <!-- 方案实施 -->
        <%} else if (taskName.equals("ExecuteTask") && operateType.equals("114")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask"/>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkCutResult"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkCutResult" id="${sheetPageName}linkCutResult"
                               initDicId="1011608" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkCutResult }"/>
            </td>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkIsPlan"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIsPlan" id="${sheetPageName}linkIsPlan"
                               initDicId="1011608" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkIsPlan }"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkTestResult"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult"
                               initDicId="1011608" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkTestResult }"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkCutComment"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCutComment" id="${sheetPageName}linkCutComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkCutComment }</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkBusinessComment"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkBusinessComment"
                          id="${sheetPageName}linkBusinessComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkBusinessComment }</textarea>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkAlertRecord"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkAlertRecord"
                          id="${sheetPageName}linkAlertRecord"
                          alt="width:500,allowBlank:false">${sheetLink.linkAlertRecord}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkUnnormalComment"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkUnnormalComment"
                          id="${sheetPageName}linkUnnormalComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkUnnormalComment}</textarea>
            </td>
        </tr>
        <!-- 实施结果验证 -->
        <%} else if (taskName.equals("ValidateTask") && operateType.equals("115")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkIsUpdateWork"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIsUpdateWork" id="${sheetPageName}linkIsUpdateWork"
                               initDicId="1011608" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkIsUpdateWork}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkCutAnalyse"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCutAnalyse" id="${sheetPageName}linkCutAnalyse"
                          alt="width:500,allowBlank:true">${sheetLink.linkCutAnalyse}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkWorkUpdateAdvice"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkWorkUpdateAdvice"
                          id="${sheetPageName}linkWorkUpdateAdvice"
                          alt="width:500,allowBlank:true">${sheetLink.linkWorkUpdateAdvice}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkSoftVersionUpdate"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkSoftVersionUpdate"
                          id="${sheetPageName}linkSoftVersionUpdate"
                          alt="width:500,allowBlank:false">${sheetLink.linkSoftVersionUpdate}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkNextPlan"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkNextPlan" id="${sheetPageName}linkNextPlan"
                          alt="width:500,allowBlank:true">${sheetLink.linkNextPlan}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netdata" key="netdata.linkProjectComment"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkProjectComment"
                          id="${sheetPageName}linkProjectComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkProjectComment}</textarea>
            </td>
        </tr>
        <%} %>
        <!--流程中的字段域 结束-->


        <!-- 公共功能，抄送和确认受理 -->
        <!-- 抄送 -->
        <%if (taskName.equals("cc")) {%>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>
            </td>
            <td colspan="3">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
                <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%} %>

        <!-- 确认受理 -->
        <%if (operateType.equals("61")) {%>
        <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
               value="${fOperateroleid}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
               value="${ftaskOwner}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
               value="${fOperateroleidType}"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>
            </td>
            <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%} %>

        <!-- 驳回到上一级 -->
        <%if (operateType.equals("4")) { %>
        <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
               value="${fOperateroleid}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
               value="${ftaskOwner}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
               value="${fOperateroleidType}"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
        <%if (taskName.equals("ProjectCreateTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DraftTask"/>
        <%} %>
        <%if (taskName.equals("PermitTask")) { %>
        <tr>
            <td class="label"></td>
            <td>
                <input type="radio" name="${sheetPageName}phaseId" value="ProjectCreateTask"
                       checked>${eoms:a2u('重新制定方案')}
                <input type="radio" name="${sheetPageName}phaseId" value="DraftTask">${eoms:a2u('驳回申请')}
            </td>
        </tr>
        <%} %>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>

        <%} %>
    </table>
    <!-- 公共功能，抄送和确认受理 结束 -->

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
