<%@page contentType="text/html;charset=gb2312"%>
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
<html>
<head>
<title>������Ϣά��</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<td align=center>��������Ϣά��</center>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000></font></td>
	<td align=center><font size=2 color=#000000>����������</font></td>
	<td align=center><font size=2 color=#000000>�ο������ݱ�</font></td>
	<td align=center><font size=2 color=#000000>��ע��Ϣ</font></td>
</tr>
	<%
		SysOpt sysopt = new SysOpt();
		SysPrmCode sysprmcode  = new SysPrmCode();
		Vector ColVec = new Vector();
		ColVec = sysopt.getSysPrmCodeInfor("0");
		systabindex SysTabIndex = new systabindex();
		int ColNum = ColVec.size();
		if (ColNum!=0)
		{
			for (int i=0;i<ColNum;i++)
			{
				sysprmcode = (SysPrmCode)ColVec.get(i);
				int pi_id	= sysprmcode.getPi_id();
				String cc_name = sysprmcode.getCc_name();
				String ct_memo = sysprmcode.getCt_memo();
				String fi_table = Integer.toString(sysprmcode.getFi_table());
				/*====================================*/
				out.println("<tr bgcolor=#eeeeee>");
				out.println("<td align=center><input type=checkbox name=pi_id value="+pi_id+"></td>");
				out.println("<td align=center><font size=2>"+cc_name+"</font></td>");	//
				String tabname = null;
				if (fi_table.equals("0"))
					cc_name = "<a href = ><font size=2 color=#cc0000>�����ӱ���</font></a>";
				else
					if (fi_table.equals("-1"))
					 cc_name="<font size=2 color=#709fd5>������</font>";
				    else
						cc_name ="<font size=2>"+sysopt.getTabNameById(fi_table)+"</font>";
				out.println("<td align=center>"+cc_name+"</td>");
				out.println("<td align=center><font size=2>"+ct_memo+"</font></td>");
				out.println("</tr>");

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
				<td width=60 align=center><a href="javascript:clearinput()"><img src="../images/button_cancel.gif" border=0></a></td>
				<td width=60 align=center><a href='editSysIndex.jsp'><font size=3 color=#effdsfs>����</font></a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</form>

<Script Language=JavaScript>
function formsubmit()
{	
	document.editsysform.action="editSysPrmCodeSave.jsp";
	document.editsysform.submit();
}
function insertclick()
{	
	document.editsysform.action="editSysPrmCode.jsp?opttype=2";
	document.editsysform.submit();
}
</Script>

</head>
</body>
</html>