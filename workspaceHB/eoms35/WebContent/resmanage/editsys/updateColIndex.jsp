<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.editsys.*"%>
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
	request.setCharacterEncoding("GBK");
	String tabid = null;
	if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
		tabid = request.getParameter("tabid");
	else
		tabid = "0";
	String fieldnum = "";
%>

<html>
<head>
<title>实体表列信息维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#cccccc height=22>
	<td align=center><font size=2 color=#000000>选择</font></td>
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

				out.println("<tr bgcolor=#eeeeee>");
				
				if(i == ColNum - 1)
					fieldnum = SysColIndex.getCc_code();

				if(!SysColIndex.getCc_code().equals("fi_city") && !SysColIndex.getCc_code().equals("pi_id") && !SysColIndex.getCc_code().equals("cc_name"))
				{
					out.println("<td align=center><input type=checkbox name=checked_id value="+pi_id+"></td>");
					out.println("<td align=center><font size=2 color=#000000>"  +SysColIndex.getCc_code()+"</font></td>");

					out.println("<td align=center><font size=2 color=#000000>"+cc_name+"</font></td>");

					out.println("<td align=center><font size=2 color=#000000>"  +SysColIndex.getCc_type()+"</font></td>");

					if (refflag == 1)
						out.println("<td align=center><font size=2 color=#000000>是</font></td>");
					else
						out.println("<td align=center><font size=2 color=#000000>否</font></td>");
					if (disflag == 1)
						out.println("<td align=center><font size=2 color=#000000>是</font></td>");
					else
						out.println("<td align=center><font size=2 color=#000000>否</font></td>");
					if (nullflag == 1)
						out.println("<td align=center><font size=2 color=#000000>否</font></td>");
					else
						out.println("<td align=center><font size=2 color=#000000>是</font></td>");
					if (readflag == 1)
						out.println("<td align=center><font size=2 color=#000000>是</font></td>");
					else
						out.println("<td align=center><font size=2 color=#000000>否</font></td>");
				}
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
				<td height=20 align=center width=60><a href="javascript:edit(editsysform)"><img src="../images/button_mot.gif" alt="修改选中的实体" border=0></a></td>
				<td width=20></td>
				<td height=20 align=center width=60><a href="javascript:Del(editsysform)"><img src="../images/button_del.gif" alt="删除选中的实体" border=0></a></td>
				<td width=20></td>
				<td align=right><a href='editAddCol.jsp?fieldnum=<%=fieldnum%>&tabid=<%=tabid%>' target=_blank><font size=2>增加一列</font></a></td>
				<td width=20></td>
				<td align=right><a href='editSysAssCol.jsp?tabid=<%=tabid%>&OptType=1' target=_blank><font size=2>增加辅助列</font></a></td>
				<td width=20></td>
				<td align=right><a href='editSysAssCol.jsp?tabid=<%=tabid%>&OptType=2' target=_blank><font size=2>修改已定义的辅助列</font></a></td>
				<td width=20></td>
				<td align=right><a href='editSysMenu.jsp?type=1&tabid=<%=tabid%>' target=_blank><font size=2>修改已定义的菜单位置</font></a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<input type="hidden" name="tabid" value='<%=tabid%>' ></input>
</form>
<Script Language=JavaScript>
function Del(form)
{
	var icheck=0;
	for (var i=0;i<form.elements.length;i++)
    {
		var e=form.elements[i];
		if(e.checked==true)
			icheck+=1;
    }
	if(form.elements.length == 1)
		alert("最少要有一列自定义列，您不能删除它，但是您可以修改该列！");
    if (icheck>0 && form.elements.length != 1)
    {
		if ( confirm("删除该列会删除数据库内该列的所有值，你确定要删除这些项目吗？"))
		{
			form.target = "_blank";
			form.action="delCol.jsp";
			form.submit();
		}
	}
    else
		alert("你必须选中要删除的项目");
}
function edit(form)
{
	var icheck=0;
	var id=0;
	for (var i=0;i<form.elements.length;i++)
    {
		var e = form.elements[i];
		if(e.checked==true)
		{
			icheck+=1;
        }
	}
	if(icheck==1) 
	{
		form.target = "_blank";
		form.action="updateCol.jsp";
		form.submit();
   }
   if(icheck>1)
	   alert("你一次只能修改一个项目！");
   if(icheck==0)
	   alert("你必须选择一个修改的项目");
}
function formsubmit()
{	
	document.editsysform.action="editSysColSave.jsp";
	document.editsysform.submit();
}
</Script>

</head>
</body>
</html>