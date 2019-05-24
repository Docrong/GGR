<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.tag.imp.SelectDynamicTag,
                 com.boco.eoms.mobilewap.tag.vo.SelectOptionVO" %>
<%@ page autoFlush="true" %>
<%
SelectDynamicTag tag = (SelectDynamicTag)request.getAttribute("selectDynamic");
SelectOptionVO soVO = tag.getOptionList();
String wapNodeId=tag.getCardId().substring(tag.getCardId().indexOf("_")+1,tag.getCardId().length());
String app = request.getContextPath(); 
%>
 <script language="javascript">
/**
 * 提交check
 */
function checkInput(){
   
   var nameValue = form1.name.value;
   var valueV = form1.value.value;
   var textV = form1.text.value;
// 下拉框名称不能为空
   if(nameValue == null || nameValue.length==0) {
      alert("下拉框名称不能为空!");
      document.form1.name.focus();
      return false;
   }
   // Option值不能为空
   if(valueV == null || valueV.length==0) {
      alert("Option值不能为空!");
      document.form1.value.focus();
      return false;
   }
   // 显示内容不能为空
   if(textV == null || textV.length==0) {
      alert("显示内容不能为空!");
      document.form1.text.focus();
      return false;
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
  <input name="tagType" type="hidden" value="selectdynamic"	>
  <input name="tagKey" type="hidden" value="<%=request.getParameter("tagKey") %>">
  <input name="cardId" type="hidden" value="<%=request.getParameter("cardId") %>">
  <input name="class_str" type="hidden" value="com.boco.eoms.mobilewap.tag.imp.SelectDynamicTag">

<table width="100%">
	 <tr>
	   <td class="title">修改动态下拉框信息</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
    <tr>
		<td class="note" colspan="2">
		注：
		<li>在跳转页面ID中如果要传递参数需在ID后加上问号（?）后手工添加</li>
		<li>在添加“option值”与“显示内容”时选择参数其名称必须含有List，且必须同为一List，否则系统会出错</li>
		</td>
	</tr>
    <tr>
		<th>下拉框名称</th>
		<td>
			<input id="name" name="name" type="text" value="<%=tag.getName() %>"  size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
    <tr>
		<th>是否多选</th>
		<td>
			<select name="multiple">
			  <option value="false" <%if(tag.getMultiple()==false)out.println("selected"); %>>单选</option>
			  <option value="true"<%if(tag.getMultiple()==true)out.println("selected"); %>>多选</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>option值</th>
		<td>
           <input id="value" name="value" type="text" value="<%=soVO.getVaule() %>"  size="15" ><font color="#FF0000">*</font>
		   <input type="button" value="选择循环参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList2Value.jsp?wapNodeId=<%=wapNodeId %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
	</tr>
	<tr>
		<th>显示内容</th>
		<td>
		   <input id="text" name="text" type="text" value="<%=soVO.getText() %>"  size="15" ><font color="#FF0000">*</font>
		   <input type="button" value="选择循环参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList2Text.jsp?wapNodeId=<%=wapNodeId %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
	</tr>
	<tr>
		<th>跳转页面ID</th>
		<td>
			<input id="hrefCardId" name="hrefCardId" type="text" value="<%=soVO.getOnpick() %>"  size="15" >
			<input type="button" value="选择提交页面" onclick="window.open('<%=app %>/webtool/common/getCardList.jsp?wapNodeId=<%=wapNodeId %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>		
	</tr>
</table>
<table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit" value="提交"></td></tr>
</table>
</form>
</body>
</html>
