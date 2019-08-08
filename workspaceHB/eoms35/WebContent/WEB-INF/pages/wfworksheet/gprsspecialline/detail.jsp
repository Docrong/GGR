<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.businessupport.product.webapp.form.GprsSpecialLineForm" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% String deleted = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("deleted"));
    String taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
    System.out.println("@@taskNameDetail" + taskName);
    System.out.println("@@sheetTypeDetail" + sheetType);
    String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
    String isView = StaticMethod.nullObject2String(request.getParameter("isView"));
%>
<script type="text/javascript">
    function modify() {
        window.location.href = './gprsspeciallines.do?method=edit&id=${gprsspeciallineForm.id}&ordersheetid=${gprsspeciallineForm.orderSheet_Id}&sheetType=${sheetType}&taskName=${taskName}';
    }

    function initPage() {
        v = new eoms.form.Validation({form: 'gprsspeciallineForm'});
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
</script>
<caption>
    <div class="header center">GPRS专线详细信息</div>
</caption>
<html:form action="gprsspeciallines.do?method=xsave" method="post" styleId="detail">
    <input type="hidden" name="orderSheet_Id" id="orderSheet_Id" value="${gprsspeciallineForm.orderSheet_Id}"/>

    <br>
    <table class="formTable">
        <tr>
            <td class="label">APN名称</td>
            <td class="content">
                <html:errors property="apnName"/>
                <bean:write name="gprsspeciallineForm" property="apnName" scope="request"/>
            </td>
            <td class="label">APN类型</td>
            <td class="content">
                <html:errors property="apnType"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.apnType}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">IP地址分配方式</td>
            <td class="content">
                <html:errors property="ipAddressAssign"/>
                <bean:write name="gprsspeciallineForm" property="ipAddressAssign" scope="request"/>
            </td>
            <td class="label">终端IP地址分配方式</td>
            <td class="content">
                <html:errors property="endIDDividType"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.endIDDividType}" beanId="ItawSystemDictTypeDao"/></td>
        </tr>
        <tr>
            <td class="label">终端数量</td>
            <td class="content">
                <html:errors property="endPortNum"/>
                <bean:write name="gprsspeciallineForm" property="endPortNum" scope="request"/>
            </td>
            <td class="label">SIM卡HLR制作范围</td>
            <td class="content">
                <html:errors property="simAdnHlrScope"/>
                <bean:write name="gprsspeciallineForm" property="simAdnHlrScope" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">申请用途</td>
            <td colspan='3'>
                <html:errors property="applyPurpose"/>
                <bean:write name="gprsspeciallineForm" property="applyPurpose" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">业务量评估</td>
            <td colspan='3'>
                <html:errors property="businessNumAssessment"/>
                <bean:write name="gprsspeciallineForm" property="businessNumAssessment" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">用户是否用户网站</td>
            <td class="content">
                <eoms:id2nameDB id="${gprsspeciallineForm.userIsUserNet}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">用户个性化设备需求</td>
            <td class="content">
                <html:errors property="userSpecifyDevNeed"/>
                <bean:write name="gprsspeciallineForm" property="userSpecifyDevNeed" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">用户端是否进行RADIUS验证</td>
            <td class="content">
                <html:errors property="userIsRadiusValid"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.userIsRadiusValid}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <c:if test="${gprsspeciallineForm.userIsRadiusValid=='101231002'}">
                <td class="label">用户端RADIUS服务器IP地址</td>
                <td class="content">
                    <html:errors property="userRadiusValidateIPAdd"/>
                    <bean:write name="gprsspeciallineForm" property="userRadiusValidateIPAdd" scope="request"/>
                </td>
            </c:if>
        </tr>
    </table>
    <br>
    <%if (sheetType.equals("businessImplement") || taskName.equals("ExplorateTask") || taskName.equals("") || taskName.equals("UserTask") || taskName.equals("AccessTask") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>

    <table class="formTable">
        <caption>客户端勘查信息</caption>
        <tbody id='customInfo'>
        <tr>
            <td class="label">A客户端标准地址</td>
            <td colspan="3">
                <bean:write name="gprsspeciallineForm" property="userStardAddA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">A客户位置经度</td>
            <td class="content">
                <html:errors property="userSiteAA"/>
                <bean:write name="gprsspeciallineForm" property="userSiteAA" scope="request"/>
            </td>
            <td class="label">A客户位置纬度</td>
            <td class="content">
                <html:errors property="userSiteHA"/>
                <bean:write name="gprsspeciallineForm" property="userSiteHA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">用户端设备端口类型</td>
            <td class="content">
                <html:errors property="portABDeviceType"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.portABDeviceType}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">A建设方式</td>
            <td class="content">
                <html:errors property="buildMethodA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.buildMethodA}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">A客户端是否具有设备</td>
            <td class="content">
                <html:errors property="userIsHaveDivA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.userIsHaveDivA}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">A是否需要移动采购</td>
            <td class="content">
                <html:errors property="isNeedBuyA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.isNeedBuyA}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        </tbody>
        <tbody id="NeededA" style="display:none">
        <tr>
            <td class="label">A需要购买的设备</td>
            <td colspan='3'>
                <html:errors property="theDevNeededA"/>
                <bean:write name="gprsspeciallineForm" property="theDevNeededA" scope="request"/>
            </td>
        </tr>
        </tbody>
    </table>
    <%} %>
    <br>
    <%if (sheetType.equals("businessImplement") || taskName.equals("ExplorateTask") || taskName.equals("") || taskName.equals("AccessTask") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>
    <table class="formTable">
        <caption>接入点勘查信息</caption>
        <tbody id='interfaceInfo'>
        <tr>
            <td class="label">A接入点机房</td>
            <td class="content">
                <html:errors property="apointComputHouseName"/>
                <bean:write name="gprsspeciallineForm" property="apointComputHouseName" scope="request"/>
            </td>
            <td class="label">A接入点地址</td>
            <td class="content">
                <html:errors property="interfacePointAddA"/>
                <bean:write name="gprsspeciallineForm" property="interfacePointAddA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">A接入点站点名称（接入基站）</td>
            <td colspan="3">
                <html:errors property="interfaceSiteNameA"/>
                <bean:write name="gprsspeciallineForm" property="interfaceSiteNameA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">A光纤设备名称</td>
            <td class="content">
                <html:errors property="fiberEquipNameA"/>
                <bean:write name="gprsspeciallineForm" property="fiberEquipNameA" scope="request"/>
            </td>
            <td class="label">A光纤设备编号</td>
            <td class="content">
                <html:errors property="fiberEquipCodeA"/>
                <bean:write name="gprsspeciallineForm" property="fiberEquipCodeA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">A纤芯个数</td>
            <td class="content">
                <bean:write name="gprsspeciallineForm" property="fiberAcount" scope="request"/>
            </td>
            <td class="label">A接入点类型</td>
            <td class="content">
                <html:errors property="interfacePointTypeA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.interfacePointTypeA}" beanId="ItawSystemDictTypeDao"/>
            </td>

        </tr>
        <tr>
            <td class="label">A光缆路由描述</td>
            <td colspan="3">
                <html:errors property="fiberAroute"/>
                <bean:write name="gprsspeciallineForm" property="fiberAroute" scope="request"/>
            </td>
        </tr>
        </tbody>
    </table>
    <%} %>

    <br>
    <%if (sheetType.equals("businessImplement") || taskName.equals("ExplorateTask") || taskName.equals("") || taskName.equals("CityTask") || taskName.equals("TransfereTask") || taskName.equals("TransferlTask") || taskName.equals("MakeTask") || taskName.equals("AuditTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) { %>
    <table class="formTable">
        <caption>传输线路勘查信息</caption>
        <tbody id='transLineInfo'>
        <tr>
            <td class="label">A最后一公里光缆长度(单位：皮长)</td>
            <td class="content">
                <html:errors property="fiberLengthA"/>
                <bean:write name="gprsspeciallineForm" property="fiberLengthA" scope="request"/>
            </td>
            <td class="label">A光缆产权</td>
            <td class="content">
                <html:errors property="fiberOwnerA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.fiberOwnerA}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">敷设方式</td>
            <td class="content">
                <html:errors property="buildTypeA"/>
                <bean:write name="gprsspeciallineForm" property="buildTypeA" scope="request"/>
            </td>
            <td class="label">A客户端到接入点能否通达</td>
            <td class="content">
                <html:errors property="isOkBetweenUserA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.isOkBetweenUserA}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <c:if test="${gprsspeciallineForm.noInputResonA=='101231001'}">
            <tr>
                <td class="label">A不能接入的原因</td>
                <td colspan='3'>
                    <html:errors property="noInputResonA"/>
                    <bean:write name="gprsspeciallineForm" property="noInputResonA" scope="request"/>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <%} %>
    <br>
    <table class="formTable">
    <table class="formTable">
        <%
            if (sheetType.equals("businessImplement") || taskName.equals("ExplorateTask") || taskName.equals("") || taskName.equals("TransfereTask") ||
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

        <caption>传输容量勘查信息</caption>
        <tbody id='transCardInfo'>
        <tr>
            <td class="label">传输容量是否满足开通</td>
            <td class="content">
                <html:errors property="isDeviceAllowOpenA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.isDeviceAllowOpenA}" beanId="ItawSystemDictTypeDao"/>


            </td>
            <td class="label">是否需要添加板卡</td>
            <td class="content">
                <html:errors property="isNeedAddCardA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.isNeedAddCardA}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <c:if test="${gprsspeciallineForm.isNeedAddCardA=='101231002'}">
            <tr>
                <td class="label">板卡类型</td>
                <td class="content">
                    <html:errors property="cardTypeA"/>
                    <bean:write name="gprsspeciallineForm" property="cardTypeA" scope="request"/>
                </td>
                <td class="label">板卡数量</td>
                <td class="content">
                    <html:errors property="cardNumA"/>
                    <bean:write name="gprsspeciallineForm" property="cardNumA" scope="request"/>
                </td>
            </tr>
        </c:if>
        </tbody>
        <br>
    </table>

    <%} %>


    <%
        if (taskName.equals("ImplementDealTask") || taskName.equals("ExplorateTask") || taskName.equals("CityTask") ||
                taskName.equals("ProjectDealTask") || taskName.equals("MakeTask") || taskName.equals("") ||
                taskName.equals("CityNetTask") || taskName.equals("AuditTask") ||
                taskName.equals("TrasitionTask") || taskName.equals("HandleTask") ||
                taskName.equals("BusinessTestTask") ||
                taskName.equals("DredgeAffirmTask") ||
                taskName.equals("HoldTask")
        ) {
    %>
    <table class="formTable">
        <caption>城域网接入信息</caption>
        <tbody id='cityInfo'>
        <tr>
            <td class="label">城域网接入站点名称</td>
            <td class="content">
                <html:errors property="siteNameZ"/>
                <bean:write name="gprsspeciallineForm" property="siteNameZ" scope="request"/>
            </td>
            <td class="label">城域网接入设备名称</td>
            <td class="content">
                <html:errors property="portZBDeviceName"/>
                <bean:write name="gprsspeciallineForm" property="portZBDeviceName" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">城域网接入设备端口</td>
            <td colspan="3">
                <html:errors property="portZBDevicePort"/>
                <bean:write name="gprsspeciallineForm" property="portZBDevicePort" scope="request"/>
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
        <tr>
            <td class="label">A是否熔接</td>
            <td class="content">
                <html:errors property="isGetInterfaceA"/>
                <eoms:id2nameDB id="${gprsspeciallineForm.isGetInterfaceA}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">A熔接序号</td>
            <td class="content">
                <html:errors property="getInterfaceNoA"/>
                <bean:write name="gprsspeciallineForm" property="getInterfaceNoA" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">A最后一公里处理意见</td>
            <td class="content" colspan="3">
                <bean:write name="gprsspeciallineForm" property="theLastOpinionA" scope="request"/>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <%} %>

    <%
        if (sheetType.equals("businessImplement") && (
                taskName.equals("TrasitionTask") || taskName.equals("") ||
                        taskName.equals("BusinessTestTask") ||
                        taskName.equals("DredgeAffirmTask") ||
                        taskName.equals("HandleTask") ||
                        taskName.equals("HoldTask"))) {
    %>

    <table class="formTable">
        <caption>电路信息</caption>
        <tr>
            <td class="label">电路名称</td>
            <td class="content">
                <html:errors property="circuitName"/>
                <bean:write name="gprsspeciallineForm" property="circuitName" scope="request"/>
            </td>
            <td class="label">电路编号</td>
            <td class="content">
                <bean:write name="gprsspeciallineForm" property="circuitSheetId" scope="request"/>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <%} %>

    <%
        if (sheetType.equals("businessImplement") && (
                taskName.equals("GGSNTask") || taskName.equals("") ||
                        taskName.equals("BusinessTestTask") ||
                        taskName.equals("DredgeAffirmTask") ||
                        taskName.equals("HandleTask") ||
                        taskName.equals("HoldTask"))) {
    %>

    <table class="formTable">
        <caption>GGSN配置</caption>
        <tr>
            <td class="label">第一个GGSN的地址池和掩码</td>
            <td class="content">
                <html:errors property="firstApnAddAndNum"/>
                <bean:write name="gprsspeciallineForm" property="firstApnAddAndNum" scope="request"/></td>
            <td class="label">第二个GGSN的地址池和掩码</td>
            <td class="content">
                <html:errors property="secondApnAddAndNum"/>
                <bean:write name="gprsspeciallineForm" property="secondApnAddAndNum" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">APN路由方式</td>
            <td class="content">
                    <html:errors property="apnRootMethod"/>
                    <bean:write name="gprsspeciallineForm" property="apnRootMethod" scope="request"/>
        </tr>
    </table>
    <%} %>

    <%
        if (sheetType.equals("businessImplement") && (
                taskName.equals("TrasitionTask") || taskName.equals("") ||
                        taskName.equals("BusinessTestTask") ||
                        taskName.equals("DredgeAffirmTask") ||
                        taskName.equals("HandleTask") ||
                        taskName.equals("HoldTask"))) {
    %>

    <br>
    <table class="formTable">
        <caption>APN配置</caption>
        <tbody id='circuitInfo'>
        <tr>
            <td class="label">APN配置</td>
            <td colspan="3">
                <bean:write name="gprsspeciallineForm" property="apnIndex" scope="request"/>
            </td>
        </tr>
        </tbody>
    </table>
    <%} %>
    <br>
    <table class="formTable">
        <tr>
            <td class="label">备注</td>
            <td colspan="3">
                <html:errors property="remark"/>
                <bean:write name="gprsspeciallineForm" property="remark" scope="request"/>
            </td>
        </tr>

        <!--
     	       
<tr>
      <td class="label">A站点设备编码</td>
      <td class="content">
	  	<html:errors property="siteEquipCodeA"/>
         <bean:write name="gprsspeciallineForm" property="siteEquipCodeA" scope="request"/> 
      </td>

	</tr>
   </tr>
 

	    <tr>
      <td class="label">A业务设备名称</td>
      <td class="content">
	  	<html:errors property="portABDeviceName"/>
        <bean:write name="gprsspeciallineForm" property="portABDeviceName" scope="request"/> 
      </td>
		</tr>

	</table>
   <br>
   <table class="formTable">
   <!--  
  <tr>
      <td class="label">A点经度</td>
     <td class="content">
         <html:errors property="longitudeA"/>
         <bean:write name="gprsspeciallineForm" property="longitudeA" scope="request"/>   
     </td>
     <td class="label">A点纬度</td>
     <td class="content">
	  	<html:errors property="latitudeA"/>
       <bean:write name="gprsspeciallineForm" property="latitudeA" scope="request"/>  
     </td>
   </tr>
   <tr>
      <td class="label">订单编号</td>
     <td class="content">
	  	<html:errors property="orderSheet_Id"/>
        <bean:write name="gprsspeciallineForm" property="orderSheet_Id" scope="request"/>  
     </td>  -->


        <!--
     <tr>
     
     <td class="label">GPRS核心网联系人电话</td>
      <td class="content">
	  	<html:errors property="contactPhoneGPRS"/>
        <bean:write name="gprsspeciallineForm" property="contactPhoneGPRS" scope="request"/>  
     </td>
   </tr>
   <tr>
      <td class="label">GPRS核心网经度</td>
      <td class="content">
	  	<html:errors property="longitudeGPRS"/>
        <bean:write name="gprsspeciallineForm" property="longitudeGPRS" scope="request"/>  
     </td>
     <td class="label">GPRS核心网纬度</td>
     <td class="content">
	  	<html:errors property="latitudeGPRS"/>
        <bean:write name="gprsspeciallineForm" property="latitudeGPRS" scope="request"/>  
     </td>
   </tr>
   <tr>
      <td class="label">GPRS核心网详细地址</td>
     <td class="content">
	  	<html:errors property="detailAddGPRS"/>
        <bean:write name="gprsspeciallineForm" property="detailAddGPRS" scope="request"/>  
     </td>
     <td class="label">GPRS核心网联系人</td>
     <td class="content">
	  	<html:errors property="contactUserGPRS"/>
        <bean:write name="gprsspeciallineForm" property="contactUserGPRS" scope="request"/>  
     </td>
   </tr> 
   

 
   
   <tr>
      <td class="label">集团用户对端Ip</td>
     <td class="content">
	  	<html:errors property="groupCustomIP"/>
        <bean:write name="gprsspeciallineForm" property="groupCustomIP" scope="request"/>  
     </td>
	</tr>
    <tr>
     <td class="label">radius地址</td>
     <td class="content">
	  	<html:errors property="radiusAddress"/>
        <bean:write name="gprsspeciallineForm" property="radiusAddress" scope="request"/>  
     </td>
	</tr>


    
	<tr>
           <td class="label">APN域名</td>
     <td class="content">
	  	<html:errors property="apnScopeName"/>
	  	 <bean:write name="gprsspeciallineForm" property="apnScopeName" scope="request"/> 
     </td>
	</tr>


	<!--  
     <tr>
      <td class="label">是否双GGSN</td>
     <td colspan='3'>
	  	<html:errors property="isDoubleGGSN"/>
      <eoms:id2nameDB id="${gprsspeciallineForm.isDoubleGGSN}" beanId="ItawSystemDictTypeDao"/></td>
	</tr>
	
 

	<tr>
      <td class="label">用户应用服务器IP</td>
     <td class="content">
	  	<html:errors property="userAppServerIP"/>
     <bean:write name="gprsspeciallineForm" property="userAppServerIP" scope="request"/>
     </td>
</tr>
<tr>
      <td class="label">号码明细</td>
     <td class="content">
	  	<html:errors property="numberDecrpt"/>
        <bean:write name="gprsspeciallineForm" property="numberDecrpt" scope="request"/>
     </td>
	</tr>




  	</table>
    <br>

     <table class="formTable">
		
		     <c:if test="${gprsspeciallineForm.isDoubleGGSN == '101231002'}"> 
		     <tr>
		      <td class="label">第一个GGSN的地址池和掩码</td>
		     <td class="content">
			  	<html:errors property="firstApnAddAndNum"/>
		      <bean:write name="gprsspeciallineForm" property="firstApnAddAndNum" scope="request"/> </td>
		      <td class="label">第二个GGSN的地址池和掩码</td>
		     <td class="content">
			  	<html:errors property="secondApnAddAndNum"/>
		        <bean:write name="gprsspeciallineForm" property="secondApnAddAndNum" scope="request"/> </td>
		     </td>
			</tr>
			</c:if>
	
        </table>
  
    <br>
   <table class="formTable">
	<tr>
      <td class="label">A客户位置经度</td>
      <td class="content">
	  	<html:errors property="userSiteAA"/>
            <bean:write name="gprsspeciallineForm" property="userSiteAA" scope="request"/> 
       
      </td>
	  <td class="label">A客户位置纬度	</td>
      <td class="content">
      	<html:errors property="userSiteHA"/>
           <bean:write name="gprsspeciallineForm" property="userSiteHA" scope="request"/> 
       
     </td>
    </tr>
    </table>
  	 <br>
	<table  class="formTable">

	<tr>
         
      <td class="label">A接入站点标识</td>
      <td class="content">
	  	<html:errors property="accessSiteIdenA"/>
      <bean:write name="gprsspeciallineForm" property="accessSiteIdenA" scope="request"/> 
      </td>
      </tr>
      

	</table>
		<br>
	
	<br>
	<table class="formTable">
	<tr>

      <td class="label">A建设方式</td>
      <td class="content">
	  	<html:errors property="buildMethodA"/>
	  	 <eoms:id2nameDB id="${gprsspeciallineForm.buildMethodA}" beanId="ItawSystemDictTypeDao"/>
      </td>
      <td class="label">Z最后一公里光缆长度</td>
      <td class="content">
	  	<html:errors property="fiberLengthZ"/>
       <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
    <tr>

    </tr>
	<tr>
	     <td class="label">A客户端到接入点能否通达</td>
      	<td class="content">
	  	<html:errors property="isOkBetweenUserA"/>
	  	 <eoms:id2nameDB id="${gprsspeciallineForm.isOkBetweenUserA}" beanId="ItawSystemDictTypeDao"/>
         </td>
	</tr>
	</table>
	     <br>
  

    <table class="formTable">

     

  
    </table>
        <br>


    <table class="formTable">
	
    </table>
	 <br>
 

	    <table class="formTable">
	
	 <tr>
      <td class="label">A业务接入点客户联系人</td>
      <td class="content">
	  	<html:errors property="interfaceCustomConnPerson"/>
         <bean:write name="gprsspeciallineForm" property="interfaceCustomConnPerson" scope="request"/> 
      </td>
      <td class="label">A业务接入点客户联系电话</td>
      <td class="content">
	  	<html:errors property="interfaceCustomConnPhone"/>
        <bean:write name="gprsspeciallineForm" property="interfaceCustomConnPhone" scope="request"/> 
      </td>
	</tr>
	    <tr>
      <td class="label">A业务接入点客户联系邮箱</td>
      <td class="content">
	  	<html:errors property="interfaceCustomConnMail"/>
        <bean:write name="gprsspeciallineForm" property="interfaceCustomConnMail" scope="request"/> 
      </td>
      <td class="label">A业务接入点客户联系地址</td>
      <td class="content">
	  	<html:errors property="interfaceCustomConnAdd"/>
    <bean:write name="gprsspeciallineForm" property="interfaceCustomConnAdd" scope="request"/> 
      </td>
	</tr>


      	</table>
	<tr>


	<table class="formTable">
  
     </table>
    <br>
 

   <table class="formTable">  



	  </table>
	  <br>

  
  
    <table class="formTable">
    
        <tr>
      <td class="label">Z客户端标准地址</td>
       <td  colspan="3">
           <bean:write name="gprsspeciallineForm" property="userStardAddZ" scope="request"/> 
		</td>
	</tr>
    <tr>
   	     <td class="label">Z站点名称</td>
        <td class="content">
	  	<html:errors property="siteNameZ"/>
       <bean:write name="gprsspeciallineForm" property="siteNameZ" scope="request"/>
        
      </td>
      <td class="label">Z站点设备编码</td>
      <td class="content">
	  	<html:errors property="siteEquipCodeZ"/>
       <bean:write name="gprsspeciallineForm" property="siteEquipCodeZ" scope="request"/>
	</tr>
	<tr>
      <td class="label">Z机房名称</td>
      <td class="content">
	  	<html:errors property="zpointComputerHorseName"/>
       <bean:write name="gprsspeciallineForm" property="zpointComputerHorseName" scope="request"/>
      </td>
      <td class="label">Z接口类型及型号</td>
      <td class="content">
	  	<html:errors property="portZInterfaceType"/>
	  	<eoms:id2nameDB id="${gprsspeciallineForm.portZInterfaceType}" beanId="ItawSystemDictTypeDao"/>
      </td>
	</tr>
      <tr>
	<td class="label">z端客户当地配合人</td>
     <td class="content">
	  	<html:errors property="zpointLocalPerson"/>
	  	<bean:write name="gprsspeciallineForm" property="zpointLocalPerson" scope="request"/>
     </td>
           <td class="label">Z客户在当地的配合人的联系电话</td>
      <td class="content">
	  	<html:errors property="portZContactPhone"/>
      <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
   </tr>

   <tr>
        <td class="label">Z端点业务设备名称</td>
     <td class="content">
	  	<html:errors property="portZBDeviceName"/>
       <bean:write name="gprsspeciallineForm" property="portZBDeviceName" scope="request"/>  
     </td>


   </tr>
	<tr>
      <td class="label">Z业务设备名称</td>
      <td class="content">
	  	<html:errors property="portZBDeviceName"/>
       <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
      <td class="label">Z客户在当地的配合人</td>
      <td class="content">
	  	<html:errors property="zpointLocalPerson"/>
       <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
	</tr>
	    <tr>

      <td class="label">Z端点详细地址</td>
      <td class="content">
	  	<html:errors property="portZDetailAdd"/>
     <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
	</tr>
	    <tr>
      <td class="label">Z业务接入点客户联系人</td>
      <td class="content">
	  	<html:errors property="interfaceCustomConnPersonZ"/>
      <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
      <td class="label">Z业务接入点客户联系电话</td>
      <td class="content">
	  	<html:errors property="interfaceCustomConnPhoneZ"/>
       <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
	</tr>
	    <tr>
      <td class="label">Z业务接入点客户联系邮箱</td>
      <td class="content">
	  	<html:errors property="interfaceCustomConnMailZ"/>
       <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
      <td class="label">Z业务接入点客户联系地址</td>
      <td class="content">
	  	<html:errors property="interfaceCustomConnAddZ"/>
      <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
	</tr>
	    <tr>
      <td class="label">Z光纤设备名称</td>
      <td class="content">
	  	<html:errors property="fiberEquipNameZ"/>
      <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
      <td class="label">Z光纤设备编号</td>
      <td class="content">
	  	<html:errors property="fiberEquipCodeZ"/>
      <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
	</tr>

	
	    <tr>
      <td class="label">Z接入站点标识</td>
      <td class="content">
	  	<html:errors property="accessSiteIdenZ"/>
       <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>

	</tr>
	
	<tr>
      <td class="label">Z光缆产权</td>
      <td class="content">
	  	<html:errors property="fiberOwnerZ"/>
	  	<eoms:id2nameDB id="${gprsspeciallineForm.fiberOwnerZ}" beanId="ItawSystemDictTypeDao"/>
	  	 </td>
      
            <td class="label">Z是否熔接</td>
      <td class="content">
	  	<html:errors property="isGetInterfaceZ"/>
        <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      
      </td>
    </tr>
	<tr>
      <td class="label">Z熔接序号</td>
      <td colspan='3'>
	  	<html:errors property="getInterfaceNoZ"/>
       <bean:write name="gprsspeciallineForm" property="getInterfaceNoZ" scope="request"/>
      </td>
	</tr>
    <tr>
      <td class="label">Z最后一公里处理意见</td>
       <td  class="content" colspan="3">
       <bean:write name="gprsspeciallineForm" property="detailAddGPRS" scope="request"/>
		</td>
	</tr>
  
-->

    </table>
    <table>
        <%if (!isView.equals("1") || taskName.equals("AcceptTask") || taskName.equals("ImplementDealTask")) {%>
        <input type="button" value="修改" onclick="modify();">
        <%} %>
        <input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">
    </table>
</html:form>

<!-- footer_eoms.jsp end-->