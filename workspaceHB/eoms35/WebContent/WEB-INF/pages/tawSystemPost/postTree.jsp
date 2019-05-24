<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">
	var config = {
			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/post/tawSystemPosts.do?method=getNodes",
		treeRootId:'1',
		treeRootText:"${eoms:a2u('所有岗位')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建下级岗位')}"},
			delNode:{text:"${eoms:a2u('删除这个岗位')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/post/tawSystemPosts.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentDeptid","id");
				}
			},
			getNode : {
				url:"${app}/post/tawSystemPosts.do?method=xget&id="
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/post/tawSystemPosts.do?method=xedit&deptId="
			},
			delNode : {
				url:"${app}/post/tawSystemPosts.do?method=xdelete",
				customData:function(){
					var node = AppSimpleTree.getSelNode();		
					return "id="+node.id+"&parentDeptid="+node.parentNode.id;
				}
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [
        new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.deptName"/>',
            name: 'deptName',
            allowBlank:false,
            width:150
        }),
         new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.deptmanager"/>',
            name: 'deptmanager',
            id: 'deptmanager',
            allowBlank:false,
            readOnly:'true',
            width:150
        }),
           new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.tmporaryManager"/>',
            name: 'tmporaryManager',
            id: 'tmporaryManager',
            allowBlank:true,
            readOnly:'true',
            width:150
        }),
         new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.tmporarybegintime"/>',
            name: 'tmporarybegintime',
            allowBlank:true,
            width:150
        }),
         new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.tmporarystopTime"/>',
            name: 'tmporarystopTime',
            allowBlank:true,
            width:150
        }),
         new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.deptemail"/>',
            name: 'deptemail',
            allowBlank:false,
            width:150,
            vtype:'email'
        }),
           new Ext.form.NumberField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.deptfax"/>',
            name: 'deptfax',
            allowBlank:false,
            width:150
        }),
         new Ext.form.NumberField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.deptmobile"/>',
            name: 'deptmobile',
            allowBlank:false,
            width:150  
        }),
         new Ext.form.NumberField({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.deptphone"/>',
            name: 'deptphone',
            allowBlank:false,
            width:150
        }),
        

        new Ext.form.SimpleSelect({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.deptType"/>',
            hiddenName:'deptType',
            values : [
        				['NetWORK', '1'],
        				['Service Layer', '0']
        			   ],
            allowBlank:false,
            value:'1',
            width:150
        }),
        new Ext.form.SimpleSelect({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.regionflag"/>',
            hiddenName:'regionflag',
            values : [
        				['Region', '1'],
        				['DepartMent', '0']
        			   ],
            allowBlank:false,
            value:'1',
            width:150
        }),
    
        new Ext.form.TextField({
            fieldLabel: 'POST',
            name: 'postName',
            id:'postName',
            allowBlank:false,
            width:150
        }),
        new Ext.form.TextArea({
            fieldLabel: '<fmt:message key="tawSystemDeptForm.remark"/>',
            name: 'remark',
            width:150,
            grow: true,
            preventScrollbars:true
        }),
        /* Hidden Field new Ext.form.HiddenField({name: 'leaf'}),
        */
        new Ext.form.HiddenField({name: 'id'}),
        new Ext.form.HiddenField({name: 'deptId',value:'1'}),
        new Ext.form.HiddenField({name: 'opertime'}),
        new Ext.form.HiddenField({name: 'operuserid'}),
        new Ext.form.HiddenField({name: 'parentDeptid'}),
         new Ext.form.HiddenField({name: 'oerdercode'}),
        new Ext.form.HiddenField({name: 'leaf'}),
        new Ext.form.HiddenField({name: 'tempid',id:'tempid'}),
        new Ext.form.HiddenField({name: 'newPostId',id:'newPostId'}),
        new Ext.form.HiddenField({name: 'tempid2',id:'tempid2'})  
		], // end of fields
		
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
			var	treeAction2='${app}/xtree.do?method=userFromDept';
			var treePost='${app}/xtree.do?method=roleTree';
			xbox({
				btnId:'deptmanager',dlgId:'hello-dlg2',
				treeDataUrl:treeAction2,treeRootId:'-1',treeRootText:'Users',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'deptmanager',saveChkFldId:'tempid'
			});
			xbox({
				btnId:'tmporaryManager',dlgId:'hello-dlg',
				treeDataUrl:treeAction2,treeRootId:'-1',treeRootText:'Users',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'tmporaryManager',saveChkFldId:'tempid2'
			});
			xbox({
				btnId:'postName',dlgId:'hello-dlg3',
				treeDataUrl:treePost,treeRootId:'1',treeRootText:'Post',treeChkMode:'',treeChkType:'post',
				showChkFldId:'postName',saveChkFldId:'newPostId'
			});
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);



</script>
<style type="text/css">
body{
	 background-image:none;
}
</style>
<div id="headerPanel" class="app-header"><img src="${app}/styles/default/images/header-post.gif"></div>
<div id="helpPanel" class="app-panel">
	<dl>
		<dt>${eoms:a2u('添加一个下级岗位')}</dt>
		<dd>${eoms:a2u('在树图中的岗位上点击右键，并选择"新建下级岗位"')}</dd>
		<dt>${eoms:a2u('修改一个岗位的信息')}</dt>
		<dd>${eoms:a2u('在树图中的岗位上点击右键，并选择"修改"')}</dd>
		<dt>${eoms:a2u('删除岗位')}</dt>
		<dd>${eoms:a2u('在树图中的岗位上点击右键，并选择"删除"')}</dd>
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
