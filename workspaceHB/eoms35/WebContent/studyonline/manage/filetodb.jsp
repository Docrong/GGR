<%@ page contentType="text/html; charset=gb2312" language="java" import="com.boco.eoms.studyonline.bo.*, com.boco.eoms.common.controller.SaveSessionBeanForm, com.boco.eoms.common.util.StaticMethod,
java.io.File, java.io.IOException,java.util.*"%>

<HTML>
<HEAD>
<TITLE>��ʾ��Ϣ</TITLE>
</HEAD>

<BODY>
<%
   String fileName = StaticMethod.getGBString(StaticMethod.null2String(request.getParameter("fileName")));
   fileName = pageContext.getServletContext().getRealPath("/") + "studyonline" + File.separator + "upload" + File.separator + fileName;
   String errMsg = "";
   String tip = null;

   SaveSessionBeanForm sessionForm = (SaveSessionBeanForm) session.getAttribute("SaveSessionBeanForm");
   String userName = sessionForm.getWrf_UserName();

   boolean flag = false;

   StudyOnlineBO studyOnlineBO = new StudyOnlineBO();
   String tip2 = null;

   try {
       tip2 = studyOnlineBO.importWarehouse( fileName , userName );
       tip = "��ʾ";
   } catch (Exception ex) {
       ex.printStackTrace();
       errMsg = ex.getMessage();
       tip = "����";
   }

%>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr valign="top">
   <td>
<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr valign=bottom>
    <td width=40><img src="<%= request.getContextPath()%>/images/studyonline/it.jpg" width="120" height="85"></td>
    <td nowrap><B><%=tip %></B></td>
    <td align=right><br></td>
  </tr>
</table>
<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr><td height="2" bgcolor="#9BB7EB"></td></tr>
  <tr><td height="2" bgcolor="#9BB7EB"></td></tr>
</table>

<p>&nbsp;</p>
<%
   if(!tip2.equalsIgnoreCase("error")){
     if( tip2.equalsIgnoreCase("all") ){
%>
     <p align="center">�����ѵ���ɹ�</p>
<%     }
     else{
%>
       <p align="center">�����ѵ���.</p>
       <p align="center"><font color="red"><%=tip2 %>�ɹ�.��鿴����ȱʧ���</font></p>
<%     }
   }
   else{
%>
<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr><td height="2" width="30%">&nbsp;</td><td height="2" width="68%">�������ݷ������󣬿���������ԭ��</td></tr>
  <tr><td height="2" width="30%">&nbsp;</td><td height="2" width="68%">1�������excel�ļ�ģ�岻��ȷ</td></tr>
  <tr><td height="2" width="30%">&nbsp;</td><td height="2" width="68%">2�������excel�ļ����ݲ���ȷ</td></tr>
  <tr><td height="2" width="30%">&nbsp;</td><td height="2" width="68%">3��ϵͳ����</td></tr>
  <tr><td height="2" width="30%">&nbsp;</td><td height="2" width="68%">���صĴ�����Ϣ�ǣ�<%= errMsg%></td></tr>
</table>
<%
   }
%>
</table>
</BODY>
</HTML>
