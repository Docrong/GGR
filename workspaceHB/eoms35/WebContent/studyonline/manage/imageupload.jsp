<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.jspsmart.upload.*" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.studyonline.dao.*,java.io.File"%>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="GENERATOR" content="IBM WebSphere Studio">
<html>
<head>
<title>
ͼƬ����
</title>
<script language="JavaScript" >
  function gohead(){
    history.go(-1);
  }

  function addattach()
{
  if (document.all.ImportForm.attachName.value==""){
      alert("�����������");
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
//ɾ��
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
  //�ж��Ƿ�����
  try {
        SmartUpload su = new SmartUpload();
        // �ϴ���ʼ��
        su.initialize(pageContext);
        // �趨�ϴ�����
        // 1.����ÿ���ϴ��ļ�����󳤶ȡ�
        su.setMaxFileSize(100000000);
        // 2.�������ϴ����ݵĳ��ȡ�
        su.setTotalMaxFileSize(200000000);
        // 3.�趨�����ϴ����ļ���ͨ����չ�����ƣ�,������doc,txt�ļ���
        su.setAllowedFilesList("jpg,JPG,gif,GIF,bmp,BMP");
        // 4.�趨��ֹ�ϴ����ļ���ͨ����չ�����ƣ�,��ֹ�ϴ�����exe,bat,
        //jsp,htm,html��չ�����ļ���û����չ�����ļ���
        su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
        // �ϴ��ļ�
        su.upload();

        if( !"".equalsIgnoreCase(fileName) ){
          int count = 0;
          // ���ϴ��ļ�ȫ�����浽ָ��Ŀ¼
          count = su.save("/studyonline/upload");
          //���ļ���תΪ����+�ļ�������ʽ
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
          System.out.println("�ļ�:��" + fileName + "�����ϴ��ɹ�");
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
     out.print("<tr><td><font color='red'>�ϴ��ļ����ʹ���</font></td></tr>");
   }
%>
 <tr >
    <td >
      <input type="file" name="attachName" class="clstext">
      <INPUT TYPE="button" VALUE="�ϴ�" class="clsBtn" onclick="return addattach();">
      <INPUT TYPE="button" VALUE="ɾ��" class="clsBtn" onclick="return delattach();">
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
