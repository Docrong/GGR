<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
                 
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
    &nbsp;&nbsp;<bean:message key="label.list"/> TawRmAssignwork
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
        <bean:message key="TawRmAssignwork.id"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.regionId"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.roomId"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.dutydate"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.workid"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.flag"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.dutymaster"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.starttimeDefined"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.endtimeDefined"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.smsflag"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmAssignwork.notes"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b></b></font></td>
     </tr>

              <bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%>
              <logic:iterate id="tawRmAssignwork" name="TAWRMASSIGNWORKS" type="com.boco.eoms.duty.model.TawRmAssignwork">

<tr bgcolor="#FFFFFF">
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="id" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="regionId" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="roomId" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="dutydate" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="workid" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="flag" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="dutymaster" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="starttimeDefined" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="endtimeDefined" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="smsflag" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmAssignwork" property="notes" scope="page"/>

    </b></font></td>
    <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <html:link href="TawRmAssignwork/edit.do" paramId="id" paramName="tawRmAssignwork" paramProperty="id"><bean:message key="label.edit"/></html:link>&nbsp;
        <html:link href="TawRmAssignwork/view.do" paramId="id" paramName="tawRmAssignwork" paramProperty="id"><bean:message key="label.view"/></html:link>&nbsp;
        <html:link href="TawRmAssignwork/remove.do" paramId="id" paramName="tawRmAssignwork" paramProperty="id"><bean:message key="label.remove"/></html:link>

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

