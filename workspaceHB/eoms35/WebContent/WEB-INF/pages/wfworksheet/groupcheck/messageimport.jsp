<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script language="javascript">
    function onsubmitCheck() {
        var ss = document.getElementById("theFile").value;

        var sss = ss.substring(ss.lastIndexOf("."));
        alert("sss=" + sss);
        if (sss != '.xls') {
            alert("请导入后缀名为xls的excel");
            return false;
        }
        var wyid = document.getElementById("wyid").value;
        //alert("wyid"+wyid);
        if (ss == '') {
            alert("请导入后缀名为xls的excel");
            return false;
        }
        return true;
    }


</script>
<form name="tawimport" method="post" enctype="multipart/form-data"
      action="${app}/sheet/groupcheck/excel.do?method=importSave&wyid=${wyid }" onsubmit="return onsubmitCheck();">
    <table class="formTable">
        <tr>
            <td class="label">
                模板导入
            </td>
            <td width="400">
                <input type="file" name="theFile" id="theFile" class="file"/>
                <input type="hidden" name="wyid" id="wyid" value="${wyid} "/>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="导入" name="B1" class="submit">
</form>

<%@ include file="/common/footer_eoms.jsp" %>