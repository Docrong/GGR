<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/kmmanager/KmrecordPer/getroom.do",
    treeRootId:'-1',
    treeRootText:"${eoms:a2u('所属值班小组')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/kmmanager/Kmrecord/query.do?typeId=",text:"${eoms:a2u('查询值班记录')}"},
	ctxMenu:[
		{id:'Child', text:'${eoms:a2u('查询')}',cls:'new-mi',url:'${app}/kmmanager/Kmrecord/query.do?typeId='}
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
    <h1>${eoms:a2u("查询值班记录")}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('功能说明')}</dt>
		<dd>${eoms:a2u('根据时间段查询班次的值班记录，管理员可以对查询出来的值班记录进行审核，同时也提供按照一定的时间段生成excle格式的报表')}</dd>
	  	<dt>${eoms:a2u('选择机房')}</dt>
		<dd>${eoms:a2u('列出来的值班小组都是管理员对用户配置的值班小组域，在选择的值班小组上点击右键，选择【查询】。或者【直接点击值班小组】')}</dd>
		<dt>${eoms:a2u('选择时间查询')}</dt>
		<dd>${eoms:a2u('选择时间，选择值班记录的关键字，点击【查询】')}</dd>
<!-- 	
		<dt>${eoms:a2u('审核')}</dt>
		<dd>${eoms:a2u('在查询出来的结果集内，选择框选择需要审核的记录，在【审核意见】内填写审核意见，点击【通过审核】，可以多条记录一起审核')}</dd>
 -->		
		<dt>${eoms:a2u('导出')}</dt>			
		<dd>${eoms:a2u('在查询出来的结果集内，点击【导出】，把条件内的所有内容生成Excel文件下载到本地')}</dd>
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