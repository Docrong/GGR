<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userId = sessionform.getUserid();
%>

<table class="formTable">
    <input type="hidden" name="type" id="type" value="interface"/>
    <input type="hidden" name="interface" id="interface" value="interface"/>
    <input type="hidden" name="userName" id="userName" value="<%=userId%>"/>
    <tr>
        <td class="label">
            派单方式
        </td>
        <td width="100%">
            <input type="hidden" name="main.mainSendMode"/>
            <eoms:comboBox name="mainSendModeChoiceExpression" id="mainSendMode" initDicId="1010305"/>
        </td>
    </tr>
    <!-- 所属地域 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.area"/>
        </td>
        <td width="100%">
            <input type="hidden" name="toDeptIdStringExpression" value="in"/>
            <input type="text" class="text" readonly="readonly" name="showArea" id="showArea"
                   beanId="tawSystemAreaDao"/>
            <input type="hidden" name="main.toDeptId" id="toAreaId"/>
        </td>
    </tr>
    <!-- 网络一级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.mainNetTypeOne"/>
        </td>
        <td>
            <input type="hidden" name="main.mainNetSortOne"/>
            <eoms:comboBox name="mainNetSortOneChoiceExpression" id="mainNetSortOne" sub="mainNetSortTwo"
                           initDicId="1010104"/>
        </td>
    </tr>
    <!-- 网络二级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.mainNetTypeTwo"/>
        </td>
        <td>
            <input type="hidden" name="main.mainNetSortTwo"/>
            <eoms:comboBox name="mainNetSortTwoChoiceExpression" id="mainNetSortTwo" sub="mainNetSortThree"/>
        </td>
    </tr>
    <!-- 网络三级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.mainNetTypeThree"/>
        </td>
        <td>
            <input type="hidden" name="main.mainNetSortThree"/>
            <eoms:comboBox name="mainNetSortThreeChoiceExpression" id="mainNetSortThree"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            告警级别
        </td>
        <td width="100%">
            <input type="hidden" name="main.mainAlarmLevel"/>
            <select name="mainAlarmLevelChoiceExpression">
                <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
        </td>
    </tr>
</table>