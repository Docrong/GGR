<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmContentsDictForm'});
});
</script>

<html:form action="/kmContentsDicts.do?method=save" styleId="kmContentsDictForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmContentsDict.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsDict.contentId" />
		</td>
		<td class="content">
			<html:text property="contentId" styleId="contentId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsDictForm.contentId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsDict.dictId" />
		</td>
		<td class="content">
			<html:text property="dictId" styleId="dictId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsDictForm.dictId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsDict.dictType" />
		</td>
		<td class="content">
			<html:text property="dictType" styleId="dictType"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmContentsDictForm.dictType}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmContentsDictForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmContentsDict/kmContentsDicts.do?method=remove&id=${kmContentsDictForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmContentsDictForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>