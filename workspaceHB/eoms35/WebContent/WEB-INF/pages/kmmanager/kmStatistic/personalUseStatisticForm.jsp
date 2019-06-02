<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'personalUseStatisticForm'});
});
</script>

<html:form action="/personalUseStatistics.do?method=save" styleId="personalUseStatisticForm" method="post"> 

<fmt:bundle basename="config/applicationResource-personalusestatistic">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="personalUseStatistic.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="personalUseStatistic.userName" />
		</td>
		<td class="content">
			<html:text property="userName" styleId="userName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalUseStatisticForm.userName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalUseStatistic.userDept" />
		</td>
		<td class="content">
			<html:text property="userDept" styleId="userDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalUseStatisticForm.userDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalUseStatistic.useCount" />
		</td>
		<td class="content">
			<html:text property="useCount" styleId="useCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalUseStatisticForm.useCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalUseStatistic.adviceCount" />
		</td>
		<td class="content">
			<html:text property="adviceCount" styleId="adviceCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalUseStatisticForm.adviceCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalUseStatistic.opinionCount" />
		</td>
		<td class="content">
			<html:text property="opinionCount" styleId="opinionCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalUseStatisticForm.opinionCount}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty personalUseStatisticForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/personalUseStatistic/personalUseStatistics.do?method=remove&id=${personalUseStatisticForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${personalUseStatisticForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>