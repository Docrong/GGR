<%@ page language="java" errorPage="/error.jsp" pageEncoding="gb2312" contentType="text/html;charset=gb2312" %>
<%@ include file="../../../duty/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page language="java" import="java.sql.*,java.util.*" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<META http-equiv="refresh" content="8">

<script language="JavaScript">
	function PerformSubmit(user) {
	parent.inputFrame.chatForm.talkwith.value=user;
		
		return false;
	}
</script>

</HEAD>
<BODY style="margin:0;">
<FORM method="post" name="userlist_form" >
<%
String Name = (String) session.getAttribute("UserName");
boolean online = false;
if (Name == null) {
	session.setAttribute("online","no");
	out.print("你和聊天室已中断!");
	return;
}
synchronized(application) {
	HashMap ListUser=null;
	ListUser =(HashMap)application.getAttribute("DeptHash");
          int num=0;
          Collection coll=ListUser.values();
          for(Iterator itr=coll.iterator();itr.hasNext();)
          {
           num+=((Vector)itr.next()).size();
          }
        Integer userNumbers=(Integer)application.getAttribute("userNumbers");
	if(ListUser!=null) {
		%>
		<table width="100%">
		<tr class="tr_show"><td>在线人员[<font color="#ff0000"><%= num %></font>]</td></tr>
		<tr><td><a href="#" onClick=PerformSubmit("所有人")>所有人</a></td></tr>
		<%
                Vector UserVect=null;
		String User = null;
                String deptname=null;
                for(Iterator itr=ListUser.keySet().iterator();itr.hasNext();)
                {
		     deptname=(String)itr.next();
                     UserVect=(Vector)ListUser.get(deptname);
                 %>
		<tr><td style="word-break:keep-all;word-wrap:normal">[<%out.print(deptname);%>]</td></tr>
                   <%
		     for(int i=0;i<UserVect.size();i++) {
			User= (String) UserVect.get(i);
			if (Name.equals(User)) {
				online = true;
			}
			%>
			<tr><td style="word-break:keep-all;word-wrap:normal"><a href="#" class="l1" onClick=PerformSubmit("<%= User%>")>&nbsp&nbsp<%
			out.print(User);
			%></a></td></tr>
			<%
		}
                }
                out.println("</table>");
		if (online) {
			session.setAttribute("online","yes");
		}
		else {
			session.setAttribute("online","no");
		}
	}
}
%>
</FORM>
</BODY>
</HTML>
