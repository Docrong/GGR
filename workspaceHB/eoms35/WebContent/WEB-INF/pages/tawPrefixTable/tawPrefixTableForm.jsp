<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawPrefixTableDetail.title"/></title>
<content tag="heading"><fmt:message key="tawPrefixTableDetail.heading"/></content>

<html:form action="saveTawPrefixTable" method="post" styleId="tawPrefixTableForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawPrefixTableForm.prefixName"/>
        <html:errors property="prefixName"/>
        <html:text property="prefixName" styleId="prefixName" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawPrefixTable')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawPrefixTableForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>