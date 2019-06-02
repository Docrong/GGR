<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.common.util.*"%>
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
String fileName="queryResult";
response.setContentType("application/vnd.ms-excel; charset=gb2312"); 
response.setHeader("Content-Disposition: ","attachment; filename="+fileName);
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
request.setCharacterEncoding("GBK");
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
entityoperate Entity = new entityoperate();
syscolindex SysColIndex = new syscolindex();
String[] colid    = request.getParameterValues("colid");
String[] colvalue = request.getParameterValues("fieldname");
String[] disflag  = request.getParameterValues("disp");
Vector colVec = new Vector();
Vector colVectemp = new Vector();
Vector disVec = new Vector();
if (colid!=null)
{
	for(int i=0;i<colid.length;i++)
	{
		syscolindex colindex = new syscolindex();
		colindex.setPi_id(Integer.parseInt(colid[i]));
		colindex.setCc_value(colvalue[i]);
		colindex.setCi_dis_flag(Integer.parseInt(disflag[i]));
		colVec.addElement(colindex);
		colVectemp = Entity.getColById(colid[i],Integer.parseInt(disflag[i]));
	}
}
//out.println("colVec.size():::"+colVec.size());
disVec= Entity.getdisQueryCol(colVectemp);
//out.println("disVec.size()::"+disVec.size());
String disName = Entity.getdisName(typeid);
String strSql="";
try
{
	//strSql = Entity.getQuerySql(colVec, "21", "0", "31", "", 1);
	strSql = Entity.getQuerySql(colVec,typeid,manufid,cityid,"",1);
}catch(Exception e)
{
	out.println("exception ::"+e.getMessage());
}
%>
<%
		VectorRS rs = new VectorRS();
		//int everypagenum=2;
		//rs.setPageCapacity(everypagenum);						//每页显示记录数据个数
		rs.setRS(strSql);
		//rs.setCurrentPageIndex(pageid);
%>
	<table bgcolor=#dddddd width='100%' border=0 cellspacing=1 id="resultRs">
	<tr>
	<%
	out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String("序号")+"</font></b></td>");
	out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(disName)+"</font></b></td>");
	out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String("所在地市")+"</font></b></td>");
	out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String("设备厂家")+"</font></b></td>");
	for(int Col = 0; Col < disVec.size(); Col ++)
	{
		SysColIndex = (syscolindex)disVec.get(Col);
		out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(SysColIndex.getCc_name())+"</font></b>");
		out.println("</td>");
	}
	%>
	</tr>
	<%
	/******************  构造分页显示  *************/
	//if(rs.getRowCount() >= 1)
	//{
		for(int i = 0; i < rs.getRowCount(); i++)
		{
			out.println("<tr bgcolor="+((i%2 == 0)?"#eaeaea":"#eeeeee")+">");
			out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+ StaticMethod.dbNull2String(rs.getString(1))+"</font></td>");
			out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+ StaticMethod.dbNull2String(rs.getString(2))+""+"</font></td>");
			out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+ StaticMethod.dbNull2String(rs.getString(3))+"</font></td>");
			out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+ StaticMethod.dbNull2String(rs.getString(4))+"</font></td>");
			for (int col=0;col<disVec.size();col++)
			{
				String colValue = StaticMethod.dbNull2String(rs.getString(col+5));
				out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+colValue+"</font></td>");
			}
			rs.next();
			out.println("</tr>");
		}
	//}

    out.println("<tr><td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String("合计：")+rs.getRowCount()+"</font></td></tr>");
	%>

</table>
<!-- <form name=excel method=post action='exportExcel.jsp'>
 -->
</form>
</body>
</html>
