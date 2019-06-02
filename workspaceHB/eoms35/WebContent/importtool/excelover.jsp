<%@ page language="java" contentType="text/html;charset=GB2312" %>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%

 String excelname = request.getParameter("excelname");
 String xmlname = request.getParameter("xmlname");
  try
  {
  java.io.File ff=new java.io.File("");
  String savepath = ff.getAbsolutePath()+"/"+excelname;
  java.io.File myCol = new java.io.File(savepath);
  myCol.delete();

  String savepath1 = ff.getAbsolutePath()+"/"+xmlname;
  java.io.File myCol1 = new java.io.File(savepath1);
  myCol1.delete();

  }catch(Exception e)
  	{
  		out.println("删除文件错误："+e.getMessage());
  	}
  	//out.println("<meta http-equiv='Refresh' content='3;url=excelview.jsp?xmlname="+xmlname+"'>");
%>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
</head>

<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
  <tr>
    <td height="20">&nbsp;</td>
  </tr>
</table>

<table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
  <tbody>
  <tr>
    <td height="27" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td height="27">　　　
      <div align="center"><font color="#FF0000">EXCEL 数据导入完成，如果导入有问题，请检查日志！</font><font color="#FF0000">。。。。。。。</font></div>
    </td>
  </tr>
  <tr>
    <td height="27">&nbsp;</td>
  </tr>
  <tr align="center">
    <td height="27"><a href="javascript:history.back();">返回</a></td>
  </tr>
</table>

</body>
</html>