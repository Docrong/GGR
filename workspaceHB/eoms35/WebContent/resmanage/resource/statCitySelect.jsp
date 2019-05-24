<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ×ÊÁÏÍ³¼ÆÄ£¿é
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%
//String typeid = request.getParameter("class");
String typeid = request.getParameter("type");
String stattype = request.getParameter("stattype");
cityClass cy = new cityClass();
queryRes qre = new queryRes();
Vector cc = new Vector();
cc = qre.getCity();
/*
queryRes qre = new queryRes();
statRes sr = new statRes();
Vector temp = new Vector();
Vector tp = new Vector();
temp = sr.getStatType(typeid);
statType st = new statType();
*/
%>
<html>
<head>
<title>Ñ¡Ôñ³ÇÊÐ½øÐÐÍ³¼Æ</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<script language=javascript>
function goBack()
{
	history.go(-1);
}
function submitForm()
{
	stat.submit();
}
</script>
<body bgcolor=#eeeeee text=#000000 class=listStyle>
<form action='statResult.jsp' name='stat' method='POST'>
<br>
<font size=2>ÇëÄúÑ¡ÔñÒªÍ³¼ÆµÄ³ÇÊÐ£º</font><br><br>
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center width='100%' colspan=2><font size=2><B>Ñ¡ÔñÍ³¼Æ³ÇÊÐ</B></font></td></tr>
<tr bgcolor=#eeeeee>
<td align=center><font size=2><select name=city multiple size=21>
<%
for(int i = 0; i < cc.size(); i ++)
{
	cy = (cityClass)cc.get(i);
	out.println("<option value="+cy.getId()+">"+cy.getName()+"</option>");
}

//if(temp.size() == 1)
%>
</select>
</td>
</tr>
</table>
<input type=hidden name=type value='<%=typeid%>'>
<p align=center><font size=2><a href="javascript:goBack()">·µ»Ø</a></font></p>
</form>
</body>
</html>