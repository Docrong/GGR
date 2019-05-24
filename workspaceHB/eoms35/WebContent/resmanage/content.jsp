<%@page contentType="text/html;charset=gb2312"%>
<html>
<head>
<title>无标题文档</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<%
if(request.getParameter("ErrMsg") != null)
	out.println("<script>alert('您没有权限查看该页！')</script>");
%>
<body background="images/bg_gear.gif" bgcolor="#eeeeee" text="#000000" class="content14">
<%
if(request.getParameter("stat") != null)
	out.println("<div><p><font size=2 color=red>目前您已掉线,请退出重新登录系统!</font></p></div>");
%>
<center>
  <img src="images/scdt.gif" border="0" usemap="#Map"> 
  <map name="Map">
    <area shape="rect" coords="221,123,328,143" href="intro/ABa.htm">
    <area shape="rect" coords="137,239,245,259" href="intro/GanZi.htm">
    <area shape="rect" coords="368,139,403,159" href="intro/MianYang.htm">
    <area shape="rect" coords="421,109,461,126" href="intro/GuangYuan.htm">
    <area shape="rect" coords="479,116,517,136" href="intro/BaZhong.htm">
    <area shape="rect" coords="514,157,551,175" href="intro/DaZhou.htm">
    <area shape="rect" coords="444,170,483,187" href="intro/NanChong.htm">
    <area shape="rect" coords="473,212,512,232" href="intro/GuangAn.htm">
    <area shape="rect" coords="413,213,447,230" href="intro/SuiNing.htm">
    <area shape="rect" coords="355,182,394,201" href="intro/DeYang.htm">
    <area shape="rect" coords="327,213,366,235" href="intro/ChengDu.htm">
    <area shape="rect" coords="388,238,423,257" href="intro/ZiYang.htm">
    <area shape="rect" coords="340,250,375,269" href="intro/MeiShan.htm">
    <area shape="rect" coords="386,269,421,286" href="intro/NeiJiang.htm">
    <area shape="rect" coords="383,293,419,310" href="intro/ZiGong.htm">
    <area shape="rect" coords="431,326,450,370" href="intro/LuZhou.htm">
    <area shape="rect" coords="388,343,423,362" href="intro/YiBin.htm">
    <area shape="rect" coords="322,305,356,323" href="intro/LeShan.htm">
    <area shape="rect" coords="275,268,309,291" href="intro/YaAn.htm">
    <area shape="rect" coords="219,400,327,421" href="intro/LiangShan.htm">
    <area shape="rect" coords="238,444,287,464" href="intro/PanZhiHua.htm">
    <area shape="rect" coords="395,33,513,59" href="resource/resCityList.jsp?cid=0">
  </map>
</center>
<br>
</body>
</html>
