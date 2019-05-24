<%@ include file="/portal/common/taglibs-portal.jsp"%>
<%@ include file="/portal/common/header_portal.jsp"%>

<title><bean:message key="portalSysManageDetail.title"/></title>
<content tag="heading"><bean:message key="portalSysManageDetail.heading"/></content>

<html:form action="savePortalSysManage" method="post" styleId="portalSysManageForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="portalSysManageForm.sysid"/>
        <html:errors property="sysid"/>
        <html:text property="sysid" styleId="sysid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="portalSysManageForm.sysname"/>
        <html:errors property="sysname"/>
        <html:text property="sysname" styleId="sysname" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="portalSysManageForm.sysurl"/>
        <html:errors property="sysurl"/>
        <html:text property="sysurl" styleId="sysurl" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <bean:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('PortalSysManage')">
            <bean:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <bean:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("portalSysManageForm"));
</script>

<%@ include file="/portal/common/footer_portal.jsp"%>