<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.businessupport.product.webapp.form.LanguageSpecialLineForm" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% String deleted = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("deleted"));
    String taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
    System.out.println("@@taskNameDetail" + taskName);
    System.out.println("@@sheetTypeDetail" + sheetType);
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isView = StaticMethod.nullObject2String(request.getParameter("isView"));
    String isShowLanguage = StaticMethod.nullObject2String(request.getParameter("isShowLanguage"));
    System.out.println("@isShowLanguage" + isShowLanguage);
    String taskType = StaticMethod.nullObject2String(request.getParameter("taskType"));
%>
<script type="text/javascript">
    function modify() {
        window.location.href = './languagespeciallines.do?method=edit&id=${languagespeciallineForm.id}&ordersheetid=${languagespeciallineForm.orderSheet_Id}&sheetType=${sheetType}&taskName=${taskName}&isEdit=isEdit&isShowLanguage=<%=isShowLanguage%>';
    }

    function initPage() {
        v = new eoms.form.Validation({form: 'languagespeciallineForm'});
        var taskName = "<%=taskName%>";

    }
</script>
<caption>
    <div class="header center"><%if (!isShowLanguage.equals("yes")) { %>语音专线
        <%} else { %>传输专线<%} %>
    </div>
</caption>
<html:form action="languagespeciallines.do?method=xsave" method="post" styleId="detail">
    <input type="hidden" name="orderSheet_Id" id="orderSheet_Id" value="${languagespeciallineForm.orderSheet_Id}"/>

    <br>
    <%if (!isShowLanguage.equals("yes") || isShowLanguage.equals("yes")) { %>
    <table class="formTable">
        <tr>
            <td class="label">平台呼入</td>
            <td class="content">
                <html:errors property="inputNumber"/>
                <bean:write name="languagespeciallineForm" property="inputNumber" scope="request"/>
            </td>
            <td class="label">位长</td>
            <td class="content">
                <html:errors property="bitLength"/>
                <bean:write name="languagespeciallineForm" property="bitLength" scope="request"/>

            </td>
        </tr>
        <tr>
            <td class="label">业务类型</td>
            <td colspan='3'>
                <html:errors property="businessType"/>
                <eoms:id2nameDB id="${languagespeciallineForm.businessType}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">主叫用户开放品牌</td>
            <td colspan="3">
                <c:forTokens items="${languagespeciallineForm.callUserOpenBrand}" delims="," var="callUserOpenBrand"
                             varStatus="status">
                    <eoms:id2nameDB id="${callUserOpenBrand}" beanId="ItawSystemDictTypeDao"/>
                <c:choose>
                    <c:when test="${status.last}"></c:when>
                <c:otherwise>,</c:otherwise>
                </c:choose>
                </c:forTokens>
        </tr>
        <tr>
            <td class="label">主叫用户归属地</td>
            <td colspan="3">
                <c:forTokens items="${languagespeciallineForm.callUserLocalZone}" delims="," var="callUserLocalZone"
                             varStatus="status">
                    <eoms:id2nameDB id="${callUserLocalZone}" beanId="tawSystemAreaDao"/>
                <c:choose>
                    <c:when test="${status.last}"></c:when>
                <c:otherwise>,</c:otherwise>
                </c:choose>
                </c:forTokens>

        </tr>
        <tr>
            <td class="label">业务开放地</td>
            <td colspan="3">

                <c:forTokens items="${languagespeciallineForm.businessOpenZone}" delims="," var="businessOpenZone"
                             varStatus="status">
                    <eoms:id2nameDB id="${businessOpenZone}" beanId="tawSystemAreaDao"/>
                <c:choose>
                    <c:when test="${status.last}"></c:when>
                <c:otherwise>,</c:otherwise>
                </c:choose>
                </c:forTokens>
        </tr>

        <tr>
            <td class="label">拨号方式</td>
            <td class="content">
                <html:errors property="callNumberType"/>
                <eoms:id2nameDB id="${languagespeciallineForm.callNumberType}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">是否开通省内长途来话</td>
            <td class="content">
                <html:errors property="isOpenPronicePhone"/>
                <eoms:id2nameDB id="${languagespeciallineForm.isOpenPronicePhone}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">是否开通省际长途来话</td>
            <td class="content">
                <html:errors property="isOpenProniceBeteen"/>
                <eoms:id2nameDB id="${languagespeciallineForm.isOpenProniceBeteen}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">是否开通国际长途来话</td>
            <td class="content">
                <html:errors property="isOpenContryPhone"/>
                <eoms:id2nameDB id="${languagespeciallineForm.isOpenContryPhone}" beanId="ItawSystemDictTypeDao"/>
            </td>

    </table>
    <br>
    <table class="formTable">
        <caption>平台呼出</caption>
        <tr>
            <td class="label">客户主叫号码</td>
            <td class="content">
                <html:errors property="userCallNumber"/>
                <bean:write name="languagespeciallineForm" property="userCallNumber" scope="request"/>
            </td>

            <td class="label">主叫号码是否透传</td>
            <td class="content">
                <html:errors property="userCallIsTouChuan"/>
                <eoms:id2nameDB id="${languagespeciallineForm.userCallIsTouChuan}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">主叫号码修改方式</td>
            <td colspan='3'>
                <html:errors property="userCallModifyType"/>
                <bean:write name="languagespeciallineForm" property="userCallModifyType" scope="request"/>
            </td>
        </tr>
        <tr>

            <td class="label">信令方式</td>
            <td colspan='3'>
                <html:errors property="infoType"/>
                <eoms:id2nameDB id="${languagespeciallineForm.infoType}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>


        <tr>
            <td class="label">信令点编码</td>
            <td class="content">
                <html:errors property="infoPointCode"/>
                <bean:write name="languagespeciallineForm" property="infoPointCode" scope="request"/>
            </td>
            <td class="label">信令协议</td>
            <td class="content">
                <html:errors property="infoProtocal"/>
                <eoms:id2nameDB id="${languagespeciallineForm.infoProtocal}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">呼出范围</td>
            <td colspan="3">
                <c:forTokens items="${languagespeciallineForm.userCallScope}" delims="," var="userCallScope"
                             varStatus="status"><eoms:id2nameDB id="${userCallScope}"
                                                                beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens>
            </td>
        </tr>
        <tr>
            <td class="label">中继数</td>
            <td colspan='3'>
                    <bean:write name="languagespeciallineForm" property="infoBetweenNumber" scope="request"/>
        </tr>
        <tr>

            <td class="label">计费说明</td>
            <td colspan='3'>
                <html:errors property="feeDesc"/>
                <bean:write name="languagespeciallineForm" property="feeDesc" scope="request"/>
            </td>

        </tr>
        <tr>
            <td class="label">客户机房地址</td>
            <td class="content">
                <html:errors property="userComputerHoseAdd"/>
                <bean:write name="languagespeciallineForm" property="userComputerHoseAdd" scope="request"/>
            </td>
            <td class="label">客户端设备品牌</td>
            <td class="content">
                <html:errors property="userPointDevBrand"/>
                <bean:write name="languagespeciallineForm" property="userPointDevBrand" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">型号</td>
            <td class="content">
                <html:errors property="userComputerType"/>
                <bean:write name="languagespeciallineForm" property="userComputerType" scope="request"/>
            </td>
            <td class="label">产权</td>
            <td class="content">
                <html:errors property="userComputerRight"/>
                <eoms:id2nameDB id="${languagespeciallineForm.userComputerRight}" beanId="ItawSystemDictTypeDao"/>

            </td>
        </tr>
        <tr>
            <td class="label">是否网间短号转网内</td>
            <td colspan='3'>
                <html:errors property="isNumberTrasiNet"/>
                <eoms:id2nameDB id="${languagespeciallineForm.isNumberTrasiNet}" beanId="ItawSystemDictTypeDao"/>

            </td>
        </tr>

    </table>
    <br>
    <%} %>
    <%
        if (taskType.equals("inputUser") && taskName.equals("LauguageTask")
                || taskName.equals("UserTask")
                || taskName.equals("AccessTask")
                || taskName.equals("TransferlTask")
                || taskName.equals("LauguageTask")
                || taskName.equals("TransfereTask")

                || taskName.equals("MakeTask")
                || taskName.equals("HandleTask")

                || taskName.equals("AuditTask")
                || taskName.equals("CodeDispthTask")
                || taskName.equals("GetWayTask")
                || taskName.equals("OpenTask")
                || taskName.equals("DataMakeTask")
                || taskName.equals("BusiTestTask")
                || taskName.equals("DataOkTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")

                || taskName.equals("HoldTask")

                || taskName.equals("")


        ) {
    %>
    <table class="formTable">
        <caption>客户端勘查信息</caption>
        <tbody id='customInfo'>
        <tr>
            <td class="label">A端点详细地址</td>
            <td class="content">
                <html:errors property="portADetailAdd"/>
                <bean:write name="languagespeciallineForm" property="portADetailAdd" scope="request"/>
            </td>
            <td class="label">用户端设备端口类型</td>
            <td class="content">
                <html:errors property="portABDeviceType"/>
                <eoms:id2nameDB id="${languagespeciallineForm.portABDeviceType}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
                <%if( (isShowLanguage.equals("yes")&&
  	taskName.equals("LauguageTask"))
  	
  	||taskName.equals("ProjectDealTask")
	 || taskName.equals("ModifyDesignTask")
     || taskName.equals("AuditDesignTask")
	 || taskName.equals("TrasitionTask")
	 || taskName.equals("BusinessTestTask")
	 || taskName.equals("DredgeAffirmTask")
	 
	 || taskName.equals("HoldTask")	 
	 	 || taskName.equals("")	 		
	
  	){%>

        <tr>
            <td class="label">城市A*</td>
            <td class="content">
                <html:errors property="cityA"/>
                <bean:write name="languagespeciallineForm" property="cityA" scope="request"/>
            </td>

            <td class="label">A站点名称</td>
            <td class="content">
                <html:errors property="siteNameA"/>
                <bean:write name="languagespeciallineForm" property="siteNameA" scope="request"/>
            </td>
        </tr>

        <tr>
            <td class="label">端口A</td>
            <td class="content">
                <html:errors property="portA"/>
                <bean:write name="languagespeciallineForm" property="portA" scope="request"/>
            </td>

            <td class="label">A接口类型及型号</td>
            <td class="content">
                <html:errors property="portAInterfaceType"/>
                <eoms:id2nameDB id="${languagespeciallineForm.portAInterfaceType}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>


        <td class="label">端点A业务设备名称</td>
        <td class="content">
            <html:errors property="portABDeviceName"/>
            <bean:write name="languagespeciallineForm" property="portABDeviceName" scope="request"/>
        </td>
        <td class="label">端点A业务设备端口</td>
        <td class="content">
            <html:errors property="portABDevicePort"/>
            <bean:write name="languagespeciallineForm" property="portABDevicePort" scope="request"/>
        </td>
        </tr>
        <tr>

            <td class="label">A客户端联系人</td>
            <td class="content">
                <html:errors property="apointLocalPerson"/>
                <bean:write name="languagespeciallineForm" property="apointLocalPerson" scope="request"/>
            </td>

            <td class="label">A客户端联系电话</td>
            <td class="content">
                <html:errors property="portAContactPhone"/>
                <bean:write name="languagespeciallineForm" property="portAContactPhone" scope="request"/>
            </td>
        </tr>
                <%}%>
    </table>
    <%} %>

    <!--
    <table class="formTable">
    <caption>业务相关信息</caption>
    <tbody id='BusinessInfo' >
    <tr>
    <td class="label">城市A</td>
    <td class="content">
    <html:errors property="cityA"/>
    <bean:write name="languagespeciallineForm" property="cityA" scope="request"/>
    </td>

    <td class="label">A站点名称</td>
    <td class="content">
    <html:errors property="siteNameA"/>
    <bean:write name="languagespeciallineForm" property="siteNameA" scope="request"/>
    </td>

    </tr>

    <td class="label">A光纤设备编号</td>
    <td class="content">
    <html:errors property="fiberEquipCodeA"/>
    <bean:write name="languagespeciallineForm" property="fiberEquipCodeA" scope="request"/>
    </td>
    </tr>
    <tr>
    <td class="label">A纤芯个数*</td>
    <td class="content">
    <bean:write name="languagespeciallineForm" property="fiberAcount" scope="request"/>
    </td>
    <td class="label">A光缆路由描述*</td>
    <td class="content">
    <bean:write name="languagespeciallineForm" property="fiberAroute" scope="request"/>
    </td>
    </tr>
    <tr>
    <td class="label">A接入点类型</td>
    <td colspan="3">
    <eoms:id2nameDB id="${languagespeciallineForm.interfacePointTypeA}" beanId="ItawSystemDictTypeDao"/>
    </td>
    </tr>
    </tbody>业务相关信息
    </table>


    <br>


    <table class="formTable">
    <caption>业务相关信息</caption>
    <tbody id='BusinessInfo' >
    <tr>

    <td class="label">城市Z</td>
    <td class="content">
    <html:errors property="cityZ"/>
    <bean:write name="languagespeciallineForm" property="cityZ" scope="request"/>
    </td>


    <td class="label">Z站点名称</td>
    <td class="content">
    <html:errors property="siteNameZ"/>
    <bean:write name="languagespeciallineForm" property="siteNameZ" scope="request"/>
    </td>
    </tr>

    <td class="label">端口Z</td>
    <td class="content">
    <html:errors property="portZ"/>
    <bean:write name="languagespeciallineForm" property="portZ" scope="request"/>
    </td>
    </tr>

    <tr>

    <td class="label">Z接口类型及型号</td>
    <td class="content">
    <html:errors property="portZInterfaceType"/>
    <eoms:id2nameDB id="${languagespeciallineForm.portZInterfaceType}" beanId="ItawSystemDictTypeDao"/>
    </td>

    <td class="label">Z端点详细地址</td>
    <td class="content">
    <html:errors property="portZDetailAdd"/>
    <bean:write name="languagespeciallineForm" property="portZDetailAdd" scope="request"/>
    </td>
    </tr>
    <tr>


    <td class="label">Z业务设备名称</td>
    <td class="content">
    <html:errors property="portZBDeviceName"/>
    <bean:write name="languagespeciallineForm" property="portZBDeviceName" scope="request"/>
    </td>


    <td class="label">端点Z业务设备端口*</td>
    <td class="content">
    <html:errors property="portZBDevicePort"/>
    <bean:write name="languagespeciallineForm" property="portZBDevicePort" scope="request"/>
    </td>
    </tr>
    <tr>

    <td class="label">Z客户在当地的配合人</td>
    <td class="content">
    <html:errors property="zpointLocalPerson"/>
    <bean:write name="languagespeciallineForm" property="zpointLocalPerson" scope="request"/>
    </td>


    <td class="label">Z客户在当地的配合人的联系电话</td>
    <td class="content">
    <html:errors property="portZContactPhone"/>
    <bean:write name="languagespeciallineForm" property="portZContactPhone" scope="request"/>
    </td>
    </tr>


    </tbody>
    </table>
    <br>
    -->

    <%
        if (isShowLanguage.equals("yes") && taskName.equals("TrasitionTask")

                || taskName.equals("AccessTask")
                || taskName.equals("TransferlTask")
                || taskName.equals("LauguageTask")
                || taskName.equals("TransfereTask")

                || taskName.equals("MakeTask")
                || taskName.equals("HandleTask")

                || taskName.equals("TrasitionTask")

                || taskName.equals("AuditTask")
                || taskName.equals("CodeDispthTask")
                || taskName.equals("GetWayTask")
                || taskName.equals("OpenTask")
                || taskName.equals("DataMakeTask")
                || taskName.equals("BusiTestTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")
                || taskName.equals("DataOkTask")


                || taskName.equals("HoldTask")
                || taskName.equals("")

        ) {
    %>
    <table class="formTable">
        <caption>接入点勘查信息</caption>
        <tbody id='interfaceInfo'>
        <tr>
            <!-- 		<td class="label">A接入点机房</td>
	      <td class="content">
		  	<html:errors property="apointComputHouseName"/>
		  	 <bean:write name="languagespeciallineForm" property="apointComputHouseName" scope="request"/> 
	      </td> -->

            <td class="label">A接入点地址</td>
            <td colspan='3'>
                <html:errors property="interfacePointAddA"/>
                <bean:write name="languagespeciallineForm" property="interfacePointAddA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">A接入点站点名称（接入基站）</td>
            <td colspan='3'>
                <html:errors property="interfaceSiteNameA"/>
                <bean:write name="languagespeciallineForm" property="interfaceSiteNameA" scope="request"/>
            </td>
            <!--      <td class="label">A接入点设备编码</td>
      <td class="content">
	  	<html:errors property="interfaceEquipCodeA"/>
	  		 <bean:write name="languagespeciallineForm" property="interfaceEquipCodeA" scope="request"/> 
      </td>  -->

        </tr>
        <tr>
            <td class="label">A光纤设备名称</td>
            <td class="content">
                <html:errors property="fiberEquipNameA"/>
                <bean:write name="languagespeciallineForm" property="fiberEquipNameA" scope="request"/>
            </td>
            <td class="label">A光纤设备编号</td>
            <td class="content">
                <html:errors property="fiberEquipCodeA"/>
                <bean:write name="languagespeciallineForm" property="fiberEquipCodeA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">A纤芯个数*</td>
            <td class="content">
                <html:errors property="fiberAcount"/>
                <bean:write name="languagespeciallineForm" property="fiberAcount" scope="request"/>
            </td>
            <td class="label">A接入点类型</td>
            <td class="content">
                <html:errors property="interfacePointTypeA"/>
                <eoms:id2nameDB id="${languagespeciallineForm.interfacePointTypeA}" beanId="ItawSystemDictTypeDao"/>
            </td>

        </tr>
        <tr>
            <td class="label">A光缆路由描述*</td>
            <td colspan="3">
                <html:errors property="fiberAroute"/>
                <bean:write name="languagespeciallineForm" property="fiberAroute" scope="request"/>
            </td>
        </tr>
        </tbody>


    </table>
    <%} %>

    <%
        if (sheetType.equals("businessImplement")
                || taskName.equals("TransferlTask")
                || taskName.equals("LauguageTask")
                || taskName.equals("TransfereTask")

                || taskName.equals("MakeTask")
                || taskName.equals("HandleTask")

                || taskName.equals("AuditTask")
                || taskName.equals("CodeDispthTask")
                || taskName.equals("GetWayTask")
                || taskName.equals("OpenTask")
                || taskName.equals("DataMakeTask")
                || taskName.equals("BusiTestTask")
                || taskName.equals("DataOkTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")

                || taskName.equals("HoldTask")
                || taskName.equals("")
        ) {
    %>
    <table class="formTable">
        <caption>传输线路勘查信息</caption>
        <tbody id='transLineInfo'>
        <tr>
            <td class="label">A最后一公里光缆长度</td>
            <td class="content">
                <html:errors property="fiberLengthA"/>
                <bean:write name="languagespeciallineForm" property="fiberLengthA" scope="request"/>
            </td>
            <td class="label">A光缆产权</td>
            <td class="content">
                <html:errors property="fiberOwnerA"/>
                <eoms:id2nameDB id="${languagespeciallineForm.fiberOwnerA}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">敷设方式*</td>
            <td class="content">
                <html:errors property="buildTypeA"/>
                <bean:write name="languagespeciallineForm" property="buildTypeA" scope="request"/>
            </td>
            <td class="label">A客户端到接入点能否通达*</td>
            <td class="content">
                <html:errors property="isOkBetweenUserA"/>
                <eoms:id2nameDB id="${languagespeciallineForm.isOkBetweenUserA}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>

            <td class="label">A不能接入的原因*</td>
            <td colspan='3'>
                <html:errors property="noInputResonA"/>
                <bean:write name="languagespeciallineForm" property="noInputResonA" scope="request"/>
            </td>

        </tr>
        </tbody>
    </table>
    <br>
    <%} %>
    <%
        if (taskType.equals("inputLanguage")
                || taskName.equals("LauguageTask")
                || taskName.equals("TransfereTask")

                || taskName.equals("MakeTask")
                || taskName.equals("HandleTask")

                || taskName.equals("AuditTask")
                || taskName.equals("CodeDispthTask")
                || taskName.equals("GetWayTask")
                || taskName.equals("OpenTask")
                || taskName.equals("DataMakeTask")
                || taskName.equals("BusiTestTask")
                || taskName.equals("DataOkTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")
                || taskName.equals("HoldTask")
                || taskName.equals("")

        ) {
    %>
    <table class="formTable">
        <caption>关口局勘查信息</caption>
        <tbody id='customInfo'>
        <tr>
            <td class="label">关口局业务设备端口*</td>
            <td class="content">
                <html:errors property="portZBDevicePort"/>
                <bean:write name="languagespeciallineForm" property="portZBDevicePort" scope="request"/>
            </td>
            <td class="label">关口局站点名称</td>
            <td class="content">
                <html:errors property="siteNameZ"/>
                <bean:write name="languagespeciallineForm" property="siteNameZ" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">关口局业务设备名称*</td>
            <td class="content">
                <html:errors property="portZBDeviceName"/>
                <bean:write name="languagespeciallineForm" property="portZBDeviceName" scope="request"/>
            </td>
            <td class="label">关口局端点详细地址</td>
            <td class="content">
                <html:errors property="portZDetailAdd"/>
                <bean:write name="languagespeciallineForm" property="portZDetailAdd" scope="request"/>
            </td>


        </tr>
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
                            || taskName.equals("")


            ) {
        %>

        <td class="label">城市Z*</td>
        <td class="content">
            <bean:write name="languagespeciallineForm" property="cityZ" scope="request"/>
        </td>

        <tr>

            <td class="label">端口Z</td>
            <td class="content">
                <html:errors property="portZ"/>
                <bean:write name="languagespeciallineForm" property="portZ" scope="request"/>
            </td>

            <td class="label">Z接口类型及型号</td>
            <td class="content">
                <eoms:id2nameDB id="${languagespeciallineForm.portZInterfaceType}" beanId="ItawSystemDictTypeDao"/>

            </td>
        </tr>


        <tr>
            <td class="label">Z客户端联系人</td>
            <td class="content">
                <bean:write name="languagespeciallineForm" property="zpointLocalPerson" scope="request"/>
            </td>

            <td class="label">Z客户端联系电话</td>
            <td class="content">
                <bean:write name="languagespeciallineForm" property="portZContactPhone" scope="request"/>
            </td>
        </tr>
        <%} %>
        </tbody>
        <tr>

    </table>
    <br>
    <%} %>
    <%
        if (sheetType.equals("businessImplement")

                || taskName.equals("TransfereTask")

                || taskName.equals("MakeTask")
                || taskName.equals("HandleTask")

                || taskName.equals("AuditTask")
                || taskName.equals("CodeDispthTask")
                || taskName.equals("GetWayTask")
                || taskName.equals("OpenTask")
                || taskName.equals("DataMakeTask")
                || taskName.equals("BusiTestTask")
                || taskName.equals("DataOkTask")

                || taskName.equals("ProjectDealTask")
                || taskName.equals("ModifyDesignTask")
                || taskName.equals("AuditDesignTask")
                || taskName.equals("TrasitionTask")
                || taskName.equals("BusinessTestTask")
                || taskName.equals("DredgeAffirmTask")

                || taskName.equals("HoldTask")
                || taskName.equals("")


        ) {
    %>
    <table class="formTable">
        <caption>传输容量勘查信息</caption>
        <tbody id='transCardInfo'>
        <tr>
            <td class="label">传输容量是否满足开通</td>
            <td class="content">
                <html:errors property="isDeviceAllowOpenA"/>
                <bean:write name="languagespeciallineForm" property="isDeviceAllowOpenA" scope="request"/>
            </td>
            <td class="label">是否需要添加板卡</td>
            <td class="content">
                <html:errors property="isNeedAddCardA"/>
                <eoms:id2nameDB id="${languagespeciallineForm.isNeedAddCardA}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">板卡类型</td>
            <td class="content">
                <html:errors property="cardTypeA"/>
                <bean:write name="languagespeciallineForm" property="cardTypeA" scope="request"/>
            </td>
            <td class="label">板卡数量</td>
            <td class="content">
                <html:errors property="cardNumA"/>
                <bean:write name="languagespeciallineForm" property="cardNumA" scope="request"/>
            </td>
        </tr>
        </tbody>

        <br>
    </table>
    <br>
    <%} %>
    <%
        if (
                taskName.equals("OpenTask")
                        || taskName.equals("DataMakeTask")
                        || taskName.equals("BusiTestTask")
                        || taskName.equals("DataOkTask") ||

                        sheetType.equals("businessImplement") && taskName.equals("ProjectDealTask")
                        || taskName.equals("ProjectDealTask")
                        || taskName.equals("ModifyDesignTask")
                        || taskName.equals("AuditDesignTask")
                        || taskName.equals("TrasitionTask")
                        || taskName.equals("BusinessTestTask")
                        || taskName.equals("DredgeAffirmTask")
                        || taskName.equals("")

        ) {
    %>
    <table class="formTable">
        <caption>最后一公里相关信息</caption>
        <tbody id='lastInfo'>
        <tr>
            <td class="label">A是否熔接</td>
            <td class="content">
                <html:errors property="isGetInterfaceA"/>
                <eoms:id2nameDB id="${languagespeciallineForm.isGetInterfaceA}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">A熔接序号</td>
            <td class="content">
                <html:errors property="getInterfaceNoA"/>
                <bean:write name="languagespeciallineForm" property="getInterfaceNoA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">A最后一公里处理意见</td>
            <td colspan="3">
                <bean:write name="languagespeciallineForm" property="theLastOpinionA" scope="request"/>
            </td>
        </tr>

        </tbody>
    </table>
    <br>
    <%} %>
    <%
        if (
                taskName.equals("OpenTask")
                        || taskName.equals("DataMakeTask")
                        || taskName.equals("BusiTestTask")
                        || taskName.equals("DataOkTask") ||
                        taskName.equals("") ||
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
                <bean:write name="languagespeciallineForm" property="circuitName" scope="request"/>
            </td>
            <td class="label">电路编号</td>
            <td class="content">
                <bean:write name="languagespeciallineForm" property="circuitSheetId" scope="request"/>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <%} %>
    <table class="formTable">
        <tr>
            <td class="label">备注</td>
            <td colspan='3'>
                <html:errors property="yuyinRemark"/>
                <bean:write name="languagespeciallineForm" property="yuyinRemark" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">客户需求描述</td>
            <td colspan='3'>
                <html:errors property="userNeedDesc"/>
                <bean:write name="languagespeciallineForm" property="userNeedDesc" scope="request"/>
            </td>
        </tr>

        <tr>
            <td class="label">产品编号</td>
            <td colspan='3'>
                <html:errors property="productCode"/>
                <bean:write name="languagespeciallineForm" property="productCode" scope="request"/>
            </td>
        </tr>

    </table>
    <table>
        <%if (isView.equals("1") || taskName.equals("AcceptTask") || taskName.equals("ImplementDealTask") || taskName.equals("TrasitionTask") || (isShowLanguage.equals("yes") && taskName.equals("LauguageTask")) || taskName.equals("ProjectDealTask")) {%>
        <input type="button" value="修改" onclick="modify();">
        <%} %>
        <input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">
    </table>
</html:form>

<!-- footer_eoms.jsp end-->