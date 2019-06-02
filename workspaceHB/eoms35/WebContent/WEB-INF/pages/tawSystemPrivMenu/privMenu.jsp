<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<style type="text/css">
    #treePanel-sourceTree, #treePanel-privTree {
    	float:left;
    	margin:5px;
    	border:1px solid #c3daf9;
    	width:200px;
    	height:300px;
    	overflow:auto;
    }
</style>
<script type="text/javascript">
var gridAction = "${app}/priv/tawSystemPrivMenus.do?method=xGetJSONList";
var gridData = '${resultSize}';
Ext.QuickTips.init();
var config = {
	/**************
	* Tree Configs
	**************/
	treeGetNodeUrl:"${app}/priv/tawSystemPrivOperations.do?method=getNodes4privmenu",
	treeRootId:'-1',
	treeRootText:"${eoms:a2u('所有菜单资源')}",
	/**************
	* Form Configs
	**************/
	actions:{
		saveNewMenu : {
			title:'${eoms:a2u("新建菜单方案")}',
			url:"${app}/priv/saveTawSystemPrivMenu.do?method=xsave",
			btnText:'${eoms:a2u("保存菜单方案")}'			
		},
		getMenu : {
			url:"${app}/priv/saveTawSystemPrivMenu.do?method=xget"
		},
		deleteMenu : {
			url:"${app}/priv/saveTawSystemPrivMenu.do?method=xdelete"
		},
		editMenu : {
			url:"${app}/priv/saveTawSystemPrivMenu.do?method=xsave",
			btnText:"${eoms:a2u('保存修改')}"
		},
		getMenuNodes : {
			url:"${app}/priv/tawSystemPrivMenuItems.do?method=xGetNodes"
		},
		saveMenuItem : {
			url:"${app}/priv/saveTawSystemPrivMenuItem.do?method=xsave"
		},
		deleteMenuItem : {
			url:"${app}/priv/saveTawSystemPrivMenuItem.do?method=xdelete"
		}
	},
	fieldOptions : {
		width:250
	},
	fields : [
        new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSystemPrivMenuForm.name"/>',
            name: 'name',
            allowBlank:false
        }),
        new Ext.form.SimpleSelect({
	            fieldLabel: '<fmt:message key="tawSystemPrivMenuForm.nature"/>',
	            hiddenName:'nature',
	            values : [
	        				['${eoms:a2u('EOMS')}', '1'],
	        				['${eoms:a2u('WAP')}', '0']
	        			   ],
	            allowBlank:false,
	            value:'1'
	        }),
      new Ext.form.TextArea({
            fieldLabel: '<fmt:message key="tawSystemPrivMenuForm.remark"/>',
            name: 'remark',
            grow: true,
            preventScrollbars:true
        }),
        //new Ext.form.HiddenField({name: 'privid'}),
		new Ext.form.HiddenField({name: 'ownerId',id:'ownerId',value:'${sessionScope.sessionform.userid}'}),
		new Ext.form.HiddenField({name: 'privid',id:'privid',value:''})
	] // end of fields
};

</script>
<script type="text/javascript" src="${app}/scripts/layout/PrivConfiger.js"></script>
<style type="text/css">
body{
	 background-image:none;
}
</style>
<div id="headerPanel" class="x-layout-inactive-content">
	<h1>${eoms:a2u('菜单方案管理')}</h1>
</div>
<div id="helpPanel" class="x-layout-inactive-content panel">
	<dl>
		<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('对应用模块及功能项（即系统权限）以菜单方案为单位进行管理并分配给用户或者角色，任意一个菜单方案代表一个权限集合。')}</dd>
		<dd>${eoms:a2u('只有管理员能够进行菜单方案的创建。')}</dd>
	</dl>
	<br/>
	<dl>
		<dt>${eoms:a2u('添加一个菜单方案')}</dt>
		<dd>${eoms:a2u('在左侧表格上方点击"新建菜单方案"')}</dd>
		<dt>${eoms:a2u('编辑菜单方案的菜单项')}</dt>
		<dd>${eoms:a2u('在左侧表格中的菜单方案上点击右键，并选择"编辑菜单项"')}</dd>
		<dt>${eoms:a2u('修改一个菜单方案的名称、备注')}</dt>
		<dd>${eoms:a2u('在左侧表格中的菜单方案上点击右键，并选择"修改菜单方案的名称和备注"')}</dd>
		<dt>${eoms:a2u('删除菜单方案')}</dt>
		<dd>${eoms:a2u('在左侧表格中的菜单方案上点击右键，并选择"删除"')}</dd>
	</dl>
</div>
<div id="gridPanel" class="x-layout-inactive-content">
	<div id="gridPanel-tb" class="tb"></div>
	<div id="gridPanel-body" class="gridPanel-grid"></div>
</div>
<div id="treePanel" class="x-layout-inactive-content panel">
	<div>	
	  <dl>
		<dt>${eoms:a2u('添加菜单项：')}</dt>
		<dd>${eoms:a2u('从左边的菜单资源树中<span class="tip">拖拽</span>你要添加的节点到右侧树图中的相应位置，每次只能拖拽一个节点。')}</dd>
		<dt>${eoms:a2u('删除菜单项')}</dt>
		<dd>${eoms:a2u('在右侧树图中你要删除的节点上<span class="tip">点击右键</span>，并选择"删除"')}</dd>
	  </dl>
	</div>
	<div id="treePanel-sourceTree"></div>
	<div style="float:left;"><br/><br/><img src="${app}/images/icons/forward.png"/></div>
	<div id="treePanel-privTree"></div>
</div>
<div id="formPanel">
	<div id="formPanel-body" class="app-panel"></div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>