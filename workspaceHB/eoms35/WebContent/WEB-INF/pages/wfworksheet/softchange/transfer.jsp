<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});

    function saveDealInfo() {
        if (!v.check()) {
            return;
        }
        var form = document.forms[0];
        var ajaxForm = Ext.getDom(form);
        Ext.Ajax.request({
            form: ajaxForm,
            method: "post",
            url: "${app}/sheet/softchange/softchange.do?method=performSaveInfo",
            success: function () {
                alert("保存成功！");
            }
        });
    }


</script>

<div id="sheetform">
    <html:form action="/softchange.do?method=performTransferWorkItem" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/softchange/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="${sheetPageName}beanId" id="${sheetPageName}beanId" value="iSoftChangeMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.softchange.model.SoftChangeMain"/> <!--mainè¡¨Modelå¯¹è±¡ç±»å-->
        <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.softchange.model.SoftChangeLink"/> <!--linkè¡¨Modelå¯¹è±¡ç±»å-->
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="${operateType}"/>

        <table class="formTable">
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.transmitReason"/>*
                </td>
                <td colspan="3">
		           <textarea name="${sheetPageName}transferReason" class="textarea max"
                             id="${sheetPageName}transferReason"
                             alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'"
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
                   onclick="v.passing=true;this.form.action='${app}/sheet/softchange/softchange.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>


        </div>
    </html:form>

</div>

