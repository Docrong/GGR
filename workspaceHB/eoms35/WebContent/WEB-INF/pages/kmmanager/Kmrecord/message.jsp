<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.* , com.boco.eoms.common.controller.*"%>
<%@ page import="com.boco.eoms.duty.dao.TawRmRecordLockDAO"%>

<html>
<head>
<title>table</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<meta http-equiv="refresh" content="30">
</head>
	<body bgcolor="#FFFFFF" text="#000000" topmargin=0 leftmargin=0 onLoad="init();" >
</body>

<%
SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");

String userId = "";
int count=0;
if(saveSessionBeanForm != null){
userId=saveSessionBeanForm.getWrf_UserID();
}
com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
TawRmRecordLockDAO tawRmRecordLockDAO = new TawRmRecordLockDAO(ds);
count = tawRmRecordLockDAO.count(userId);
%>


<script language="JavaScript">

function init(){
  var i = <%=String.valueOf(count)%>;
  if( i != '0'){
  var win;
  if (win && win.open && !win.closed)
    win.close();
  win = window.open('alert.jsp', 'newwindow', 'min=no,height=100, width=300, top=300,left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
 }
}
</script>

</body>
</html>

