<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="mcs.common.db.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 显示资源列表信息
*@ version 1.0
**/
%>
<%
 if(!bflag)
out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
String cityId = null;
if(request.getParameter("cityid") != null)
	cityId = request.getParameter("cityid");
else
	cityId = ug.getCity() + "";
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

String sId = null;
if(request.getParameter("id") != null)
	sId = request.getParameter("id");
else
	sId = "2";
//操作权限
//out.println("group id is: "+opt.getIGroup());
int powerflag = 0;
int pnum = entityPower.size();
if (ug.getRoot() && opt.getIGroupManager()==1)
{
	//out.println("root login in ");
	powerflag =1;
}
//out.println("pnum is:"+pnum);
if (pnum>0)
{
	for (int i=0;i<pnum;i++)
	{
		epw = (power)entityPower.get(i);
		if (epw.getObj()==Integer.parseInt(sId))
		{
			//out.println("epw getObj() is:"+epw.getObj());
	       if (opt.getIGroupManager()==1 && epw.getPower()==1)
			{
				powerflag = 1;
				break;
			}
		}
	}
}
%>
<html>
<head>
<title>拓扑图管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<form name="entity" method=POST action="newOperator.jsp">
<tr bgcolor=#eeeeee>
<td align=left width='50%'><font size=2>网络基础数据->各地市拓扑图列表</font></td>
</tr>
<table width='100%' border=0 cellspacing=1>
<tr>
<td align=left><font size=2>拓扑图列表显示</font>
</td>
</tr>
<br>
<tr bgcolor=#dddddd width='100%'>
<td align=center><font size=2><B>拓扑图名称</B></font></td>
<td align=center><font size=2><B>所在城市</B></font></td>
<td align=center><font size=2><B>查看拓扑图</B></font></td>
</tr>
<tr>
<%
String strSql = "";
if (cityId.equals("0"))
{
	strSql = "select showtopo.pi_id,showtopo.cc_name,edm_city.cc_name,showtopo.cc_ulr from showtopo,edm_city where showtopo.fi_city=edm_city.pi_id";
}
else
{
	strSql = "select showtopo.pi_id,showtopo.cc_name,edm_city.cc_name,showtopo.cc_ulr from showtopo,edm_city where showtopo.fi_city=edm_city.pi_id and showtopo.fi_city="+cityId;
}
//out.println("strsql is: "+strSql);
VectorRS rs = new VectorRS();
rs.setPageCapacity(15);						//每页显示记录数据个数

rs.setRS(strSql);
rs.setCurrentPageIndex(pageid);
if(rs.getRowCount() >= 1)
{
	for(int i = 0; i < rs.getCurrentPageRowNum(); i ++)
	{
		out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+rs.getString(2)+"</font></td>");
		out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+rs.getString(3)+"</font></td>");
		out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href="+rs.getString(4)+" target=_blank>查看</a></font></td>");
		rs.next();
		out.println("</tr>");
	}
}
%>
</tr>
</table>
<table width='100%' border=0 cellspacing=1>
<tr>
<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>第<%=rs.getCurrentPageIndex()%>页 / 共<%=rs.getPageCount()%>页</font></td>
<td height=20 align=center>
<%
if(rs.getCurrentPageIndex() != rs.getFirstPageId())
	out.println("<font face=webdings color=#06b020>4</font><a href=topoList.jsp?pageid="+rs.getFirstPageId()+">首页</a>");
%>
<%
if(rs.getCurrentPageIndex() != rs.getFirstPageId())
	out.println("<font face=webdings color=#06b020>4</font><a href=topoList.jsp?pageid="+rs.getPreviewPageId()+">上一页</a>");
%>
<%
if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
	out.println("<font face=webdings color=#06b020>4</font><a href=topoList.jsp?pageid="+rs.getNextPageId()+">下一页</a>");
%>
<%
if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
	out.println("<font face=webdings color=#06b020>4</font><a href=topoList.jsp?pageid="+rs.getLastPageId()+">末页</a>");
%>
</td>
<td></td>
<td></td>
</table>
</form>
</body>
</html>