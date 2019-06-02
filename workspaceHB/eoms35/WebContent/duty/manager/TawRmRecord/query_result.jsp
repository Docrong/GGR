<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<form name="tawRmCycleTableForm">
<input type="hidden" name="id">

<table class="listTable" id="list-table">
  <caption>未确认交接班内容表</caption>
  
 
  <thead>
  <tr>
	<td class="label" align="center" width="150">时间</td>
	<td class="label" align="center" width="300">班次</td>
    <td class="label" align="center" width="150">值班人</td>
    <td class="label" align="center" width="150">接班人</td>

  </tr>
  </thead>
  <%
  java.util.List list=(java.util.List)request.getAttribute("TAWRMEXCHANGEPER");
  if(list!=null&&list.size()!=0){
  for(int i=0;i<list.size();i++){
  	java.util.Map map=(java.util.Map)list.get(i);
   %>
   <tr>
   <td align="center"><%=String.valueOf(map.get("starttime_defined")).substring(0,10)%></td>
   <td align="center" nowrap><%out.print(String.valueOf(map.get("starttime_defined"))+"至"+String.valueOf(map.get("endtime_defined"))); %></td>
   <td align="center"><%=map.get("workserial") %></td>
   <td align="center"><%=map.get("receiver")%></td>
   </tr>
   <%}}%>
  <tbody>
</table>

</form>


<%@ include file="/common/footer_eoms.jsp"%>