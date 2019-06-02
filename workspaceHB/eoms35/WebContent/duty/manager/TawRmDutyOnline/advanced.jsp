<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>高级功能</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META http-equiv="refresh" content="30">
<link rel="stylesheet" href="chat.css">

<script language="javascript">
function checkForm() {
	if (document.changeNameForm.changeName.value=="") {
		alert("名字不能为空");
		return false;
	}
	if (document.changeNameForm.changeName.value.length>8) {
		alert("名字不能太长");
		return false;
	}
	return true;
}
</script>

</head>

<body bgcolor="#FF9900">
<p align="center"><font size="3" color="#FF0000"><b>高级功能</b></font></p>
<p align="center"> (只有巫师才有这些权限，具体请看<a href="../bbs/rule.htm">详细规则</a>) </p>
<table width="350" border="0" align="center">
  <tr>
<td colspan="2">
<div align="center"><font color="red">踢人</font>　(会减经验值的哦，还是少用)</div>
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
        密码
<input type="password" name="password">
</td>
<td>
<input type="submit" name="Submit" value="踢人">
</td>
</form>
</tr>
<tr><td colspan="2">&nbsp;</td></tr>

<tr>
<td colspan="2">
<div align="center"><font color="red">换个名字</font>　(换名后身份暂时变成过客)</div>
</td>
</tr>
<tr>
<form action="changename.jsp" method="post" name="changeNameForm">
<td>
        <input type="text" name="changeName" size="30">
</td>
<td>
<input type="submit" name="changeNameSubmit" value="换名" onclick="return checkForm();">
</td>
</form>
</tr>

<tr><td colspan="2">&nbsp;</td></tr>

<tr>
<td colspan="2">
<div align="center"><font color="red">加入动作</font>　(本功能还未实现)</div>
</td>
</tr>
<tr>
<form action="" method="post">
<td>
        <input type="text" name="textfield" size="50">
</td>
<td>
<input type="submit" name="Submit2" value="加入">
</td>
</form>
</tr>
</table>
<p align="center"><a href="javascript:window.close();">关闭</a></p>
</body>
</html>
