<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'personalAuditStatisticForm'});
});
</script>

<html:form action="/personalAuditStatistics.do?method=save" styleId="personalAuditStatisticForm" method="post"> 

<fmt:bundle basename="config/applicationResource-personalauditstatistic">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="personalAuditStatistic.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="personalAuditStatistic.userName" />
		</td>
		<td class="content">
			<html:text property="userName" styleId="userName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalAuditStatisticForm.userName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalAuditStatistic.userDept" />
		</td>
		<td class="content">
			<html:text property="userDept" styleId="userDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalAuditStatisticForm.userDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalAuditStatistic.auditCount" />
		</td>
		<td class="content">
			<html:text property="auditCount" styleId="auditCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalAuditStatisticForm.auditCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalAuditStatistic.auditOkCount" />
		</td>
		<td class="content">
			<html:text property="auditOkCount" styleId="auditOkCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalAuditStatisticForm.auditOkCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalAuditStatistic.auditBackCount" />
		</td>
		<td class="content">
			<html:text property="auditBackCount" styleId="auditBackCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalAuditStatisticForm.auditBackCount}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty personalAuditStatisticForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/personalAuditStatistic/personalAuditStatistics.do?method=remove&id=${personalAuditStatisticForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${personalAuditStatisticForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>