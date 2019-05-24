<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmExamChoiceForm'});
});
</script>

<html:form action="/kmExamChoices.do?method=save" styleId="kmExamChoiceForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmexamchoice">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmExamChoice.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmExamChoice.choiceID" />
		</td>
		<td class="content">
			<html:text property="choiceID" styleId="choiceID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamChoiceForm.choiceID}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamChoice.questionsID" />
		</td>
		<td class="content">
			<html:text property="questionsID" styleId="questionsID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamChoiceForm.questionsID}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamChoice.content" />
		</td>
		<td class="content">
			<html:text property="content" styleId="content"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamChoiceForm.content}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamChoice.orderBy" />
		</td>
		<td class="content">
			<html:text property="orderBy" styleId="orderBy"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamChoiceForm.orderBy}" />
		</td>
	</tr>


</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmExamChoiceForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmExamChoice/kmExamChoices.do?method=remove&id=${kmExamChoiceForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmExamChoiceForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>