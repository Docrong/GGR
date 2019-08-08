<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript" src="../../sheetfllowline/css/functions.js"></script>


<form name="addform" method="post" action="../sheetflowline/autoDealsop.do?method=queryList">


    <table width="500" class="formTable">
        <caption>自动处理信息</caption>
        <tr>
            <td class="label">
                网管告警ID
            </td>
            <td>
                <input type="text" name="alarmId" id="alarmId" class="text"/>
            </td>
            <td class="label">
                告警主题
            </td>
            <td>
                <input type="text" name="alarmTitle" id="alarmTitle" class="text"/>
            </td>
        </tr>
        <tr>

            <td class="label">
                自动处理环节

            </td>
            <td width="400">
                <select id="autoDealTask" name="autoDealTask" class="select" onchange="dealmodel(this.value);">
                    <option value="">请选择</option>
                    <option value="T1">T1</option>
                    <option value="T2">T2</option>
                </select>
            </td>
            <td class="label">
                自动处理方式
            </td>
            <td>
                <div id="tranfer">
                    <select id="autoDealMode" name="autoDealMode" class="select">
                        <option value="">请选择</option>
                        <option value="1">移交下一级</option>
                        <option value="2">回复并归档</option>
                    </select>
                </div>
                <div id="notranfer" style="display: none">
                    <select id="autoDealMode" name="autoDealMode" class="select">
                        <option value="">请选择</option>
                        <option value="2">回复并归档</option>
                    </select>
                </div>
            </td>
        </tr>

    </table>
    <br>
    <input type="submit" value="查询" name="B1" Class="submit">


</form>

<%@ include file="/common/footer_eoms.jsp" %>
<script type="text/javascript">
    function dealmodel(input) {
        var v1 = document.getElementById("tranfer");
        var v2 = document.getElementById("notranfer");
        if (input == 'T1') {//显示
            v1.style.display = 'block';
            v2.style.display = 'none';
        } else {//不显示
            v1.style.display = 'none';
            v2.style.display = 'block';
        }
    }
</script>    	
