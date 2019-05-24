<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.duty.util.RelativeSheetAttrName" %>

<html>
<head>
<title>值班机房和附加表配置删除</title>

</head>

<body >
<html:form method="post" action="/TawConfRoomSheet/delDone">
<br>
<br>
<br>
<html:hidden property="id"/>
<table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
    <td width="30%"  class="clsfth">所在机房</td>
    <td width="70%">
        <bean:write name="tawConfRoomSheetForm" property="roomName" scope="request"/>
  　</td>
  </tr>

  <tr class="tr_show">
    <td width="30%"  class="clsfth" >附加表</td>
    <td width="70%">
       <bean:write name="tawConfRoomSheetForm" property="sheetName" scope="request"/>
  　</td>
  </tr>
  <%--
  <tr class="tr_show">
    <td width="30%"  class="clsfth" >是否是故障表</td>
    <td width="70%">
      <bean:write name="tawConfRoomSheetForm" property="isNotFault" scope="request"/>
  　</td>
  </tr>
 <tr class="tr_show">
    <td width="30%"  class="clsfth" >进入遗留问题条件</td>
    <td width="70%">
      <bean:write name="tawConfRoomSheetForm" property="byAttrName" scope="request"/>
  　</td>
  </tr>
 <tr class="tr_show">
    <td width="30%"  class="clsfth" >进入遗留问题属性</td>
    <td width="70%">
      <bean:write name="tawConfRoomSheetForm" property="toAttrName" scope="request"/>
  　</td>
  </tr>
  --%>
</table>



<table border="0" width="80%" cellspacing="1" cellpadding="1" align="center">
<TR>
        <TD align="right" height="32">
         <input type="submit" value="删除" class="clsbtn2">
         &nbsp;
         <input type="button" value="返回" onclick="javascript:self.history.back();" class="clsbtn2">
        </TD>

</TR>
</TABLE>

</html:form>
</body>
</html>

