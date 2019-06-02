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
    &nbsp;&nbsp;<bean:message key="label.list"/> TawRmDutyfile
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
        <bean:message key="TawRmDutyfile.id"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmDutyfile.workserial"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmDutyfile.filename"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmDutyfile.encodename"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmDutyfile.dutyman"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmDutyfile.transtime"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmDutyfile.class_no"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <bean:message key="TawRmDutyfile.notes"/>
        </b></font></td>
        <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b></b></font></td>
     </tr>

              <bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%>
              <logic:iterate id="tawRmDutyfile" name="TAWRMDUTYFILES" type="com.boco.eoms.duty.model.TawRmDutyfile">

<tr bgcolor="#FFFFFF">
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmDutyfile" property="id" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmDutyfile" property="workserial" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmDutyfile" property="filename" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmDutyfile" property="encodename" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmDutyfile" property="dutyman" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmDutyfile" property="transtime" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmDutyfile" property="class_no" scope="page"/>

    </b></font></td>
     <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
                    <bean:write name="tawRmDutyfile" property="notes" scope="page"/>

    </b></font></td>
    <td style="border-left-width: 1; border-right-width: 1; border-top-width: 1; border-bottom-style: solid; border-bottom-width: 1; padding-left: 6; padding-right: 6; padding-top: 3; padding-bottom: 3"><font face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#022077"><b>
        <html:link href="TawRmDutyfile/edit.do" paramId="id" paramName="tawRmDutyfile" paramProperty="id"><bean:message key="label.edit"/></html:link>&nbsp;
        <html:link href="TawRmDutyfile/view.do" paramId="id" paramName="tawRmDutyfile" paramProperty="id"><bean:message key="label.view"/></html:link>&nbsp;
        <html:link href="TawRmDutyfile/remove.do" paramId="id" paramName="tawRmDutyfile" paramProperty="id"><bean:message key="label.remove"/></html:link>

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

