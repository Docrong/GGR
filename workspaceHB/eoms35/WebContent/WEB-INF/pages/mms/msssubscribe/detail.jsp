<%@ page language="java" import="java.util.*,com.boco.eoms.commons.mms.msssubscribe.webapp.form.*,com.boco.eoms.commons.mms.msssubscribe.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'msssubscribeForm'});
});
</script>

<%
		MsssubscribeForm msssubscribeForm = (MsssubscribeForm)request.getAttribute("msssubscribeForm");
%>

<html:form action="/mmsreports.do?method=save" styleId="msssubscribeForm" method="post"> 

<fmt:bundle basename="config/applicationResources-mms">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="mmsreport.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			订阅报表名称
		</td>
		<td class="content" >
			${msssubscribeForm.mmreportName}
		</td>
	</tr>
	<tr>
		<td class="label">
			订阅人
		</td>
		<td class="content">
		<%
			String[] p = msssubscribeForm.getReceivePerson().split(",");
			String outString = MsssubscribeDisplaytagDecoratorHelper.getReceivePerson(p);
			out.print(outString);
		 %>
		</td>
	</tr>
	<tr>
		<td class="label">
			订阅操作人
		</td>
		<td class="content">
			${msssubscribeForm.createPerson}
		</td>
	</tr>
	<tr>
		<td class="label">
			彩信报发送时间
		</td>
		<td class="content">
			${msssubscribeForm.receiveTime}
		</td>
	</tr>

</table>

</fmt:bundle>

<html:hidden property="id" value="${mmsreportForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>