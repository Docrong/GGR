<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<table cellpadding="1" cellspacing="0" border="0" width="500">
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="500">
    <tr>
	<td class="table_title">
      &nbsp;&nbsp;<bean:message key="label.view"/>&nbsp;
    </td>
</tr>
<tr>
<td>

<table border="0" width="500" cellspacing="1" cellpadding="1" class="table_show" align=center>
<logic:present name="tawBoardForm" scope="request">
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.boardId"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="boardId" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.parentId"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="parentId" scope="request"/>

            </td>
</tr>
<tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth">\u4E3B\u9898\u7F16\u7801</td><td width="76%">
              <bean:write name="tawBoardForm" property="boardCode" scope="request"/>

            </td>
</tr>
<tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.boardName"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="boardName" scope="request"/>

            </td>
</tr>



        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.boardAdmin"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="boardAdmin" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.deptId"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="deptId" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.operSendNo"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="operSendNo" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.operCheckNo"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="operCheckNo" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.operReadNo"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="operReadNo" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.editSelfNo"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="editSelfNo" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.editNo"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="editNo" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.delNo"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="delNo" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.createTime"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="createTime" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.modiTime"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="modiTime" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.childNum"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="childNum" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.articleNum"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="articleNum" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.infoType"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="infoType" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.comments"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="comments" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.operType"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="operType" scope="request"/>

            </td>
</tr>
        <tr class="tr_show">
<td width="5%"></td><td width="19%" class="clsfth"><bean:message key="TawBoard.deleted"/></td><td width="76%">
              <bean:write name="tawBoardForm" property="deleted" scope="request"/>

            </td>
</tr>

</logic:present>
<logic:notPresent name="tawBoardForm" scope="request">
  <tr>
    <td>
    <bean:message key="error.notfound"/>
    </td>
  <tr>
</logic:notPresent>
</table>
</td>
</tr>
</table>
</td>
</tr>
</table>
