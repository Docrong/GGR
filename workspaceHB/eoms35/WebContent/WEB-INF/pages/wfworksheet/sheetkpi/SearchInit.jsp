<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ include file="/common/header_eoms_form.jsp"%>
<%@page
	import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>


<%
			String sendTimeStartDate = com.boco.eoms.base.util.StaticMethod
			.getLocalString(-7);
	String sendTimeEndDate = com.boco.eoms.base.util.StaticMethod
			.getCurrentDateTime();

	String fileName = com.boco.eoms.base.util.StaticMethod
			.nullObject2String(request.getAttribute("fileName"), "");
			
	TawSystemSessionForm sessionform = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
%>
<script type="text/javascript">

Ext.onReady(function(){
	v = new eoms.form.Validation({form:"theform"});
});
</script>

<form id="theform" method="post" action="sheetKpiBaseAction.do?method=search">
<input type="hidden" class="text" name="filename" value="<%=fileName %>">

<table class="formTable">
	
	<tr>
			<td class="label"><bean:message bundle="sheet"
			key="query.sendTime" /></td>
		<td width="100%"><input type="hidden" name="main.sendTime" />
		开始时间: <input type="text" name="sendTimeStartDate"
			onclick="popUpCalendar(this, this, null, null, null, true, -1)"
			readonly="true" class="text" value="<%=sendTimeStartDate %>" /> &nbsp;&nbsp;
		结束时间： <input type="text" name="sendTimeEndDate"
			id="sendTimeEndDate"
			onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""
			value="<%=sendTimeEndDate %>" readonly="true" class="text" />
		</div>
		</td>
	</tr>

</table>
<input type="submit" name="method.save" id="method.save" class="btn" />
</form>
<div ID="idSpecial"></div>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp"%>
<%@ include file="/common/footer_eoms.jsp"%>
