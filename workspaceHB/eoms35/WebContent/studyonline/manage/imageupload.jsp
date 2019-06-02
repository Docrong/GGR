<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.jspsmart.upload.*" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.studyonline.dao.*,java.io.File"%>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="GENERATOR" content="IBM WebSphere Studio">
<html>
<head>
<title>
图片导入
</title>
<script language="JavaScript" >
  function gohead(){
    history.go(-1);
  }

  function addattach()
{
  if (document.all.ImportForm.attachName.value==""){
      alert("请先浏览附件");
      return false;
  }
  else
  {
       var fileStr= document.all.ImportForm.attachName.value;
       var index=fileStr.lastIndexOf("\\");
       fileStr=fileStr.substring(index+1,fileStr.length);
       //document.all.ImportForm.fileName.value=fileStr;
       document.all.ImportForm.action = document.all.ImportForm.action + "?del=add&fileName="
          + fileStr + "&subId=<%=StaticMethod.null2String(request.getParameter("subId"))%>"
          + "&delName=" + document.all.ImportForm.fileName.value;
       document.all.ImportForm.submit();
       return true;
  }
}
function delattach()
{
  if (document.all.ImportForm.fileName.value==""){
      return false;
  }
  else
  {
       document.all.ImportForm.action = document.all.ImportForm.action + "?del=del"
          + "&delName=" + document.all.ImportForm.fileName.value
          + "&subId=<%=StaticMethod.null2String(request.getParameter("subId"))%>";
       document.all.ImportForm.submit();
       return true;
  }
}
</script>

<%
String fileName = StaticMethod.getGBString(StaticMethod.null2String(request.getParameter("fileName")));
//删除
String delName = StaticMethod.getGBString(StaticMethod.null2String(request.getParameter("delName")));
if( !"".equalsIgnoreCase( StaticMethod.null2String( request.getParameter("del") ) )
    && !"".equalsIgnoreCase( delName )){
  StudyOnlineDAO DAO = new StudyOnlineDAO();
  DAO.updateImage( StaticMethod.null2String( request.getParameter("subId") ) , "" );
  delName = pageContext.getServletContext().getRealPath("/") + "studyonline" + File.separator + "upload" + File.separator + delName;
  System.out.println(delName);
  java.io.File f = new java.io.File(delName);
  f.delete();
}
if( "add".equalsIgnoreCase( StaticMethod.null2String( request.getParameter("del") ) ) ){
  //判断是否新增
  try {
        SmartUpload su = new SmartUpload();
        // 上传初始化
        su.initialize(pageContext);
        // 设定上传限制
        // 1.限制每个上传文件的最大长度。
        su.setMaxFileSize(100000000);
        // 2.限制总上传数据的长度。
        su.setTotalMaxFileSize(200000000);
        // 3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。
        su.setAllowedFilesList("jpg,JPG,gif,GIF,bmp,BMP");
        // 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,
        //jsp,htm,html扩展名的文件和没有扩展名的文件。
        su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
        // 上传文件
        su.upload();

        if( !"".equalsIgnoreCase(fileName) ){
          int count = 0;
          // 将上传文件全部保存到指定目录
          count = su.save("/studyonline/upload");
          //将文件名转为日期+文件名的形式
          String dirName = pageContext.getServletContext().getRealPath("/")
                  + "studyonline" + File.separator + "upload" + File.separator + fileName;
          String tempName = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss")
             + fileName.substring(fileName.lastIndexOf(".")) ;
          java.io.File f1 = new java.io.File(dirName);
          java.io.File f2 = new java.io.File( f1.getParent() + File.separator + tempName);
          f1.renameTo(f2);
          fileName = tempName;

          StudyOnlineDAO DAO = new StudyOnlineDAO();
          DAO.updateImage( StaticMethod.null2String( request.getParameter("subId") ) , fileName );
          System.out.println("文件:‘" + fileName + "’已上传成功");
       }
  }
      catch (SmartUploadException sue) {
        response.sendRedirect("imageupload.jsp?errMSG=true");
        //sue.printStackTrace();
      }
      catch (SecurityException se) {
        //se.printStackTrace();
        response.sendRedirect("imageupload.jsp?errMSG=true");
      }
      catch (Exception e) {
        System.out.println("Unable to upload the file.<br>");
        System.out.println("Error : " + e.toString());
      }
}
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/upload.css" type="text/css">
</head>
<body >
<FORM name="ImportForm" METHOD="POST" ACTION="imageupload.jsp" ENCTYPE="multipart/form-data">
<input type="hidden" name="fileName"  value="<%=fileName%>">
<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">
<%
   if(!"".equalsIgnoreCase(StaticMethod.null2String(request.getParameter("errMSG")))){
     out.print("<tr><td><font color='red'>上传文件类型错误</font></td></tr>");
   }
%>
 <tr >
    <td >
      <input type="file" name="attachName" class="clstext">
      <INPUT TYPE="button" VALUE="上传" class="clsBtn" onclick="return addattach();">
      <INPUT TYPE="button" VALUE="删除" class="clsBtn" onclick="return delattach();">
    </td>
 </tr>
<%
   if(!"".equalsIgnoreCase( fileName )
   && !"del".equalsIgnoreCase(StaticMethod.null2String(request.getParameter("del")))){
     out.print("<tr><td><a href='/EOMS_J2EE/studyonline/manage/view.jsp?fileName=" + fileName + "' target='_blank'><font color='blue'>" + fileName + "</font></a></td></tr>");
   }
%>
 </tr>
</table>
</form>
</body>
</html>
