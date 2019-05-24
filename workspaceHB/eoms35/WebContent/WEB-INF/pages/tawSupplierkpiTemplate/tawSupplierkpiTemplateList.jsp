<%@ page language="java" import="com.boco.eoms.base.util.ApplicationContextHolder,com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes" %> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/supplierkpi/tawSupplierkpiTemplates.do?method=getNodes",
    treeRootId:'<%=((SupplierkpiAttributes) ApplicationContextHolder.getInstance().getBean("supplierkpiAttributes")).getTreeRootId()%>',
	treeRootText:"${eoms:a2u('评估报表模型')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'newnode', text:'${eoms:a2u('新建服务类型')}',cls:'new-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiTemplate.do?dictIdNew=" />'},
		{id:'NewSpecial', text:'${eoms:a2u('新建专业模型')}',cls:'new-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiTemplate.do?dictIdNew=" />'},
		{id:'edtnode', text:'${eoms:a2u('修改专业')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiTemplate.do?dictIdEdit=" />'},
		{id:'ViewSpecial', text:'${eoms:a2u('查看/送审')}',cls:'edit-mi',url:'<c:url value="/supplierkpi/viewTawSupplierkpiTemplate.do?method=view&specialType=" />'},
		{id:'delnode', isDelete:true, text:'${eoms:a2u('删除专业')}',cls:'remove-mi',url:'<c:url value="/supplierkpi/editTawSupplierkpiTemplate.do?method=delete&dictId=" />'}
	],//end of ctxMenu
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
  </script>
  <style type="text/css">
  	body{background-image:none;}
  </style>
<div id="headerPanel" class="x-layout-inactive-content">
	<h1>${eoms:a2u('评估报表模型管理')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>${eoms:a2u('添加一个专业模型')}</dt>
		<dd>${eoms:a2u('step1.在树图中的服务类型或专业类型上点击右键，选择"新建专业模型"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面中填写专业类型信息后点击"保存"按钮即可')}</dd>		
		<dt>${eoms:a2u('修改一个专业模型')}</dt>
		<dd>${eoms:a2u('step1.在树图中需要修改的专业上点击右键，选择"修改专业模型"')}</dd>
		<dd>${eoms:a2u('step2.在右侧页面中修改专业类型信息后点击"保存"按钮即可')}</dd>
		<dt>${eoms:a2u('删除一个专业模型')}</dt>
		<dd>${eoms:a2u('在树图中需要删除的专业节点上点击右键，选择"删除专业模型"')}</dd>
		<dt>${eoms:a2u('查看专业模型')}</dt>
		<dd>${eoms:a2u('在树图中专业类型上点击右键，选择"查看/送审"，右面的页面即可呈现专业信息，包括模型属性以及相关指标')}</dd>
		<dt>${eoms:a2u('送审专业模型')}</dt>
		<dd>${eoms:a2u('step1.先对需要送审的专业模型进行"查看/送审"操作')}</dd>
		<dd>${eoms:a2u('step2.若此时该专业模型的审核状态为"未送审"，则点击"送审"按钮可将该专业模型送审')}</dd>
		<dd>${eoms:a2u('step3.若此时该专业模型的审核状态为"驳回"，且指标列表中有红色的指标，则请到"评估指标管理"功能中修改红色指标，之后即可执行送审操作')}</dd>
	</dl>
</div>
<div id="treePanel" class="x-layout-inactive-content">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel" class="x-layout-inactive-content">
	<iframe id="formPanel-page" name="formPanel-page" frameborder="no" style="width:100%;height:100%" src=""></iframe>
</div>

<%@ include file="/common/footer_eoms.jsp"%>