<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.softchange.model.SoftChangeLink softChangeLink = (com.boco.eoms.sheet.softchange.model.SoftChangeLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(softChangeLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(softChangeLink.getOperateType()));
%>

<%@ include file="/WEB-INF/pages/wfworksheet/softchange/baseinputlinkhtmlnew.jsp" %>
<html:form action="/softchange.do" styleId="theform">
    <br/>


    <input type="hidden" name="${sheetPageName}beanId" value="iSoftChangeMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.softchange.model.SoftChangeMain"/> <!--main表Model对象类名-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.softchange.model.SoftChangeLink"/> <!--link表Model对象类名-->
    <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->

    <table class="formTable">
        <caption><bean:message bundle="softchange" key="softchange.header"/></caption>
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
            if (taskName.equals("ProjectCreateTask")) {
                if (operateType.equals("110")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="110"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="linkForm.acceptLimit"/>*</td>
            <td colspan="3" class="content">
                <input type="text" class="text" name="${sheetPageName}nodeAcceptLimit" readonly="readonly"
                       id="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkCompleteLimitTime"/>*</td>
            <td class="content"><input class="text" onclick="popUpCalendar(this, this)" type="text"
                                       name="${sheetPageName}linkCompleteLimitTime"
                                       readonly="readonly" alt="allowBlank:false"
                                       value="${eoms:date2String(sheetLink.linkCompleteLimitTime)}"
                                       id="${sheetPageName}linkCompleteLimitTime"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkDesignKey"/>*</td>
            <td class="content"><input type="text" class="text" name="${sheetPageName}linkDesignKey"
                                       id="${sheetPageName}linkDesignKey" value="${sheetLink.linkDesignKey}"
                                       alt="allowBlank:false"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkDesignComment"/></td>
            <td colspan="3" class="content">
                <textarea class="textarea max" name="${sheetPageName}linkDesignComment"
                          id="${sheetPageName}linkDesignComment"
                          alt="width:500,allowBlank:true">${sheetLink.linkDesignComment}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkInvolvedProvince"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkInvolvedProvince"
                          id="${sheetPageName}linkInvolvedProvince"
                          alt="width:500,allowBlank:false">${sheetLink.linkInvolvedProvince}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkInvolvedCity"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkInvolvedCity"
                          id="${sheetPageName}linkInvolvedCity"
                          alt="width:500,allowBlank:false">${sheetLink.linkInvolvedCity}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkRiskEstimate"/>*</td>
            <td colspan="3" class="content">
                <textarea class="textarea max" name="${sheetPageName}linkRiskEstimate"
                          id="${sheetPageName}linkRiskEstimate"
                          alt="width:500,allowBlank:false">${sheetLink.linkRiskEstimate}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkEffectAnalyse"/>*</td>
            <td colspan="3" class="content">
                <textarea class="textarea max" name="${sheetPageName}linkEffectAnalyse"
                          id="${sheetPageName}linkEffectAnalyse"
                          alt="width:500,allowBlank:false">${sheetLink.linkEffectAnalyse}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkProjectAccessories"/></td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="nodeAccessories"
                                 scope="request" idField="${sheetPageName}nodeAccessories" appCode="softchange"/>

            </td>
        </tr>

        <%
                }
            }
        %>


        <% if (taskName.equals("AuditTask")) {
            if (operateType.equals("111")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111"/>


        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkIsCheck"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIsCheck" id="${sheetPageName}linkIsCheck"
                               initDicId="1010907" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkIsCheck}" onchange="ifAuditPass(this.value);"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkCheckComment"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCheckComment"
                          id="${sheetPageName}linkCheckComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkCheckComment}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>
        <% if (taskName.equals("PermitTask")) {
            if (operateType.equals("112")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PlanTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="112"/>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkPermitResult"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult"
                               initDicId="1010908" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkPermitResult}" onchange="ifAuditPass1(this.value);"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkPermitIdea"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkPermitIdea" id="${sheetPageName}linkPermitIdea"
                          alt="width:500,allowBlank:false">${sheetLink.linkPermitIdea}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>
        <% if (taskName.equals("PlanTask")) {
            if (operateType.equals("113")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="113"/>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkManager"/>*</td>
            <td><input type="text" class="text" name="${sheetPageName}linkManager" id="${sheetPageName}linkManager"
                       value="${sheetLink.linkManager}" alt="allowBlank:false"/></td>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkContact"/>*</td>
            <td><input type="text" class="text" name="${sheetPageName}linkContact" id="${sheetPageName}linkContact"
                       value="${sheetLink.linkContact}" alt="allowBlank:false"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkPlanStartTime"/>*</td>
            <td>
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}linkPlanStartTime" readonly="readonly" alt="allowBlank:true"
                       value="${eoms:date2String(sheetLink.linkPlanStartTime)}" id="${sheetPageName}linkPlanStartTime"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkPlanEndTime"/></td>
            <td>
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}linkPlanEndTime" readonly="readonly" alt="allowBlank:true"
                       value="${eoms:date2String(sheetLink.linkPlanEndTime)}" id="${sheetPageName}linkPlanEndTime"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkCellInfo"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCellInfo" id="${sheetPageName}linkCellInfo"
                          alt="width:500,allowBlank:false">${sheetLink.linkCellInfo}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkIsEffectBusiness"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIsEffectBusiness" id="${sheetPageName}linkIsEffectBusiness"
                               initDicId="10301" alt="allowBlank:true" defaultValue="${sheetLink.linkIsEffectBusiness}"
                               styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkEffectCondition"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkEffectCondition"
                          id="${sheetPageName}linkEffectCondition"
                          alt="width:500,allowBlank:true">${sheetLink.linkEffectCondition}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkNetManage"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkNetManage" id="${sheetPageName}linkNetManage"
                          alt="width:500,allowBlank:true ">${sheetLink.linkNetManage}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkBusinessDept"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkBusinessDept" id="${sheetPageName}linkBusinessDept"
                               initDicId="1010902" alt="allowBlank:true" defaultValue="${sheetLink.linkBusinessDept}"
                               styleClass="select-class"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkIsSendToFront"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIsSendToFront" id="${sheetPageName}linkIsSendToFront"
                               initDicId="10301" alt="allowBlank:true" defaultValue="${sheetLink.linkIsSendToFront}"
                               styleClass="select-class"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkExecuteAccessories"/></td>
            <td colspan="3">

                <eoms:attachment name="sheetLink" property="nodeAccessories"
                                 scope="request" idField="${sheetPageName}nodeAccessories" appCode="softchange"/>
            </td>
        </tr>
        <%
                }
            }
        %>

        <% if (taskName.equals("ExecuteTask")) {
            if (operateType.equals("114")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="114"/>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkCutResult"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkCutResult" id="${sheetPageName}linkCutResult"
                               initDicId="1010903" alt="allowBlank:false" defaultValue="${sheetLink.linkCutResult}"
                               styleClass="select-class"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkIsPlan"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIsPlan" id="${sheetPageName}linkIsPlan"
                               initDicId="10301" alt="allowBlank:false" defaultValue="${sheetLink.linkIsPlan}"
                               styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkCutComment"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCutComment" id="${sheetPageName}linkCutComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkCutComment}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkBusinessComment"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkBusinessComment"
                          id="${sheetPageName}linkBusinessComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkBusinessComment}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkTestResult"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult"
                               initDicId="1010904" alt="allowBlank:false" defaultValue="${sheetLink.linkTestResult}"
                               styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkAlertRecord"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkAlertRecord"
                          id="${sheetPageName}linkAlertRecord"
                          alt="width:500,allowBlank:false">${sheetLink.linkAlertRecord}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkUnnormalComment"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkUnnormalComment"
                          id="${sheetPageName}linkUnnormalComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkUnnormalComment}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkTestReport"/>*</td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="nodeAccessories"
                                 scope="request" alt="allowBlank:false" idField="${sheetPageName}nodeAccessories"
                                 appCode="softchange"/>
            </td>
        </tr>
        <%
                }
            }
        %>
        <% if (taskName.equals("ValidateTask")) {
            if (operateType.equals("115")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="115"/>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkCutAnalyse"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCutAnalyse" id="${sheetPageName}linkCutAnalyse"
                          alt="width:500,allowBlank:true">${sheetLink.linkCutAnalyse}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkIsUpdateWork"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIsUpdateWork" id="${sheetPageName}linkIsUpdateWork"
                               initDicId="1010909" alt="allowBlank:false" defaultValue="${sheetLink.linkIsUpdateWork}"
                               styleClass="select-class"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkIsNeedProject"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIsNeedProject" id="${sheetPageName}linkIsNeedProject"
                               initDicId="10301" alt="allowBlank:false" defaultValue="${sheetLink.linkIsNeedProject}"
                               styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkWorkUpdateAdvice"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkWorkUpdateAdvice"
                          id="${sheetPageName}linkWorkUpdateAdvice"
                          alt="width:500,allowBlank:true">${sheetLink.linkWorkUpdateAdvice}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkSoftVersionUpdate"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkSoftVersionUpdate"
                          id="${sheetPageName}linkSoftVersionUpdate"
                          alt="width:500,allowBlank:false">${sheetLink.linkSoftVersionUpdate}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkNextPlan"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkNextPlan" id="${sheetPageName}linkNextPlan"
                          alt="width:500,allowBlank:true">${sheetLink.linkNextPlan}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkProjectComment"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkProjectComment"
                          id="${sheetPageName}linkProjectComment"
                          alt="width:500,allowBlank:false">${sheetLink.linkProjectComment}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>


        <%if (taskName.equals("HoldTask")) {%>
        <%if (operateType.equals("7")) { %>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7"/>
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
                <textarea name="${sheetPageName}endResult" alt="allowBlank:false" id="${sheetPageName}endResult"
                          class="textarea max">${sheetMain.endResult}</textarea>
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
                          alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%} %>
        <%if (taskName.equals("ProjectCreateTask") || taskName.equals("AuditTask") || taskName.equals("PermitTask") || taskName.equals("PlanTask") || taskName.equals("ExecuteTask") || taskName.equals("ValidateTask")) {%>

        <%if (operateType.equals("61")) { %>
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

        <%
                }
            }
        %>
        <%if (operateType.equals("4")) { %>
        <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
               value="${fOperateroleid}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
               value="${ftaskOwner}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
               value="${fOperateroleidType}"/>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>

        <%if (taskName.equals("ProjectCreateTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectHumTask"/>
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

<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/softchange/softchange.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
        form.submit();
    }

    function removeTemplate() {
        if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
            var thisform = document.forms[0];
            thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
            thisform.submit();
        }
    }

    Ext.onReady(function () {
        //viewer
        var areaViewer = new Ext.JsonView("areaview",
            '<div class="viewlistitem-{nodeType}">{name}</div>',
            {
                emptyText: '<div>没有选择项目</div>'
            }
        );
        var data = "[{id:'${sheetLink.linkInvolvedCity}',name:'<eoms:id2nameDB id='${sheetLink.linkInvolvedCity}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
        areaViewer.jsonData = eoms.JSONDecode('${sheetLink.linkInvolvedCity}' == '' ? '[]' : data);
        areaViewer.refresh();

        //area tree
        var deptTreeAction = '${app}/xtree.do?method=areaTree';
        deptetree = new xbox({

            btnId: '${sheetPageName}showDept',
            dlgId: 'dlg3',

            treeDataUrl: deptTreeAction,
            treeRootId: '-1',
            treeRootText: '地市',
            treeChkMode: 'single',
            treeChkType: 'area',
            showChkFldId: '${sheetPageName}showDept',
            saveChkFldId: '${sheetPageName}linkInvolvedCity',
            viewer: areaViewer
        });
    });
</script>

<%@ include file="/common/footer_eoms.jsp" %>