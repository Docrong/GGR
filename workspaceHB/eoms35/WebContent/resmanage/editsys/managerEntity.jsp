<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (�Ĵ�ʡ)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ��ʾ��Դ�б���Ϣ
*@ version 1.0
**/
%>
<%
 //if(!bflag)
  //out.println("<script>alert('���Ѿ����ߣ������µ�½��');parent.location='../index.jsp';</script>");
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
<title>��ʾ��Դ��Ϣ�б�</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<%
entityoperate Entity = new entityoperate();
systabindex SysTabIndex = new systabindex();

Vector TabVect = new Vector();
TabVect = Entity.getTabinfor(sId);

int TabNum = TabVect.size();

if(TabNum != 0)
{
	/******************	��������	*****************/
	//out.println("<br><table bgcolor=#dddddd width='100%'><tr>");
	%>
	<tr bgcolor=#eeeeee>
	<td align=left width='50%'><font size=2>ϵͳ����->ʵ��ά��</font></td>
	</tr>
	<br>
	<br>
	<font size=2>��ѡ��һ����Ҫά����ʵ��:</font>
	<br><br>
	<table bgcolor=#dddddd width='100%'><tr>
	<td align=center colspan="4"><font size=2 color=#000000><b>ʵ������</b></font></td>
	</tr>
	<%
	int flag = 0;
	for(int Col = 0; Col < TabNum; Col ++)
	{
		if(Col%4 == 0)
			out.println("<tr bgcolor=#eeeeee>");

		//out.println("<td align=center><font size=2>"+ Col + "</font></td>");
		SysTabIndex = (systabindex)TabVect.get(Col);
		out.println("<td align=center><font size=2 color=#000000><a href=updateColIndex.jsp?tabid="+SysTabIndex.getPi_id()+">"+SysTabIndex.getCc_name()+"</a></font></td>");
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