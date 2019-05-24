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
*@ 资料统计模块
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%
//if(!bflag)
//out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
int cityid = 0;
if(request.getParameter("city") != null)
	cityid = Integer.parseInt(request.getParameter("city"));
else
    cityid = ug.getCity();
int astmp = 0;
if(request.getParameter("menu") != null)
	astmp = Integer.parseInt(request.getParameter("menu"));

String typeid = request.getParameter("type");
String stattype = request.getParameter("stattypeid");
queryRes qre = new queryRes();
statRes sr = new statRes();
Vector temp = new Vector();
Vector tp = new Vector();
Vector cc = new Vector();
Vector mnv = new Vector();
Vector regionV = new Vector();

temp = sr.getStatType(typeid);
statType st = new statType();
cityClass cy = new cityClass();
cc = qre.getCity(cityid);
manufacturer mn = new manufacturer();
/*if(Integer.parseInt(typeid) == 105 || Integer.parseInt(typeid) == 106)
	mnv = qre.getEpeManufacturer();
else
	mnv = qre.getManufacturer();
*/

cityClass region = new cityClass();
regionV = qre.getRegion(cityid);
%>
<html>
<head>
<title>选择统计类型</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<script language=javascript>
function goBack()
{
	history.go(-1);
}
function submitForm()
{
	var i = 0;
	for(var a = 0; a < stat.citytmp.length; a ++)
	{
		if(stat.citytmp.options[a].selected)
			i ++;
	}
	if(i > 0)
	{
		stat.target = '_blank';
		stat.submit();
	}
	else
		alert("请选择一个统计方式");
}
function submitTo()
{
	if(stat.menu.value != 0)
	{
		stat.target = '_self';
		stat.action = 'Select.jsp';
		stat.submit();
	}
}
</script>
<body>
<form action='statResult.jsp' name='stat' method='POST'>
<center>
<table width="90%" border="0" cellpadding=0 cellspacing=0>
	<tr>
		<td>请您选择一个统计方式：</td>
	</tr
	<tr>
		<td class="table_title">选择一个统计方式</td>
	</tr>
</table>
</center>
<table border="0" width="90%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
<td align=center width='50%'>
<%
if((Integer.parseInt(typeid) == 105 || Integer.parseInt(typeid) == 106) && (Integer.parseInt(stattype) == 11 || Integer.parseInt(stattype) == 13))
{
	out.println("<input type=hidden name=city value="+cityid+"><input type=hidden name=stattypeid value="+stattype+"><input type=hidden name=type value="+typeid+"><input type=hidden name=astmp value="+astmp+"><select name=menu onchange='submitTo()'>");
	assStat as = new assStat();
	assStat as1 = new assStat();

	tp = sr.getAssStat(stattype+"");
	if(astmp == 0)
		out.println("<option value=0>请选择一个统计项目</option>");
	for(int a = 0; a < tp.size(); a ++)
	{
		as = (assStat)tp.get(a);
		if(as.getId() == astmp)
		{
			out.println("<option value="+as.getId()+" selected>"+StaticMethod.dbNull2String(as.getName())+"</option>");
			as1 = as;
		}
		else
			out.println("<option value="+as.getId()+">"+StaticMethod.dbNull2String(as.getName())+"</option>");
	}
	%>
	</select><td align=center width='50%'>
	<%
	if(astmp != 0)
	{
		out.println("<select name=citytmp size=21 multiple>");
		Vector tmp = new Vector();
		tmp = sr.getStatMenuList(as1);
		for(int a = 0; a < tmp.size(); a ++)
		{
			publicClass pc = new publicClass();
			pc = (publicClass)tmp.get(a);
			out.println("<option value="+pc.getId()+" selected>"+StaticMethod.dbNull2String(pc.getName())+"</option>");
		}
		out.println("</select>");
	}
}
else
{
	if(Integer.parseInt(typeid) == 105 || Integer.parseInt(typeid) == 106)
		mnv = qre.getEpeManufacturer();
	else
		mnv = qre.getManufacturer();
	//mnv = qre.getManufacturer(-1);
	%>
	<font size=2><select name=citytmp size=21 multiple>
	<%
	int typed = sr.getStatTypeId(stattype);
	switch(typed)
	{
		case 2:
			for(int i = 0; i < mnv.size(); i ++)
			{
				mn = (manufacturer)mnv.get(i);
				out.println("<option value="+mn.getId()+" selected>"+StaticMethod.dbNull2String(mn.getName())+"</option>");
			}
			break;
		case 1:
			for(int i = 0; i < cc.size(); i ++)
			{
				cy = (cityClass)cc.get(i);
				out.println("<option value="+cy.getId()+" selected>"+StaticMethod.dbNull2String(cy.getName())+"</option>");
			}
			break;
		case 3:
			for(int i = 0; i < regionV.size(); i ++)
			{
				region = (cityClass)regionV.get(i);
				out.println("<option value="+region.getId()+" selected>"+StaticMethod.dbNull2String(region.getName())+"</option>");
			}
			break;
		default:
			break;
	}
	out.println("</select></font></td>");
	%>
	<td align=center width='50%'><font size=2><select name=menu size=21 multiple>
	<%
	assStat as = new assStat();

	tp = sr.getAssStat(stattype+"");
	for(int a = 0; a < tp.size(); a ++)
	{
		as = (assStat)tp.get(a);
		out.println("<option value="+as.getId()+" selected>"+StaticMethod.dbNull2String(as.getName())+"</option>");
	}
	%>
	</select></font>
	<%
}
%></td>
</tr>
</table>
<input type=hidden name=type value='<%=typeid%>'>
<input type=hidden name=stattype value='<%=stattype%>'>
<p align=center><td align=left><input type=checkbox name=img checked><font size=2>以图形显示结果</font></td>
<td align=center><a href="javascript:submitForm()">统计</a></td></p>
</form>
</body>
</html>