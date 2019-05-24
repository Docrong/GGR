<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAskCommentForm'});
});
</script>

<html:form action="/kmAskComments.do?method=save" styleId="kmAskCommentForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmAskComment.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmAskComment.commentUser" />
		</td>
		<td class="content">
			<html:text property="commentUser" styleId="commentUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCommentForm.commentUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskComment.commentDate" />
		</td>
		<td class="content">
			<html:text property="commentDate" styleId="commentDate"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCommentForm.commentDate}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskComment.commentDept" />
		</td>
		<td class="content">
			<html:text property="commentDept" styleId="commentDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCommentForm.commentDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskComment.comment" />
		</td>
		<td class="content">
			<html:text property="comment" styleId="comment"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCommentForm.comment}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskComment.questionId" />
		</td>
		<td class="content">
			<html:text property="questionId" styleId="questionId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCommentForm.questionId}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmAskCommentForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmAskComment/kmAskComments.do?method=remove&id=${kmAskCommentForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAskCommentForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>