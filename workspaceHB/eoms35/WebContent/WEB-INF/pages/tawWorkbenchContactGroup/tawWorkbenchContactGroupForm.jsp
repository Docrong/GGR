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
<html:form action="saveTawWorkbenchContactGroup?method=xsave"
	method="post" styleId="tawWorkbenchContactGroupForm">
	<ul>

		<li>

			<eoms:label styleClass="desc"
				key="tawWorkbenchContactGroupForm.groupName" />

			<html:text property="groupName" styleId="groupName"
				styleClass="text medium" alt="allowBlank:false,vtext:'${eoms:a2u('请输入分组名称')}'"/>
		</li>
		<html:hidden property="id" />
		<html:hidden property="groupId" />
		<li>

			<eoms:label styleClass="desc"
				key="tawWorkbenchContactGroupForm.remark" />

	<html:textarea property="remark" styleId="remark"
						styleClass="text medium" rows="5" cols="17" />
			 
		</li>
		<li>
		<li class="buttonBar bottom">
			<html:submit styleClass="button" property="method.save"
				onclick="bCancel=false">
				<fmt:message key="button.save" />
			</html:submit>
			 
		</li>
	</ul>
	 

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
