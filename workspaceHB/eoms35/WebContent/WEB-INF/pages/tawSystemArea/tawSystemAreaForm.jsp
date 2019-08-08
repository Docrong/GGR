<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>

<title><fmt:message key="tawSystemAreaForm.title"/></title>
<content tag="heading"><fmt:message key="tawSystemAreaForm.title"/></content>

<html:form action="saveTawSystemArea" method="post" styleId="tawSystemAreaForm">
    <ul>
        <html:hidden property="areaid"/>
        <html:hidden property="id"/>
        <html:hidden property="leaf"/>
        <li>
            <eoms:label styleClass="desc" key="tawSystemAreaForm.areaname"/>
            <html:errors property="areaname"/>
            <html:text property="areaname" styleId="areaname" styleClass="text medium"/>

        </li>
        <li>
            <eoms:label styleClass="desc" key="tawSystemAreaForm.areaname"/>
            <html:errors property="areacode"/>
            <html:text property="areacode" styleId="areacode" styleClass="text medium"/>

        </li>
        <li>
            <eoms:label styleClass="desc" key="tawSystemAreaForm.remark"/>
            <html:errors property="remark"/>
            <html:text property="remark" styleId="remark" styleClass="text medium"/>

        </li>

        <li class="buttonBar bottom">
            <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
                <fmt:message key="button.save"/>
            </html:submit>

            <html:submit styleClass="button" property="method.delete"
                         onclick="bCancel=true; return confirmDelete('TawSystemArea')">
                <fmt:message key="button.delete"/>
            </html:submit>

            <html:cancel styleClass="button" onclick="bCancel=true">
                <fmt:message key="button.cancel"/>
            </html:cancel>
        </li>
    </ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemAreaForm"));
</script>


<%@ include file="/common/footer_eoms.jsp" %>