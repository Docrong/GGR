<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 数据表信息保存
*@ version 1.0
**/
%>

<%
	
   SysOpt   sysopt  = new SysOpt();	
   String tabid			= request.getParameter("tabid");
   String [] pi_id		= request.getParameterValues("pi_id");
   String [] cc_name	= request.getParameterValues("cc_name");
   String [] cc_code	= request.getParameterValues("cc_code");
   String [] cc_type	= request.getParameterValues("cc_type");
   String [] ref_flag   = request.getParameterValues("refflag");
   String [] dis_flag   = request.getParameterValues("disflag");
   String ass_flag		= "0";
   String [] null_flag   = request.getParameterValues("nullflag");
   int row = 0;
  
   for (int i=0;i<pi_id.length;i++)
   {
	   syscolindex SysColIndex = new syscolindex();
	   SysColIndex.setPi_id(Integer.parseInt(pi_id[i]));
	   String str = new String(cc_name[i].getBytes("ISO-8859-1"));
	   SysColIndex.setCc_name(str);
	   SysColIndex.setCc_code(cc_code[i]);
	   SysColIndex.setCc_type(cc_type[i]);
	   SysColIndex.setFi_table(tabid);
	   SysColIndex.setCi_ref_flag(Integer.parseInt(ref_flag[i]));
	   if (i==0)
	   {
		   dis_flag[i] = "1";
		   null_flag[i] = "1";
		   ref_flag[i] = "0";
	   }
	   SysColIndex.setCi_ref_flag(Integer.parseInt(ref_flag[i]));
	   SysColIndex.setCi_dis_flag(Integer.parseInt(dis_flag[i]));
	   SysColIndex.setCi_ass_flag(Integer.parseInt(ass_flag));
	   SysColIndex.setCi_nul_flag(Integer.parseInt(null_flag[i]));
	   SysColIndex.setCi_use_flag(0);
	   row  = sysopt.SysColSave(SysColIndex);
	   //out.println("row si: "+row);
   }
   String script = "<Script>alert('操作完成');location='newEntity.jsp';</Script>";
   if (row >0)
   {
	   for(int i = 0; i < ref_flag.length; i ++)
	   {
		   if(Integer.parseInt(ref_flag[i]) == 1)
		   {
			   script = "<Script>alert('下一步：定义参考列');location='editRefColIndex.jsp?tabid="+tabid+"'</Script>";
			}
	   }
	   out.println(script);
   }
   /*Vector ColVec = new Vector();
   ColVec = sysopt.getAssColInfor(tabid,-1);
   int ColNum = ColVec.size();
   if (row >0 && ColNum==0)
   {
	  out.println("保存成功<br><br><a href=editSysAssCol.jsp?tabid="+tabid+"&OptType=1 target=_blank>新增数据表的辅助列</a><br>");
	  out.println("<a href=editRefColIndex.jsp?tabid="+tabid+" target=_blank>定义列的参考属性</a>");
   }
   if (row>0 && ColNum>0)
   {
	out.println("保存成功<br><br><a href=editSysAssCol.jsp?tabid="+tabid+"&OptType=1 target=_blank>新增数据表的辅助列</a><br>");
	out.println("<a href=editSysAssCol.jsp?tabid="+tabid+"&OptType=2 target=_blank>修改数据表的辅助列</a><br>");
	out.println("<a href=editRefColIndex.jsp?tabid="+tabid+" target=_blank>定义列的参考属性</a>");
   }*/
%>
