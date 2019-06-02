<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
</head>
<body>
<div align="center">
<B>--心情转换--</B></div>
<BR>
<table cellpadding=6 cellspacing=1 align=center >
    <tr >
      <th height="26" colspan="4" align="center">
         请点击图标添加到内容</A></th>
    </tr>
<%
String ii="";
int h=0;
for (int i=1;i<29;i++)
{
        h+=1;
	if (i<10)
	  ii="0" + i;
	else
	  ii=String.valueOf(i);
	out.println("<td align=center><img src='../images/emot/em" + ii + ".gif' border=0 onclick=\"insertsmilie('em" + ii + "')\" style=\"CURSOR: hand\"></td>");
	if (h==7)
	{
	  out.println("</tr><tr>");
	  h=0;
	}
}
%>
 <tr >
      <td colspan="4" >
        <p align="center"><input type=submit name="winclose" value="关闭" onclick=window.close();></td>
    </tr>
  </table>
</body></html>
<script language="javascript">
function insertsmilie(smilieface){
        alert("表情符已加入");
	self.opener.tawInformationForm.body.value+="[emot]" + smilieface + ".gif[/emot]";
}
</script>