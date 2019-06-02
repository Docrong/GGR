<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.count.*"%>
<%
//SessionCounter.getActiveSessions();
int activeNum = 0;
//取得ServletContext对象实例 
ServletContext session1 = getServletConfig().getServletContext();
if((SessionListener)session1.getAttribute("listener1")==null) 
{ 

	SessionListener sessionListener1 = new SessionListener();
	//actisessionListener1.getCount();
	session1.setAttribute("listener1",sessionListener1); 
}
session.setAttribute("listener1",(SessionListener)session1.getAttribute("listener1"));
//activeNum = ((SessionListener)session.getAttribute("listener1")).getCount(); 

%>
<html>
<head>
<title>无标题文档</title>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
 -->
 <meta http-equiv="refresh" content="120">
 <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body bgcolor="#eeeeee" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="unnamed1" height="20">
  <tr><td align=left height='37'><font color=aaaaaa><font face="Verdana, Arial, Helvetica, sans-serif">&nbsp;&nbsp;当前在线人数：<%=activeNum%></font></td>
    <td align="right" height="37"><font color=aaaaaa><font face="Verdana, Arial, Helvetica, sans-serif">&copy;2002-2003</font> 
      <b><font face="Verdana, Arial, Helvetica, sans-serif">BOCO</font></b> 亿阳信通科技股份有限公司&nbsp;</font></td>
  </tr>
</table>
</body>
</html>
