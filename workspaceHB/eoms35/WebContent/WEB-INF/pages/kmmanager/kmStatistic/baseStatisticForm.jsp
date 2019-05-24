<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'baseStatisticForm'});
});
</script>

<html:form action="/baseStatistics.do?method=save" styleId="baseStatisticForm" method="post"> 

<fmt:bundle basename="config/applicationResource-basestatistic">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="baseStatistic.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.baseName" />
		</td>
		<td class="content">
			<html:text property="baseName" styleId="baseName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.baseName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.userName" />
		</td>
		<td class="content">
			<html:text property="userName" styleId="userName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.userName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.availableCount" />
		</td>
		<td class="content">
			<html:text property="availableCount" styleId="availableCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.availableCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.invalidCount" />
		</td>
		<td class="content">
			<html:text property="invalidCount" styleId="invalidCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.invalidCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.deleteCount" />
		</td>
		<td class="content">
			<html:text property="deleteCount" styleId="deleteCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.deleteCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.utilizationCount" />
		</td>
		<td class="content">
			<html:text property="utilizationCount" styleId="utilizationCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.utilizationCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.utilizationRate" />
		</td>
		<td class="content">
			<html:text property="utilizationRate" styleId="utilizationRate"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.utilizationRate}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.readCount" />
		</td>
		<td class="content">
			<html:text property="readCount" styleId="readCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.readCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.usedCount" />
		</td>
		<td class="content">
			<html:text property="usedCount" styleId="usedCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.usedCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.usedRate" />
		</td>
		<td class="content">
			<html:text property="usedRate" styleId="usedRate"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.usedRate}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="baseStatistic.recommendCount" />
		</td>
		<td class="content">
			<html:text property="recommendCount" styleId="recommendCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${baseStatisticForm.recommendCount}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty baseStatisticForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/baseStatistic/baseStatistics.do?method=remove&id=${baseStatisticForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${baseStatisticForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>