
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html:html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<%
     String path=(String)request.getSession().getAttribute("path");
%>
<title><bean:write name="msg" scope="request" filter="false"/></title>
<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/template/img/bg001.gif">
   <tr>
      <td width="100%" height="100%" align="center">
          <table width="743" height="440" background="<%=request.getContextPath()%>/images/bg001.jpg">
           <tr>
             <td align="center">
               <font style="font-size:14px;color:#CC0000;"><strong><bean:write name="msg" scope="request" filter="false"/></strong></font>
             </td>
           </tr>
           <tr style="display:none">
             <td align="center">
               <font style="font-size:14px;color:#CC0000;"><strong><a href="<%=path%>">${eoms:a2u('返回')}</a></strong></font>
             </td>
           </tr>
          </table>
    </td>
 </tr>
</table>
</body>
</html:html>