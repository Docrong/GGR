<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="smsApplyDetail.title"/></title>
<content tag="heading"><fmt:message key="smsApplyDetail.heading"/></content>

<html:form action="saveSmsApply" method="post" styleId="smsApplyForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.count"/>
        <html:errors property="count"/>
        <html:text property="count" styleId="count" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.cycle"/>
        <html:errors property="cycle"/>
        <html:text property="cycle" styleId="cycle" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.cycleTime"/>
        <html:errors property="cycleTime"/>
        <html:text property="cycleTime" styleId="cycleTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.endTime"/>
        <html:errors property="endTime"/>
        <html:text property="endTime" styleId="endTime" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.name"/>
        <html:errors property="name"/>
        <html:text property="name" styleId="name" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.interval"/>
        <html:errors property="interval"/>
        <html:text property="interval" styleId="interval" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.mobile"/>
        <html:errors property="mobile"/>
        <html:text property="mobile" styleId="mobile" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.receiverId"/>
        <html:errors property="receiverId"/>
        <html:text property="receiverId" styleId="receiverId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.receiverType"/>
        <html:errors property="receiverType"/>
        <html:text property="receiverType" styleId="receiverType" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.regetData"/>
        <html:errors property="regetData"/>
        <html:text property="regetData" styleId="regetData" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.sendDay"/>
        <html:errors property="sendDay"/>
        <html:text property="sendDay" styleId="sendDay" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.sendHour"/>
        <html:errors property="sendHour"/>
        <html:text property="sendHour" styleId="sendHour" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.sendMin"/>
        <html:errors property="sendMin"/>
        <html:text property="sendMin" styleId="sendMin" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.sendStatus"/>
        <html:errors property="sendStatus"/>
        <html:text property="sendStatus" styleId="sendStatus" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.serviceId"/>
        <html:errors property="serviceId"/>
        <html:text property="serviceId" styleId="serviceId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.startTime"/>
        <html:errors property="startTime"/>
        <html:text property="startTime" styleId="startTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="smsApplyForm.userId"/>
        <html:errors property="userId"/>
        <html:text property="userId" styleId="userId" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('SmsApply')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("smsApplyForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>