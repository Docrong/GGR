<!-- base info -->
<input type="hidden" name="${sheetPageName}linkId" id="${sheetPageName}linkId" value="${sheetLink.id}"/>
<input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetLink.id}"/>
<input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
<input type="hidden" name="${sheetPageName}piid" value="${sheetLink.piid}"/>
<input type="hidden" name="${sheetPageName}aiid" value="${sheetLink.aiid}"/>
<input type="hidden" name="${sheetPageName}activeTemplateId" value="${taskName}"/>
<input type="hidden" name="${sheetPageName}mainId" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
<input type="hidden" name="${sheetPageName}TKID" value="${sheetLink.tkid}"/>
<input type="hidden" name="${sheetPageName}preLinkId" value="${preLink.id}"/>
<input type="hidden" name="${sheetPageName}taskCompleteLimit" id="${sheetPageName}taskCompleteLimit"
       value="${eoms:date2String(preLink.nodeAcceptLimit)}"/>
<input type="hidden" name="${sheetPageName}taskAcceptLimit" id="${sheetPageName}taskAcceptLimit"
       value="${eoms:date2String(preLink.nodeCompleteLimit)}"/>
<input type="hidden" name="${sheetPageName}taskName" value="${taskName}"/>
<input type="hidden" name="${sheetPageName}taskStatus" value="${taskStatus}"/>
<input type="hidden" name="sheetPageName" id="sheetPageName" value="${sheetPageName}"/>
<input type="hidden" name="methodBeanId" id="methodBeanId" value="${methodBeanId}"/>
<br/>
<table class="listTable">
    <caption><bean:message bundle="sheet" key="linkForm.header"/></caption>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operateUserName"/>*
            <input type="hidden" name="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
        </td>
        <td class="content">
            <eoms:id2nameDB id="${sheetLink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operateDeptName"/>*
            <input type="hidden" name="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
        </td>
        <td class="content">
            <eoms:id2nameDB id="${sheetLink.operateDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
        </td>
    </tr>


    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.operaterContact"/> *
        </td>
        <td class="content" colspan="3">
            <input type="text" class="text" name="${sheetPageName}operaterContact"
                   id="${sheetPageName}operaterContact" value="${sheetLink.operaterContact}" alt="allowBlank:false"/>
        </td>

    </tr>


</table>
