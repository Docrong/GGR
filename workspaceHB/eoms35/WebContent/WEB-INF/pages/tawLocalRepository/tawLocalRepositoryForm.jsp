<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawLocalRepositoryForm'});
    });
</script>

<html:form action="/tawLocalRepositorys.do?method=save" styleId="tawLocalRepositoryForm" method="post">

    <fmt:bundle basename="config/applicationResource-tawlocalrepository">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="tawLocalRepository.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepository.province"/>
                </td>
                <td class="content">
                    <html:text property="province" styleId="province"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="湖北"/>
                </td>

                <td class="label">
                    <fmt:message key="tawLocalRepository.city"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}city" id="${sheetPageName}city" initDicId="1010107"
                                   defaultValue="${tawLocalRepositoryForm.city}" alt="allowBlank:false"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepository.net"/>
                </td>
                <td class="content">
                    <html:text property="net" styleId="net"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${tawLocalRepositoryForm.net}"/>
                </td>

                <td class="label">
                    <fmt:message key="tawLocalRepository.netType"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}netType" id="${sheetPageName}netType"
                                   sub="${sheetPageName}driverTpye" initDicId="1011701"
                                   defaultValue="${tawLocalRepositoryForm.netType}" alt="allowBlank:false"
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
                                   defaultValue="${tawLocalRepositoryForm.driverTpye}" alt="allowBlank:false"
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
                    <fmt:message key="tawLocalRepository.patch"/>
                </td>
                <td class="content">
                    <html:text property="patch" styleId="patch"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${tawLocalRepositoryForm.patch}"/>
                </td>

                <td class="label">
                    <fmt:message key="tawLocalRepository.networkTime"/>
                </td>
                <td class="content">

                    <html:text property="networkTime" styleId="networkTime"
                               styleClass="text medium" readonly="true"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1);"
                               alt="allowBlank:true,vtext:''" value="${tawLocalRepositoryForm.networkTime}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepository.state"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-repository" dictId="state" beanId="selectXML"
                               defaultId="${tawLocalRepositoryForm.state}" isQuery="false"
                               selectId="state" alt="allowBlank:false,vtext:''"/>
                </td>
                    <%--
                            <td class="content">
                                <eoms:comboBox name="state" id="state" initDicId="10305" ></eoms:comboBox>
                            </td>
                            --%>
                <td class="label">
                </td>
                <td class="content">
                </td>
            </tr>

        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty tawLocalRepositoryForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('确认删除?')){
                                   var url='${app}/repository/tawLocalRepositorys.do?method=remove&id=${tawLocalRepositoryForm.id}';
                                   location.href=url}"/>
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${tawLocalRepositoryForm.id}"/>
    <html:hidden property="state" value='1'/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>