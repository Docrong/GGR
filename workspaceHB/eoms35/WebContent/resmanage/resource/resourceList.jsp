<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.resource.*"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="java.util.*"%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ×ÊÁÏÁÐ±íÄ£¿é
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<html>
<head>
<title>×ÊÔ´Í³¼ÆÁÐ±í</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<br>
<body bgcolor="#eeeeee" text="#000000" class="content14">
<tr bgcolor=#eeeeee>
<td align=left width='50%'><font size=2>ÍøÂç»ù´¡Êý¾Ý->È«Ê¡×ÊÔ´Í³¼ÆÁÐ±í</font></td>
</tr>
<br>
<%
String secId = "0";
Vector tpV = new Vector();
typeData tp = new typeData();
Vector sumV = new Vector();
statRes sr = new statRes();
String subname = null;
tpV = resOperator.getPrmClass(secId,1);
for(int i = 0; i < tpV.size(); i ++)
{
	tp = (typeData)tpV.get(i);
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
		  <table width="100%" border="0" cellspacing="1" bgcolor="dddddd">
              <tr>
		<td align=center width=40 bgcolor=#dddddd><font size=2><B>·ÖÀà</B></font></td>
		<%
		Vector mu = new Vector();
		mu = resOperator.getMenuData(tp.getId(),0,1);
		menuData md = new menuData();
		for(int a = 0; a < mu.size(); a ++)
		{
			//out.println("md.getName() is: "+md.getName());
			md = (menuData)mu.get(a);
			out.println("<td align=center bgcolor=#dddddd><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><B>"+md.getName()+"</B></font></td>");
		}
	%>
	</tr>
	<%
		Vector sjd = new Vector();
	    //out.println("tp.getId() is: "+tp.getId());
		sjd = resOperator.getSubjectData(tp.getId(),0,1);
		for(int b = 0; b < sjd.size(); b ++)
		{
			subjectData sd = new subjectData();
			sd = (subjectData)sjd.get(b);
			out.println("<tr bgcolor="+(((b+1)%2 == 0)?"#eaeaea":"#eeeeee")+">");
			out.println("<td align=center><font size=2 color=#000000>"+sd.getName()+"</font></td>");
			Vector sdv = new Vector();
			sdv = resOperator.getStat(mu,sd.getId(),0);
			int con[] = new int[sdv.size()];
			for(int c = 0; c < sdv.size(); c ++)
			{
				statData stat = new statData();
				stat = (statData)sdv.get(c);
				if(stat.getLinkInfo() == null)
					out.println("<td align=center ><font size=2 color=#000000>"+stat.getCount()+"</font></td>");
				else
					out.println("<td align=center ><a href='"+stat.getLinkInfo()+sd.getId()+"'><font size=2 color=#000000>"+stat.getCount()+"</font></a></td>");
				con[c] = stat.getCount(); 
			}
			sumV = sr.sumCount(sumV,con);
			out.println("</tr>");
		}
		out.println("<tr><td align=center class=td ><font size=2><B>×Ü¼Æ</B></font></td>");
		for(int ab = 0; ab < sumV.size(); ab ++)
			out.println("<td align=center class=td bgcolor=#eeeeee><font size=2><B>"+sumV.get(ab)+"</B></font></td>");
		sumV.clear();
		out.println("</tr>");
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
		subd = resOperator.getSubClass(tp.getId(),1);
		for(int d = 0; d < subd.size(); d ++)
		{
			subData sd = new subData();
			sd = (subData)subd.get(d);
			if(sd.getName() != null)
				out.println("<br><font size=2 color=#000000><B>"+sd.getName()+"</B></font>");
		%>
		<table width="100%" border="0" cellspacing="1" bgcolor="ffffff">
			<tr>
			  <td>
			  <table width="100%" border="0" cellspacing="1" bgcolor="dddddd">
				  <tr>
			<td align=center width=40 bgcolor=#dddddd><font size=2><B>·ÖÀà</B></font></td>
			<%
			Vector smu = new Vector();
		    //out.println("sd.getId() is: "+sd.getId());
			smu =  resOperator.getMenuData(sd.getId(),1,1);
			for(int e = 0; e < smu.size(); e ++)
			{
				menuData smud = new menuData();
				smud = (menuData)smu.get(e);
				out.println("<td align=center bgcolor=#dddddd><font size=2 color=#000000><B>"+smud.getName()+"</B></font></td>");
			}
		%>
		</tr>
		<%
			Vector ssjd = new Vector();
			ssjd = resOperator.getSubjectData(sd.getId(),1,1);
			for(int f = 0; f < ssjd.size(); f ++)
			{
				subjectData ssd = new subjectData();
				ssd = (subjectData)ssjd.get(f);
				if(ssd.getName() == null)
				{
					out.println("<tr bgcolor="+(((f+1)%2 == 0)?"#eaeaea":"#eeeeee")+">");
					out.println("<td></td>");
				}
				else
				{
					out.println("<tr bgcolor="+(((f+1)%2 == 0)?"#eaeaea":"#eeeeee")+">");
					out.println("<td align=center ><font size=2 color=#000000>"+ssd.getName()+"</font></td>");
				}
				Vector ssdv = new Vector();
				ssdv = resOperator.getStat(smu,ssd.getId(),0);
				int scon[] = new int[ssdv.size()];
				for(int g = 0; g < ssdv.size(); g++)
				{
					statData sstat = new statData();
					sstat = (statData)ssdv.get(g);
					if(sstat.getLinkInfo() == null)
						out.println("<td align=center ><font size=2 color=#000000>"+sstat.getCount()+"</font></td>");
					else
						out.println("<td align=center ><a href='"+sstat.getLinkInfo()+"'><font size=2 color=#000000>"+sstat.getCount()+"</font></a></td>");
					scon[g] = sstat.getCount(); 
				}
				sumV = sr.sumCount(sumV,scon);
				out.println("</tr>");
			}
			out.println("<tr><td align=center class=td bgcolor=#eeeeee><font size=2><B>×Ü¼Æ</B></font></td>");
			for(int ab = 0; ab < sumV.size(); ab ++)
				out.println("<td align=center class=td bgcolor=#eeeeee><font size=2><B>"+sumV.get(ab)+"</B></font></td>");
			sumV.clear();
			out.println("</tr>");
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
%>
</body>
</html>
