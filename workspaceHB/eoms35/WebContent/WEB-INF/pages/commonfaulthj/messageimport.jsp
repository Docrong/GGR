<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script language="javascript">
    function onsubmitCheck(value1) {
        //alert("111="+value1);
        var ss = document.getElementById("theFile").value;
        if (ss == '') {
            alert("请导入excel");
            return false;
        }
        var form = document.getElementById("tawimport");
        form.action = "${app }/commonfaulthj/excelimportnet.do?method=importSave&flag=" + value1;
        form.submit();
        return true;
    }
</script>
<form name="tawimport" id="tawimport" method="post" enctype="multipart/form-data"
      action="${app }/commonfaulthj/excelimportnet.do?method=importSave" onsubmit="return onsubmitCheck();">
    <table class="formTable">
        <tr>
            <td class="label">
                模板导入
            </td>
            <td width="400">
                <input type="file" name="theFile" id="theFile" class="file">
                <a href="${app}/accessories/commonfaulthj/netararmsubrole.xls">模板下载</a>
            </td>
        </tr>
    </table>
    <br>
    <!--  <input type="submit" value="导入" name="B1"  class="submit">
   <input type="button" value="返回" onclick="javascript:reback();" class="button"> -->

    <input type="button" value="保存&修改" onclick="javascript:onsubmitCheck('1');" class="button"/>
    <input type="button" value="删除" onclick="javascript:onsubmitCheck('3');" class="button"/>
</form>
<script type="text/javascript">
    function reback() {
        var url = '${app}/commonfaulthj/commonfaulthjs.do?method=search';
        window.location.href = url;
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>