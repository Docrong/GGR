<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.editsys.*"%>
<%
/**
*@ E-DIS (�Ĵ�ʡ)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ϵͳ���ά��
*@ version 1.0
**/
%>

<%
	String tabid = null;
	String len = "";
	String fieldtype = "";
	if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
		tabid = request.getParameter("tabid");
	else
		tabid = "0";
	int id = Integer.parseInt(request.getParameter("checked_id"));
	SysOpt so = new SysOpt();
    syscolindex SysColIndex = new syscolindex();
	Vector tmp = new Vector();
	tmp = so.getColInforByColId(tabid,id);
	if(tmp.size() != 0)
	{
		SysColIndex = (syscolindex)tmp.get(0);
%>

<html>
<head>
<title>�������ݱ���</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<script>
function showLength()
{
	var y = editsysform.fieldtype.value.indexOf("(");
	if(y != -1)
		editsysform.len.disabled = false;
	else
		editsysform.len.disabled = true;
}
</script>
<body bgcolor="#eeeeee" text="#000000" class="listStyle" onload="showLength()">
<br>
<br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#c6c6c6>
	<td align=center><font size=2 color=#000000></font></td>
	<td align=center><font size=2 color=#000000>���ݱ�������</font></td>
	<td align=center><font size=2 color=#000000>���ݱ�������</font></td>
	<td align=center><font size=2 color=#000000>����(������varchar��nchar)</font></td>
	<td align=center><font size=2 color=#000000>�Ƿ��Ϊ��</font></td>
	<td align=center><font size=2 color=#000000>�Ƿ�ο���</font></td>
	<td align=center><font size=2 color=#000000>�Ƿ����б�����ʾ</font></td>
	<td align=center><font size=2 color=#000000>�Ƿ�ֻ������</font></td>
</tr>
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000></font></td>
	<td align=center><input type=text name=fieldname value='<%=SysColIndex.getCc_name()%>'><input type=hidden name=cc_code value='<%=SysColIndex.getCc_code()%>'></td>
	<td align=center><select name=fieldtype onchange='showLength()'>
	<%
	esys es = new esys();
	if(es.bjVarChar(SysColIndex.getCc_type()))
		len = es.getIntegerOfType(SysColIndex.getCc_type());

	String tmptype = es.strjVarChar(SysColIndex.getCc_type());
	String [] type = es.getTypeList();
	for(int i = 0; i < type.length; i ++)
	{
		if(type[i].equals(tmptype))
			out.println("<option value='"+type[i]+"' selected>"+type[i]+"</option>");
		else
			out.println("<option value='"+type[i]+"'>"+type[i]+"</option>");
	}
	%></select></td>
	<td align=center>
	<%
		if(es.bjVarChar(SysColIndex.getCc_type()))
			out.println("<input type=text name=len value='"+len+"'>");
	else
		out.println("<input type=text name=len disabled>");
		%></td>
	<td align=center>
	<%
		if(!SysOpt.tabIsData(Integer.parseInt(tabid)))
		{
			if(SysColIndex.getCi_nul_flag() == 1)
				out.println("<select name=nulflag><option value=0 selected>��</option><option value=1>��</option>");
			else
				out.println("<select name=nulflag><option value=0>��</option><option value=1 selected>��</option>");
		}
		else
			out.println("<input type=hidden name=nulflag value='0'><select name='' disabled><option selected>��</option>");
			%>
	
	</select></td>
	<td align=center><select name=refflag><%
		if(SysColIndex.getCi_ref_flag() == 1)
			out.println("<option value=1 selected>��</option><option value=0>��</option>");
		else
			out.println("<option value=1>��</option><option value=0 selected>��</option>");
		%></select></td>
	<td align=center><select name=disflag><%
		if(SysColIndex.getCi_dis_flag() == 1)
			out.println("<option value=1 selected>��</option><option value=0>��</option>");
		else
			out.println("<option value=1>��</option><option value=0 selected>��</option>");
		%></select></td>
	<td align=center><select name=readflag><%
		if(SysColIndex.getCi_read_flag() == 1)
			out.println("<option value=1 selected>��</option><option value=0>��</option>");
		else
			out.println("<option value=1>��</option><option value=0 selected>��</option>");
			%></select></td>
</tr>
<%
%>
</table>
<br>
<table width='100%'>
	<tr>
		<td align=center width='100%'>
		<table>
			<tr>
				<td width=60 align=center><a href="javascript:formsubmit()"><img src="../images/button_submit.gif" border=0></a></td>
				<td width=20></td>
				<td width=60 align=center><a href='javascript:window.close()'><font size=2>�ر�</font></a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<input type="hidden" name="tabid" value='<%=tabid%>' ><input type="hidden" name="id" value='<%=id%>' ><input type="hidden" name="ot" value='2'></input>
</form>
<Script Language=JavaScript>
function formsubmit()
{
	if(allTrim(editsysform.fieldname.value) < 1)
	{
		alert('��û�������ֶ�����Ϣ����ʵ������');
	}
	else if(editsysform.fieldtype.value.indexOf("(") != -1)
	{
		if(!IsNumber(editsysform.len.value) || allTrim(editsysform.len.value) < 1)
			alert('�����ֶγ���û����д������д�д���');
		else if(editsysform.len.value > 255 || editsysform.len.value < 1)
			alert('����Ӧ�� 1~255');
		else
		{
			document.editsysform.action="AddaCol.jsp";
			document.editsysform.submit();
		}
	}
	else
	{
		document.editsysform.action="AddaCol.jsp";
		document.editsysform.submit();
	}
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
</Script>
</body>
</html>
<%
	}
%>