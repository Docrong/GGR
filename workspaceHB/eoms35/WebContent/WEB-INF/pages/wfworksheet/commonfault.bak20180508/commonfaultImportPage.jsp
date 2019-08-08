<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<form method="post" id="theform" action="commonfaultauto.do?method=commonFaultAutoImport" enctype="multipart/form-data">
    <table class="formTable">
        <caption>规则配置导入</caption>

        <input type="hidden" name="sheetType" value="${sheetType}"/>
        <input type="hidden" name="colseSwitch" value="${colseSwitch}"/>

        <tr>
            <td class="label">导入EXCEL</td>
            <td width="100%">
                <input type="file" name="upfile" size="40" style="height: 20px;">
                <a href="${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=8aa082a81ca40782011ca66b14aa117a">模板文件下载</a>
            </td>
        </tr>

        <tr>
            <td colspan="2">

                <input type="submit" value="提交" class="btn">
            </td>


    </table>

</form>
