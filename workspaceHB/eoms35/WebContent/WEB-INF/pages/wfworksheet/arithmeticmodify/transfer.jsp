<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/arithmeticmodify.do?method=performTransferWorkItem" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/arithmeticmodify/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="${sheetPageName}beanId" id="${sheetPageName}beanId"
               value="iArithmeticModifyMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain"/>
        <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyLink"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="${operateType}"/>
        <table class="formTable">
            <tr>
                <td class="label">受理时限</td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                           id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                           onclick="popUpCalendar(this, this)"
                           alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:true"/>

                </td>
                <td class="label">处理时限</td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                           id="${sheetPageName}sheetCompleteLimit"
                           value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                           onclick="popUpCalendar(this, this)"
                           alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:true"/>

                </td>
            </tr>


            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                </td>
                <td colspan="3">
		           <textarea name="${sheetPageName}transferReason" class="textarea max"
                             id="${sheetPageName}transferReason"
                             alt="allowBlank:true,maxLength:255,width:500,vtext:'请填入说明，最多输入125字'"
                             alt="width:'500px'"></textarea>
                </td>
            </tr>

        </table>
        <fieldset>
            <legend>
                请选择派发对象
            </legend>
            <eoms:chooser id="test" config="{{returnJSON:true}"
                          category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"
                          panels="[
                                     {text:'可选人员',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                                     {text:'备选子角色',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                                 ]"
            />
        </fieldset>
        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>">
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/arithmeticmodify/arithmeticmodify.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>
        </div>
    </html:form>

</div>

