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
    <td>${eoms:a2u('��רҵ')}</td>
    <td>${eoms:a2u('Сרҵ')}</td>
     <td>${eoms:a2u('��Ԫ����')}</td>
     <td>${eoms:a2u('��������')}</td>
     <td>${eoms:a2u('���쳧��')}</td>
     <td>${eoms:a2u('�ֿ�')}</td>
     <td>${eoms:a2u('��������')}</td>
     <td>${eoms:a2u('���к�')}</td>
     <td>${eoms:a2u('�����汾��')}</td>
     <td>${eoms:a2u('״̬')}</td>
     <td>${eoms:a2u('��������')}</td>
     <td>${eoms:a2u('���ʱ��')}</td>
     <td>${eoms:a2u('������')}</td>
     <td>${eoms:a2u('����λ��')}</td>
     <td>${eoms:a2u('��ע')}</td>
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
