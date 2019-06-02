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
   String colid			= request.getParameter("colid");
   String tabid			= request.getParameter("tabid");

   String[] pi_id		= request.getParameterValues("pi_id");
   String[] cc_name     = request.getParameterValues("cc_name");
   String[] reftable    = request.getParameterValues("reftable");
   String[] ct_memo     = request.getParameterValues("ct_memo");
   
   int row = 0;
   for (int i=0;i<pi_id.length;i++)
   {
	   SysPrmCode sysprmcode = new SysPrmCode();
	   
	   sysprmcode.setPi_id(Integer.parseInt(pi_id[i]));
	   String str = new String(cc_name[i].getBytes("ISO-8859-1"));
	   sysprmcode.setCc_name(str);

	   sysprmcode.setFi_table(Integer.parseInt(reftable[i]));
	   sysprmcode.setCt_memo(ct_memo[i]);
	   row  = sysopt.SysPrmCodeSave(sysprmcode);
	   out.println("row is : "+row);
   }
    if (row >0)
  {
	  out.println("保存成功<br><br><br>");
	
  }


  /*String retpage = null;
  if (row >0)
  {
	  retpage="";
  }*/

%>
<!-- <body>
<form action="" method=POST name=editSysForm>
<input type=hidden name="tabid" value='<%=tabid%>'></input>
</form>
</body> -->
<!-- <body onload="returnInput()">
<form action="" method=POST name=editSysForm>
<input type=hidden name="tabid" value='<%=tabid%>'></input>
</form>
</body>
 <script>
function returnInput()
{
	editSysForm.action="editSysAssCol.jsp";
	editSysForm.submit();
}
</script> -->
