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
  //操作类型
  int optType = 1;
  //if (request.getParameter("optType")
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
<form id=form1 name=form1 action="NetsegSave.jsp" method=post>
<table border="0" width="60%" cellspacing="1" bgcolor="#709ED5" align=center>
<tr>
<td width="30%" height="25" bgcolor="#d7dae1" align=right>起始地址:</td>
<td width="70%" height="25" bgcolor="#E5EDF8">
    <% for (int i=0;i<4;i++){ if (i<3){%>
		<input type="text" name="beginip<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)"><b>.</b>
	<%} else{%>
		<input type="text" name="beginip<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)">
	<%}}%><font color="#FF0000">**</font>
</td>
</tr>
<tr>
<td width="30%" height="25" bgcolor="#d7dae1" align=right>终止地址:</td>
<td width="70%" height="25" bgcolor="#E5EDF8">
	<% for (int i=0;i<4;i++){ if (i<3){%>
		<input type="text" name="endip<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)"><b>.</b>
	<%} else{%>
		<input type="text" name="endip<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)">
	<%}}%><font color="#FF0000">**</font>
</td>
</tr>
<tr>
<td width="30%" height="25" bgcolor="#d7dae1" align=right>子网掩码:</td>
<td width="70%" height="25" bgcolor="#E5EDF8">
	<% for (int i=0;i<4;i++){ if (i<3){%>
	
	<input type="text" name="mask<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)"><b>.</b>
	<%} else{%>
		<input type="text" name="mask<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)">
	<%}}%><font color="#FF0000">**</font>
</td>
</tr>
<tr>
  <td width="30%" height="25" bgcolor="#d7dae1" align=right>所属地市:</td>
  <%
  queryRes qre = new queryRes();
  Vector ctV = new Vector();
  ctV = qre.getCity(0);
  cityClass ct = new cityClass();
  out.println("<td width=70% height=25 bgcolor=#E5EDF8><select name=city>");
  for(int a = 0; a < ctV.size(); a ++)
  {
	ct = (cityClass)ctV.get(a);
	out.println("<option value="+ct.getId()+">"+StaticMethod.dbNull2String(ct.getName())+"</option>");
  }
  out.println("</select></td>");
%>
 </tr>
 <tr>
  <td width="30%" height="25" bgcolor="#d7dae1" align=right>所属项目:</td>
  <%
  IpresOpt ipopt = new IpresOpt();
  Vector prjVec = new Vector();
  prjVec = ipopt.getIpprjList("eip_ipproject",0);
  IpPrjmodel prjmodel = new IpPrjmodel();
  out.println("<td width=70% height=25 bgcolor=#E5EDF8><select name=ipproject>");
  for(int a = 0; a < prjVec.size(); a ++)
  {
	prjmodel = (IpPrjmodel)prjVec.get(a);
	out.println("<option value="+prjmodel.getId()+">"+StaticMethod.dbNull2String(prjmodel.getName())+"</option>");
  }
  out.println("</select></td>");
%>
</tr>
<tr>
  <td width="30%" height="25" bgcolor="#d7dae1" align=right>地址状态:</td>
  <%
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
</form>
<script language="JavaScript">
function formsubmit()
{
	//alert(form1.beginip0.value);
	if (form1.beginip0.value=="" || form1.beginip1.value=="" || form1.beginip2.value=="" || form1.beginip3.value=="")
	{
		alert("开始地址不能为空");
		return;
	}
	if (form1.endip0.value=="" || form1.endip1.value=="" || form1.endip2.value=="" || form1.endip3.value=="")
	{
		alert("终止地址不能为空");
		return;
	}
	if (form1.mask0.value=="" || form1.mask1.value=="" || form1.mask2.value=="" || form1.mask3.value=="")
	{
		alert("子网掩码不能为空");
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