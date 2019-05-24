<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<center>
<br>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="106%" class="table_title" align="center">${eoms:a2u('备件统计2次查询结果显示列表')}<bean:message key="label.list"/></td>
  </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
    <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%></td>
  </tr>
   <tr class="td_label" align="center">
    <td ><bean:message key="storage"/></td>
     <td><bean:message key="sparepart.department"/></td>
     <td><bean:message key="sparepart.necode"/></td>
     <td><bean:message key="sparepart.objecttype"/></td>
     <td><bean:message key="sparepart.supplier"/></td>
     <td><bean:message key="sparepart.state"/></td>
     <td><bean:message key="sparepart.operator"/></td>
     <td><bean:message key="sparepart.managecode"/></td>
     <td><bean:message key="sparepart.intime"/></td>
     <td><bean:message key="sparepart.serialno"/></td>
    </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
    <a href="../part/amplyview.do?id=<bean:write name="sparepart" property="id" scope="page"/>">
    <tr class="tr_show" align="center" style="cursor:hand" onmousemove="this.style.backgroundColor='#87CEEB'" onmouseout="this.style.backgroundColor=''">
       <td><bean:write name="sparepart" property="storage" scope="page"/></td>
       <td><bean:write name="sparepart" property="nettype" scope="page"/></td>
       <td><bean:write name="sparepart" property="necode" scope="page"/></td>
       <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
       <td><bean:write name="sparepart" property="supplier" scope="page"/></td>
       <td><bean:write name="sparepart" property="state" scope="page"/></td>
       <td><bean:write name="sparepart" property="operator" scope="page"/></td>
       <td><bean:write name="sparepart" property="managecode" scope="page"/></td>
       <td><bean:write name="sparepart" property="intime" scope="page"/></td>
       <td><bean:write name="sparepart" property="serialno" scope="page"/></td>
    </tr>
    </a>
    </logic:iterate>
</table>
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
<html:submit styleClass="clsbtn2" onclick="window.history.back(-1)">
        ${eoms:a2u('返回')}
      </html:submit>
      </td>
    </tr>
</table>
  </center>
</body>
</html>
