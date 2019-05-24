<%@ page contentType="text/html; charset=gb2312"%>

<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<head>
<title>查询换班记录</title>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmChangeduty/save">
<html:hidden property="strutsAction"/>
<br>
<table cellpadding="0" cellspacing="0" border="0" width="100%" align="center">
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="100%" align="center">
<tr>
<td class="table_title" align="center">交换班情况查询</td>
</tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="td_label">
<td>申请时间</td>
<td>请求人</td>
<td>值班开始时间</td>
<td>接收人</td>
<td>值班开始时间</td>
<td>申请原因</td>
<td>状态</td>
</tr>

<%
Vector allQueryVector = new Vector();
allQueryVector = (Vector)request.getAttribute("ALLQUERYVECTOR");
Vector getInputdate = (Vector)allQueryVector.elementAt(0);
Vector getWorkserial1 = (Vector)allQueryVector.elementAt(1);
Vector getWorkserial2 = (Vector)allQueryVector.elementAt(2);
Vector getHander = (Vector)allQueryVector.elementAt(3);
Vector getReceiver = (Vector)allQueryVector.elementAt(4);
Vector getFlag = (Vector)allQueryVector.elementAt(5);
Vector getReason = (Vector)allQueryVector.elementAt(6);
if (getInputdate.size()==0){
%>
<tr align="center" valign="middle">
<td colspan=10>该时间段内无交换班</td>
</tr>
<%
}else{
for(int i=0;i<getInputdate.size();i++){
%>
<tr class="tr_show">
<td><%=String.valueOf(getInputdate.elementAt(i))%></td>
<td><%=String.valueOf(getHander.elementAt(i))%></td>
<td><%=String.valueOf(getWorkserial1.elementAt(i))%></td>
<td><%=String.valueOf(getReceiver.elementAt(i))%></td>
<td><%=String.valueOf(getWorkserial2.elementAt(i))%></td>
<td><%=String.valueOf(getReason.elementAt(i))%></td>
<td>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==0){%>
被申请人未处理
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==1){%>
被申请人同意，管理员未处理
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==2){%>
被申请人不同意
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==4){%>
管理员同意换班
<%}%>
<%if (Integer.parseInt(String.valueOf(getFlag.elementAt(i)).trim())==5){%>
管理员不同意换班
<%}%>
&nbsp;
</td>
</tr>
<%}}%>
</table>
</td>
</tr>
</table>

</html:form>
</body>

