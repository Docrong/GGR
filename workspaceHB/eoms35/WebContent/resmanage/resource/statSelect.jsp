<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<%
/**
*@ E-DIS (�Ĵ�ʡ)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ����ͳ��ģ��
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%
String typeid = request.getParameter("class");
queryRes qre = new queryRes();
statRes sr = new statRes();
Vector temp = new Vector();
Vector tp = new Vector();
Vector cc = new Vector();
temp = sr.getStatType(typeid);
statType st = new statType();
cityClass cy = new cityClass();
cc = qre.getCity();
%>
<html>
<head>
<title>ѡ��ͳ������</title>
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
<form action='Select.jsp' name='stat' method='POST'>
<br>
<font size=2>����ѡ��һ��ͳ�Ʒ�ʽ��</font><br><br>
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center width='100%' colspan=2><font size=2><B>ѡ��һ��ͳ�Ʒ�ʽ</B></font></td></tr>
<tr bgcolor=#eeeeee>

<%
for(int i = 0; i < temp.size(); i ++)
{
	st = (statType)temp.get(i);
	out.println("<td align=center><font size=2><input type=radio name=stattype value="+st.getId()+" onclick='javascript:submitForm()'>"+st.getName()+"</font></td>");
}

%>
</tr>
</table>
<input type=hidden name=type value='<%=typeid%>'>
<p align=center><font size=2><a href="javascript:goBack()">����</a></font></p>
</form>
</body>
</html>