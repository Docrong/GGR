<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.editsys.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 添加实体 first
*@ version 1.0
**/
%>
<%
//if(!bflag)
//	out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
request.setCharacterEncoding("GBK");
%>
<html>
<head>
<title>实体字段选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<form action='pageColIndex.jsp' name='entity' method='post'>
<table bgcolor=#dddddd width='100%' border=0 cellspacing=1 cellpadding=0>
<tr><td>
<table width='100%' id='inputinfo' border=0 cellspacing=1 cellpadding=0>
<TR bgcolor=#eeeeee align=center>
	<TD align=center colspan=6><font size=2>实体表中文名称：</font><input type=text name=tablename value='表名称'><font color=red>*</font></TD>
	<TD align=center colspan=6><font size=2>实体表英文名称：</font><input type=text name=tablecode><font color=red>*</font></TD>
</TR>
<TR bgcolor=#eeeeee align=center>
	<TD><font size=2><input type=checkbox name=unuse disabled>禁用该列</font></TD>
	<TD><input type=text name='cc_name' value='名称'><font color=red>*</font></TD>
	<TD><select name='cc_null' disabled><option value=1 selected>不为空</option></select></TD>
	<TD><select name='cc_dis' disabled><option value=1 selected>显示列</option><option value=1>不显示列</option></TD>
	<TD><select name='cc_type' disabled><option value=1 selected>varchar()</option></TD>
	<TD><font size=2>长度：</font><input type=text name='cc_length' value=64 disabled></TD>
</TR>
<TR bgcolor=#eeeeee align=center>
	<TD><font size=2><input type=checkbox name=unuse disabled>禁用该列</font></TD>
	<TD><input type=text name='fi_city' value='所属城市' disabled><font color=red>*</font></TD>
	<TD><select name='cc_null' disabled><option value=1 selected>不为空</option></select></TD>
	<TD><select name='cc_dis' disabled><option value=1 selected>显示列</option><option value=1>不显示列</option></TD>
	<TD><select name='cc_type' disabled><option value=1 selected>integer</option><option value=1>varchar()</option></TD>
	<TD></TD>
</TR>
<%
esys es = new esys();
String [] type = es.getTypeList();
for(int i = 0; i < 100; i ++)
{
	out.println("<TR bgcolor=#eeeeee id=info style='display:none' align=center><TD><font size=2><input type=checkbox name=useless onclick='javascript:less()'>禁用该列</font></TD>");
	out.println("<TD><input type=text name=fieldname><font color=red>*</font></TD>");
	out.println("<TD><select name=notnull><option value=1 selected>不为空</option><option value=0 selected>可为空</option></select></TD>");
	out.println("<TD><select name='disp'><option value=1>显示列</option><option value=0 selected>不显示列</option></TD>");
	out.println("<TD><select name=fieldtype onchange='showLength()'>");
	for(int a = 0; a < type.length; a ++)
		out.println("<option value='"+type[a]+"'>"+type[a]+"</option>");
	out.println("</TD>");
	out.println("<TD id=tplength style='display:none'><font size=2>长度：</font><input type=text name=typelength><font color=red>*</font></TD></TR>");
}
%>
</TABLE>
</td>
</tr>
</table>
<br>
<table width='200' align=center>
<tr>
<td align=center><a href='javascript:goSubmit()'>生成实体</a></td>
<td align=center><input type=hidden name=num><a href='javascript:addInput()'>生成一列</a></td>
</tr>
</table>
</form>
<script>
var a = 0;
var ar = new Array();
function less()
{
	var flag = 0;
	for(var i = 0; i < info.length; i ++)
	{
		if(entity.useless[i].checked)
		{
			info[i].style.display = "none";
			entity.fieldname[i].disabled = true;
			entity.notnull[i].disabled = true;
			entity.disp[i].disabled = true;
			entity.fieldtype[i].disabled = true;
			entity.typelength[i].disabled = true;
			ar[flag] = i;
			flag ++;
		}
	}
	a--;
}
function less1(a)
{
	if(entity.useless[a].checked)
	{
		entity.useless[a].checked = false;
		entity.fieldname[a].disabled = false;
		entity.fieldname[a].value = "";
		entity.notnull[a].disabled = false;
		entity.disp[a].disabled = false;
		entity.fieldtype[a].disabled = false;
		entity.typelength[a].disabled = false;
		entity.fieldname[a].value = "";
	}
	info[a].style.display = "";
}

function addInput()
{
	var tmp = new Array();
	if(a == 100)
	{
		alert('超出范围！');
	}
	else
	{
		if(ar.length > 0)
		{
			less1(ar[0]);
			for(var i = 1; i < ar.length; i ++)
			{
				tmp[i-1] = ar[i];
			}
			ar = tmp;
		}
		else
		{
			less1(a);
			//info[a].style.display = "";
		}
		a ++;
	}
}

function showLength()
{
	for(var i = 0; i < entity.fieldtype.length; i ++)
	{
		var y = entity.fieldtype[i].value.indexOf("(");
		if(y != -1)
			tplength[i].style.display = "";
		else
		{
			entity.typelength[i].value = "";
			tplength[i].style.display = "none";
		}
	}
}

function checkData()
{
	var flag = 0;
	if(allTrim(entity.tablename.value) == 0)
	{
		alert('请输入实体表名称');
		return false;
	}
	if(a == 0)
	{
		alert('请添加自定义实体列！');
		return false;
	}

	for(var i = 0; i < a; i ++)
	{
		if(entity.fieldname[i].disabled == false)
		{
			if(allTrim(entity.fieldname[i].value).length == 0)
			{
				alert('请输入字段的中文名！');
				entity.fieldname[i].focus();
				return false;
			}
			var y = entity.fieldtype[i].value.indexOf("(");
			if(y != -1)
			{
				if(allTrim(entity.typelength[i].value) == 0)
				{
					alert('请输入字段长度');
					entity.typelength[i].focus();
					return false;
				}
				else
				{
					if(!IsNumber(entity.typelength[i].value))
					{
						alert('长度只能为数字');
						entity.typelength[i].focus();
						return false;
					}
					else
					{
						if(entity.typelength[i].value > 1024 || entity.typelength[i].value < 1)
						{
							alert('字段长度应在 1~255 之间');
							entity.typelength[i].focus();
							return false;
						}
					}
				}
			}
		}
	}
	return true;
}

function IsDigit(cCheck)
{
	return (('0'<=cCheck) && (cCheck<='9'));
}

function IsNumber(str)
{
	for (var nIndex=0; nIndex<str.length; nIndex++)
	{
		cCheck = str.charAt(nIndex);
		if (!(IsDigit(cCheck)))
			return false;
	}
   return true;
}

function allTrim(ui)
{
	var notValid=/\s/;
	while(notValid.test(ui))
		ui=ui.replace(notValid,"");
	return ui;
}
function goSubmit()
{
	if(checkData())
	{
		entity.num.value = a;
		entity.submit();
	}
}
</script>
</body>
</html>