<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.controller.SaveSessionBeanForm,com.boco.eoms.duty.model.*"%>
<HTML>
<%
String month=(String)request.getAttribute("month");
String deptId=(String)request.getAttribute("deptId");
String deptName=(String)request.getAttribute("deptName");
%>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<link href="../css/tab.css" rel="stylesheet" type="text/css">
<script src="../js/scripts.js"></script>
<script src="../js/navigate.js"></script>
<script src="../js/checkselect.js"></script>
<script src="../js/tab.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function _doClick(roomId,nodeId,cycles,Action,dutydate,workserial) {
    var url="editRecord.do?roomId="+roomId+"&parent_id="+nodeId+"&Action="+Action+"&Cycle="+cycles+"&Summary=true";
    if(dutydate!=null)
       url+="&dutydate="+dutydate;
    if(workserial!=null)
       url+="&workserial="+workserial;
    window.open(url, "_self");
  }
// -->
</SCRIPT>

<title>填写值班作业</title>
</HEAD>

<BODY TEXT="000000" BGCOLOR="FFFFFF" topmargin=0 leftmargin=0 bgColor=#ffffff onLoad="setTableColor(table1,1);">
<FORM>
<%
    SaveSessionBeanForm saveSessionBeanForm=null;
    saveSessionBeanForm =
       (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
String CurrentUser=saveSessionBeanForm.getWrf_UserID();
String action=(String)request.getAttribute("Action");
%>
<table>
<tr>
<td>
<br></td>
</tr>
</table>
<table id="table1" border=0 align=center width=1400 cellpadding=0 cellspacing=1 style=font-size:11pt>
<tr class=shitu_headcolor>
<td width=150 nowrap><center>机房名称</center></td>
<td width=150 nowrap><center>作业名称</center></td>
<td width=50 nowrap><center>专业</center></td>
<td width=50 nowrap><center>类型</center></td>
<%
//String month=(String)request.getAttribute("month");
String[] yearmonth=month.split("-");
Calendar calendar=new GregorianCalendar();
if(month!=null)
   calendar.set(Integer.parseInt(yearmonth[0]),Integer.parseInt(yearmonth[1]),0);
int daynums=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
for(int i=1;i<=daynums;i++)
{
%>
<td width=30><center><%=i%></center></td>
<%}%>
</tr>
<%
HashMap roomMapPlan=(HashMap)request.getAttribute("roomMapPlan");
HashMap roomMapRecord=(HashMap)request.getAttribute("roomMapRecord");
TawRmDefineTree tawRmDefineTree=null;
String workerials=null;
String[] workerial=null;

Iterator roomMapPlanItr=roomMapPlan.keySet().iterator();
for(;roomMapPlanItr.hasNext();)
{
String roomName=(String)roomMapPlanItr.next();
Vector planVect=(Vector)roomMapPlan.get(roomName);
HashMap recordOfMonth=(HashMap)roomMapRecord.get(roomName);
%>
<tr>
<td><%=roomName%></td>
<%
boolean flag=true;
for(Iterator itr=planVect.iterator();itr.hasNext();)
{
  tawRmDefineTree=(TawRmDefineTree)itr.next();
  if(!flag)
    out.print("<tr><td></td>");
  flag=false;
%>
<td><center><%=tawRmDefineTree.getName()%></center></td>
<td><center><%=tawRmDefineTree.getSpecility()%></center></td>
<td><center>
<%
String cycle=tawRmDefineTree.getCycles();
if("workserial".equals(cycle))
  out.print("班次");
else if("day".equals(cycle))
  out.print("天");
else if("week".equals(cycle))
  out.print("周");
else if("month".equals(cycle))
  out.print("月");
else if("quarter".equals(cycle))
  out.print("季度");
else if("halfyear".equals(cycle))
  out.print("半年");
else if("year".equals(cycle))
  out.print("年");
%>
</center></td>
<%
HashMap defsOfMonth=(HashMap)recordOfMonth.get(tawRmDefineTree.getNodeId());
for(int k=1;k<=daynums;k++)
{
if(defsOfMonth.containsKey(String.valueOf(k)))
{
        String dutydate=calendar.get(calendar.YEAR)+"-"+(calendar.get(calendar.MONTH)+1)+"-"+k;
        String[] workAndAuthor=null;
        workerials=(String)defsOfMonth.get(String.valueOf(k));
        if("workserial".equals(tawRmDefineTree.getCycles()))   //按班次
         {
	workerial=workerials.split(",");
        out.println("<td>");
        for(int n=0;n<workerial.length;n++)
         {
         workAndAuthor=workerial[n].split(":");
         if(CurrentUser.equals(workAndAuthor[1]))    //是本人
         {
%>
<a href="javascript:_doClick('<%=tawRmDefineTree.getRoomId()%>','<%=tawRmDefineTree.getNodeId()%>','<%=tawRmDefineTree.getCycles()%>','Edit','<%=dutydate%>','<%=workAndAuthor[0]%>');">
<img src="../images/058.gif" border="0"></a>
<%
         }else
         {
%>
<a href="javascript:_doClick('<%=tawRmDefineTree.getRoomId()%>','<%=tawRmDefineTree.getNodeId()%>','<%=tawRmDefineTree.getCycles()%>','View','<%=dutydate%>','<%=workAndAuthor[0]%>');">
<img src="../images/082.gif" border="0"></a>
<%
         }
         }
         out.println("</td>");
         }else
       {
         workAndAuthor=workerials.split(":");
%>
<td>
<%
         if(CurrentUser.equals(workAndAuthor[1]))    //是本人
         {
%>
<a href="javascript:_doClick('<%=tawRmDefineTree.getRoomId()%>','<%=tawRmDefineTree.getNodeId()%>','<%=tawRmDefineTree.getCycles()%>','Edit','<%=dutydate%>','<%=workAndAuthor[0]%>');">
<img src="../images/058.gif" border="0"></a>
<%
         }else
         {
%>
<a href="javascript:_doClick('<%=tawRmDefineTree.getRoomId()%>','<%=tawRmDefineTree.getNodeId()%>','<%=tawRmDefineTree.getCycles()%>','View','<%=dutydate%>','<%=workAndAuthor[0]%>');">
<img src="../images/082.gif" border="0"></a>
<%
         }
%>
</td>
<%
       }
}else
{
%>
<td>&nbsp;</td>
<%
}
}%>
</tr>
<%}
}
%>
</table>
</FORM>
</BODY>
</HTML>
