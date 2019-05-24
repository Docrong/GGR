<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmOperateDateLogForm'});
});
</script>

<html:form action="/kmOperateDateLogs.do?method=save" styleId="kmOperateDateLogForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmOperateDateLog.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.operateUser" />
		</td>
		<td class="content">
			<html:text property="operateUser" styleId="operateUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.operateUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.operateDept" />
		</td>
		<td class="content">
			<html:text property="operateDept" styleId="operateDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.operateDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.addCount" />
		</td>
		<td class="content">
			<html:text property="addCount" styleId="addCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.addCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.addScore" />
		</td>
		<td class="content">
			<html:text property="addScore" styleId="addScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.addScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.modifyCount" />
		</td>
		<td class="content">
			<html:text property="modifyCount" styleId="modifyCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.modifyCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.modifyScore" />
		</td>
		<td class="content">
			<html:text property="modifyScore" styleId="modifyScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.modifyScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.overCount" />
		</td>
		<td class="content">
			<html:text property="overCount" styleId="overCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.overCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.overScore" />
		</td>
		<td class="content">
			<html:text property="overScore" styleId="overScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.overScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.deleteCount" />
		</td>
		<td class="content">
			<html:text property="deleteCount" styleId="deleteCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.deleteCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.deleteScore" />
		</td>
		<td class="content">
			<html:text property="deleteScore" styleId="deleteScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.deleteScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.upCount" />
		</td>
		<td class="content">
			<html:text property="upCount" styleId="upCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.upCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.upScore" />
		</td>
		<td class="content">
			<html:text property="upScore" styleId="upScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.upScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.downCount" />
		</td>
		<td class="content">
			<html:text property="downCount" styleId="downCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.downCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.downScore" />
		</td>
		<td class="content">
			<html:text property="downScore" styleId="downScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.downScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.useCount" />
		</td>
		<td class="content">
			<html:text property="useCount" styleId="useCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.useCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.useScore" />
		</td>
		<td class="content">
			<html:text property="useScore" styleId="useScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.useScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.opinionCount" />
		</td>
		<td class="content">
			<html:text property="opinionCount" styleId="opinionCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.opinionCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.opinionScore" />
		</td>
		<td class="content">
			<html:text property="opinionScore" styleId="opinionScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.opinionScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.adviceCount" />
		</td>
		<td class="content">
			<html:text property="adviceCount" styleId="adviceCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.adviceCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.adviceScore" />
		</td>
		<td class="content">
			<html:text property="adviceScore" styleId="adviceScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.adviceScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.auditOkCount" />
		</td>
		<td class="content">
			<html:text property="auditOkCount" styleId="auditOkCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.auditOkCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.auditOkScore" />
		</td>
		<td class="content">
			<html:text property="auditOkScore" styleId="auditOkScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.auditOkScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.auditBackCount" />
		</td>
		<td class="content">
			<html:text property="auditBackCount" styleId="auditBackCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.auditBackCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateDateLog.auditBackScore" />
		</td>
		<td class="content">
			<html:text property="auditBackScore" styleId="auditBackScore"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateDateLogForm.auditBackScore}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmOperateDateLogForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmmanager/kmOperateDateLogs.do?method=remove&id=${kmOperateDateLogForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmOperateDateLogForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>