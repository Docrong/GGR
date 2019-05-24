<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%
Vector SelectRoom = (Vector)request.getAttribute("SelectRoom");
Vector SelectRoomName = (Vector)request.getAttribute("SelectRoomName");
%>
<head>
<title>请选择机房</title>

</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<form method="post" name="form1" action="addDefRecord.do">
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center" class="clsbkgd">
  <tr>
<td class="clsscd" align="center">

    &nbsp;请选择机房

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
               <option value='-1'>系统机房</option>
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
      <input type="submit" Class="clsbtn2"  value='<bean:message key="TawRmSysteminfo.enter_room"/>'/>
  </td>
</tr>
</table>
</td>
</tr>
  </table>

</form>
<script language="javascript">
</script>
</body>

