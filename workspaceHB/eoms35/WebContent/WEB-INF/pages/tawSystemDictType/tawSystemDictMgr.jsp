<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">
	var config = {
			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/dict/tawSystemDictTypes.do?method=getNodes",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('所有字典')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建下级字典项')}"},
			delNode:{text:"${eoms:a2u('删除这个字典项')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/dict/tawSystemDictTypes.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentDictId","dictId");
				}
			},
			getNode : {
				url:"${app}/dict/tawSystemDictTypes.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/dict/tawSystemDictTypes.do?method=xedit"
			},
			delNode : {
				url:"${app}/dict/tawSystemDictTypes.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [
	        new Ext.form.TextField({
	            fieldLabel: '<fmt:message key="tawSystemDictTypeForm.dictName"/>',
	            name: 'dictName',
	            id:'dictName',
	            allowBlank:false
	        }),
	        new Ext.form.TextField({
	            fieldLabel: '<fmt:message key="tawSystemDictTypeForm.dictCode"/>',
	            name: 'dictCode',
	            id:'dictCode'
	        }),
	      new Ext.form.TextArea({
	            fieldLabel: '<fmt:message key="tawSystemDictTypeForm.dictRemark"/>',
	            name: 'dictRemark',
	            grow: true,
	            preventScrollbars:true
	        }),
	        /* Hidden Field
	        */
	        new Ext.form.HiddenField({name:'dictId'}),
	        new Ext.form.HiddenField({name: 'moduleId'}),
	        new Ext.form.HiddenField({name: 'moduleName'}),
	        new Ext.form.HiddenField({name: 'id'}),
	        new Ext.form.HiddenField({name: 'parentDictId'}),
	        new Ext.form.HiddenField({name: 'sysType'}),
	        new Ext.form.HiddenField({name: 'leaf'})
		], // end of fields
		
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);
</script>

<div id="headerPanel" class="app-header"><img src="${app}/styles/${theme}/images/header-dict.gif"></div>
<div id="helpPanel" class="app-panel">
	<dl>
		<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('维护系统运行所需的基本数据，这些数据可能因系统的使用对象不同或不同时期而有不同的配置。常用的字典表配置包括：机房配置、故障类型配置、故障紧急程度配置等。通过字典表的配置可使系统的应用更加灵活。')}</dd>
	</dl>
	<br/>
	<dl>
		<dt>${eoms:a2u('添加一个下级字典项')}</dt>
		<dd>${eoms:a2u('在树图中的字典项上点击右键，并选择"新建下级字典项"')}</dd>
		<dt>${eoms:a2u('修改一个字典项的信息')}</dt>
		<dd>${eoms:a2u('在树图中的字典项上点击右键，并选择"修改"')}</dd>
		<dt>${eoms:a2u('删除字典项')}</dt>
		<dd>${eoms:a2u('在树图中的字典项上点击右键，并选择"删除这个字典项"')}</dd>
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
