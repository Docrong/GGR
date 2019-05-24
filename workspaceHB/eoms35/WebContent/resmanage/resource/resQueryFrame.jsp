<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<html>
<head>
<title>×ÊÔ´²éÑ¯</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
</head>
<script language='javascript' type='text/javascript'>
var loading_done = false;
</script>
<frameset rows="150,*" frameborder="NO" border="0" framespacing="0" onload='loading_done=true'>
  <frame name="firestFrame" scrolling='auto' noresize src="resourceQuery.jsp">
  <frame name="searchframe" scrolling="auto" noresize src="temp.jsp">
</frameset>
<noframes>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</noframes>
</html>
