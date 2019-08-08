<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.base.util.UUIDHexGenerator" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>

<%
    request.setAttribute("roleId", "915");

    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();

    String orderId = UUIDHexGenerator.getInstance().getID();
    String orderSheetId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("orderSheetId"));
    String mainBelongPronice = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("mainBelongPronice"));
    String mainBelongCity = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("mainBelongCity"));
    System.out.println("@@mainBelongPronice" + mainBelongPronice);
    String specialtyType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("specialtyType"));
    System.out.println("@@specialtyType" + specialtyType);
    if (orderSheetId != null && !"".equals(orderSheetId)) {
        orderId = orderSheetId;
    }
    //
    String isShowLanguage = "false";
    if (specialtyType.equals("101230104")) {
        isShowLanguage = "yes";
    }
    if (mainBelongPronice.equals("yes")) {
        isShowLanguage = "yes";
    }

    String isShowSms = "false";
    // if(orderSheetId != null && !"".equals(orderSheetId)){
    //      isShowSms ="yes";
    //}
    if (mainBelongCity.equals("yes")) {
        isShowSms = "yes";
    }
    String startType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("startType"));
    System.out.println("@input =orderSheetId" + orderSheetId);
    System.out.println("@input =startType" + startType);
    System.out.println("@inputmian =isShowLanguage=" + isShowLanguage);
    System.out.println("@inputmian  =isShowSms     =" + isShowSms);
%>
<%@ include file="/WEB-INF/pages/wfworksheet/businessimplement/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    //selectLimit();
    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "businessimplement.do?method=newShowLimit&flowName=BusinessImplementProcess",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    // $("${sheetPageName}sheetAcceptLimit").value = "";
                    // $('${sheetPageName}sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date().add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    $("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }

    <% if(!isShowLanguage.equals("yes")&& startType ==""){ %>
    changeOperate = function (input) {

        if (input == 101230101) {
            eoms.form.enableArea('GPRS');
            eoms.form.disableArea('InterNet', true);
            eoms.form.disableArea('Transition', true);

        } else if (input == 101230102) {
            eoms.form.enableArea('InterNet');
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('Transition', true);
        } else if (input == 101230103) {
            eoms.form.enableArea('Transition');
            eoms.form.disableArea('InterNet', true);
            eoms.form.disableArea('GPRS', true);
        }
        refreshFrame();
    }
    var frm = document.forms[0];
    var temp = frm.mainSpecifyType ? frm.mainSpecifyType.value : '';
    if (temp != '') {
        changeOperate(temp);
    }
    <%}%>
</script>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="BusinessImplementProcess"/>
<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessImplementProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet"/>
<input type="hidden" name="${sheetPageName}mainBelongPronice" value="<%=isShowLanguage %>"/>
<input type="hidden" name="${sheetPageName}mainBelongCity" value="<%=startType %>"/>
<input type="hidden" name="${sheetPageName}mainBelongZone" value="<%=specialtyType %>"/>


<input type="hidden" name="${sheetPageName}orderSheetId" value="<%=orderSheetId %>"/>

<c:if test="${status!=1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ImplementDealTask"/>
    <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType"
           value="${sheetPageName}operateType"/>
    <input type="hidden" name="${sheetPageName}gatherPhaseId" id="${sheetPageName}gatherPhaseId" value="HoldTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink"/>

<input type="hidden" name="${sheetPageName}orderId" value="<%=orderId%>"/>
<br>
<%if (isShowLanguage.equals("yes")) { %>

<fieldset id="link10">
    <legend>客户相关信息</legend>
    <table class="formTable">
        <tr>
            <td class="label">客户名称*</td>
            <td class="content"><bean:write name="orderSheet" property="customName" scope="request"/></td>
            </td>
            <td class="label">客户地址*</td>
            <td class="content"><bean:write name="orderSheet" property="customAdd" scope="request"/></td>
        </tr>
        <tr>
            <td class="label">客户联系人</td>
            <td class="content"><bean:write name="orderSheet" property="customContact" scope="request"/></td>

            <td class="label">客户联系人电话</td>
            <td class="content"><bean:write name="orderSheet" property="customContactPhone" bundle="businessimplementyy"
                                            scope="request"/></td>

        </tr>
        <tr>
            <td class="label">集团客户联系人邮箱</td>
            <td class="content"><bean:write name="orderSheet" property="customConnMain" bundle="businessimplementyy"
                                            scope="request"/></td>
            <td class="label">集团客户编号*</td>
            <td class="content"><bean:write name="orderSheet" property="groupCustomNo" bundle="businessimplementyy"
                                            scope="request"/></td>
        </tr>
    </table>
</fieldset>
<fieldset id="link1">
    <legend>CMCC客户经理信息</legend>
    <table class="formTable">
        <br>
        <tr>
            <td class="label">客户经理姓名*</td>
            <td class="content"><bean:write name="orderSheet" property="cmanagerPhone" bundle="businessimplementyy"
                                            scope="request"/></td>
            <td class="label">业务所属区域*</td>
            <td class="content"><bean:write name="orderSheet" property="businesBelongCity" bundle="businessimplementyy"
                                            scope="request"/></td>
        </tr>
        <!-- old end-->
        <tr>
            <td class="label">客户经理联系邮箱*</td>
            <td class="content"><bean:write name="orderSheet" property="customManagerMail" bundle="businessimplementyy"
                                            scope="request"/></td>
            <td class="label">客户经理电话*</td>
            <td class="content"><bean:write name="orderSheet" property="projectManagerPhone"
                                            bundle="businessimplementyy" scope="request"/></td>
        </tr>
    </table>
</fieldset>
<fieldset id="link2">
    <legend>合同号信息</legend>
    <table class="formTable">
        <br>
        <tr>
            <td class="label">合同号</td>
            <td class="content"><bean:write name="orderSheet" property="productSN" bundle="businessimplementyy"
                                            scope="request"/></td>
            <td class="label">签订日期</td>
            <td class="content">

            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                       id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                       alt="allowBlank:true"/>

            </td>
        </tr>
    </table>
</fieldset>
<fieldset id="link3">
    <legend>技术方案信息</legend>
    <table class="formTable">
        <br>
        <tr>
            <td class="label">技术方案描述</td>
            <td class="content"><bean:write name="orderSheet" property="needDesc" bundle="businessimplementyy"
                                            scope="request"/></td>
        </tr>

    </table>
</fieldset>
<br>
<%} else if (startType != null && startType != "") { %>
<br>
<fieldset>
    <legend>集团客户信息</legend>
    <table class="formTable">
        <!-- added by liufuyuan -->
        <tr>

            <td class="label">集团客户名称</td>
            <td colspan='3'>
                <html:errors property="customName"/>
                <bean:write name="orderSheet" property="customName" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团客户名称</td>
            <td class="content">
                <bean:write name="orderSheet" property="customName" scope="request"/>
            </td>
            <td class="label">集团客户级别</td>
            <td class="content"><eoms:id2nameDB id="${orderSheet.customLevel}" beanId="ItawSystemDictTypeDao"/>
            </td>
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
    </table>
</fieldset>
<br>
<fieldset>
    <table class="formTable">
        <legend>客户业务联系人信息</legend>
        <tr>
            <td class="label">集团客户联系人</td>
            <td class="content">

                <bean:write name="orderSheet" property="customContact" scope="request"/>
            </td>
            <td class="label">集团客户联系人电话</td>
            <td class="content">

                <bean:write name="orderSheet" property="customContactPhone" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团客户联系人邮箱</td>
            <td class="content">

                <bean:write name="orderSheet" property="customConnMain" scope="request"/>
            </td>
            <td class="label">客户地址</td>
            <td class="content">

                <bean:write name="orderSheet" property="customAdd" scope="request"/>
            </td>
        </tr>
    </table>
</fieldset>
<br>
<fieldset>
    <legend>客户经理信息</legend>
    <table class="formTable">
        <tr>
            <td class="label">客户经理姓名</td>
            <td class="content">

                <bean:write name="orderSheet" property="cmanagerPhone" scope="request"/>
            </td>
            <td class="label">客户经理联系电话</td>
            <td class="content">

                <bean:write name="orderSheet" property="cmanagerContactPhone" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">客户经理联系邮箱</td>
            <td class="content">

                <bean:write name="orderSheet" property="customManagerMail" scope="request"/>
            </td>
            <td class="label">客户经理部门</td>
            <td class="content">

                <bean:write name="orderSheet" property="customManagerDept" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">所属地市</td>
            <td class="content">

                <bean:write name="orderSheet" property="cityName" scope="request"/>
            </td>
            <td class="label">客户经理工号</td>
            <td class="content">

                <bean:write name="orderSheet" property="bdeptContact" scope="request"/>
            </td>
        </tr>
    </table>
</fieldset>
<br>
<fieldset>
    <legend>产品信息</legend>
    <table class="formTable">
        <tr>
            <td class="label">产品名称</td>
            <td class="content">

                <bean:write name="orderSheet" property="productName" scope="request"/>
            </td>
            <td class="label">资源确认的CRM侧工单号</td>
            <td class="content">

                <bean:write name="orderSheet" property="mainProductInstanceCode" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团客户联系人邮箱</td>
            <td class="content">

                <bean:write name="orderSheet" property="customConnMain" scope="request"/>
            </td>
            <td class="label">拟生效时间</td>
            <td class="content">

                <bean:write name="orderSheet" property="planExecuteTime" scope="request"/>
            </td>
        </tr>
    </table>
</fieldset>
<br>
<fieldset>
    <legend>项目经理信息</legend>
    <table class="formTable">
        <tr>
            <td class="label">项目名称</td>
            <td colspan="3">

                <bean:write name="orderSheet" property="productName" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">项目经理姓名</td>
            <td class="content">

                <bean:write name="orderSheet" property="projectManagerName" scope="request"/>
            </td>

            <td class="label">项目经理电话</td>
            <td class="content">

                <bean:write name="orderSheet" property="projectManagerPhone" scope="request"/>
            </td>
        </tr>
    </table>
</fieldset>
<br>
<fieldset>
    <legend>审核人信息</legend>
    <table class="formTable">
        <tr>
            <td class="label">审核人姓名</td>
            <td class="content">
                <bean:write name="orderSheet" property="checkPerson" scope="request"/>
            </td>
            <td class="label">审核人部门</td>
            <td class="content">
                <bean:write name="orderSheet" property="checkDepmt" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">审核人电话</td>
            <td class="content">

                <bean:write name="orderSheet" property="checkPhone" scope="request"/>
            </td>
            <td class="label">审核人邮箱</td>
            <td class="content">

                <bean:write name="orderSheet" property="checkMail" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">审核人意见</td>
            <td class="content">

                <bean:write name="orderSheet" property="checkComments" scope="request"/>
            </td>
            <td class="label">审核时间</td>
            <td class="content">

                <bean:write name="orderSheet" property="checkTime" scope="request"/>
            </td>
        </tr>
    </table>
</fieldset>
<br>

<%} else { %>
<fieldset>
    <legend>集团客户信息</legend>
    <table class="formTable">
        <tr>
            <td class="label">集团客户名称*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}customName" id="${sheetPageName}customName"
                       alt="allowBlank:false"/>
            </td>
            <td class="label">集团客户级别</td>
            <td class="content">
                <eoms:id2nameDB id="${orderSheet.customLevel}" beanId="ItawSystemDictTypeDao"/>
                <eoms:comboBox name="${sheetPageName}customLevel" id="${sheetPageName}customLevel" initDicId="10405"
                               styleClass="select-class" alt="allowBlank:false"
                               defaultValue="${sheetMain.mainArgument}"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团客户编号*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}groupCustomNo" id="${sheetPageName}groupCustomNo"
                       alt="allowBlank:false"/>
            </td>
            <td class="label">集团客户行业*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}groupCustomBusinesType"
                       id="${sheetPageName}groupCustomBusinesType" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团客户联系地址</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}groupCustomAdd"
                       id="${sheetPageName}groupCustomAdd" alt="allowBlank:true"/>
            </td>
            <td class="label">集团客户联系邮编</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}groupCustomMail"
                       id="${sheetPageName}groupCustomMail" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">业务所属地市</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}businesBelongCity"
                       id="${sheetPageName}businesBelongCity" alt="allowBlank:true"/>
            </td>
            <td class="label">集团客户业务范围*</td>
            <td class="content"><eoms:id2nameDB id="${orderSheet.groupCustomBusinessScope}"
                                                beanId="ItawSystemDictTypeDao"/>
                <eoms:comboBox name="${sheetPageName}groupCustomBusinessScope"
                               id="${sheetPageName}groupCustomBusinessScope" initDicId="1012307"
                               styleClass="select-class" alt="allowBlank:false" defaultValue=""/>
            </td>
        </tr>
    </table>
</fieldset>
<fieldset>
    <legend>客户业务联系人信息</legend>
    <table class="formTable">

        <!-- added by liufyuan end -->
        <tr>
            <td class="label">集团客户联系人</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}customContact" id="${sheetPageName}customContact"
                       alt="allowBlank:false"/>
            </td>
            <td class="label">集团客户联系人电话</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}customContactPhone"
                       id="${sheetPageName}customContactPhone" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团客户联系人邮箱</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}customConnMain"
                       id="${sheetPageName}customConnMain" alt="allowBlank:true"/>
            </td>
            <td class="label">客户地址</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}customAdd" id="${sheetPageName}customAdd"
                       alt="allowBlank:true"/>
            </td>
        </tr>
    </table>
</fieldset>
<fieldset id="link1">
    <legend>客户经理信息</legend>
    <table class="formTable">
        <br>
        <tr>
            <td class="label">客户经理姓名*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}cmanagerPhone" id="${sheetPageName}cmanagerPhone"
                       alt="allowBlank:false"/>
            </td>
            <td class="label">客户经理联系电话*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}cmanagerContactPhone"
                       id="${sheetPageName}cmanagerContactPhone" alt="allowBlank:false"/>
            </td>
        </tr>
        <!-- old end-->
        <tr>
            <td class="label">客户经理联系邮箱</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}customManagerMail"
                       id="${sheetPageName}customManagerMail" alt="allowBlank:true"/>
            </td>
            <td class="label">客户经理部门</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}customManagerDept"
                       id="${sheetPageName}customManagerDept" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">所属地市</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}cityName" id="${sheetPageName}cityName"
                       alt="allowBlank:false"/>
            </td>
            <td class="label">客户经理工号</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact"
                       alt="allowBlank:false"/>
            </td>
        </tr>
        <!-- end -->

    </table>
</fieldset>
<fieldset id="link1">
    <legend>产品信息</legend>
    <table class="formTable">
        <tr>
            <td class="label">产品名称</td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}productName" id="${sheetPageName}productName"
                       alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">资源确认的CRM侧工单号</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainProductInstanceCode"
                       id="${sheetPageName}mainProductInstanceCode" alt="allowBlank:false"/>
            </td>
            <td class="label">拟生效时间</td>
            <td class="content">
            </td>
        </tr>
    </table>
</fieldset>
<fieldset>
    <legend>项目经理信息</legend>
    <table class="formTable">
        <tr>
            <td class="label">项目名称</td>
            <td colspan='3'>
                <input type="text" class="text" name="${sheetPageName}productName" id="${sheetPageName}productName"
                       alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">项目经理姓名</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}projectManagerName"
                       id="${sheetPageName}projectManagerName" alt="allowBlank:false"/>
            </td>
            <td class="label">项目经理电话</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}projectManagerPhone"
                       id="${sheetPageName}projectManagerPhone" alt="allowBlank:false"/>
            </td>
        </tr>
    </table>
</fieldset>
<fieldset>
        <%} %>
    <legend>申请业务信息</legend>
    <table class="formTable">
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                       id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

            </td>
            <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>

            </td>
        </tr>
        <% if (isShowLanguage.equals("yes") || (startType.equals("startTranfer"))) { %>
        <input type="hidden" name="${sheetPageName}mainSpecifyType" value="101230103"/>
        <%} else if (startType.equals("startIp")) {%>
        <input type="hidden" name="${sheetPageName}mainSpecifyType" value="101230102"/>
        <%} else {%>
        <tr>
            <td class="label">专线类型*</td>
            <td><eoms:comboBox name="${sheetPageName}mainSpecifyType" id="${sheetPageName}mainSpecifyType"
                               defaultValue="${sheetMain.mainSpecifyType}"
                               initDicId="1012301" alt="allowBlank:false" styleClass="select-class"/>
            </td>
            <%} %>
            <td class="label">紧急程度*</td>
            <td><eoms:comboBox name="${sheetPageName}mainArgument" id="${sheetPageName}mainArgument" initDicId="1012302"
                               styleClass="select-class" alt="allowBlank:false"
                               defaultValue="${sheetMain.mainArgument}"/>
            </td>
        </tr>
        <tr>
            <td class="label">需求描述</td>
            <td colspan="3">
		        <textarea name="${sheetPageName}needDesc" class="textarea max" id="${sheetPageName}needDesc"
                          alt="allowBlank:true,width:200,vtext:'请最多输入100字'" alt="width:'200px'"></textarea>
            </td>
        </tr>


        <% if (isShowLanguage.equals("yes") && (startType == "")) { %>

        <iframe id="frame3"
                src="${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=<%=orderSheetId %>&taskStatus=${taskStatus}&specialtyType=101230104&sheetType=businessImplementyy&taskName=LauguageTask&isShowLanguage=yes"
                width="100%" height="300px"></iframe>

        <%} else if (startType != null && startType != "") {%>
        <iframe id="frame4"
                src="${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=<%=orderSheetId %>&taskStatus=${taskStatus}&specialtyType=<%=specialtyType %>&sheetType=businessImplementSms&taskName=TranferTask&isShowSms=yes"
                width="100%" height="300px"></iframe>
        <%} else {%>
        <tr>
            <td colspan="4">
                <input type="button" class="btn" value="添加产品" onclick="javascript:openwin()">
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <iframe id="frame" src="" width="100%" height="300px" style="display:none"></iframe>
            </td>
        </tr>
        <%} %>
    </table>
    <c:if test="${status!=1}">
            <%if(isShowLanguage.equals("yes")||startType !=""){ %>
    <fieldset>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
            <span id="roleName1">:线缆施工
			 </span>
        </legend>
        <div class="x-form-item">
            <eoms:chooser id="test1" type="dept" roleId="917" flowId="320" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          data="${sendUserAndRoles}"/>
        </div>
    </fieldset>

            <%} else{%>
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
            <span id="roleName">:需求审核人
			 </span>
        </legend>
        <div class="x-form-item">
            <eoms:chooser id="test1" type="role" roleId="916" flowId="320" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          data="${sendUserAndRoles}"/>
        </div>
    </fieldset>
            <%} %>

    </c:if>

        <% if(!isShowLanguage.equals("yes") &&  startType ==""){ %>
    <script type="text/javascript">
        changeOperate(document.getElementById('mainSpecifyType').value);

        function openwin() {
            var mainSpecifyType = document.getElementById("mainSpecifyType").value;

            if (mainSpecifyType == '') {
                alert('请先选择专业类型');
                return;
            }
            var actiontype = '';
            if (mainSpecifyType == '101230101') {
                actiontype = "gprsspeciallines";
            }
            if (mainSpecifyType == '101230102') {
                actiontype = "ipspeciallines";
            }
            if (mainSpecifyType == '101230103') {
                actiontype = "transferspeciallines";
            }
            if (mainSpecifyType == '101230104') {
                actiontype = "languagespeciallines";
            }
            var duanXinCaiXinType = '';
            if (mainSpecifyType == '101230105') {
                duanXinCaiXinType = 'CaiXin';
                actiontype = "smsspeciallines";
            }
            if (mainSpecifyType == '101230106') {
                duanXinCaiXinType = 'DuanXin';
                actiontype = "smsspeciallines";
            }

            var urls = "${app}/businessupport/product/" + actiontype + ".do?method=xedit&type=add&sheetType=businessImplement&ifReference=&orderId=<%=orderId%>&duanXinCaiXinType=" + duanXinCaiXinType;
            window.showModalDialog(urls, "", "dialogWidth=1200px;dialogHeight=100px;help:no;resizable:yes;status:no;dialogWidth=1024px;dialogHeight=700px");
            obj.style.display = "block";
            refreshFrame();
        }

        function refreshFrame() {
            var mainSpecifyType = document.getElementById("mainSpecifyType").value;

            obj = document.all["frame"];
            if (obj.style.display == "none")
                return false;

            obj.src = "${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=<%=orderId%>&specialtyType=" + mainSpecifyType + "&sheetType=resourceConfirm&taskName=${taskName}&taskStatus=${taskStatus}";

        }

    </script>
        <%} %>
