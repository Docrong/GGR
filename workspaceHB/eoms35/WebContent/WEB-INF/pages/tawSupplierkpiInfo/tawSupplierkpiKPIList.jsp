<%@ page language="java" import="java.util.*,com.boco.eoms.extra.supplierkpi.model.KPIShowObject" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
List specialList = (List) request.getAttribute("specialList");
List kpiList = (List) request.getAttribute("kpiList");
%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    //init Form validation and styles
	//valider({form:'tawSupplierkpiAssessInstanceForm',vbtn:'method.delete'});
})
</script>

<div class="list-title">
	<B><bean:write name="tawSupplierkpiInfoForm" property="supplierName" scope="request"/></B>${eoms:a2u(' 评估指标列表')}
</div>
<html:form action="saveTawSupplierkpiAssessInstance" method="post" styleId="tawSupplierkpiAssessInstanceForm"> 
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
	<tr height="30" class="header">
		<td width="40%">
			${eoms:a2u('服务专业')}
		</td>
		<td width="60%">
			${eoms:a2u('评估指标列表')}
		</td>
	</tr>
	<%if (specialList.size() <= 0) {%>
		<ul>
			<li class="error">
				<img src="<c:url value="/images/icons/warning.gif"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />
				${eoms:a2u('该厂商尚未订制任何评估指标')}
			</li>
		</ul>
	<%} else {%>
	<%for (int i = 0; i < specialList.size(); i++) {
		KPIShowObject specialObj = (KPIShowObject) specialList.get(i);
	%>
		<tr height="30">
			<td>
				<eoms:id2nameDB id="<%=specialObj.getServiceType()%>" beanId="tawSupplierkpiDictDao" />
				-
				<eoms:id2nameDB id="<%=specialObj.getSpecialType()%>" beanId="tawSupplierkpiDictDao" />
			</td>
			<td>
				<%for (int j = 0; j < kpiList.size(); j++) {
					KPIShowObject kpiObj = (KPIShowObject) kpiList.get(j);
					if (kpiObj.getSpecialType().equals(specialObj.getSpecialType())) {
				%>
					${eoms:a2u('√')}<%=kpiObj.getKpiName()%><p>
				<%}}%>
			</td>
		</tr>
	<%}}%>
</table>
</div>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>