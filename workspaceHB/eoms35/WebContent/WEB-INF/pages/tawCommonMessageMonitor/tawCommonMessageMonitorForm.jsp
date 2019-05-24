<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawCommonMessageMonitorDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonMessageMonitorDetail.heading"/></content>

<html:form action="saveTawCommonMessageMonitor" method="post" styleId="tawCommonMessageMonitorForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.urgent"/>
        <html:errors property="urgent"/>
        <html:text property="urgent" styleId="urgent" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.cycle"/>
        <html:errors property="cycle"/>
        <html:text property="cycle" styleId="cycle" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.dispatcher"/>
        <html:errors property="dispatcher"/>
        <html:text property="dispatcher" styleId="dispatcher" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.dispatchTime"/>
        <html:errors property="dispatchTime"/>
        <html:text property="dispatchTime" styleId="dispatchTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.endTime"/>
        <html:errors property="endTime"/>
        <html:text property="endTime" styleId="endTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieAmount"/>
        <html:errors property="hieAmount"/>
        <html:text property="hieAmount" styleId="hieAmount" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieArrive"/>
        <html:errors property="hieArrive"/>
        <html:text property="hieArrive" styleId="hieArrive" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieClose"/>
        <html:errors property="hieClose"/>
        <html:text property="hieClose" styleId="hieClose" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieContent"/>
        <html:errors property="hieContent"/>
        <html:text property="hieContent" styleId="hieContent" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieCount"/>
        <html:errors property="hieCount"/>
        <html:text property="hieCount" styleId="hieCount" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieId"/>
        <html:errors property="hieId"/>
        <html:text property="hieId" styleId="hieId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieInterval"/>
        <html:errors property="hieInterval"/>
        <html:text property="hieInterval" styleId="hieInterval" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieTimeLimit"/>
        <html:errors property="hieTimeLimit"/>
        <html:text property="hieTimeLimit" styleId="hieTimeLimit" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.hieWay"/>
        <html:errors property="hieWay"/>
        <html:text property="hieWay" styleId="hieWay" styleClass="text medium"/>

    </li>

<html:hidden property="monitorId"/>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.nightAllow"/>
        <html:errors property="nightAllow"/>
        <html:text property="nightAllow" styleId="nightAllow" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.receiver"/>
        <html:errors property="receiver"/>
        <html:text property="receiver" styleId="receiver" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.receiverType"/>
        <html:errors property="receiverType"/>
        <html:text property="receiverType" styleId="receiverType" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.regionId"/>
        <html:errors property="regionId"/>
        <html:text property="regionId" styleId="regionId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.startTime"/>
        <html:errors property="startTime"/>
        <html:text property="startTime" styleId="startTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageMonitorForm.taskId"/>
        <html:errors property="taskId"/>
        <html:text property="taskId" styleId="taskId" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonMessageMonitor')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonMessageMonitorForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>