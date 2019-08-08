<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawLocalRepositoryForm'});
    });
</script>

<html:form action="/tawLocalRepositorys.do?method=queryHistory" styleId="tawLocalRepositoryForm" method="post">

    <fmt:bundle basename="config/applicationResource-tawlocalrepository">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="tawLocalRepositoryUp.query.title"/></div>
            </caption>

            <tr>

                <td class="label">
                    <fmt:message key="tawLocalRepository.city"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}city" id="${sheetPageName}city" initDicId="1010107"
                                   defaultValue="${tawLocalRepositoryForm.city}"/>
                </td>
                <td class="label">
                    <fmt:message key="tawLocalRepository.state"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-repository" dictId="state" beanId="selectXML"
                               defaultId="1" isQuery="false"
                               selectId="state"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepository.net"/>
                </td>
                <td class="content" colspan="3">
                    <html:text property="net" styleId="net"
                               styleClass="text medium"
                               value="${tawLocalRepositoryForm.net}"/>
                </td>
            </tr>

        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.query"/>"/>
            </td>
        </tr>
    </table>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>