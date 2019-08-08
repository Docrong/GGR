<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String startDate = com.boco.eoms.base.util.StaticMethod.getLocalString(-31);
    String endDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();
%>
<table class="formTable">
    <!-- 派单时间 -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.sendTime"/>
        </td>
        <td width="100%">
            <input type="hidden" name="main.sendTime"/>
            <bean:message bundle="sheet" key="worksheet.query.startDate"/>
            <input type="hidden" id="sendTimeStartDateExpression" name="sendTimeStartDateExpression" value=">="/>
            <input type="text" name="sendTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)"
                   readonly="true" class="text" value="<%=startDate %>"/> &nbsp;&nbsp;
            <input type="hidden" name="sendTimeLogicExpression" value="and"/>
            <bean:message bundle="sheet" key="worksheet.query.endDate"/>
            <input type="hidden" id="sendTimeEndDateExpression" name="sendTimeEndDateExpression" value="<=">
            <input type="text" name="sendTimeEndDate" id="sendTimeEndDate"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="" value="<%=endDate %>"
                   readonly="true" class="text"/></div>
        </td>
    </tr>
    <!-- 查询类型  -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="worksheet.query.type"/>
        </td>
        <td class="content">
            <select name="queryType">
                <option value="record"><bean:message bundle="sheet" key="worksheet.query.record"/></option>
                <option value="number"><bean:message bundle="sheet" key="worksheet.query.number"/></option>
            </select>
        </td>
    </tr>
</table>
<script type="text/javascript">
    function checktime() {
        var sendTimeStartDate = document.getElementById("sendTimeStartDate").value;
        var sendTimeEndDate = document.getElementById("sendTimeEndDate").value;
        var beginTimes = sendTimeStartDate.substring(0, 10).split('-');
        var endTimes = sendTimeEndDate.substring(0, 10).split('-');
        sendTimeStartDate = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0] + ' ' + sendTimeStartDate.substring(10, 19);
        sendTimeEndDate = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + sendTimeEndDate.substring(10, 19);
        var a = (Date.parse(sendTimeEndDate) - Date.parse(sendTimeStartDate)) / 24 / 3600 / 1000;
        if (a > 31) {
            alert('选择时间间隔不能超过一个月!');
            return false;
        } else {
            return true;
        }
    }
</script>