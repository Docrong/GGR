<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="java.util.*"%>
<%@page import ="com.boco.eoms.jbzl.bo.*"%>
<%@include file="../power.jsp"%>
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
//**********鉴权处理*******
String typeid = request.getParameter("typeid");
int oper_id=0;
entityoperate Entity = new entityoperate();
String stroppId = Entity.getoperatorId(Integer.parseInt(typeid));
List oppId = new ArrayList();
if (stroppId!=null)
	oppId = Entity.spitStr(stroppId,",");
oper_id = Integer.parseInt(oppId.get(0).toString());
Vector cityVec = new Vector();
TawValidatePrivBO ValidatePrivBO = new TawValidatePrivBO();
cityVec = ValidatePrivBO.validatePriv(userId,oper_id,1);
//out.println("domain vec size is:::"+cityVec.size());
if (cityVec.size()==0)
{
	out.println("<script>alert('没有权限,请与系统管理员联系!"+"');</script>");
	return;
}
%>
<html>
<head>
<title>条件选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="content14" onload=showinfo()>
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center width='100%' colspan=2><font size=2><B>查询条件选择</B></font></td></tr>
<form name=condition method=post >
<tr bgcolor=#eeeeee>
<%
String classid = request.getParameter("classtype");

int manufid = 0;
if(request.getParameter("manuf") != null)
	manufid = Integer.parseInt(request.getParameter("manuf"));

int cityid = 0;
if(request.getParameter("city") != null)
	cityid = Integer.parseInt(request.getParameter("city"));
else
    cityid = ug.getCity();

queryRes qre = new queryRes();
Vector manuV = new Vector();
if(Integer.parseInt(classid) == 106 || Integer.parseInt(classid) == 105)
	manuV = qre.getPowerMaunf();
else
	manuV = qre.getManufacturer(Integer.parseInt(typeid));
//manuV = qre.getManufacturer(Integer.parseInt(typeid));
manufacturer manu = new manufacturer();
out.println("<tr bgcolor=#eeeeee>");
out.println("<td></td>");
out.println("<td align=right width='33%'><font size=2>设备厂家:</font></td><td align=left><select name=manuf><option value=0>--全部--</option>");
for(int a = 0; a < manuV.size(); a ++)
{
	manu = (manufacturer)manuV.get(a);
	if(manu.getId() == manufid)
		out.println("<option value="+manu.getId()+" selected>"+manu.getName()+"</option>");
	else
		out.println("<option value="+manu.getId()+">"+manu.getName()+"</option>");
}
out.println("</select></td>");
out.println("<td></td>");
out.println("</tr>");

Vector ctV = new Vector();
ctV = qre.getCity(cityVec);
cityClass ct = new cityClass();
if (ValidatePrivBO.validatePriv(userId))
{
		out.println("<tr bgcolor=#eeeeee><td></td><td align=right width='33%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city><option value=0>--全部--</option>");
}
else
{
	out.println("<tr bgcolor=#eeeeee><td></td><td align=right width='33%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city>");
}
for(int a = 0; a < ctV.size(); a ++)
{
	ct = (cityClass)ctV.get(a);
	if(ct.getId() == cityid)
		out.println("<option value="+ct.getId()+" selected>"+ct.getName()+"</option>");
	else
		out.println("<option value="+ct.getId()+">"+ct.getName()+"</option>");
}
out.println("</select></td><td></td>");
out.println("</tr>");
entityoperate Ent = new entityoperate();
syscolindex colindex = new syscolindex();
syscolindex colindex1 = new syscolindex();
Vector queryVec = new Vector();
queryVec = Ent.getQueryCol(typeid);
int num=0;
num = queryVec.size();
if (queryVec.size()>0)
{
	for(int i = 1; i < queryVec.size(); i ++)
	{		
		colindex = (syscolindex)queryVec.get(i);
		out.println("<TR bgcolor=#eeeeee id=info  align=center>");
		out.println("<TD width='10%' align=center><input type=checkbox name=colid value="+colindex.getPi_id()+" onclick='less()'><font size=2>选择</font>"+"</td>"); 
    	out.println("<td width='25%' align=right>");
		out.println("<font size=2>"+colindex.getCc_name()+"</font>:</td>");
		//out.println("refVec size is:"+refVec.size());
		Vector refVec = new Vector();
		refcoldata coldata = new refcoldata();
		out.println("<TD width='33%' align=left>");
		if (colindex.getCi_ref_flag()==1)
		{
		  refVec = Ent.getQueryColValue(colindex.getPi_id(),cityVec);
		  //out.println("<select name='"+colindex.getCc_code()+"'>");
		  out.println("<select name=fieldname>");
		  for (int k=0;k<refVec.size();k++)
			{
               coldata = (refcoldata)refVec.get(k);
			   out.println("<option value='"+coldata.getPi_idvalue()+"'>"+coldata.getCc_namevalue()+"</option>");
			}
		  out.println("</select>");
		}
		else
		{
		  //out.println("<input type=text name='"+colindex.getCc_code()+"'>");
		  out.println("<input type=text name=fieldname>");
		}
		out.println("</td>");
		/*if (colindex.getCi_ref_flag()==1)
			strTemp = "<select name=fieldname><option></option></select>";
		else
			strTemp = "<input type=text name=fieldname>";
		out.println("<TD width='33%' align=left>"+strTemp+"</TD>");*/
		//out.println("<TD width='33%' align=left><input type=text name=fieldname></TD>");
		out.println("<TD width='27%' align=left><select name='disp'><option value=1>显示</option><option value=0 selected>不显示</option></select></TD>");
	}
}
//out.println("</table>");
/*Vector otV = new Vector();
otV = qre.getOtherType(classid,typeid);
otherType ot = new otherType();

for(int i = 0; i < otV.size(); i ++)
{
	ot = (otherType)otV.get(i);
	switch(ot.getType())
	{
		case 1:
			out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个"+ot.getName()+":</font></td><td align=left><select name="+ot.getId()+"><option value=0>--全部--</option>");
			Vector tp = new Vector();
			tp = qre.getSelectOption(ot,cityid);
			//out.println("<script>alert('city:"+cityid+"')</script>");
			//out.println("Field :"+ot.getField()+"...Flag:"+ot.getFlag()+"..Table:"+ot.getTable());
			for(int a = 0; a < tp.size(); a ++)
			{
				publicClass pb = new publicClass();
				pb = (publicClass)tp.get(a);
				if(ot.getField().compareTo(ot.getFlag()) == 0)
					out.println("<option value='"+pb.getName()+"'>"+pb.getName()+"</option>");
				else
					out.println("<option value='"+pb.getId()+"'>"+pb.getName()+"</option>");
			}
			out.println("</select></td></tr>");
			break;
		case 2:
			out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您输入"+ot.getName()+":</font></td><td align=left><input type=input name="+ot.getId()+"></td></tr>");
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
	}
}*/
%>
<table bgcolor="#dddddd" width='100%'>
<tr bgcolor=#eeeeee>
<input type=hidden name=classtype value=<%=classid%>>
<input type=hidden name=typeid value=<%=typeid%>>
<!-- <td width='50%' align=right colspan=2><input type=button name=go value='增加查询条件' onclick='javascript:addInput()'></td>
 --><td width='100%' align=center colspan=2><input type=submit name=go value='查询' onclick='javascript:goSearch()'></td>
</tr>
</table>
</form>
<script language=javascript>
var a = 0;
var ar = new Array();
var num =<%=num%>;
function goSearch()
{
	condition.target = '_blank';
	condition.action = "queryResult.jsp";
}
function gofilter()
{
	condition.action = "conditionSelect.jsp";
	condition.submit();
}
function showinfo()
{
	//alert(condition.fieldname.length);
	if (num>0)
	{
		for(var i = 0; i < condition.fieldname.length; i++)
		{
			condition.fieldname[i].disabled="true";
			condition.disp[i].disabled="true";
		}
	}

}
function ShowSelect()
{
	var flag = 0;
	for(i = 0; i < condition.colname.length; i ++)
	{
		if(condition.colname.options[i].selected)
		{
			alert("aaaa");
			flag = i;
			break;
		}
	}
	/*for(i = 0; i < condition.fieldname.length; i ++)
	{
		if(i == flag)
			condition.fieldname[i].style.display = "";
		else
			condition.fieldname[i].style.display = "none";
	}*/
}

function less()
{
	var flag = 0;
	for(var i = 0; i < info.length; i ++)
	{
		if(condition.colid[i].checked)
		{
			condition.fieldname[i].disabled = false;
			condition.disp[i].disabled = false;
			ar[flag] = i;
			flag ++;
		}
		else
		{
			condition.fieldname[i].disabled = true;
			condition.disp[i].disabled = true;
			ar[flag] = i;
			flag ++;
		}
	}
	a--;
}
function less1(a)
{
	if(condition.useless[a].checked)
	{
		condition.useless[a].checked = false;
		condition.fieldname[a].disabled = false;
		condition.fieldname[a].value = "";
		condition.disp[a].disabled = false;
		condition.fieldname[a].value = "";
	}
	info[a].style.display = "";
}
function addInput()
{
	showinfo();
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
function add()
{   
   condition.tartget="_self";
   condition.action = "conditionSelect.jsp";
   condition.submit();
}
</script>

</table>
</body>
</html>
