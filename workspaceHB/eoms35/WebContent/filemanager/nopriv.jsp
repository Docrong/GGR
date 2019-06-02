<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/template/img/bg001.gif">
   <tr>
      <td width="100%" height="100%" align="center"><table width="743" height="440" background="<%=request.getContextPath()%>/images/bg001.jpg">
			<tr>
				<td align="center">
		        <font style="font-size:14px;color:#CC0000;"><strong><bean:message key="label.nopriv"/></strong></font>
				</td>
			</tr>
		</table> </td>
    </tr>
</table>
</body>
</html:html>