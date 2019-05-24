<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<title><fmt:message key="tawSheetSpecialDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSheetSpecialDetail.heading"/></content>

<html:form action="saveTawSheetSpecial" method="post" styleId="tawSheetSpecialForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.leaf"/>
        <html:errors property="leaf"/>
        <html:text property="leaf" styleId="leaf" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.ordercode"/>
        <html:errors property="ordercode"/>
        <html:text property="ordercode" styleId="ordercode" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.parspeid"/>
        <html:errors property="parspeid"/>
        <html:text property="parspeid" styleId="parspeid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.refuserid"/>
        <html:errors property="refuserid"/>
        <html:text property="refuserid" styleId="refuserid" styleClass="text medium"/>
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.specialname"/>
        <html:errors property="specialname"/>
        <html:text property="specialname" styleId="specialname" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.specialtype"/>
        <html:errors property="specialtype"/>
        <html:text property="specialtype" styleId="specialtype" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.speid"/>
        <html:errors property="speid"/>
        <html:text property="speid" styleId="speid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSheetSpecialForm.style"/>
        <html:errors property="style"/>
        <html:text property="style" styleId="style" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSheetSpecial')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSheetSpecialForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>