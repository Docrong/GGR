<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>
redirect
</title>
</head>
<body bgcolor="#ffffff">
<%
int boardId = Integer.parseInt(request.getParameter("boardId"));
String audit = request.getParameter("audit");
response.sendRedirect("../TawInformation/list.do?boardId=" + boardId + "&audit=" + audit);
%>
</body>
</html>
