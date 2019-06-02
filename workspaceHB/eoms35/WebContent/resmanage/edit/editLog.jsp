<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ÏÔÊ¾×ÊÔ´ÁÐ±íÐÅÏ¢
*@ version 1.0
**/
%>
<%
request.setCharacterEncoding("GBK");

int pageid;
if(request.getParameter("pageid") != null)
	pageid = Integer.parseInt(request.getParameter("pageid"));
else
	pageid=1;

String sId = "0";
%>
<html>
<head>
<title>ÏÔÊ¾×ÊÔ´ÐÅÏ¢ÁÐ±í</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<%
LogOpt logopt = new LogOpt();
systabindex SysTabIndex = new systabindex();

Vector TabVect = new Vector();
TabVect = logopt.getLogTabInfor(sId);

int TabNum = TabVect.size();

if(TabNum != 0)
{
	/******************	¹¹ÔìÄÚÈÝ	*****************/
	//out.println("<br><table bgcolor=#dddddd width='100%'><tr>");
	%>
	<br>
	<font size=2>ÇëÑ¡ÔñÒ»¸öÄúÒª¹ÜÀíµÄ¼ÇÂ¼:</font>
	<br><br>
	<table bgcolor=#dddddd width='100%'><tr>
	<td align=center colspan="4"><font size=2 color=#000000><b>¼ÇÂ¼Ãû³Æ</b></font></td>
	</tr>
	<%
	int flag = 0;
	for(int Col = 0; Col < TabNum; Col ++)
	{
		if(Col%4 == 0)
			out.println("<tr class=td_label>");

		//out.println("<td align=center><font size=2>"+ Col + "</font></td>");
		SysTabIndex = (systabindex)TabVect.get(Col);
		out.println("<td align=center><font size=2 color=#000000><a href=editList.jsp?id="+SysTabIndex.getPi_id()+">"+SysTabIndex.getCc_name()+"</a></font></td>");
		if((Col-3)%4 == 0)
			out.println("</tr>");
	}
	%>
	</table>
<%
}
else
	out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ sId+"</font>");
%>
</body>
</html>