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
<title>值班日志修改通知</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<meta http-equiv="refresh" content="12">
</head>
	<body bgcolor="#FFFFFF" text="#000000" topmargin=0 leftmargin=0 onLoad="init();" >
</body>

<%
SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");

String userId = "";
String message = "";
if(saveSessionBeanForm != null){
userId=saveSessionBeanForm.getWrf_UserID();
}
com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
TawRmRecordLockDAO tawRmRecordLockDAO = new TawRmRecordLockDAO(ds);
message = tawRmRecordLockDAO.message(userId);
%>
<CENTER>
<H5><%=message%></H5><br/><FORM>
<INPUT TYPE=button VALUE="刷新" onClick="refresh_parent();">&nbsp;&nbsp;<INPUT TYPE=button VALUE="忽略" Window onClick="javascript:window.close();"></FORM>
</CENTER>
<script language="javascript">
function refresh_parent(){
//window.opener.parent.location.reload();
//window.opener.parent.location='<%=request.getContextPath()%>/duty/TawRmRecord/record.do';
var roomId=window.opener.parent.form1.roomId.value;
var tableId=window.opener.parent.form1.tableId.value;
var workserial=window.opener.parent.form1.workserial.value;
var tableDesc=window.opener.parent.form1.tableDesc.value;
window.opener.parent.location='<%=request.getContextPath()%>/duty/TawRmRecord/recordtable.do?action=EDIT&roomId='+roomId+'&tableId='+tableId+'&workserial='+workserial+'&tableDesc='+tableDesc;
window.close();
}
</script>
</body>
</html>


