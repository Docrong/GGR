<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="java.util.*"%>
<%@page import ="com.boco.eoms.resmanage.eoms.jbzl.bo.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ×ÊÁÏ²éÑ¯Ä£¿é
*@ author LiuYang
*@ version 1.0
*@ date    2003-05-09
**/
%>
<%
// if(!bflag)
//out.println("<script>alert('ÄúÒÑ¾­µôÏß£¬ÇëÖØÐÂµÇÂ½£¡');parent.location='../index.jsp';</script>");
//**********¼øÈ¨´¦Àí*******
int oper_id=270001;
Vector cityVec = new Vector();
TawValidatePrivBO ValidatePrivBO = new TawValidatePrivBO();
cityVec = ValidatePrivBO.validatePriv(userId,oper_id,1);
//out.println("domain vec size is:::"+cityVec.size());
if (cityVec.size()==0)
{
	out.println("<script>alert('Ã»ÓÐÈ¨ÏÞ"+"');</script>");
	return;
}
%>
<html>
<head>
<title>Ìõ¼þÑ¡Ôñ</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<script language=javascript>
var a = 0;
var ar = new Array();

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
	for(i = 0; i < condition.fieldname.length; i++)
		condition.fieldname[i].style.display="none";
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
		if(condition.useless[i].checked)
		{
			info[i].style.display = "none";
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
		alert('³¬³ö·¶Î§£¡');
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
<body bgcolor="#eeeeee" text="#000000" class="content14">
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center width='100%' colspan=2><font size=2><B>²éÑ¯Ìõ¼þÑ¡Ôñ</B></font></td></tr>
<form name=condition method=post>
<tr bgcolor=#eeeeee>
<%
String classid = request.getParameter("classtype");
String typeid = request.getParameter("typeid");

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
/*
if(Integer.parseInt(classid) == 106 || Integer.parseInt(classid) == 105)
	manuV = qre.getPowerMaunf();
else
	manuV = qre.getManufacturer();
*/
manuV = qre.getManufacturer(Integer.parseInt(typeid));
manufacturer manu = new manufacturer();
out.println("<tr bgcolor=#eeeeee>");
out.println("<td align=right width='33%'><font size=2>Éè±¸³§¼Ò:</font></td><td align=left><select name=manuf><option value=0>--È«²¿--</option>");
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
//out.println("<TD width='33%' align=left><select name='disp'><option value=1>ÏÔÊ¾</option><option value=0 selected>²»ÏÔÊ¾</option></TD>");
out.println("</tr>");

Vector ctV = new Vector();
ctV = qre.getCity(cityid);
cityClass ct = new cityClass();
//out.println("ctV.szie si: "+ctV.size());
if (ValidatePrivBO.validatePriv(userId))
{
	//out.println("root logint!!!!!");
	//if(Integer.parseInt(typeid) == 33)
		out.println("<tr bgcolor=#eeeeee><td align=right width='33%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city><option value=0>--È«²¿--</option>");
	//else
	//	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city><option value=0>--È«²¿--</option>");
}
else
{
	//if(Integer.parseInt(typeid) == 33)
		out.println("<tr bgcolor=#eeeeee><td align=right width='33%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city");
	//else
	//	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city>");
}
/*if(Integer.parseInt(typeid) == 33)
	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city onchange='gofilter()'><option value=0>--È«²¿--</option>");
else
	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city><option value=0>--È«²¿--</option>");
*/
for(int a = 0; a < ctV.size(); a ++)
{
	ct = (cityClass)ctV.get(a);
	if(ct.getId() == cityid)
		out.println("<option value="+ct.getId()+" selected>"+ct.getName()+"</option>");
	else
		out.println("<option value="+ct.getId()+">"+ct.getName()+"</option>");
}
out.println("</select></td>");
//out.println("<TD width='33%' align=left><select name='disp'><option value=1>ÏÔÊ¾</option><option value=0 selected>²»ÏÔÊ¾</option></TD>");
out.println("</tr>");
/***************** Ôö¼ÓÇøÏØ²éÑ¯ ********************/
/*if(cityid != 0 && Integer.parseInt(classid) != 104)
{
	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸öÇøÏØ:</font></td><td align=left><select name=region><option value=0>--È«²¿--</option>");
	Vector region = new Vector();
	region = qre.getRegionByCityId(cityid);
	for(int i = 0; i < region.size(); i ++)
	{
		publicClass pc = new publicClass();
		pc = (publicClass)region.get(i);
		out.println("<option value="+pc.getId()+">"+pc.getName()+"</option>");
	}
	out.println("</td></tr>");
}*/
//out.println("<table width='100%' id='inputinfo' border=0 cellspacing=1 cellpadding=0>");
entityoperate Ent = new entityoperate();
syscolindex colindex = new syscolindex();
syscolindex colindex1 = new syscolindex();
Vector queryVec = new Vector();
queryVec = Ent.getQueryCol(typeid);
if (queryVec.size()>0)
{
	for(int i = 0; i < queryVec.size(); i ++)
	{
		out.println("<TR bgcolor=#eeeeee id=info style='display:none' align=center><TD width='40%' align=right><font size=2><input type=checkbox name=useless onclick='javascript:less()'>È¡Ïû</font>"); 
		out.println("<select name=colname onchange='ShowSelect()'>");	
		colindex = (syscolindex)queryVec.get(i);
		for (int j=1;j<queryVec.size();j++)
		{
			colindex1=(syscolindex)queryVec.get(j);
    		out.println("<option value='"+colindex1.getPi_id()+"'>"+colindex1.getCc_name()+"</option>");
		}
		out.println("</select></td>");
		//out.println("refVec size is:"+refVec.size());
		Vector refVec = new Vector();
		refcoldata coldata = new refcoldata();
		out.println("<TD width='33%' align=left>");
		if (colindex.getCi_ref_flag()==1)
		{
		  refVec = Ent.getQueryColValue(colindex.getPi_id(),cityVec);
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
		  out.println("<input type=text name=fieldname>");
		}
		out.println("</td>");
		/*if (colindex.getCi_ref_flag()==1)
			strTemp = "<select name=fieldname><option></option></select>";
		else
			strTemp = "<input type=text name=fieldname>";
		out.println("<TD width='33%' align=left>"+strTemp+"</TD>");*/
		//out.println("<TD width='33%' align=left><input type=text name=fieldname></TD>");
		out.println("<TD width='27%' align=left><select name='disp'><option value=1>ÏÔÊ¾</option><option value=0 selected>²»ÏÔÊ¾</option></TD>");
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
			out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö"+ot.getName()+":</font></td><td align=left><select name="+ot.getId()+"><option value=0>--È«²¿--</option>");
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
			out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>ÇëÄúÊäÈë"+ot.getName()+":</font></td><td align=left><input type=input name="+ot.getId()+"></td></tr>");
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
<td width='50%' align=right colspan=2><input type=button name=go value='Ôö¼Ó²éÑ¯Ìõ¼þ' onclick='javascript:addInput()'></td>
<td width='50%' align=left colspan=2><input type=submit name=go value='²éÑ¯' onclick='javascript:goSearch()'></td>
</tr>
</table>
</form>
</table>
</body>
</html>
