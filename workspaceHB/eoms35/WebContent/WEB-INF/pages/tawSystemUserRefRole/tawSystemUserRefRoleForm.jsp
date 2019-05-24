<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<title><fmt:message key="tawSystemUserRefRoleDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemUserRefRoleDetail.heading"/></content>

<html:form action="saveTawSystemUserRefRole" method="post" styleId="tawSystemUserRefRoleForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserRefRoleForm.roleid"/>
        <html:errors property="roleid"/>
        <html:text property="roleid" styleId="roleid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserRefRoleForm.rolename"/>
        <html:errors property="rolename"/>
        <html:text property="rolename" styleId="rolename" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserRefRoleForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserRefRoleForm.username"/>
        <html:errors property="username"/>
        <html:text property="username" styleId="username" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserRefRoleForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemUserRefRole')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemUserRefRoleForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>