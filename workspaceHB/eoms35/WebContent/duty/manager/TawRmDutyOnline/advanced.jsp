<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>�߼�����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META http-equiv="refresh" content="30">
<link rel="stylesheet" href="chat.css">

<script language="javascript">
function checkForm() {
	if (document.changeNameForm.changeName.value=="") {
		alert("���ֲ���Ϊ��");
		return false;
	}
	if (document.changeNameForm.changeName.value.length>8) {
		alert("���ֲ���̫��");
		return false;
	}
	return true;
}
</script>

</head>

<body bgcolor="#FF9900">
<p align="center"><font size="3" color="#FF0000"><b>�߼�����</b></font></p>
<p align="center"> (ֻ����ʦ������ЩȨ�ޣ������뿴<a href="../bbs/rule.htm">��ϸ����</a>) </p>
<table width="350" border="0" align="center">
  <tr>
<td colspan="2">
<div align="center"><font color="red">����</font>��(�������ֵ��Ŷ����������)</div>
</td>
</tr>
<tr><form action="kick.jsp" method="post" name="kickForm">
<td>
<select name="userName">
	<%
	synchronized(application) {
		Vector ListUser=null;
		ListUser =(Vector)application.getAttribute("UserName");
		if(ListUser!=null) {
			String User = null;
			for(int i=0;i<ListUser.size();i++) {
				User= (String) ListUser.get(i);
				%>
				<option><%= User %></option>
				<%
			}
		}
	}
	%>
</select>
        ����
<input type="password" name="password">
</td>
<td>
<input type="submit" name="Submit" value="����">
</td>
</form>
</tr>
<tr><td colspan="2">&nbsp;</td></tr>

<tr>
<td colspan="2">
<div align="center"><font color="red">��������</font>��(�����������ʱ��ɹ���)</div>
</td>
</tr>
<tr>
<form action="changename.jsp" method="post" name="changeNameForm">
<td>
        <input type="text" name="changeName" size="30">
</td>
<td>
<input type="submit" name="changeNameSubmit" value="����" onclick="return checkForm();">
</td>
</form>
</tr>

<tr><td colspan="2">&nbsp;</td></tr>

<tr>
<td colspan="2">
<div align="center"><font color="red">���붯��</font>��(�����ܻ�δʵ��)</div>
</td>
</tr>
<tr>
<form action="" method="post">
<td>
        <input type="text" name="textfield" size="50">
</td>
<td>
<input type="submit" name="Submit2" value="����">
</td>
</form>
</tr>
</table>
<p align="center"><a href="javascript:window.close();">�ر�</a></p>
</body>
</html>
