<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ page language="java" import ="com.boco.eoms.common.controller.SaveSessionBeanForm"%>

<eoms:MonthList/>
<eoms:WeekList/>
<%
Vector SelectRoom = (Vector)request.getAttribute("SelectRoom");
Vector SelectRoomName = (Vector)request.getAttribute("SelectRoomName");
%>
<head>
<title>值班月报统计</title>

<script language="JavaScript">
			function initTime() {
				<%
				String tempTime="";
				String current=StaticMethod.getCurrentDateTime();
				String tempYear=current.substring(0,4);
				String tempMon=current.substring(5,7);
				String month=current.substring(5,7);
				current = current.substring(0,7)+"-01";
				String options_year = "";//初始化年份下拉选项
				for(int year=2000;year<2030;year++){
				  options_year += "<option value='"+year+"'"+(year == Integer.parseInt(tempYear) ? " selected" : "")+">"+year+"</option>";
				}
				String options_month = "";//初始化月份下拉选项
				for(int mon=1;mon<13;mon++){
				  //if (mon<10){
				    //tempTime="0"+String.valueOf(mon);
				  //}else{
				  	tempTime=String.valueOf(mon);
				  //}
				  options_month += "<option value='"+tempTime+"'"+(mon == Integer.parseInt(tempMon) ? " selected" : "")+">"+tempTime+"</option>";
				}
				%>
			}
		</script>
</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmRecordPer/monthstatdo.do">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td align="center" valign="middle" class="table_title" colspan="2">值班月报统计</td>
</tr>
</table>
<table  cellspacing="1" cellpadding="1" border="0" width="500" align="center" class="table_show">

<tr class="tr_show">
<td class="clsfth">
    时间选择
</td>
<td>
	<html:select property="yearSelect" style="width: 1.5cm;" value = "">
		<%=options_year%>
	</html:select>
	<html:select property="monthSelect" style="width: 1.5cm;" value = "">
		<%=options_month%>
	</html:select>
</td>
</tr>
<tr class="tr_show">
<td class="clsfth">
     机房选择
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
      <html:submit property="strutsButton" styleClass="clsbtn2">
          统计
      </html:submit>
  </td>
</tr>
</table>
</html:form>
</body>

