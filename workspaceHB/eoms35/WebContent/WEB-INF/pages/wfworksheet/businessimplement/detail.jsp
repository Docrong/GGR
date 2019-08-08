<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain" %>
<%@page import="com.boco.eoms.sheet.businessimplement.model.BusinessImplementTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@ page import="java.util.*" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    BusinessImplementTask task = new BusinessImplementTask();
    String operaterType = "";
    String ifsub = "";
    String ifwaitfor = "";
    if (request.getAttribute("task") != null) {
        task = (BusinessImplementTask) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
        operaterType = task.getOperateType();
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
        ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
    }

    request.setAttribute("operaterType", operaterType);
    BusinessImplementMain basemain = (BusinessImplementMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();
    String startType = basemain.getMainBelongCity();
    String CaiXinOrDuanXin = basemain.getMainBelongZone();
    System.out.println("@CaiXinOrDuanXin=" + CaiXinOrDuanXin);
    System.out.println("@detialstartType=" + startType);
//added by liufuyuan
    String isCalledByLangugage = basemain.getMainBelongPronice();
    System.out.println("@@isCalledByLangugage" + isCalledByLangugage);
//根据类型判断
    String mainSpecifyType = basemain.getMainSpecifyType();
    String specialtyType = mainSpecifyType;
    specialtyType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("specialtyType"));
    System.out.println("@detail.specialtyType=" + specialtyType);
    System.out.println("-----mainSpecifyType--------value=" + mainSpecifyType);
    request.setAttribute("mainSpecifyType", mainSpecifyType);
    String orderSheetId = basemain.getOrderSheetId();
    System.out.println("====orderSheetId====" + orderSheetId);
    request.setAttribute("orderSheetId", orderSheetId);
    request.setAttribute("taskStatus", taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("@@ busi detail===taskName======" + taskName);
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
                url: 'businessimplement.do?method=showSheetDealList&orderByDetp=true&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                url: 'businessimplement.do?method=showWorkFlow&linkServiceName=iBusinessImplementLinkManager&dictSheetName=dict-sheet-businessimplement&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                url: 'businessimplement.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "businessimplement.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "businessimplement.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "businessimplement.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {

            var url2 = "businessimplement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);

        } else if (obj == 2) {

            var url2 = "businessimplement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "businessimplement.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "businessimplement.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
            <%@ include file="/WEB-INF/pages/wfworksheet/businessimplement/basedetailnew.jsp" %>
            <%

                System.out.println("detail页面:" + orderSheetId + mainSpecifyType);
                if (orderSheetId != null && !"".equals(orderSheetId)) { %>
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
                            <eoms:id2nameDB id="${orderSheet.groupCustomBusinessScope}" beanId="ItawSystemDictTypeDao"/>
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
                            <bean:write name="sheetMain" property="mainResourceAffCRMNumber" scope="request"/>
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
            <%} %>
            <%
                if (orderSheetId != null && !"".equals(orderSheetId)) {
                    System.out.println("--22--" + orderSheetId + isCalledByLangugage + startType);
            %>

            <%
                if ("yes".equals(isCalledByLangugage)) {
                    System.out.println("1----" + orderSheetId + isCalledByLangugage + startType);
            %>
            <iframe id="frame"
                    src="${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=${orderSheetId}&taskStatus=${taskStatus}&specialtyType=yuyin&sheetType=businessImplement&taskName=${taskName}"
                    width="100%" height="300px"></iframe>

            <%
            } else if (("startTranfer").equals(startType)) {
                System.out.println("-2---" + orderSheetId + isCalledByLangugage + startType);
            %>
            <iframe id="frame"
                    src="${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=${orderSheetId}&taskStatus=${taskStatus}&specialtyType=<%=CaiXinOrDuanXin %>&sheetType=businessImplement&taskName=${taskName}"
                    width="100%" height="300px"></iframe>
            <%
            } else if (("startIp").equals(startType)) {
                System.out.println("-3---" + orderSheetId + isCalledByLangugage + startType);
            %>

            <iframe id="frame"
                    src="${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=${orderSheetId}&taskStatus=${taskStatus}&specialtyType=<%=CaiXinOrDuanXin %>&sheetType=businessImplement&taskName=${taskName}"
                    width="100%" height="300px"></iframe>

            <%
            } else {
                System.out.println("--4--" + orderSheetId + isCalledByLangugage + startType);
            %>
            <iframe id="frame"
                    src="${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=${orderSheetId}&taskStatus=${taskStatus}&specialtyType=${sheetMain.mainSpecifyType}&sheetType=businessImplement&taskName=${taskName}"
                    width="100%" height="300px"></iframe>
            <%} %>
            <%}%>


        </logic:present>
    </div>
</div>
<%if (taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")) {%>
<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="businessimplement.do">
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
<!--派现场施工单 -->
<c:url var="urlShowImplementDealTaskDealPage" value="businessimplement.do">
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
<!-- 一级审批提交验证 -->
<c:url var="urlShowOneToValidateTaskDealPage" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="912"/>
</c:url>
<!-- 最后施工一公里提交-->
<c:url var="urlShowSendToProvinceTaskDealPage" value="businessimplement.do">
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
<!-- 光纤设备提交-->
<c:url var="urlShowSendToFDDITaskDealPage" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="930"/>
</c:url>
<!-- 二级审批提交验证 -->
<c:url var="urlShowTwoToValidateTaskDealPage" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="922"/>
</c:url>
<!-- 提交传输-->
<c:url var="urlShowProvinceToValidateTaskDealPage" value="businessimplement.do">
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
<!-- apn提交业务测试 -->
<c:url var="urlShowSendToBillTaskDealPage" value="businessimplement.do">
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
<!--传输电路 提交业务测试 -->
<c:url var="urlShowSendToBillTaskDealPage2" value="businessimplement.do">
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
<!-- 验证结果反馈 -->
<c:url var="urlShowValidateToHoldTaskDealPage" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="942"/>
</c:url>
<!-- 提交归档-->
<c:url var="urlShowBillToHoldTaskDealPage" value="businessimplement.do">
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


<!--任务分派-->
<c:url var="urlShowInputSplit" value="businessimplement.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="BusinessImplementSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="operateType" value="10"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>

<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="businessimplement.do">
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
<c:url var="urlShowDealReplyAcceptPage" value="businessimplement.do">
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
<c:url var="urlShowDealReplyRejectPage" value="businessimplement.do">
    <c:param name="method" value="showDealReplyRejectPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="BusinessImplementSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
</c:url>


<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="businessimplement.do">
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
<c:url var="urlShowCompleteDraftPage" value="businessimplement.do">
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
<c:url var="urlShowBackDeal" value="businessimplement.do">
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
<c:url var="urlShowReSendDeal" value="businessimplement.do">
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
<c:url var="urlShowRejectBackPage" value="businessimplement.do">
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
<c:url var="urlShowPhaseReplyPage" value="businessimplement.do">
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

<c:url var="urlShowAcceptRejectTaskDealPage" value="businessimplement.do">
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
<c:url var="urlShowTransferkPage" value="businessimplement.do">
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
<!-- add by yangwei -->
<!-- 修改设计方案完成 -->
<c:url var="urlShowModifyToAuditTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="72"/>
</c:url>
<!-- 审核不通过 -->
<c:url var="urlShowNOAuditTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="73"/>
</c:url>
<!-- 一派七个环节 -->
<!-- 城域网配置反馈 -->
<c:url var="urlShowCityNetToBusinessTestTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="741"/>
</c:url>
<!-- APN资源反馈 -->
<c:url var="urlShowApnToBusinessTestTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="742"/>
</c:url>
<!-- 传输电路调度反馈 -->
<c:url var="urlShowTrasitionToBusinessTestTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="743"/>
</c:url>
<!-- 包含PON配置反馈 -->
<c:url var="urlShowPONConfigToBusinessTestTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="744"/>
</c:url>
<!-- GGSN反馈 -->
<c:url var="urlShowGGSNToBusinessTestTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="745"/>
</c:url>
<c:url var="urlShowHLRTaskToBusinessTestTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="748"/>
</c:url>
<!-- 光纤调度反馈 -->
<c:url var="urlShowFDDIToBusinessTestTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="746"/>
</c:url>
<!-- 客户端勘查反馈 -->
<c:url var="urlShowClientToBusinessTestTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="747"/>
</c:url>
<!-- 网络端到端测试 -->
<!-- 业务测试完成 -->
<c:url var="urlShowBusinessTestToDredgeAffirmTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="75"/>
</c:url>
<!-- 开通确认完成 -->
<c:url var="urlShowDredgeAffirmToHoldTask" value="businessimplement.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="76"/>
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
                    <%if (taskName.equals("ImplementDealTask")) { %>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) {%>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowImplementDealTaskDealPage}">定单审核通过</option>

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
                        <%}%>
                    </select>
                    <%} else if (taskName.equals("ProjectDealTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>--%>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowSendToProvinceTaskDealPage}">最后施工一公里提交</option>

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
                    <%} else if (taskName.equals("FDDITask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%-- <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>--%>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowSendToFDDITaskDealPage}">光纤设备提交</option>

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
                    <%} else if (taskName.equals("ModifyDesignTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowModifyToAuditTask}">修改设计方案完成</option>
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
                    <%} else if (taskName.equals("AuditDesignTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowNOAuditTask}">审核完成</option>
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
                    <!-- 七个开始反馈 -->
                    <!-- CityNetTask -->
                    <%} else if (taskName.equals("CityNetTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>

                        <!--  <option value="${urlShowCityNetToBusinessTestTask}">城域网配置反馈</option>-->
                        <option value="${ urlShowProvinceToValidateTaskDealPage}">提交电路调度</option>
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
                    <!-- ApnTask -->
                    <%} else if (taskName.equals("ApnTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowApnToBusinessTestTask}">APN资源反馈</option>
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
                    <!-- TrasitionTask -->
                    <%} else if (taskName.equals("TrasitionTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <!-- <option value="${urlShowTrasitionToBusinessTestTask}">传输电路调度反馈</option> -->
                        <option value="${urlShowSendToBillTaskDealPage2}">提交网络端到端测试</option>
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
                    <!-- PONConfigTask -->
                    <%} else if (taskName.equals("PONConfigTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowPONConfigToBusinessTestTask}">包含PON配置反馈</option>
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
                    <!-- GGSNTask -->
                    <%} else if (taskName.equals("GGSNTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowGGSNToBusinessTestTask}">GGSN反馈</option>
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
                    <%} else if (taskName.equals("HLRTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowHLRTaskToBusinessTestTask}">HLR反馈</option>
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
                    <!-- FDDITask -->
                    <%} else if (taskName.equals("FDDITask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowFDDIToBusinessTestTask}">光纤调度反馈</option>
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
                    <!-- ClientTask -->
                    <%} else if (taskName.equals("ClientTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowClientToBusinessTestTask}">客户端勘查反馈</option>
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
                    <!-- BusinessTestTask -->
                    <%} else if (taskName.equals("BusinessTestTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowBusinessTestToDredgeAffirmTask}">业务测试完成</option>
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
                    <!-- DredgeAffirmTask -->
                    <%} else if (taskName.equals("DredgeAffirmTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) { %>
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDredgeAffirmToHoldTask}">开通确认完成</option>
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
                        <option value="${urlShowCompleteDraftPage}"><bean:message bundle="businessimplement"
                                                                                  key="operateType.draft"/></option>
                    </select>
                    <%} else if (taskName.equals("RejectTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowReSendDeal}"><bean:message bundle="sheet" key="common.reSend"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="businessimplement"
                                                                             key="operateType.hold"/></option>
                        <!--    <option value="${urlShowBackDeal}">退回验证</option>-->
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
            url = "businessimplement.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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
