<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ¼ÇÂ¼ÐÅÏ¢Î¬»¤
*@ author wuzongxian
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%
    if(!bflag)
out.println("<script>alert('ÄúÒÑ¾­µôÏß£¬ÇëÖØÐÂµÇÂ½£¡');parent.location='../index.jsp';</script>");
request.setCharacterEncoding("GBK");
String sId = null;
if(request.getParameter("id") != null)
	sId = request.getParameter("id");
else
	sId = "0";
%>
<html>
<head>
<title>ÎÄµµÀàÐÍÀàÐÍÑ¡Ôñ</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<script language=javascript>
function submitForm()
{
	condition.action = "editDocList.jsp";
	condition.submit();
}
function goBack()
{
	history.go(-1);
}
</script>
<body>
<font size=2>ÇëÄúÑ¡ÔñÒ»¸öÎÄµµÀàÐÍ:</font>
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center width='100%'><font size=2><B>ÎÄµµÀàÐÍÑ¡Ôñ</B></font></td></tr>
</table>
<table bgcolor="#dddddd" width='100%'>
<form name=condition method=post>
<%
Vector docVec = new Vector();
RoomOpt roomopt = new RoomOpt();
docVec = roomopt.getDocType(0);
AssDocPath docpath = new AssDocPath();
for(int i = 0; i < docVec.size(); i ++)
{
	docpath = (AssDocPath)docVec.get(i);
	if(i%4 == 0)
	out.println("<tr class=td_label>");
    out.println("<td align=center width='25%'><input type=radio name=type value="+docpath.getPi_id()+" onclick=javascript:submitForm()><font size=2 face='Verdana, Arial, Helvetica, sans-serif'>"+docpath.getCc_name()+"</font></td>");
 	 if((i+1)%4 == 0)
		out.println("</tr>");
}
if(docVec.size() == 0)
	out.println("<tr class=td_label><td align=center><font size=2 color=red>ÔÝÎÞÎÄµµÀàÐÍ£¬ÇëºËÊµºóÖØÊÔ</font></td></tr>");
%>
<input type=hidden name=id value=<%=sId%>>
</form>
</table>
<br><br>
</body>
</html>


