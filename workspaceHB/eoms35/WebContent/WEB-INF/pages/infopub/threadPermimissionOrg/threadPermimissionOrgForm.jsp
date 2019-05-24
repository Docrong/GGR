<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="threadPermimissionOrgDetail.title"/></title>
<content tag="heading"><fmt:message key="threadPermimissionOrgDetail.heading"/></content>

<html:form action="saveThreadPermimissionOrg" method="post" styleId="threadPermimissionOrgForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="threadPermimissionOrgForm.orgId"/>
        <html:errors property="orgId"/>
        <html:text property="orgId" styleId="orgId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="threadPermimissionOrgForm.ortType"/>
        <html:errors property="ortType"/>
        <html:text property="ortType" styleId="ortType" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('ThreadPermimissionOrg')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("threadPermimissionOrgForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>