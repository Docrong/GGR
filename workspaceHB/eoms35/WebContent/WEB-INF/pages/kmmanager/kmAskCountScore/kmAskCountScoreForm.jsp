<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAskCountScoreForm'});
});
</script>

<html:form action="/kmAskCountScores.do?method=save" styleId="kmAskCountScoreForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmaskcountscore">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmAskCountScore.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmAskCountScore.userName" />
		</td>
		<td class="content">
			<html:text property="userName" styleId="userName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCountScoreForm.userName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskCountScore.userDept" />
		</td>
		<td class="content">
			<html:text property="userDept" styleId="userDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCountScoreForm.userDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskCountScore.countScore" />
		</td>
		<td class="content">
			<html:text property="countScore" styleId="countScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCountScoreForm.countScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskCountScore.role" />
		</td>
		<td class="content">
			<html:text property="role" styleId="role"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAskCountScoreForm.role}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmAskCountScoreForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmAskCountScore/kmAskCountScores.do?method=remove&id=${kmAskCountScoreForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAskCountScoreForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>