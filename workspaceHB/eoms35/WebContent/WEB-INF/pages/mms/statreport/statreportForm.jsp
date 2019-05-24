<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'statreportForm'});
});
</script>

<html:form action="/statreports.do?method=save" styleId="statreportForm" method="post"> 

<fmt:bundle basename="config/applicationResources-mms">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="statreport.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="statreport.mmsreport_template_id" />
		</td>
		<td class="content">
			<html:text property="mmsreport_template_id" styleId="mmsreport_template_id"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${statreportForm.mmsreport_template_id}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="statreport.ExcelID" />
		</td>
		<td class="content">
			<html:text property="excelID" styleId="excelID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${statreportForm.excelID}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="statreport.picID" />
		</td>
		<td class="content">
			<html:text property="picID" styleId="picID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${statreportForm.picID}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="statreport.htmlID" />
		</td>
		<td class="content">
			<html:text property="htmlID" styleId="htmlID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${statreportForm.htmlID}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty statreportForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/statreport/statreports.do?method=remove&id=${statreportForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${statreportForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>