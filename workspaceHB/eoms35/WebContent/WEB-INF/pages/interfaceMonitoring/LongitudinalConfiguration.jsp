<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<style>
#tabs {
	width: 90%;
}

#tabs .x-tabs-item-body {
	display: none;
	padding: 10px;
}
</style>
<script type="text/javascript">
Ext.onReady(function(){
	var tabs = new Ext.TabPanel('tabs');
    var formTab = tabs.addTab('form', "<bean:message key='interfaceMonitoring.horizontalConfiguration'/>");
    var infoTab = tabs.addTab('info', "<bean:message key='interfaceMonitoring.longitudinalConfiguration'/>");
    formTab.on('activate',function(){
    	//location.href = "/interfaceMonitoringLog.do?method=newInterfaceMonitoring&id=ssssss"
    });
    infoTab.on('activate',function(){
    	//location.href = "/interfaceMonitoringLog.do?method=newInterfaceMonitoring&id=ss"
    });
    tabs.activate('info');	
});
</script>

<content tag="heading">
<bean:message key="interfaceMonitoring.Configuration" />
</content>



<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

	<div id="form" class="tab-content">
		<html:form
			action="/interfaceMonitoringLog.do?method=saveHorizontalInterfaceConfiguration">
			<table border="0" width="100%" cellspacing="1">

				<tr class="tr_show">
					<td class="clsfth">
						<bean:message key='interfaceMonitoring.ProviderName' />
					</td>
					<td>
						<html:text property="provider" />
					</td>

				</tr>
				<tr class="tr_show">

					<td class="clsfth">
						<bean:message key='interfaceMonitoring.name' />
					</td>
					<td>
						<html:text property="interfaceName" />
					</td>

				</tr>
				<tr class="tr_show">
					<td class="clsfth">
						<bean:message key='interfaceMonitoring.Address' />
					</td>
					<td>
						<html:text property="interfaceUrl" size="60" />
					</td>

				</tr>




			</table>
			<table border="0" width="100%" cellspacing="0">
				<tr>
					<td width="100%" height="32" align="right">
						<html:submit property="strutsButton" styleClass="clsbtn2">
							<bean:message key='interfaceMonitoring.save' />
						</html:submit>
						&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</html:form>
		<bean:message key='interfaceMonitoring.horizontalConfigurationList' />

		<table cellspacing="0" cellpadding="1" width="100%" border="0"
			class="listTable">
			<tr class="tr_show">
				<td class="clsfth">
					<bean:message key='interfaceMonitoring.ProviderName' />

				</td>
				<td class="clsfth">
					<bean:message key='interfaceMonitoring.name' />

				</td>
				<td class="clsfth">
					<bean:message key='interfaceMonitoring.Address' />

				</td>
				<td class="clsfth">
					<bean:message key='interfaceMonitoring.OperationName' />

				</td>
			</tr>
			<logic:iterate id="InterfaceMonitoringList"
				name="InterfaceMonitoringList">

				<tr class="tr_show">
					<td class="clsfth">

						<bean:write name="InterfaceMonitoringList" property="provider" />
					</td>
					<td class="clsfth">

						<bean:write name="InterfaceMonitoringList"
							property="interfaceName" />
					</td>
					<td class="clsfth">
						<html:text property="interfaceUrl" size="50"
							value="${InterfaceMonitoringList.interfaceUrl}" />

					</td>
					<td  height="32" align="right">
						<html:submit property="strutsButton" styleClass="clsbtn2">
							<bean:message key='interfaceMonitoring.Operation' />
						</html:submit>
						&nbsp;&nbsp;
					</td>
				</tr>
			</logic:iterate>
		</table>
	</div>
	<div id="info">
		<table border="0" width="95%" cellspacing="1">
			<html:form
				action="/interfaceMonitoringLog.do?method=saveLongitudinalInterfaceConfiguration">
					<table border="0" width="100%" cellspacing="1">

				<tr class="tr_show">
					<td class="clsfth">
						<bean:message key='interfaceMonitoring.ProviderName' />
					</td>
					<td>
						<html:text property="provider" />
					</td>

				</tr>
				<tr class="tr_show">

					<td class="clsfth">
						<bean:message key='interfaceMonitoring.name' />
					</td>
					<td>
						<html:text property="interfaceName" />
					</td>

				</tr>
				<tr class="tr_show">
					<td class="clsfth">
						<bean:message key='interfaceMonitoring.Address' />
					</td>
					<td>
						<html:text property="interfaceUrl" size="60" />
					</td>

				</tr>




			</table>
				<table border="0" width="100%" cellspacing="0">
					<tr>
						<td  height="32" align="right">
						<html:submit property="strutsButton" styleClass="clsbtn2">
							<bean:message key='interfaceMonitoring.save' />
						</html:submit>
						&nbsp;&nbsp;
					</td>
					</tr>
				</table>
			</html:form>
			<bean:message key='interfaceMonitoring.longitudinalConfigurationList' />
			
				<table cellspacing="0" cellpadding="1" width="100%" border="0"
			class="listTable">
			<tr class="tr_show">
				<td class="clsfth">
					<bean:message key='interfaceMonitoring.ProviderName' />

				</td>
				<td class="clsfth">
					<bean:message key='interfaceMonitoring.name' />

				</td>
				<td class="clsfth">
					<bean:message key='interfaceMonitoring.Address' />

				</td>
				<td class="clsfth">
					<bean:message key='interfaceMonitoring.OperationName' />

				</td>
			</tr>
			<logic:iterate id="LongitudinalList"
				name="LongitudinalList">

				<tr class="tr_show">
					<td class="clsfth">

						<bean:write name="LongitudinalList" property="provider" />
					</td>
					<td class="clsfth">

						<bean:write name="LongitudinalList"
							property="interfaceName" />
					</td>
					<td class="clsfth">
						<html:text property="interfaceUrl" size="50"
							value="${LongitudinalList.interfaceUrl}" />

					</td>
					<td  height="32" align="right">
						<html:submit property="strutsButton" styleClass="clsbtn2">
							<bean:message key='interfaceMonitoring.Operation' />
						</html:submit>
						&nbsp;&nbsp;
					</td>
				</tr>
			</logic:iterate>
		</table>
			</div>
			<c:out value="${buttons}" escapeXml="false" />

			<%@ include file="/common/footer_eoms.jsp"%>