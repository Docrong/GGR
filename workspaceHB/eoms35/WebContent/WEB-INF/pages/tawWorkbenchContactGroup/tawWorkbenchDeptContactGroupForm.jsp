<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<title><fmt:message key="tawWorkbenchContactGroupDetail.title" />
</title>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawWorkbenchDeptContactGroupForm'});
});
</script>
<html:form action="saveTawWorkbenchDeptContactGroup?method=xsave"
	method="post" styleId="tawWorkbenchDeptContactGroupForm">
	<table>
		<tr>
			<td>
				<eoms:label styleClass="desc" key="tawWorkbenchContactGroupForm.groupName" />
			</td>
			<td>
				<html:text property="groupName" styleId="groupName"	styleClass="text medium" alt="allowBlank:false,vtext:'${eoms:a2u('请输入分组名称')}'"/>
			</td>
			<td>
				<html:checkbox styleId="radio" property="allow"><bean:message key="tawWorkbenchDeptContactGroupForm.allow"/></html:checkbox>
			</td>
		</tr>
				
		<tr>
			<td>
				<eoms:label styleClass="desc" key="tawWorkbenchContactGroupForm.remark" />
			</td>
			<td colspan="2">
				<html:textarea property="remark" styleId="remark" styleClass="text medium" rows="5" cols="17" />
			</td>
		</tr>
	</table>
		
		<html:hidden property="id" /> 
		<html:hidden property="groupId" />
		<html:hidden property="deptId"/>	
		
		<html:submit styleClass="button" property="method.save"	onclick="bCancel=false">
			<fmt:message key="button.save" />
		</html:submit>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
