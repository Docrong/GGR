<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">

Ext.onReady(function(){
	colorRows('list-table');
})
function openSheet(url){
	if(parent.frames['north']){
		parent.frames['north'].location.href = url;
	}
	else{
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
		if (parent == null)
			return;
		var taskName = "${draft}";
		if (taskName != null && taskName == "DraftHumTask") {
			window.opener.location.href = "${app}/sheet/emergencydrill/emergencydrill.do?method=showDetailPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+templateId ;
		} else {
			window.opener.location.href = "${app}/sheet/emergencydrill/emergencydrill.do?method=referenceTemplate&templateId="+templateId ;
		}
		
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

<bean:define id="url" value="emergencydrill"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table resourceAssessTable"
		export="false" requestURI="emergencydrill" 
		sort="list" size="total" partialList="true"
		>
		<display:column>
			<input type="radio" name="templateId" value="${taskList.id}"/>
		</display:column>
		<display:column sortable="true" headerClass="sortable" title="模板名称">
			<a href="${app}/sheet/emergencydrill/emergencydrill.do?method=editTemplateInfo&templateId=${taskList.id}&type=${templateManage}">
				${taskList.sheetTemplateName} 
			</a>
		</display:column>
		<display:column sortable="true" headerClass="sortable" title="应用分类一">
			 <eoms:id2nameDB id="${taskList.mainEmergencySortOne}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
	</display:table>
<logic:notPresent name="templateManage">
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="check()">
     <bean:message bundle="sheet" key="button.refere"/>    	
</html:button>
</logic:notPresent>
<%@ include file="/common/footer_eoms.jsp"%>