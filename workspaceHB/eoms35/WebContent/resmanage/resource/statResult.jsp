<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.common.util.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 资料查询模块
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<html>
<head>
<title>统计列表</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script language=javascript>
//<!--
function exportToFile()
{
	excel.submit();
}
//-->
</script>
</head>
<script>
function closeW()
{
	window.close();
}
</script>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<%
request.setCharacterEncoding("GBK");
int cityid = ug.getCity();
String title = "统计结果";
int flag = 0;
if(request.getParameter("img") != null)
	flag = 1;
else
	flag = 0;
%>
<%
if(flag == 0)
{
	%>
	<table bgcolor="#dddddd" width='100%'>
	<tr><td align=center><font size=2><B>统计列表</B></font></td>
	</table>
<%
}
if(flag == 1)
	out.println("<table bgcolor=#dddddd width='100%' id=resultRs style='display:none'>");
else
	out.println("<table bgcolor=#dddddd width='100%' id=resultRs>");
%>
<tr bgcolor=#eeeeee><td></td>
<%
String typeid = request.getParameter("type");
String stattype = request.getParameter("stattype");
int astmp = 0;
if(request.getParameter("astmp") != null)
	astmp = Integer.parseInt(request.getParameter("astmp"));

String [] city = request.getParameterValues("citytmp");
String [] menu = request.getParameterValues("menu");
statRes sr = new statRes();
Vector temp = new Vector();
temp = sr.getStatType(typeid);
statType st = new statType();
statType sa = new statType();

String dataString = "";

String tab = null;
for(int i = 0; i < temp.size(); i ++)
{
	st = (statType)temp.get(i);
	if(st.getId() == Integer.parseInt(stattype))
	{
		tab = st.getTab()+"";
		sa = st;
	}
}
Vector tp = new Vector();
assStat as = new assStat();
Vector tmpa = new Vector();

tp = sr.getAssStat(stattype+"");
for(int a = 0; a < tp.size(); a ++)
{
	for(int i = 0; i < menu.length; i ++)
	{
		as = (assStat)tp.get(a);
		if(as.getId() == Integer.parseInt(menu[i]))
		{
			tmpa.addElement(as);
			break;
		}
	}
}
Vector strV = new Vector();
for(int a = 0; a < tmpa.size(); a++)
{
	resClass resStr = new resClass();
	as = (assStat)tmpa.get(a);
	// modify by wuzongxian
	//out.println("<td align=center class=ttTable><font size=2>"+as.getName()+".."+as.getTab()+"</font></td>");
	out.println("<td align=center class=ttTable><font size=2>"+StaticMethod.dbNull2String(as.getName())+"</font></td>");
	resStr.setId(a+1);
	resStr.setName(as.getName());
	strV.addElement(resStr);
}

out.println("</tr>");
Vector ty = new Vector();
if(astmp != 0)
	ty = sr.getStatMenuList((assStat)tmpa.get(0));
else
	ty = sr.getTypeInfo(tab);
int colSize = 0;
%>
<%
if(Integer.parseInt(tab) == 1)
{
	String sql = "";
	Vector countV = new Vector();
	Vector sumV  = new Vector();
	Vector tnn = new Vector();
	for(int i = 0; i < ty.size(); i++)
	{
		for(int a = 0; a < city.length; a ++)
		{
			publicClass pc = new publicClass();
			pc = (publicClass)ty.get(i);
			if(pc.getId() == Integer.parseInt(city[a]))
				tnn.addElement(pc);
		}
	}
	colSize = tnn.size();
	for(int i = 0; i < tnn.size(); i ++)
	{
		publicClass pc = new publicClass();
		pc = (publicClass)tnn.get(i);
		Vector count = new Vector();
		resClass resStr = new resClass();
		out.println("<tr bgcolor="+(((i+1)%2 == 0)?"#eaeaea":"#eeeeee")+">");
		out.println("<td align=center class=td><font size=2>"+StaticMethod.dbNull2String(pc.getName())+"</font></td>");
		
		resStr.setId(0);
		resStr.setName(pc.getName());
		strV.addElement(resStr);

		count = sr.getCount(pc,tmpa,sa,city,typeid,stattype,cityid);	
		
		int c[] = new int[count.size()];
		for(int a = 0; a < count.size(); a++)
		{
			if(count.get(a) != null)
				c[a] = Integer.parseInt((String)count.get(a));
			else
				c[a] = 0;
			
			resClass resStra = new resClass();
			resStra.setId(a+1);
			resStra.setName(c[a]+"");
			strV.addElement(resStra);

			if(count.get(a) != null)
				out.println("<td align=center class=td><font size=2>"+count.get(a)+"</font></td>");
			else
				out.println("<td align=center class=td><font size=2>0</font></td>");
		}
		sumV = sr.sumCount(sumV,c);
		
		out.println("</tr>");
	}
	out.println("<tr bgcolor=#eeeeee><td align=center class=td><font size=2><B>总计</B></font></td>");

	for(int i = 0; i < sumV.size(); i ++)
		out.println("<td align=center class=td><font size=2><B>"+sumV.get(i)+"</B></font></td>");

	out.println("</tr>");
}
else
{
	String sql = "";
	Vector countV = new Vector();
	Vector sumV  = new Vector();
	Vector tnn = new Vector();
	for(int i = 0; i < ty.size(); i++)
	{
		for(int a = 0; a < city.length; a ++)
		{
			publicClass pc = new publicClass();
			pc = (publicClass)ty.get(i);
			if(pc.getId() == Integer.parseInt(city[a]))
				tnn.addElement(pc);
		}
	}
	colSize = tnn.size();
	for(int i = 0; i < tnn.size(); i ++)
	{
		publicClass pc = new publicClass();
		pc = (publicClass)tnn.get(i);
		Vector count = new Vector();
		resClass resStr = new resClass();
		out.println("<tr bgcolor=#eeeeee><td align=center class=td><font size=2>"+StaticMethod.dbNull2String(pc.getName())+"</font></td>");
		
		resStr.setId(0);
		resStr.setName(pc.getName());
		strV.addElement(resStr);
		
		count = sr.getCount(pc,tmpa,sa,city,typeid,stattype,cityid);
		int c[] = new int[count.size()];
		for(int a = 0; a < count.size(); a++)
		{
			if(count.get(a) != null)
				c[a] = Integer.parseInt((String)count.get(a));
			else
				c[a] = 0;
			resClass resStra = new resClass();
			resStra.setId(a+1);
			resStra.setName(c[a]+"");
			strV.addElement(resStra);
			
			if(count.get(a) != null)
				out.println("<td align=center class=td><font size=2>"+count.get(a)+"</font></td>");
			else
				out.println("<td align=center class=td><font size=2>0</font></td>");
		}

		sumV = sr.sumCount(sumV,c);
		out.println("</tr>");
	}
	out.println("<tr bgcolor=#eeeeee><td align=center class=td><font size=2><B>总计</B></font></td>");
	for(int i = 0; i < sumV.size(); i ++)
		out.println("<td align=center class=td><font size=2><B>"+sumV.get(i)+"</B></font></td>");

	out.println("</tr>");
}
dataString = StaticMethod.dbNull2String(sr.getAppletString(strV,tmpa.size(),colSize));
%>
</table>
<br>
<%
if(flag == 1)
	out.println("<div align=center>");
else
	out.println("<div align=center style='display:none'>");
%>
<APPLET  CODE = "sichuancharttest.ChartApplet" CODEBASE = "." ARCHIVE = "chart.jar,chartedis.jar" WIDTH = "900" HEIGHT = "500" ALIGN = "baseline" ALT = "APPLET tag not recognized" alt="APPLET tag not recognized"></XMP>
<PARAM NAME = CODE VALUE = "sichuancharttest.ChartApplet" >
<PARAM NAME = CODEBASE VALUE = "." >
<PARAM NAME = ARCHIVE VALUE = "chart.jar,chartedis.jar" >
<PARAM NAME = "title" VALUE    = <%=title%>>
<PARAM NAME = "dataString" VALUE    = <%=dataString%>>
</APPLET>
<p align=center>说明：查看饼状图请在柱状图上单击鼠标右键</p>
</div>
<table>
<tr><td align=center><a href="javascript:exportToFile()">导出Excel</a></td></tr>
</table>
<form name=excel method=post action='exportStat.jsp' style='display:none'>
<input type=hidden name=type value='<%=typeid%>'>
<input type=hidden name=stattype value='<%=stattype%>'>
<select name=city multiple>
<%
for(int i = 0; i < city.length; i ++)
	out.println("<option value='"+city[i]+"' selected></option>");
%>
</select>
<select name=menu multiple>
<%
for(int i = 0; i < menu.length; i ++)
	out.println("<option value='"+menu[i]+"' selected></option>");
%>
</select>
</form>
<p align=center><font size=2><a href="javascript:closeW()">关闭</a></font></p>
</body>
</html>