<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="mcs.common.db.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 文档录入
*@ version 1.0
**/
%>
<%
     if(!bflag)
  out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
  request.setCharacterEncoding("GBK");

	String sId = null;
	if(request.getParameter("id") != null)
		sId = request.getParameter("id");
	else
		sId = "0";
	String pi_id = null;
	if(request.getParameter("pi_id") != null)
		pi_id = request.getParameter("pi_id");
	else
		pi_id = "0";
	String tabId = null;
	if(request.getParameter("tabid") != null)
		tabId = request.getParameter("tabid");
	else
		tabId = "0";
    String cityId = null;
	if(request.getParameter("cityid") != null)
		cityId = request.getParameter("cityid");
	else
		cityId = ug.getCity() + "";
	String type = null;
	if(request.getParameter("type") != null)
		type = request.getParameter("type");
	else
		type ="0";
	String docPath="";
	if(request.getParameter("docPath") != null)
		docPath = request.getParameter("docPath");
	int cityFlag=0;
	if(request.getParameter("cityflag") != null)
		cityFlag = Integer.parseInt(request.getParameter("cityflag"));
%>
<html>
<head>

<script language=javascript>

function formsubmit()
{
	if (editPic.cc_dis.value=="")
	{
		alert("文档说明不能为空！");
		return;
	}
	editPic.action="editDocSave.jsp";
	editPic.submit();
}
function clearinput()
{
	editPic.reset();
}
	
</script>

<title>添加文档</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<form  method="post" name="editPic"  enctype="multipart/form-data" action="editDocSave.jsp">
<br>
<table class='table_show' border=0 cellspacing=1 width='100%'>
<tr class=td_label>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>上传文档</font>
	</td>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><input type=file name=fname1 >　　文件名必须为英文名</font>
	</td>
</tr>
<tr class=td_label>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>上传文档</font>
	</td>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><input type=file name=fname2 >　　文件名必须为英文名</font>
	</td>
</tr>
<tr class=td_label>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>上传文档</font>
	</td>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><input type=file name=fname3 >　　文件名必须为英文名</font>
	</td>
</tr> 
<tr class=td_label>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>上传文档</font>
	</td>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><input type=file name=fname4 >　　文件名必须为英文名</font>
	</td>
</tr> 
<tr bgcolor=#eaeaea>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica,sans-serif'>文档说明</font>
	</td>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><input type=text name=cc_dis maxlength=128 >  必填</font>
	</td>
</tr>
<tr bgcolor=#eaeaea>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica,sans-serif'>年月</font>
	</td>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><input type=text name=cc_data maxlength=8 ></font>(YYYY-MM)
	</td>
</tr>
<tr bgcolor=#eaeaea>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica,sans-serif'>所属城市</font>
	</td>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><select name=fi_city>
	<%
		queryRes qre = new queryRes();
		Vector ctV = new Vector();
		cityClass ct = new cityClass();
		ctV = qre.getCity(Integer.parseInt(cityId));
		if (cityFlag == 0)
		{
			out.println("<option value='0'>全省</option>");
		}
		else
		{
			for (int i=0;i<ctV.size();i++)
			{
				ct = (cityClass)ctV.get(i);
				if(ct.getId() == Integer.parseInt(cityId))
					out.println("<option value="+ct.getId()+" selected>"+ct.getName()+"</option>");
				else
					out.println("<option value="+ct.getId()+">"+ct.getName()+"</option>");
			}
		}
	%>
	</select></td>
</tr>
<tr bgcolor=#eaeaea>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica,sans-serif'>备注</font>
	</td>
	<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><input type=text name=cc_memo maxlength=128 ></font>
	</td>
</tr>

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
<input type="hidden" name="type" value=<%=type%>></input>
<input type="hidden" name="id" value=<%=sId%>></input>
<input type=hidden name='cityid' value='<%=cityId%>'>
<input type=hidden name='cityflag' value='<%=cityFlag%>'>
<input type=hidden name='docPath' value='<%=docPath%>'>
<input type="hidden" name="OptType" value="1"></input>
</table>
</form>
</body>
</html>