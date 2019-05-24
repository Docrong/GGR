<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/netdisk/AppFrameTreeNetDisk.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/workbench/netdisk/tawWorkbenchNetDisks.do?method=getNodes",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('个人文件夹')}",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/workbench/netdisk/searchFiles.do?method=",text:"${eoms:a2u('')}"},
	ctxMenu:[
		{id:'newnode', text:'${eoms:a2u('新建文件夹')}',cls:'new-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=newFolder&folderPath='},
		{id:'edtnode', text:'${eoms:a2u('重命名')}',cls:'edit-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=editFolder&folderPath='},
		{id:'sharenode', text:'${eoms:a2u('共享')}',cls:'edit-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=shareFolder&folderPath='},
		{id:'listnode', text:'${eoms:a2u('文件列表')}',cls:'list-mi',url:'${app}/workbench/netdisk/tawWorkbenchFiles.do?method=listFilesMain&folderPath='},
		{id:'delnode', isDelete:true, text:'${eoms:a2u('删除')}',cls:'remove-mi',url:'${app}/workbench/netdisk/tawWorkbenchFolders.do?method=deleteFolder&folderPath='}
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
	<h1>${eoms:a2u('网络U盘')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>${eoms:a2u('网络U盘')}</dt>
		<dd>${eoms:a2u('为每个用户提供1G存储空间作为个人网络U盘，可以上传下载管理个人文件，并可以设置共享，将自己的文件夹共享给别人。')}</dd>
	    <dt>${eoms:a2u('我的文件夹')}</dt>
		<dd>${eoms:a2u('1.新建文件夹')}</dd>
		<dd>${eoms:a2u('在左侧的【我的文件夹】以及下级的文件夹上点击右键，选择【新建文件夹】。')}</dd>
		<dd>${eoms:a2u('在右侧的页面中填写新文件夹名称，之后点击【保存】按钮完成新文件夹的创建。')}</dd>
		<dd>${eoms:a2u('2.重命名文件夹')}</dd>
		<dd>${eoms:a2u('展开左侧的【我的文件夹】，在要修改的文件夹上点击右键，选择【重命名】。')}</dd>
		<dd>${eoms:a2u('在右侧的页面中填写新文件夹名称，之后点击【保存】按钮完成新文件夹的重命名。')}</dd>
		<dd>${eoms:a2u('3.共享文件夹')}</dd>
		<dd>${eoms:a2u('展开左侧的【我的文件夹】，在要共享的文件夹上点击右键，选择【共享】。')}</dd>
		<dd>${eoms:a2u('右侧页面中可看到此文件夹下的文件列表。')}</dd>
		<dd>${eoms:a2u('点击【更改人员列表】按钮，在弹出的窗口中选择接受文件夹共享的用户。')}</dd>
		<dd>${eoms:a2u('点击【共享给以上用户】按钮，将此文件夹共享给选择的用户。')}</dd>
		<dd>${eoms:a2u('点击【共享给所有人】按钮，将此文件夹共享给所有用户。')}</dd>
		<dd>${eoms:a2u('4.查看文件列表/上传文件')}</dd>
		<dd>${eoms:a2u('展开左侧的【我的文件夹】，在要查看的文件夹上点击右键，选择【文件列表】。')}</dd>
		<dd>${eoms:a2u('右侧页面列出此文件夹下的所有文件。')}</dd>
		<dd>${eoms:a2u('通过【浏览】按钮选择要上传的文件，之后点击【上传】按钮上传文件。')}</dd>
		<dd>${eoms:a2u('点击文件列表中的【下载】或【删除】可下载或删除文件。')}</dd>
		<dd>${eoms:a2u('5.删除文件夹')}</dd>
		<dd>${eoms:a2u('展开左侧的【我的文件夹】,在要删除的文件夹上点击右键，选择【删除】。')}</dd>
		<dt>${eoms:a2u('我的共享')}</dt>
		<dd>${eoms:a2u('展开左侧的【我的共享】,可看到自己共享给别人的所有文件夹。')}</dd>
		<dt>${eoms:a2u('共享给我')}</dt>
		<dd>${eoms:a2u('展开左侧的【共享给我】,可看到共享给自己文件夹的用户，继续展开可以看到某用户共享给自己的文件夹。')}</dd>
		<dt>${eoms:a2u('查询')}</dt>
		<dd>${eoms:a2u('点击查询，在右边面板出现查询条件输入框。输入条件即可查询到相关的文件列表')}</dd>
		<dd>${eoms:a2u('注意：')}</dd>
		<dd>${eoms:a2u('1、查询范围为自己上传的文件和别人共享给自己的文件。')}</dd>
		<dd>${eoms:a2u('2、查询出的文件能下载和删除，但是只有属于自己的文件才能删除，不属于自己的文件将不出现"删除"按钮')}</dd>
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