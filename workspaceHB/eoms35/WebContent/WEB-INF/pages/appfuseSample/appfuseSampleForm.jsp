<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="appfuseSampleDetail.title"/></title>
<content tag="heading"><fmt:message key="appfuseSampleDetail.heading"/></content>

<html:form action="saveAppfuseSample" method="post" styleId="appfuseSampleForm"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="appfuseSampleForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('AppfuseSample')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("appfuseSampleForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>