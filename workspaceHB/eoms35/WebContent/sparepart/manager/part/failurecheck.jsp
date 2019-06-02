
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%

List errList = (List) request.getAttribute("errorList");
System.out.println(errList.size());
%>
<html>
<head>
<title>
${eoms:a2u('批量导入')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="td_label" align="center">
        <td>${eoms:a2u('错误信息')}</td>
       </tr>
       <tr class="tr_show" align="center">
        <%
		for (int i=0;i<errList.size();i++){
		%>
			<tr>
			<td><%out.println(errList.get(i).toString());%></td>
			</tr>
		<%
		}
	%>
       </tr>
</table>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
