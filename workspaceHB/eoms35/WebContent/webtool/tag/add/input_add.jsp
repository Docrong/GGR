<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%
String app = request.getContextPath(); 

String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");
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
  <input name="act" type="hidden" value="save"	>
  <input name="tagType" type="hidden" value="<%=tagType %>"	>
  
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapInputTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <table width="100%">
	 <tr>
	   <td class="title">����һ�������</td>
	   <td align="right"></td>
	 </tr>
  </table>
  <table class="form_table">
	<tr>
	  <th>���������</td>
	  <td><input name="name" type="text" value=""/>
	  <font color="#FF0000">*</font></td>
	</tr>
	<tr>
	  <th>���������</td>
	  <td>
	    <select name="type"/>
	      <option value="text">text</option>
	      <option value="password">password</option>
	    </select>
	  </td>
	</tr>
	<tr>
	  <th>Ĭ��ֵ</td>
	  <td><input name="value" type="text" value=""/></td>
	</tr>
	<tr>
	  <th>�����ʽҪ��</td>
	  <td><input name="format" type="text" value=""/></td>
	</tr>
	<tr>
	  <th>�Ƿ�����Ϊ��</td>
	  <td>
	    <select name="emptyok"/>
	      <option value="true">����</option>
	      <option value="false">������</option>
	    </select>
	  </td>
	</tr>
	<tr>
	  <th>������С</td>
	  <td><input name="size" type="text" value=""/></td>
	</tr>
	<tr>
	  <th>��������ַ���</td>
	  <td><input name="maxlength" type="text" value=""/></td>
	</tr>
  </table>
  <table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td></tr>
  </table>
</form>
</body>
</html>
	
