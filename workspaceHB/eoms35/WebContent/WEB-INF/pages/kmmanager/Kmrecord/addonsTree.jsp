<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/duty/TawRmRecordPer/getroom.do",
    treeRootId:'-1',
    treeRootText:"${eoms:a2u('附加表管理')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/duty/TawRmRecord/addonslist.do?typeId=",text:"${eoms:a2u('详细列表')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('新增附加表')}',cls:'new-mi',url:'${app}/duty/TawRmRecord/addnos.do?typeId='}
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
    <h1>${eoms:a2u("附加表模板管理")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl> <dt>${eoms:a2u('功能说明')}</dt>
		 <dd>${eoms:a2u('附加表管理主要是对某个机房所填写附加表的自动化管理。只要在这个机房上配置附加表，则在填写值班记录的时候就会同时生成一个附加表供值班人员填写')}</dd>
	     <dt>${eoms:a2u('选择机房：')}</dt>
		 <dd>${eoms:a2u('列出来的机房都是管理员对用户配置的机房域，在选择的机房上点击右键，选择【新增附加表】')}</dd>
		 <dt>${eoms:a2u('新增附加表：')}</dt>
		 <dd>${eoms:a2u('进入新增附加表页面，填写信息【提交】，上传的附加表模板要把表格打好后再上传')}</dd>
		 <dt>${eoms:a2u('查看：')}</dt>
		 <dd>${eoms:a2u('直接点击机房进入查看页面，在页面内点击【详细】可以查看附加表的内容，并且可以修改附加表，点击【下载】下载附加表，点击【删除】删除附加表')}</dd>
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