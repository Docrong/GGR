<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'personalContributeStatisticForm'});
});
</script>

<html:form action="/personalContributeStatistics.do?method=save" styleId="personalContributeStatisticForm" method="post"> 

<fmt:bundle basename="config/applicationResource-personalcontributestatistic">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="personalContributeStatistic.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="personalContributeStatistic.userName" />
		</td>
		<td class="content">
			<html:text property="userName" styleId="userName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalContributeStatisticForm.userName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalContributeStatistic.userDept" />
		</td>
		<td class="content">
			<html:text property="userDept" styleId="userDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalContributeStatisticForm.userDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalContributeStatistic.addCount" />
		</td>
		<td class="content">
			<html:text property="addCount" styleId="addCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalContributeStatisticForm.addCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalContributeStatistic.modifyCount" />
		</td>
		<td class="content">
			<html:text property="modifyCount" styleId="modifyCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalContributeStatisticForm.modifyCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalContributeStatistic.overCount" />
		</td>
		<td class="content">
			<html:text property="overCount" styleId="overCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalContributeStatisticForm.overCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalContributeStatistic.deleteCount" />
		</td>
		<td class="content">
			<html:text property="deleteCount" styleId="deleteCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalContributeStatisticForm.deleteCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="personalContributeStatistic.upCount" />
		</td>
		<td class="content">
			<html:text property="upCount" styleId="upCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${personalContributeStatisticForm.upCount}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty personalContributeStatisticForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/personalContributeStatistic/personalContributeStatistics.do?method=remove&id=${personalContributeStatisticForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${personalContributeStatisticForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>