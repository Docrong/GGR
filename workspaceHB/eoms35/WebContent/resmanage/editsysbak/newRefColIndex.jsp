<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
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
	if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
		tabid = request.getParameter("tabid");
	else
		tabid = "0";

	String colcode = request.getParameter("col_code");
%>

<html>
<head>
<title>ʵ�������Ϣά��</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">

<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#cccccc>
	<td align=center><font size=2 color=#000000></font></td>
	<td align=center><font size=2 color=#000000>���ݱ���Ӣ����</font></td>
	<td align=center><font size=2 color=#000000>���ݱ�������</font></td>
	<td align=center><font size=2 color=#000000>�ο����������</font></td>
</tr>
	<%
		SysOpt sysopt = new SysOpt();
		SysPrmCode TabIndex  = new SysPrmCode();
		syscolindex  SysColIndex = new syscolindex();
		Vector ColVec = new Vector();
		Vector TabVec = new Vector();
        ColVec = sysopt.getAssColInfor(tabid,1);
		TabVec = sysopt.getTabVec("0");
		int ColNum = ColVec.size();
		int TabNum = TabVec.size();
		if (ColNum!=0)
		{
			for (int i=0;i<ColNum;i++)
			{
				SysColIndex = (syscolindex)ColVec.get(i);
				int pi_id	= SysColIndex.getPi_id();
				String cc_name = SysColIndex.getCc_name();
				String cc_code = SysColIndex.getCc_code();
				if(cc_code.equals(colcode))
				{
					out.println("<tr bgcolor=#eeeeee>");
					out.println("<td align=center><input type=hidden name=pi_id value="+pi_id+"></td>");
					out.println("<td align=center><font size=2>"+cc_code+"</font></td>");
					out.println("<td align=center><font size=2>"+cc_name+"</font></td>");
					out.println("<td align=center>");
					out.println("<select name=prmcode>");
					if (TabNum != 0)
					{	
						for (int j=0;j<TabNum;j++)
						{
							TabIndex = (SysPrmCode)TabVec.get(j);
							SysPrmCode sysprm = new SysPrmCode();
							Vector prmVecTemp = new Vector();
							prmVecTemp = sysopt.getTabVec(Integer.toString(pi_id));
							if (prmVecTemp.size()==0)
							{
								out.println("<option value="+TabIndex.getPi_id()+">"+TabIndex.getCc_name()+"("+TabIndex.getCt_memo()+")</option>");
							}
							else
							{
								for (int k=0;k<prmVecTemp.size();k++)
								{
									sysprm = (SysPrmCode)prmVecTemp.get(k);
									int pi_idTemp = sysprm.getPi_id();
									out.println("pi_id is: "+ pi_id+ " pi_idTemp is: "+pi_idTemp);
									if (pi_idTemp == TabIndex.getPi_id())
									{
										out.println("<option value="+TabIndex.getPi_id()+" selected >"+TabIndex.getCc_name()+"("+TabIndex.getCt_memo()+")</option>");
									}
									else								
										out.println("<option value="+TabIndex.getPi_id()+">"+TabIndex.getCc_name()+"("+TabIndex.getCt_memo()+")</option>");
								}
							}

						}
					}
					out.println("</select>");
					out.println("</td>");
					out.println("</tr>");
				}
			}
		}
	%>
</td>
</tr>
</table>
<br>
<table width='100%'>
	<tr>
		<td align=center width='100%'>
		<table>
			<tr>
				<td width=60 align=center><a href="javascript:formsubmit()"><img src="../images/button_submit.gif" border=0></a></td>
				<td width=20></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<input type="hidden" name="tabid" value='<%=tabid%>' ></input>
</form>

<Script Language=JavaScript>
function formsubmit()
{	
	document.editsysform.action="newRefColSave.jsp";
	document.editsysform.submit();
}
</Script>

</head>
</body>
</html>