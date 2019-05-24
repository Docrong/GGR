<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 系统表的维护
*@ version 1.0
**/
%>

<%
	String systabid = null;
	if(request.getParameter("systabid") != null)
		systabid = request.getParameter("systabid");
	else
		systabid = "0";
	String tabid = null;
	if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
		tabid = request.getParameter("tabid");
	else
		tabid = "0";
	
	String ColName = null;
	if(request.getParameter("ColName") != null)
		ColName = request.getParameter("ColName");
	else
		ColName = "0";

	/*
	//得列的信息
	SysOpt sysopt = new SysOpt();
	systabindex SysTabIndex = new systabindex();
	Vector TabVec = new Vector();
    String cc_name = "";
	
	int   fi_type = 0;
	String ct_sql  = "";
	int  ci_icon = 0;


	TabVec = sysopt.getTabInfor(tabid);
	int TabNum = TabVec.size();
	//out.println("tabnum is : "+TabNum);
	if (TabNum!=0)
	{
		for(int Col=0;Col<TabNum;Col++)
		{
			SysTabIndex = (systabindex)TabVec.get(Col);
			cc_name  = SysTabIndex.getCc_name();
			cc_code  = SysTabIndex.getCc_code();
			fi_type  = SysTabIndex.getFi_type();
			ct_sql  =  SysTabIndex.getCt_sql();
			ci_icon =  SysTabIndex.getCi_icon();
		}
	}*/
%>

<html>
<head>
<title>实体表信息维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">

<br>
<font size=2>&nbsp;&nbsp;&nbsp;&nbsp;第三步：定义数据表中列的信息:</font>
<br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000>数据表列名称：</font></td>
	<td align=left><font size=2 color=#000000><input name='cc_name' maxlength=32 ></input></font>*</td>
</tr>
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000>数据表列代号：</font></td>
	<td align=left><font size=2 color=#000000></font><input name='cc_code' maxlength=32 readonly></input></td>
</tr>
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000>是否是参考列：</font></td>
	<td align=left><font size=2 color=#000000></font><input type =checkbox name='refflag' ></input></td>
</tr>
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000>是否是在列表列表中显示：</font></td>
	<td align=left><font size=2 color=#000000></font><input type =checkbox name='disflag' ></input></td>
</tr>
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000>是否是辅助列：</font></td>
	<td align=left><font size=2 color=#000000></font>
	<select name="assflag">
	<option value='0'>不是辅助列</option>
	<option value='1'>记录情况</option>
	<option value='2'>拓扑图情况</option>
	</select>
	</td>
</tr>
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000>是否可为空：</font></td>
	<td align=left><font size=2 color=#000000></font><input type =checkbox name='nullflag' ></input></td>
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
			</tr>
		</table>
		</td>
	</tr>
</table>
<input type="hidden" name="colid" value='' ></input>
<input type="hidden" name="tabid" value='<%=tabid%>' ></input>
<input type="hidden" name="systabid" value='<%=systabid%>' ></input>

</form>

<Script Language=JavaScript>
function formsubmit()
{	
	if (document.editsysform.cc_name.value=="")
	{
		alert("数据表列名称不能为空！");
		editsysform.cc_name.focus();
		return;
	}	
	document.editsysform.action="";
	document.editsysform.submit();
}
</Script>

</head>
</body>
</html>