<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<%-- Layout component
  parameters : title, header, navigate, body, footer
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/report/default.css" type="text/css">
<body bgcolor="#ffffff" text="#000000" link="#023264" alink="#023264" vlink="#023264" leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 width="800px">
<table border="0" width="100%" cellspacing="2" cellpadding="0">
  <tr>
    <td colspan="2"><tiles:insert attribute="topic" /></td>
  </tr>
  <tr>
    <td valign="top"  align="left" colspan="2" height="600"> <tiles:insert attribute="templet"/> </td>
  </tr>
</table>
</body>
</html>

