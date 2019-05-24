<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<html:form method="post" action="/TawRmSysteminfo/save">
<html:hidden property="strutsAction"/>

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

    <c:choose>
      <c:when test="${requestScope['tawRmSysteminfoForm'].strutsAction == 1}">
        <bean:message key="label.edit"/>
      </c:when>
      <c:otherwise>
        <bean:message key="label.edit"/>
      </c:otherwise>
    </c:choose>
    &nbsp;<bean:message key="TawRmSysteminfo.tablename"/>

  </td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="30%"></td><td width="60%"></td>
</tr>
  <tr>
<td>
<table bgcolor="#f2f2f2" width="500" cellspacing="0" border="0">
    <tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="30%"></td><td width="60%"></td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="30%"></td><td width="60%"></td>
</tr>
    <c:choose>
      <c:when test="${requestScope['tawRmSysteminfoForm'].strutsAction == 1}">
    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.roomName"/></b></font></td><td width="60%"><font size="2" face="Tahoma"><html:hidden property="roomId" /><%=request.getAttribute("ROOMNAME") %></font></td>
</tr>
    </c:when>
    <c:otherwise>
    <html:hidden property="roomId"/>
    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.roomName"/></b></font></td><td width="60%"><font size="2" face="Tahoma"><html:hidden property="roomId" /><%=request.getAttribute("ROOMNAME") %></font></td>
</tr>
    </c:otherwise></c:choose>
<!--<tr><td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.deptId"/></b></font></td><td width="60%"><font size="2" face="Tahoma">-->
<html:hidden property="deptId" />
<!--</font></td></tr>-->
    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.maxdutynum"/></b></font></td>
<td width="60%">
<font size="2" face="Tahoma">
<html:select property="maxdutynum">
<html:options collection="MAXDUTYNUM" property="value"  labelProperty="label"/>
</html:select>
<bean:message key="TawRmSysteminfo.maxdutynum_unit"/>
</font>
</td>
</tr>

    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.maxerrortime"/></b></font></td>
<td width="60%">
<font size="2" face="Tahoma">
<html:select property="maxerrortime">
<html:options collection="MAXERRORTIME" property="value"  labelProperty="label"/>
</html:select>
<bean:message key="TawRmSysteminfo.maxerrortime_unit"/>
</font>
</td>
</tr>

    <c:choose>
      <c:when test="${requestScope['tawRmSysteminfoForm'].strutsAction == 1}">
    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmExchange.exchangetime"/></b></font></td>
<td width="60%"><font size="2" face="Tahoma"><input type="text" name="exchangetime" value = "8:00,17:00"></font></td>
</tr>
      </c:when>
      <c:otherwise>
    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmExchange.exchangetime"/></b></font></td>
<td width="60%"><font size="2" face="Tahoma"><input type="text" name="exchangetime" value =<%=request.getAttribute("exchangetime")%>></font></td>
</tr>
      </c:otherwise>
    </c:choose>

    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.exRequest"/></b></font></td>
<td width="60%">
<font size="2" face="Tahoma">
<html:select property="exRequest">
<html:options collection="EXREQUEST" property="value"  labelProperty="label"/>
</html:select>
</font>
</td>
</tr>
    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.exAnswer"/></b></font></td>
<td width="60%">
<font size="2" face="Tahoma">
<html:select property="exAnswer">
<html:options collection="EXANSWER" property="value"  labelProperty="label"/>
</html:select>
</font>
</td>
</tr>
    <tr>
<td width="5%"></td><td width="30%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawRmSysteminfo.dutyInform"/></b></font></td>
<td width="60%">
<font size="2" face="Tahoma">
<html:select property="dutyInform">
<html:options collection="DUTYINFORM" property="value"  labelProperty="label"/>
</html:select>
</font>
</td>
</tr>
    <tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="30%"></td><td width="60%">

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
  </table>
</td>
</tr>
  <tr>
<td>
<table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="center" valign="middle">
<td>

      <html:submit>
        <bean:message key="label.save"/>
      </html:submit>
      <INPUT id=transmit type=button value=<bean:message key="TawUserRoom.butnew"/>  name=button Onclick="window.location.href ='../TawUserRoom/new.do?roomId=<bean:write name="tawRmSysteminfoForm" property="roomId"/>'">&nbsp;&nbsp;
      <html:cancel>
        <bean:message key="label.cancel"/>
      </html:cancel>

  </td>
</tr>
</table>
</td>
</tr>
  </table>
</td>
</tr>
</table>

</html:form>


