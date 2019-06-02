<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script language="javascript">
var submitTimes = 0;
function submitCheck(){
  	myForm.btnSubmit.disabled=true;
	if(submitTimes == 1){
          return false;
	}else{
          submitTimes = 1;
          return true;
	}
}

function checkForm(){
  var exportType = document.forms[0].exportType;
  var bureauId = document.forms[0].bureauId;
  var BUREAUDATA_SHEET = document.forms[0].BUREAUDATA_SHEET;
  if(exportType.value==''){
  	alert("${eoms:a2u('请选择数据源！')}");
        exportType.focus();
        return false;
  }else if(exportType.value==1 || exportType.value==2){
  	if(bureauId.value == ''){
        	alert("${eoms:a2u('请输入局数据编号!')}");
                bureauId.focus();
                return false;
        }
  }
  document.forms[0].submit();
  return false;
}

function checkType(){
  var exportType = document.forms[0].exportType;
  var bureauIdTr = document.getElementById('bureauIdTr');
  var bureauId = document.forms[0].bureauId;
  var BUREAUDATA_SHEET = document.forms[0].BUREAUDATA_SHEET;
  if(exportType.value==1 || exportType.value==2){
  	bureauIdTr.style.display = 'block';
  }else if(exportType.value==3){
  	bureauIdTr.style.display = 'none';
  }else{
  	bureauIdTr.style.display = 'none';
  }
}
</script>
<form action="bureaudataHlr.do?method=importexportexcelfile" method="post" onsubmit="return submitCheck();" onload="checkType();document.forms[0].exportType.focus();">
<table class="formTable">
  <tr>
    <td align="center" class= "label" colspan="4"><b>${eoms:a2u('局数据管理源数据导出')}</b></td>
  </tr>
  <tr class="tr_show">
    <td  class= "label" width="15%"><div align="right"><strong>${eoms:a2u('导出类型')}</strong></div></td>
    <td colspan="3">
    	<select name="exportType" onchange="checkType();">
			<option value="">${eoms:a2u('---请选择导出类型---')}</option>
			<option value="1">${eoms:a2u('号段新增单次导出')}</option>
			<option value="2">${eoms:a2u('号段地市调整单次导出')}</option>
			<option value="3">${eoms:a2u('变更归属单次导出')}</option>
			<option value="4">${eoms:a2u('全局导出')}</option>
    	</select>
   </td>
  </tr>
  <tr class="tr_show" id="bureauIdTr" style="display:none;">
    <td class= "label"><div align="right"><strong>${eoms:a2u('局数据编号*')}</strong></div></td>
    <td><input type="text" name="bureauId" maxlength="50"/></td>
  </tr>
  <tr>
    <td align="right" colspan="4">
        <input type="button" name="btnSubmit" value="${eoms:a2u('导出数据')}" class="btn" onclick="return checkForm();">
      </td>
  </tr>
</table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>
