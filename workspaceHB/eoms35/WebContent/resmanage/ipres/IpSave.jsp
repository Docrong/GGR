<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
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
	
     //操作类型
	int optType = 0;
	if (request.getParameter("optType")!=null)
      optType = Integer.parseInt(request.getParameter("optType"));
	int fi_segment = 0;
	if (request.getParameter("parentid")!=null)
	 fi_segment = Integer.parseInt(request.getParameter("parentid"));
	int pi_id = 0;
	if (request.getParameter("id")!=null)
      pi_id = Integer.parseInt(request.getParameter("id"));
   String ip = "";
	for (int i=0;i<4;i++)
	{
		String tmpid = request.getParameter("ip"+i);
		if (i==3)
		{
			if (tmpid!=null && !tmpid.equals(""))
				ip+=tmpid;
		}
		else
		{
			if (tmpid!=null && !tmpid.equals(""))
				ip+=tmpid+".";
		}
	}
	int		ipstate		= 0;
	if (request.getParameter("ipstate")!=null)
		ipstate = Integer.parseInt(request.getParameter("ipstate"));
	String  ipaddinfor  = request.getParameter("addinfor");
	//===========
	int ipproject=0;
	int	search_type = 0;
	String ipaddress = "";
	int city = 0;
	if (optType==1)
	{
		if (request.getParameter("search_type")!=null)
			search_type = Integer.parseInt(request.getParameter("search_type"));
		if (request.getParameter("ipaddress")!=null)
			ipaddress = request.getParameter("ipaddress");
		if (request.getParameter("ipproject")!=null)
			ipproject = Integer.parseInt(request.getParameter("ipproject"));
		if (request.getParameter("city")!=null)
			city = Integer.parseInt(request.getParameter("city"));
	}
	if (optType==2 || optType==3)
	{
		if (request.getParameter("search_type")!=null)
			search_type = Integer.parseInt(request.getParameter("search_type"));
		if (request.getParameter("ipaddress")!=null)
			ipaddress = request.getParameter("ipaddress");
	}
	//===========
	//set value to model
	IpresOpt ipresopt = new IpresOpt();
	IpModel ipModel = new IpModel();
	if (optType==2 || optType==3)
	{
		ipModel.setPi_id(pi_id);
	}
	if (optType!=3)
	{
		ipModel.setCc_ip(ip);
		ipModel.setFi_netsegment(fi_segment);
		ipModel.setFi_state(ipstate);
		ipModel.setCc_addresinfo(ipaddinfor);
	}
	if (ipresopt.IpSave(ipModel,optType))
	{
		if (optType==1)
		{
			out.println("<script>alert('插入IP地址成功！');location='list.jsp?search_type="+search_type+"&ipproject="+ipproject+"&city="+city+"&ipaddress="+ipaddress+"';</script>");
		}
		if (optType==2)
		{
			out.println("<script>alert('修改IP地址成功！');location='list.jsp?search_type="+search_type+"&ipaddress="+ipaddress+"';</script>");
		}
		if (optType==3)
		{
			out.println("<script>alert('删除IP地址成功！');location='list.jsp?search_type="+search_type+"&ipaddress="+ipaddress+"';</script>");
		}
	}
	else
		out.println("<script>alert('失败！');history.back();</script>");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>IP地址保存</title>
<script language="javascript">
</script>
</head>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<form id=form1 name=form1 action="" method=post>
<table border="0" width="100%" cellspacing="1" bgcolor="#709ED5" align=center>
</table>
</form>
</body>