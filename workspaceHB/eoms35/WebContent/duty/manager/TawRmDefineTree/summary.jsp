<%@ page contentType="text/html; charset=gb2312"%>

<%@ page language="java" import="java.util.*,org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod,com.boco.eoms.jbzl.model.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<head>
<title>table</title>


</head>
<%
HashMap deptMapsecond=(HashMap)request.getAttribute("deptMapsecond");
HashMap deptMapPlanNums=(HashMap)request.getAttribute("deptMapPlanNums");
HashMap deptMapRealPlanNums=(HashMap)request.getAttribute("deptMapRealPlanNums");
HashMap deptMaproom=(HashMap)request.getAttribute("deptMaproom");
String YearMonth=(String)request.getAttribute("YearMonth");
%>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmAssignwork/query_result">

<table cellpadding="0" cellspacing="0" border="0" width="800" align="center">
  <tr>
<td width="100%" align="center" valign="middle"  class="table_title">
<%=YearMonth%>  值班作业考核结果
</td>
</tr>
<tr>
<td>
<table  height="30" cellpadding="1" cellspacing="1" border="0" width="100%" class="table_show">
<tr align="center" valign="middle" class="td_label">
<td>
地市
</td>
<td  class="td_label">
部门
</td>
<td  class="td_label">
缺失次数
</td>
<td  class="td_label">
完成率
</td>
</tr>
<%
Iterator itr=deptMapsecond.keySet().iterator();
String deptName=null;
for(;itr.hasNext();)
{
  deptName=(String)itr.next();
  List deptList=(List)deptMapsecond.get(deptName);
%>
<tr align="center"  class="tr_show">
  <td rowspan="<%=deptList.size()%>">
    <%=deptName%>
  </td>
<%
  if(deptList!=null && deptList.size()>0)
  {
  for(int i=0;i<deptList.size();i++)
  {
    TawDept tawdept=(TawDept)deptList.get(i);
    String deptName2=tawdept.getDeptName();
    String deptId=String.valueOf(tawdept.getDeptId());
    if(i>0)
      out.print("<tr align='center'  class='tr_show'>");
%>
  <td>
    <%=deptName2%>
  </td>
  <td><a href="RecordSummary.do?month=<%=YearMonth%>&roomId=<%=deptMaproom.get(deptId)%>" target="_blank">
    <font color="red"><%=Integer.parseInt(deptMapPlanNums.get(deptId).toString())-Integer.parseInt(deptMapRealPlanNums.get(deptId).toString())%>
    次</font></a>
  </td>
  <td><font color='red'>
    <%if(!"0".equals(deptMapPlanNums.get(deptId)))
       {
        java.text.NumberFormat nf=new java.text.DecimalFormat(".00");
        out.print(""+nf.format((Float.parseFloat(deptMapRealPlanNums.get(deptId).toString())/Float.parseFloat(deptMapPlanNums.get(deptId).toString()))*100)+"");
       }
      else
        out.print("0.0");
    %>%</font>
  </td>
</tr>
<%
  }
}else    //该部门没有机房或机房下没有排班
{
 out.print("<td>空</td><td>空</td><td>空</td></tr>");
}
}
%>
</table>
</td>
</tr>


<tr>
    <td colspan=10 align = 'center' height="40">
      <input type='button' Class="clsbtn2"  value='打印' onclick = 'javascript:window.print()'>
      <input type='button' Class="clsbtn2"  value='返回' onclick = 'window.location="Search.do"'>
    <td>
</tr>
</table>


</html:form>
</body>
