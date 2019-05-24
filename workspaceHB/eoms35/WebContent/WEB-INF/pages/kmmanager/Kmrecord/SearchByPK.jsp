<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%
String roomId = (String)request.getAttribute("roomId");
%>
<head>
<title></title>
 
</head>
<html:form method="post" action="/Kmrecord/search">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td align="center" class="table_title" colspan="2"><bean:message key="label.query"/></td>
</tr>
</table>
 <input type="hidden" name="roomId" value="<%=roomId %>" >
<table border="0" width="500" cellspacing="1" cellpadding="1" class="formTable" align=center>
<tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecord.starttime"/>
</td>
<td>
	<eoms:SelectDate name="starttime" formName="kmrecordForm"/>
</td>
</tr>
<tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecord.endtime"/>
</td>
<td>
	<eoms:SelectDate name="endtime" formName="kmrecordForm"/>
</td>
</tr>
<%--<tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecord.dutyrecord"/>
</td>
<td>
    <input type='text' name = 'dutyRecord' value = '' size="40"/>
</td>
</tr>
  --%></table>
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td height="32" align="left">
      <html:submit property="strutsButton" styleClass="button">
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
<%@ include file="/common/footer_eoms.jsp"%>

