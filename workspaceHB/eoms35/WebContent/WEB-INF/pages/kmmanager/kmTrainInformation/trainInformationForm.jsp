<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainInformationForm'});
});
</script>

<html:form action="/trainInformations.do?method=save" styleId="trainInformationForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="trainInformation.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainName" />
		</td>
		<td class="content">
			<html:text property="trainName" styleId="trainName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainGrade" />
		</td>
		<td class="content">
			<html:text property="trainGrade" styleId="trainGrade"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainGrade}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainAddress" />
		</td>
		<td class="content">
			<html:text property="trainAddress" styleId="trainAddress"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainAddress}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainTime" />
		</td>
		<td class="content">
			<html:text property="trainTime" styleId="trainTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainBeginTime" />
		</td>
		<td class="content">
			<html:text property="trainBeginTime" styleId="trainBeginTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainBeginTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainEndTime" />
		</td>
		<td class="content">
			<html:text property="trainEndTime" styleId="trainEndTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainEndTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainDept" />
		</td>
		<td class="content">
			<html:text property="trainDept" styleId="trainDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainDocument" />
		</td>
		<td class="content">
			<html:text property="trainDocument" styleId="trainDocument"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainDocument}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainVender" />
		</td>
		<td class="content">
			<html:text property="trainVender" styleId="trainVender"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainVender}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainSpeciality" />
		</td>
		<td class="content">
			<html:text property="trainSpeciality" styleId="trainSpeciality"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainSpeciality}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainInformation.trainType" />
		</td>
		<td class="content">
			<html:text property="trainType" styleId="trainType"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trainInformationForm.trainType}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty trainInformationForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/trainInformation/trainInformations.do?method=remove&id=${trainInformationForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${trainInformationForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>