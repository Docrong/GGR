<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapOneventTag" %>
<%@ page autoFlush="true" %>
<%
WapOneventTag tag = (WapOneventTag)request.getAttribute("onevent");

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
   // 跳转页面ID不能为空
   var addressValue = form1.hrefCardId.value;
   var timervaluV = form1.timervalue.value;

   if(addressValue == null || addressValue.length==0) {
      alert("跳转页面ID不能为空!");
      document.form1.hrefCardId.focus();
      return false;
   }
      // 跳转时间必须为数字
   if(timervaluV==""||!timervaluV.match(/^[0-9]*$/gi)) {
      alert("提交地址必须为数字!");
      document.form1.timervalue.focus();
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
  <input name="act" type="hidden" value="modify">
  <input name="tagType" type="hidden" value="onevent">
  
  <input name="tagKey" type="hidden" value="<%=tagKey %>">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapOneventTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="orderId" type="hidden" value="<%=tag.getOrderId() %>">
<table width="100%">
	 <tr>
	   <td class="title">修改定时跳转信息</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
    <tr>
		<td colspan="2" class="note">注：1个单位为1/10秒</td>
	</tr>
    <tr>
		<th>定时时间：</th>
		<td>
			<input id="value" name="value" type="text" value="<%=tag.getValue() %>"  size="20" >1/10秒<font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
		<th>跳转页面ID</th>
		<td>
			<input id="href" name="href" type="text" value="<%=tag.getHref() %>"  size="20" ><font color="#FF0000">*</font>
			<!-- 
			<input type="button" value="选择提交页面" onclick="window.open('<%=app %>/webtool/common/getCardList.jsp?wapNodeId=<%=wapNodeId %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			 -->
		</td>
	</tr>
</table>
    <table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit" value="提交"></td></tr>
  </table>
</form>
</body>
</html>
