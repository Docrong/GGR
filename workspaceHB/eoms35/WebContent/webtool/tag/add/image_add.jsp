<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page autoFlush="true" %>
<%
String app = request.getContextPath(); 
String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");
 %>
<html>
<head>
<title>管理员WAP配置工具</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
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
	Para.innerHTML ='<table class="form_table"> '+
                	'<tr><th>请选择图片：</th>'+
	                '<td><input name="src" type="file" align="left"  onkeydown="javascript:fileClick();">'+
	                '<font color="#FF0000">*</font></td></tr></table>';
}	               
else{
   Para.innerHTML = '<input name="src" type="hidden" value="" >';
}              
}
</script>


</head>
<body>
<form action="<%=app %>/webtool/tag" name="form1" onSubmit="return checkInput();">
  <input name="act" type="hidden" value="save">
  <input name="tagType" type="hidden" value="<%=tagType %>"	>
  
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>" >
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapImageTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>" >
<table width="100%">
	 <tr>
	   <td class="title">增加一个图片</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
    <tr>
      <td colspan="2" class="note">
      说明： <br>
        <li>图片的宽度和高度不填则取默认值</li>
	    <li>图片的高度请不要超过80px，图片的宽度请不要超过90px</li>
        <li>图片名称只允许为数字、字母和下划线</li>
    </tr>
    <tr>
      <th>图片名称：</td>
      <td>
          <input id="text0" name="alt" type="text"  size="30" maxlength="50" value=""><font color="#FF0000">*</font>
          <!-- 
        <input type="button" value="选择参数变量" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=0','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
         -->
        </div></td>
    </tr>
    <tr >
      <th>图片位置：</td>
      <td>
	    <select name="align">
	     <option value="middle">中间 </option>
		   <option value="top">顶部</option>
		   <option value="bottom"> 底部</option>
	    </select>
	  </td>
    </tr>
	 <tr >
      <th>图片宽度：</td>
      <td>
	    <input name="width" type="text"  size="15" maxlength="3" value="">
	    &nbsp; px	
	  </td>
    </tr>
	<tr >
      <th>图片高度：</td>
      <td>
	     <input name="height" size="15" type="text" maxlength="3" value="" >
		&nbsp; px	
	  </td>
    </tr>
    <tr>
      <th>图片类型：</td>
      <td>
	    <select name="type" onchange="javascript:addUpload(form1.type.value)">
	       <option value="auto">正常本地上传 </option>
		   <option value="cake">网管数据饼图</option>
		   <option value="line">网管数据线图</option>
		   <option value="pole">网管数据柱图</option>
	    </select>
	  </td>
    </tr>
    </table>
    <span id="Para">
    <table class="form_table" >
    <tr>   
      <th>请选择图片：</td>
      <td>
	    <input name="src" type="file" align="left"  onkeydown="javascript:fileClick();">
	  <font color="#FF0000">*</font>
	  </td>
    </tr>
</table>
</span>

<table width="100%">
 <tr>
   <td align="right"><input type="submit" class="inputsubmit" value="提交"/></td>
 </tr>
</table>
</form>
</body>
</html>
