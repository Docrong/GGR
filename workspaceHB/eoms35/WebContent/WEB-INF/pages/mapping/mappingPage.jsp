<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'mappingForm'});
    });
</script>

<html:form action="/mappings.do?method=save" styleId="mappingForm" method="post">

    <fmt:bundle basename="config/applicationResource-mapping">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="mapping.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="mapping.appcode"/>
                </td>
                <td class="content">
                    <html:text property="app_code" styleId="app_code"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${mappingForm.app_code}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="mapping.appname"/>
                </td>
                <td class="content">
                    <html:text property="app_name" styleId="app_name"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${mappingForm.app_name}"/>
                </td>
            </tr>


            <tr>
                <td class="label">
                    <fmt:message key="mapping.newtable"/>
                </td>
                <td class="newtable">
                    <html:text property="new_table" styleId="new_table"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${mappingForm.new_table}" readonly="true"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="mapping.beanid"/>
                </td>
                <td class="newtable">
                    <html:text property="beanid" styleId="mapping.beanid"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${mappingForm.beanid}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="mapping.context"/>
                </td>
                <td class="content">
                    <html:textarea cols="20" rows="5" property="context" styleId="context"
                                   styleClass="text medium"
                                   alt="allowBlank:true,vtext:''" value="${mappingForm.context}"/>
                </td>
            </tr>


        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty mappingForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('confirm?')){
                                   var url='${app}/mappingstorage/mappings.do?method=remove&id=${mappingForm.id}';
                                   location.href=url}"/>
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${mappingForm.id}"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>