<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'mmsreportForm'});
});
</script>

<html:form action="/mmsreports.do?method=save" styleId="mmsreportForm" method="post"> 

<fmt:bundle basename="config/applicationResources-mms">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="mmsreport.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="mmsreport.mmsReportName" />
		</td>
		<td class="content">
			<html:text property="mmsReportName" styleId="mmsReportName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${mmsreportForm.mmsReportName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="mmsreport.mmsreport_template_id" />
		</td>
		<td class="content">
			<html:text property="mmsreport_template_id" styleId="mmsreport_template_id"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${mmsreportForm.mmsreport_template_id}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="mmsreport.statreport_id" />
		</td>
		<td class="content">
			<html:text property="statreport_id" styleId="statreport_id"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${mmsreportForm.statreport_id}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="mmsreport.mmsReportCreateDate" />
		</td>
		<td class="content">
			<html:text property="mmsReportCreateDate" styleId="mmsReportCreateDate"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${mmsreportForm.mmsReportCreateDate}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty mmsreportForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/mmsreport/mmsreports.do?method=remove&id=${mmsreportForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${mmsreportForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>