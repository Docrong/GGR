<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
      GregorianCalendar cal_start = new GregorianCalendar();
      cal_start.add(cal_start.DATE,-1);
      String str_start = StaticMethod.Cal2String(cal_start);
      str_start = String.valueOf(StaticMethod.getVector(str_start," ").elementAt(0));

Vector SelectRoom = (Vector)request.getAttribute("SelectRoom");
Vector SelectRoomName = (Vector)request.getAttribute("SelectRoomName");
%>
<head>
<title>值班周报统计</title>

<script language="javascript">
	function onStat()
	{
		if(tawRmRecordPerForm.starttime.value>tawRmRecordPerForm.endtime.value){
			alert(" \"开始时间\"应小于\"结束时间\"!");
			return false;
		}
		tawRmRecordPerForm.submit();
        return true;
	}
</script>
</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmRecordPer/weekstatdo.do">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td align="center" valign="middle" class="table_title" colspan="2">值班周报统计</td>
</tr>
</table>
<table  cellspacing="1" cellpadding="1" border="0" width="500" align="center" class="table_show">

<tr class="tr_show">
<td class="clsfth">
     <bean:message key="TawRmSysteminfo.select_room"/>
</td>
<td>
               <select name="roomId">
               <option value='-1' selected>请选择</option>
               <%
               for(int i=0;i<SelectRoom.size();i++) {
               %>
               <option value='<%=SelectRoom.get(i).toString()%>'><%=SelectRoomName.get(i).toString()%></option>
               <%}%>
               </select>
</td>
</tr>
<tr class="tr_show">
<td class="clsfth">
    开始时间
</td>
<td>
    <eoms:SelectDate name="starttime" formName="tawRmRecordPerForm" flag = "-1" value = "<%=str_start%>"/>
</td>
</tr>
<tr class="tr_show">
<td class="clsfth">
    结束时间
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

<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr align="center">
<td height="32" align="right">
       <input type="button" value="统计" onClick="javascript:onStat();" class="clsbtn2">
  </td>
</tr>
</table>
</html:form>
</body>

