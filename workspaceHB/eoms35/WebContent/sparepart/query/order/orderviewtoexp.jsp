<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="com.boco.eoms.sparepart.model.TawOrderDetail" %>
<html>
<head>
</head>
<body>
<center>
<br>
  <%
  int i=0;
  response.setHeader("Content-disposition","attachment; filename=oderpart.xls");
  %>
<table border="1" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
        <tr class="td_label">
		                <td width="8%"  align="center">${eoms:a2u('序号')}</td>
                      <td width="8%" align="center"><bean:message key="order.proposer"/></td>
                      <td width="8%" align="center"><bean:message key="order.type"/></td>
                      <td width="10%" align="center">${eoms:a2u('操作时间')}</td>
                      <td width="8%" align="center">${eoms:a2u('状态')}</td>
                      <td align="center">${eoms:a2u('序列号')}</td>
                      <td align="center">${eoms:a2u('实物条形码')}</td>
        </tr>
    <logic:iterate id="oderdetail" name="tawOrder" type="com.boco.eoms.sparepart.model.TawOrderDetail">
    <tr  class="td_label" align="center">
	    <%i=i+1;%>
      <td><%=i%></td>
       <td nowrap="nowrap"><bean:write name="oderdetail" property="proposer" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="oderdetail" property="orderName" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="oderdetail" property="startdate" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="oderdetail" property="orderPartStateName" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="oderdetail" property="serialno" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="oderdetail" property="managecode" scope="page"/></td>
    </tr>
    </logic:iterate>		  
</table>
<br>
  </center>
</body>
</html>