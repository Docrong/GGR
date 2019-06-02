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
	//resclass.action = 'Select.jsp';
	for(i = 0; i < resclass.stattype.length; i ++)
	{
		if(resclass.stattype[i].style.display == '')
			resclass.stattypeid.value = resclass.stattype[i].value;
	}
	parent.statframe.location = 'Select.jsp?stattypeid='+resclass.stattypeid.value+'&type='+resclass.type.value;
	//resclass.submit();
}
function showinfo()
{
	for(i = 1; i < resclass.stattype.length; i ++)
		resclass.stattype[i].style.display="none";
}
function ShowSelect()
{
	var flag = 0;
	for(i = 0; i < resclass.type.length; i ++)
	{
		if(resclass.type.options[i].selected)
		{
			flag = i;
			break;
		}
	}
	for(i = 0; i < resclass.stattype.length; i ++)
	{
		if(i == flag)
			resclass.stattype[i].style.display = "";
		else
			resclass.stattype[i].style.display = "none";
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
<tr><td>请选择一个您要统计的类别：</td></tr>
</table>
<table width='100%'>
<tr><td align=center class="table_title">类别列表</td></tr>
</table>
<table width='100%'>
<tr><td>
<table border="0" width='100%' cellspacing="1" cellpadding="1" class="table_show" align=center>
<form name=resclass method=post>
<tr class="tr_show">
<td align=center width='20%' class="clsfth"><font size=2>请选择类型：</font></td><td align=center width='40%'><select name=type onchange='ShowSelect()'>
<%
for(int i = 0; i < resV.size(); i ++)
{
	cls = (resClass)resV.get(i);
	out.println("<option value="+cls.getId()+">"+StaticMethod.dbNull2String(cls.getName())+"</option>");
}
%>
</select>
<%
for(int i = 0; i < resV.size(); i ++)
{
	cls=(resClass)resV.get(i);
	String typeid = cls.getId()+"";
	statRes sr = new statRes();
	Vector temp = new Vector();
	temp = sr.getStatType(typeid);
	statType st = new statType();
	out.println("<td align=center><select name=stattype>");
	for(int a = 0; a < temp.size(); a ++)
	{
		st = (statType)temp.get(a);
		out.println("<option value="+st.getId()+">"+StaticMethod.dbNull2String(st.getName())+"</option>");
	}
	out.println("</select></td>");
}
%>
</tr>
<input type=hidden name=stattypeid>
</form>
</table>
</td></tr>
</table>
<table width='100%'>
<tr><td align=right height=32>
<a href='javascript:goSearch()'>选择统计条件</a></td>
</tr>
</table>
</body>
</html>