<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawLocalRepositoryForm'});
    });
</script>

<html:form action="/tawLocalRepositorys.do?method=query" styleId="tawLocalRepositoryForm" method="post">

    <fmt:bundle basename="config/applicationResource-tawlocalrepository">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="tawLocalRepository.query.title"/></div>
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
                <td class="content">
                    <html:text property="net" styleId="net"
                               styleClass="text medium"
                               value="${tawLocalRepositoryForm.net}"/>
                </td>
                <td class="label">
                    <fmt:message key="tawLocalRepository.netType"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}netType" id="${sheetPageName}netType"
                                   sub="${sheetPageName}driverTpye" initDicId="1011701"
                                   defaultValue="${tawLocalRepositoryForm.netType}"
                                   onchange="selectLimit(this.value);"/>

                </td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepository.driverTpye"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}driverTpye" id="${sheetPageName}driverTpye"
                                   sub="${sheetPageName}company" initDicId="${tawLocalRepositoryForm.netType}"
                                   defaultValue="${tawLocalRepositoryForm.driverTpye}"
                                   onchange="selectLimit(this.value);"/>
                </td>
                <td class="label">
                    <fmt:message key="tawLocalRepository.company"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}company" id="${sheetPageName}company"
                                   sub="${sheetPageName}netModale" initDicId="${tawLocalRepositoryForm.driverTpye}"
                                   defaultValue="${tawLocalRepositoryForm.company}"
                                   onchange="selectLimit(this.value);"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepository.netModale"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}netModale" id="${sheetPageName}netModale"
                                   sub="${sheetPageName}hardwareRepository"
                                   initDicId="${tawLocalRepositoryForm.company}"
                                   defaultValue="${tawLocalRepositoryForm.netModale}"
                                   onchange="selectLimit(this.value);"/>
                </td>

                <td class="label">
                    <fmt:message key="tawLocalRepository.hardwareRepository"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}hardwareRepository" id="${sheetPageName}hardwareRepository"
                                   sub="${sheetPageName}softwareRepository"
                                   initDicId="${tawLocalRepositoryForm.netModale}"
                                   defaultValue="${tawLocalRepositoryForm.hardwareRepository}"
                                   onchange="selectLimit(this.value);"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepository.softwareRepository"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}softwareRepository" id="${sheetPageName}softwareRepository"
                                   sub="${sheetPageName}mainNetTypeThree"
                                   initDicId="${tawLocalRepositoryForm.hardwareRepository}"
                                   defaultValue="${tawLocalRepositoryForm.softwareRepository}"
                                   onchange="selectLimit(this.value);"/>
                </td>
            </tr>

            <tr>

                <td class="label">
                    <fmt:message key="tawLocalRepository.networkTimeFrom"/>
                </td>
                <td class="content">

                    <html:text property="networkTimeFrom" styleId="networkTimeFrom"
                               styleClass="text medium" readonly="true"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1);"
                               alt="allowBlank:true,vtext:''" value="${tawLocalRepositoryForm.networkTimeFrom}"/>
                </td>
                <td class="label">
                    <fmt:message key="tawLocalRepository.networkTimeTo"/>
                </td>
                <td class="content">

                    <html:text property="networkTimeTo" styleId="networkTimeTo"
                               styleClass="text medium" readonly="true"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1);"
                               alt="allowBlank:true,vtext:''" value="${tawLocalRepositoryForm.networkTimeTo}"/>
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