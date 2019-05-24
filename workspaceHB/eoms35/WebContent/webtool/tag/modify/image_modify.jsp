<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapImageTag" %>
<%@ page autoFlush="true" %>
<%
WapImageTag tag = (WapImageTag)request.getAttribute("image");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String tagKey = (String)request.getAttribute("tagKey");

String app = request.getContextPath(); 
%>
<script language="javascript">
/**
 * 提交check
 */
function checkInput() {
    var imgAlt = document.form1.alt.value;
    var typeV = document.form1.type.value;

    if(imgAlt.length==0) {
        alert("图片名称不能为空,请输入!");
        document.all.alt.focus();
        return false;
    }

    if(typeV=="auto") {
      if( document.form1.src.value==""){
        alert("请点击“浏览”选择图片!");
        document.all.src.focus();
        return false;
      }
    }
}

/**
 * 选择图片不能直接输入
 */
function fileClick(){
    alert('请点' + '"' + '浏览' + '"' + '按钮来选择图片!');
    document.all.Submit.focus();
}
function addUpload(value){
Para.innerHTML ="";
if(value=="auto"){
	Para.innerHTML ='<table width="100%" border="1" cellpadding="1" cellspacing="1"> '+
                	'<tr ><td width="40%" ><div align="right">现图片<img alt="没有上传图片" src="<%=request.getRealPath("/")+tag.getSrc()%>" width="<%=tag.getWidth()%>" height="<%=tag.getHeight() %>"/>请选择图片：</div></td>'+
	                '<td width="" align="left" > <div align="left">'+
	                '<input name="src" type="file" align="left"  onkeydown="javascript:fileClick();">'+
	                '<font color="#FF0000">*</font></div></td></tr></table>';
}	               
else{
   Para.innerHTML = '<input name="src" type="hidden" value="" >';
}              
}
</script>
<html>
<head>
<title>管理员WAP配置工具</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="modify"	>
  <input name="tagType" type="hidden" value="image"	>
  
  <input name="tagKey" type="hidden" value="<%=tagKey %>">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapImageTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="orderId" type="hidden" value="<%=tag.getOrderId() %>">
  
<table width="100%">
	 <tr>
	   <td class="title">修改图片信息</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
    <tr>
      <td class="note" colspan="2">
      说明<br>
        <li>图片的宽度和高度不填则取默认值</li>
	    <li>图片的高度请不要超过80pix，图片的宽度请不要超过90pix</li>
        <li>图片名称只允许为数字、字母和下划线</li>
    </th>
    <tr>
      <th>图片名称：</th>
      <td>
        <input id="text0" name="alt" type="text"  size="30" maxlength="50" value="<%=tag.getAlt()%>"><font color="#FF0000">*</font>
        <!-- 
        <input type="button" value="选择参数变量" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=0','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
         -->
      </td>
    </tr>
    <tr >
      <th>图片位置：</th>
      <td>
	    <select name="align">
	     <option value="middle" <%if(tag.getAlign().equals("middle"))out.println("select"); %>>中间 </option>
		   <option value="top" <%if(tag.getAlign().equals("top"))out.println("select"); %>>顶部</option>
		   <option value="bottom" <%if(tag.getAlign().equals("bottom"))out.println("select"); %>> 底部</option>
	    </select>
	  </th>
    </tr>
	 <tr>
      <th>图片宽度：</th>
      <td>
	    <input name="width" type="text"  size="15" maxlength="3" value="<%=tag.getWidth()%>">
	    &nbsp; px	
	  </td>
    </tr>
	<tr>
      <th>图片高度：</th>
      <td>
	     <input name="height" size="15" type="text" maxlength="3" value="<%=tag.getHeight() %>" >
		&nbsp; px	
	  </td>
    </tr>
    <tr >
      <th>图片类型：</th>
      <td>
	    <select name="type" onchange="javascript:addUpload(form1.type.value)">
	       <option value="auto" <%if(tag.getType().equals("auto"))out.println("selected"); %>>正常本地上传 </option>
		   <option value="cake" <%if(tag.getType().equals("cake"))out.println("selected"); %>>网管数据饼图</option>
		   <option value="line" <%if(tag.getType().equals("line"))out.println("selected"); %>> 网管数据线图</option>
		   <option value="pole" <%if(tag.getType().equals("pole"))out.println("selected"); %>> 网管数据柱图</option>
	    </select>
	  </td>
    </tr>
    <tr>
    	<th>图片路径：</th>
    	<td>
    		<input name="src" size="50" type="text" maxlength="50" value="<%=tag.getSrc() %>" >
    	</td>
    </tr>
</table>
<span id ="Para">
<%if(tag.getImageType().equals("auto")){ %>
    <table class="form_table"> 
      <tr>
        <th>现图片<img alt="没有上传图片" src="<%=request.getRealPath("/")+tag.getSrc()%>" width="<%=tag.getWidth()%>" height="<%=tag.getHeight() %>"/>
            <br/>请选择图片：
        </th>
	    <td>
	  <!-- <input name="src" type="file" align="left"  onkeydown="javascript:fileClick();"> 
	  <font color="#FF0000">*</font></td></tr>-->
	</table>
<%} %>	
</span>  
<table width="100%">  
    <tr >
	  <td  align="right"><input type="submit" class="inputsubmit" name="Submit" value="确定"></td>
	</tr>
</table>
</form>
</body>
</html>
