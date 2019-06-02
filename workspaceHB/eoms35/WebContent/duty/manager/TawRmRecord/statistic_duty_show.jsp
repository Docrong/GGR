<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*,java.lang.*,com.boco.eoms.duty.model.DutyStatisticQO"%>

<%
   //取出第一行的时间段标题
   List dutyList = (List)request.getAttribute("DUTYLIST");
   String temp = dutyList.get(0).toString();
   dutyList.remove(0);
   String[] title = temp.split(",");
%>

<head>
<title>值班班次统计</title>
 
</head>
<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0" class="clssclbar">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center" valign="middle" class="table_title">
    &nbsp;&nbsp;统计结果 &nbsp;&nbsp;
  </td>
</tr>
    <tr>
<td>
<table border="0"  cellspacing="1" cellpadding="1" class="table_show" align=center width="90%">
<!--标题显示-->
    <tr class="td_label">
        <td>
          人员姓名
        </td>
<%  for(int i=0 ; i < title.length ; i++){
       out.println("<td>" + title[i] + "</td>");
    }
%>
     </tr>

<% //-------内容显示及格式调整
int k = 0;
for(int i=0 ; i < dutyList.size() ; i++){
  DutyStatisticQO dutyStatisticQO = new DutyStatisticQO();
  dutyStatisticQO = (DutyStatisticQO)dutyList.get(i);

  if ( k == 0 ){
%>
    <tr class="tr_show" align='center'>
        <td><%=dutyStatisticQO.getUserName()%></td>
<%
  }  //end if

  while( k < title.length ){
    if( !dutyStatisticQO.getDefinedTime().equalsIgnoreCase(String.valueOf(title[k])) ){
      out.println("<td>0</td>");
      ++k;
    }
    else
      break;
  }

  if ( k < title.length && dutyStatisticQO.getDefinedTime().equalsIgnoreCase(String.valueOf(title[k])) ){
     ++k;
     out.println("<td>" + dutyStatisticQO.getTimes() + "</td>");
  }

  if ( k >= title.length ){
     k = 0;
     out.println("</tr>");
  }

}
%>


</table>
</td>
</tr>
</table>
</body>

