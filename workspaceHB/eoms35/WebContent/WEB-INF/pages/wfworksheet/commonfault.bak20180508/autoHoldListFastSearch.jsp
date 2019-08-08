<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<form method="post" id="theform" action="commonfaultauto.do?method=fastSearch">
    <table class="formTable">
        <tr>
            <td class="label">网络告警ID</td>
            <td>
                <html:text property="remark1" styleId="remark1" maxlength="20" styleClass="text medium" value=""/>
            </td>
        </tr>
        <tr>
            <td class="label">措施</td>
            <td>
                <html:text property="commonFaultDesc" styleId="commonFaultDesc" maxlength="20" styleClass="text medium"
                           value=""/>
            </td>
        </tr>

        <tr>
            <td class="label">归档描述</td>
            <td>
                <html:text property="remark2" styleId="remark2" maxlength="20" styleClass="text medium" value=""/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="提交" class="btn">
            </td>
        </tr>
    </table>
</form>
<%@ include file="/common/footer_eoms.jsp" %>