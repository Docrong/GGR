
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

<logic:present name="tawBoardForm" scope="request">

        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.boardId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="boardId" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.parentId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="parentId" scope="request"/>

            </font></td>
</tr>
<tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth">\u4E3B\u9898\u7F16\u7801</td><td width="76%">
              <bean:write name="tawBoardForm" property="boardCode" scope="request"/>

            </td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.boardName"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="boardName" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.boardAdmin"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="boardAdmin" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.deptId"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="deptId" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.operSendNo"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="operSendNo" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.operCheckNo"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="operCheckNo" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.operReadNo"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="operReadNo" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.editSelfNo"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="editSelfNo" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.editNo"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="editNo" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.delNo"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="delNo" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.createTime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="createTime" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.modiTime"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="modiTime" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.childNum"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="childNum" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.articleNum"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="articleNum" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.infoType"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="infoType" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.comments"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="comments" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.operType"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="operType" scope="request"/>

            </font></td>
</tr>
        <tr>
<td width="5%"></td><td width="19%"><font color="#022077" size="1" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="TawBoard.deleted"/></b></font></td><td width="76%"><font size="2" face="Tahoma">
              <bean:write name="tawBoardForm" property="deleted" scope="request"/>

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
<td><html:form method="POST" action="/TawBoard/trash">
<%
String infoType=String.valueOf(request.getParameter("infoType"));
%>
  <input type="hidden" name="infoType" value="<%=infoType%>">
        <input type="hidden" name="boardId" value="<bean:write name="tawBoardForm" property="boardId" scope="request"/>">
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

