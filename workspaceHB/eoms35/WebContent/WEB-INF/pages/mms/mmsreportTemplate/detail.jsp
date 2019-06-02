<%@ page language="java" import="java.util.*,com.boco.eoms.commons.mms.mmsreport.webapp.form.*,com.boco.eoms.commons.mms.base.config.*,com.boco.eoms.commons.mms.base.util.*" pageEncoding="UTF-8"%>
<%@page import="com.boco.eoms.commons.mms.mmsreporttemplate.util.MmsreportTemplateDisplaytagDecoratorHelper,com.boco.eoms.commons.mms.mmsreporttemplate.webapp.form.*"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'mmsreportTemplateForm'});
});
</script>

<html:form action="/mmsreportTemplates.do?method=save" styleId="mmsreportTemplateForm" method="post"> 

<fmt:bundle basename="config/applicationResources-mms">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="mmsreportTemplate.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			彩信报名称
		</td>
		<td class="content">
			${mmsreportTemplateForm.mmsName}
		</td>
	</tr>
	<tr>
		<td class="label">
			彩信报创建人
		</td>
		<td class="content">
			${mmsreportTemplateForm.userid}
		</td>
	</tr>
	<tr>
		<td class="label">
			彩信报执行周期
		</td>
		
		<c:if test="${mmsreportTemplateForm.executeCycle=='dailyReport'}">
		<td class="content">
			日报表
		</td>
		</c:if>
		<c:if test="${mmsreportTemplateForm.executeCycle=='weekReport'}">
		<td class="content">
			周报表
		</td>
		</c:if>
		<c:if test="${mmsreportTemplateForm.executeCycle=='monthReport'}">
		<td class="content">
			年报表
		</td>
		</c:if>		
	</tr>
	<tr>
		<td class="label">
			报表名称
		</td>
		<td class="content">
			<%
				MmsreportTemplateForm mmsreportTemplateForm = (MmsreportTemplateForm)request.getAttribute("mmsreportTemplateForm");
				String statReportStr = mmsreportTemplateForm.getStatReportId();
				String outString = MmsreportTemplateDisplaytagDecoratorHelper.getStatReportId(statReportStr);
				out.print(outString);
			 %>
		</td>
	</tr>

	<tr>
		<td class="label">
			彩信报任务编号
		</td>
		<td class="content">
			${mmsreportTemplateForm.jobid}
		</td>
	</tr>

</table>

</fmt:bundle>

<html:hidden property="id" value="${mmsreportTemplateForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>