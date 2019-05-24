<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html:html>
<head>
	<title>new</title>
</head>
<script type="text/javascript">
	var deptTree;
	Ext.onReady(function(){
		v = new eoms.form.Validation({form:'tawSystemReportedForm'});
			
		reportedOrgViewer = new Ext.JsonView("reportedOrgView",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div></div>'
			}
		);
		var reportedOrg='${reportedOrg}';
		reportedOrgViewer.jsonData = eoms.JSONDecode(reportedOrg);
		reportedOrgViewer.refresh();
		var	treeAction='${app}/xtree.do?method=userFromDept';
		reportedTree = new xbox({
			btnId:'clkReported',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'${eoms:a2u('选择上报用户')}',treeChkMode:'',treeChkType:'user',
			showChkFldId:'reportedOrgView',saveChkFldId:'reportedOrg',viewer:reportedOrgViewer,returnJSON:true
		});
	});

</script>
<body>
	<center>
		<table cellSpacing="0" cellPadding="0" width="85%" border="0">
			<tr>
				<td class="table_title" align="center">
					<b>${eoms:a2u('用户上报设置')}</b>
				</TD>
			</tr>
		</table>
		<html:form action="/tawSystemReported.do?method=save" method="post" styleId="tawSystemReportedForm" styleClass="required-validate">
			<table border="0" width="500" cellspacing="1" cellpadding="1" class="formTable" align=center>
				<html:hidden property="id" />
				<tr class="tr_show">
					<td class="label">
						<bean:message key="tawSystemReported.modelName" />
					</td>
					<td >
						<eoms:dict key="dict-reported" dictId="modelId" beanId="selectXML" isQuery="false" selectId="modelId" alt="allowBlank:false" defaultId="${tawSystemReportedForm.modelId}"/>
					</td>
				</tr>
				<tr class="tr_show">
					<td class="label">
						<bean:message key="tawSystemReported.functionName" />
					</td>
					<td >
						<eoms:dict key="dict-reported" dictId="functionId" beanId="selectXML" isQuery="false" selectId="functionId" alt="allowBlank:false" defaultId="${tawSystemReportedForm.functionId}"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="tawSystemReported.reportedSelectUser" />
					</td>
					<td >
						<input type="hidden" readonly id="showReportedOrg" name="showReportedOrg" />
						<input type="button" id="clkReported" name="clkReported" value="<bean:message key='tawSystemReported.reportedSelectUser'/>" />
						<div id="reportedOrgView"></div>
						<input type="hidden" id="reportedOrg" name="reportedOrg" />
					</td>
				</tr>
				<tr class="tr_show">
					<td class="label">
						<bean:message key="tawSystemReported.remark" />
					</td>
					<td >
        				<html:textarea property="remark" styleId="remark" styleClass="textarea medium"/>
					</td>
				</tr>
			</table>
			<BR>

			<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align=center>
				<TR>
					<TD align="right">
						<html:submit styleClass="button">
							<fmt:message key="button.save"/>
						</html:submit>
						&nbsp;
					</TD>
				</TR>
			</TABLE>
		</html:form>
	</center>
</body>
</html:html>

<%@ include file="/common/footer_eoms.jsp"%>