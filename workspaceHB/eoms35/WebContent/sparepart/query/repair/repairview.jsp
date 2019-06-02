<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<html>
<head>
<title>${eoms:a2u('借出情况统计')}</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
	  <br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b>${eoms:a2u('借出情况统计')}</b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
      <tr class="tr_show">
         <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
        <%! String key;%></td>
      </tr>
      <tr class="td_label" align="center">
        <td><bean:message key="storage"/></td>
        <td><bean:message key="sparepart.department"/></td>
        <td><bean:message key="sparepart.necode"/></td>
        <td><bean:message key="sparepart.objecttype"/></td>
        <td>${eoms:a2u('维修频率')}</td>
      </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show" align="center">
          <td><bean:write name="sparepart" property="storage" scope="page"/></td>
          <td><bean:write name="sparepart" property="nettype" scope="page"/></td>
          <td><bean:write name="sparepart" property="necode" scope="page"/></td>
          <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
          <td><bean:write name="sparepart" property="repairnum" scope="page"/></td>
        </tr>
      </logic:iterate>
</table>
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><a href="<bean:write name='path' scope='session'/>">${eoms:a2u('返回')}</a></b>
      </td>
    </tr>
</table>
</center>
</div>
</body>
</html>
