<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/businessbackout.do?method=performTransferWorkItem" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/businessbackout/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="${sheetPageName}beanId" id="${sheetPageName}beanId"
               value="ibusinessbackoutMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.businessbackout.model.businessbackoutMain"/> <!--mainè\u00a1¨Model\u00e5\u00af\u00b9è±\u00a1\u00e7±\u00bb\u00e5\u0090\u008d-->
        <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.businessbackout.model.businessbackoutLink"/> <!--linkè\u00a1¨Model\u00e5\u00af\u00b9è±\u00a1\u00e7±\u00bb\u00e5\u0090\u008d-->
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
        <table class="listTable">
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
                </td>
                <td class="content">
                    <input class="text" onclick="popUpCalendar(this, this)" type="text"
                           name="${sheetPageName}nodeAcceptLimit" readonly="readonly"
                           value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                           id="${sheetPageName}nodeAcceptLimit"/>
                </td>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.completeLimit"/>
                </td>
                <td class="content">
                    <!--   <input type="hidden" name="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"/> -->
                    <input class="text" onclick="popUpCalendar(this, this)" type="text"
                           name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                           value="${eoms:date2String(sheetLink.nodeCompleteLimit)}"
                           id="${sheetPageName}nodeCompleteLimit"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                </td>
                <td colspan="3">
                    <textarea class="textarea max" name="${sheetPageName}transferReason"
                              id="${sheetPageName}transferReason"
                              alt="width:'500px'">${sheetLink.transferReason}</textarea>
                </td>
            </tr>
        </table>
        <fieldset>
            <legend>
                    ${eoms:a2u('请选择派发对象')}
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test" type="role" roleId="${bigRoleId}" flowId="51"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',childType:'user,subrole',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择派发人')}'}]"
                />
            </div>
        </fieldset>
        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>">
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/businessbackout/businessbackout.do?method=performSaveInfo'"
                   value="<bean:message bundle='sheet' key='button.save'/>"/>
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/businessbackout/businessbackout.do?method=performSaveInfoAndExit'"
                   value="<bean:message bundle='sheet' key='button.saveback'/>"/>
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/businessbackout/businessbackout.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>
        </div>
    </html:form>

</div>

