<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%
    String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
    if (orderId.equals(""))
        orderId = StaticMethod.nullObject2String(request.getAttribute("orderId"));
    System.out.println("=====" + orderId);
    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
    if (taskName.equals(""))
        taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("@@taskName==" + taskName);
    String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
    System.out.println("@@sheetType==" + sheetType);

    String url = "";
    String head = "http://10.25.2.74:8899";
    String host = StaticMethod.nullObject2String(request.getRequestURL());
    if (host.indexOf("10.131.62") < 0) {
        head = "http://10.25.2.74:8899";
    }
%>

<caption>
    <div class="header center">IP数据专线</div>
</caption>
<html:form action="ipspeciallines.do?method=xedit" method="post" styleId="ipspeciallineForm" target="myname">
    <br>

    <table class="formTable">
        <caption>业务相关信息</caption>
        <input type="hidden" id="id" name="id" value="${ipspeciallineForm.id}"/>
        <input type="hidden" id="orderId" name="orderId" value="<%=orderId%>"/>
        <input type="hidden" id="tradeId" name="tradeId" value="${ipspeciallineForm.tradeId}"/>
        <tbody id='BusinessInfo'>
        <tr>
            <td class="label">接口类型</td>
            <td colspan="3">
                <html:errors property="portAInterfaceType"/>
                <eoms:comboBox name="portAInterfaceType" id="portAInterfaceType"
                               defaultValue="${ipspeciallineForm.portAInterfaceType}" initDicId="1011015"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">客户端联系人*</td>
            <td class="content">
                <html:errors property="apointLocalPerson"/>
                <html:text property="apointLocalPerson" styleId="apointLocalPerson" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">客户端详细地址*</td>
            <td class="content">
                <html:errors property="portADetailAdd"/>
                <html:text property="portADetailAdd" styleId="portADetailAdd" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">业务带宽*</td>
            <td class="content">
                <html:errors property="businessBandwidth"/>
                <html:text property="businessBandwidth" styleId="businessBandwidth" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">业务数量（传输条数）*</td>
            <td class="content">
                <html:errors property="businessAmount"/>
                <html:text property="businessAmount" styleId="businessAmount" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">IP地址-客户应用服务需求数量（个）*</td>
            <td class="content">
                <html:errors property="ipServerNeedNum"/>
                <html:text property="ipServerNeedNum" styleId="ipServerNeedNum" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">IP地址-互联需求数量(个)*</td>
            <td class="content">
                <html:errors property="ipNeedNum"/>
                <html:text property="ipNeedNum" styleId="ipNeedNum" styleClass="text medium" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">子网掩码</td>
            <td class="content">
                <html:errors property="cnetCode"/>
                <html:text property="cnetCode" styleId="cnetCode" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">网关</td>
            <td class="content">
                <html:errors property="gateway"/>
                <html:text property="gateway" styleId="gateway" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">业务需求描述*</td>
            <td colspan="3">
                <textarea name="businessRequireDesc" id="businessRequireDesc" class="textarea max"
                          alt="allowBlank:false">${ipspeciallineForm.businessRequireDesc}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">用户个性化设备需求</td>
            <td class="content">
                <html:errors property="userSpecifyDevNeed"/>
                <html:text property="userSpecifyDevNeed" styleId="userSpecifyDevNeed" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">用户是否有用户网站</td>
            <td class="content">
                <html:errors property="userIsUserNet"/>
                <eoms:comboBox name="userIsUserNet" id="userIsUserNet" defaultValue="${ipspeciallineForm.userIsUserNet}"
                               initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
        </tr>

        <tr>
            <td class="label">端口数量*</td>
            <td colspan="3">
                <html:errors property="portNum"/>
                <html:text property="portNum" styleId="portNum" styleClass="text medium" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">业务需求区域</td>
            <td colspan="3">
                <textarea name="requirmentArea" id="requirmentArea" class="textarea max"
                          alt="allowBlank:true">${ipspeciallineForm.requirmentArea}</textarea>
            </td>
        </tr>
        </tbody>
    </table>


    <%if (sheetType.equals("businessImplement") || taskName.equals("UserTask") || taskName.equals("AccessTask") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>
    <table class="formTable">
        <caption>客户端勘查信息</caption>
        <tbody id='customInfo'>
        <tr>
            <td class="label">客户端标准地址</td>
            <td colspan='3'>
                <html:text property="userStardAddA" styleId="userStardAddA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">客户位置经度</td>
            <td class="content">
                <html:errors property="userSiteAA"/>
                <html:text property="userSiteAA" styleId="userSiteAA" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">客户位置纬度</td>
            <td class="content">
                <html:errors property="userSiteHA"/>
                <html:text property="userSiteHA" styleId="userSiteHA" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">用户端设备端口类型</td>
            <td class="content">
                <html:errors property="portABDeviceType"/>
                <eoms:comboBox name="portABDeviceType" id="portABDeviceType"
                               defaultValue="${ipspeciallineForm.portABDeviceType}" initDicId="1012304"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">建设方式</td>
            <td class="content">
                <html:errors property="buildMethodA"/>
                <eoms:comboBox name="buildMethodA" id="buildMethodA" defaultValue="${ipspeciallineForm.buildMethodA}"
                               initDicId="1012317"
                               alt="allowBlank:false" styleClass="select-class"/>

            </td>
        </tr>
        <tr>
            <td class="label">客户端是否具有设备</td>
            <td class="content">
                <html:errors property="userIsHaveDivA"/>
                <eoms:comboBox name="userIsHaveDivA" id="userIsHaveDivA"
                               defaultValue="${ipspeciallineForm.userIsHaveDivA}" initDicId="1012310"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
            <td class="label">是否需要移动采购</td>
            <td class="content">
                <html:errors property="isNeedBuyA"/>
                <eoms:comboBox name="isNeedBuyA" id="isNeedBuyA" defaultValue="${ipspeciallineForm.isNeedBuyA}"
                               initDicId="1012310"
                               alt="allowBlank:false" styleClass="select-class" onchange="changeOperate(this.value);"/>
            </td>
        </tr>
        </tbody>
        <tbody id="NeededA" style="display:none">
        <tr>
            <td class="label">需要购买的设备</td>
            <td colspan='3'>
                <html:errors property="theDevNeededA"/>
                <html:textarea property="theDevNeededA" styleId="theDevNeededA" styleClass="textarea max"
                               alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        <tr>

    </table>
    <%} %>
    <br>
    <%if (sheetType.equals("businessImplement") || taskName.equals("AccessTask") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>
    <table class="formTable">
        <caption>接入点勘查信息</caption>
        <tbody id='interfaceInfo'>
        <%if (taskName.equals("AccessTask")) {%>
        <tr>
            <td class="label">接入点勘察</td>
            <td colspan="3">
                <a style="cursor:hand;color:darkbule"
                   onclick="javascript:popupIrmsPreSurvey('${ipspeciallineForm.portADetailAdd}')">点击进行接入点勘察</a>
            </td>
        </tr>
        <%} %>
        <tr>
            <td class="label">接入点地址</td>
            <td colspan="3">
                <html:errors property="interfacePointAddA"/>
                <html:text property="interfacePointAddA" styleId="interfacePointAddA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">接入点站点名称（接入基站）</td>
            <td colspan="3">
                <html:errors property="interfaceSiteNameA"/>
                <html:text property="interfaceSiteNameA" styleId="interfaceSiteNameA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">光纤设备名称</td>
            <td class="content">
                <html:errors property="fiberEquipNameA"/>
                <html:text property="fiberEquipNameA" styleId="fiberEquipNameA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">光纤设备编号</td>
            <td class="content">
                <html:errors property="fiberEquipCodeA"/>
                <html:text property="fiberEquipCodeA" styleId="fiberEquipCodeA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">纤芯个数*</td>
            <td class="content">
                <html:errors property="fiberAcount"/>
                <html:text property="fiberAcount" styleId="fiberAcount" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">A接入点类型</td>
            <td class="content">
                <html:errors property="interfacePointTypeA"/>
                <eoms:comboBox name="interfacePointTypeA" id="interfacePointTypeA"
                               defaultValue="${ipspeciallineForm.interfacePointTypeA}" initDicId="1012316"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">A光缆路由描述</td>
            <td colspan="3">
                <html:errors property="fiberAroute"/>
                <textarea name="fiberAroute" id="fiberAroute" class="textarea max"
                          alt="allowBlank:true">${ipspeciallineForm.fiberAroute}</textarea>
            </td>
        </tr>
        </tbody>

    </table>
    <%} %>


    <br>
    <%if (sheetType.equals("businessImplement") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>
    <table class="formTable">
        <caption>传输线路勘查信息</caption>
        <tbody id='transLineInfo'>
        <tr>
            <td class="label">最后一公里光缆长度(单位：皮长)</td>
            <td class="content">
                <html:errors property="fiberLengthA"/>
                <html:text property="fiberLengthA" styleId="fiberLengthA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">光缆产权</td>
            <td class="content">
                <html:errors property="fiberOwnerA"/>
                <eoms:comboBox name="fiberOwnerA" id="fiberOwnerA" defaultValue="${ipspeciallineForm.fiberOwnerA}"
                               initDicId="1012315"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">敷设方式*</td>
            <td colspan="3">
                <html:errors property="buildTypeA"/>
                <textarea name="buildTypeA" id="buildTypeA" class="textarea max"
                          alt="allowBlank:true">${ipspeciallineForm.buildTypeA}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label">客户端到接入点能否通达*</td>
            <td colspan="3">
                <html:errors property="isOkBetweenUserA"/>
                <eoms:comboBox name="isOkBetweenUserA" id="isOkBetweenUserA"
                               defaultValue="${ipspeciallineForm.isOkBetweenUserA}" initDicId="1012310"
                               alt="allowBlank:false" styleClass="select-class" onchange="changeOperate2(this.value);"/>
            </td>
        </tr>
        <tr>
        <tbody id='YuanYin' style="display:none">
        <td class="label">不能接入的原因*</td>
        <td colspan='3'>
            <html:errors property="noInputResonA"/>
            <textarea name="noInputResonA" id="noInputResonA" class="textarea max"
                      alt="allowBlank:true">${ipspeciallineForm.noInputResonA}</textarea>
        </td>
        </tbody>
        </tr>
        </tbody>

    </table>
    <%} %>
    <br>

    <%
        if (sheetType.equals("businessImplement") || taskName.equals("TransfereTask") ||
                taskName.equals("ProjectDealTask") ||
                taskName.equals("CityNetTask") ||
                taskName.equals("TrasitionTask") ||
                taskName.equals("BusinessTestTask") ||
                taskName.equals("DredgeAffirmTask") ||
                taskName.equals("MakeTask") ||
                taskName.equals("HandleTask") ||
                taskName.equals("AuditTask") ||
                taskName.equals("HoldTask")) {
    %>
    <table class="formTable">
        <caption>传输容量勘查信息</caption>
        <tbody id='transCardInfo'>
        <tr>
            <td class="label">传输容量是否满足开通</td>
            <td class="content">
                <html:errors property="isDeviceAllowOpenA"/>
                <html:text property="isDeviceAllowOpenA" styleId="isDeviceAllowOpenA" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">是否需要添加板卡</td>
            <td class="content">
                <html:errors property="isNeedAddCardA"/>
                <eoms:comboBox name="isNeedAddCardA" id="isNeedAddCardA"
                               defaultValue="${ipspeciallineForm.isNeedAddCardA}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class" onchange="needAddCardA(this.value);"/>
            </td>
        </tr>
        <tbody id='FanKa' style="display:none">
        <tr>
            <td class="label">板卡类型</td>
            <td class="content">
                <html:errors property="cardTypeA"/>
                <html:text property="cardTypeA" styleId="cardTypeA" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">板卡数量</td>
            <td class="content">
                <html:errors property="cardNumA"/>
                <html:text property="cardNumA" styleId="cardNumA" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        </tbody>
        <br>
    </table>
    <%} %>


    <%
        if (taskName.equals("ImplementDealTask") || taskName.equals("CityTask") ||
                taskName.equals("ProjectDealTask") ||
                taskName.equals("CityNetTask") ||
                taskName.equals("TrasitionTask") ||
                taskName.equals("BusinessTestTask") ||
                taskName.equals("DredgeAffirmTask") ||
                taskName.equals("HoldTask")
        ) {
    %>
    <table class="formTable">
        <caption>城域网接入信息</caption>
        <tbody id='cityInfo'>
        <tr>
            <td class="label">城域网接入站点名称*</td>
            <td class="content">
                <html:errors property="siteNameZ"/>
                <html:text property="siteNameZ" styleId="siteNameZ" styleClass="text medium" alt="allowBlank:false"/>
            </td>
            <td class="label">城域网接入设备名称</td>
            <td class="content">
                <html:errors property="portZBDeviceName"/>
                <html:text property="portZBDeviceName" styleId="portZBDeviceName" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">城域网接入设备端口</td>
            <td class="content">
                <html:errors property="portZBDevicePort"/>
                <html:text property="portZBDevicePort" styleId="portZBDevicePort" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">城域网接入详细地址</td>
            <td class="content">
                <html:errors property="portZDetailAdd"/>
                <html:text property="portZDetailAdd" styleId="portZDetailAdd" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        <!-- @@@ -->
    </table>
    <br>
    <%} %>

    <%if (sheetType.equals("businessImplement") && !taskName.equals("ImplementDealTask")) { %>
    <table class="formTable">
        <caption>最后一公里相关信息</caption>
        <tbody id='lastInfo'>
        <tr>
            <td class="label">是否熔接</td>
            <td class="content">
                <html:errors property="isGetInterfaceA"/>
                <eoms:comboBox name="isGetInterfaceA" id="isGetInterfaceA"
                               defaultValue="${ipspeciallineForm.isGetInterfaceA}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">熔接序号</td>
            <td colspan="3">
                <html:errors property="getInterfaceNoA"/>
                <html:text property="getInterfaceNoA" styleId="getInterfaceNoA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">最后一公里处理意见</td>
            <td class="content" colspan="3">
                <textarea name="theLastOpinionA" id="theLastOpinionA" class="textarea max"
                          alt="allowBlank:false">${ipspeciallineForm.theLastOpinionA}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">工程信息</td>
            <td colspan="3">
                <a style="cursor:hand;color:darkbule" onclick="javascript:popupProjectInfo()">导入工程信息</a>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <%} %>

    <%
        if (sheetType.equals("businessImplement") && (
                taskName.equals("TrasitionTask") ||
                        taskName.equals("BusinessTestTask") ||
                        taskName.equals("DredgeAffirmTask") ||
                        taskName.equals("HandleTask") ||
                        taskName.equals("HoldTask"))) {
    %>
    <br>
    <table class="formTable">
        <caption>电路信息</caption>
        <tbody id='circuitInfo'>
        <tr>
            <td class="label">电路名称</td>
            <td class="content">
                <html:errors property="circuitName"/>
                <html:text property="circuitName" styleId="circuitName" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">电路编号</td>
            <td class="content">
                <html:errors property="circuitSheetId"/>
                <html:text property="circuitSheetId" styleId="circuitSheetId" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
    </table>
    <%} %>
    <table class="formTable">
        <tr>
            <td class="label">备注</td>
            <td colspan="3">
                <textarea name="remark" id="remark" class="textarea max"
                          alt="allowBlank:true">${ipspeciallineForm.remark}</textarea>
            </td>
        </tr>
    </table>
    <br/>
    <input type="button" styleClass="button" onclick="check();" value="保存"/>
    <input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">
</html:form>
<script type="text/javascript">
    function initPage() {
        v = new eoms.form.Validation({form: 'ipspeciallineForm'});
        var taskName = "<%=taskName%>";

        if (taskName != "" && taskName != "AcceptTask" && taskName != "ImplementDealTask") {
            eoms.form.readOnlyArea('BusinessInfo');
        }

        if (taskName == "AccessTask") {
            eoms.form.readOnlyArea('customInfo');
        } else if (taskName == "TransferlTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
        } else if (taskName == "TransfereTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
            eoms.form.readOnlyArea('transLineInfo');
        } else if (taskName == "CityTask" || taskName == "CityNetTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
            eoms.form.readOnlyArea('transLineInfo');
            eoms.form.readOnlyArea('transCardInfo');
        } else if (taskName == "ProjectDealTask") {

        } else if (taskName == "TrasitionTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
            eoms.form.readOnlyArea('transLineInfo');
            eoms.form.readOnlyArea('transCardInfo');
            eoms.form.readOnlyArea('cityInfo');
            eoms.form.readOnlyArea('lastInfo');
        }

    }

    Ext.onReady(
        function () {
            initPage();
        }
    );

    function check() {
        v1 = new eoms.form.Validation({form: 'ipspeciallineForm'});
        if (v1.check()) {
            document.forms[0].submit();
            window.close();
        } else {
            return false;
        }
    }

    window.name = "myname";

    function popupIrmsPreSurvey(cityCnName) {
        var gisUrl = encodeURI("${app}/sheet/resourceconfirm/resourceconfirm.do?method=showTnmsPage&type=1&cityCnName=" + cityCnName);
        var params = window.showModalDialog(gisUrl, "", "dialogWidth:" + screen.width * 1.0 + "px;dialogHeight:" + screen.height * 0.9 + "px");
        if (params != null) {
            //var portADetailAdd = $('${sheetPageName}portADetailAdd').value;
            $('${sheetPageName}fiberEquipNameA').value = params["fiberEquipName"];
            $('${sheetPageName}fiberEquipCodeA').value = params["fiberEquipCode"];
            //$('${sheetPageName}interfaceEquipCodeA').value = params["siteEquipCode"];
            $('${sheetPageName}interfaceSiteNameA').value = params["siteName"];
            //$('${sheetPageName}accessSiteIden').value = params["accessSiteIden"];

        }

    }

    function changeOperate(input) {
        var frm = document.forms[0];
        var temp = frm.isNeedBuyA ? frm.isNeedBuyA.value : '';
        if (temp != '') {
            if (input == 101231002) {
                eoms.form.enableArea('NeededA');
            } else if (input == 101231001) {
                eoms.form.disableArea('NeededA', true);
            } else {
                eoms.form.disableArea('NeededA', true);
            }
        }
    }

    function changeOperate2(input) {
        var frm = document.forms[0];
        var temp = frm.isOkBetweenUserA ? frm.isOkBetweenUserA.value : '';
        if (temp != '') {
            if (input == 101231001) {
                eoms.form.enableArea('YuanYin');
            } else if (input == 101231002) {
                eoms.form.disableArea('YuanYin', true);
            } else {
                eoms.form.disableArea('YuanYin', true);
            }
        }
    }

    function needAddCardA(input) {
        var frm = document.forms[0];
        var temp = frm.isNeedBuyA ? frm.isNeedBuyA.value : '';
        if (temp != '') {
            if (input == 101231002) {
                eoms.form.enableArea('FanKa');
            } else if (input == 101231001) {
                eoms.form.disableArea('FanKa', true);
            } else {
                eoms.form.disableArea('FanKa', true);
            }
        }
    }

    function popupProjectInfo() {
        var urlstr = "<%=head%>/webmaster/jsp/res/import/custexcelimport/custexcelimport.jsp?includeSpecialties=ProjectResource&userName=admin&prodCode=${gprsspeciallineForm.id}";
        var params = window.showModalDialog(urlstr, "", "dialogWidth:" + screen.width * 1.0 + "px;dialogHeight:" + screen.height * 0.9 + "px");
    }
</script>
<!-- footer_eoms.jsp end-->
