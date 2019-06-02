
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>

<script type="text/javascript">
	var config = {			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"tawRmLogUnites.do?method=xGetChildNodes",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('�?有项�?')}",
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
				url:"tawRmLogUnites.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentId","id");
				}
			},
			getNode : {
				url:"tawRmLogUnites.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"tawRmLogUnites.do?method=xedit"
			},
			delNode : {
				url:"tawRmLogUnites.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [

			   /* Hidden Field
	            */
			  	new Ext.form.HiddenField({name: 'id'})

			   /* Hidden Field
	            */
			  	new Ext.form.HiddenField({name: 'id'})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.workerNames"/>',
	            	name: 'workerNames',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.planContent"/>',
	            	name: 'planContent',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.workOrder"/>',
	            	name: 'workOrder',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.workbenchMemo"/>',
	            	name: 'workbenchMemo',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.dispatchRecord"/>',
	            	name: 'dispatchRecord',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.visitRecord"/>',
	            	name: 'visitRecord',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.reliefRecord"/>',
	            	name: 'reliefRecord',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.loanRecord"/>',
	            	name: 'loanRecord',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.beginTime"/>',
	            	name: 'beginTime',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.endTime"/>',
	            	name: 'endTime',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.userId"/>',
	            	name: 'userId',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.workSerial"/>',
	            	name: 'workSerial',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.assessorId"/>',
	            	name: 'assessorId',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.auditingReason"/>',
	            	name: 'auditingReason',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmLogUniteForm.auditingTime"/>',
	            	name: 'auditingTime',
	            	allowBlank:false
	        	})

			], // end of fields
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);
</script>

<div id="headerPanel" class="app-header"><h1>Title</h1></div>
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

