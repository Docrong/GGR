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
	String tabid = null;
	if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
		tabid = request.getParameter("tabid");
	else
		tabid = "0";
	//out.println("tabid is :"+tabid);
	
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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<br>
<font size=2>&nbsp;&nbsp;&nbsp;&nbsp;第二步：输入数据表列的信息:</font>
<br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#cccccc height=22>
	<td align=center><font size=2 color=#000000></font></td>
	<td align=center><font size=2 color=#000000>数据表列英文名</font></td>
	<td align=center><font size=2 color=#000000>数据表列名称</font></td>
	<td align=center><font size=2 color=#000000>数据表列类型</font></td>
	<td align=center><font size=2 color=#000000>是否是参考列</font></td>
	<td align=center><font size=2 color=#000000>是否是在列表列表中显示</font></td>
	<td align=center><font size=2 color=#000000>是否可为空</font></td>	
	<td align=center><font size=2 color=#000000>是否只读列</font></td>	
</tr>
	<%
		SysOpt sysopt = new SysOpt();
		syscolindex  SysColIndex = new syscolindex();
		Vector ColVec = new Vector();
        ColVec = sysopt.getColInfor(tabid,"");
		int ColNum = ColVec.size();
		//out.println("ColNum is : "+ColNum);
		if (ColNum!=0)
		{
			for (int i=0;i<ColNum;i++)
			{
				SysColIndex = (syscolindex)ColVec.get(i);
				/* =======得到已经定义的列的信息======*/
				int pi_id=0;
				String cc_name="";
				int refflag =0;
				int disflag = 0;
				int assflag = 0;
				int nullflag = 0;
				int readflag = 0;
				pi_id	= SysColIndex.getPi_id();
				cc_name = SysColIndex.getCc_name();
				refflag = SysColIndex.getCi_ref_flag();
				disflag = SysColIndex.getCi_dis_flag();
				assflag = SysColIndex.getCi_ass_flag();
				nullflag = SysColIndex.getCi_nul_flag();
				readflag = SysColIndex.getCi_read_flag();
			//若列的信息没有在sys_col_index表中定义列，的pi_id列的值由表的id+两位序号
				/*if(pi_id==0)
				{
					int xh = i + 1;
					String strXh=null;
					if (xh <10)
					{
						strXh = "0"+xh;
					}
					else
					{
						strXh = Integer.toString(xh);
					}
					pi_id = Integer.parseInt(tabid+strXh);
				}*/
				/*====================================*/
				out.println("<tr bgcolor=#eeeeee>");
				out.println("<td align=center><input type=hidden name=pi_id value="+pi_id+"></td>");
				out.println("<td align=center><input type=hidden name=cc_code value='"+SysColIndex.getCc_code()+"'><font size=2 color=#000000>"  +SysColIndex.getCc_code()+"</font></td>");
				
				if(SysColIndex.getCc_code().equals("fi_city") || SysColIndex.getCc_code().equals("pi_id") || SysColIndex.getCc_code().equals("cc_name"))
					out.println("<td align=center><input name=cc_name value='"+cc_name+"' readonly></td>");
				else
					out.println("<td align=center><input name=cc_name value='"+cc_name+"'></td>");

				out.println("<td align=center><input type=hidden name=cc_type value='"+SysColIndex.getCc_type()+"'><font size=2 color=#000000>"  +SysColIndex.getCc_type()+"</font></td>");
				out.println("<td align=center>");

				out.println("<select name=refflag>");
				if (refflag == 1)
				{	
					out.println("<option value=1 selected>是</option>");
					out.println("<option value=0 >否</option>");
				}
				else
				{
					out.println("<option value=1>是</option>");
					out.println("<option value=0 selected>否</option>");
				}
				out.println("</select>");
				out.println("</td>");

				out.println("<td align=center>");
				out.println("<select name=disflag>");
				if (disflag == 1)
				{	
					out.println("<option value=1 selected>是</option>");
					out.println("<option value=0 >否</option>");
				}
				else
				{
					out.println("<option value=1>是</option>");
					out.println("<option value=0 selected>否</option>");
				}
				out.println("</select>");
				out.println("</td>");
				out.println("<td align=center>");
				out.println("<select name=nullflag>");
				if (nullflag == 1)
				{	
					out.println("<option value=0>是</option>");
					out.println("<option value=1 selected>否</option>");
				}
				else
				{
					out.println("<option value=0 selected>是</option>");
					out.println("<option value=1>否</option>");
				}
				out.println("<td align=center>");
				out.println("<select name=readflag>");
				if (readflag == 1)
				{	
					out.println("<option value=1 selected>是</option>");
					out.println("<option value=0 >否</option>");
				}
				else
				{
					out.println("<option value=1>是</option>");
					out.println("<option value=0 selected>否</option>");
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
				<td align=right><a href='editSysAssCol.jsp?tabid=<%=tabid%>&OptType=1' target=_blank><font size=2>定义辅助列</font></a></td>
				<td width=20></td>
				<td align=right><a href='editSysMenu.jsp?type=0&tabid=<%=tabid%>' target=_blank><font size=2>定义该实体的菜单位置</font></a></td>
				<td width=20></td>
				<td align=right><a href='editSysIndex.jsp'><font size=2>返回</font></a></td>
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
	document.editsysform.action="editSysColSave.jsp";
	document.editsysform.submit();
}
</Script>

</head>
</body>
</html>