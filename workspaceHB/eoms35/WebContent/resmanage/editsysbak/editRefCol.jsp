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
	String ColId = null;
	if(request.getParameter("colid") != null )
		ColId = request.getParameter("colid");
	else
		ColId = "0";
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
<font size=2>&nbsp;&nbsp;&nbsp;&nbsp;第四步：定义数据表列的参考信息:</font>
<br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000></font></td>
	<td align=center><font size=2 color=#000000>数据表列英文名</font></td>
	<td align=center><font size=2 color=#000000>数据表列名称</font></td>
	<td align=center><font size=2 color=#000000>参考的主编码表</font></td>
	<!-- <td align=center><font size=2 color=#000000>数据表列类型</font></td>
	<td align=center><font size=2 color=#000000>是否是参考列</font></td>
	<td align=center><font size=2 color=#000000>是否是在列表列表中显示</font></td> -->
	<!--<td align=center><font size=2 color=#000000>是否是辅助列</font></td>-->	
<!-- 	<td align=center><font size=2 color=#000000>是否可为空</font></td>	
 --></tr>
	<%
		SysOpt sysopt = new SysOpt();

		SysPrmCode TabIndex  = new SysPrmCode();
		syscolindex  SysColIndex = new syscolindex();
		Vector ColVec = new Vector();
		Vector TabVec = new Vector();
		ColVec = sysopt.getAssColInfor(tabid,1);
        TabVec = sysopt.getTabVec("0");
		int TabNum = TabVec.size();
		int ColNum = ColVec.size();
		out.println("ColNum is : "+ColNum);
		if (ColNum!=0)
		{
			for (int i=0;i<ColNum;i++)
			{
				SysColIndex = (syscolindex)ColVec.get(i);
				/* =======得到已经定义的列的信息======*/
				
				int pi_id	= SysColIndex.getPi_id();
				String cc_name = SysColIndex.getCc_name();
				String cc_code = SysColIndex.getCc_code();
				
				/*====================================*/
				out.println("<tr bgcolor=#eeeeee>");
				
			
				out.println("<td align=center><input type=hidden name=pi_id value="+pi_id+"></td>");

				out.println("<td align=center>"+cc_code+"</td>");
				out.println("<td align=center>"+cc_name+"</td>");
                out.println("<td align=center>");
				out.println("<select name=prmcode>");
				if (TabNum != 0)
				{	
					for (int j=0;j<TabNum;j++)
					{
						TabIndex = (SysPrmCode)TabVec.get(j);
						SysPrmCode sysprm = new SysPrmCode();
						Vector prmVecTemp = new Vector();
						prmVecTemp = sysopt.getTabVec(ColId);
						for (int k=0;k<prmVecTemp.size();k++)
						{
							sysprm = (SysPrmCode)prmVecTemp.get(k);
							int pi_idTemp = sysprm.getPi_id();
							if (pi_idTemp == TabIndex.getPi_id())
							{
								out.println("<option value="+TabIndex.getPi_id()+" selected >"+TabIndex.getCc_name()+"</option>");
							}
						}
						out.println("<option value="+TabIndex.getPi_id()+">"+TabIndex.getCc_name()+"</option>");

					}
				}
				out.println("</select>");
				out.println("</td>");
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
	document.editsysform.action="editRefColSave.jsp";
	document.editsysform.submit();
}
</Script>

</head>
</body>
</html>