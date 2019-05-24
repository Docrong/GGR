<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page import="com.boco.eoms.common.util.*"%>
<%@page import="java.util.*"%>
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
<%
resClass cls = new resClass();
Vector resV = new Vector();
queryRes qr = new queryRes();
resV = qr.getResClass();
%>
<script language=javascript>
function goSearch()
{
	//resclass.action = 'conditionSelect.jsp';
	for(i = 0; i < resclass.type.length; i ++)
	{
		if(resclass.type[i].style.display == '')
			resclass.typeid.value = resclass.type[i].value;
	}
	parent.searchframe.location = 'conditionSelect.jsp?classtype='+resclass.classtype.value+'&typeid='+resclass.typeid.value;
}
function showinfo()
{
	for(i = 1; i < resclass.type.length; i ++)
		resclass.type[i].style.display="none";
}
function ShowSelect()
{
	var flag = 0;
	for(i = 0; i < resclass.classtype.length; i ++)
	{
		if(resclass.classtype.options[i].selected)
		{
			flag = i;
			break;
		}
	}
	for(i = 0; i < resclass.type.length; i ++)
	{
		if(i == flag)
			resclass.type[i].style.display = "";
		else
			resclass.type[i].style.display = "none";
	}
}
</script>
<html>
<head>
<title>资源统计列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body onload="showinfo()">
<table width='100%'>
<tr><td>请选择一个您要查询或统计的类别：</td></tr>
</table>
<table width='100%'>
<tr><td align=center class="table_title">类别列表</td></tr>
</table>
<table width='100%'>
<tr><td>
<table border="0" width='100%' cellspacing="1" cellpadding="1" class="table_show" align=center>
<form name=resclass method=post>
<tr class="tr_show">
<td align=center width='20%' class="clsfth"><font size=2>请选择类型：</font></td><td align=center width='40%'><select name=classtype onchange='ShowSelect()'>
<%
for(int i = 0; i < resV.size(); i ++)
{
	cls = (resClass)resV.get(i);
	out.println("<option value="+cls.getId()+">"+StaticMethod.dbNull2String(cls.getName())+"</option>");
}
%>
</select></td><td align=right><font size=2>选择种类：</font></td>
<%
for(int i = 0; i < resV.size(); i ++)
{
	cls=(resClass)resV.get(i);
	String classid = cls.getId()+"";
	out.println("<td align=center><select name=type>");
	Vector devT = new Vector();
	queryRes qre = new queryRes();
	devT = qre.getSubResClass(classid);
	resClass rc = new resClass();
	for(int a = 0; a < devT.size(); a ++)
	{
		rc = (resClass)devT.get(a);
		out.println("<option value="+rc.getId()+">"+StaticMethod.dbNull2String(rc.getName())+"</option>");
	}
	out.println("</select></td>");
}
%>
</tr>
<input type=hidden name=typeid>
</form>
</table>
<table width='100%'>
<tr><td align=right height=32>
<a href='javascript:goSearch()'>输入查询条件</a></td>
</tr>
</table>
</body>
</html>