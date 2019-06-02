<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'personalStatisticForm'});
});
</script>

<html:form action="/personalStatistics.do?method=save" styleId="personalStatisticForm" method="post"> 

<fmt:bundle basename="config/applicationResource-personalstatistic">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="personalStatistic.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.userName" />
		</td>
		<td class="content">
			<html:text property="userName" styleId="userName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.userName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.userDept" />
		</td>
		<td class="content">
			<html:text property="userDept" styleId="userDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.userDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.addCount" />
		</td>
		<td class="content">
			<html:text property="addCount" styleId="addCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.addCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.modifyCount" />
		</td>
		<td class="content">
			<html:text property="modifyCount" styleId="modifyCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.modifyCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.overCount" />
		</td>
		<td class="content">
			<html:text property="overCount" styleId="overCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.overCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.deleteCount" />
		</td>
		<td class="content">
			<html:text property="deleteCount" styleId="deleteCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.deleteCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.utilizationCount" />
		</td>
		<td class="content">
			<html:text property="utilizationCount" styleId="utilizationCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.utilizationCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.usedCount" />
		</td>
		<td class="content">
			<html:text property="usedCount" styleId="usedCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.usedCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.upCount" />
		</td>
		<td class="content">
			<html:text property="upCount" styleId="upCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.upCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalStatistic.downCount" />
		</td>
		<td class="content">
			<html:text property="downCount" styleId="downCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalStatisticForm.downCount}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty personalStatisticForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/personalStatistic/personalStatistics.do?method=remove&id=${personalStatisticForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${personalStatisticForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>