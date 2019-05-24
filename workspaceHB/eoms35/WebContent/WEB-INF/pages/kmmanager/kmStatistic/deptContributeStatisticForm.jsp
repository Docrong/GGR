<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'deptContributeStatisticForm'});
});
</script>

<html:form action="/deptContributeStatistics.do?method=save" styleId="deptContributeStatisticForm" method="post"> 

<fmt:bundle basename="config/applicationResource-deptcontributestatistic">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="deptContributeStatistic.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="deptContributeStatistic.deptName" />
		</td>
		<td class="content">
			<html:text property="deptName" styleId="deptName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${deptContributeStatisticForm.deptName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="deptContributeStatistic.addCount" />
		</td>
		<td class="content">
			<html:text property="addCount" styleId="addCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${deptContributeStatisticForm.addCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="deptContributeStatistic.auditOkPercentage" />
		</td>
		<td class="content">
			<html:text property="auditOkPercentage" styleId="auditOkPercentage"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${deptContributeStatisticForm.auditOkPercentage}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="deptContributeStatistic.utilizationRate" />
		</td>
		<td class="content">
			<html:text property="utilizationRate" styleId="utilizationRate"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${deptContributeStatisticForm.utilizationRate}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="deptContributeStatistic.useCount" />
		</td>
		<td class="content">
			<html:text property="useCount" styleId="useCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${deptContributeStatisticForm.useCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="deptContributeStatistic.upCount" />
		</td>
		<td class="content">
			<html:text property="upCount" styleId="upCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${deptContributeStatisticForm.upCount}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty deptContributeStatisticForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/deptContributeStatistic/deptContributeStatistics.do?method=remove&id=${deptContributeStatisticForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${deptContributeStatisticForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>