<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/commonfault.do?method=performTransferWorkItem" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/groupcomplaint/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="${sheetPageName}beanId" id="${sheetPageName}beanId"
               value="iGroupComplaintMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain"/>
        <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>

        <table class="formTable">
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
                请选择派发对象
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test" type="role" roleId="${bigRoleId}" flowId="51"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',childType:'user,subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"
                />
            </div>
        </fieldset>


        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>">
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=performSaveInfo'"
                   value="<bean:message bundle='sheet' key='button.save'/>"/>
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=performSaveInfoAndExit'"
                   value="<bean:message bundle='sheet' key='button.saveback'/>"/>
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>


        </div>
    </html:form>

</div>

