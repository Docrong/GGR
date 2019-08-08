<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.industrysms.model.IndustrySmsMain" %>
<%@page import="com.boco.eoms.sheet.industrysms.model.IndustrySmsTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>


<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    IndustrySmsTask task = new IndustrySmsTask();
    String operaterType = "";
    String ifsub = "";
    String ifwaitfor = "";
    if (request.getAttribute("task") != null) {
        task = (IndustrySmsTask) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
        operaterType = task.getOperateType();
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
        ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
    }

    request.setAttribute("operaterType", operaterType);
    IndustrySmsMain basemain = (IndustrySmsMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();
    String spareTwo = StaticMethod.nullObject2String(basemain.getSpareTwo());

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
                url: 'industrysms.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                url: 'industrysms.do?method=showWorkFlow&linkServiceName=iIndustrySmsLinkManager&dictSheetName=dict-sheet-industrysms&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }
                , {
                    text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                    url: 'industrysms.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "industrysms.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "industrysms.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "industrysms.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {

            var url2 = "industrysms.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else if (obj == 2) {

            var url2 = "industrysms.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "industrysms.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "industrysms.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
            <%@ include file="/WEB-INF/pages/wfworksheet/industrysms/basedetailnew.jsp" %>
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
                        <!-- spareOne -->
                        工单任务性质
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.spareOne}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- spareTwo -->
                        是否自动处理
                    </td>
                    <td>
                        <%
                            if (spareTwo.equals("1")) {
                        %>
                        是
                        <%
                        } else {
                        %>
                        否
                        <%
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- ADC/MAS地域 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.regional"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.regional}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- EC/SI类型 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.ecsiType"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.ecsiType}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- EC/SI简称 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.abbreviation"/>
                    </td>
                    <td>
                            ${sheetMain.abbreviation}
                    </td>
                    <td class="label">
                        <!-- 企业代码 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.enterpriseCode"/>
                    </td>
                    <td>
                            ${sheetMain.enterpriseCode}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- EC/SI服务代码 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.serviceCode"/>
                    </td>
                    <td>
                            ${sheetMain.serviceCode}
                    </td>
                    <td class="label">
                        <!-- 客户服务器IP -->
                        <bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddr"/>
                    </td>
                    <td>
                            ${sheetMain.cilentServerIpAddr}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 是否为多IP -->
                        <bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddrs"/>
                    </td>
                    <td>
                            ${sheetMain.cilentServerIpAddrs}
                    </td>
                    <td class="label">
                        <!-- 登录网关用户名 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.user"/>
                    </td>
                    <td>
                            ${sheetMain.userValue}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 登录网关密码 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.password"/>
                    </td>
                    <td>
                            ${sheetMain.passwordValue}
                    </td>
                    <td class="label">
                        <!-- 最大连接数 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.maxConnections"/>
                    </td>
                    <td>
                            ${sheetMain.maxConnections}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 最大下发流量 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.maxInBytes"/>
                    </td>
                    <td>
                            ${sheetMain.maxInBytes}
                    </td>
                    <td class="label">
                        <!-- 最大上行流量 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.maxOutBytes"/>
                    </td>
                    <td>
                            ${sheetMain.maxOutBytes}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- EC/SI使用协议 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.protocol"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.protocol}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 是否M模块鉴权 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.isAuthentication"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.isAuthentication}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 业务联系人 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.seviceContact"/>
                    </td>
                    <td>
                            ${sheetMain.seviceContact}
                    </td>
                    <td class="label">
                        <!-- 业务联系电话 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.sevicePhone"/>
                    </td>
                    <td>
                            ${sheetMain.sevicePhone}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 客户联系人 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.customerContact"/>
                    </td>
                    <td>
                            ${sheetMain.customerContact}
                    </td>
                    <td class="label">
                        <!-- 客户联系电话 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.customerPhone"/>
                    </td>
                    <td>
                            ${sheetMain.customerPhone}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 业务数据申请单位 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.unit"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.unit}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 业务数据据申请日期 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.applyDate"/>
                    </td>
                    <td>
                            ${eoms:date2String(sheetMain.applyDate)}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 硬件型号 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.hardwareModel"/>
                    </td>
                    <td>
                            ${sheetMain.hardwareModel}
                    </td>
                    <td class="label">
                        <!-- 软件版本 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.softVersion"/>
                    </td>
                    <td>
                            ${sheetMain.softVersion}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 集成商 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.integrator"/>
                    </td>
                    <td>
                            ${sheetMain.integrator}
                    </td>
                    <td class="label">
                        <!-- 是否为加急流程 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.isUrgent"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.isUrgent}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 集团批复工单号 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.groupNumber"/>
                    </td>
                    <td>
                            ${sheetMain.groupNumber}
                    </td>
                    <td class="label">
                        <!-- MASID -->
                        <bean:message bundle="industrysms" key="industrySmsMain.masId"/>
                    </td>
                    <td>
                            ${sheetMain.masId}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 工单回复时限 -->
                        <bean:message bundle="industrysms" key="industrySmsMain.timeLimit"/>
                    </td>
                    <td>
                            ${eoms:date2String(sheetMain.timeLimit)}
                    </td>
                    <td class="label">
                        <!-- EC/SI简称(新) -->
                        <bean:message bundle="industrysms" key="industrySmsMain.abbreviationNew"/>
                    </td>
                    <td>
                            ${sheetMain.abbreviationNew}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 客户服务器IP(新) -->
                        <bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddrNew"/>
                    </td>
                    <td>
                            ${sheetMain.cilentServerIpAddrNew}
                    </td>
                    <td class="label">
                        <!-- 是否为多IP(新) -->
                        <bean:message bundle="industrysms" key="industrySmsMain.cilentServerIpAddrsNew"/>
                    </td>
                    <td>
                            ${sheetMain.cilentServerIpAddrsNew}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 登录网关密码(新) -->
                        <bean:message bundle="industrysms" key="industrySmsMain.passwordNew"/>
                    </td>
                    <td>
                            ${sheetMain.passwordNew}
                    </td>
                    <td class="label">
                        <!-- 最大连接数(新) -->
                        <bean:message bundle="industrysms" key="industrySmsMain.maxConnectionsNew"/>
                    </td>
                    <td>
                            ${sheetMain.maxConnectionsNew}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 最大下发流量(新) -->
                        <bean:message bundle="industrysms" key="industrySmsMain.maxInBytesNew"/>
                    </td>
                    <td>
                            ${sheetMain.maxInBytesNew}
                    </td>
                    <td class="label">
                        <!-- 最大上行流量(新) -->
                        <bean:message bundle="industrysms" key="industrySmsMain.maxOutBytesNew"/>
                    </td>
                    <td>
                            ${sheetMain.maxOutBytesNew}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- EC/SI使用协议(新) -->
                        <bean:message bundle="industrysms" key="industrySmsMain.protocolNew"/>
                    </td>
                    <td>
                        <eoms:id2nameDB id="${sheetMain.protocolNew}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
                    <td colspan='3'>
                        <eoms:attachment name="sheetMain" property="sheetAccessories"
                                         scope="request" idField="sheetAccessories" appCode="industrysms"
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
<!-- 审核 -->
<c:url var="urlShowAuditHumTask102Page" value="industrysms.do">
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
<!-- 审核不通过 -->
<c:url var="urlShowAuditHumTask22Page" value="industrysms.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="22"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 数据制作 -->
<c:url var="urlShowDataHumTask103Page" value="industrysms.do">
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
<c:url var="urlShowHoldTask18Page" value="industrysms.do">
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
<c:url var="urlShowRejectDealPage" value="industrysms.do">
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
<c:url var="urlShowTransferkPage" value="industrysms.do">
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
<c:url var="urlShowPhaseReplyPage" value="industrysms.do">
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
<c:url var="urlShowInputSplit" value="industrysms.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="IndustrySmsSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="operateType" value="10"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>

<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="industrysms.do">
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
<c:url var="urlShowDealReplyRejectPage" value="industrysms.do">
    <c:param name="method" value="showDealReplyRejectPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="IndustrySmsSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
</c:url>
<!-- 驳回上一级 -->
<c:url var="urlShowRejectBackPage" value="industrysms.do">
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
<c:url var="urlShowAcceptDealPage" value="industrysms.do">
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
<c:url var="urlBackDealPage" value="industrysms.do">
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
<c:url var="urlShowDraftPage" value="industrysms.do">
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
<c:url var="urlShowDispatchPage" value="industrysms.do">
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
<c:url var="urlShowInputSplitAudit" value="industrysms.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="IndustrySmsSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="30"/>
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="industrysms.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="IndustrySmsSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="55"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>

<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="industrysms.do">
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
            url = "industrysms.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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

                    <!-- 审核 -->
                    <% if (taskName.equals("AuditHumTask")) { %>
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
                        <option value="${urlShowAuditHumTask102Page}"><bean:message bundle="industrysms"
                                                                                    key="operateType.AuditHumTask102"/></option>
                        <option value="${urlShowAuditHumTask22Page}">审核不通过</option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="industrysms"
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
                    <!-- 数据制作 -->
                    <% }
                        if (taskName.equals("DataHumTask")) { %>
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
                        <option value="${urlShowDataHumTask103Page}">数据制作情况</option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="industrysms"
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
                        <option value="${urlShowHoldTask18Page}"><bean:message bundle="industrysms"
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
                        <option value="${urlShowDispatchPage}"><bean:message bundle="industrysms"
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
                        <option value="${urlShowDraftPage}"><bean:message bundle="industrysms"
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
            url = "industrysms.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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
