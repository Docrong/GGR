<%@ page contentType="text/html; charset=gb2312"%>

<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.Date" %>


<head>
<title>排班查询结果</title>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmAssignwork/query_result">
<html:hidden property="strutsAction"/>
<br>
<center>
<table cellpadding="0" cellspacing="0" border="0" width="780">
<tr>
    <td width="100%" align="center" class="table_title">
    &nbsp;<bean:message key="TawRmAssignwork.tablename"/>
    </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="780">
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
<bean:message key="TawRmAssignwork.begin_date"/>
</td>
<td>
星期日
</td>
<td>
星期一
</td>
<td>
星期二
</td>
<td>
星期三
</td>
<td>
星期四
</td>
<td>
星期五
</td>
<td>
星期六
</td>
</tr>

<%
//得到查询的第一天是星期几
String strDutydate=String.valueOf(getDutydate.elementAt(0));
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date=dateFormat.parse(strDutydate);
GregorianCalendar gCalendar = new GregorianCalendar();
gCalendar.setTime(date);
int dayOfWeek=gCalendar.get(gCalendar.DAY_OF_WEEK);
%>

<%
int i=0;
while(i<getDutydate.size()){
  strDutydate=String.valueOf(getDutydate.elementAt(i));
  int p=i;
  int count=0;
  int countMan=0;
  while(p<getDutydate.size()
        && strDutydate.trim().equals(String.valueOf(getDutydate.elementAt(p)).trim())){
    p++;
    count++;
  }
%>
<tr class="tr_show">
<td>
<table>
<tr align="center" valign="middle">
<td>
&nbsp;
</td>
</tr>
<%
  for(int j=i;j<i+count;j++){
%>
<tr align="center" valign="middle">
<td  nowrap>
<%
    if(j < getDutydate.size()){ %>
<%=String.valueOf(getStarttime.elementAt(j)).substring(11,19)%>
</td>
</tr>
<%
    }
  }
%>
</table>
</td>

<%
  for(int k=1;k<8;k++){
    if(dayOfWeek != 1){
%>
<td  class="clsfth1">
<table>
<%
      for(int j=0;j<count+1;j++){
%>
<tr align="center" valign="middle">
<td>
&nbsp;
</td>
</tr>
<%
      }
%>
</table>
</td>

<%
      dayOfWeek--;
      continue;
    }
%>

<td>
<table>
<tr align="center" valign="middle">
<td nowrap>
<%
    if(i < getDutydate.size()){
      if(i==0)
        countMan=count;
      else
        countMan=0;
      strDutydate=String.valueOf(getDutydate.elementAt(i));
      int x=i;
      if(i>0 && !strDutydate.trim().equals(String.valueOf(getDutydate.elementAt(i-1)).trim())){
        while(x < getDutydate.size()
              && strDutydate.trim().equals(String.valueOf(getDutydate.elementAt(x)).trim())){
          x++;
          countMan++;
        }
      }
%>
<b><font color="#CC0000" face="Arial"><%=String.valueOf(getDutydate.elementAt(i))%></font></b>
</td>
<%
    }
%>
</tr>
<% int n=i;%>
<%
    for(int j=n;j<n+countMan;j++){
%>
<tr align="center" valign="middle">
<td>
<%
      if (j < getDutydate.size()){
 %>
<%=String.valueOf(getDutyman.elementAt(j))%>
</td>
</tr>
<%
        i++;
      }
    }
%>
</table>
</td>
<%
  }
%>

</tr>
<%
}}
%>
</table>
</td>
</tr>
</table>
</center>
</html:form>
</body>


