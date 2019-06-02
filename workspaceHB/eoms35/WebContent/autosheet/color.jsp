<%@ page contentType="text/html; charset=GB2312" %>
<html><head><title>颜色选择器</title></head>
<body align=center>
<form>
<P align=center><font size=-1 color=red>单击颜色，再单击关闭按钮<br>或直接双击选中颜色</font></p>
<table><tr>
<td colspan=4 bgcolor=balck onclick='window.returnValue="black";' ondblclick='window.returnValue="black";self.close();' align=center>默认颜色</td></tr>
<%
String colors[]={
"aqua",	"gray","navy","silver",
"black","green","olive","teal",
"blue","lime","purple","yellow",
"fuchsia","maroon","red","white"
};
for(int i=0;i<colors.length;i++){
	if(i%4==0)
		out.println("<tr>");
	out.println("<td bgcolor='"+colors[i]+"' onclick='window.returnValue=\""+colors[i]+"\";' ondblclick='window.returnValue=\""+colors[i]+"\";self.close();'>"+colors[i]+"</td>");
	if((i-3)>=0&&(i+1)%4==0)
		out.println("</tr>");
}
%>
<tr><td colspan=4 align=center>
<input type='button' value='关闭' onclick='if(window.returnValue==null){window.returnValue="black";}self.close();'>
</td></tr>

</table><br>

</form></center></body></html>