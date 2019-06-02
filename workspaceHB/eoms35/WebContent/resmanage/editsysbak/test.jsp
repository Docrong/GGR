<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<html>
<head>
<title>ÎÞ±êÌâÎÄµµ</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<%
String [] a = request.getParameterValues("fieldtype");
for(int i = 0; i < a.length; i ++)
{
	out.println("<br>"+a[i]);
}
%>
</body>
</html>
