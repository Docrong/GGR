<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
    switcher = new detailSwitcher();
    switcher.init({
        container: 'showInvokeRelationShipList',
        handleEl: 'div.history-item-title'
    });

    function openSheet(flowName, sheetKey, taskName) {

        var url = '';
        if (taskName == 'irontowner') {//故障工单调用铁塔
            url = '../commonfault/commonfault.do?method=showListTowner&sheetKey=' + sheetKey;
        } else if (flowName == 'BusinessPilotProcess') {//新业务试点工单
            url = '../business/businesspilot.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'NetDataMainProcess') {//网络数据管理工单
            url = '../netdata/netdata.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'SoftChangeMainProcess') {//软件变更流程
            url = '../softchange/softchange.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'NetChangeMainProcess') {//网络综合调整工单
            url = '../netchange/netchange.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'CircuitDispatchMainFlowProcess') {//电路调度工单
            url = '../circuitdispatch/circuitdispatch.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'BusinessPlanProcess') {//新业务设计工单
            url = '../businessplan/businessplan.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'BusinessOperationProcess') {//新业务正式实施工单
            url = '../businessoperation/businessoperation.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'UrgentFaultMainFlowProcess') {//紧急故障工单
            url = '../urgentfault/urgentfault.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'ComplaintProcess') {//投诉处理工单
            url = '../complaint/complaint.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'CommonFaultMainFlowProcess') {//故障处理工单
            url = '../commonfault/commonfault.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'BusinessChangeProcess') { //业务变更工单
            url = '../businesschange/businesschange.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'BusinessBackoutProcess') {//业务拆除工单
            url = '../businessbackout/businessbackout.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'BusinessDredgeMainFlowProcess') {//业务开通工单
            url = '../businessdredge/businessdredge.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'ResourceAffirmProcess') {//资源确认工单
            url = '../resourceaffirm/resourceaffirm.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'ITRequirementProcess') {//IT需求申请工单
            url = '../itrequirement/itrequirement.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'ITSoftChangeProcess') {//IT需求开发工单
            url = '../itsoftchange/itsoftchange.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'SecurityDealProcess') {//安全问题处理工单
            url = '../securitydeal/securitydeal.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'SecurityDealProcess') {//流程变更工单
            url = '../processchange/processchange.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'GreatEventProcess') {//重大事件保障工单
            url = '../greatevent/greatevent.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'AgentMaintenanceMainFlowProcess') {//代维流程工单
            url = '../agentmaintenance/agentmaintenance.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'CommonTaskMainFlowProcess') {//通用任务工单
            url = '../commontask/commontask.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'NetOptimizeProcess') {//网络优化
            url = '../netOptimize/netOptimize.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        } else if (flowName == 'CommonfaultCorrigendum') {//故障勘误流程
            url = '../commonfaultcorrigendum/commonfaultcorrigendum.do?method=showMainDetailPage&sheetKey=' + sheetKey;
        }

        window.open(url);

    }
</script>

<div id="showInvokeRelationShipList" class="panel">
    <div class="history-item" width="100%"><!-- add space to hack ie-->&nbsp;
        <div class="history-item-content">
            <br>
            <br>
            <bean:message bundle="sheet" key="sheet.sheetRelation.mySend"/>
            <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="label" width="15%"><bean:message bundle="sheet" key="sheet.sheetRelation.flowName"/></td>
                    <td class="label" width="25%"><bean:message bundle="sheet" key="sheet.sheetRelation.sheetId"/></td>
                    <td class="label" width="35%"><bean:message bundle="sheet"
                                                                key="sheet.sheetRelation.sheetTitle"/></td>
                    <td class="label" width="25%"><bean:message bundle="sheet" key="sheet.sheetRelation.sendTime"/></td>
                    <td class="label" width="25%"><bean:message bundle="sheet"
                                                                key="sheet.sheetRelation.invokeNode"/></td>
                </tr>

                <logic:present name="FROMRELATIONLIST" scope="request">
                    <logic:iterate id="tawSheetRelation" name="FROMRELATIONLIST"
                                   type="com.boco.eoms.sheet.tool.relation.webapp.form.TawSheetRelationForm">
                        <tr>
                            <td class="label">
                                <c:choose>
                                <c:when test="${tawSheetRelation.taskName == 'irontowner'}">
                                    铁塔工单
                                </c:when>
                                <c:otherwise>
                                <bean:write name="tawSheetRelation" property="currentFlowCnName" scope="page"/></td>
                            </c:otherwise>
                            </c:choose>

                            <td class="label">
                                <a onclick=openSheet('<bean:write name="tawSheetRelation" property="currentFlowName"
                                                                  scope="page"/>','<bean:write name="tawSheetRelation"
                                                                                               property="currentId"
                                                                                               scope="page"/>','
                                <bean:write name="tawSheetRelation" property="taskName" scope="page"/>')>
                                <bean:write name="tawSheetRelation" property="currentSheetId" scope="page"/>
                                </a>
                            </td>
                            <td class="label"><bean:write name="tawSheetRelation" property="currentTitle"
                                                          scope="page"/></td>
                            <td class="label"><bean:write name="tawSheetRelation" property="invokeTime"
                                                          scope="page"/></td>
                            <td class="label"><bean:write name="tawSheetRelation" property="taskCnName"
                                                          scope="page"/></td>
                        </tr>
                    </logic:iterate>
                </logic:present>
            </table>
            <br>
            <br>
            <bean:message bundle="sheet" key="sheet.sheetRelation.sendToMe"/>
            <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="label" width="15%"><bean:message bundle="sheet" key="sheet.sheetRelation.flowName"/></td>
                    <td class="label" width="25%"><bean:message bundle="sheet" key="sheet.sheetRelation.sheetId"/></td>
                    <td class="label" width="35%"><bean:message bundle="sheet"
                                                                key="sheet.sheetRelation.sheetTitle"/></td>
                    <td class="label" width="25%"><bean:message bundle="sheet" key="sheet.sheetRelation.sendTime"/></td>
                    <td class="label" width="25%"><bean:message bundle="sheet"
                                                                key="sheet.sheetRelation.invokeNode"/></td>

                </tr>

                <logic:present name="TORELATIONLIST" scope="request">
                    <logic:iterate id="tawSheetRelation" name="TORELATIONLIST"
                                   type="com.boco.eoms.sheet.tool.relation.webapp.form.TawSheetRelationForm">
                        <tr>
                            <td class="label">
                                <bean:write name="tawSheetRelation" property="parentFlowCnName" scope="page"/></td>
                            <td class="label">
                                <a onclick=openSheet('<bean:write name="tawSheetRelation" property="parentFlowName"
                                                                  scope="page"/>','<bean:write name="tawSheetRelation"
                                                                                               property="parentId"
                                                                                               scope="page"/>')>
                                    <bean:write name="tawSheetRelation" property="parentSheetId" scope="page"/>
                                </a>
                            </td>
                            <td class="label"><bean:write name="tawSheetRelation" property="parentTitle"
                                                          scope="page"/></td>
                            <td class="label"><bean:write name="tawSheetRelation" property="invokeTime"
                                                          scope="page"/></td>
                            <td class="label"><bean:write name="tawSheetRelation" property="taskCnName"
                                                          scope="page"/></td>
                        </tr>
                    </logic:iterate>
                </logic:present>
            </table>

        </div>
    </div>
</div>
