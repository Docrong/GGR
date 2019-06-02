<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawCommonsJobconfigDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonsJobconfigDetail.heading"/></content>

<html:form action="saveTawCommonsJobconfig" method="post" styleId="tawCommonsJobconfigForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobconfigForm.ifSerialFlag"/>
        <html:errors property="ifSerialFlag"/>
        <html:text property="ifSerialFlag" styleId="ifSerialFlag" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobconfigForm.busyEndTime"/>
        <html:errors property="busyEndTime"/>
        <html:text property="busyEndTime" styleId="busyEndTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobconfigForm.busyStartTime"/>
        <html:errors property="busyStartTime"/>
        <html:text property="busyStartTime" styleId="busyStartTime" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonsJobconfig')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonsJobconfigForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>