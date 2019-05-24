<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
</head>
<body>
<center>
<br>
  <%
  int i=0;
  response.setHeader("Content-disposition","attachment; filename=sparepart.xls");
  %>
<table border="1" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
   <tr class="td_label" align="center">
     <td>${eoms:a2u('ÐòºÅ')}</td>
     <td nowrap="nowrap">${eoms:a2u('·Ö¹«Ë¾')}</td>
     <td nowrap="nowrap"><bean:message key="storage"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.department"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.necode"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.objecttype"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.serialno"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.supplier"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.state"/></td>

     <td nowrap="nowrap"><bean:message key="sparepart.intime"/></td>
    </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
    <tr  class="td_label" align="center">
	    <%i=i+1;%>
      <td><%=i%></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="deptName" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="storage" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="nettype" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="necode" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="objecttype" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="serialno" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="supplier" scope="page"/></td>
       <td nowrap="nowrap">
         <bean:write name="sparepart" property="state" scope="page"/>
       </td>

       <td nowrap="nowrap"><bean:write name="sparepart" property="intime" scope="page"/></td>
    </tr>
    </a>
    </logic:iterate>
</table>
<br>
  </center>
</body>
</html>
