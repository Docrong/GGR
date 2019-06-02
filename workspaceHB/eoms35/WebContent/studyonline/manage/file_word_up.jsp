<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%@ page import="com.jspsmart.upload.*" %>
<%@ page import="com.boco.eoms.common.util.*" %>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>数据导入</TITLE>
<style type="text/css">
<!--
body {  font-family: "宋体"; font-size: 12px}
table {  font-family: "宋体"; font-size: 12px}
-->
</style>
<%!
  private static final String ERR_MSG = "<strong>上传文件失败，可能是由于以下原因：</strong>"+
  										   "<div align='left'> 1. 网络忙。 </div> "+
  										   "<div align='left'> 2. 您上传的文件过大。系统允许的单个文件最大不超过8M。</div>" +
  										   "<div align='left'> 3. 您的文件格式不对。系统允许的文件格式为doc。</div>"+
  										   "<div align='left'> 4. 您的文件在服务器上已经存在。 </div>"+
  										   "<strong>请确认后重传，若仍有问题，请与系统管理员联系</strong>";

%>
<%  String fileName = "";
    String specialtySel ="";
    String manufacturerSel ="";
    String equipIdSel ="";
    String issueType="";
    String s_value="";
    SmartUpload su = new SmartUpload();
    int count = 0;
    try {
      // 上传初始化
      su.initialize(pageContext);
      // 设定上传限制
      // 1.限制每个上传文件的最大长度。
      su.setMaxFileSize(100000000);
      // 2.限制总上传数据的长度。
      su.setTotalMaxFileSize(200000000);
      // 3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。
      su.setAllowedFilesList("doc,DOC");
      // 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,
      //jsp,htm,html扩展名的文件和没有扩展名的文件。
      su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
      // 上传文件
      su.upload();
      // 将上传文件全部保存到指定目录
      count = su.save("/studyonline/upload");

      fileName = StaticMethod.null2String(su.getRequest().getParameter("fileName"));
      specialtySel=StaticMethod.null2String(su.getRequest().getParameter("specialtySel"));
      manufacturerSel=StaticMethod.null2String(su.getRequest().getParameter("manufacturerSel"));
      equipIdSel=StaticMethod.null2String(su.getRequest().getParameter("equipIdSel"));
      issueType=StaticMethod.null2String(su.getRequest().getParameter("issueType"));
        s_value=StaticMethod.null2String(su.getRequest().getParameter("s_value"));
      System.out.println("文件:‘" + fileName + "’已上传成功");
    }
    catch (SmartUploadException sue) {
      response.sendRedirect(
          "tip.jsp?msg="+StaticMethod.getString(ERR_MSG));
      //sue.printStackTrace();
    }
    catch (SecurityException se) {
      //se.printStackTrace();
      response.sendRedirect("tip.jsp?msg="+StaticMethod.getString(ERR_MSG));
    }
    catch (Exception e) {
      System.out.println("Unable to upload the file.<br>");
      System.out.println("Error : " + e.toString());
    }
%>
</HEAD>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff" onload="location.replace('fileload.jsp?fileName=<%=fileName%>&specialtySel=<%=specialtySel%>&manufacturerSel=<%=manufacturerSel%>&equipIdSel=<%=equipIdSel%>&issueType=<%=issueType%>&s_value=<%=s_value%>')">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
     <td align="center" width="100%" valign="center" height="48">
      <FONT size=4 color="#0000FF" face="宋体"><b>数据导入</b></FONT>
    </td>
  </tr>
  <tr>
     <td align="center" width="100%" valign="center" height="100%">
      <p align="center"><img src="../../images/studyonline/wait.GIF" align="bottom" width="60" height="60"></p>
      <p align="center">　</p>
      <p align="center"><FONT size=4 color="#800040" face="宋体">正在导入数据,请稍候....</FONT></p>
    </td>
  </tr>
</table>
</BODY>
</HTML>




