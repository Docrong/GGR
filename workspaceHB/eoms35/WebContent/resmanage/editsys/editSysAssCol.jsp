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
    int  OptType = 1;
    if(request.getParameter("OptType") != null)
		OptType = Integer.parseInt(request.getParameter("OptType"));
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
<title>实体表辅助列信息维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<br>
<font size=2>&nbsp;&nbsp;&nbsp;&nbsp;请完成数据表辅助列的信息的定义:</font>
<br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#eeeeee>
	<td align=center><font size=2 color=#000000></font></td>
	<td align=center><font size=2 color=#000000>数据表列英文名</font></td>
	<td align=center><font size=2 color=#000000>数据表列名称</font></td>
	<td align=center><font size=2 color=#000000>辅助标志</font></td>
</tr>
	<%
		SysOpt sysopt = new SysOpt();
		syscolindex  SysColIndex = new syscolindex();
		Vector ColVec = new Vector();
        ColVec = sysopt.getAssColInfor(tabid,-1);
		int ColNum = ColVec.size();
		//out.println("ColNum is : "+ColNum);
		//修改
		if (OptType == 2)
		{
			if (ColNum!=0)
			{
				for (int i=0;i<ColNum;i++)
				{
					SysColIndex = (syscolindex)ColVec.get(i);

					Vector SysColVec = new Vector();
					int pi_id=0;
					String cc_name="";
					int refflag =0;
					int disflag = 0;
					int assflag = 0;
					int nullflag = 0;
					pi_id	= SysColIndex.getPi_id();
					cc_name = SysColIndex.getCc_name();
					refflag = SysColIndex.getCi_ref_flag();
					disflag = SysColIndex.getCi_dis_flag();
					assflag = SysColIndex.getCi_ass_flag();
					nullflag = SysColIndex.getCi_nul_flag();
					//若列的信息没有在sys_col_index表中定义列，的pi_id列的值由表的max(pi_id)+1
					if(pi_id==0)
						pi_id = sysopt.getMaxColId(tabid);

					out.println("<tr bgcolor=#eeeeee>");
					out.println("<td align=center><input type=hidden name=pi_id value="+pi_id+"></td>");

					out.println("<td align=center><input name=cc_code value='"+SysColIndex.getCc_code()+"'><font size=2 color=#000000>"+"</font></td>");

					out.println("<td align=center><input name=cc_name value='"+cc_name+"'></input></td>");

					out.println("<td align=center>");
					out.println("<select name=assflag>");
					if (refflag == 1)
					{	
						out.println("<option value=1 selected>普通辅助标志</option>");
						out.println("<option value=2 >拓扑图或者记录</option>");
					}
					else
					{
						out.println("<option value=1 >普通辅助标志</option>");
						out.println("<option value=2 selected>拓扑图或者记录</option>");
					}
					out.println("</select>");
					out.println("</td>");
					out.println("</tr>");
				}
			}
			else
				out.println("<script>alert('还没有定义辅助列！');window.close();</script>");
		}
		if (OptType==1)
		{
			int pi_id = sysopt.getMaxColId(tabid);
			out.println("<tr bgcolor=#eeeeee>");
			out.println("<td align=center><input type=hidden name=pi_id value="+pi_id+"></td>");
			out.println("<td align=center><input name=cc_code></input></td>");
			out.println("<td align=center><input name=cc_name></input></td>");
			out.println("<td align=center>");
			out.println("<select name=assflag>");	
			out.println("<option value=1>普通辅助标志</option>");
			out.println("<option value=2 >拓扑图或者记录</option>");
			out.println("</select>");
			out.println("</td>");
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
				<td width=60 align=center><a href='javascript:window.close()'><font size=2>关闭</font></a></td>
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
	if(allTrim(editsysform.cc_name.value) < 1 || allTrim(editsysform.cc_code.value) < 1)
	{
		alert('您没有完整的输入信息！核实后重试');
	}
	else
	{
		document.editsysform.action="editSysAssColSave.jsp";
		document.editsysform.submit();
	}
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
