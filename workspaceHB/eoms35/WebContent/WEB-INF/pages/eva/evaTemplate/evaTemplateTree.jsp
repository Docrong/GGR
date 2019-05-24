<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
<script type="text/javascript">
  eoms.loadCSS(eoms.appPath+"/styles/eva/style.css");
  var config = {
    /**************
 	* Tree Configs
 	**************/
	treeGetNodeUrl:"${app}/eva/evaTrees.do?method=getTemplateNodes",
    treeRootId:'<%=EvaConstants.TREE_ROOT_ID%>',
	treeRootText:'考核模板',
	pageFrameId:'formPanel-page',
	ctxMenu:[
		{id:'newnode', text:'新增考核模板分类',cls:'new-mi',url:'${app}/eva/evaTemplateTypes.do?method=newTemplateType&nodeId='},
		{id:'editTemplateType', text:'修改考核模板分类',cls:'edit-mi',url:'${app}/eva/evaTemplateTypes.do?method=editTemplateType&nodeId='},
		{id:'newTemplate', text:'新增考核模板',cls:'new-mi',url:'${app}/eva/evaTemplates.do?method=newTemplate&nodeId='},
		{id:'newKpi', text:'新增考核指标',cls:'new-mi',url:'${app}/eva/evaKpis.do?method=listNextLevelKpi&parentNodeId='},
		{id:'editTemplate', text:'修改/激活考核模板',cls:'edit-mi',url:'${app}/eva/evaTemplates.do?method=editTemplate&nodeId='},
		{id:'editKpi', text:'修改考核指标',cls:'edit-mi',url:'${app}/eva/evaKpis.do?method=editKpi&parentNodeId='},
		{id:'delTemplate', isDelete:true, text:'删除考核模板',cls:'remove-mi',url:'${app}/eva/evaTemplates.do?method=removeTemplate&nodeId='},
		{id:'delKpi', isDelete:true, text:'删除考核指标',cls:'remove-mi',url:'${app}/eva/evaKpis.do?method=delKpiFromTree&nodeId='}
	],//end of ctxMenu 
	/************************
 	* Custom onLoad Functions
 	*************************/
	onLoadFunctions:function(){
	}
  }; // end of config
  Ext.onReady(AppFrameTree.init, AppFrameTree);
</script>
<div id="headerPanel" class="x-layout-inactive-content">
	<h1>考核模板管理</h1>
</div>
<div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
	<dl>
		<dt>指标模板管理</dt>
		<dd>提供应用于合同的服务质量考核模型的增、删、查、改功能。</dd>
		<dd>考核模型是对代维公司进行考核的依据，主要对KPI进行考核，以树形结构展示，增加用户的可操作性。</dd>
		<dd>内容包括考核模板分类、考核模板、考核指标、任务激活等模块。</dd>
		<dd>按照树节点的层次关系从上到下分为考核模板分类、考核模板、考核指标，其中指标支持无限级。</dd>
		<dd>只有激活的考核模板才能进行考核执行。</dd>
		<dt>考核执行</dt>
		<dd>基站考核数据手工填报。根据生成的考核任务用户进行手工填写，完成考核对象的评分、增扣分原因、备注填写。包括考核执行和考核结果查询模块。</dd>
		<dt>考核报表</dt>
		<dd>对考核执行的评估结果进行不同形式的统计报表展示，包括“单一月份、单一厂商”、“不同月份、同一厂商”、“同一月份、不同厂商”3张报表的统计。</dd>	
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