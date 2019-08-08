<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawSupplierkpiInfoForm'});
    })
</script>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <div class="list-title">
        <fmt:message key="tawSupplierkpiInfoDetail.heading"/>
    </div>
</fmt:bundle>
<html:form action="saveTawSupplierkpiInfo" method="post" styleId="tawSupplierkpiInfoForm" onsubmit="return validate();">

    <table>
        <tr height="30">
            <td width="30%">
                <eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierName"/>
            </td>
            <td width="70%">
                <html:text property="supplierName" styleId="supplierName" styleClass="text medium"
                           alt="allowBlank:false,vtext:'${eoms:a2u('请输入供应商名称')}'"/>
            </td>
        </tr>
        <tr height="30">
            <td>
                <eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierLinkman"/>
            </td>
            <td>
                <html:text property="supplierLinkman" styleId="supplierLinkman" styleClass="text medium"/>
            </td>
        </tr>
        <tr height="60">
            <td>
                <eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierContact"/>
            </td>
            <td>
                <html:textarea property="supplierContact" styleId="supplierContact" styleClass="textarea small"/>
            </td>
        </tr>
        <tr height="30">
            <td colspan="2">
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
            </td>
        </tr>
    </table>
    <html:hidden property="id"/>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>