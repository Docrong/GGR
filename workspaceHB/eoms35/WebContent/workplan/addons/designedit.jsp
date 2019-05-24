<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="org.jdom.*"%>
<%@ page import="org.jdom.output.*"%>
<%@ page import="org.jdom.Document"%>
<%@ page import="org.jdom.Element"%>
<%@ page import="org.jdom.input.*"%>
<%@ page import="com.boco.eoms.workplan.vo.TawwpAddonsElementVO"%>
<%@ page import="com.boco.eoms.workplan.vo.TawwpAddonsTableVO"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable"%>

<%@ page import="com.boco.eoms.workplan.util.CodeTypeComsInfo"%>

<script language="JavaScript" src="../css/Validator.js"></script>
<script>
function checkForm(form,num){ 
    if(document.getElementById("name").value==null||document.getElementById("name").value==""){
    	alert("请填写文件名");
    	return false;
    } 
    
}
lastobj=null;
function current(obj)
{
	if(lastobj!=null)
        {
		lastobj.style.background="#E2F1FC";
	}
	obj.style.background="#A2B1CC";
	lastobj=obj;
}
function GoBack()
{
  window.history.back()
}
</script>

<%

  TawwpAddonsTableVO tawwpAddonsTableVO=(TawwpAddonsTableVO)request.getAttribute("tawwpAddonsTableVO");
 %>  
<table>
  <caption><bean:message key="designedit.title.formTitle" /></caption>
</table> 
<form method="post" name="tawRmCycleTableForm" action="addonsexcelsave.do" onSubmit="return checkForm()" enctype="multipart/form-data"> 
    <input type="hidden" name=id value="<%=tawwpAddonsTableVO.getId()%>">
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		 
		<tr>
			<td COLSPAN="3" class="label">
				<bean:message key="designer.title.formAChinese" />
			</td>
			<td COLSPAN="14">
			 
				<input name="name" value="<%=tawwpAddonsTableVO.getName()%>"   dataType="Chinese"  class="text" />
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
				<textarea cols="50" name="remark" value="" class="textarea max"><%=tawwpAddonsTableVO.getText()%></textarea>
			</td>
		</tr>

		<tr class="tr_show">
			<td COLSPAN="3" class="label">
			附加表xls:
			</td>
			<td COLSPAN="14">
				<input type="file" id="thisFile" name="thisFile">
			</td>
		</tr>

		<tr>
			<td COLSPAN="17">
				<input type="hidden" name="action" value="edit">
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
 
<!--修改XML各项变量表格：结束-->
<%@ include file="/common/footer_eoms.jsp"%>
