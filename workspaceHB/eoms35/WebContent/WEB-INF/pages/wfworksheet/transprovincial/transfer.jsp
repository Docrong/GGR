<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/transprovincial.do?method=performTransferWorkItem" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/transprovincial/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="beanId" id="beanId" value="iTransprovincialMainManager"/>
        <input type="hidden" name="mainClassName" id="mainClassName"
               value="com.boco.eoms.sheet.transprovincial.model.TransprovincialMain"/>
        <input type="hidden" name="linkClassName" id="linkClassName"
               value="com.boco.eoms.sheet.transprovincial.model.TransprovincialLink"/>
        <input type="hidden" name="operateType" id="operateType" value="8"/>
        <table class="listTable">
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
                </td>
                <td class="content">
                    <input class="text" onclick="popUpCalendar(this, this)" type="text"
                           name="nodeAcceptLimit" readonly="readonly"
                           value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                           id="nodeAcceptLimit"/>
                </td>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.completeLimit"/>
                </td>
                <td class="content">
                    <input class="text" onclick="popUpCalendar(this, this)" type="text" name="nodeCompleteLimit"
                           readonly="readonly" value="${eoms:date2String(sheetLink.nodeCompleteLimit)}"
                           id="nodeCompleteLimit"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                </td>
                <td colspan="3">
                    <textarea class="textarea max" name="transferReason" id="transferReason"
                              alt="width:'500px'">${sheetLink.transferReason}</textarea>
                </td>
            </tr>
        </table>
        <fieldset>
            <legend>
                请选择派发对象
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test" config="{{returnJSON:true}"
                              category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"
                              panels="[
                   {text:'可选人员',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                   {text:'备选子角色',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                 ]"
                />
            </div>
        </fieldset>
        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>">
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/transprovincial/transprovincial.do?method=performSaveInfo'"
                   value="<bean:message bundle='sheet' key='button.save'/>"/>
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/transprovincial/transprovincial.do?method=performSaveInfoAndExit'"
                   value="<bean:message bundle='sheet' key='button.saveback'/>"/>
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/transprovincial/transprovincial.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>
        </div>
    </html:form>
</div>