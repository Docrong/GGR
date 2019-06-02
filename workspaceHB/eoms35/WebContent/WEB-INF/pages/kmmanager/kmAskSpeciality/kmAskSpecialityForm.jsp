<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAskSpecialityForm'});
});
</script>

<html:form action="/kmAskSpecialitys.do?method=save" styleId="kmAskSpecialityForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmAskSpeciality.form.heading"/></div>
	</caption>

	<tr>
		<td  class="label">
			<fmt:message key="kmAskSpeciality.name" />
		</td>
		<td class="content">
			<html:text property="name" styleId="name"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'',maxLength:50" value="${kmAskSpecialityForm.name}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmAskSpecialityForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmmanager/kmAskSpecialitys.do?method=remove&nodeId=${kmAskSpecialityForm.nodeId}';
						location.href=url}"
						/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAskSpecialityForm.id}" />
<html:hidden property="nodeId" value="${kmAskSpecialityForm.nodeId}" />
<html:hidden property="parentNodeId" value="${kmAskSpecialityForm.parentNodeId}" />
<html:hidden property="leaf" value="${kmAskSpecialityForm.leaf}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>