<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<script language="javascript">
function SubmitCheck(){
  frmReg = document.tawwpmodeladd;
  
  if(frmReg.leave.value == '0'||frmReg.leave.value == '-1'||frmReg.leave.value == ''){
 
    alert("${eoms:a2u('请选择存放单位')}");
   
    return false;
  }
  

  return true;
}
</script>
<html>
<head>
<title>${eoms:a2u('测试卡导入与导出:')}</title>

</head>
<body>
<br>
<form name="tawwpmodeladd" action="../TawTestcard/toexport.do" method="post" enctype="multipart/form-data" onsubmit='return SubmitCheck()'>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="formTable" align=center>
  <tr class="td_label">
      <td align="center" class="label"> ${eoms:a2u('存放公司:')}</td>
      <td colspan="2">
      <eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401"/>
  
      </td>
      </tr>
   <tr class="tr_show">
                  <td colspan="3" align="center">
        <input class="button"  type="submit" value="${eoms:a2u('导出EXCEL表格')}" name="submit" >
      </td>
                </tr>
</table>
<br>
</form>
</body>
</html>


