<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.menu.*"%>
<%@page import="com.boco.eoms.resmanage.editsys.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (�Ĵ�ʡ)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ���ʵ�� first
*@ version 1.0
**/
%>
<%
//if(!bflag)
//	out.println("<script>alert('���Ѿ����ߣ������µ�½��');parent.location='../index.jsp';</script>");
int tabid = Integer.parseInt(request.getParameter("tabid"));
int type = Integer.parseInt(request.getParameter("type"));
int id = 0;
if(type != 0)
{
	id = menuVector.getMenuIdforTabId(tabid);
	if(id == 0)
	{
		type = 0;
		out.println("<script>alert('��ʵ����δ����˵�λ�ã��붨�塣');</script>");
	}
}
%>
<html>
<head>
<title>�˵�λ��ѡ��</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<form action='menuSave.jsp' name='entity' method='post'>
<table bgcolor=#dddddd width='100%' border=0 cellspacing=1 cellpadding=0>
<tr><td>
<table width='100%' id='inputinfo' border=0 cellspacing=1 cellpadding=0>
<TR bgcolor=#eeeeee align=center>
	<TD align=center><font size=2>��ʵ��ҿ��Ĳ˵��У�</font></td>
	<td><input type=hidden name=tabid value='<%=tabid%>'><input type=hidden name=type value='<%=type%>'><select name=menuid>
<%
Vector tmp = new Vector();
tmp = menuVector.getMenuListForEntity();
for(int i = 0; i < tmp.size(); i ++)
{
	secMenu sm = new secMenu();
	sm = (secMenu)tmp.get(i);
	if(type != 0)
	{
		if(sm.getSecMId() == id)
			out.println("<option value='"+sm.getSecMId()+"^"+sm.getSecLevel()+"' selected>"+sm.getSecMName()+"</option>");
		else
			out.println("<option value='"+sm.getSecMId()+"^"+sm.getSecLevel()+"'>"+sm.getSecMName()+"</option>");
	}
	else
		out.println("<option value='"+sm.getSecMId()+"^"+sm.getSecLevel()+"'>"+sm.getSecMName()+"</option>");
}
%>	
	</select></TD>
</TR>
<TR bgcolor=#eeeeee align=center>
	<TD align=center><font size=2>���ɲ˵������֣�</font></TD>
	<TD>
	<%
	if(type != 0)
		out.println("<input type=text name=menuname value='"+menuVector.getMenuNameforTabId(tabid)+"'>");
	else
		out.println("<input type=text name=menuname>");
	%>
	</TD>
</TR>
</TABLE>
</td>
</tr>
</table>
<br>
<table width='200' align=center>
<tr>
<td align=center><a href='javascript:goSubmit()'>�ύ</a></td>
</tr>
</table>
</form>
<script>
function allTrim(ui)
{ 
	var notValid=/\s/; 
	while(notValid.test(ui))
		ui=ui.replace(notValid,"");
	return ui;
}
function goSubmit()
{
	if(allTrim(entity.menuname) < 1)
		alert('û������˵�����');
	else
		entity.submit();
}
</script>
</body>
</html>