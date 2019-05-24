<%@ page contentType="text/html;charset=gb2312" import="com.pud.study.file.*,
com.pud.study.file.appinfo.*,com.boco.eoms.attachment.dao.TawAttachmentDAO,
com.boco.eoms.attachment.model.TawAttachment,com.boco.eoms.db.util.ConnectionPool,com.boco.eoms.common.util.StaticMethod" %>

<%
try
{
  ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
  TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO(ds);
  AppInformations appInformations = AppInformations.getInstance();
  AppInformation aAppInformation = appInformations.get(request.getParameter("app"));
  TawAttachment tawAttachment = tawAttachmentDAO.retrieve(request.getParameter("fileid"));
  SmartUpload su = new SmartUpload();
  su.initialize(pageContext);
  su.setContentDisposition(null);
  su.downloadFile(aAppInformation.getPath()+"/"+StaticMethod.dbNull2String(tawAttachment.getAttachmentFilename()),"",tawAttachment.getAttachmentName());
}
catch(Exception e)
{
e.printStackTrace();
}
%>