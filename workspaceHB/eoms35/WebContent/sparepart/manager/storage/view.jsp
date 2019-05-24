
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawStorage,com.boco.eoms.common.util.StaticMethod"%>
<html>
<head>
<title>
<bean:message key="storage.admin"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
	  <br>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="storage.admin"/></b>
      </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="td_label">
                      <td align="center"><bean:message key="storage.name"/></td>
                      <td align="center"><bean:message key="storage.note"/></td>
                      <td align="center">${eoms:a2u('所属分公司')}</td>
                      <td align="center"><bean:message key="label.edit"/></td>
                      <td align="center"><bean:message key="label.remove"/></td>
        </tr>
<logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
        <tr class="tr_show">
         <td><bean:write name="storage" property="storagename" scope="page"/></td>
         <td><bean:write name="storage" property="note" scope="page"/></td>
         <td><bean:write name="storage" property="deptname" scope="page"/></td>
         <td align="center"><a href="../storage/edit.do?id=<bean:write name="storage" property="id" scope="page"/>"><img src="<%=request.getContextPath()%>/images/icons/edit.gif" border="0"></a></td>
         <td align="center"><img src="<%=request.getContextPath()%>/images/icons/delete.gif" border="0"
Onclick="onPublic(<bean:write name='storage' property='id' scope='page'/>);"></td>
        </tr>
</logic:iterate>
<tr class="tr_show">
<form action="../storage/create.do" method="post" name="tawStorageForm">
      <td height="32" align="right" colspan="5">
          <input  align="right" type="submit" value="<bean:message key="label.add"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
</form>
</tr>
<script language="javascript">
function onPublic(aaa)
    {
      if( !confirm('${eoms:a2u("删除后不能恢复，您是否确认删除仓库？")}')) return ;
      window.navigate("../storage/drop.do?id="+aaa);
    }
</script>
</table>
</center>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>

