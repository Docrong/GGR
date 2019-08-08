<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    AgentMaintenanceTask task = new AgentMaintenanceTask();
    String operaterType = "";
    if (request.getAttribute("task") != null) {
        task = (AgentMaintenanceTask) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
        operaterType = task.getOperateType();
    }

    request.setAttribute("operaterType", operaterType);
    BaseMain basemain = (BaseMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();

    request.setAttribute("taskStatus", taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("=====taskName======" + taskName);
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
                url: 'agentmaintenance.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                //url : '/ProcessMonitor/runtime/html/index.jsp?appName=agentmaintenanceProcessApp&templateName=agentmaintenanceMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
                url: 'agentmaintenance.do?method=showPic',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                url: 'agentmaintenance.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "agentmaintenance.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "agentmaintenance.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "agentmaintenance.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {

            var url2 = "agentmaintenance.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);

        } else if (obj == 2) {

            var url2 = "agentmaintenance.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "agentmaintenance.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "agentmaintenance.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
            <%@ include file="/WEB-INF/pages/wfworksheet/agentmaintenance/basedetailnew.jsp" %>
            <br/>
            <table width="100%" style="border: 0px">
                <c:if test="${sheetMain.type=='commonfault' }">
                    <tr>
                        <td>
                            <table class="formTable">
                                <tr>
                                    <td class="label"><bean:message bundle="agentmaintenance"
                                                                    key="agentMaintenanceMain.mainFaultAlarmId"/></td>
                                    <td class="content">
                                            ${sheetMain.mainFaultAlarmId}
                                    </td>
                                    <td class="label"><bean:message bundle="agentmaintenance"
                                                                    key="agentMaintenanceMain.mainFaultAlarmNum"/></td>
                                    <td class="content">
                                            ${sheetMain.mainFaultAlarmNum}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label"><bean:message bundle="agentmaintenance"
                                                                    key="agentMaintenanceMain.mainFaultAlarmSolveDate"/></td>
                                    <td class="content">
                                            ${sheetMain.mainFaultAlarmSolveDate}
                                    </td>
                                    <td class="label"><bean:message bundle="agentmaintenance"
                                                                    key="agentMaintenanceMain.mainFaultAlarmSource"/></td>
                                    <td class="content">
                                            ${sheetMain.mainFaultAlarmSource}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label"><bean:message bundle="agentmaintenance"
                                                                    key="agentMaintenanceMain.mainFaultAlarmLevel"/></td>
                                    <td class="content">
                                            ${sheetMain.mainFaultAlarmLevel}
                                    </td>
                                    <td class="label">
                                        <!-- 告警逻辑分类 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultAlarmLogicSort"/>*
                                    </td>
                                    <td>
                                            ${sheetMain.mainFaultAlarmLogicSort}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 告警逻辑子类 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultAlarmLogicSortSub"/>*
                                    </td>
                                    <td>
                                            ${sheetMain.mainFaultAlarmLogicSortSub}
                                    </td>
                                    <td class="label">
                                        <!-- 故障描述 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultDesc"/>*
                                    </td>
                                    <td>
                                            ${sheetMain.mainFaultDesc}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 故障发现方式 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultDiscoverableMode"/>*
                                    </td>
                                    <td>
                                            ${sheetMain.mainFaultDiscoverableMode}
                                    </td>
                                    <td class="label">
                                        <!-- 故障处理响应级别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultResponseLevel"/>*
                                    </td>
                                    <td>
                                        <eoms:id2nameDB id="${sheetMain.mainFaultResponseLevel}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 网络分类(一级) -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultNetSortOne"/>*
                                    </td>
                                    <td>
                                        <eoms:id2nameDB id="${sheetMain.mainFaultNetSortOne}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 网络分类(二级) -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultNetSortTwo"/>*
                                    </td>
                                    <td>
                                        <eoms:id2nameDB id="${sheetMain.mainFaultNetSortTwo}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="label">
                                        <!-- 网络分类(三级) -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultNetSortThree"/>*
                                    </td>
                                    <td>
                                        <eoms:id2nameDB id="${sheetMain.mainFaultNetSortThree}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 派单方式 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultSendMode"/>*
                                    </td>
                                    <td>
                                        <eoms:id2nameDB id="${sheetMain.mainFaultSendMode}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 故障设备厂商 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultEquipmentFactory"/>*
                                    </td>
                                    <td>
                                        <eoms:id2nameDB id="${sheetMain.mainFaultEquipmentFactory}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 网元名称 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultNetName"/>*
                                    </td>
                                    <td>
                                            ${sheetMain.mainFaultNetName}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 故障设备型号 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultEquipmentModel"/>*
                                    </td>
                                    <td>
                                            ${sheetMain.mainFaultEquipmentModel}
                                    </td>
                                    <td class="label">
                                        <!-- 故障发生时间 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultGenerantTime"/>*
                                    </td>
                                    <td>
                                            ${eoms:date2String(sheetMain.mainFaultGenerantTime)}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 故障省份 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultGenerantPriv"/>*
                                    </td>
                                    <td>${sheetMain.mainFaultGenerantPriv}
                                    </td>
                                    <td class="label">
                                        <!-- 故障地市 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultGenerantCity"/>*
                                    </td>
                                    <td>
                                            ${sheetMain.mainFaultGenerantCity}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 是否影响业务 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultIfAffectOperation"/>*
                                    </td>
                                    <td><eoms:id2nameDB id="${sheetMain.mainFaultIfAffectOperation}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 相关投诉处理工单号 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultApplySheetId"/>*
                                    </td>
                                    <td>
                                            ${sheetMain.mainFaultApplySheetId}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 是否已预处理 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainFaultPretreatment"/>*
                                    </td>
                                    <td><eoms:id2nameDB id="${sheetMain.mainFaultPretreatment}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                    </td>
                                    <td>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
                                    <td colspan='3'>
                                        <eoms:attachment name="sheetMain" property="sheetAccessories" scope="request"
                                                         idField="sheetAccessories" appCode="agentmaintenance"
                                                         viewFlag="Y"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${sheetMain.type=='commontask' }">
                    <tr>
                        <td>
                            <table class="formTable">
                                <tr>
                                    <td class="label">
                                        <!-- 网络分类一级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainTaskNetSort1"/>*
                                    </td>
                                    <td><eoms:id2nameDB id="${sheetMain.mainTaskNetSort1}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 网络分类二级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainTaskNetSort2"/>*
                                    </td>
                                    <td><eoms:id2nameDB id="${sheetMain.mainTaskNetSort2}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="label">
                                        <!-- 网络分类三级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainTaskNetSort3"/>*
                                    </td>
                                    <td><eoms:id2nameDB id="${sheetMain.mainTaskNetSort3}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 任务类型 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainTaskType"/>*
                                    </td>
                                    <td><eoms:id2nameDB id="${sheetMain.mainTaskType}" beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="label">
                                        <!-- 任务描述 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainTaskDescription"/>*
                                    </td>
                                    <td colspan="3">
                                            ${sheetMain.mainTaskDescription}
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${sheetMain.type=='complaint' }">
                    <tr>
                        <td>
                            <table class="formTable">
                                <tr>
                                    <td class="label">
                                        <!-- 紧急程度 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComurgentDegree"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComurgentDegree}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 投诉分类一级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComType1"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComType1}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="label">
                                        <!-- 投诉分类二级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComType2"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComType2}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 投诉分类三级类别 -->
                                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComType"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComType}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 投诉分类四级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComType4"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComType4}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 投诉分类五级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComType5"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComType5}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 投诉分类六级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComType6"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComType6}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 投诉分类七级类别 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComType7"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComType7}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 派发联系人 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainCombtype1"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainCombtype1}
                                    </td>
                                    <td class="label">
                                        <!-- 派发联系人电话 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainCombdeptContact"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainCombdeptContact}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 是否大面积投诉 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainCombdeptContactPhone"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainCombdeptContactPhone}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 重复投诉次数 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainrepeatComTimes"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainrepeatComTimes}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 客户姓名 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComcustomerName"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainComcustomerName}
                                    </td>
                                    <td class="label">
                                        <!-- 客户电话 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComcustomPhone"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainComcustomPhone}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 客户品牌 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComcustomBrand"/>*
                                    </td>
                                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainComcustomBrand}"
                                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label">
                                        <!-- 投诉受理省份 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComstartDealCity"/>*
                                    </td>
                                    <td class="content">
                                        <eoms:id2nameDB id="${sheetMain.toDeptId}" beanId='tawSystemAreaDao'/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 用户归属地 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComcustomAttribution"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainComcustomAttribution}
                                    </td>
                                    <td class="label">
                                        <!-- 故障时间 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComfaultTime"/>*
                                    </td>
                                    <td class="content">
                                            ${eoms:date2String(sheetMain.mainComfaultTime)}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 投诉时间 -->
                                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComTime"/>*
                                    </td>
                                    <td class="content">
                                            ${eoms:date2String(sheetMain.mainComTime)}
                                    </td>
                                    <td class="label">
                                        <!-- 故障号码 -->
                                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComtNum"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainComtNum}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 故障地点 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComfaultSite"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainComfaultSite}
                                    </td>
                                    <td class="label">
                                        <!-- 终端描述 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainComterminalType"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainComterminalType}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <!-- 投诉内容 -->
                                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComDesc"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainComDesc}
                                    </td>
                                    <td class="label">
                                        <!-- 预处理情况 -->
                                        <bean:message bundle="agentmaintenance"
                                                      key="agentMaintenanceMain.mainCompreDealResult"/>*
                                    </td>
                                    <td class="content">
                                            ${sheetMain.mainCompreDealResult}
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </c:if>
            </table>
        </logic:present>
    </div>
    <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->


<%if (taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")) {%>
<!-- ???? -->
<c:url var="urlShowAcceptDealPage" value="agentmaintenance.do">
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


<!-- ???????? -->
<c:url var="urlShowTransmitDealPage" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="201"/>
</c:url>
<!-- ????????? -->
<c:url var="urlShowTransmit2DealPage" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="202"/>
</c:url>
<!-- ?? -->
<c:url var="urlShowSplitDealPage" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="10"/>
</c:url>
<!-- ???????? -->
<c:url var="urlShowCompleteDealPage" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="205"/>
</c:url>
<!-- ?????? -->
<c:url var="urlShowPassDealPage" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="206"/>
</c:url>
<!-- ???????? -->
<c:url var="urlShowTaskCompleteAuditYes" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="208"/>
</c:url>
<!-- ????????? -->
<c:url var="urlShowTaskCompleteAuditNo" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="209"/>
</c:url>
<!--?????? -->
<c:url var="urlShowDealReplyPassPage" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="211"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
</c:url>
<!--??????? -->
<c:url var="urlShowDealReplyNoPassPage" value="agentmaintenance.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="212"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
</c:url>
<!-- ?? -->
<c:url var="urlShowHoldDealPage" value="agentmaintenance.do">
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
<!-- ?? -->
<c:url var="urlShowCompleteDraftPage" value="agentmaintenance.do">
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
<!-- ?? -->
<c:url var="urlShowBackDeal" value="agentmaintenance.do">
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
<!-- ?? -->
<c:url var="urlShowTransferPage" value="agentmaintenance.do">
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

<!-- ?? -->
<c:url var="urlShowRejectBackPage" value="agentmaintenance.do">
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
<!-- ???? -->
<c:url var="urlShowPhaseReplyPage" value="agentmaintenance.do">
    <c:param name="method" value="showPhaseBackToUpPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="reply"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operaterType" value="9"/>
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
                    <%if (taskName.equals("TaskCreateAuditHumTask")) { %>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) {%>
                        <option value="${urlShowTransmitDealPage}"><bean:message bundle="agentmaintenance"
                                                                                 key="agentmaintenance.taskpass"/></option>
                        <option value="${urlShowTransmit2DealPage}"><bean:message bundle="agentmaintenance"
                                                                                  key="agentmaintenance.tasknopass"/></option>
                        <%}%>
                    </select>
                    <%} else if (taskName.equals("ExcuteHumTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED) || taskStatus.equals(Constants.TASK_STATUS_READY)) { %>
                        <option value="${urlShowCompleteDealPage}"><bean:message bundle="agentmaintenance"
                                                                                 key="agentmaintenance.dealwcnosp"/></option>
                        <!-- <option value="${urlShowTransferPage}"><bean:message bundle="agentmaintenance" key="agentmaintenance.yijiao"/></option> -->
                        <option value="${urlShowSplitDealPage}"><bean:message bundle="agentmaintenance"
                                                                              key="agentmaintenance.fenpai"/></option>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("TaskCompleteAuditHumTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowTaskCompleteAuditYes}"><bean:message bundle="agentmaintenance"
                                                                                     key="agentmaintenance.TaskCompleteAuditYes"/></option>
                        <option value="${urlShowTaskCompleteAuditNo}"><bean:message bundle="agentmaintenance"
                                                                                    key="agentmaintenance.TaskCompleteAuditNo"/></option>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("AffirmHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowDealReplyPassPage}"><bean:message bundle="agentmaintenance"
                                                                                  key="agentmaintenance.dealrejectpass"/></option>
                        <option value="${urlShowDealReplyNoPassPage}"><bean:message bundle="agentmaintenance"
                                                                                    key="agentmaintenance.nopassback"/></option>
                    </select>
                    <%} else if (taskName.equals("DraftHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowCompleteDraftPage}"><bean:message bundle="agentmaintenance"
                                                                                  key="operateType.draft"/></option>
                    </select>
                    <%} else if (taskName.equals("ByRejectHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowBackDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldHumTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="agentmaintenance"
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
            url = "agentmaintenance.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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
        <input type="button" title="${eoms:a2u('????????????????????????????')}"
               value="<bean:message bundle="sheet" key="button.forceHold"/>"
               onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
        <input type="button" title="${eoms:a2u('????????????????????????????')}"
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
        <input type="button" title="${eoms:a2u('??????????????????????????')}"
               value="<bean:message bundle="sheet" key="button.nullity"/>"
               onclick="$('advice').style.display='none';forceOperation(2);">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('advice').style.display='none';eventOperation(1);">
    </div>
    <% }%>
</c:if>
<%@ include file="/common/footer_eoms.jsp" %>
