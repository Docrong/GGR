<%@ page language="java" errorPage="/error.jsp" pageEncoding="gb2312" contentType="text/html;charset=gb2312" %>
<%@ include file="../../../duty/taglibs.jsp"%>
 <%@ include file="../../../duty/header_eoms_form.jsp"%>
<html>
<head>
 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<frameset rows = "*,80,50" frameborder="no" frameborder="6" framespacing="3" bordercolor="#E2F1FC" scrolling="NO" >
	<frameset cols = "*,200" frameborder="YES" border="3" framespacing="2">
		<frameset rows = "25,*,25,80" frameborder="5" border="0" framespacing="2">
 	 	    <frame src = "../manager/TawRmDutyOnline/chattitle.jsp" name="tFrame" scrolling="no">
 	 	    <frame src = "../manager/TawRmDutyOnline/show.jsp" name="mainFrame" scrolling="auto">
 	 	    <frame src = "../manager/TawRmDutyOnline/privatetitle.jsp" name="sFrame" scrolling="no">
            <frame src = "../manager/TawRmDutyOnline/Privateshow.jsp" name="privateFrame" scrolling="auto">
		</frameset>
		<frameset rows = "80,*,0" frameborder="0" border="0" framespacing="0" >
			<frame name = "image" src="../manager/TawRmDutyOnline/image.htm" scrolling="NO">
			<frame name = "userlistFrame" src="../manager/TawRmDutyOnline/userlist.jsp">
			<frame name = "hiddenFrame" src="../manager/TawRmDutyOnline/transact.jsp">
		</frameset>
	</frameset>
	<frame src="../manager/TawRmDutyOnline/input.jsp?username=<%= session.getAttribute("UserName") %>" name="inputFrame" scrolling="no" >
	<frame src="<%=request.getContextPath()%>/duty/TawRmDutyOnline/file.do?"   scrolling="no" name="fileFrame">
</frameset>
<noframes>
<body bgcolor="#FFFFFF">
 
</body>
</noframes>
</html>
