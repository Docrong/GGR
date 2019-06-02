<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmOperateLogForm'});
});
</script>

<html:form action="/kmOperateLogs.do?method=save" styleId="kmOperateLogForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmOperateLog.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateLog.themeId" />
		</td>
		<td class="content">
			<html:text property="themeId" styleId="themeId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateLogForm.themeId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateLog.tableId" />
		</td>
		<td class="content">
			<html:text property="tableId" styleId="tableId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateLogForm.tableId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateLog.contentId" />
		</td>
		<td class="content">
			<html:text property="contentId" styleId="contentId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateLogForm.contentId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateLog.operateUser" />
		</td>
		<td class="content">
			<html:text property="operateUser" styleId="operateUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateLogForm.operateUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateLog.operateDept" />
		</td>
		<td class="content">
			<html:text property="operateDept" styleId="operateDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateLogForm.operateDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateLog.operateTime" />
		</td>
		<td class="content">
			<html:text property="operateTime" styleId="operateTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateLogForm.operateTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateLog.score" />
		</td>
		<td class="content">
			<html:text property="score" styleId="score"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateLogForm.score}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateLog.toUser" />
		</td>
		<td class="content">
			<html:text property="toUser" styleId="toUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateLogForm.toUser}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmOperateLogForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmOperateLog/kmOperateLogs.do?method=remove&id=${kmOperateLogForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmOperateLogForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>