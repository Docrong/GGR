<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
      GregorianCalendar cal_start = new GregorianCalendar();
      cal_start.add(cal_start.DATE,-1);
      String str_start = StaticMethod.Cal2String(cal_start);
      str_start = String.valueOf(StaticMethod.getVector(str_start," ").elementAt(0));
	String roomId = (String)request.getAttribute("typeId");
//Vector SelectRoom = (Vector)request.getAttribute("SelectRoom");
//Vector SelectRoomName = (Vector)request.getAttribute("SelectRoomName");
%>
<head>
<title></title>


</head>
<html:form method="post" action="/TawRmRecordPer/showconfig.do">
<br>
<input type="hidden" name="roomId" value="<%=roomId%>" >
<table cellpadding="0" cellspacing="0" border="0" width="400" align="center">
<tr>
<td align="center" valign="middle" class="table_title" colspan="2"><bean:message key="TawRmRecordPer.dutystat"/></td>
</tr>
</table>
<table  cellspacing="1" cellpadding="1" border="0" width="400" align="center" class="formTable">
<%--<tr class="tr_show">
<td class="label">
     <bean:message key="TawRmSysteminfo.select_room"/>
</td>
<td>
               <select name="roomId">
               <%
               for(int i=0;i<SelectRoom.size();i++) {
               %>
               <option value='<%=SelectRoom.get(i).toString()%>'><%=SelectRoomName.get(i).toString()%></option>
               <%}%>
               </select>
</td>
</tr>
--%><tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecord.starttime"/>
</td>
<td>
    <eoms:SelectDate name="starttime" formName="tawRmRecordPerForm" flag = "0" value = "<%=str_start%>"/>
</td>
</tr>
<tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecord.endtime"/>
</td>
<td>

    <eoms:SelectDate name="endtime" formName="tawRmRecordPerForm" flag = "-1" value = "<%=str_start%>"/>
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

<table cellpadding="0" cellspacing="0" border="0" width="400" align="left">
<tr align="left">
<td height="32" align="left">
      
      <input type="submit" name="name" value =  <bean:message key="TawRmSysteminfo.enter_room"/> class="button" >
  </td>
</tr>
</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>

