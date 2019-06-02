<%@ page language="java" pageEncoding="UTF-8" %>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);

%>
<html>
  <%
      String addonsid = request.getParameter("addonsid");
      String url = (String)request.getAttribute("url");
      String action = (String)request.getAttribute("action");
      String model = request.getParameter("model");
      String myid = request.getParameter("myid");
      String xmltype = (String)request.getAttribute("xmltype");
      String reaction = request.getParameter("reaction");
      String window = request.getParameter("window");
	//显示XMLHtml
	StringBuffer StrBuffer =(StringBuffer)request.getAttribute("strbuffer");
%>
<head>
<title>附加表</title>

<SCRIPT language=JavaScript src="../css/Validator.js">
</SCRIPT>

<style>
<!--配置右键style
.skin0 {
position:absolute;
text-align:left;
width:200px;
border:2px solid black;
background-color:menu;
font-family:Verdana;
line-height:20px;
cursor:default;
visibility:hidden;
}
.skin1 {
cursor:default;
font:menutext;
position:absolute;
text-align:left;
font-family: Arial, Helvetica, sans-serif;
font-size: 10pt;
width:120px;
background-color:menu;
border:1 solid buttonface;
visibility:hidden;
border:2 outset buttonhighlight;
}
.menuitems {
padding-left:15px;
padding-right:10px;
}
-->
</style>
</script>

<script language="JavaScript">
</script>
</head>

<body>
  <base target="_self">
<table align=center border=0 cellspacing="1" cellpadding="1" class="table_show">
<%=StrBuffer%>

</tr>
</table>
</body>
</html>
