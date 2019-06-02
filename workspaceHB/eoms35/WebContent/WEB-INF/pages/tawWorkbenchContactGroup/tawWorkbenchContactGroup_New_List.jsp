
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>

<script type="text/javascript">
	var config = {			
		/**************
		* Tree Configs
		**************/
		test:"tawWorkbenchContactGroups.do?method=getNodes",
		treeGetNodeUrl:"tawWorkbenchContactGroups.do?method=getNodes",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('�?有项')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建')}"},
			delNode:{text:"${eoms:a2u('删除')}"}
		},//end of ctxMenu		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"tawWorkbenchContactGroups.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentId","id");
				}
			},
			getNode : {
				url:"tawWorkbenchContactGroups.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"tawWorkbenchContactGroups.do?method=xedit"
			},
			delNode : {
				url:"tawWorkbenchContactGroups.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [
		    	new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawWorkbenchContactGroupForm.deleted"/>',
	            	name: 'deleted',
	            	allowBlank:false
	        	}),

		    	new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawWorkbenchContactGroupForm.groupName"/>',
	            	name: 'groupName',
	            	allowBlank:false
	        	}),

			  	new Ext.form.HiddenField({name: 'id'}),
		    	new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawWorkbenchContactGroupForm.remark"/>',
	            	name: 'remark',
	            	allowBlank:false
	        	}),

		    	new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawWorkbenchContactGroupForm.userId"/>',
	            	name: 'userId',
	            	allowBlank:false
	        	}),

	           /* Hidden Field
	            */
	            new Ext.form.HiddenField({name: 'parentId'}),
		        new Ext.form.HiddenField({})
			], // end of fields
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);
</script>

<div id="headerPanel" class="app-header"></div>
<div id="helpPanel" class="app-panel">
	<dl>
		<dt>${eoms:a2u('添加')}</dt>
		<dd></dd>
		<dt>${eoms:a2u('修改')}</dt>
		<dd></dd>
		<dt>${eoms:a2u('删除')}</dt>
		<dd></dd>
	</dl>
</div>
<div id="treePanel">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel">
	<div id="formPanel-body" class="app-panel"></div>
</div>

<%@ include file="/common/footer_eoms.jsp"%>

