<table class="formTable">
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.sheetId"/>
        </td>
        <td width="100%">
            <input type="hidden" name="sheetIdStringExpression" value="like"/>
            <input type="text" name="main.sheetId" id="sheetId" size="30" class="text"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.title"/>
        </td>
        <td>
            <input type="hidden" name="titleStringExpression" value="like"/>
            <input type="text" name="main.title" id="title" size="30" class="text"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.status"/>
        </td>
        <td>
            <input type="hidden" name="main.status"/>
            <select name="statusChoiceExpression">
                <option value=""></option>
                <option value="0"><bean:message bundle="sheet" key="query.status.running"/></option>
                <option value="1"><bean:message bundle="sheet" key="query.status.hold"/></option>
                <option value="-1"><bean:message bundle="sheet" key="query.status.cancel"/></option>
                <option value="-14">${eoms:a2u('强制作废')}</option>
                <option value="-13"><bean:message bundle="sheet" key="query.status.forceCancel"/></option>
                <option value="-12">${eoms:a2u('作废')}</option>
            </select>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.sendTime"/>
        </td>
        <td>
            <input type="hidden" name="main.sendTime"/>
            <bean:message bundle="sheet" key="worksheet.query.startDate"/>
            <input type="hidden" id="sendTimeStartDateExpression" name="sendTimeStartDateExpression" value=">="/>
            <input type="text" name="sendTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)"
                   readonly="true" class="text"/> &nbsp;&nbsp;
            <input type="hidden" name="sendTimeLogicExpression" value="and"/>
            <bean:message bundle="sheet" key="worksheet.query.endDate"/>
            <input type="hidden" id="sendTimeEndDateExpression" name="sendTimeEndDateExpression" value="<=">
            <input type="text" name="sendTimeEndDate" id="sendTimeEndDate"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="" readonly="true"
                   class="text"/></div>
        </td>
    </tr>
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