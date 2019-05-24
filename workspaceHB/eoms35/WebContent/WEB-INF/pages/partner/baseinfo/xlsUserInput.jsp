<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
 <%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript"><%--
 var problem = "${problemRow}";
 if(problem!="")
 {
 var size = problem.length;
 var sub = problem.substring(1,size) ;
 var array = sub.split(",");
  var str = "";
  for(var i=0;i<array.length;i++){
  
  str = str+"请检查第"+array[i]+"行"+"第"+array[i+1]+"列"+"\n";
  i++;
  alert(str);
  }
  alert(str);
  document.getElementById("pro").value="dddsss";
 }if(problem==""){
 alert("nothing is wrong!");
 }--%>
function checkForm(form,num){ 
    if(document.getElementById("accessoryName").value==null||document.getElementById("accessoryName").value==""){
    	alert("请选择上传文件");
    	return false;
    }else if(document.getElementById("accessoryName").value.indexOf(".xls")<0){
    	alert("确认选择的文件为Excel");
    	return false;
    }else{
    return true;//Validator.Validate(form,num);
    }
  }
  
</script>
<table class="formTable">
	<caption >
	 
		人力信息批量录入
		
	</caption>
</table>
<html:form action="/partnerUsers.do?method=xlsSave"
	method="post" styleId="partnerUserForm"
	enctype="multipart/form-data" onsubmit="return checkForm()">
	
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		
		<tr class="tr_show">
			<td COLSPAN="3" class="label">
				人力信息上传
			</td>
			<td COLSPAN="14">

				<input name="accessoryName" type="file" class="file" /><font color="red">请选择上传xls格式的文件</font>

				<input name="treeNodeId" type="hidden" value="${treeNodeId }">	
			</td>
		</tr>
<tr>
<td colspan="17">

<font color="red" >${problemRow }</font>

</td>
</tr>
		<tr>
			<td COLSPAN="17">
				<input type="submit"
					value="提交"
					class="submit">
					&nbsp;&nbsp;&nbsp;			
 <input type="button" class="button" name="save" value="下载导入模板" Onclick="window.location.href ='${app }/partner/baseinfo/partnerUsers.do?method=outPutModel'" >
&nbsp;	
					
			</td>
		</tr>
	</table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
