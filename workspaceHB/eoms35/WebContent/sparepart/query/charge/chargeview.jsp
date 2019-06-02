
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
${eoms:a2u('维修费用统计显示')}
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
         <b>${eoms:a2u('维修费用统计显示')}</b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="tr_show">
              <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
             <%! String key;%></td>
       </tr>
       <tr class="td_label">
         <td align="center"><bean:message key="storage"/></td>
         <td align="center"><bean:message key="sparepart.supplier"/></td>
         <td align="center"><bean:message key="sparepart.department"/></td>
         <td align="center"><bean:message key="sparepart.necode"/></td>
         <td align="center"><bean:message key="sparepart.objectname"/></td>
         <td align="center"><bean:message key="sparepart.managecode"/></td>
         <td align="center"><bean:message key="sparepart.contract"/></td>
         <td align="center"><bean:message key="sparepart.operator"/></td>
         <td align="center">${eoms:a2u('维修费用（元）')}</td>
       </tr>
       <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
       <tr class="tr_show">
          <td align="center"><bean:write name="sparepart" property="storage" scope="page"/></td>
          <td align="center"><bean:write name="sparepart" property="supplier" scope="page"/></td>
          <td align="center"><bean:write name="sparepart" property="nettype" scope="page"/></td>
          <td align="center"><bean:write name="sparepart" property="necode" scope="page"/></td>
          <td align="center"><bean:write name="sparepart" property="objecttype" scope="page"/></td>
          <td align="center"><bean:write name="sparepart" property="managecode" scope="page"/></td>
          <td align="center"><bean:write name="sparepart" property="contract" scope="page"/></td>
          <td align="center"><bean:write name="sparepart" property="operator" scope="page"/></td>
          <td align="center"><bean:write name="sparepart" property="charge" scope="page"/></td>
        </tr>
        </logic:iterate>
        <tr class="tr_show">
              <td width="106%" colspan="10" align="right">
              ${eoms:a2u('总费用：')}<bean:write  name="rate" scope="request"/>
              </td>
        </tr>
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
<%@ include file="/common/footer_eoms.jsp"%>

</html>
