<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.sql.*"%>
<% String sId=request.getParameter("sId");
   String cityId=request.getParameter("cityId");
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<title></title>
</head>

<body>
<form name="form1" method="post" ACTION="editUpload.jsp?sId=<%=sId%>&&cityId=<%=cityId%>" ENCTYPE="multipart/form-data">
<table width="100%" border="0" cellspacing="0">
    <tr>
      <td>ÇëÑ¡ÔñÎÄ¼þ£º <input type="file" name="textfield">
</table>
  <br>
  <br>
  <p align="center"> 
    <input type="submit" name="Submit" value="Ìá½»">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" name="Cancel" value="ÖØÖÃ">
  </p>
</form>
</body>
</html>
