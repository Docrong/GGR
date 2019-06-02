<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%> 

<script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/${module}/${module}.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
   	form.submit();
}

function removeTemplate() {
	if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
		var thisform = document.forms[0];
		thisform.action = "${app}/sheet/${module}/${module}.do?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
		thisform.submit();
	}
}
</script>


<html:form action="/${module}.do?method=performDealNew"  styleId="theform">   
<!-- 标题栏 -->    
<br/>  
<table class="formTable">
<caption><bean:message bundle="${module}" key="${module}.header"/></caption>
</table>

<!-- 环节中处理的内容 -->
<jsp:include page="/WEB-INF/pages/wfworksheet/${module}/workflow/${forwordJsp}.jsp"/>

<!-- 模板按钮 -->

<br>

<logic:present name="type">
<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
	<!-- 模板的标题栏 -->
	<table class="formTable">
		<tr>
			 <td  class="label">模板名称*</td>
			 <td clspan="3">  
	     			<input type="text" class="text max" name="templateName" value="${sheetLink.templateName}">
   	 		</td>
		</tr> 
	</table>
	<br>
	<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveDealTemplate('current')">
         	<bean:message bundle="sheet" key="button.saveCurTemplate" />
   	</html:button>
</c:if>

<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
    <bean:message bundle="sheet" key="button.delete" />
</html:button>
</logic:present>

<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
         <bean:message bundle="sheet" key="button.back" />
</html:button>



</html:form>

 