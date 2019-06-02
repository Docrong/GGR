<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*,javax.sql.*,java.lang.*"%>
<%@ page import ="com.boco.eoms.common.controller.*,com.boco.eoms.common.util.*"%>
<html:html>
<head>
<title>统计报表</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<script language="JavaScript">
       function GoBack(){
          window.history.back()
       }
</script>
<%
String begintime=request.getAttribute("begintime").toString();
String endtime=request.getAttribute("endtime").toString();
%>
<body leftmargin="0" topmargin="0" class="clssclbar">
<center>
<br>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
<td align="center" valign="top">
<table cellpadding="0" cellspacing="0" width="95%">
  <tr>
  <td width="100%" align="center" class="table_title">
    统计结果列表
  </td>
  </tr>
</table>
<table cellpadding="0" cellspacing="0" width="95%">
  <tr>
  <td width="100%" align="left" valign="top">
    &nbsp;统计时间段：<%=begintime%>---<%=endtime%>
  </td>
  </tr>
<tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
	<tr class="td_label">
		<td width="25%">上传部门</td>
		<td width="25%">上传数量</td>
		<td width="25%">通过数量</td>
		<td width="25%">通过率</td>

	</tr>

    <logic:iterate id="kbsStatResults" name="KBSSTATRESULTS" type="com.boco.eoms.kbs.model.KbsStatResult">

	<bean:define id="publicDeptName" name="kbsStatResults" property="publicDeptName" type="java.lang.String"/>
	<bean:define id="sum" name="kbsStatResults" property="sum" type="java.lang.Integer"/>
        <bean:define id="publicDept" name="kbsStatResults" property="publicDept" type="java.lang.Integer"/>
	<bean:define id="passSum" name="kbsStatResults" property="passSum" type="java.lang.Integer"/>
	<bean:define id="passRateStr" name="kbsStatResults" property="passRateStr" type="java.lang.String"/>
       <bean:define id="deptIdAll" name="kbsStatResults" property="deptIdAll" type="java.lang.String"/>
        <bean:define id="deptIdpass" name="kbsStatResults" property="deptIdpass" type="java.lang.String"/>
  	<tr class="td_label">
		<td nowrap >
                  <bean:write name="kbsStatResults" property="publicDeptName" scope="page"/>
		</td>
		<td nowrap >
                  <a href="<%=request.getContextPath()%>/kbs/KbsBase/statlistdo.do?deptId=<%=publicDept%>&beginTime=<%=begintime%>&endTime=<%=endtime%>"><bean:write name="kbsStatResults" property="sum" scope="page"/></a>
		</td>
		<td nowrap >
                 <a href="<%=request.getContextPath()%>/kbs/KbsBase/statlistdo.do?deptId=<%=publicDept%>&beginTime=<%=begintime%>&endTime=<%=endtime%>"> <bean:write name="kbsStatResults" property="passSum" scope="page"/></a>
		</td>
		<td nowrap >
                  <bean:write name="kbsStatResults" property="passRateStr" scope="page"/>
		</td>

	</tr>
	</logic:iterate>
            </table>
</td>
</tr>
</table>
</td>
</tr>
</table>



<table cellpadding="0" cellspacing="0" width="95%">
<tr>

<td align="right" height="32">
<INPUT id=fs_back type=button value=<bean:message key="label.back"/>  name=button Onclick="GoBack();">
</td>

</tr>
</table>


</center>
</body>

</html:html>
