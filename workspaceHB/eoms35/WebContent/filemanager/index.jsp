<%
    String listType="SeparateReportList";      //列出需要上传处理的报表
    if(request.getParameter("listType")!=null)   //列出汇总报表  CollectReportList
        listType=(String)request.getParameter("listType");  
   
%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
  <script type="text/javascript">
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/filemanager/topic/InfoTypeMenuAction.do?pageType=topic&listType=<%=listType%>",
    treeRootId:'-1',
    treeRootText:"代维报告",
	pageFrameId:'formPanel-page',
	onClick:{url:"${app}/filemanager/ReportMgrAction.do?act=<%=listType%>&topicId=",text:"详细信息"},
	ctxMenu:[
		//{id:'newnode', text:'newNode',cls:'new-mi',url:'${app}/filemanager/ReportMgrAction.do?act=<%=listType%>&topicId='},
		//{id:'editnode', text:'editList',cls:'edit-mi',url:'<c:url value="/filemanager/ReportMgrAction.do?method=xsave&act=SeparateReportList&topicId=" />'},
    	//{id:'newnode', text:'显示列表',cls:'list-mi',url:'${app}/filemanager/ReportMgrAction.do?act=<%=listType%>&topicId=', rootCanShow:true}
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
	<h1>数据报表上报</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>数据报表的查看</dt>
		<dd>单击右键选择显示列表，右侧会显示出所派发的数据列表。</dd>
	    <dt>数据报表的上报</dt>
		<dd>点击报表专题，点击需要处理的报表任务，填写联系方式，回复说明，并将本部门按模板填写好的报表以附件形式上传，保存内容。</dd>
		<dd>所上传的文件格式必须与派发报表中文件格式一致。否则派发人不能正常合并报表。</dd>
		<dt>修改已上传报表</dt>
		<dd>进入相应的报表任务，用左键单击报表，系统弹出提示框，点“确定”删除旧的报表。</dd>
		<dt>下载报表模板</dt>
		<dd>点击“模板文件”的附件，将文件保存到本地，然后选择存放目录，点保存后，模板文件被下载到本地。</dd>
		<dt>审核数据报表</dt>
		<dd>点击代维报告树形菜单中的报表审核菜单，右键将列出所有已经派发的报表审核任务，进入详细列表后进行相应任务一级审核，可以“审核通过”或“审核驳回”。</dd>
		<dt>汇总数据报表</dt>
		<dd>点击代维报告树形菜单中的报表汇总菜单，右键将列出所有已经派发的报表汇总任务，进入详细列表后进行相应任务二级审核，可以“审核通过”或“审核驳回”。</dd>
		<dd>进入详细列表后点击合并报表，选择报表后合并成需要的报表。</dd>
		<dd>生成汇总报表之后，在新页面中可以选择下载。</dd>
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
