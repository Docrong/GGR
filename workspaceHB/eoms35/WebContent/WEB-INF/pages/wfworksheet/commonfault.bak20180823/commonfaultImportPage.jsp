<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    function valDate() {
        var sheetAccessories = document.getElementById('sheetAccessories').value;
        var sheetType = document.getElementById('sheetType').value;
        var colseSwitch = document.getElementById('colseSwitch').value;
        var ruleType = document.getElementById('ruleType').value;
        Ext.Ajax.request({
            method: "post",
            url: "commonfaultauto.do?method=commonFaultAutoValDate&sheetAccessories=" + sheetAccessories + "&sheetType=" + sheetType
                + "&colseSwitch=" + colseSwitch + "&ruleType=" + ruleType,
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                var detail = o.result;
                if (detail != "") {
                    alert(detail);
                } else {
                    theform.submit();
                }
            }
        });
    }

</script>


<form method="post" id="theform" action="commonfaultauto.do?method=commonFaultAutoImport">
    <table class="formTable">
        <caption>规则配置导入</caption>

        <input type="hidden" name="sheetType" value="${sheetType }"/>
        <input type="hidden" name="colseSwitch" value="${colseSwitch }"/>
        <input type="hidden" name="ruleType" value="${ruleType }"/>
        <tr>
            <td class="label">
                故障自动归档模板
            </td>
            <td width="100%">
                点击 <a href="${app}/accessories/uploadfile/faultAutoHoldTaskTemplate.xls">故障自动归档模板.xls</a> 下载
            </td>
        </tr>
        <tr>
            <td class="label">导入EXCEL</td>
            <td width="100%">
                <font color="red">导入时间较长，请耐心等待!</font><br>
                <eoms:attachment name="sheetAccessories" property="sheetAccessories" scope="request"
                                 idField="sheetAccessories" appCode="commonfault"
                                 alt="allowBlank:false,vtext:'请点击上传按钮上传文件'"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="button" value="提交" class="btn" onclick="valDate()">
            </td>
        </tr>
    </table>
</form>
<%@ include file="/common/footer_eoms.jsp" %>