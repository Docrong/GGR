<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.entity.*,com.boco.eoms.common.util.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 记录信息维护
*@ author wuzongxian
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%
    String sId = null;
	if(request.getParameter("id") != null)
		sId = request.getParameter("id");
	else
		sId = "0";
%>
<html>
<head>
<title>选择图片所属设备类型</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<script language=javascript>
function submitForm()
{
	condition.action = "selectEnt.jsp";
	condition.submit();
}
function goBack()
{
	history.go(-1);
}
</script>
<body bgcolor="#eeeeee" text="#000000" class="content14">
<font size=2>请您选择一个设备类型:</font>
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center width='100%'><font size=2><B>图片所属设备类型选择</B></font></td></tr>
</table>
<table bgcolor="#dddddd" width='100%'>
<form name=condition method=post>
<%
   Vector devT = new Vector();
   LogOpt logopt = new LogOpt();
   systabindex TabIndex = new systabindex();
   devT = logopt.getImgtab(sId);
	for(int i = 0; i < devT.size(); i ++)
	{
		TabIndex = (systabindex)devT.get(i);
		if(i%4 == 0)			
			out.println("<tr bgcolor=#eeeeee>");
		if(TabIndex.getCc_name().equals("机房")){

		out.println("<td align=center width='25%'><input type=radio name=type value="+TabIndex.getPi_id()+" onclick=javascript:submitForm()><font size=2 face='Verdana, Arial, Helvetica, sans-serif'>"+TabIndex.getCc_name()+"</font></td>");		
		}

		if((i+1)%4 == 0)
			out.println("</tr>");
	}
%>
</form>
</table>
<br><br>
</body>
</html>
