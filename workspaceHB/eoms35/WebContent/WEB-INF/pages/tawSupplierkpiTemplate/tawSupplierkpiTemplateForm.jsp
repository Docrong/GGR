<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'tawSupplierkpiTemplateForm'});
})
</script>

<%
String dictId = request.getAttribute("dictId").toString(); 
String flag = request.getAttribute("flag").toString();
%>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
<div class="list-title">
	<fmt:message key="tawSupplierkpiTemplateDetail.heading"/>
</div>

<html:form action="saveTawSupplierkpiTemplate" method="post" styleId="tawSupplierkpiTemplateForm"> 

<table>
	<tr height="40">
		<td width="80">
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.specialType"/>
		</td>
		<td colspan="2">
			<html:text property="specialType" styleId="specialType" styleClass="text medium" 
			  alt="allowBlank:false,vtext:'${eoms:a2u('请输入名称')}'" />
		</td>
	</tr>
	<tr height="30">
		<td colspan="2">
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>

<html:hidden property="dictId" value="<%=dictId%>" />

<html:hidden property="flag" value="<%=flag%>" />

</html:form>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>