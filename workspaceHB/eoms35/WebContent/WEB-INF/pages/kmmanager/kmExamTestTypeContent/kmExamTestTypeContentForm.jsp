<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmExamTestTypeContentForm'});
});
</script>

<html:form action="/kmExamTestTypeContents.do?method=save" styleId="kmExamTestTypeContentForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmexamtesttypecontent">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmExamTestTypeContent.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestTypeContent.typeContentID" />
		</td>
		<td class="content">
			<html:text property="typeContentID" styleId="typeContentID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeContentForm.typeContentID}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestTypeContent.testTypeID" />
		</td>
		<td class="content">
			<html:text property="testTypeID" styleId="testTypeID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeContentForm.testTypeID}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestTypeContent.score" />
		</td>
		<td class="content">
			<html:text property="score" styleId="score"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeContentForm.score}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamTestTypeContent.questionID" />
		</td>
		<td class="content">
			<html:text property="questionID" styleId="questionID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestTypeContentForm.questionID}" />
		</td>
	</tr>


</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmExamTestTypeContentForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmExamTestTypeContent/kmExamTestTypeContents.do?method=remove&id=${kmExamTestTypeContentForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmExamTestTypeContentForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>