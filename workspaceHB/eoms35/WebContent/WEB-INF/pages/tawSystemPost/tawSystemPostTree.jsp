<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">
	var config = {
			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/post/tawSystemPosts.do?method=getNodes&allowChild=true&allowDelete=false",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('所有岗位')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建岗位')}"},
			delNode:{text:"${eoms:a2u('删除这个岗位')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/post/tawSystemPosts.do?method=save",
				init:function(){
					AppSimpleTree.setField("deptId","id");
				}
			},
			getNode : {
				url:"${app}/post/tawSystemPosts.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/post/tawSystemPosts.do?method=edit"
			},
			delNode : {
				url:"${app}/post/tawSystemPosts.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemPostForm.postName'/>",
	            name: 'postName',
	            allowBlank:false
	        }),
			
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemPostForm.postUsers'/>",
	            name: 'userNames', id: 'userNames',
	            readOnly:'true'
	        }),
	   
	        new Ext.form.TextArea({
	            fieldLabel: "<fmt:message key='tawSystemPostForm.postRemark'/>",
	            name: 'notes',
	            grow: true,
	            preventScrollbars:true
	        }),
	        /* Hidden Field new Ext.form.HiddenField({name: 'leaf'}),
	        */
	        new Ext.form.HiddenField({name: 'postId'}),
	        new Ext.form.HiddenField({name: 'deptId'}),
	        new Ext.form.HiddenField({name: 'deleted'}),
	        new Ext.form.HiddenField({name: 'userids',id:'userids'}),
	        new Ext.form.HiddenField({name: 'leaf'})
		], // end of fields
		
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
			//user tree	
			var	userTreeAction='${app}/xtree.do?method=userFromDept';
			Ext.onReady(function(){
				xbox({
					btnId:'userNames',dlgId:'dlg-user',
					treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("用户")}',treeChkMode:'',treeChkType:'user',
					showChkFldId:'userNames',saveChkFldId:'userids'
				});
			});			
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);
</script>

<div id="headerPanel" class="app-header"><img src="${app}/styles/${theme}/images/header-post.gif"></div>
<div id="helpPanel" class="app-panel">
	<dl>
		<dt>${eoms:a2u('添加一个下级岗位')}</dt>
		<dd>${eoms:a2u('在树图中的岗位上点击右键，并选择"新建岗位"')}</dd>
		<dt>${eoms:a2u('修改一个岗位的信息')}</dt>
		<dd>${eoms:a2u('在树图中的岗位上点击右键，并选择"修改"')}</dd>
		<dt>${eoms:a2u('删除岗位')}</dt>
		<dd>${eoms:a2u('在树图中的岗位上点击右键，并选择"删除这个岗位"')}</dd>
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
