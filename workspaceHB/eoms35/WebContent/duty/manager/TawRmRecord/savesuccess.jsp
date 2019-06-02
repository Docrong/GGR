<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>

<html:form method="post" action="/TawRmRecord/record.do">
    <HEAD>
        <title>\u00CCá\u00CA\u00BE\u00D2\u00B3</title>
        <META content="text/html; charset=gb2312" http-equiv="Content-Type">
		
    </HEAD>
    <body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/template/img/bg001.gif">
   <tr>
      <td width="100%" height="100%" align="center"><table width="743" height="440" background="<%=request.getContextPath()%>/images/bg001.jpg">
			<tr>
				<td align="center">
		        <font style="font-size:14px;color:#CC0000;"><strong>
				<bean:message key="TawUserRoom.savesuccess"/></strong></font><br><br>

                      <html:submit property="strutsButton">
                         <bean:message key="label.yes"/>
                      </html:submit>
				</td>
			</tr>
		</table> </td>
    </tr>
</table>
    </body>
</html:form>
