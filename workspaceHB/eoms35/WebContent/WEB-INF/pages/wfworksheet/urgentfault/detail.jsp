<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain" %>
<%@page
        import="com.boco.eoms.sheet.urgentfault.task.impl.UrgentFaultTask" %>
<%@page
        import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String userId = sessionform.getUserid();
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    UrgentFaultTask task = new UrgentFaultTask();
    if (request.getAttribute("task") != null) {
        task = (UrgentFaultTask) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
    }

    String operateType1 = task.getOperateType();
    request.setAttribute("operateType1", operateType1);
    UrgentFaultMain basemain = (UrgentFaultMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();
    String mainSendContentType = StaticMethod.nullObject2String(basemain.getMainSendContentType());

    request.setAttribute("taskStatus", taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));

%>

<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript">
    Ext.onReady(function () {
        var tabConfig = {
            items: [{
                id: 'sheetinfo',
                text: '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.historyView"/>',
                url: 'urgentfault.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                url: 'urgentfault.do?method=showWorkFlow&linkServiceName=iUrgentFaultLinkManager&dictSheetName=dict-sheet-urgentfault&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                url: 'urgentfault.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.allSheetsView"/>',
                url: '../sheetRelation/sheetRelation.do?method=showInvokeRelationShipList&sheetKey=${sheetMain.id}',
                isIframe: true
            }]
        };
        var tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);
        <%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
        <%if(taskName.equals("cc")){ %>
        var url2 = "urgentfault.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "urgentfault.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "urgentfault.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {
            var url2 = "urgentfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else if (obj == 2) {
            var url2 = "urgentfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "urgentfault.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "urgentfault.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
            eoms.util.appendPage("sheet-deal-content", url2, true);

        }
    }
</script>

<!-- Sheet Tabs Start -->
<div id="sheet-detail-page"><!-- Sheet Detail Tab Start -->
    <div id="sheetinfo"><logic:present name="sheetMain"
                                       scope="request">
        <%@ include file="/WEB-INF/pages/wfworksheet/common/basedetailnew.jsp" %>
        <br/>
        <table class="formTable">
            <caption></caption>
            <tr>
                <td class="label"><bean:message bundle="urgentfault" key="operate.sendType"/></td>
                <td class="content" colspan='3'>
                    <eoms:dict key="dict-sheet-urgentfault" dictId="subOperateType"
                               itemId="${sheetMain.mainSendContentType}" beanId="id2nameXML"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainIfGreatFault"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainIfGreatFault}" beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainGreatFaultId"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainGreatFaultId" scope="request"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainFaultSheetId"/></td>
                <td class="content" colspan='3'><bean:write name="sheetMain"
                                                            property="mainFaultSheetId" scope="request"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainFaultGenerantTime"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainFaultGenerantTime" formatKey="date.formatDateTimeAll"
                                                bundle="sheet" scope="request"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainFaultGenerantPlace"/></td>
                <td class="content"><eoms:id2nameDB id="${sheetMain.toDeptId}"
                                                    beanId="tawSystemAreaDao"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainNetSortOne"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainNetSortTwo"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainNetSortThree"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainEquipmentFactory"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainEquipmentFactory}"
                        beanId="ItawSystemDictTypeDao"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainFaultDesc"/></td>
                <td class="content" colspan='3'><pre><bean:write
                        name="sheetMain" property="mainFaultDesc" scope="request"/></pre>
                </td>
            </tr>
            <% if (mainSendContentType.equals("92")) { %>
            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainFaultReason"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainFaultReason}" beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainFaultSubReason"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainFaultSubReason}" beanId="ItawSystemDictTypeDao"/></td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainUrgentLeve"/></td>
                <td class="content" colspan="3"><eoms:id2nameDB
                        id="${sheetMain.mainUrgentLeve}" beanId="ItawSystemDictTypeDao"/></td>

            </tr>
            <%} %>
            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainTrafficLoss"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainTrafficLoss" scope="request"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainImpactUserNum"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainImpactUserNum" scope="request"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainIfAffectOperation"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainIfAffectOperation}"
                        beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainAffectStartTime"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainAffectStartTime" formatKey="date.formatDateTimeAll"
                                                bundle="sheet" scope="request"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainAffectArea"/></td>
                <td class="content" colspan='3'><bean:write name="sheetMain"
                                                            property="mainAffectArea" scope="request"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainAffectOperationDesc"/></td>
                <td class="content" colspan='3'><pre><bean:write
                        name="sheetMain" property="mainAffectOperationDesc" scope="request"/></pre>
                </td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainAffectOperationLevel"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainAffectOperationLevel}"
                        beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainAffectOperationSort"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainAffectOperationSort" scope="request"/></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainIfReport"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainIfReport}" beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainTriggerUserComplaint"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainTriggerUserComplaint" scope="request"/></td>
            </tr>


            <!--shi zhong  -->

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainIfAchieveProjectStartup"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainIfAchieveProjectStartup}"
                        beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"></td>
                <td class="content"></td>
            </tr>

            <!--shi hou  -->


            <% if (mainSendContentType.equals("92")) { %>
            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainEquipIntegrator"/></td>
                <td class="content"><eoms:id2nameDB
                        id="${sheetMain.mainEquipIntegrator}" beanId="ItawSystemDictTypeDao"/></td>
                <td class="label"></td>
                <td class="content"></td>
            </tr>

            <tr>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainFaultAvoidTime"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainFaultAvoidTime" formatKey="date.formatDateTimeAll"
                                                bundle="sheet" scope="request"/></td>
                <td class="label"><bean:message bundle="urgentfault"
                                                key="urgentfault.mainAffectTimeLength"/></td>
                <td class="content"><bean:write name="sheetMain"
                                                property="mainAffectTimeLength" scope="request"/></td>
            </tr>
            <%} %>


        </table>

        <br/>

        <table class="formTable">
            <caption></caption>

            <tr>
                <td class="label"><bean:message bundle="sheet"
                                                key="mainForm.accessories"/></td>
                <td colspan='5'><eoms:attachment name="sheetMain"
                                                 property="sheetAccessories" scope="request"
                                                 idField="sheetAccessories" appCode="urgentfault" viewFlag="Y"/></td>
            </tr>
        </table>


    </logic:present></div>
    <!-- Sheet Detail Tab End --></div>
<!-- Sheet Tabs End -->

<%if (taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")) {%>

<!-- Sheet Deal Content Start -->
<c:url var="urlShowMiddleReportAffirmDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="913"/>
</c:url>

<c:url var="urlShowBackReportAffirmDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="923"/>
</c:url>

<c:url var="urlShowSolveFaultDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="93"/>
</c:url>

<c:url var="urlShowSolveFaultAffirmDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="933"/>
</c:url>

<c:url var="urlShowReportDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="94"/>

</c:url>

<c:url var="urlShowSumUpDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="95"/>
</c:url>

<c:url var="urlShowHoldDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="18"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>


<c:url var="urlBackDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="53"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
    <c:param name="backFlag" value="yes"/>
</c:url>

<c:url var="urlShowRejectDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="4"/>
</c:url>
<!--
<c:url var="urlShowTransferkPage" value="urgentfault.do">
    <c:param name="method" value="showTransferWorkItemPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
-->
<!-- 组内移交 -->
<c:url var="urlShowTransferPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="8"/>
</c:url>

<c:url var="urlShowPhaseReplyPage" value="urgentfault.do">
    <c:param name="method" value="showPhaseBackToUpPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="reply"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType1" value="${operateType1}"/>
</c:url>
<c:url var="urlShowInputSplit1" value="urgentfault.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask1"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowInputSplit2" value="urgentfault.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask2"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowInputSplit3" value="urgentfault.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask3"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowInputSplit4" value="urgentfault.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask4"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowInputSplit5" value="urgentfault.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask5"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowInputSplit6" value="urgentfault.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask6"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowAcceptDealPage" value="urgentfault.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="61"/>
</c:url>
<div class="sheet-deal">
    <div class='sheet-deal-header'>
        <table width="95%">
            <tr>
                <td width="50%">
                    <%if (!taskName.equals("cc") && !taskName.equals("reply") && !taskName.equals("advice")) {%>
                    <bean:message bundle="sheet" key="sheet.deal"/>:<%}%>
                    <%if (taskName.equals("MiddleReportAffirmHumTask")) { %>
                    <%--if(taskStatus.equals("8") && (task!=null && task.getTaskOwner().equals(userId))){--%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectDealPage}"><bean:message bundle="urgentfault"
                                                                               key="operate.reject"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowMiddleReportAffirmDealPage}"><bean:message bundle="urgentfault"
                                                                                           key="operate.MiddleReportAffirm"/></option>

                        <option value="${urlShowTransferPage}"><bean:message bundle="sheet"
                                                                             key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>

                        <%}%>
                    </select>
                    <%} else if (taskName.equals("BackReportAffirmHumTask")) {%>
                    <select id="dealSelector">

                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectDealPage}"><bean:message bundle="urgentfault"
                                                                               key="operate.reject"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowBackReportAffirmDealPage}"><bean:message bundle="urgentfault"
                                                                                         key="operate.BackReportAffirm"/></option>

                        <option value="${urlShowTransferPage}"><bean:message bundle="sheet"
                                                                             key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                    </select> <%} else if (taskName.equals("SolveFaultHumTask")) { %>
                    <select id="dealSelector">

                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectDealPage}"><bean:message bundle="urgentfault"
                                                                               key="operate.reject"/></option>

                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowSolveFaultDealPage}"><bean:message bundle="urgentfault"
                                                                                   key="operate.SolveFault"/></option>
                        <option value="${urlShowTransferPage}"><bean:message bundle="sheet"
                                                                             key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>


                        <!--  <option value="${urlShowInputSplit1}"><bean:message bundle="sheet" key="common.split"/></option>-->
                    </select> <%} else if (taskName.equals("SolveFaultAffirmHumTask")) { %>
                    <select id="dealSelector">

                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectDealPage}"><bean:message bundle="urgentfault"
                                                                               key="operate.reject"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowSolveFaultAffirmDealPage}"><bean:message bundle="urgentfault"
                                                                                         key="operate.SolveFaultAffirm"/></option>

                        <option value="${urlShowTransferPage}"><bean:message bundle="sheet"
                                                                             key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>


                        <!--  <option value="${urlShowInputSplit1}"><bean:message bundle="sheet" key="common.split"/></option>-->
                    </select> <%} else if (taskName.equals("ReportHumTask")) { %>
                    <select id="dealSelector">

                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectDealPage}"><bean:message bundle="urgentfault"
                                                                               key="operate.reject"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowReportDealPage}"><bean:message bundle="urgentfault"
                                                                               key="operate.Report"/></option>
                        <option value="${urlShowTransferPage}"><bean:message bundle="sheet"
                                                                             key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <!--  <option value="${urlShowInputSplit1}"><bean:message bundle="sheet" key="common.split"/></option>-->
                    </select> <%} else if (taskName.equals("SumUpHumTask")) { %>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectDealPage}"><bean:message bundle="urgentfault"
                                                                               key="operate.reject"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowSumUpDealPage}"><bean:message bundle="urgentfault"
                                                                              key="operate.SumUp"/></option>
                        <option value="${urlShowTransferPage}"><bean:message bundle="sheet"
                                                                             key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                    </select>
                    <%} else if (taskName.equals("DraftHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="urgentfault"
                                                                             key="operate.Draft"/></option>
                    </select>
                    <%} else if (taskName.equals("BackHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlBackDealPage}"><bean:message bundle="urgentfault"
                                                                         key="operate.reSend"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldHumTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="urgentfault"
                                                                             key="operate.Hold"/></option>
                    </select> <%
                } else if (taskName.equals("MiddleReportAffirmSubTask") || taskName.equals("BackReportAffirmSubTask") ||
                        taskName.equals("SolveFaultSubTask") || taskName.equals("SolveFaultAffirmSubTask") ||
                        taskName.equals("ReportSubTask") || taskName.equals("SumUpSubTask")) {
                %>
                    <select id="dealSelector">
                        <%if (taskName.equals("MiddleReportAffirmHumTask")) { %>
                        <option value="${urlShowMiddleReportAffirmDealPage}"><bean:message bundle="urgentfault"
                                                                                           key="operate.MiddleReportAffirm"/></option>
                        <%} %>
                        <%if (taskName.equals("BackReportAffirmHumTask")) { %>
                        <option value="${urlShowBackReportAffirmDealPage}"><bean:message bundle="urgentfault"
                                                                                         key="operate.BackReportAffirm"/></option>
                        <%} %>
                        <%if (taskName.equals("SolveFaultHumTask")) { %>
                        <option value="${urlShowSolveFaultDealPage}"><bean:message bundle="urgentfault"
                                                                                   key="operate.SolveFault"/></option>
                        <%} %>
                        <%if (taskName.equals("SolveFaultAffirmHumTask")) { %>
                        <option value="${urlShowSolveFaultAffirmDealPage}"><bean:message bundle="urgentfault"
                                                                                         key="operate.SolveFaultAffirm"/></option>
                        <%} %>
                        <%if (taskName.equals("ReportHumTask")) { %>
                        <option value="${urlShowReportDealPage}"><bean:message bundle="urgentfault"
                                                                               key="operate.Report"/></option>
                        <%} %>
                        <%if (taskName.equals("SumUpHumTask")) { %>
                        <option value="${urlShowSumUpDealPage}"><bean:message bundle="urgentfault"
                                                                              key="operate.SumUp"/></option>
                        <%} %>
                        <%--	  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
                      <%if(taskName.equals("subTask1")){%>
                      <option value="${urlShowInputSplit1}"><bean:message bundle="sheet" key="common.split"/></option>
                      <%}%>
                      <%if(taskName.equals("subTask2")){%>
                      <option value="${urlShowInputSplit2}"><bean:message bundle="sheet" key="common.split"/></option>
                      <%}%>
                      <%if(taskName.equals("subTask3")){%>
                      <option value="${urlShowInputSplit3}"><bean:message bundle="sheet" key="common.split"/></option>
                      <%}%>
                      <%if(taskName.equals("subTask4")){%>
                      <option value="${urlShowInputSplit4}"><bean:message bundle="sheet" key="common.split"/></option>
                      <%}%> --%>
                    </select> <%} %>
                </td>
            </tr>
        </table>
    </div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>

<script type="text/javascript">
    Ext.onReady(function () {
        //设置下拉框为ajax页面载入器
        eoms.Sheet.setPageLoader("dealSelector", "sheet-deal-content");

        var url = "";
        try {
            url = $("dealSelector").value + "&taskStatus=${taskStatus}";
        } catch (e) {
        }
        var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
            url = "urgentfault.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
        }
        eoms.util.appendPage("sheet-deal-content", url);
    });
</script>

<!-- Sheet Deal Content End -->
<%}%>
<c:if test="${sheetMain.status==0}">
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
    <%if (isAdmin.equals("true")) {%>
    <div id="buttonDisplay" style="display:block"><input type="button"
                                                         title="执行该操作，工单将进入强制归档状态，其他人不能在处理工单"
                                                         value="<bean:message bundle="sheet" key="button.forceHold"/>"
                                                         onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
        <input type="button"
               title="执行该操作，工单将进入强制作废状态，其他人不能在处理工单"
               value="<bean:message bundle="sheet" key="button.forceNullity"/>"
               onclick="$('buttonDisplay').style.display='none';forceOperation(3);"> <input type="button"
                                                                                            value="<bean:message bundle="sheet" key="event.advice"/>"
                                                                                            onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
    <% } else if ((taskStatus.equals("5") && !taskName.equals("cc") && !taskName.equals("reply") && !taskName.equals("advice"))) {%>
    <div id="buttonDisplay"><input type="button"
                                   value="<bean:message bundle="sheet" key="event.advice"/>"
                                   onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
    <% } else if ((taskStatus == null || taskStatus.equals("")) && (userId.equals(sendUserId))) {%>
    <div id="advice" style="display:block"><input type="button"
                                                  title="执行该操作，工单将进入作废状态，其他人不能在处理工单"
                                                  value="<bean:message bundle="sheet" key="button.nullity"/>"
                                                  onclick="$('advice').style.display='none';forceOperation(2);"> <input
            type="button"
            value="<bean:message bundle="sheet" key="event.advice"/>"
            onclick="$('advice').style.display='none';eventOperation(1);">
    </div>
    <% }%>
</c:if>
<%@ include file="/common/footer_eoms.jsp" %>
