<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp"%>

<%@page
	import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm;"%>
<%
			String startDate = com.boco.eoms.base.util.StaticMethod
			.getLocalString(-1);
	String endDate = com.boco.eoms.base.util.StaticMethod
			.getCurrentDateTime();

	String initType = com.boco.eoms.base.util.StaticMethod
			.nullObject2String(request.getAttribute("initType"), "");
	
	TawSystemSessionForm sessionform = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");


%>
<script type="text/javascript">

Ext.onReady(function(){
	v = new eoms.form.Validation({form:"theform"});
});

changeEndtime(){
	var startTime = new Date(document.getElementById("sendTimeStartDate"));
	var endDate = new Date().add(startTime.MONTH,1);
	
}
</script>

<form id="theform" method="post" action="http://10.25.2.113:8080/kpiDome/ExcelServlet">
<input type="hidden" class="text" name="initType" value="<%=initType %>">

<table class="formTable">
	<!--  派单时间 -->
	<tr>
		<td width="100%"><input type="hidden" name="main.sendTime" />
		开始时间 <input type="text"
			name="sendTimeStartDate" id ="sendTimeStartDate"
			onclick="popUpCalendar(this, this, null, null, null, true, -1)"
			readonly="true" class="text" value="<%=startDate %>" /> &nbsp;&nbsp;
		结束时间 <input type="text" name="sendTimeEndDate"
			id="sendTimeEndDate"
			onclick="" alt=""
			value="<%=endDate %>" readonly="true" class="text" />
		</div>
		</td>
	</tr>

<%if (initType.equals("1")) {%>
	<tr>
		<td class="label">统计指标</td>	
			<td width="70%"><select type="select" name="sqlName" id="type">
			<option value="1">专线故障平均历时报表</option>
			<option value="2">专线故障率报表</option>
			<option value="3">专线故障总历时报表</option>
			<option value="4">专线业务可用率报表</option>
			<option value="5">专线投诉率报表</option>
			<option value="6">专线条数报表</option>
			<option value="7">专线故障主动发现率报表</option>
			<option value="8">专线割接相关故障比例报表</option>
			<option value="9">专线故障原因定位成功率报表</option>
		</select></td>
	</tr>
<%} %>

</table>
<input type="submit" name="method.save" id="method.save" onclick="javascript:changeAreaId();" class="btn" />


</form>
<div ID="idSpecial"></div>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp"%>
<%@ include file="/common/footer_eoms.jsp"%>
