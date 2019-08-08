<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
%>
<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/localCommonTask.do?method=newPerformNonFlow" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/localCommonTask/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
               value="LocalCommonTaskMainFlowProcess"/>
        <input type="hidden" name="${sheetPageName}beanId" id="${sheetPageName}beanId"
               value="iLocalCommonTaskMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.localCommonTask.model.LocalCommonTaskMain"/>
        <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.localCommonTask.model.LocalCommonTaskLink"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <table class="formTable">
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.remark"/>*
                </td>
                <td colspan="3">
                    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
                           value="-15"/>
                    <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500,maxLength:2000,vtext:'请填入备注，最多输入2000个字符'">${sheetLink.remark}</textarea>
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
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/localCommonTask/localCommonTask.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>


        </div>
    </html:form>

</div>

