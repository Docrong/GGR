<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawCommonMessageMonitorRefDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonMessageMonitorRefDetail.heading"/></content>

<html:form action="saveTawCommonMessageMonitorRef" method="post" styleId="tawCommonMessageMonitorRefForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorRefForm.monitorid"/>
        <html:errors property="monitorid"/>
        <html:text property="monitorid" styleId="monitorid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorRefForm.teloremail"/>
        <html:errors property="teloremail"/>
        <html:text property="teloremail" styleId="teloremail" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorRefForm.toobjectid"/>
        <html:errors property="toobjectid"/>
        <html:text property="toobjectid" styleId="toobjectid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorRefForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonMessageMonitorRef')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonMessageMonitorRefForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>