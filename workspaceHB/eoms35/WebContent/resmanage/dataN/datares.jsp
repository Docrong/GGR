<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.resource.*"%>
<%@page import="java.util.*"%>
<%@include file="../power.jsp"%>
<%
if(session.getAttribute("UserInfo") == null)
	response.sendRedirect("../index.jsp");
%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 数据网资源统计
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%
int cityId = 0;
if(request.getParameter("cid") != null)
	cityId = Integer.parseInt(request.getParameter("cid"));
else
{
	if(cityPower.size() == 0)
		cityId = 0;
	else
	{
		cpw = (power)cityPower.get(0);
		cityId = cpw.getObj();
	}
}
out.println("City id :"+cityId);
int powerflag = 0;
/*
for(int i = 0; i < cityPower.size(); i ++)
{
	cpw = (power)cityPower.get(i);
	if(cpw.getObj() == cityId)
		powerflag = 1;
}
*/
/*
if(powerflag != 1 && RootPower != 1)
	response.sendRedirect("../content.jsp?ErrMsg='ab'");
*/
%>
<html>
<head>
<title>数据网资源统计列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="content14">
<%

	String sid = null;
	if(request.getParameter("subjectid") != null)
		sid = request.getParameter("subjectid");

	String secId = "0";
	Vector tpV = new Vector();
	typeData tp = new typeData();

	String subname = null;
	tpV = resOperator.getPrmClass(secId,cityId);
	for(int i = 0; i < tpV.size(); i ++)
	{
		tp = (typeData)tpV.get(i);
		if(tp.getId() == 3)
		{
		if(tp.getFlag() == 0)
		{
%>
<br>
<table width="100%" border="0" cellspacing="0">
  <tr>
    <td>
	<font size=2 color=#000000><B><%=tp.getTypeName()%></B></font>
	<table width="100%" border="0" cellspacing="1" bgcolor="ffffff">
        <tr>
          <td>
		  <table width="100%" border="0" cellspacing="0" bgcolor="eeeeee">
              <tr bgcolor=#dddddd>
		<td align=center width=40><font size=2>分类</font></td>
		<%
		Vector mu = new Vector();
		mu = resOperator.getMenuData(tp.getId(),0,cityId);
		menuData md = new menuData();
		for(int a = 0; a < mu.size(); a ++)
		{
			md = (menuData)mu.get(a);
			out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+md.getName()+"</font></td>");
		}
	%>
	</tr>
	<%
		Vector sjd = new Vector();
		sjd = resOperator.getSubjectData(tp.getId(),0,cityId);
		for(int b = 0; b < sjd.size(); b ++)
		{
			subjectData sd = new subjectData();
			sd = (subjectData)sjd.get(b);
			out.println("<tr><td align=center><font size=2 color=#000000>"+sd.getName()+"</font></td>");
			Vector sdv = new Vector();
			sdv = resOperator.getStat(mu,sd.getId(),0);
			for(int c = 0; c < sdv.size(); c ++)
			{
				statData stat = new statData();
				stat = (statData)sdv.get(c);
				if(stat.getLinkInfo() == null)
					out.println("<td align=center><font size=2 color=#000000>"+stat.getCount()+"</font></td>");
				else
					out.println("<td align=center><a href='"+stat.getLinkInfo()+sd.getId()+"'><font size=2 color=#000000>"+stat.getCount()+"</font></a></td>");
			}
			out.println("</tr>");
		}
		%>
            </table>
		</td>
        </tr>
      </table></td>
  </tr>
</table>
<%
	}
	else
	{
		%>
<br>
<table width="100%" border="0" cellspacing="0">
  <tr>
    <td>
	<font size=2 color=#000000><B><%=tp.getTypeName()%></B></font>
	<%
		Vector subd = new Vector();
		subd = resOperator.getSubClass(tp.getId(),cityId);
		for(int d = 0; d < subd.size(); d ++)
		{
			subData sd = new subData();
			sd = (subData)subd.get(d);
			if(sd.getName() != null)
				out.println("<br><font size=2 color=#000000>"+sd.getName()+"</font>");
		%>
		<table width="100%" border="0" cellspacing="1" bgcolor="ffffff">
			<tr>
			  <td>
			  <table width="100%" border="0" cellspacing="0" bgcolor="eeeeee">
				  <tr bgcolor=#dddddd>
			<td align=center width=40><font size=2>分类</font></td>
			<%
			Vector smu = new Vector();
			smu =  resOperator.getMenuData(sd.getId(),1,cityId);
			for(int e = 0; e < smu.size(); e ++)
			{
				menuData smud = new menuData();
				smud = (menuData)smu.get(e);
				out.println("<td align=center><font size=2 color=#000000>"+smud.getName()+"</font></td>");
			}
		%>
		</tr>
		<%
			Vector ssjd = new Vector();
			ssjd = resOperator.getCitySubjectData(sd.getId(),1,cityId);
			for(int f = 0; f < ssjd.size(); f ++)
			{
				subjectData ssd = new subjectData();
				ssd = (subjectData)ssjd.get(f);
				if(ssd.getName() == null && ssjd.size() == 1)
					out.println("<tr><td></td>");
				else if(ssd.getName() == null)
					continue;
				else
					out.println("<tr><td align=center><font size=2 color=#000000>"+ssd.getName()+"</font></td>");
				Vector ssdv = new Vector();
				ssdv = resOperator.getStat(smu,ssd.getId(),0);
				for(int g = 0; g < ssdv.size(); g++)
				{
					statData sstat = new statData();
					sstat = (statData)ssdv.get(g);
					/*----need modify----*/
					int temp = f + 1;
					if(sstat.getLinkInfo() == null)
						out.println("<td align=center><a href=dataNList.jsp?id=43&systemid="+temp+"><font size=2 color=#000000>"+sstat.getCount()+"</font></a></td>");
					else
						out.println("<td align=center><a href='"+sstat.getLinkInfo()+"'><font size=2 color=#000000>"+sstat.getCount()+"</font></a></td>");
				}
				out.println("</tr>");
			}
		%>
            </table>
		</td>
        </tr>
      </table></td>
  </tr>
</table>
		<%
		}
	}
	}
}
%>
</body>
</html>