<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*,com.boco.eoms.duty.model.TawRmAssignwork"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<head>
<style>
#webfx {
    writing-mode: tb-rl;
}
</STYLE>
<title>统计</title>

</head>
<%!
 public String FormatDate(String time)
{
 String shorttime=time.substring(time.indexOf(" "));
 shorttime=shorttime.substring(0,shorttime.length()-3);
 return shorttime;
}
%>
<%
HashMap hashWorkRefSub=(HashMap)request.getAttribute("hashWorkRefSub");
Vector vecWorkNumofDayByMonth=(Vector)request.getAttribute("vecWorkNumofDayByMonth");
Vector vecWorkSerialofMonth=(Vector)request.getAttribute("vecWorkSerialofMonth");
TawRmAssignwork tawRmAssignwork=null;
String roomId=(String)request.getAttribute("roomId");
System.out.println(vecWorkNumofDayByMonth.size());
System.out.println(vecWorkSerialofMonth.size());
System.out.println("hash="+hashWorkRefSub);
%>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
 <%
  if(vecWorkNumofDayByMonth!=null && vecWorkNumofDayByMonth.size()>0)
  {
   int start=((Integer)vecWorkNumofDayByMonth.elementAt(0)).intValue();
   int m=0;
 %>
<table cellpadding="0" cellspacing="0" border="0"  align="center">
<tr>
<td align="center" class="table_title">
    &nbsp;自定义值班记录统计
  </td>
</tr>
</table>
  <table border="0"  cellspacing="1" cellpadding="1" class="table_show"  width="100%"  bgColor="#f3f3f3">

   <tr class="tr_show">
      <td  ></td>
      <%for(int i=1;i<vecWorkNumofDayByMonth.size();i++){%>
      <td     colspan="<%=(Integer)vecWorkNumofDayByMonth.elementAt(i)%>" align="center"><%=i+start-1%>号</td>
      <%}%>
    </tr>
    <tr class="tr_show">
      <td class="clsfth"  ></td>
    <%
    for(int i=1;i<vecWorkNumofDayByMonth.size();i++){
    int worknum=((Integer)vecWorkNumofDayByMonth.elementAt(i)).intValue();
    //if(worknum==0)
    //{
    %>
      <%--td class="clsfth" height="1" width="14"></td>--%>
   <%
    //continue;
    //}
    //else
    //{
     for(int j=0;j<worknum;j++)
     {
     tawRmAssignwork=(TawRmAssignwork)vecWorkSerialofMonth.elementAt(m);
     out.println("<td class=\"clsfth\" >"+FormatDate(tawRmAssignwork.getStarttimeDefined())+"-"+FormatDate(tawRmAssignwork.getEndtimeDefined())+"</td>");
     m++;
     }
    //}
    }
    %>
    </tr>
    <%
    m=0;
    Set keys=hashWorkRefSub.keySet();
    for(Iterator itr=keys.iterator();itr.hasNext();)
    {
    System.out.println("load in define sub inner.....");
    System.out.println("----------------------------------------------");
    String key=(String)itr.next();
    String value=(String)hashWorkRefSub.get(key);
    %>
    <tr class="tr_show">
      <td class="clsfth"   ><%=key%></td>
    <%
    for(int i=1;i<vecWorkNumofDayByMonth.size();i++){
    int worknum=((Integer)vecWorkNumofDayByMonth.elementAt(i)).intValue();
    /*
    if(worknum==0)
    {
    */
    %>
      <!--td class="clsfth" width="14" ></td>-->
   <%
    /*
    continue;
    }
    else
    {
    */
     for(int j=0;j<worknum;j++)
     {
     tawRmAssignwork=(TawRmAssignwork)vecWorkSerialofMonth.elementAt(m);
     m++;
     System.out.println("workid="+tawRmAssignwork.getId());
     if(value.indexOf(String.valueOf(tawRmAssignwork.getId()))>-1)
     {
       out.println("<td class=\"clsfth\">");
       out.println("<a href='preview.do?");
       out.println("roomId="+roomId+"&action=VIEW&workserial="+tawRmAssignwork.getId()+"' target='blank'>");
       out.println("<img src='"+request.getContextPath()+"/images/bottom/an_bj.gif' border='0'></a></td>");
     }
     else
       out.println("<td class=\"clsfth\"  ></td>");
     }
    }
    m=0;
    out.println("</tr>");
    }
    %>
  </table>
  <%}
   %>
</body>

</html>
