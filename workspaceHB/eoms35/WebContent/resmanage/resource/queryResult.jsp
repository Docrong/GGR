<%@page contentType="text/html;charset=gb2312"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.common.util.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<%
/**
*@ Eoms_Resmanage (for eoms)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ×ÊÁÏ²éÑ¯Ä£¿é
*@ author wuzongxian
*@ version 1.0
*@ date    2003-11-10(update)
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
<title>·ûºÏÌõ¼þµÄ²éÑ¯½á¹û</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css"
</head>
<body>
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
//ÓÃÓÚÅÅÐò
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
	}
}
//get ÏÔÊ¾ÁÐ±íµÄÁÐÃû
disVec= Entity.getdisQueryCol(colVec);
String disName = Entity.getdisName(typeid);
String strSql="";
try
{
	//µÃµ½²éÑ¯sqlÓï¾ä
	strSql = Entity.getQuerySql(colVec,typeid,manufid,cityid,"",1);
	//out.println("strSql is::"+strSql);
}catch(Exception e)
{
	out.println("exception ::"+e.getMessage());
}
%>
<%
		VectorRS rs = new VectorRS();
		int everypagenum=15;
		rs.setPageCapacity(everypagenum);						//Ã¿Ò³ÏÔÊ¾¼ÇÂ¼Êý¾Ý¸öÊý
		rs.setRS(strSql);
		rs.setCurrentPageIndex(pageid);
%>
	<table border="0" width="80%" cellspacing="1" cellpadding="1" align=center>
	<td align=center class="table_title">²éÑ¯³öµÄÉè±¸ÊýÁ¿Îª<%=rs.getRowCount()%>
	</td>
	</table>
	<br><br>
	<table class="table_show" border="0" width="80%" cellspacing="1" cellpadding="1" align=center>
	<tr class="td_label">
	<%
	/* sort */
	//out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href=javascript:goorder("+"'cc_name',"+tmpflag+")>"+disName+"</font></b></td>");
	out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(disName)+"</font></b></td>");
	out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>ËùÔÚµØÊÐ</font></b></td>");
	//out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>Éè±¸³§¼Ò</font></b></td>");
	for(int Col = 0; Col < disVec.size(); Col ++)
	{
		SysColIndex = (syscolindex)disVec.get(Col);
		out.println("<td align=center class=ttTable><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(SysColIndex.getCc_name())+"</font></b>");
		out.println("</td>");
	}
	%>
	</tr>
	<%
	/******************  ¹¹Ôì·ÖÒ³ÏÔÊ¾  *************/
	if(rs.getRowCount() >= 1)
	{
		for(int i = 0; i < rs.getCurrentPageRowNum(); i++)
		{
			out.println("<tr bgcolor="+((i%2 == 0)?"#eaeaea":"#eeeeee")+">");
			//out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+rs.getString(1)+"</font></td>");
			out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<a href=../edit/editDetail.jsp?id="+rs.getString(1)+"&tabname="+typeid+">"+StaticMethod.dbNull2String(rs.getString(2))+""+"</font></td>");
			out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(rs.getString(3))+"</font></td>");
			out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(rs.getString(4))+"</font></td>");
			for (int col=0;col<disVec.size();col++)
			{
				String colValue = StaticMethod.dbNull2String(rs.getString(col+5));
				
				out.println("<td align=center class=td><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+colValue+"</font></td>");
			}
			rs.next();
			out.println("</tr>");
		}
	}
	%>
<table width=100%>
<tr>
<td height=20 align=center width=40></td>
<%if (rs.getRowCount()>0) {%>
<td height=20 align=center width=60>
<a href="javascript:exportToFile()">µ¼³öExcel</a>
</td>
<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>µÚ<%=rs.getCurrentPageIndex()%>Ò³ / ¹²<%=rs.getPageCount()%>Ò³ / ¹²<%=rs.getRowCount()%>Ìõ¼ÇÂ¼ </font></td>
<%}%>
<td height=20 align=center>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
	 out.println("<font face=webdings color=#06b020>4</font><a href='javascript:firstpage()'>Ê×Ò³</a>");%>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
	  out.println("<font face=webdings color=#06b020>4</font><a href='javascript:previewpage()'>ÉÏÒ»Ò³</a>");%>
<%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > everypagenum)
	  out.println("<font face=webdings color=#06b020>4</font><a href='javascript:nextpage()'>ÏÂÒ»Ò³</a>");%>
<%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > everypagenum)
	  out.println("<font face=webdings color=#06b020>4</font><a href='javascript:lastpage()'>Ä©Ò³</a>");%>
</td>
<td></td>
<td></td>
</table>
</table>
<!-- <form name=excel method=post action='exportExcel.jsp'>
 --><input type=hidden name=sql value="<%=strSql%>">
<input type=hidden name=type value='<%=typeid%>'>
<input type=hidden name="orderColName" value='<%=orderColName%>'>
<input type=hidden name="orderflag" value='<%=tmpflag%>'>
<% 
  if (colid!=null)
  {
  for (int i=0;i<colid.length;i++)
  {
%>
<input type=hidden name=colid value='<%=colid[i]%>'>
<input type=hidden name="fieldname" value='<%=colvalue[i]%>'>
<input type=hidden name="disp" value='<%=disflag[i]%>'>
<%}}%>
</form>
<script language=javascript>
function  goorder(orderCol,orderflag)
{
	excel.action="queryResult.jsp?id=<%=typeid%>&classtype=<%=classid%>&typeid=<%=typeid%>&manuf=<%=manufid%>&city=<%=cityid%><%=arg%>&orderColName="+orderCol+"&orderflag="+orderflag;
	excel.submit();
}
function firstpage()
{
	excel.action="queryResult.jsp?id=<%=typeid%>&pageid=<%=rs.getFirstPageId()%>&classtype=<%=classid%>&typeid=<%=typeid%>&manuf=<%=manufid%>&city=<%=cityid%><%=arg%>&orderColName=<%=orderColName%>&orderflag=<%=orderflag%>";
	excel.submit();
}
function nextpage()
{
	excel.action="queryResult.jsp?id=<%=typeid%>&pageid=<%=rs.getNextPageId()%>&classtype=<%=classid%>&typeid=<%=typeid%>&manuf=<%=manufid%>&city=<%=cityid%><%=arg%>&orderColName=<%=orderColName%>&orderflag=<%=orderflag%>";
	excel.submit();
}
function previewpage()
{
	excel.action="queryResult.jsp?id=<%=typeid%>&pageid=<%=rs.getPreviewPageId()%>&classtype=<%=classid%>&typeid=<%=typeid%>&manuf=<%=manufid%>&city=<%=cityid%><%=arg%>&orderColName=<%=orderColName%>&orderflag=<%=orderflag%>";
	excel.submit();
}
function lastpage()
{
	excel.action="queryResult.jsp?id=<%=typeid%>&pageid=<%=rs.getLastPageId()%>&classtype=<%=classid%>&typeid=<%=typeid%>&manuf=<%=manufid%>&city=<%=cityid%><%=arg%>&orderColName=<%=orderColName%>&orderflag=<%=orderflag%>";
	excel.submit();
}
function exportToFile()
{
	excel.action="exportToExcel.jsp?id=<%=typeid%>&classtype=<%=classid%>&typeid=<%=typeid%>&manuf=<%=manufid%>&city=<%=cityid%><%=arg%>&orderColName=<%=orderColName%>&orderflag=<%=orderflag%>";
	excel.submit();
}
function closeW()
{
	window.close();
}
</script>
<p align=center><font size=2><a href="javascript:closeW()">¹Ø±Õ</a></font></p>
</body>
</html>
