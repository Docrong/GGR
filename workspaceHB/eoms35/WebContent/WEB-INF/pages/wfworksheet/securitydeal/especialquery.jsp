<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table class="formTable">
    <!-- 所属地域 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.toDeptId"/>
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
            <bean:message bundle="securitydeal" key="securitydeal.mainNetSortOne"/>
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
            <bean:message bundle="securitydeal" key="securitydeal.mainNetSortTwo"/>
        </td>
        <td>
            <input type="hidden" name="main.mainNetSortTwo"/>
            <eoms:comboBox name="mainNetSortTwoChoiceExpression" id="mainNetSortTwo" sub="mainNetSortThree"/>
        </td>
    </tr>
    <!-- 网络三级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="securitydeal" key="securitydeal.mainNetSortThree"/>
        </td>
        <td>
            <input type="hidden" name="main.mainNetSortThree"/>
            <eoms:comboBox name="mainNetSortThreeChoiceExpression" id="mainNetSortThree"/>
        </td>
    </tr>
</table>