<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="smsServiceDetail.title"/></title>
<content tag="heading"><fmt:message key="smsServiceDetail.heading"/></content>

<html:form action="saveSmsService" method="post" styleId="smsServiceForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.deleted"/>
        <html:errors property="deleted"/>
        <html:text property="deleted" styleId="deleted" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.isSendImediat"/>
        <html:errors property="isSendImediat"/>
        <html:text property="isSendImediat" styleId="isSendImediat" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.isSendNight"/>
        <html:errors property="isSendNight"/>
        <html:text property="isSendNight" styleId="isSendNight" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.moduleId"/>
        <html:errors property="moduleId"/>
        <html:text property="moduleId" styleId="moduleId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.moduleName"/>
        <html:errors property="moduleName"/>
        <html:text property="moduleName" styleId="moduleName" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.msgType"/>
        <html:errors property="msgType"/>
        <html:text property="msgType" styleId="msgType" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.name"/>
        <html:errors property="name"/>
        <html:text property="name" styleId="name" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.password"/>
        <html:errors property="password"/>
        <html:text property="password" styleId="password" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.urgency"/>
        <html:errors property="urgency"/>
        <html:text property="urgency" styleId="urgency" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsServiceForm.userId"/>
        <html:errors property="userId"/>
        <html:text property="userId" styleId="userId" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('SmsService')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("smsServiceForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>