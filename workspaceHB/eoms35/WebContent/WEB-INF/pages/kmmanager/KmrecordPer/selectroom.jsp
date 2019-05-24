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
    treeRootText:"${eoms:a2u('值班工作统计')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/duty/TawRmRecordPer/selectDate.do?typeId=",text:"${eoms:a2u('选择日期')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('选择日期')}',cls:'new-mi',url:'${app}/duty/TawRmRecordPer/selectDate.do?typeId='}
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
    <h1>${eoms:a2u("值班工作统计")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		 <dt>${eoms:a2u('功能说明')}</dt>
		 <dd>${eoms:a2u('值班工作统计就是统计某个值班状态下都完成了一些什么事情，不如说值班。统计出来的是某段时间在值班中所记录的信息')}</dd>
	     <dt>${eoms:a2u('选择机房：')}</dt>
		 <dd>${eoms:a2u('列出来的机房都是管理员对用户配置的机房域，在选择的机房上点击右键，选择【选择日期】。或者【直接点击机房】')}</dd>
		 <dt>${eoms:a2u('选择时间：')}</dt>
		 <dd>${eoms:a2u('选择时间，点击【确认】')}</dd>
		 <dt>${eoms:a2u('统计：')}</dt>
		 <dd>${eoms:a2u('进入统计页面，【选择统计类型】、【选择完成状态】、【选择操作员】，【选择班次】 如果不选择则默认为所有，其中【选择班次】为选择的时间是一天，最后点击【统计】')}</dd>
	
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