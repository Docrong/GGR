<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<%
 String sheetType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("sheetType"));
 String sheetId   = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("sheetId"));
%>


<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<META HTTP-EQUIV="MSThemeCompatible" CONTENT="Yes">
<title>流程图</title>

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
</style>

<script src="<%=request.getContextPath()%>/scripts/flowview/flowchar.js" language="javascript"></script>

<script type="text/javascript">
  function downRect(){
	  var obj = event.srcElement;
	  if(obj.tagName == "RoundRect"){
	  	  obj.shadow.color = "#b3b3b3";
	  }
	  else {
	  	  obj.parentNode.shadow.color = "#b3b3b3";
	  }      
  }

  function upRect(){
	  var obj = event.srcElement;
	  if(obj.tagName == "RoundRect"){
	  	  obj.shadow.color = "#000000";
	  }
	  else {
	  	  obj.parentNode.shadow.color = "#000000";
	  }
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

  function showInfo(v_id, v_linkId, v_parId, v_parLinkId){
	 	  window.open('.${module}.do?method=getLinkOperatePage&aiid='+ v_id,null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')
   }
</script>
</head>

<body>

<div id="div" style="WIDTH: 1000px; HEIGHT: 1000px">
<table class="table_show" cellspacing="1">
	<tr class="tr_show">
		<td>${eoms:a2u('图 例:')}</td>
	  <td><input type=button value="${eoms:a2u('复原')}" onclick="zoomout()"/>
	  	  <input type=button value="${eoms:a2u('缩小')}" onclick="zoomin()"/></td>
	</tr>
	<tr>	
	  <td bgColor=#3366cc width="8%">&nbsp;</td>
	  <td nowrap="nowrap">${eoms:a2u('此操作已完成 :')}</td>
	</tr>
	<tr>
	  <td bgColor=#ff0033 width="8%">&nbsp;</td>
	  <td nowrap="nowrap">${eoms:a2u('此操作未完成或在等待下级处理 :')}</td>
	</tr>
</table>	
</div>


<script type="text/javascript">
 <% 
    String[] xmlFile = (String[]) request.getAttribute("vml");
    int fileLength = xmlFile.length;
    for (int i=0; i<fileLength; i++){
 %>

  var Ins = new Xml2Vml("<%=xmlFile[i]%>");
  Ins.ParseXml();
  Ins.RenderVml();
  $("div").appendChild(Ins.VmlRoot);

 <% }%>

</script>

</body>
</html>