<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>



<%@page import="java.util.*"%>
<%
List list = (ArrayList) request.getAttribute("colMap");
%>

<form id="theform" method="post" action="kpiBaseAction.do?method=search">
<input type="hidden" class="text"  name="cnname" value="${cnname }">
<input type="hidden" class="text"  name="filename" value="${filename }">
<input type="hidden" class="text"  name="sendTimeEndDate" value="${sendTimeEndDate}">
<input type="hidden" class="text"  name="sendTimeStartDate" value="${ sendTimeStartDate }">


<input type="hidden" class="text"  name="deptid" value="${listdept}">
<bean:define id="sendTimeEndDate" value="${sendTimeEndDate}" />
<bean:define id="sendTimeStartDate" value="${sendTimeStartDate}" />
<bean:define id="filename" value="${filename }" />
<bean:define id="url" value="./sheetKpiBaseAction.do?method=search&filename=" />
<display:table name="taskList" cellspacing="0" cellpadding="0"
	id="taskList" pagesize="${pageSize}" class="table bureaudataHlrList"
	export="true" requestURI="sheetKpiBaseAction.do" sort="external"
	size="total" partialList="true"
	decorator="com.boco.eoms.sheet.sheetkpi.webapp.action.ReportListDisplaytagDecoratorHelper">
	<%
		if (list != null) {
		       int size =list.size()-1;
			for (int i = 1; i <size; i++) {
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
	
        <display:setProperty name="export.pdf" value="true"/>
		<display:setProperty name="export.xml" value="false"/>
</display:table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>

