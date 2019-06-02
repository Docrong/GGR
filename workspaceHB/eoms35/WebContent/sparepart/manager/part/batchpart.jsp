
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
${eoms:a2u('导入导出')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<br>
<form action="../part/batchover.do" method="post" name="upLoadsForm" enctype="multipart/form-data">

<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('导入导出')}</b>
      </td>
    </tr>
</table>

<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr>
      <td colspan="3" height="25" bgcolor="#EEECED" align="center">
        <b>${eoms:a2u('备品备件批量导入')}</b>
      </td>
    </tr>
    <tr>
                  <td height="25" bgcolor="#EEECED" width="30%"></td>
                  <td height="25" bgcolor="#EEECED" width="70%" colspan="2">
                  <a href="../../sparepart/manager/part/down.jsp?fileName=${eoms:a2u("备品备件数据导入模板.xls")}&&path=excelmodel">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${eoms:a2u('下载导入模板')}
                  </a>
                  </td>
                </tr>
                <tr>
                  <td height="25" bgcolor="#E5EDF8" width="30%">${eoms:a2u('导入文件的路径与名称：')}</td>
                  <td height="25" bgcolor="#E5EDF8" width="70%" colspan="2">
                  <input type="file" name="theFile" size="40" value=""  maxlength="255" >
                  </td>
                </tr>
</table>
<table border="0" width="75%" cellspacing="0">
   <tr>
     <td height="32" align="center">
          <input type="submit" value='${eoms:a2u("导入")}' name="submit" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>
<br />
<form action="../part/toexport.do" method="post" enctype="multipart/form-data">

<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr>
      <td colspan="3" height="25" bgcolor="#EEECED" align="center">
        <b>${eoms:a2u('备品备件批量导出')}</b>
      </td>
    </tr>
    <tr>
                  <td height="25" bgcolor="#EEECED" width="30%">${eoms:a2u('导出仓库：')}</td>
                  <td height="25" bgcolor="#EEECED" width="70%" colspan="2">
									&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<bean:write name="storage" scope="session"/>
                  </td>
                </tr>
</table>

<table  align=center>
   <tr>
     <td height="100" align="center">
        <input type="submit" value='${eoms:a2u("导出EXCEL表格")}' name="submit" >
      </td>
    </tr>
  </table>
</form>


<form action="../part/toexportall.do" method="post" enctype="multipart/form-data">

<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr>
      <td colspan="3" height="25" bgcolor="#EEECED" align="center">
        <b>${eoms:a2u('备品备件批量导出')}</b>
      </td>
    </tr>
    <tr>
                  <td height="25" bgcolor="#EEECED" width="30%">${eoms:a2u('导出仓库：')}</td>
                  <td height="25" bgcolor="#EEECED" width="70%" colspan="2">
									${eoms:a2u('所有分管仓库')}
                  </td>
                </tr>
</table>

<table  align=center>
   <tr>
     <td height="100" align="center">
        <input type="submit" value='${eoms:a2u("导出EXCEL表格")}' name="submit" >
      </td>
    </tr>
  </table>
</form>

</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>


