<%@ page contentType="text/html; charset=GB2312" %>
<%
String webapp = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<title>流程图浏览</title>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<script src="<%=webapp%>/scripts/flowview/FlowViewWorkSpace.js" language="javascript"></script>
<script type="text/javascript">
FlowViewWorkSpace.build("<%=webapp%>");
var currentStepIds = ${currentList};
var historyStepIds = ${historyList};

var steps = ${stepList};
var actions = ${joinLineList};


function init(){
 	var flowview = new FlowView($("workflowCanvas"));
	flowview.getFlow("<%=webapp%>/templates/workflowchar/${workflowXML}");

}

</script>
</head>

<body onload="init()">
  <div id="head">
	  <div id="title">${workFlowName}</div>
		<div id="note">
		  <img src="<%=webapp%>/scripts/flowview/img/icon_history.gif" width="12" height="12" border="0" alt="" align="absMiddle"> 已完成步骤 
		  <img src="<%=webapp%>/scripts/flowview/img/icon_curstep.gif" width="12" height="12" border="0" alt="" align="absMiddle"> 当前步骤
		  <a href="#" onclick="window.open(window.location.href)">
		  <img src="<%=webapp%>/images/icons/expand.gif" width="16" height="16" border="0" alt="" align="absMiddle"> 在新窗口中打开
		  </a>
		</div>
	  </div>
	<div id="workflowCanvas"></div>
<div id="tip" class="fv-tips" style="display:none"></div>
</body>
</html>
