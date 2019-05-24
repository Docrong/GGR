<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainSpecialtyForm'});
});
</script>

<html:form action="/trainSpecialtys.do?method=save" styleId="trainSpecialtyForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="trainSpecialty.form.heading"/></div>
	</caption>

	<tr>
		<td  class="label">
			<fmt:message key="trainSpecialty.specialtyName" />
		</td>
		<td class="content">
			<html:text property="specialtyName" styleId="specialtyName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'',maxLength:32" value="${trainSpecialtyForm.specialtyName}" />
		</td>
	</tr>
</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty trainSpecialtyForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/trainSpecialty/trainSpecialtys.do?method=remove&nodeId=${trainSpecialtyForm.nodeId}';
						location.href=url}"
						/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${trainSpecialtyForm.id}" />
<html:hidden property="isDelete" value="0" />
<html:hidden property="nodeId" value="${trainSpecialtyForm.nodeId}" />
<html:hidden property="parentNodeId" value="${trainSpecialtyForm.parentNodeId}" />
<html:hidden property="leaf" value="${trainSpecialtyForm.leaf}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>