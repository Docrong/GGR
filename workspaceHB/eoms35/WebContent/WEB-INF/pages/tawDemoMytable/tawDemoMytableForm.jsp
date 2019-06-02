<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawDemoMytableDetail.title"/></title>
<content tag="heading"><fmt:message key="tawDemoMytableDetail.heading"/></content>

<html:form action="saveTawDemoMytable" method="post" styleId="tawDemoMytableForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawDemoMytableForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawDemoMytableForm.tableName"/>
        <html:errors property="tableName"/>
        <html:text property="tableName" styleId="tableName" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawDemoMytable')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawDemoMytableForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>