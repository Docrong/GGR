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
   String colid			= request.getParameter("colid");
   String tabid			= request.getParameter("tabid");

   String[] pi_id		= request.getParameterValues("pi_id");
   String[] prmcode =   request.getParameterValues("prmcode");
   
   
   int row = 0;
   for (int i=0;i<pi_id.length;i++)
   {
	   SysRlt sysrlt = new SysRlt();
	   
	   sysrlt.setFi_primarycode(Integer.parseInt(prmcode[i]));
	   sysrlt.setFi_fk(Integer.parseInt(pi_id[i]));
	   row  = sysopt.RefColSave(sysrlt);
   }
    if (row >0)
  {
	  //out.println("保存成功<br><br><a href=editRefColIndex.jsp?tabid="+tabid+">返回</a><br>");
	  out.println("<script>alert('保存成功.该实体已经建立，请浏览资源管理进行数据修改！');location='newEntity.jsp'</script>");
	
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
