<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.businessdredgebroad.model.BusinessDredgebroadMain" %>
<%@page import="com.boco.eoms.sheet.businessdredgebroad.task.impl.BusinessDredgebroadTaskImpl" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.service.ILinkService" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getAttribute("taskStatus"));

    if (taskStatus.equals("")) {
        taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
        System.out.println("taskStatus taskStatus taskStatus=" + taskStatus);
    }
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    BusinessDredgebroadTaskImpl task = new BusinessDredgebroadTaskImpl();

    String operaterType = "";
    if (request.getAttribute("task") != null) {
        task = (BusinessDredgebroadTaskImpl) request.getAttribute("task");
        if (task.getTaskStatus().equals("2") || taskStatus.equals("8")) {
            String tat = task.getTaskStatus();
        } else {
            taskStatus = task.getTaskStatus();
        }
        operaterType = task.getOperateType();
    }

    String myPreLinkId = (String) request.getAttribute("preLinkId");
    ILinkService linkManager = (ILinkService) ApplicationContextHolder.getInstance().getBean("iBusinessDredgebroadLinkManager");
    BaseLink myLink = null;
    if (myPreLinkId != null && !"".equals(myPreLinkId)) {
        myLink = linkManager.getSingleLinkPO(myPreLinkId);
    }
    request.setAttribute("operaterType", operaterType);
    BusinessDredgebroadMain basemain = (BusinessDredgebroadMain) request.getAttribute("sheetMain");
    String businesstype1 = basemain.getBusinesstype1();
    String sendUserId = basemain.getSendUserId();
    request.setAttribute("taskStatus", taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String ifInvokeUrgentFault = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvokeUrgentFault"));
    System.out.println("taskname is ================" + taskName + "======operaterType=====" + operaterType);
//add by yangna
    String zxType = com.boco.eoms.base.util.StaticMethod.nullObject2String(basemain.getZxType());
    System.out.println("zxType====" + zxType);
//end by yangna
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
                url: 'businessdredgebroad.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
            },
                //{
                //	text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                //url : '/ProcessMonitor/runtime/html/index.jsp?appName=resourceaffirmProcessApp&templateName=resourceaffirmMainFlowProcess&piid=${sheetMain.piid}&listType=myProcesses&curPage=0',
                //	url : 'businessdredgebroad.do?method=showPic',
                //	isIframe : true
                //},
                {
                    text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                    url: 'businessdredgebroad.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
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
        var url2 = "businessdredgebroad.do?method=showDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "businessdredgebroad.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "businessdredgebroad.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>

    });

    function forceOperation(obj) {

        if (obj == 1) {
            var url2 = "businessdredgebroad.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else if (obj == 2) {
            var url2 = "businessdredgebroad.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {
            var url2 = "businessdredgebroad.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "businessdredgebroad.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
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
            <%@ include file="/WEB-INF/pages/wfworksheet/businessdredgebroad/basedetailnew.jsp" %>
            <br/>
            <table class="formTable">
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.sheetacceptlimit"/></td>
                    <td class="content"><bean:write name="sheetMain" property="sheetAcceptLimit"
                                                    formatKey="date.formatDateTimeAll" bundle="sheet"
                                                    scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.sheetcompletelimit"/></td>
                    <td class="content"><bean:write name="sheetMain" property="sheetCompleteLimit"
                                                    formatKey="date.formatDateTimeAll" bundle="sheet"
                                                    scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.businesstype1"/></td>
                    <input type="hidden" id="businesstype1" value="<%=businesstype1%>">
                    <td class="content"><eoms:id2nameDB id="${sheetMain.businesstype1}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.mainRemark"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.urgentDegree}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <% if (!"101100109".equals(businesstype1)) { %>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.bdeptContact"/></td>
                    <td class="content"><bean:write name="sheetMain" property="bdeptContact" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.bdeptContactPhone"/></td>
                    <td class="content"><bean:write name="sheetMain" property="bdeptContactPhone" scope="request"/></td>

                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.customNo"/></td>
                    <td class="content"><bean:write name="sheetMain" property="customNo" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.customName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="customName" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.customContact"/></td>
                    <td class="content"><bean:write name="sheetMain" property="customContact" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.customContactPhone"/></td>
                    <td class="content"><bean:write name="sheetMain" property="customContactPhone"
                                                    scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.cityName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cityName" scope="request"/></td>
                    <!-- alter by yangna -->
                    <!-- <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.countyName"/></td> -->
                    <td class="label">${eoms:a2u('客户经理所属部门')}</td>
                    <td class="content"><bean:write name="sheetMain" property="countyName" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.cmanagerPhone"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cmanagerPhone" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.cmanagerContactPhone"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cmanagerContactPhone"
                                                    scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.customLevel"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.customLevel}"
                                                        beanId="ItawSystemDictTypeDao"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.productName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="productName" scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.provinceName"/></td>
                    <td class="content" colspan='3'><bean:write name="sheetMain" property="provinceName"
                                                                scope="request"/></td>
                </tr>
                <%} %>
                <!-- add by yangna xunjian -->
                <tr>
                    <td class="label">${eoms:a2u('CRM侧工单号')}</td>
                    <td class="content" colspan='3'><bean:write name="sheetMain" property="parentCorrelation"
                                                                scope="request"/></td>
                </tr>
                <!-- end by yangna -->


                <!-- GPRS -->
                <% if ("101100101".equals(businesstype1) && "".equals(zxType)) { %>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.factureArea"/></td>
                    <!--  <td  class="content"><eoms:id2nameDB id="${sheetMain.factureArea}" beanId="tawSystemAreaDao"/></td>-->
                    <td><bean:write name="sheetMain" property="factureArea" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.AppServerIPAdd"/></td>
                    <td><bean:write name="sheetMain" property="appServerIPAdd" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.isRadiusValidate"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.isRadiusValidate}"
                                                        beanId="ItawSystemDictTypeDao"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.radiusValidateIPAdd"/></td>
                    <td class="content"><bean:write name="sheetMain" property="radiusValidateIPAdd"
                                                    scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.ipAddressAssign"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.ipAddressAssign}"
                                                        beanId="ItawSystemDictTypeDao"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.apnRouterMode"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.apnRouterMode}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.apnIPPool"/></td>
                    <td class="content"><bean:write name="sheetMain" property="apnIPPool" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.transferMode"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.transferMode}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.doubleGGSN"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.doubleGGSN}"
                                                        beanId="ItawSystemDictTypeDao"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.secondIPPool"/></td>
                    <td class="content"><bean:write name="sheetMain" property="secondIPPool" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.simHLR"/></td>
                    <td class="content"><bean:write name="sheetMain" property="simHLR" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.APNName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="apnName" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.terminalNum"/></td>
                    <td class="content"><bean:write name="sheetMain" property="terminalNum" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.numDetail"/></td>
                    <td class="content"><bean:write name="sheetMain" property="numDetail" scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.numDetailFile"/></td>
                    <td colspan="3">
                        <eoms:attachment name="sheetMain" property="numDetailFile"
                                         scope="request" idField="${sheetPageName}numDetailFile"
                                         appCode="businessdredgebroad" viewFlag="Y"/>
                    </td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.volumeAssessment"/></td>
                    <td class="content" colspan="3">
                        <pre><bean:write name="sheetMain" property="volumeAssessment" scope="request"/></pre>
                    </td>
                </tr>
                <%} %>
                <!-- MMS -->     <% if ("101100102".equals(businesstype1)) { %>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.factureArea"/></td>
                    <!-- <td  class="content"><eoms:id2nameDB id="${sheetMain.factureArea}" beanId="tawSystemAreaDao"/></td> -->
                    <td class="content"><bean:write name="sheetMain" property="factureArea" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.nameListSetType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.nameListSetType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <!-- <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.bCode"/></td> -->
                    <td class="label">${eoms:a2u('业务代码&MASID参数')}</td>
                    <td class="content"><bean:write name="sheetMain" property="bcode" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.isConnectMISC"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.isConnectMISC}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>

                <!-- add by yangna -->
                <tr>
                    <td class="label">${eoms:a2u('厂商编码')}</td>
                    <td class="content"><bean:write name="sheetMain" property="mainFactoryCode" scope="request"/></td>
                    <td class="label">${eoms:a2u('业务类型')}</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainBusinessType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <!-- end by yangna -->
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.siconnectMMSGatewayName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="siconnectMMSGatewayName"
                                                    scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.SIEnterpriseCode"/></td>
                    <td class="content"><bean:write name="sheetMain" property="siEnterpriseCode" scope="request"/></td>

                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.SIServerCode"/></td>
                    <td class="content"><bean:write name="sheetMain" property="siServerCode" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.SIConnectMMSGatewayID"/></td>
                    <td class="content"><bean:write name="sheetMain" property="siConnectMMSGatewayID"
                                                    scope="request"/></td>

                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.SIIPAdd"/></td>
                    <td class="content"><bean:write name="sheetMain" property="siIPAdd" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.SIUplinkUrl"/></td>
                    <td class="content"><bean:write name="sheetMain" property="siUplinkUrl" scope="request"/></td>

                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.comProtocol"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.comProtocol}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.connectGatewayBandwidth"/></td>
                    <td class="content"><bean:write name="sheetMain" property="connectGatewayBandwidth"
                                                    scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.maxConnections"/></td>
                    <td class="content"><bean:write name="sheetMain" property="maxConnections" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.maxUnderFlow"/></td>
                    <td class="content"><bean:write name="sheetMain" property="maxUnderFlow" scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.maxUplinkFlow"/></td>
                    <td class="content"><bean:write name="sheetMain" property="maxUplinkFlow" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.flowControlPriority"/></td>
                    <td class="content"><bean:write name="sheetMain" property="flowControlPriority"
                                                    scope="request"/></td>

                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portRateIsDown"/></td>
                    <td><eoms:id2nameDB id="${sheetMain.portRateIsDown}" beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.enterpriseType"/></td>
                    <td><eoms:id2nameDB id="${sheetMain.enterpriseType}" beanId="ItawSystemDictTypeDao"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.AppProgramme"/></td>
                    <td colspan="3">
                        <eoms:attachment name="sheetMain" property="appProgramme"
                                         scope="request" idField="${sheetPageName}appProgramme"
                                         appCode="businessdredgebroad" viewFlag="Y"/></td>
                </tr>

                <!--SMS  -->        <%} %>

                <% if ("101100103".equals(businesstype1)) { %>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.nameListSetType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.nameListSetType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.SMSSigned"/></td>
                    <td class="content"><bean:write name="sheetMain" property="smsSigned" scope="request"/></td>
                </tr>
                <!-- add by yangna -->
                <tr>
                    <td class="label">${eoms:a2u('厂商编码')}</td>
                    <td class="content"><bean:write name="sheetMain" property="mainFactoryCode" scope="request"/></td>
                    <td class="label">${eoms:a2u('业务类型')}</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.mainBusinessType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <!-- end by yangna -->
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.enterpriseType"/></td>
                    <td><eoms:id2nameDB id="${sheetMain.enterpriseType}" beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.enterpriseCode"/></td>
                    <td class="content"><bean:write name="sheetMain" property="enterpriseCode" scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.serverCode"/></td>
                    <td class="content"><bean:write name="sheetMain" property="serverCode" scope="request"/></td>
                    <!-- <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.bCode"/></td> -->
                    <td class="label">${eoms:a2u('业务代码&MASID参数')}</td>
                    <td class="content"><bean:write name="sheetMain" property="bcode" scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.HostIPAdd"/></td>
                    <td class="content"><bean:write name="sheetMain" property="hostIPAdd" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.connectGatewayName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="connectGatewayName"
                                                    scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.connectGatewayID"/></td>
                    <td class="content"><bean:write name="sheetMain" property="connectGatewayID" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.factureArea"/></td>
                    <!--  <td  class="content"><eoms:id2nameDB id="${sheetMain.factureArea}" beanId="tawSystemAreaDao"/></td>-->
                    <td class="content"><bean:write name="sheetMain" property="factureArea" scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.comProtocol"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.comProtocol}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.connectGatewayBandwidth"/></td>
                    <td class="content"><bean:write name="sheetMain" property="connectGatewayBandwidth"
                                                    scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.maxConnections"/></td>
                    <td class="content"><bean:write name="sheetMain" property="maxConnections" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.maxUnderFlow"/></td>
                    <td class="content"><bean:write name="sheetMain" property="maxUnderFlow" scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.maxUplinkFlow"/></td>
                    <td class="content"><bean:write name="sheetMain" property="maxUplinkFlow" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portRateIsDown"/></td>
                    <td><eoms:id2nameDB id="${sheetMain.portRateIsDown}" beanId="ItawSystemDictTypeDao"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.authenticationModel"/></td>
                    <td><eoms:id2nameDB id="${sheetMain.authenticationModel}" beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.singleWordsBit"/></td>
                    <td class="content"><bean:write name="sheetMain" property="singleWordsBit" scope="request"/></td>

                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.flowControlPriority"/></td>
                    <td class="content" colspan="3"><bean:write name="sheetMain" property="flowControlPriority"
                                                                scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.AppProgramme"/></td>
                    <td colspan="3">
                        <eoms:attachment name="sheetMain" property="appProgramme"
                                         scope="request" idField="${sheetPageName}appProgramme"
                                         appCode="businessdredgebroad" viewFlag="Y"/></td>
                </tr>
                <%} %>

                <!-- chuanshu -->
                <% if ("101100104".equals(businesstype1) || "101100106".equals(businesstype1) || "101100107".equals(businesstype1) || ("101100108".equals(businesstype1) && !"".equals(zxType))) { %>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.bandwidth"/></td>
                    <td class="content"><bean:write name="sheetMain" property="bandwidth" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.amount"/></td>
                    <td class="content"><bean:write name="sheetMain" property="amount" scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.cityA"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cityA" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.cityZ"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cityZ" scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.portA"/></td>
                    <td class="content"><bean:write name="sheetMain" property="portA" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.portZ"/></td>
                    <td class="content"><bean:write name="sheetMain" property="portZ" scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portAInterfaceType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.portAInterfaceType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portADetailAdd"/></td>
                    <td class="content"><bean:write name="sheetMain" property="portADetailAdd" scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portABDeviceName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="portABDeviceName" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portABDevicePort"/></td>
                    <td class="content"><bean:write name="sheetMain" property="portABDevicePort" scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portZInterfaceType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.portZInterfaceType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portZBDeviceName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="portZBDeviceName" scope="request"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portZBDevicePort"/></td>
                    <td class="content"><bean:write name="sheetMain" property="portZBDevicePort" scope="request"/></td>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portZContactPhone"/></td>
                    <td class="content"><bean:write name="sheetMain" property="portZContactPhone" scope="request"/></td>
                </tr>
                <!-- add by yangna -->
                <tr>
                    <td class="label">${eoms:a2u('端点Z业务设备名称')}</td>
                    <td><bean:write name="sheetMain" property="portZDeviceName" scope="request"/></td>
                </tr>
                <!-- add by yangna -->
                <tr>
                    <!-- <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.confCRMTicketNo"/></td>
		            <td class="content"><bean:write name="sheetMain" property="confCRMTicketNo" scope="request"/></td>
                     -->
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.transfBusiness"/></td>
                    <td colspan='3'><eoms:id2nameDB id="${sheetMain.transfBusiness}"
                                                    beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.portAContactPhone"/></td>
                    <td class="content" colspan='3'><bean:write name="sheetMain" property="portAContactPhone"
                                                                scope="request"/></td>
                </tr>
                <!-- add by yangna -->
                <tr>
                    <td class="label">${eoms:a2u('SI工程师姓名')}</td>
                    <td><bean:write name="sheetMain" property="siEngineerName" scope="request"/></td>
                    <td class="label">${eoms:a2u('SI工程师联系电话')}</td>
                    <td><bean:write name="sheetMain" property="siEngineerPhone" scope="request"/></td>
                </tr>
                <!-- end by yangna -->
                <%} %>
                <!--个人宽带业务 add by yangna 20100607-->
                <% if ("101100109".equals(businesstype1)) { %>
                <tr>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.provinceName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="provinceName" scope="request"/></td>
                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.cityName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="cityName" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('客户联系人')}</td>
                    <td><bean:write name="sheetMain" property="factureArea" scope="request"/></td>
                    <td class="label">${eoms:a2u('客户联系人电话')}</td>
                    <td><bean:write name="sheetMain" property="appServerIPAdd" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('所属地区')}</td>
                    <td><bean:write name="sheetMain" property="simHLR" scope="request"/></td>
                    <td class="label">${eoms:a2u('所属区县')}</td>
                    <td><bean:write name="sheetMain" property="isRadiusValidate" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('业务序号')}</td>
                    <td><bean:write name="sheetMain" property="radiusValidateIPAdd" scope="request"/></td>
                    <td class="label">${eoms:a2u('受理来源')}</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.terminalNum}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('营业分局')}</td>
                    <td><bean:write name="sheetMain" property="apnName" scope="request"/></td>
                    <td class="label">${eoms:a2u('营业员名称/工号')}</td>
                    <td><bean:write name="sheetMain" property="ipAddressAssign" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('用户属性')}</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.apnRouterMode}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label">${eoms:a2u('联系人姓名')}</td>
                    <td><bean:write name="sheetMain" property="apnIPPool" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('联系人性别')}</td>
                    <td><bean:write name="sheetMain" property="transferMode" scope="request"/></td>
                    <td class="label">${eoms:a2u('联系方式1')}</td>
                    <td><bean:write name="sheetMain" property="doubleGGSN" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('联系方式2')}</td>
                    <td><bean:write name="sheetMain" property="secondIPPool" scope="request"/></td>
                    <td class="label">${eoms:a2u('第二联系人姓名')}</td>
                    <td><bean:write name="sheetMain" property="numDetail" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('第二联系人联系方式')}</td>
                    <td><bean:write name="sheetMain" property="bcode" scope="request"/></td>
                    <td class="label">${eoms:a2u('用户订购套餐类型')}</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.isConnectMISC}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('用户要求联系时间')}</td>
                    <td><bean:write name="sheetMain" property="factureTime" scope="request"/></td>
                    <td class="label">${eoms:a2u('用户预约装机时间')}</td>
                    <td><bean:write name="sheetMain" property="siEngineerPhone" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('接入类型')}</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.siconnectMMSGatewayName}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label">${eoms:a2u('带宽')}</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.siEnterpriseCode}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('最大下发流量')}</td>
                    <td><bean:write name="sheetMain" property="siServerCode" scope="request"/></td>
                    <td class="label">${eoms:a2u('最大上行流量')}</td>
                    <td><bean:write name="sheetMain" property="siConnectMMSGatewayID" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('IP地址分配方式')}</td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.siIPAdd}" beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label">${eoms:a2u('所属小区名称')}</td>
                    <td><bean:write name="sheetMain" property="siUplinkUrl" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('用户新安装详细地址')}</td>
                    <td><bean:write name="sheetMain" property="comProtocol" scope="request"/></td>
                    <td class="label">${eoms:a2u('用户新安装地址编码')}</td>
                    <td><bean:write name="sheetMain" property="connectGatewayBandwidth" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('所属小区2（旧）')}</td>
                    <td><bean:write name="sheetMain" property="maxConnections" scope="request"/></td>
                    <td class="label">${eoms:a2u('用户原安装安装地址')}</td>
                    <td><bean:write name="sheetMain" property="maxUnderFlow" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('用户原安装安装编码')}</td>
                    <td><bean:write name="sheetMain" property="maxUplinkFlow" scope="request"/></td>
                    <td class="label">${eoms:a2u('用户账号')}</td>
                    <td><bean:write name="sheetMain" property="flowControlPriority" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('账号密码')}</td>
                    <td><bean:write name="sheetMain" property="portRateIsDown" scope="request"/></td>
                    <td class="label">${eoms:a2u('设备编码')}</td>
                    <td><bean:write name="sheetMain" property="smsSigned" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('设备名称')}</td>
                    <td><bean:write name="sheetMain" property="hostIPAdd" scope="request"/></td>
                    <td class="label">${eoms:a2u('设备端口')}</td>
                    <td><bean:write name="sheetMain" property="connectGatewayName" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('设备地址')}</td>
                    <td><bean:write name="sheetMain" property="connectGatewayID" scope="request"/></td>
                    <td class="label">${eoms:a2u('发票信息')}</td>
                    <td><bean:write name="sheetMain" property="siEngineerName" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('赠品信息')}</td>
                    <td><bean:write name="sheetMain" property="cityA" scope="request"/></td>
                    <td class="label">${eoms:a2u('终端信息')}</td>
                    <td><bean:write name="sheetMain" property="cityZ" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('用户类型')}</td>
                    <td><bean:write name="sheetMain" property="bandwidth" scope="request"/></td>
                    <td class="label">${eoms:a2u('用户要求')}</td>
                    <td><bean:write name="sheetMain" property="amount" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label">${eoms:a2u('施工内容')}</td>
                    <td class="content" colspan='3'><bean:write name="sheetMain" property="volumeAssessment"
                                                                scope="request"/></td>
                </tr>
                <%} %>
                <!--个人宽带业务 end by yangna 20100607-->
                <tr>

                    <td class="label"><bean:message bundle="businessdredgebroad"
                                                    key="businessdredgebroad.bRequirementDesc"/></td>
                    <td class="content" colspan="3">
                        <pre><bean:write name="sheetMain" property="brequirementDesc" scope="request"/></pre>
                    </td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
                    <td colspan='3'>
                        <eoms:attachment name="sheetMain" property="sheetAccessories"
                                         scope="request" idField="sheetAccessories" appCode="businessdredgebroad"
                                         viewFlag="Y"/>
                    </td>
                </tr>
                <!-- add by yangna 查询url接口 -->
                <tr>

                    <td class="label">${eoms:a2u('pboss状态查询')}</td>
                    <td class="content">
                        <a href="http://10.131.39.94:9090/ngiom/ngiom/shxi/workform/infos.jsp?serialNo=${sheetMain.parentCorrelation}">${eoms:a2u('查询URL')}</a>
                    </td>

                    <td class="label">${eoms:a2u('施工单')}</td>
                    <td class="content">
                        <a href="businessdredgebroad.do?method=showMainPrintInfoPage&sheetKey=${sheetMain.id}"
                           target=_blank>${eoms:a2u('施工单查看')}</a>
                    </td>
                </tr>
                <!-- end by yangna 查询url接口 -->

            </table>

        </logic:present>
    </div>
    <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->

<%if (taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")) { %>

<!-- ???? -->
<c:url var="urlShowNewAuditPassPage" value="businessdredgebroad.do">
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
<!-- ????? -->
<c:url var="urlShowNewAuditUnpassPage" value="businessdredgebroad.do">
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
<!-- ???????? -->
<c:url var="urlShowCompleteDealPage" value="businessdredgebroad.do">
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
<!-- ?? -->
<c:url var="urlShowCompleteDraftPage" value="businessdredgebroad.do">
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
<!-- ?????? -->
<c:url var="urlShowPassDealPage" value="businessdredgebroad.do">
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
<!-- ?? -->
<c:url var="urlShowHoldDealPage" value="businessdredgebroad.do">
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
<c:url var="urlShowTransmitDealPage" value="businessdredgebroad.do">
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
<!-- ?? -->
<c:url var="urlShowExamineYesDealPage" value="businessdredgebroad.do">
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
<!-- ???????? -->
<c:url var="urlShowTaskCompleteAuditYes" value="businessdredgebroad.do">
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
<c:url var="urlShowTaskCompleteAuditNo" value="businessdredgebroad.do">
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
<!-- 移交-->
<c:url var="urlShowTransferkPage" value="businessdredgebroad.do">
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
<!--????????? -->
<c:url var="urlShowExamineDealPage" value="businessdredgebroad.do">
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
<!--?????? -->
<c:url var="urlShowExamineNODealPage" value="businessdredgebroad.do">
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
<!--???? -->
<c:url var="urlShowPhaseReplyPage" value="businessdredgebroad.do">
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
<c:url var="urlShowInputSplit1" value="businessdredgebroad.do">
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
<c:url var="urlShowInputSplit2" value="businessdredgebroad.do">
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
<c:url var="urlShowInputSplit3" value="businessdredgebroad.do">
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
<c:url var="urlShowInputSplit4" value="businessdredgebroad.do">
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
<!-- ?? -->
<c:url var="urlShowRejectBackPage" value="businessdredgebroad.do">
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
<c:url var="urlShowAcceptDealPage" value="businessdredgebroad.do">
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
<c:url var="urlShowDispatchDealPage" value="businessdredgebroad.do">
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
<c:url var="urlShowRejectPage" value="businessdredgebroad.do">
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
                        <option value="${urlShowNewAuditPassPage}"><bean:message bundle="businessdredgebroad"
                                                                                 key="businessdredgebroad.taskpass"/></option>
                        <option value="${urlShowNewAuditUnpassPage}"><bean:message bundle="businessdredgebroad"
                                                                                   key="businessdredgebroad.tasknopass"/></option>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("TaskCompleteAuditHumTask")) { %>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowTaskCompleteAuditYes}"><bean:message bundle="businessdredgebroad"
                                                                                     key="businessdredgebroad.TaskCompleteAuditYes"/></option>
                        <option value="${urlShowTaskCompleteAuditNo}"><bean:message bundle="businessdredgebroad"
                                                                                    key="businessdredgebroad.TaskCompleteAuditNo"/></option>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("ExcuteHumTask")) { %>
                    <select id="dealSelector">
                        <%
                            //if(taskStatus.equals(Constants.TASK_STATUS_READY)){
                            if (taskStatus.equals(Constants.TASK_STATUS_READY) && myLink != null && myLink.getOperateType().intValue() != 6) {
                        %>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <%if (!("101100109".equals(businesstype1))) {%>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} %>
                        <%
                            //}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){
                        } else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED) || (myLink != null && myLink.getOperateType().intValue() == 6)) {
                        %>
                        <option value="${urlShowTransmitDealPage}"><bean:message bundle="sheet"
                                                                                 key="common.split"/></option>
                        <option value="${urlShowDispatchDealPage}"><bean:message bundle="sheet"
                                                                                 key="common.dispatchType"/></option>
                        <option value="${urlShowCompleteDealPage}"><bean:message bundle="businessdredgebroad"
                                                                                 key="businessdredgebroad.dealwcnosp"/></option>
                        <option value="${urlShowPassDealPage}"><bean:message bundle="businessdredgebroad"
                                                                             key="businessdredgebroad.totaskcompsq"/></option>
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <option value="${urlShowTransferkPage}"><bean:message bundle="sheet"
                                                                              key="common.transfer"/></option>
                        <%} %>
                    </select>
                    <%} else if (taskName.equals("AffirmHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowExamineNODealPage}"><bean:message bundle="businessdredgebroad"
                                                                                  key="businessdredgebroad.dealrejectpass"/></option>
                        <option value="${urlShowExamineDealPage}"><bean:message bundle="businessdredgebroad"
                                                                                key="businessdredgebroad.nopassback"/></option>
                    </select>
                    <%} else if (taskName.equals("DraftHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowCompleteDraftPage}"><bean:message bundle="businessdredgebroad"
                                                                                  key="businessdredgebroad.draft"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldHumTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="businessdredgebroad"
                                                                             key="businessdredgebroad.Hold"/></option>
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
            url = "businessdredgebroad.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
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
