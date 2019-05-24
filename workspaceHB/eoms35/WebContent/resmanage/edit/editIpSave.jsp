<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page  import="com.boco.eoms.common.util.*"%>
<%@page import ="com.boco.eoms.jbzl.bo.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 显示资源列表信息
*@ version 1.0
**/
%>
<%
   int pageid;
   if(request.getParameter("pageid") != null)
		pageid = Integer.parseInt(request.getParameter("pageid"));
   else
	    pageid=1;
   String state = null;
	if(request.getParameter("state") != null)
		state = request.getParameter("state");
	else
		state ="0";
	String sId = null;
	if(request.getParameter("id") != null)
		sId = request.getParameter("id");
	else
		sId = "0";
	String id=request.getParameter("pi_id");
	out.println("id::"+id);
	entityoperate Ent = new entityoperate();
	int row = Ent.updateIpapply(sId,id,state);
	out.println("row::"+row);
	
%>
<%
String sucMsg="";
if (row>0)
{
	sucMsg = "审批成功";
}
String retpage = null;
retpage = "editIpList.jsp?pageid="+pageid+"&state=140";
%>
	<body onload="returnInput()">
	<form action="<%=retpage%>" method=POST name=editSaveForm>
	<input type=hidden name=SucMsg value='<%=sucMsg%>'>
	<input type=hidden name=id value='<%=sId%>'>
    <input type=hidden name='pageid' value=<%=pageid%>>
	</form>
	</body>
<script>
function returnInput()
{
	editSaveForm.submit();
}
</script>