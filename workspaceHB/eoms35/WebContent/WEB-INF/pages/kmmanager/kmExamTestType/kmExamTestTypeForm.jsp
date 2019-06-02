<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmExamTestTypeForm'});
});
</script>

<html:form action="/kmExamTestTypes.do?method=save" styleId="kmExamTestTypeForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmexamtesttype">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmExamTestType.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestType.testTypeId" />
		</td>
		<td class="content">
			<html:text property="testTypeId" styleId="testTypeId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm.testTypeId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestType.testID" />
		</td>
		<td class="content">
			<html:text property="testID" styleId="testID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm.testID}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestType.type" />
		</td>
		<td class="content">
			<html:text property="type" styleId="type"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm.type}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestType.description" />
		</td>
		<td class="content">
			<html:text property="description" styleId="description"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm.description}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestType.quantity" />
		</td>
		<td class="content">
			<html:text property="quantity" styleId="quantity"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm.quantity}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestType.score" />
		</td>
		<td class="content">
			<html:text property="score" styleId="score"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm.score}" />
		</td>
	</tr>


</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmExamTestTypeForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmExamTestType/kmExamTestTypes.do?method=remove&id=${kmExamTestTypeForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmExamTestTypeForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>