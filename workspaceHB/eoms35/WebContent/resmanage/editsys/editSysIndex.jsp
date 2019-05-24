<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 显示资源列表信息
*@ version 1.0
**/
%>
<%
int pageid;
if(request.getParameter("pageid") != null)
	pageid = Integer.parseInt(request.getParameter("pageid"));
else
	pageid=1;

String sId = "0";
%>
<html>
<head>
<title>显示实体数据表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<%
SysOpt sysopt = new SysOpt();
SysTabClass Systab = new SysTabClass();

Vector TabVect = new Vector();
TabVect = sysopt.getSysTabInfor(sId);

int TabNum = TabVect.size();

if(TabNum != 0)
{
	/******************	构造内容	*****************/
	//out.println("<br><table bgcolor=#dddddd width='100%'><tr>");
	%>
	<br>
	<font size=2>请选择一个您要管理的实体表:</font>
	<br><br>
	<table bgcolor=#dddddd width='100%'><tr>
	<td align=center colspan="4"><font size=2 color=#000000><b>实体表名称</b></font></td>
	</tr>
	<%
	int flag = 0;
	for(int Col = 0; Col < TabNum; Col ++)
	{
		if(Col%4 == 0)
			out.println("<tr bgcolor=#eeeeee>");

		//out.println("<td align=center><font size=2>"+ Col + "</font></td>");
		Systab = (SysTabClass)TabVect.get(Col);
		String tabid = sysopt.getTabIdByName(Systab.getTabname());
		out.println("<td align=center><font size=2 color=#000000><a href=editSysTable.jsp?systabid="+Systab.getTabid()+"&tabid="+tabid+"&tabcode="+Systab.getTabname()+">"+Systab.getTabname()+"</a></font></td>");
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