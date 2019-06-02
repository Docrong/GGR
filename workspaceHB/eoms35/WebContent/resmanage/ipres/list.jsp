<%@page contentType="text/html;charset=gb2312"%>

<%@page import="java.util.*"%>

<%@page import="mcs.common.db.*"%>

<%@page import="com.boco.eoms.common.util.*"%>

<%

/**

*@ AHEOMS (resmanage)

*@ Copyright : (c) 2004

*@ Company : BOCO.

*@ ip资源管理模块

*@ author Wuzongxian

*@ version 1.0

*@ date    2004-03-05

**/

%>

<%

	int	search_type = 0;

	if (request.getParameter("search_type")!=null)

		search_type = Integer.parseInt(request.getParameter("search_type"));

	int  city        = 0;

	if (request.getParameter("city")!=null)

		Integer.parseInt(request.getParameter("city"));

	int  ipproject   = 0;

	if (request.getParameter("ipproject")!=null)

		ipproject = Integer.parseInt(request.getParameter("ipproject"));

	String ipaddress = "";

	for (int i=0;i<4;i++)

	{

		String tmpip = request.getParameter("ip"+i);

		if (i==3)

		{

			if (tmpip!=null && !tmpip.equals(""))

				ipaddress+=tmpip;

		}

		else

		{

			if (tmpip!=null && !tmpip.equals(""))

				ipaddress+=tmpip+".";

		}

	}

	if (request.getParameter("ipaddress")!=null)

		ipaddress = request.getParameter("ipaddress");

	int pageid;

	if(request.getParameter("pageid") != null)

		pageid = Integer.parseInt(request.getParameter("pageid"));

	else

		pageid=1;

%>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<title>IP地址管理</title>

<script language="javascript">

</script>

</head>

<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<form id=form1 name=form1 action="" method=post>

<table border="0" width="100%" cellspacing="1" bgcolor="#709ED5" align=center>

<tr>

   <%if (search_type==1){%>

    <td bgcolor="#E5EDF8" align="center">起始地址</td>

    <td bgcolor="#E5EDF8" align="center">终止地址</td>

    <td bgcolor="#E5EDF8" align="center">子网掩码</td>

	<td bgcolor="#E5EDF8" align="center">所属地市</td>

	<td bgcolor="#E5EDF8" align="center">所属项目</td>

	<td bgcolor="#E5EDF8" align="center">状态</td> 

	<td bgcolor="#E5EDF8" align="center"><font color="#cc0000">查看</font></td>

    <td bgcolor="#E5EDF8" align="center"><font color="#cc0000">修改</font></td>

    <td bgcolor="#E5EDF8" align="center"><font color="#cc0000">删除</font></td>

	<td bgcolor="#E5EDF8" align="center"><font color="#cc0000">新增IP</font></td>

	<%} else {%>

	<td bgcolor="#E5EDF8" align="center">所属网段</td>

    <td bgcolor="#E5EDF8" align="center">ip地址</td>

    <td bgcolor="#E5EDF8" align="center">用途</td>

	<td bgcolor="#E5EDF8" align="center">状态</td> 

	<td bgcolor="#E5EDF8" align="center"><font color="#cc0000">查看</font></td>

    <td bgcolor="#E5EDF8" align="center"><font color="#cc0000">修改</font></td>

    <td bgcolor="#E5EDF8" align="center"><font color="#cc0000">删除</font></td>

	<%}%>

 

</tr>

<%

	String strSql = "";

	//按网段查询

	if (search_type==1)

	{

		strSql = "select a.pi_id,a.cc_beginip,a.cc_endip,a.cc_mask,b.cc_name,c.cc_name,d.cc_name";

		strSql+=" from eip_ipsegment a,edm_city b,eip_ipproject c,eip_ipstate d ";

		strSql+=" where a.fi_city=b.pi_id and a.fi_project=c.pi_id and a.fi_state=d.pi_id ";

		if (city!=0)

			strSql+=" and a.fi_city="+city;

		if (ipproject!=0)

			strSql+=" and a.fi_project="+ipproject;

		if (!ipaddress.equals(""))

			strSql+=" and (a.cc_beginip like '%"+ipaddress+"%' or a.cc_endip like '%"+ipaddress+"%' )";

		strSql+=" order by a.cc_beginip";

	}

	//具体ip地址查询

	if (search_type==2)

	{

		strSql = "select a.pi_id,b.cc_beginip,b.cc_endip,a.cc_ip,cc_addresinfo,c.cc_name,a.fi_netsegment from eip_ipadd a,outer eip_ipsegment b,eip_ipstate c where a.fi_netsegment=b.pi_id and a.fi_state=c.pi_id ";

		if (!ipaddress.equals(""))

			strSql+=" and a.cc_ip like '%"+ipaddress+"%'";

		strSql+=" order by a.cc_ip";

	}

	//out.println(strSql);

	VectorRS rs = new VectorRS();

	rs.setPageCapacity(10);						//每页显示记录数据个数

	rs.setRS(strSql);

	rs.setCurrentPageIndex(pageid);

	if(rs.getRowCount() >= 1)

	{

		for(int i = 0; i < rs.getCurrentPageRowNum(); i ++)

		{

			out.println("<tr>");

			if (search_type==1)

			{

				String beginip = rs.getString(2);

				String endip   = rs.getString(3);

				out.println("<td bgcolor='#E5EDF8' align='center'>"+beginip+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'>"+endip+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'>"+rs.getString(4)+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'>"+StaticMethod.dbNull2String(rs.getString(5))+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'>"+StaticMethod.dbNull2String(rs.getString(6))+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'>"+StaticMethod.dbNull2String(rs.getString(7))+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'><a href='UpdateIpseg.jsp?id="+rs.getString(1)+"&optType=4'><img src='../images/view.gif' alt='查看' border=0></a></td>");

				out.println("<td bgcolor='#E5EDF8' align='center'><a href='UpdateIpseg.jsp?id="+rs.getString(1)+"&optType=2&search_type="+search_type+"&ipproject="+ipproject+"&city="+city+"&ipaddress="+ipaddress+"'><img src='../images/update.gif' alt='修改' border=0></a></td>");   

				out.println("<td bgcolor='#E5EDF8' align='center'><a href='javascript:del("+rs.getString(1)+")'><img src='../images/del.gif' alt='删除' border=0></a></td>");

				out.println("<td bgcolor='#E5EDF8' align='center'><a href='addip.jsp?parentid="+rs.getString(1)+"&beginip="+beginip+"&endip="+endip+"&optType=1&search_type="+search_type+"&ipproject="+ipproject+"&city="+city+"&ipaddress="+ipaddress+"'>新增IP<a/></td>");

			}

			if (search_type==2)

			{

				String beginiptmp = StaticMethod.dbNull2String(rs.getString(2));

				String endiptmp   = StaticMethod.dbNull2String(rs.getString(3));

				String fi_segment = rs.getString(7);

				out.println("<td bgcolor='#E5EDF8' align='center'>"+beginiptmp+" --> "+endiptmp+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'>"+rs.getString(4)+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'>"+StaticMethod.dbNull2String(rs.getString(5))+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'>"+StaticMethod.dbNull2String(rs.getString(6))+"</td>");

				out.println("<td bgcolor='#E5EDF8' align='center'><a href='UpdateIp.jsp?id="+rs.getString(1)+"&optType=4&beginip="+beginiptmp+"&endip="+endiptmp+"'><img src='../images/view.gif' alt='查看' border=0></a></td>");

				out.println("<td bgcolor='#E5EDF8' align='center'><a href='UpdateIp.jsp?id="+rs.getString(1)+"&beginip="+beginiptmp+"&endip="+endiptmp+"&optType=2&search_type="+search_type+"&ipaddress="+ipaddress+"'><img src='../images/update.gif' alt='修改' border=0></a></td>");   

				out.println("<td bgcolor='#E5EDF8' align='center'><a href='javascript:delip("+rs.getString(1)+")'><img src='../images/del.gif' alt='删除' border=0></a></td>");

			}

			rs.next();

			out.println("</tr>");

		}

	}

%>

</table>

<table>

<tr>

<td></td>

<td></td>

<td></td>

<td></td>

<td></td>

<%if (rs.getRowCount()>0){%>

<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>第<%=rs.getCurrentPageIndex()%>页 / 共<%=rs.getPageCount()%>页</font></td>

<%}%>

<td height=20 align=right>

<%

if(rs.getCurrentPageIndex() != rs.getFirstPageId())

	out.println("<font face=webdings color=#06b020>4</font><a href=operatorList.jsp?pageid="+rs.getFirstPageId()+"&search_type="+search_type+"&city="+city+"&ipproject="+ipproject+"&ipaddress="+ipaddress+"首页</a>");

%>

<%

if(rs.getCurrentPageIndex() != rs.getFirstPageId())

	out.println("<font face=webdings color=#06b020>4</font><a href=list.jsp?pageid="+rs.getPreviewPageId()+"&search_type="+search_type+"&city="+city+"&ipproject="+ipproject+"&ipaddress="+ipaddress+">上一页</a>");

%>

<%

if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 10)

	out.println("<font face=webdings color=#06b020>4</font><a href=list.jsp?pageid="+rs.getNextPageId()+"&search_type="+search_type+"&city="+city+"&ipproject="+ipproject+"&ipaddress="+ipaddress+">下一页</a>");

%>

<%

if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 10)

	out.println("<font face=webdings color=#06b020>4</font><a href=list.jsp?pageid="+rs.getLastPageId()+"&search_type="+search_type+"&city="+city+"&ipproject="+ipproject+"&ipaddress="+ipaddress+">末页</a>");

%>

</td>

</tr>

</table>

<input type="hidden" name="search_type" value=<%=search_type%>>

<input type="hidden" name="city" value=<%=city%>>

<input type="hidden" name="ipproject" value=<%=ipproject%>>

<input type="hidden" name="ipaddress" value=<%=ipaddress%>>

</form>
<script language="javascript">
function del(currentid)
{
	if ( confirm("你确定要删除该地址段吗？"))
      {
	   form1.action="NetsegSave.jsp?id="+currentid+"&optType=3";
       form1.submit();
      }
}
function delip(currentid)
{
	if ( confirm("你确定要删除该IP地址吗？"))
      {
	   form1.action="IpSave.jsp?id="+currentid+"&optType=3";
       form1.submit();
      }
}
</script>

</body>