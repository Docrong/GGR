<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapHrefTag" %>
<%@ page autoFlush="true" %>
<%
WapHrefTag tag = (WapHrefTag)request.getAttribute("href");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String tagKey = (String)request.getAttribute("tagKey");

String app = request.getContextPath(); 
%>
<script language="javascript">
/**
 * �ύcheck
 */
function checkInput(){
   // �ύ��ַ����Ϊ��
   var addressValue = form1.href.value;
   if(addressValue == null || addressValue.length==0) {
      alert("�ύ��ַ����Ϊ��!");
      document.form1.href.focus();
      return false;
   }
   var textValue = form1.text.value;
   if(textValue == null || textValue.length==0) {
      alert("'��������'����Ϊ��!");
      document.form1.text.focus();
      return false;
   }
}

</script>
<html>
<head>
<title>����ԱWAP���ù���</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css" />
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="modify"	>
  <input name="tagType" type="hidden" value="href"	>
  
  <input name="tagKey" type="hidden" value="<%=tagKey %>">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapHrefTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="orderId" type="hidden" value="<%=tag.getOrderId() %>">
  
<table width="100%">
  <tr>
    <td class="title">�޸��ⲿ����</td>
    <td align="right"></td>
  </tr>
</table>
<table class="form_table">
    <tr>
		<th>�����ӵ�ַ��</th>
		<td>
			<input id="href" name="href" type="text" value="<%=tag.getHref() %>"  size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
		<th>��������</th>
		<td>
			<input id="text0" name="text" type="text" value="<%=tag.getText().trim() %>"  size="20" ><font color="#FF0000">*</font>
			<!-- 
			<input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=0','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			 -->
		</td>
	</tr>
</table>
    <table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td></tr>
  </table>
</form>
</body>
</html>
