<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawSystemPostTypeDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemPostTypeDetail.heading"/></content>

<html:form action="saveTawSystemPostType" method="post" styleId="tawSystemPostTypeForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPostTypeForm.deleted"/>
        <html:errors property="deleted"/>
        <html:text property="deleted" styleId="deleted" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPostTypeForm.notes"/>
        <html:errors property="notes"/>
        <html:text property="notes" styleId="notes" styleClass="text medium"/>

    </li>

<html:hidden property="posttype_id"/>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPostTypeForm.posttype_name"/>
        <html:errors property="posttype_name"/>
        <html:text property="posttype_name" styleId="posttype_name" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPostTypeForm.system_name"/>
        <html:errors property="system_name"/>
        <html:text property="system_name" styleId="system_name" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemPostType')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemPostTypeForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>