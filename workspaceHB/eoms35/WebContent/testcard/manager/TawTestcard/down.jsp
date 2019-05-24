
<%@ page contentType="text/html;charset=gb2312" import="com.pud.study.file.*,
com.pud.study.file.appinfo.*,com.boco.eoms.common.util.StaticMethod" %>

<%
try
{
  String path = request.getParameter("path");
  String fileName = new String (request.getParameter("fileName").getBytes("ISO8859_1"),"GB2312");
  SmartUpload su = new SmartUpload();
  su.initialize(pageContext);
  su.setContentDisposition(null);
  su.downloadFile("testcard/"+path+"/"+fileName,"",fileName);
}
catch(Exception e)
{
e.printStackTrace();
}
%>
