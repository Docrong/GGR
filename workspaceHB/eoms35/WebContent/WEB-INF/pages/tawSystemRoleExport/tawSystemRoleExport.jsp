<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">

Ext.onReady(function(){
var reportUserViewer = new Ext.JsonView("reportView",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择</div>'
			}
		);
		var r='[]';
		reportUserViewer.jsonData = eoms.JSONDecode(r);
		reportUserViewer.refresh();
	    var roleXbox = new xbox({
        btnId: 'roleList',
        dlgId: 'showrole-dlg',
        treeDataUrl: '${app}/role/systemRoleImport.do?method=getRoles',
        treeRootId: '-1',
        treeRootText: '角色列表',
        treeChkMode: '',
        treeChkType: 'workflow,role,group',
        saveChkFldId: 'id',viewer:reportUserViewer,returnJSON:true
    });
    
    	//v = new eoms.form.Validation({form:'tawSystemRoleImportForm'});
    	
})
	function isSelectRole(){
    	var id = document.getElementById("id").value;
    	if(id=="[]"){
    	alert("请选择角色");
    	return false;
    	}
    	}
</script>
	<html:form action="systemRoleImport.do?method=exortExcel" method="post"
		styleId="tawSystemRoleImportForm">
		<table class="formTable">
			<caption>
				<div class="header center">
					角色导出
				</div>
			</caption>
			<tr class="tr_show">
				<td>
					<div id="reportView" class="viewer-box"></div>
					&nbsp;&nbsp;&nbsp;
					<input type="button" id="roleList" class="btn" value="角色列表" />
					
						<html:hidden property="id" styleId="id"/>
						<html:submit styleClass="button"  property="method.exortExcel"
				onclick="javascript: return isSelectRole();">
				角色导出
			</html:submit>
		</table>
	</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
