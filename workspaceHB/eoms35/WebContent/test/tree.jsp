<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript">
var tree, treeLoader, root;
Ext.onReady(function(){
	treeLoader = new Ext.tree.TreeLoader({
		dataUrl : "${app}/xtree.do?method=nav"
	});
	
	treeLoader.on('beforeload',function(treeLoader,node,callback){
		treeLoader.baseParams['nodeType'] = node.attributes.nodeType || '';
		treeLoader.baseAttrs = this.baseAttrs;
	},this);
	
	tree = new Ext.tree.TreePanel("tree", {
		animate : true,
		enableDD : false,
		containerScroll : true,
		loader : treeLoader
	});
	
	root = new Ext.tree.AsyncTreeNode({
		id : "-1",
		text : "${eoms:a2u('导航树')}",
		nodeType : 'root'
	});
	tree.setRootNode(root);	
	tree.render();
	
	Ext.get("rootId").addKeyListener(13,function(){
		treeLoader.dataUrl = $V("url");
		root.id = $V("rootId");
		root.reload();
	});
	Ext.get("url").addKeyListener(13,function(){
		treeLoader.dataUrl = $V("url");
		root.id = $V("rootId");
		root.reload();
	});
	
	
	new xbox({
		btnId:'xbox',
		dlgTitle:'${eoms:a2u('请选择新的父部门')}',
		treeDataUrl:"${app}/xtree.do?method=deptForDuty",
		treeRootId:'-1',
		treeRootText:'${eoms:a2u('所有部门')}',
		treeChkMode:'single',
		treeChkType:'user,dept'
	});
	
});

function selectTree(el){
	var item = el.options[el.selectedIndex];
	treeLoader.dataUrl = item.value;
	root.setText(item.text);
	if(item.getAttribute("rootId") || item["rootId"]){
		root.id = item.getAttribute("rootId") || item["rootId"];
	}
	else{
		root.id = -1;
	}
	root.reload();
	
	$("url").update(el.value);
	$("rootId").update(root.id);
	
}
</script>
<p> 
<select onchange="selectTree(this);" id="lister">
  <option value="${app}/xtree.do?method=nav" selected="true">${eoms:a2u("导航树")}</option>
  <option value="${app}/xtree.do?method=dept">${eoms:a2u("部门树")}</option>
  <option value="${app}/xtree.do?method=role" rootId="1">${eoms:a2u("角色树")}</option>
  <option value="${app}/xtree.do?method=userFromDept">${eoms:a2u("部门用户树")}</option>
  <option value="${app}/xtree.do?method=userByDept" rootId="1">${eoms:a2u("某部门下的用户")}</option>
  <option value="${app}/xtree.do?method=userByDeptForTaskplan"  rootId="1">${eoms:a2u("工作计划部门角色树")}</option>
  <option value="${app}/xtree.do?method=postTree">${eoms:a2u("岗位树")}</option>
  <option value="${app}/xtree.do?method=dict">${eoms:a2u("字典管理树")}</option>
  <option value="${app}/xtree.do?method=getContactTree">${eoms:a2u("联系人 选择树")}</option>
  <option value="${app}/xtree.do?method=subroleFromDept">${eoms:a2u("部门子角色树")}</option>
  <option value="${app}/xtree.do?method=areaTree">${eoms:a2u("部门地域树")}</option>
  <option value="${app}/xtree.do?method=getDeptSubRoleUserTree">${eoms:a2u("部门子角色用户树")}</option>
  <option value="${app}/xtree.do?method=deptForDuty">${eoms:a2u("值班部门树")}</option>
  <option value="${app}/xtree.do?method=postFromDept">${eoms:a2u("部门岗位树")}</option>
  <option value="${app}/xtree.do?method=getPortalRoleTree">${eoms:a2u("门户的角色树")}</option>
  <option value="${app}/xtree.do?method=getCptroomTree">${eoms:a2u("机房组织树")}</option>
  <option value="${app}/xtree.do?method=getSubRoleUserTree">${eoms:a2u("子角色用户树")}</option>
</select>
 <input type="button" id="xbox" value="xbox"/>
<br/><br/>

<form onsubmit="javascript:doSearch();return false;">

url:<input id="url" value="/xtree.do?method=nav" class="text" style="width:300px"/>

<br/><br/>

rId:<input id="rootId" value="-1" class="text" style="width:300px"/>
</form>

<br/><br/>

<div id="tree" style="overflow:auto; height:400px;width:350px;border:1px solid #c3daf9;"></div>

<br/><br/>
	<div id="container">
		<div class="viewer-box"></div>
		<input type='hidden' name="userId">
		<input type='hidden' name="deptId">
		<div class="data" style="display:none"></div>
	</div>
	<input type="button" value="dlg" onclick="window.showModalDialog('test/test.jsp',document.getElementById('container'),'scroll=0;resizable=0;dialogWidth=300px;dialogHeight=350px');"/>
<%@ include file="/common/footer_eoms.jsp"%>
