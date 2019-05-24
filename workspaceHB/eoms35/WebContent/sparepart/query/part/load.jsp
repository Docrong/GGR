<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
   response.setHeader("Content-disposition","attachment; filename=plantnote.xls");
%>
<html>
<head>

</head>
<body>
<center>
<br>
  <%
  int i=0;
  %>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="106%" class="table_title" align="center">${eoms:a2u('备件查询结果显示')}<bean:message key="label.list"/></td>
  </tr>
</table>
<table border="1" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  
   <tr class="td_label" align="center">
     <td>${eoms:a2u('序号')}</td>
     <td nowrap="nowrap">${eoms:a2u('分公司')}</td>
     <td nowrap="nowrap"><bean:message key="storage"/></td>
     <td nowrap="nowrap">${eoms:a2u('动作')}</td>
     <td nowrap="nowrap">${eoms:a2u('备件名称')}</td>
     <td nowrap="nowrap">${eoms:a2u('单据状态')}</td>
     <td nowrap="nowrap"><bean:message key="sparepart.serialno"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.supplier"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.state"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.operator"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.intime"/></td>
    </tr>
    <logic:iterate id="vawOrderDetail" name="vawOrderDetail" type="com.boco.eoms.sparepart.model.VawOrderDetail">
    
    
    <tr >
      <%i=i+1;%>
      <td><%=i%></td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="prop_dept" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="storage_name" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="order_name" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="objectname" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="order_part_state_name" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="serialno" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="supplier" scope="page"/></td>
       <td nowrap="nowrap">
         <bean:write name="vawOrderDetail" property="order_name" scope="page"/>
       </td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="operater" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="vawOrderDetail" property="startdate" scope="page"/></td>
    </tr>
    </a>
    </logic:iterate>
</table>
<br>

  </center>
</body>
</html>
