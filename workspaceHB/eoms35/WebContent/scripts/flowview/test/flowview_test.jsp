<%@ page contentType="text/html; charset=UTF-8" %>
<%
String webapp = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<title>流程图浏览</title>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<script src="<%=webapp%>/scripts/flowview/FlowViewWorkSpace.js" language="javascript"></script>
<script src="<%=webapp%>/scripts/flowview/excanvas-compressed.js" language="javascript"></script>
<script type="text/javascript">
FlowViewWorkSpace.build("<%=webapp%>");

var currentStepIds = [];
var historyStepIds = [];
var steps = new Array();
var actions = new Array();


actions.push({id:5,view:"送部门审核",from:0,to:1});
actions.push({id:6,view:"保存",from:0,to:3});
steps.push({id:0,name:"新建"});
steps.push({id:1,name:"部门审核"});
  actions.push({id:11,view:"部门驳回",from:1,to:3});
  actions.push({id:13,view:"部门通过",from:1,to:9});
steps.push({id:2,name:"领导审核"});
  actions.push({id:18,view:"审核通过",from:2,to:16});
  actions.push({id:68,view:"审核驳回",from:9,to:2});
steps.push({id:3,name:"草稿"});
  actions.push({id:8,view:"送部门审核",from:3,to:1});
  actions.push({id:74,view:"修改",from:3,to:3});
steps.push({id:4,name:"任务处理中"});
  actions.push({id:51,view:"重派",from:4,to:4});
  actions.push({id:53,view:"归档",from:4,to:29});
steps.push({id:9,name:"部门通过"});
  actions.push({id:15,view:"送领导审核",from:2,to:9});
  actions.push({id:38,view:"下发任务",from:9,to:4});
  actions.push({id:60,view:"送其他部门会签",from:9,to:57});
steps.push({id:16,name:"领导审核通过"});
  actions.push({id:20,view:"下发任务",from:16,to:4});
steps.push({id:29,name:"归档"});
steps.push({id:57,name:"其他部门会签"});
  actions.push({id:62,view:"会签驳回",from:57,to:9});
  actions.push({id:64,view:"会签通过",from:57,to:58});
steps.push({id:58,name:"会签通过"});
  actions.push({id:72,view:"下发任务",from:58,to:4});
  actions.push({id:77,view:"送领导审核",from:58,to:75});
steps.push({id:75,name:"领导审核"});
  actions.push({id:79,view:"驳回",from:75,to:58});
  actions.push({id:81,view:"审核通过",from:75,to:16});

currentStepIds[0] = 1;
historyStepIds[0] = 0;
historyStepIds[0] = 3;
historyStepIds[1] = 1;

<%--
function showDetail(stepId){
   window.open("../../Taskleadsheet/detailNode.do?stepId=" + stepId + "&processId=<%=id%>&taskId=<%=taskId%>&sheetKey=<%=sheetKey%>");
}
--%>

function init(){
  var flowview = new FlowView($("workflowCanvas"));
  flowview.getFlow("test.lyt.xml");
  //flowview.getFlow("<%=webapp%>/wfworksheet/taskleadsheet/historystep/taskleadsheet.lyt.xml");
  //flowview.getFlow("<%=webapp%>/wfworksheet/taskleadsheet/historystep/taskleadsheet_subqs_sub.lyt.xml");
}
</script>
</head>

<body onload="init()"style="overflow:scroll">
<div id="outer">
  <div id="head">
	  <div id="title">实例流程</div>
		<div id="note">
		<img src="../img/icon_history.gif" width="12" height="12" border="0" alt="" align="absMiddle"> 已完成步骤 
		<img src="../img/icon_curstep.gif" width="12" height="12" border="0" alt="" align="absMiddle"> 当前步骤</div>
	</div>
   
  <div id="workflowCanvas">
     <canvas id="canvas" width="500" height="500" ></canvas>
  </div>
  <div id="foot"></div>
</div>

<div id="tip"></div>
</body>
</html>
