<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmExamAttendForm'});
});
</script>

<html:form action="/kmExamAttends.do?method=save" styleId="kmExamAttendForm" method="post"> 

<fmt:bundle basename="config/applicationResource-kmexamattend">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmExamAttend.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAttend.testId" />
		</td>
		<td class="content">
			<html:text property="testId" styleId="testId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAttendForm.testId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAttend.attendUser" />
		</td>
		<td class="content">
			<html:text property="attendUser" styleId="attendUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAttendForm.attendUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAttend.attendDept" />
		</td>
		<td class="content">
			<html:text property="attendDept" styleId="attendDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAttendForm.attendDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAttend.attendTime" />
		</td>
		<td class="content">
			<html:text property="attendTime" styleId="attendTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAttendForm.attendTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAttend.attendOverTime" />
		</td>
		<td class="content">
			<html:text property="attendOverTime" styleId="attendOverTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAttendForm.attendOverTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAttend.score" />
		</td>
		<td class="content">
			<html:text property="score" styleId="score"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAttendForm.score}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExamAttend.isRead" />
		</td>
		<td class="content">
			<html:text property="isRead" styleId="isRead"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamAttendForm.isRead}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmExamAttendForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmExamAttend/kmExamAttends.do?method=remove&id=${kmExamAttendForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmExamAttendForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>