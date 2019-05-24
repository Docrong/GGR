<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAuditerStepForm'});
});
</script>

<html:form action="/kmAuditerSteps.do?method=save" styleId="kmAuditerStepForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmAuditerStep.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmAuditerStep.operateTime" />
		</td>
		<td class="content">
			<html:text property="operateTime" styleId="operateTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAuditerStepForm.operateTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAuditerStep.operateType" />
		</td>
		<td class="content">
			<html:text property="operateType" styleId="operateType"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAuditerStepForm.operateType}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAuditerStep.operateId" />
		</td>
		<td class="content">
			<html:text property="operateId" styleId="operateId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAuditerStepForm.operateId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAuditerStep.auditResult" />
		</td>
		<td class="content">
			<html:text property="auditResult" styleId="auditResult"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAuditerStepForm.auditResult}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAuditerStep.remark" />
		</td>
		<td class="content">
			<html:text property="remark" styleId="remark"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmAuditerStepForm.remark}" />
		</td>
	</tr>


</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmAuditerStepForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmAuditerStep/kmAuditerSteps.do?method=remove&id=${kmAuditerStepForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAuditerStepForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>