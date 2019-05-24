<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 用户组及用户管理
*@ version 1.0
**/
%>
<%
request.setCharacterEncoding("GBK");
String type = null;
if(request.getParameter("type") != null)
	type = request.getParameter("type");
else
	type ="0";
String pi_id[] = request.getParameterValues("pi_id");
int flag = 1;
for(int i = 0; i < pi_id.length; i ++)
{
	if(!RoomOpt.delDoc(Integer.parseInt(pi_id[i])))
		flag = 0;
}
String retpage = null;
if(flag == 0)
	out.println("<script>alert('删除文档失败!');history.go(-1);</script>");
else
	retpage = "editDocList.jsp";
%>
<body onload="returnInput()">
   <form action="<%=retpage%>" method=POST name=editSaveForm>
	<input type=hidden name=type value='<%=type%>'>
	<input type=hidden name=id value='202'>
	</form>
	</body>
<script>
function returnInput()
{
	var flag=<%=flag%>;
	if (flag==1)
		alert("文档删除成功！");
	editSaveForm.submit();
}
</script>