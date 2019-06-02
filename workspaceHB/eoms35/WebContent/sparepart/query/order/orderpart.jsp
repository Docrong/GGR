
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
<bean:message key="sparepart.msg"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<%
     String path=(String)request.getSession().getAttribute("path");
%>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
	  <br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:message key="sparepart.msg"/></b>
      </td>
    </tr>
</table>
<br>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
        <tr class="td_label">
                      <td align="center"><bean:message key="storage.name"/></td>
                      <td align="center"><bean:message key="sparepart.department"/></td>
                      <td align="center"><bean:message key="sparepart.necode"/></td>
                      <td align="center"><bean:message key="sparepart.objecttype"/></td>
                      <td align="center"><bean:message key="sparepart.supplier"/></td>
                      <td align="center"><bean:message key="sparepart.objectname"/></td>
                      <td align="center"><bean:message key="sparepart.state"/></td>
                      <td align="center"><bean:message key="sparepart.serialno"/></td>
        </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show">
                      <td align="center"><bean:write name="sparepart" property="storage" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="nettype" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="necode" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="objecttype" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="supplier" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="objectname" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="state" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="serialno" scope="page"/></td>
        </tr>
      </logic:iterate>
</table>
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><a href="<%=path%>">${eoms:a2u('返回')}</a></b>
      </td>
    </tr>
</table>
</center>
</div>
</body>
</html>
