<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%
Vector SelectRoom = (Vector)request.getAttribute("SelectRoom");
Vector SelectRoomName = (Vector)request.getAttribute("SelectRoomName");
%>
<head>
<title><bean:message key="TawRmDefineTree.title1"/></title>

</head>
<form method="post" name="form1" action="">
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center" class="clsbkgd">
  <tr>
<td class="clsscd" align="center">

    &nbsp;<bean:message key="TawRmDefineTree.selectroom"/>

  </td>
</tr>
  <tr>
<td>
<table width="100%">
<tr align="center" valign="middle">
<td class="clsfth1">
<%String is_Admin = String.valueOf(request.getAttribute("is_Admin"));%>
               <select name="roomId">
<%if (is_Admin.equals("1")){%>
               <option value='-1'><bean:message key="TawRmDefineTree.systemroom"/></option>
<%}%>
               <%
               for(int i=0;i<SelectRoom.size();i++) {
               %>
               <option value='<%=SelectRoom.get(i).toString()%>'><%=SelectRoomName.get(i).toString()%></option>
               <%}%>
               </select>
</td>
</tr>
<input type="hidden" name="roomName">

<tr align="center" valign="middle">
<td class="clsfth1">
      <input type="button" Class="clsbtn2" onclick="putValue()" value='<bean:message key="TawRmSysteminfo.enter_room"/>'/>
  </td>
</tr>
</table>
</td>
</tr>
  </table>

</form>
<script language="javascript">
function putValue()
{
form1.roomName.value=form1.roomId.options[form1.roomId.selectedIndex].text
form1.action="listview.do";
form1.submit();
//window.location.href="../manager/TawRmDefineTree/DefineTree.jsp?roomId="+form1.roomId.value;
}
</script>
<%@ include file="/common/footer_eoms.jsp"%>

