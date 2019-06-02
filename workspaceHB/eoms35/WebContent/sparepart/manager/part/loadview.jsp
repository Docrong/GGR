
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
   //response.setHeader("Content-disposition","attachment; filename=plantnote.xls");
%>
<html>
<head>
<title>
<bean:message key="label.edit"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:message key="label.edit"/></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<font color="red"><bean:write name="storage_dept_name" scope="session"/></font>&nbsp;&nbsp;${eoms:a2u('的')}<b>&nbsp;&nbsp;<font color="blue"><bean:write name="storage" scope="session"/></font></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="tr_show">
         <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
         <%! String key;%></td>
       </tr>
       <tr class="td_label" align="center">
          <td><bean:message key="sparepart.department"/></td>
		  <td>${eoms:a2u('小专业')}</td>
          <td><bean:message key="sparepart.necode"/></td>
          <td><bean:message key="sparepart.objecttype"/></td>
          <td><bean:message key="sparepart.supplier"/></td>
          <td><bean:message key="sparepart.objectname"/></td>
          <td><bean:message key="sparepart.state"/></td>
          <td><bean:message key="sparepart.managecode"/></td>
          <td><bean:message key="sparepart.operator"/></td>
          

        </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show" align="center">
                     <td><bean:write name="sparepart" property="nettype" scope="page"/></td>
					 <td><bean:write name="sparepart" property="subdept" scope="page"/></td>
                     <td><bean:write name="sparepart" property="necode" scope="page"/></td>
                     <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
                     <td><bean:write name="sparepart" property="supplier" scope="page"/></td>
                     <td><bean:write name="sparepart" property="objectname" scope="page"/></td>
                     <td><bean:write name="sparepart" property="state" scope="page"/></td>
                     <td><bean:write name="sparepart" property="managecode" scope="page"/></td>
                     <td><bean:write name="sparepart" property="operator" scope="page"/></td>
                     

          </tr>
</logic:iterate>
</table>
<script language="javascript">
function onPublic(aaa)
    {
      if( !confirm('${eoms:a2u("删除后不能恢复，您是否确认删除备件？")}') ) return ;
      window.navigate("../part/drop.do?id="+aaa);
    }
</script>
</center>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
