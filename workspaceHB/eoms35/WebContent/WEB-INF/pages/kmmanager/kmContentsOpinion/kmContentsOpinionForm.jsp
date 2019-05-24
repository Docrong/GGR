<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmContentsOpinionForm'});
});
</script>

<html:form action="/kmContentsOpinions.do?method=save" styleId="kmContentsOpinionForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmContentsOpinion.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.contentId" />
		</td>
		<td class="content">
			<html:text property="contentId" styleId="contentId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.contentId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.createUser" />
		</td>
		<td class="content">
			<html:text property="createUser" styleId="createUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.createUser}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.createDept" />
		</td>
		<td class="content">
			<html:text property="createDept" styleId="createDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.createDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.createTime" />
		</td>
		<td class="content">
			<html:text property="createTime" styleId="createTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.createTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.opinionContent" />
		</td>
		<td class="content">
			<html:text property="opinionContent" styleId="opinionContent"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.opinionContent}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.opinionGrade" />
		</td>
		<td class="content">
			<html:text property="opinionGrade" styleId="opinionGrade"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.opinionGrade}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.isEdit" />
		</td>
		<td class="content">
			<html:text property="isEdit" styleId="isEdit"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.isEdit}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.isRefedit" />
		</td>
		<td class="content">
			<html:text property="isRefedit" styleId="isRefedit"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.isRefedit}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.isDeleted" />
		</td>
		<td class="content">
			<html:text property="isDeleted" styleId="isDeleted"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsOpinionForm.isDeleted}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmContentsOpinionForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmContentsOpinion/kmContentsOpinions.do?method=remove&id=${kmContentsOpinionForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmContentsOpinionForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>