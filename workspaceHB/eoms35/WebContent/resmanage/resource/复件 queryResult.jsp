<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
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
int pageid;
if(request.getParameter("pageid") != null)
	pageid = Integer.parseInt(request.getParameter("pageid"));
else
	pageid=1;
%>
<html>
<head>
<title>符合条件的查询结果</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script language=javascript>
function exportToFile()
{
	excel.submit();
}
function closeW()
{
	window.close();
}
</script>
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<form name=excel method=post action='exportExcel.jsp'>
<%
String classid = request.getParameter("classtype");
queryRes qre = new queryRes();
String typeid = request.getParameter("typeid");
String manufid = request.getParameter("manuf");
String cityid = request.getParameter("city");

String regionid = "0";
if(request.getParameter("region") != null);
	regionid = request.getParameter("region");

String arg = "";
if(regionid == null)
	regionid = "0";
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
<%
Vector otV = new Vector();
Vector tpV = new Vector();
otV = qre.getOtherType(classid,typeid);
otherType ot = new otherType();
Vector otvv = new Vector();
Vector pos = new Vector();

for(int i = 0; i < otV.size(); i ++)
{
	ot = (otherType)otV.get(i);
	if(Integer.parseInt(typeid) == ot.getClassId())
	{
		//out.println("Method :"+ot.getId()+"<BR>");
		String temp = request.getParameter(ot.getId()+"");
		if(request.getParameter(ot.getId()+"") != null && temp.length() != 0)
		{
			tpV.addElement(request.getParameter(ot.getId()+""));
			arg = arg+"&"+ot.getId()+"="+temp;
			pos.addElement(i+"");
		}
		otvv.addElement(ot);
	}
}

if(arg == null)
	arg = "";
entityoperate Entity = new entityoperate();
syscolindex SysColIndex = new syscolindex();

Vector EntVect = new Vector();
EntVect = Entity.getcolVec(typeid,1);	//得到实体Map
String tabname = qre.getTabName(typeid);
String strSql = "";

if(Integer.parseInt(classid) == 106 || Integer.parseInt(classid) == 105)
	strSql = Entity.getPowerQuerySql(EntVect,tabname,manufid,cityid,tpV,otvv,pos,regionid,orderColName,orderflag);
else
	strSql = Entity.getQuerySql(EntVect,tabname,manufid,cityid,tpV,otvv,pos,regionid,orderColName,orderflag);

//out.println("SQL is :::   "+strSql);
Vector SysVect = new Vector();

SysVect = Entity.getdiscol(typeid);

int colNum = EntVect.size();				//列信息
if(colNum != 0)
{
	coldata colData = new coldata();

	/******************	构造内容	*****************/
	%>
	<%
		VectorRS rs = new VectorRS();
		rs.setPageCapacity(15);						//每页显示记录数据个数
		rs.setRS(strSql);
		rs.setCurrentPageIndex(pageid);
	%>
	<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>查询出的设备数量为<%=rs.getRowCount()%></font></b>
	</td>
	<br><br>
	<table bgcolor=#dddddd width='100%' border=0 cellspacing=1 id="resultRs">
	<tr>
	<%
	for(int Col = 1; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href=javascript:goorder("+"'"+SysColIndex.getCc_code()+"',"+tmpflag+")>"+SysColIndex.getCc_name()+"</a></font></b>");
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
	/******************  构造分页显示  *************/
	if(rs.getRowCount() >= 1)
	{
		for(int i = 1; i <= rs.getCurrentPageRowNum(); i++)
		{
			out.println("<tr bgcolor="+((i%2 == 0)?"#eaeaea":"#eeeeee")+">");
			int temp = 0;
			int tempFlag = 0;
			for(int a = 0; a < colNum; a ++)
			{
				colData = (coldata)EntVect.get(temp);
				temp ++;
				if(colData.getCol_flag() == 1)
				{
					out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='../edit/editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+rs.getString(a+2+tempFlag)+"</a></font></td>");
					tempFlag = tempFlag + 1;
				}
				else
				{
					switch(a)
					{
						case 0:
							out.println("<td align=center class=td><a href=../edit/editDetail.jsp?id="+rs.getString(a+1)+"&tabname="+typeid+">");
							break;
						case 1:
							out.println(rs.getString(a+1)+"</a></td>");
							break;
						default:
							String tempValue=rs.getString(a+1+tempFlag);
						    if (tempValue==null)
								tempValue = "";
							out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+tempValue+"</font></td>");
							break;
					}
				}
			}
			rs.next();
			out.println("</tr>");
		}
	}
	out.println("</table>");
	%>
<table width=100%>
<tr>
<td height=20 align=center width=40></td>
<%if (rs.getRowCount()>0) {%>
<td height=20 align=center width=60>
<a href="javascript:exportToFile()">导出Excel</a>
</td>
<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>第<%=rs.getCurrentPageIndex()%>页 / 共<%=rs.getPageCount()%>页 / 共<%=rs.getRowCount()%>条记录 </font></td>
<%}%>
<td height=20 align=center>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
	 out.println("<font face=webdings color=#06b020>4</font><a href=queryResult.jsp?id="+typeid+"&pageid="+rs.getFirstPageId()+"&classtype="+classid+"&typeid="+typeid+"&manuf="+manufid+"&city="+cityid+arg+"&region="+regionid+"&orderColName="+orderColName+"&orderflag="+orderflag+">首页</a>");%>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
	  out.println("<font face=webdings color=#06b020>4</font><a href=queryResult.jsp?id="+typeid+"&pageid="+rs.getPreviewPageId()+"&classtype="+classid+"&typeid="+typeid+"&manuf="+manufid+"&city="+cityid+arg+"&region="+regionid+"&orderColName="+orderColName+"&orderflag="+orderflag+">上一页</a>");%>
<%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
	  out.println("<font face=webdings color=#06b020>4</font><a href=queryResult.jsp?id="+typeid+"&pageid="+rs.getNextPageId()+"&classtype="+classid+"&typeid="+typeid+"&manuf="+manufid+"&city="+cityid+arg+"&region="+regionid+"&orderColName="+orderColName+"&orderflag="+orderflag+">下一页</a>");%>
<%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
	  out.println("<font face=webdings color=#06b020>4</font><a href=queryResult.jsp?id="+typeid+"&pageid="+rs.getLastPageId()+"&classtype="+classid+"&typeid="+typeid+"&manuf="+manufid+"&city="+cityid+arg+"&region="+regionid+"&orderColName="+orderColName+"&orderflag="+orderflag+">末页</a>");%>
</td>
<td></td>
<td></td>
</table>

	<%
}
else
	out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ typeid+"</font>");
%>
<!-- <form name=excel method=post action='exportExcel.jsp'>
 --><input type=hidden name=sql value="<%=strSql%>">
<input type=hidden name=type value='<%=typeid%>'>
<input type=hidden name="orderColName" value='<%=orderColName%>'>
<input type=hidden name="orderflag" value='<%=tmpflag%>'>
</form>
<script language=javascript>
//<!--
function  goorder(orderCol,orderflag)
{
	excel.action="queryResult.jsp?id=<%=typeid%>&classtype=<%=classid%>&typeid=<%=typeid%>&manuf=<%=manufid%>&city=<%=cityid%><%=arg%>&region=<%=regionid%>&orderColName="+orderCol+"&orderflag="+orderflag;
	excel.submit();
}
//-->
</script>
<p align=center><font size=2><a href="javascript:closeW()">关闭</a></font></p>
</body>
</html>
