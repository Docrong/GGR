<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String specialType = request.getAttribute("specialType").toString();
String serviceType = request.getAttribute("serviceType").toString();
%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    //init Form validation and styles
	//valider({form:'tawSupplierkpiInfoForm',vbtn:'method.save'});
})

function viewKPIs(id) {
  var specialType = document.forms[0].specialType.value;
  var serviceType = document.forms[0].serviceType.value;
  var url = "<c:url value="/supplierkpi/editTawSupplierkpiInfo.do?method=customSpecialKPIs&id=" />";
  url += id;
  url = url + "&specialType=" + specialType;
  url = url + "&serviceType=" + serviceType;
  location.href=url;
}
</script>
<div class="list-title">
	<eoms:id2nameDB id="<%=serviceType%>" beanId="tawSupplierkpiDictDao" />
	-
	<eoms:id2nameDB id="<%=specialType%>" beanId="tawSupplierkpiDictDao" />
	${eoms:a2u('   定制该专业评估指标的供应商列表')}
</div>
<html:form action="saveTawSupplierkpiInfo" method="post" styleId="tawSupplierkpiInfoForm"> 
<html:hidden property="specialType" value="<%=specialType%>" />
<html:hidden property="serviceType" value="<%=serviceType%>" />
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
	<tr class="header" height="30">
		<td width="20%">
			<eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierName"/>
		</td>
		<td width="15%">
			<eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierLinkman"/>
		</td>
		<td width="50%">
			<eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierContact"/>
		</td>
		<td width="15%">
			${eoms:a2u('评估指标')}
		</td>
	</tr>
<logic:iterate id="it" name="it">
	<tr height="30">
		<td>
			<bean:write name="it" property="supplierName"/>
		</td>
		<td>
			<bean:write name="it" property="supplierLinkman"/>
		</td>
		<td>
			<bean:write name="it" property="supplierContact"/>
		</td>
		<td>
			<a href="javascript:viewKPIs('<bean:write name="it" property="id" />')">${eoms:a2u('查看')}</a>
		</td>
</logic:iterate>
</table>
</div>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>