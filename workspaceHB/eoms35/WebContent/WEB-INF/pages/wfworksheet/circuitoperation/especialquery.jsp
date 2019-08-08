<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table class="formTable">
    <!-- 集团编号-->
    <tr>
        <td class="label">
            <bean:message bundle="circuitoperation" key="circuitoperation.mainGroupNumber"/>
        </td>
        <td>
            <input type="hidden" name="mainGroupNumberStringExpression" value="like"/>
            <input type="text" name="main.mainGroupNumber" id="title" size="30" class="text"/>
        </td>
    </tr>
    <!-- 集团名称-->
    <tr>
        <td class="label">
            <bean:message bundle="circuitoperation" key="circuitoperation.mainGroupName"/>
        </td>
        <td>
            <input type="hidden" name="mainGroupNameStringExpression" value="like"/>
            <input type="text" name="main.mainGroupName" id="title" size="30" class="text"/>
        </td>
    </tr>
</table>