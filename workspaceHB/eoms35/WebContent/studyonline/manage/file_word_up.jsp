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
<TITLE>���ݵ���</TITLE>
<style type="text/css">
<!--
body {  font-family: "����"; font-size: 12px}
table {  font-family: "����"; font-size: 12px}
-->
</style>
<%!
  private static final String ERR_MSG = "<strong>�ϴ��ļ�ʧ�ܣ���������������ԭ��</strong>"+
  										   "<div align='left'> 1. ����æ�� </div> "+
  										   "<div align='left'> 2. ���ϴ����ļ�����ϵͳ����ĵ����ļ���󲻳���8M��</div>" +
  										   "<div align='left'> 3. �����ļ���ʽ���ԡ�ϵͳ������ļ���ʽΪdoc��</div>"+
  										   "<div align='left'> 4. �����ļ��ڷ��������Ѿ����ڡ� </div>"+
  										   "<strong>��ȷ�Ϻ��ش������������⣬����ϵͳ����Ա��ϵ</strong>";

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
      // �ϴ���ʼ��
      su.initialize(pageContext);
      // �趨�ϴ�����
      // 1.����ÿ���ϴ��ļ�����󳤶ȡ�
      su.setMaxFileSize(100000000);
      // 2.�������ϴ����ݵĳ��ȡ�
      su.setTotalMaxFileSize(200000000);
      // 3.�趨�����ϴ����ļ���ͨ����չ�����ƣ�,������doc,txt�ļ���
      su.setAllowedFilesList("doc,DOC");
      // 4.�趨��ֹ�ϴ����ļ���ͨ����չ�����ƣ�,��ֹ�ϴ�����exe,bat,
      //jsp,htm,html��չ�����ļ���û����չ�����ļ���
      su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
      // �ϴ��ļ�
      su.upload();
      // ���ϴ��ļ�ȫ�����浽ָ��Ŀ¼
      count = su.save("/studyonline/upload");

      fileName = StaticMethod.null2String(su.getRequest().getParameter("fileName"));
      specialtySel=StaticMethod.null2String(su.getRequest().getParameter("specialtySel"));
      manufacturerSel=StaticMethod.null2String(su.getRequest().getParameter("manufacturerSel"));
      equipIdSel=StaticMethod.null2String(su.getRequest().getParameter("equipIdSel"));
      issueType=StaticMethod.null2String(su.getRequest().getParameter("issueType"));
        s_value=StaticMethod.null2String(su.getRequest().getParameter("s_value"));
      System.out.println("�ļ�:��" + fileName + "�����ϴ��ɹ�");
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
      <FONT size=4 color="#0000FF" face="����"><b>���ݵ���</b></FONT>
    </td>
  </tr>
  <tr>
     <td align="center" width="100%" valign="center" height="100%">
      <p align="center"><img src="../../images/studyonline/wait.GIF" align="bottom" width="60" height="60"></p>
      <p align="center">��</p>
      <p align="center"><FONT size=4 color="#800040" face="����">���ڵ�������,���Ժ�....</FONT></p>
    </td>
  </tr>
</table>
</BODY>
</HTML>




