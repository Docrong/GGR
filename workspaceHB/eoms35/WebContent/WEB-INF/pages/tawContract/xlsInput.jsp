<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
 <%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript">

function checkForm(form,num){ 
    if(document.getElementById("excelInput").value==null||document.getElementById("excelInput").value==""){
    	alert("请选择上传文件");
    	return false;
    }else if(document.getElementById("excelInput").value.indexOf(".xls")<0){
    	alert("确认选择的文件为Excel");
    	return false;
    }else{
    return Validator.Validate(form,num);
    }
  }
  
</script>
<table>
	<caption>
		仪器仪表批量录入
	</caption>
</table>
<html:form action="/tawContracts.do?method=xlsSave"
	method="post" styleId="tawContractForm"
	enctype="multipart/form-data" onsubmit="return checkForm()">
	
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		
		<tr class="tr_show">
			<td COLSPAN="3" class="label">
				仪表仪器表上传
			</td>
			<td COLSPAN="14">

				<input name="excelInput" type="file"
					class="file" /><font color="red">请选择上传xls格式的文件</font>
			</td>
		</tr>
<tr>
<td colspan="17">
<h2>
<font color="red">${problemRow }</font>
</h2>
</td>
</tr>
		<tr>
			<td COLSPAN="17">
				<input type="submit"
					value="提交"
					class="submit">
					&nbsp;&nbsp;
&nbsp; <input type="button" class="button" name="save" value="导出模板" Onclick="window.location.href ='${app }/contract/tawContracts.do?method=outPutModel'" >
&nbsp;
					
			</td>
		</tr>
	</table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
