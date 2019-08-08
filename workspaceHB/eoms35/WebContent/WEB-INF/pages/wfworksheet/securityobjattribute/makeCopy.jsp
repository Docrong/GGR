<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
%>
<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/securityobjattribute.do?method=newPerformNonFlow" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/securityobjattribute/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="processTemplateName" id="processTemplateName" value="SecurityObjAttribute"/>
        <input type="hidden" name="beanId" id="beanId" value="iSecurityObjAttributeMainManager"/>
        <input type="hidden" name="mainClassName" id="mainClassName"
               value="com.boco.eoms.sheet.securityobjattribute.model.SecurityObjAttributeMain"/>
        <input type="hidden" name="linkClassName" id="linkClassName"
               value="com.boco.eoms.sheet.securityobjattribute.model.SecurityObjAttributeLink"/>
        <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
        <table class="formTable">
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.remark"/>*
                </td>
                <td colspan="3">
                    <input type="hidden" name="operateType" id="operateType" value="-15"/>
                    <textarea name="remark" class="textarea max" id="remark"
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
                   onclick="v.passing=true;this.form.action='${app}/sheet/SecurityObjAttribute/SecurityObjAttribute.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>


        </div>
    </html:form>
</div>