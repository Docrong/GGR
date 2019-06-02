<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAskReplyForm'});
});
</script>

<html:form action="/kmAskReplys.do?method=save" styleId="kmAskReplyForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmaskreply">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmAskReply.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.questionId" />
		</td>
		<td class="content">
			<html:text property="questionId" styleId="questionId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.questionId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.answer" />
		</td>
		<td class="content">
			<html:text property="answer" styleId="answer"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.answer}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.conment" />
		</td>
		<td class="content">
			<html:text property="conment" styleId="conment"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.conment}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.isBest" />
		</td>
		<td class="content">
			<html:text property="isBest" styleId="isBest"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.isBest}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.answerUser" />
		</td>
		<td class="content">
			<html:text property="answerUser" styleId="answerUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.answerUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.answerDept" />
		</td>
		<td class="content">
			<html:text property="answerDept" styleId="answerDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.answerDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.answerTime" />
		</td>
		<td class="content">
			<html:text property="answerTime" styleId="answerTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.answerTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.fileName" />
		</td>
		<td class="content">
			<html:text property="fileName" styleId="fileName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.fileName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskReply.isAnonymity" />
		</td>
		<td class="content">
			<html:text property="isAnonymity" styleId="isAnonymity"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskReplyForm.isAnonymity}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmAskReplyForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmAskReply/kmAskReplys.do?method=remove&id=${kmAskReplyForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAskReplyForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>