<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="java.util.*"%>
<%
List list = (ArrayList) request.getAttribute("colMap");
%>
<script type="text/javascript">
function exportClick(btn){
	btn.disabled='false';
	var form = document.getElementById("theform");
	var ajaxForm = Ext.getDom(form);
	var returnValues = window.showModalDialog("http://10.25.2.113:8080/kpiDome/ExcelServlet?filename=${filename}&sendTimeEndDate=${sendTimeEndDate}&sendTimeStartDate=${sendTimeStartDate}&deptid=${deptid}&test="+Math.round(Math.random()*1000),"","dialogWidth=700px;dialogHeight=400px;status:no;help:no;");
	btn.disabled='';
}

Ext.onReady(function(){
	v = new eoms.form.Validation({form:"theform"});
});
</script>
<form id="theform" method="post" action="kpiBaseAction.do?method=excelsearch">
<input type="hidden" class="text"  name="cnname" value="${cnname }">
<input type="hidden" class="text"  name="sendTimeEndDate" value="${ sendTimeEndDate }">
<input type="hidden" class="text"  name="sendTimeStartDate" value="${ sendTimeStartDate }">
<display:table name="taskList" cellspacing="0" cellpadding="0"
	id="taskList" pagesize="${pageSize}" class="table bureaudataHlrList"
	export="false" requestURI="kpiBaseAction.do" sort="external"
	size="total" partialList="true">
	<%
		if (list != null) {
			for (int i = 1; i < list.size(); i++) {
				String str = (String) list.get(i);
				String[] value = str.split(",");
		%>
		<display:column property="<%=value[2] %>" sortable="false"	maxLength="12" paramProperty="<%=value[2] %>" paramId="<%=value[2] %>" headerClass="sortable" title="<%=value[0] %>" />
		<%
			}
		}
	%>
	
	<display:footer>
		<tr>
			<td colspan="<%=list.size() %>">
				<input type="button" id="excel" value="导出" onclick="exportClick(this);" class="btn">
			</td>
		</tr>
	</display:footer>
</display:table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>

