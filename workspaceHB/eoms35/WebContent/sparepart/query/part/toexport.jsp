<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
response.setContentType("application/vnd.ms-excel");
response.addHeader("content-disposition","attachment;filename=sparepart.xls");

%>

<html>
<head>
</head>
<body>
<center>
<br>
<table border="1" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
   <tr class="td_label" align="center">
    <td>${eoms:a2u('大专业')}</td>
    <td>${eoms:a2u('小专业')}</td>
     <td>${eoms:a2u('网元类型')}</td>
     <td>${eoms:a2u('备件类型')}</td>
     <td>${eoms:a2u('制造厂商')}</td>
     <td>${eoms:a2u('仓库')}</td>
     <td>${eoms:a2u('备件名称')}</td>
     <td>${eoms:a2u('序列号')}</td>
     <td>${eoms:a2u('备件版本号')}</td>
     <td>${eoms:a2u('状态')}</td>
     <td>${eoms:a2u('备件性能')}</td>
     <td>${eoms:a2u('入库时间')}</td>
     <td>${eoms:a2u('经手人')}</td>
     <td>${eoms:a2u('备件位置')}</td>
     <td>${eoms:a2u('备注')}</td>
    </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
    <tr class="tr_show" align="center">
       <td><bean:write name="sparepart" property="nettype" scope="page"/></td>
       <td><bean:write name="sparepart" property="subdept" scope="page"/></td>
       <td><bean:write name="sparepart" property="necode" scope="page"/></td>
       <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
       <td><bean:write name="sparepart" property="supplier" scope="page"/></td>
       <td><bean:write name="sparepart" property="storage" scope="page"/></td>
       <td><bean:write name="sparepart" property="objectname" scope="page"/></td>
       <td><bean:write name="sparepart" property="serialno" scope="page"/></td>
       <td><bean:write name="sparepart" property="version" scope="page"/></td>
       <td><bean:write name="sparepart" property="state" scope="page"/></td>
       <td><bean:write name="sparepart" property="proform" scope="page"/></td>
       <td><bean:write name="sparepart" property="intime" scope="page"/></td>
       <td><bean:write name="sparepart" property="operator" scope="page"/></td>
       <td><bean:write name="sparepart" property="position" scope="page"/></td>
       <td><bean:write name="sparepart" property="note" scope="page"/></td>
    </tr>
    </logic:iterate>
</table>
<br>
  </center>

</body>

</html>
