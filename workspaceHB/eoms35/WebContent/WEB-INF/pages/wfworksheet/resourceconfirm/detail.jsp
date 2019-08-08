<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmMain" %>
<%@page import="com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="com.boco.eoms.businessupport.product.model.TrancePoint" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    ResourceConfirmTask task = new ResourceConfirmTask();
    String operaterType = "";
    String ifsub = "";
    String ifwaitfor = "";
    if (request.getAttribute("task") != null) {
        task = (ResourceConfirmTask) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
        operaterType = task.getOperateType();
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
        ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
    }

    request.setAttribute("operaterType", operaterType);
    ResourceConfirmMain basemain = (ResourceConfirmMain) request.getAttribute("sheetMain");
    String mainSpecifyType = basemain.getMainSpecifyType();
    String sendUserId = basemain.getSendUserId();
    String orderSheetId = basemain.getOrderSheetId();
    System.out.println("====orderSheetId====" + orderSheetId);
    request.setAttribute("orderSheetId", orderSheetId);
    request.setAttribute("taskStatus", taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));

    String addr = "";
    Object trancPoint = request.getSession().getAttribute("support");
    if (trancPoint != null)
        addr = ((TrancePoint) trancPoint).getPortDetailAdd();

%>
<%@page import="com.ibm.ws.webservices.xml.waswebservices.transport" %>
<%@page import="com.boco.eoms.businessupport.product.model.TrancePoint" %>
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
                url: 'resourceconfirm.do?method=showSheetDealList&orderByDetp=true&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                url: 'resourceconfirm.do?method=showWorkFlow&linkServiceName=iResourceConfirmLinkManager&dictSheetName=dict-sheet-resourceconfirm&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                url: 'resourceconfirm.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "resourceconfirm.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "resourceconfirm.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "resourceconfirm.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>
    });

    function forceOperation(obj) {

        if (obj == 1) {

            var url2 = "resourceconfirm.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);

        } else if (obj == 2) {

            var url2 = "resourceconfirm.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "resourceconfirm.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "resourceconfirm.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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

    function handleCallBackInit(originalRequest) {
        var urls = originalRequest.responseText;

        obj = document.all["tabiframe"];
        obj.src = urls;

    }
</script>

<h3 class="sheet-title">

</h3>

<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
    <!-- Sheet Detail Tab Start -->
    <div id="sheetinfo">
        <logic:present name="sheetMain" scope="request">
            <%@ include file="/WEB-INF/pages/wfworksheet/resourceconfirm/basedetailnew.jsp" %>
            <% if (orderSheetId != null && !"".equals(orderSheetId)) { %>
            <br>
            <fieldset>
                <legend>集团客户信息</legend>
                <table class="formTable">
                    <!-- added by liufuyuan -->
                    <tr>
                        <td class="label">集团客户名称</td>
                        <td>
                            <html:errors property="customName"/>
                            <bean:write name="orderSheet" property="customName" scope="request"/>
                        </td>
                        <td class="label">集团级别</td>
                        <td class="content"><eoms:id2nameDB id="${orderSheet.customLevel}"
                                                            beanId="ItawSystemDictTypeDao"/></td>
                    </tr>
                    <tr>
                        <td class="label">集团客户编号</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="groupCustomNo" scope="request"/>
                        </td>
                        <td class="label">集团客户行业</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="groupCustomBusinesType" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">集团客户联系地址</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="groupCustomAdd" scope="request"/>
                        </td>
                        <td class="label">集团客户联系邮编</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="groupCustomMail" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">集团客户行业</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="groupCustomBusinesType" scope="request"/>
                        </td>
                        <td class="label">集团客户业务范围</td>
                        <td class="content">
                            <eoms:id2nameDB id="${sheetMain.mainSpecifyType}" beanId="ItawSystemDictTypeDao"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <br>
            <fieldset>
                <legend>客户业务联系人信息</legend>
                <table class="formTable">
                    <tr>
                        <td class="label">集团客户联系人</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="customContact" scope="request"/>
                        </td>
                        <td class="label">集团客户联系电话</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="customContactPhone" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">集团客户联系邮编</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="groupCustomMail" scope="request"/>
                        </td>
                        <td class="label">集团客户联系地址</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="groupCustomAdd" scope="request"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <br>
            <fieldset>
                <legend>客户业务联系人信息</legend>
                <table class="formTable">
                    <tr>
                        <td class="label">客户名称</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="customName" scope="request"/>
                        </td>
                        <td class="label">客户编号</td>
                        <td class="content">
                            <html:errors property="customNo"/>
                            <bean:write name="orderSheet" property="customNo" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">客户经理名称*</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="cmanagerPhone" scope="request"/>
                        </td>
                        <td class="label">客户经理联系电话*</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="cmanagerContactPhone" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">客户联系邮箱</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="customManagerMail" scope="request"/>
                        </td>
                        <td class="label">客户经理部门</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="customManagerDept" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">所属省份</td>
                        <td class="content">
                            <html:errors property="provinceName"/>
                            <bean:write name="orderSheet" property="provinceName" scope="request"/>
                        </td>
                        <td class="label">所属地市</td>
                        <td class="content">
                            <html:errors property="cityName"/>
                            <bean:write name="orderSheet" property="cityName" scope="request"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <br>
            <fieldset>
                <legend>客户业务联系人信息</legend>
                <table class="formTable">
                    <tr>
                        <td class="label">产品名称</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="productName" scope="request"/>
                        </td>
                        <td class="label">CRM侧工单号</td>
                        <td class="content">
                            <bean:write name="orderSheet" property="mainProductInstanceCode" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">拟生效时间</td>
                        <td class="content" colspan="3">
                            <bean:write name="orderSheet" property="planExecuteTime" scope="request"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <br>
            <fieldset>
                <legend>申请业务信息</legend>
                <table class="formTable">
                    <tr>
                        <td class="label">工单受理时限</td>
                        <td class="content">
                            <bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll"
                                        bundle="sheet" scope="request"/>
                        </td>
                        <td class="label">工单处理时限</td>
                        <td class="content">
                            <bean:write name="sheetMain" property="sheetCompleteLimit"
                                        formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">专线类型*</td>
                        <td class="content">
                            <eoms:id2nameDB id="${sheetMain.mainSpecifyType}" beanId="ItawSystemDictTypeDao"/>
                        </td>
                        <td class="label">紧急程度*</td>
                        <td class="content">
                            <eoms:id2nameDB id="${sheetMain.mainArgument}" beanId="ItawSystemDictTypeDao"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">业务需求描述</td>
                        <td colspan="3">
                            <bean:write name="orderSheet" property="brequirementDesc" scope="request"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <br>
            <iframe id="frame"
                    src="${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=${orderSheetId}&specialtyType=${sheetMain.mainSpecifyType}&sheetType=resourceConfirm&taskName=${taskName}&taskStatus=${taskStatus}&addr=<%=addr%>"
                    width="100%" height="300px"></iframe>

            <%} %>

        </logic:present>
    </div>
    <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->
<%if (taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")) {%>
<!-- 资源确认受理 -->
<c:url var="urlShowAcceptTaskDealPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="200"/>
</c:url>
<!-- 现场勘查 -->
<c:url var="urlShowExplorateDealPage" value="resourceconfirm.do">
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
<!-- 用户端资源勘查反馈 -->
<c:url var="urlShowUserDealPage" value="resourceconfirm.do">
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
<!-- 接入点资源勘查反馈 -->
<c:url var="urlShowAccessDealPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="203"/>
</c:url>
<!-- 城域网端口查询反馈 -->
<c:url var="urlShowCityDealPage" value="resourceconfirm.do">
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
<!-- 传输设备查勘 -->
<c:url var="urlShowTransfereTaskDealPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="207"/>
</c:url>
<!-- 传输线路查勘 -->
<c:url var="urlShowTransferlTaskDealPage" value="resourceconfirm.do">
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
<!-- LauguageTask -->
<c:url var="urlShowLauguageTaskDealPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="210"/>
</c:url>

<c:url var="urlShowCaiXinTaskDealPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="220"/>
</c:url>
<!-- 组网方案制作 -->
<c:url var="urlShowMakeDealPage" value="resourceconfirm.do">
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
<!-- 方案审核 -->
<c:url var="urlShowAuditTaskDealPage" value="resourceconfirm.do">
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
<!-- 处理回复 -->
<c:url var="urlShowHandleDealPage" value="resourceconfirm.do">
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

<!-- 草稿派发 -->
<c:url var="urlShowDraftDeal" value="resourceconfirm.do">
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

<!-- 重新派发 -->
<c:url var="urlShowBackDeal" value="resourceconfirm.do">
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

<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="resourceconfirm.do">
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
<c:url var="urlShowRejectBackDealPage" value="resourceconfirm.do">
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

<!--移交-->
<c:url var="urlShowTransferkPage" value="resourceconfirm.do">
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
<c:url var="urlShowTransferAuditPage" value="resourceconfirm.do">
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

<!-- 阶段回复 -->
<c:url var="urlShowPhaseReplyPage" value="resourceconfirm.do">
    <c:param name="method" value="showPhaseBackToUpPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="reply"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operaterType" value="${operaterType}"/>
    <c:param name="displayTaskName" value="${taskName}"/>
</c:url>

<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="resourceconfirm.do">
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
<c:url var="urlShowAcceptDealPage" value="resourceconfirm.do">
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

<!-- 分派 -->
<c:url var="urlShowInputSplit" value="resourceconfirm.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="ResourceConfirmSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="10"/>
</c:url>

<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="resourceconfirm.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="30"/>
</c:url>

<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="11"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="55"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealReplyAcceptPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="6"/>
</c:url>
<!-- 处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="resourceconfirm.do">
    <c:param name="method" value="showDealReplyRejectPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
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
                    <% if (taskName.equals("AcceptTask")) {%>
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
                        <option value="${urlShowAcceptTaskDealPage}">资源确认受理</option>
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
                    <%} else if (taskName.equals("ExplorateTask")) {%>
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
                        <option value="${urlShowExplorateDealPage}">现场勘查</option>
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
                    <%} else if (taskName.equals("UserTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option> -->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowUserDealPage}">用户端资源勘查反馈</option>
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
                    <%} else if (taskName.equals("AccessTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!--  <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>-->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowAccessDealPage}">接入点资源勘查反馈</option>
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
                    <%} else if (taskName.equals("CityTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option> -->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>

                        <option value="${urlShowCityDealPage}">城域网端口查询反馈</option>

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
                    <%} else if (taskName.equals("TransfereTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option> -->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowTransfereTaskDealPage}">传输设备查勘</option>
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
                    <%} else if (taskName.equals("TransferlTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option> -->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowTransferlTaskDealPage}">传输线路查勘</option>
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
                    <%} else if (taskName.equals("LauguageTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option> -->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowLauguageTaskDealPage}">语音专线查勘查询反馈</option>
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
                    <%} else if (taskName.equals("CaiXinTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option> -->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowCaiXinTaskDealPage}">行业网关接入勘察反馈</option>
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
                    <%} else if (taskName.equals("MakeTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>-->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>

                        <option value="${urlShowMakeDealPage}">组网方案制作</option>
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
                    <%} else if (taskName.equals("AuditTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <!-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>-->
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>

                        <option value="${urlShowAuditTaskDealPage}">方案审核</option>
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
                    <%} else if (taskName.equals("HandleTask")) {%>
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
                        <option value="${urlShowHandleDealPage}">处理回复</option>
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
                    <%} else if (taskName.equals("DraftTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowDraftDeal}"><bean:message bundle="resourceconfirm"
                                                                          key="operateType.draft"/></option>
                    </select>
                    <%} else if (taskName.equals("RejectTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowBackDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="resourceconfirm"
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
            url = "resourceconfirm.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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
