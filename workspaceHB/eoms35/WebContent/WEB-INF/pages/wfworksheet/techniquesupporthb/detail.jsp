﻿<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbTask" %>
<%@page import="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbMain" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    TechniqueSupportHbTask task = new TechniqueSupportHbTask();
    String operaterType = "";
    String ifsub = "";
    String ifwaitfor = "";
    if (request.getAttribute("task") != null) {
        task = (TechniqueSupportHbTask) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
        operaterType = task.getOperateType();
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
        ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
    }

    request.setAttribute("operaterType", operaterType);
    TechniqueSupportHbMain basemain = (TechniqueSupportHbMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();
    String mainTechSupportType = basemain.getMainTechSupportType();
    String mainSheetType = basemain.getMainSheetType();
    request.setAttribute("taskStatus", taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("11=====taskName======" + taskName);
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
                url: 'techniquesupporthb.do?method=showSheetDealList&orderByDetp=true&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                url: 'techniquesupporthb.do?method=showWorkFlow&linkServiceName=iTechniqueSupportHbLinkManager&dictSheetName=dict-sheet-techniquesupporthb&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                url: 'techniquesupporthb.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "techniquesupporthb.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "techniquesupporthb.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "techniquesupporthb.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {

            var url2 = "techniquesupporthb.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);

        } else if (obj == 2) {

            var url2 = "techniquesupporthb.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "techniquesupporthb.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "techniquesupporthb.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
            <%@ include file="/WEB-INF/pages/wfworksheet/techniquesupporthb/basedetailnew.jsp" %>
            <br/>
            <table class="formTable">
                <caption></caption>

                <tr>
                    <td class="label">工单受理时限</td>
                    <td class="content">
                        <bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                    <td class="label">工单处理时限</td>
                    <td class="content">
                        <bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">技术支持类型</td>
                    <td colspan="3">
                        <eoms:id2nameDB id="${sheetMain.mainTechSupportType}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <!-- GSM技术支持： -->

                <!-- 数据专业技术支持 SJZY-->
                <% if ("101470102".equals(mainTechSupportType) || "101470101".equals(mainTechSupportType) || "101470106".equals(mainTechSupportType) || "101470107".equals(mainTechSupportType) || "101470108".equals(mainTechSupportType)) { %>
                <tr>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainSheetType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainSheetType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainFaultType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainIsMainFault"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsMainFault}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainFaultPhoneType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultPhoneType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <%} %>
                <!-- 无线专业技术支持 WXZY -->
                <% if ("101470103".equals(mainTechSupportType)) { %>
                <tr>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainSheetType"/></td>
                    <td class="content" colspan='3'><eoms:id2nameDB id="${sheetMain.mainSheetType}"
                                                                    beanId="ItawSystemDictTypeDao"/></td>
                    <!--<td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultType"/></td>
           <td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultType}" beanId="ItawSystemDictTypeDao"/></td>-->
                </tr>
                <% if ("101470201".equals(mainSheetType)) { %>
                <tr>
                    <td class="label">故障级别</td>
                    <td class="content" colspan='3'><eoms:id2nameDB id="${sheetMain.mainFaultLevel}"
                                                                    beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <%} %>
                <tr>
                    <td class="label">子专业</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainProfessional}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label">厂商</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainManufacturer}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <!--<tr>
           <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainIsMainFault"/></td>
           <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsMainFault}" beanId="ItawSystemDictTypeDao"/></td>
           <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainDevType"/></td>
           <td class="content"><eoms:id2nameDB id="${sheetMain.mainDevType}" beanId="ItawSystemDictTypeDao"/></td>
      </tr>
            <tr>
           <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultPhoneType"/></td>
           <td class="content" colspan='3'><eoms:id2nameDB id="${sheetMain.mainFaultPhoneType}" beanId="ItawSystemDictTypeDao"/></td>
      </tr>-->

                <%} %>
                <!-- 传输专业技术支持 CSZY -->
                <% if ("101470104".equals(mainTechSupportType)) { %>
                <tr>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainTrasitionDevType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainTrasitionDevType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainFaultType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainIsMainFault"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsMainFault}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainFaultPhoneType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultPhoneType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <%} %>
                <!-- 空调和配套专业技术支持 KTPTZY -->
                <% if ("101470105".equals(mainTechSupportType)) { %>
                <tr>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainFaultType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainFaultType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainIsMainFault"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainIsMainFault}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainFaultPhoneType"/></td>
                    <td class="content" colspan='3'><eoms:id2nameDB id="${sheetMain.mainFaultPhoneType}"
                                                                    beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <%} %>
                <% if ("101470204".equals(mainSheetType)) { %>
                <tr>
                    <td class="label">板卡数量</td>
                    <td class="content" colspan='3'>${sheetMain.mainCardNumber}</td>
                </tr>
                <%} %>
                <tr>
                    <td class="label"><bean:message bundle="techniquesupporthb"
                                                    key="techniquesupporthb.mainQuestionDescript"/></td>
                    <td class="content" colspan='3'><bean:write name="sheetMain" property="mainQuestionDescript"
                                                                scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">初步处理意见</td>
                    <td class="content" colspan='3'><bean:write name="sheetMain" property="mainInitDealOpinition"
                                                                scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
                    <td colspan='3'>
                        <eoms:attachment name="sheetMain" property="sheetAccessories"
                                         scope="request" idField="sheetAccessories" appCode="techniquesupporthb"
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
<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="techniquesupporthb.do">
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
<!-- 提供实施方案 -->
<c:url var="urlShowDoneImplTaskDealPage" value="techniquesupporthb.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="911"/>
</c:url>

<!-- 故障解决 -->
<c:url var="urlShowImplValidTaskPassDealPage" value="techniquesupporthb.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="921"/>
</c:url>
<!-- 故障未解决 -->
<c:url var="urlShowImplValidTaskNotPassDealPage" value="techniquesupporthb.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="920"/>
</c:url>
<!--任务分派-->
<c:url var="urlShowInputSplit" value="techniquesupporthb.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="TechniqueSupportHbSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="operateType" value="10"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>

<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="techniquesupporthb.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="11"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>

<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="techniquesupporthb.do">
    <c:param name="method" value="showDealReplyAcceptPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="6"/>
</c:url>

<!-- 处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="techniquesupporthb.do">
    <c:param name="method" value="showDealReplyRejectPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="TechniqueSupportHbSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
</c:url>


<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="techniquesupporthb.do">
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
<!-- 草稿 -->
<c:url var="urlShowCompleteDraftPage" value="techniquesupporthb.do">
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
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>

<!-- 待归档/退回 -->
<c:url var="urlShowBackDeal" value="techniquesupporthb.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="17"/>
    <c:param name="backFlag" value="yes"/>
</c:url>

<!-- 重派 -->
<c:url var="urlShowReSendDeal" value="techniquesupporthb.do">
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


<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="techniquesupporthb.do">
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
<!-- 阶段回复 -->
<c:url var="urlShowPhaseReplyPage" value="techniquesupporthb.do">
    <c:param name="method" value="showPhaseBackToUpPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="reply"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="${operateType}"/>
</c:url>

<!-- 确认驳回urlShowAcceptRejectTaskDealPage -->

<c:url var="urlShowAcceptRejectTaskDealPage" value="techniquesupporthb.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="204"/>
</c:url>

<!-- 移交，由于不走showDealPage方法，所以不需要设置operateType,也不需要保存模板 -->
<c:url var="urlShowTransferkPage" value="techniquesupporthb.do">
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
                    <bean:message bundle="sheet" key="sheet.deal"/>:
                    <% }
                    }
                    }

                    %>


                    <%if (taskName.equals("DoneImplTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDoneImplTaskDealPage}">提供实施方案</option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>

                        <%} %>
                        <%} else { %>
                        <!-- 有子任务 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}">分派回复</option>
                        <%} %>
                        <% } %>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>

                        <%} %>
                    </select>
                    <%} else if (taskName.equals("ImplValidTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>

                        <option value="${urlShowImplValidTaskPassDealPage}">故障解决</option>
                        <option value="${urlShowImplValidTaskNotPassDealPage}">故障未解决</option>

                        <%} %>
                        <%} else { %>
                        <!-- 有子任务 -->
                        <% if (ifwaitfor.equals("false")) {%>

                        <%} %>
                        <% } %>

                        <%} %>
                    </select>

                    <%} else if (taskName.equals("DraftTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowCompleteDraftPage}"><bean:message bundle="techniquesupporthb"
                                                                                  key="operateType.draft"/></option>
                    </select>
                    <%} else if (taskName.equals("RejectTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowReSendDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="techniquesupporthb"
                                                                             key="operateType.hold"/></option>
                    </select>
                    <%} %>
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
            url = "techniquesupporthb.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
        }
        eoms.util.appendPage("sheet-deal-content", url);
    });
    var selector = document.getElementById("dealSelector");
    var operateType = "${operateType}";
    if (operateType != "") {
        for (var i = 0; i < selector.length; i++) {
            var selectorValue = selector[i].value;
            var splitArray = selectorValue.split("&operateType=");
            if (splitArray.length == 2) {
                if (splitArray[1] == operateType) {
                    selector.selectedIndex = i;
                    break;
                }
            }
        }
    }
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
