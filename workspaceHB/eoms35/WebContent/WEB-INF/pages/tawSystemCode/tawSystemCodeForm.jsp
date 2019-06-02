<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
  Ext.onReady(function() {
	v = new eoms.form.Validation({form:'TawSystemCodeForm'});
	
	viewer = new Ext.JsonView("view",
		'<div class="viewlistitem-subrole">{name}</div>'
	);
	var data = '${receiveUserId}';
	viewer.jsonData = eoms.JSONDecode(data==''?'[]':data);
	viewer.refresh();
	
	var treeAction = "${app}/piccunflow/pufTree.do?method=formDeptRole&type=safeservicenotes";
	tree = new xbox({
		btnId:'selTree',dlgId:'dlg',
		treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("上报接收人")}',treeChkMode:'single',treeChkType:'post,user',
		showChkFldId:'receiveUserName',saveChkFldId:'receiveUserId',returnJSON:true,viewer:viewer
	});
});
</script>

<html:form action="/codes.do?method=xsave" method="post"
	styleId="TawSystemCodeForm">

	<html:hidden property="id" />
	<table class="formTable middle" align="center">
		<tr>
			<td colspan="2" align="center" class="label">
				<bean:message key="tawSystemCodeForm.xinzengTitle" />
				<p align="left"><font color="#ff0000">${eoms:a2u('注：*为必填项')}</font></p>
			</td>
		</tr>
		
		<tr>
			<td class="label" nowrap="nowrap" align="right">
				<bean:message key='tawSystemCodeForm.mingcheng' />
			</td>
			<td>
				<html:text property="name" styleId="name" styleClass="text medium"
				 alt="allowBlank:false"/><font color="#ff0000">*</font>
		</td>
		</tr>
		<tr>
			<td class="label" nowrap="nowrap" align="right">
				<bean:message key='tawSystemCodeForm.bianma' />
			</td>
			<td>
				<html:text property="code" styleId="code" styleClass="text medium"
				 alt="allowBlank:false"/><font color="#ff0000">*</font>
			</td>
		</tr>
		
		

		<tr>
			<td colspan="2" align="center">
				<html:submit styleClass="button" property="method.save">
					<fmt:message key="button.save" />
				</html:submit>
				&nbsp;&nbsp;
				<html:reset styleClass="button" onclick="bCancel=true">
					<bean:message key="tawSystemCodeForm.fuwei" />
				</html:reset>
			</td>
		</tr>
	</table>


</html:form>
<%@ include file="/common/footer_eoms.jsp"%>