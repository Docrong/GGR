<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.editsys.*"%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ÏµÍ³±íµÄÎ¬»¤
*@ version 1.0
**/
%>

<%
	String tabid = null;
	String len = "";
	String fieldtype = "";
	if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
		tabid = request.getParameter("tabid");
	else
		tabid = "0";
	//int id = Integer.parseInt(request.getParameter("checked_id"));
	String[] id = request.getParameterValues("checked_id");
	SysOpt so = new SysOpt();
%>

<html>
<head>
<title>Ôö¼ÓÊý¾Ý±íÁÐ</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<script>
</script>
<body bgcolor="#eeeeee" text="#000000" class="listStyle" onload="formsubmit()" style="display:none">
<br>
<br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000></font></td>
<%
for(int i = 0; i < id.length; i ++)
{
    syscolindex SysColIndex = new syscolindex();
	Vector tmp = new Vector();
	tmp = so.getColInforByColId(tabid,Integer.parseInt(id[i]));
	if(tmp.size() != 0)
	{
		SysColIndex = (syscolindex)tmp.get(0);
%>
	<td align=center><input type=hidden name=cc_code value='<%=SysColIndex.getCc_code()%>'><input type="hidden" name="id" value='<%=id[i]%>' ></td>
<%
	}
}
	%>
</tr>
</table>
<input type="hidden" name="tabid" value='<%=tabid%>' ><input type="hidden" name="ot" value='3'></input>
</form>
<Script Language=JavaScript>
function formsubmit()
{
	document.editsysform.action="DelaCol.jsp";
	document.editsysform.submit();
}
</Script>
</body>
</html>