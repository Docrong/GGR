<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<html>
<head>
<title>
<bean:message key="stat.view"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
	  <br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:message key="stat.view"/></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="tr_show">
              <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
             <%! String key;%></td>
       </tr>
       <tr class="td_label" align="center">
        <td ><bean:message key="storage"/></td>
        <!-- <td><bean:message key="sparepart.department"/></td> -->
        <td>${eoms:a2u('备件所属大专业')}</td>
        <td>${eoms:a2u('备件所属小专业')}</td>
        <td><bean:message key="sparepart.necode"/></td>
        <td><bean:message key="sparepart.objecttype"/></td>
        <td><bean:message key="sparepart.state"/></td>
        <td width="12%"><bean:message key="sparepart.sum"/></td>
       </tr>
       <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
       <a href="../query/secondstat.do?storage=<bean:write name="sparepart" property="storage" scope="page"/>&nettype=<bean:write name="sparepart" property="nettype" scope="page"/>&subdept=<bean:write name="sparepart" property="subdept" scope="page"/>&necode=<bean:write name="sparepart" property="necode" scope="page"/>&objecttype=<bean:write name="sparepart" property="objecttype" scope="page"/>&state=<bean:write name="sparepart" property="state" scope="page"/>">
       <tr class="tr_show" align="center" style="cursor:hand" onmouseover="this.style.backgroundColor='#87CEEB'" onmouseout="this.style.backgroundColor=''">
         <td ><bean:write name="sparepart" property="storage" scope="page"/></td>
         <td><bean:write name="sparepart" property="nettype" scope="page"/></td>
         <td><bean:write name="sparepart" property="subdept" scope="page"/></td><!-- 增加小专业 by dww 070308 -->
         <td><bean:write name="sparepart" property="necode" scope="page"/></td>
         <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
         <td><bean:write name="sparepart" property="state" scope="page"/></td>
         <td><bean:write name="sparepart" property="sum" scope="page"/></td>
        </tr>
        </a>
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
