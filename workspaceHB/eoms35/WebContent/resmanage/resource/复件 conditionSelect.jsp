<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
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
<html>
<head>
<title>条件选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<script language=javascript>
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
</script>
<%
String typeid = request.getParameter("typeid");
int  oper_id=0;
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
	out.println("<script>alert('没有权限,请与系统管理员联系！"+"');</script>");
	return;
}
%>
<body bgcolor="#eeeeee" text="#000000" class="content14">
<table bgcolor="#dddddd" width='100%'>
<tr><td align=center width='100%' colspan=2><font size=2><B>查询条件选择</B></font></td></tr>
<form name=condition method=post>
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

/*if(Integer.parseInt(classid) == 106 || Integer.parseInt(classid) == 105)
	manuV = qre.getPowerMaunf();
else
	manuV = qre.getManufacturer();
*/
manuV = qre.getManufacturer(Integer.parseInt(typeid));
manufacturer manu = new manufacturer();
out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个设备厂家:</font></td><td align=left><select name=manuf><option value=0>--全部--</option>");
for(int a = 0; a < manuV.size(); a ++)
{
	manu = (manufacturer)manuV.get(a);
	if(manu.getId() == manufid)
		out.println("<option value="+manu.getId()+" selected>"+manu.getName()+"</option>");
	else
		out.println("<option value="+manu.getId()+">"+manu.getName()+"</option>");
}
out.println("</select></td></tr>");

Vector ctV = new Vector();
ctV = qre.getCity(cityVec);
//out.println(ctV.size());
cityClass ct = new cityClass();
//out.println("ctV.szie si: "+ctV.size());
/*if (ug.getRoot())
{
	//out.println("root logint!!!!!");
	//if(Integer.parseInt(typeid) == 33)
		out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city onchange='gofilter()'><option value=0>--全部--</option>");
	//else
	//	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city><option value=0>--全部--</option>");
}
else
{
	//if(Integer.parseInt(typeid) == 33)
		out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city onchange='gofilter()'>");
	//else
	//	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city>");
}*/
/*if(Integer.parseInt(typeid) == 33)
	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city onchange='gofilter()'><option value=0>--全部--</option>");
else
	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city><option value=0>--全部--</option>");
*/
out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个城市:</font></td><td align=left><select name=city onchange='gofilter()'><option value=0>--全部--</option>");
for(int a = 0; a < ctV.size(); a ++)
{
	ct = (cityClass)ctV.get(a);
	if(ct.getId() == cityid)
		out.println("<option value="+ct.getId()+" selected>"+ct.getName()+"</option>");
	else
		out.println("<option value="+ct.getId()+">"+ct.getName()+"</option>");
}
out.println("</select></td></tr>");
/***************** 增加区县查询 ********************/
if(cityid != 0 && Integer.parseInt(classid) != 104)
{
	out.println("<tr bgcolor=#eeeeee><td align=right width='50%'><font size=2>请您选择一个区县:</font></td><td align=left><select name=region><option value=0>--全部--</option>");
	Vector region = new Vector();
	region = qre.getRegionByCityId(cityid);
	for(int i = 0; i < region.size(); i ++)
	{
		publicClass pc = new publicClass();
		pc = (publicClass)region.get(i);
		out.println("<option value="+pc.getId()+">"+pc.getName()+"</option>");
	}
	out.println("</td></tr>");
}
Vector otV = new Vector();
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
}
%>
<tr bgcolor=#eeeeee>
<input type=hidden name=classtype value=<%=classid%>>
<input type=hidden name=typeid value=<%=typeid%>>
<td width='100%' align=center colspan=2><input type=submit name=go value='查询' onclick='javascript:goSearch()'></td>
</tr>
</form>
</table>
</body>
</html>
