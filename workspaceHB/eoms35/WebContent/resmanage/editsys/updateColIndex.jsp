<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.editsys.*"%>
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
<title>ʵ�������Ϣά��</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<br><br>
<form name=editsysform action="" method="post">
<table bgcolor=#dddddd width='100%' cellspacing="1" cellpadding="0" border="0">
<tr bgcolor=#cccccc height=22>
	<td align=center><font size=2 color=#000000>ѡ��</font></td>
	<td align=center><font size=2 color=#000000>���ݱ���Ӣ����</font></td>
	<td align=center><font size=2 color=#000000>���ݱ�������</font></td>
	<td align=center><font size=2 color=#000000>���ݱ�������</font></td>
	<td align=center><font size=2 color=#000000>�Ƿ��ǲο���</font></td>
	<td align=center><font size=2 color=#000000>�Ƿ������б��б�����ʾ</font></td>
	<td align=center><font size=2 color=#000000>�Ƿ��Ϊ��</font></td>	
	<td align=center><font size=2 color=#000000>�Ƿ�ֻ����</font></td>	
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
				/* =======�õ��Ѿ�������е���Ϣ======*/
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
						out.println("<td align=center><font size=2 color=#000000>��</font></td>");
					else
						out.println("<td align=center><font size=2 color=#000000>��</font></td>");
					if (disflag == 1)
						out.println("<td align=center><font size=2 color=#000000>��</font></td>");
					else
						out.println("<td align=center><font size=2 color=#000000>��</font></td>");
					if (nullflag == 1)
						out.println("<td align=center><font size=2 color=#000000>��</font></td>");
					else
						out.println("<td align=center><font size=2 color=#000000>��</font></td>");
					if (readflag == 1)
						out.println("<td align=center><font size=2 color=#000000>��</font></td>");
					else
						out.println("<td align=center><font size=2 color=#000000>��</font></td>");
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
				<td height=20 align=center width=60><a href="javascript:edit(editsysform)"><img src="../images/button_mot.gif" alt="�޸�ѡ�е�ʵ��" border=0></a></td>
				<td width=20></td>
				<td height=20 align=center width=60><a href="javascript:Del(editsysform)"><img src="../images/button_del.gif" alt="ɾ��ѡ�е�ʵ��" border=0></a></td>
				<td width=20></td>
				<td align=right><a href='editAddCol.jsp?fieldnum=<%=fieldnum%>&tabid=<%=tabid%>' target=_blank><font size=2>����һ��</font></a></td>
				<td width=20></td>
				<td align=right><a href='editSysAssCol.jsp?tabid=<%=tabid%>&OptType=1' target=_blank><font size=2>���Ӹ�����</font></a></td>
				<td width=20></td>
				<td align=right><a href='editSysAssCol.jsp?tabid=<%=tabid%>&OptType=2' target=_blank><font size=2>�޸��Ѷ���ĸ�����</font></a></td>
				<td width=20></td>
				<td align=right><a href='editSysMenu.jsp?type=1&tabid=<%=tabid%>' target=_blank><font size=2>�޸��Ѷ���Ĳ˵�λ��</font></a></td>
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
		alert("����Ҫ��һ���Զ����У�������ɾ�����������������޸ĸ��У�");
    if (icheck>0 && form.elements.length != 1)
    {
		if ( confirm("ɾ�����л�ɾ�����ݿ��ڸ��е�����ֵ����ȷ��Ҫɾ����Щ��Ŀ��"))
		{
			form.target = "_blank";
			form.action="delCol.jsp";
			form.submit();
		}
	}
    else
		alert("�����ѡ��Ҫɾ������Ŀ");
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
	   alert("��һ��ֻ���޸�һ����Ŀ��");
   if(icheck==0)
	   alert("�����ѡ��һ���޸ĵ���Ŀ");
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