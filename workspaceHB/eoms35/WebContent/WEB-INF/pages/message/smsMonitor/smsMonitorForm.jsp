<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<title><fmt:message key="smsMonitorDetail.title"/></title>
<content tag="heading"><fmt:message key="smsMonitorDetail.heading"/></content>

<html:form action="saveSmsMonitor" method="post" styleId="smsMonitorForm">
    <ul>

        <li>
            <eoms:label styleClass="desc" key="smsMonitorForm.applyId"/>
            <html:errors property="applyId"/>
            <html:text property="applyId" styleId="applyId" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsMonitorForm.content"/>
            <html:errors property="content"/>
            <html:text property="content" styleId="content" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsMonitorForm.dispatchTime"/>
            <html:errors property="dispatchTime"/>
            <html:text property="dispatchTime" styleId="dispatchTime" styleClass="text medium"/>

        </li>

        <html:hidden property="id"/>

        <li>
            <eoms:label styleClass="desc" key="smsMonitorForm.mobile"/>
            <html:errors property="mobile"/>
            <html:text property="mobile" styleId="mobile" styleClass="text medium"/>

        </li>

        <li>
            <eoms:label styleClass="desc" key="smsMonitorForm.receiverId"/>
            <html:errors property="receiverId"/>
            <html:text property="receiverId" styleId="receiverId" styleClass="text medium"/>

        </li>

        <li class="buttonBar bottom">
            <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
                <fmt:message key="button.save"/>
            </html:submit>

            <html:submit styleClass="button" property="method.delete"
                         onclick="bCancel=true; return confirmDelete('SmsMonitor')">
                <fmt:message key="button.delete"/>
            </html:submit>

            <html:cancel styleClass="button" onclick="bCancel=true">
                <fmt:message key="button.cancel"/>
            </html:cancel>
        </li>
    </ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("smsMonitorForm"));
</script>

<%@ include file="/common/footer_eoms.jsp" %>