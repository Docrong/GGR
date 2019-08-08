<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.businessupport.product.webapp.form.LanguageSpecialLineForm" %>
<%
    String isEdit = StaticMethod.nullObject2String(request.getAttribute("isEdit"));

    String taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
    Object languagespeciallineForm = request.getAttribute("languagespeciallineForm");
    System.out.println("=====languagespeciallineForm:" + languagespeciallineForm);
    if (taskName.equals(""))
        taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
    System.out.println("@@@taskName==form=ProjectDealTask==" + taskName);
    String addr = StaticMethod.nullObject2String(request.getParameter("addr"));
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userName = sessionform.getUserid();
    String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
    System.out.println("@@sheetType=" + sheetType);

    String isShowLanguage = StaticMethod.nullObject2String(request.getAttribute("isShowLanguage"));


    //if(!isShowLanguage==null || !isShowLanguage.equals("")){
    //	String orderId = StaticMethod.nullObject2String(request.getAttribute("orderId"));
    //}else{

    String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
    if (orderId.equals(""))
        orderId = StaticMethod.nullObject2String(request.getAttribute("orderId"));
    //}

    String taskType = StaticMethod.nullObject2String(request.getParameter("taskType"));
    System.out.println("@||isShowLanguage" + isShowLanguage);
    System.out.println("@||taskName" + taskName);
    System.out.println("@||taskType" + taskType);


    String url = "";
    String head = "http://10.25.2.74:8899";
    String host = StaticMethod.nullObject2String(request.getRequestURL());
    if (host.indexOf("10.131.62") < 0) {
        head = "http://10.25.2.74:8899";
    }
%>
<script type="text/javascript">
    function check() {
        v1 = new eoms.form.Validation({form: 'languagespeciallineForm'});
        if (v1.check()) {
            document.forms[0].submit();
            window.close();
        } else {
            return false;
        }
    }

    window.name = "myname";
</script>
<caption>
    <div class="header center"><%if (!isShowLanguage.equals("true")) { %>
        语音专线
        <%} else {%>传输专线<%} %>
    </div>
</caption>
<html:form action="languagespeciallines.do?method=xedit" method="post" styleId="languagespeciallineForm"
           target="myname">
    <br>
    <table class="formTable">
        <input type="hidden" id="id" name="id" value="${languagespeciallineForm.id}"/>
        <input type="hidden" id="orderId" name="orderId" value="<%=orderId%>"/>
    </table>
    <br>
    <table class="formTable">
        <caption>平台呼入</caption>
        <tbody id='BusinessInfo'>
        <tr>
            <td class="label">接入号码</td>
            <td class="content">
                <html:errors property="inputNumber"/>
                <html:text property="inputNumber" styleId="inputNumber" styleClass="text medium" alt="allowBlank:true"/>
            </td>
            <td class="label">位长</td>
            <td class="content">
                <html:errors property="bitLength"/>
                <html:text property="bitLength" styleId="bitLength" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A端点详细地址</td>
            <td colspan='3'>
                <html:errors property="portADetailAdd"/>
                <html:text property="portADetailAdd" styleId="portADetailAdd" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <!--
       <td class="label">Z端点详细地址</td>
      <td class="content">
	  	<html:errors property="portZDetailAdd"/>
        <html:text property="portZDetailAdd" styleId="portZDetailAdd"  styleClass="text medium" alt="allowBlank:true"/>
      </td>
      -->
        </tr>
        <tr>
            <td class="label">业务类型</td>
            <td colspan='3'>
                <html:errors property="businessType"/>
                <eoms:comboBox name="businessType" id="businessType"
                               defaultValue="${languagespeciallineForm.businessType}" initDicId="1013309"
                               alt="allowBlank:false" styleClass="select-class"/>

            </td>
        </tr>

        <tr>

        <tr>
            <td class="label">主叫用户开放品牌</td>
            <td colspan="3">
                <div id="factoryview" class="hide"></div>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showFactory" style="height:50px"
                          id="${sheetPageName}showFactory"
                          alt="allowBlank:false,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens
                        items="${languagespeciallineForm.callUserOpenBrand}" delims="," var="callUserOpenBrand"
                        varStatus="status"><eoms:id2nameDB id="${callUserOpenBrand}"
                                                           beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}callUserOpenBrand" id="${sheetPageName}callUserOpenBrand"
                       value="${languagespeciallineForm.callUserOpenBrand}"/>
            </td>
        </tr>
        <tr>
            <td class="label">主叫用户归属地*</td>
            <td colspan="3">
                <div id="areaview" class="hide"></div>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showDept" style="height:50px"
                          id="${sheetPageName}showDept"
                          alt="allowBlank:false,maxLength:50,vtext:'请填入地市，最多输入50字符'"><c:forTokens
                        items="${languagespeciallineForm.callUserLocalZone}" delims="," var="callUserLocalZone"
                        varStatus="status"><eoms:id2nameDB id="${callUserLocalZone}"
                                                           beanId="tawSystemAreaDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}callUserLocalZone" id="${sheetPageName}callUserLocalZone"
                       value="${languagespeciallineForm.callUserLocalZone}"/>
            </td>
        </tr>
        <tr>
            <td class="label">业务开放地*</td>
            <td colspan="3">
                <div id="areaview2" class="hide"></div>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showDept2" style="height:50px"
                          id="${sheetPageName}showDept2"
                          alt="allowBlank:false,maxLength:50,vtext:'请填入地市，最多输入50字符'"><c:forTokens
                        items="${languagespeciallineForm.businessOpenZone}" delims="," var="businessOpenZone"
                        varStatus="status"><eoms:id2nameDB id="${businessOpenZone}"
                                                           beanId="tawSystemAreaDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}businessOpenZone" id="${sheetPageName}businessOpenZone"
                       value="${languagespeciallineForm.businessOpenZone}"/>
            </td>
        <tr>
            <td class="label">拨号方式*</td>
            <td class="content">
                <html:errors property="callNumberType"/>
                <eoms:comboBox name="callNumberType" id="callNumberType"
                               defaultValue="${languagespeciallineForm.callNumberType}" initDicId="1013313"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
            <td class="label">是否开通省内长途来话</td>
            <td class="content">
                <html:errors property="isOpenPronicePhone"/>
                <eoms:comboBox name="isOpenPronicePhone" id="isOpenPronicePhone"
                               defaultValue="${languagespeciallineForm.isOpenPronicePhone}" initDicId="1013301"
                               alt="allowBlank:false" styleClass="select-class"/>

            </td>
        </tr>
        <tr>
            <td class="label">是否开通省际长途来话*</td>
            <td class="content">
                <html:errors property="isOpenProniceBeteen"/>
                <eoms:comboBox name="isOpenProniceBeteen" id="isOpenProniceBeteen"
                               defaultValue="${languagespeciallineForm.isOpenProniceBeteen}" initDicId="1013301"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
            <td class="label">是否开通国际长途来话</td>
            <td class="content">
                <html:errors property="isOpenContryPhone"/>
                <eoms:comboBox name="isOpenContryPhone" id="isOpenContryPhone"
                               defaultValue="${languagespeciallineForm.isOpenContryPhone}" initDicId="1013301"
                               alt="allowBlank:false" styleClass="select-class"/>

            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <table class="formTable">
        <caption>平台呼出</caption>
        <tbody id='BusinessInfo2'>
        <tr>
            <td class="label">客户主叫号码*</td>
            <td class="content">
                <html:errors property="userCallNumber"/>
                <html:text property="userCallNumber" styleId="userCallNumber" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">主叫号码是否透传*</td>
            <td class="content">
                <html:errors property="userCallIsTouChuan"/>
                <eoms:comboBox name="userCallIsTouChuan" id="userCallIsTouChuan"
                               defaultValue="${languagespeciallineForm.userCallIsTouChuan}" initDicId="1013301"
                               alt="allowBlank:false" styleClass="select-class"
                               onchange="onchangelinkIsOkNeed(this.value)"/>

            </td>
        </tr>
        </tbody>
        <tbody id="IsOkNeed" style="display:none">
        <tr>
            <td class="label">主叫号码修改方式</td>
            <td colspan='3'>
                <html:errors property="userCallModifyType"/>
                <html:textarea property="userCallModifyType" styleId="userCallModifyType" styleClass="textarea max"
                               alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        <tbody id='BusinessInfo4'>
        <tr>
            <td class="label">信令方式*</td>
            <td colspan='3'>
                <html:errors property="infoType"/>
                <eoms:comboBox name="infoType" id="infoType" defaultValue="${languagespeciallineForm.infoType}"
                               initDicId="1013303"
                               alt="allowBlank:false" styleClass="select-class"
                               onchange="onchangeinfoType(this.value);"/>
            </td>
        </tr>
        </tbody>
        <tbody id="infoOk" style="display:none">
        <tr>
            <td class="label">信令点编码</td>
            <td class="content">
                <html:errors property="infoPointCode"/>
                <html:text property="infoPointCode" styleId="infoPointCode" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">信令协议</td>
            <td class="content">
                <html:errors property="infoProtocal"/>
                <eoms:comboBox name="infoProtocal" id="infoProtocal"
                               defaultValue="${languagespeciallineForm.infoProtocal}" initDicId="1013304"
                               alt="allowBlank:true" styleClass="select-class"/>

            </td>
        </tr>
        </tbody>

        <tbody id='BusinessInfo3'>
        <tr>
            <td class="label">呼出范围*</td>
            <td colspan="3">
                <div id="factoryview2" class="hide"></div>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showFactory2" style="height:50px"
                          id="${sheetPageName}showFactory2"
                          alt="allowBlank:false,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens
                        items="${languagespeciallineForm.userCallScope}" delims="," var="userCallScope"
                        varStatus="status"><eoms:id2nameDB id="${userCallScope}"
                                                           beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}userCallScope" id="${sheetPageName}userCallScope"
                       value="${languagespeciallineForm.userCallScope}"/>
            </td>
        </tr>


        <tr>
            <td class="label">中继数*</td>
            <td colspan='3'>
                <html:text property="infoBetweenNumber" styleId="infoBetweenNumber" styleClass="text medium"
                           alt="allowBlank:false"/></td>
        </tr>
        <tr>
            <td class="label">计费说明*</td>
            <td colspan='3'>
                <html:errors property="feeDesc"/>
                <html:textarea property="feeDesc" styleId="feeDesc" styleClass="textarea max" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">客户机房地址*</td>
            <td class="content">
                <html:errors property="userComputerHoseAdd"/>
                <html:text property="userComputerHoseAdd" styleId="userComputerHoseAdd" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">客户端设备品牌</td>
            <td class="content">
                <html:errors property="userPointDevBrand"/>
                <html:text property="userPointDevBrand" styleId="userPointDevBrand" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">型号</td>
            <td class="content">
                <html:errors property="userComputerType"/>
                <html:text property="userComputerType" styleId="userComputerType" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">产权</td>
            <td class="content">
                <html:errors property="userComputerRight"/>
                <eoms:comboBox name="userComputerRight" id="userComputerRight"
                               defaultValue="${languagespeciallineForm.userComputerRight}" initDicId="1013315"
                               alt="allowBlank:false" styleClass="select-class"/>

            </td>
        </tr>
        <tr>
            <td class="label">是否网间短号转网内*</td>
            <td colspan='3'>
                <html:errors property="isNumberTrasiNet"/>
                <eoms:comboBox name="isNumberTrasiNet" id="isNumberTrasiNet"
                               defaultValue="${languagespeciallineForm.isNumberTrasiNet}" initDicId="1013301"
                               alt="allowBlank:false" styleClass="select-class"/>

            </td>
        </tr>
        </tbody>

    </table>
    <%
        if (taskType.equals("inputUser") && taskName.equals("LauguageTask")
                || taskName.equals("UserTask")
                || taskName.equals("AccessTask")
                || taskName.equals("TransferlTask")
                || taskName.equals("LauguageTask")
                || taskName.equals("TransfereTask")

                || taskName.equals("MakeTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")


        ) {
    %>
    <table class="formTable">
        <caption>客户端勘查信息</caption>
        <tbody id='customInfo'>
        <tr>
        <tr>
            <td class="label">A端点详细地址</td>
            <td class="content">
                <html:errors property="portADetailAdd"/>
                <html:text property="portADetailAdd" styleId="portADetailAdd" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">用户端设备端口类型</td>
            <td class="content">
                <html:errors property="portABDeviceType"/>
                <eoms:comboBox name="portABDeviceType" id="portABDeviceType"
                               defaultValue="${languagespeciallineForm.portABDeviceType}" initDicId="1012304"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
        </tr>

        </tbody>
        <%
            if (
                    (isShowLanguage.equals("yes") &&

                            taskName.equals("LauguageTask"))
                            || taskName.equals("ProjectDealTask")
                            || taskName.equals("ModifyDesignTask")
                            || taskName.equals("AuditDesignTask")
                            || taskName.equals("TrasitionTask")
                            || taskName.equals("BusinessTestTask")
                            || taskName.equals("DredgeAffirmTask")

                            || taskName.equals("HoldTask")

            ) {
        %>
        <tbody id='customInfo2'>
        <tr>
            <td class="label">城市A*</td>
            <td class="content">
                <html:errors property="cityA"/>
                <html:text property="cityA" styleId="cityA" styleClass="text medium" alt="allowBlank:false"/>
            </td>

            <td class="label">A站点名称</td>
            <td class="content">
                <html:errors property="siteNameA"/>
                <html:text property="siteNameA" styleId="siteNameA" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label">端口A</td>
            <td class="content">
                <html:errors property="portA"/>
                <html:text property="portA" styleId="portA" styleClass="text medium" alt="allowBlank:true"/>
            </td>

            <td class="label">A接口类型及型号*</td>
            <td class="content">
                <html:errors property="portAInterfaceType"/>
                <eoms:comboBox name="portAInterfaceType" id="portAInterfaceType"
                               defaultValue="${languagespeciallineForm.portAInterfaceType}" initDicId="1011015"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
        </tr>


        <td class="label">端点A业务设备名称</td>
        <td class="content">
            <html:errors property="portABDeviceName"/>
            <html:text property="portABDeviceName" styleId="portABDeviceName" styleClass="text medium"
                       alt="allowBlank:true"/>
        </td>
        <td class="label">端点A业务设备端口</td>
        <td class="content">
            <html:errors property="portABDevicePort"/>
            <html:text property="portABDevicePort" styleId="portABDevicePort" styleClass="text medium"
                       alt="allowBlank:true"/>
        </td>
        </tr>
        <tr>

            <td class="label">A客户端联系人</td>
            <td class="content">
                <html:errors property="apointLocalPerson"/>
                <html:text property="apointLocalPerson" styleId="apointLocalPerson" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>

            <td class="label">A客户端联系电话</td>
            <td class="content">
                <html:errors property="portAContactPhone"/>
                <html:text property="portAContactPhone" styleId="portAContactPhone" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        <%} %>
        <!--
    <tr>
      <td class="label">带宽*</td>
      <td class="content">
	  	<html:errors property="bandwidth"/>
        <html:text property="bandwidth" styleId="bandwidth"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
     <td class="label">电路速率*</td>
     <td class="content">
	  	<html:errors property="circuitRate"/>
        <html:text property="circuitRate" styleId="circuitRate"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
    </tr>
    <tr>
    	<td class="label">用户是否有用户网站*</td>
	     <td class="content">
		  	<html:errors property="userIsUserNet"/>
			<eoms:comboBox name="userIsUserNet" id="userIsUserNet" defaultValue="${transferspeciallineForm.userIsUserNet}" initDicId="1012310" 
			  	       alt="allowBlank:true" styleClass="select-class" />
	     </td>
	     <td class="label">用户个性化设备需求*</td>
        <td colspan='3'>
	  	<html:errors property="userSpecifyDevNeed"/>
        <html:text property="userSpecifyDevNeed" styleId="userSpecifyDevNeed"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
    </tr> -->


    </table>
    <br>
    <%} %>

    <%
        if (sheetType.equals("businessImplement")
                || taskName.equals("AccessTask")
                || taskName.equals("TransferlTask")
                || taskName.equals("LauguageTask")
                || taskName.equals("TransfereTask")

                || taskName.equals("TrasitionTask")
                || taskName.equals("MakeTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")
        ) {
    %>
    <table class="formTable">
        <caption>接入点勘查信息</caption>
        <tbody id='interfaceInfo'>
        <%if (taskName.equals("AccessTask")) {%>
        <tr>
            <td class="label">接入点勘察</td>
            <td colspan="3">
                <a style="cursor:hand;color:darkbule"
                   onclick="javascript:popupIrmsPreSurvey('${languagespeciallineForm.portADetailAdd}')">点击进行接入点勘察</a>
            </td>
        </tr>
        <%} %>
        <tr>
            <!--
		<td class="label">A接入点机房</td>
	      <td class="content">
		  	<html:errors property="apointComputHouseName"/>
	        <html:text property="apointComputHouseName" styleId="apointComputHouseName"  styleClass="text medium" alt="allowBlank:true"/>
	      </td> -->
            <td class="label">A接入点地址</td>
            <td colspan='3'>
                <html:errors property="interfacePointAddA"/>
                <html:text property="interfacePointAddA" styleId="interfacePointAddA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A接入点站点名称（接入基站）</td>
            <td colspan='3'>
                <html:errors property="interfaceSiteNameA"/>
                <html:text property="interfaceSiteNameA" styleId="interfaceSiteNameA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <!--
            <td class="label">A接入点设备编码</td>
      <td class="content">
	  	<html:errors property="interfaceEquipCodeA"/>
        <html:text property="interfaceEquipCodeA" styleId="interfaceEquipCodeA"  styleClass="text medium" alt="allowBlank:true"/>
      </td> -->

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
            <td class="label">A纤芯个数*</td>
            <td class="content">
                <html:errors property="fiberAcount"/>
                <html:text property="fiberAcount" styleId="fiberAcount" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">A接入点类型</td>
            <td class="content">
                <html:errors property="interfacePointTypeA"/>
                <eoms:comboBox name="interfacePointTypeA" id="interfacePointTypeA"
                               defaultValue="${languagespeciallineForm.interfacePointTypeA}" initDicId="1012316"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>

        </tr>
        <tr>

            <td class="label">A光缆路由描述*</td>

            <td colspan='3'>
                <html:errors property="fiberAroute"/>
                <textarea name="fiberAroute" id="fiberAroute" class="textarea max"
                          alt="allowBlank:true">${languagespeciallineForm.fiberAroute}</textarea>
            </td>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <%} %>

    <%
        if (sheetType.equals("businessImplement")
                || taskName.equals("TransferlTask")
                || taskName.equals("LauguageTask")
                || taskName.equals("TransfereTask")
                || taskName.equals("MakeTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")
        ) {
    %>
    <table class="formTable">
        <caption>传输线路勘查信息</caption>
        <tbody id='transLineInfo'>
        <tr>
            <td class="label">A最后一公里光缆长度</td>
            <td class="content">
                <html:errors property="fiberLengthA"/>
                <html:text property="fiberLengthA" styleId="fiberLengthA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
            <td class="label">A光缆产权</td>
            <td class="content">
                <html:errors property="fiberOwnerA"/>
                <eoms:comboBox name="fiberOwnerA" id="fiberOwnerA" defaultValue="${languagespeciallineForm.fiberOwnerA}"
                               initDicId="1012315"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">敷设方式*</td>
            <td class="content">
                <html:errors property="buildTypeA"/>
                <html:text property="buildTypeA" styleId="buildTypeA" styleClass="text medium" alt="allowBlank:false"/>
            </td>
            <td class="label">A客户端到接入点能否通达*</td>
            <td class="content">
                <html:errors property="isOkBetweenUserA"/>
                <eoms:comboBox name="isOkBetweenUserA" id="isOkBetweenUserA"
                               defaultValue="${languagespeciallineForm.isOkBetweenUserA}" initDicId="1012310"
                               alt="allowBlank:false" styleClass="select-class" onchange="changeOperate2(this.value);"/>
            </td>
        </tr>
        <tr>
        <tbody id='YuanYin' style="display:none">
        <td class="label">A不能接入的原因*</td>
        <td colspan='3'>
            <html:errors property="noInputResonA"/>
            <textarea name="noInputResonA" id="noInputResonA" class="textarea max"
                      alt="allowBlank:true">${languagespeciallineForm.noInputResonA}</textarea>
        </td>
        </tbody>
        </tr>
        </tbody>
    </table>
    <br>
    <%} %>

    <%
        if (taskName.equals("LauguageTask")
                || taskName.equals("TransfereTask")
                || taskName.equals("MakeTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")
        ) {
    %>
    <table class="formTable">
        <caption>关口局勘查信息</caption>
        <tbody id='cityInfo'>
        <tr>
            <td class="label">关口局业务设备端口*</td>
            <td class="content">
                <html:errors property="portZBDevicePort"/>
                <html:text property="portZBDevicePort" styleId="portZBDevicePort" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">关口局站点名称</td>
            <td class="content">
                <html:errors property="siteNameZ"/>
                <html:text property="siteNameZ" styleId="siteNameZ" styleClass="text medium" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">关口局业务设备名称*</td>
            <td class="content">
                <html:errors property="portZBDeviceName"/>
                <html:text property="portZBDeviceName" styleId="portZBDeviceName" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">关口局端点详细地址</td>
            <td class="content">
                <html:errors property="portZDetailAdd"/>
                <html:text property="portZDetailAdd" styleId="portZDetailAdd" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>


        </tr>
        </tbody>
        <%
            if (
                    (isShowLanguage.equals("yes") && taskName.equals("LauguageTask"))
                            || taskName.equals("ProjectDealTask")
                            || taskName.equals("ModifyDesignTask")
                            || taskName.equals("AuditDesignTask")
                            || taskName.equals("TrasitionTask")
                            || taskName.equals("BusinessTestTask")
                            || taskName.equals("DredgeAffirmTask")

                            || taskName.equals("HoldTask")

            ) {
        %>
        <tbody id='cityInfo2'>
        <tr>
            <td class="label">城市Z*</td>
            <td class="content">
                <html:errors property="cityZ"/>
                <html:text property="cityZ" styleId="cityZ" styleClass="text medium" alt="allowBlank:false"/>
            </td>

        <tr>

            <td class="label">端口Z</td>
            <td class="content">
                <html:errors property="portZ"/>
                <html:text property="portZ" styleId="portZ" styleClass="text medium" alt="allowBlank:true"/>
            </td>

            <td class="label">Z接口类型及型号</td>
            <td class="content">
                <html:errors property="portZInterfaceType"/>
                <eoms:comboBox name="portZInterfaceType" id="portZInterfaceType"
                               defaultValue="${languagespeciallineForm.portZInterfaceType}" initDicId="1011015"
                               alt="allowBlank:false" styleClass="select-class"/>

            </td>
        </tr>
        <tr>
            <td class="label">Z客户端联系人</td>
            <td class="content">
                <html:errors property="zpointLocalPerson"/>
                <html:text property="zpointLocalPerson" styleId="zpointLocalPerson" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>


            <td class="label">Z客户端联系电话</td>
            <td class="content">
                <html:errors property="portZContactPhone"/>
                <html:text property="portZContactPhone" styleId="portZContactPhone" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
        <%} %>


        <tr>

    </table>
    <br>
    <%} %>
    <%
        if (sheetType.equals("businessImplement")

                || taskName.equals("TransfereTask")
                || taskName.equals("MakeTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")
        ) {
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
                               defaultValue="${languagespeciallineForm.isNeedAddCardA}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class" onchange="onchangeBanKa(this.value);"/>
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
    <br>
    <%} %>

    <%
        if (taskName.equals("ProjectDealTask")

                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")
        ) {
    %>
    <table class="formTable">
        <caption>最后一公里相关信息</caption>
        <tbody id='lastInfo'>
        <tr>
            <td class="label">A是否熔接</td>
            <td class="content">
                <eoms:comboBox name="isGetInterfaceA" id="isGetInterfaceA"
                               defaultValue="${languagespeciallineForm.isGetInterfaceA}" initDicId="1012310"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
            <td class="label">A熔接序号</td>
            <td class="content">
                <html:errors property="getInterfaceNoA"/>
                <html:text property="getInterfaceNoA" styleId="getInterfaceNoA" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">A最后一公里处理意见</td>
            <td colspan="3">
                <textarea name="theLastOpinionA" id="theLastOpinionA" class="textarea max"
                          alt="allowBlank:false">${languagespeciallineForm.theLastOpinionA}</textarea>
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

                taskName.equals("OpenTask") ||
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
                           alt="allowBlank:false"/>
            </td>
            <td class="label">电路编号</td>
            <td class="content">
                <html:errors property="circuitSheetId"/>
                <html:text property="circuitSheetId" styleId="circuitSheetId" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <%} %>
    <table class="formTable">
        <tbody id='BusinessInfo5'>
        <tr>
            <td class="label">备注</td>
            <td colspan='3'>
                <html:errors property="yuyinRemark"/>
                <html:textarea property="yuyinRemark" styleId="yuyinRemark" styleClass="textarea max"
                               alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">客户需求描述</td>
            <td colspan='3'>
                <html:errors property="userNeedDesc"/>
                <html:textarea property="userNeedDesc" styleId="userNeedDesc" styleClass="textarea max"
                               alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label">产品编号</td>
            <td colspan='3'>
                <html:errors property="productCode"/>
                <html:text property="productCode" styleId="productCode" readonly="readonly" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        </tbody>
    </table>

    <br/>
    <input type="button" styleClass="button" onclick="check();" value="保存"/>
    <input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">
</html:form>
<script type="text/javascript">
    function onchangelinkIsOkNeed(input) {
        var frm = document.forms[0];
        var temp = frm.userCallIsTouChuan ? frm.userCallIsTouChuan.value : '';

        if (temp != '') {
            if (input == 101330102) {
                eoms.form.enableArea('IsOkNeed');
            } else {
                eoms.form.disableArea('IsOkNeed', true);
            }
        }
    }

    function onchangeinfoType(input) {
        var frm = document.forms[0];
        var temp = frm.infoType ? frm.infoType.value : '';

        if (temp != '') {
            if (input == 101330302) {
                eoms.form.enableArea('infoOk');
            } else {
                eoms.form.disableArea('infoOk', true);
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

    function onchangeBanKa(input) {
        var frm = document.forms[0];
        var temp = frm.isNeedAddCardA ? frm.isNeedAddCardA.value : '';
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
</script>
<script type="text/javascript" id="my1">
    var factoryViewer = new Ext.JsonView("factoryview",
        '<div class="viewlistitem-{nodeType}">{name}</div>',
        {
            emptyText: '<div>没有选择项目</div>'
        }
    );
    var data = "[]";
    factoryViewer.jsonData = eoms.JSONDecode(data);
    //factoryViewer.refresh();

    //area tree
    var factoryTreeAction = '${app}/xtree.do?method=dict';
    factoryTree = new xbox({

        btnId: '${sheetPageName}showFactory',

        treeDataUrl: factoryTreeAction,
        treeRootId: '1013310',
        treeRootText: '主叫用户开放品牌',
        treeChkMode: '',
        treeChkType: 'dict',
        showChkFldId: '${sheetPageName}showFactory',
        saveChkFldId: '${sheetPageName}callUserOpenBrand',
        viewer: factoryViewer
    });
</script>
<!-- 主叫用户归属地 -->
<script type="text/javascript" id="my2">
    //viewer
    var areaViewer = new Ext.JsonView("areaview",
        '<div class="viewlistitem-{nodeType}">{name}</div>',
        {
            emptyText: '<div>没有选择项目</div>'
        }
    );
    var data = "[]";
    areaViewer.jsonData = eoms.JSONDecode(data);
    areaViewer.refresh();

    //area tree
    var deptTreeAction = '${app}/xtree.do?method=areaTree';
    deptetree = new xbox({

        btnId: '${sheetPageName}showDept', dlgId: 'dlg3',

        treeDataUrl: deptTreeAction, treeRootId: '-1', treeRootText: '地市', treeChkMode: '', treeChkType: 'area',
        showChkFldId: '${sheetPageName}showDept', saveChkFldId: '${sheetPageName}callUserLocalZone', viewer: areaViewer
    });
</script>
<!-- 业务开放地 -->
<script type="text/javascript" id="my3">
    //viewer
    var areaViewer2 = new Ext.JsonView("areaview2",
        '<div class="viewlistitem-{nodeType}">{name}</div>',
        {
            emptyText: '<div>没有选择项目</div>'
        }
    );
    var data = "[]";
    areaViewer2.jsonData = eoms.JSONDecode(data);
    areaViewer2.refresh();

    //area tree
    var deptTreeAction = '${app}/xtree.do?method=areaTree';
    deptetree = new xbox({

        btnId: '${sheetPageName}showDept2', dlgId: 'dlg3',

        treeDataUrl: deptTreeAction, treeRootId: '-1', treeRootText: '地市', treeChkMode: '', treeChkType: 'area',
        showChkFldId: '${sheetPageName}showDept2', saveChkFldId: '${sheetPageName}businessOpenZone', viewer: areaViewer2
    });
</script>
<!-- 呼出范围 -->
<script type="text/javascript" id="my4">
    var factoryViewer2 = new Ext.JsonView("factoryview2",
        '<div class="viewlistitem-{nodeType}">{name}</div>',
        {
            emptyText: '<div>没有选择项目</div>'
        }
    );
    var data = "[]";
    factoryViewer2.jsonData = eoms.JSONDecode(data);
    //factoryViewer.refresh();

    //area tree
    var factoryTreeAction = '${app}/xtree.do?method=dict';
    factoryTree = new xbox({

        btnId: '${sheetPageName}showFactory2',

        treeDataUrl: factoryTreeAction,
        treeRootId: '1013317',
        treeRootText: '呼出范围',
        treeChkMode: '',
        treeChkType: 'dict',
        showChkFldId: '${sheetPageName}showFactory2',
        saveChkFldId: '${sheetPageName}userCallScope',
        viewer: factoryViewer2
    });
</script>


<script type="text/javascript">
    function initPage() {
        v = new eoms.form.Validation({form: 'languagespeciallineForm'});
        var taskName = "<%=taskName%>";

        if (taskName != "" && taskName != "AcceptTask" && taskName != "ImplementDealTask") {
            eoms.form.readOnlyArea('BusinessInfo');
            eoms.form.readOnlyArea('BusinessInfo2');
            eoms.form.readOnlyArea('BusinessInfo3');
            eoms.form.readOnlyArea('BusinessInfo4');
            eoms.form.readOnlyArea('BusinessInfo5');
        }

        if (taskName == "AccessTask") {
            eoms.form.readOnlyArea('customInfo');
        } else if (taskName == "TransferlTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
        } else if (taskName == "TransfereTask") {
            eoms.form.readOnlyArea('cityInfo');
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
            eoms.form.readOnlyArea('transLineInfo');
        } else if (taskName == "LauguageTask" || taskName == "CityNetTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('interfaceInfo');
            eoms.form.readOnlyArea('transLineInfo');
            eoms.form.readOnlyArea('transCardInfo');
        } else if (taskName == "ProjectDealTask") {

            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('customInfo2');

            eoms.form.readOnlyArea('interfaceInfo');

            eoms.form.readOnlyArea('transLineInfo');
            eoms.form.readOnlyArea('transCardInfo');
            eoms.form.readOnlyArea('cityInfo');
            eoms.form.readOnlyArea('cityInfo2');
        } else if (taskName == "TrasitionTask") {
            eoms.form.readOnlyArea('customInfo');
            eoms.form.readOnlyArea('customInfo2');
            eoms.form.readOnlyArea('transLineInfo');
            eoms.form.readOnlyArea('transCardInfo');

            eoms.form.readOnlyArea('lastInfo');
        }

    }

    Ext.onReady(
        function () {
            initPage();
        }
    );

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

    function popupProjectInfo() {
        var urlstr = "<%=head%>/webmaster/jsp/res/import/custexcelimport/custexcelimport.jsp?includeSpecialties=ProjectResource&userName=admin&prodCode=${gprsspeciallineForm.id}";
        var params = window.showModalDialog(urlstr, "", "dialogWidth:" + screen.width * 1.0 + "px;dialogHeight:" + screen.height * 0.9 + "px");
    }
</script>      