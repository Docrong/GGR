<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%
    String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
    if (orderId.equals(""))
        orderId = StaticMethod.nullObject2String(request.getAttribute("orderId"));
    System.out.println("@orderid=====" + orderId);
    String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
    if (taskName.equals(""))
        taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("@@taskName==" + taskName);
    String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
    String duanXinCaiXinType = StaticMethod.nullObject2String(request.getAttribute("duanXinCaiXinType"));
    String duanXinCaiXinShowType = StaticMethod.nullObject2String(request.getAttribute("duanXinCaiXinShowType"));
    System.out.println("@@sheetType==" + sheetType);
    System.out.println("@@duanXinCaiXinType==" + duanXinCaiXinType);

    String specialtyType = StaticMethod.nullObject2String(request.getAttribute("specialtyType"));
    if (specialtyType.equals("101230105")) {
        duanXinCaiXinType = "CaiXin";
    }
    if (specialtyType.equals("101230106")) {
        duanXinCaiXinType = "DuanXin";
    }

%>


<html:form action="smsspeciallines.do?method=xedit" styleId="smsspeciallineForm" method="post" target="myname">

    <fmt:bundle basename="config/applicationResource-smsspecialline">

        <table class="formTable">
            <caption>
                <div class="header center">
                    <%if (duanXinCaiXinType.equals("CaiXin")) { %>
                    彩信信息
                    <%} else if (duanXinCaiXinType.equals("DuanXin")) {%>短信信息<%} %>

                </div>
            </caption>
            <input type="hidden" id="id" name="id" value="${smsspeciallineForm.id}"/>
            <input type="hidden" id="orderId" name="orderId" value="<%=orderId%>"/>
            <tbody id='BusinessInfo'>
            <%if (duanXinCaiXinType.equals("CaiXin")) { %>
            <tr>
                <td class="label">A端点详细地址*</td>
                <td class="content">
                    <html:text property="portADetailAdd" styleId="portADetailAdd"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.portADetailAdd}"/>
                </td>
                <td class="label">
                    产品编号*
                </td>
                <td class="content">
                    <html:text property="productNum" styleId="productNum"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.productNum}"/>
                </td>
            </tr>
            <tr>
                <td class="label">业务售后SLA维护登记</td>
                <td class="content">

                    <html:text property="businessSLABook" styleId="businessSLABook" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">是否要支持上行彩信流量*</td>
                <td class="content">
                    <eoms:comboBox name="isSupportUpCaiXinStream" id="isSupportUpCaiXinStream"
                                   defaultValue="${smsspeciallineForm.isSupportUpCaiXinStream}" initDicId="1012310"
                                   alt="allowBlank:true" styleClass="select-class"/>
                </td>

            </tr>
            <tr>


                <td class="label">
                    业务范围*
                </td>
                <td class="content">
                    <html:text property="scopeOfBusiness" styleId="scopeOfBusiness"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.scopeOfBusiness}"/>
                </td>
                <td class="label">
                    使用协议
                </td>
                <td class="content">
                    <eoms:comboBox name="useAccord" id="useAccord" defaultValue="${smsspeciallineForm.useAccord}"
                                   initDicId="1013503"
                                   alt="allowBlank:true" styleClass="select-class"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    终端IP地址类型*
                </td>
                <td class="content">
                    <eoms:comboBox name="ipAdressType" id="ipAdressType"
                                   defaultValue="${smsspeciallineForm.ipAdressType}" initDicId="1012309"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>

                <td class="label">
                    终端IP地址
                </td>
                <td class="content">
                    <html:text property="ipAdress" styleId="ipAdress"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.ipAdress}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    接入的方式*
                </td>
                <td class="content">

                    <eoms:comboBox name="cableModem" id="cableModem" defaultValue="${smsspeciallineForm.cableModem}"
                                   initDicId="1013504"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>


                <td class="label">
                    业务流向限制
                </td>
                <td class="content">
                    <eoms:comboBox name="businessLimit" id="businessLimit"
                                   defaultValue="${smsspeciallineForm.businessLimit}" initDicId="1013505"
                                   alt="allowBlank:true" styleClass="select-class"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    限制发送和接收范围
                </td>
                <td class="content">
                    <html:text property="limitSendAndRecScope" styleId="limitSendAndRecScope"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.limitSendAndRecScope}"/>
                </td>

                <!--
		<td class="label">
			主机地址
		</td>
		 <td class="content">
				  	 <eoms:comboBox name="businessLimit" id="businessLimit" 
				  	       initDicId="1013502" defaultValue="${smsspeciallineForm.businessLimit}" alt="allowBlank:false" />
				  </td>	-->
            </tr>
            <tr>
                <td class="label">
                    主机地址
                </td>
                <td class="content">
                    <html:text property="hostAddress" styleId="hostAddress"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.hostAddress}"/>
                </td>
                <td class="label">
                    是否接入MISC/DSMP*
                </td>
                <td class="content">
                    <eoms:comboBox name="ifCable" id="ifCable" defaultValue="${smsspeciallineForm.ifCable}"
                                   initDicId="1012310"
                                   alt="allowBlank:false" styleClass="select-class"/>


                </td>

            </tr>
            <tr>
                <td class="label">
                    计费类型*
                </td>
                <td class="content">

                    <eoms:comboBox name="billingType" id="billingType"
                                   initDicId="1013501" defaultValue="${smsspeciallineForm.billingType}"
                                   alt="allowBlank:false"/>
                </td>

                <td class="label">
                    信息费*
                </td>
                <td class="content">
                    <html:text property="informationFeeds" styleId="informationFeeds"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.informationFeeds}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    地域制作范围
                </td>
                <td class="content">
                    <html:text property="scopeMakeScope" styleId="scopeMakeScope"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.scopeMakeScope}"/>
                </td>

                <td class="label">
                    时间制作范围
                </td>
                <td class="content">
                    <html:text property="timeMakeScope" styleId="timeMakeScope"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.timeMakeScope}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    接入网关带宽
                </td>
                <td class="content">
                    <html:text property="inputNetWayBandWidth" styleId="inputNetWayBandWidth"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.inputNetWayBandWidth}"/>
                </td>

                <td class="label">
                    最大链接数
                </td>
                <td class="content">
                    <html:text property="computerAdd" styleId="computerAdd"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.computerAdd}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    最大下发流量
                </td>
                <td class="content">
                    <html:text property="lagerDownStream" styleId="lagerDownStream"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.lagerDownStream}"/>
                </td>

                <td class="label">
                    最大上行流量
                </td>
                <td class="content">
                    <html:text property="lagerUptStream" styleId="lagerUptStream"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.lagerUptStream}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    企业流控优先级
                </td>
                <td class="content">
                    <html:text property="rightModule" styleId="rightModule"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.rightModule}"/>
                </td>
                <td class="label">
                    端口速率是否自适应下调
                </td>
                <td class="content">
                    <eoms:comboBox name="nameSheetMethod" id="nameSheetMethod"
                                   defaultValue="${smsspeciallineForm.nameSheetMethod}" initDicId="1012310"
                                   alt="allowBlank:true" styleClass="select-class"/>


                </td>

            </tr>
            <!--
	<tr>		
		
		<td class="label">
			上行URL
		</td>
		<td class="content">
			<html:text property="upUrl" styleId="upUrl"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.upUrl}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			ProvisionURL
		</td>
		<td class="content">
			<html:text property="provisionURL" styleId="provisionURL"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.provisionURL}" />
		</td>
	

	</tr>



	<tr>
		<td class="label">
			业务名称
		</td>
		<td class="content">
			<html:text property="businessName" styleId="businessName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.businessName}" />
		</td>
	
		<td class="label">
			业务代码
		</td>
		<td class="content">
			<html:text property="businessCode" styleId="businessCode"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.businessCode}" />
		</td>
	</tr>
 -->


            <%} else if (duanXinCaiXinType.equals("DuanXin")) {%>
            <tr>
                <td class="label">A端点详细地址*</td>
                <td colspan='3'>
                    <html:text property="portADetailAdd" styleId="portADetailAdd"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.portADetailAdd}"/>
                </td>
                <!--
 		<td class="label">
			产品编号
		</td>
		<td class="content">
			<html:text property="productNum" styleId="productNum"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.productNum}" />
		</td> -->
            </tr>
            <tr>
                <td class="label">产品类型</td>
                <td class="content">
                    <html:text property="productType" styleId="productType"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.productType}"/>
                </td>
                <td class="label">
                    产品实例名称
                </td>
                <td class="content">
                    <html:text property="productInstanceName" styleId="productInstanceName"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${smsspeciallineForm.productInstanceName}"/>
                </td>
            </tr>

            <tr>
                <td class="label">业务售后SLA维护登记</td>
                <td class="content">

                    <html:text property="businessSLABook" styleId="businessSLABook" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">接入设备编号或名称</td>
                <td class="content">

                    <html:text property="inputDevNoAndName" styleId="inputDevNoAndName" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">客户占用接入设备端口</td>
                <td class="content">

                    <html:text property="userUseDevPort" styleId="userUseDevPort" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">接入设备端口状态</td>
                <td class="content">

                    <html:text property="inputDevPortStatue" styleId="inputDevPortStatue" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>

            <tr>
                <td class="label">终端IP地址</td>
                <td class="content">

                    <html:text property="ipAdress" styleId="ipAdress" styleClass="text medium" alt="allowBlank:true"/>
                </td>
                <td class="label">终端IP地址类型*</td>
                <td>
                    <eoms:comboBox name="ipAdressType" id="ipAdressType"
                                   defaultValue="${smsspeciallineForm.ipAdressType}" initDicId="1012309"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>

            </tr>
            <tr>
                <td class="label">协议*</td>
                <td>
                    <eoms:comboBox name="protocolDuanXin" id="protocolDuanXin"
                                   defaultValue="${smsspeciallineForm.protocolDuanXin}" initDicId="1013507"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>
                <td class="label">品牌范围*</td>
                <td>
                    <eoms:comboBox name="brandScope" id="brandScope" defaultValue="${smsspeciallineForm.brandScope}"
                                   initDicId="1013508"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>

            </tr>
            <tr>
                <td class="label">
                    是否接入DSMP*
                </td>
                <td class="content">
                    <eoms:comboBox name="isinputDSMP" id="isinputDSMP" defaultValue="${smsspeciallineForm.isinputDSMP}"
                                   initDicId="1012310"
                                   alt="allowBlank:false" styleClass="select-class"/>


                </td>
                <td class="label">
                    接入方式*
                </td>
                <td class="content">
                    <eoms:comboBox name="inputMethodDuanXin" id="inputMethodDuanXin"
                                   defaultValue="${smsspeciallineForm.inputMethodDuanXin}" initDicId="1013504"
                                   alt="allowBlank:false" styleClass="select-class"/>


                </td>
            </tr>
            <tr>
                <td class="label">限制发送和接收范围</td>
                <td class="content">

                    <html:text property="limitSendAndRecScope" styleId="limitSendAndRecScope" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">
                    接入等级号
                </td>
                <td class="content">
                    <eoms:comboBox name="inputLevelNumber" id="inputLevelNumber"
                                   defaultValue="${smsspeciallineForm.inputLevelNumber}" initDicId="1013509"
                                   alt="allowBlank:true" styleClass="select-class"/>


                </td>
            </tr>
            <tr>

                <td class="label">
                    品牌范围
                </td>
                <td class="content">
                    <eoms:comboBox name="brandScope" id="brandScope" defaultValue="${smsspeciallineForm.brandScope}"
                                   initDicId="1013508"
                                   alt="allowBlank:true" styleClass="select-class"/>


                </td>

                <td class="label">短信签名</td>
                <td class="content">

                    <html:text property="duanxinSing" styleId="duanxinSing" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">企业中文实名</td>
                <td class="content">

                    <html:text property="enterpriseCNName" styleId="enterpriseCNName" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">企业英文实名</td>
                <td class="content">

                    <html:text property="enterpriseENname" styleId="enterpriseENname" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">地域制作范围</td>
                <td class="content">

                    <html:text property="scopeMakeScope" styleId="scopeMakeScope" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">时间制作范围</td>
                <td class="content">

                    <html:text property="timeMakeScope" styleId="timeMakeScope" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            </tr>
            <tr>
                <td class="label">接入点</td>
                <td class="content">

                    <html:text property="inputDot" styleId="inputDot" styleClass="text medium" alt="allowBlank:true"/>
                </td>
                <td class="label">主机IP地址</td>
                <td class="content">

                    <html:text property="computerIpAdd" styleId="computerIpAdd" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">接入网关带宽</td>
                <td class="content">

                    <html:text property="inputNetWayBandWidth" styleId="inputNetWayBandWidth" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">端口速率自适应下调</td>
                <td class="content">
                    <eoms:comboBox name="portSpeed" id="portSpeed" defaultValue="${smsspeciallineForm.portSpeed}"
                                   initDicId="1012310"
                                   alt="allowBlank:true" styleClass="select-class"/>
                </td>
            </tr>
            <tr>
                <td class="label">最大下发流量</td>
                <td class="content">

                    <html:text property="lagerDownStream" styleId="lagerDownStream" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">最大上行流量</td>
                <td class="content">

                    <html:text property="lagerUptStream" styleId="lagerUptStream" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">鉴权模式</td>
                <td class="content">

                    <html:text property="rightModule" styleId="rightModule" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">话单位长</td>
                <td class="content">

                    <html:text property="feeBit" styleId="feeBit" styleClass="text medium" alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">黑白名单设置方式</td>
                <td colspan="3">

                    <html:text property="nameSheetMethod" styleId="nameSheetMethod" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>

            </tr>
            <%} %>
            </tbody>
        </table>

        <%
            if (sheetType.equals("businessImplement")
                    || sheetType.equals("businessImplementSms")
                    || taskName.equals("UserTask")
                    || taskName.equals("AccessTask")
                    || taskName.equals("CityTask")
                    || taskName.equals("CaiXinTask")
                    || taskName.equals("TransfereTask")
                    || taskName.equals("TransferlTask")
                    || taskName.equals("MakeTask")
                    || taskName.equals("AuditTask")
                    || taskName.equals("HandleTask")


                    || taskName.equals("HoldTask")) {
        %>
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
                    <html:text property="userSiteAA" styleId="userSiteAA" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">客户位置纬度</td>
                <td class="content">
                    <html:errors property="userSiteHA"/>
                    <html:text property="userSiteHA" styleId="userSiteHA" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">用户端设备端口类型</td>
                <td class="content">
                    <html:errors property="portABDeviceType"/>
                    <eoms:comboBox name="portABDeviceType" id="portABDeviceType"
                                   defaultValue="${smsspeciallineForm.portABDeviceType}" initDicId="1012304"
                                   alt="allowBlank:true" styleClass="select-class"/>
                </td>
                <td class="label">建设方式</td>
                <td class="content">
                    <html:errors property="buildMethodA"/>
                    <eoms:comboBox name="buildMethodA" id="buildMethodA"
                                   defaultValue="${smsspeciallineForm.buildMethodA}" initDicId="1012317"
                                   alt="allowBlank:false" styleClass="select-class"/>

                </td>
            </tr>
            <tr>
                <td class="label">客户端是否具有设备</td>
                <td class="content">
                    <html:errors property="userIsHaveDivA"/>
                    <eoms:comboBox name="userIsHaveDivA" id="userIsHaveDivA"
                                   defaultValue="${smsspeciallineForm.userIsHaveDivA}" initDicId="1012310"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>
                <td class="label">是否需要移动采购</td>
                <td class="content">
                    <html:errors property="isNeedBuyA"/>
                    <eoms:comboBox name="isNeedBuyA" id="isNeedBuyA" defaultValue="${smsspeciallineForm.isNeedBuyA}"
                                   initDicId="1012310"
                                   alt="allowBlank:false" styleClass="select-class"
                                   onchange="changeOperate(this.value);"/>
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
        <%
            if (sheetType.equals("businessImplement")
                    || sheetType.equals("businessImplementSms")
                    || taskName.equals("CaiXinTask")
                    || taskName.equals("AccessTask")
                    || taskName.equals("CityTask")
                    || taskName.equals("TransfereTask")
                    || taskName.equals("TransferlTask")
                    || taskName.equals("MakeTask")
                    || taskName.equals("AuditTask")
                    || taskName.equals("HandleTask")


                    || taskName.equals("HoldTask")) {
        %>
        <table class="formTable">
            <caption>接入点勘查信息</caption>
            <tbody id='interfaceInfo'>
            <%if (taskName.equals("AccessTask")) {%>
            <tr>
                <td class="label">接入点勘察</td>
                <td colspan="3">
                    <a style="cursor:hand;color:darkbule"
                       onclick="javascript:popupIrmsPreSurvey('${smsspeciallineForm.portADetailAdd}')">点击进行接入点勘察</a>
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
                                   defaultValue="${smsspeciallineForm.interfacePointTypeA}" initDicId="1012316"
                                   alt="allowBlank:true" styleClass="select-class"/>
                </td>
            </tr>
            <tr>
                <td class="label">A光缆路由描述</td>
                <td colspan="3">
                    <html:errors property="fiberAroute"/>
                    <textarea name="fiberAroute" id="fiberAroute" class="textarea max"
                              alt="allowBlank:true">${smsspeciallineForm.fiberAroute}</textarea>
                </td>
            </tr>
            </tbody>

        </table>
        <%} %>


        <br>
        <%
            if (sheetType.equals("businessImplement")
                    || sheetType.equals("businessImplementSms")
                    || taskName.equals("CaiXinTask")
                    || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) {
        %>
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
                    <eoms:comboBox name="fiberOwnerA" id="fiberOwnerA" defaultValue="${smsspeciallineForm.fiberOwnerA}"
                                   initDicId="1012315"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>
            </tr>
            <tr>
                <td class="label">敷设方式*</td>
                <td colspan="3">
                    <html:errors property="buildTypeA"/>
                    <textarea name="buildTypeA" id="buildTypeA" class="textarea max"
                              alt="allowBlank:true">${smsspeciallineForm.buildTypeA}</textarea>
                </td>
            </tr>

            <tr>
                <td class="label">客户端到接入点能否通达*</td>
                <td colspan="3">
                    <html:errors property="isOkBetweenUserA"/>
                    <eoms:comboBox name="isOkBetweenUserA" id="isOkBetweenUserA"
                                   defaultValue="${smsspeciallineForm.isOkBetweenUserA}" initDicId="1012310"
                                   alt="allowBlank:false" styleClass="select-class"
                                   onchange="changeOperate2(this.value);"/>
                </td>
            </tr>
            <tr>
            <tbody id='YuanYin' style="display:none">
            <td class="label">不能接入的原因*</td>
            <td colspan='3'>
                <html:errors property="noInputResonA"/>
                <textarea name="noInputResonA" id="noInputResonA" class="textarea max"
                          alt="allowBlank:true">${smsspeciallineForm.noInputResonA}</textarea>
            </td>
            </tbody>
            </tr>
            </tbody>

        </table>
        <%} %>
        <br>

        <%
            if (sheetType.equals("businessImplement")
                    || sheetType.equals("businessImplementSms")
                    || taskName.equals("TransfereTask") ||
                    taskName.equals("ProjectDealTask") ||
                    taskName.equals("CityNetTask") ||

                    taskName.equals("CaiXinTask") ||
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
                                   defaultValue="${smsspeciallineForm.isNeedAddCardA}" initDicId="1012310"
                                   alt="allowBlank:true" styleClass="select-class"
                                   onchange="needAddCardA(this.value);"/>
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
            if (taskName.equals("ImplementDealTask")
                    || sheetType.equals("businessImplementSms")
                    || taskName.equals("CityTask") ||
                    taskName.equals("ProjectDealTask") ||
                    taskName.equals("CityNetTask") ||
                    taskName.equals("CaiXinTask") ||
                    taskName.equals("TrasitionTask") ||
                    taskName.equals("BusinessTestTask") ||
                    taskName.equals("DredgeAffirmTask") ||
                    taskName.equals("HoldTask")
            ) {
        %>
        <table class="formTable">
            <caption>接入勘察信息</caption>
            <tbody id='cityInfo'>
            <tr>
                <td class="label">短彩信接入站点名称*</td>
                <td class="content">
                    <html:errors property="siteNameZ"/>
                    <html:text property="siteNameZ" styleId="siteNameZ" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
                <td class="label">短彩信接入设备名称</td>
                <td class="content">
                    <html:errors property="portZBDeviceName"/>
                    <html:text property="portZBDeviceName" styleId="portZBDeviceName" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label">短彩信接入设备端口</td>
                <td class="content">
                    <html:errors property="portZBDevicePort"/>
                    <html:text property="portZBDevicePort" styleId="portZBDevicePort" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">短彩信接入详细地址</td>
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
                                   defaultValue="${smsspeciallineForm.isGetInterfaceA}" initDicId="1012310"
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
                              alt="allowBlank:false">${smsspeciallineForm.theLastOpinionA}</textarea>
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
            if (
                    sheetType.equals("businessImplement") && (
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
                    <html:text property="circuitName" styleId="circuitName" styleClass="text medium"
                               alt="allowBlank:true"/>
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
            <tbody id='BusinessInfo2'>
            <tr>
                <td class="label">
                    说明
                </td>
                <td colspan="3">
                    <html:errors property="illustrate"/>
                    <textarea name="illustrate" id="illustrate" class="textarea max"
                              alt="allowBlank:true">${smsspeciallineForm.illustrate}</textarea>
                </td>


            </tr>
            </tbody>

        </table>

    </fmt:bundle>

    <input type="button" styleClass="button" onclick="check();" value="保存"/>
    <input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">
    <html:hidden property="id" value="${smsspeciallineForm.id}"/>
</html:form>
<script type="text/javascript">
    function initPage() {
        v = new eoms.form.Validation({form: 'smsspeciallineForm'});
        var taskName = "<%=taskName%>";

        if (taskName != "" && taskName != "AcceptTask" && taskName != "ImplementDealTask") {
            eoms.form.readOnlyArea('BusinessInfo');
            eoms.form.readOnlyArea('BusinessInfo2');
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
        } else if (taskName == "CityTask" || taskName == "CityNetTask" || taskName == "CaiXinTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
            eoms.form.readOnlyArea('transLineInfo');
            eoms.form.readOnlyArea('transCardInfo');
        } else if (taskName == "ProjectDealTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
            eoms.form.readOnlyArea('transLineInfo');
            eoms.form.readOnlyArea('transCardInfo');
            eoms.form.readOnlyArea('cityInfo');
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
        v1 = new eoms.form.Validation({form: 'smsspeciallineForm'});
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
        var urlstr = "http://10.131.62.59:8080/webmaster/jsp/res/import/custexcelimport/custexcelimport.jsp?includeSpecialties=ProjectResource&userName=admin&prodCode=${gprsspeciallineForm.id}";
        var params = window.showModalDialog(urlstr, "", "dialogWidth:" + screen.width * 1.0 + "px;dialogHeight:" + screen.height * 0.9 + "px");
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>