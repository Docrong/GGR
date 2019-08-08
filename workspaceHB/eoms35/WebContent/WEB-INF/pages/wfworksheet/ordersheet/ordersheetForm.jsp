<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<script type="text/javascript">
    <!--验证-->
    function initPage() {
        v = new eoms.form.Validation({form: 'ordersheetForm'});
    }

    Ext.onReady(
        function () {
            initPage();
        }
    );

    function query() {
        var formObj = document.forms[0];
        formObj.action = "ordersheets.do?method=relationSheet";
        formObj.submit();
    }

</script>
<%String flag = (String) request.getAttribute("flag");%>
<% String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if (ifReference.equals(""))
        ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
    request.setAttribute("ifReference", ifReference);
    System.out.println("form ifReference=" + ifReference);
%>
<title><fmt:message key="ordersheetDetail.title"/></title>
<content tag="heading">定单产品信息</content>
<html:form action="ordersheets.do?method=xsave&ifReference=${ifReference}" method="post" styleId="ordersheetForm">
    <br>
    <table class="formTable">
        <input type="hidden" id="id" name="id" value="${ordersheetForm.id}"/>
        <tr>
            <td class="label">定单类型*</td>
            <td class="content">
                <html:errors property="orderType"/>
                <eoms:comboBox name="orderType" id="orderType" defaultValue="${ordersheetForm.orderType}"
                               initDicId="10401"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>

            <td class="label">紧急程度*</td>
            <td class="content">
                <html:errors property="urgentDegree"/>
                <eoms:comboBox name="urgentDegree" id="urgentDegree" defaultValue="${ordersheetForm.urgentDegree}"
                               initDicId="1010102"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">定单业务类型*</td>
            <td class="content">
                <html:errors property="orderBuisnessType"/>
                <eoms:comboBox name="orderBuisnessType" id="orderBuisnessType"
                               defaultValue="${ordersheetForm.orderBuisnessType}" initDicId="1012301"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
            <td class="label">是否竣工</td>
            <td class="content">
                <html:errors property="isCompleted"/>
                <eoms:comboBox name="isCompleted" id="isCompleted" defaultValue="${ordersheetForm.isCompleted}"
                               initDicId="10407"
                               alt="allowBlank:true" styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">定单编号*</td>
            <td class="content">
                <html:errors property="orderNumber"/>
                <html:text property="orderNumber" styleId="orderNumber" styleClass="text medium"
                           alt="allowBlank:false"/>
            </td>
            <td class="label">主产品实例编码</td>
            <td class="content">
                <html:errors property="mainProductInstanceCode"/>
                <html:text property="mainProductInstanceCode" styleId="mainProductInstanceCode" styleClass="text medium"
                           alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label">生成时间*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}creatTime" readonly="readonly"
                       id="${sheetPageName}creatTime" value="${ordersheetForm.creatTime}"
                       onclick="popUpCalendar(this, this)"/>
            </td>
            <td class="label">竣工时间</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}endTime" readonly="readonly"
                       id="${sheetPageName}endTime" value="${eoms:date2String(ordersheetForm.endTime)}"
                       onclick="popUpCalendar(this, this)"/>
            </td>
        </tr>
        <tr>
            <td class="label">完成期限*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}completeLimit" readonly="readonly"
                       id="${sheetPageName}completeLimit" value="${eoms:date2String(ordersheetForm.completeLimit)}"
                       onclick="popUpCalendar(this, this)"/>
            </td>
            <td class="label">变更时间</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}changeTime" readonly="readonly"
                       id="${sheetPageName}changeTime" value="${eoms:date2String(ordersheetForm.changeTime)}"
                       onclick="popUpCalendar(this, this)"/>
            </td>
        </tr>
        <tr>
            <td class="label">撤销时间</td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}cancelTime" readonly="readonly"
                       id="${sheetPageName}cancelTime" value="${eoms:date2String(ordersheetForm.cancelTime)}"
                       onclick="popUpCalendar(this, this)"/>
            </td>
        </tr>
    </table>
    <fieldset>
        <legend>客户相关信息</legend>
        <table class="formTable">
            <tr>
                <td class="label">集团编号*</td>
                <td class="content">
                    <html:errors property="customNo"/>
                    <html:text property="customNo" styleId="customNo" styleClass="text medium" alt="allowBlank:false"/>
                </td>
                <td class="label">集团名称*</td>
                <td class="content">
                    <html:errors property="customName"/>
                    <html:text property="customName" styleId="customName" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">集团级别*</td>
                <td class="content">
                    <html:errors property="customLevel"/>
                    <eoms:comboBox name="customLevel" id="customLevel" defaultValue="${ordersheetForm.customLevel}"
                                   initDicId="10405"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>
                <td class="label">所属省份*</td>
                <td class="content">
                    <html:errors property="provinceName"/>
                    <html:text property="provinceName" styleId="provinceName" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">所属地市*</td>
                <td class="content">
                    <html:errors property="cityName"/>
                    <html:text property="cityName" styleId="cityName" styleClass="text medium" alt="allowBlank:false"/>
                </td>
                <td class="label">所属区县*</td>
                <td class="content">
                    <html:errors property="countyName"/>
                    <html:text property="countyName" styleId="countyName" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset>
        <legend>申请业务信息</legend>
        <table class="formTable">
            <tr>
                <td class="label">产品名称*</td>
                <td class="content">
                    <html:errors property="productName"/>
                    <html:text property="productName" styleId="productName" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
                <td class="label">产品类别*</td>
                <td class="content">
                    <html:errors property="productKind"/>
                    <eoms:comboBox name="productKind" id="productKind" defaultValue="${ordersheetForm.productKind}"
                                   initDicId="10404"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>
            </tr>
            <tr>
                <td class="label">产品编号*</td>
                <td class="content">
                    <html:errors property="productID"/>
                    <html:text property="productID" styleId="productID" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
                <td class="label">产品序列号*</td>
                <td class="content">
                    <html:errors property="productSN"/>
                    <html:text property="productSN" styleId="productSN" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">电路类别*</td>
                <td class="content">
                    <html:errors property="isNonLocal"/>
                    <eoms:comboBox name="isNonLocal" id="isNonLocal" defaultValue="${ordersheetForm.isNonLocal}"
                                   initDicId="10406"
                                   alt="allowBlank:false" styleClass="select-class"/>
                </td>
                <td class="label">传输承载的业务*</td>
                <td class="content">
                    <html:errors property="transfBusiness"/>
                    <html:text property="transfBusiness" styleId="transfBusiness" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">业务需求描述</td>
                <td class="content" colspan="3">
                    <textarea name="brequirementDesc" id="brequirementDesc" class="textarea max"
                              alt="allowBlank:true">${ordersheetForm.brequirementDesc}</textarea>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset>
        <legend>相关联系人信息</legend>
        <table class="formTable">
            <tr>
                <td class="label">客户经理*</td>
                <td class="content">
                    <html:errors property="cmanagerPhone"/>
                    <html:text property="cmanagerPhone" styleId="cmanagerPhone" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
                <td class="label">客户经理联系电话*</td>
                <td class="content">
                    <html:errors property="cmanagerContactPhone"/>
                    <html:text property="cmanagerContactPhone" styleId="cmanagerContactPhone" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">集团客户联系人*</td>
                <td class="content">
                    <html:errors property="customContact"/>
                    <html:text property="customContact" styleId="customContact" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
                <td class="label">集团客户联系电话*</td>
                <td class="content">
                    <html:errors property="customContactPhone"/>
                    <html:text property="customContactPhone" styleId="customContactPhone" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">集客部联系人*</td>
                <td class="content">
                    <html:errors property="bdeptContact"/>
                    <html:text property="bdeptContact" styleId="bdeptContact" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
                <td class="label">集客部联系人联系电话*</td>
                <td class="content">
                    <html:errors property="bdeptContactPhone"/>
                    <html:text property="bdeptContactPhone" styleId="bdeptContactPhone" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">集团业务联系人*</td>
                <td class="content">
                    <html:errors property="groupDealContact"/>
                    <html:text property="groupDealContact" styleId="groupDealContact" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
                <td class="label">集团业务联系人电话*</td>
                <td class="content">
                    <html:errors property="groupDealContactPhone"/>
                    <html:text property="groupDealContactPhone" styleId="groupDealContactPhone" styleClass="text medium"
                               alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">集团技术联系人</td>
                <td class="content">
                    <html:errors property="groupTechContact"/>
                    <html:text property="groupTechContact" styleId="groupTechContact" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
                <td class="label">集团技术联系人电话</td>
                <td class="content">
                    <html:errors property="groupTechContactPhone"/>
                    <html:text property="groupTechContactPhone" styleId="groupTechContactPhone" styleClass="text medium"
                               alt="allowBlank:true"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <br/>
    <input type="submit" styleClass="submit" property="method.xsave"
           onclick="return;location.href='<c:url
                   value="/businessupport/order/ordersheets.do?method=xquery&ifReference=${ifReference}"/>'"
           value="保存"/>
    <input type="button" styleClass="button"
           onclick="location.href='<c:url
                   value="/businessupport/order/ordersheets.do?method=xquery&ifReference=${ifReference}"/>'"
           value="返回"/>
</html:form>

