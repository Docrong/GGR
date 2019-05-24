<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawCommonMessageModelTypeDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonMessageModelTypeDetail.heading"/></content>

<html:form action="saveTawCommonMessageModelType" method="post" styleId="tawCommonMessageModelTypeForm" styleClass="required-validate"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageModelTypeForm.modelid"/>
        <html:errors property="modelid"/>
        <html:text property="modelid" styleId="modelid" styleClass="text medium required  max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageModelTypeForm.modelname"/>
        <html:errors property="modelname"/>
        <html:text property="modelname" styleId="modelname" styleClass="text medium required  max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageModelTypeForm.modelremark"/>
        <html:errors property="modelremark"/>
        <html:text property="modelremark" styleId="modelremark" styleClass="text medium  max-length-100" />

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonMessageModelType')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonMessageModelTypeForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>