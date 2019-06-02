<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawWorkbenchDeptContactGroupForm'});
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
	userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,		
			emptyText : '<div><bean:message key="tawWorkbenchContact.impuserlist"/></div>'								
		}
	);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'<bean:message key="tawWorkbenchContact.deptusertree"/>',treeChkMode:'',treeChkType:'user,dept',
		viewer:userViewer,saveChkFldId:'userId',returnJSON:true 
	});
});
</script>
<html:form action="tawWorkbenchDeptContacts.do?method=impFromDeptTree"
	method="post" styleId="tawWorkbenchDeptContactGroupForm" enctype="multipart/form-data">
	<%
	String nodeid = request.getParameter("nodeid");
	%>
	<table>
		<tr>
			<td>
				<td class="label"><div id="user-list" class="viewer-list"></div>
    			<input type="button" value="<bean:message key='tawWorkbenchContact.deptusertree'/>" id="userTreeBtn" class="btn"/>
			</td>
		</tr>
	</table>
	<html:hidden property="userId"/>
	<html:hidden property="nodeid" value="<%=nodeid%>"/>
<BR> 

<input type="submit" value="${eoms:a2u('导入')}" class="btn" />

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>


