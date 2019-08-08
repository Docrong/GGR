<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script language="javascript">
    function onsubmitCheck() {
        var ss = document.getElementById("theFile").value;
        if (ss == '') {
            alert("请导入excel");
            return false;
        }
        return true;
    }
</script>
<form name="tawimport" method="post" enctype="multipart/form-data"
      action="../sheetflowline/preAllocated.do?method=preAllocateImportSave" onsubmit="return onsubmitCheck();">

    <table class="formTable">
        <tr>
            <td class="label">
                模板导入
            </td>
            <td width="400">
                <input type="file" name="theFile" id="theFile" class="file"> <a
                    href="${app}/accessories/muban.xls">模板下载</a>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="导入" name="B1" class="submit">
    <input type="button" value="返回" onclick="javascript:window.history.back();" class="button">


</form>
<%@ include file="/common/footer_eoms.jsp" %>