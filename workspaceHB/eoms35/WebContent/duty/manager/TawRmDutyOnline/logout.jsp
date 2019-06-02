<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*,com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<%
String logout=(String)request.getParameter("logout");

if (logout.equals("yes")) {
	synchronized (application) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
                HashMap DeptHash=(HashMap)application.getAttribute("DeptHash");
                int userNumbers=((Integer)application.getAttribute("userNumbers")).intValue();
		Vector UserName=null;
                UserName=(Vector)DeptHash.get(saveSessionBeanForm.getUsername().trim());
		//UserName.remove(session.getAttribute("UserName"));
                userNumbers--;
                application.setAttribute("userNumbers",new Integer(userNumbers));
	}

	synchronized (application) {
		String Name = (String) session.getAttribute("UserName");
		Vector outMessage=null;
		outMessage= (Vector)application.getAttribute("Message");
		if(outMessage==null)
		outMessage= new Vector(30,10);
		String outstr="系统公告：<font color=blue>"+Name+"</font>依依不舍的离开了聊天室";
		String str1="no";
		String systemSpeak = "yes";
		outMessage.addElement(str1);
		outMessage.addElement(Name);
		outMessage.addElement(str1);
		outMessage.addElement(outstr);
		outMessage.addElement(systemSpeak);
		outMessage.addElement(str1);
		application.setAttribute("Message", outMessage);
		//session.destroy("Name");
		%>
		<script language="JavaScript">
			window.close();
		</script>
		<%
	}
}
%>

