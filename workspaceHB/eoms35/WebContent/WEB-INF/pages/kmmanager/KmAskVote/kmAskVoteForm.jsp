<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAskVoteForm'});
});
</script>

<html:form action="/kmAskVotes.do?method=save" styleId="kmAskVoteForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmaskvote">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmAskVote.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmAskVote.voteUser" />
		</td>
		<td class="content">
			<html:text property="voteUser" styleId="voteUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskVoteForm.voteUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskVote.voteDate" />
		</td>
		<td class="content">
			<html:text property="voteDate" styleId="voteDate"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskVoteForm.voteDate}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskVote.voteDept" />
		</td>
		<td class="content">
			<html:text property="voteDept" styleId="voteDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskVoteForm.voteDept}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmAskVoteForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmAskVote/kmAskVotes.do?method=remove&id=${kmAskVoteForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAskVoteForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>