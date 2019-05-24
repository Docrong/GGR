<%@ page contentType="text/html; charset=UTF-8" %>
<%
String webapp = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<title>流程图浏览</title>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<script src="<%=webapp%>/scripts/flowview/FlowViewWorkSpace.js" language="javascript"></script>
<script src="<%=webapp%>/scripts/flowview/excanvas-compressed.js" language="javascript"></script>
<script type="text/javascript">
FlowViewWorkSpace.build("<%=webapp%>");

var currentStepIds = [];
var historyStepIds = [];
var steps = new Array();
var actions = new Array();

//步骤流程
//第一步：创建工单 
steps.push({id:11,name:"创建故障工单", url:"<%=webapp%>/html/common/list.htm", tips:"处理人:故障建单人<br/>处理时间：2008年4月12日 14:03:34"});//工单开始 创建故障工单
//最后一步：工单结束
steps.push({id:100,name:"工单归档", tips:"处理人:故障建单人<br/>处理时间：2008年4月12日 14:03:34"});//工单结束 工单归档

steps.push({id:12,name:"处理故障",url:"<%=webapp%>/html/common/list.htm", tips:"处理人:故障建单人<br/>处理时间：2008年4月12日 14:03:34"});//处理故障

actions.push({id:61,text:"派发",from:11,to:12,complete:true});//连接创建故障工单到处理故障
actions.push({id:62,text:"处理完成",from:12,to:100});//连接处理故障到归档

steps.push({id:13,name:"处理故障", tips:"处理人:故障建单人<br/>处理时间：2008年4月12日 14:03:34"});//处理故障
actions.push({id:63,text:"移交",from:12,to:13,complete:true});//连接创建故障工单到处理故障
actions.push({id:64,text:"处理完成",from:13,to:100});//连接处理故障到归档

steps.push({id:14,name:"处理故障"});//处理故障
actions.push({id:65,text:"移交",from:13,to:14});//连接创建故障工单到处理故障
actions.push({id:66,text:"处理完成",from:14,to:100});//连接处理故障到归档

steps.push({id:15,name:"审批故障延迟"});//审批故障延迟

actions.push({id:67,text:"延期申请",from:12,to:15});//连接处理故障到审批故障延迟
actions.push({id:68,text:"是否通过",from:15,to:12});//连接审批故障延迟到处理故障

actions.push({id:69,text:"延期申请",from:13,to:15});//连接处理故障到审批故障延迟
actions.push({id:610,text:"是否通过",from:15,to:13});//连接审批故障延迟到处理故障

actions.push({id:611,text:"延期申请",from:14,to:15});//连接处理故障到审批故障延迟
actions.push({id:612,text:"是否通过",from:15,to:14});//连接审批故障延迟到处理故障


currentStepIds[0] = 13;
historyStepIds[0] = 11;
historyStepIds[1] = 12;
historyStepIds[2] = 13;

function init(){
  var flowview = new FlowView($("workflowCanvas"));
  flowview.getFlow("commfaultFlow.xml");
}
</script>
</head>

<body onload="init()">
  <div id="head">
	  <div id="title">紧急故障流程</div>
		<div id="note">
		<img src="../img/icon_history.gif" width="12" height="12" border="0" alt="" align="absMiddle"> 已完成步骤 
		<img src="../img/icon_curstep.gif" width="12" height="12" border="0" alt="" align="absMiddle"> 当前步骤
		<a href="#" onclick="window.open(window.location.href)">
		<img src="<%=webapp%>/images/icons/expand.gif" width="16" height="16" border="0" alt="" align="absMiddle"> 在新窗口中打开
		</a>
		</div>
	</div>
  <div id="workflowCanvas">
     <canvas id="canvas" width="1000" height="650"></canvas>
  </div>

<div id="tip" class="fv-tips" style="display:none"></div>
</body>
</html>