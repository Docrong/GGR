 
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
	String roomId = request.getParameter("typeId");
      GregorianCalendar cal_start = new GregorianCalendar();
      cal_start.add(cal_start.DATE,-1);
      String str_start = StaticMethod.Cal2String(cal_start);
      str_start = String.valueOf(StaticMethod.getVector(str_start," ").elementAt(0));

    
%>
<head>
   
</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<br>
<html:form method="post" action="/TawRmRecord/statistic_show">
<html:hidden property="strutsAction"/>
<input type = "hidden" name="roomId" value="<%=roomId%>">
<table  width="600" align="left">
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="400" >
<tr>
<td align="center" class="table_title">
    <bean:message key="TawRmSysteminfo.stat"/>
  </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="400">
<tr>
<td>
<table border="0" width="80%" cellspacing="1" cellpadding="1" class="formTable" align=center>
 
<tr class="tr_show">
<td class="label">
<bean:message key="TawRmRecord.starttime"/>
</td>
<td>
<eoms:SelectDate name="starttime" formName="tawRmRecordForm" flag = "-1" value = "<%=str_start%>"/>
</td>
</tr>
<tr class="tr_show">
<td class="label">
<bean:message key="TawRmRecord.endtime"/>
</td>
<td>
<eoms:SelectDate name="endtime" formName="tawRmRecordForm" flag = "-1" value = "<%=str_start%>"/>
</td>
</tr>
</table>
</td>
</tr>




  <tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
<td align="left" height="32">
<input type = "hidden" name="SAVEFLAG" value="false"><%--
      <html:submit styleClass="clsbtn2">
      <bean:message key="TawRmChangeduty.apply"/>
      </html:submit>
      
      --%><input type="submit" name="name" value =<bean:message key="TawRmSysteminfo.stat"/> class="button" >

  </td>
</tr>
</table>

</html:form>
</body>

