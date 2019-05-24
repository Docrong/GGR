<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<title><fmt:message key="tawWorkbenchContactDetail.title" />
</title>
<script type="text/javascript"
	src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=dept';
	userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,		
			emptyText : '<div>${eoms:a2u("所属部门")} </div>'
								
		}
	);
	userViewer.refresh();
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'deptId' 
	});
})
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawWorkbenchContactForm'});
	 
});

</script>


<html:form
	action="/interfaceMonitoringLog.do?method=saveHorizontalInterfaceConfiguration"
	styleId="interfaceMonitoringForm" method="post">
	<table border="0" width="100%" cellspacing="1">
		<html:hidden property="moduleid" styleId="moduleid" />
		<html:hidden property="id" styleId="id" />
		<tr class="tr_show">
			<td class="clsfth">
				<bean:message key='interfaceMonitoring.ProviderName' />
			</td>
			
		</tr>
		<tr class="tr_show">
		
			<td>
				<html:text property="provider" styleId="provider"/>
			</td>

		</tr>
		<tr class="tr_show">

			<td class="clsfth">
				<bean:message key='interfaceMonitoring.name' />
			</td>
			

		</tr>
		<tr class="tr_show">

		
			<td>
				<html:text property="interfaceName" styleId="interfaceName"/>
			</td>

		</tr>
		<tr class="tr_show">
			<td class="clsfth">
				<bean:message key='interfaceMonitoring.Address' />
			</td>
			

		</tr>
	<tr class="tr_show">
			<td>
				<html:text property="interfaceUrl" styleId="interfaceUrl" size="60" />
			</td>

		</tr>



	</table>
	<table border="0" width="100%" cellspacing="0">
		<tr>
			<td width="100%" height="32" align="left">
				<html:submit property="strutsButton" styleClass="clsbtn2">
					<fmt:message key="button.save" />
				</html:submit>
				&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
