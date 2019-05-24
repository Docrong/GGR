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
    treeRootText:"${eoms:a2u('所属机房域')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/duty/TawRmRecord/queryedit.do?typeId=",text:"${eoms:a2u('修改值班记录')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('查询')}',cls:'new-mi',url:'${app}/duty/TawRmRecord/queryedit.do?typeId='}
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
    <h1>${eoms:a2u("修改值班记录")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('根据时间段查询班次的值班记录，进行修改')}</dd>
	    <dt>${eoms:a2u('选择机房：')}</dt>
		<dd>${eoms:a2u('列出来的机房都是管理员对用户配置的机房域，在选择的机房上点击右键，选择【查询】。或者【直接点击机房】')}</dd>
		<dt>${eoms:a2u('选择时间查询：')}</dt>
		<dd>${eoms:a2u('选择时间，点击【查询值班记录】')}</dd>
		<dt>${eoms:a2u('修改：')}</dt>
		<dd>${eoms:a2u('选择需要修改的记录，点击后面的【编辑】按钮进入编辑页面。填写，保存')}</dd>
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