<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.sql.*"%>
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
String strSql = request.getParameter("sql");
//out.println("strSql si: "+strSql);
%>
<html>
<head>
<title>ÏÔÊ¾×ÊÔ´ÐÅÏ¢ÁÐ±í</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script language=javascript>
//<!--
function table2array(table1) //°ÑÒ»¸ö±í¸ñ×ª»»ÎªÒ»¸ö2Î¬Êý×é
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
    Spreadsheet1.ActiveSheet.Export("excel_table.xls")
	history.go(-1);
}
//-->
</script>
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle" onload="exportFile()">
<%
String typeid = request.getParameter("type");
/*
Vector otV = new Vector();
Vector tpV = new Vector();
queryRes qre = new queryRes();
otV = qre.getOtherType(classid,typeid);
otherType ot = new otherType();
Vector otvv = new Vector();
Vector pos = new Vector();
*/

entityoperate Entity = new entityoperate();
syscolindex SysColIndex = new syscolindex();

Vector EntVect = new Vector();
EntVect = Entity.getcolVec(typeid,1);	//µÃµ½ÊµÌåMap

Vector SysVect = new Vector();

SysVect = Entity.getdiscol(typeid);

int colNum = EntVect.size();				//ÁÐÐÅÏ¢
if(colNum != 0)
{
	coldata colData = new coldata();
	%>
	<br><table bgcolor=#dddddd width='100%' border=0 cellspacing=1 id="resultRs" style='display:none'>
	<tr>
	<%
	for(int Col = 1; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+SysColIndex.getCc_name()+"</font></b></td>");
	}
	%>
	</tr>
	<%
ResultSet rs = db.executeSelect(strSql);
	while(rs.next())
	{
		out.println("<tr bgcolor='#eeeeee'>");
		int temp = 0;
		int tempFlag = 0;
		for(int a = 0; a < colNum; a ++)
		{
			colData = (coldata)EntVect.get(temp);
			temp ++;
			if(colData.getCol_flag() == 1)
			{
				out.println("<td align=center class=td>"+rs.getString(a+2+tempFlag)+"</td>");
				tempFlag = tempFlag + 1;
			}
			else
			{
				switch(a)
				{
					case 0:
						out.println("<td align=center class=td><a href=../edit/editDetail.jsp?id="+rs.getString(a+1)+"&tabname="+typeid+">");
						break;
					case 1:
						out.println(rs.getString(a+1)+"</a></td>");
						break;
					default:
						String tempValue=rs.getString(a+1+tempFlag);
					    if (tempValue==null)
							tempValue = "";
						out.println("<td align=center class=td>"+tempValue+"</td>");
						break;
				}
			}
		}
		out.println("</tr>");
	}
	out.println("</table>");
%>
<table width=100%>
<tr>
<td height=20 align=center width=40></td>
<td height=20 align=center width=60><form name=file style='display:none'>
<INPUT TYPE="button" name=bo value="Êý¾Ýµ¼³ö" onclick="showExcel(arRS,'²éÑ¯½á¹û');"></form>
</td>
</table>
	<%
}
else
	out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ typeid+"</font>");
%>
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
+".wcC24FAB9{white-space:nowrap;font-family:ËÎÌå;mso-number-format:General;font-size:auto;font-weight:auto;font-style:auto;text-decoration:auto;mso-background-source:auto;mso-pattern:auto;mso-color-source:auto;text-align:general;vertical-align:bottom;border-top:none;border-left:none;border-right:none;border-bottom:none;mso-protection:locked;}"
+".wcCF5FAB9{white-space:nowrap;font-family:ËÎÌå;mso-number-format:General;font-size:auto;font-weight:auto;font-style:auto;text-decoration:auto;mso-background-source:auto;mso-pattern:auto;mso-color-source:auto;text-align:general;vertical-align:bottom;border-top:none;border-left:none;border-right:none;border-bottom:none;mso-protection:unlocked;}"
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
