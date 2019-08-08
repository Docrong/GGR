<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        var tabs = new Ext.TabPanel('main');
        tabs.addTab('sheetform', "工单质检");
        tabs.activate('sheetform');
    });

    function checkData() {
        var qcRemark = $('qcRemark').value;
        //alert(jmz.GetLength(qcRemark));
        if (qcRemark == '') {
            alert("请填写质检评语！");
            return false;
        } else if (jmz.GetLength(qcRemark) > 2000) {
            alert("质检评语输入的信息过长");
            return false;
        }
        return true;
    }

    var jmz = {};
    jmz.GetLength = function (str) {
        ///<summary>获得字符串实际长度，中文2，英文1</summary>
        ///<param name="str">要获得长度的字符串</param>
        var realLength = 0, len = str.length, charCode = -1;
        for (var i = 0; i < len; i++) {
            charCode = str.charCodeAt(i);
            if (charCode >= 0 && charCode <= 128) realLength += 1;
            else realLength += 2;
        }
        return realLength;
    };
</script>

<div id="sheetform" class="tabContent">
    <html:form action="/${module}.do?method=saveQCRemark" styleId="theform">
        <table class="formTable">
            <tr>
                <td class="label">质检评语*</td>
                <td colspan="3">
                    <textarea class="textarea max" name="qcRemark" id="qcRemark"></textarea>
                </td>
            </tr>
        </table>
        <br>
        <input type="hidden" name="sheetKey" id="sheetKey" value="${sheetKey }"/>
        <input type="submit" class="btn" value="确定" onclick="return checkData()"/>&nbsp;&nbsp;
        <input type="reset" class="btn" value="重置"/>
    </html:form>
</div>

<%@ include file="/common/footer_eoms.jsp" %>
