<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@page import="com.boco.eoms.commons.accessories.webapp.form.TawCommonsAccessoriesConfigForm" %>
<%
    TawCommonsAccessoriesConfigForm form =
            (TawCommonsAccessoriesConfigForm) request.getAttribute("tawCommonsAccessoriesConfigForm");
    String appCode = form.getAppCode();
%>
<title>
    <bean:message key="tawCommonsAccessoriesConfigDetail.title"/>
</title>
<content tag="heading">
    <bean:message key="tawCommonsAccessoriesConfigDetail.add"/></content>
<script type="text/javascript">
    function confirms() {
        var frm = document.forms[0];
        var appCode = frm.appCode.value;
        var appId = frm.appId.value;
        var allowFileType = frm.allowFileType.value;
        var path = frm.path.value;
        if (appCode == "") {
            alert("<bean:message key="tawCommonsAccessoriesConfigForm.appCode.warn"/>");
            return false;
        }
        if (appId == "") {
            alert("<bean:message key="tawCommonsAccessoriesConfigForm.appName.warn"/>");
            return false;
        }
        if (allowFileType == "") {
            alert("<bean:message key="tawCommonsAccessoriesConfigForm.allowFileType.warn"/>");
            return false;
        }
        var patrn = /^(\/[a-zA-Z]{2,})+$/;
        if (!patrn.exec(path)) {
            alert("<bean:message key="tawCommonsAccessoriesConfigForm.path.warn"/>");
            return false;
        }
        return ture;
    }
</script>
<html:form action="saveTawCommonsAccessoriesConfig" method="post" styleId="tawCommonsAccessoriesConfigForm">
    <ul>

        <html:hidden property="id"/>
        <li>
            <eoms:label styleClass="desc" key="tawCommonsAccessoriesConfigForm.appName"/>
            <html:errors property="appId"/>
            <%if (appCode != null) { %>
            <html:text property="appName" styleId="appName" styleClass="text medium" readonly="true"/>
            <html:hidden property="appId"/>
            <%} else { %>
            <html:select property="appId">
                <html:option value=""><bean:message key="tawCommonsAccessoriesConfigForm.select"/></html:option>
                <html:options collection="applicationList" property="value" labelProperty="label"/>
            </html:select>
            <%}%>
        </li>
        <li>
            <eoms:label styleClass="desc" key="tawCommonsAccessoriesConfigForm.appCode"/>
            <html:errors property="appCode"/>
            <html:text property="appCode" styleId="appCode" styleClass="text medium"/>

        </li>
        <li>
            <eoms:label styleClass="desc" key="tawCommonsAccessoriesConfigForm.maxSize"/>
            <html:errors property="maxSize"/>
            <html:text property="maxSize" styleId="maxSize" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="tawCommonsAccessoriesConfigForm.path"/>
            <html:errors property="path"/>
            <html:text property="path" styleId="path" styleClass="text medium"/>
        </li>

        <li>
            <eoms:label styleClass="desc" key="tawCommonsAccessoriesConfigForm.allowFileType"/>
            <html:errors property="allowFileType"/>
            <html:select property="allowFileType" multiple="true">
                <html:option value="xls"><bean:message
                        key="tawCommonsAccessoriesConfigForm.allowFileType.xls"/></html:option>
                <html:option value="txt"><bean:message
                        key="tawCommonsAccessoriesConfigForm.allowFileType.txt"/></html:option>
                <html:option value="doc"><bean:message
                        key="tawCommonsAccessoriesConfigForm.allowFileType.doc"/></html:option>
                <html:option value="jpg"><bean:message
                        key="tawCommonsAccessoriesConfigForm.allowFileType.jpg"/></html:option>
                <html:option value="gif"><bean:message
                        key="tawCommonsAccessoriesConfigForm.allowFileType.gif"/></html:option>
                <html:option value="zip"><bean:message
                        key="tawCommonsAccessoriesConfigForm.allowFileType.zip"/></html:option>
                <html:option value="rar"><bean:message
                        key="tawCommonsAccessoriesConfigForm.allowFileType.rar"/></html:option>
            </html:select>
        </li>
        <li class="buttonBar bottom">
            <html:submit styleClass="button" property="method.save" onclick="bCancel=false;return confirms()">
                <fmt:message key="button.save"/>
            </html:submit>

            <html:submit styleClass="button" property="method.delete"
                         onclick="bCancel=true; return confirmDelete('TawCommonsAccessoriesConfig')">
                <fmt:message key="button.delete"/>
            </html:submit>

            <html:cancel styleClass="button" onclick="bCancel=true">
                <fmt:message key="button.cancel"/>
            </html:cancel>
        </li>
    </ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonsAccessoriesConfigForm"));
</script>

<%@ include file="/common/footer_eoms.jsp" %>