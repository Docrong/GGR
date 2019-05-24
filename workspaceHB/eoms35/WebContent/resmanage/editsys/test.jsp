<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page  import="com.boco.eoms.common.tree.*"%>
<%@page  import="com.boco.eoms.common.util.*"%>
<html>
<head>
<title>无标题文档</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<%
int tmp = Integer.parseInt(request.getParameter("aa"));
if (tmp != 0)
{
  out.println("<input type =button value='not equals 0'>");
}
%>
</body>
</html>
