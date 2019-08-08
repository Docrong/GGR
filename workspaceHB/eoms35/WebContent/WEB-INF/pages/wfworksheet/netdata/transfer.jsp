<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/netdata.do?method=performTransferWorkItem" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/netdata/baseinputlinkhtmlnew.jsp" %>

        <input type="hidden" name="${sheetPageName}beanId" value="iNetDataMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.netdata.model.NetDataMain"/>
        <input type="hidden" name="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.netdata.model.NetDataLink"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="${operateType}"/>
        <br/>
        <br/>
        <table class="formTable">
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
                </td>
                <td class="content">
                    <input class="text" onclick="popUpCalendar(this, this)" type="text"
                           name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                           readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                           alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>

                </td>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
                </td>
                <td class="content">
                    <input class="text" onclick="popUpCalendar(this, this)" type="text"
                           name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                           value="${eoms:date2String(sheetLink.nodeCompleteLimit)}"
                           id="${sheetPageName}nodeCompleteLimit"
                           alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.transmitReason"/>*
                </td>
                <td colspan="3">
                    <textarea class="textarea max" name="${sheetPageName}transferReason"
                              id="${sheetPageName}transferReason"
                              alt="allowBlank:false,maxLength:4000,vtext:'${eoms:a2u('请填入备注信息，最多输入4000字符')}'">${sheetLink.transferReason}</textarea>
                </td>
            </tr>

        </table>
        <fieldset>
            <legend>
                    ${eoms:a2u('请选择派发对象')}
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test" config="{{returnJSON:true}"
                              category="[{id:'dealPerformer',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择派发人')}'}]"
                              panels="[
                   {text:'${eoms:a2u('可选人员')}',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                   {text:'${eoms:a2u('备选子角色')}',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                 ]"
                />
            </div>
        </fieldset>


        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>">
        </div>
    </html:form>

</div>

