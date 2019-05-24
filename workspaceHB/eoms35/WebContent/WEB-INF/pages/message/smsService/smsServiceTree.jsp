<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/msg/smsServices.do?method=getNodes",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('所有模块业务')}",
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'newnode', text:'${eoms:a2u('新建模块业务')}',cls:'new-mi',url:'<c:url value="/msg/newModule.do?status=1&parentid=" />'},
		{id:'editnode', text:'${eoms:a2u('修改模块业务')}',cls:'edit-mi',url:'<c:url value="/msg/smsServices.do?method=xeditbefore&status=1&id=" />'},
		{id:'newsnode', text:'${eoms:a2u('新增服务')}',cls:'new-mi',url:'<c:url value="/msg/newService.do?status=2&parentid=" />'},
		{id:'edtsnode', text:'${eoms:a2u('修改服务')}',cls:'edit-mi',url:'<c:url value="/msg/smsServices.do?method=xedit2service&status=2&id=" />'},
		{id:'copynode', text:'${eoms:a2u('复制服务ID至剪切板')}',cls:'edit-mi',url:'<c:url value="/msg/coptToClipboard.do?status=2&id=" />'},
		{id:'deletesnode', isDelete:true, text:'${eoms:a2u('删除')}',cls:'remove-mi',url:'<c:url value="/msg/smsServices.do?method=xdelete&id=" />'}
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
	<img src="${app}/styles/default/images/header-msg.gif">
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('功能说明：')}</dt>
		<dd>${eoms:a2u('该模块提供模块订制、服务订制、服务修改、个性化服务和订制取消服务功能；模块订制主要是为了区分系统和模块')}</dd>
		<dd>${eoms:a2u('间的层次关系，无任何业务，目的是使各个服务都有所属模块，不会造成混淆。在定义好了模块业务的层次后就可以在')}</dd>
		<dd>${eoms:a2u('其下建立服务，根据业务的不同订制不同的服务，方便程序调用并根据定义的服务规则发送短信等消息')}</dd>
		<dt>${eoms:a2u('新建模块业务')}</dt>
		<dd>${eoms:a2u('在树图中的所有模块业务上点击右键,并选择"新建模块业务",模块业务主要是为了层次清晰,并无实际含义')}</dd>
		<dt>${eoms:a2u('修改模块业务')}</dt>
		<dd>${eoms:a2u('在树图中的模块业务上点击右键,并选择"修改模块业务",主要是修改模块业务名称')}</dd>
		<dt>${eoms:a2u('新增服务')}</dt>
		<dd>${eoms:a2u('在树图中的模块业务上点击右键，并选择"新增服务",模块业务下可以新增服务,服务节点上不可以新增服务')}</dd>
		<dt>${eoms:a2u('个性化服务')}</dt>
		<dd>${eoms:a2u('点击个性化服务,下拉列表中显示的是该登录人员所拥有的全部服务,可以选择一个进行更改服务参数')}</dd>
		<dt>${eoms:a2u('订制取消服务')}</dt>
		<dd>${eoms:a2u('在树图上选上不需要的服务进行取消')}</dd>		
		<dt>${eoms:a2u('删除')}</dt>
		<dd>${eoms:a2u('在树图中的模块业务上或者服务上点击右键,并选择"删除"')}</dd>
		<dt>${eoms:a2u('复制服务ID至剪切板:支持IE、FireFox')}</dt>
		<dd>${eoms:a2u('该右键功能是为了在订制服务后方便的获取32位服务ID,避免手抄造成错误')}</dd>
		<dt>${eoms:a2u('使用注意：')}</dt>
		<dd>${eoms:a2u('新增服务里面有个正选的checkbox，默认是不选择的，这样新增的服务默认所有人都拥有，这时在更改人员列表里选中的人')}</dd>
		<dd>${eoms:a2u('是代表不需要该服务，这种情况适合工单等业务，即使所有人都有服务但是如果只有工单涉及到某人，才会发短信，不会造成发给所')}</dd>
		<dd>${eoms:a2u('有人的情况，当选上这个叫正选的checkbox就意味该服务是正选，正选的含义就是在更改人员列表的树里面选上谁，谁才有该服务')}</dd>		
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
