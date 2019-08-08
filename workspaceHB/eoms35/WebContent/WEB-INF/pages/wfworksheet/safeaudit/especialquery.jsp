<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table class="formTable">
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
            <bean:message bundle="safeaudit" key="safeaudit.mainSafeAuditType1"/>
        </td>
        <td>
            <input type="hidden" name="main.mainSafeAuditType1"/>
            <eoms:comboBox name="mainSafeAuditType1ChoiceExpression" id="mainSafeAuditType1" sub="mainSafeAuditType2"
                           initDicId="1010104"/>
        </td>
    </tr>
    <!-- 网络二级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="safeaudit" key="safeaudit.mainSafeAuditType2"/>
        </td>
        <td>
            <input type="hidden" name="main.mainSafeAuditType2"/>
            <eoms:comboBox name="mainSafeAuditType2ChoiceExpression" id="mainSafeAuditType2" sub="mainSafeAuditType3"/>
        </td>
    </tr>
    <!-- 网络三级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="safeaudit" key="safeaudit.mainSafeAuditType3"/>
        </td>
        <td>
            <input type="hidden" name="main.mainSafeAuditType3"/>
            <eoms:comboBox name="mainSafeAuditType3ChoiceExpression" id="mainSafeAuditType3"/>
        </td>
    </tr>
</table>