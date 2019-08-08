<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.greatevent.model.GreatEventTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    GreatEventTask task = new GreatEventTask();
    String operaterType = "";
    String ifsub = "";
    String ifwaitfor = "";
    if (request.getAttribute("task") != null) {
        task = (GreatEventTask) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
        operaterType = task.getOperateType();
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
        ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
    }

    request.setAttribute("operaterType", operaterType);
    BaseMain basemain = (BaseMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();
    System.out.println("sendUserId>>>>>>" + sendUserId);

    request.setAttribute("taskStatus", taskStatus);
    System.out.println("taskStatus>>>>>>>>" + taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("taskName>>>>>>" + taskName);

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
                url: 'greatevent.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                //url : '/ProcessMonitor/runtime/html/index.jsp?appName=itsoftchangeProcessApp&templateName=itsoftchangeMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
                //url : 'itsoftchange.do?method=showWorkFlow&sheetKey=${sheetMain.id}',
                url: 'greatevent.do?method=showWorkFlow&linkServiceName=iGreatEventLinkManager&dictSheetName=dict-sheet-greatevent&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                url: 'greatevent.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "greatevent.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "greatevent.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "greatevent.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>


    });

    function forceOperation(obj) {

        if (obj == 1) {
            var url2 = "greatevent.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else if (obj == 2) {
            var url2 = "greatevent.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {
            var url2 = "greatevent.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "greatevent.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
            eoms.util.appendPage("sheet-deal-content", url2, true);
        }
    }

    function operateType(url) {
        var R = GetQueryString("operateType", url);
        var operateTypeObj = document.getElementById("operateType");
        operateTypeObj.value = opertateTypeValue;
        var divObj = document.getElementById("templateButtonDiv");
        if (opertateTypeValue == "") {
            divObj.style.display = "none";
        } else {
            divObj.style.display = "";
        }
    }

    function GetQueryString(name, url) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = url.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return "";
    }
</script>

<h3 class="sheet-title">

</h3>

<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
    <!-- Sheet Detail Tab Start -->
    <div id="sheetinfo">
        <logic:present name="sheetMain" scope="request">
            <%@ include file="/WEB-INF/pages/wfworksheet/greatevent/basedetailnew.jsp" %>

            <br/>
            <table class="formTable">
                <caption></caption>
                <tr>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.sheetAcceptLimit"/></td>
                    <td class="content">
                        <bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.sheetCompleteLimit"/></td>
                    <td class="content">
                        <bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.mainNetSortOne"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainNetSortOne}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.mainNetSortTwo"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainNetSortTwo}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.mainNetSortThree"/></td>
                    <td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.mainNetSortThree}"
                                                                    beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.mainEventStartTime"/></td>
                    <td class="content"><bean:write name="sheetMain" property="sheetAcceptLimit"
                                                    formatKey="date.formatDateTimeAll" bundle="sheet"
                                                    scope="request"/></td>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.mainEventEndTime"/></td>
                    <td class="content"><bean:write name="sheetMain" property="sheetAcceptLimit"
                                                    formatKey="date.formatDateTimeAll" bundle="sheet"
                                                    scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.mainEventDesc"/></td>
                    <td class="content" colspan="3">
                        <bean:write name="sheetMain" property="mainEventDesc" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.mainInitDealResult"/></td>
                    <td class="content" colspan="3">
                        <bean:write name="sheetMain" property="mainInitDealResult" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="greatevent" key="greatevent.mainEventDealAdvice"/></td>
                    <td class="content" colspan="3">
                        <bean:write name="sheetMain" property="mainEventDealAdvice" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
                    <td colspan='3'>
                        <eoms:attachment name="sheetMain" property="sheetAccessories"
                                         scope="request" idField="sheetAccessories" appCode="greatevent"
                                         viewFlag="Y"/>
                    </td>
                </tr>
            </table>
        </logic:present>
    </div>
    <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->

<%if (taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")) {%>


<!-- 方案制定页面 -->
<c:url var="urlShowMakeTaskDealPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="90"/>
</c:url>
<!-- 方案审核页面 -->
<c:url var="urlShowAuditTaskDealPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="91"/>
</c:url>
<!-- 方案审批页面 -->
<c:url var="urlShowApprovalTaskDealPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="92"/>
</c:url>
<!-- 方案实施页面 -->
<c:url var="urlShowPerformTaskDealPage" value="greatevent.do">
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
<!-- 审批保障结束申请页面 -->
<c:url var="urlShowAuditEndTaskDealPage" value="greatevent.do">
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
<!-- 完成保障报告，提交审核页面 -->
<c:url var="urlShowAssessmentTaskDealPage" value="greatevent.do">
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
<!-- 审核保障报告 -->
<c:url var="urlShowAuditReportTaskDealPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="96"/>
</c:url>
<!-- 完成预案修订，提交预案页面 -->
<c:url var="urlShowModifyTaskDealPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="97"/>
</c:url>
<!-- 归档页面 -->
<c:url var="urlShowHoldDealPage" value="greatevent.do">
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
</c:url>
<!-- 退回 -->
<c:url var="urlShowRejectBackDealPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="102"/>
</c:url>
<!-- 其他公共页面 -->
<!-- 草稿派发 -->
<c:url var="urlShowDraftDeal" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="3"/>
</c:url>
<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="greatevent.do">
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
<!-- 被驳回，显示草稿修改页面 -->
<c:url var="urlBackDealPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="54"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
    <c:param name="backFlag" value="yes"/>
</c:url>
<!-- 重新派发 -->
<c:url var="urlShowBackDeal" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="54"/>
    <c:param name="backFlag" value="yes"/>
</c:url>
<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="greatevent.do">
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
<!-- 移交页面-->
<c:url var="urlShowTransferkPage" value="greatevent.do">
    <c:param name="method" value="showTransferWorkItemPage"/>
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
<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="greatevent.do">
    <c:param name="method" value="showTransferWorkItemPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="88"/>
</c:url>
<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="greatevent.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="GreatEventSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="30"/>
</c:url>
<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="GreatEventSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="55"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="greatevent.do">
    <c:param name="method" value="showDealReplyAcceptPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="GreatEventSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="6"/>
</c:url>
<!-- 处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="greatevent.do">
    <c:param name="method" value="showDealReplyRejectPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="GreatEventSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
</c:url>
<!-- 分派 -->
<c:url var="urlShowInputSplit" value="greatevent.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="GreatEventSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="10"/>
</c:url>
<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="greatevent.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="GreatEventSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="11"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>


<!-- 阶段回复 -->
<c:url var="urlShowPhaseReplyPage" value="greatevent.do">
    <c:param name="method" value="showPhaseBackToUpPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="reply"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operaterType" value="${operaterType}"/>
</c:url>
<div class="sheet-deal">
    <div class='sheet-deal-header'>
        <table>
            <tr>
                <td width="50%">
                    <%
                        if (!taskName.equals("cc")) {
                            if (!taskName.equals("reply")) {
                                if (!taskName.equals("advice")) {
                    %>
                    <bean:message bundle="sheet" key="sheet.deal"/>:<%
                            }
                        }
                    }
                %>
                    <%if (taskName.equals("MakeTask")) { %>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <%if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowMakeTaskDealPage}"><bean:message bundle="greatevent"
                                                                                 key="operateType.MakeTask"/></option>
                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <%} else {%>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="sheet"
                                                                             key="common.splitReply"/></option>
                        <%}%>
                        <%}%>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            System.out.println(taskName+"~~~~~~~~~~~~~~~~~~~");
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("AuditTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <%if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowAuditTaskDealPage}"><bean:message bundle="greatevent"
                                                                                  key="operateType.AuditTask"/></option>
                        <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet"
                                                                                  key="common.transferAudit"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <%} else {%>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet"
                                                                                  key="common.splitReplyAudit"/></option>
                        <%}%>
                        <%}%>
                        <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet"
                                                                                key="common.splitAudit"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <%} %>

                    </select>
                    <%} else if (taskName.equals("ApprovalTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <%if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowApprovalTaskDealPage}"><bean:message bundle="greatevent"
                                                                                     key="operateType.ApprovalTask"/></option>
                        <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet"
                                                                                  key="common.transferAudit"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <%} else {%>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet"
                                                                                  key="common.splitReplyAudit"/></option>
                        <%}%>
                        <%}%>
                        <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet"
                                                                                key="common.splitAudit"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("PerformTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        -->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <%if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowPerformTaskDealPage}"><bean:message bundle="greatevent"
                                                                                    key="operateType.PerformTask"/></option>
                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <%} else {%>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="sheet"
                                                                             key="common.splitReply"/></option>
                        <%}%>
                        <%}%>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("AuditEndTask")) {%>
                    <select id="dealSelector">
                        <%
                            if (taskStatus.equals(Constants.TASK_STATUS_READY)) {
                                System.out.println(taskName + "&&&&&&&&&&&&&&&&&&");
                        %>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <%if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowAuditEndTaskDealPage}"><bean:message bundle="greatevent"
                                                                                     key="operateType.AuditEndTask"/></option>
                        <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet"
                                                                                  key="common.transferAudit"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <%} else {%>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet"
                                                                                  key="common.splitReplyAudit"/></option>
                        <%}%>
                        <%}%>
                        <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet"
                                                                                key="common.splitAudit"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("AssessmentTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        -->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <%if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowAssessmentTaskDealPage}"><bean:message bundle="greatevent"
                                                                                       key="operateType.AssessmentTask"/></option>
                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <%} else {%>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="sheet"
                                                                             key="common.splitReply"/></option>
                        <%}%>
                        <%}%>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("AuditReportTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <%if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowAuditReportTaskDealPage}"><bean:message bundle="greatevent"
                                                                                        key="operateType.AuditReportTask"/></option>
                        <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet"
                                                                                  key="common.transferAudit"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <%} else {%>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet"
                                                                                  key="common.splitReplyAudit"/></option>
                        <%}%>
                        <%}%>
                        <option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet"
                                                                                key="common.splitAudit"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("ModifyTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <%if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowModifyTaskDealPage}"><bean:message bundle="greatevent"
                                                                                   key="operateType.ModifyTask"/></option>
                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <%}%>
                        <%} else {%>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="sheet"
                                                                             key="common.splitReply"/></option>
                        <%}%>
                        <%}%>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("DraftTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowDraftDeal}"><bean:message bundle="greatevent"
                                                                          key="operateType.DraftTask"/></option>
                    </select>
                    <%} else if (taskName.equals("reject")) {%>
                    <select id="dealSelector">
                        <option value="${urlBackDealPage}"><bean:message bundle="sheet" key="common.reSend"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="greatevent"
                                                                             key="operateType.HoldTask"/></option>
                        <option value="${urlShowRejectBackDealPage}"><bean:message bundle="greatevent"
                                                                                   key="operateType.back"/></option>
                    </select>
                    <%}%>
                </td>
            </tr>
        </table>
    </div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>

<script type="text/javascript">
    Ext.onReady(function () {
        eoms.Sheet.setPageLoader("dealSelector", "sheet-deal-content");

        var url = "";
        try {
            url = $("dealSelector").value + "&taskStatus=${taskStatus}";
        } catch (e) {
        }

        var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
            url = "greatevent.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
        }
        eoms.util.appendPage("sheet-deal-content", url);
    });
</script>
<!-- Sheet Deal Content End -->
<%}%>

<c:if test="${sheetMain.status==0}">
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
    <%if (isAdmin.equals("true")) {%>
    <div id="buttonDisplay" style="display:block">
        <input type="button" title="执行该操作，工单将进入强制归档状态，其他人不能在处理工单"
               value="<bean:message bundle="sheet" key="button.forceHold"/>"
               onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
        <input type="button" title="执行该操作，工单将进入强制作废状态，其他人不能在处理工单"
               value="<bean:message bundle="sheet" key="button.forceNullity"/>"
               onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
    <% } else if (taskStatus.equals("5") && !taskName.equals("cc") && !taskName.equals("reply") && !taskName.equals("advice")) {%>
    <div id="buttonDisplay">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
    <% } else if ((taskStatus == null || taskStatus.equals("")) && (userId.equals(sendUserId))) {%>
    <div id="advice" style="display:block">
        <input type="button" title="执行该操作，工单将进入作废状态，其他人不能在处理工单"
               value="<bean:message bundle="sheet" key="button.nullity"/>"
               onclick="$('advice').style.display='none';forceOperation(2);">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('advice').style.display='none';eventOperation(1);">
    </div>
    <% }%>
</c:if>
<%@ include file="/common/footer_eoms.jsp" %>