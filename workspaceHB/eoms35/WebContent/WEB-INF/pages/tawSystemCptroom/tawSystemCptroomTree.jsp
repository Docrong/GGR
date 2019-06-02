<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">
	var config = {
			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/cptroom/tawSystemCptrooms.do?method=getNodes",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('所有机房')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建机房')}"},
			delNode:{text:"${eoms:a2u('删除此机房')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/cptroom/tawSystemCptrooms.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentid","id");
				}
			},
			getNode : {
				url:"${app}/cptroom/tawSystemCptrooms.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/cptroom/tawSystemCptrooms.do?method=xedit"
			},
			delNode : {
				url:"${app}/cptroom/tawSystemCptrooms.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [	       			
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.roomname'/>",
	            name: 'roomname',
	            allowBlank:false  
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.address'/>",
	            name: 'address'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.manager'/>",
	            name: 'managerName',
	            id:'managerName',
	            allowBlank:false,
	            readOnly:'true'
	        }),
	        
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.tempmanager'/>",
	            name: 'tempmanagerName',
	            id:'tempmanagerName',
	            allowBlank:false,
	            readOnly:'true'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.deptid'/>",
	            name: 'deptName',
	            id:'deptName',
	            allowBlank:false,
	            readOnly:'true'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.phone'/>",
	            name: 'phone',
	            vtype:'number',
	            id:'phone'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.mobile'/>",
	            name: 'mobile',
	            vtype:'number',
	            id:'mobile'
	        }),
	        
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.fax'/>",
	            name: 'fax',
	            vtype:'number',
	            id:'fax'
	        }),
	        new Ext.form.DateField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.endtime'/>",
	            name: 'endtime',
	            format:'Y-m-d'
	        }),		        
	        new Ext.form.TextArea({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.notes'/>",
	            name: 'notes',
	            grow: true,
	            preventScrollbars:true
	        }),
	        /* Hidden Field
		    */
		    new Ext.form.HiddenField({name: 'id'}),
		    new Ext.form.HiddenField({name: 'deleted'}),
		    new Ext.form.HiddenField({name: 'leaf'}),
		    new Ext.form.HiddenField({name: 'parentid'}),		    
		    new Ext.form.HiddenField({name: 'deptid',id:'deptid'}),
		    new Ext.form.HiddenField({name: 'manager',id:'manager'}),
		    new Ext.form.HiddenField({name: 'tempmanager',id:'tempmanager'})
	      	
		], // end of fields    
		
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){		
		   var treedept='${app}/xtree.do?method=dept';
		   var treeAction2='${app}/xtree.do?method=userFromDept';
		   new xbox({
				btnId:'managerName',dlgId:'hello-dlg1',
				treeDataUrl:treeAction2,treeRootId:'-1',treeRootText:'${eoms:a2u('用户')}',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'managerName',saveChkFldId:'manager' 
			});
		  new xbox({
				btnId:'tempmanagerName',dlgId:'hello-dlg3',
				treeDataUrl:treeAction2,treeRootId:'-1',treeRootText:'${eoms:a2u('用户')}',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'tempmanagerName',saveChkFldId:'tempmanager' 
			});
		  new xbox({
				btnId:'deptName',dlgId:'hello-dlg2',
				treeDataUrl:treedept,treeRootId:'-1',treeRootText:'${eoms:a2u('部门')}',treeChkMode:'single',treeChkType:'dept',
				showChkFldId:'deptName',saveChkFldId:'deptid' 
			});
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);
</script>

<div id="headerPanel" class="app-header"><img src="${app}/styles/${theme}/images/header-room.gif"></div>
<div id="helpPanel" class="app-panel">
	<dl>
		<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('管理设定的所有机房，呈树状结构，可实现增删改查等功能。有"组织管理"权限的用户有此操作的权限。')}</dd>
	</dl>
	<br/>
	<dl>
		<dt>${eoms:a2u('在机房管理里新增一个机房')}</dt>
		<dd>${eoms:a2u('在树图中的所有机房上点击右键，并选择"新建机房"')}</dd>
		<dt>${eoms:a2u('修改一个机房的信息')}</dt>
		<dd>${eoms:a2u('在树图中的机房上点击右键，并选择"修改"')}</dd>
		<dt>${eoms:a2u('删除机房')}</dt>
		<dd>${eoms:a2u('在树图中的机房上点击右键，并选择"删除此机房"')}</dd>
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
