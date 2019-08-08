<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.plannconfirm.model.PlannConfirmTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>


<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    PlannConfirmTask task = new PlannConfirmTask();
    String operaterType = "";
    String ifsub = "";
    String ifwaitfor = "";
    if (request.getAttribute("task") != null) {
        task = (PlannConfirmTask) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
        operaterType = task.getOperateType();
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
        ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
    }

    request.setAttribute("operaterType", operaterType);
    BaseMain basemain = (BaseMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();

    request.setAttribute("taskStatus", taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
%>

<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>
<script type="text/javascript">
    Ext.onReady(function () {
        var tabConfig = {
            items: [{
                id: 'sheetinfo',
                text: '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.historyView"/>',
                url: 'plannconfirm.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                url: 'plannconfirm.do?method=showWorkFlow&linkServiceName=iPlannConfirmLinkManager&dictSheetName=dict-sheet-plannconfirm&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }
                , {
                    text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                    url: 'plannconfirm.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "plannconfirm.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "plannconfirm.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "plannconfirm.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {

            var url2 = "plannconfirm.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else if (obj == 2) {

            var url2 = "plannconfirm.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "plannconfirm.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "plannconfirm.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
            <%@ include file="/WEB-INF/pages/wfworksheet/plannconfirm/basedetailnew.jsp" %>
            <br/>
            <table id="sheet" class="formTable">

                <tr>
                    <td class="label">受理时限</td>
                    <td class="content">
                        <bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                    <td class="label">回复时限</td>
                    <td class="content">
                        <bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 所属专业 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.professional"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.professional}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 网络分类一级类别 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.networkTypeOne"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.networkTypeOne}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 网络分类二级类别 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.networkTypeTwo"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.networkTypeTwo}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 网络分类三级类别 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.networkTypeThree"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.networkTypeThree}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 任务类型 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.taskType"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.taskType}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 任务描述 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.taskDescription"/>
                    </td>
                    <td>
                            ${sheetMain.taskDescription}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 站址选点时间 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.selectionTime"/>
                    </td>
                    <td>
                            ${eoms:date2String(sheetMain.selectionTime)}
                    </td>
                    <td class="label">
                        <!-- 规划编号 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.planningNumber"/>
                    </td>
                    <td>
                            ${sheetMain.planningNumber}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 属地分公司 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.territorialBranch"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.territorialBranch}" beanId="tawSystemDeptDao"/>
                    </td>
                    <td class="label">
                        <!-- 基站站址 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.stationSite"/>
                    </td>
                    <td>
                            ${sheetMain.stationSite}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 场景类型 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.sceneType"/>
                    </td>
                    <td>
                            ${sheetMain.sceneType}
                    </td>
                    <td class="label">
                        <!-- 经度 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.longitude"/>
                    </td>
                    <td>
                            ${sheetMain.longitude}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 纬度 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.latitude"/>
                    </td>
                    <td>
                            ${sheetMain.latitude}
                    </td>
                    <td class="label">
                        <!-- 楼宇类型 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.buildingType"/>
                    </td>
                    <td>
                            ${sheetMain.buildingType}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 楼宇层数 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.buildingNumber"/>
                    </td>
                    <td>
                            ${sheetMain.buildingNumber}
                    </td>
                    <td class="label">
                        <!-- 天线挂高 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.antennaHeight"/>
                    </td>
                    <td>
                            ${sheetMain.antennaHeight}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 平台类型 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.platformType"/>
                    </td>
                    <td>
                            ${sheetMain.platformType}
                    </td>
                    <td class="label">
                        <!-- 天线类型 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.antennaType"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.antennaType}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 天线型号 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.antennaModel"/>
                    </td>
                    <td>
                            ${sheetMain.antennaModel}
                    </td>
                    <td class="label">
                        <!-- 经纬度符合要求 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.requirement"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.requirement}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 挂高符合要求 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.highRequirement"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.highRequirement}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 存在阻挡 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.existenceBarrier"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.existenceBarrier}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 其他情况 -->
                        <bean:message bundle="plannconfirm" key="plannConfirmMain.otherCircumstance"/>
                    </td>
                    <td>
                            ${sheetMain.otherCircumstance}
                    </td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
                    <td colspan='3'>
                        <eoms:attachment name="sheetMain" property="sheetAccessories"
                                         scope="request" idField="sheetAccessories" appCode="plannconfirm"
                                         viewFlag="Y"/>
                    </td>
                </tr>
            </table>
        </logic:present>
    </div>
</div>
<!-- Sheet Tabs End -->

<!-- 工单处理环节开始 -->
<% if (taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")) {%>

<!-- Sheet Deal Content Start -->
<!-- 下面是流程中的步骤URL -->
<!-- 统一受理 -->
<c:url var="urlShowConfirmTask102Page" value="plannconfirm.do">
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
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 审核 -->
<c:url var="urlShowAuditTask103Page" value="plannconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="103"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 归档 -->
<c:url var="urlShowHoldTask18Page" value="plannconfirm.do">
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

<!-- 流程中的步骤已结束 -->

<!-- 下面是公共的URL -->
<!-- 退回 -->
<c:url var="urlShowRejectDealPage" value="plannconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
</c:url>


<!-- 移交，由于不走showDealPage方法，所以不需要设置operateType,也不需要保存模板 -->
<c:url var="urlShowTransferkPage" value="plannconfirm.do">
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

<!-- 阶段回复 直接到另一个页面,不需要保存模板-->
<c:url var="urlShowPhaseReplyPage" value="plannconfirm.do">
    <c:param name="method" value="showPhaseBackToUpPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="reply"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="${operaterType}"/>
</c:url>

<!--任务分派-->
<c:url var="urlShowInputSplit" value="plannconfirm.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="PlannConfirmSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="operateType" value="10"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>

<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="plannconfirm.do">
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
<c:url var="urlShowDealReplyRejectPage" value="plannconfirm.do">
    <c:param name="method" value="showDealReplyRejectPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="PlannConfirmSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
</c:url>
<!-- 驳回上一级 -->
<c:url var="urlShowRejectBackPage" value="plannconfirm.do">
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

<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="plannconfirm.do">
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

<!-- 被驳回 -->
<c:url var="urlBackDealPage" value="plannconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="46"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
    <c:param name="backFlag" value="yes"/>
</c:url>

<!-- 草稿 -->
<c:url var="urlShowDraftPage" value="plannconfirm.do">
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


<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="plannconfirm.do">
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

<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="plannconfirm.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="PlannConfirmSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="30"/>
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="plannconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="PlannConfirmSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="55"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>

<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="plannconfirm.do">
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
            url = "plannconfirm.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
        }
        eoms.util.appendPage("sheet-deal-content", url);
    });
</script>

<div class="sheet-deal">
    <div class='sheet-deal-header'>
        <!-- 下面是各个处理环节的下拉框功能 -->
        <table>
            <tr>
                <td width="50%">
                    <%if (!taskName.equals("cc") && !taskName.equals("reply") && !taskName.equals("advice")) { %>
                    <bean:message bundle="sheet" key="sheet.deal"/>:
                    <%}%>

                    <!-- 统一受理 -->
                    <% if (taskName.equals("ConfirmTask")) { %>
                    <select id="dealSelector">

                        <% if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <!-- 确认受理 -->
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- 驳回 -->
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <% } else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>

                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false") || ifsub == null) {%>
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <!-- 流程步骤，移交，阶段回复，分派 -->
                        <option value="${urlShowConfirmTask102Page}"><bean:message bundle="plannconfirm"
                                                                                   key="operateType.ConfirmTask102"/></option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="plannconfirm"
                                                                             key="operateType.subTaskReply"/></option>
                        <% }%>
                        <% } %>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>

                        <%}%>

                    </select>
                    <!-- 审核 -->
                    <% }
                        if (taskName.equals("AuditTask")) { %>
                    <select id="dealSelector">

                        <% if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <!-- 确认受理 -->
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- 驳回 -->
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <% } else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>

                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false") || ifsub == null) {%>
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <!-- 流程步骤，移交，阶段回复，分派 -->
                        <option value="${urlShowAuditTask103Page}"><bean:message bundle="plannconfirm"
                                                                                 key="operateType.AuditTask103"/></option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="plannconfirm"
                                                                             key="operateType.subTaskReply"/></option>
                        <% }%>
                        <% } %>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>

                        <%}%>

                    </select>
                    <!-- 归档 -->
                    <% }
                        if (taskName.equals("HoldTask")) { %>
                    <select id="dealSelector">

                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false") || ifsub == null) {%>
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <!-- 流程步骤，移交，阶段回复，分派 -->
                        <option value="${urlShowHoldTask18Page}"><bean:message bundle="plannconfirm"
                                                                               key="operateType.HoldTask18"/></option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="plannconfirm"
                                                                             key="operateType.subTaskReply"/></option>
                        <% }%>
                        <% } %>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>

                    </select>
                    <% } else if (taskName.equals("RejectTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlBackDealPage}"><bean:message bundle="sheet" key="common.reSend"/></option>
                    </select>
                    <% } else if (taskName.equals("DraftTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowDraftPage}"><bean:message bundle="plannconfirm"
                                                                          key="operateType.DraftTask"/></option>
                    </select>
                    <% } %>
                </td>
            </tr>
        </table>

        <!-- 各个处理环节的下拉框功能结束 -->
    </div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>
<!-- 工单处理环节结束 -->


<script type="text/javascript">
    Ext.onReady(function () {
        var url = "";
        try {
            url = $("dealSelector").value + "&taskStatus=${taskStatus}";
        } catch (e) {
        }
        var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
            url = "plannconfirm.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
        }
        eoms.util.appendPage("sheet-deal-content", url);
    });
</script>
<% }%>


<c:if test="${sheetMain.status==0}">
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
    <% if (isAdmin.equals("true")) {%>
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
