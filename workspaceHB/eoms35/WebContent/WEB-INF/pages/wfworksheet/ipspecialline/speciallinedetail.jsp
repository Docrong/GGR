<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% String deleted = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("deleted"));
    String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if (ifReference.equals(""))
        ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
%>
<script type="text/javascript">
    function modify() {
        window.location.href = './ordersheets.do?method=editSpecialLine&id=${ordersheetForm.id}';
    }

    function remove() {
        if (confirm("确认删除所选对象吗？")) {
            window.location.href = './ordersheets.do?method=xdelete&ordersheetid=${ordersheetForm.id}&ifReference=${ifReference}';
        }
    }
</script>
<title><fmt:message key="ordersheetDetail.title"/></title>
<caption>
    <div class="header center">定单详细信息</div>
</caption>
<html:form action="ordersheets.do?method=xsave" method="post" styleId="detail">
    <input type="hidden" name="ifReference" value="${ifReference}"/>
    <table class="formTable">
        <tr>
            <td class="label">定单类型</td>
            <td class="content"><eoms:id2nameDB id="${ordersheetForm.orderType}" beanId="ItawSystemDictTypeDao"/></td>
            <td class="label">紧急程度</td>
            <td class="content"><eoms:id2nameDB id="${ordersheetForm.urgentDegree}"
                                                beanId="ItawSystemDictTypeDao"/></td>
        </tr>
        <tr>
            <td class="label">定单业务类型</td>
            <td class="content"><eoms:id2nameDB id="${ordersheetForm.orderBuisnessType}"
                                                beanId="ItawSystemDictTypeDao"/></td>
            <td class="label">是否竣工</td>
            <td class="content"><eoms:id2nameDB id="${ordersheetForm.isCompleted}" beanId="ItawSystemDictTypeDao"/></td>
        </tr>
        <tr>
            <td class="label">定单编号</td>
            <td class="content">
                <html:errors property="orderNumber"/>
                <bean:write name="ordersheetForm" property="orderNumber" scope="request"/>
            </td>
            <td class="label">主产品实例编码</td>
            <td class="content">
                <html:errors property="mainProductInstanceCode"/>
                <bean:write name="ordersheetForm" property="mainProductInstanceCode" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">生成时间</td>
            <td class="content">
                <bean:write name="ordersheetForm" property="creatTime" formatKey="date.formatDateTimeAll"
                            bundle="ordersheet" scope="request"/>
            </td>
            <td class="label">竣工时间</td>
            <td class="content">
                <bean:write name="ordersheetForm" property="endTime" formatKey="date.formatDateTimeAll"
                            bundle="ordersheet" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">完成期限</td>
            <td class="content">
                <bean:write name="ordersheetForm" property="completeLimit" formatKey="date.formatDateTimeAll"
                            bundle="ordersheet" scope="request"/>
            </td>
            <td class="label">变更时间</td>
            <td class="content">
                <bean:write name="ordersheetForm" property="changeTime" formatKey="date.formatDateTimeAll"
                            bundle="ordersheet" scope="request"/>
            </td>

        <tr>
            <td class="label">撤销时间</td>
            <td class="content" colspan="3">
                <bean:write name="ordersheetForm" property="cancelTime" formatKey="date.formatDateTimeAll"
                            bundle="ordersheet" scope="request"/>
            </td>
        </tr>
    </table>
    <br>
    <table class="formTable">
        <tr>
            <td class="label" colspan="4">
                <div class="header center">客户相关信息</div>
            </td>
        </tr>
        <tr>
            <td class="label">集团编号</td>
            <td class="content">
                <html:errors property="customNo"/>
                <bean:write name="ordersheetForm" property="customNo" scope="request"/>
            </td>
            <td class="label">集团名称</td>
            <td class="content">
                <html:errors property="customName"/>
                <bean:write name="ordersheetForm" property="customName" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团级别</td>
            <td class="content"><eoms:id2nameDB id="${ordersheetForm.customLevel}" beanId="ItawSystemDictTypeDao"/></td>
            <td class="label">所属省份</td>
            <td class="content">
                <html:errors property="provinceName"/>
                <bean:write name="ordersheetForm" property="provinceName" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">所属地市</td>
            <td class="content">
                <html:errors property="cityName"/>
                <bean:write name="ordersheetForm" property="cityName" scope="request"/>
            </td>
            <td class="label">所属区县</td>
            <td class="content">
                <html:errors property="countyName"/>
                <bean:write name="ordersheetForm" property="countyName" scope="request"/>
            </td>
        </tr>
    </table>
    <br>
    <table class="formTable">
        <tr>
            <td class="label" colspan="4">
                <div class="header center">申请业务信息</div>
            </td>
        </tr>
        <tr>
            <td class="label">产品名称</td>
            <td class="content">
                <html:errors property="productName"/>
                <bean:write name="ordersheetForm" property="productName" scope="request"/>
            </td>
            <td class="label">产品类别</td>
            <td class="content"><eoms:id2nameDB id="${ordersheetForm.productKind}" beanId="ItawSystemDictTypeDao"/></td>
        </tr>
        <tr>
            <td class="label">产品编号</td>
            <td class="content">
                <html:errors property="productID"/>
                <bean:write name="ordersheetForm" property="productID" scope="request"/>
            </td>
            <td class="label">产品序列号</td>
            <td class="content">
                <html:errors property="productSN"/>
                <bean:write name="ordersheetForm" property="productSN" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">电路类别</td>
            <td class="content"><eoms:id2nameDB id="${ordersheetForm.isNonLocal}" beanId="ItawSystemDictTypeDao"/></td>
            <td class="label">传输承载的业务</td>
            <td class="content"><eoms:id2nameDB id="${ordersheetForm.transfBusiness}"
                                                beanId="ItawSystemDictTypeDao"/></td>
        </tr>
        <tr>
            <td class="label">业务需求描述*</td>
            <td class="content" colspan="3">
                <html:errors property="brequirementDesc"/>
                <bean:write name="ordersheetForm" property="brequirementDesc" scope="request"/>
            </td>
        </tr>
    </table>
    <br>
    <table class="formTable">
        <tr>
            <td class="label" colspan="4">
                <div class="header center">相关联系人信息</div>
            </td>
        </tr>
        <tr>
            <td class="label">客户经理</td>
            <td class="content">
                <html:errors property="cmanagerPhone"/>
                <bean:write name="ordersheetForm" property="cmanagerPhone" scope="request"/>
            </td>
            <td class="label">客户经理联系电话</td>
            <td class="content">
                <html:errors property="cmanagerContactPhone"/>
                <bean:write name="ordersheetForm" property="cmanagerContactPhone" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团客户联系人</td>
            <td class="content">
                <html:errors property="customContact"/>
                <bean:write name="ordersheetForm" property="customContact" scope="request"/>
            </td>
            <td class="label">集团客户联系电话</td>
            <td class="content">
                <html:errors property="customContactPhone"/>
                <bean:write name="ordersheetForm" property="customContactPhone" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团业务联系人</td>
            <td class="content">
                <html:errors property="groupDealContact"/>
                <bean:write name="ordersheetForm" property="groupDealContact" scope="request"/>
            </td>
            <td class="label">集团业务联系人电话</td>
            <td class="content">
                <html:errors property="groupDealContactPhone"/>
                <bean:write name="ordersheetForm" property="groupDealContactPhone" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">集团技术联系人</td>
            <td class="content">
                <html:errors property="groupDealContact"/>
                <bean:write name="ordersheetForm" property="groupDealContact" scope="request"/>
            </td>
            <td class="label">集团技术联系人电话</td>
            <td class="content">
                <html:errors property="groupDealContactPhone"/>
                <bean:write name="ordersheetForm" property="groupDealContactPhone" scope="request"/>
            </td>
        </tr>
    </table>

    <iframe id="frame"
            src="${app}/businessupport/order/ordersheets.do?method=getSpecialLines&id=${ordersheetForm.id}&specialtyType=${ordersheetForm.specialtyType}"
            width="100%" height="300px"></iframe>
    <table>
        <tr>
            <td>
                <input type="button" value="修改" onclick="modify();">
                <input type="button" value="删除" onclick="remove();"/>
                <logic:empty name="type">
                <input type="button" style="margin-right: 5px"
                       onclick="location.href='<c:url
                               value="/businessupport/order/ordersheets.do?method=xquery&ifReference=${ifReference}"/>'"
                       value="返回"/>
                </logic:empty>
        </tr>
    </table>
</html:form>

<!-- footer_eoms.jsp end-->