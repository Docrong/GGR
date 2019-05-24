<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.List"%>
<%
String excelUrl = StaticMethod.nullObject2String(request.getAttribute("excelUrl"));
List knowledgeMonthUsedOrderList = (List)request.getAttribute("knowledgeMonthUsedOrderList");
%>

<table class="formTable">
	<caption>
		<div class="header center">知识库本月使用情况排行</div>
	</caption>
  <tr>
  	<td class="label">排行</td>
    <td class="label">知识库名</td>
    <td class="label">数量</td>
  </tr>
  <%
for(int i=0;i<knowledgeMonthUsedOrderList.size();i++){
	Object [] objs = (Object[])knowledgeMonthUsedOrderList.get(i);
%>
  <tr>
  	<td class="content"><%=i+1 %></td>
    <td class="content"><eoms:id2nameDB id="<%=objs[0].toString() %>" beanId="kmTableGeneralDao" /></td>
    <td class="content"><%=objs[1].toString() %></td>
  </tr>

<%
}
%>

</table>
<%@ include file="/common/footer_eoms.jsp"%>