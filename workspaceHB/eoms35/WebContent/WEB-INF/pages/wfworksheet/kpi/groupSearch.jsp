<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>



<%@page import="java.util.*"%>
<%
List list = (ArrayList) request.getAttribute("colMap");
%>
<script type="text/javascript">
function exportClick(btn){
	btn.disabled='false';
	var form = document.getElementById("theform");
	var ajaxForm = Ext.getDom(form);
	var returnValues = window.showModalDialog("http://10.25.119.42:8080/kpiDome/ExcelServlet?filename=${filename}&sendTimeEndDate=${sendTimeEndDate}&sendTimeStartDate=${sendTimeStartDate}&deptid=${deptid}&test="+Math.round(Math.random()*1000),"","dialogWidth=700px;dialogHeight=400px;status:no;help:no;");
	btn.disabled='';
}

Ext.onReady(function(){
	v = new eoms.form.Validation({form:"theform"});
});
</script>
<form id="theform" method="post" action="kpiBaseAction.do?method=search">
<input type="hidden" class="text"  name="cnname" value="${cnname }">
<input type="hidden" class="text"  name="filename" value="${filename }">
<input type="hidden" class="text"  name="searchType" value="groupSearch">
<input type="hidden" class="text"  name="sendTimeEndDate" value="${sendTimeEndDate}">
<input type="hidden" class="text"  name="sendTimeStartDate" value="${ sendTimeStartDate }">


<input type="hidden" class="text"  name="deptid" value="${listdept}">
<bean:define id="sendTimeEndDate" value="${sendTimeEndDate}" />
<bean:define id="sendTimeStartDate" value="${sendTimeStartDate}" />
<bean:define id="filename" value="${filename }" />
<display:table name="taskList" cellspacing="0" cellpadding="0"
	id="taskList" pagesize="${pageSize}" class="table bureaudataHlrList"
	export="false" requestURI="kpiBaseAction.do" sort="external"
	size="total" partialList="true">
	<%
		if (list != null) {
		       int size =list.size()-1;
			for (int i = 1; i <=size; i++) {
				String str = (String) list.get(i);
				String[] value = str.split(",");
				System.out.println("----------------------------"+value[0]);
				System.out.println("----------------------------"+value[1]);	
				System.out.println("----------------------------"+value[2]);
				int j=i;
		%>
		<display:column property="<%=value[2] %>" sortable="false"	maxLength="12"  headerClass="sortable" title="<%=value[0] %>" >
		
		</display:column>
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

