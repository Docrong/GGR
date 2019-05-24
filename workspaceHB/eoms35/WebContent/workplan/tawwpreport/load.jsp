<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<head>
<title>
loading
</title>
<%
String time=request.getParameter("time");
String year=request.getParameter("year");
int type=StaticMethod.null2int(request.getParameter("type"));
%>
<SCRIPT>

//cnease=window.open("","etangWHSAD","top=2000");cnease.close();
//focus()
//self.resizeTo(800,600)
//self.moveTo(-3,-3)

</SCRIPT>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
td,form,select,input,textarea,body  {font-family: 宋体;font-color:#010000;font-size: 15px;line-height:150%;letter-spacing:2px}
a:link { font-size: 9pt; text-decoration: none; color: #666666}
a:visited {  font-size:9pt; text-decoration: none; color: #666666}
a:hover {  text-decoration: none; font-size: 9pt; color: #666666}
a:active {  font-size: 9px; text-decoration: none; color: #666666}
</style>
<script>
var url='report.do?time=<%=time%>&&type=<%=type%>&&year=<%=year%>';
</script>
</head>
<body onLoad="location.href=url;" style='overflow:hidden;overflow-y:hidden'>
<div align=center>
<table height=70% valign=middle align=center>
<tr>
<td align="center" disabled>
<!--  Displaytext-->
<p></p>
<font >数据处理中，请勿中断......<br>
</font>
<p></p><p></p>
<style><!--.proccess{border:1px solid;width:8;height:8;background:#ffffff;margin:3}--></style>
<p></p><p></p>
<div align="center">
<form method=post name=proccess>
<script language=javascript>
for(i=0;i<30;i++)document.write("<input class=proccess>")
</script>
</form>
</div></td></tr></table>
<div align="center">
<script language=JavaScript>var p=0,j=0;
var c=new Array("lightskyblue","white")
setInterval('proccess();',300)
function proccess(){
document.forms.proccess.elements[p].style.background=c[j];
p+=1;
if(p==30){p=0;j=1-j;}}
--></script>
</div>
</div>
<div align="center">
<script>
//<!--
//if (document.layers)
//document.write('<Layer src="'+ url + ' " VISIBILITY="hide"> </Layer>');
//else if (document.all || document.getElementById)
//document.write('<iframe src="'+ url + '" style="visibility: hidden;"></iframe>');
//else location.href=url;
//-->
</script>
</div>
