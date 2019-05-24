<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="threadHistoryDetail.title"/></title>
<content tag="heading"><fmt:message key="threadHistoryDetail.heading"/></content>

<html:form action="saveThreadHistory" method="post" styleId="threadHistoryForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="threadHistoryForm.ip"/>
        <html:errors property="ip"/>
        <html:text property="ip" styleId="ip" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="threadHistoryForm.readTime"/>
        <html:errors property="readTime"/>
        <html:text property="readTime" styleId="readTime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="threadHistoryForm.threadId"/>
        <html:errors property="threadId"/>
        <html:text property="threadId" styleId="threadId" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="threadHistoryForm.userId"/>
        <html:errors property="userId"/>
        <html:text property="userId" styleId="userId" styleClass="text medium"/>

    </li>
    


    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('ThreadHistory')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("threadHistoryForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>