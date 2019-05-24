<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ page autoFlush="true"%>
<%
String app = request.getContextPath(); 
String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");
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
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css" />
</head>
<body>
<form name="form1" action="<%=app%>/webtool/tag"
	onSubmit="return checkInput()">
	<input name="act" type="hidden" value="save"	>
  <input name="tagType" type="hidden" value="<%=tagType %>">
  
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapOneventTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">	
<table width="100%">
	<tr>
		<td class="title">增加一个定时跳转</td>
		<td align="right"></td>
	</tr>
</table>
<table class="form_table">
	<tr>
		<td colspan="2" class="note"><li>注：1单位等于1/10秒</li></td>
	</tr>
	<tr>
		<th>定时时间：
		</td>
		<td><input id="timervalue" name="value" type="text" value=""
			size="20">1/10秒<font color="#FF0000">*</font></td>
	</tr>
	<tr>
		<th>跳转页面ID
		</td>
		<td><input id="hrefCardId" name="href" type="text" value=""
			size="20"><font color="#FF0000">*</font> 
			<!-- 
			<input
			type="button" value="选择提交页面"
			onclick="window.open('<%=app%>/webtool/common/getCardList.jsp?wapNodeId=<%=wapNodeId%>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">
			 -->
		</td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td align="right"><input type="submit" class="inputsubmit" value="提交"></td>
	</tr>
</table>
</form>
</body>
</html>
