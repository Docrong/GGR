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
String tabid = "";
if(request.getParameter("tabid") != null && !request.getParameter("tabid").equals("null"))
	tabid = request.getParameter("tabid");
String cc_code = request.getParameter("cc_code");
String fieldtype = request.getParameter("fieldtype");
if(fieldtype.indexOf("(") != -1)
	fieldtype = fieldtype.substring(0,fieldtype.indexOf("(")+1) + request.getParameter("len") +")";
String fieldname = request.getParameter("fieldname");
//out.println("Type :"+fieldtype);
int nulflag = Integer.parseInt(request.getParameter("nulflag"));
int refflag = Integer.parseInt(request.getParameter("refflag"));
int disflag = Integer.parseInt(request.getParameter("disflag"));
int readflag = Integer.parseInt(request.getParameter("readflag"));
int ot = Integer.parseInt(request.getParameter("ot"));
int id = 0;
syscolindex colIndex = new syscolindex();
colIndex.setCc_name(fieldname);
colIndex.setCc_code(cc_code);
colIndex.setCc_type(fieldtype);
colIndex.setCi_dis_flag(disflag);
colIndex.setCi_nul_flag(nulflag);
colIndex.setCi_ref_flag(refflag);
colIndex.setCi_read_flag(readflag);

Vector tmp = new Vector();
if(ot == 2)
{
	id = Integer.parseInt(request.getParameter("id"));
	colIndex.setPi_id(id);
}
tmp.addElement(colIndex);
if(SysOpt.SysDataOpt(Integer.parseInt(tabid),tmp,ot))
{
	if(refflag == 1)
		out.println("<script>alert('成功添加该列！请继续定义参考列');location='newRefColIndex.jsp?tabid="+tabid+"&col_code="+colIndex.getCc_code()+"';</script>");
	else
		out.println("<script>alert('成功添加该列！');opener.document.location.reload();window.close();</script>");
}
else
	out.println("<script>alert('失败');opener.document.location.reload();window.close();</script>");
%>