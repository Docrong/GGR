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
*@ ip��Դ����ģ��
*@ author Wuzongxian
*@ version 1.0
*@ date    2004-03-05
**/
%>
<%
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>IP��ַ����</title>
<base target = "searchframe">
</head>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<form id=form1 name=form1 action="list.jsp" method=post>
<table border="0" width="60%" cellspacing="1" bgcolor="#709ED5" align=center>
  <tr>
    <td width="106%" colspan="8" height="25" bgcolor="#EEECED" align="center"><b>����IP</b></td>
  </tr>
  <tr>
    <td width="30%" height="25" bgcolor="#d7dae1" align=right>��ѯ��ʽ:</td>
    <td width="70%" height="25" bgcolor="#E5EDF8">
		<select name="search_type">
		<option value="1">���β�ѯ</option>
		<option value="2">����IP��ַ��ѯ</option>
		</select>
	</td>
  </tr>
  <tr>
  <td width="30%" height="25" bgcolor="#d7dae1" align=right>��������:</td>
  <%
  queryRes qre = new queryRes();
  Vector ctV = new Vector();
  ctV = qre.getCity(0);
  cityClass ct = new cityClass();
  out.println("<td width=70% height=25 bgcolor=#E5EDF8><select name=city>");
  out.println("<option value=0>--ȫ��--</option>");
  for(int a = 0; a < ctV.size(); a ++)
  {
	ct = (cityClass)ctV.get(a);
	out.println("<option value="+ct.getId()+">"+StaticMethod.dbNull2String(ct.getName())+"</option>");
  }
  out.println("</select></td>");
%>
 </tr>
 <tr>
  <td width="30%" height="25" bgcolor="#d7dae1" align=right>������Ŀ:</td>
  <%
  IpresOpt ipopt = new IpresOpt();
  Vector prjVec = new Vector();
  prjVec = ipopt.getIpprjList("eip_ipproject",0);
  IpPrjmodel prjmodel = new IpPrjmodel();
  out.println("<td width=70% height=25 bgcolor=#E5EDF8><select name=ipproject>");
  out.println("<option value=0>--ȫ��--</option>");
  for(int a = 0; a < prjVec.size(); a ++)
  {
	prjmodel = (IpPrjmodel)prjVec.get(a);
	out.println("<option value="+prjmodel.getId()+">"+StaticMethod.dbNull2String(prjmodel.getName())+"</option>");
  }
  out.println("</select></td>");
%>
 </tr>
  <tr>
   <td width="30%" height="25" bgcolor="#d7dae1" align=right>IP��ַ:</td>
   <td width="70%" height="25" bgcolor="#E5EDF8"> 
     <% for (int i=0;i<4;i++){ if (i<3){%>
		<input type="text" name="ip<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)"><b>.</b>
	<%} else{%>
		<input type="text" name="ip<%=i%>" size="5" maxlength="3"  onkeyup="checknum(this)">
	<%}}%>
     (ip��ַ��Ϊ��)
      </td>
    </tr>
    <tr> 
      <td colspan=4 align=center bgcolor="#E5EDF8"> 
        <input id=button type=button value="��������" name=button Onclick="javascript:addnet();">
        &nbsp;&nbsp;&nbsp;&nbsp; 
        <input  type=submit value="��ѯ">
     </td>
    </tr>
  </table>
</form>
<script language="javascript">
function addnet()
{
 window.parent.searchframe.location.href="addnetseg.jsp"
}
function goSearch()
{
	//window.parent.searchframe.location = 'list.jsp';
     form1.action="list.jsp";
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
		alert("����������!");
		field.value="";
		field.focus();
		return;
	}
    else
	{
		if (field.value>255 || field.value<0)
		{
			alert("������0~255֮��");
		    field.value="";
			field.focus();
			return;
		}
	}
}
</script>
</body>
</html>