<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="com.boco.eoms.mobilewap.base.tag.imp.WapInputTag" %>
<%
WapInputTag tag = (WapInputTag)request.getAttribute("input");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String tagKey = (String)request.getAttribute("tagKey");

String app = request.getContextPath(); 
%>
<script language="javascript">
function checkInput() {
    var imgAlt = document.form1.name.value;
    if(imgAlt.length==0) {
        alert("��������Ʋ���Ϊ��,������!");
        document.all.name.focus();
        return false;
    }
}
</script>
<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="modify">
  <input name="tagType" type="hidden" value="input"	>
  
  <input name="tagKey" type="hidden" value="<%=tagKey %>">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapInputTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="orderId" type="hidden" value="<%=tag.getOrderId() %>">
<table width="100%">
	 <tr>
	   <td class="title">�޸��������Ϣ</td>
	   <td align="right"></td>
	 </tr>
</table>
  
  <table class="form_table">
	<tr>
	  <th>���������</th>
	  <td><input name="name" value="<%=tag.getName() %>">
	  <font color="#FF0000">*</font></td>
	</tr>
	<tr>
	  <th>���������</th>
	  <td>
	    <select name="type"/>
	      <option value="text" <%if(tag.getType().equals("text"))out.println("selected");%>>text</option>
	      <option value="password" <%if(tag.getType().equals("password"))out.println("selected");%>>password</option>
	    </select>
	  </td>
	</tr>
	<tr>
	  <th>Ĭ��ֵ</th>
	  <td><input name="value" value="<%=tag.getValue() %>"></td>
	</tr>
	<tr>
	  <th>�����ʽҪ��</th>
	  <td><input name="format" value="<%if(tag.getFormat()==null || tag.getFormat().length()==0 || tag.getFormat().trim().equals("null") ){out.println("");}else{out.println(tag.getFormat());} %>"></td>
	</tr>
	<tr>
	  <th>�Ƿ�����Ϊ��</th>
	  <td>
	    <select name="emptyok"/>
	      <option value="true" <%if((tag.getEmptyok()+"").equals("true"))out.println("selected");%>>����</option>
	      <option value="false" <%if((tag.getEmptyok()+"").equals("false"))out.println("selected");%>>������</option>
	    </select>
	  </td>
	</tr>
	<tr>
	  <th>������С</th>
	  <td><input name="size" value="<%=(Integer.parseInt(tag.getSize())==0)?"":tag.getSize() %>"></td>
	</tr>
	<tr>
	  <th>��������ַ���</th>
	  <td><input name="maxlength" value="<%=(Integer.parseInt(tag.getMaxlength())==0)?"":tag.getMaxlength()%>"></td>
	</tr>
  </table>
  <table width="100%">
    <tr>
       <td align="right">
	      <input type="submit" class="inputsubmit" value="�ύ">
	   </td>
    </tr>
  </table>
  </form>
</body>
</html>
	
