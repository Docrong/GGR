
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>

<script type="text/javascript">
	var config = {			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"emailMonitors.do?method=xGetChildNodes",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('扄1�7有项盄1�7')}",
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
				url:"emailMonitors.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentId","id");
				}
			},
			getNode : {
				url:"emailMonitors.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"emailMonitors.do?method=xedit"
			},
			delNode : {
				url:"emailMonitors.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.applyId"/>',
	            	name: 'applyId',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.content"/>',
	            	name: 'content',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.dispatchTime"/>',
	            	name: 'dispatchTime',
	            	allowBlank:false
	        	})

			   /* Hidden Field
	            */
			  	new Ext.form.HiddenField({name: 'id'})

			   /* Hidden Field
	            */
			  	new Ext.form.HiddenField({name: 'id'})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.email"/>',
	            	name: 'email',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.receiverId"/>',
	            	name: 'receiverId',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.buizid"/>',
	            	name: 'buizid',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.serviceId"/>',
	            	name: 'serviceId',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.isSendImediat"/>',
	            	name: 'isSendImediat',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.deleted"/>',
	            	name: 'deleted',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="emailMonitorForm.regetData"/>',
	            	name: 'regetData',
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

