<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmTableDictForm'});
});
</script>

<html:form action="/kmTableDicts.do?method=save" styleId="kmTableDictForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmTableDict.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmTableDict.id" />
		</td>
		<td class="content">
			<html:text property="id" styleId="id"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableDictForm.id}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmTableDict.dictName" />
		</td>
		<td class="content">
			<html:text property="dictName" styleId="dictName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableDictForm.dictName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmTableDict.parentId" />
		</td>
		<td class="content">
			<html:text property="parentId" styleId="parentId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableDictForm.parentId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmTableDict.isDeleted" />
		</td>
		<td class="content">
			<html:text property="isDeleted" styleId="isDeleted"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableDictForm.isDeleted}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmTableDictForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmTableDict/kmTableDicts.do?method=remove&id=${kmTableDictForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmTableDictForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>