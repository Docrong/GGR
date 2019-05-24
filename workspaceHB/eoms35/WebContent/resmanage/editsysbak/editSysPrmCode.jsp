<%@page contentType="text/html;charset=ISO8859_1"%>
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
	
	String tabid = null;
	if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
		tabid = request.getParameter("tabid");
	else
		tabid = "0";
	String ColId = null;
	if(request.getParameter("colid") != null )
		ColId = request.getParameter("colid");
	else
		ColId = "0";
	int  opttype = Integer.parseInt(request.getParameter("opttype"));
	
    out.println("colid is:"+ColId);
	out.println("tabid is :"+tabid);
	
	/*
	//得表的信息
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
			//cc_code  = SysTabIndex.getCc_code();
			fi_type  = SysTabIndex.getFi_type();
			ct_sql  =  SysTabIndex.getCt_sql();
			ci_icon =  SysTabIndex.getCi_icon();
		}
	}*/
%>

<html>
<head>
<title>实体表列信息维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">

<br>
 <font size=2>&nbsp;&nbsp;&nbsp;&nbsp;录入主编码的信息:</font><br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000></font></td>
	<!-- <td align=center><font size=2 color=#000000>数据表列英文名</font></td> -->
	<td align=center><font size=2 color=#000000>主编码名称</font></td>
	<td align=center><font size=2 color=#000000>参考的数据表</font></td>
	<td align=center><font size=2 color=#000000>备注信息</font></td>
	<!-- <td align=center><font size=2 color=#000000>数据表列类型</font></td>
	<td align=center><font size=2 color=#000000>是否是参考列</font></td>
	<td align=center><font size=2 color=#000000>是否是在列表列表中显示</font></td> -->
	<!--<td align=center><font size=2 color=#000000>是否是辅助列</font></td>-->	
<!-- 	<td align=center><font size=2 color=#000000>是否可为空</font></td>	
 --></tr>
	<%
		SysOpt sysopt = new SysOpt();

		SysPrmCode sysprmcode  = new SysPrmCode();
		Vector ColVec = new Vector();
		Vector TabVec = new Vector();
		ColVec = sysopt.getSysPrmCodeInfor("0");

		systabindex SysTabIndex = new systabindex();
		TabVec = sysopt.getSysTabIndexInfor("0");
		int TabNum = TabVec.size();
		int ColNum = ColVec.size();
		out.println("TabNum is : "+TabNum);
		if (opttype==1)
		{
		if (ColNum!=0)
		{
			for (int i=0;i<ColNum;i++)
			{
				sysprmcode = (SysPrmCode)ColVec.get(i);
				/* =======得到已经定义的列的信息======*/
				
				int pi_id	= sysprmcode.getPi_id();
				String cc_name = sysprmcode.getCc_name();
				String ct_memo = sysprmcode.getCt_memo();
				
				/*====================================*/
				out.println("<tr bgcolor=#eeeeee>");
				
			
				out.println("<td align=center><input type=hidden name=pi_id value="+pi_id+"></td>");

				out.println("<td align=center>"+"<input name=cc_name   value="+cc_name+"></input>"+"</td>");
				
				//
				
                out.println("<td align=center>");
				out.println("<select name=reftable>");
				if (TabNum != 0)
				{	
					for (int j=0;j<TabNum;j++)
					{
						SysTabIndex = (systabindex)TabVec.get(j);
						systabindex SysTabIndexTemp = new systabindex();
						Vector TabVecTemp = new Vector();
						TabVecTemp = sysopt.getSysTabIndexInfor(Integer.toString(sysprmcode.getFi_table()));

						int temppi_id1 = 0 ;
						int temppi_id2 = 0;

						int flag = 0;
						if (TabVecTemp.size()!=0)
						{
							for (int k=0;k<TabVecTemp.size();k++)
							{
								
								SysTabIndexTemp = (systabindex)TabVecTemp.get(k);
								int pi_idTemp = SysTabIndexTemp.getPi_id();
								if (pi_idTemp == SysTabIndex.getPi_id())
								{
									temppi_id1 = SysTabIndex.getPi_id();
									out.println("<option value="+SysTabIndex.getPi_id()+" selected >"+SysTabIndex.getCc_name()+"</option>");
								}
								else
								{
									out.println("<option value="+SysTabIndex.getPi_id()+">"+SysTabIndex.getCc_name()+"</option>");
									
								}
							}
						}
					}
				}
				out.println("</select>");
				out.println("</td>");
				out.println("<td align=center><textarea name=ct_memo value="+ct_memo+"></textarea></td>");
				out.println("</tr>");

			}
		}
		}
		if (opttype==2)
		{
			int pi_id= sysopt.getTabMaxId("sys_prm_code");
			out.println("<tr bgcolor=#eeeeee>");
			out.println("<td align=center><input type=hidden name=pi_id value="+pi_id+"></td>");

			out.println("<td align=center>"+"<input name=cc_name></input>"+"</td>");
			 out.println("<td align=center>");
			out.println("<select name=reftable>");
			if (TabNum != 0)
			{	
				for (int j=0;j<TabNum;j++)
				{
					SysTabIndex = (systabindex)TabVec.get(j);			
					out.println("<option value="+SysTabIndex.getPi_id()+">"+SysTabIndex.getCc_name()+"</option>");
								
				}
			}
			out.println("</select>");
			out.println("</td>");
			out.println("<td align=center><textarea name=ct_memo ></textarea></td>");
			out.println("</tr>");
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
				<td width=60 align=center><a href='editSysIndex.jsp'><font size=3 color=#effdsfs>返回</font></a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<input type="hidden" name="tabid" value='<%=tabid%>' ></input>
<input type="hidden" name="colid" value='<%=ColId%>' ></input>

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