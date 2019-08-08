<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript" src="${app}/scripts/deployJava.js"></script>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%
    String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
    System.out.println("orderId=====" + orderId);
    if (orderId.equals(""))
        orderId = StaticMethod.nullObject2String(request.getAttribute("orderId"));
    System.out.println("=====" + orderId);
    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
    if (taskName.equals(""))
        taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));

    String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));

    String addr = StaticMethod.nullObject2String(request.getParameter("addr"));

    String url = "";
    String head = "http://10.25.2.97:8899";
    String host = StaticMethod.nullObject2String(request.getRequestURL());
    if (host.indexOf("10.131.62") < 0) {
        head = "http://10.25.2.97:8899";
    }

%>
<script type="text/javascript">
    function clickweb() {
        var haveNotJREInfor = '你没有安装传输综合网管支持的JRE版本！';

        if (deployJava.versionCheck('1.6.0_10+')) {
            window.location = "http://10.26.106.210:9900/webclient/dmsclient_zh6.jnlp";
        } else if (deployJava.versionCheck('1.5.0_10+')) {
            window.location = "http://10.26.106.210:9900/webclient/dmsclient_zh5.jnlp";
        } else {
            document.write(haveNotJREInfor);
        }
        /*var list = deployJava.getJREs();
        var support_jres = "1.5.0_10+ <br> 1.6.0_10+";
        var result = "";
        if (list.length != 0) {
            result = list[0];
            for (var i=1; i < list.length; i++) {
                result += "<br>" + list[i];
            }
            document.write("<br><br><br><br><br><br><br><br><br><br><br>");
            document.write('<br>' + suportJREInfor + '<br>' + support_jres);
            document.write('<br>' + localInstallJREInfor + '<br>' + result);
        }*/

    }

</script>
<script type="text/javascript">
    <!--验证-->
    function initPage() {
        v = new eoms.form.Validation({form: 'transferspeciallineForm'});
        var taskName = "<%=taskName%>";
        var addr = "<%=addr%>";

        if (taskName != "" && taskName != "AcceptTask" && taskName != "ImplementDealTask") {
            eoms.form.readOnlyArea('BusinessInfo');
        }

        if (taskName == "UserTask") {
            if (addr == "${transferspeciallineForm.portADetailAdd}")
                eoms.form.readOnlyArea('customInfoZ');
            else if (addr == "${transferspeciallineForm.portZDetailAdd}")
                eoms.form.readOnlyArea('customInfoA');
        } else if (taskName == "AccessTask") {
            eoms.form.readOnlyArea('customInfoA');
            eoms.form.readOnlyArea('customInfoZ');
            if (addr == "${transferspeciallineForm.portADetailAdd}")
                eoms.form.readOnlyArea('interfaceInfoZ');
            else if (addr == "${transferspeciallineForm.portZDetailAdd}")
                eoms.form.readOnlyArea('interfaceInfoA');
        } else if (taskName == "TransferlTask") {
            eoms.form.readOnlyArea('customInfoA');
            eoms.form.readOnlyArea('customInfoZ');
            eoms.form.readOnlyArea('interfaceInfoA');
            eoms.form.readOnlyArea('interfaceInfoZ');

            if (addr == "${transferspeciallineForm.portADetailAdd}")
                eoms.form.readOnlyArea('transLineInfoZ');
            else if (addr == "${transferspeciallineForm.portZDetailAdd}")
                eoms.form.readOnlyArea('transLineInfoA');

        } else if (taskName == "TransfereTask") {
            eoms.form.readOnlyArea('customInfoA');
            eoms.form.readOnlyArea('customInfoZ');
            eoms.form.readOnlyArea('interfaceInfoA');
            eoms.form.readOnlyArea('interfaceInfoZ');
            eoms.form.readOnlyArea('transLineInfoA');
            eoms.form.readOnlyArea('transLineInfoZ');

            if (addr == "${transferspeciallineForm.portADetailAdd}")
                eoms.form.readOnlyArea('transCardInfoZ');
            else if (addr == "${transferspeciallineForm.portZDetailAdd}")
                eoms.form.readOnlyArea('transCardInfoA');

        } else if (taskName == "ProjectDealTask") {
            eoms.form.readOnlyArea('customInfoA');
            eoms.form.readOnlyArea('customInfoZ');
            eoms.form.readOnlyArea('interfaceInfoA');
            eoms.form.readOnlyArea('interfaceInfoZ');
            eoms.form.readOnlyArea('transLineInfoA');
            eoms.form.readOnlyArea('transLineInfoZ');
            eoms.form.readOnlyArea('transCardInfoA');
            eoms.form.readOnlyArea('transCardInfoZ');

            if (addr == "${transferspeciallineForm.portADetailAdd}")
                eoms.form.readOnlyArea('lastInfoZ');
            else if (addr == "${transferspeciallineForm.portZDetailAdd}")
                eoms.form.readOnlyArea('lastInfoA');

        } else if (taskName == "TrasitionTask") {
            eoms.form.readOnlyArea('customInfoA');
            eoms.form.readOnlyArea('customInfoZ');
            eoms.form.readOnlyArea('interfaceInfoA');
            eoms.form.readOnlyArea('interfaceInfoZ');
            eoms.form.readOnlyArea('transLineInfoA');
            eoms.form.readOnlyArea('transLineInfoZ');
            eoms.form.readOnlyArea('transCardInfoA');
            eoms.form.readOnlyArea('transCardInfoZ');
            eoms.form.readOnlyArea('lastInfoA');
            eoms.form.readOnlyArea('lastInfoZ');
        }
    }

    Ext.onReady(
        function () {
            initPage();
        }
    );

    function check() {
        v1 = new eoms.form.Validation({form: 'transferspeciallineForm'});
        if (v1.check()) {
            document.forms[0].submit();
            window.close();
        } else {
            return false;
        }
    }

    function changeOperateA(input) {
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

    function changeOperateZ(input) {
        var frm = document.forms[0];
        var temp = frm.isNeedBuyZ ? frm.isNeedBuyZ.value : '';
        if (temp != '') {
            if (input == 101231002) {
                eoms.form.enableArea('NeededZ');
            } else if (input == 101231001) {
                eoms.form.disableArea('NeededZ', true);
            } else {
                eoms.form.disableArea('NeededZ', true);
            }
        }
    }

    function changeOperate2A(input) {
        var frm = document.forms[0];
        var temp = frm.isOkBetweenUserA ? frm.isOkBetweenUserA.value : '';
        if (temp != '') {
            if (input == 101231001) {
                eoms.form.enableArea('YuanYinA');
            } else if (input == 101231002) {
                eoms.form.disableArea('YuanYinA', true);
            } else {
                eoms.form.disableArea('YuanYinA', true);
            }
        }
    }

    function changeOperate2Z(input) {
        var frm = document.forms[0];
        var temp = frm.isOkBetweenUserZ ? frm.isOkBetweenUserZ.value : '';
        if (temp != '') {
            if (input == 101231001) {
                eoms.form.enableArea('YuanYinZ');
            } else if (input == 101231002) {
                eoms.form.disableArea('YuanYinZ', true);
            } else {
                eoms.form.disableArea('YuanYinZ', true);
            }
        }
    }

    function needAddCardA(input) {
        var frm = document.forms[0];
        var temp = frm.isNeedBuyA ? frm.isNeedBuyA.value : '';
        if (temp != '') {
            if (input == 101230902) {
                eoms.form.enableArea('FanKaA');
            } else if (input == 101230901) {
                eoms.form.disableArea('FanKaA', true);
            } else {
                eoms.form.disableArea('FanKaA', true);
            }
        }
    }

    function needAddCardZ(input) {
        var frm = document.forms[0];
        var temp = frm.isNeedBuyZ ? frm.isNeedBuyZ.value : '';
        if (temp != '') {
            if (input == 101230902) {
                eoms.form.enableArea('FanKaZ');
            } else if (input == 101230901) {
                eoms.form.disableArea('FanKaZ', true);
            } else {
                eoms.form.disableArea('FanKaZ', true);
            }
        }
    }

    function popupIrmsPreSurveyA(cityCnName) {
        var gisUrl = encodeURI("${app}/sheet/resourceconfirm/resourceconfirm.do?method=showTnmsPage&type=1&cityCnName=" + cityCnName);
        var params = window.showModalDialog(gisUrl, "", "dialogWidth:" + screen.width * 1.0 + "px;dialogHeight:" + screen.height * 0.9 + "px");
        if (params != null) {
            $('${sheetPageName}fiberEquipNameA').value = params["fiberEquipName"];
            $('${sheetPageName}fiberEquipCodeA').value = params["fiberEquipCode"];
            //$('${sheetPageName}interfaceEquipCodeA').value = params["siteEquipCode"];
            $('${sheetPageName}interfaceSiteNameA').value = params["siteName"];


        }

    }

    function popupIrmsPreSurveyZ(cityCnName) {
        var gisUrl = encodeURI("${app}/sheet/resourceconfirm/resourceconfirm.do?method=showTnmsPage&type=1&cityCnName=" + cityCnName);
        var params = window.showModalDialog(gisUrl, "", "dialogWidth:" + screen.width * 1.0 + "px;dialogHeight:" + screen.height * 0.9 + "px");
        if (params != null) {
            $('${sheetPageName}fiberEquipNameZ').value = params["fiberEquipName"];
            $('${sheetPageName}fiberEquipCodeZ').value = params["fiberEquipCode"];
            //$('${sheetPageName}interfaceEquipCodeZ').value = params["siteEquipCode"];
            $('${sheetPageName}interfaceSiteNameZ').value = params["siteName"];

        }
    }

    function popupProjectInfo() {
        var urlstr = "<%=head%>/webmaster/jsp/res/import/custexcelimport/custexcelimport.jsp?includeSpecialties=ProjectResource&userName=admin&prodCode=${gprsspeciallineForm.id}";
        var params = window.showModalDialog(urlstr, "", "dialogWidth:" + screen.width * 1.0 + "px;dialogHeight:" + screen.height * 0.9 + "px");
    }

    window.name = "myname";
</script>
<caption>
    <div class="header center">传输专线</div>
</caption>
<html:form action="transferspeciallines.do?method=xedit" method="post" styleId="transferspeciallineForm"
           target="myname">
    <input type="hidden" id="id" name="id" value="${transferspeciallineForm.id}"/>
    <input type="hidden" id="orderId" name="orderId" value="<%=orderId%>"/>
    <input type="hidden" id="tradeId" name="tradeId" value="${transferspeciallineForm.tradeId}"/>
    <br>
    <table class="formTable">
        <caption>业务相关信息</caption>
        <tbody id='BusinessInfo'>
        <tr>
            <td class="label">城市A*</td>
            <td class="content">
                <html:errors property="cityA"/>
                <html:text property="cityA" styleId="cityA" styleClass="text medium" alt="allowBlank:false"/>
            </td>
            <td class="label">城市Z*</td>
            <td class="content">
                <html:errors property="cityZ"/>
                <html:text property="cityZ" styleId="cityZ" styleClass="text medium" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">端点A</td>
            <td class="content">
                <html:errors property="portA"/>
                <html:text property="portA" styleId="portA" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">端点Z</td>
            <td class="content">
                <html:errors property="portZ"/>
                <html:text property="portZ" styleId="portZ" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A接口类型及型号</td>
            <td class="content">
                <html:errors property="portAInterfaceType"/>
                <eoms:comboBox name="portAInterfaceType" id="portAInterfaceType"
                               defaultValue="${transferspeciallineForm.portAInterfaceType}" initDicId="1011015"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">Z接口类型及型号</td>
            <td class="content">
                <html:errors property="portZInterfaceType"/>
                <eoms:comboBox name="portZInterfaceType" id="portZInterfaceType"
                               defaultValue="${transferspeciallineForm.portZInterfaceType}" initDicId="1011015"
                               alt="allowBlank:true" styleClass="select-class"/>

            </td>
        </tr>
        <tr>
            <td class="label">A端点详细地址*</td>
            <td class="content">
                <html:errors property="portADetailAdd"/>
                <html:text property="portADetailAdd" styleId="portADetailAdd" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">Z端点详细地址*</td>
            <td class="content">
                <html:errors property="portZDetailAdd"/>
                <html:text property="portZDetailAdd" styleId="portZDetailAdd" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">端点A业务设备名称*</td>
            <td class="content">
                <html:errors property="portABDeviceName"/>
                <html:text property="portABDeviceName" styleId="portABDeviceName" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">端点Z业务设备名称</td>
            <td class="content">
                <html:errors property="portZBDeviceName"/>
                <html:text property="portZBDeviceName" styleId="portZBDeviceName" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">端点A业务设备端口</td>
            <td class="content">
                <html:errors property="portABDevicePort"/>
                <html:text property="portABDevicePort" styleId="portABDevicePort" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">端点Z业务设备端口</td>
            <td class="content">
                <html:errors property="portZBDevicePort"/>
                <html:text property="portZBDevicePort" styleId="portZBDevicePort" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A客户端联系人*</td>
            <td class="content">
                <html:errors property="apointLocalPerson"/>
                <html:text property="apointLocalPerson" styleId="apointLocalPerson" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">Z客户端联系人*</td>
            <td class="content">
                <html:errors property="zpointLocalPerson"/>
                <html:text property="zpointLocalPerson" styleId="zpointLocalPerson" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">A客户端联系电话*</td>
            <td class="content">
                <html:errors property="portAContactPhone"/>
                <html:text property="portAContactPhone" styleId="portAContactPhone" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">Z客户端联系电话*</td>
            <td class="content">
                <html:errors property="portZContactPhone"/>
                <html:text property="portZContactPhone" styleId="portZContactPhone" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">带宽*</td>
            <td colspan="3">
                <html:errors property="bandwidth"/>
                <html:text property="bandwidth" styleId="bandwidth" styleClass="text medium" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">用户是否有用户网站</td>
            <td class="content">
                <html:errors property="userIsUserNet"/>
                <eoms:comboBox name="userIsUserNet" id="userIsUserNet"
                               defaultValue="${transferspeciallineForm.userIsUserNet}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">用户个性化设备需求</td>
            <td colspan='3'>
                <html:errors property="userSpecifyDevNeed"/>
                <html:text property="userSpecifyDevNeed" styleId="userSpecifyDevNeed" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
    </table>

    <%if (sheetType.equals("businessImplement") || taskName.equals("UserTask") || taskName.equals("AccessTask") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>
    <table class="formTable">
        <caption>客户端勘查信息</caption>
        <tbody id='customInfoA'>
        <tr>
            <td class="label">A客户端标准地址</td>
            <td colspan='3'>
                <html:text property="userStardAddA" styleId="userStardAddA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A客户位置经度</td>
            <td class="content">
                <html:errors property="userSiteAA"/>
                <html:text property="userSiteAA" styleId="userSiteAA" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">A客户位置纬度</td>
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
                               defaultValue="${transferspeciallineForm.portABDeviceType}" initDicId="1012304"
                               alt="allowBlank:true" styleClass="select-class"/>

            </td>
            <td class="label">A建设方式</td>
            <td class="content">
                <html:errors property="buildMethodA"/>
                <eoms:comboBox name="buildMethodA" id="buildMethodA"
                               defaultValue="${transferspeciallineForm.buildMethodA}" initDicId="1012308"
                               alt="allowBlank:true" styleClass="select-class"/>

            </td>
        </tr>
        <tr>
            <td class="label">A客户端是否具有设备</td>
            <td class="content">
                <html:errors property="userIsHaveDivA"/>
                <eoms:comboBox name="userIsHaveDivA" id="userIsHaveDivA"
                               defaultValue="${transferspeciallineForm.userIsHaveDivA}" initDicId="1012309"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">A是否需要移动采购</td>
            <td class="content">
                <html:errors property="isNeedBuyA"/>
                <eoms:comboBox name="isNeedBuyA" id="isNeedBuyA" defaultValue="${transferspeciallineForm.isNeedBuyA}"
                               initDicId="1012309"
                               alt="allowBlank:true" styleClass="select-class" onchange="changeOperateA(this.value);"/>
            </td>
        </tr>
        </tbody>
        <tbody id="NeededA" style="display:none">
        <tr>
            <td class="label">A需要购买的设备</td>
            <td colspan='3'>
                <html:errors property="theDevNeededA"/>
                <html:textarea property="theDevNeededA" styleId="theDevNeededA" styleClass="textarea max"
                               alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        <tr height="20px">
        </tr>
        <tbody id='customInfoZ'>
        <tr>
            <td class="label">Z客户端标准地址</td>
            <td colspan='3'>
                <html:text property="userStardAddZ" styleId="userStardAddZ" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">Z客户位置经度</td>
            <td class="content">
                <html:errors property="userSiteAZ"/>
                <html:text property="userSiteAZ" styleId="userSiteAZ" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">Z客户位置纬度</td>
            <td class="content">
                <html:errors property="userSiteHZ"/>
                <html:text property="userSiteHZ" styleId="userSiteHZ" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">用户端设备端口类型</td>
            <td class="content">
                <html:errors property="portZBDeviceType"/>
                <eoms:comboBox name="portZBDeviceType" id="portZBDeviceType"
                               defaultValue="${transferspeciallineForm.portZBDeviceType}" initDicId="1012304"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">Z建设方式</td>
            <td class="content">
                <html:errors property="buildMethodZ"/>
                <eoms:comboBox name="buildMethodZ" id="buildMethodZ"
                               defaultValue="${transferspeciallineForm.buildMethodZ}" initDicId="1012308"
                               alt="allowBlank:true" styleClass="select-class"/>

            </td>
        </tr>
        <tr>
            <td class="label">Z客户端是否具有设备</td>
            <td class="content">
                <html:errors property="userIsHaveDivZ"/>
                <eoms:comboBox name="userIsHaveDivZ" id="userIsHaveDivZ"
                               defaultValue="${transferspeciallineForm.userIsHaveDivZ}" initDicId="1012309"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">Z是否需要移动采购</td>
            <td class="content">
                <html:errors property="isNeedBuyZ"/>
                <eoms:comboBox name="isNeedBuyZ" id="isNeedBuyZ" defaultValue="${transferspeciallineForm.isNeedBuyZ}"
                               initDicId="1012309"
                               alt="allowBlank:true" styleClass="select-class" onchange="changeOperateZ(this.value);"/>
            </td>
        </tr>
        </tbody>
        <tbody id="NeededZ" style="display:none">
        <tr>
            <td class="label">Z需要购买的设备</td>
            <td colspan='3'>
                <html:errors property="theDevNeededZ"/>
                <html:textarea property="theDevNeededZ" styleId="theDevNeededZ" styleClass="textarea max"
                               alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        <tr>
            <td>勘察资源</td>
            <td><input type="button" onclick="clickweb();" class="btn" value="启动管线管理系统客户端"/></td>
        </tr>
    </table>
    <%} %>
    <br>
    <%if (sheetType.equals("businessImplement") || taskName.equals("AccessTask") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>
    <table class="formTable">
        <caption>接入点勘查信息</caption>
        <tbody id='interfaceInfoA'>
        <tr>
            <td class="label">A接入点机房</td>
            <td class="content">
                <html:errors property="apointComputHouseName"/>
                <html:text property="apointComputHouseName" styleId="apointComputHouseName" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">A接入点地址</td>
            <td class="content">
                <html:errors property="interfacePointAddA"/>
                <html:text property="interfacePointAddA" styleId="interfacePointAddA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A接入点站点名称（接入基站）</td>
            <td colspan="3">
                <html:errors property="interfaceSiteNameA"/>
                <html:text property="interfaceSiteNameA" styleId="interfaceSiteNameA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A光纤设备名称</td>
            <td class="content">
                <html:errors property="fiberEquipNameA"/>
                <html:text property="fiberEquipNameA" styleId="fiberEquipNameA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">A光纤设备编号</td>
            <td class="content">
                <html:errors property="fiberEquipCodeA"/>
                <html:text property="fiberEquipCodeA" styleId="fiberEquipCodeA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A纤芯个数</td>
            <td class="content">
                <html:errors property="fiberAcount"/>
                <html:text property="fiberAcount" styleId="fiberAcount" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">A接入点类型</td>
            <td class="content">
                <html:errors property="interfacePointTypeA"/>
                <eoms:comboBox name="interfacePointTypeA" id="interfacePointTypeA"
                               defaultValue="${transferspeciallineForm.interfacePointTypeA}" initDicId="1012316"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">A光缆路由描述</td>
            <td colspan="3">
                <html:errors property="fiberAroute"/>
                <textarea name="fiberAroute" id="fiberAroute" class="textarea max"
                          alt="allowBlank:true">${transferspeciallineForm.fiberAroute}</textarea>
            </td>
        </tr>
        <%if (taskName.equals("AccessTask")) {%>
        <tr>
            <td class="label">A端接入点勘察</td>
            <td colspan="3">
                <a style="cursor:hand;color:darkbule"
                   onclick="javascript:popupIrmsPreSurveyA('${transferspeciallineForm.portADetailAdd}')">点击进行接入点勘察</a>
            </td>
        </tr>
        <%} %>
        </tbody>
        <tr height="20px">
        </tr>
        <tr>
            <tbody id='interfaceInfoZ'>
            <td class="label">Z接入点机房</td>
            <td class="content">
                <html:errors property="zpointComputerHorseName"/>
                <html:text property="zpointComputerHorseName" styleId="zpointComputerHorseName" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">Z接入点地址</td>
            <td class="content">
                <html:errors property="interfacePointAddZ"/>
                <html:text property="interfacePointAddZ" styleId="interfacePointAddZ" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            </tr>
            <tr>
                <td class="label">Z接入点站点名称（接入基站）</td>
                <td colspan="3">
                    <html:errors property="interfaceSiteNameZ"/>
                    <html:text property="interfaceSiteNameZ" styleId="interfaceSiteNameZ" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">Z光纤设备名称</td>
                <td class="content">
                    <html:errors property="fiberEquipNameZ"/>
                    <html:text property="fiberEquipNameZ" styleId="fiberEquipNameZ" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">Z光纤设备编号</td>
                <td class="content">
                    <html:errors property="fiberEquipCodeZ"/>
                    <html:text property="fiberEquipCodeZ" styleId="fiberEquipCodeZ" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">Z纤芯个数</td>
                <td class="content">
                    <html:errors property="fiberZcount"/>
                    <html:text property="fiberZcount" styleId="fiberZcount" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">Z接入点类型</td>
                <td class="content">
                    <html:errors property="interfacePointTypeZ"/>
                    <eoms:comboBox name="interfacePointTypeZ" id="interfacePointTypeZ"
                                   defaultValue="${transferspeciallineForm.interfacePointTypeZ}" initDicId="1012316"
                                   alt="allowBlank:true" styleClass="select-class"/>
                </td>
            </tr>
            <tr>
                <td class="label">Z光缆路由描述</td>
                <td colspan="3">
                    <html:errors property="fiberZroute"/>
                    <textarea name="fiberZroute" id="fiberZroute" class="textarea max"
                              alt="allowBlank:true">${transferspeciallineForm.fiberZroute}</textarea>
                </td>
            </tr>
            <%if (taskName.equals("AccessTask")) {%>
            <tr>
                <td class="label">Z端接入点勘察</td>
                <td colspan="3">
                    <a style="cursor:hand;color:darkbule"
                       onclick="javascript:popupIrmsPreSurveyZ('${transferspeciallineForm.portZDetailAdd}')">点击进行接入点勘察</a>
                </td>
            </tr>
            <%} %>
            </tbody>
    </table>
    <%} %>


    <br>
    <%if (sheetType.equals("businessImplement") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>
    <table class="formTable">
        <caption>传输线路勘查信息</caption>
        <tbody id='transLineInfoA'>
        <tr>
            <td class="label">A最后一公里光缆长度(单位：皮长)</td>
            <td class="content">
                <html:errors property="fiberLengthA"/>
                <html:text property="fiberLengthA" styleId="fiberLengthA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">A光缆产权</td>
            <td class="content">
                <html:errors property="fiberOwnerA"/>
                <eoms:comboBox name="fiberOwnerA" id="fiberOwnerA" defaultValue="${transferspeciallineForm.fiberOwnerA}"
                               initDicId="1012315"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">敷设方式</td>
            <td class="content">
                <html:errors property="buildTypeA"/>
                <textarea name="buildTypeA" id="buildTypeA" class="textarea max"
                          alt="allowBlank:true">${transferspeciallineForm.buildTypeA}</textarea>
            </td>
            <td class="label">A客户端到接入点能否通达</td>
            <td class="content">
                <html:errors property="isOkBetweenUserA"/>
                <eoms:comboBox name="isOkBetweenUserA" id="isOkBetweenUserA"
                               defaultValue="${transferspeciallineForm.isOkBetweenUserA}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class" onchange="changeOperate2A(this.value);"/>
            </td>
        </tr>
        <tr>
            <c:if test="${transferspeciallineForm.isOkBetweenUserA == '101231001'}">
                <td class="label">A不能接入的原因</td>
                <td colspan='3'>
                    <html:errors property="noInputResonA"/>
                    <textarea name="noInputResonA" id="noInputResonA" class="textarea max"
                              alt="allowBlank:true">${transferspeciallineForm.noInputResonA}</textarea>
                </td>
            </c:if>
        </tr>
        </tbody>
        <tbody id='YuanYinA' style="display:none">
        <td class="label">A不能接入的原因</td>
        <td colspan='3'>
            <html:errors property="noInputResonA"/>
            <textarea name="noInputResonA" id="noInputResonA" class="textarea max"
                      alt="allowBlank:true">${transferspeciallineForm.noInputResonA}</textarea>
        </td>
        </tbody>
        </tr>
        <tr height="20px">
        </tr>
        <tbody id='transLineInfoZ'>
        <tr>
            <td class="label">Z最后一公里光缆长度</td>
            <td class="content">
                <html:errors property="fiberLengthZ"/>
                <html:text property="fiberLengthZ" styleId="fiberLengthZ" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">Z光缆产权</td>
            <td class="content">
                <html:errors property="fiberOwnerZ"/>
                <eoms:comboBox name="fiberOwnerZ" id="fiberOwnerZ" defaultValue="${transferspeciallineForm.fiberOwnerZ}"
                               initDicId="1012315"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">敷设方式</td>
            <td class="content">
                <html:errors property="buildTypeZ"/>
                <textarea name="buildTypeZ" id="buildTypeZ" class="textarea max"
                          alt="allowBlank:true">${transferspeciallineForm.buildTypeZ}</textarea>
            </td>
            <td class="label">Z客户端到接入点能否通达</td>
            <td class="content">
                <html:errors property="isOkBetweenUserZ"/>
                <eoms:comboBox name="isOkBetweenUserZ" id="isOkBetweenUserZ"
                               defaultValue="${transferspeciallineForm.isOkBetweenUserZ}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class" onchange="changeOperate2Z(this.value);"/>
            </td>
        </tr>
        <tr>
            <c:if test="${transferspeciallineForm.isOkBetweenUserZ == '101231001'}">
                <td class="label">A不能接入的原因</td>
                <td colspan='3'>
                    <html:errors property="noInputResonZ"/>
                    <textarea name="noInputResonZ" id="noInputResonZ" class="textarea max"
                              alt="allowBlank:true">${transferspeciallineForm.noInputResonZ}</textarea>
                </td>
            </c:if>
        </tr>
        </tbody>
        <tr>
            <tbody id='YuanYinZ' style="display:none">
            <td class="label">Z不能接入的原因</td>
            <td colspan='3'>
                <html:errors property="noInputResonZ"/>
                <textarea name="noInputResonZ" id="noInputResonZ" class="textarea max"
                          alt="allowBlank:true">${transferspeciallineForm.noInputResonZ}</textarea>
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
        <tbody id='transCardInfoA'>
        <tr>
            <td class="label">A传输容量是否满足开通</td>
            <td class="content">
                <html:errors property="isDeviceAllowOpenA"/>
                <html:text property="isDeviceAllowOpenA" styleId="isDeviceAllowOpenA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">A是否需要添加板卡</td>
            <td class="content">
                <html:errors property="isNeedAddCardA"/>
                <eoms:comboBox name="isNeedAddCardA" id="isNeedAddCardA"
                               defaultValue="${transferspeciallineForm.isNeedAddCardA}" initDicId="1012309"
                               alt="allowBlank:true" styleClass="select-class" onchange="needAddCardA(this.value);"/>
            </td>
        </tr>
        <c:if test="${transferspeciallineForm.isNeedAddCardA == '101231002'}">
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
        </c:if>
        </tbody>
        <tbody id='FanKaA' style="display:none">
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
        <tr height="20px">
        </tr>
        <tbody id='transCardInfoZ'>
        <tr>
            <td class="label">Z传输容量是否满足开通</td>
            <td class="content">
                <html:errors property="isDeviceAllowOpenZ"/>
                <html:text property="isDeviceAllowOpenZ" styleId="isDeviceAllowOpenZ" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">Z是否需要添加板卡</td>
            <td class="content">
                <html:errors property="isNeedAddCardZ"/>
                <eoms:comboBox name="isNeedAddCardZ" id="isNeedAddCardZ"
                               defaultValue="${transferspeciallineForm.isNeedAddCardZ}" initDicId="1012309"
                               alt="allowBlank:true" styleClass="select-class" onchange="needAddCardZ(this.value);"/>
            </td>
        </tr>
        <c:if test="${transferspeciallineForm.isNeedAddCardZ == '101231002'}">
            <tr>
                <td class="label">板卡类型</td>
                <td class="content">
                    <html:errors property="cardTypeZ"/>
                    <html:text property="cardTypeZ" styleId="cardTypeZ" styleClass="text medium" alt="allowBlank:true"/>
                </td>
                <td class="label">板卡数量</td>
                <td class="content">
                    <html:errors property="cardNumZ"/>
                    <html:text property="cardNumZ" styleId="cardNumZ" styleClass="text medium" alt="allowBlank:true"/>
                </td>
            </tr>
        </c:if>
        </tbody>
        <tbody id='FanKaZ' style="display:none">
        <tr>
            <td class="label">Z板卡类型</td>
            <td class="content">
                <html:errors property="cardTypeZ"/>
                <html:text property="cardTypeZ" styleId="cardTypeZ" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">Z板卡数量</td>
            <td class="content">
                <html:errors property="cardNumZ"/>
                <html:text property="cardNumZ" styleId="cardNumZ" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        </tbody>
        <br>
    </table>
    <%} %>

    <%if (sheetType.equals("businessImplement") && !taskName.equals("ImplementDealTask")) { %>
    <table class="formTable">
        <caption>最后一公里相关信息</caption>
        <tbody id='lastInfoA'>
        <tr>
            <td class="label">A是否熔接</td>
            <td class="content">
                <html:errors property="isGetInterfaceA"/>
                <eoms:comboBox name="isGetInterfaceA" id="isGetInterfaceA"
                               defaultValue="${transferspeciallineForm.isGetInterfaceA}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">A熔接序号</td>
            <td colspan="3">
                <html:errors property="getInterfaceNoA"/>
                <html:text property="getInterfaceNoA" styleId="getInterfaceNoA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A最后一公里处理意见</td>
            <td class="content" colspan="3">
                <textarea name="theLastOpinionA" id="theLastOpinionA" class="textarea max"
                          alt="allowBlank:true">${transferspeciallineForm.theLastOpinionA}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">工程信息</td>
            <td colspan="3">
                <a style="cursor:hand;color:darkbule" onclick="javascript:popupProjectInfo()">导入工程信息</a>
            </td>
        </tr>
        </tbody>
        <tr height="20px">
        </tr>
        <tbody id='lastInfoZ'>
        <tr>
            <td class="label">Z是否熔接</td>
            <td class="content">
                <html:errors property="isGetInterfaceZ"/>
                <eoms:comboBox name="isGetInterfaceZ" id="isGetInterfaceZ"
                               defaultValue="${transferspeciallineForm.isGetInterfaceZ}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">Z熔接序号</td>
            <td colspan="3">
                <html:errors property="getInterfaceNoZ"/>
                <html:text property="getInterfaceNoZ" styleId="getInterfaceNoZ" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">Z最后一公里处理意见</td>
            <td class="content" colspan="3">
                <textarea name="theLastOpinionZ" id="theLastOpinionZ" class="textarea max"
                          alt="allowBlank:true">${transferspeciallineForm.theLastOpinionZ}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">工程信息</td>
            <td colspan="3">
                <a style="cursor:hand;color:darkbule" onclick="javascript:popupIrmsPreSurvey()">导入工程信息</a>
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
    <br/>
    <input type="hidden" name="deleted" value="0"/>
    <input type="button" styleClass="button" onclick="check();" value="保存"/>
    <input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">
</html:form>

<!-- footer_eoms.jsp end-->
