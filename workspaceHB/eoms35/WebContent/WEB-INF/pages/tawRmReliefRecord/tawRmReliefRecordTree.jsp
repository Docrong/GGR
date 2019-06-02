
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>

<script type="text/javascript">
	var config = {			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"tawRmReliefRecords.do?method=xGetChildNodes",
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
				url:"tawRmReliefRecords.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentId","id");
				}
			},
			getNode : {
				url:"tawRmReliefRecords.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"tawRmReliefRecords.do?method=xedit"
			},
			delNode : {
				url:"tawRmReliefRecords.do?method=xdelete"
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
	            	fieldLabel: '<fmt:message key="tawRmReliefRecordForm.handoverId"/>',
	            	name: 'handoverId',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmReliefRecordForm.successorId"/>',
	            	name: 'successorId',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmReliefRecordForm.roomId"/>',
	            	name: 'roomId',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmReliefRecordForm.time"/>',
	            	name: 'time',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmReliefRecordForm.lbProblem"/>',
	            	name: 'lbProblem',
	            	allowBlank:false
	        	})

		    	,new Ext.form.TextField({
	            	fieldLabel: '<fmt:message key="tawRmReliefRecordForm.nuiteFlag"/>',
	            	name: 'nuiteFlag',
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

