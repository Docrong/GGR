<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.List"%>
<%
String excelUrl = StaticMethod.nullObject2String(request.getAttribute("excelUrl"));
List knowledgeUsedList = (List)request.getAttribute("knowledgeUsedOrderList");
%>

<table>
<%
for(int i=0;i<knowledgeUsedList.size();i++){
	Object [] objs = (Object[])knowledgeUsedList.get(i);
%>
  <tr>
  	<td><%=i+1 %></td><td>  <eoms:id2nameDB id="<%=objs[0].toString() %>" beanId="kmTableGeneralDao" /> </td><td>   <%=objs[1].toString() %></td>
  </tr>

<%
}
%>

</table>
<%@ include file="/common/footer_eoms.jsp"%>