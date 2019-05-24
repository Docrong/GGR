<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%
String roomName = request.getAttribute("roomName").toString();
%>
<html:form method="post" action="/TawRmRecord/search">

<center>
<br>
<table cellpadding="1" cellspacing="0" border="1" width="500" align="center">
<tr>
<td class="table_title">
    <bean:message key="TawRmRecord.hander"/></font>
</td>
<td >
    <font size="2" face="Tahoma">
<%String dm="admin";%>
     <input name="dutymansub" id="dutymansub" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" maxlength="150" size="20" value="<%=dm%>" />
    </font>
</td>
</tr>
<tr>
<td>
    <bean:message key="TawRmRecord.handerpassword"/>
</td>
<td >
    <font size="2" face="Tahoma">
    </font>
</td>
</tr>

<tr>
<td>
    <bean:message key="TawRmRecord.receiver"/>
</td>
<td >
    <font size="2" face="Tahoma">
    <input name="dutymansub" id="dutymansub" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" maxlength="150" size="20" value="<logic:iterate id="tawRmRecord" name="TAWRMRECORD_RECEIVERMASTER" type="com.boco.eoms.duty.model.TawRmRecord"><bean:write name="tawRmRecord" property="dutymaster" scope="page"/> </logic:iterate>" />
    </font>
</td>
</tr><tr>
<td>
    <bean:message key="TawRmRecord.receiverpassword"/>
</td>
<td >
    <font size="2" face="Tahoma">
    </font>
</td>
</tr>

<tr bgcolor="#FFFFFF">
<td colspan="2">
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
</td>
</tr>
<tr align="center">
<td colspan="2" align="center">
      <html:submit property="strutsButton">
         <bean:message key="label.yes"/>
      </html:submit>
  </td>
</tr>
  </table>
<br>
<table cellpadding="1" cellspacing="0" border="1" width="500" align="center">
<tr>
<td>
    <bean:message key="TawRmRecord.hander"/>
</td>
<td >
    <font size="2" face="Tahoma">
	<logic:iterate id="tawRmRecord" name="TAWRMRECORDS" type="com.boco.eoms.duty.model.TawRmRecord"> <bean:write name="tawRmRecord" property="hander" scope="page"/> </logic:iterate>
    </font>
</td>
</tr>
<tr>
<td>
    <bean:message key="TawRmRecord.dutyman"/>
</td>
<td >
    <font size="2" face="Tahoma">
        <logic:iterate id="tawRmRecord" name="TAWRMRECORD_DUTYMAN" type="com.boco.eoms.duty.model.TawRmRecord"><bean:write name="tawRmRecord" property="dutyman" scope="page"/> </logic:iterate>
    </font>
</td>
</tr>

<tr>
<td>
    <bean:message key="TawRmRecord.receiver"/>
</td>
<td >
    <font size="2" face="Tahoma">
    <logic:iterate id="tawRmRecord" name="TAWRMRECORD_RECEIVER" type="com.boco.eoms.duty.model.TawRmRecord"><bean:write name="tawRmRecord" property="receiver" scope="page"/> </logic:iterate>
    </font>
</td>
</tr>
<tr bgcolor="#FFFFFF">
<td colspan="2">
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
</td>
</tr>
<tr align="center">
</tr>
  </table>
  </center>
</html:form>