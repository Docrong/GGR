<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="deptDetail.title"/></title>
<content tag="heading"><fmt:message key="deptDetail.heading"/></content>

<html:form action="saveDept" method="post" styleId="deptForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="deptForm.deptName"/>
        <html:errors property="deptName"/>
        <html:text property="deptName" styleId="deptName" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="deptForm.deptDescription"/>
        <html:errors property="deptDescription"/>
        <html:text property="deptDescription" styleId="deptDescription" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="deptForm.dudajiang"/>
        <html:errors property="dudajiang"/>
        <html:text property="dudajiang" styleId="dudajiang" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="deptForm.aaadd"/>
        <html:errors property="aaadd"/>
        <html:text property="aaadd" styleId="aaadd" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('Dept')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("deptForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>