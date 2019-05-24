<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%
Vector SelectRoom = (Vector)request.getAttribute("SelectRoom");
Vector SelectRoomName = (Vector)request.getAttribute("SelectRoomName");
%>
<script language="javascript">
function confirm(){

	var frm = document.forms[0];
	
	
        var starttime=frm.starttime.value;
        var endtime=frm.endtime.value;
       
        if(endtime<starttime){
		alert("结束时间不能小于开始时间");
		return false;
          }
       

	return true;
}


</SCRIPT>
<head>
<title>考核记录</title>

</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmRecord/checkdetail">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td align="center" class="table_title" colspan="2"><bean:message key="label.query"/>考核记录</td>
</tr>
</table>
<table border="0" width="500" cellspacing="1" cellpadding="1" class="table_show" align=center>

</tr>
<tr class="tr_show">
<td class="clsfth">
    开始时间
</td>
<td>
    <eoms:SelectDate name="starttime" formName="tawRmRecordForm" day="1"/>
</td>
</tr>
</tr>
<tr class="tr_show">
<td class="clsfth">
    结束时间
</td>
<td>
    <eoms:SelectDate name="endtime" formName="tawRmRecordForm" day="2"/>
</td>
</tr>
<tr class="tr_show">
<td class="clsfth">
    选择机房
</td>
<td>
    <select name="roomId">
	<option value=''>全部机房</option>
               <%
               for(int i=0;i<SelectRoom.size();i++) {
               %>
               <option value='<%=SelectRoom.get(i).toString()%>'><%=SelectRoomName.get(i).toString()%></option>
               <%}%>
               </select>
</td>
</tr>
  </table>
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td height="32" align="right">
      <html:submit property="strutsButton" styleClass="clsbtn2" onclick="return confirm();">
         <bean:message key="label.query"/>
      </html:submit>
  </td>
</tr>
  </table>
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
</html:form>
</body>

