
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
  <%
  TawSparepartForm sparepartForm=(TawSparepartForm)request.getAttribute("sparepartForm");
  request.setAttribute("sparepartForm",sparepartForm);
  %>
<head>
<title>
${eoms:a2u('附件上传')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<br>
<form action="../part/uploadover.do" method="post" name="upLoadsForm" enctype="multipart/form-data">
<table border="0" width="75%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<font color="red"><bean:write name="storage_dept_name" scope="session"/></font>&nbsp;&nbsp;${eoms:a2u('的')}<b>&nbsp;&nbsp;<font color="blue"><bean:write name="storage" scope="session"/></font></b>
      </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr>
      <td colspan="3" height="25" bgcolor="#EEECED" align="center">
        <b>{eoms:a2u('附件上传')}</b>
      </td>
    </tr>
                <tr>
                  <td height="25" bgcolor="#E5EDF8" width="20%">{eoms:a2u('导入文件的路径与名称：')}</td>
                  <td height="25" bgcolor="#E5EDF8" width="80%" colspan="2">
                  <input type="file" name="theFile" size="40" value=""  maxlength="255" >
                  </td>
                </tr>
</table>
<table border="0" width="75%" cellspacing="0">
   <tr>
     <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
          <input type="reset" value="<bean:message key="label.reset"/>" name="reset" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
