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
String tabid = "";
if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
	tabid = request.getParameter("tabid");
String[] cc_code = request.getParameterValues("cc_code");
String[] id = request.getParameterValues("id");
Vector tmp = new Vector();
for(int i = 0; i < id.length; i ++)
{
	syscolindex colIndex = new syscolindex();
	colIndex.setPi_id(Integer.parseInt(id[i]));
	colIndex.setCc_code(cc_code[i]);
	tmp.addElement(colIndex);
}

if(SysOpt.SysDataOpt(Integer.parseInt(tabid),tmp,3))
{
	out.println("<script>alert('成功删除该列！');opener.document.location.reload();window.close();</script>");
}
else
	out.println("<script>alert('失败');opener.document.location.reload();window.close();</script>");
%>