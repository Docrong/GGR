<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.securitydeal.model.securitydealLink securitydealLink = (com.boco.eoms.sheet.securitydeal.model.securitydealLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(SecurityDealLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(SecurityDealLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/securitydeal/securitydeal.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
        form.submit();
    }

    function removeTemplate() {
        if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
            var thisform = document.forms[0];
            thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
            thisform.submit();
        }
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/securitydeal/baseinputlinkhtmlnew.jsp" %>
<html:form action="/securitydeal.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="isecuritydealMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.securitydeal.model.securitydealMain"/> <!--main\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.securitydeal.model.securitydealLink"/> <!--link\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
    <table class="listTable">
        <tr>
            <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkSecurityInproveAdvice"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkSecurityInproveAdvice"
                          id="${sheetPageName}linkSecurityInproveAdvice">${sheetLink.linkSecurityInproveAdvice}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkSecurityInproveProgram"/></td>
            <td colspan='3'>
                <eoms:attachment idList="" idField="${sheetPageName}linkSecurityInproveProgram"
                                 appCode="securitydealsheet"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkAuditViews"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkAuditViews"
                          id="${sheetPageName}linkAuditViews">${sheetLink.linkAuditViews}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkAuditResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkAuditResult"
                          id="${sheetPageName}linkAuditResult">${sheetLink.linkAuditResult}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkPerformResult"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkPerformResult"
                          id="${sheetPageName}linkPerformResult">${sheetLink.linkPerformResult}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkIfStartChangeProcess"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkIfStartChangeProcess"
                          id="${sheetPageName}linkIfStartChangeProcess">${sheetLink.linkIfStartChangeProcess}</textarea>
            </td>
        </tr>
    </table>
    </table>
</html:form>
<logic:present name="type">
    <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
        <html:button styleClass="btn" property="method.save" styleId="method.save"
                     onclick="saveDealTemplate('current')">
            <bean:message bundle="sheet" key="button.saveCurTemplate"/>
        </html:button>
    </c:if>
    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
        <bean:message bundle="sheet" key="button.delete"/>
    </html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
    <bean:message bundle="sheet" key="button.back"/>
</html:button>
