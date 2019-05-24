<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
function openSheet(url){
	if(parent.frames['north']){
		parent.frames['north'].location.href = url;
	} else{
		location.href = url;
	}
}

function check() {
	var templateIds = document.getElementsByName("templateId");
	var i = 0;
	var templateId = "";
	for (i = 0 ; i < templateIds.length; i ++) {
		if (templateIds[i].checked) {
			templateId = templateIds[i].value;
		}
	}
	if (templateId == "") {
		alert("<bean:message bundle="sheet" key="template.select"/>");
		return false;
	} else {
		if (parent == null) return;
		window.opener.location.href = "${app}/sheet/${module}/${module}.do?method=newReferenceTemplate&templateId="+templateId ;
		window.close();
	}
	
}

function removeTemplate() {
	if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
		var thisform = document.forms[0];
		thisform.action = thisform.action + "?method=removeTemplate&templateId=${sheetMain.id}";
		thisform.submit();
	}
}
</script>

<bean:define id="url" value="${module}.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table resourceAssessTable"
		export="false" requestURI="${module}.do" 
		sort="list" size="total" partialList="true"
		>
		<display:column>
			<input type="radio" name="templateId" value="${taskList.id}"/>
		</display:column>
		<display:column sortable="true" headerClass="sortable" title="模板名称">
			<a href="${app}/sheet/${module}/${module}.do?method=newEditTemplateInfo&templateId=${taskList.id}&type=${templateManage}">
				${taskList.sheetTemplateName} 
			</a>
		</display:column>
		<display:column property="sendTime" sortable="true"
			headerClass="sortable" title="创建时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
</display:table>

<logic:notPresent name="templateManage">
	<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="check()">
	     <bean:message bundle="sheet" key="button.refere"/>    	
	</html:button>
</logic:notPresent>


<%@ include file="/common/footer_eoms.jsp"%>