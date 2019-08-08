<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/businesspilot.do?method=performTransferWorkItem" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/businesspilot/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="${sheetPageName}beanId" id="${sheetPageName}beanId"
               value="iBusinessPilotMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.businesspilot.model.BusinessPilotMain"/> <!--main表Model对象类名-->
        <input type="hidden" name="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink"/> <!--link表Model对象类名-->
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>

        <table class="listTable">

            <td class="label">
                <bean:message bundle="sheet" key="linkForm.transmitReason"/>
            </td>
            <td colspan="3">
		           <textarea name="${sheetPageName}transferReason" class="textarea max"
                             id="${sheetPageName}transferReason"
                             alt="allowBlank:false,maxLength:1000,width:500,vtext:'${eoms:a2u('请填入信息，最多输入1000字')}'"
                             alt="width:'500px'"></textarea>
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
            <html:submit styleClass="btn" property="method.save2" styleId="method.save2">
                <bean:message bundle="sheet" key="button.done"/>
            </html:submit>
        </div>
    </html:form>

</div>

