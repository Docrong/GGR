<%@ page contentType="text/html; charset=gb2312"%>

<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<head>
<title>排班查询结果</title>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmAssignwork/query_result">
<html:hidden property="strutsAction"/>
<br>
<center>
<table cellpadding="0" cellspacing="0" border="0" width="800">
  <tr>
  <td width="100%" align="center" class="table_title">
    &nbsp;<bean:message key="TawRmAssignwork.tablename"/>
  </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="800">
<tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
<td>
<%=request.getAttribute("ROOMNAME") %>
&nbsp;&nbsp;&nbsp;
<bean:message key="TawRmAssignwork.apparatusroom"/>
&nbsp;
<%=String.valueOf(request.getAttribute("TIMEFROM")).trim()%>
&nbsp;
<bean:message key="TawRmAssignwork.to"/>
&nbsp;
<%=String.valueOf(request.getAttribute("TIMETO")).trim()%>
&nbsp;
<bean:message key="TawRmAssignwork.assign"/>
</td>
</tr>
</table>
<br>
</td>
</tr>
<%
Vector vectorQueryResult = (Vector)request.getAttribute("QUERYRESULT");
Vector getDutydate = (Vector)vectorQueryResult.elementAt(0);
Vector getStarttime = (Vector)vectorQueryResult.elementAt(1);
Vector getEndtime = (Vector)vectorQueryResult.elementAt(2);
Vector getDutymaster =  (Vector)vectorQueryResult.elementAt(3);
Vector getDutyman = (Vector)vectorQueryResult.elementAt(4);
%>
<tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<%if (getDutydate.size() == 0){%>
<tr class="tr_show">
<td colspan=10>
<bean:message key="TawRmAssignwork.alertnoassign"/>
</td>
</tr>
<%
}
else
{
%>
<tr class="td_label">
<td>
<bean:message key="TawRmAssignwork.dutydate"/>
</td>
<td>
星期
</td>
<td>
<bean:message key="TawRmAssignwork.begin_date"/>
</td>
<td>
<bean:message key="TawRmAssignwork.end_date"/>
</td>
<td>
<bean:message key="TawRmAssignwork.dutymaster"/>
</td>
<td>
<bean:message key="TawRmAssignSub.dutyman"/>
</td>
</tr>
<%
Vector td_num = new Vector();
String temp_Dutydate = "";
int tdNum = 0;
if (getDutydate.size() > 0){
temp_Dutydate = String.valueOf(getDutydate.elementAt(0));
}
for(int i=0;i<getDutydate.size();i++){
td_num.add("0");
if (i<getDutydate.size()-1){
  if (temp_Dutydate.equals(String.valueOf(getDutydate.elementAt(i)))){
      tdNum++;
      }
  else
      {
      temp_Dutydate = String.valueOf(getDutydate.elementAt(i));
      td_num.setElementAt(String.valueOf(tdNum),i-tdNum);
      i--;
      tdNum = 0;
      }
  }
else
{
temp_Dutydate = String.valueOf(getDutydate.elementAt(i));
td_num.setElementAt(String.valueOf(tdNum+1),i-tdNum);
}
}
%>

<%
GregorianCalendar cal = new GregorianCalendar();
String weekName[] = {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
for(int i=0;i<getDutydate.size();i++){
%>
<tr class="tr_show">
<%if (!String.valueOf(td_num.elementAt(i)).equals("0")){%>
<td  rowspan="<%=String.valueOf(td_num.elementAt(i))%>">
<%=String.valueOf(getDutydate.elementAt(i))%>
</td>
<%
   Vector date_vector = StaticMethod.getVector(String.valueOf(getDutydate.elementAt(i)).trim(),"-");
   int year=Integer.parseInt(String.valueOf(date_vector.elementAt(0)).trim());
   int month=Integer.parseInt(String.valueOf(date_vector.elementAt(1)).trim());
   int day=Integer.parseInt(String.valueOf(date_vector.elementAt(2)).trim());
   java.util.Date date = new java.util.Date(year-1900,month-1,day-0);

   int numOfWeek = date.getDay();
%>
<td rowspan="<%=String.valueOf(td_num.elementAt(i))%>"  <%if (numOfWeek==0 || numOfWeek==6){%> <%}else{%><%}%>>
<%=weekName[numOfWeek]%>
</td>
<%}%>
<td >
<%=String.valueOf(getStarttime.elementAt(i)).substring(11,19)%>
</td>
<td >
<%=String.valueOf(getEndtime.elementAt(i)).substring(11,19)%>
</td>
<td >
<%=String.valueOf(getDutymaster.elementAt(i))%>
</td>
<td >
<%=String.valueOf(getDutyman.elementAt(i))%>
</td>
</tr>
<%}}%>
</table>
</td>
</tr>
  </table>
<table cellpadding="0" cellspacing="0" border="0" width="800">
<tr>
<td height="32" align = 'right'>
<input type='button' Class="clsbtn2"  value='打印' onclick = 'javascript:window.print()'>
<td>
</tr>
</table>
</center>
</html:form>
</body>

