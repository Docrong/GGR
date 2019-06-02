<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<table bgcolor="#666666" cellpadding="1" cellspacing="0" border="0" width="500">
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="500">
<tr>
<td bgcolor="#fecc51">&nbsp;</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="500">
  <tr>
<td bgcolor="#d6e0ed">
    &nbsp;&nbsp;<bean:message key="label.list"/> TawRmExchange
  </td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="19%"></td><td width="76%"></td>
</tr>
    <tr>
<td>
<table bgcolor="#f2f2f2" width="500" cellspacing="0" border="0">
      <tr bgcolor="#bacce1">
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmExchange.roomId"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmExchange.id"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmExchange.exchangetime"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b></b></font></td>
     </tr>

              <bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%>
              <logic:iterate id="tawRmExchange" name="TAWRMEXCHANGES" type="com.boco.eoms.duty.model.TawRmExchange">

<tr bgcolor="#FFFFFF">
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmExchange" property="roomId" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmExchange" property="id" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmExchange" property="exchangetime" scope="page"/>

    </b></font></td>
    <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
<%
java.util.HashMap map = new java.util.HashMap();
map.put("roomId", String.valueOf(tawRmExchange.getRoomId()));
map.put("id", String.valueOf(tawRmExchange.getId()));
pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
%>
        <html:link href="TawRmExchange/edit.do" name="map" scope="page"><bean:message key="label.edit"/></html:link>&nbsp;
        <html:link href="TawRmExchange/view.do" name="map" scope="page"><bean:message key="label.view"/></html:link>&nbsp;
        <html:link href="TawRmExchange/remove.do" name="map" scope="page"><bean:message key="label.remove"/></html:link>

    </b></font></td>
</tr>

              </logic:iterate>

            </table>
</td>
</tr>
</table>
</td>
</tr>
</table>

