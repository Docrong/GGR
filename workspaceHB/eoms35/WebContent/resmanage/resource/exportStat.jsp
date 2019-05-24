<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.common.util.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<%/******修改部分******/%>
<%@include file="../power.jsp"%>
<%/********************/%>
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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language=javascript>
//<!--
function table2array(table1) //把一个表格转换为一个2维数组
{
return tbody2array(table1.firstChild)
}
function tbody2array(tbody1)
{
var elm=tbody1.children;
var result = new Array();
for (var i=0;i<elm.length;i++)
{
var tmp = new Array();

for (var j=0;j<elm[i].children.length;j++)
tmp[tmp.length] = elm[i].children[j].innerText;
result [result.length]=tmp;
}
return (result);
}
function exportFile()
{
	file.bo.onclick();
}
function export1()
{
    Spreadsheet1.ActiveSheet.Export("Stat_excel.xls")
	history.go(-1);
}
//-->
</script>
</head>
<script>
function goBack()
{
	history.go(-1);
}
</script>
<body bgcolor="#eeeeee" text="#000000" class="listStyle" onload="exportFile()">
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center><font size=2><B></B></font></td>
</table>
<table bgcolor="#dddddd" width='100%' id="resultRs" style='display:none'>
<tr bgcolor=#eeeeee><td></td>
<%
String typeid = request.getParameter("type");
String stattype = request.getParameter("stattype");
String [] city = request.getParameterValues("city");
String [] menu = request.getParameterValues("menu");

/******修改部分******/
int cityid = ug.getCity();
/************/

statRes sr = new statRes();
Vector temp = new Vector();
temp = sr.getStatType(typeid);
statType st = new statType();
statType sa = new statType();

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
for(int a = 0; a < tmpa.size(); a++)
{
	as = (assStat)tmpa.get(a);
	out.println("<td align=center class=ttTable><font size=2>"+StaticMethod.dbNull2String(as.getName())+"</font></td>");
}
out.println("</tr>");
Vector ty = new Vector();
ty = sr.getTypeInfo(tab);
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
	for(int i = 0; i < tnn.size(); i ++)
	{
		publicClass pc = new publicClass();
		pc = (publicClass)tnn.get(i);
		Vector count = new Vector();
		out.println("<tr bgcolor=#eeeeee><td align=center class=td><font size=2>"+StaticMethod.dbNull2String(pc.getName())+"</font></td>");
	
		count = sr.getCount(pc,tmpa,sa,city,typeid,stattype,cityid);
		
		
		int c[] = new int[count.size()];
		for(int a = 0; a < count.size(); a++)
		{
			if(count.get(a) != null)
				c[a] = Integer.parseInt((String)count.get(a));
			else
				c[a] = 0;
				
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
	for(int i = 0; i < tnn.size(); i ++)
	{
		publicClass pc = new publicClass();
		pc = (publicClass)tnn.get(i);
		Vector count = new Vector();
		out.println("<tr bgcolor=#eeeeee><td align=center class=td><font size=2>"+StaticMethod.dbNull2String(pc.getName())+"</font></td>");
	
		count = sr.getCount(pc,tmpa,sa,city,typeid,stattype,cityid);
		int c[] = new int[count.size()];
		for(int a = 0; a < count.size(); a++)
		{
			if(count.get(a) != null)
				c[a] = Integer.parseInt((String)count.get(a));
			else
				c[a] = 0;
			
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
%>
</table>
<br>
<table>
<tr><td align=center>
<form name=file style='display:none'>
<INPUT TYPE="button" value="数据导出到Excel" name=bo onclick="showExcel(arRS,'统计结果');"></form></td></tr>
</table>
<script language=javascript>
//<!--
var arRS = table2array(resultRs);
//-->
</script>
<script language=javascript>
function showExcel(ar,title){
var s_head="<html xmlns:x=\"urn:schemas-microsoft-com:office:excel\" xmlns=\"http://www.w3.org/TR/REC-html40\">"
+"<head><style type=\"text/css\">"
+"<!--"
+"tr{mso-height-source:auto;}"
+"td{white-space:nowrap;}"
+".wcC24FAB9{white-space:nowrap;font-family:宋体;mso-number-format:General;font-size:auto;font-weight:auto;font-style:auto;text-decoration:auto;mso-background-source:auto;mso-pattern:auto;mso-color-source:auto;text-align:general;vertical-align:bottom;border-top:none;border-left:none;border-right:none;border-bottom:none;mso-protection:locked;}"
+".wcCF5FAB9{white-space:nowrap;font-family:宋体;mso-number-format:General;font-size:auto;font-weight:auto;font-style:auto;text-decoration:auto;mso-background-source:auto;mso-pattern:auto;mso-color-source:auto;text-align:general;vertical-align:bottom;border-top:none;border-left:none;border-right:none;border-bottom:none;mso-protection:unlocked;}"
+"-->"
+"</style></head><body>"
+"<!--[if gte mso 9]>"
+"<xml>"
+"<x:ExcelWorkbook>"
+"<x:ExcelWorksheets>"
+"<x:ExcelWorksheet>"
+"<x:OWCVersion>9.0.0.2710</x:OWCVersion>"
+"<x:Label style='border-top:solid .5pt silver;border-left:solid .5pt silver;   order-right:solid .5pt silver;border-bottom:solid .5pt silver'>"
+"<x:Caption>"+ title +"</x:Caption>"
+"</x:Label>"
+"<x:Name>Sheet1</x:Name>"
+"<x:WorksheetOptions>"
+"<x:Selected/>"
+"<x:Height>7620</x:Height>"
+"<x:Width>15240</x:Width>"
+"<x:TopRowVisible>0</x:TopRowVisible>"
+"<x:LeftColumnVisible>0</x:LeftColumnVisible>"
+"<x:ProtectContents>False</x:ProtectContents>"
+"<x:DefaultRowHeight>210</x:DefaultRowHeight>"
+"<x:StandardWidth>2389</x:StandardWidth>"
+"</x:WorksheetOptions>"
+"</x:ExcelWorksheet>"
+"</x:ExcelWorksheets>"
+"<x:SpreadsheetAutoFit/>"
+"<x:MaxHeight>80%</x:MaxHeight>"
+"<x:MaxWidth>80%</x:MaxWidth>"
+"</x:ExcelWorkbook>"
+"</xml><![endif]-->"
+"<table class=wcC24FAB9 x:str>"
//alert(s_head);
for (var i=0;i<ar[0].length;i++) {
s_head+="<col class=wcC24FAB9 width=\"200\" style='mso-width-source:userset'> "
}
s_head+="<tr height=\"14\"><td align=\"center\" colspan=\"" + ar[0].length + "\"><font size=\"3\">" + title+ "</font></td></tr>"
for (var i=0;i<ar.length;i++){
s_head+="<tr height=\"14\">"
for(var j=0;j<ar[0].length;j++){
s_head += "<td class=wcC24FAB9>" + ar[i][j] + "</td>"
}
s_head+="</tr>"
}
s_head+="</table></body></html>"

Spreadsheet1.HTMLData=s_head;
export1();
}
</script>
<div id="showExceldiv" style="display:none"  align="center">
<object classid="clsid:0002E510-0000-0000-C000-000000000046" id="Spreadsheet1" codebase="" width="100%" height="1400"> 
</object>
</div>
</body>
</html>