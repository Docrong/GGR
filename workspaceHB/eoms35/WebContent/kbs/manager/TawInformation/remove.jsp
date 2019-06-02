

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

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
<td bgcolor="#d6e0ed">&nbsp;&nbsp;Delete Confirmation
    </td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="19%"></td><td width="76%"></td>
</tr>
    <tr>
<td>
<table bgcolor="#f2f2f2" width="500" cellspacing="0" border="0">
      <tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="19%"></td><td width="76%"></td>
</tr>
<tr bgcolor="#FFFFFF">
<td width="5%"></td><td width="19%"></td><td width="76%"></td>
</tr>

<logic:present name="tawInformationForm" scope="request">

        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.id"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="id" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.parentId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="parentId" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.boardId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="boardId" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.childNum"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="childNum" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.userId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="userId" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.deptId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="deptId" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.topic"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="topic" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.body"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="body" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.dateTime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="dateTime" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.modiUser"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="modiUser" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.modiTime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="modiTime" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.delTime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="delTime" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.hitNum"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="hitNum" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.attachName"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="attachName" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.modEffected"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="modEffected" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.timeLimited"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="timeLimited" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.confirmed"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="confirmed" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.deleted"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="deleted" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.infoType"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="infoType" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.checkInfo"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="checkInfo" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.important"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="important" scope="request"/>
            
            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawInformation.operType"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawInformationForm" property="operType" scope="request"/>
            
            </font></td>
</tr>

</logic:present>

    </table>
</td>
</tr>
    <tr>
<td>
<table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="right" valign="middle">
<td><html:form method="POST" action="/TawInformation/trash">
        <input type="hidden" name="id" value="<bean:write name="tawInformationForm" property="id" scope="request"/>">
                    <html:submit><bean:message key="label.remove"/></html:submit>
                    &nbsp;&nbsp;
                    <html:cancel><bean:message key="label.cancel"/></html:cancel>
            </html:form>
      
    </td>
</tr>
</table>
</td>
</tr>
  </table>
</td>
</tr>
</table>

