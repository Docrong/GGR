<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmExamAnswersForm'});
});
</script>

<html:form action="/kmExamAnswerss.do?method=save" styleId="kmExamAnswersForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmexamanswers">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmExamAnswers.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAnswers.questionId" />
		</td>
		<td class="content">
			<html:text property="questionId" styleId="questionId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAnswersForm.questionId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAnswers.answer" />
		</td>
		<td class="content">
			<html:text property="answer" styleId="answer"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAnswersForm.answer}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAnswers.isRight" />
		</td>
		<td class="content">
			<html:text property="isRight" styleId="isRight"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAnswersForm.isRight}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAnswers.score" />
		</td>
		<td class="content">
			<html:text property="score" styleId="score"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAnswersForm.score}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAnswers.attendId" />
		</td>
		<td class="content">
			<html:text property="attendId" styleId="attendId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAnswersForm.attendId}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmExamAnswersForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmExamAnswers/kmExamAnswerss.do?method=remove&id=${kmExamAnswersForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmExamAnswersForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>