<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%
String app = request.getContextPath();
%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function valuetoparent(i){
  var text = document.getElementById('dbName'+i).value;
//  alert(text);
if(opener.document.form1.paramTypes.value=="")
  opener.document.form1.paramTypes.value=text;
else opener.document.form1.paramTypes.value+=","+text;
  window.close();
}
</script>
</head>
<body>
<form name="dbform" action="">
<table class="list_table">
<tr>
  <th></td>
  <th>��������</td>
  <th>����</td>
</tr>
<tr>
  <th class="spec"><input type="radio" id="dbName0" name="dbName" value="String" onclick="javascript:valuetoparent(0)"></td>
  <td>String</td>
  <td>�ַ�������</td>
</tr>
<tr>
  <th class="spec"><input type="radio" id="dbName1" name="dbName" value="char" onclick="javascript:valuetoparent(1)"></td>
  <td>char</td>
  <td>�ַ�����</td>
</tr>
<tr>
  <th class="spec"><input type="radio" id="dbName2" name="dbName" value="int" onclick="javascript:valuetoparent(2)"></td>
  <td>int</td>
  <td>����</td>
</tr>
<tr>
  <th class="spec"><input type="radio" id="dbName3" name="dbName" value="long" onclick="javascript:valuetoparent(3)"></td>
  <td>long</td>
  <td>������</td>
</tr>
<tr>
  <th class="spec"><input type="radio" id="dbName4" name="dbName" value="short" onclick="javascript:valuetoparent(4)"></td>
  <td>short</td>
  <td>������</td>
</tr>
<tr>
  <th class="spec"><input type="radio" id="dbName5" name="dbName" value="boolean" onclick="javascript:valuetoparent(5)"></td>
  <td>boolean</td>
  <td>��������</td>
</tr>
<tr>
  <th class="spec"><input type="radio" id="dbName6" name="dbName" value="float" onclick="javascript:valuetoparent(6)"></td>
  <td>float</td>
  <td>�����ȸ�����</td>
</tr>
<tr>
  <th class="spec"><input type="radio" id="dbName7" name="dbName" value="double" onclick="javascript:valuetoparent(7)"></td>
  <td>double</td>
  <td>˫���ȸ�����</td>
</tr>
</table>
</form>
</body>
</html>