<%@ page language="java" import="java.util.*,com.boco.eoms.commons.mms.mmsreport.webapp.form.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'mmsreportForm'});
});
</script>

<%
	MmsreportForm mmsreportForm = (MmsreportForm)request.getAttribute("mmsreportForm");
%>

<html:form action="/mmsreports.do?method=save" styleId="mmsreportForm" method="post"> 

<fmt:bundle basename="config/applicationResources-mms">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="mmsreport.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			创建时间
		</td>
		<td class="content">
			${mmsreportForm.mmsReportCreateDate}
		</td>
	</tr>
	<tr>
		<td class="label">
			彩信报名称
		</td>
		<td class="content">
			${mmsreportForm.mmsReportName}
		</td>
	</tr>
	<tr>
		<td class="label">
			彩信报表类型
		</td>
		<td class="content">
			${mmsreportForm.mmsreportType}
		</td>
	</tr>
	<tr>
		<td class="label">
			彩信报表实例编号
		</td>
		<td class="content">
			${mmsreportForm.statreport_id}
		</td>
	</tr>

	<tr>
		<td class="label">
			彩信报模板id
		</td>
		<td class="content">
			${mmsreportForm.mmsreport_template_id}
		</td>
	</tr>

</table>

</fmt:bundle>

<html:hidden property="id" value="${mmsreportForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>