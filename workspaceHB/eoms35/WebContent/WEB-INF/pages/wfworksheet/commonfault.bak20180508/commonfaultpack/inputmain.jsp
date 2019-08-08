<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<c:if test="${empty sheetMain.piid}">
    <script type="text/javascript">
        if ($("${sheetPageName}mainFaultGenerantTime").value == "") {
            var dt = new Date();
            //alert(dt);
            $("${sheetPageName}mainFaultGenerantTime").value = dt.format('Y-m-d H:i:s');
        }

    </script>
</c:if>
<base target="_self">
<table id="sheet" class="formTable">
    <tr>
        <td class="label">告警标题*</td>
        <td colspan="3"><input type="text" class="text max"
                               name="${sheetPageName}title" id="${sheetPageName}title"
                               value="${sheetMain.title}" alt="allowBlank:false"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainAlarmId"/></td>
        <td class="content"><input type="text" class="text"
                                   name="${sheetPageName}mainAlarmId" id="${sheetPageName}mainAlarmId"
                                   value="${sheetMain.mainAlarmId}" alt="allowBlank:true"/></td>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainAlarmNum"/></td>
        <td class="content"><input type="text" class="text"
                                   name="${sheetPageName}mainAlarmNum" id="${sheetPageName}mainAlarmNum"
                                   value="${sheetMain.mainAlarmNum}" alt="allowBlank:true"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainFaultGenerantTime"/>*
        </td>
        <td><input type="text" class="text"
                   name="${sheetPageName}mainFaultGenerantTime" readonly="readonly"
                   id="${sheetPageName}mainFaultGenerantTime"
                   value="${eoms:date2String(sheetMain.mainFaultGenerantTime)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   alt="allowBlank:false"/></td>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainEquipmentModel"/></td>
        <td><input type="text" class="text"
                   name="${sheetPageName}mainEquipmentModel"
                   id="${sheetPageName}mainEquipmentModel"
                   value="${sheetMain.mainEquipmentModel}" alt="allowBlank:true"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainAlarmLevel"/></td>
        <td><input type="text" class="text"
                   name="${sheetPageName}mainAlarmLevel"
                   id="${sheetPageName}mainAlarmLevel"
                   value="${sheetMain.mainAlarmLevel}" alt="allowBlank:true"/></td>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainAlarmSource"/></td>
        <td><input type="text" class="text"
                   name="${sheetPageName}mainAlarmSource"
                   id="${sheetPageName}mainAlarmSource"
                   value="${sheetMain.mainAlarmSource}" alt="allowBlank:true"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainAlarmLogicSort"/></td>
        <td><input type="text" class="text"
                   name="${sheetPageName}mainAlarmLogicSort"
                   id="${sheetPageName}mainAlarmLogicSort"
                   value="${sheetMain.mainAlarmLogicSort}" alt="allowBlank:true"/></td>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainAlarmLogicSortSub"/></td>
        <td><input type="text" class="text"
                   name="${sheetPageName}mainAlarmLogicSortSub"
                   id="${sheetPageName}mainAlarmLogicSortSub"
                   value="${sheetMain.mainAlarmLogicSortSub}" alt="allowBlank:true"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainAlarmDesc"/></td>
        <td colspan="3"><textarea name="mainAlarmDesc" id="mainAlarmDesc"
                                  class="textarea max"
                                  alt="allowBlank:ture,width:500,maxLength:1000,vtext:'请填写告警描述信息！'">${sheetMain.mainAlarmDesc}</textarea>
        </td>
    </tr>
</table>
<table id="sheet" class="formTable">
    <tr>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainNetName"/></td>
        <td><input type="text" class="text"
                   name="${sheetPageName}mainNetName" id="${sheetPageName}mainNetName"
                   value="${sheetMain.mainNetName}" alt="allowBlank:true"/></td>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainApplySheetId"/></td>
        <td colspan="3"><input type="text" class="text"
                               name="${sheetPageName}mainApplySheetId"
                               id="${sheetPageName}mainApplySheetId"
                               value="${sheetMain.mainApplySheetId}"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="commonfault"
                                        key="commonfault.mainFaultDiscoverableMode"/>*
        </td>
        <td><eoms:comboBox
                name="${sheetPageName}mainFaultDiscoverableMode"
                id="${sheetPageName}mainFaultDiscoverableMode" initDicId="1010301"
                defaultValue="${sheetMain.mainFaultDiscoverableMode}"
                alt="allowBlank:false"/></td>
    </tr>
</table>
