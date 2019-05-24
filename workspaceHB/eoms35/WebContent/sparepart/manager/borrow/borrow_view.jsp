<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
  <table class="listTable" border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="td_label" align="center">
    <td ><bean:message key="storage"/></td>
     <td><bean:message key="sparepart.department"/></td>
     <td><bean:message key="sparepart.necode"/></td>
     <td><bean:message key="sparepart.objecttype"/></td>
     <td><bean:message key="sparepart.supplier"/></td>
     <td><bean:message key="sparepart.operator"/></td>
     <td><bean:message key="sparepart.managecode"/></td>
     <td><bean:message key="sparepart.intime"/></td>
     <td><bean:message key="sparepart.serialno"/></td>
    </tr>
        <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawBorrow">
          <tr class="tr_show" align="center">
       <td><bean:write name="sparepart" property="storage" scope="page"/></td>
       <td><bean:write name="sparepart" property="cname" scope="page"/></td>
       <td><bean:write name="sparepart" property="necname" scope="page"/></td>
       <td><bean:write name="sparepart" property="objecttypename" scope="page"/></td>
       <td><bean:write name="sparepart" property="suppliername" scope="page"/></td>
       <td><bean:write name="sparepart" property="operator" scope="page"/></td>
       <td><bean:write name="sparepart" property="managecode" scope="page"/></td>
       <td><bean:write name="sparepart" property="intime" scope="page"/></td>
       <td><bean:write name="sparepart" property="serialno" scope="page"/></td>
    </tr>
        </logic:iterate>
    </table>
    <table class="listTable" border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><a href="<bean:write name='path' scope='session'/>">${eoms:a2u('返回')}</a></b>
      </td>
    </tr>
</table>
</body>

<%@ include file="/common/footer_eoms.jsp"%>
</html>
