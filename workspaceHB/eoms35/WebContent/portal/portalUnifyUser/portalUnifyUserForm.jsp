<%@ include file="/portal/common/taglibs-portal.jsp"%>
<%@ include file="/portal/common/header_portal.jsp"%>

<title><bean:message key="portalUnifyUserDetail.title"/></title>
<content tag="heading"><bean:message key="portalUnifyUserDetail.heading"/></content>

<html:form action="savePortalUnifyUser" method="post" styleId="portalUnifyUserForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="portalUnifyUserForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium"/>

    </li>
    
    <li>
        <eoms:label styleClass="desc" key="portalUnifyUserForm.passwd"/>
        <html:errors property="passwd"/>
        <html:text property="passwd" styleId="passwd" styleClass="text medium"/>

    </li>

    <li>
        <html:hidden property="relateuserid" styleId="relateuserid" styleClass="text medium"/>
        <html:hidden property="sysid" styleId="sysid" styleClass="text medium"/>

    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <bean:message key="button.save"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <bean:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("portalUnifyUserForm"));
</script>

<%@ include file="/portal/common/footer_portal.jsp"%>