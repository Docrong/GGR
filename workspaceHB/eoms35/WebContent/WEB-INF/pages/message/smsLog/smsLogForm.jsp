<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<title><fmt:message key="smsLogDetail.title"/></title>
<content tag="heading"><fmt:message key="smsLogDetail.heading"/></content>

<html:form action="saveSmsLog" method="post" styleId="smsLogForm">
    <ul>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.content"/>
            <html:errors property="content"/>
            <html:text property="content" styleId="content" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.dispatchTime"/>
            <html:errors property="dispatchTime"/>
            <html:text property="dispatchTime" styleId="dispatchTime" styleClass="text medium"/>

        </li>

        <html:hidden property="id"/>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.insertTime"/>
            <html:errors property="insertTime"/>
            <html:text property="insertTime" styleId="insertTime" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.mobile"/>
            <html:errors property="mobile"/>
            <html:text property="mobile" styleId="mobile" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.moduleId"/>
            <html:errors property="moduleId"/>
            <html:text property="moduleId" styleId="moduleId" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.moduleName"/>
            <html:errors property="moduleName"/>
            <html:text property="moduleName" styleId="moduleName" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.monitorId"/>
            <html:errors property="monitorId"/>
            <html:text property="monitorId" styleId="monitorId" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.reason"/>
            <html:errors property="reason"/>
            <html:text property="reason" styleId="reason" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.receiverId"/>
            <html:errors property="receiverId"/>
            <html:text property="receiverId" styleId="receiverId" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.serviceId"/>
            <html:errors property="serviceId"/>
            <html:text property="serviceId" styleId="serviceId" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.serviceName"/>
            <html:errors property="serviceName"/>
            <html:text property="serviceName" styleId="serviceName" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsLogForm.success"/>
            <html:errors property="success"/>
            <html:text property="success" styleId="success" styleClass="text medium"/>

        </li>

        <li class="buttonBar bottom">
            <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
                <fmt:message key="button.save"/>
            </html:submit>

            <html:submit styleClass="button" property="method.delete"
                         onclick="bCancel=true; return confirmDelete('SmsLog')">
                <fmt:message key="button.delete"/>
            </html:submit>

            <html:cancel styleClass="button" onclick="bCancel=true">
                <fmt:message key="button.cancel"/>
            </html:cancel>
        </li>
    </ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("smsLogForm"));
</script>

<%@ include file="/common/footer_eoms.jsp" %>