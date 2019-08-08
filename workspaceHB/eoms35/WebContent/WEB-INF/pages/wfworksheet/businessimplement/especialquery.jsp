<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table class="formTable">
    <!-- 网络一级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.mainArgument"/>
        </td>
        <td>
            <input type="hidden" name="main.mainArgument"/>
            <eoms:comboBox name="mainArgumentChoiceExpression" id="mainArgument" sub="" initDicId="1012302"/>
        </td>
    </tr>
    <!-- 网络二级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.mainSpecifyType"/>
        </td>
        <td>
            <input type="hidden" name="main.mainSpecifyType"/>
            <eoms:comboBox name="mainSpecifyTypeChoiceExpression" id="mainSpecifyType" sub="" initDicId="1012301"/>
        </td>
    </tr>

</table>