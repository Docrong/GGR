<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.resource.*"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.common.util.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<%@page import ="com.boco.eoms.jbzl.bo.*"%>
<%@include file="../power.jsp"%>
<%/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 数据网资源统计
*@ author wuzongxian
*@ version 1.0
*@ date    2003-05-09
**/

request.setCharacterEncoding("GBK");
int sysId = Integer.parseInt(request.getParameter("system"));
String devType = request.getParameter("devtype");

String sId = null;
if(request.getParameter("id") != null)
	sId = request.getParameter("id");
else
	sId = "0";
int  oper_id=0;
entityoperate Entity = new entityoperate();
String stroppId = Entity.getoperatorId(Integer.parseInt(sId));
//modify by wuzongxian 2004-02-17
if (stroppId!=null)
   oper_id = Integer.parseInt(stroppId);
/*
List oppId = new ArrayList();
if (stroppId!=null)
	oppId = Entity.spitStr(stroppId,",");
oper_id = Integer.parseInt(oppId.get(0).toString());
*/
//**********鉴权处理*******
Vector cityVec = new Vector();
TawValidatePrivBO ValidatePrivBO = new TawValidatePrivBO();
cityVec = ValidatePrivBO.validatePriv(userId,oper_id,1);
//out.println("domain vec size is:::"+cityVec.size());
if (cityVec.size()==0)
{
	out.println("<script>alert('没有权限,请与系统管理员联系"+"');</script>");
	return;
}
String cityId = null;
if(request.getParameter("cityid") != null)
	cityId = request.getParameter("cityid");
else
	cityId = "0";
	//cityId = ug.getCity() + "";
int pageid;
if(request.getParameter("pageid") != null)
	pageid = Integer.parseInt(request.getParameter("pageid"));
else
	pageid=1;

int iFlag;
if(request.getParameter("flag") != null)
	iFlag = Integer.parseInt(request.getParameter("flag"));
else
	iFlag = 1;
String orderColName = null;
if(request.getParameter("orderColName") != null)
	orderColName = request.getParameter("orderColName");
else
	orderColName = "";
int orderflag =1;
int tmpflag = 1;

if(request.getParameter("orderflag") != null)
{
	orderflag = Integer.parseInt(request.getParameter("orderflag"));
	if (orderflag == 1)
		tmpflag =2;
	if (orderflag == 2)
		tmpflag =1;
}
else
{
	orderflag =1;
}

%>
<html>
<head>
<title>显示资源信息列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<form action="editInsert" name="entity" method=POST >
<table  width='100%' border=0 cellspacing=1>
<%
//加入当前位置
syscolindex SysColIndex = new syscolindex();
//得到实体类型
systabindex SysTab = new systabindex();
Vector TabVect = new Vector();
TabVect = Entity.getTabinfor(sId);
int EntityType=0;
int cityFlag=0;
String tabname = null;
String cc_location="";
for(int i=0;i<TabVect.size();i++)
{
	SysTab = (systabindex)TabVect.get(i);
	EntityType = SysTab.getFi_type();
	tabname   = StaticMethod.dbNull2String(SysTab.getCc_name());
	cityFlag  = SysTab.getCi_cityflag();
	cc_location = StaticMethod.dbNull2String(SysTab.getCc_location());
}
out.println("<tr bgcolor=#eeeeee>");
//out.println("<td align=left width='70%'><font size=2>"+cc_location+"->"+tabname+"列表显示</font></td>");

%>
</table>
<%
Vector EntVect = new Vector();
EntVect = Entity.getcolVec(sId,iFlag);	//得到实体Map

//out.println("Entity name :"+Entity.getCc_name());
Vector SysVect = new Vector();
if(iFlag == 1)
	SysVect = Entity.getdiscol(sId);
else
	SysVect = Entity.getcolinfor(sId);
int colNum = EntVect.size();				//列信息
if(colNum != 0)
{
	coldata colData = new coldata();
	/******************	构造内容	*****************/
	%>
	<br><table bgcolor=#dddddd width='100%' border=0 cellspacing=1>
	<tr>
	<%
	out.println("<td width=20></td>");
	for(int Col = 1; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href=javascript:goorder("+"'"+SysColIndex.getCc_code()+"',"+tmpflag+")>"+SysColIndex.getCc_name()+"</a></font></b>");
		if(SysColIndex.getCc_code().equals(orderColName))
		{
			if (orderflag == 1)
				out.println("<image src=../images/order2.gif>");
			if (orderflag ==2)
				out.println("<image src=../images/order1.gif>");
		}
		out.println("</td>");
	}
	%>
	</tr>
	<%
	String state=null;
    if (request.getParameter("state")!=null)
	{
		state=request.getParameter("state");
	}
	else
	{
		state="0";
	}
	String strSql = Entity.getStrSql(EntVect,cityVec,orderColName,orderflag,state);
	strSql = strSql.substring(0,strSql.indexOf("order")) + "  where pi_id="+sysId + "  "+ strSql.substring(strSql.lastIndexOf("order"),strSql.length());
	//out.println(strSql);
	//得到查询的SQL语句
	//out.println(strSql);
	/******************  构造分页显示  *************/

	VectorRS rs = new VectorRS();
	int pagecapacity = 15;
	rs.setPageCapacity(pagecapacity);						//每页显示记录数据个数

	rs.setRS(strSql);
	rs.setCurrentPageIndex(pageid);
	out.println("row count is: "+rs.getRowCount());
	if(rs.getRowCount() >= 1)
	{
		for(int i = 1; i <= rs.getCurrentPageRowNum(); i++)
		{
			out.println("<tr bgcolor="+((i%2 == 0)?"#eaeaea":"#eeeeee")+">");
			//=====modify
			/*if (powerflag ==1)
			{
				out.println("<td><input type=checkbox name='pi_id' value="+rs.getString(1)+"></td>");
			}*/
			out.println("<td></td>");
			//========
			int temp = 0;
			int tempFlag = 0;
			for(int a = 0; a < colNum; a ++)
			{
				colData = (coldata)EntVect.get(temp);
				temp ++;
				if(colData.getCol_flag() == 1)
				{
					if (EntityType ==0)
					{
						out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+rs.getString(a+2+tempFlag)+"</font></td>");
					}
					else
					{
						String templink="";
						if (colData.getTab_id()!=null)
						{
							templink ="<a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+rs.getString(a+2+tempFlag)+"</a>";
						}
						//out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+rs.getString(a+2+tempFlag)+"</a></font></td>");
						out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+templink+"</font></td>");
					}
					tempFlag = tempFlag + 1;
				}
				else
				{
					switch(a)
					{
						case 0:
							if (EntityType == 0)
							{
								out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
							}
							else
							{
								out.println("<td align=center><a href=editDetail.jsp?id="+rs.getString(a+1)+"&tabname="+sId+">");
							}
							break;
						case 1:
							//modify 2003-07-14 by wuzongxian
							String tempvalue = rs.getString(a+1);
							//out.println("tempvalue is: "+tempvalue);
							if (tempvalue==null)
							{
							  tempvalue="";
							}
							out.println(tempvalue+"</font></a></td>");
							break;
						default:
							String tempValue=rs.getString(a+1+tempFlag);
						    if (tempValue==null)
								tempValue = "";
							out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+tempValue+"</font></td>");
							break;
					}
				}
			}
			rs.next();
			out.println("</tr>");
		}
	}
	/*else
	{
		out.println("<font size=2 color=#000000 face='Verdana, Arial, Helvetica,sans-serif'>无此系统设备</font>");
	}*/
	out.println("</table>");
	%>
	<%
}
%>
<input type=hidden name='id' value='<%=sId%>'>
<input type=hidden name='tabid' value='<%=sId%>'>
<input type=hidden name='cityid' value='<%=cityId%>'>
<input type=hidden name="distabname" value='<%=tabname%>'>
<input type=hidden name="cc_location" value='<%=cc_location%>'>
<input type=hidden name="orderColName" value='<%=orderColName%>'>
<input type=hidden name="orderflag" value='<%=tmpflag%>'>
<input type=hidden name='OptType' value='2'>
<input type=hidden name='pageid' value=<%=pageid%>>
<br><br>
<table width='100%' border=0 cellspacing=1>
<%
   queryRes qr = new queryRes();
   resClass resclass = new resClass();
   Vector   tabVec = new Vector();
   Vector   distabVec = new Vector();
   tabVec   = qr.getSubTabClass(devType);
   
   int tabNum = tabVec.size();
   //out.println("tabNum is:"+tabNum);

   if (tabNum!=0)
   {
     distabVec = qr.getdisTabClass(tabVec,Integer.parseInt(cityId),sysId);
	 int distabNum = distabVec.size();
	 //out.println("distabNum is:"+distabNum);
	 if (distabNum!=0)
	   {
		out.println("<tr><td></td>");
		for (int i=0;i<distabNum;i++)
		   {
			 resclass = (resClass)distabVec.get(i);
			 out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(resclass.getName())+"</font></td>");
		   }
 		 out.println("</tr><tr bgcolor=#eeeeee><td align=center><font size=2>数量</font></td>");
	    for (int j=0;j<distabNum;j++)
		  {
			resclass = (resClass)distabVec.get(j);
			out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href=dataNList.jsp?id="+resclass.getId()+"&systemid="+sysId+"&cityid="+cityId+">"+qr.getResamount(resclass.getCode(),Integer.parseInt(cityId),sysId)+"</a></font></td>");
		  }
		 out.println("</tr>");
	   }
	   else
	   {
		  		out.println("<font size=2 color=#000000 face='Verdana, Arial, Helvetica,sans-serif'>无此系统设备</font>");
	   }
   }
   else
   {
	   out.println("no infor ");
   }

%>
</table>
</body>
</html>