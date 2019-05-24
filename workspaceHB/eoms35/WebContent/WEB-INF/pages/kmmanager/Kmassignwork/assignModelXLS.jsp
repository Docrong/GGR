<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
 
<script type="text/javascript">
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
  
  function model()
{
  
  document.forms[0].action = "/TawRmAssignwork/modelSetAndAssign";
  document.forms[0].submit();
  }  
</script>
<table>
	<caption>
		${eoms:a2u('周期排班表管理')}
	</caption>
</table>
<%
	String roomId = request.getAttribute("roomId").toString();
	System.out.println(roomId);
	
 %>
<html:form action="/TawRmAssignwork/xlsInput"
	method="post" styleId="tawRmAssignworkForm"
	enctype="multipart/form-data" onsubmit="return checkForm()">
	
	<input type="hidden" name="roomId" value="<%=roomId %>" >
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		
		<tr class="tr_show">
			<td COLSPAN="3" class="label">
				${eoms:a2u('周期表上传')}
			</td>
			<td COLSPAN="14">

				<input name="thisFile" type="file"
					class="file" /><font color="red">${eoms:a2u('请选择上传xls格式的文件')}</font>
			</td>
		</tr>

		<tr>
			<td COLSPAN="17">
				<input type="hidden" name="action" value="add">
				<input type="submit"
					value="<bean:message key="designer.title.btnSubmit" />"
					class="submit">
					&nbsp;&nbsp;&nbsp;
					 <input type="button" class="button" name="save" value="${eoms:a2u('导出周期模板')}" Onclick="window.location.href ='../TawRmAssignwork/xlsOutput.do?roomId=<%=roomId %>'" >
&nbsp;
					 
			</td>
		</tr>
	</table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
