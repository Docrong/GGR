<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="java.util.*"%>
<%@page
	import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<%
			String beginTime = com.boco.eoms.base.util.StaticMethod
			.getLocalString(-1);
	String endTime = com.boco.eoms.base.util.StaticMethod
			.getCurrentDateTime();
	String fileType = com.boco.eoms.base.util.StaticMethod
			.nullObject2String(request.getAttribute("fileType"), "");
%>
<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:"theform"});
});
</script>
<%if ("1".equals(fileType)) {%>
<form id="theform" method="post" action="kpiBaseAction.do?method=groupSearch">
<%}else if ("2".equals(fileType)){ %>
<form id="theform" method="post" action="http://10.25.2.113:8080/groupKpiDome/ExcelServlet">
<%
}
%> <input type="hidden" class="text" name="searchType"
	value="groupSearch">

	<tr>
	<td width="100%">
	
	<%
	if ("2".equals(fileType)) {
	%>
		<input type="hidden" name="main.sendTime" />
	开始时间 <input type="text" name="sendTimeStartDate"
		onclick="popUpCalendar(this, this, null, null, null, true, -1)"
		readonly="true" class="text" value="<%=beginTime %>" /> &nbsp;&nbsp;
	结束时间 <input type="text" name="sendTimeEndDate" id="sendTimeEndDate"
		onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""
		value="<%=endTime %>" readonly="true" class="text" />
	<%}else{ %>
	<input type="hidden" name="main.sendTime" />
	开始时间 <input type="text" name="beginTime"
		onclick="popUpCalendar(this, this, null, null, null, true, -1)"
		readonly="true" class="text" value="<%=beginTime %>" /> &nbsp;&nbsp;
	结束时间 <input type="text" name="endTime" id="endTime"
		onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""
		value="<%=endTime %>" readonly="true" class="text" />
		<%} %>
	</td>
	</tr>
	<table class="formTable">
	<%
	if ("1".equals(fileType)) {
	%>
	<tr>
		<td class="label">指标选择*</td>
		<td width="70%">
		<select type="select" name="filename"
			id="filename">
			<option value="zxgzpjls">专线故障平均历时报表</option>
			<option value="zxgzl">专线故障率报表</option>
			<option value="zxgzls">专线故障总历时报表</option>
			<option value="zxywkyl">专线业务可用率报表</option>
			<option value="zxtsl">专线投诉率报表</option>
			<option value="zxts">专线条数报表</option>
			<option value="zxgzzdfxl">专线故障主动发现率报表</option>
			<option value="zxygjbl">专线割接相关故障比例</option>
			<option value="zxdwcg">专线故障原因定位成功率</option>

		</select>
		</td>
	</tr>
	<%
	} else if ("2".equals(fileType)) {
	%>
	<tr>
		<td class="label">指标选择*</td>
		<td width="70%"><select type="select" name="filename"
			id="filename">
			<option value="jkgz">全量集客故障工单列表</option>
			<option value="jtts">全量集客投诉工单列表</option>
			<option value="jkgz">主动发现率详情报表</option>
		</select></td>
	</tr>
	<%} %>
</table>

<input type="submit" name="method.save" id="method.save" class="btn" />

</form>
<div ID="idSpecial"></div>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp"%>
<%@ include file="/common/footer_eoms.jsp"%>