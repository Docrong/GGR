<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawCommonsJobmonitorDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonsJobmonitorDetail.heading"/></content>

<html:form action="saveTawCommonsJobmonitor" method="post" styleId="tawCommonsJobmonitorForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobmonitorForm.jobSortId"/>
        <html:errors property="jobSortId"/>
        <html:text property="jobSortId" styleId="jobSortId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobmonitorForm.jobSubId"/>
        <html:errors property="jobSubId"/>
        <html:text property="jobSubId" styleId="jobSubId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobmonitorForm.maxExecuteTime"/>
        <html:errors property="maxExecuteTime"/>
        <html:text property="maxExecuteTime" styleId="maxExecuteTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobmonitorForm.executeEndTime"/>
        <html:errors property="executeEndTime"/>
        <html:text property="executeEndTime" styleId="executeEndTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobmonitorForm.executeStartTime"/>
        <html:errors property="executeStartTime"/>
        <html:text property="executeStartTime" styleId="executeStartTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobmonitorForm.status"/>
        <html:errors property="status"/>
        <html:text property="status" styleId="status" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonsJobmonitor')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonsJobmonitorForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>