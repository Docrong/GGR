<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<br>
<table class="formTable">
    <tr>
        <td class="label">
            完成时间:
        </td>
        <td colspan="3">
            <input type="hidden" name="main.mainCompleteTime"/>
            <input type="hidden" id="mainCompleteTimeStartDateExpression" name="mainCompleteTimeStartDateExpression"
                   value=">="/>
            <input type="text" name="mainCompleteTimeStartDate"
                   onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text"/>
            <input type="hidden" name="mainCompleteTimeLogicExpression" value="and"/>
            到
            <input type="hidden" id="mainCompleteTimeEndDateExpression" name="mainCompleteTimeDateExpression"
                   value="<=">
            <input type="text" name="mainCompleteTimeEndDate" id="mainCompleteTimeEndDate"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="" readonly="true" class="text"/>
        </td>
    </tr>
</table>
<br>