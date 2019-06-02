<jsp:directive.page import="com.boco.eoms.operuser.util.OperuserConstants"/>
<jsp:directive.page import="com.boco.eoms.operuser.model.*"/>
<%@page import="java.util.List,java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
</script>

<fmt:bundle basename="config/applicationResource-operuser">
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="operuser.form.heading"/></div>
	</caption>
	<tr>
		<td  class="label">
			<fmt:message key="operuser.name" />
		</td>
		<td  class="label">
			<fmt:message key="operuser.deptname" />
		</td>
		
 <%
 		List list = (List)request.getAttribute("list"); 
		 Operuser operuser = null;	
	     for(int i=0; i<list.size(); i++){
	        operuser = (Operuser)list.get(i);
	        String oname = operuser.getName();
	        String deptname = operuser.getDeptname();
	        String id = operuser.getId();	        
    %>
	<tr>	
		<td class="content">
			<a href="${app}/operuser/operusers.do?method=list&id=<%=id %>"><%=oname %></a>
		</td>		
		<td class="content">
			<%= deptname%>
		</td>
	</tr>
	<%}      
	    %>
	
</table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>