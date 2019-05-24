
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
<bean:write name="treeMsg" scope="request"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:write name="treeMsg" scope="request"/></b>
      </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="td_label" align="center">
                     <td>${eoms:a2u('名称')}</td>
                     <td>${eoms:a2u('备注')}</td>
                     <td><bean:message key="label.edit"/></td>
                     <td><bean:message key="label.remove"/></td>
        </tr>
		<logic:notEmpty name="tree"> 	
        <logic:iterate id="tree" name="tree" type="com.boco.eoms.sparepart.model.TawTree">
        <tr class="tr_show" align="center">
           <td><bean:write name="tree" property="cname" scope="page"/></td>
           <td><bean:write name="tree" property="note" scope="page"/></td>
          <td><a href="../tree/edit.do?id=<bean:write name='tree' property='id' scope='page'/>"><img src="<%=request.getContextPath()%>/images/icons/edit.gif" border="0"></a></td>
          <td><img src="<%=request.getContextPath()%>/images/icons/delete.gif" border="0"
            Onclick="onPublic(<bean:write name='tree' property='id' scope='page'/>);"></td>
        </tr>
        </logic:iterate>
		 </logic:notEmpty>
        <tr class="tr_show">
         <form action="../tree/add.do" method="post" name="tawTreeForm">
           <html:hidden property="id" name="tawTreeForm" />
           <td height="32" align="right" colspan="4">
              <input  align="right" type="submit" value="<bean:message key="label.add"/>" name="submit" class="clsbtn2">
                 &nbsp;&nbsp;
           </td>
         </form>
        </tr>
<script language="javascript">
function onPublic(aaa)
    {
      if( !confirm('${eoms:a2u("删除后不能恢复，您是否确认删除？")}')) return ;
      window.navigate("../tree/drop.do?id="+aaa);
    }
</script>
</table>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
