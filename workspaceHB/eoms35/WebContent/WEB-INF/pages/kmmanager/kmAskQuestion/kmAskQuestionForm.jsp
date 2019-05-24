<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAskQuestionForm'});
});
</script>

<html:form action="/kmAskQuestions.do?method=save" styleId="kmAskQuestionForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmAskQuestion.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.name" />
		</td>
		<td class="content">
			<html:text property="name" styleId="name"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.name}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.question" />
		</td>
		<td class="content">
			<html:text property="question" styleId="question"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.question}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.speciality" />
		</td>
		<td class="content">
			<html:text property="speciality" styleId="speciality"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.speciality}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.answerCount" />
		</td>
		<td class="content">
			<html:text property="answerCount" styleId="answerCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.answerCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.questionStatus" />
		</td>
		<td class="content">
			<html:text property="questionStatus" styleId="questionStatus"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.questionStatus}" />
		</td>
	</tr>


	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.score" />
		</td>
		<td class="content">
			<html:text property="score" styleId="score"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.score}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.questionSupply" />
		</td>
		<td class="content">
			<html:text property="questionSupply" styleId="questionSupply"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.questionSupply}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.userfulTime" />
		</td>
		<td class="content">
			<html:text property="userfulTime" styleId="userfulTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.userfulTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.isAddScore" />
		</td>
		<td class="content">
			<html:text property="isAddScore" styleId="isAddScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.isAddScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.isClosed" />
		</td>
		<td class="content">
			<html:text property="isClosed" styleId="isClosed"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.isClosed}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.isAnonymity" />
		</td>
		<td class="content">
			<html:text property="isAnonymity" styleId="isAnonymity"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.isAnonymity}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmAskQuestionForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmAskQuestion/kmAskQuestions.do?method=remove&id=${kmAskQuestionForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAskQuestionForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>