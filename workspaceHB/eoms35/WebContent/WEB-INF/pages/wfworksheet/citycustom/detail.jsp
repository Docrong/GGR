<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.citycustom.model.CityCustomTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>


<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    CityCustomTask task = new CityCustomTask();
    String operaterType = "";
    String ifsub = "";
    String ifwaitfor = "";
    if (request.getAttribute("task") != null) {
        task = (CityCustomTask) request.getAttribute("task");
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
                url: 'citycustom.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                url: 'citycustom.do?method=showWorkFlow&linkServiceName=iCityCustomLinkManager&dictSheetName=dict-sheet-citycustom&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }
                , {
                    text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                    url: 'citycustom.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "citycustom.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "citycustom.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "citycustom.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {

            var url2 = "citycustom.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else if (obj == 2) {

            var url2 = "citycustom.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "citycustom.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "citycustom.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
            <%@ include file="/WEB-INF/pages/wfworksheet/citycustom/basedetailnew.jsp" %>
            <br/>
            <table id="sheet" class="formTable">

                <tr>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/></td>
                    <td class="content">
                            ${eoms:date2String(sheetMain.sheetAcceptLimit)}
                    </td>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/></td>
                    <td class="content">
                            ${eoms:date2String(sheetMain.sheetCompleteLimit)}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- A端客户经理 -->
                        A端客户经理
                    </td>
                    <td class="content">
                            ${sheetMain.manager}
                    </td>
                    <td class="label">
                        <!-- 客户经理联系电话 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.managerNum"/>
                    </td>
                    <td class="content">
                            ${sheetMain.managerNum}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 产品名称 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.prodouct"/>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${sheetMain.prodouct}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 集团编号 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.groupNum"/>
                    </td>
                    <td class="content">
                            ${sheetMain.groupNum}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 集团名称 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.groupName"/>
                    </td>
                    <td class="content">
                            ${sheetMain.groupName}
                    </td>
                    <td class="label">
                        <!-- 集团级别 -->
                        集团级别
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${sheetMain.groupLevel}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 业务保障等级 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.businessGrade"/>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${sheetMain.businessGrade}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 任务类型 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.taskType"/>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${sheetMain.taskType}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 所属地市 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.area"/>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${sheetMain.area}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 带宽 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.bandWidth"/>(M)
                    </td>
                    <td class="content">
                            ${sheetMain.bandWidth}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 专线数量 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.lineNum"/>
                    </td>
                    <td class="content">
                            ${sheetMain.lineNum}
                    </td>
                    <td class="label">
                        <!-- A端地市 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.atypearea"/>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${sheetMain.atypearea}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- Z端地市 -->
                        <c:forTokens items="${sheetMain.ztypearea}" delims="," var="ztypearea"
                                     varStatus="status"><eoms:id2nameDB id="${ztypearea}"
                                                                        beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                                test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${sheetMain.ztypearea}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                    <td class="label">
                        <!-- 客户服务等级 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.customGrade"/>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${sheetMain.customGrade}" beanId="ItawSystemDictTypeDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 任务描述 -->
                        任务描述
                    </td>
                    <td class="content">
                            ${sheetMain.mainTaskDescription}
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 资源确认汇总表 -->
                        <bean:message bundle="citycustom" key="cityCustomMain.resourceReport"/>
                    </td>
                    <td>
                        <eoms:attachment name="sheetMain" property="resourceReport" scope="request"
                                         idField="resourceReport" appCode="citycustom" viewFlag="Y"/>

                    </td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
                    <td colspan='3'>
                        <eoms:attachment name="sheetMain" property="sheetAccessories"
                                         scope="request" idField="sheetAccessories" appCode="citycustom"
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
<!-- 订单审核 -->
<c:url var="urlShowOrderExamine102Page" value="citycustom.do">
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
<!-- 订单审核 -->
<c:url var="urlShowOrderExamine117Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="117"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 网管订单审核 -->
<c:url var="urlShowNetOrderExamine103Page" value="citycustom.do">
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
<!-- 网管订单审核 -->
<c:url var="urlShowNetOrderExamine118Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="118"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 方案设计 -->
<c:url var="urlShowSchemeDesign104Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="104"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 提交审核 -->
<c:url var="urlShowSubmitExamine105Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="105"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 提交审核 -->
<c:url var="urlShowSubmitExamine115Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="115"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 审核方案 -->
<c:url var="urlShowExamineScheme106Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="106"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 审核方案 -->
<c:url var="urlShowExamineScheme113Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="113"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 施工 -->
<c:url var="urlShowConstruction107Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="107"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 提交验收 -->
<c:url var="urlShowSubmitCheck108Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="108"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 提交验收 -->
<c:url var="urlShowSubmitCheck116Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="116"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 验收审核 -->
<c:url var="urlShowCheckExamine109Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="109"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 验收审核 -->
<c:url var="urlShowCheckExamine114Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="114"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 开通完成 -->
<c:url var="urlShowOpenComplete110Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="110"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 回复建单人 -->
<c:url var="urlShowAffirmHumTask111Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="111"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 回复建单人 -->
<c:url var="urlShowAffirmHumTask112Page" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="112"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 归档 -->
<c:url var="urlShowHoldTask18Page" value="citycustom.do">
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
<c:url var="urlShowRejectDealPage" value="citycustom.do">
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
<c:url var="urlShowTransferkPage" value="citycustom.do">
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
<c:url var="urlShowPhaseReplyPage" value="citycustom.do">
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
<c:url var="urlShowInputSplit" value="citycustom.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="CityCustomSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="operateType" value="10"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>

<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="citycustom.do">
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
<c:url var="urlShowDealReplyRejectPage" value="citycustom.do">
    <c:param name="method" value="showDealReplyRejectPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="CityCustomSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
</c:url>
<!-- 驳回上一级 -->
<c:url var="urlShowRejectBackPage" value="citycustom.do">
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
<c:url var="urlShowAcceptDealPage" value="citycustom.do">
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
<c:url var="urlBackDealPage" value="citycustom.do">
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
<c:url var="urlShowDraftPage" value="citycustom.do">
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
<c:url var="urlShowDispatchPage" value="citycustom.do">
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
<c:url var="urlShowInputSplitAudit" value="citycustom.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="CityCustomSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="30"/>
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="citycustom.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="CityCustomSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="55"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>

<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="citycustom.do">
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
            url = "citycustom.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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

                    <!-- 订单审核 -->
                    <% if (taskName.equals("OrderExamine")) { %>
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
                        <option value="${urlShowOrderExamine102Page}"><bean:message bundle="citycustom"
                                                                                    key="operateType.OrderExamine102"/></option>
                        <option value="${urlShowOrderExamine117Page}">审核不通过</option>

                        <!--<option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>-->
                        <!--<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>-->
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
                                                                             key="operateType.subTaskReply"/></option>
                        <% }%>
                        <% } %>
                        <!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>

                        <%}%>

                    </select>
                    <!-- 网管订单审核 -->
                    <% }
                        if (taskName.equals("NetOrderExamine")) { %>
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
                        <option value="${urlShowNetOrderExamine103Page}"><bean:message bundle="citycustom"
                                                                                       key="operateType.NetOrderExamine103"/></option>
                        <option value="${urlShowNetOrderExamine118Page}">审核不通过</option>

                        <!--<option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>-->
                        <!--<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>-->
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
                                                                             key="operateType.subTaskReply"/></option>
                        <% }%>
                        <% } %>
                        <!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>

                        <%}%>

                    </select>
                    <!-- 方案设计 -->
                    <% }
                        if (taskName.equals("SchemeDesign")) { %>
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
                        <option value="${urlShowSchemeDesign104Page}"><bean:message bundle="citycustom"
                                                                                    key="operateType.SchemeDesign104"/></option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                    <!-- 提交审核 -->
                    <% }
                        if (taskName.equals("SubmitExamine")) { %>
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
                        <option value="${urlShowSubmitExamine105Page}"><bean:message bundle="citycustom"
                                                                                     key="operateType.SubmitExamine105"/></option>
                        <option value="${urlShowSubmitExamine115Page}">审核不通过</option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                    <!-- 审核方案 -->
                    <% }
                        if (taskName.equals("ExamineScheme")) { %>
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
                        <option value="${urlShowExamineScheme106Page}"><bean:message bundle="citycustom"
                                                                                     key="operateType.ExamineScheme106"/></option>
                        <option value="${urlShowExamineScheme113Page}">审核不通过</option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                    <!-- 施工 -->
                    <% }
                        if (taskName.equals("Construction")) { %>
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
                        <option value="${urlShowConstruction107Page}"><bean:message bundle="citycustom"
                                                                                    key="operateType.Construction107"/></option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                    <!-- 提交验收 -->
                    <% }
                        if (taskName.equals("SubmitCheck")) { %>
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
                        <option value="${urlShowSubmitCheck108Page}"><bean:message bundle="citycustom"
                                                                                   key="operateType.SubmitCheck108"/></option>
                        <option value="${urlShowSubmitCheck116Page}">验收不通过</option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                    <!-- 验收审核 -->
                    <% }
                        if (taskName.equals("CheckExamine")) { %>
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
                        <option value="${urlShowCheckExamine109Page}">验收通过</option>
                        <option value="${urlShowCheckExamine114Page}">验收不通过</option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                    <!-- 开通完成 -->
                    <% }
                        if (taskName.equals("OpenComplete")) { %>
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
                        <option value="${urlShowOpenComplete110Page}">开通完成</option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                    <!-- 回复建单人 -->
                    <% }
                        if (taskName.equals("AffirmHumTask")) { %>
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
                        <option value="${urlShowAffirmHumTask111Page}"><bean:message bundle="citycustom"
                                                                                     key="operateType.AffirmHumTask111"/></option>
                        <option value="${urlShowAffirmHumTask112Page}"><bean:message bundle="citycustom"
                                                                                     key="operateType.AffirmHumTask112"/></option>

                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <% }%>
                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                        <option value="${urlShowHoldTask18Page}"><bean:message bundle="citycustom"
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
                        <option value="${urlShowDispatchPage}"><bean:message bundle="citycustom"
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
                        <option value="${urlShowDraftPage}"><bean:message bundle="citycustom"
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
            url = "citycustom.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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
