
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.*,com.boco.eoms.common.util.StaticMethod"%>
<html>
<head>
<title><bean:message key="order.view"/></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:message key="order.view"/></b>
      </td>
    </tr>
</table>
<form action="../part/view.do" method="post" name="tawSparepartForm">
<html:hidden property="type" name="tawSparepartForm" />
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<b>&nbsp;&nbsp;<bean:write name="storage" scope="session"/></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="tr_show">
         <td width="106%" colspan="11" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
         <%! String key;%></td>
       </tr>
       <tr class="td_label" align="center">
        <td><bean:message key="label.select"/></td>
        <td><bean:message key="sparepart.department"/></td>
		<td>${eoms:a2u('小专业')}</td>
        <td><bean:message key="sparepart.necode"/></td>
        <td>${eoms:a2u('名称')}</td>
        <td>${eoms:a2u('规格型号')}</td>
        <td>${eoms:a2u('生产厂商')}</td>
        <td>${eoms:a2u('性能状态')}</td>
        <td>${eoms:a2u('资产条形码')}</td>
        <td>${eoms:a2u('版本号')}</td>
        <td><bean:message key="sparepart.serialno"/></td>
      </tr>
      <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
      <tr class="tr_show" align="center">
       <td>
         <INPUT TYPE="checkbox" name="id" value="<bean:write name="sparepart" property="id" scope="page"/>"></td>
       <td><bean:write name="sparepart" property="nettype" scope="page"/></td>
	   <td><bean:write name="sparepart" property="subdept" scope="page"/></td>
       <td><bean:write name="sparepart" property="necode" scope="page"/></td>
       <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
       <td><bean:write name="sparepart" property="objtype" scope="page"/></td>
       <td><bean:write name="sparepart" property="fixe" scope="page"/></td>
       <td><bean:write name="sparepart" property="proform" scope="page"/></td>
       <td><bean:write name="sparepart" property="managecode" scope="page"/></td>
       <td><bean:write name="sparepart" property="version" scope="page"/></td>
       <td><bean:write name="sparepart" property="serialno" scope="page"/></td>
      </tr>
      </logic:iterate>
</table>
<html:hidden property="orderType" name="tawSparepartForm" />
<html:hidden property="orderId" name="tawSparepartForm" />
<table border="0" width="95%" cellspacing="0">
   <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.ok"/>" name="pp" class="clsbtn2">
          &nbsp;&nbsp;
			  <input type="button" value="${eoms:a2u('返回')}" name="pp" class="clsbtn2" onclick="history.back()">
      </td>
    </tr>
</table>
</form>
<logic:present name="choosed">
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
           <b><bean:message key="view.choosed"/></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
    <tr class="td_label">
      <td><bean:message key="sparepart.department"/></td>
	  <td>${eoms:a2u('小专业')}</td>
	  <td><bean:message key="sparepart.necode"/></td>
        <td>${eoms:a2u('名称')}</td>
        <td>${eoms:a2u('规格型号')}</td>
        <td>${eoms:a2u('生产厂商')}</td>
        <td>${eoms:a2u('性能状态')}</td>
        <td>${eoms:a2u('资产条形码')}</td>
        <td>${eoms:a2u('版本号')}</td>
        <td><bean:message key="sparepart.serialno"/></td>
    </tr>
<logic:iterate id="choosed" name="choosed" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show" align="center">
          <td><bean:write name="choosed" property="nettype" scope="page"/></td>
		  <td><bean:write name="choosed" property="subdept" scope="page"/></td>
       <td><bean:write name="sparepart" property="necode" scope="page"/></td>
       <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
       <td><bean:write name="sparepart" property="objtype" scope="page"/></td>
       <td><bean:write name="sparepart" property="fixe" scope="page"/></td>
       <td><bean:write name="sparepart" property="proform" scope="page"/></td>
       <td><bean:write name="sparepart" property="managecode" scope="page"/></td>
       <td><bean:write name="sparepart" property="version" scope="page"/></td>
       <td><bean:write name="sparepart" property="serialno" scope="page"/></td>
        </tr>
</logic:iterate>
</table>
<form action="../part/over.do" method="post" name="TawSparepartForm">
<html:hidden property="orderType" name="tawSparepartForm" />
<html:hidden property="orderId" name="tawSparepartForm" />
<html:hidden property="sumid" name="tawSparepartForm" />
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="pp" class="clsbtn2">
          &nbsp;&nbsp;
			
      </td>
    </tr>
</table>
</form>
</logic:present>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
