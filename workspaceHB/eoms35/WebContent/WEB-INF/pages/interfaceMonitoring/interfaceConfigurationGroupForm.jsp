<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<title><fmt:message key="tawWorkbenchContactGroupDetail.title" />
</title>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawWorkbenchContactGroupForm'});
});
</script>
<html:form action="/interfaceMonitoringLog.do?method=xsave"
	method="post" styleId="interfaceMonitoringForm">
	<table border="0" width="100%" cellspacing="1">
		<html:hidden property="id" styleId="id" />
		<tr class="tr_show">
			<td class="clsfth">
				<bean:message key='interfaceMonitoring.moduleName'/>
			</td>


		</tr>
		<tr class="tr_show">

			<td>
				<html:text property="moduleName" styleId="moduleName"/>
			</td>

		</tr>
		<tr class="tr_show">

			<td class="clsfth">
				<bean:message key='interfaceMonitoring.moduleDesignation'/>
			</td>


		</tr>
		<tr class="tr_show">

			<td>
				<html:text property="moduleDesignation" styleId="moduleDesignation"/>
			</td>

		</tr>
		<tr>
			<td width="100%" height="32" align="left">
				<html:submit property="strutsButton" styleClass="clsbtn2">
					<bean:message key='interfaceMonitoring.save' />
				</html:submit>
				&nbsp;&nbsp;
			</td>
		</tr>

	</table>


</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
