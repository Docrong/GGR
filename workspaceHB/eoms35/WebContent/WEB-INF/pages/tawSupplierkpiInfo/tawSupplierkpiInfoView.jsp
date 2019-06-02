<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    //init Form validation and styles
	//valider({form:'tawSupplierkpiInfoForm',vbtn:'method.save'});
})
</script>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
<div class="list-title">
	<B><bean:write name="tawSupplierkpiInfoForm" property="supplierName" scope="request"/></B>${eoms:a2u(' 详细信息')}
</div>
</fmt:bundle>
<html:form action="saveTawSupplierkpiInfo" method="post" styleId="tawSupplierkpiInfoForm"> 

<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
	<tr height="30">
		<td width="30%">
			<eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierName"/>
		</td>
		<td width="70%">
			<bean:write name="tawSupplierkpiInfoForm" property="supplierName" scope="request"/>
		</td>
	</tr>
	<tr height="30">
		<td>
			<eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierLinkman"/>
		</td>
		<td>
			<bean:write name="tawSupplierkpiInfoForm" property="supplierLinkman" scope="request"/>
		</td>
	</tr>
	<tr height="30">
		<td>
			<eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierContact"/>
		</td>
		<td>
			<bean:write name="tawSupplierkpiInfoForm" property="supplierContact" scope="request"/>
		</td>
	</tr>
</table>
</div>
<html:hidden property="id" />

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>