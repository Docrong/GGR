<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'knowledgeChangesStatisticForm'});
});
</script>

<html:form action="/knowledgeChangesStatistics.do?method=save" styleId="knowledgeChangesStatisticForm" method="post"> 

<fmt:bundle basename="config/applicationResource-knowledgechangesstatistic">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="knowledgeChangesStatistic.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="knowledgeChangesStatistic.baseName" />
		</td>
		<td class="content">
			<html:text property="baseName" styleId="baseName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${knowledgeChangesStatisticForm.baseName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="knowledgeChangesStatistic.userName" />
		</td>
		<td class="content">
			<html:text property="userName" styleId="userName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${knowledgeChangesStatisticForm.userName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="knowledgeChangesStatistic.addCount" />
		</td>
		<td class="content">
			<html:text property="addCount" styleId="addCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${knowledgeChangesStatisticForm.addCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="knowledgeChangesStatistic.modifyCount" />
		</td>
		<td class="content">
			<html:text property="modifyCount" styleId="modifyCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${knowledgeChangesStatisticForm.modifyCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="knowledgeChangesStatistic.deleteCount" />
		</td>
		<td class="content">
			<html:text property="deleteCount" styleId="deleteCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${knowledgeChangesStatisticForm.deleteCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="knowledgeChangesStatistic.overCount" />
		</td>
		<td class="content">
			<html:text property="overCount" styleId="overCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${knowledgeChangesStatisticForm.overCount}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty knowledgeChangesStatisticForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/knowledgeChangesStatistic/knowledgeChangesStatistics.do?method=remove&id=${knowledgeChangesStatisticForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${knowledgeChangesStatisticForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>