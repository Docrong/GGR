<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table class="formTable">
    <tr>
        <td>${status }</td>
    </tr>
    <tr>
        <td>诊断日志</td>
    </tr>
    <tr>
        <td>
            <textarea class="textarea max" id="show" readonly="readonly" style="height: 300px"
                      alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'"></textarea>
        </td>
    </tr>
    <tr>
        <td>处理意见</td>
    </tr>
    <tr>
        <td><textarea class="textarea max" id="showinfo" readonly="readonly"
                      alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'"></textarea></td>
    </tr>
    <tr>
        <td>处理操作</td>
    </tr>
    <tr>
        <td>
            <input type="button" title="knowledge" value="诊断" onclick="search();" class="btn">
            <input type="button" title="knowledge" value="修复" onclick="searchs();" class="btn">
        </td>
    </tr>
</table>
<script type="text/javascript">
    function searchs() {
        Ext.Ajax.request({
                url: "${app}/sheet/telnetSocket/telnetSocket.do?method=send",
                method: "POST",
                params: {
                    cmd: 'recrip:rp=72',
                    time: '1000'
                },
                success: handleCall
            }
        );
    }

    function handleCall() {
        var o = eoms.JSONDecode(x.responseText);
        document.getElementById("show").value += o.data;
        document.getElementById("showinfo").value = "告警已修复！";
    }

    function search() {
        Ext.Ajax.request({
                url: "${app}/sheet/telnetSocket/telnetSocket.do?method=send",
                method: "POST",
                params: {
                    cmd: 'allip:acl=a2',
                    time: '1000'
                },
                success: handleCallBack
            }
        );
    }

    function handleCallBack(x) {
        var o = eoms.JSONDecode(x.responseText);
        document.getElementById("show").value = o.data;
        var status = o.data.indexOf("RP FAULT");
        if (status > -1) {

            Ext.Ajax.request({
                url: "${app}/sheet/telnetSocket/telnetSocket.do?method=send",
                method: "POST",
                parameters: {
                    cmd: 'remri:rp=72,pcb="GARP2-GPH B";',
                    time: '1000'
                },
                success: handleCallBacks
            });
        } else {
            document.getElementById("showinfo").value = "没有RP FAULT 告警！";
        }
    }

    function handleCallBacks(x) {
        var o = eoms.JSONDecode(x.responseText);
        document.getElementById("show").value += o.data;

    }
</script>


<%@ include file="/common/footer_eoms.jsp" %>