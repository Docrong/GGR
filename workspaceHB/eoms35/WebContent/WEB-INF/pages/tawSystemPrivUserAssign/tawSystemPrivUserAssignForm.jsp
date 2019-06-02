<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<title><fmt:message key="tawSystemPrivUserAssignDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemPrivUserAssignDetail.heading"/></content>

<html:form action="saveTawSystemPrivUserAssign" method="post" styleId="tawSystemPrivUserAssignForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.currentprivid"/>
        <html:errors property="currentprivid"/>
        <html:text property="currentprivid" styleId="currentprivid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.currentprivname"/>
        <html:errors property="currentprivname"/>
        <html:text property="currentprivname" styleId="currentprivname" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.hide"/>
        <html:errors property="hide"/>
        <html:text property="hide" styleId="hide" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.isonepriv"/>
        <html:errors property="isonepriv"/>
        <html:text property="isonepriv" styleId="isonepriv" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.leaf"/>
        <html:errors property="leaf"/>
        <html:text property="leaf" styleId="leaf" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.ordercode"/>
        <html:errors property="ordercode"/>
        <html:text property="ordercode" styleId="ordercode" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.parentprivid"/>
        <html:errors property="parentprivid"/>
        <html:text property="parentprivid" styleId="parentprivid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.parentprivname"/>
        <html:errors property="parentprivname"/>
        <html:text property="parentprivname" styleId="parentprivname" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemPrivUserAssignForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemPrivUserAssign')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemPrivUserAssignForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>