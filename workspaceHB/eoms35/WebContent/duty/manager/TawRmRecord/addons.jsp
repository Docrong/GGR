<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script language="JavaScript" src="../css/Validator.js"></script>
<script>
function checkForm(form,num){ 
 
    if(document.getElementById("thisFile").value==null||document.getElementById("thisFile").value==""){
    	alert("${eoms:a2u('请选择上传文件')}");
    	return false;
    }else if(document.getElementById("thisFile").value.indexOf(".xls")<0){
    	alert("${eoms:a2u('确认选择的文件为Excel')}");
    	return false;
    }else{
    return Validator.Validate(form,num);
    }
}
function GoBack()
{
  window.history.back()
}
</script>
<table>
	<caption>
		<bean:message key="designer.title.formTitle" />
	</caption>
</table>
<%
String typeId = request.getAttribute("typeId").toString();
%>
<html:form action="TawRmRecord/upload" method="post"
	styleId="tawRmAddonsTableForm" enctype="multipart/form-data"
	onsubmit="return checkForm()">
	<input type="hidden" name="typeId" value="<%=typeId%>" />
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		
		<tr>
			<td COLSPAN="3" class="label">
				<bean:message key="designer.title.formAName" />
			</td>
			<td COLSPAN="14">
				<input name="name"   dataType="Chinese" class="text">
			</td>
		</tr>
		<%--
		<tr>
			<td COLSPAN="3" class="label">
				<bean:message key="designer.title.formAChinese" />
			</td>
			<td COLSPAN="14">
				<input name="value" value="" dataType="EnglishAndInteger" class="text">
			</td>
		</tr>
		--%>
		<%--<tr>
			<td COLSPAN="3" class="label">
			<bean:message key="designer.title.formAModel" />
			</td>
			<td COLSPAN="14">
				<select name="model" class="select">
					<option value="50">
						mouban
					</option>
				</select>
			</td>
		</tr>
		--%>
		<tr>
			<td COLSPAN="3" class="label">
				<bean:message key="designer.title.formARemark" />
			</td>
			<td COLSPAN="14">
				<textarea cols="50" name="remark"   class="textarea max"></textarea>
			</td>
		</tr>

		<tr class="tr_show">
			<td COLSPAN="3" class="label">
				${eoms:a2u('附加表xls：')}
			</td>
			<td COLSPAN="14">

				<input name="thisFile" type="file" class="file" />
			</td>
		</tr>

		<tr>
			<td COLSPAN="17">
				<input type="hidden" name="action" value="add">
				<input type="submit"
					value="<bean:message key="designer.title.btnSubmit" />"
					class="submit">

			</td>
		</tr>
	</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
