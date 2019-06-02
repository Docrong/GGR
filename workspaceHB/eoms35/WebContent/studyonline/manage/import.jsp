<%@ page contentType="text/html; charset=GB18030" %>
<html>
<head>
<title>
试题导入
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="JavaScript" >
  function gohead(){
    history.go(-1);
  }

  function addattach(flag)
{
  if (document.all.ImportForm.attachName.value=="")
      return false;
  else
  {
       var fileStr= document.all.ImportForm.attachName.value;
       var index=fileStr.lastIndexOf("\\");
       fileStr=fileStr.substring(index+1,fileStr.length);
       document.all.ImportForm.fileName.value=fileStr;
       document.all.ImportForm.submit();
       return true;
  }
}
</script>
</head>
<body bgcolor="#ffffff">
<FORM name="ImportForm" METHOD="POST" ACTION="manage/fileup.jsp" ENCTYPE="multipart/form-data">
<input type="hidden" name="fileName"  value="">
<center>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
	<tr>
	<td class="table_title" align="center"><b>试题导入</b></TD>
	</tr>
</table>
<br>
<br>
<br>

<table border="0" width="70%" cellspacing="1" class="table_show">
 <tr class="tr_show" align="center">
    <td class= "clsfth" width="25%" align="center">
      <B>附件</B>
    </td>
    <td >
      <input type="file" name="attachName" class="clstext">
      <INPUT TYPE="button" VALUE="导入" onclick="return addattach(1);">
      <input name="reset" type="button" id="reset" value="返回" onClick="javascript:gohead()">
    </td>
 </tr>
</table>
</center>
</form>
</body>
</html>
