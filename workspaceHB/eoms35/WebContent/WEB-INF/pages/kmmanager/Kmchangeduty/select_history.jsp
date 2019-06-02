<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%
Vector SelectRoom = (Vector)request.getAttribute("SelectRoom");
Vector SelectRoomName = (Vector)request.getAttribute("SelectRoomName");
%>
<head>
<title>查询换班记录</title>

</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmChangeduty/list">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td align="center" class="table_title">查询换班记录</td>
</tr>
</table>
<table border="0" width="500" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
<td class="clsfth">
     <bean:message key="TawRmSysteminfo.select_room"/>
</td>
<td>
               <select name="roomId">
               <%
               for(int i=0;i<SelectRoom.size();i++) {
               %>
               <option value='<%=SelectRoom.get(i).toString()%>'><%=SelectRoomName.get(i).toString()%></option>
               <%}%>
               </select>
</td>
</tr>
</tr>
<tr class="tr_show">
<td class="clsfth">
    申请时间，从
</td>
<td>
    <eoms:SelectDate name="inputdate_from" formName="tawRmChangedutyForm"/>
</td>
</tr>
</tr>
<tr class="tr_show">
<td class="clsfth">
    到
</td>
<td>
    <eoms:SelectDate name="inputdate_to" formName="tawRmChangedutyForm"/>
</td>
</tr>
  </table>
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td height="32" align="right">
      <html:submit property="strutsButton" styleClass="clsbtn2">
         <bean:message key="label.query"/>
      </html:submit>
  </td>
</tr>
</table>
      <logic:messagesPresent>
      <bean:message key="errors.header"/>
      <ul>
        <html:messages id="error">
          <li>
            <bean:write name="error"/>
          </li>
        </html:messages>
      </ul>
      <hr/>
    </logic:messagesPresent>
</html:form>
</body>

