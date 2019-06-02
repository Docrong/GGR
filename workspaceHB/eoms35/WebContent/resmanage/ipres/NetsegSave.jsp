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
	int pi_id = 0;
	if (request.getParameter("id")!=null)
      pi_id = Integer.parseInt(request.getParameter("id"));
   String beginip = "";
   String endip = "";
   String mask  = "";
	for (int i=0;i<4;i++)
	{
		String tmpbeginip = request.getParameter("beginip"+i);
		String tmpendip = request.getParameter("endip"+i);
		String tmpmaskip = request.getParameter("mask"+i);
		if (i==3)
		{
			if (tmpbeginip!=null && !tmpbeginip.equals(""))
				beginip+=tmpbeginip;
			if (tmpendip!=null && !tmpendip.equals(""))
				endip+=tmpendip;
			if (tmpmaskip!=null && !tmpmaskip.equals(""))
				mask+=tmpmaskip;
		}
		else
		{
			if (tmpbeginip!=null && !tmpbeginip.equals(""))
				beginip+=tmpbeginip+".";
			if (tmpendip!=null && !tmpendip.equals(""))
				endip+=tmpendip+".";
			if (tmpmaskip!=null && !tmpmaskip.equals(""))
				mask+=tmpmaskip+".";
		}
	}
	int		city        = 0;
	if (request.getParameter("city")!=null)
		city = Integer.parseInt(request.getParameter("city"));
	int	    ipproject   = 0;
	if (request.getParameter("ipproject")!=null)
		ipproject = Integer.parseInt(request.getParameter("ipproject"));
	int		ipstate		= 0;
	if (request.getParameter("ipstate")!=null)
		ipstate = Integer.parseInt(request.getParameter("ipstate"));
	String  ipaddinfor  = request.getParameter("addinfor");
	//===========
	int	search_type = 0;
	String ipaddress = "";
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
	IpaddModel ipsegModel = new IpaddModel();
	if (optType==2 || optType==3)
	{
		ipsegModel.setPi_id(pi_id);
	}
	if (optType!=3)
	{
		ipsegModel.setCc_beginip(beginip);
		ipsegModel.setCc_endip(endip);
		ipsegModel.setCc_mask(mask);
		ipsegModel.setFi_city(city);
		ipsegModel.setFi_project(ipproject);
		ipsegModel.setFi_state(ipstate);
		ipsegModel.setCc_useinfo(ipaddinfor);
	}
	if (ipresopt.IpsegSave(ipsegModel,optType))
	{
		if (optType==1)
		{
			out.println("<script>alert('插入地址段成功！');location='addnetseg.jsp';</script>");
		}
		if (optType==2)
		{
			out.println("<script>alert('修改地址段成功！');location='list.jsp?search_type="+search_type+"&ipproject="+ipproject+"&city="+city+"&ipaddress="+ipaddress+"';</script>");
		}
		if (optType==3)
		{
			out.println("<script>alert('删除地址段成功！');location='list.jsp?search_type="+search_type+"&ipproject="+ipproject+"&city="+city+"&ipaddress="+ipaddress+"';</script>");
		}
	}
	else
		out.println("<script>alert('失败！')histor.back();</script>");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>IP地址段保存</title>
<script language="javascript">
</script>
</head>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<form id=form1 name=form1 action="" method=post>
<table border="0" width="100%" cellspacing="1" bgcolor="#709ED5" align=center>
</table>
</form>
</body>