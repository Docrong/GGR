<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page import="java.util.*"%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ×ÊÁÏ²éÑ¯Ä£¿é
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%

int manufid = 0;
if(request.getParameter("manuf") != null)
	manufid = Integer.parseInt(request.getParameter("manuf"));
int cityid = 0;
if(request.getParameter("city") != null)
	cityid = Integer.parseInt(request.getParameter("city"));
%>
<html>
<head>
<title>²éÑ¯ÀàÐÍÌõ¼þÑ¡Ôñ</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<%
int typeida = -1;
if(request.getParameter("type") != null)
{
	typeida = Integer.parseInt(request.getParameter("type"));
	out.println("<body bgcolor='#eeeeee' text='#000000' class='content14' onload='ShowTable("+typeida+")'>");
}
else
	out.println("<body bgcolor='#eeeeee' text='#000000' class='content14'>");
%>
<font size=2>ÇëÄúÑ¡ÔñÒ»¸öÉè±¸ÀàÐÍ:</font>
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center width='100%'><font size=2><B>²éÑ¯»òÍ³¼ÆÌõ¼þÑ¡Ôñ</B></font></td></tr>
</table>
<form name=condition method=post>
<table bgcolor="#dddddd" width='100%'>
<%
String classid = request.getParameter("class");
Vector devT = new Vector();
queryRes qre = new queryRes();
devT = qre.getSubResClass(classid);
resClass rc = new resClass();
for(int i = 0; i < devT.size(); i ++)
{
	rc = (resClass)devT.get(i);
	if(i%4 == 0)
		out.println("<tr bgcolor=#eeeeee>");
	if(typeida == rc.getId())
		out.println("<td align=center width='25%'><input type=radio name=type value="+rc.getId()+" onclick=javascript:ShowTable("+rc.getId()+") checked><font size=2 face='Verdana, Arial, Helvetica, sans-serif'>"+rc.getName()+"</font></td>");
	else
		out.println("<td align=center width='25%'><input type=radio name=type value="+rc.getId()+" onclick=javascript:ShowTable("+rc.getId()+")><font size=2 face='Verdana, Arial, Helvetica, sans-serif'>"+rc.getName()+"</font></td>");
	if((i+1)%4 == 0 || i + 1 == devT.size())
		out.println("</tr>");
}
out.println("</table>");
if(devT.size() == 0)
{
	out.println("<tr bgcolor=#eeeeee><td align=center><font size=2 color=red>ÔÝÎÞ²éÑ¯Ñ¡Ïî£¬ÇëºËÊµºóÖØÊÔ</font></td></tr></table>");
}
else
{
	for(int x = 0; x < devT.size(); x ++)
	{
		rc = (resClass)devT.get(x);
		String typeid = rc.getId()+"";

		//queryRes qre = new queryRes();
		Vector manuV = new Vector();
		if(Integer.parseInt(classid) == 106 || Integer.parseInt(classid) == 105)
			manuV = qre.getPowerMaunf();
		else
			manuV = qre.getManufacturer();

		manufacturer manu = new manufacturer();
		out.println("<table id='"+mtools.removeString(rc.getName())+"' style='display:none' bgcolor=#dddddd width='100%'><tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸öÉè±¸³§¼Ò:</font></td><td align=left><select name=manuf><option value=0>--È«²¿--</option>");
		for(int a = 0; a < manuV.size(); a ++)
		{
			manu = (manufacturer)manuV.get(a);
			if(manu.getId() == manufid)
				out.println("<option value="+manu.getId()+" selected>"+manu.getName()+"</option>");
			else
				out.println("<option value="+manu.getId()+">"+manu.getName()+"</option>");
		}
		out.println("</select></td></tr>");

		Vector ctV = new Vector();
		ctV = qre.getCity();
		cityClass ct = new cityClass();
		if(Integer.parseInt(typeid) == 33)
			out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city onchange='gofilter()'><option value=0>--È«²¿--</option>");
		else
			out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city><option value=0>--È«²¿--</option>");

		for(int a = 0; a < ctV.size(); a ++)
		{
			ct = (cityClass)ctV.get(a);
			if(ct.getId() == cityid)
				out.println("<option value="+ct.getId()+" selected>"+ct.getName()+"</option>");
			else
				out.println("<option value="+ct.getId()+">"+ct.getName()+"</option>");
		}
		out.println("</select></td></tr>");

		Vector otV = new Vector();
		otV = qre.getOtherType(classid,typeid);
		otherType ot = new otherType();

		for(int i = 0; i < otV.size(); i ++)
		{
			ot = (otherType)otV.get(i);
			switch(ot.getType())
			{
				case 1:
					out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö"+ot.getName()+":</font></td><td align=left><select name="+ot.getId()+"><option value=0>--È«²¿--</option>");
					Vector tp = new Vector();
					tp = qre.getSelectOption(ot,cityid);
					for(int a = 0; a < tp.size(); a ++)
					{
						publicClass pb = new publicClass();
						pb = (publicClass)tp.get(a);
						if(ot.getField().compareTo(ot.getFlag()) == 0)
							out.println("<option value='"+pb.getName()+"'>"+pb.getName()+"</option>");
						else
							out.println("<option value='"+pb.getId()+"'>"+pb.getName()+"</option>");
					}
					out.println("</select></td></tr>");
					break;
				case 2:
					out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÊäÈë"+ot.getName()+":</font></td><td align=left><input type=input name="+ot.getId()+"></td></tr>");
					break;
				case 3:
					break;
				case 4:
					break;
				default:
					break;
			}
		}
		out.println("</table>");
	}
}
%>
<input type=hidden name=class value=<%=classid%>>
</table>
</form>
<br><br>
<table width='100%'>
<tr>
<td align=center><font size=2><a href='javascript:goSearch()'>²éÑ¯</a></font></td>
<td align=center><font size=2><a href='javascript:goBack()'>·µ»Ø</a></font></td>
</tr>
</table>
<script>
function ShowTable(i)
{
	switch(i)
	{
		<%
		for(int y = 0; y < devT.size(); y ++)
		{
			rc = (resClass)devT.get(y);
			out.println("case "+rc.getId()+":");
			out.println(mtools.removeString(rc.getName())+".style.display = '';");
			for(int z = 0; z < devT.size(); z++)
			{
				if(z != y)
				{
					rc = (resClass)devT.get(z);
					out.println(mtools.removeString(rc.getName())+".style.display='none';");
				}
			}
			out.println("break;");
		}
		%>
		default:
			break;
	}
}
</script>
<script language=javascript>
function goSearch()
{
	condition.action = "queryResult.jsp";
	condition.submit();
}
function gofilter()
{
	condition.action = "typeSelect.jsp";
	condition.city[0].value = condition.city[1].value;
	condition.submit();
}
function goBack()
{
	history.go(-1);
}
</script>
</body>
</html>
