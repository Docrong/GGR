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
				<html:text property="provider" styleId="provider" readonly="true"/>
			</td>

		</tr>
		<tr class="tr_show">

			<td class="clsfth">
				<bean:message key='interfaceMonitoring.name'/>
			</td>
			

		</tr>
		<tr class="tr_show">

		
			<td>
				<html:text property="interfaceName" styleId="interfaceName" readonly="true"/>
			</td>

		</tr>
		<tr class="tr_show">
			<td class="clsfth">
				<bean:message key='interfaceMonitoring.Address' />
			</td>
			

		</tr>
	<tr class="tr_show">
			<td>
			
				<html:text property="interfaceUrl" styleId="interfaceUrl" size="60" readonly="true"/>
			</td>

		</tr>



	</table>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
