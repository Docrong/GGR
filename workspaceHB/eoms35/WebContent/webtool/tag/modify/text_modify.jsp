<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="com.boco.eoms.mobilewap.base.tag.imp.WapTextTag" %>
<%
WapTextTag tag = (WapTextTag)request.getAttribute("text");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String tagKey = (String)request.getAttribute("tagKey");

String app = request.getContextPath(); 
%>
 <script language="javascript">
/**
 * 提交check
 */
function checkInput(){
   var textV = form1.text.value;
   // 文本内容不能为空
   if(textV == null || textV.length==0) {
      alert("文本内容不能为空!");
      document.form1.text.focus();
      return false;
   }
}
</script>
<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()" method="post">
  <input name="act" type="hidden" value="modify">
  <input name="tagType" type="hidden" value="text"	>
  <input name="cardId" type="hidden" value="<%=wapCardId %>">

  <input name="tagKey" type="hidden" value="<%=tagKey %>">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapTextTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="orderId" type="hidden" value="<%=tag.getOrderId() %>">
  
<table width="100%">
	 <tr>
	   <td class="title">修改文本信息</td>
	   <td align="right"></td>
	 </tr>
</table>
  <table class="form_table">
	<tr>
	  <th>请输入文本内容</th>
	  <td>
	    <textarea rows="2" id="text0" name="text" type="text" value="" ><%=tag.getText().trim() %></textarea>
	    <!-- 
	    <input type="button" value="选择" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=0','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
	     -->
	    <font color="#FF0000">*</font>
	  </td>
	</tr>	
  </table>
  </table>
  <table width="100%">
    <tr>
       <td align="right">
	      <input type="submit" class="inputsubmit" value="提交">
	   </td>
    </tr>
  </table>
  </form>
</body>
</html>
	
