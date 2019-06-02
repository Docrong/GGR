
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
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
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="tr_show">
         <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
         <%! String key;%></td>
       </tr>
       <tr class="td_label" align="center">
          <td><bean:message key="sparepart.department"/></td>
          <td><bean:message key="sparepart.necode"/></td>
          <td><bean:message key="sparepart.objecttype"/></td>
          <td><bean:message key="sparepart.supplier"/></td>
          <td><bean:message key="sparepart.objectname"/></td>
          <td><bean:message key="sparepart.state"/></td>
          <td><bean:message key="sparepart.serialno"/></td>
          <td><bean:message key="sparepart.operator"/></td>
          <td>${eoms:a2u('选择')}</td>
<!--          <td width="10%"><bean:message key="label.remove"/></td>  -->
        </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show" align="center">
                     <td><bean:write name="sparepart" property="nettype" scope="page"/></td>
                     <td><bean:write name="sparepart" property="necode" scope="page"/></td>
                     <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
                     <td><bean:write name="sparepart" property="supplier" scope="page"/></td>
                     <td><bean:write name="sparepart" property="objectname" scope="page"/></td>
                     <td><bean:write name="sparepart" property="state" scope="page"/></td>
                     <td><bean:write name="sparepart" property="serialno" scope="page"/></td>
                     <td><bean:write name="sparepart" property="operator" scope="page"/></td>
                     <td>
                           <a href="../charge/updatepage.do?id=<bean:write name="sparepart" property="id" scope="page"/>"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"></a>
                      </td>
<%--                  <td><img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0"
                        Onclick="onPublic(<bean:write name='sparepart' property='id' scope='page'/>);">
                       </td>
                       --%>
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
