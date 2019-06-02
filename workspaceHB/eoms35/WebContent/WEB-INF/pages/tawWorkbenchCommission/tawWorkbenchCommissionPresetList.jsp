<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String moduleId = request.getAttribute("moduleId").toString();
String trustorIds = request.getAttribute("trustorIds").toString();
String trustorsStr = request.getAttribute("trustorsStr").toString();
%>

<script type="text/javascript">
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';	
	userViewer = new Ext.JsonView("user-list",
		'<div id="role-user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,
			emptyText : '<div><bean:message key="tawWorkbenchCommission.noTrustor" /></div>'
		}
	);
	var s = '<%=trustorIds%>';
	userViewer.jsonData = eoms.JSONDecode(s);
    userViewer.refresh();
	roleUserTree = new xbox({
		btnId:'role-userTreeBtn',dlgId:'dlg-role-user',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'用户',treeChkMode:'',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'trustorId'
	});	
	checkModuleId();
	setHidden();
})

function findTrustors() {
  var moduleId = document.forms[0].moduleId.value;
  if (moduleId == '' || moduleId < 0) {
    document.getElementById('fs').style.display='none';
  }
  else {
    var url="${app}/workbench/commission/tawWorkbenchCommissionPresets.do?method=listAllPresets&moduleId=" + moduleId;
    window.location.href = url;
  }
}

function checkModuleId() {
  var moduleId = document.forms[0].moduleId.value;
  if (moduleId == '' || moduleId < 0) {
    document.getElementById('fs').style.display='none';
  }
}

function setHidden() {
  document.forms[0].trustorId.value='<%=trustorsStr%>';
}
</script>
<div class="list-title">
	<bean:message key="tawWorkbenchCommission.preset" />
</div>

<html:form action="/tawWorkbenchCommissionPresets.do?method=savePreset"
	styleId="tawWorkbenchCommissionPresetForm" method="post"
	onsubmit="return validate();"> 
<bean:message key="tawWorkbenchCommission.selectModule" />
<eoms:dict key="dict-commission" dictId="module" isQuery="false"
		defaultId="<%=moduleId%>" selectId="moduleId" beanId="selectXML"
		onchange="findTrustors();" />
	<br>
	<br>
	<div id="fs">
		<fieldset>
			<legend>
				<bean:message key="tawWorkbenchCommission.selectTrustor" />
			</legend>
			<bean:message key="tawWorkbenchCommission.selectUser" />
			<input type="button" value="<bean:message key="tawWorkbenchCommission.changeUser" />"
				id="role-userTreeBtn" class="btn" />
			<div id="user-list" class="viewer-list"></div>
			<input type="hidden" id="trustorId" name="trustorId" />
		</fieldset>
		<br>
		<input type="submit" class="btn"
			value="<fmt:message key="button.save"/>" />
	</div>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
