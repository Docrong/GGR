<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.boco.eoms.km.score.model.KmInputScoreColumn"%>
<%
List column = (List)request.getAttribute("column");
List result = (List)request.getAttribute("kmInputScoreList");
%>
<style>
<!--
td.labelcolumn{
	vertical-align:top;
	background-color:#EDF5FD;
}

-->
</style>
<table class="formTable">
	<caption>
		<div class="header center">导入操作历史列表</div>
	</caption>
  <tr>
  	<td class="label">导入时间</td>
  	<td class="label">导入用户</td>
    <td class="label">积分用户</td>
    <td class="label">积分部门</td>
    <% 
    for(int i=0;i<column.size();i++){
		KmInputScoreColumn kmInputScoreColumn = (KmInputScoreColumn)column.get(i);
    %>
    <td class="labelcolumn"><%=kmInputScoreColumn.getColumnName() %></td>
    <%	
    }
    %>
  </tr>
  <tr>
    <% 
    for(int i=0;i<result.size();i++){
		String[] inputScore = (String[])result.get(i);
	%>
	<tr>
	  <td><%=inputScore[1] %></td>
	  <td><eoms:id2nameDB id="<%=inputScore[2] %>" beanId="tawSystemUserDao" /></td>
	  <td><eoms:id2nameDB id="<%=inputScore[3] %>" beanId="tawSystemUserDao" /></td>
	  <td><eoms:id2nameDB id="<%=inputScore[4] %>" beanId="tawSystemDeptDao" /></td>
	<%
	for(int j=5;j<inputScore.length;j++){
    %>
    <td><%=inputScore[j] %></td>
    <%	
		}
	%>
	</tr>
	<%
    	}
    %>
  </tr>
</table>


<%@ include file="/common/footer_eoms.jsp"%>