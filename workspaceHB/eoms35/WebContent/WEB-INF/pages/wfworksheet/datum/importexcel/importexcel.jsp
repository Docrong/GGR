<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<%@ page
	import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<%
			TawSystemSessionForm sessionuser = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
%>
<script language="javascript">
var submitTimes = 0;
function checkForm(){
var TawImportExcelForm = document.forms[0];
   if(TawImportExcelForm.sheetId.value==""){
  	alert("请选择数据源！");
        TawImportExcelForm.sheetId.focus();
        return false;
   }
   if(TawImportExcelForm.bureauId.value==''){
  	alert("请输入局数据编号！");
        TawImportExcelForm.bureauId.focus();
        return false;
   }
   if (TawImportExcelForm.fileName.value==""){
           alert("上传附件不能为空！");
           TawImportExcelForm.fileName.focus();
           return false;
   }
   var fileStr= TawImportExcelForm.fileName.value;
   var index=fileStr.lastIndexOf("\\");
   fileStr=fileStr.substring(index+1,fileStr.length);
   TawImportExcelForm.fileName1.value=fileStr;
   try{
   	TawImportExcelForm.submit();
   }catch(e){
   	alert("路径错误，请重新输入！");
        TawImportExcelForm.fileName.focus();
        return false;
   }
   submitTimes = 1;
   TawImportExcelForm.button.disabled=true;
   TawImportExcelForm.fileNameTemp.value = TawImportExcelForm.fileName.value;
   document.getElementById("fileDiv").style.display='none';
   document.getElementById("tempDiv").style.display='block';
   return true;
}

function submitCheck(){
	if(submitTimes >= 1){
          return false;
	}else{
          return true;
	}
}
</script>
<html:form action="bureaudataHlr.do?method=importexcelfile"
	method="post" enctype="multipart/form-data"
	onsubmit="return submitCheck();">
	<table width="95%"class="formTable">
		<tr>
			<td class="label" colspan="4" align="center" style="font-size:13px;"><b>${eoms:a2u('局数据管理源数据导入')}</b></td>
		</tr>
		<tr class="tr_show">
			<td colspan="4" style="color:red" class="label">${eoms:a2u('(*导入数据之前请检查上传文件中的城市名称和区号是否与标准模板文件里的《城市列表.xls》中的数据匹配)')}</td>
		</tr>
		<tr class="tr_show">
			<td width="15%" class="label">
			<div align="right"><strong>${eoms:a2u('数据源')}</strong></div>
			</td>
			<td><select name="sheetId">
				<option value="">${eoms:a2u('--请选择数据源--')}</option>
				<option value="1">${eoms:a2u('未确定HLR归属的号段')}</option>
				<option value="2">${eoms:a2u('变更批复表')}</option>
			</select></td>
		</tr>
		<tr class="tr_show">
			<td align="right" class="label"><strong>${eoms:a2u('局数据编号*')}</strong></td>
			<td><input type="text" name="bureauId" size="27" maxlength="50" /></td>
		</tr>
		<tr class="tr_show">
			<td align="right" class="label"><strong>${eoms:a2u('发送短信内容*')}</strong></td>
			<td>
			<textarea class="textarea max"  name="sendContent"></textarea>
		</tr>
		<tr class="tr_show">
			<td align="right" class="label"><strong>${eoms:a2u('EXCEL文件*')}</strong></td>
			<td colspan="3">
			<div id="fileDiv"><input type="file" size='40' name="fileName"></div>
			<div id="tempDiv" style="display:none"><input type="text"
				size="40" disabled="disabled" name="fileNameTemp" /><input
				type="button" disabled="disabled" value="浏览..." /></div>
			</td>
		</tr>
		<input type='hidden' name="fileName1" value="">
		<tr class="tr_show">
			<td colspan="4" class="label">
			<div align="right"><input type="button" name="button" class="btn"
				value="${eoms:a2u('导入数据')}" onclick="return checkForm();"></div>
			</td>
		</tr>
		<tr class="tr_show">
			<td colspan="4">
			<strong><a
				href="<%=request.getContextPath()%>/userfiles/standardtemp.zip">${eoms:a2u('下载标准模板文件')}</a></strong>
			</td>
		</tr>
	</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
