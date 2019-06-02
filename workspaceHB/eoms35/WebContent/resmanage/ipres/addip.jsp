<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
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
	int ipproject=0;
	int	search_type = 0;
	String ipaddress = "";
	int city = 0;
	if (request.getParameter("search_type")!=null)
		search_type = Integer.parseInt(request.getParameter("search_type"));
	if (request.getParameter("ipaddress")!=null)
		ipaddress = request.getParameter("ipaddress");
	if (request.getParameter("ipproject")!=null)
		ipproject = Integer.parseInt(request.getParameter("ipproject"));
	if (request.getParameter("city")!=null)
		city = Integer.parseInt(request.getParameter("city"));
	//操作类型
	int optType = 1;
	int fi_segment = 0;
	String beginip="";
	String endip = "";
	if (request.getParameter("parentid")!=null)
	 fi_segment = Integer.parseInt(request.getParameter("parentid"));
	if (request.getParameter("beginip")!=null)
	 beginip = request.getParameter("beginip");
	String[] beginiptmp = StaticMethod.TokenizerString2(beginip,".");
	if (request.getParameter("endip")!=null)
	 endip = request.getParameter("endip");
  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>IP地址段增加</title>
<script language="javascript">
</script>
</head>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<form id=form1 name=form1 action="IpSave.jsp" method=post>
<table border="0" width="60%" cellspacing="1" bgcolor="#709ED5" align=center>
<tr>
<td width="30%" height="25" bgcolor="#d7dae1" align=right>所属网段:</td>
<td width="70%" height="25" bgcolor="#E5EDF8">&nbsp;<%=beginip%>&nbsp;---->&nbsp;<%=endip%>&nbsp;</td>
</tr>
<tr>
<td width="30%" height="25" bgcolor="#d7dae1" align=right>IP地址:</td>
<td width="70%" height="25" bgcolor="#E5EDF8">
	<% for (int i=0;i<4;i++){ if (i<3){%>
		<input type="text" name="ip<%=i%>" size="5"  value='<%=beginiptmp[i]%>' readonly maxlength="3"  ><b>.</b>
	<%} else{%>
		<input type="text" name="ip<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)">
	<%}}%><font color="#FF0000">**</font>
</td>
</tr>
<tr>
  <td width="30%" height="25" bgcolor="#d7dae1" align=right>地址状态:</td>
  <%
  IpresOpt ipopt = new IpresOpt();
  IpPrjmodel prjmodel = new IpPrjmodel();
  Vector stVec = new Vector();
  stVec = ipopt.getIpprjList("eip_ipstate",0);
  out.println("<td width=70% height=25 bgcolor=#E5EDF8><select name=ipstate>");
  for(int a = 0; a < stVec.size(); a ++)
  {
	prjmodel = (IpPrjmodel)stVec.get(a);
	out.println("<option value="+prjmodel.getId()+">"+StaticMethod.dbNull2String(prjmodel.getName())+"</option>");
  }
  out.println("</select></td>");
%>
</tr>
<tr>
  <td width="30%" height="25" bgcolor="#d7dae1" align=right>地址用途:</td>
  <td width="70%" height="25" bgcolor="#E5EDF8">
	<textarea name="addinfor" cols="30" rows="4"></textarea>
  </td>
</tr>
<tr>
  <td width="70%" height="25" bgcolor="#E5EDF8" colspan=2 align=center>
  <input type="button" value="保存" onclick='formsubmit()'>
  <input type="reset" value="重置">
  <input type="button" value="返回" onclick="javascript:history.back()">
  </td>
</tr>
</table>
<input type="hidden" name="optType" value=<%=optType%>>
<input type="hidden" name="parentid" value=<%=fi_segment%>>
<input type="hidden" name="search_type" value=<%=search_type%>>
<input type="hidden" name="ipproject" value=<%=ipproject%>>
<input type="hidden" name="city" value=<%=city%>>
<input type="hidden" name="ipaddress" value=<%=ipaddress%>>
</form>
<script language="JavaScript">
function formsubmit()
{
	if (form1.ip3.value=="")
	{
		alert("IP地址不能为空");
		return;
	}
	form1.submit();
}
function IsDigit(cCheck)
{
	return (('0'<=cCheck) && (cCheck<='9'));
}
function IsNumber(str)
{
	for (var nIndex=0; nIndex<str.length; nIndex++)
	{
		cCheck = str.charAt(nIndex);
		if (!(IsDigit(cCheck)))
			return false;
	}
   return true;
}

function allTrim(ui)
{
	var notValid=/\s/;
	while(notValid.test(ui))
		ui=ui.replace(notValid,"");
	return ui;
}
function checknum(field)
{
	if (!IsNumber(field.value))
	{
		alert("请输入数字!");
		field.value="";
		field.focus();
		return;
	}
    else
	{
		if (field.value>255 || field.value<0)
		{
			alert("必须在0~255之间");
		    field.value="";
			field.focus();
			return;
		}
	}
}
</script>

</body>