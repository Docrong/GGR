<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%
int roomId = Integer.parseInt(String.valueOf(request.getAttribute("roomId")).trim());
%>
<html:form method="post" action="/TawRmSysteminfo/save">
<table>
<tr>
<td align=center>
      <INPUT id=transmit type=button value=<bean:message key="TawUserRoom.savesuccess"/>  name=button Onclick="window.location.href ='../TawUserRoom/new.do?roomId=<%=roomId%>'">&nbsp;&nbsp;
</td>
</tr>
</table>
</html:form>