<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
function checkForm(form,num){ 
 
    if(document.getElementById("accessoryName").value==null||document.getElementById("accessoryName").value==""){
    	alert("${eoms:a2u('请选择上传文件')}");
    	return false;
    }else if(document.getElementById("accessoryName").value.indexOf(".xls")<0){
    	alert("${eoms:a2u('确认选择的文件为Excel')}");
    	return false;
    }else{
    return Validator.Validate(form,num);
    }
  }
</script>
<title>${eoms:a2u('合作伙伴基本信息批量录入')}</title>
<html:form action="tawSystemDepts.do?method=xaccessory"
	method="post" styleId="tawSystemDeptForm"
	enctype="multipart/form-data" onsubmit="return checkForm()">


	<table class="formTable small" align="center">
		<tr>
			<td colspan="2" align="center" class="label">
				<h2>
				${eoms:a2u('合作伙伴基本信息批量录入')}
				</h2>
				<p align="right"><font color="#ff0000">${eoms:a2u('请先下载模板，录入后再上传该xls文件')}</font></p>
			</td>
		</tr>
		<tr>
			<td class="label" nowrap="nowrap" align="right" >
				${eoms:a2u('模板文件')}
			</td>
			<td>
			<%
					String url = "/WEB-INF/pages/tawSystemDept/template";
					String fileRealName = "partnerDeptTemplate.xls";
					url += "/" + fileRealName;
			%>
				<a
					href="<%=request.getContextPath()%>/dept/tawSystemDepts.do?method=xmlOutPut&url=<%=url%>"><%=fileRealName%>
				</a>
			</td>
		</tr>
		<tr>
			<td class="label" nowrap="nowrap" align="right">
				${eoms:a2u('文件上传')}
			</td>
			<td >          
				<input name="accessoryName" type="file" class="file" />
			</td>
		</tr>
		<tr>
			<td align="right">
				<html:submit styleClass="button" property="method.save">
					<fmt:message key="button.save" />
				</html:submit>
			</td>
			<td >
				<html:reset styleClass="button" property="method.reset">
					<fmt:message key="button.reset" />
				</html:reset>
			</td>
		</tr>
	</table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>