<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%
/**
*@ E-DIS (�Ĵ�ʡ)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ �û��鼰�û�����
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
	out.println("<script>alert('ɾ���ĵ�ʧ��!');history.go(-1);</script>");
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
		alert("�ĵ�ɾ���ɹ���");
	editSaveForm.submit();
}
</script>