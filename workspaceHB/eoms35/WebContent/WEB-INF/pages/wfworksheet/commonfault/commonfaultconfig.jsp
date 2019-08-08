<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<form method="post" id="theform" action="commonfaultauto.do?method=commonFaultAutoconfig">
    <table class="formTable">
        <caption>规则配置导入</caption>

        <tr>
            <td class="label">是否自动归档</td>
            <td width="100%">
                <select name="sheetType">
                    <option value="auto">自动</option>
                    <option value="handler">手动</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">功能开/关</td>
            <td width="100%">
                <select name="colseSwitch">

                    <option value="yes">开</option>
                    <option value="no">关</option>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">

                <input type="submit" value="确定" class="btn">
            </td>


    </table>

</form>
