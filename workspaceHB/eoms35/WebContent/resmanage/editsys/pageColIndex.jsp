<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.editsys.*"%>
<%@page import="com.boco.eoms.resmanage.operator.Cvt"%>
<%@page  import="com.boco.eoms.common.util.*"%>
<%
//request.setCharacterEncoding("GBK");
String tablename = StaticMethod.dbNull2String(request.getParameter("tablename"));
String [] ftype = request.getParameterValues("fieldtype");
String [] ltype = request.getParameterValues("typelength");
String [] notnull = request.getParameterValues("notnull");
String [] fieldname = request.getParameterValues("fieldname");
String [] disflag = request.getParameterValues("disp");
String ccname = StaticMethod.dbNull2String(request.getParameter("cc_name"));
int num = Integer.parseInt(request.getParameter("num"));

esys es = new esys();
//String tablecode = "user_tmp"+es.getTableCode();
String tablecode = request.getParameter("tablecode");
out.println("tablecode:"+tablecode);
systabindex tabindex = new systabindex();
tabindex.setCc_code(tablecode);
tabindex.setCc_name(tablename);

String [] conv = es.setType(ftype,ltype);
syscolindex picol = new syscolindex();
syscolindex picol1 = new syscolindex();
syscolindex picol2 = new syscolindex();
Vector colv = new Vector();
/***********必要列************/
/***********pi_id***************/
picol.setCc_name(StaticMethod.dbNull2String("唯一标识"));
picol.setCc_code("pi_id");
picol.setCc_type("integer");
picol.setCi_dis_flag(1);
picol.setCi_nul_flag(1);
colv.addElement(picol);
/***********cc_name*************/
picol1.setCc_name(ccname);
picol1.setCc_code("cc_name");
picol1.setCc_type("varchar(64)");
picol1.setCi_dis_flag(1);
picol1.setCi_nul_flag(1);
colv.addElement(picol1);
/***********fi_city*************/
picol2.setCc_name(StaticMethod.dbNull2String("所属城市"));
picol2.setCc_code("fi_city");
picol2.setCc_type("integer");
picol2.setCi_ref_flag(1);
picol2.setCi_dis_flag(1);
picol2.setCi_nul_flag(1);
colv.addElement(picol2);
/******************************/
for(int i = 0; i < num; i ++)
{
	syscolindex colIndex = new syscolindex();
	colIndex.setCc_name(Cvt.cha(fieldname[i]));
	colIndex.setCc_code("field_"+i);
	colIndex.setCc_type(conv[i]);
	colIndex.setCi_dis_flag(Integer.parseInt(disflag[i]));
	colIndex.setCi_nul_flag(Integer.parseInt(notnull[i]));
	colv.addElement(colIndex);
}

int flagint = 0;
int tabid = 0;
if(SysOpt.createEntity(tabindex.getCc_code(),colv,0))
{
	tabid = SysOpt.addSysTab(tabindex);
	if(tabid > 0)
	{
		flagint = SysOpt.addSysCol(tabid,colv);
		//out.println("Int :"+flagint);
	}
	else
		out.println("<script>alert('创建失败！');location='./newEntity.jsp';</script>");
}
else
	out.println("<script>alert('创建失败！');location='./newEntity.jsp';</script>");

if(flagint > 0)
	out.println("<script>alert('成功建立实体!下一步：建立列关系。');location='./editSysColIndex.jsp?tabid="+tabid+"';</script>");
else
	out.println("<script>alert('addSysCol() 函数返回值错误！');location='./editSysColIndex.jsp?tabid="+tabid+"';</script>");

/*
for(int i = 0; i < colv.size(); i ++)
{
	syscolindex tmp = new syscolindex();
	tmp = (syscolindex)colv.get(i);
	out.println("	列信息"+i+"：<br>");
	out.println(" Name : " +tmp.getCc_name());
	out.println(" Code : " +tmp.getCc_code());
	out.println(" Type : " +tmp.getCc_type());
	out.println(" Dis_flag : " +tmp.getCi_dis_flag());
	out.println(" Nul_flag : " +tmp.getCi_nul_flag());
	out.println("<br>");
}
*/
%>
