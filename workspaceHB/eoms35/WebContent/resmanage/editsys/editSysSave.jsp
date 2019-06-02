<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page  import="com.boco.eoms.common.util.*"%>
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
   String systabid = request.getParameter("systabid");
   int    tabid   = 0;
   String cc_name = null;
   String cc_code = null;
   int    fi_type = 0;
   String ct_sql  = null;
   int    ci_icon = 0;
   String cc_operateid ="0";
   String cc_location="";
   tabid   = Integer.parseInt(request.getParameter("tabid"));
   cc_name = StaticMethod.dbNull2String(request.getParameter("cc_name"));
   cc_code = request.getParameter("cc_code");
   fi_type = Integer.parseInt(request.getParameter("fi_type"));
   ct_sql  = request.getParameter("ct_sql");
   cc_operatid = request.getParameter("cc_operateid");
   cc_location = StaticMethod.dbNull2String(request.getParameter("cc_location"));
   if (ct_sql.equals(""))
   {
	   ct_sql = null;
   }
   ci_icon = Integer.parseInt(request.getParameter("ci_icon"));
   systabindex SysTabIndex = new systabindex();
   SysOpt   sysopt  = new SysOpt();
   SysTabIndex.setPi_id(tabid);
   SysTabIndex.setCc_name(cc_name);
   SysTabIndex.setCc_code(cc_code);
   SysTabIndex.setFi_type(fi_type);
   SysTabIndex.setCt_sql(ct_sql);
   SysTabIndex.setCi_icon(ci_icon);
   SysTabIndex.setCi_icon(ci_icon);
   int row = sysopt.SysTabIndexSave(SysTabIndex);
   //out.println("row is :" + row);
   
   if (row>0)
   {
	   out.println("success!!!");
   }

%>
<%
  String retpage = null;
  if (row >0)
  {
	  retpage="editSysColIndex.jsp";
  }
%>
<body onload="returnInput()">
<form action="<%=retpage%>" method=POST name=editSysForm>
<input type=hidden name="systabid" value='<%=systabid%>'></input>
<input type=hidden name="tabid" value='<%=tabid%>'></input>
</form>
</body>
<script>
function returnInput()
{
	editSysForm.submit();
}
</script>
