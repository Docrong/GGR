<%@ page contentType="text/html; charset=gb2312" language="java"
import="java.util.*,com.boco.eoms.filemanage.*,com.boco.eoms.filemanage.appinfo.*,
java.io.File,com.boco.eoms.attachment.dao.TawAttachmentDAO,
com.boco.eoms.attachment.model.TawAttachment,
com.boco.eoms.db.util.ConnectionPool,
com.boco.eoms.common.controller.SaveSessionBeanForm" errorPage="" %>

<head>

<title>文件上传处理页面</title>

</head>


<%
String fileIdList = "";
String[] fileIds;
String removeIdList = "";
String[] removeIds;


AppInformations appInformations = AppInformations.getInstance();
AppInformation aAppInformation = appInformations.get(request.getParameter("app"));

fileIdList = request.getParameter("filelist");
fileIds = fileIdList.split(",");

removeIdList = request.getParameter("removeid");
removeIdList = removeIdList == null?"":removeIdList;
removeIds = removeIdList.split(",");

for(int i=0; i<removeIds.length; i++)
{
  for(int j=0; j<fileIds.length; j++)
  {
    if(removeIds[i].equals(fileIds[j]))
    {
      fileIds[j] = "";
    }
  }
}
fileIdList ="";
for(int j=0; j<fileIds.length; j++)
{
  if(!fileIds[j].equals(""))
  {
    fileIdList = fileIdList + "," + fileIds[j];
  }
}
if(!fileIdList.equals(""))
{
  fileIdList = fileIdList.substring(1,fileIdList.length());
}

ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO(ds);

List list = tawAttachmentDAO.list(removeIdList);

TawAttachment tawAttachment = null;
SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");

for(int i=0; i<list.size(); i++)
{
  tawAttachment = (TawAttachment)list.get(i);
  tawAttachmentDAO.delete(tawAttachment.getAttachmentId());
  java.io.File file = new java.io.File(saveSessionBeanForm.getRealPath() + aAppInformation.getPath() + File.separator + tawAttachment.getAttachmentFilename());
  file.delete();
}

response.sendRedirect("do_upload.jsp?app=" + aAppInformation.getAppCode() + "&filelist=" + fileIdList + "&idfile=" + request.getParameter("idfile"));
%>

