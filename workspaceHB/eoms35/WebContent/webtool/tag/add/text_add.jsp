<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%
String app = request.getContextPath(); 

String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");

%>
 <script language="javascript">
/**
 * �ύcheck
 */
function checkInput(){
   var textV = form1.text.value;
   // �ı����ݲ���Ϊ��
   if(textV == null || textV.length==0) {
      alert("�ı����ݲ���Ϊ��!");
      document.form1.text.focus();
      return false;
   }
}
</script>
<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css" />
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="save"	>
  
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="tagType" type="hidden" value="<%=tagType %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapTextTag">
  
<table width="100%">
  <tr>
    <td class="title">����һ���ı�</td>
    <td align="right"></td>
  </tr>
</table>
  <table class="form_table">
	<tr>
	  <th>�������ı�����</td>
	  <td>
	    <textarea rows="4" id="text0" name="text" type="text" value="" ></textarea>
	    <!-- 
	    <input type="button" value="ѡ��" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=0','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
	     -->
	    <font color="#FF0000">*</font>
	  </td>
	</tr>	
  </table>
  <table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td></tr>
  </table>
</form>
</body>
</html>
	
