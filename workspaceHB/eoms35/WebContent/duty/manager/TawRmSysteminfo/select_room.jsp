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
    treeRootText:"${eoms:a2u('值班配置')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/duty/TawRmSysteminfo/add.do?roomId=",text:"${eoms:a2u('配置值班信息')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('配置值班信息')}',cls:'new-mi',url:'${app}/duty/TawRmSysteminfo/add.do?roomId='}
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
    <h1>${eoms:a2u("配置值班信息")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	 	<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('值班信息主要分为两个功能 1、配置信息：配置机房的值班信息，主要包括交接班误差时间，交接班时间等等 2、配置人员：配置机房值班人员')}</dd>
	    <dt>${eoms:a2u('选择机房')}</dt>
		<dd>${eoms:a2u('列出来的机房都是管理员对用户配置的机房域，在选择的机房上点击右键，选择【配置值班信息】。或者【直接点击机房】')}</dd>
	    <dt>${eoms:a2u('配置值班系统参数')}</dt>
		<dd>${eoms:a2u('进入到配置参数的页面，其中【值班人数】是每个班次的最大值班人数，【周期附加表执行周期】是在填写值班周期附加报表的时候生成周期报表的周期，【交接班最大误差时间】是上下班允许的最大时间，【交接班时间】是选择的上一个班次和下一个班次交接的时间，选择时间到添加方框内，如果想删除选择方框内的时间点击删除。【交接班形式】是用户选择用那总方式进行交接班。所有的都选择好之后点击【保存】')}</dd>
		<dt>${eoms:a2u('配置用户')}</dt>
		<dd>${eoms:a2u('进入配置用户页面，选择部门，选择人员（人员可以多选），【添加】到右边的【本机房参加人员】内的方框内，如果需要删除选择人员，选择上人员点击【删除】，如果不需要人员参加排班，选择上人员点击【向下】放入下面的【本机房不参加人员】，点击【保存】')}</dd>
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