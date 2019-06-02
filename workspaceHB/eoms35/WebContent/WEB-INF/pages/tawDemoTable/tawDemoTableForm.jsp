<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawDemoTableDetail.title"/></title>
<content tag="heading"><fmt:message key="tawDemoTableDetail.heading"/></content>

<html:form action="saveTawDemoTable" method="post" styleId="tawDemoTableForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawDemoTableForm.addr"/>
        <html:errors property="addr"/>
        <html:text property="addr" styleId="addr" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawDemoTableForm.tel"/>
        <html:errors property="tel"/>
        <html:text property="tel" styleId="tel" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawDemoTable')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawDemoTableForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>