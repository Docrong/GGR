<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<jsp:directive.page import="com.boco.eoms.km.expert.config.KmExpertProps" />
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">
	var config = {
			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/kmmanager/kmsystemCptrooms.do?method=getNodes",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('所有值班小组')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建值班小组')}"},
			delNode:{text:"${eoms:a2u('删除此值班小组')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/kmmanager/kmsystemCptrooms.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentid","id");
				}
			},
			getNode : {
				url:"${app}/kmmanager/kmsystemCptrooms.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/kmmanager/kmsystemCptrooms.do?method=xedit"
			},
			delNode : {
				url:"${app}/kmmanager/kmsystemCptrooms.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [	       			
	        new Ext.form.TextField({
	            fieldLabel: "${eoms:a2u('值班小组名称')}",
	            name: 'roomname',
	            allowBlank:false  
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "${eoms:a2u('值班地址')}",
	            name: 'address'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "${eoms:a2u('值班小组负责人')}",
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
	            fieldLabel: "${eoms:a2u('所属专业')}",
	            name: 'deptName',
	            id:'deptName',
	            allowBlank:false,
	            readOnly:'true'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.phone'/>",
	            name: 'phone',
	            id:'phone'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemCptroomForm.mobile'/>",
	            name: 'mobile',
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
				treeDataUrl:'${app}/kmmanager/kmExpertTree.do?method=userFromMenu',
				treeRootId:'-1',treeRootText:'${eoms:a2u('用户')}',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'managerName',saveChkFldId:'manager' 
			});
		  new xbox({
				btnId:'tempmanagerName',dlgId:'hello-dlg3',
				treeDataUrl:'${app}/kmmanager/kmExpertTree.do?method=userFromMenu&fieldDictId=1010104/1050103&fieldName=specialty/expertLevel',
				treeRootId:'-1',treeRootText:'${eoms:a2u('临时用户')}',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'tempmanagerName',saveChkFldId:'tempmanager' 
			});
		  new xbox({
				btnId:'deptName',dlgId:'hello-dlg2',
				treeDataUrl:'${app}/xtree.do?method=dict',
				  treeRootId: '<%=KmExpertProps.getDictRootId("RootSpecialty")%>',treeRootText:'${eoms:a2u('所有专业')}',treeChkMode:'single',
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
		<dd>${eoms:a2u('管理设定所有的值班小组，呈树状结构，可实现增删改查等功能。有"组织管理"权限的用户有此操作的权限。')}</dd>
	</dl>
	<br/>
	<dl>
		<dt>${eoms:a2u('在值班小组管理里新增一个值班小组')}</dt>
		<dd>${eoms:a2u('在树图中的所有值班小组上点击右键，并选择"新建值班小组"')}</dd>
		<dt>${eoms:a2u('修改一个值班小组的信息')}</dt>
		<dd>${eoms:a2u('在树图中的值班小组上点击右键，并选择"修改"')}</dd>
		<dt>${eoms:a2u('删除值班小组')}</dt>
		<dd>${eoms:a2u('在树图中的值班小组上点击右键，并选择"删除此值班小组"')}</dd>
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
