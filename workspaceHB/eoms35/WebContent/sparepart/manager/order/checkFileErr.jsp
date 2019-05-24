
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<link rel="stylesheet" href="/EOMS_J2EE/css/table_style.css" type="text/css">
</head>
<title>${eoms:a2u('上传文件错误')}</title>
<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="/EOMS_J2EE/template/img/bg001.gif">
   <tr>
      <td width="100%" height="100%" align="center">
          <table width="743" height="440" background="/EOMS_J2EE/images/bg001.jpg">
           <tr>
             <td align="center">
               <font style="font-size:14px;color:#CC0000;"><strong>${eoms:a2u('目录中已经有该文件，请更改文件名！')}</strong></font>
             </td>
           </tr>
           <tr>
             <td align="center">
               <font style="font-size:14px;color:#CC0000;"><strong><a href="../part/upload.do">${eoms:a2u('返回')}</a></strong></font>
             </td>
           </tr>
          </table>
    </td>
 </tr>
</table>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>