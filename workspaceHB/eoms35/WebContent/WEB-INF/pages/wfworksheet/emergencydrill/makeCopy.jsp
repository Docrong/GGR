<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/emergencydrill.do?method=newPerformNonFlow" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/emergencydrill/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
               value="EmergencyDrillProcess"/>
        <input type="hidden" name="${sheetPageName}beanId" id="${sheetPageName}beanId"
               value="iEmergencyDrillMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillMain"/>
        <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillLink"/>

        <table class="formTable">
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.remark"/>*
                </td>
                <td colspan="3">
                    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
                           value="-15"/>
                    <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500,maxLength:255,vtext:'请填入备注，最多输入125个字符'">${sheetLink.remark}</textarea>
                </td>
            </tr>

        </table>
        <fieldset>
            <legend>
                请选择抄送对象
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                              category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
            </div>
        </fieldset>


        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>">
            <!-- <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/circuitdispatch/circuitdispatch.do?method=performSaveInfo'" value="<bean:message bundle='sheet' key='button.save'/>" />
	    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/circuitdispatch/circuitdispatch.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" /> -->
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/emergencydrill/emergencydrill.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>


        </div>
    </html:form>

</div>

