 <%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.workplan.util.*"%>
<script language="JavaScript" src="../css/Validator.js"></script>
<script>
function checkForm(form,num){ 
    if(document.getElementById("name").value==null||document.getElementById("name").value==""){
    	alert("请填写文件名");
    	return false;
    } else if(document.getElementById("thisFile").value==null||document.getElementById("thisFile").value==""){
    	alert("请选择上传文件");
    	return false;
    }else{
     
    	var filepath=document.getElementById('thisFile').value;
    	if(filepath.substring(filepath.length-4)==".xls"){
    		 return true;
    	}else{
    	     alert("上传文件类型错误");
    		 return  false;
    	}
    	 
         
    }
}
function GoBack()
{
  window.history.back()
}
</script>
<table>
  <caption><bean:message key="designer.title.formTitle" /></caption>
</table>
<form method="post" name="tawRmCycleTableForm" action="../tawwpaddons/addonsexcelsave.do" onSubmit="return checkForm()"
	 enctype="multipart/form-data"> 
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		 
		<tr>
			<td COLSPAN="3" class="label">
				<bean:message key="designer.title.formAChinese" />
			</td>
			<td COLSPAN="14">
			     
				<input name="name" value="" dataType="Chinese" class="text">
			</td>
		</tr>
		<tr>
			<td COLSPAN="3" class="label">
				<bean:message key="designer.title.formAModel" />
			</td>
			<td COLSPAN="14">
				<select name="model" class="select">
					<option value="50">
						<bean:message key="designer.title.selWorkplan" />
					</option>
				</select>
			</td>
		</tr>
		<tr>
			<td COLSPAN="3" class="label">
				<bean:message key="designer.title.formARemark" />
			</td>
			<td COLSPAN="14">
				<textarea cols="50" name="remark" value="" class="textarea max"></textarea>
			</td>
		</tr>

		<tr class="tr_show">
			<td COLSPAN="3" class="label">
			附加表xls：
			</td>
			<td COLSPAN="14">
				<input type="file" id="file" name="thisFile">
			</td>
		</tr>

		<tr>
			<td COLSPAN="17">
				<input type="hidden" name="action" value="add">
				<input type="submit"
					value="<bean:message key="designer.title.btnSubmit" />"
					class="submit">
				<input type="button"
					value="<bean:message key="designer.title.btnBack" />" name="B2"
					Onclick="GoBack();" class="button">
			</td>
		</tr>
	</table>
</form>
 
<%@ include file="/common/footer_eoms.jsp"%>
