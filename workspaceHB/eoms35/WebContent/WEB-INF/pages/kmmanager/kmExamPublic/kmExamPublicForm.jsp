<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmExamPublicForm'});
});
</script>

<html:form action="/kmExamPublics.do?method=save" styleId="kmExamPublicForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmexampublic">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmExamPublic.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmExamPublic.testId" />
		</td>
		<td class="content">
			<html:text property="testId" styleId="testId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamPublicForm.testId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamPublic.createUser" />
		</td>
		<td class="content">
			<html:text property="createUser" styleId="createUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamPublicForm.createUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamPublic.createDept" />
		</td>
		<td class="content">
			<html:text property="createDept" styleId="createDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamPublicForm.createDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamPublic.createTime" />
		</td>
		<td class="content">
			<html:text property="createTime" styleId="createTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamPublicForm.createTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamPublic.description" />
		</td>
		<td class="content">
			<html:text property="description" styleId="description"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamPublicForm.description}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmExamPublicForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmExamPublic/kmExamPublics.do?method=remove&id=${kmExamPublicForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmExamPublicForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>