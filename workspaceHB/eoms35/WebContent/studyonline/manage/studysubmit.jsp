<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*"%>
<html>
<head>
<title>
提交结果
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<bean:define id="right" name="OnlineWarehouse" property="value" type="java.lang.Integer" />
<%
String showString = "";
if ( right.intValue() < 1 )
  showString = "回答错误";
else if ( right.intValue() == 1 )
  showString = "部分正确，再接再厉！";
else
  showString = "<font color='red'>恭喜，答对了！</font>";

String typeSel = StaticMethod.null2String(request.getParameter("typeSel"));
%>

<body bgcolor="#ffffff">
<html:form action="/study" method="post">
<html:hidden property="typeSel" value="<%=typeSel%>" />
<br>
<br>
<br>
<br>
<table cellpadding="0" cellspacing="0" width="95%" border="0">
  <tr align="center">
   <td>
     <B><%=showString%></B>
   </td>
  </tr>
</table>
<br>
<table cellpadding="1" cellspacing="1" width="95%" border="0" class="table_show">
  <tr class="tr_show" >
    <td width="10%" align="center" class="td_label">
      标题
    </td>
    <td width="90%" >
      <bean:write name="OnlineWarehouse" property="title" />
    </td>
  </tr>
  <tr class="tr_show">
    <td width="10%" align="center" class="td_label">
      答案
    </td>
    <td width="90%">
      <bean:write name="OnlineWarehouse" property="comment" />
    </td>
  </tr>
  <tr class="tr_show" align="center">
    <td colspan="2">
      <input type="button" Class="clsbtn2" value="下一题" onclick="goNext();">
      <input type="button" Class="clsbtn2" value="返回首选页" onclick="goFirst()">
    </td>
  </tr>
</table>
</html:form>
<script language="javascript">
function goNext()
{
  document.StudyForm.submit();
}
function goFirst()
{
  window.location="StudySelect.do";
}
</script>
</body>
</html>
