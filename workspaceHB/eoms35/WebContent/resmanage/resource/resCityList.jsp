<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.resource.*"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@include file="../power.jsp"%>
<%
if(session.getAttribute("UserInfo") == null)
	response.sendRedirect("../index.jsp");
%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ×ÊÁÏ·ÖÊ¡Í³¼ÆÄ£¿é
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%
int cityId = 0;
if(request.getParameter("cid") != null)
	cityId = Integer.parseInt(request.getParameter("cid"));

if(ug.getCity() != cityId && !ug.getRoot())
	response.sendRedirect("../content.jsp?ErrMsg='ab'");
%>
<html>
<head>
<title>×ÊÔ´Í³¼ÆÁÐ±í</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="content14">
<%
String secId = "0";
Vector tpV = new Vector();
typeData tp = new typeData();
	//out.println("city :"+cityId);
String cityName = resOperator.getCityInfo(cityId);

String subname = null;
tpV = resOperator.getPrmClass(secId,cityId);
out.println("<br><font size=2 color=#000000>ÄúÏÖÔÚ²é¿´µÄÊÇ£º<b>"+cityName+"</b> µÄ×ÊÔ´Í³¼ÆÁÐ±í</font>");
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
		  <table width="100%" border="0" cellspacing="0" bgcolor="eeeeee">
              <tr bgcolor=#dddddd>
		<td align=center width=40><font size=2>·ÖÀà</font></td>
		<%
		Vector mu = new Vector();
		mu = resOperator.getMenuData(tp.getId(),0,cityId);
		menuData md = new menuData();
		for(int a = 0; a < mu.size(); a ++)
		{
			md = (menuData)mu.get(a);
			out.println("<td align=center><font size=2 color=#000000>"+md.getName()+"</font></td>");
		}
	%>
	</tr>
	<%
		out.println("<tr><td align=center><font size=2 color=#000000>"+cityName+"</font></td>");
		Vector sdv = new Vector();
		sdv = resOperator.getStat(mu,cityId,0);
		for(int c = 0; c < sdv.size(); c ++)
		{
			statData stat = new statData();
			stat = (statData)sdv.get(c);
			if(stat.getLinkInfo() == null)
				out.println("<td align=center><font size=2 color=#000000>"+stat.getCount()+"</font></td>");
			else
				out.println("<td align=center><a href='"+stat.getLinkInfo()+cityId+"'><font size=2 color=#000000>"+stat.getCount()+"</font></a></td>");
		}
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
        //out.println("tp.getId() is: "+tp.getId());
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
			<td align=center width=40><font size=2>·ÖÀà</font></td>
			<%
			Vector smu = new Vector();
		    //out.println("sd.getId() is: "+sd.getId());
			smu =  resOperator.getMenuData(sd.getId(),1,cityId);
			for(int e = 0; e < smu.size(); e ++)
			{
				menuData smud = new menuData();
				smud = (menuData)smu.get(e);
				out.println("<td align=center><font size=2 color=#000000>"+smud.getId()+".."+smud.getName()+"</font></td>");
			}
%>
		</tr>
		<%
			Vector ssjd = new Vector();
            //out.println("sd.getId() is: "+sd.getId());
			ssjd = resOperator.getCitySubjectData(sd.getId(),1,cityId);
			//out.println("SIZE :"+ssjd.size());
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
				out.println("subject is: "+ssd.getId());
				
				
				//ssdv = resOperator.getStat(smu,ssd.getId(),cityId);
				if(ssd.getFlag() == 0)
					ssdv = resOperator.getStat(smu,ssd.getId(),0);
				else
					ssdv = resOperator.getStat(smu,ssd.getId(),cityId);

				for(int g = 0; g < ssdv.size(); g++)
				{
					statData sstat = new statData();
					sstat = (statData)ssdv.get(g);
					//out.println("sstat.getLinkInfo() is: "+ sstat.getLinkInfo());
					if(sstat.getLinkInfo() == null)
					{
						out.println("<td align=center><font size=2 color=#000000>"+sstat.getCount()+"</font></td>");
					}
					else
					{
						if(cityId!=0)
						{
							String tempLink = sstat.getLinkInfo();
							String linkinfo="";
							if (tempLink!=null)
							{
								if (tempLink.indexOf("&cityid=")>0)
								{
									linkinfo=cityId+"";
								}
								else
								{
									linkinfo ="&cityid="+cityId;
								}
							}
							out.println("<td align=center><a href="+sstat.getLinkInfo()+linkinfo+"><font size=2 color=#000000>"+sstat.getCount()+"</font></a></td>");
						}
						else
						{
							out.println("<td align=center><a href='"+sstat.getLinkInfo()+"'><font size=2 color=#000000>"+sstat.getCount()+"</font></a></td>");
						}
					}
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
%>
</body>
</html>