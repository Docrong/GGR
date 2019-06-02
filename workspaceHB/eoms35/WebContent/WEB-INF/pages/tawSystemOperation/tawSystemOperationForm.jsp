<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<title><fmt:message key="tawSystemOperationDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemOperationDetail.heading"/></content>

<html:form action="saveTawSystemOperation" method="post" styleId="tawSystemOperationForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawSystemOperationForm.code"/>
        <html:errors property="code"/>
        <html:text property="code" styleId="code" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawSystemOperationForm.isApp"/>
        <html:errors property="isApp"/>
        <html:text property="isApp" styleId="isApp" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemOperationForm.name"/>
        <html:errors property="name"/>
        <html:text property="name" styleId="name" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemOperationForm.parentId"/>
        <html:errors property="parentId"/>
        <html:text property="parentId" styleId="parentId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemOperationForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemOperationForm.url"/>
        <html:errors property="url"/>
        <html:text property="url" styleId="url" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemOperation')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemOperationForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>