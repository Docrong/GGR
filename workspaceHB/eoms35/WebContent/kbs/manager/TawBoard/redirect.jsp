<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>
redirect
</title>
</head>
<body bgcolor="#ffffff">
<%
int infoType = Integer.parseInt(request.getParameter("infoType"));
response.sendRedirect("../TawBoard/list.do?infoType=" + infoType);
%>
</body>
</html>
