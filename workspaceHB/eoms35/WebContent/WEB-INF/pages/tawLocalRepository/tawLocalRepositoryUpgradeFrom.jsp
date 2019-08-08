<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawLocalRepositoryUpForm'});
    });
</script>

<html:form action="/tawLocalRepositoryUps.do?method=save" styleId="tawLocalRepositoryUpForm" method="post">

    <fmt:bundle basename="config/applicationResource-tawlocalrepository">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="tawLocalRepositoryUp.form.heading"/><font
                        color="red">${saveFlag }</font></div>
            </caption>

            <html:hidden property="id"/>
            <html:hidden property="repositoryId"/>
            <html:hidden property="oper" value="${oper}"/>
            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.beforeHardwareRepository"/>
                </td>
                <td class="content">
                    <input type="hidden" name="beforeHardwareRepository" id="beforeHardwareRepository"
                           value="${tawLocalRepositoryUpForm.beforeHardwareRepository}">
                    <eoms:id2nameDB id="${tawLocalRepositoryUpForm.beforeHardwareRepository}"
                                    beanId="ItawSystemDictTypeDao"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.beforeSoftwareRepository"/>
                </td>
                <td class="content">
                    <input type="hidden" name="beforeSoftwareRepository" id="beforeSoftwareRepository"
                           value="${tawLocalRepositoryUpForm.beforeSoftwareRepository}">
                    <eoms:id2nameDB id="${tawLocalRepositoryUpForm.beforeSoftwareRepository}"
                                    beanId="ItawSystemDictTypeDao"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.beforePatch"/>
                </td>
                <td class="content">${tawLocalRepositoryUpForm.beforePatch}
                    <input type="hidden" name="beforePatch" id="beforePatch"
                           value="${tawLocalRepositoryUpForm.beforePatch}">
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.hardwareRepository"/>
                </td>
                <td class="content">
                    <html:text property="hardwareRepository" styleId="hardwareRepository"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${tawLocalRepositoryUpForm.hardwareRepository}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.softwareRepository"/>
                </td>
                <td class="content">
                    <html:text property="softwareRepository" styleId="softwareRepository"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${tawLocalRepositoryUpForm.softwareRepository}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.patch"/>
                </td>
                <td class="content">
                    <html:text property="patch" styleId="patch"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${tawLocalRepositoryUpForm.patch}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.uptime"/>
                </td>
                <td class="content">
                    <html:text property="uptime" styleId="uptime"
                               styleClass="text medium" readonly="true"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1);"
                               alt="allowBlank:false,vtext:''" value="${tawLocalRepositoryUpForm.uptime}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.content"/>
                </td>
                <td class="content">
                    <html:textarea property="content" styleId="content"
                                   styleClass="text medium" cols="30" rows="3"
                                   alt="allowBlank:true,vtext:''" value="${tawLocalRepositoryUpForm.content}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.reason"/>
                </td>
                <td class="content">
                    <html:textarea property="reason" styleId="reason"
                                   styleClass="text medium" cols="30" rows="3"
                                   alt="allowBlank:true,vtext:''" value="${tawLocalRepositoryUpForm.reason}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawLocalRepositoryUp.approval"/>
                </td>
                <td class="content">
                    <html:textarea property="approval" styleId="approval"
                                   styleClass="text medium" cols="30" rows="3"
                                   alt="allowBlank:true,vtext:''" value="${tawLocalRepositoryUpForm.approval}"/>
                </td>
            </tr>

            <!--  	<tr>
		<td class="label">
			<fmt:message key="tawLocalRepositoryUp.uptime" />
		</td>
		<td class="content">
			<html:text property="uptime" styleId="uptime"
						styleClass="text medium"
						onclick="popUpCalendar(this, this,null,null,null,true,-1);"
						alt="allowBlank:false,vtext:''" value="${tawLocalRepositoryUpForm.uptime}" />
		</td>
	</tr>
-->
        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <input type="button" class="btn" value="<fmt:message key="button.back"/>"
                       onclick="window.location = '${app}/repository/tawLocalRepositorys.do?method=queryHistory';"/>
            </td>
        </tr>
    </table>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>