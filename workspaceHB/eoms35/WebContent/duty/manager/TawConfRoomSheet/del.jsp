<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.duty.util.RelativeSheetAttrName" %>

<html>
<head>
<title>ֵ������͸��ӱ�����ɾ��</title>

</head>

<body >
<html:form method="post" action="/TawConfRoomSheet/delDone">
<br>
<br>
<br>
<html:hidden property="id"/>
<table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
    <td width="30%"  class="clsfth">���ڻ���</td>
    <td width="70%">
        <bean:write name="tawConfRoomSheetForm" property="roomName" scope="request"/>
  ��</td>
  </tr>

  <tr class="tr_show">
    <td width="30%"  class="clsfth" >���ӱ�</td>
    <td width="70%">
       <bean:write name="tawConfRoomSheetForm" property="sheetName" scope="request"/>
  ��</td>
  </tr>
  <%--
  <tr class="tr_show">
    <td width="30%"  class="clsfth" >�Ƿ��ǹ��ϱ�</td>
    <td width="70%">
      <bean:write name="tawConfRoomSheetForm" property="isNotFault" scope="request"/>
  ��</td>
  </tr>
 <tr class="tr_show">
    <td width="30%"  class="clsfth" >����������������</td>
    <td width="70%">
      <bean:write name="tawConfRoomSheetForm" property="byAttrName" scope="request"/>
  ��</td>
  </tr>
 <tr class="tr_show">
    <td width="30%"  class="clsfth" >����������������</td>
    <td width="70%">
      <bean:write name="tawConfRoomSheetForm" property="toAttrName" scope="request"/>
  ��</td>
  </tr>
  --%>
</table>



<table border="0" width="80%" cellspacing="1" cellpadding="1" align="center">
<TR>
        <TD align="right" height="32">
         <input type="submit" value="ɾ��" class="clsbtn2">
         &nbsp;
         <input type="button" value="����" onclick="javascript:self.history.back();" class="clsbtn2">
        </TD>

</TR>
</TABLE>

</html:form>
</body>
</html>

