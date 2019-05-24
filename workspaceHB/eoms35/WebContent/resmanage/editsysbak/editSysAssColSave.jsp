<%@page contentType="text/html;charset=ISO8859_1"%>
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
   String  cc_type	= "INTEGER";
   String  ref_flag		= "-1";
   String dis_flag      = "0";
   String []ass_flag	= request.getParameterValues("assflag");
   String null_flag		= "0";
   String use_flag      = "0";
   String page_jump = "editSysCol.jsp";
   String ShowInfo = "保存成功!";
   int row = 0;
  
   for (int i=0;i<pi_id.length;i++)
   {
		syscolindex SysColIndex = new syscolindex();

		SysColIndex.setPi_id(Integer.parseInt(pi_id[i]));
		String str = new String(cc_name[i].getBytes("ISO-8859-1"));
		SysColIndex.setCc_name(str);
		SysColIndex.setCc_code(cc_code[i]);
		SysColIndex.setCc_type(cc_type);
		SysColIndex.setFi_table(tabid);
		SysColIndex.setCi_ref_flag(Integer.parseInt(ref_flag));
		SysColIndex.setCi_dis_flag(Integer.parseInt(dis_flag));
		SysColIndex.setCi_ass_flag(Integer.parseInt(ass_flag[i]));
		SysColIndex.setCi_nul_flag(Integer.parseInt(null_flag));
		SysColIndex.setCi_use_flag(Integer.parseInt(use_flag));
		row  = sysopt.SysColSave(SysColIndex);
   }


  /*String retpage = null;
  if (row >0)
  {
	  retpage="";
  }*/

%>
<body onload="goPage()">
<form action="" method=POST name=editSysForm>
</form>
</body>
<script>
function goPage()
{
	<%
	if(row >0)
	{
	%>
		if(confirm('保存成功,如果要继续添加辅助列信息请单击\"确定\",否则请单击\"取消\"'))
			location='editSysAssCol.jsp?tabid='+<%=tabid%>+'&OptType=1';
		else
			window.close();
	<%
	}
	%>
}
</script>
