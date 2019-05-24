<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 String sheetType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("sheetType"));
 String sheetId   = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("sheetId"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
  <title>流程图浏览</title>
  <meta http-equiv="Cache-Control" content="no-store"/>
  <meta http-equiv="Pragma" content="no-cache"/>
  <meta http-equiv="Expires" content="0"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <script type="text/javascript" charset="utf-8" src="${app}/scripts/local/zh_CN.js"></script>  
  <script type="text/javascript" charset="utf-8" src="${app}/scripts/base/eoms.js"></script>
  <script type="text/javascript">eoms.appPath = "${app}";</script>
  <link rel="stylesheet" type="text/css" href="${app}/styles/default/theme.css" />
  <!--[if IE]><link rel="stylesheet" type="text/css" href="${app}/styles/common/ie.css" /><![endif]-->

<style type="text/css">
    v\:*{
        behavior:url(#default#VML);
    }
    v\:RoundRect{
        text-align:center;
        position:relative;
        cursor:pointer;
    }
    v\:TextBox{
        vertical-align:middle;
        font-size:13px;
    }
.fv-tips{
  width:230px;
  color:#007236;
  background: E2FEDE;
  border: #00a651 1px solid;
  position: absolute;
  z-index: 200;
  margin: 5px;
  padding: 5px;
  Filter: Alpha(Opacity=90);
  word-wrap : normal;
  word-break:keep-all;
}
</style>

<script src="<%=request.getContextPath()%>/scripts/flowview/wholeflowchar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/scripts/util/Toolkit.js" type="text/javascript"></script>

<script type="text/javascript">
  function downRect(){
	  var obj = event.srcElement;
	  if(obj.tagName == "RoundRect"){
	  	  obj.shadow.color = "#b3b3b3";
	  	 
	  }
	  else {
	  	  obj.parentNode.shadow.color = "#b3b3b3";
	  }  
	  hideTips();    
  }

  function upRect(displayMsg){
	var event = window.event;
	var obj = event.srcElement;
	if(obj.tagName != "RoundRect"){
		obj = obj.parentNode;
	}
	  
	obj.shadow.color = "#000000";
	var msg = obj.getAttribute("dispalyMsg");
	var msg = displayMsg;

	var p = Toolkit.getXY(obj);
      
	if ((p[0] + 100 + 230) > document.clientWidth) {
		p[0] -= 235;
	} else {
		p[0] += 105;
	}
	p[1] -= 2;
	showTips(msg,p);
  }

  function zoomout(){
      for(var m=0; m<document.all.tags("TextBox").length; m++){
    	  var size = document.all.tags("TextBox").item(m).style.fontSize;
          document.all.tags("TextBox").item(m).style.fontSize="9pt";
      }

      for(var j=0; j<document.all.tags("group").length; j++) {
          var x = document.all.tags("group").item(j).coordsize.x;
          var y = document.all.tags("group").item(j).coordsize.y;
          document.all.tags("group").item(j).coordsize="1000,1000";
      }
  }

  var font = 5;
  function zoomin(){
      for(var m=0; m<document.all.tags("TextBox").length; m++){
    	  var size = document.all.tags("TextBox").item(m).style.fontSize;
          document.all.tags("TextBox").item(m).style.fontSize=(font/0.8)+"pt";
      }

      for(var j=0; j<document.all.tags("group").length; j++) {
          var x = document.all.tags("group").item(j).coordsize.x;
          var y = document.all.tags("group").item(j).coordsize.y;
          document.all.tags("group").item(j).coordsize=x/0.8+","+y/0.8;
      }
  }

  function showInfo(v_id, v_linkId, v_parId, v_parLinkId,v_mainId){
	 	  window.open('.${module}.do?method=getAllWorkflowStepInfoPage&mainId='+ v_mainId + '&aiid='+ v_id,null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')
   }
</script>
</head>

<body>

<div id="div" style="WIDTH: 1000px; HEIGHT: 1000px">
<table class="table_show" cellspacing="1">
	<tr class="tr_show">
		<td>图 例 :</td>
	  <td><input type=button value="复原" onclick="zoomout()"/>
	  	  <input type=button value="缩小" onclick="zoomin()"/></td>
	</tr>
	<tr>	
	  <td bgColor=#3366cc width="8%">&nbsp;</td>
	  <td nowrap="nowrap">此操作已完成 :</td>
	</tr>
	<tr>
	  <td bgColor=#ff0033 width="8%">&nbsp;</td>
	  <td nowrap="nowrap">此操作未完成或在等待下级处理 :</td>
	</tr>
</table>	
</div>


<script type="text/javascript">
 <% 
	Object obj = (Object)request.getAttribute("vml");
	if (obj != null) {
    String[] xmlFile = (String[])obj;
    int fileLength = xmlFile.length;
    for (int i=0; i<fileLength; i++){
 %>

  var Ins = new Xml2Vml("<%=xmlFile[i]%>");
  Ins.ParseXml();
  Ins.RenderVml();
  $("div").appendChild(Ins.VmlRoot);

 <% } } else {%>
  alert("该工单正处于草稿状态或新建送审状态！")
 <%}%>

</script>
<div id="tip" class="fv-tips" style="display:none"></div>
</body>
</html>