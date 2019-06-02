<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>

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
	var templateIds = document.getElementsByName("dealTemplateId");
	var i = 0;
	var dealTemplateId = "";
	for (i = 0 ; i < templateIds.length; i ++) {
		if (templateIds[i].checked) {
			dealTemplateId = templateIds[i].value;
		}
	}
	if (dealTemplateId == "") {
		alert('<bean:message bundle="sheet" key="template.select"/>');
		return false;
	} else {
		if (parent == null)
			return;
		var url = "${app}/sheet/equipmentinstallation/equipmentinstallation.do?method=showDetailPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId;
		window.opener.location.href = url;
		window.close();
	}
}
</script>
<bean:define id="url" value="equipmentinstallation.do"/>
<fmt:bundle basename="config/ApplicationResources-sheet">
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table resourceAssessTable"
		export="false" requestURI="equipmentinstallation.do"
		sort="list" size="total" partialList="true">
		<display:column>
			<input type="radio" name="dealTemplateId" value="${process.id}"/>
		</display:column>
		<display:column sortable="true" headerClass="sortable" titleKey="template.templateName">
			<a href="${app}/sheet/equipmentinstallation/equipmentinstallation.do?method=openDealTemplateInfo&dealTemplateId=${process.id}&type=${templateManage}">
				${process.templateName}
			</a>
		</display:column>
		<display:column property="operateTime" sortable="true"
			headerClass="sortable" title="创建时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
	</display:table>
</fmt:bundle>
<logic:notPresent name="templateManage">
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="check()">
     <bean:message bundle="sheet" key="button.refere"/>  	
</html:button>
</logic:notPresent>

<%@ include file="/common/footer_eoms.jsp"%>

