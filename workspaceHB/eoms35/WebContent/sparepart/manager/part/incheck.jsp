
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<center>
<br>
  <%
  int i=0;
  %>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="106%" class="table_title" align="center">${eoms:a2u('备件出入库申请待审核')}<bean:message key="label.list"/></td>
  </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>

   <tr class="td_label" align="center">
     <td>${eoms:a2u('序号')}</td>
     <td nowrap="nowrap">${eoms:a2u('申请人')}</td>
     <td nowrap="nowrap"><bean:message key="storage"/></td>
     <td nowrap="nowrap">${eoms:a2u('申请时间')}</td>
     <td nowrap="nowrap">${eoms:a2u('工单编号')}</td>
     <td nowrap="nowrap">${eoms:a2u('工单类型')}</td>
     <td nowrap="nowrap">${eoms:a2u('工单状态')}</td>
     <td nowrap="nowrap">${eoms:a2u('原因说明')}</td>
     <td nowrap="nowrap">${eoms:a2u('实物名称')}</td>
     <td nowrap="nowrap">${eoms:a2u('实物编码')}</td>
	  <!--<td nowrap="nowrap">备件厂商</td>-->
    </tr>
    <logic:iterate id="order" name="orderlist" type="com.boco.eoms.sparepart.model.TawOrder">
    
    <a href="../part/incheckdetail.do?id=<bean:write name="order" property="id" scope="page"/>&sparepart_id=<bean:write name="order" property="sparepart_id" scope="page"/>&orderpart_id=<bean:write name="order" property="orderpart_id" scope="page"/>">
    <tr align="center" style="cursor:hand" onmousemove="this.style.backgroundColor='#87CEEB'" onmouseout="this.style.backgroundColor=''">
      <%i=i+1;%>
      <td><%=i%></td>
       <td nowrap="nowrap"><bean:write name="order" property="proposer" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="order" property="storagename" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="order" property="startdate" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="order" property="sheetid" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="order" property="orderType" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="order" property="orderState" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="order" property="reason" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="order" property="ename" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="order" property="managecode" scope="page"/></td>
       <%--<td nowrap="nowrap"><bean:write name="order" property="fixe" scope="page"/></td>--%>
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
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
