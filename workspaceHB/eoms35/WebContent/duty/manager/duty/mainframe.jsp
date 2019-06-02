
<%@ page language="java"
   import="javax.servlet.http.*,javax.servlet.*"%>

<html>
<head>
<title>EOMS</title>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
</head>
<body  text="#000000" bgcolor="#ffffff"  leftmargin="0" topmargin="0">

<table  border="0" width="100%" height="100%" cellspacing=0>
<tr height="99" width="100%">
	<td colspan="2"  width="100%">
		<iframe id="topFrame" name="topFrame" style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 100%; Z-INDEX: 2" frameborder=0 src="<%=request.getContextPath()%>/template/header.jsp" scrolling="no" ></iframe>
	</td>
</tr>
<tr>
    <td style="width:20%" >
        <iframe id="leftFrame" name="leftFrame" style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 100%; Z-INDEX: 2" frameborder=0 src="<%=request.getContextPath()%>/template/module_tree.jsp?id=11" scrolling="auto" ></iframe>
    </td>
    <td style="width:80%">
        <iframe id="rightFrame" name="rightFrame" style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 100%; Z-INDEX: 2" frameborder=0 src="<%=request.getContextPath()%>/template/null.jsp" scrolling="auto" ></iframe>
    </td>
  </tr>
</table>

</body>
</html>
