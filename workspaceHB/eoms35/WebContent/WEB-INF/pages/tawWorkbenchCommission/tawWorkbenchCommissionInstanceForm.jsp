<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String moduleId = request.getAttribute("moduleId").toString();
%>

<style type="text/css">
  	body{background-image:none;}
</style>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawWorkbenchCommissionInstanceForm'});
	
	var	userTreeAction='${app}/workbench/commission/tawWorkbenchCommissionPresets.do?method=userFromPreset&moduleId=' + <%=moduleId%>;
	userViewer = new Ext.JsonView("trustorName",
		'<div id="role-user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: false,
			emptyText : '<div></div>'
		}
	);
	var s = '[]';
	userViewer.jsonData = eoms.JSONDecode(s);
	userViewer.refresh();
	
	roleUserTree = new xbox({
		btnId:'role-userTreeBtn',dlgId:'dlg-role-user',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'<bean:message key="tawWorkbenchCommission.presetTrustor" />',treeChkMode:'single',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'trustorId'
	});
	
});

function reinitFrameSize() {
  var iframe = self.parent.frames['commissionInstanceList'];
  iframe.height = iframe.contentWindow.document.documentElement.scrollHeight;
}

</script>

<html:form
	action="/tawWorkbenchCommissionInstances.do?method=saveInstance"
	method="post" styleId="tawWorkbenchCommissionInstanceForm">
	<html:hidden property="moduleId" value="<%=moduleId%>" />
	<table class="formTable">
		<tr>
			<td width="15%" class="label">
				<bean:message key="tawWorkbenchCommissionInstance.moduleName" />
			</td>
			<td width="35%">
				<eoms:dict key="dict-commission" dictId="module"
					itemId="<%=moduleId%>" beanId="id2nameXML" />
			</td>
		</tr>
		<tr>
			<td class="label">
				<bean:message key="tawWorkbenchCommissionInstance.trustorName" />
			</td>
			<td>
				<div id="trustorName" class="viewer-list"></div>
				<input type="hidden" id="trustorId" name="trustorId"
					alt="allowBlank:false,vtext:'<fmt:message key="tawWorkbenchCommission.trustorNotSelected" />'" />
				<input type="button"
					value="<bean:message key="tawWorkbenchCommission.selectTrustor" />"
					id="role-userTreeBtn" class="btn" />
			</td>
		</tr>
		<tr>
			<td class="label">
				<bean:message key="tawWorkbenchCommissionInstance.startTime" />
			</td>
			<td>
				<input id="startTime" name="startTime" type="text" class="text"
					readonly="readonly"
					alt="vtype:'lessThen',link:'endTime',vtext:'<fmt:message key="tawWorkbenchCommission.laterThan" />'"
					onclick="popUpCalendar(this, this);" />
			</td>
		</tr>
		<tr>
			<td class="label">
				<bean:message key="tawWorkbenchCommissionInstance.endTime" />
			</td>
			<td>
				<input id="endTime" name="endTime" type="text" class="text"
					readonly="readonly"
					alt="vtype:'moreThen',link:'startTime',vtext:'<fmt:message key="tawWorkbenchCommission.earlierThan" />'"
					onclick="popUpCalendar(this, this);" />
			</td>
		</tr>

	</table>

	<br>
	<br>

	<table>
		<tr>
			<td>
				<html:submit styleClass="button" property="method.save"
					onclick="bCancel=true">
					<fmt:message key="button.save" />
				</html:submit>
				<html:button styleClass="button" property="method.cancel"
					onclick="javascript:window.history.back();">
					<fmt:message key="button.cancel" />
				</html:button>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</html:form>

<script src="${app}/scripts/util/iframe.js" type="text/javascript" />



<script type="text/javascript">
reinitFrameSize();
</script>
<%@ include file="/common/footer_eoms.jsp"%>
