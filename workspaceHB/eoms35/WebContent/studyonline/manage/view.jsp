<%@ page contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*"%>
<html:html>
<head>
<%
String Name = StaticMethod.nullObject2String(request.getParameter("fileName"));
%>
<title>
<%=Name%>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">

</head>
<body>
  <table style="width:100%;height:100%">
    <tr>
      <td align="center" style="width:100%;height:90%">
         <img src="<%=request.getContextPath()%>/studyonline/upload/<%=Name%>" border="0">
      </td>
    </tr>
    <tr>
      <td align="right" style="width:100%;height:10%">
        <input type="button" value="¹Ø±Õ" onclick="return window.close()">
      </td>
    </tr>
  </table>
</body>
</html:html>

