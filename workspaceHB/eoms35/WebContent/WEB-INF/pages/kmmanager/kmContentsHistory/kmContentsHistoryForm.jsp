<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmContentsHistoryForm'});
});
</script>

<html:form action="/kmContentsHistorys.do?method=save" styleId="kmContentsHistoryForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmContentsHistory.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.themeId" />
		</td>
		<td class="content">
			<html:text property="themeId" styleId="themeId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.themeId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.tableId" />
		</td>
		<td class="content">
			<html:text property="tableId" styleId="tableId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.tableId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.createUser" />
		</td>
		<td class="content">
			<html:text property="createUser" styleId="createUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.createUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.createDept" />
		</td>
		<td class="content">
			<html:text property="createDept" styleId="createDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.createDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.createTime" />
		</td>
		<td class="content">
			<html:text property="createTime" styleId="createTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.createTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.modifyUser" />
		</td>
		<td class="content">
			<html:text property="modifyUser" styleId="modifyUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.modifyUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.modifyDept" />
		</td>
		<td class="content">
			<html:text property="modifyDept" styleId="modifyDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.modifyDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.modifyTime" />
		</td>
		<td class="content">
			<html:text property="modifyTime" styleId="modifyTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.modifyTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.contentTitle" />
		</td>
		<td class="content">
			<html:text property="contentTitle" styleId="contentTitle"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.contentTitle}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.contentKeys" />
		</td>
		<td class="content">
			<html:text property="contentKeys" styleId="contentKeys"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.contentKeys}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.contentStatus" />
		</td>
		<td class="content">
			<html:text property="contentStatus" styleId="contentStatus"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.contentStatus}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.auditFlag" />
		</td>
		<td class="content">
			<html:text property="auditFlag" styleId="auditFlag"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.auditFlag}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.rolestrFlag" />
		</td>
		<td class="content">
			<html:text property="rolestrFlag" styleId="rolestrFlag"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.rolestrFlag}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.levelFlag" />
		</td>
		<td class="content">
			<html:text property="levelFlag" styleId="levelFlag"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.levelFlag}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.isBest" />
		</td>
		<td class="content">
			<html:text property="isBest" styleId="isBest"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.isBest}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.isPublic" />
		</td>
		<td class="content">
			<html:text property="isPublic" styleId="isPublic"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.isPublic}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.gradeOne" />
		</td>
		<td class="content">
			<html:text property="gradeOne" styleId="gradeOne"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.gradeOne}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.gradeTwo" />
		</td>
		<td class="content">
			<html:text property="gradeTwo" styleId="gradeTwo"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.gradeTwo}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.gradeThree" />
		</td>
		<td class="content">
			<html:text property="gradeThree" styleId="gradeThree"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.gradeThree}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.gradeFour" />
		</td>
		<td class="content">
			<html:text property="gradeFour" styleId="gradeFour"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.gradeFour}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.gradeFive" />
		</td>
		<td class="content">
			<html:text property="gradeFive" styleId="gradeFive"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.gradeFive}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.readCount" />
		</td>
		<td class="content">
			<html:text property="readCount" styleId="readCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.readCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.useCount" />
		</td>
		<td class="content">
			<html:text property="useCount" styleId="useCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.useCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.contentXml" />
		</td>
		<td class="content">
			<html:text property="contentXml" styleId="contentXml"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.contentXml}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.modifyCount" />
		</td>
		<td class="content">
			<html:text property="modifyCount" styleId="modifyCount"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.modifyCount}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsHistory.version" />
		</td>
		<td class="content">
			<html:text property="version" styleId="version"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsHistoryForm.version}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmContentsHistoryForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmContentsHistory/kmContentsHistorys.do?method=remove&id=${kmContentsHistoryForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmContentsHistoryForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>