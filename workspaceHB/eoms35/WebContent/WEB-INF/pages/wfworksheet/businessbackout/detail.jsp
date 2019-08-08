<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.businessbackout.task.impl.BusinessBackoutTaskImpl" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    BusinessBackoutTaskImpl task = new BusinessBackoutTaskImpl();
    String operaterType = "";
    if (request.getAttribute("task") != null) {
        task = (BusinessBackoutTaskImpl) request.getAttribute("task");
        taskStatus = task.getTaskStatus();
        operaterType = task.getOperateType();
    }

    request.setAttribute("operaterType", operaterType);
    BaseMain basemain = (BaseMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();

    request.setAttribute("taskStatus", taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String ifInvokeUrgentFault = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvokeUrgentFault"));
%>
<%
    System.out.println("+++++++++++taskName:" + taskName);
    System.out.println("+++++++++++operateType:" + operaterType);
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
                url: 'businessbackout.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            },
                //{
                //	text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                //url : '/ProcessMonitor/runtime/html/index.jsp?appName=businessbackoutProcessApp&templateName=businessbackoutMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
                //	url : 'businessbackout.do?method=showPic',
                //	isIframe : true
                //},
                {
                    text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                    url: 'businessbackout.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "businessbackout.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "businessbackout.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "businessbackout.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {
            var url2 = "businessbackout.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else if (obj == 2) {
            var url2 = "businessbackout.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {
            var url2 = "businessbackout.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "businessbackout.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
            <%@ include file="/WEB-INF/pages/wfworksheet/businessbackout/basedetailnew.jsp" %>
            <br/>
            <table class="formTable">
                <tr>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.btype1"/></td>
                    <td><eoms:id2nameDB id="${sheetMain.btype1}" beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.urgentDegree"/></td>
                    <td><eoms:id2nameDB id="${sheetMain.urgentDegree}" beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessbackout"
                                                    key="businessbackout.sheetAcceptLimit"/></td>
                    <td><bean:write name="sheetMain" property="sheetAcceptLimit"/></td>
                    <td class="label"><bean:message bundle="businessbackout"
                                                    key="businessbackout.sheetCompleteLimit"/></td>
                    <td><bean:write name="sheetMain" property="sheetCompleteLimit"/></td>

                <tr>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.bdeptContact"/></td>
                    <td>
                            <%-- <eoms:id2nameDB id="${sheetMain.bdeptContact}" beanId="ItawSystemDictTypeDao"/> --%>
                        <bean:write name="sheetMain" property="bdeptContact"/></td>
                    <td class="label"><bean:message bundle="businessbackout"
                                                    key="businessbackout.bdeptContactPhone"/></td>
                    <td><bean:write name="sheetMain" property="bdeptContactPhone"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.customNo"/></td>
                    <td><bean:write name="sheetMain" property="customNo"/></td>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.customName"/></td>
                    <td><bean:write name="sheetMain" property="customName"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.customContact"/></td>
                    <td><bean:write name="sheetMain" property="customContact"/></td>
                    <td class="label"><bean:message bundle="businessbackout"
                                                    key="businessbackout.customContactPhone"/></td>
                    <td><bean:write name="sheetMain" property="customContactPhone"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.cityName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cityName" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.countyName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="countyName" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.cmanagerPhone"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cmanagerPhone" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessbackout"
                                                    key="businessbackout.cmanagerContactPhone"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cmanagerContactPhone"
                                                    scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.customLevel"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.customLevel}"
                                                        beanId="ItawSystemDictTypeDao"/></td>

                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.productName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="productName" scope="request"/></td>
                </tr>
                <tr>

                    <td class="label"><bean:message bundle="businessbackout" key="businessbackout.provinceName"/></td>
                    <td class="content" colspan='3'><bean:write name="sheetMain" property="provinceName"
                                                                scope="request"/></td>
                </tr>
                <logic:equal value="101100101" name="sheetMain" property="btype1">
                    <tr>

                        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.APNName"/></td>
                        <td colspan='3'><bean:write name="sheetMain" property="apnName"/></td>
                    </tr>
                    <tr>
                        <td class="label"><bean:message bundle="businessbackout"
                                                        key="businessbackout.backoutCause"/></td>
                        <td class="content" colspan="3">
                            <pre><bean:write name="sheetMain" property="backoutCause"/></pre>
                            </textarea>
                        </td>
                    </tr>
                </logic:equal>
                <logic:equal value="101100102" name="sheetMain" property="btype1">
                    <tr>
                        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.bcode"/></td>
                        <td><bean:write name="sheetMain" property="bcode"/></td>
                        <td class="label"><bean:message bundle="businessbackout"
                                                        key="businessbackout.enterpriseCode"/></td>
                        <td><bean:write name="sheetMain" property="enterpriseCode"/></td>
                    </tr>
                    <tr>
                        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.SIName"/></td>
                        <td><bean:write name="sheetMain" property="siName"/></td>
                        <td class="label"><bean:message bundle="businessbackout"
                                                        key="businessbackout.SIEnterpriseCode"/></td>
                        <td><bean:write name="sheetMain" property="siEnterpriseCode"/></td>
                    </tr>
                    <tr>
                        <td class="label"><bean:message bundle="businessbackout"
                                                        key="businessbackout.SIServerCode"/></td>
                        <td colspan='3'><bean:write name="sheetMain" property="siServerCode"/></td>

                    </tr>
                </logic:equal>
                <logic:equal value="101100103" name="sheetMain" property="btype1">
                    <tr>
                        <td class="label"><bean:message bundle="businessbackout"
                                                        key="businessbackout.urgentDegree"/></td>
                        <td colspan='3'><eoms:id2nameDB id="${sheetMain.urgentDegree}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    </tr>
                    <tr>
                        <td class="label"><bean:message bundle="businessbackout"
                                                        key="businessbackout.enterpriseCode"/></td>
                        <td><bean:write name="sheetMain" property="enterpriseCode"/></td>
                        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.serverCode"/></td>
                        <td><bean:write name="sheetMain" property="serverCode"/></td>
                    </tr>
                </logic:equal>
                <logic:equal value="101100104" name="sheetMain" property="btype1">
                    <tr>

                        <td class="label"><bean:message bundle="businessbackout"
                                                        key="businessbackout.circuitCode"/></td>
                        <td colspan='3'><bean:write name="sheetMain" property="circuitCode"/></td>
                    </tr>
                    <tr>
                        <td class="label"><bean:message bundle="businessbackout"
                                                        key="businessbackout.backoutCause"/></td>
                        <td class="content" colspan="3">
                            <pre><bean:write name="sheetMain" property="backoutCause"/></pre>
                            </textarea>
                        </td>
                    </tr>
                </logic:equal>
                <tr>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
                    <td colspan="3"><eoms:attachment name="sheetMain" property="sheetAccessories"
                                                     scope="request" idField="sheetAccessories" appCode="commonfault"
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

<!-- 审批通过 -->
<c:url var="urlShowNewAuditPassPage" value="businessbackout.do">
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
<!-- 审批不通过 -->
<c:url var="urlShowNewAuditUnpassPage" value="businessbackout.do">
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
<!-- 处理完成无需审批 -->
<c:url var="urlShowCompleteDealPage" value="businessbackout.do">
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
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 草稿 -->
<c:url var="urlShowCompleteDraftPage" value="businessbackout.do">
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
<!-- 执行完成送审 -->
<c:url var="urlShowPassDealPage" value="businessbackout.do">
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
<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="businessbackout.do">
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
<!-- 转派 -->
<c:url var="urlShowTransmitDealPage" value="businessbackout.do">
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
<!-- 移交 -->
<c:url var="urlShowExamineYesDealPage" value="businessbackout.do">
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
<!-- 任务完成审批通过 -->
<c:url var="urlShowTaskCompleteAuditYes" value="businessbackout.do">
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
<!-- 任务完成审批不通过 -->
<c:url var="urlShowTaskCompleteAuditNo" value="businessbackout.do">
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

<c:url var="urlShowTransferkPage" value="businessbackout.do">
    <c:param name="method" value="showTransferWorkItemPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<!--处理回复不通过退回 -->
<c:url var="urlShowExamineDealPage" value="businessbackout.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
</c:url>
<!--处理回复通过 -->
<c:url var="urlShowExamineNODealPage" value="businessbackout.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="6"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
</c:url>
<!--阶段回复 -->
<c:url var="urlShowPhaseReplyPage" value="businessbackout.do">
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
<c:url var="urlShowInputSplit1" value="businessbackout.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="firstSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowInputSplit2" value="businessbackout.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="secondSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowInputSplit3" value="businessbackout.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="thirdSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<c:url var="urlShowInputSplit4" value="businessbackout.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="fourSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
</c:url>
<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="businessbackout.do">
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
<!-- 接单确认 -->
<c:url var="urlShowAcceptDealPage" value="businessbackout.do">
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
<!-- 启动网络配置流程 -->
<c:url var="urlShowDispatchDealPage" value="businessbackout.do">
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
</c:url>
<!-- 重派 -->
<c:url var="urlShowRejectPage" value="businessbackout.do">
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
</c:url>
<div class="sheet-deal">
    <div class='sheet-deal-header'>
        <table>
            <tr>
                <td width="50%">
                    <%if (!taskName.equals("cc") && !taskName.equals("reply") && !taskName.equals("advice")) {%>
                    <bean:message bundle="sheet" key="sheet.deal"/>:
                    <%}%>

                    <%if (taskName.equals("TaskCreateAuditHumTask")) { %>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowNewAuditPassPage}"><bean:message bundle="businessbackout"
                                                                                 key="businessbackout.taskpass"/></option>
                        <option value="${urlShowNewAuditUnpassPage}"><bean:message bundle="businessbackout"
                                                                                   key="businessbackout.tasknopass"/></option>
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
                        <option value="${urlShowTaskCompleteAuditYes}"><bean:message bundle="businessbackout"
                                                                                     key="businessbackout.TaskCompleteAuditYes"/></option>
                        <option value="${urlShowTaskCompleteAuditNo}"><bean:message bundle="businessbackout"
                                                                                    key="businessbackout.TaskCompleteAuditNo"/></option>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("ExcuteHumTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowTransmitDealPage}"><bean:message bundle="sheet"
                                                                                 key="common.split"/></option>
                        <option value="${urlShowDispatchDealPage}"><bean:message bundle="sheet"
                                                                                 key="common.dispatchType"/></option>
                        <option value="${urlShowCompleteDealPage}"><bean:message bundle="businessbackout"
                                                                                 key="businessbackout.dealwcnosp"/></option>
                        <option value="${urlShowPassDealPage}"><bean:message bundle="businessbackout"
                                                                             key="businessbackout.totaskcompsq"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <!--<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
			    <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>	
	  		   <option value="${urlShowInputSplit1}"><bean:message bundle="sheet" key="common.split"/></option>-->
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("AffirmHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowExamineNODealPage}"><bean:message bundle="businessbackout"
                                                                                  key="businessbackout.dealrejectpass"/></option>
                        <option value="${urlShowExamineDealPage}"><bean:message bundle="businessbackout"
                                                                                key="businessbackout.nopassback"/></option>
                    </select>
                    <%} else if (taskName.equals("DraftHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowCompleteDraftPage}"><bean:message bundle="businessbackout"
                                                                                  key="operateType.send"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldHumTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="businessbackout"
                                                                             key="operateType.hold"/></option>
                    </select>
                    <%} else if (taskName.equals("ByRejectHumTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowRejectPage}"><bean:message bundle="sheet" key="common.reSend"/></option>
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
            url = "businessbackout.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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
        <input type="button" title="${eoms:a2u('执行该操作，工单将进入强制归档状态，其他人不能在处理工单')}"
               value="<bean:message bundle="sheet" key="button.forceHold"/>"
               onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
        <input type="button" title="${eoms:a2u('执行该操作，工单将进入强制作废状态，其他人不能在处理工单')}"
               value="<bean:message bundle="sheet" key="button.forceNullity"/>"
               onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
    <% } else if ((taskStatus == null || taskStatus.equals("")) && (userId.equals(sendUserId))) {%>
    <div id="advice" style="display:block">
        <input type="button" title="${eoms:a2u('执行该操作，工单将进入作废状态，其他人不能在处理工单')}"
               value="<bean:message bundle="sheet" key="button.nullity"/>"
               onclick="$('advice').style.display='none';forceOperation(2);">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('advice').style.display='none';eventOperation(1);">
    </div>
    <% }%>
</c:if>
<%@ include file="/common/footer_eoms.jsp" %>
