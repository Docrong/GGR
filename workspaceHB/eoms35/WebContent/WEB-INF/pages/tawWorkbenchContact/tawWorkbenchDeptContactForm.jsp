<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<title><fmt:message key="tawWorkbenchContactDetail.title" /></title>
<script type="text/javascript"
	src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=dept';
	userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,		
			emptyText : '<div>${eoms:a2u("所属部门")} </div>'
								
		}
	);
	userViewer.refresh();
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'deptId' 
	});
})
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawWorkbenchDeptContactForm'});
	 
});

</script>


<html:form action="saveTawWorkbenchDeptContact?method=xsave" method="post"
	styleId="tawWorkbenchDeptContactForm">
	<ul>
		<li>

			<eoms:label styleClass="desc"
				key="tawWorkbenchContactForm.contactName" />
			<html:errors property="contactName" />

			<html:text property="contactName" styleId="contactName"
				styleClass="text medium"
				alt="allowBlank:false,vtext:'${eoms:a2u('请输入用户名称')}'" />
		</li>
		<li>
			<html:hidden property="deptId" styleId="deptId"
				styleClass="text medium" />

			<div id="user-list" class="viewer-list">
			</div>
			<html:text property="deptName" styleId="deptName"
				styleClass="text medium" readonly="true" />
			<input type="button" value="${eoms:a2u('部门列表')}" id="userTreeBtn"
				class="btn" />
		</li>
		<li>
 
			<eoms:label styleClass="desc" key="tawWorkbenchContactForm.position" />
			<html:errors property="position" />

			<html:text property="position" styleId="position"
				styleClass="text medium" />
		</li>

		<li>

			<eoms:label styleClass="desc" key="tawWorkbenchContactForm.tele" />
			<html:errors property="tele" />

			<html:text property="tele" styleId="tele" styleClass="text medium"
				alt="vtype:'number'" />
		</li>


		<li>

			<eoms:label styleClass="desc" key="tawWorkbenchContactForm.address" />
			<html:errors property="address" />

			<html:text property="address" styleId="address"
				styleClass="text medium" />
		</li>
		<li>

			<eoms:label styleClass="desc" key="tawWorkbenchContactForm.email" />
			<html:errors property="email" />

			<html:text property="email" styleId="email" styleClass="text medium"
				alt="vtype:'email'" />
		</li>

		<html:hidden property="id" />
		<html:hidden property="groupId" />


		<li class="buttonBar bottom">
			<html:submit styleClass="button" property="method.save"
				onclick="bCancel=false">
				<fmt:message key="button.save" />
			</html:submit>

		</li>
	</ul>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
