<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

  <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
 <script type="text/javascript">
  var config = { 
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/testcard/tawRmTestcards.do?method=getNodes",
    treeRootId:'-1',
	treeRootText:"${eoms:a2u('测试卡管理')}",
	pageFrame:'formPanel-page',
	pageFrameId:'formPanel-page',
	//onClick:{url:"${app}/duty/tawRmPlanContent.do?method=allSearch&id=",text:'${eoms:a2u('查询')}'},
	ctxMenu:[  
		{id:'TestCardAdd', text:'${eoms:a2u('新增')}',cls:'new-mi',url:'${app}/testcard/tawRmTestcards.do?method=edit&folderPath='},
		{id:'TestCardQuery', text:'${eoms:a2u('查询')}',cls:'list-mi',url:'${app}/testcard/tawRmTestcards.do?method=testCardSearch&folderPath='},
		{id:'OutAdd', text:'${eoms:a2u('测试卡出库')}',cls:'new-mi',url:'${app}/testcard/tawRmInoutRecords.do?method=outEdit&folderPath='},
		{id:'InAdd', text:'${eoms:a2u('测试卡入库')}',cls:'new-mi',url:'${app}/testcard/tawRmInoutRecords.do?method=inEdit&folderPath='},
		{id:'InOutQuery', text:'${eoms:a2u('出入库查询')}',cls:'list-mi',url:'${app}/testcard/tawRmInoutRecords.do?method=toSearch&folderPath='},
		{id:'RenewAdd', text:'${eoms:a2u('测试卡续借')}',cls:'new-mi',url:'${app}/testcard/tawRmRenewals.do?method=renewEdit&folderPath='},
		{id:'RenewSearch', text:'${eoms:a2u('测试卡续借记录查询')}',cls:'list-mi',url:'${app}/testcard/tawRmRenewals.do?method=toSearch&folderPath='},
		{id:'StatTestCard', text:'${eoms:a2u('统计测试卡信息')}',cls:'list-mi',url:'${app}/testcard/tawRmTestcards.do?method=toStatTestCard&folderPath='},
		{id:'StatInoutRecord', text:'${eoms:a2u('统计借用记录')}',cls:'list-mi',url:'${app}/testcard/tawRmInoutRecords.do?method=toStatInoutRecord&folderPath='}
	 	
	],//end of ctxMenu 
	/************************s
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
	<h1>${eoms:a2u('日志管理')}</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
	    <dt>${eoms:a2u('测试卡管理:')}</dt>
		<dd>${eoms:a2u('实现对测试卡的电子化管理功能。')}</dd>
		
		<dt>${eoms:a2u('测试卡基本资料管理:')}</dt>
		<dd>${eoms:a2u('新增：鼠标右键点击树图中的【测试卡基本资料管理】，选择【新增】，进入新增界面，填写相关内容后，
			点击【保存】按钮即保存完成操作。')}</dd>
		<dd>${eoms:a2u('查询：鼠标右键点击树图中的【测试卡基本资料管理】，选择【查询】，进入查询界面，输入相关查询
			条件后点击【查询】按钮。相应的事务记录列表被显示出来。')}</dd>
		<dt>${eoms:a2u('测试卡出入库管理:')}</dt>
		<dd>${eoms:a2u('测试卡出库：鼠标右键点击树图中的【测试卡出入库管理】，选择【测试卡出库】，进入待出库测试卡信息列表，选择后，
			点击【出库】按钮进入出库操作界面，输入相关内容后点击【保存】按钮，完成出库操作。')}</dd>
		<dd>${eoms:a2u('测试卡入库：鼠标右键点击树图中的【测试卡出入库管理】，选择【测试卡入库】，进入待入库测试卡信息列表，选择后，
			点击【入库】按钮进入入库操作界面，输入相关内容后点击【保存】按钮，完成入库操作。')}</dd>
		<dd>${eoms:a2u('查询：鼠标右键点击树图中的【测试卡出入库管理】，选择【出入库记录查询】，进入查询界面，输入相关查询
			条件后点击【查询】按钮。相应的出入库记录列表被显示出来。')}</dd>
		<dt>${eoms:a2u('测试卡续借:')}</dt>
		<dd>${eoms:a2u('测试卡续借：鼠标右键点击树图中的【测试卡续借】，选择【测试卡续借】，进入可续借测试卡信息列表，选择后，
			点击【续借】按钮进入续借操作界面，输入相关内容后点击【保存】按钮，完成续借操作。')}</dd>
		<dd>${eoms:a2u('测试卡续借记录查询：鼠标右键点击树图中的【测试卡续借】，选择【测试卡续借记录查询】，进入查询界面，输入相关查询
			条件后点击【查询】按钮。相应的续借记录列表被显示出来。')}</dd>
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