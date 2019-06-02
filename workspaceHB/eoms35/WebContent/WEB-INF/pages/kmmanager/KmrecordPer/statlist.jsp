<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<%
String dateStr = (String)request.getAttribute("dateStr");
String flagStr = (String)request.getAttribute("flagStr");
%>
<html>
<head>
<title>list</title>

</head>
<body>
<center>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
	<tr>
		<td class="table_title" align="center"><b>值班记录<%=flagStr%>报统计<%=dateStr%></b></TD>
	</tr>
</table>

<table cellpadding="0" cellspacing="0" border="0" width="95%">
  <tr>
    <td align="center" valign="top">
<!---正文开始------------------------------------------------------------------------------------------>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
	<tr class="td_label">
		<td nowrap>机房名称</td>
		<td nowrap>大专业名称</td>
		<td nowrap>小专业名称</td>
		<td nowrap>条数</td>
	</tr>
	
	    <logic:iterate id="baseInfoList" name="BASEINFOLIST">
	    	<tr class="tr_show">
	  			<bean:define id="hs" name="baseInfoList"/>
	  			<td nowrap><bean:write name="hs" property="roomName" scope="page"/></td>
	  			<td nowrap><bean:write name="hs" property="type" scope="page"/></td>
	  			<td nowrap><bean:write name="hs" property="subType" scope="page"/></td>
	  			<td nowrap><bean:write name="hs" property="totalNum" scope="page"/></td>
  			</tr>
  		</logic:iterate>
	
</table>

<BR>

<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align=center>
	<TR>
         <TD align="right">
          <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>&nbsp;
		</TD>
	</TR>
</TABLE>
</center>
</body>
</html>
