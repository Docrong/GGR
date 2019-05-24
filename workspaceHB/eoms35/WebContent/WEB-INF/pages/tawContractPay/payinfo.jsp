<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawContractPayForm'});
});
</script>

<html:form action="/tawContractPays.do?method=payadd" styleId="tawContractPayForm" method="post"> 

	<fmt:bundle basename="config/applicationResource-tawcontractpay">
		
		

			<display:table name="payinfoList" cellspacing="0" cellpadding="0"
				id="payinfoList" pagesize="${pageSize}"
				class="table tawContractList" export="false"
				requestURI="${app}/contract/tawContractPays.do?method=payinfo" sort="list"
				partialList="true" size="resultSize">
			
			<display:column property="payer" 
				titleKey="tawContractPay.payer" />
	
			<display:column property="money" sortable="true"
				headerClass="sortable" titleKey="tawContractPay.money" />
	
			<display:column property="paytime" sortable="true"
				headerClass="sortable" titleKey="tawContractPay.paytime"/>
	
			<display:column property="remark" sortable="true"
				headerClass="sortable" titleKey="tawContractPay.remark" />
	
	
		</display:table>
	</fmt:bundle>
<html:hidden property="contractid" value="${tawContractPayForm.contractid}"/>
<html:hidden property="id" value="${tawContractPayForm.id}" />
<html:hidden property="contractname" value="${tawContractPayForm.contractname}" />
<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="付款" />
			
		</td>
	</tr>
</table>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>